package com.google.android.gms.internal.ads;

import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.ads.internal.overlay.zzo;
import com.google.android.gms.ads.internal.zzq;

final class zzdbd implements zzcsq {
    private final zzdaz zzgmu;

    zzdbd(zzdaz zzdaz0) {
        this.zzgmu = zzdaz0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzcsq
    public final void onSuccess(Object object0) {
        if(this.zzgmu.zzgmo != null) {
            this.zzgmu.zzgmo.destroy();
        }
        this.zzgmu.zzgmo = (zzbla)object0;
        zzdaz.zza(this.zzgmu).removeAllViews();
        zzdaz.zza(this.zzgmu).addView(((zzbla)object0).zzagm(), zzq.zzkx().zzwu());
        if(((zzbla)object0).zzagg() || zzdaz.zzb(this.zzgmu).zzdxg < ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcpe))))) {
            zzo zzo0 = zzdaz.zza(this.zzgmu, ((zzbla)object0));
            RelativeLayout.LayoutParams relativeLayout$LayoutParams0 = zzdaz.zzd(((zzbla)object0));
            zzo0.zzal(((zzbla)object0).zzagn());
            zzdaz.zza(this.zzgmu).addView(zzo0, relativeLayout$LayoutParams0);
        }
        zzdaz.zzb(this.zzgmu, ((zzbla)object0));
        zzdaz.zza(this.zzgmu).setMinimumHeight(zzdaz.zzc(this.zzgmu).heightPixels);
        zzdaz.zza(this.zzgmu).setMinimumWidth(zzdaz.zzc(this.zzgmu).widthPixels);
        zzdaz.zzd(this.zzgmu).zzb(new zzblc(((zzbla)object0), this.zzgmu));
        ((zzbla)object0).zzags();
    }

    @Override  // com.google.android.gms.internal.ads.zzcsq
    public final void zzaow() {
        this.zzgmu.zzgmo = null;
    }
}

