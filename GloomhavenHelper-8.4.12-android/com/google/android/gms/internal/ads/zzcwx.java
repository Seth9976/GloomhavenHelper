package com.google.android.gms.internal.ads;

import android.os.Bundle;

public final class zzcwx implements zzcyb {
    private String zzdkd;

    public zzcwx(String s) {
        this.zzdkd = s;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        ((Bundle)object0).putString("request_id", this.zzdkd);
    }
}

