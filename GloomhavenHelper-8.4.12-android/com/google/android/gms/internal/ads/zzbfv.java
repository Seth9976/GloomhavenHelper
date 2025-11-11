package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;

final class zzbfv implements zzafz {
    private final zzbft zzejf;

    zzbfv(zzbft zzbft0) {
        this.zzejf = zzbft0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        zzbdv zzbdv0 = (zzbdv)object0;
        if(map0 != null) {
            String s = (String)map0.get("height");
            if(!TextUtils.isEmpty(s)) {
                try {
                    int v = Integer.parseInt(s);
                    zzbft zzbft0 = this.zzejf;
                    synchronized(zzbft0) {
                        if(zzbft.zza(this.zzejf) != v) {
                            zzbft.zza(this.zzejf, v);
                            this.zzejf.requestLayout();
                        }
                    }
                }
                catch(Exception exception0) {
                    zzawf.zzd("Exception occurred while getting webview content height", exception0);
                }
            }
        }
    }
}

