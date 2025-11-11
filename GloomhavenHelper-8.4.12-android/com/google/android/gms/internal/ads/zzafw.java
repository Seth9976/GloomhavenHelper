package com.google.android.gms.internal.ads;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import java.util.HashMap;
import java.util.Map;

final class zzafw implements zzafz {
    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        DisplayMetrics displayMetrics0 = zzawo.zza(((WindowManager)((zzbdv)object0).getContext().getSystemService("window")));
        int v = displayMetrics0.widthPixels;
        int v1 = displayMetrics0.heightPixels;
        int[] arr_v = new int[2];
        HashMap hashMap0 = new HashMap();
        ((View)(((zzbdv)object0))).getLocationInWindow(arr_v);
        hashMap0.put("xInPixels", ((int)arr_v[0]));
        hashMap0.put("yInPixels", ((int)arr_v[1]));
        hashMap0.put("windowWidthInPixels", v);
        hashMap0.put("windowHeightInPixels", v1);
        ((zzbdv)object0).zza("locationReady", hashMap0);
        zzawf.zzfa("GET LOCATION COMPILED");
    }
}

