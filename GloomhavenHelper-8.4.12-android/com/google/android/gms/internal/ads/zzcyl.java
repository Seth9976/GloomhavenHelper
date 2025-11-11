package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;

public final class zzcyl implements zzcyb {
    private final String zzdns;
    private final int zzdnt;
    private final int zzdnu;
    private final int zzdnv;
    private final boolean zzdnw;
    private final int zzdnx;

    public zzcyl(String s, int v, int v1, int v2, boolean z, int v3) {
        this.zzdns = s;
        this.zzdnt = v;
        this.zzdnu = v1;
        this.zzdnv = v2;
        this.zzdnw = z;
        this.zzdnx = v3;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        boolean z = TextUtils.isEmpty(this.zzdns);
        boolean z1 = true;
        zzdez.zza(((Bundle)object0), "carrier", this.zzdns, !z);
        Integer integer0 = this.zzdnt;
        if(this.zzdnt == -2) {
            z1 = false;
        }
        zzdez.zza(((Bundle)object0), "cnt", integer0, z1);
        ((Bundle)object0).putInt("gnt", this.zzdnu);
        ((Bundle)object0).putInt("pt", this.zzdnv);
        Bundle bundle0 = zzdez.zza(((Bundle)object0), "device");
        ((Bundle)object0).putBundle("device", bundle0);
        Bundle bundle1 = zzdez.zza(bundle0, "network");
        bundle0.putBundle("network", bundle1);
        bundle1.putInt("active_network_state", this.zzdnx);
        bundle1.putBoolean("active_network_metered", this.zzdnw);
    }
}

