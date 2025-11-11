package com.google.android.gms.common.api.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public final class BackgroundDetector implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    @KeepForSdk
    public interface BackgroundStateChangeListener {
        @KeepForSdk
        void onBackgroundStateChanged(boolean arg1);
    }

    private static final BackgroundDetector zzat;
    private final AtomicBoolean zzau;
    private final AtomicBoolean zzav;
    @GuardedBy("sInstance")
    private final ArrayList zzaw;
    @GuardedBy("sInstance")
    private boolean zzax;

    static {
        BackgroundDetector.zzat = new BackgroundDetector();
    }

    @KeepForSdk
    private BackgroundDetector() {
        this.zzau = new AtomicBoolean();
        this.zzav = new AtomicBoolean();
        this.zzaw = new ArrayList();
        this.zzax = false;
    }

    @KeepForSdk
    public final void addListener(BackgroundStateChangeListener backgroundDetector$BackgroundStateChangeListener0) {
        synchronized(BackgroundDetector.zzat) {
            this.zzaw.add(backgroundDetector$BackgroundStateChangeListener0);
        }
    }

    @KeepForSdk
    public static BackgroundDetector getInstance() {
        return BackgroundDetector.zzat;
    }

    @KeepForSdk
    public static void initialize(Application application0) {
        synchronized(BackgroundDetector.zzat) {
            if(!BackgroundDetector.zzat.zzax) {
                application0.registerActivityLifecycleCallbacks(BackgroundDetector.zzat);
                application0.registerComponentCallbacks(BackgroundDetector.zzat);
                BackgroundDetector.zzat.zzax = true;
            }
        }
    }

    @KeepForSdk
    public final boolean isInBackground() {
        return this.zzau.get();
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity0, Bundle bundle0) {
        boolean z = this.zzau.compareAndSet(true, false);
        this.zzav.set(true);
        if(z) {
            this.onBackgroundStateChanged(false);
        }
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity0) {
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity0) {
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity0) {
        boolean z = this.zzau.compareAndSet(true, false);
        this.zzav.set(true);
        if(z) {
            this.onBackgroundStateChanged(false);
        }
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity0, Bundle bundle0) {
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity0) {
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity0) {
    }

    private final void onBackgroundStateChanged(boolean z) {
        synchronized(BackgroundDetector.zzat) {
            ArrayList arrayList0 = this.zzaw;
            int v1 = arrayList0.size();
            int v2 = 0;
            while(v2 < v1) {
                Object object0 = arrayList0.get(v2);
                ++v2;
                ((BackgroundStateChangeListener)object0).onBackgroundStateChanged(z);
            }
        }
    }

    @Override  // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration0) {
    }

    @Override  // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override  // android.content.ComponentCallbacks2
    public final void onTrimMemory(int v) {
        if(v == 20 && this.zzau.compareAndSet(false, true)) {
            this.zzav.set(true);
            this.onBackgroundStateChanged(true);
        }
    }

    @TargetApi(16)
    @KeepForSdk
    public final boolean readCurrentStateIfPossible(boolean z) {
        if(!this.zzav.get()) {
            ActivityManager.RunningAppProcessInfo activityManager$RunningAppProcessInfo0 = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(activityManager$RunningAppProcessInfo0);
            if(!this.zzav.getAndSet(true) && activityManager$RunningAppProcessInfo0.importance > 100) {
                this.zzau.set(true);
            }
        }
        return this.isInBackground();
    }
}

