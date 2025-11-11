package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import org.json.JSONObject;

public final class zzcbt {
    private final Executor zzfeo;
    private final zzdeu zzfir;
    private final zzceb zzfrh;
    private final Context zzur;

    public zzcbt(Context context0, zzdeu zzdeu0, Executor executor0, zzceb zzceb0) {
        this.zzur = context0;
        this.zzfir = zzdeu0;
        this.zzfeo = executor0;
        this.zzfrh = zzceb0;
    }

    // 检测为 Lambda 实现
    final zzdof zza(String s, String s1, Object object0) throws Exception [...]

    // 检测为 Lambda 实现
    final zzdof zza(JSONObject jSONObject0, zzbdv zzbdv0) throws Exception [...]

    // 检测为 Lambda 实现
    final void zza(zzbdv zzbdv0, zzazv zzazv0, boolean z) [...]

    // 检测为 Lambda 实现
    final void zzb(zzbdv zzbdv0, zzazv zzazv0, boolean z) [...]

    private final void zzk(zzbdv zzbdv0) {
        zzbdv0.zza("/video", zzafi.zzcyh);
        zzbdv0.zza("/videoMeta", zzafi.zzcyi);
        zzbdv0.zza("/precache", new zzbdf());
        zzbdv0.zza("/delayPageLoaded", zzafi.zzcyl);
        zzbdv0.zza("/instrument", zzafi.zzcyj);
        zzbdv0.zza("/log", zzafi.zzcyc);
        zzbdv0.zza("/videoClicked", zzafi.zzcyd);
        zzbdv0.zzaaf().zzbb(true);
        zzbdv0.zza("/click", zzafi.zzcxy);
        if(this.zzfir.zzdlk != null) {
            zzbdv0.zzaaf().zzbc(true);
            zzbdv0.zza("/open", new zzagd(null, null));
            return;
        }
        zzbdv0.zzaaf().zzbc(false);
    }

    public final zzdof zzn(JSONObject jSONObject0) {
        return zzdnt.zzb(zzdnt.zzb(zzdnt.zzaj(null), (Object object0) -> {
            zzuk zzuk0 = zzuk.zzh(this.zzur);
            zzbdv zzbdv0 = this.zzfrh.zza(zzuk0, false);
            zzdof zzdof0 = zzazv.zzl(zzbdv0);
            this.zzk(zzbdv0);
            zzbdv0.zzaaf().zza(new zzcbx(((zzazv)zzdof0)));
            zzbdv0.loadUrl(((String)zzvh.zzpd().zzd(zzzx.zzcmv)));
            return zzdof0;
        }, this.zzfeo), (zzbdv zzbdv0) -> {
            zzdof zzdof0 = zzazv.zzl(zzbdv0);
            if(this.zzfir.zzdlk == null) {
                zzbdv0.zza(zzbfl.zzabw());
            }
            else {
                zzbdv0.zza(zzbfl.zzabx());
            }
            zzbdv0.zzaaf().zza((boolean z) -> {
                if(this.zzfir.zzgqp != null && zzbdv0.zzyq() != null) {
                    zzbdv0.zzyq().zzb(this.zzfir.zzgqp);
                }
                ((zzazv)zzdof0).zzxs();
            });
            zzbdv0.zzb("google.afma.nativeAds.renderVideo", jSONObject0);
            return zzdof0;
        }, this.zzfeo);
    }

    public final zzdof zzo(String s, String s1) {
        return zzdnt.zzb(zzdnt.zzaj(null), (Object object0) -> {
            zzuk zzuk0 = zzuk.zzh(this.zzur);
            zzbdv zzbdv0 = this.zzfrh.zza(zzuk0, false);
            zzdof zzdof0 = zzazv.zzl(zzbdv0);
            this.zzk(zzbdv0);
            if(this.zzfir.zzdlk == null) {
                zzbdv0.zza(zzbfl.zzabw());
            }
            else {
                zzbdv0.zza(zzbfl.zzabx());
            }
            zzbdv0.zzaaf().zza((boolean z) -> {
                if(z) {
                    if(this.zzfir.zzgqp != null && zzbdv0.zzyq() != null) {
                        zzbdv0.zzyq().zzb(this.zzfir.zzgqp);
                    }
                    ((zzazv)zzdof0).zzxs();
                    return;
                }
                ((zzazv)zzdof0).setException(new zzcpe("Instream video Web View failed to load.", 0));
            });
            zzbdv0.zzb(s, s1, null);
            return zzdof0;
        }, this.zzfeo);
    }

    // 检测为 Lambda 实现
    final zzdof zzq(Object object0) throws Exception [...]
}

