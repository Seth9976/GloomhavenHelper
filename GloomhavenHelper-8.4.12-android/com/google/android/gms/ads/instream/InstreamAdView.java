package com.google.android.gms.ads.instream;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.Preconditions;

public final class InstreamAdView extends FrameLayout {
    public InstreamAdView(Context context0) {
        super(context0);
        Preconditions.checkNotNull(context0, "Context cannot be null");
    }

    public InstreamAdView(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    public InstreamAdView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
    }

    public final void setInstreamAd(InstreamAd instreamAd0) {
        if(instreamAd0 != null) {
            instreamAd0.zza(this);
        }
    }
}

