package com.google.android.gms.internal.ads;

import android.os.Bundle;

final class zzcxx implements zzcyb {
    private final Bundle zzgki;

    zzcxx(Bundle bundle0) {
        this.zzgki = bundle0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        ((Bundle)object0).putBundle("shared_pref", this.zzgki);
    }
}

