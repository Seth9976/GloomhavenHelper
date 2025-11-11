package com.google.android.gms.internal.ads;

final class zzcek implements zzbfh {
    private final zzazy zzbsd;

    zzcek(zzazy zzazy0) {
        this.zzbsd = zzazy0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbfh
    public final void zzai(boolean z) {
        zzazy zzazy0 = this.zzbsd;
        if(z) {
            zzazy0.set(null);
            return;
        }
        zzazy0.setException(new Exception("Ad Web View failed to load."));
    }
}

