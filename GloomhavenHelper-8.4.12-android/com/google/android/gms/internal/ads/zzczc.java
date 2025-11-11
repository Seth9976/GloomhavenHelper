package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;
import org.json.JSONObject;

public final class zzczc implements zzcye {
    private final String packageName;
    private final zzdoe zzfrv;
    private final zzare zzgli;
    private final Context zzur;

    public zzczc(@Nullable zzare zzare0, Context context0, String s, zzdoe zzdoe0) {
        this.zzgli = zzare0;
        this.zzur = context0;
        this.packageName = s;
        this.zzfrv = zzdoe0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        zzczb zzczb0 = () -> {
            JSONObject jSONObject0 = new JSONObject();
            zzare zzare0 = this.zzgli;
            if(zzare0 != null) {
                zzare0.zza(this.zzur, this.packageName, jSONObject0);
            }
            return new zzcyz(jSONObject0);
        };
        return this.zzfrv.zzd(zzczb0);
    }

    // 检测为 Lambda 实现
    final zzcyz zzapt() throws Exception [...]
}

