package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;

public final class zzcsb extends zzvt {
    private zzvk zzblz;
    private final zzbgk zzgcx;
    private final Context zzgfp;
    @VisibleForTesting
    private final zzdew zzgfq;
    @VisibleForTesting
    private final zzbzi zzgfr;

    public zzcsb(zzbgk zzbgk0, Context context0, String s) {
        this.zzgfq = new zzdew();
        this.zzgfr = new zzbzi();
        this.zzgcx = zzbgk0;
        this.zzgfq.zzgn(s);
        this.zzgfp = context0;
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zza(PublisherAdViewOptions publisherAdViewOptions0) {
        this.zzgfq.zzb(publisherAdViewOptions0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zza(zzach zzach0) {
        this.zzgfq.zzb(zzach0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zza(zzadr zzadr0) {
        this.zzgfr.zzb(zzadr0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zza(zzads zzads0) {
        this.zzgfr.zzb(zzads0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zza(zzaef zzaef0, zzuk zzuk0) {
        this.zzgfr.zza(zzaef0);
        this.zzgfq.zzd(zzuk0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zza(zzaeg zzaeg0) {
        this.zzgfr.zzb(zzaeg0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zza(zzahl zzahl0) {
        this.zzgfq.zzb(zzahl0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zza(zzaht zzaht0) {
        this.zzgfr.zzb(zzaht0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zza(String s, zzady zzady0, zzadx zzadx0) {
        this.zzgfr.zzb(s, zzady0, zzadx0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zzb(zzvk zzvk0) {
        this.zzblz = zzvk0;
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final void zzb(zzwl zzwl0) {
        this.zzgfq.zzc(zzwl0);
    }

    @Override  // com.google.android.gms.internal.ads.zzvq
    public final zzvp zzpi() {
        zzbzg zzbzg0 = this.zzgfr.zzala();
        ArrayList arrayList0 = zzbzg0.zzaky();
        this.zzgfq.zzb(arrayList0);
        ArrayList arrayList1 = zzbzg0.zzakz();
        this.zzgfq.zzc(arrayList1);
        zzdew zzdew0 = this.zzgfq;
        if(zzdew0.zzke() == null) {
            zzdew0.zzd(zzuk.zzh(this.zzgfp));
        }
        return new zzcsa(this.zzgfp, this.zzgcx, this.zzgfq, zzbzg0, this.zzblz);
    }
}

