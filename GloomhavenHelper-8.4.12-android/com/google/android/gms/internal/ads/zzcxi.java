package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.List;

final class zzcxi implements zzdne {
    private final String zzczs;
    private final zzcxg zzgjs;
    private final List zzgju;
    private final Bundle zzgjv;

    zzcxi(zzcxg zzcxg0, String s, List list0, Bundle bundle0) {
        this.zzgjs = zzcxg0;
        this.zzczs = s;
        this.zzgju = list0;
        this.zzgjv = bundle0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdne
    public final zzdof zzapl() {
        return this.zzgjs.zza(this.zzczs, this.zzgju, this.zzgjv);
    }
}

