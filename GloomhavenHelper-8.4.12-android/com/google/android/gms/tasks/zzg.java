package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzg implements zzq {
    private final Object mLock;
    private final Executor zzd;
    @GuardedBy("mLock")
    private OnCanceledListener zzj;

    public zzg(@NonNull Executor executor0, @NonNull OnCanceledListener onCanceledListener0) {
        this.mLock = new Object();
        this.zzd = executor0;
        this.zzj = onCanceledListener0;
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void cancel() {
        synchronized(this.mLock) {
            this.zzj = null;
        }
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void onComplete(@NonNull Task task0) {
        if(task0.isCanceled()) {
            Object object0 = this.mLock;
            synchronized(object0) {
                if(this.zzj == null) {
                    return;
                }
            }
            zzh zzh0 = new zzh(this);
            this.zzd.execute(zzh0);
        }
    }

    static Object zza(zzg zzg0) {
        return zzg0.mLock;
    }

    static OnCanceledListener zzb(zzg zzg0) {
        return zzg0.zzj;
    }
}

