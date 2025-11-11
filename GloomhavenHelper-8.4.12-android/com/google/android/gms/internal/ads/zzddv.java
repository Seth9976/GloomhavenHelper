package com.google.android.gms.internal.ads;

final class zzddv implements zzcsq {
    private final zzddw zzgox;

    zzddv(zzddw zzddw0) {
        this.zzgox = zzddw0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzcsq
    public final void onSuccess(Object object0) {
        synchronized(this.zzgox) {
            zzddw.zza(this.zzgox, ((zzcdn)object0));
            zzddw.zza(this.zzgox).zzags();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcsq
    public final void zzaow() {
        synchronized(this.zzgox) {
            zzddw.zza(this.zzgox, null);
        }
    }
}

