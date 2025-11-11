package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Set;

public final class zzcwq implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzght;
    private final zzeew zzgid;

    private zzcwq(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzght = zzeew0;
        this.zzelc = zzeew1;
        this.zzgid = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcwo(((zzdoe)this.zzght.get()), ((Context)this.zzelc.get()), ((Set)this.zzgid.get()));
    }

    public static zzcwq zzp(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        return new zzcwq(zzeew0, zzeew1, zzeew2);
    }
}

