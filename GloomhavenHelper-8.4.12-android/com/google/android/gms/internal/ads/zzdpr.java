package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class zzdpr {
    private static final Charset UTF_8;
    private final Class zzhdx;
    private ConcurrentMap zzhei;
    private zzdpq zzhej;

    static {
        zzdpr.UTF_8 = Charset.forName("UTF-8");
    }

    private zzdpr(Class class0) {
        this.zzhei = new ConcurrentHashMap();
        this.zzhdx = class0;
    }

    public static zzdpr zza(Class class0) {
        return new zzdpr(class0);
    }

    public final zzdpq zza(Object object0, zza zzdum$zza0) throws GeneralSecurityException {
        byte[] arr_b;
        if(zzdum$zza0.zzavi() != zzdug.zzhjk) {
            throw new GeneralSecurityException("only ENABLED key is allowed");
        }
        switch(zzdpd.zzhdu[zzdum$zza0.zzavj().ordinal()]) {
            case 1: 
            case 2: {
                arr_b = ByteBuffer.allocate(5).put(0).putInt(zzdum$zza0.zzaza()).array();
                break;
            }
            case 3: {
                arr_b = ByteBuffer.allocate(5).put(1).putInt(zzdum$zza0.zzaza()).array();
                break;
            }
            case 4: {
                arr_b = zzdpa.zzhdt;
                break;
            }
            default: {
                throw new GeneralSecurityException("unknown output prefix type");
            }
        }
        zzdpq zzdpq0 = new zzdpq(object0, arr_b, zzdum$zza0.zzavi(), zzdum$zza0.zzavj(), zzdum$zza0.zzaza());
        ArrayList arrayList0 = new ArrayList();
        arrayList0.add(zzdpq0);
        String s = new String(zzdpq0.zzavk(), zzdpr.UTF_8);
        List list0 = (List)this.zzhei.put(s, Collections.unmodifiableList(arrayList0));
        if(list0 != null) {
            ArrayList arrayList1 = new ArrayList();
            arrayList1.addAll(list0);
            arrayList1.add(zzdpq0);
            this.zzhei.put(s, Collections.unmodifiableList(arrayList1));
        }
        return zzdpq0;
    }

    public final void zza(zzdpq zzdpq0) {
        if(zzdpq0 == null) {
            throw new IllegalArgumentException("the primary entry must be non-null");
        }
        if(zzdpq0.zzavi() != zzdug.zzhjk) {
            throw new IllegalArgumentException("the primary entry has to be ENABLED");
        }
        String s = new String(zzdpq0.zzavk(), zzdpr.UTF_8);
        List list0 = (List)this.zzhei.get(s);
        if(list0 == null) {
            list0 = Collections.emptyList();
        }
        if(list0.isEmpty()) {
            throw new IllegalArgumentException("the primary entry cannot be set to an entry which is not held by this primitive set");
        }
        this.zzhej = zzdpq0;
    }

    public final Class zzauy() {
        return this.zzhdx;
    }

    public final zzdpq zzavl() {
        return this.zzhej;
    }
}

