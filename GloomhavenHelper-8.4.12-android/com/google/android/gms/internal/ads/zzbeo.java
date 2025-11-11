package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;

final class zzbeo implements zzafz {
    private final zzbem zzeij;

    zzbeo(zzbem zzbem0) {
        this.zzeij = zzbem0;
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
                    zzbem zzbem0 = this.zzeij;
                    synchronized(zzbem0) {
                        if(zzbem.zza(this.zzeij) != v) {
                            zzbem.zza(this.zzeij, v);
                            this.zzeij.requestLayout();
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

