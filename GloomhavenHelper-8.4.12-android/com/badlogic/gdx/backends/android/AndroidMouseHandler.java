package com.badlogic.gdx.backends.android;

import android.view.MotionEvent;
import com.badlogic.gdx.Gdx;

public class AndroidMouseHandler {
    private int deltaX;
    private int deltaY;

    public AndroidMouseHandler() {
        this.deltaX = 0;
        this.deltaY = 0;
    }

    private void logAction(int v) {
        String s;
        switch(v) {
            case 7: {
                s = "HOVER_MOVE";
                break;
            }
            case 8: {
                s = "SCROLL";
                break;
            }
            case 9: {
                s = "HOVER_ENTER";
                break;
            }
            case 10: {
                s = "HOVER_EXIT";
                break;
            }
            default: {
                s = "UNKNOWN (" + v + ")";
            }
        }
        Gdx.app.log("AndroidMouseHandler", "action " + s);
    }

    public boolean onGenericMotion(MotionEvent motionEvent0, DefaultAndroidInput defaultAndroidInput0) {
        if((motionEvent0.getSource() & 2) == 0) {
            return false;
        }
        long v = System.nanoTime();
        __monitor_enter(defaultAndroidInput0);
        switch(motionEvent0.getAction() & 0xFF) {
            case 7: {
                try {
                    int v1 = (int)motionEvent0.getX();
                    int v2 = (int)motionEvent0.getY();
                    if(v1 != this.deltaX || v2 != this.deltaY) {
                        this.postTouchEvent(defaultAndroidInput0, 4, v1, v2, 0, 0, v);
                        this.deltaX = v1;
                        this.deltaY = v2;
                        break;
                    label_13:
                        float f = Math.signum(motionEvent0.getAxisValue(9));
                        this.postTouchEvent(defaultAndroidInput0, 3, 0, 0, ((int)(-Math.signum(motionEvent0.getAxisValue(10)))), ((int)(-f)), v);
                    }
                    break;
                }
                catch(Throwable throwable0) {
                    __monitor_exit(defaultAndroidInput0);
                    throw throwable0;
                }
            }
            case 8: {
                goto label_13;
            }
        }
        __monitor_exit(defaultAndroidInput0);
        Gdx.app.getGraphics().requestRendering();
        return true;
    }

    private void postTouchEvent(DefaultAndroidInput defaultAndroidInput0, int v, int v1, int v2, int v3, int v4, long v5) {
        TouchEvent defaultAndroidInput$TouchEvent0 = (TouchEvent)defaultAndroidInput0.usedTouchEvents.obtain();
        defaultAndroidInput$TouchEvent0.timeStamp = v5;
        defaultAndroidInput$TouchEvent0.x = v1;
        defaultAndroidInput$TouchEvent0.y = v2;
        defaultAndroidInput$TouchEvent0.type = v;
        defaultAndroidInput$TouchEvent0.scrollAmountX = v3;
        defaultAndroidInput$TouchEvent0.scrollAmountY = v4;
        defaultAndroidInput0.touchEvents.add(defaultAndroidInput$TouchEvent0);
    }
}

