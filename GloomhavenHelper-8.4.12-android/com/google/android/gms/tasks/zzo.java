package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzo implements OnCanceledListener, OnFailureListener, OnSuccessListener, zzq {
    private final Executor zzd;
    private final zzu zzf;
    private final SuccessContinuation zzr;

    public zzo(@NonNull Executor executor0, @NonNull SuccessContinuation successContinuation0, @NonNull zzu zzu0) {
        this.zzd = executor0;
        this.zzr = successContinuation0;
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
        zzp zzp0 = new zzp(this, task0);
        this.zzd.execute(zzp0);
    }

    @Override  // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(@NonNull Exception exception0) {
        this.zzf.setException(exception0);
    }

    @Override  // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object object0) {
        this.zzf.setResult(object0);
    }

    static SuccessContinuation zza(zzo zzo0) {
        return zzo0.zzr;
    }
}

