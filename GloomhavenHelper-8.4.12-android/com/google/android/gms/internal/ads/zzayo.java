package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzq;

public final class zzayo extends zzawb {
    private final String url;
    private final zzazl zzdwb;

    public zzayo(Context context0, String s, String s1) {
        this(s1, zzq.zzkv().zzr(context0, s));
    }

    private zzayo(String s, String s1) {
        this.zzdwb = new zzazl(s1);
        this.url = s;
    }

    @Override  // com.google.android.gms.internal.ads.zzawb
    public final void zztz() {
        this.zzdwb.zzeo(this.url);
    }
}

