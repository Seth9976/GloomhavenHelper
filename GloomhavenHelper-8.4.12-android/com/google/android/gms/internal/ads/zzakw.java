package com.google.android.gms.internal.ads;

import org.json.JSONObject;

public final class zzakw implements zzdng {
    private final zzake zzdcc;
    private final zzakh zzdcd;
    private final String zzdce;
    private final zzdof zzdci;

    zzakw(zzdof zzdof0, String s, zzakh zzakh0, zzake zzake0) {
        this.zzdci = zzdof0;
        this.zzdce = s;
        this.zzdcd = zzakh0;
        this.zzdcc = zzake0;
    }

    // 检测为 Lambda 实现
    final zzdof zza(Object object0, zzajy zzajy0) throws Exception [...]

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) throws Exception {
        zzakz zzakz0 = (zzajy zzajy0) -> {
            zzdof zzdof0 = new zzazy();
            zzaky zzaky0 = new zzaky(this, ((zzazy)zzdof0));
            zzafi.zzcyk.zza("abc98f91-27ff-4ccf-98cd-455d941fb614", zzaky0);
            JSONObject jSONObject0 = new JSONObject();
            jSONObject0.put("id", "abc98f91-27ff-4ccf-98cd-455d941fb614");
            jSONObject0.put("args", this.zzdcd.zzj(object0));
            zzajy0.zzb(this.zzdce, jSONObject0);
            return zzdof0;
        };
        return zzdnt.zzb(this.zzdci, zzakz0, zzazq.zzdxp);
    }
}

