package com.badlogic.gdx.backends.android;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View.OnGenericMotionListener;
import android.view.View.OnKeyListener;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.badlogic.gdx.AbstractInput;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Input.OnscreenKeyboardType;
import com.badlogic.gdx.Input.Orientation;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.backends.android.surfaceview.GLSurfaceView20;
import com.badlogic.gdx.utils.Pool;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultAndroidInput extends AbstractInput implements AndroidInput {
    static class KeyEvent {
        static final int KEY_DOWN = 0;
        static final int KEY_TYPED = 2;
        static final int KEY_UP = 1;
        char keyChar;
        int keyCode;
        long timeStamp;
        int type;

    }

    class SensorListener implements SensorEventListener {
        @Override  // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor0, int v) {
        }

        @Override  // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent0) {
            if(sensorEvent0.sensor.getType() == 1) {
                if(DefaultAndroidInput.this.nativeOrientation == Orientation.Portrait) {
                    System.arraycopy(sensorEvent0.values, 0, DefaultAndroidInput.this.accelerometerValues, 0, DefaultAndroidInput.this.accelerometerValues.length);
                }
                else {
                    DefaultAndroidInput.this.accelerometerValues[0] = sensorEvent0.values[1];
                    DefaultAndroidInput.this.accelerometerValues[1] = -sensorEvent0.values[0];
                    DefaultAndroidInput.this.accelerometerValues[2] = sensorEvent0.values[2];
                }
            }
            if(sensorEvent0.sensor.getType() == 2) {
                System.arraycopy(sensorEvent0.values, 0, DefaultAndroidInput.this.magneticFieldValues, 0, DefaultAndroidInput.this.magneticFieldValues.length);
            }
            if(sensorEvent0.sensor.getType() == 4) {
                if(DefaultAndroidInput.this.nativeOrientation == Orientation.Portrait) {
                    System.arraycopy(sensorEvent0.values, 0, DefaultAndroidInput.this.gyroscopeValues, 0, DefaultAndroidInput.this.gyroscopeValues.length);
                }
                else {
                    DefaultAndroidInput.this.gyroscopeValues[0] = sensorEvent0.values[1];
                    DefaultAndroidInput.this.gyroscopeValues[1] = -sensorEvent0.values[0];
                    DefaultAndroidInput.this.gyroscopeValues[2] = sensorEvent0.values[2];
                }
            }
            if(sensorEvent0.sensor.getType() == 11) {
                if(DefaultAndroidInput.this.nativeOrientation == Orientation.Portrait) {
                    System.arraycopy(sensorEvent0.values, 0, DefaultAndroidInput.this.rotationVectorValues, 0, DefaultAndroidInput.this.rotationVectorValues.length);
                    return;
                }
                DefaultAndroidInput.this.rotationVectorValues[0] = sensorEvent0.values[1];
                DefaultAndroidInput.this.rotationVectorValues[1] = -sensorEvent0.values[0];
                DefaultAndroidInput.this.rotationVectorValues[2] = sensorEvent0.values[2];
            }
        }
    }

    static class TouchEvent {
        static final int TOUCH_DOWN = 0;
        static final int TOUCH_DRAGGED = 2;
        static final int TOUCH_MOVED = 4;
        static final int TOUCH_SCROLLED = 3;
        static final int TOUCH_UP = 1;
        int button;
        int pointer;
        int scrollAmountX;
        int scrollAmountY;
        long timeStamp;
        int type;
        int x;
        int y;

    }

    public static final int NUM_TOUCHES = 20;
    final float[] R;
    public boolean accelerometerAvailable;
    private SensorEventListener accelerometerListener;
    protected final float[] accelerometerValues;
    final Application app;
    private float azimuth;
    int[] button;
    private boolean compassAvailable;
    private SensorEventListener compassListener;
    private final AndroidApplicationConfiguration config;
    final Context context;
    private long currentEventTimeStamp;
    int[] deltaX;
    int[] deltaY;
    private final ArrayList genericMotionListeners;
    public boolean gyroscopeAvailable;
    private SensorEventListener gyroscopeListener;
    protected final float[] gyroscopeValues;
    private Handler handle;
    final boolean hasMultitouch;
    private boolean[] justPressedButtons;
    private boolean justTouched;
    ArrayList keyEvents;
    ArrayList keyListeners;
    boolean keyboardAvailable;
    protected final float[] magneticFieldValues;
    private SensorManager manager;
    private final AndroidMouseHandler mouseHandler;
    protected final Orientation nativeOrientation;
    final float[] orientation;
    private float pitch;
    float[] pressure;
    private InputProcessor processor;
    int[] realId;
    boolean requestFocus;
    private float roll;
    private boolean rotationVectorAvailable;
    private SensorEventListener rotationVectorListener;
    protected final float[] rotationVectorValues;
    private int sleepTime;
    ArrayList touchEvents;
    protected final AndroidTouchHandler touchHandler;
    int[] touchX;
    int[] touchY;
    boolean[] touched;
    Pool usedKeyEvents;
    Pool usedTouchEvents;
    protected final Vibrator vibrator;

    public DefaultAndroidInput(Application application0, Context context0, Object object0, AndroidApplicationConfiguration androidApplicationConfiguration0) {
        this.usedKeyEvents = new Pool(16, 1000) {
            protected KeyEvent newObject() {
                return new KeyEvent();
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return this.newObject();
            }
        };
        this.usedTouchEvents = new Pool(16, 1000) {
            protected TouchEvent newObject() {
                return new TouchEvent();
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return this.newObject();
            }
        };
        this.keyListeners = new ArrayList();
        this.keyEvents = new ArrayList();
        this.touchEvents = new ArrayList();
        this.touchX = new int[20];
        this.touchY = new int[20];
        this.deltaX = new int[20];
        this.deltaY = new int[20];
        this.touched = new boolean[20];
        this.button = new int[20];
        this.realId = new int[20];
        this.pressure = new float[20];
        this.justPressedButtons = new boolean[20];
        this.accelerometerAvailable = false;
        this.accelerometerValues = new float[3];
        this.gyroscopeAvailable = false;
        this.gyroscopeValues = new float[3];
        this.sleepTime = 0;
        this.compassAvailable = false;
        this.rotationVectorAvailable = false;
        this.magneticFieldValues = new float[3];
        this.rotationVectorValues = new float[3];
        this.azimuth = 0.0f;
        this.pitch = 0.0f;
        this.roll = 0.0f;
        this.justTouched = false;
        this.currentEventTimeStamp = 0L;
        this.genericMotionListeners = new ArrayList();
        this.requestFocus = true;
        this.R = new float[9];
        this.orientation = new float[3];
        if(object0 instanceof View) {
            ((View)object0).setOnKeyListener(this);
            ((View)object0).setOnTouchListener(this);
            ((View)object0).setFocusable(true);
            ((View)object0).setFocusableInTouchMode(true);
            ((View)object0).requestFocus();
            ((View)object0).setOnGenericMotionListener(this);
        }
        this.config = androidApplicationConfiguration0;
        this.mouseHandler = new AndroidMouseHandler();
        for(int v = 0; true; ++v) {
            int[] arr_v = this.realId;
            if(v >= arr_v.length) {
                break;
            }
            arr_v[v] = -1;
        }
        this.handle = new Handler();
        this.app = application0;
        this.context = context0;
        this.sleepTime = androidApplicationConfiguration0.touchSleepTime;
        this.touchHandler = new AndroidTouchHandler();
        this.hasMultitouch = this.touchHandler.supportsMultitouch(context0);
        this.vibrator = (Vibrator)context0.getSystemService("vibrator");
        int v1 = this.getRotation();
        DisplayMode graphics$DisplayMode0 = this.app.getGraphics().getDisplayMode();
        this.nativeOrientation = (v1 == 0 || v1 == 180) && graphics$DisplayMode0.width >= graphics$DisplayMode0.height || (v1 == 90 || v1 == 270) && graphics$DisplayMode0.width <= graphics$DisplayMode0.height ? Orientation.Landscape : Orientation.Portrait;
        this.setCatchKey(0xFF, true);
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidInput
    public void addGenericMotionListener(View.OnGenericMotionListener view$OnGenericMotionListener0) {
        this.genericMotionListeners.add(view$OnGenericMotionListener0);
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidInput
    public void addKeyListener(View.OnKeyListener view$OnKeyListener0) {
        this.keyListeners.add(view$OnKeyListener0);
    }

    @Override  // com.badlogic.gdx.Input
    public void cancelVibrate() {
        this.vibrator.cancel();
    }

    @Override  // com.badlogic.gdx.Input
    public float getAccelerometerX() {
        return this.accelerometerValues[0];
    }

    @Override  // com.badlogic.gdx.Input
    public float getAccelerometerY() {
        return this.accelerometerValues[1];
    }

    @Override  // com.badlogic.gdx.Input
    public float getAccelerometerZ() {
        return this.accelerometerValues[2];
    }

    public static int getAndroidInputType(OnscreenKeyboardType input$OnscreenKeyboardType0) {
        switch(com.badlogic.gdx.backends.android.DefaultAndroidInput.5.$SwitchMap$com$badlogic$gdx$Input$OnscreenKeyboardType[input$OnscreenKeyboardType0.ordinal()]) {
            case 1: {
                return 2;
            }
            case 2: {
                return 3;
            }
            case 3: {
                return 33;
            }
            case 4: {
                return 0x81;
            }
            case 5: {
                return 17;
            }
            default: {
                return 0x90;
            }
        }
    }

    @Override  // com.badlogic.gdx.Input
    public float getAzimuth() {
        if(!this.compassAvailable && !this.rotationVectorAvailable) {
            return 0.0f;
        }
        this.updateOrientation();
        return this.azimuth;
    }

    @Override  // com.badlogic.gdx.Input
    public long getCurrentEventTime() {
        return this.currentEventTimeStamp;
    }

    @Override  // com.badlogic.gdx.Input
    public int getDeltaX() {
        return this.deltaX[0];
    }

    @Override  // com.badlogic.gdx.Input
    public int getDeltaX(int v) {
        return this.deltaX[v];
    }

    @Override  // com.badlogic.gdx.Input
    public int getDeltaY() {
        return this.deltaY[0];
    }

    @Override  // com.badlogic.gdx.Input
    public int getDeltaY(int v) {
        return this.deltaY[v];
    }

    public int getFreePointerIndex() {
        int v = this.realId.length;
        for(int v1 = 0; v1 < v; ++v1) {
            if(this.realId[v1] == -1) {
                return v1;
            }
        }
        this.pressure = this.resize(this.pressure);
        this.realId = this.resize(this.realId);
        this.touchX = this.resize(this.touchX);
        this.touchY = this.resize(this.touchY);
        this.deltaX = this.resize(this.deltaX);
        this.deltaY = this.resize(this.deltaY);
        this.touched = this.resize(this.touched);
        this.button = this.resize(this.button);
        return v;
    }

    @Override  // com.badlogic.gdx.Input
    public float getGyroscopeX() {
        return this.gyroscopeValues[0];
    }

    @Override  // com.badlogic.gdx.Input
    public float getGyroscopeY() {
        return this.gyroscopeValues[1];
    }

    @Override  // com.badlogic.gdx.Input
    public float getGyroscopeZ() {
        return this.gyroscopeValues[2];
    }

    @Override  // com.badlogic.gdx.Input
    public InputProcessor getInputProcessor() {
        return this.processor;
    }

    @Override  // com.badlogic.gdx.Input
    public int getMaxPointers() {
        return 20;
    }

    @Override  // com.badlogic.gdx.Input
    public Orientation getNativeOrientation() {
        return this.nativeOrientation;
    }

    @Override  // com.badlogic.gdx.Input
    public float getPitch() {
        if(!this.compassAvailable && !this.rotationVectorAvailable) {
            return 0.0f;
        }
        this.updateOrientation();
        return this.pitch;
    }

    @Override  // com.badlogic.gdx.Input
    public float getPressure() {
        return this.getPressure(0);
    }

    @Override  // com.badlogic.gdx.Input
    public float getPressure(int v) {
        return this.pressure[v];
    }

    @Override  // com.badlogic.gdx.Input
    public float getRoll() {
        if(!this.compassAvailable && !this.rotationVectorAvailable) {
            return 0.0f;
        }
        this.updateOrientation();
        return this.roll;
    }

    @Override  // com.badlogic.gdx.Input
    public int getRotation() {
        switch((this.context instanceof Activity ? ((Activity)this.context).getWindowManager().getDefaultDisplay().getRotation() : ((WindowManager)this.context.getSystemService("window")).getDefaultDisplay().getRotation())) {
            case 0: {
                return 0;
            }
            case 1: {
                return 90;
            }
            case 2: {
                return 180;
            }
            case 3: {
                return 270;
            }
            default: {
                return 0;
            }
        }
    }

    @Override  // com.badlogic.gdx.Input
    public void getRotationMatrix(float[] arr_f) {
        if(this.rotationVectorAvailable) {
            SensorManager.getRotationMatrixFromVector(arr_f, this.rotationVectorValues);
            return;
        }
        SensorManager.getRotationMatrix(arr_f, null, this.accelerometerValues, this.magneticFieldValues);
    }

    @Override  // com.badlogic.gdx.Input
    public void getTextInput(TextInputListener input$TextInputListener0, String s, String s1, String s2) {
        this.getTextInput(input$TextInputListener0, s, s1, s2, OnscreenKeyboardType.Default);
    }

    @Override  // com.badlogic.gdx.Input
    public void getTextInput(TextInputListener input$TextInputListener0, String s, String s1, String s2, OnscreenKeyboardType input$OnscreenKeyboardType0) {
        this.handle.post(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder alertDialog$Builder0 = new AlertDialog.Builder(DefaultAndroidInput.this.context);
                alertDialog$Builder0.setTitle(s);
                EditText editText0 = new EditText(DefaultAndroidInput.this.context);
                if(input$OnscreenKeyboardType0 != OnscreenKeyboardType.Default) {
                    editText0.setInputType(DefaultAndroidInput.getAndroidInputType(input$OnscreenKeyboardType0));
                }
                editText0.setHint(s2);
                editText0.setText(s1);
                editText0.setSingleLine();
                if(input$OnscreenKeyboardType0 == OnscreenKeyboardType.Password) {
                    editText0.setTransformationMethod(new PasswordTransformationMethod());
                }
                alertDialog$Builder0.setView(editText0);
                alertDialog$Builder0.setPositiveButton(DefaultAndroidInput.this.context.getString(0x104000A), new DialogInterface.OnClickListener() {
                    @Override  // android.content.DialogInterface$OnClickListener
                    public void onClick(DialogInterface dialogInterface0, int v) {
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                String s = com.badlogic.gdx.backends.android.DefaultAndroidInput.3.1.this.val$input.getText().toString();
                                com.badlogic.gdx.backends.android.DefaultAndroidInput.3.this.val$listener.input(s);
                            }
                        });
                    }
                });
                alertDialog$Builder0.setNegativeButton(DefaultAndroidInput.this.context.getString(0x1040000), new DialogInterface.OnClickListener() {
                    @Override  // android.content.DialogInterface$OnClickListener
                    public void onClick(DialogInterface dialogInterface0, int v) {
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                com.badlogic.gdx.backends.android.DefaultAndroidInput.3.this.val$listener.canceled();
                            }
                        });
                    }
                });
                alertDialog$Builder0.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override  // android.content.DialogInterface$OnCancelListener
                    public void onCancel(DialogInterface dialogInterface0) {
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                com.badlogic.gdx.backends.android.DefaultAndroidInput.3.this.val$listener.canceled();
                            }
                        });
                    }
                });
                alertDialog$Builder0.show();
            }
        });
    }

    @Override  // com.badlogic.gdx.Input
    public int getX() {
        synchronized(this) {
        }
        return this.touchX[0];
    }

    @Override  // com.badlogic.gdx.Input
    public int getX(int v) {
        synchronized(this) {
        }
        return this.touchX[v];
    }

    @Override  // com.badlogic.gdx.Input
    public int getY() {
        synchronized(this) {
        }
        return this.touchY[0];
    }

    @Override  // com.badlogic.gdx.Input
    public int getY(int v) {
        synchronized(this) {
        }
        return this.touchY[v];
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isButtonJustPressed(int v) {
        return v < 0 || v > 20 ? false : this.justPressedButtons[v];
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isButtonPressed(int v) {
        boolean z = true;
        synchronized(this) {
            if(this.hasMultitouch) {
                for(int v2 = 0; v2 < 20; ++v2) {
                    if(this.touched[v2] && this.button[v2] == v) {
                        return true;
                    }
                }
            }
            if(!this.touched[0] || this.button[0] != v) {
                z = false;
            }
            return z;
        }
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isCursorCatched() {
        return false;
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isPeripheralAvailable(Peripheral input$Peripheral0) {
        if(input$Peripheral0 == Peripheral.Accelerometer) {
            return this.accelerometerAvailable;
        }
        if(input$Peripheral0 == Peripheral.Gyroscope) {
            return this.gyroscopeAvailable;
        }
        if(input$Peripheral0 == Peripheral.Compass) {
            return this.compassAvailable;
        }
        if(input$Peripheral0 == Peripheral.HardwareKeyboard) {
            return this.keyboardAvailable;
        }
        if(input$Peripheral0 == Peripheral.OnscreenKeyboard) {
            return true;
        }
        if(input$Peripheral0 == Peripheral.Vibrator) {
            return this.vibrator != null && this.vibrator.hasVibrator();
        }
        if(input$Peripheral0 == Peripheral.MultitouchScreen) {
            return this.hasMultitouch;
        }
        return input$Peripheral0 == Peripheral.RotationVector ? this.rotationVectorAvailable : input$Peripheral0 == Peripheral.Pressure;
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isTouched() {
        synchronized(this) {
            if(this.hasMultitouch) {
                for(int v1 = 0; v1 < 20; ++v1) {
                    if(this.touched[v1]) {
                        return true;
                    }
                }
            }
        }
        return this.touched[0];
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isTouched(int v) {
        synchronized(this) {
        }
        return this.touched[v];
    }

    @Override  // com.badlogic.gdx.Input
    public boolean justTouched() {
        return this.justTouched;
    }

    public int lookUpPointerIndex(int v) {
        for(int v2 = 0; v2 < this.realId.length; ++v2) {
            if(this.realId[v2] == v) {
                return v2;
            }
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        for(int v1 = 0; v1 < this.realId.length; ++v1) {
            stringBuilder0.append(v1 + ":" + this.realId[v1] + " ");
        }
        Gdx.app.log("AndroidInput", "Pointer ID lookup failed: " + v + ", " + stringBuilder0.toString());
        return -1;
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidInput
    public void onDreamingStarted() {
        this.registerSensorListeners();
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidInput
    public void onDreamingStopped() {
        this.unregisterSensorListeners();
        Arrays.fill(this.realId, -1);
        Arrays.fill(this.touched, false);
    }

    @Override  // android.view.View$OnGenericMotionListener
    public boolean onGenericMotion(View view0, MotionEvent motionEvent0) {
        if(this.mouseHandler.onGenericMotion(motionEvent0, this)) {
            return true;
        }
        int v = this.genericMotionListeners.size();
        for(int v1 = 0; v1 < v; ++v1) {
            if(((View.OnGenericMotionListener)this.genericMotionListeners.get(v1)).onGenericMotion(view0, motionEvent0)) {
                return true;
            }
        }
        return false;
    }

    @Override  // android.view.View$OnKeyListener
    public boolean onKey(View view0, int v, android.view.KeyEvent keyEvent0) {
        int v1 = this.keyListeners.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            if(((View.OnKeyListener)this.keyListeners.get(v2)).onKey(view0, v, keyEvent0)) {
                return true;
            }
        }
        if(keyEvent0.getAction() == 0 && keyEvent0.getRepeatCount() > 0) {
            return this.isCatchKey(v);
        }
        synchronized(this) {
            if(keyEvent0.getKeyCode() == 0 && keyEvent0.getAction() == 2) {
                String s = keyEvent0.getCharacters();
                for(int v4 = 0; v4 < s.length(); ++v4) {
                    KeyEvent defaultAndroidInput$KeyEvent0 = (KeyEvent)this.usedKeyEvents.obtain();
                    defaultAndroidInput$KeyEvent0.timeStamp = System.nanoTime();
                    defaultAndroidInput$KeyEvent0.keyCode = 0;
                    defaultAndroidInput$KeyEvent0.keyChar = s.charAt(v4);
                    defaultAndroidInput$KeyEvent0.type = 2;
                    this.keyEvents.add(defaultAndroidInput$KeyEvent0);
                }
                return false;
            }
            char c = (char)keyEvent0.getUnicodeChar();
            if(v == 67) {
                c = '\b';
            }
            if(keyEvent0.getKeyCode() >= 0 && keyEvent0.getKeyCode() <= 0xFF) {
                switch(keyEvent0.getAction()) {
                    case 0: {
                        KeyEvent defaultAndroidInput$KeyEvent1 = (KeyEvent)this.usedKeyEvents.obtain();
                        defaultAndroidInput$KeyEvent1.timeStamp = System.nanoTime();
                        defaultAndroidInput$KeyEvent1.keyChar = '\u0000';
                        defaultAndroidInput$KeyEvent1.keyCode = keyEvent0.getKeyCode();
                        defaultAndroidInput$KeyEvent1.type = 0;
                        if(v == 4 && keyEvent0.isAltPressed()) {
                            defaultAndroidInput$KeyEvent1.keyCode = 0xFF;
                            v = 0xFF;
                        }
                        this.keyEvents.add(defaultAndroidInput$KeyEvent1);
                        if(!this.pressedKeys[defaultAndroidInput$KeyEvent1.keyCode]) {
                            ++this.pressedKeyCount;
                            this.pressedKeys[defaultAndroidInput$KeyEvent1.keyCode] = true;
                        }
                        break;
                    }
                    case 1: {
                        long v5 = System.nanoTime();
                        KeyEvent defaultAndroidInput$KeyEvent2 = (KeyEvent)this.usedKeyEvents.obtain();
                        defaultAndroidInput$KeyEvent2.timeStamp = v5;
                        defaultAndroidInput$KeyEvent2.keyChar = '\u0000';
                        defaultAndroidInput$KeyEvent2.keyCode = keyEvent0.getKeyCode();
                        defaultAndroidInput$KeyEvent2.type = 1;
                        if(v == 4 && keyEvent0.isAltPressed()) {
                            defaultAndroidInput$KeyEvent2.keyCode = 0xFF;
                            v = 0xFF;
                        }
                        this.keyEvents.add(defaultAndroidInput$KeyEvent2);
                        KeyEvent defaultAndroidInput$KeyEvent3 = (KeyEvent)this.usedKeyEvents.obtain();
                        defaultAndroidInput$KeyEvent3.timeStamp = v5;
                        defaultAndroidInput$KeyEvent3.keyChar = c;
                        defaultAndroidInput$KeyEvent3.keyCode = 0;
                        defaultAndroidInput$KeyEvent3.type = 2;
                        this.keyEvents.add(defaultAndroidInput$KeyEvent3);
                        if(v != 0xFF) {
                            boolean[] arr_z = this.pressedKeys;
                            if(arr_z[keyEvent0.getKeyCode()]) {
                                --this.pressedKeyCount;
                                boolean[] arr_z1 = this.pressedKeys;
                                arr_z1[keyEvent0.getKeyCode()] = false;
                            }
                        }
                        else if(this.pressedKeys[0xFF]) {
                            --this.pressedKeyCount;
                            this.pressedKeys[0xFF] = false;
                        }
                    }
                }
                this.app.getGraphics().requestRendering();
                return this.isCatchKey(v);
            }
            return false;
        }
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidInput
    public void onPause() {
        this.unregisterSensorListeners();
        Arrays.fill(this.realId, -1);
        Arrays.fill(this.touched, false);
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidInput
    public void onResume() {
        this.registerSensorListeners();
    }

    @Override  // android.view.View$OnTouchListener
    public boolean onTouch(View view0, MotionEvent motionEvent0) {
        if(this.requestFocus && view0 != null) {
            view0.setFocusableInTouchMode(true);
            view0.requestFocus();
            this.requestFocus = false;
        }
        this.touchHandler.onTouch(motionEvent0, this);
        int v = this.sleepTime;
        if(v != 0) {
            try {
                Thread.sleep(v);
            }
            catch(InterruptedException unused_ex) {
            }
        }
        return true;
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidInput
    public void processEvents() {
        synchronized(this) {
            if(this.justTouched) {
                this.justTouched = false;
                for(int v1 = 0; v1 < this.justPressedButtons.length; ++v1) {
                    this.justPressedButtons[v1] = false;
                }
            }
            if(this.keyJustPressed) {
                this.keyJustPressed = false;
                for(int v2 = 0; v2 < this.justPressedKeys.length; ++v2) {
                    this.justPressedKeys[v2] = false;
                }
            }
            if(this.processor == null) {
                int v7 = this.touchEvents.size();
                for(int v8 = 0; v8 < v7; ++v8) {
                    TouchEvent defaultAndroidInput$TouchEvent1 = (TouchEvent)this.touchEvents.get(v8);
                    if(defaultAndroidInput$TouchEvent1.type == 0) {
                        this.justTouched = true;
                    }
                    this.usedTouchEvents.free(defaultAndroidInput$TouchEvent1);
                }
                int v9 = this.keyEvents.size();
                for(int v10 = 0; v10 < v9; ++v10) {
                    this.usedKeyEvents.free(this.keyEvents.get(v10));
                }
            }
            else {
                InputProcessor inputProcessor0 = this.processor;
                int v3 = this.keyEvents.size();
                for(int v4 = 0; v4 < v3; ++v4) {
                    KeyEvent defaultAndroidInput$KeyEvent0 = (KeyEvent)this.keyEvents.get(v4);
                    this.currentEventTimeStamp = defaultAndroidInput$KeyEvent0.timeStamp;
                    switch(defaultAndroidInput$KeyEvent0.type) {
                        case 0: {
                            inputProcessor0.keyDown(defaultAndroidInput$KeyEvent0.keyCode);
                            this.keyJustPressed = true;
                            this.justPressedKeys[defaultAndroidInput$KeyEvent0.keyCode] = true;
                            break;
                        }
                        case 1: {
                            inputProcessor0.keyUp(defaultAndroidInput$KeyEvent0.keyCode);
                            break;
                        }
                        case 2: {
                            inputProcessor0.keyTyped(defaultAndroidInput$KeyEvent0.keyChar);
                        }
                    }
                    this.usedKeyEvents.free(defaultAndroidInput$KeyEvent0);
                }
                int v5 = this.touchEvents.size();
                for(int v6 = 0; v6 < v5; ++v6) {
                    TouchEvent defaultAndroidInput$TouchEvent0 = (TouchEvent)this.touchEvents.get(v6);
                    this.currentEventTimeStamp = defaultAndroidInput$TouchEvent0.timeStamp;
                    switch(defaultAndroidInput$TouchEvent0.type) {
                        case 0: {
                            inputProcessor0.touchDown(defaultAndroidInput$TouchEvent0.x, defaultAndroidInput$TouchEvent0.y, defaultAndroidInput$TouchEvent0.pointer, defaultAndroidInput$TouchEvent0.button);
                            this.justTouched = true;
                            this.justPressedButtons[defaultAndroidInput$TouchEvent0.button] = true;
                            break;
                        }
                        case 1: {
                            inputProcessor0.touchUp(defaultAndroidInput$TouchEvent0.x, defaultAndroidInput$TouchEvent0.y, defaultAndroidInput$TouchEvent0.pointer, defaultAndroidInput$TouchEvent0.button);
                            break;
                        }
                        case 2: {
                            inputProcessor0.touchDragged(defaultAndroidInput$TouchEvent0.x, defaultAndroidInput$TouchEvent0.y, defaultAndroidInput$TouchEvent0.pointer);
                            break;
                        }
                        case 3: {
                            inputProcessor0.scrolled(((float)defaultAndroidInput$TouchEvent0.scrollAmountX), ((float)defaultAndroidInput$TouchEvent0.scrollAmountY));
                            break;
                        }
                        case 4: {
                            inputProcessor0.mouseMoved(defaultAndroidInput$TouchEvent0.x, defaultAndroidInput$TouchEvent0.y);
                        }
                    }
                    this.usedTouchEvents.free(defaultAndroidInput$TouchEvent0);
                }
            }
            if(this.touchEvents.isEmpty()) {
                for(int v11 = 0; v11 < this.deltaX.length; ++v11) {
                    this.deltaX[0] = 0;
                    this.deltaY[0] = 0;
                }
            }
            this.keyEvents.clear();
            this.touchEvents.clear();
        }
    }

    void registerSensorListeners() {
        if(this.config.useAccelerometer) {
            this.manager = (SensorManager)this.context.getSystemService("sensor");
            if(this.manager.getSensorList(1).isEmpty()) {
                this.accelerometerAvailable = false;
            }
            else {
                Sensor sensor0 = (Sensor)this.manager.getSensorList(1).get(0);
                this.accelerometerListener = new SensorListener(this);
                this.accelerometerAvailable = this.manager.registerListener(this.accelerometerListener, sensor0, this.config.sensorDelay);
            }
        }
        else {
            this.accelerometerAvailable = false;
        }
        if(this.config.useGyroscope) {
            this.manager = (SensorManager)this.context.getSystemService("sensor");
            if(this.manager.getSensorList(4).isEmpty()) {
                this.gyroscopeAvailable = false;
            }
            else {
                Sensor sensor1 = (Sensor)this.manager.getSensorList(4).get(0);
                this.gyroscopeListener = new SensorListener(this);
                this.gyroscopeAvailable = this.manager.registerListener(this.gyroscopeListener, sensor1, this.config.sensorDelay);
            }
        }
        else {
            this.gyroscopeAvailable = false;
        }
        this.rotationVectorAvailable = false;
        if(this.config.useRotationVectorSensor) {
            if(this.manager == null) {
                this.manager = (SensorManager)this.context.getSystemService("sensor");
            }
            List list0 = this.manager.getSensorList(11);
            if(!list0.isEmpty()) {
                this.rotationVectorListener = new SensorListener(this);
                for(Object object0: list0) {
                    Sensor sensor2 = (Sensor)object0;
                    if(sensor2.getVendor().equals("Google Inc.") && sensor2.getVersion() == 3) {
                        this.rotationVectorAvailable = this.manager.registerListener(this.rotationVectorListener, sensor2, this.config.sensorDelay);
                        break;
                    }
                    if(false) {
                        break;
                    }
                }
                if(!this.rotationVectorAvailable) {
                    this.rotationVectorAvailable = this.manager.registerListener(this.rotationVectorListener, ((Sensor)list0.get(0)), this.config.sensorDelay);
                }
            }
        }
        if(!this.config.useCompass || this.rotationVectorAvailable) {
            this.compassAvailable = false;
        }
        else {
            if(this.manager == null) {
                this.manager = (SensorManager)this.context.getSystemService("sensor");
            }
            Sensor sensor3 = this.manager.getDefaultSensor(2);
            if(sensor3 == null) {
                this.compassAvailable = false;
            }
            else {
                this.compassAvailable = this.accelerometerAvailable;
                if(this.compassAvailable) {
                    this.compassListener = new SensorListener(this);
                    this.compassAvailable = this.manager.registerListener(this.compassListener, sensor3, this.config.sensorDelay);
                }
            }
        }
        Gdx.app.log("AndroidInput", "sensor listener setup");
    }

    private float[] resize(float[] arr_f) {
        float[] arr_f1 = new float[arr_f.length + 2];
        System.arraycopy(arr_f, 0, arr_f1, 0, arr_f.length);
        return arr_f1;
    }

    private int[] resize(int[] arr_v) {
        int[] arr_v1 = new int[arr_v.length + 2];
        System.arraycopy(arr_v, 0, arr_v1, 0, arr_v.length);
        return arr_v1;
    }

    private boolean[] resize(boolean[] arr_z) {
        boolean[] arr_z1 = new boolean[arr_z.length + 2];
        System.arraycopy(arr_z, 0, arr_z1, 0, arr_z.length);
        return arr_z1;
    }

    @Override  // com.badlogic.gdx.Input
    public void setCursorCatched(boolean z) {
    }

    @Override  // com.badlogic.gdx.Input
    public void setCursorPosition(int v, int v1) {
    }

    @Override  // com.badlogic.gdx.Input
    public void setInputProcessor(InputProcessor inputProcessor0) {
        synchronized(this) {
            this.processor = inputProcessor0;
        }
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidInput
    public void setKeyboardAvailable(boolean z) {
        this.keyboardAvailable = z;
    }

    @Override  // com.badlogic.gdx.Input
    public void setOnscreenKeyboardVisible(boolean z) {
        this.setOnscreenKeyboardVisible(z, OnscreenKeyboardType.Default);
    }

    @Override  // com.badlogic.gdx.Input
    public void setOnscreenKeyboardVisible(boolean z, OnscreenKeyboardType input$OnscreenKeyboardType0) {
        this.handle.post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputMethodManager0 = (InputMethodManager)DefaultAndroidInput.this.context.getSystemService("input_method");
                if(z) {
                    View view0 = ((AndroidGraphics)DefaultAndroidInput.this.app.getGraphics()).getView();
                    OnscreenKeyboardType input$OnscreenKeyboardType0 = input$OnscreenKeyboardType0 == null ? OnscreenKeyboardType.Default : input$OnscreenKeyboardType0;
                    if(((GLSurfaceView20)view0).onscreenKeyboardType != input$OnscreenKeyboardType0) {
                        ((GLSurfaceView20)view0).onscreenKeyboardType = input$OnscreenKeyboardType0;
                        inputMethodManager0.restartInput(view0);
                    }
                    view0.setFocusable(true);
                    view0.setFocusableInTouchMode(true);
                    inputMethodManager0.showSoftInput(((AndroidGraphics)DefaultAndroidInput.this.app.getGraphics()).getView(), 0);
                    return;
                }
                inputMethodManager0.hideSoftInputFromWindow(((AndroidGraphics)DefaultAndroidInput.this.app.getGraphics()).getView().getWindowToken(), 0);
            }
        });
    }

    void unregisterSensorListeners() {
        SensorManager sensorManager0 = this.manager;
        if(sensorManager0 != null) {
            SensorEventListener sensorEventListener0 = this.accelerometerListener;
            if(sensorEventListener0 != null) {
                sensorManager0.unregisterListener(sensorEventListener0);
                this.accelerometerListener = null;
            }
            SensorEventListener sensorEventListener1 = this.gyroscopeListener;
            if(sensorEventListener1 != null) {
                this.manager.unregisterListener(sensorEventListener1);
                this.gyroscopeListener = null;
            }
            SensorEventListener sensorEventListener2 = this.rotationVectorListener;
            if(sensorEventListener2 != null) {
                this.manager.unregisterListener(sensorEventListener2);
                this.rotationVectorListener = null;
            }
            SensorEventListener sensorEventListener3 = this.compassListener;
            if(sensorEventListener3 != null) {
                this.manager.unregisterListener(sensorEventListener3);
                this.compassListener = null;
            }
            this.manager = null;
        }
        Gdx.app.log("AndroidInput", "sensor listener tear down");
    }

    private void updateOrientation() {
        if(this.rotationVectorAvailable) {
            SensorManager.getRotationMatrixFromVector(this.R, this.rotationVectorValues);
        }
        else if(!SensorManager.getRotationMatrix(this.R, null, this.accelerometerValues, this.magneticFieldValues)) {
            return;
        }
        SensorManager.getOrientation(this.R, this.orientation);
        this.azimuth = (float)Math.toDegrees(this.orientation[0]);
        this.pitch = (float)Math.toDegrees(this.orientation[1]);
        this.roll = (float)Math.toDegrees(this.orientation[2]);
    }

    @Override  // com.badlogic.gdx.Input
    public void vibrate(int v) {
        if(Build.VERSION.SDK_INT >= 26) {
            VibrationEffect vibrationEffect0 = VibrationEffect.createOneShot(v, -1);
            this.vibrator.vibrate(vibrationEffect0);
            return;
        }
        this.vibrator.vibrate(((long)v));
    }

    @Override  // com.badlogic.gdx.Input
    public void vibrate(long[] arr_v, int v) {
        if(Build.VERSION.SDK_INT >= 26) {
            VibrationEffect vibrationEffect0 = VibrationEffect.createWaveform(arr_v, v);
            this.vibrator.vibrate(vibrationEffect0);
            return;
        }
        this.vibrator.vibrate(arr_v, v);
    }
}

