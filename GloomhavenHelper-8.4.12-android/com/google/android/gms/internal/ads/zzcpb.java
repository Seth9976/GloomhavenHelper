package com.google.android.gms.internal.ads;

public final class zzcpb implements zzeej {
    private final zzeew zzfmx;
    private final zzeew zzfmy;
    private final zzeew zzfmz;
    private final zzeew zzgcz;

    public zzcpb(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzfmx = zzeew0;
        this.zzfmy = zzeew1;
        this.zzgcz = zzeew2;
        this.zzfmz = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcoy(((zzbgk)this.zzfmx.get()), ((zza)this.zzfmy.get()), ((zzcrh)this.zzgcz.get()), ((zzbtl)this.zzfmz.get()));
    }
}

