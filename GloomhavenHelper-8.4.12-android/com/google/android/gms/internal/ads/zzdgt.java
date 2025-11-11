package com.google.android.gms.internal.ads;

final class zzdgt implements zzdnu {
    private final zzdgs zzgtz;

    zzdgt(zzdgs zzdgs0) {
        this.zzgtz = zzdgs0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        synchronized(this.zzgtz) {
            this.zzgtz.zzgtw.zza(((zzdgk)object0));
            if(this.zzgtz.zzgtv != zzdgi.zzgth) {
                this.zzgtz.zzgnx.zza(this.zzgtz.zzgtt.zzaql(), ((zzdgk)object0));
            }
            if(this.zzgtz.zzgtv == zzdgi.zzgtg) {
                this.zzgtz.zzd(this.zzgtz.zzgtt);
            }
            this.zzgtz.zzgtv = zzdgi.zzgtg;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        this.zzgtz.zzgtw.zzb(throwable0);
    }
}

