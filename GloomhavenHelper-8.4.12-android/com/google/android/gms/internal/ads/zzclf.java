package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzclf implements zzeej {
    private final zzeew zzelc;

    private zzclf(zzeew zzeew0) {
        this.zzelc = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzclc(((Context)this.zzelc.get()));
    }

    public static zzclf zzad(zzeew zzeew0) {
        return new zzclf(zzeew0);
    }
}

