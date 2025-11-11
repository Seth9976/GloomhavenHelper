package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzq;

final class zzchw implements Runnable {
    private final zzcho zzfwv;
    private final zzazy zzfwx;

    zzchw(zzcho zzcho0, zzazy zzazy0) {
        this.zzfwv = zzcho0;
        this.zzfwx = zzazy0;
    }

    @Override
    public final void run() {
        zzazy zzazy0 = this.zzfwx;
        String s = zzq.zzkz().zzvk().zzwf().zzvq();
        if(!TextUtils.isEmpty(s)) {
            zzazy0.set(s);
            return;
        }
        zzazy0.setException(new Exception());
    }
}

