package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;

class zzdlp extends zzdls {
    int size;
    Object[] zzhab;
    boolean zzhac;

    zzdlp(int v) {
        zzdll.zze(v, "initialCapacity");
        this.zzhab = new Object[v];
        this.size = 0;
    }

    public zzdlp zzae(Object object0) {
        zzdlg.checkNotNull(object0);
        this.zzdw(this.size + 1);
        int v = this.size;
        this.size = v + 1;
        this.zzhab[v] = object0;
        return this;
    }

    @Override  // com.google.android.gms.internal.ads.zzdls
    public zzdls zzaf(Object object0) {
        return this.zzae(object0);
    }

    private final void zzdw(int v) {
        Object[] arr_object = this.zzhab;
        if(arr_object.length < v) {
            if(v < 0) {
                throw new AssertionError("cannot store more than MAX_VALUE elements");
            }
            int v1 = arr_object.length + (arr_object.length >> 1) + 1 >= v ? arr_object.length + (arr_object.length >> 1) + 1 : Integer.highestOneBit(v - 1) << 1;
            if(v1 < 0) {
                v1 = 0x7FFFFFFF;
            }
            this.zzhab = Arrays.copyOf(arr_object, v1);
            this.zzhac = false;
            return;
        }
        if(this.zzhac) {
            this.zzhab = (Object[])arr_object.clone();
            this.zzhac = false;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdls
    public zzdls zze(Iterable iterable0) {
        if(iterable0 instanceof Collection) {
            this.zzdw(this.size + ((Collection)iterable0).size());
            if(((Collection)iterable0) instanceof zzdlq) {
                this.size = ((zzdlq)(((Collection)iterable0))).zza(this.zzhab, this.size);
                return this;
            }
        }
        super.zze(iterable0);
        return this;
    }
}

