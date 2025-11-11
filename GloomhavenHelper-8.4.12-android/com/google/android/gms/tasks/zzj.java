package com.google.android.gms.tasks;

final class zzj implements Runnable {
    private final Task zzg;
    private final zzi zzm;

    zzj(zzi zzi0, Task task0) {
        this.zzm = zzi0;
        this.zzg = task0;
        super();
    }

    @Override
    public final void run() {
        synchronized(zzi.zza(this.zzm)) {
            if(zzi.zzb(this.zzm) != null) {
                zzi.zzb(this.zzm).onComplete(this.zzg);
            }
        }
    }
}

