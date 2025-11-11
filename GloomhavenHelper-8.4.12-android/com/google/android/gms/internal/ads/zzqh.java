package com.google.android.gms.internal.ads;

final class zzqh implements Runnable {
    private final zzqi zzbpm;

    zzqh(zzqi zzqi0) {
        this.zzbpm = zzqi0;
        super();
    }

    @Override
    public final void run() {
        synchronized(zzqi.zza(this.zzbpm)) {
            if(!zzqi.zzb(this.zzbpm) || !zzqi.zzc(this.zzbpm)) {
                zzawf.zzeb("App is still foreground");
            }
            else {
                zzqi.zza(this.zzbpm, false);
                zzawf.zzeb("App went background");
                for(Object object1: zzqi.zzd(this.zzbpm)) {
                    zzqk zzqk0 = (zzqk)object1;
                    try {
                        zzqk0.zzp(false);
                    }
                    catch(Exception exception0) {
                        zzazh.zzc("", exception0);
                    }
                }
            }
        }
    }
}

