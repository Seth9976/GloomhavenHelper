package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzi implements zzq {
    private final Object mLock;
    private final Executor zzd;
    @GuardedBy("mLock")
    private OnCompleteListener zzl;

    public zzi(@NonNull Executor executor0, @NonNull OnCompleteListener onCompleteListener0) {
        this.mLock = new Object();
        this.zzd = executor0;
        this.zzl = onCompleteListener0;
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void cancel() {
        synchronized(this.mLock) {
            this.zzl = null;
        }
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void onComplete(@NonNull Task task0) {
        synchronized(this.mLock) {
            if(this.zzl == null) {
                return;
            }
        }
        zzj zzj0 = new zzj(this, task0);
        this.zzd.execute(zzj0);
    }

    static Object zza(zzi zzi0) {
        return zzi0.mLock;
    }

    static OnCompleteListener zzb(zzi zzi0) {
        return zzi0.zzl;
    }
}

