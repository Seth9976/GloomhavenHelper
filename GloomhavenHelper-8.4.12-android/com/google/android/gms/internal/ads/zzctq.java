package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class zzctq implements zzcyb {
    private final zzdeu zzfir;

    public zzctq(zzdeu zzdeu0) {
        Preconditions.checkNotNull(zzdeu0, "the targeting must not be null");
        this.zzfir = zzdeu0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        Bundle bundle0 = (Bundle)object0;
        zzuh zzuh0 = this.zzfir.zzgqq;
        bundle0.putString("slotname", this.zzfir.zzgqr);
        boolean z = true;
        if(this.zzfir.zzgqx.contains("new_rewarded")) {
            bundle0.putBoolean("is_new_rewarded", true);
        }
        zzdez.zza(bundle0, "cust_age", new SimpleDateFormat("yyyyMMdd", Locale.US).format(new Date(zzuh0.zzccm)), zzuh0.zzccm != -1L);
        zzdez.zza(bundle0, "extras", zzuh0.extras);
        zzdez.zza(bundle0, "cust_gender", zzuh0.zzccn, zzuh0.zzccn != -1);
        zzdez.zza(bundle0, "kw", zzuh0.zzcco);
        zzdez.zza(bundle0, "tag_for_child_directed_treatment", zzuh0.zzabv, zzuh0.zzabv != -1);
        if(zzuh0.zzccp) {
            bundle0.putBoolean("test_request", zzuh0.zzccp);
        }
        zzdez.zza(bundle0, "d_imp_hdr", 1, zzuh0.versionCode >= 2 && zzuh0.zzbkp);
        boolean z1 = zzuh0.versionCode >= 2 && !TextUtils.isEmpty(zzuh0.zzccq);
        zzdez.zza(bundle0, "ppid", zzuh0.zzccq, z1);
        if(zzuh0.zzmk != null) {
            Float float0 = (float)(zzuh0.zzmk.getAccuracy() * 1000.0f);
            Long long0 = (long)(zzuh0.zzmk.getTime() * 1000L);
            Long long1 = (long)(zzuh0.zzmk.getLatitude() * 10000000.0);
            Long long2 = (long)(zzuh0.zzmk.getLongitude() * 10000000.0);
            Bundle bundle1 = new Bundle();
            bundle1.putFloat("radius", ((float)float0));
            bundle1.putLong("lat", ((long)long1));
            bundle1.putLong("long", ((long)long2));
            bundle1.putLong("time", ((long)long0));
            bundle0.putBundle("uule", bundle1);
        }
        zzdez.zza(bundle0, "url", zzuh0.zzccs);
        zzdez.zza(bundle0, "neighboring_content_urls", zzuh0.zzccz);
        zzdez.zza(bundle0, "custom_targeting", zzuh0.zzccu);
        zzdez.zza(bundle0, "category_exclusions", zzuh0.zzccv);
        zzdez.zza(bundle0, "request_agent", zzuh0.zzccw);
        zzdez.zza(bundle0, "request_pkg", zzuh0.zzccx);
        zzdez.zza(bundle0, "is_designed_for_families", Boolean.valueOf(zzuh0.zzccy), zzuh0.versionCode >= 7);
        if(zzuh0.versionCode >= 8) {
            Integer integer0 = zzuh0.zzabw;
            if(zzuh0.zzabw == -1) {
                z = false;
            }
            zzdez.zza(bundle0, "tag_for_under_age_of_consent", integer0, z);
            zzdez.zza(bundle0, "max_ad_content_rating", zzuh0.zzabx);
        }
    }
}

