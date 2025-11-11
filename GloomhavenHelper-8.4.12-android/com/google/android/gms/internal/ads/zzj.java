package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.concurrent.Executor;

public final class zzj implements zzaa {
    private final Executor zzv;

    public zzj(Handler handler0) {
        this.zzv = new zzi(this, handler0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaa
    public final void zza(zzq zzq0, zzae zzae0) {
        zzq0.zzb("post-error");
        zzl zzl0 = new zzl(zzq0, zzz.zzd(zzae0), null);
        this.zzv.execute(zzl0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaa
    public final void zza(zzq zzq0, zzz zzz0, Runnable runnable0) {
        zzq0.zzk();
        zzq0.zzb("post-response");
        zzl zzl0 = new zzl(zzq0, zzz0, runnable0);
        this.zzv.execute(zzl0);
    }

    @Override  // com.google.android.gms.internal.ads.zzaa
    public final void zzb(zzq zzq0, zzz zzz0) {
        this.zza(zzq0, zzz0, null);
    }
}

