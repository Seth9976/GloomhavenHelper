package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.google.android.gms.ads.internal.zzq;
import java.util.Map;

public final class zzbjm implements zzbjj {
    private final Context zzur;

    public zzbjm(Context context0) {
        this.zzur = context0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbjj
    public final void zzk(Map map0) {
        String s = (String)map0.get("cookie");
        if(!TextUtils.isEmpty(s)) {
            CookieManager cookieManager0 = zzq.zzkx().zzbg(this.zzur);
            if(cookieManager0 != null) {
                cookieManager0.setCookie("googleads.g.doubleclick.net", s);
            }
        }
    }
}

