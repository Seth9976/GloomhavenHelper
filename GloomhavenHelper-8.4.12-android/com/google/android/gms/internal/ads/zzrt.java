package com.google.android.gms.internal.ads;

final class zzrt implements zzqk {
    private final zzrr zzbrq;

    zzrt(zzrr zzrr0) {
        this.zzbrq = zzrr0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzqk
    public final void zzp(boolean z) {
        if(z) {
            this.zzbrq.connect();
            return;
        }
        this.zzbrq.disconnect();
    }
}

