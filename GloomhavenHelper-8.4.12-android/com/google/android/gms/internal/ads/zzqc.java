package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

final class zzqc implements zzqe {
    private final Activity val$activity;
    private final Bundle zzxd;

    zzqc(zzpw zzpw0, Activity activity0, Bundle bundle0) {
        this.val$activity = activity0;
        this.zzxd = bundle0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzqe
    public final void zza(Application.ActivityLifecycleCallbacks application$ActivityLifecycleCallbacks0) {
        application$ActivityLifecycleCallbacks0.onActivitySaveInstanceState(this.val$activity, this.zzxd);
    }
}

