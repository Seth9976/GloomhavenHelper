package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

final class zze implements OnCanceledListener, OnFailureListener, OnSuccessListener, zzq {
    private final Executor zzd;
    private final Continuation zze;
    private final zzu zzf;

    public zze(@NonNull Executor executor0, @NonNull Continuation continuation0, @NonNull zzu zzu0) {
        this.zzd = executor0;
        this.zze = continuation0;
        this.zzf = zzu0;
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void cancel() {
        throw new UnsupportedOperationException();
    }

    @Override  // com.google.android.gms.tasks.OnCanceledListener
    public final void onCanceled() {
        this.zzf.zza();
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void onComplete(@NonNull Task task0) {
        zzf zzf0 = new zzf(this, task0);
        this.zzd.execute(zzf0);
    }

    @Override  // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(@NonNull Exception exception0) {
        this.zzf.setException(exception0);
    }

    @Override  // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object object0) {
        this.zzf.setResult(object0);
    }

    static Continuation zza(zze zze0) {
        return zze0.zze;
    }

    static zzu zzb(zze zze0) {
        return zze0.zzf;
    }
}

