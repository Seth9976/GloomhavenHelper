package com.google.android.gms.tasks;

import androidx.annotation.NonNull;

final class zza extends CancellationToken {
    private final zzu zza;

    zza() {
        this.zza = new zzu();
    }

    public final void cancel() {
        this.zza.trySetResult(null);
    }

    @Override  // com.google.android.gms.tasks.CancellationToken
    public final boolean isCancellationRequested() {
        return this.zza.isComplete();
    }

    @Override  // com.google.android.gms.tasks.CancellationToken
    public final CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener onTokenCanceledListener0) {
        zzb zzb0 = new zzb(this, onTokenCanceledListener0);
        this.zza.addOnSuccessListener(zzb0);
        return this;
    }
}

