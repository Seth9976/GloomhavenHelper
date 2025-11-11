package com.google.android.gms.internal.ads;

import android.os.Bundle;

public final class zzcwc implements zzcyb {
    private final zzdeg zzfdn;

    public zzcwc(zzdeg zzdeg0) {
        this.zzfdn = zzdeg0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        zzdeg zzdeg0 = this.zzfdn;
        if(zzdeg0 != null) {
            ((Bundle)object0).putBoolean("render_in_browser", zzdeg0.zzaqt());
            ((Bundle)object0).putBoolean("disable_ml", this.zzfdn.zzaqu());
        }
    }
}

