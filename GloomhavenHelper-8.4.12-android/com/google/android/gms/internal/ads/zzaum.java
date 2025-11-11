package com.google.android.gms.internal.ads;

final class zzaum implements Runnable {
    private final String zzdcq;
    private final zzaui zzdqx;
    private final zzauy zzdqy;

    zzaum(zzaui zzaui0, zzauy zzauy0, String s) {
        this.zzdqx = zzaui0;
        this.zzdqy = zzauy0;
        this.zzdcq = s;
    }

    @Override
    public final void run() {
        this.zzdqx.zza(this.zzdqy, this.zzdcq);
    }
}

