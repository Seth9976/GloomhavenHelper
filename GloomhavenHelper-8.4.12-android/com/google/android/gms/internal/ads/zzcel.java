package com.google.android.gms.internal.ads;

final class zzcel implements zzbvl {
    private final zzbdv zzeiw;

    private zzcel(zzbdv zzbdv0) {
        this.zzeiw = zzbdv0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbvl
    public final void zzaiy() {
        this.zzeiw.destroy();
    }

    static zzbvl zzn(zzbdv zzbdv0) {
        return new zzcel(zzbdv0);
    }
}

