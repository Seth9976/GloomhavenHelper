package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import androidx.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@ParametersAreNonnullByDefault
public final class zzqf {
    private final Object zzbph;
    @GuardedBy("activityTrackerLock")
    private zzqi zzbpi;
    @GuardedBy("activityTrackerLock")
    private boolean zzbpj;

    public zzqf() {
        this.zzbph = new Object();
        this.zzbpi = null;
        this.zzbpj = false;
    }

    @Nullable
    public final Activity getActivity() {
        synchronized(this.zzbph) {
            if(this.zzbpi != null) {
                return this.zzbpi.getActivity();
            }
        }
        return null;
    }

    @Nullable
    public final Context getContext() {
        synchronized(this.zzbph) {
            if(this.zzbpi != null) {
                return this.zzbpi.getContext();
            }
        }
        return null;
    }

    public final void initialize(Context context0) {
        synchronized(this.zzbph) {
            if(!this.zzbpj) {
                Application application0 = null;
                Context context1 = context0.getApplicationContext();
                if(context1 == null) {
                    context1 = context0;
                }
                if(context1 instanceof Application) {
                    application0 = (Application)context1;
                }
                if(application0 == null) {
                    zzawf.zzfa("Can not cast Context to Application");
                    return;
                }
                if(this.zzbpi == null) {
                    this.zzbpi = new zzqi();
                }
                this.zzbpi.zza(application0, context0);
                this.zzbpj = true;
            }
        }
    }

    public final void zza(zzqk zzqk0) {
        synchronized(this.zzbph) {
            if(this.zzbpi == null) {
                this.zzbpi = new zzqi();
            }
            this.zzbpi.zza(zzqk0);
        }
    }

    public final void zzb(zzqk zzqk0) {
        synchronized(this.zzbph) {
            if(this.zzbpi == null) {
                return;
            }
            this.zzbpi.zzb(zzqk0);
        }
    }
}

