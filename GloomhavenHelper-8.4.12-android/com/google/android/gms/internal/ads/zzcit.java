package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcit implements zzeej {
    private final zzeew zzelc;

    private zzcit(zzeew zzeew0) {
        this.zzelc = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzcit.zzcf(((Context)this.zzelc.get()));
    }

    public static zzcit zzac(zzeew zzeew0) {
        return new zzcit(zzeew0);
    }

    public static String zzcf(Context context0) {
        return (String)zzeep.zza("com.esotericsoftware.gloomhavenhelper", "Cannot return null from a non-@Nullable @Provides method");
    }
}

