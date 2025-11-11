package com.google.android.gms.ads.internal.overlay;

import android.graphics.Bitmap;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.internal.ads.zzawb;
import com.google.android.gms.internal.ads.zzawo;

final class zzj extends zzawb {
    final zze zzdio;

    private zzj(zze zze0) {
        this.zzdio = zze0;
        super();
    }

    zzj(zze zze0, zzf zzf0) {
        this(zze0);
    }

    @Override  // com.google.android.gms.internal.ads.zzawb
    public final void zztz() {
        Bitmap bitmap0 = zzq.zzlo().zza(this.zzdio.zzdhv.zzdja.zzbll);
        if(bitmap0 != null) {
            zzm zzm0 = new zzm(this, zzq.zzkx().zza(this.zzdio.zzzo, bitmap0, this.zzdio.zzdhv.zzdja.zzblj, this.zzdio.zzdhv.zzdja.zzblk));
            zzawo.zzdtx.post(zzm0);
        }
    }
}

