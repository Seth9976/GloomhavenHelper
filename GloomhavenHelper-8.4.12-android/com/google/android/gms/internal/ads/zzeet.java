package com.google.android.gms.internal.ads;

public final class zzeet implements zzeew {
    private volatile Object zzdwc;
    private static final Object zzigi;
    private volatile zzeew zzigj;

    static {
        zzeet.zzigi = new Object();
    }

    private zzeet(zzeew zzeew0) {
        this.zzdwc = zzeet.zzigi;
        this.zzigj = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        Object object0 = this.zzdwc;
        if(object0 == zzeet.zzigi) {
            zzeew zzeew0 = this.zzigj;
            if(zzeew0 == null) {
                return this.zzdwc;
            }
            object0 = zzeew0.get();
            this.zzdwc = object0;
            this.zzigj = null;
        }
        return object0;
    }

    public static zzeew zzaq(zzeew zzeew0) {
        return !(zzeew0 instanceof zzeet) && !(zzeew0 instanceof zzeek) ? new zzeet(((zzeew)zzeep.checkNotNull(zzeew0))) : zzeew0;
    }
}

