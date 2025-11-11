package com.google.android.gms.internal.ads;

import java.util.Set;
import javax.annotation.concurrent.GuardedBy;

public final class zzbvk extends zzbtk {
    @GuardedBy("this")
    private boolean zzein;

    protected zzbvk(Set set0) {
        super(set0);
    }

    public final void onVideoEnd() {
        this.zza(zzbvm.zzfkj);
    }

    public final void onVideoPause() {
        this.zza(zzbvn.zzfkj);
    }

    public final void onVideoPlay() {
        synchronized(this) {
            if(!this.zzein) {
                this.zza(zzbvo.zzfkj);
                this.zzein = true;
            }
            this.zza(zzbvr.zzfkj);
        }
    }

    public final void onVideoStart() {
        synchronized(this) {
            this.zza(zzbvp.zzfkj);
            this.zzein = true;
        }
    }
}

