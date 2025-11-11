package com.google.android.gms.tasks;

final class zzh implements Runnable {
    private final zzg zzk;

    zzh(zzg zzg0) {
        this.zzk = zzg0;
        super();
    }

    @Override
    public final void run() {
        synchronized(zzg.zza(this.zzk)) {
            if(zzg.zzb(this.zzk) != null) {
                zzg.zzb(this.zzk).onCanceled();
            }
        }
    }
}

