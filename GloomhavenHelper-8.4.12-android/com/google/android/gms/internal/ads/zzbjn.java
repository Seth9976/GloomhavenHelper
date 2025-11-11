package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;

public final class zzbjn implements zzbjj {
    private final zzdeg zzfdn;

    public zzbjn(zzdeg zzdeg0) {
        this.zzfdn = zzdeg0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbjj
    public final void zzk(Map map0) {
        String s = (String)map0.get("render_in_browser");
        if(!TextUtils.isEmpty(s)) {
            try {
                this.zzfdn.zzbn(Boolean.parseBoolean(s));
            }
            catch(Exception unused_ex) {
                throw new IllegalStateException("Invalid render_in_browser state");
            }
        }
    }
}

