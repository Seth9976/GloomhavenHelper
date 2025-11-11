package com.google.android.gms.internal.ads;

import android.os.Build.VERSION;
import java.util.HashMap;
import java.util.concurrent.Callable;

final class zzdar implements Callable {
    static final Callable zzgjg;

    static {
        zzdar.zzgjg = new zzdar();
    }

    @Override
    public final Object call() {
        HashMap hashMap0 = new HashMap();
        String s = (String)zzvh.zzpd().zzd(zzzx.zzcic);
        if(s != null && !s.isEmpty() && Build.VERSION.SDK_INT >= ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcid))))) {
            String[] arr_s = s.split(",", -1);
            for(int v = 0; v < arr_s.length; ++v) {
                String s1 = arr_s[v];
                hashMap0.put(s1, zzayv.zzes(s1));
            }
        }
        return new zzdap(hashMap0);
    }
}

