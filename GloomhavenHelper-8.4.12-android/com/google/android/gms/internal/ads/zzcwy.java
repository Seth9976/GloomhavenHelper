package com.google.android.gms.internal.ads;

import android.os.Bundle;

public final class zzcwy implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzgjp;

    private zzcwy(zzeew zzeew0, zzeew zzeew1) {
        this.zzfev = zzeew0;
        this.zzgjp = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcww(((zzdoe)this.zzfev.get()), ((Bundle)this.zzgjp.get()));
    }

    public static zzcwy zzax(zzeew zzeew0, zzeew zzeew1) {
        return new zzcwy(zzeew0, zzeew1);
    }
}

