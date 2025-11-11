package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

final class zzdw implements zzef {
    private final Activity val$activity;
    private final Bundle zzxd;

    zzdw(zzdx zzdx0, Activity activity0, Bundle bundle0) {
        this.val$activity = activity0;
        this.zzxd = bundle0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzef
    public final void zza(Application.ActivityLifecycleCallbacks application$ActivityLifecycleCallbacks0) {
        application$ActivityLifecycleCallbacks0.onActivityCreated(this.val$activity, this.zzxd);
    }
}

