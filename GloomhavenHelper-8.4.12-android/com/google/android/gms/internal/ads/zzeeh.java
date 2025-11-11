package com.google.android.gms.internal.ads;

public final class zzeeh implements zzeej {
    private zzeew zzigh;

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        zzeew zzeew0 = this.zzigh;
        if(zzeew0 == null) {
            throw new IllegalStateException();
        }
        return zzeew0.get();
    }

    public static void zzbd(zzeew zzeew0, zzeew zzeew1) {
        zzeep.checkNotNull(zzeew1);
        if(((zzeeh)zzeew0).zzigh != null) {
            throw new IllegalStateException();
        }
        ((zzeeh)zzeew0).zzigh = zzeew1;
    }
}

