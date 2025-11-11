package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzbic implements zzdea {
    private final zzbhf zzene;
    private zzeew zzeyl;
    private zzeew zzeym;
    private zzeew zzfbk;
    private zzeew zzfbl;
    private zzeew zzfbm;
    private zzeew zzfbn;
    private zzeew zzfbo;
    private zzeew zzfbp;

    private zzbic(zzbhf zzbhf0, Context context0, String s) {
        this.zzene = zzbhf0;
        super();
        this.zzeyl = zzeem.zzbe(context0);
        this.zzfbk = new zzdcr(this.zzeyl, zzbhf.zzah(this.zzene), zzbhf.zzai(this.zzene));
        this.zzfbl = zzeek.zzaq(new zzddo(zzbhf.zzah(this.zzene)));
        this.zzfbm = zzeek.zzaq(zzdes.zzaqw());
        this.zzfbn = zzeek.zzaq(new zzddt(this.zzeyl, zzbhf.zzw(this.zzene), zzbhf.zzq(this.zzene), this.zzfbk, this.zzfbl, zzdev.zzaqy(), this.zzfbm));
        this.zzfbo = zzeek.zzaq(new zzded(this.zzfbn, this.zzfbl, this.zzfbm));
        this.zzeym = zzeem.zzbf(s);
        this.zzfbp = zzeek.zzaq(new zzddx(this.zzeym, this.zzfbn, this.zzfbl, this.zzfbm));
    }

    zzbic(zzbhf zzbhf0, Context context0, String s, zzbhi zzbhi0) {
        this(zzbhf0, context0, s);
    }

    @Override  // com.google.android.gms.internal.ads.zzdea
    public final zzdec zzafb() {
        return (zzdec)this.zzfbo.get();
    }

    @Override  // com.google.android.gms.internal.ads.zzdea
    public final zzddw zzafc() {
        return (zzddw)this.zzfbp.get();
    }
}

