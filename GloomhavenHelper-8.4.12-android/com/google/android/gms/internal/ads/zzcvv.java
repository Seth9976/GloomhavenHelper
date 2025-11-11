package com.google.android.gms.internal.ads;

public final class zzcvv implements zzeej {
    private final zzeew zzfev;

    private zzcvv(zzeew zzeew0) {
        this.zzfev = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcvt(((zzdoe)this.zzfev.get()));
    }

    public static zzcvv zzal(zzeew zzeew0) {
        return new zzcvv(zzeew0);
    }
}

