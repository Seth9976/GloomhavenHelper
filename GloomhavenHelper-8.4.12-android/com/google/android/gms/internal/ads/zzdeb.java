package com.google.android.gms.internal.ads;

final class zzdeb implements zzcsq {
    private final zzdec zzgpc;

    zzdeb(zzdec zzdec0) {
        this.zzgpc = zzdec0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzcsq
    public final void onSuccess(Object object0) {
        synchronized(this.zzgpc) {
            zzdec.zza(this.zzgpc, ((zzcdn)object0));
            zzdec.zza(this.zzgpc).zzags();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcsq
    public final void zzaow() {
        synchronized(this.zzgpc) {
            zzdec.zza(this.zzgpc, null);
        }
    }
}

