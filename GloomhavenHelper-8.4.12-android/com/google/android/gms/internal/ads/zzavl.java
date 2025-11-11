package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.PackageInfo;

public final class zzavl implements zzavn {
    @Override  // com.google.android.gms.internal.ads.zzavn
    public final zzdof zza(Context context0, int v) {
        return zzdnt.zzaj(null);
    }

    @Override  // com.google.android.gms.internal.ads.zzavn
    public final zzdof zza(String s, PackageInfo packageInfo0) {
        return zzdnt.zzaj(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzavn
    public final zzdof zzam(Context context0) {
        zzdof zzdof0 = new zzazy();
        if(zzayx.zzbn(context0)) {
            zzavo zzavo0 = new zzavo(this, context0, ((zzazy)zzdof0));
            zzazq.zzdxk.execute(zzavo0);
        }
        return zzdof0;
    }
}

