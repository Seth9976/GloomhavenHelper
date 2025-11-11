package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.internal.zzq;
import org.json.JSONObject;

final class zzcko implements zzdng {
    private final zzcyd zzfzg;

    zzcko(zzcyd zzcyd0) {
        this.zzfzg = zzcyd0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdng
    public final zzdof zzf(Object object0) {
        JSONObject jSONObject0 = zzq.zzkv().zzd(((Bundle)object0));
        return this.zzfzg.zzt(jSONObject0);
    }
}

