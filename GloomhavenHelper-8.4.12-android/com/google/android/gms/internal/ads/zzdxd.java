package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class zzdxd implements zzeah {
    protected int zzhnp;

    public zzdxd() {
        this.zzhnp = 0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeah
    public final byte[] toByteArray() {
        try {
            byte[] arr_b = new byte[this.zzbda()];
            zzdyi zzdyi0 = zzdyi.zzv(arr_b);
            this.zzb(zzdyi0);
            zzdyi0.zzbcc();
            return arr_b;
        }
        catch(IOException iOException0) {
            throw new RuntimeException("Serializing " + this.getClass().getName() + " to a " + "byte array" + " threw an IOException (should never happen).", iOException0);
        }
    }

    protected static void zza(Iterable iterable0, List list0) {
        zzdzc.checkNotNull(iterable0);
        if(iterable0 instanceof zzdzs) {
            List list1 = ((zzdzs)iterable0).zzbdu();
            int v = list0.size();
            for(Object object0: list1) {
                if(object0 == null) {
                    String s = "Element at index " + (((zzdzs)list0).size() - v) + " is null.";
                    for(int v1 = ((zzdzs)list0).size() - 1; v1 >= v; --v1) {
                        ((zzdzs)list0).remove(v1);
                    }
                    throw new NullPointerException(s);
                }
                if(object0 instanceof zzdxn) {
                    ((zzdzs)list0).zzaj(((zzdxn)object0));
                }
                else {
                    ((zzdzs)list0).add(((String)object0));
                }
            }
            return;
        }
        if(iterable0 instanceof zzeat) {
            list0.addAll(((Collection)iterable0));
            return;
        }
        if(list0 instanceof ArrayList && iterable0 instanceof Collection) {
            ((ArrayList)list0).ensureCapacity(list0.size() + ((Collection)iterable0).size());
        }
        int v2 = list0.size();
        for(Object object1: iterable0) {
            if(object1 == null) {
                String s1 = "Element at index " + (list0.size() - v2) + " is null.";
                for(int v3 = list0.size() - 1; v3 >= v2; --v3) {
                    list0.remove(v3);
                }
                throw new NullPointerException(s1);
            }
            list0.add(object1);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzeah
    public final zzdxn zzbai() {
        try {
            zzdxv zzdxv0 = zzdxn.zzfg(this.zzbda());
            this.zzb(zzdxv0.zzbba());
            return zzdxv0.zzbaz();
        }
        catch(IOException iOException0) {
            throw new RuntimeException("Serializing " + this.getClass().getName() + " to a " + "ByteString" + " threw an IOException (should never happen).", iOException0);
        }
    }

    int zzbaj() {
        throw new UnsupportedOperationException();
    }

    void zzfa(int v) {
        throw new UnsupportedOperationException();
    }
}

