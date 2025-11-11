package com.google.android.gms.internal.ads;

import android.os.Bundle;

final class zzcvo implements zzcyb {
    private final String zzdlj;
    private final String zzgjc;
    private final Bundle zzgjd;

    private zzcvo(String s, String s1, Bundle bundle0) {
        this.zzdlj = s;
        this.zzgjc = s1;
        this.zzgjd = bundle0;
    }

    zzcvo(String s, String s1, Bundle bundle0, zzcvl zzcvl0) {
        this(s, s1, bundle0);
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        ((Bundle)object0).putString("consent_string", this.zzdlj);
        ((Bundle)object0).putString("fc_consent", this.zzgjc);
        ((Bundle)object0).putBundle("iab_consent_info", this.zzgjd);
    }
}

