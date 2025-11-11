package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;
import java.util.ArrayList;

final class zzbpk implements zzbqt, zzbrn {
    private final zzapm zzbla;
    private final zzdei zzfhg;
    private final Context zzur;

    public zzbpk(Context context0, zzdei zzdei0, zzapm zzapm0) {
        this.zzur = context0;
        this.zzfhg = zzdei0;
        this.zzbla = zzapm0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbrn
    public final void onAdLoaded() {
        if(this.zzfhg.zzgqd != null && this.zzfhg.zzgqd.zzdjf) {
            ArrayList arrayList0 = new ArrayList();
            if(!this.zzfhg.zzgqd.zzdjg.isEmpty()) {
                arrayList0.add(this.zzfhg.zzgqd.zzdjg);
            }
            this.zzbla.zza(this.zzur, arrayList0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqt
    public final void zzby(@Nullable Context context0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzbqt
    public final void zzbz(@Nullable Context context0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzbqt
    public final void zzca(@Nullable Context context0) {
        this.zzbla.detach();
    }
}

