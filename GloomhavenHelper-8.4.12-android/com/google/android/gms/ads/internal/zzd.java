package com.google.android.gms.ads.internal;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzakc;
import com.google.android.gms.internal.ads.zzakj;
import com.google.android.gms.internal.ads.zzavs;
import com.google.android.gms.internal.ads.zzawf;
import com.google.android.gms.internal.ads.zzazo;
import com.google.android.gms.internal.ads.zzazq;
import com.google.android.gms.internal.ads.zzazu;
import com.google.android.gms.internal.ads.zzdnt;
import com.google.android.gms.internal.ads.zzdof;
import com.google.android.gms.internal.ads.zzvh;
import com.google.android.gms.internal.ads.zzzx;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
public final class zzd {
    private long zzble;
    private Context zzur;

    public zzd() {
        this.zzble = 0L;
    }

    @VisibleForTesting
    private final void zza(Context context0, zzazo zzazo0, boolean z, @Nullable zzavs zzavs0, String s, @Nullable String s1, @Nullable Runnable runnable0) {
        if(zzq.zzlc().elapsedRealtime() - this.zzble < 5000L) {
            zzawf.zzfa("Not retrying to fetch app settings");
            return;
        }
        this.zzble = zzq.zzlc().elapsedRealtime();
        if(zzavs0 != null && (zzq.zzlc().currentTimeMillis() - zzavs0.zzvo() <= ((long)(((Long)zzvh.zzpd().zzd(zzzx.zzcnq)))) && zzavs0.zzvp())) {
            return;
        }
        if(context0 == null) {
            zzawf.zzfa("Context not provided to fetch application settings");
            return;
        }
        if(TextUtils.isEmpty(s) && TextUtils.isEmpty(s1)) {
            zzawf.zzfa("App settings could not be fetched. Required parameters missing");
            return;
        }
        Context context1 = context0.getApplicationContext();
        if(context1 == null) {
            context1 = context0;
        }
        this.zzur = context1;
        zzakc zzakc0 = zzq.zzli().zzb(this.zzur, zzazo0).zza("google.afma.config.fetchAppSettings", zzakj.zzdbu, zzakj.zzdbu);
        try {
            JSONObject jSONObject0 = new JSONObject();
            if(!TextUtils.isEmpty(s)) {
                jSONObject0.put("app_id", s);
            }
            else if(!TextUtils.isEmpty(s1)) {
                jSONObject0.put("ad_unit_id", s1);
            }
            jSONObject0.put("is_init", z);
            jSONObject0.put("pn", "com.esotericsoftware.gloomhavenhelper");
            zzdof zzdof0 = zzakc0.zzi(jSONObject0);
            zzdof zzdof1 = zzdnt.zzb(zzdof0, zzf.zzblf, zzazq.zzdxp);
            if(runnable0 != null) {
                zzdof0.addListener(runnable0, zzazq.zzdxp);
            }
            zzazu.zza(zzdof1, "ConfigLoader.maybeFetchNewAppSettings");
        }
        catch(Exception exception0) {
            zzawf.zzc("Error requesting application settings", exception0);
        }
    }

    public final void zza(Context context0, zzazo zzazo0, String s, zzavs zzavs0) {
        this.zza(context0, zzazo0, false, zzavs0, (zzavs0 == null ? null : zzavs0.zzvr()), s, null);
    }

    public final void zza(Context context0, zzazo zzazo0, String s, @Nullable Runnable runnable0) {
        this.zza(context0, zzazo0, true, null, s, null, runnable0);
    }
}

