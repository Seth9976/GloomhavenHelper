package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.TreeMap;

public final class zzo {
    public final List allHeaders;
    public final byte[] data;
    public final int statusCode;
    public final Map zzab;
    public final boolean zzac;
    private final long zzad;

    private zzo(int v, byte[] arr_b, Map map0, List list0, boolean z, long v1) {
        this.statusCode = v;
        this.data = arr_b;
        this.zzab = map0;
        this.allHeaders = list0 == null ? null : Collections.unmodifiableList(list0);
        this.zzac = z;
        this.zzad = v1;
    }

    @Deprecated
    public zzo(int v, byte[] arr_b, Map map0, boolean z, long v1) {
        List list0;
        if(map0 == null) {
            list0 = null;
        }
        else if(map0.isEmpty()) {
            list0 = Collections.emptyList();
        }
        else {
            ArrayList arrayList0 = new ArrayList(map0.size());
            for(Object object0: map0.entrySet()) {
                arrayList0.add(new zzk(((String)((Map.Entry)object0).getKey()), ((String)((Map.Entry)object0).getValue())));
            }
            list0 = arrayList0;
        }
        this(v, arr_b, map0, list0, z, v1);
    }

    public zzo(int v, byte[] arr_b, boolean z, long v1, List list0) {
        Map map0;
        if(list0 == null) {
            map0 = null;
        }
        else if(list0.isEmpty()) {
            map0 = Collections.emptyMap();
        }
        else {
            TreeMap treeMap0 = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            for(Object object0: list0) {
                treeMap0.put(((zzk)object0).getName(), ((zzk)object0).getValue());
            }
            map0 = treeMap0;
        }
        this(v, arr_b, map0, list0, z, v1);
    }

    @Deprecated
    public zzo(byte[] arr_b, Map map0) {
        this(200, arr_b, map0, false, 0L);
    }
}

