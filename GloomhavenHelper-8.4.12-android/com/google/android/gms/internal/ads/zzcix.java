package com.google.android.gms.internal.ads;

public abstract class zzcix implements zzdng {
    private final zzbsm zzfxx;

    public zzcix(zzbsm zzbsm0) {
        this.zzfxx = zzbsm0;
    }

    protected abstract zzdof zzb(zzaqx arg1) throws zzcid;

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) throws Exception {
        this.zzfxx.zzd(((zzaqx)object0));
        zzdof zzdof0 = this.zzb(((zzaqx)object0));
        zzdnt.zza(zzdof0, new zzciw(this), zzazq.zzdxp);
        return zzdof0;
    }
}

