package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;

public final class zzbbe extends zzbaw {
    @Override  // com.google.android.gms.internal.ads.zzbaw
    @Nullable
    public final zzbat zza(Context context0, zzbbm zzbbm0, int v, boolean z, zzaak zzaak0, zzbbj zzbbj0) {
        if(context0.getApplicationInfo() != null && false) {
            return null;
        }
        zzbbl zzbbl0 = new zzbbl(context0, zzbbm0.zzyw(), zzbbm0.zzyu(), zzaak0, zzbbm0.zzyr());
        return v == 2 ? new zzbbp(context0, zzbbl0, zzbbm0, z, zzbbe.zzb(zzbbm0), zzbbj0) : new zzbak(context0, z, zzbbe.zzb(zzbbm0), zzbbj0, new zzbbl(context0, zzbbm0.zzyw(), zzbbm0.zzyu(), zzaak0, zzbbm0.zzyr()));
    }
}

