package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzq;

public final class zzarq implements zzarb {
    private zzakc zzdop;
    private zzakc zzdou;

    public zzarq(Context context0) {
        this.zzdou = zzq.zzli().zza(context0, zzazo.zzxr()).zza("google.afma.request.getAdDictionary", zzakj.zzdbu, zzakj.zzdbu);
        this.zzdop = zzq.zzli().zza(context0, zzazo.zzxr()).zza("google.afma.sdkConstants.getSdkConstants", zzakj.zzdbu, zzakj.zzdbu);
    }

    @Override  // com.google.android.gms.internal.ads.zzarb
    public final zzakc zzue() {
        return this.zzdop;
    }
}

