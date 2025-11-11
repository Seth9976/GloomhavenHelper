package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;

@TargetApi(18)
public class zzawy extends zzawv {
    @Override  // com.google.android.gms.internal.ads.zzawu
    public boolean isAttachedToWindow(View view0) {
        return super.isAttachedToWindow(view0) || view0.getWindowId() != null;
    }

    @Override  // com.google.android.gms.internal.ads.zzawu
    public final int zzwt() {
        return 14;
    }

    @Override  // com.google.android.gms.internal.ads.zzawu
    public final long zzwx() {
        return ((Boolean)zzvh.zzpd().zzd(zzzx.zzcmp)).booleanValue() ? new StatFs(Environment.getDataDirectory().getAbsolutePath()).getAvailableBytes() / 0x400L : -1L;
    }
}

