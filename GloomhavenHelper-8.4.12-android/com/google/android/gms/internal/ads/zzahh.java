package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.initialization.AdapterStatus.State;
import com.google.android.gms.ads.initialization.AdapterStatus;

public final class zzahh implements AdapterStatus {
    private final String description;
    private final int zzczh;
    private final State zzczj;

    public zzahh(State adapterStatus$State0, String s, int v) {
        this.zzczj = adapterStatus$State0;
        this.description = s;
        this.zzczh = v;
    }

    @Override  // com.google.android.gms.ads.initialization.AdapterStatus
    public final String getDescription() {
        return this.description;
    }

    @Override  // com.google.android.gms.ads.initialization.AdapterStatus
    public final State getInitializationState() {
        return this.zzczj;
    }

    @Override  // com.google.android.gms.ads.initialization.AdapterStatus
    public final int getLatency() {
        return this.zzczh;
    }
}

