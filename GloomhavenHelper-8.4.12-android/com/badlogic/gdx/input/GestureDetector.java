package com.badlogic.gdx.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.Timer;

public class GestureDetector extends InputAdapter {
    public static class GestureAdapter implements GestureListener {
        @Override  // com.badlogic.gdx.input.GestureDetector$GestureListener
        public boolean fling(float f, float f1, int v) {
            return false;
        }

        @Override  // com.badlogic.gdx.input.GestureDetector$GestureListener
        public boolean longPress(float f, float f1) {
            return false;
        }

        @Override  // com.badlogic.gdx.input.GestureDetector$GestureListener
        public boolean pan(float f, float f1, float f2, float f3) {
            return false;
        }

        @Override  // com.badlogic.gdx.input.GestureDetector$GestureListener
        public boolean panStop(float f, float f1, int v, int v1) {
            return false;
        }

        @Override  // com.badlogic.gdx.input.GestureDetector$GestureListener
        public boolean pinch(Vector2 vector20, Vector2 vector21, Vector2 vector22, Vector2 vector23) {
            return false;
        }

        @Override  // com.badlogic.gdx.input.GestureDetector$GestureListener
        public void pinchStop() {
        }

        @Override  // com.badlogic.gdx.input.GestureDetector$GestureListener
        public boolean tap(float f, float f1, int v, int v1) {
            return false;
        }

        @Override  // com.badlogic.gdx.input.GestureDetector$GestureListener
        public boolean touchDown(float f, float f1, int v, int v1) {
            return false;
        }

        @Override  // com.badlogic.gdx.input.GestureDetector$GestureListener
        public boolean zoom(float f, float f1) {
            return false;
        }
    }

    public interface GestureListener {
        boolean fling(float arg1, float arg2, int arg3);

        boolean longPress(float arg1, float arg2);

        boolean pan(float arg1, float arg2, float arg3, float arg4);

        boolean panStop(float arg1, float arg2, int arg3, int arg4);

        boolean pinch(Vector2 arg1, Vector2 arg2, Vector2 arg3, Vector2 arg4);

        void pinchStop();

        boolean tap(float arg1, float arg2, int arg3, int arg4);

        boolean touchDown(float arg1, float arg2, int arg3, int arg4);

        boolean zoom(float arg1, float arg2);
    }

    static class VelocityTracker {
        float deltaX;
        float deltaY;
        long lastTime;
        float lastX;
        float lastY;
        long[] meanTime;
        float[] meanX;
        float[] meanY;
        int numSamples;
        int sampleSize;

        VelocityTracker() {
            this.sampleSize = 10;
            this.meanX = new float[10];
            this.meanY = new float[10];
            this.meanTime = new long[10];
        }

        private float getAverage(float[] arr_f, int v) {
            int v1 = Math.min(this.sampleSize, v);
            float f = 0.0f;
            for(int v2 = 0; v2 < v1; ++v2) {
                f += arr_f[v2];
            }
            return f / ((float)v1);
        }

        private long getAverage(long[] arr_v, int v) {
            int v1 = Math.min(this.sampleSize, v);
            long v3 = 0L;
            for(int v2 = 0; v2 < v1; ++v2) {
                v3 += arr_v[v2];
            }
            return v1 == 0 ? 0L : v3 / ((long)v1);
        }

        private float getSum(float[] arr_f, int v) {
            int v1 = Math.min(this.sampleSize, v);
            float f = 0.0f;
            for(int v2 = 0; v2 < v1; ++v2) {
                f += arr_f[v2];
            }
            return v1 == 0 ? 0.0f : f;
        }

        public float getVelocityX() {
            float f = this.getAverage(this.meanX, this.numSamples);
            float f1 = ((float)this.getAverage(this.meanTime, this.numSamples)) / 1000000000.0f;
            return f1 == 0.0f ? 0.0f : f / f1;
        }

        public float getVelocityY() {
            float f = this.getAverage(this.meanY, this.numSamples);
            float f1 = ((float)this.getAverage(this.meanTime, this.numSamples)) / 1000000000.0f;
            return f1 == 0.0f ? 0.0f : f / f1;
        }

        public void start(float f, float f1, long v) {
            this.lastX = f;
            this.lastY = f1;
            this.deltaX = 0.0f;
            this.deltaY = 0.0f;
            this.numSamples = 0;
            for(int v1 = 0; v1 < this.sampleSize; ++v1) {
                this.meanX[v1] = 0.0f;
                this.meanY[v1] = 0.0f;
                this.meanTime[v1] = 0L;
            }
            this.lastTime = v;
        }

        public void update(float f, float f1, long v) {
            this.deltaX = f - this.lastX;
            this.deltaY = f1 - this.lastY;
            this.lastX = f;
            this.lastY = f1;
            long v1 = v - this.lastTime;
            this.lastTime = v;
            int v2 = this.numSamples;
            int v3 = v2 % this.sampleSize;
            this.meanX[v3] = this.deltaX;
            this.meanY[v3] = this.deltaY;
            this.meanTime[v3] = v1;
            this.numSamples = v2 + 1;
        }
    }

    private boolean inTapRectangle;
    private final Vector2 initialPointer1;
    private final Vector2 initialPointer2;
    private int lastTapButton;
    private int lastTapPointer;
    private long lastTapTime;
    private float lastTapX;
    private float lastTapY;
    final GestureListener listener;
    boolean longPressFired;
    private float longPressSeconds;
    private final Task longPressTask;
    private long maxFlingDelay;
    private boolean panning;
    private boolean pinching;
    Vector2 pointer1;
    private final Vector2 pointer2;
    private int tapCount;
    private long tapCountInterval;
    private float tapRectangleCenterX;
    private float tapRectangleCenterY;
    private float tapRectangleHeight;
    private float tapRectangleWidth;
    private long touchDownTime;
    private final VelocityTracker tracker;

    public GestureDetector(float f, float f1, float f2, float f3, float f4, GestureListener gestureDetector$GestureListener0) {
        this.tracker = new VelocityTracker();
        this.pointer1 = new Vector2();
        this.pointer2 = new Vector2();
        this.initialPointer1 = new Vector2();
        this.initialPointer2 = new Vector2();
        this.longPressTask = new Task() {
            @Override  // com.badlogic.gdx.utils.Timer$Task
            public void run() {
                if(!GestureDetector.this.longPressFired) {
                    GestureDetector.this.longPressFired = GestureDetector.this.listener.longPress(GestureDetector.this.pointer1.x, GestureDetector.this.pointer1.y);
                }
            }
        };
        if(gestureDetector$GestureListener0 == null) {
            throw new IllegalArgumentException("listener cannot be null.");
        }
        this.tapRectangleWidth = f;
        this.tapRectangleHeight = f1;
        this.tapCountInterval = (long)(f2 * 1000000000.0f);
        this.longPressSeconds = f3;
        this.maxFlingDelay = (long)(f4 * 1000000000.0f);
        this.listener = gestureDetector$GestureListener0;
    }

    public GestureDetector(float f, float f1, float f2, float f3, GestureListener gestureDetector$GestureListener0) {
        this(f, f, f1, f2, f3, gestureDetector$GestureListener0);
    }

    public GestureDetector(GestureListener gestureDetector$GestureListener0) {
        this(20.0f, 0.4f, 1.1f, 2147483648.0f, gestureDetector$GestureListener0);
    }

    public void cancel() {
        this.longPressTask.cancel();
        this.longPressFired = true;
    }

    public void invalidateTapSquare() {
        this.inTapRectangle = false;
    }

    public boolean isLongPressed() {
        return this.isLongPressed(this.longPressSeconds);
    }

    // 去混淆评级： 低(30)
    public boolean isLongPressed(float f) {
        return this.touchDownTime == 0L ? false : 37197372709100L - this.touchDownTime > ((long)(f * 1000000000.0f));
    }

    public boolean isPanning() {
        return this.panning;
    }

    private boolean isWithinTapRectangle(float f, float f1, float f2, float f3) {
        return Math.abs(f - f2) < this.tapRectangleWidth && Math.abs(f1 - f3) < this.tapRectangleHeight;
    }

    public void reset() {
        this.touchDownTime = 0L;
        this.panning = false;
        this.inTapRectangle = false;
        this.tracker.lastTime = 0L;
    }

    public void setLongPressSeconds(float f) {
        this.longPressSeconds = f;
    }

    public void setMaxFlingDelay(long v) {
        this.maxFlingDelay = v;
    }

    public void setTapCountInterval(float f) {
        this.tapCountInterval = (long)(f * 1000000000.0f);
    }

    public void setTapRectangleSize(float f, float f1) {
        this.tapRectangleWidth = f;
        this.tapRectangleHeight = f1;
    }

    public void setTapSquareSize(float f) {
        this.setTapRectangleSize(f, f);
    }

    public boolean touchDown(float f, float f1, int v, int v1) {
        if(v > 1) {
            return false;
        }
        if(v == 0) {
            this.pointer1.set(f, f1);
            this.touchDownTime = Gdx.input.getCurrentEventTime();
            this.tracker.start(f, f1, this.touchDownTime);
            if(Gdx.input.isTouched(1)) {
                this.inTapRectangle = false;
                this.pinching = true;
                this.initialPointer1.set(this.pointer1);
                this.initialPointer2.set(this.pointer2);
                this.longPressTask.cancel();
                return this.listener.touchDown(f, f1, 0, v1);
            }
            this.inTapRectangle = true;
            this.pinching = false;
            this.longPressFired = false;
            this.tapRectangleCenterX = f;
            this.tapRectangleCenterY = f1;
            if(!this.longPressTask.isScheduled()) {
                Timer.schedule(this.longPressTask, this.longPressSeconds);
                return this.listener.touchDown(f, f1, 0, v1);
            }
        }
        else {
            this.pointer2.set(f, f1);
            this.inTapRectangle = false;
            this.pinching = true;
            this.initialPointer1.set(this.pointer1);
            this.initialPointer2.set(this.pointer2);
            this.longPressTask.cancel();
        }
        return this.listener.touchDown(f, f1, v, v1);
    }

    @Override  // com.badlogic.gdx.InputAdapter
    public boolean touchDown(int v, int v1, int v2, int v3) {
        return this.touchDown(((float)v), ((float)v1), v2, v3);
    }

    public boolean touchDragged(float f, float f1, int v) {
        if(v > 1) {
            return false;
        }
        if(this.longPressFired) {
            return false;
        }
        if(v == 0) {
            this.pointer1.set(f, f1);
        }
        else {
            this.pointer2.set(f, f1);
        }
        if(this.pinching) {
            GestureListener gestureDetector$GestureListener0 = this.listener;
            if(gestureDetector$GestureListener0 != null) {
                boolean z = gestureDetector$GestureListener0.pinch(this.initialPointer1, this.initialPointer2, this.pointer1, this.pointer2);
                float f2 = this.initialPointer1.dst(this.initialPointer2);
                float f3 = this.pointer1.dst(this.pointer2);
                return this.listener.zoom(f2, f3) || z;
            }
            return false;
        }
        long v1 = Gdx.input.getCurrentEventTime();
        this.tracker.update(f, f1, v1);
        if(this.inTapRectangle && !this.isWithinTapRectangle(f, f1, this.tapRectangleCenterX, this.tapRectangleCenterY)) {
            this.longPressTask.cancel();
            this.inTapRectangle = false;
        }
        if(!this.inTapRectangle) {
            this.panning = true;
            return this.listener.pan(f, f1, this.tracker.deltaX, this.tracker.deltaY);
        }
        return false;
    }

    @Override  // com.badlogic.gdx.InputAdapter
    public boolean touchDragged(int v, int v1, int v2) {
        return this.touchDragged(((float)v), ((float)v1), v2);
    }

    public boolean touchUp(float f, float f1, int v, int v1) {
        if(v > 1) {
            return false;
        }
        if(this.inTapRectangle && !this.isWithinTapRectangle(f, f1, this.tapRectangleCenterX, this.tapRectangleCenterY)) {
            this.inTapRectangle = false;
        }
        boolean z = this.panning;
        this.panning = false;
        this.longPressTask.cancel();
        if(this.longPressFired) {
            return false;
        }
        if(this.inTapRectangle) {
            if(this.lastTapButton != v1 || this.lastTapPointer != v || 37198086273400L - this.lastTapTime > this.tapCountInterval || !this.isWithinTapRectangle(f, f1, this.lastTapX, this.lastTapY)) {
                this.tapCount = 0;
            }
            ++this.tapCount;
            this.lastTapTime = 37198086665100L;
            this.lastTapX = f;
            this.lastTapY = f1;
            this.lastTapButton = v1;
            this.lastTapPointer = v;
            this.touchDownTime = 0L;
            return this.listener.tap(f, f1, this.tapCount, v1);
        }
        if(this.pinching) {
            this.pinching = false;
            this.listener.pinchStop();
            this.panning = true;
            if(v == 0) {
                float f2 = this.pointer2.x;
                float f3 = this.pointer2.y;
                long v2 = Gdx.input.getCurrentEventTime();
                this.tracker.start(f2, f3, v2);
                return false;
            }
            float f4 = this.pointer1.x;
            float f5 = this.pointer1.y;
            long v3 = Gdx.input.getCurrentEventTime();
            this.tracker.start(f4, f5, v3);
            return false;
        }
        boolean z1 = !z || this.panning ? false : this.listener.panStop(f, f1, v, v1);
        long v4 = Gdx.input.getCurrentEventTime();
        if(v4 - this.touchDownTime <= this.maxFlingDelay) {
            this.tracker.update(f, f1, v4);
            float f6 = this.tracker.getVelocityX();
            float f7 = this.tracker.getVelocityY();
            z1 = this.listener.fling(f6, f7, v1) || z1;
        }
        this.touchDownTime = 0L;
        return z1;
    }

    @Override  // com.badlogic.gdx.InputAdapter
    public boolean touchUp(int v, int v1, int v2, int v3) {
        return this.touchUp(((float)v), ((float)v1), v2, v3);
    }
}

