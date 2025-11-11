package com.badlogic.gdx.graphics.profiling;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;

public interface GLErrorListener {
    public static final GLErrorListener LOGGING_LISTENER;
    public static final GLErrorListener THROWING_LISTENER;

    static {
        GLErrorListener.LOGGING_LISTENER = new GLErrorListener() {
            @Override  // com.badlogic.gdx.graphics.profiling.GLErrorListener
            public void onError(int v) {
                String s = null;
                try {
                    StackTraceElement[] arr_stackTraceElement = Thread.currentThread().getStackTrace();
                    int v1 = 0;
                    while(v1 < arr_stackTraceElement.length) {
                        if("check".equals(arr_stackTraceElement[v1].getMethodName())) {
                            if(v1 + 1 >= arr_stackTraceElement.length) {
                                break;
                            }
                            s = arr_stackTraceElement[v1 + 1].getMethodName();
                            if(true) {
                                break;
                            }
                        }
                        else {
                            ++v1;
                        }
                    }
                }
                catch(Exception unused_ex) {
                }
                if(s != null) {
                    Gdx.app.error("GLProfiler", "Error " + GLInterceptor.resolveErrorNumber(v) + " from " + s);
                    return;
                }
                Gdx.app.error("GLProfiler", "Error " + GLInterceptor.resolveErrorNumber(v) + " at: ", new Exception());
            }
        };
        GLErrorListener.THROWING_LISTENER = new GLErrorListener() {
            @Override  // com.badlogic.gdx.graphics.profiling.GLErrorListener
            public void onError(int v) {
                throw new GdxRuntimeException("GLProfiler: Got GL error " + GLInterceptor.resolveErrorNumber(v));
            }
        };
    }

    void onError(int arg1);
}

