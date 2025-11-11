package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.util.Log;
import java.util.concurrent.atomic.AtomicReference;

public final class zzk extends zzq {
    private final AtomicReference zza;
    private boolean zzb;

    public zzk() {
        this.zza = new AtomicReference();
    }

    public static Object zza(Bundle bundle0, Class class0) {
        if(bundle0 != null) {
            Object object0 = bundle0.get("r");
            if(object0 != null) {
                try {
                    return class0.cast(object0);
                }
                catch(ClassCastException classCastException0) {
                    Log.w("AM", String.format("Unexpected object type. Expected, Received: %s, %s", class0.getCanonicalName(), object0.getClass().getCanonicalName()), classCastException0);
                    throw classCastException0;
                }
            }
            return null;
        }
        return null;
    }

    public final String zza(long v) {
        return (String)zzk.zza(this.zzb(v), String.class);
    }

    @Override  // com.google.android.gms.internal.measurement.zzn
    public final void zza(Bundle bundle0) {
        synchronized(this.zza) {
            try {
                this.zza.set(bundle0);
                this.zzb = true;
            }
            finally {
                this.zza.notify();
            }
        }
    }

    public final Bundle zzb(long v) {
        synchronized(this.zza) {
            if(!this.zzb) {
                try {
                    this.zza.wait(v);
                }
                catch(InterruptedException unused_ex) {
                    return null;
                }
            }
        }
        return (Bundle)this.zza.get();
    }
}

