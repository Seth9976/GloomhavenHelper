package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzp;
import java.util.Set;

public final class zzbrv extends zzbtk implements zzp {
    public zzbrv(Set set0) {
        super(set0);
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void onPause() {
        synchronized(this) {
            this.zza(zzbrx.zzfkj);
        }
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void onResume() {
        synchronized(this) {
            this.zza(zzbsa.zzfkj);
        }
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void zztj() {
        synchronized(this) {
            this.zza(zzbry.zzfkj);
        }
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void zztk() {
        synchronized(this) {
            this.zza(zzbrz.zzfkj);
        }
    }
}

