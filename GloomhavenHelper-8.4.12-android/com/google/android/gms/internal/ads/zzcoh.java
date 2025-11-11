package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public final class zzcoh implements zzcoe {
    private final zzdoe zzfrv;
    private final zzbxo zzgcr;
    private final zzcay zzgcs;

    public zzcoh(zzbxo zzbxo0, zzdoe zzdoe0, zzcay zzcay0) {
        this.zzgcr = zzbxo0;
        this.zzfrv = zzdoe0;
        this.zzgcs = zzcay0;
    }

    // 检测为 Lambda 实现
    final zzbyo zza(zzdof zzdof0, zzdof zzdof1, zzdeq zzdeq0, zzdei zzdei0, JSONObject jSONObject0) throws Exception [...]

    // 检测为 Lambda 实现
    final zzdof zza(zzdei zzdei0, zzccv zzccv0) throws Exception [...]

    // 检测为 Lambda 实现
    final zzdof zza(zzdeq zzdeq0, zzdei zzdei0, JSONArray jSONArray0) throws Exception [...]

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final boolean zza(zzdeq zzdeq0, zzdei zzdei0) {
        return zzdei0.zzgpq != null && zzdei0.zzgpq.zzfnc != null;
    }

    private final zzdof zzb(zzdeq zzdeq0, zzdei zzdei0, JSONObject jSONObject0) {
        zzdof zzdof0 = this.zzgcr.zzadz().zzard();
        zzdof zzdof1 = this.zzgcs.zza(zzdeq0, zzdei0, jSONObject0);
        return zzdnt.zzb(new zzdof[]{zzdof0, zzdof1}).zza(() -> {
            zzbyz zzbyz0 = (zzbyz)zzdof1.get();
            zzccv zzccv0 = (zzccv)zzdof0.get();
            zzbnv zzbnv0 = new zzbnv(zzdeq0, zzdei0, null);
            zzbzl zzbzl0 = new zzbzl(zzbyz0);
            zzbyc zzbyc0 = new zzbyc(jSONObject0, zzccv0);
            zzbzb zzbzb0 = this.zzgcr.zza(zzbnv0, zzbzl0, zzbyc0);
            zzbzb0.zzadr().zzalw();
            zzbzb0.zzads().zzb(zzccv0);
            zzbzb0.zzadt().zzl(zzbyz0.zzakj());
            return zzbzb0.zzadq();
        }, this.zzfrv);
    }

    @Override  // com.google.android.gms.internal.ads.zzcly
    public final zzdof zzb(zzdeq zzdeq0, zzdei zzdei0) {
        zzdof zzdof0 = this.zzgcr.zzadz().zzard();
        this.zzgcr.zzadz().zzd(zzdof0);
        return zzdnt.zzb(zzdnt.zzb(zzdof0, (zzccv zzccv0) -> {
            JSONObject jSONObject0 = zzayf.zza("isNonagon", Boolean.TRUE);
            JSONObject jSONObject1 = new JSONObject();
            jSONObject1.put("response", zzdei0.zzgpq.zzfnc);
            jSONObject1.put("sdk_params", jSONObject0);
            return zzdnt.zzb(zzccv0.zzc("google.afma.nativeAds.preProcessJson", jSONObject1), zzcoi.zzblf, this.zzfrv);
        }, this.zzfrv), (JSONArray jSONArray0) -> {
            if(jSONArray0.length() == 0) {
                return zzdnt.immediateFailedFuture(new zzcid(3));
            }
            if(zzdeq0.zzgql.zzfir.zzggu > 1) {
                int v1 = jSONArray0.length();
                this.zzgcr.zzadz().zzdm(Math.min(v1, zzdeq0.zzgql.zzfir.zzggu));
                ArrayList arrayList0 = new ArrayList(zzdeq0.zzgql.zzfir.zzggu);
                for(int v = 0; v < zzdeq0.zzgql.zzfir.zzggu; ++v) {
                    if(v < v1) {
                        arrayList0.add(this.zzb(zzdeq0, zzdei0, jSONArray0.getJSONObject(v)));
                    }
                    else {
                        arrayList0.add(zzdnt.immediateFailedFuture(new zzcid(3)));
                    }
                }
                return zzdnt.zzaj(arrayList0);
            }
            return zzdnt.zzb(this.zzb(zzdeq0, zzdei0, jSONArray0.getJSONObject(0)), zzcol.zzdpv, this.zzfrv);
        }, this.zzfrv);
    }
}

