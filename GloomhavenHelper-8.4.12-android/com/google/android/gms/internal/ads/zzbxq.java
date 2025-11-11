package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

public final class zzbxq implements zzeej {
    private final zzeew zzfgl;

    public zzbxq(zzeew zzeew0) {
        this.zzfgl = zzeew0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzbxq.zza(((zzbzg)this.zzfgl.get()));
    }

    public static Set zza(zzbzg zzbzg0) {
        return zzbzg0.zzakw() == null ? ((Set)zzeep.zza(Collections.emptySet(), "Cannot return null from a non-@Nullable @Provides method")) : ((Set)zzeep.zza(Collections.singleton("banner"), "Cannot return null from a non-@Nullable @Provides method"));
    }
}

