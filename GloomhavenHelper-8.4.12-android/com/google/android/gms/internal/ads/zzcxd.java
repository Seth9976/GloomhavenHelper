package com.google.android.gms.internal.ads;

import android.os.Bundle;

public final class zzcxd implements zzcyb {
    private final String zzgjr;

    public zzcxd(String s) {
        this.zzgjr = s;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        ((Bundle)object0).putString("rtb", this.zzgjr);
    }
}

