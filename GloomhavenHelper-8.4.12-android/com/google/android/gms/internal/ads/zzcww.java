package com.google.android.gms.internal.ads;

import android.os.Bundle;
import androidx.annotation.Nullable;

public final class zzcww implements zzcye {
    private final Bundle zzfjz;
    private final zzdoe zzfrv;

    public zzcww(zzdoe zzdoe0, @Nullable Bundle bundle0) {
        this.zzfrv = zzdoe0;
        this.zzfjz = bundle0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        zzcwv zzcwv0 = () -> new zzcwt(this.zzfjz);
        return this.zzfrv.zzd(zzcwv0);
    }

    // 检测为 Lambda 实现
    final zzcwt zzapk() throws Exception [...]
}

