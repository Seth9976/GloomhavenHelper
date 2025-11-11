package com.google.android.gms.internal.ads;

final class zzbwb implements zzbsr {
    private final zzbdv zzeiw;

    zzbwb(zzbdv zzbdv0) {
        this.zzeiw = zzbdv0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbsr
    public final void zzagw() {
        zzbdv zzbdv0 = this.zzeiw;
        if(zzbdv0.zzaab() != null) {
            zzbdv0.zzaab().close();
        }
    }
}

