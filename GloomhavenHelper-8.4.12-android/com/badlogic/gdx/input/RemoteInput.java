package com.badlogic.gdx.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.OnscreenKeyboardType;
import com.badlogic.gdx.Input.Orientation;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class RemoteInput implements Input, Runnable {
    class EventTrigger implements Runnable {
        KeyEvent keyEvent;
        TouchEvent touchEvent;

        public EventTrigger(TouchEvent remoteInput$TouchEvent0, KeyEvent remoteInput$KeyEvent0) {
            this.touchEvent = remoteInput$TouchEvent0;
            this.keyEvent = remoteInput$KeyEvent0;
        }

        @Override
        public void run() {
            RemoteInput.this.justTouched = false;
            if(RemoteInput.this.keyJustPressed) {
                RemoteInput.this.keyJustPressed = false;
                for(int v = 0; v < RemoteInput.this.justPressedKeys.length; ++v) {
                    RemoteInput.this.justPressedKeys[v] = false;
                }
            }
            if(RemoteInput.this.processor == null) {
                TouchEvent remoteInput$TouchEvent1 = this.touchEvent;
                if(remoteInput$TouchEvent1 != null) {
                    switch(remoteInput$TouchEvent1.type) {
                        case 0: {
                            RemoteInput.this.deltaX[this.touchEvent.pointer] = 0;
                            RemoteInput.this.deltaY[this.touchEvent.pointer] = 0;
                            RemoteInput.this.isTouched[this.touchEvent.pointer] = true;
                            RemoteInput.this.justTouched = true;
                            break;
                        }
                        case 1: {
                            RemoteInput.this.deltaX[this.touchEvent.pointer] = 0;
                            RemoteInput.this.deltaY[this.touchEvent.pointer] = 0;
                            RemoteInput.this.isTouched[this.touchEvent.pointer] = false;
                            break;
                        }
                        case 2: {
                            RemoteInput.this.deltaX[this.touchEvent.pointer] = this.touchEvent.x - RemoteInput.this.touchX[this.touchEvent.pointer];
                            RemoteInput.this.deltaY[this.touchEvent.pointer] = this.touchEvent.y - RemoteInput.this.touchY[this.touchEvent.pointer];
                        }
                    }
                    RemoteInput.this.touchX[this.touchEvent.pointer] = this.touchEvent.x;
                    RemoteInput.this.touchY[this.touchEvent.pointer] = this.touchEvent.y;
                }
                KeyEvent remoteInput$KeyEvent1 = this.keyEvent;
                if(remoteInput$KeyEvent1 != null) {
                    if(remoteInput$KeyEvent1.type == 0) {
                        if(!RemoteInput.this.keys[this.keyEvent.keyCode]) {
                            ++RemoteInput.this.keyCount;
                            RemoteInput.this.keys[this.keyEvent.keyCode] = true;
                        }
                        RemoteInput.this.keyJustPressed = true;
                        RemoteInput.this.justPressedKeys[this.keyEvent.keyCode] = true;
                    }
                    if(this.keyEvent.type == 1 && RemoteInput.this.keys[this.keyEvent.keyCode]) {
                        --RemoteInput.this.keyCount;
                        RemoteInput.this.keys[this.keyEvent.keyCode] = false;
                    }
                }
            }
            else {
                TouchEvent remoteInput$TouchEvent0 = this.touchEvent;
                if(remoteInput$TouchEvent0 != null) {
                    switch(remoteInput$TouchEvent0.type) {
                        case 0: {
                            RemoteInput.this.deltaX[this.touchEvent.pointer] = 0;
                            RemoteInput.this.deltaY[this.touchEvent.pointer] = 0;
                            RemoteInput.this.processor.touchDown(this.touchEvent.x, this.touchEvent.y, this.touchEvent.pointer, 0);
                            RemoteInput.this.isTouched[this.touchEvent.pointer] = true;
                            RemoteInput.this.justTouched = true;
                            break;
                        }
                        case 1: {
                            RemoteInput.this.deltaX[this.touchEvent.pointer] = 0;
                            RemoteInput.this.deltaY[this.touchEvent.pointer] = 0;
                            RemoteInput.this.processor.touchUp(this.touchEvent.x, this.touchEvent.y, this.touchEvent.pointer, 0);
                            RemoteInput.this.isTouched[this.touchEvent.pointer] = false;
                            break;
                        }
                        case 2: {
                            RemoteInput.this.deltaX[this.touchEvent.pointer] = this.touchEvent.x - RemoteInput.this.touchX[this.touchEvent.pointer];
                            RemoteInput.this.deltaY[this.touchEvent.pointer] = this.touchEvent.y - RemoteInput.this.touchY[this.touchEvent.pointer];
                            RemoteInput.this.processor.touchDragged(this.touchEvent.x, this.touchEvent.y, this.touchEvent.pointer);
                        }
                    }
                    RemoteInput.this.touchX[this.touchEvent.pointer] = this.touchEvent.x;
                    RemoteInput.this.touchY[this.touchEvent.pointer] = this.touchEvent.y;
                }
                KeyEvent remoteInput$KeyEvent0 = this.keyEvent;
                if(remoteInput$KeyEvent0 != null) {
                    switch(remoteInput$KeyEvent0.type) {
                        case 0: {
                            goto label_33;
                        }
                        case 1: {
                            goto label_42;
                        }
                        case 2: {
                            RemoteInput.this.processor.keyTyped(this.keyEvent.keyChar);
                            return;
                        }
                    }
                    return;
                label_33:
                    RemoteInput.this.processor.keyDown(this.keyEvent.keyCode);
                    if(!RemoteInput.this.keys[this.keyEvent.keyCode]) {
                        ++RemoteInput.this.keyCount;
                        RemoteInput.this.keys[this.keyEvent.keyCode] = true;
                    }
                    RemoteInput.this.keyJustPressed = true;
                    RemoteInput.this.justPressedKeys[this.keyEvent.keyCode] = true;
                    return;
                label_42:
                    RemoteInput.this.processor.keyUp(this.keyEvent.keyCode);
                    if(RemoteInput.this.keys[this.keyEvent.keyCode]) {
                        --RemoteInput.this.keyCount;
                        RemoteInput.this.keys[this.keyEvent.keyCode] = false;
                    }
                }
            }
        }
    }

    class KeyEvent {
        static final int KEY_DOWN = 0;
        static final int KEY_TYPED = 2;
        static final int KEY_UP = 1;
        char keyChar;
        int keyCode;
        long timeStamp;
        int type;

    }

    public interface RemoteInputListener {
        void onConnected();

        void onDisconnected();
    }

    class TouchEvent {
        static final int TOUCH_DOWN = 0;
        static final int TOUCH_DRAGGED = 2;
        static final int TOUCH_UP = 1;
        int pointer;
        long timeStamp;
        int type;
        int x;
        int y;

    }

    public static int DEFAULT_PORT = 0x1FFE;
    private static final int MAX_TOUCHES = 20;
    private float[] accel;
    private float[] compass;
    private boolean connected;
    int[] deltaX;
    int[] deltaY;
    private float[] gyrate;
    public final String[] ips;
    boolean[] isTouched;
    boolean[] justPressedKeys;
    boolean justTouched;
    int keyCount;
    boolean keyJustPressed;
    boolean[] keys;
    private RemoteInputListener listener;
    private boolean multiTouch;
    private final int port;
    InputProcessor processor;
    private float remoteHeight;
    private float remoteWidth;
    private ServerSocket serverSocket;
    int[] touchX;
    int[] touchY;

    static {
    }

    public RemoteInput() {
        this(RemoteInput.DEFAULT_PORT);
    }

    public RemoteInput(int v) {
        this(v, null);
    }

    public RemoteInput(int v, RemoteInputListener remoteInput$RemoteInputListener0) {
        this.accel = new float[3];
        this.gyrate = new float[3];
        this.compass = new float[3];
        this.multiTouch = false;
        this.remoteWidth = 0.0f;
        this.remoteHeight = 0.0f;
        this.connected = false;
        this.keyCount = 0;
        this.keys = new boolean[0x100];
        this.keyJustPressed = false;
        this.justPressedKeys = new boolean[0x100];
        this.deltaX = new int[20];
        this.deltaY = new int[20];
        this.touchX = new int[20];
        this.touchY = new int[20];
        this.isTouched = new boolean[20];
        this.justTouched = false;
        this.processor = null;
        this.listener = remoteInput$RemoteInputListener0;
        try {
            this.port = v;
            this.serverSocket = new ServerSocket(v);
            Thread thread0 = new Thread(this);
            thread0.setDaemon(true);
            thread0.start();
            InetAddress[] arr_inetAddress = InetAddress.getAllByName("localhost");
            this.ips = new String[arr_inetAddress.length];
            for(int v1 = 0; v1 < arr_inetAddress.length; ++v1) {
                this.ips[v1] = arr_inetAddress[v1].getHostAddress();
            }
        }
        catch(Exception exception0) {
            throw new GdxRuntimeException("Couldn\'t open listening socket at port \'" + v + "\'", exception0);
        }
    }

    public RemoteInput(RemoteInputListener remoteInput$RemoteInputListener0) {
        this(RemoteInput.DEFAULT_PORT, remoteInput$RemoteInputListener0);
    }

    @Override  // com.badlogic.gdx.Input
    public void cancelVibrate() {
    }

    @Override  // com.badlogic.gdx.Input
    public float getAccelerometerX() {
        return this.accel[0];
    }

    @Override  // com.badlogic.gdx.Input
    public float getAccelerometerY() {
        return this.accel[1];
    }

    @Override  // com.badlogic.gdx.Input
    public float getAccelerometerZ() {
        return this.accel[2];
    }

    @Override  // com.badlogic.gdx.Input
    public float getAzimuth() {
        return this.compass[0];
    }

    @Override  // com.badlogic.gdx.Input
    public long getCurrentEventTime() {
        return 0L;
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

    @Override  // com.badlogic.gdx.Input
    public float getGyroscopeX() {
        return this.gyrate[0];
    }

    @Override  // com.badlogic.gdx.Input
    public float getGyroscopeY() {
        return this.gyrate[1];
    }

    @Override  // com.badlogic.gdx.Input
    public float getGyroscopeZ() {
        return this.gyrate[2];
    }

    public String[] getIPs() {
        return this.ips;
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
        return Orientation.Landscape;
    }

    @Override  // com.badlogic.gdx.Input
    public float getPitch() {
        return this.compass[1];
    }

    @Override  // com.badlogic.gdx.Input
    public float getPressure() {
        return this.getPressure(0);
    }

    // 去混淆评级： 低(20)
    @Override  // com.badlogic.gdx.Input
    public float getPressure(int v) {
        return this.isTouched(v) ? 1.0f : 0.0f;
    }

    @Override  // com.badlogic.gdx.Input
    public float getRoll() {
        return this.compass[2];
    }

    @Override  // com.badlogic.gdx.Input
    public int getRotation() {
        return 0;
    }

    @Override  // com.badlogic.gdx.Input
    public void getRotationMatrix(float[] arr_f) {
    }

    @Override  // com.badlogic.gdx.Input
    public void getTextInput(TextInputListener input$TextInputListener0, String s, String s1, String s2) {
        Gdx.app.getInput().getTextInput(input$TextInputListener0, s, s1, s2);
    }

    @Override  // com.badlogic.gdx.Input
    public void getTextInput(TextInputListener input$TextInputListener0, String s, String s1, String s2, OnscreenKeyboardType input$OnscreenKeyboardType0) {
        Gdx.app.getInput().getTextInput(input$TextInputListener0, s, s1, s2, input$OnscreenKeyboardType0);
    }

    @Override  // com.badlogic.gdx.Input
    public int getX() {
        return this.touchX[0];
    }

    @Override  // com.badlogic.gdx.Input
    public int getX(int v) {
        return this.touchX[v];
    }

    @Override  // com.badlogic.gdx.Input
    public int getY() {
        return this.touchY[0];
    }

    @Override  // com.badlogic.gdx.Input
    public int getY(int v) {
        return this.touchY[v];
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isButtonJustPressed(int v) {
        return v == 0 && this.justTouched;
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isButtonPressed(int v) {
        if(v != 0) {
            return false;
        }
        for(int v1 = 0; true; ++v1) {
            boolean[] arr_z = this.isTouched;
            if(v1 >= arr_z.length) {
                break;
            }
            if(arr_z[v1]) {
                return true;
            }
        }
        return false;
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isCatchBackKey() {
        return false;
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isCatchKey(int v) {
        return false;
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isCatchMenuKey() {
        return false;
    }

    public boolean isConnected() {
        return this.connected;
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isCursorCatched() {
        return false;
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isKeyJustPressed(int v) {
        if(v == -1) {
            return this.keyJustPressed;
        }
        return v < 0 || v > 0xFF ? false : this.justPressedKeys[v];
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isKeyPressed(int v) {
        if(v == -1) {
            return this.keyCount > 0;
        }
        return v < 0 || v > 0xFF ? false : this.keys[v];
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isPeripheralAvailable(Peripheral input$Peripheral0) {
        if(input$Peripheral0 == Peripheral.Accelerometer) {
            return true;
        }
        if(input$Peripheral0 == Peripheral.Compass) {
            return true;
        }
        return input$Peripheral0 == Peripheral.MultitouchScreen ? this.multiTouch : false;
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isTouched() {
        return this.isTouched[0];
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isTouched(int v) {
        return this.isTouched[v];
    }

    @Override  // com.badlogic.gdx.Input
    public boolean justTouched() {
        return this.justTouched;
    }

    @Override
    public void run() {
        KeyEvent remoteInput$KeyEvent0;
        while(true) {
            try {
                this.connected = false;
                if(this.listener != null) {
                    this.listener.onDisconnected();
                }
                System.out.println("listening, port " + this.port);
                Socket socket0 = this.serverSocket.accept();
                socket0.setTcpNoDelay(true);
                socket0.setSoTimeout(3000);
                this.connected = true;
                if(this.listener != null) {
                    this.listener.onConnected();
                }
                DataInputStream dataInputStream0 = new DataInputStream(socket0.getInputStream());
                this.multiTouch = dataInputStream0.readBoolean();
                while(true) {
                    TouchEvent remoteInput$TouchEvent0 = null;
                    switch(dataInputStream0.readInt()) {
                        case 0: {
                            remoteInput$KeyEvent0 = new KeyEvent(this);
                            remoteInput$KeyEvent0.keyCode = dataInputStream0.readInt();
                            remoteInput$KeyEvent0.type = 0;
                            break;
                        }
                        case 1: {
                            remoteInput$KeyEvent0 = new KeyEvent(this);
                            remoteInput$KeyEvent0.keyCode = dataInputStream0.readInt();
                            remoteInput$KeyEvent0.type = 1;
                            break;
                        }
                        case 2: {
                            remoteInput$KeyEvent0 = new KeyEvent(this);
                            remoteInput$KeyEvent0.keyChar = dataInputStream0.readChar();
                            remoteInput$KeyEvent0.type = 2;
                            break;
                        }
                        case 3: {
                            TouchEvent remoteInput$TouchEvent1 = new TouchEvent(this);
                            remoteInput$TouchEvent1.x = (int)(((float)dataInputStream0.readInt()) / this.remoteWidth * ((float)Gdx.graphics.getWidth()));
                            remoteInput$TouchEvent1.y = (int)(((float)dataInputStream0.readInt()) / this.remoteHeight * ((float)Gdx.graphics.getHeight()));
                            remoteInput$TouchEvent1.pointer = dataInputStream0.readInt();
                            remoteInput$TouchEvent1.type = 0;
                            remoteInput$TouchEvent0 = remoteInput$TouchEvent1;
                            remoteInput$KeyEvent0 = null;
                            break;
                        }
                        case 4: {
                            TouchEvent remoteInput$TouchEvent2 = new TouchEvent(this);
                            remoteInput$TouchEvent2.x = (int)(((float)dataInputStream0.readInt()) / this.remoteWidth * ((float)Gdx.graphics.getWidth()));
                            remoteInput$TouchEvent2.y = (int)(((float)dataInputStream0.readInt()) / this.remoteHeight * ((float)Gdx.graphics.getHeight()));
                            remoteInput$TouchEvent2.pointer = dataInputStream0.readInt();
                            remoteInput$TouchEvent2.type = 1;
                            remoteInput$TouchEvent0 = remoteInput$TouchEvent2;
                            remoteInput$KeyEvent0 = null;
                            break;
                        }
                        case 5: {
                            TouchEvent remoteInput$TouchEvent3 = new TouchEvent(this);
                            remoteInput$TouchEvent3.x = (int)(((float)dataInputStream0.readInt()) / this.remoteWidth * ((float)Gdx.graphics.getWidth()));
                            remoteInput$TouchEvent3.y = (int)(((float)dataInputStream0.readInt()) / this.remoteHeight * ((float)Gdx.graphics.getHeight()));
                            remoteInput$TouchEvent3.pointer = dataInputStream0.readInt();
                            remoteInput$TouchEvent3.type = 2;
                            remoteInput$TouchEvent0 = remoteInput$TouchEvent3;
                            remoteInput$KeyEvent0 = null;
                            break;
                        }
                        case 6: {
                            float[] arr_f = this.accel;
                            arr_f[0] = dataInputStream0.readFloat();
                            float[] arr_f1 = this.accel;
                            arr_f1[1] = dataInputStream0.readFloat();
                            float[] arr_f2 = this.accel;
                            arr_f2[2] = dataInputStream0.readFloat();
                            remoteInput$KeyEvent0 = null;
                            break;
                        }
                        case 7: {
                            float[] arr_f3 = this.compass;
                            arr_f3[0] = dataInputStream0.readFloat();
                            float[] arr_f4 = this.compass;
                            arr_f4[1] = dataInputStream0.readFloat();
                            float[] arr_f5 = this.compass;
                            arr_f5[2] = dataInputStream0.readFloat();
                            remoteInput$KeyEvent0 = null;
                            break;
                        }
                        case 8: {
                            this.remoteWidth = dataInputStream0.readFloat();
                            this.remoteHeight = dataInputStream0.readFloat();
                            remoteInput$KeyEvent0 = null;
                            break;
                        }
                        case 9: {
                            float[] arr_f6 = this.gyrate;
                            arr_f6[0] = dataInputStream0.readFloat();
                            float[] arr_f7 = this.gyrate;
                            arr_f7[1] = dataInputStream0.readFloat();
                            float[] arr_f8 = this.gyrate;
                            arr_f8[2] = dataInputStream0.readFloat();
                            remoteInput$KeyEvent0 = null;
                            break;
                        }
                        default: {
                            remoteInput$KeyEvent0 = null;
                            break;
                        }
                    }
                    Gdx.app.postRunnable(new EventTrigger(this, remoteInput$TouchEvent0, remoteInput$KeyEvent0));
                }
            }
            catch(IOException iOException0) {
                iOException0.printStackTrace();
            }
        }
    }

    @Override  // com.badlogic.gdx.Input
    public void setCatchBackKey(boolean z) {
    }

    @Override  // com.badlogic.gdx.Input
    public void setCatchKey(int v, boolean z) {
    }

    @Override  // com.badlogic.gdx.Input
    public void setCatchMenuKey(boolean z) {
    }

    @Override  // com.badlogic.gdx.Input
    public void setCursorCatched(boolean z) {
    }

    @Override  // com.badlogic.gdx.Input
    public void setCursorPosition(int v, int v1) {
    }

    @Override  // com.badlogic.gdx.Input
    public void setInputProcessor(InputProcessor inputProcessor0) {
        this.processor = inputProcessor0;
    }

    @Override  // com.badlogic.gdx.Input
    public void setOnscreenKeyboardVisible(boolean z) {
    }

    @Override  // com.badlogic.gdx.Input
    public void setOnscreenKeyboardVisible(boolean z, OnscreenKeyboardType input$OnscreenKeyboardType0) {
    }

    @Override  // com.badlogic.gdx.Input
    public void vibrate(int v) {
    }

    @Override  // com.badlogic.gdx.Input
    public void vibrate(long[] arr_v, int v) {
    }
}

