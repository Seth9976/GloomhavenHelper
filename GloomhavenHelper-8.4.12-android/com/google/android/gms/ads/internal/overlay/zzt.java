package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.internal.ads.zzawf;

public final class zzt extends zze {
    public zzt(Activity activity0) {
        super(activity0);
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zze
    public final void onCreate(Bundle bundle0) {
        zzawf.zzee("AdOverlayParcel is null or does not contain valid overlay type.");
        this.zzdie = 3;
        this.zzzo.finish();
    }
}

