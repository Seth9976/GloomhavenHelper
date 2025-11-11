package com.google.android.gms.internal.ads;

import android.content.Context;
import android.webkit.CookieManager;
import com.google.android.gms.ads.internal.zzq;
import java.util.concurrent.Callable;

final class zzcij implements Callable {
    private final Context zzcgz;

    zzcij(Context context0) {
        this.zzcgz = context0;
    }

    @Override
    public final Object call() {
        CookieManager cookieManager0 = zzq.zzkx().zzbg(this.zzcgz);
        return cookieManager0 == null ? "" : cookieManager0.getCookie("googleads.g.doubleclick.net");
    }
}

