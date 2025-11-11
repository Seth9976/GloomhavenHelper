package com.google.android.gms.internal.ads;

public final class zzcwd implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzgbw;

    private zzcwd(zzeew zzeew0, zzeew zzeew1) {
        this.zzfev = zzeew0;
        this.zzgbw = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcwb(((zzdoe)this.zzfev.get()), ((zzdeg)this.zzgbw.get()));
    }

    public static zzcwd zzav(zzeew zzeew0, zzeew zzeew1) {
        return new zzcwd(zzeew0, zzeew1);
    }
}

