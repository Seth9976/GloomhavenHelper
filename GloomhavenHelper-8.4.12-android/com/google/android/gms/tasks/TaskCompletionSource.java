package com.google.android.gms.tasks;

import androidx.annotation.NonNull;

public class TaskCompletionSource {
    private final zzu zza;

    public TaskCompletionSource() {
        this.zza = new zzu();
    }

    public TaskCompletionSource(@NonNull CancellationToken cancellationToken0) {
        this.zza = new zzu();
        cancellationToken0.onCanceledRequested(new zzs(this));
    }

    @NonNull
    public Task getTask() {
        return this.zza;
    }

    public void setException(@NonNull Exception exception0) {
        this.zza.setException(exception0);
    }

    public void setResult(Object object0) {
        this.zza.setResult(object0);
    }

    public boolean trySetException(@NonNull Exception exception0) {
        return this.zza.trySetException(exception0);
    }

    public boolean trySetResult(Object object0) {
        return this.zza.trySetResult(object0);
    }
}

