package com.google.android.gms.internal.ads;

public final class zzbmz implements zzeej {
    private final zzeew zzfeb;
    private final zzeew zzfhp;
    private final zzeew zzfhq;

    private zzbmz(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzfeb = zzeew0;
        this.zzfhp = zzeew1;
        this.zzfhq = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbmw(((zzdei)this.zzfeb.get()), ((zzbqp)this.zzfhp.get()), ((zzbrr)this.zzfhq.get()));
    }

    public static zzbmz zzf(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        return new zzbmz(zzeew0, zzeew1, zzeew2);
    }
}

