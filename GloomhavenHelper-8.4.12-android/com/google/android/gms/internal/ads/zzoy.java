package com.google.android.gms.internal.ads;

import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

final class zzoy extends HandlerThread implements SurfaceTexture.OnFrameAvailableListener, Handler.Callback {
    private zzdkp zzadx;
    private final int[] zzbhn;
    private SurfaceTexture zzbho;
    private Error zzbhp;
    private RuntimeException zzbhq;
    private zzow zzbhr;

    public zzoy() {
        super("dummySurface");
        this.zzbhn = new int[1];
    }

    @Override  // android.os.Handler$Callback
    public final boolean handleMessage(Message message0) {
        switch(message0.what) {
            case 1: {
                try {
                    try {
                        boolean z = message0.arg1 != 0;
                        EGLDisplay eGLDisplay0 = EGL14.eglGetDisplay(0);
                        zzob.checkState(eGLDisplay0 != null, "eglGetDisplay failed");
                        int[] arr_v = new int[2];
                        zzob.checkState(EGL14.eglInitialize(eGLDisplay0, arr_v, 0, arr_v, 1), "eglInitialize failed");
                        EGLConfig[] arr_eGLConfig = new EGLConfig[1];
                        int[] arr_v1 = new int[1];
                        zzob.checkState(EGL14.eglChooseConfig(eGLDisplay0, new int[]{0x3040, 4, 0x3024, 8, 0x3023, 8, 0x3022, 8, 0x3021, 8, 0x3025, 0, 0x3027, 0x3038, 0x3033, 4, 0x3038}, 0, arr_eGLConfig, 0, 1, arr_v1, 0) && arr_v1[0] > 0 && arr_eGLConfig[0] != null, "eglChooseConfig failed");
                        EGLConfig eGLConfig0 = arr_eGLConfig[0];
                        EGLContext eGLContext0 = EGL14.eglCreateContext(eGLDisplay0, eGLConfig0, EGL14.EGL_NO_CONTEXT, (z ? new int[]{0x3098, 2, 0x32C0, 1, 0x3038} : new int[]{0x3098, 2, 0x3038}), 0);
                        zzob.checkState(eGLContext0 != null, "eglCreateContext failed");
                        EGLSurface eGLSurface0 = EGL14.eglCreatePbufferSurface(eGLDisplay0, eGLConfig0, (z ? new int[]{0x3057, 1, 0x3056, 1, 0x32C0, 1, 0x3038} : new int[]{0x3057, 1, 0x3056, 1, 0x3038}), 0);
                        zzob.checkState(eGLSurface0 != null, "eglCreatePbufferSurface failed");
                        zzob.checkState(EGL14.eglMakeCurrent(eGLDisplay0, eGLSurface0, eGLSurface0, eGLContext0), "eglMakeCurrent failed");
                        GLES20.glGenTextures(1, this.zzbhn, 0);
                        this.zzbho = new SurfaceTexture(this.zzbhn[0]);
                        this.zzbho.setOnFrameAvailableListener(this);
                        this.zzbhr = new zzow(this, this.zzbho, z, null);
                        goto label_51;
                    }
                    catch(RuntimeException runtimeException0) {
                    }
                    catch(Error error0) {
                        goto label_32;
                    }
                    Log.e("DummySurface", "Failed to initialize dummy surface", runtimeException0);
                    this.zzbhq = runtimeException0;
                }
                catch(Throwable throwable0) {
                    goto label_43;
                }
                synchronized(this) {
                    this.notify();
                }
                return true;
                try {
                label_32:
                    Log.e("DummySurface", "Failed to initialize dummy surface", error0);
                    this.zzbhp = error0;
                }
                catch(Throwable throwable0) {
                    goto label_43;
                }
                synchronized(this) {
                    this.notify();
                }
                return true;
            label_43:
                synchronized(this) {
                    this.notify();
                }
                throw throwable0;
            label_51:
                synchronized(this) {
                    this.notify();
                }
                return true;
            }
            case 2: {
                this.zzbho.updateTexImage();
                return true;
            }
            case 3: {
                try {
                    try {
                        this.zzbho.release();
                        return true;
                    }
                    finally {
                        this.zzbhr = null;
                        this.zzbho = null;
                        GLES20.glDeleteTextures(1, this.zzbhn, 0);
                    }
                }
                catch(Throwable throwable5) {
                    Log.e("DummySurface", "Failed to release dummy surface", throwable5);
                    return true;
                }
                finally {
                    this.quit();
                }
            }
            default: {
                return true;
            }
        }
    }

    @Override  // android.graphics.SurfaceTexture$OnFrameAvailableListener
    public final void onFrameAvailable(SurfaceTexture surfaceTexture0) {
        this.zzadx.sendEmptyMessage(2);
    }

    public final void release() {
        this.zzadx.sendEmptyMessage(3);
    }

    public final zzow zzm(boolean z) {
        this.start();
        this.zzadx = new zzdkp(this.getLooper(), this);
        synchronized(this) {
            boolean z1 = false;
            this.zzadx.obtainMessage(1, (z ? 1 : 0), 0).sendToTarget();
            while(this.zzbhr == null && this.zzbhq == null && this.zzbhp == null) {
                try {
                    this.wait();
                }
                catch(InterruptedException unused_ex) {
                    z1 = true;
                }
            }
        }
        if(z1) {
            Thread.currentThread().interrupt();
        }
        RuntimeException runtimeException0 = this.zzbhq;
        if(runtimeException0 != null) {
            throw runtimeException0;
        }
        Error error0 = this.zzbhp;
        if(error0 != null) {
            throw error0;
        }
        return this.zzbhr;
    }
}

