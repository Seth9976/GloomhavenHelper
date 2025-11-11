package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;

public final class zzuo extends zzwe {
    private final AppEventListener zzbkr;

    public zzuo(AppEventListener appEventListener0) {
        this.zzbkr = appEventListener0;
    }

    public final AppEventListener getAppEventListener() {
        return this.zzbkr;
    }

    @Override  // com.google.android.gms.internal.ads.zzwf
    public final void onAppEvent(String s, String s1) {
        this.zzbkr.onAppEvent(s, s1);
    }
}

