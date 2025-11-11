package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zza;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

public final class zzcdf implements Callable {
    private final zzbee zzbms;
    private final zzazo zzdjo;
    private final zzdq zzehb;
    private final Executor zzfeo;
    private final zza zzfsj;
    private final Context zzur;

    public zzcdf(Context context0, Executor executor0, zzdq zzdq0, zzazo zzazo0, zza zza0, zzbee zzbee0) {
        this.zzur = context0;
        this.zzfeo = executor0;
        this.zzehb = zzdq0;
        this.zzdjo = zzazo0;
        this.zzfsj = zza0;
        this.zzbms = zzbee0;
    }

    @Override
    public final Object call() throws Exception {
        zzccv zzccv0 = new zzccv(this);
        zzccv0.zzalx();
        return zzccv0;
    }
}

