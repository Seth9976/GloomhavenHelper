package com.google.android.gms.internal.ads;

import android.webkit.ValueCallback;

final class zzqo implements ValueCallback {
    private final zzql zzbqm;

    zzqo(zzql zzql0) {
        this.zzbqm = zzql0;
        super();
    }

    @Override  // android.webkit.ValueCallback
    public final void onReceiveValue(Object object0) {
        this.zzbqm.zzbqi.zza(this.zzbqm.zzbqf, this.zzbqm.zzbqg, ((String)object0), this.zzbqm.zzbqh);
    }
}

