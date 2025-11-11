package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzdcr implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzemv;
    private final zzeew zzemw;

    public zzdcr(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzelc = zzeew0;
        this.zzemv = zzeew1;
        this.zzemw = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        Context context0 = (Context)this.zzelc.get();
        zzdfw zzdfw0 = (zzdfw)this.zzemv.get();
        zzdgo zzdgo0 = (zzdgo)this.zzemw.get();
        if(((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcrl)))) > 0) {
            zzdbn zzdbn0 = new zzdbn();
            zzdbw zzdbw0 = new zzdbw(zzdbn0);
            zzdgn zzdgn0 = zzdgo0.zza(zzdgf.zzgsj, context0, zzdfw0, zzdbw0);
            return (zzdco)zzeep.zza(new zzdbs(new zzdcf(new zzdcg()), new zzdbx(zzdgn0.zzgnx, zzazq.zzdxk), zzdbn0, zzdgn0.zzgto, zzazq.zzdxk), "Cannot return null from a non-@Nullable @Provides method");
        }
        return (zzdco)zzeep.zza(new zzdcg(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

