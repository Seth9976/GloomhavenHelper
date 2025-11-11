package com.google.android.gms.internal.ads;

import java.util.Map;

public final class zzbxl implements zzeej {
    private final zzeew zzfgl;
    private final zzeew zzfhy;
    private final zzeew zzfmq;
    private final zzeew zzfms;
    private final zzeew zzfmt;

    public zzbxl(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4) {
        this.zzfhy = zzeew0;
        this.zzfms = zzeew1;
        this.zzfmt = zzeew2;
        this.zzfmq = zzeew3;
        this.zzfgl = zzeew4;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        Object object0 = this.zzfhy.get();
        Object object1 = this.zzfms.get();
        Object object2 = this.zzfmt.get();
        Object object3 = this.zzfgl.get();
        return new zzbxi(((Map)object0), ((Map)object1), ((Map)object2), this.zzfmq, ((zzbzg)object3));
    }
}

