package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

@TargetApi(14)
final class zzqi implements Application.ActivityLifecycleCallbacks {
    private boolean foreground;
    private final Object lock;
    private boolean zzbpn;
    @GuardedBy("lock")
    private final List zzbpo;
    @GuardedBy("lock")
    private final List zzbpp;
    private Runnable zzbpq;
    private long zzbpr;
    private Context zzur;
    private boolean zzyb;
    @Nullable
    private Activity zzzo;

    zzqi() {
        this.lock = new Object();
        this.foreground = true;
        this.zzbpn = false;
        this.zzbpo = new ArrayList();
        this.zzbpp = new ArrayList();
        this.zzyb = false;
    }

    @Nullable
    public final Activity getActivity() {
        return this.zzzo;
    }

    @Nullable
    public final Context getContext() {
        return this.zzur;
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity0, Bundle bundle0) {
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity0) {
        synchronized(this.lock) {
            if(this.zzzo == null) {
                return;
            }
            if(this.zzzo.equals(activity0)) {
                this.zzzo = null;
            }
            Iterator iterator0 = this.zzbpp.iterator();
            while(iterator0.hasNext()) {
                Object object1 = iterator0.next();
                zzqv zzqv0 = (zzqv)object1;
                try {
                    if(!zzqv0.zza(activity0)) {
                        continue;
                    }
                    iterator0.remove();
                }
                catch(Exception exception0) {
                    zzq.zzkz().zza(exception0, "AppActivityTracker.ActivityListener.onActivityDestroyed");
                    zzazh.zzc("", exception0);
                }
            }
        }
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity0) {
        this.setActivity(activity0);
        synchronized(this.lock) {
            for(Object object1: this.zzbpp) {
                zzqv zzqv0 = (zzqv)object1;
                try {
                    zzqv0.onActivityPaused(activity0);
                }
                catch(Exception exception0) {
                    zzq.zzkz().zza(exception0, "AppActivityTracker.ActivityListener.onActivityPaused");
                    zzazh.zzc("", exception0);
                }
            }
        }
        this.zzbpn = true;
        if(this.zzbpq != null) {
            zzawo.zzdtx.removeCallbacks(this.zzbpq);
        }
        zzqh zzqh0 = new zzqh(this);
        this.zzbpq = zzqh0;
        zzawo.zzdtx.postDelayed(zzqh0, this.zzbpr);
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity0) {
        this.setActivity(activity0);
        this.zzbpn = false;
        int v = !this.foreground;
        this.foreground = true;
        if(this.zzbpq != null) {
            zzawo.zzdtx.removeCallbacks(this.zzbpq);
        }
        synchronized(this.lock) {
            for(Object object1: this.zzbpp) {
                zzqv zzqv0 = (zzqv)object1;
                try {
                    zzqv0.onActivityResumed(activity0);
                }
                catch(Exception exception0) {
                    zzq.zzkz().zza(exception0, "AppActivityTracker.ActivityListener.onActivityResumed");
                    zzazh.zzc("", exception0);
                }
            }
            if(v == 0) {
                zzawf.zzeb("App is still foreground.");
            }
            else {
                for(Object object2: this.zzbpo) {
                    zzqk zzqk0 = (zzqk)object2;
                    try {
                        zzqk0.zzp(true);
                    }
                    catch(Exception exception1) {
                        zzazh.zzc("", exception1);
                    }
                }
            }
        }
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity0, Bundle bundle0) {
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity0) {
        this.setActivity(activity0);
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity0) {
    }

    private final void setActivity(Activity activity0) {
        synchronized(this.lock) {
            if(!activity0.getClass().getName().startsWith("com.google.android.gms.ads")) {
                this.zzzo = activity0;
            }
        }
    }

    static Object zza(zzqi zzqi0) {
        return zzqi0.lock;
    }

    static boolean zza(zzqi zzqi0, boolean z) {
        zzqi0.foreground = false;
        return false;
    }

    public final void zza(Application application0, Context context0) {
        if(!this.zzyb) {
            application0.registerActivityLifecycleCallbacks(this);
            if(context0 instanceof Activity) {
                this.setActivity(((Activity)context0));
            }
            this.zzur = application0;
            this.zzbpr = (long)(((Long)zzvh.zzpd().zzd(zzzx.zzcju)));
            this.zzyb = true;
        }
    }

    public final void zza(zzqk zzqk0) {
        synchronized(this.lock) {
            this.zzbpo.add(zzqk0);
        }
    }

    static boolean zzb(zzqi zzqi0) {
        return zzqi0.foreground;
    }

    public final void zzb(zzqk zzqk0) {
        synchronized(this.lock) {
            this.zzbpo.remove(zzqk0);
        }
    }

    static boolean zzc(zzqi zzqi0) {
        return zzqi0.zzbpn;
    }

    static List zzd(zzqi zzqi0) {
        return zzqi0.zzbpo;
    }
}

