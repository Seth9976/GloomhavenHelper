package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbsy extends zzbtk implements zzafg {
    public zzbsy(Set set0) {
        super(set0);
    }

    @Override  // com.google.android.gms.internal.ads.zzafg
    public final void onAppEvent(String s, String s1) {
        synchronized(this) {
            this.zza(new zzbsx(s, s1));
        }
    }
}

