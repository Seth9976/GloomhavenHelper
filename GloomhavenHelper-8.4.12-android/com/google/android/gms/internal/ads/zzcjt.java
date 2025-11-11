package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;

final class zzcjt implements zzbsn {
    private final zzaui zzbnp;
    private final Context zzur;

    zzcjt(Context context0, zzaui zzaui0) {
        this.zzur = context0;
        this.zzbnp = zzaui0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbsn
    public final void zzb(zzdeq zzdeq0) {
        if(!TextUtils.isEmpty(zzdeq0.zzgqm.zzgqj.zzdmp)) {
            this.zzbnp.zza(this.zzur, zzdeq0.zzgql.zzfir.zzgqq);
            this.zzbnp.zzj(this.zzur, zzdeq0.zzgqm.zzgqj.zzdmp);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbsn
    public final void zzd(zzaqx zzaqx0) {
    }
}

