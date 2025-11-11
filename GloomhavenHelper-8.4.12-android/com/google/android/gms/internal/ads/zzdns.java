package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public abstract class zzdns extends zzdnp implements zzdof {
    @Override  // com.google.android.gms.internal.ads.zzdof
    public void addListener(Runnable runnable0, Executor executor0) {
        this.zzauu().addListener(runnable0, executor0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdnp
    protected Object zzatv() {
        return this.zzauu();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnp
    protected Future zzaut() {
        return this.zzauu();
    }

    protected abstract zzdof zzauu();
}

