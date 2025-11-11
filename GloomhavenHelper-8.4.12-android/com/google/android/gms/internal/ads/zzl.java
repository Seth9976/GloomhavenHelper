package com.google.android.gms.internal.ads;

final class zzl implements Runnable {
    private final zzq zzw;
    private final zzz zzx;
    private final Runnable zzy;

    public zzl(zzq zzq0, zzz zzz0, Runnable runnable0) {
        this.zzw = zzq0;
        this.zzx = zzz0;
        this.zzy = runnable0;
    }

    @Override
    public final void run() {
        if(this.zzx.zzbi == null) {
            this.zzw.zza(this.zzx.result);
        }
        else {
            this.zzw.zzb(this.zzx.zzbi);
        }
        if(this.zzx.zzbj) {
            this.zzw.zzb("intermediate-response");
        }
        else {
            this.zzw.zzc("done");
        }
        Runnable runnable0 = this.zzy;
        if(runnable0 != null) {
            runnable0.run();
        }
    }
}

