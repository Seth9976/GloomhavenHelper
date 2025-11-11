package com.google.android.gms.internal.ads;

import android.view.View;
import java.util.Map;
import java.util.concurrent.Executor;

public final class zzcby {
    private final Executor zzfeo;
    private final zzbuu zzfnj;
    private final zzbkk zzfsz;

    zzcby(Executor executor0, zzbkk zzbkk0, zzbuu zzbuu0) {
        this.zzfeo = executor0;
        this.zzfnj = zzbuu0;
        this.zzfsz = zzbkk0;
    }

    // 检测为 Lambda 实现
    final void zze(zzbdv zzbdv0, Map map0) [...]

    // 检测为 Lambda 实现
    final void zzf(zzbdv zzbdv0, Map map0) [...]

    public final void zzl(zzbdv zzbdv0) {
        if(zzbdv0 == null) {
            return;
        }
        View view0 = zzbdv0.getView();
        this.zzfnj.zzq(view0);
        zzccb zzccb0 = new zzccb(zzbdv0);
        this.zzfnj.zza(zzccb0, this.zzfeo);
        zzcca zzcca0 = new zzcca(zzbdv0);
        this.zzfnj.zza(zzcca0, this.zzfeo);
        this.zzfnj.zza(this.zzfsz, this.zzfeo);
        this.zzfsz.zzg(zzbdv0);
        zzbdv0.zza("/trackActiveViewUnit", (zzbdv zzbdv0, Map map0) -> this.zzfsz.enable());
        zzbdv0.zza("/untrackActiveViewUnit", (zzbdv zzbdv0, Map map0) -> this.zzfsz.disable());
    }
}

