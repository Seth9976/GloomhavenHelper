package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;

final class zzec implements zzef {
    private final Activity val$activity;

    zzec(zzdx zzdx0, Activity activity0) {
        this.val$activity = activity0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzef
    public final void zza(Application.ActivityLifecycleCallbacks application$ActivityLifecycleCallbacks0) {
        application$ActivityLifecycleCallbacks0.onActivityDestroyed(this.val$activity);
    }
}

