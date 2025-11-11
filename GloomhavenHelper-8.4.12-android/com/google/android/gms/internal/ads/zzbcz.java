package com.google.android.gms.internal.ads;

import java.util.HashMap;

final class zzbcz implements Runnable {
    private final String zzdvm;
    private final String zzeeh;
    private final int zzeei;
    private final int zzeej;
    private final boolean zzeek;
    private final zzbda zzeel;

    zzbcz(zzbda zzbda0, String s, String s1, int v, int v1, boolean z) {
        this.zzeel = zzbda0;
        this.zzdvm = s;
        this.zzeeh = s1;
        this.zzeei = v;
        this.zzeej = v1;
        this.zzeek = false;
        super();
    }

    @Override
    public final void run() {
        HashMap hashMap0 = new HashMap();
        hashMap0.put("event", "precacheProgress");
        hashMap0.put("src", this.zzdvm);
        hashMap0.put("cachedSrc", this.zzeeh);
        hashMap0.put("bytesLoaded", Integer.toString(this.zzeei));
        hashMap0.put("totalBytes", Integer.toString(this.zzeej));
        hashMap0.put("cacheReady", (this.zzeek ? "1" : "0"));
        this.zzeel.zza("onPrecacheEvent", hashMap0);
    }
}

