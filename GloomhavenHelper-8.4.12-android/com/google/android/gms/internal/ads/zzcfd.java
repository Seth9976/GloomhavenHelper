package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcfd implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzffj;
    private final zzeew zzfjs;
    private final zzeew zzfkh;
    private final zzeew zzfuy;

    private zzcfd(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4) {
        this.zzelc = zzeew0;
        this.zzfjs = zzeew1;
        this.zzfkh = zzeew2;
        this.zzffj = zzeew3;
        this.zzfuy = zzeew4;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        Context context0 = (Context)this.zzelc.get();
        String s = (String)this.zzfjs.get();
        zzazo zzazo0 = (zzazo)this.zzfkh.get();
        int v = (int)(((Integer)this.zzffj.get()));
        String s1 = (String)this.zzfuy.get();
        zzsn zzsn0 = new zzsn(new zzss(context0));
        zztu zztu0 = new zztu();
        zztu0.zzcba = zzazo0.zzdxf;
        zztu0.zzcbb = zzazo0.zzdxg;
        zztu0.zzcbc = (int)(zzazo0.zzdxh ? 0 : 2);
        zzsn0.zza(new zzcfa(v, s, zztu0, s1));
        return (zzsn)zzeep.zza(zzsn0, "Cannot return null from a non-@Nullable @Provides method");
    }

    public static zzcfd zze(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2, zzeew zzeew3, zzeew zzeew4) {
        return new zzcfd(zzeew0, zzeew1, zzeew2, zzeew3, zzeew4);
    }
}

