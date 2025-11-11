package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;

public final class zzcrl implements zzcrf {
    private final zzbwt zzgbv;
    private final Context zzur;

    public zzcrl(Context context0, zzbwt zzbwt0) {
        this.zzur = context0;
        this.zzgbv = zzbwt0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcrf
    public final Object zza(zzdeq zzdeq0, zzdei zzdei0, View view0, zzcri zzcri0) {
        zzcrn zzcrn0 = new zzcrn(this, zzcrk.zzgfb);
        zzbnv zzbnv0 = new zzbnv(zzdeq0, zzdei0, null);
        zzbvw zzbvw0 = this.zzgbv.zza(zzbnv0, zzcrn0);
        zzcri0.zza(new zzcrm(this, zzbvw0));
        return zzbvw0.zzaex();
    }
}

