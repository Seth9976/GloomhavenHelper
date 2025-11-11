package com.google.android.gms.tasks;

final class zzb implements OnSuccessListener {
    private final OnTokenCanceledListener zzb;

    zzb(zza zza0, OnTokenCanceledListener onTokenCanceledListener0) {
        this.zzb = onTokenCanceledListener0;
        super();
    }

    @Override  // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object object0) {
        this.zzb.onCanceled();
    }
}

