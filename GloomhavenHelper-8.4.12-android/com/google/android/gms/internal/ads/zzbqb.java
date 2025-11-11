package com.google.android.gms.internal.ads;

public final class zzbqb implements zzeej {
    private final zzeew zzfhn;
    private final zzeew zzfkd;

    private zzbqb(zzeew zzeew0, zzeew zzeew1) {
        this.zzfhn = zzeew0;
        this.zzfkd = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbqc(((zzdei)this.zzfhn.get()), ((String)this.zzfkd.get()));
    }

    public static zzbqb zzl(zzeew zzeew0, zzeew zzeew1) {
        return new zzbqb(zzeew0, zzeew1);
    }
}

