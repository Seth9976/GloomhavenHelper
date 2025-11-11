package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzc;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.Executor;
import org.json.JSONObject;

public final class zzccv {
    private final zzbee zzbms;
    private final zzazo zzdjo;
    private final zzdq zzehb;
    private final Executor zzfeo;
    private final zza zzfsj;
    private final zzcda zzftj;
    private final zzagf zzftk;
    private zzdof zzftl;
    private final Context zzur;

    zzccv(zzcdf zzcdf0) {
        this.zzur = zzcdf0.zzur;
        this.zzfeo = zzcdf0.zzfeo;
        this.zzehb = zzcdf0.zzehb;
        this.zzdjo = zzcdf0.zzdjo;
        this.zzfsj = zzcdf0.zzfsj;
        this.zzftj = new zzcda(null);
        this.zzbms = zzcdf0.zzbms;
        this.zzftk = new zzagf();
    }

    public final void destroy() {
        synchronized(this) {
            if(this.zzftl == null) {
                return;
            }
            zzdnt.zza(this.zzftl, new zzccw(this), this.zzfeo);
            this.zzftl = null;
        }
    }

    // 检测为 Lambda 实现
    final zzdof zza(String s, JSONObject jSONObject0, zzbdv zzbdv0) throws Exception [...]

    public final void zza(String s, zzafz zzafz0) {
        synchronized(this) {
            if(this.zzftl == null) {
                return;
            }
            zzdnt.zza(this.zzftl, new zzccz(this, s, zzafz0), this.zzfeo);
        }
    }

    public final void zza(String s, Map map0) {
        synchronized(this) {
            if(this.zzftl == null) {
                return;
            }
            zzdnt.zza(this.zzftl, new zzcdb(this, s, map0), this.zzfeo);
        }
    }

    public final void zza(WeakReference weakReference0, String s, zzafz zzafz0) {
        this.zza(s, new zzcde(this, weakReference0, s, zzafz0, null));
    }

    public final void zzalx() {
        synchronized(this) {
            String s = (String)zzvh.zzpd().zzd(zzzx.zzcmt);
            this.zzftl = zzdnt.zzb(zzbee.zza(this.zzur, this.zzdjo, s, this.zzehb, this.zzfsj), (zzbdv zzbdv0) -> {
                zzbdv0.zza("/result", this.zzftk);
                zzbfi zzbfi0 = zzbdv0.zzaaf();
                zzc zzc0 = new zzc(this.zzur, null, null);
                zzbfi0.zza(null, this.zzftj, this.zzftj, this.zzftj, this.zzftj, false, null, zzc0, null, null);
                return zzbdv0;
            }, this.zzfeo);
            zzazu.zza(this.zzftl, "NativeJavascriptExecutor.initializeEngine");
        }
    }

    public final void zzb(String s, zzafz zzafz0) {
        synchronized(this) {
            if(this.zzftl == null) {
                return;
            }
            zzdnt.zza(this.zzftl, new zzccy(this, s, zzafz0), this.zzfeo);
        }
    }

    public final zzdof zzc(String s, JSONObject jSONObject0) {
        synchronized(this) {
            return this.zzftl == null ? zzdnt.zzaj(null) : zzdnt.zzb(this.zzftl, (zzbdv zzbdv0) -> this.zzftk.zza(zzbdv0, s, jSONObject0), this.zzfeo);
        }
    }

    // 检测为 Lambda 实现
    final zzbdv zzm(zzbdv zzbdv0) [...]
}

