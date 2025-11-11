package com.badlogic.gdx.backends.android;

import android.content.Context;
import android.view.MotionEvent;
import com.badlogic.gdx.Gdx;

public class AndroidTouchHandler {
    private void logAction(int v, int v1) {
        String s;
        switch(v) {
            case 0: {
                s = "DOWN";
                break;
            }
            case 1: {
                s = "UP";
                break;
            }
            case 2: {
                s = "MOVE";
                break;
            }
            case 3: {
                s = "CANCEL";
                break;
            }
            case 4: {
                s = "OUTSIDE";
                break;
            }
            case 5: {
                s = "POINTER DOWN";
                break;
            }
            case 6: {
                s = "POINTER UP";
                break;
            }
            default: {
                s = "UNKNOWN (" + v + ")";
            }
        }
        Gdx.app.log("AndroidMultiTouchHandler", "action " + s + ", Android pointer id: " + v1);
    }

    public void onTouch(MotionEvent motionEvent0, DefaultAndroidInput defaultAndroidInput0) {
        int v16;
        int v11;
        int v10;
        int v9;
        int v27;
        int v26;
        int v25;
        int v23;
        int v = motionEvent0.getAction();
        int v1 = (motionEvent0.getAction() & 0xFF00) >> 8;
        int v2 = motionEvent0.getPointerId(v1);
        long v3 = System.nanoTime();
        synchronized(defaultAndroidInput0) {
            boolean z = false;
        alab1:
            switch(v & 0xFF) {
                case 2: {
                    int v17 = motionEvent0.getPointerCount();
                    for(int v18 = 0; v18 < v17; v18 = v23 + 1) {
                        int v19 = motionEvent0.getPointerId(v18);
                        int v20 = (int)motionEvent0.getX(v18);
                        int v21 = (int)motionEvent0.getY(v18);
                        int v22 = defaultAndroidInput0.lookUpPointerIndex(v19);
                        if(v22 == -1) {
                            v23 = v18;
                        }
                        else {
                            if(v22 >= 20) {
                                break;
                            }
                            int v24 = defaultAndroidInput0.button[v22];
                            if(v24 == -1) {
                                v25 = v22;
                                v26 = v21;
                                v23 = v18;
                                v27 = v20;
                                this.postTouchEvent(defaultAndroidInput0, 4, v27, v26, v22, 0, v3);
                            }
                            else {
                                v25 = v22;
                                v26 = v21;
                                v23 = v18;
                                v27 = v20;
                                this.postTouchEvent(defaultAndroidInput0, 2, v20, v21, v22, v24, v3);
                            }
                            defaultAndroidInput0.deltaX[v25] = v27 - defaultAndroidInput0.touchX[v25];
                            defaultAndroidInput0.deltaY[v25] = v26 - defaultAndroidInput0.touchY[v25];
                            defaultAndroidInput0.touchX[v25] = v27;
                            defaultAndroidInput0.touchY[v25] = v26;
                            float[] arr_f1 = defaultAndroidInput0.pressure;
                            arr_f1[v25] = motionEvent0.getPressure(v23);
                        }
                    }
                    break;
                }
                case 3: {
                    for(int v28 = 0; true; ++v28) {
                        if(v28 >= defaultAndroidInput0.realId.length) {
                            break alab1;
                        }
                        defaultAndroidInput0.realId[v28] = -1;
                        defaultAndroidInput0.touchX[v28] = 0;
                        defaultAndroidInput0.touchY[v28] = 0;
                        defaultAndroidInput0.deltaX[v28] = 0;
                        defaultAndroidInput0.deltaY[v28] = 0;
                        defaultAndroidInput0.touched[v28] = false;
                        defaultAndroidInput0.button[v28] = 0;
                        defaultAndroidInput0.pressure[v28] = 0.0f;
                    }
                }
                case 0: 
                case 5: {
                    int v5 = defaultAndroidInput0.getFreePointerIndex();
                    if(v5 < 20) {
                        defaultAndroidInput0.realId[v5] = v2;
                        int v6 = (int)motionEvent0.getX(v1);
                        int v7 = (int)motionEvent0.getY(v1);
                        int v8 = this.toGdxButton(motionEvent0.getButtonState());
                        if(v8 == -1) {
                            v9 = -1;
                            v10 = v6;
                            v11 = v7;
                        }
                        else {
                            v9 = v8;
                            v10 = v6;
                            v11 = v7;
                            this.postTouchEvent(defaultAndroidInput0, 0, v6, v7, v5, v9, v3);
                        }
                        defaultAndroidInput0.touchX[v5] = v10;
                        defaultAndroidInput0.touchY[v5] = v11;
                        defaultAndroidInput0.deltaX[v5] = 0;
                        defaultAndroidInput0.deltaY[v5] = 0;
                        boolean[] arr_z = defaultAndroidInput0.touched;
                        if(v9 != -1) {
                            z = true;
                        }
                        arr_z[v5] = z;
                        defaultAndroidInput0.button[v5] = v9;
                        float[] arr_f = defaultAndroidInput0.pressure;
                        arr_f[v5] = motionEvent0.getPressure(v1);
                    }
                    break;
                }
                case 1: 
                case 4: 
                case 6: {
                    int v12 = defaultAndroidInput0.lookUpPointerIndex(v2);
                    if(v12 != -1 && v12 < 20) {
                        defaultAndroidInput0.realId[v12] = -1;
                        int v13 = (int)motionEvent0.getX(v1);
                        int v14 = (int)motionEvent0.getY(v1);
                        int v15 = defaultAndroidInput0.button[v12];
                        if(v15 == -1) {
                            v16 = v13;
                        }
                        else {
                            v16 = v13;
                            this.postTouchEvent(defaultAndroidInput0, 1, v13, v14, v12, v15, v3);
                        }
                        defaultAndroidInput0.touchX[v12] = v16;
                        defaultAndroidInput0.touchY[v12] = v14;
                        defaultAndroidInput0.deltaX[v12] = 0;
                        defaultAndroidInput0.deltaY[v12] = 0;
                        defaultAndroidInput0.touched[v12] = false;
                        defaultAndroidInput0.button[v12] = 0;
                        defaultAndroidInput0.pressure[v12] = 0.0f;
                    }
                }
            }
        }
        Gdx.app.getGraphics().requestRendering();
    }

    private void postTouchEvent(DefaultAndroidInput defaultAndroidInput0, int v, int v1, int v2, int v3, int v4, long v5) {
        TouchEvent defaultAndroidInput$TouchEvent0 = (TouchEvent)defaultAndroidInput0.usedTouchEvents.obtain();
        defaultAndroidInput$TouchEvent0.timeStamp = v5;
        defaultAndroidInput$TouchEvent0.pointer = v3;
        defaultAndroidInput$TouchEvent0.x = v1;
        defaultAndroidInput$TouchEvent0.y = v2;
        defaultAndroidInput$TouchEvent0.type = v;
        defaultAndroidInput$TouchEvent0.button = v4;
        defaultAndroidInput0.touchEvents.add(defaultAndroidInput$TouchEvent0);
    }

    public boolean supportsMultitouch(Context context0) {
        return context0.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch");
    }

    private int toGdxButton(int v) {
        switch(v) {
            case 0: 
            case 1: {
                return 0;
            }
            case 2: {
                return 1;
            }
            case 4: {
                return 2;
            }
            case 8: {
                return 3;
            }
            case 16: {
                return 4;
            }
            default: {
                return -1;
            }
        }
    }
}

