package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;

public final class zzbxj implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfdd;
    private final zzeew zzfec;
    private final zzeew zzffj;
    private final zzeew zzfht;

    private zzbxj(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4) {
        this.zzfht = zzeew0;
        this.zzelc = zzeew1;
        this.zzfdd = zzeew2;
        this.zzfec = zzeew3;
        this.zzffj = zzeew4;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzbxg(((zzauf)this.zzfht.get()), ((Context)this.zzelc.get()), ((zzaui)this.zzfdd.get()), ((View)this.zzfec.get()), ((int)(((Integer)this.zzffj.get()))));
    }

    public static zzbxj zzd(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4) {
        return new zzbxj(zzeew0, zzeew1, zzeew2, zzeew3, zzeew4);
    }
}

