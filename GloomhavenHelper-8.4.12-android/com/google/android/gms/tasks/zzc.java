package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzc implements zzq {
    private final Executor zzd;
    private final Continuation zze;
    private final zzu zzf;

    public zzc(@NonNull Executor executor0, @NonNull Continuation continuation0, @NonNull zzu zzu0) {
        this.zzd = executor0;
        this.zze = continuation0;
        this.zzf = zzu0;
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void cancel() {
        throw new UnsupportedOperationException();
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void onComplete(@NonNull Task task0) {
        zzd zzd0 = new zzd(this, task0);
        this.zzd.execute(zzd0);
    }

    static zzu zza(zzc zzc0) {
        return zzc0.zzf;
    }

    static Continuation zzb(zzc zzc0) {
        return zzc0.zze;
    }
}

