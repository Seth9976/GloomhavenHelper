package com.google.android.gms.internal.ads;

import android.os.Bundle;

public final class zzcvu implements zzcyb {
    private final String zzdkt;
    private final boolean zzdkv;

    public zzcvu(String s, boolean z) {
        this.zzdkt = s;
        this.zzdkv = z;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        ((Bundle)object0).putString("gct", this.zzdkt);
        if(this.zzdkv) {
            ((Bundle)object0).putString("de", "1");
        }
    }
}

