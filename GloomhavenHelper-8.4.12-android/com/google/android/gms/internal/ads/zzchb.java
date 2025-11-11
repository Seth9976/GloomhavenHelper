package com.google.android.gms.internal.ads;

public final class zzchb implements zzeej {
    private final zzeew zzfwj;
    private final zzeew zzfwk;

    public zzchb(zzeew zzeew0, zzeew zzeew1) {
        this.zzfwj = zzeew0;
        this.zzfwk = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcgy(((String)this.zzfwj.get()), ((zzcgx)this.zzfwk.get()));
    }
}

