package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

@TargetApi(19)
public class zzawx extends zzawy {
    @Override  // com.google.android.gms.internal.ads.zzawy
    public final boolean isAttachedToWindow(View view0) {
        return view0.isAttachedToWindow();
    }

    @Override  // com.google.android.gms.internal.ads.zzawu
    public final ViewGroup.LayoutParams zzwu() {
        return new ViewGroup.LayoutParams(-1, -1);
    }
}

