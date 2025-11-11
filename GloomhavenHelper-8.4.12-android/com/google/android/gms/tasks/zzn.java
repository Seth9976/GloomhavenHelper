package com.google.android.gms.tasks;

final class zzn implements Runnable {
    private final Task zzg;
    private final zzm zzq;

    zzn(zzm zzm0, Task task0) {
        this.zzq = zzm0;
        this.zzg = task0;
        super();
    }

    @Override
    public final void run() {
        synchronized(zzm.zza(this.zzq)) {
            if(zzm.zzb(this.zzq) != null) {
                zzm.zzb(this.zzq).onSuccess(this.zzg.getResult());
            }
        }
    }
}

