package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

public final class zzeei {
    public static List zzhi(int v) {
        return v == 0 ? Collections.emptyList() : new ArrayList(v);
    }

    static HashSet zzhj(int v) {
        return new HashSet(zzeei.zzhl(v));
    }

    public static LinkedHashMap zzhk(int v) {
        return new LinkedHashMap(zzeei.zzhl(v));
    }

    private static int zzhl(int v) {
        if(v < 3) {
            return v + 1;
        }
        return v >= 0x40000000 ? 0x7FFFFFFF : ((int)(((float)v) / 0.75f + 1.0f));
    }
}

