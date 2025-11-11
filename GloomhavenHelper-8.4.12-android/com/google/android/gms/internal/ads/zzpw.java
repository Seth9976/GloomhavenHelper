package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;

final class zzpw implements Application.ActivityLifecycleCallbacks {
    private final Application zzxe;
    private final WeakReference zzxf;
    private boolean zzxg;

    public zzpw(Application application0, Application.ActivityLifecycleCallbacks application$ActivityLifecycleCallbacks0) {
        this.zzxg = false;
        this.zzxf = new WeakReference(application$ActivityLifecycleCallbacks0);
        this.zzxe = application0;
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity0, Bundle bundle0) {
        this.zza(new zzpv(this, activity0, bundle0));
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity0) {
        this.zza(new zzqb(this, activity0));
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity0) {
        this.zza(new zzqa(this, activity0));
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity0) {
        this.zza(new zzpx(this, activity0));
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity0, Bundle bundle0) {
        this.zza(new zzqc(this, activity0, bundle0));
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity0) {
        this.zza(new zzpy(this, activity0));
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity0) {
        this.zza(new zzpz(this, activity0));
    }

    private final void zza(zzqe zzqe0) {
        try {
            Application.ActivityLifecycleCallbacks application$ActivityLifecycleCallbacks0 = (Application.ActivityLifecycleCallbacks)this.zzxf.get();
            if(application$ActivityLifecycleCallbacks0 != null) {
                zzqe0.zza(application$ActivityLifecycleCallbacks0);
                return;
            }
            if(!this.zzxg) {
                this.zzxe.unregisterActivityLifecycleCallbacks(this);
                this.zzxg = true;
            }
        }
        catch(Exception exception0) {
            zzawf.zzc("Error while dispatching lifecycle callback.", exception0);
        }
    }
}

