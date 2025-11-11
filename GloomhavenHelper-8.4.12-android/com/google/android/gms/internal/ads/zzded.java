package com.google.android.gms.internal.ads;

public final class zzded implements zzeej {
    private final zzeew zzfbm;
    private final zzeew zzgmw;
    private final zzeew zzgmx;

    public zzded(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzgmw = zzeew0;
        this.zzgmx = zzeew1;
        this.zzfbm = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzdec(((zzddq)this.zzgmw.get()), ((zzdct)this.zzgmx.get()), ((zzdep)this.zzfbm.get()));
    }
}

