package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import java.util.concurrent.ScheduledExecutorService;

public final class zzdac implements zzeej {
    private final zzeew zzevg;
    private final zzeew zzfst;
    private final zzeew zzfxt;
    private final zzeew zzglz;

    public zzdac(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3) {
        this.zzevg = zzeew0;
        this.zzfst = zzeew1;
        this.zzglz = zzeew2;
        this.zzfxt = zzeew3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzdaa(((zzalb)this.zzevg.get()), ((ScheduledExecutorService)this.zzfst.get()), ((Boolean)this.zzglz.get()).booleanValue(), ((ApplicationInfo)this.zzfxt.get()));
    }
}

