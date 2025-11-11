package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.MuteThisAdListener;

public final class zzwv extends zzwt {
    private final MuteThisAdListener zzcep;

    public zzwv(MuteThisAdListener muteThisAdListener0) {
        this.zzcep = muteThisAdListener0;
    }

    @Override  // com.google.android.gms.internal.ads.zzwq
    public final void onAdMuted() {
        this.zzcep.onAdMuted();
    }
}

