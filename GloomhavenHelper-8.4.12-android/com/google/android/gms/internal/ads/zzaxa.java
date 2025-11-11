package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.WebResourceResponse;
import com.google.android.gms.ads.internal.zzq;
import java.io.InputStream;
import java.util.Map;

@TargetApi(21)
public class zzaxa extends zzawx {
    @Override  // com.google.android.gms.internal.ads.zzawu
    public final WebResourceResponse zza(String s, String s1, int v, String s2, Map map0, InputStream inputStream0) {
        return new WebResourceResponse(s, s1, v, s2, map0, inputStream0);
    }

    @Override  // com.google.android.gms.internal.ads.zzawu
    public final zzbdy zza(zzbdv zzbdv0, zzsn zzsn0, boolean z) {
        return new zzbez(zzbdv0, zzsn0, z);
    }

    @Override  // com.google.android.gms.internal.ads.zzawu
    public final CookieManager zzbg(Context context0) {
        if(zzaxa.zzwv()) {
            return null;
        }
        try {
            return CookieManager.getInstance();
        }
        catch(Throwable throwable0) {
            zzawf.zzc("Failed to obtain CookieManager.", throwable0);
            zzq.zzkz().zza(throwable0, "ApiLevelUtil.getCookieManager");
            return null;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzawu
    public final int zzww() {
        return 0x1030226;
    }
}

