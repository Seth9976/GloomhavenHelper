package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.zzg;

final class zzcnv implements zzbxb {
    private final zzazo zzblr;
    private final zzdei zzfpr;
    private final zzdof zzgcn;
    private final zzbdv zzgco;
    private final Context zzur;

    private zzcnv(Context context0, zzazo zzazo0, zzdof zzdof0, zzdei zzdei0, zzbdv zzbdv0) {
        this.zzur = context0;
        this.zzblr = zzazo0;
        this.zzgcn = zzdof0;
        this.zzfpr = zzdei0;
        this.zzgco = zzbdv0;
    }

    zzcnv(Context context0, zzazo zzazo0, zzdof zzdof0, zzdei zzdei0, zzbdv zzbdv0, zzcns zzcns0) {
        this(context0, zzazo0, zzdof0, zzdei0, zzbdv0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbxb
    public final void zza(boolean z, Context context0) {
        zzbvw zzbvw0 = (zzbvw)zzdnt.zzb(this.zzgcn);
        this.zzgco.zzax(true);
        zzg zzg0 = new zzg(false, zzawo.zzbd(this.zzur), false, 0.0f, -1, z, this.zzfpr.zzgpx, false);
        zzl.zza(context0, new AdOverlayInfoParcel(null, zzbvw0.zzaez(), null, this.zzgco, this.zzfpr.zzgpy, this.zzblr, this.zzfpr.zzdlu, zzg0, this.zzfpr.zzgpq.zzdiu, this.zzfpr.zzgpq.zzdiw), true);
    }
}

