package com.google.android.gms.internal.ads;

public final class zzddx implements zzeej {
    private final zzeew zzfbm;
    private final zzeew zzgmv;
    private final zzeew zzgmw;
    private final zzeew zzgmx;

    public zzddx(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzgmv = zzeew0;
        this.zzgmw = zzeew1;
        this.zzgmx = zzeew2;
        this.zzfbm = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzddw(((String)this.zzgmv.get()), ((zzddq)this.zzgmw.get()), ((zzdct)this.zzgmx.get()), ((zzdep)this.zzfbm.get()));
    }
}

