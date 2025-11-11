package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzq;
import java.util.Map;

public final class zzaoo {
    private final zzbdv zzdae;
    private final boolean zzdgw;
    private final String zzdgx;

    public zzaoo(zzbdv zzbdv0, Map map0) {
        this.zzdae = zzbdv0;
        this.zzdgx = (String)map0.get("forceOrientation");
        if(map0.containsKey("allowOrientationChange")) {
            this.zzdgw = Boolean.parseBoolean(((String)map0.get("allowOrientationChange")));
            return;
        }
        this.zzdgw = true;
    }

    public final void execute() {
        int v;
        if(this.zzdae == null) {
            zzawf.zzfa("AdWebView is null");
            return;
        }
        if("portrait".equalsIgnoreCase(this.zzdgx)) {
            v = 7;
        }
        else if("landscape".equalsIgnoreCase(this.zzdgx)) {
            v = 6;
        }
        else {
            v = this.zzdgw ? -1 : zzq.zzkx().zzwt();
        }
        this.zzdae.setRequestedOrientation(v);
    }
}

