package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class zzcgq {
    private ConcurrentHashMap zzfvt;

    public zzcgq(zzcgx zzcgx0) {
        this.zzfvt = zzcgx0.zzanb();
    }

    public final void zzc(zzdeq zzdeq0) {
        if(zzdeq0.zzgqm.zzgqi.size() > 0) {
            switch(((zzdei)zzdeq0.zzgqm.zzgqi.get(0)).zzfmh) {
                case 1: {
                    this.zzfvt.put("ad_format", "banner");
                    break;
                }
                case 2: {
                    this.zzfvt.put("ad_format", "interstitial");
                    break;
                }
                case 3: {
                    this.zzfvt.put("ad_format", "native_express");
                    break;
                }
                case 4: {
                    this.zzfvt.put("ad_format", "native_advanced");
                    break;
                }
                case 5: {
                    this.zzfvt.put("ad_format", "rewarded");
                    break;
                }
                default: {
                    this.zzfvt.put("ad_format", "unknown");
                }
            }
            if(!TextUtils.isEmpty(zzdeq0.zzgqm.zzgqj.zzcac)) {
                this.zzfvt.put("gqi", zzdeq0.zzgqm.zzgqj.zzcac);
            }
        }
    }

    public final void zzi(Bundle bundle0) {
        if(bundle0.containsKey("cnt")) {
            this.zzfvt.put("network_coarse", Integer.toString(bundle0.getInt("cnt")));
        }
        if(bundle0.containsKey("gnt")) {
            this.zzfvt.put("network_fine", Integer.toString(bundle0.getInt("gnt")));
        }
    }

    public final Map zzqv() {
        return this.zzfvt;
    }
}

