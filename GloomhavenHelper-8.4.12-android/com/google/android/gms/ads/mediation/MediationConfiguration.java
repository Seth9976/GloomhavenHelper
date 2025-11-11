package com.google.android.gms.ads.mediation;

import android.os.Bundle;
import com.google.android.gms.ads.AdFormat;

public class MediationConfiguration {
    private final Bundle zzejn;
    private final AdFormat zzejr;

    public MediationConfiguration(AdFormat adFormat0, Bundle bundle0) {
        this.zzejr = adFormat0;
        this.zzejn = bundle0;
    }

    public AdFormat getFormat() {
        return this.zzejr;
    }

    public Bundle getServerParameters() {
        return this.zzejn;
    }
}

