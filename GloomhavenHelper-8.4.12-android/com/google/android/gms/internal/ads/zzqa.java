package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;

final class zzqa implements zzqe {
    private final Activity val$activity;

    zzqa(zzpw zzpw0, Activity activity0) {
        this.val$activity = activity0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzqe
    public final void zza(Application.ActivityLifecycleCallbacks application$ActivityLifecycleCallbacks0) {
        application$ActivityLifecycleCallbacks0.onActivityPaused(this.val$activity);
    }
}

