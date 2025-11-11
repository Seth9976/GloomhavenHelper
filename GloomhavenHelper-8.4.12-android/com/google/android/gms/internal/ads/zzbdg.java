package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.HashMap;

final class zzbdg implements Runnable {
    private final String val$message;
    private final String zzdvm;
    private final String zzeeh;
    private final zzbda zzeel;
    private final String zzeer;

    zzbdg(zzbda zzbda0, String s, String s1, String s2, String s3) {
        this.zzeel = zzbda0;
        this.zzdvm = s;
        this.zzeeh = s1;
        this.zzeer = s2;
        this.val$message = s3;
        super();
    }

    @Override
    public final void run() {
        HashMap hashMap0 = new HashMap();
        hashMap0.put("event", "precacheCanceled");
        hashMap0.put("src", this.zzdvm);
        if(!TextUtils.isEmpty(this.zzeeh)) {
            hashMap0.put("cachedSrc", this.zzeeh);
        }
        hashMap0.put("type", zzbda.zza(this.zzeel, this.zzeer));
        hashMap0.put("reason", this.zzeer);
        if(!TextUtils.isEmpty(this.val$message)) {
            hashMap0.put("message", this.val$message);
        }
        this.zzeel.zza("onPrecacheEvent", hashMap0);
    }
}

