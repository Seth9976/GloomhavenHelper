package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzauq implements zzauy {
    private final Context zzcgz;
    private final String zzczs;

    zzauq(Context context0, String s) {
        this.zzcgz = context0;
        this.zzczs = s;
    }

    @Override  // com.google.android.gms.internal.ads.zzauy
    public final void zza(zzbgd zzbgd0) {
        zzbgd0.zzb(ObjectWrapper.wrap(this.zzcgz), this.zzczs, "com.esotericsoftware.gloomhavenhelper");
    }
}

