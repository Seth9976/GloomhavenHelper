package com.google.android.gms.internal.ads;

import android.os.Bundle;

public final class zzcut implements zzcyb {
    private final float zzdkj;
    private final int zzdnm;
    private final boolean zzdnn;
    private final boolean zzdno;
    private final int zzdnp;
    private final int zzdnq;
    private final int zzdnr;
    private final boolean zzgiq;

    public zzcut(int v, boolean z, boolean z1, int v1, int v2, int v3, float f, boolean z2) {
        this.zzdnm = v;
        this.zzdnn = z;
        this.zzdno = z1;
        this.zzdnp = v1;
        this.zzdnq = v2;
        this.zzdnr = v3;
        this.zzdkj = f;
        this.zzgiq = z2;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        ((Bundle)object0).putInt("am", this.zzdnm);
        ((Bundle)object0).putBoolean("ma", this.zzdnn);
        ((Bundle)object0).putBoolean("sp", this.zzdno);
        ((Bundle)object0).putInt("muv", this.zzdnp);
        ((Bundle)object0).putInt("rm", this.zzdnq);
        ((Bundle)object0).putInt("riv", this.zzdnr);
        ((Bundle)object0).putFloat("android_app_volume", this.zzdkj);
        ((Bundle)object0).putBoolean("android_app_muted", this.zzgiq);
    }
}

