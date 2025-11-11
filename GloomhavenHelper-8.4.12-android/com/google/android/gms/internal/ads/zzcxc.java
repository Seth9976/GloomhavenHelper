package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;

public final class zzcxc implements zzcye {
    private final String zzfka;
    private final Context zzur;

    zzcxc(Context context0, @Nullable String s) {
        this.zzur = context0;
        this.zzfka = s;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        return this.zzfka == null ? zzdnt.zzaj(null) : zzdnt.zzaj((Bundle bundle0) -> bundle0.putString("rewarded_sku_package", "com.esotericsoftware.gloomhavenhelper"));
    }

    // 检测为 Lambda 实现
    final void zzp(Bundle bundle0) [...]
}

