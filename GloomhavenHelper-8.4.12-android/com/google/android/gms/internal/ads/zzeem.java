package com.google.android.gms.internal.ads;

public final class zzeem implements zzeed, zzeej {
    private final Object zzdwc;
    private static final zzeem zzigl;

    static {
        zzeem.zzigl = new zzeem(null);
    }

    private zzeem(Object object0) {
        this.zzdwc = object0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeed, com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return this.zzdwc;
    }

    public static zzeej zzbe(Object object0) {
        return new zzeem(zzeep.zza(object0, "instance cannot be null"));
    }

    public static zzeej zzbf(Object object0) {
        return object0 == null ? zzeem.zzigl : new zzeem(object0);
    }
}

