package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzaxg;

@VisibleForTesting
final class zzh extends RelativeLayout {
    @VisibleForTesting
    private zzaxg zzdim;
    @VisibleForTesting
    boolean zzdin;

    public zzh(Context context0, String s, String s1) {
        super(context0);
        this.zzdim = new zzaxg(context0, s);
        this.zzdim.zzx(s1);
    }

    @Override  // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent0) {
        if(!this.zzdin) {
            this.zzdim.zzd(motionEvent0);
        }
        return false;
    }
}

