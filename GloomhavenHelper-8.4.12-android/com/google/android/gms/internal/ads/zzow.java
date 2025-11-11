package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.view.Surface;

@TargetApi(17)
public final class zzow extends Surface {
    private final boolean zzazi;
    private static boolean zzbhj;
    private static boolean zzbhk;
    private final zzoy zzbhl;
    private boolean zzbhm;

    private zzow(zzoy zzoy0, SurfaceTexture surfaceTexture0, boolean z) {
        super(surfaceTexture0);
        this.zzbhl = zzoy0;
        this.zzazi = z;
    }

    zzow(zzoy zzoy0, SurfaceTexture surfaceTexture0, boolean z, zzov zzov0) {
        this(zzoy0, surfaceTexture0, z);
    }

    @Override  // android.view.Surface
    public final void release() {
        super.release();
        synchronized(this.zzbhl) {
            if(!this.zzbhm) {
                this.zzbhl.release();
                this.zzbhm = true;
            }
        }
    }

    public static zzow zzc(Context context0, boolean z) {
        zzob.checkState(!z || zzow.zzd(context0));
        return new zzoy().zzm(z);
    }

    public static boolean zzd(Context context0) {
        synchronized(zzow.class) {
            if(!zzow.zzbhk) {
                boolean z = false;
                String s = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 0x3055);
                if(s != null && s.contains("EGL_EXT_protected_content")) {
                    z = true;
                }
                zzow.zzbhj = z;
                zzow.zzbhk = true;
            }
            return zzow.zzbhj;
        }
    }
}

