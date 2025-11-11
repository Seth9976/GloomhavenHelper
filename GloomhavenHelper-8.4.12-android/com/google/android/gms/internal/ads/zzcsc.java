package com.google.android.gms.internal.ads;

import android.view.ViewGroup;
import android.view.ViewParent;

final class zzcsc implements zzdnu {
    private final zzbmc zzgfs;
    private final zzcsd zzgft;

    zzcsc(zzcsd zzcsd0, zzbmc zzbmc0) {
        this.zzgft = zzcsd0;
        this.zzgfs = zzbmc0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        synchronized(this.zzgft) {
            zzcsd.zza(this.zzgft, null);
            if(zzcsd.zza(this.zzgft) != null) {
                zzcsd.zza(this.zzgft).destroy();
            }
            zzcsd.zza(this.zzgft, ((zzblg)object0));
            zzcsd.zzb(this.zzgft).removeAllViews();
            if(((zzblg)object0).zzagm() != null) {
                ViewParent viewParent0 = ((zzblg)object0).zzagm().getParent();
                if(viewParent0 instanceof ViewGroup) {
                    zzawf.zzfa(("Banner view provided from " + this.zzgft.getMediationAdapterClassName() + " already has a parent view. Removing its old parent."));
                    ((ViewGroup)viewParent0).removeView(((zzblg)object0).zzagm());
                }
            }
            zzcsd.zzb(this.zzgft).addView(((zzblg)object0).zzagm());
            ((zzblg)object0).zzags();
            zzcsd.zzc(this.zzgft).zzdg(((zzblg)object0).zzagr());
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        synchronized(this.zzgft) {
            zzcsd.zza(this.zzgft, null);
            this.zzgfs.zzady().onAdFailedToLoad(zzcid.zzd(throwable0));
            zzcsd.zzc(this.zzgft).zzdg(60);
            zzdfc.zzc(throwable0, "BannerAdManagerShim.onFailure");
        }
    }
}

