package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.ArrayList;

final class zzclq implements zzdnu {
    private final boolean zzgam;
    final zzclr zzgan;

    zzclq(zzclr zzclr0, boolean z) {
        this.zzgan = zzclr0;
        this.zzgam = z;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        ArrayList arrayList0 = zzclr.zza(this.zzgan, ((Bundle)object0));
        zzc zzsz$zzj$zzc0 = zzclr.zzb(this.zzgan, ((Bundle)object0));
        zzh zzsz$zzh0 = this.zzgan.zzj(((Bundle)object0));
        this.zzgan.zzgap.zza(new zzclt(this, this.zzgam, arrayList0, zzsz$zzh0, zzsz$zzj$zzc0));
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        zzawf.zzey("Failed to get signals bundle");
    }
}

