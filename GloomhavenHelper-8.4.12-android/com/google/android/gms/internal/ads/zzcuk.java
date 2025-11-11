package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.ArrayList;

final class zzcuk implements zzcyb {
    private final ArrayList zzgie;

    zzcuk(ArrayList arrayList0) {
        this.zzgie = arrayList0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        ((Bundle)object0).putStringArrayList("android_permissions", this.zzgie);
    }
}

