package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.VisibleForTesting;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import jeb.synthetic.FIN;

@TargetApi(14)
public final class zzbbk extends Thread implements SurfaceTexture.OnFrameAvailableListener, zzbbh {
    private int height;
    private int width;
    private final float[] zzeag;
    private static final float[] zzeav;
    private final zzbbf zzeaw;
    private final float[] zzeax;
    private final float[] zzeay;
    private final float[] zzeaz;
    private final float[] zzeba;
    private final float[] zzebb;
    private final float[] zzebc;
    private float zzebd;
    private float zzebe;
    private float zzebf;
    private SurfaceTexture zzebg;
    private SurfaceTexture zzebh;
    private int zzebi;
    private int zzebj;
    private int zzebk;
    private FloatBuffer zzebl;
    private final CountDownLatch zzebm;
    private final Object zzebn;
    private EGL10 zzebo;
    private EGLDisplay zzebp;
    private EGLContext zzebq;
    private EGLSurface zzebr;
    private volatile boolean zzebs;
    private volatile boolean zzebt;

    static {
        zzbbk.zzeav = new float[]{-1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f};
    }

    public zzbbk(Context context0) {
        super("SphericalVideoProcessor");
        this.zzebl = ByteBuffer.allocateDirect(zzbbk.zzeav.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.zzebl.put(zzbbk.zzeav).position(0);
        this.zzeag = new float[9];
        this.zzeax = new float[9];
        this.zzeay = new float[9];
        this.zzeaz = new float[9];
        this.zzeba = new float[9];
        this.zzebb = new float[9];
        this.zzebc = new float[9];
        this.zzebd = NaNf;
        this.zzeaw = new zzbbf(context0);
        this.zzeaw.zza(this);
        this.zzebm = new CountDownLatch(1);
        this.zzebn = new Object();
    }

    @Override  // android.graphics.SurfaceTexture$OnFrameAvailableListener
    public final void onFrameAvailable(SurfaceTexture surfaceTexture0) {
        ++this.zzebk;
        synchronized(this.zzebn) {
            this.zzebn.notifyAll();
        }
    }

    @Override
    public final void run() {
        Object object0;
        int v1;
        boolean z;
        if(this.zzebh == null) {
            zzawf.zzey("SphericalVideoProcessor started with no output texture.");
            this.zzebm.countDown();
            return;
        }
        this.zzebo = (EGL10)EGLContext.getEGL();
        this.zzebp = this.zzebo.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if(this.zzebp == EGL10.EGL_NO_DISPLAY) {
            z = false;
        }
        else if(this.zzebo.eglInitialize(this.zzebp, new int[2])) {
            int[] arr_v = new int[1];
            EGLConfig[] arr_eGLConfig = new EGLConfig[1];
            EGLConfig eGLConfig0 = !this.zzebo.eglChooseConfig(this.zzebp, new int[]{0x3040, 4, 0x3024, 8, 0x3023, 8, 0x3022, 8, 0x3025, 16, 0x3038}, arr_eGLConfig, 1, arr_v) || arr_v[0] <= 0 ? null : arr_eGLConfig[0];
            if(eGLConfig0 == null) {
                z = false;
            }
            else {
                this.zzebq = this.zzebo.eglCreateContext(this.zzebp, eGLConfig0, EGL10.EGL_NO_CONTEXT, new int[]{0x3098, 2, 0x3038});
                if(this.zzebq == null || this.zzebq == EGL10.EGL_NO_CONTEXT) {
                    z = false;
                }
                else {
                    this.zzebr = this.zzebo.eglCreateWindowSurface(this.zzebp, eGLConfig0, this.zzebh, null);
                    if(this.zzebr == null || this.zzebr == EGL10.EGL_NO_SURFACE) {
                        z = false;
                    }
                    else if(!this.zzebo.eglMakeCurrent(this.zzebp, this.zzebr, this.zzebr, this.zzebq)) {
                        z = false;
                    }
                    else {
                        z = true;
                    }
                }
            }
        }
        else {
            z = false;
        }
        int v = zzbbk.zzc(0x8B31, (((String)zzvh.zzpd().zzd(zzzx.zzckx)).equals(zzzx.zzckx.zzqi()) ? "attribute highp vec3 aPosition;varying vec3 pos;void main() {  gl_Position = vec4(aPosition, 1.0);  pos = aPosition;}" : ((String)zzvh.zzpd().zzd(zzzx.zzckx))));
        if(v == 0) {
            v1 = 0;
        }
        else {
            int v2 = zzbbk.zzc(0x8B30, (((String)zzvh.zzpd().zzd(zzzx.zzcky)).equals(zzzx.zzcky.zzqi()) ? "#extension GL_OES_EGL_image_external : require\n#define INV_PI 0.3183\nprecision highp float;varying vec3 pos;uniform samplerExternalOES uSplr;uniform mat3 uVMat;uniform float uFOVx;uniform float uFOVy;void main() {  vec3 ray = vec3(pos.x * tan(uFOVx), pos.y * tan(uFOVy), -1);  ray = (uVMat * ray).xyz;  ray = normalize(ray);  vec2 texCrd = vec2(    0.5 + atan(ray.x, - ray.z) * INV_PI * 0.5, acos(ray.y) * INV_PI);  gl_FragColor = vec4(texture2D(uSplr, texCrd).xyz, 1.0);}" : ((String)zzvh.zzpd().zzd(zzzx.zzcky))));
            if(v2 == 0) {
                v1 = 0;
            }
            else {
                v1 = GLES20.glCreateProgram();
                zzbbk.zzfe("createProgram");
                if(v1 != 0) {
                    GLES20.glAttachShader(v1, v);
                    zzbbk.zzfe("attachShader");
                    GLES20.glAttachShader(v1, v2);
                    zzbbk.zzfe("attachShader");
                    GLES20.glLinkProgram(v1);
                    zzbbk.zzfe("linkProgram");
                    int[] arr_v1 = new int[1];
                    GLES20.glGetProgramiv(v1, 0x8B82, arr_v1, 0);
                    zzbbk.zzfe("getProgramiv");
                    if(arr_v1[0] == 1) {
                        GLES20.glValidateProgram(v1);
                        zzbbk.zzfe("validateProgram");
                    }
                    else {
                        Log.e("SphericalVideoRenderer", "Could not link program: ");
                        Log.e("SphericalVideoRenderer", GLES20.glGetProgramInfoLog(v1));
                        GLES20.glDeleteProgram(v1);
                        zzbbk.zzfe("deleteProgram");
                        v1 = 0;
                    }
                }
            }
        }
        this.zzebi = v1;
        GLES20.glUseProgram(this.zzebi);
        zzbbk.zzfe("useProgram");
        int v3 = GLES20.glGetAttribLocation(this.zzebi, "aPosition");
        GLES20.glVertexAttribPointer(v3, 3, 0x1406, false, 12, this.zzebl);
        zzbbk.zzfe("vertexAttribPointer");
        GLES20.glEnableVertexAttribArray(v3);
        zzbbk.zzfe("enableVertexAttribArray");
        int[] arr_v2 = new int[1];
        GLES20.glGenTextures(1, arr_v2, 0);
        zzbbk.zzfe("genTextures");
        int v4 = arr_v2[0];
        GLES20.glBindTexture(0x8D65, v4);
        zzbbk.zzfe("bindTextures");
        GLES20.glTexParameteri(0x8D65, 0x2800, 0x2601);
        zzbbk.zzfe("texParameteri");
        GLES20.glTexParameteri(0x8D65, 0x2801, 0x2601);
        zzbbk.zzfe("texParameteri");
        GLES20.glTexParameteri(0x8D65, 0x2802, 0x812F);
        zzbbk.zzfe("texParameteri");
        GLES20.glTexParameteri(0x8D65, 0x2803, 0x812F);
        zzbbk.zzfe("texParameteri");
        this.zzebj = GLES20.glGetUniformLocation(this.zzebi, "uVMat");
        GLES20.glUniformMatrix3fv(this.zzebj, 1, false, new float[]{1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f}, 0);
        if(z && this.zzebi != 0) {
            this.zzebg = new SurfaceTexture(v4);
            this.zzebg.setOnFrameAvailableListener(this);
            this.zzebm.countDown();
            this.zzeaw.start();
            try {
                this.zzebs = true;
            }
            catch(IllegalStateException unused_ex) {
                goto label_137;
            }
            catch(Throwable throwable0) {
                goto label_141;
            }
            finally {
                this.zzeaw.stop();
                this.zzebg.setOnFrameAvailableListener(null);
                this.zzebg = null;
                this.zzym();
            }
            while(!this.zzebt) {
                try {
                    try {
                        while(this.zzebk > 0) {
                            this.zzebg.updateTexImage();
                            --this.zzebk;
                        }
                        if(this.zzeaw.zza(this.zzeag)) {
                            if(Float.isNaN(this.zzebd)) {
                                new float[]{this.zzeag[0] * 0.0f + this.zzeag[1] * 1.0f + this.zzeag[2] * 0.0f, this.zzeag[3] * 0.0f + this.zzeag[4] * 1.0f + this.zzeag[5] * 0.0f, this.zzeag[6] * 0.0f + this.zzeag[7] * 1.0f + this.zzeag[8] * 0.0f};
                                float f = this.zzeag[0] * 0.0f + this.zzeag[1] * 1.0f + this.zzeag[2] * 0.0f;
                                float f1 = this.zzeag[3] * 0.0f + this.zzeag[4] * 1.0f + this.zzeag[5] * 0.0f;
                                float f2 = this.zzeag[6] * 0.0f + this.zzeag[7] * 1.0f + this.zzeag[8] * 0.0f;
                                this.zzebd = -(((float)Math.atan2(f1, f)) - 1.570796f);
                            }
                            zzbbk.zzb(this.zzebb, this.zzebd + this.zzebe);
                        }
                        else {
                            zzbbk.zza(this.zzeag, -1.570796f);
                            zzbbk.zzb(this.zzebb, this.zzebe);
                        }
                        zzbbk.zza(this.zzeax, 1.570796f);
                        zzbbk.zza(this.zzeay, this.zzebb, this.zzeax);
                        zzbbk.zza(this.zzeaz, this.zzeag, this.zzeay);
                        zzbbk.zza(this.zzeba, this.zzebf);
                        zzbbk.zza(this.zzebc, this.zzeba, this.zzeaz);
                        GLES20.glUniformMatrix3fv(this.zzebj, 1, false, this.zzebc, 0);
                        GLES20.glDrawArrays(5, 0, 4);
                        zzbbk.zzfe("drawArrays");
                        GLES20.glFinish();
                        this.zzebo.eglSwapBuffers(this.zzebp, this.zzebr);
                        if(this.zzebs) {
                            GLES20.glViewport(0, 0, this.width, this.height);
                            zzbbk.zzfe("viewport");
                            int v6 = GLES20.glGetUniformLocation(this.zzebi, "uFOVx");
                            int v7 = GLES20.glGetUniformLocation(this.zzebi, "uFOVy");
                            if(this.width > this.height) {
                                GLES20.glUniform1f(v6, 0.872665f);
                                GLES20.glUniform1f(v7, ((float)this.height) * 0.872665f / ((float)this.width));
                            }
                            else {
                                GLES20.glUniform1f(v6, ((float)this.width) * 0.872665f / ((float)this.height));
                                GLES20.glUniform1f(v7, 0.872665f);
                            }
                            this.zzebs = false;
                        }
                        try {
                            object0 = this.zzebn;
                            __monitor_enter(object0);
                        }
                        catch(InterruptedException unused_ex) {
                            continue;
                        }
                    }
                    catch(IllegalStateException unused_ex) {
                        goto label_137;
                    }
                    int v8 = FIN.finallyOpen$NT();
                    if(!this.zzebt && !this.zzebs && this.zzebk == 0) {
                        try {
                            this.zzebn.wait();
                        label_133:
                            FIN.finallyCodeBegin$NT(v8);
                            __monitor_exit(object0);
                            FIN.finallyCodeEnd$NT(v8);
                            continue;
                        }
                        catch(InterruptedException unused_ex) {
                            continue;
                        }
                        catch(IllegalStateException unused_ex) {
                            goto label_137;
                        }
                    }
                    goto label_133;
                }
                catch(Throwable throwable0) {
                    goto label_141;
                }
            label_137:
                zzawf.zzfa("SphericalVideoProcessor halted unexpectedly.");
                return;
            label_141:
                zzawf.zzc("SphericalVideoProcessor died.", throwable0);
                zzq.zzkz().zza(throwable0, "SphericalVideoProcessor.run.2");
                return;
            }
            return;
        }
        String s = GLUtils.getEGLErrorString(this.zzebo.eglGetError());
        String s1 = s.length() == 0 ? new String("EGL initialization failed: ") : "EGL initialization failed: " + s;
        zzawf.zzey(s1);
        zzq.zzkz().zza(new Throwable(s1), "SphericalVideoProcessor.run.1");
        this.zzym();
        this.zzebm.countDown();
    }

    private static void zza(float[] arr_f, float f) {
        arr_f[0] = 1.0f;
        arr_f[1] = 0.0f;
        arr_f[2] = 0.0f;
        arr_f[3] = 0.0f;
        arr_f[4] = (float)Math.cos(f);
        arr_f[5] = (float)(-Math.sin(f));
        arr_f[6] = 0.0f;
        arr_f[7] = (float)Math.sin(f);
        arr_f[8] = (float)Math.cos(f);
    }

    private static void zza(float[] arr_f, float[] arr_f1, float[] arr_f2) {
        arr_f[0] = arr_f1[0] * arr_f2[0] + arr_f1[1] * arr_f2[3] + arr_f1[2] * arr_f2[6];
        arr_f[1] = arr_f1[0] * arr_f2[1] + arr_f1[1] * arr_f2[4] + arr_f1[2] * arr_f2[7];
        arr_f[2] = arr_f1[0] * arr_f2[2] + arr_f1[1] * arr_f2[5] + arr_f1[2] * arr_f2[8];
        arr_f[3] = arr_f1[3] * arr_f2[0] + arr_f1[4] * arr_f2[3] + arr_f1[5] * arr_f2[6];
        arr_f[4] = arr_f1[3] * arr_f2[1] + arr_f1[4] * arr_f2[4] + arr_f1[5] * arr_f2[7];
        arr_f[5] = arr_f1[3] * arr_f2[2] + arr_f1[4] * arr_f2[5] + arr_f1[5] * arr_f2[8];
        arr_f[6] = arr_f1[6] * arr_f2[0] + arr_f1[7] * arr_f2[3] + arr_f1[8] * arr_f2[6];
        arr_f[7] = arr_f1[6] * arr_f2[1] + arr_f1[7] * arr_f2[4] + arr_f1[8] * arr_f2[7];
        arr_f[8] = arr_f1[6] * arr_f2[2] + arr_f1[7] * arr_f2[5] + arr_f1[8] * arr_f2[8];
    }

    public final void zza(SurfaceTexture surfaceTexture0, int v, int v1) {
        this.width = v;
        this.height = v1;
        this.zzebh = surfaceTexture0;
    }

    private static void zzb(float[] arr_f, float f) {
        arr_f[0] = (float)Math.cos(f);
        arr_f[1] = (float)(-Math.sin(f));
        arr_f[2] = 0.0f;
        arr_f[3] = (float)Math.sin(f);
        arr_f[4] = (float)Math.cos(f);
        arr_f[5] = 0.0f;
        arr_f[6] = 0.0f;
        arr_f[7] = 0.0f;
        arr_f[8] = 1.0f;
    }

    public final void zzb(float f, float f1) {
        float f3;
        float f2;
        int v = this.width;
        int v1 = this.height;
        if(v > v1) {
            f2 = f * 1.745329f / ((float)v);
            f3 = f1 * 1.745329f / ((float)v);
        }
        else {
            f2 = f * 1.745329f / ((float)v1);
            f3 = f1 * 1.745329f / ((float)v1);
        }
        this.zzebe -= f2;
        this.zzebf -= f3;
        if(this.zzebf < -1.570796f) {
            this.zzebf = -1.570796f;
        }
        if(this.zzebf > 1.570796f) {
            this.zzebf = 1.570796f;
        }
    }

    private static int zzc(int v, String s) {
        int v1 = GLES20.glCreateShader(v);
        zzbbk.zzfe("createShader");
        if(v1 != 0) {
            GLES20.glShaderSource(v1, s);
            zzbbk.zzfe("shaderSource");
            GLES20.glCompileShader(v1);
            zzbbk.zzfe("compileShader");
            int[] arr_v = new int[1];
            GLES20.glGetShaderiv(v1, 0x8B81, arr_v, 0);
            zzbbk.zzfe("getShaderiv");
            if(arr_v[0] == 0) {
                Log.e("SphericalVideoRenderer", "Could not compile shader " + v + ":");
                Log.e("SphericalVideoRenderer", GLES20.glGetShaderInfoLog(v1));
                GLES20.glDeleteShader(v1);
                zzbbk.zzfe("deleteShader");
                return 0;
            }
        }
        return v1;
    }

    private static void zzfe(String s) {
        int v = GLES20.glGetError();
        if(v != 0) {
            Log.e("SphericalVideoRenderer", s + ": glError " + v);
        }
    }

    public final void zzm(int v, int v1) {
        synchronized(this.zzebn) {
            this.width = v;
            this.height = v1;
            this.zzebs = true;
            this.zzebn.notifyAll();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbbh
    public final void zztv() {
        synchronized(this.zzebn) {
            this.zzebn.notifyAll();
        }
    }

    public final void zzyk() {
        synchronized(this.zzebn) {
            this.zzebt = true;
            this.zzebh = null;
            this.zzebn.notifyAll();
        }
    }

    public final SurfaceTexture zzyl() {
        if(this.zzebh == null) {
            return null;
        }
        try {
            this.zzebm.await();
        }
        catch(InterruptedException unused_ex) {
        }
        return this.zzebg;
    }

    @VisibleForTesting
    private final boolean zzym() {
        boolean z = false;
        if(this.zzebr != null && this.zzebr != EGL10.EGL_NO_SURFACE) {
            boolean z1 = this.zzebo.eglMakeCurrent(this.zzebp, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            z = this.zzebo.eglDestroySurface(this.zzebp, this.zzebr) | z1;
            this.zzebr = null;
        }
        EGLContext eGLContext0 = this.zzebq;
        if(eGLContext0 != null) {
            z |= this.zzebo.eglDestroyContext(this.zzebp, eGLContext0);
            this.zzebq = null;
        }
        EGLDisplay eGLDisplay0 = this.zzebp;
        if(eGLDisplay0 != null) {
            z |= this.zzebo.eglTerminate(eGLDisplay0);
            this.zzebp = null;
        }
        return z;
    }
}

