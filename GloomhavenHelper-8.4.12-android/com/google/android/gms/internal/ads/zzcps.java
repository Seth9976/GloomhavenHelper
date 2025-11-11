package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONObject;

public final class zzcps implements zzcmc {
    private final zzcnk zzfws;
    @GuardedBy("this")
    private final Map zzgdm;

    public zzcps(zzcnk zzcnk0) {
        this.zzgdm = new HashMap();
        this.zzfws = zzcnk0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcmc
    public final zzcmd zzd(String s, JSONObject jSONObject0) throws zzdfa {
        synchronized(this) {
            zzcmd zzcmd0 = (zzcmd)this.zzgdm.get(s);
            if(zzcmd0 == null) {
                zzdfb zzdfb0 = this.zzfws.zze(s, jSONObject0);
                if(zzdfb0 == null) {
                    return null;
                }
                zzcmd0 = new zzcmd(zzdfb0, new zzcnl(), s);
                this.zzgdm.put(s, zzcmd0);
            }
            return zzcmd0;
        }
    }
}

