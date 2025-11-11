package com.google.android.gms.internal.ads;

import android.os.Bundle;

public final class zzcux implements zzcyb {
    private final double zzdny;
    private final boolean zzdnz;

    public zzcux(double f, boolean z) {
        this.zzdny = f;
        this.zzdnz = z;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        Bundle bundle0 = zzdez.zza(((Bundle)object0), "device");
        ((Bundle)object0).putBundle("device", bundle0);
        Bundle bundle1 = zzdez.zza(bundle0, "battery");
        bundle0.putBundle("battery", bundle1);
        bundle1.putBoolean("is_charging", this.zzdnz);
        bundle1.putDouble("battery_level", this.zzdny);
    }
}

