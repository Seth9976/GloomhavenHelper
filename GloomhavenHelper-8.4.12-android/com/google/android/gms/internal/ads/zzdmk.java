package com.google.android.gms.internal.ads;

import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class zzdmk {
    static boolean zza(Set set0, @NullableDecl Object object0) {
        if(set0 == object0) {
            return true;
        }
        if(object0 instanceof Set) {
            Set set1 = (Set)object0;
            try {
                if(set0.size() == set1.size() && set0.containsAll(set1)) {
                    return true;
                }
            }
            catch(NullPointerException | ClassCastException unused_ex) {
            }
            return false;
        }
        return false;
    }

    static int zzg(Set set0) {
        int v = 0;
        for(Object object0: set0) {
            v += (object0 == null ? 0 : object0.hashCode());
        }
        return v;
    }
}

