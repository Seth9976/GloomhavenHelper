package com.google.android.gms.internal.ads;

import android.os.Bundle;

public final class zzcun implements zzcyb {
    private final boolean zzgik;
    private final boolean zzgil;
    private final boolean zzgim;

    public zzcun(boolean z, boolean z1, boolean z2) {
        this.zzgik = false;
        this.zzgil = false;
        this.zzgim = z2;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        ((Bundle)object0).putBoolean("c_pcbg", this.zzgik);
        ((Bundle)object0).putBoolean("c_phbg", this.zzgil);
        ((Bundle)object0).putBoolean("ar_lr", this.zzgim);
    }
}

