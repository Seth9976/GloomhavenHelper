package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.Set;

public final class zzcgu implements zzeej {
    private final zzeew zzfex;
    private final zzeew zzfvy;
    private final zzeew zzfvz;

    private zzcgu(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        this.zzfvy = zzeew0;
        this.zzfvz = zzeew1;
        this.zzfex = zzeew2;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return new zzcgs(((zzcgq)this.zzfvy.get()), ((Set)this.zzfvz.get()), ((Clock)this.zzfex.get()));
    }

    public static zzcgu zzm(zzeew zzeew0, zzeew zzeew1, zzeew zzeew2) {
        return new zzcgu(zzeew0, zzeew1, zzeew2);
    }
}

