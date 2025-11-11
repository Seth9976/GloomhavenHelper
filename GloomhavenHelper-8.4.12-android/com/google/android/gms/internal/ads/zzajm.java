package com.google.android.gms.internal.ads;

final class zzajm implements zzbaa {
    private final zzaja zzdaz;
    private final zzajv zzdbb;

    zzajm(zzaja zzaja0, zzajv zzajv0) {
        this.zzdaz = zzaja0;
        this.zzdbb = zzajv0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzbaa
    public final void run() {
        synchronized(this.zzdaz.lock) {
            this.zzdaz.status = 1;
            zzawf.zzee("Failed loading new engine. Marking new engine destroyable.");
            this.zzdbb.zzsj();
        }
    }
}

