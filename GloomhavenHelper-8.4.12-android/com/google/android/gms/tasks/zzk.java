package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzk implements zzq {
    private final Object mLock;
    private final Executor zzd;
    @GuardedBy("mLock")
    private OnFailureListener zzn;

    public zzk(@NonNull Executor executor0, @NonNull OnFailureListener onFailureListener0) {
        this.mLock = new Object();
        this.zzd = executor0;
        this.zzn = onFailureListener0;
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void cancel() {
        synchronized(this.mLock) {
            this.zzn = null;
        }
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void onComplete(@NonNull Task task0) {
        if(!task0.isSuccessful() && !task0.isCanceled()) {
            Object object0 = this.mLock;
            synchronized(object0) {
                if(this.zzn == null) {
                    return;
                }
            }
            zzl zzl0 = new zzl(this, task0);
            this.zzd.execute(zzl0);
        }
    }

    static Object zza(zzk zzk0) {
        return zzk0.mLock;
    }

    static OnFailureListener zzb(zzk zzk0) {
        return zzk0.zzn;
    }
}

