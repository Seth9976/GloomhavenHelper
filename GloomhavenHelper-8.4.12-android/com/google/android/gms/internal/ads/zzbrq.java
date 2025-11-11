package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.Set;

public final class zzbrq extends zzbtk implements zzafe {
    private Bundle zzfkn;

    public zzbrq(Set set0) {
        super(set0);
        this.zzfkn = new Bundle();
    }

    public final Bundle getAdMetadata() {
        synchronized(this) {
            return new Bundle(this.zzfkn);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzafe
    public final void zza(String s, Bundle bundle0) {
        synchronized(this) {
            this.zzfkn.putAll(bundle0);
            this.zza(zzbrp.zzfkj);
        }
    }
}

