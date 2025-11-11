package com.google.android.gms.internal.ads;

public final class zzeek implements zzeed, zzeew {
    private volatile Object zzdwc;
    private static final Object zzigi;
    private volatile zzeew zzigj;

    static {
        zzeek.zzigi = new Object();
    }

    private zzeek(zzeew zzeew0) {
        this.zzdwc = zzeek.zzigi;
        this.zzigj = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeed, com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        Object object0 = this.zzdwc;
        if(object0 == zzeek.zzigi) {
            synchronized(this) {
                object0 = this.zzdwc;
                if(object0 == zzeek.zzigi) {
                    object0 = this.zzigj.get();
                    Object object1 = this.zzdwc;
                    if(object1 != zzeek.zzigi && !(object1 instanceof zzeeq) && object1 != object0) {
                        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + object1 + " & " + object0 + ". This is likely due to a circular dependency.");
                    }
                    this.zzdwc = object0;
                    this.zzigj = null;
                }
                return object0;
            }
        }
        return object0;
    }

    public static zzeew zzaq(zzeew zzeew0) {
        zzeep.checkNotNull(zzeew0);
        return zzeew0 instanceof zzeek ? zzeew0 : new zzeek(zzeew0);
    }

    public static zzeed zzar(zzeew zzeew0) {
        return zzeew0 instanceof zzeed ? ((zzeed)zzeew0) : new zzeek(((zzeew)zzeep.checkNotNull(zzeew0)));
    }
}

