package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public final class zzees implements zzeej {
    private static final zzeej zzigm;
    private final List zzign;
    private final List zzigo;

    static {
        zzees.zzigm = zzeem.zzbe(Collections.emptySet());
    }

    private zzees(List list0, List list1) {
        this.zzign = list0;
        this.zzigo = list1;
    }

    zzees(List list0, List list1, zzeer zzeer0) {
        this(list0, list1);
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        ArrayList arrayList0 = new ArrayList(this.zzigo.size());
        int v = this.zzigo.size();
        int v2 = this.zzign.size();
        for(int v3 = 0; v3 < v; ++v3) {
            Collection collection0 = (Collection)((zzeew)this.zzigo.get(v3)).get();
            v2 += collection0.size();
            arrayList0.add(collection0);
        }
        HashSet hashSet0 = zzeei.zzhj(v2);
        int v4 = this.zzign.size();
        for(int v5 = 0; v5 < v4; ++v5) {
            hashSet0.add(zzeep.checkNotNull(((zzeew)this.zzign.get(v5)).get()));
        }
        int v6 = arrayList0.size();
        for(int v1 = 0; v1 < v6; ++v1) {
            for(Object object0: ((Collection)arrayList0.get(v1))) {
                hashSet0.add(zzeep.checkNotNull(object0));
            }
        }
        return Collections.unmodifiableSet(hashSet0);
    }

    public static zzeeu zzas(int v, int v1) {
        return new zzeeu(v, v1, null);
    }
}

