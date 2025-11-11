package com.google.android.gms.tasks;

final class zzl implements Runnable {
    private final Task zzg;
    private final zzk zzo;

    zzl(zzk zzk0, Task task0) {
        this.zzo = zzk0;
        this.zzg = task0;
        super();
    }

    @Override
    public final void run() {
        synchronized(zzk.zza(this.zzo)) {
            if(zzk.zzb(this.zzo) != null) {
                zzk.zzb(this.zzo).onFailure(this.zzg.getException());
            }
        }
    }
}

