package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.ads.zzayx;

public final class zzo extends FrameLayout implements View.OnClickListener {
    private final ImageButton zzdjb;
    private final zzw zzdjc;

    public zzo(Context context0, zzr zzr0, @Nullable zzw zzw0) {
        super(context0);
        this.zzdjc = zzw0;
        this.setOnClickListener(this);
        this.zzdjb = new ImageButton(context0);
        this.zzdjb.setImageResource(0x1080017);
        this.zzdjb.setBackgroundColor(0);
        this.zzdjb.setOnClickListener(this);
        int v = zzayx.zzb(context0, zzr0.paddingLeft);
        int v1 = zzayx.zzb(context0, 0);
        int v2 = zzayx.zzb(context0, zzr0.paddingRight);
        int v3 = zzayx.zzb(context0, zzr0.paddingBottom);
        this.zzdjb.setPadding(v, v1, v2, v3);
        this.zzdjb.setContentDescription("Interstitial close button");
        FrameLayout.LayoutParams frameLayout$LayoutParams0 = new FrameLayout.LayoutParams(zzayx.zzb(context0, zzr0.size + zzr0.paddingLeft + zzr0.paddingRight), zzayx.zzb(context0, zzr0.size + zzr0.paddingBottom), 17);
        this.addView(this.zzdjb, frameLayout$LayoutParams0);
    }

    @Override  // android.view.View$OnClickListener
    public final void onClick(View view0) {
        zzw zzw0 = this.zzdjc;
        if(zzw0 != null) {
            zzw0.zztq();
        }
    }

    public final void zzal(boolean z) {
        if(z) {
            this.zzdjb.setVisibility(8);
            return;
        }
        this.zzdjb.setVisibility(0);
    }
}

