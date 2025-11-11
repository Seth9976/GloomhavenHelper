package com.google.android.gms.internal.ads;

final class zzsi extends zzazy {
    private final zzsf zzbsc;

    zzsi(zzsf zzsf0) {
        this.zzbsc = zzsf0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzazy
    public final boolean cancel(boolean z) {
        this.zzbsc.disconnect();
        return super.cancel(z);
    }
}

