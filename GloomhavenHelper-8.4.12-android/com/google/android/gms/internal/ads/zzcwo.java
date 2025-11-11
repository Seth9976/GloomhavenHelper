package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzq;
import java.util.Set;

public final class zzcwo implements zzcye {
    private final zzdoe zzghq;
    private final Set zzgib;
    private final Context zzur;

    public zzcwo(zzdoe zzdoe0, Context context0, Set set0) {
        this.zzghq = zzdoe0;
        this.zzur = context0;
        this.zzgib = set0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        zzcwn zzcwn0 = () -> // 去混淆评级： 低(20)
        (!((Boolean)zzvh.zzpd().zzd(zzzx.zzcoz)).booleanValue() || !zzcwl.zze(this.zzgib) ? new zzcwl(null) : new zzcwl(zzq.zzlk().getVersion(this.zzur)));
        return this.zzghq.zzd(zzcwn0);
    }

    // 去混淆评级： 低(20)
    // 检测为 Lambda 实现
    final zzcwl zzapj() throws Exception [...]
}

