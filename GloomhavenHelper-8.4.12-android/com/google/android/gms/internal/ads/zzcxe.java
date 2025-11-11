package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcxe implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzeqp;

    private zzcxe(zzeew zzeew0, zzeew zzeew1) {
        this.zzelc = zzeew0;
        this.zzeqp = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzcxe.zzt(((Context)this.zzelc.get()), ((String)this.zzeqp.get()));
    }

    public static zzcxe zzay(zzeew zzeew0, zzeew zzeew1) {
        return new zzcxe(zzeew0, zzeew1);
    }

    public static zzcxc zzt(Context context0, String s) {
        return new zzcxc(context0, s);
    }
}

