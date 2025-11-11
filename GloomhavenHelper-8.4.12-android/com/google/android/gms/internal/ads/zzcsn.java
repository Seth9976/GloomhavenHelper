package com.google.android.gms.internal.ads;

final class zzcsn implements zzcsq {
    private final zzcsk zzggp;

    zzcsn(zzcsk zzcsk0) {
        this.zzggp = zzcsk0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzcsq
    public final void onSuccess(Object object0) {
        synchronized(this.zzggp) {
            zzcsk.zza(this.zzggp, false);
            this.zzggp.zzaca = ((zzbnf)object0).zzahi();
            ((zzbnf)object0).zzags();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcsq
    public final void zzaow() {
        synchronized(this.zzggp) {
            zzcsk.zza(this.zzggp, false);
        }
    }
}

