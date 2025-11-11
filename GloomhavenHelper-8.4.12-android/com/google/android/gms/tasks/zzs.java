package com.google.android.gms.tasks;

final class zzs implements OnTokenCanceledListener {
    private final TaskCompletionSource zzv;

    zzs(TaskCompletionSource taskCompletionSource0) {
        this.zzv = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.tasks.OnTokenCanceledListener
    public final void onCanceled() {
        this.zzv.zza.zza();
    }
}

