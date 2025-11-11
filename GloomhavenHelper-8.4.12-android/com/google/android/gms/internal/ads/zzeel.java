package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Map;

public final class zzeel extends zzeeg {
    private static final zzeew zzigk;

    static {
        zzeel.zzigk = zzeem.zzbe(Collections.emptyMap());
    }

    private zzeel(Map map0) {
        super(map0);
    }

    zzeel(Map map0, zzeeo zzeeo0) {
        this(map0);
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        LinkedHashMap linkedHashMap0 = zzeei.zzhk(this.zzbgm().size());
        for(Object object0: this.zzbgm().entrySet()) {
            linkedHashMap0.put(((Map.Entry)object0).getKey(), ((zzeew)((Map.Entry)object0).getValue()).get());
        }
        return Collections.unmodifiableMap(linkedHashMap0);
    }

    public static zzeen zzhm(int v) {
        return new zzeen(v, null);
    }
}

