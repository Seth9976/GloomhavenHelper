package com.google.android.gms.internal.ads;

import java.util.Set;

public final class zzbqp extends zzbtk {
    private boolean zzfkk;

    public zzbqp(Set set0) {
        super(set0);
        this.zzfkk = false;
    }

    public final void onAdImpression() {
        synchronized(this) {
            if(!this.zzfkk) {
                this.zza(zzbqs.zzfkj);
                this.zzfkk = true;
            }
        }
    }
}

