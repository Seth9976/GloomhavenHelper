package com.google.android.gms.internal.ads;

final class zzcms implements zzbnc {
    private final zzdfb zzgbn;

    private zzcms(zzdfb zzdfb0) {
        this.zzgbn = zzdfb0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbnc
    public final zzxj getVideoController() {
        return this.zzgbn.getVideoController();
    }

    static zzbnc zza(zzdfb zzdfb0) {
        return new zzcms(zzdfb0);
    }
}

