package com.google.android.gms.internal.ads;

import android.os.Bundle;

public final class zzcvy implements zzcyb {
    private final Bundle zzdks;

    public zzcvy(Bundle bundle0) {
        this.zzdks = bundle0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        Bundle bundle0 = zzdez.zza(((Bundle)object0), "device");
        bundle0.putBundle("android_mem_info", this.zzdks);
        ((Bundle)object0).putBundle("device", bundle0);
    }
}

