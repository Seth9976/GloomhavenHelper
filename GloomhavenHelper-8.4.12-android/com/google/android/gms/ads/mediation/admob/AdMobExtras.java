package com.google.android.gms.ads.mediation.admob;

import android.os.Bundle;
import com.google.ads.mediation.NetworkExtras;

@Deprecated
public final class AdMobExtras implements NetworkExtras {
    private final Bundle extras;

    public AdMobExtras(Bundle bundle0) {
        this.extras = bundle0 == null ? null : new Bundle(bundle0);
    }

    public final Bundle getExtras() {
        return this.extras;
    }
}

