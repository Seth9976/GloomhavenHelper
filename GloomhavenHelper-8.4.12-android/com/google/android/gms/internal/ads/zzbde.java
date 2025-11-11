package com.google.android.gms.internal.ads;

import java.util.HashMap;

final class zzbde implements Runnable {
    private final String zzdvm;
    private final String zzeeh;
    private final int zzeej;
    private final zzbda zzeel;

    zzbde(zzbda zzbda0, String s, String s1, int v) {
        this.zzeel = zzbda0;
        this.zzdvm = s;
        this.zzeeh = s1;
        this.zzeej = v;
        super();
    }

    @Override
    public final void run() {
        HashMap hashMap0 = new HashMap();
        hashMap0.put("event", "precacheComplete");
        hashMap0.put("src", this.zzdvm);
        hashMap0.put("cachedSrc", this.zzeeh);
        hashMap0.put("totalBytes", Integer.toString(this.zzeej));
        this.zzeel.zza("onPrecacheEvent", hashMap0);
    }
}

