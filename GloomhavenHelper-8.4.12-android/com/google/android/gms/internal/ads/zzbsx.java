package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;

final class zzbsx implements zzbtm {
    private final String zzczs;
    private final String zzczz;

    zzbsx(String s, String s1) {
        this.zzczz = s;
        this.zzczs = s1;
    }

    @Override  // com.google.android.gms.internal.ads.zzbtm
    public final void zzp(Object object0) {
        ((AppEventListener)object0).onAppEvent(this.zzczz, this.zzczs);
    }
}

