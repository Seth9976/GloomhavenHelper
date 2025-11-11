package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;

public final class zzagg implements zzafz {
    private final zzagj zzcyx;

    private zzagg(zzagj zzagj0) {
        this.zzcyx = zzagj0;
    }

    public static void zza(zzbdv zzbdv0, zzagj zzagj0) {
        zzbdv0.zza("/reward", new zzagg(zzagj0));
    }

    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        zzasq zzasq0;
        String s = (String)map0.get("action");
        if("grant".equals(s)) {
            try {
                zzasq0 = null;
                int v = Integer.parseInt(((String)map0.get("amount")));
                String s1 = (String)map0.get("type");
                if(!TextUtils.isEmpty(s1)) {
                    zzasq0 = new zzasq(s1, v);
                }
            }
            catch(NumberFormatException numberFormatException0) {
                zzawf.zzd("Unable to parse reward amount.", numberFormatException0);
            }
            this.zzcyx.zza(zzasq0);
            return;
        }
        if("video_start".equals(s)) {
            this.zzcyx.zzrx();
            return;
        }
        if("video_complete".equals(s)) {
            this.zzcyx.zzry();
        }
    }
}

