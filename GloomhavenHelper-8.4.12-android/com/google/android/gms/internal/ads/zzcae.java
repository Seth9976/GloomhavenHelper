package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;

final class zzcae implements zzafz {
    private final zzcad zzfrg;

    zzcae(zzcad zzcad0) {
        this.zzfrg = zzcad0;
    }

    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        ((zzbdv)object0).zzaaf().zza(new zzcaj(this.zzfrg, map0));
        Object object1 = map0.get("overlayHtml");
        Object object2 = map0.get("baseUrl");
        if(TextUtils.isEmpty(((String)object2))) {
            ((zzbdv)object0).loadData(((String)object1), "text/html", "UTF-8");
            return;
        }
        ((zzbdv)object0).loadDataWithBaseURL(((String)object2), ((String)object1), "text/html", "UTF-8", null);
    }
}

