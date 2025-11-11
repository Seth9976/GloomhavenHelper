package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.zzg;

final class zzcqb implements zzbxb {
    private final zzazo zzblr;
    private final zzdei zzfhg;
    private final zzdeu zzfir;
    private final zzceb zzfrh;
    private final zzdof zzgcn;
    private final zzbdv zzgdq;
    private final Context zzur;

    private zzcqb(Context context0, zzceb zzceb0, zzdeu zzdeu0, zzazo zzazo0, zzdei zzdei0, zzdof zzdof0, zzbdv zzbdv0) {
        this.zzur = context0;
        this.zzfrh = zzceb0;
        this.zzfir = zzdeu0;
        this.zzblr = zzazo0;
        this.zzfhg = zzdei0;
        this.zzgcn = zzdof0;
        this.zzgdq = zzbdv0;
    }

    zzcqb(Context context0, zzceb zzceb0, zzdeu zzdeu0, zzazo zzazo0, zzdei zzdei0, zzdof zzdof0, zzbdv zzbdv0, zzcpy zzcpy0) {
        this(context0, zzceb0, zzdeu0, zzazo0, zzdei0, zzdof0, zzbdv0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbxb
    public final void zza(boolean z, Context context0) {
        zzbdv zzbdv0;
        zzcdp zzcdp0 = (zzcdp)zzdnt.zzb(this.zzgcn);
        try {
            zzdei zzdei0 = this.zzfhg;
            if(!this.zzgdq.zzaau()) {
                zzbdv0 = this.zzgdq;
            }
            else if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcjn)).booleanValue()) {
                zzbdv0 = this.zzgdq;
            }
            else {
                zzbdv zzbdv1 = this.zzfrh.zzc(this.zzfir.zzblv);
                zzagg.zza(zzbdv1, zzcdp0.zzafe());
                zzcer zzcer0 = new zzcer();
                View view0 = zzbdv1.getView();
                zzcer0.zza(this.zzur, view0);
                zzcdp0.zzaek().zzb(zzbdv1, true);
                zzbdv1.zzaaf().zza(new zzcqa(zzcer0, zzbdv1));
                zzbfi zzbfi0 = zzbdv1.zzaaf();
                zzbdv1.getClass();
                zzbfi0.zza(zzcqd.zzq(zzbdv1));
                zzbdv1.zzb(zzdei0.zzgpq.zzdiu, zzdei0.zzgpq.zzdiw, null);
                zzbdv0 = zzbdv1;
            }
        }
        catch(zzbei zzbei0) {
            zzazh.zzc("", zzbei0);
            return;
        }
        zzbdv0.zzax(true);
        zzg zzg0 = new zzg(false, zzawo.zzbd(this.zzur), false, 0.0f, -1, z, this.zzfhg.zzgpx, this.zzfhg.zzblo);
        zzl.zza(context0, new AdOverlayInfoParcel(null, zzcdp0.zzaez(), null, zzbdv0, this.zzfhg.zzgpy, this.zzblr, this.zzfhg.zzdlu, zzg0, this.zzfhg.zzgpq.zzdiu, this.zzfhg.zzgpq.zzdiw), true);
    }
}

