package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.ArrayList;

final class zzcuc implements zzcyb {
    private final ArrayList zzgie;

    zzcuc(ArrayList arrayList0) {
        this.zzgie = arrayList0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        ((Bundle)object0).putStringArrayList("ad_types", this.zzgie);
    }
}

