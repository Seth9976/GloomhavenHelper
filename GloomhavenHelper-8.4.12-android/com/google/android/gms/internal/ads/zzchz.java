package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;
import javax.annotation.Nullable;

final class zzchz implements zzdnu {
    final zzcho zzfxe;

    zzchz(zzcho zzcho0) {
        this.zzfxe = zzcho0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(@Nullable Object object0) {
        synchronized(this) {
            zzcho.zza(this.zzfxe, true);
            long v1 = zzq.zzlc().elapsedRealtime();
            this.zzfxe.zza("com.google.android.gms.ads.MobileAds", true, "", ((int)(v1 - this.zzfxe.zzfwp)));
            this.zzfxe.executor.execute(new zzchy(this, ((String)object0)));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        synchronized(this) {
            zzcho.zza(this.zzfxe, true);
            long v1 = zzq.zzlc().elapsedRealtime();
            this.zzfxe.zza("com.google.android.gms.ads.MobileAds", false, "Internal Error.", ((int)(v1 - this.zzfxe.zzfwp)));
            this.zzfxe.zzfwq.setException(new Exception());
        }
    }
}

