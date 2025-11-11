package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.concurrent.Executor;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzbkk implements zzpt {
    private final Clock zzbmz;
    private boolean zzbsm;
    private zzbdv zzdae;
    private final zzbjz zzfel;
    private final Executor zzfeo;
    private zzbkd zzfeq;
    private boolean zzffk;

    public zzbkk(Executor executor0, zzbjz zzbjz0, Clock clock0) {
        this.zzbsm = false;
        this.zzffk = false;
        this.zzfeq = new zzbkd();
        this.zzfeo = executor0;
        this.zzfel = zzbjz0;
        this.zzbmz = clock0;
    }

    public final void disable() {
        this.zzbsm = false;
    }

    public final void enable() {
        this.zzbsm = true;
        this.zzafz();
    }

    @Override  // com.google.android.gms.internal.ads.zzpt
    public final void zza(zzpu zzpu0) {
        this.zzfeq.zzbnz = this.zzffk ? false : zzpu0.zzbnz;
        zzbkd zzbkd0 = this.zzfeq;
        zzbkd0.timestamp = this.zzbmz.elapsedRealtime();
        this.zzfeq.zzffb = zzpu0;
        if(this.zzbsm) {
            this.zzafz();
        }
    }

    private final void zzafz() {
        try {
            JSONObject jSONObject0 = this.zzfel.zza(this.zzfeq);
            if(this.zzdae != null) {
                zzbkn zzbkn0 = () -> this.zzdae.zzb("AFMA_updateActiveView", jSONObject0);
                this.zzfeo.execute(zzbkn0);
            }
        }
        catch(JSONException jSONException0) {
            zzawf.zza("Failed to call video active view js", jSONException0);
        }
    }

    public final void zzbf(boolean z) {
        this.zzffk = z;
    }

    public final void zzg(zzbdv zzbdv0) {
        this.zzdae = zzbdv0;
    }

    // 检测为 Lambda 实现
    final void zzi(JSONObject jSONObject0) [...]
}

