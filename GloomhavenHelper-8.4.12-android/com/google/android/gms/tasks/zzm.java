package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzm implements zzq {
    private final Object mLock;
    private final Executor zzd;
    @GuardedBy("mLock")
    private OnSuccessListener zzp;

    public zzm(@NonNull Executor executor0, @NonNull OnSuccessListener onSuccessListener0) {
        this.mLock = new Object();
        this.zzd = executor0;
        this.zzp = onSuccessListener0;
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void cancel() {
        synchronized(this.mLock) {
            this.zzp = null;
        }
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void onComplete(@NonNull Task task0) {
        if(task0.isSuccessful()) {
            Object object0 = this.mLock;
            synchronized(object0) {
                if(this.zzp == null) {
                    return;
                }
            }
            zzn zzn0 = new zzn(this, task0);
            this.zzd.execute(zzn0);
        }
    }

    static Object zza(zzm zzm0) {
        return zzm0.mLock;
    }

    static OnSuccessListener zzb(zzm zzm0) {
        return zzm0.zzp;
    }
}

