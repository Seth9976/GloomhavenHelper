package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamite.DynamiteModule;

public final class zzcxp implements zzcye {
    private final zzazo zzblu;
    private final zzdoe zzfrv;
    private final Context zzur;

    public zzcxp(zzdoe zzdoe0, Context context0, zzazo zzazo0) {
        this.zzfrv = zzdoe0;
        this.zzur = context0;
        this.zzblu = zzazo0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        zzcxs zzcxs0 = () -> new zzcxq(Wrappers.packageManager(this.zzur).isCallerInstantApp(), zzawo.zzba(this.zzur), this.zzblu.zzbmj, zzawu.zzwv(), zzawo.zzax(this.zzur), DynamiteModule.getRemoteVersion(this.zzur, "com.google.android.gms.ads.dynamite"), DynamiteModule.getLocalVersion(this.zzur, "com.google.android.gms.ads.dynamite"));
        return this.zzfrv.zzd(zzcxs0);
    }

    // 检测为 Lambda 实现
    final zzcxq zzapo() throws Exception [...]
}

