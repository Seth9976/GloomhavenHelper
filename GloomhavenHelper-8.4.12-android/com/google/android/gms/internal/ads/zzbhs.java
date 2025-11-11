package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzbhs implements zzdbf {
    private final zzbhf zzene;
    private zzeew zzeyl;
    private zzeew zzeym;
    private zzeew zzeyn;
    private zzeew zzeyo;
    private zzeew zzeyp;
    private zzeew zzeyq;

    private zzbhs(zzbhf zzbhf0, Context context0, String s) {
        this.zzene = zzbhf0;
        super();
        this.zzeyl = zzeem.zzbe(context0);
        this.zzeym = zzeem.zzbe(s);
        this.zzeyn = new zzdcs(this.zzeyl, zzbhf.zzah(this.zzene), zzbhf.zzai(this.zzene));
        this.zzeyo = zzeek.zzaq(new zzdbo(zzbhf.zzah(this.zzene)));
        this.zzeyp = zzeek.zzaq(new zzdba(this.zzeyl, zzbhf.zzw(this.zzene), zzbhf.zzq(this.zzene), this.zzeyn, this.zzeyo, zzdev.zzaqy()));
        this.zzeyq = zzeek.zzaq(new zzdbg(zzbhf.zzq(this.zzene), this.zzeyl, this.zzeym, this.zzeyp, this.zzeyo, zzbhf.zzn(this.zzene)));
    }

    zzbhs(zzbhf zzbhf0, Context context0, String s, zzbhi zzbhi0) {
        this(zzbhf0, context0, s);
    }

    @Override  // com.google.android.gms.internal.ads.zzdbf
    public final zzdaz zzaem() {
        return (zzdaz)this.zzeyq.get();
    }
}

