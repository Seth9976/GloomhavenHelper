package com.google.android.gms.internal.ads;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;

final class zzsh implements BaseConnectionCallbacks {
    private final zzrz zzbsa;
    private final zzazy zzbsb;
    final zzsf zzbsc;

    zzsh(zzsf zzsf0, zzrz zzrz0, zzazy zzazy0) {
        this.zzbsc = zzsf0;
        this.zzbsa = zzrz0;
        this.zzbsb = zzazy0;
        super();
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle0) {
        synchronized(this.zzbsc.lock) {
            if(this.zzbsc.zzbrz) {
                return;
            }
            zzsf.zza(this.zzbsc, true);
            zzsa zzsa0 = this.zzbsc.zzbrm;
            if(zzsa0 == null) {
                return;
            }
            zzsk zzsk0 = new zzsk(this, zzsa0, this.zzbsa, this.zzbsb);
            zzdof zzdof0 = zzazq.zzdxk.zzf(zzsk0);
            zzsj zzsj0 = new zzsj(this.zzbsb, zzdof0);
            this.zzbsb.addListener(zzsj0, zzazq.zzdxp);
        }
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public final void onConnectionSuspended(int v) {
    }
}

