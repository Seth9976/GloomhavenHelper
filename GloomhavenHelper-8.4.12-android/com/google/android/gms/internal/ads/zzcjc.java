package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzq;
import java.io.StringReader;
import java.util.concurrent.Executor;
import org.json.JSONObject;

public final class zzcjc {
    private final Executor executor;
    private final zzdeu zzfir;
    private final zzazo zzfyd;
    private final Context zzur;

    public zzcjc(Context context0, zzazo zzazo0, zzdeu zzdeu0, Executor executor0) {
        this.zzur = context0;
        this.zzfyd = zzazo0;
        this.zzfir = zzdeu0;
        this.executor = executor0;
    }

    public final zzdof zzanr() {
        zzakc zzakc0 = zzq.zzli().zzb(this.zzur, this.zzfyd).zza("google.afma.response.normalize", zzakj.zzdbu, zzakj.zzdbu);
        return zzdnt.zzb(zzdnt.zzb(zzdnt.zzb(zzdnt.zzaj(""), new zzcjf(this, this.zzfir.zzgqq.zzcda), this.executor), new zzcje(zzakc0), this.executor), (JSONObject jSONObject0) -> zzdnt.zzaj(new zzdeq(new zzdel(this.zzfir), zzdeo.zza(new StringReader(jSONObject0.toString())))), this.executor);
    }

    // 检测为 Lambda 实现
    final zzdof zzo(JSONObject jSONObject0) throws Exception [...]
}

