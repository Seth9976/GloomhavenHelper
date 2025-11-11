package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class zzdl implements zzgn {
    protected int zza;

    public zzdl() {
        this.zza = 0;
    }

    protected static void zza(Iterable iterable0, List list0) {
        zzfe.zza(iterable0);
        if(iterable0 instanceof zzfu) {
            List list1 = ((zzfu)iterable0).zzb();
            int v = list0.size();
            for(Object object0: list1) {
                if(object0 == null) {
                    String s = "Element at index " + (((zzfu)list0).size() - v) + " is null.";
                    for(int v1 = ((zzfu)list0).size() - 1; v1 >= v; --v1) {
                        ((zzfu)list0).remove(v1);
                    }
                    throw new NullPointerException(s);
                }
                if(object0 instanceof zzdv) {
                    ((zzfu)list0).zza(((zzdv)object0));
                }
                else {
                    ((zzfu)list0).add(((String)object0));
                }
            }
            return;
        }
        if(iterable0 instanceof zzgz) {
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

    @Override  // com.google.android.gms.internal.measurement.zzgn
    public final zzdv zzbg() {
        try {
            zzed zzed0 = zzdv.zzc(this.zzbl());
            this.zza(zzed0.zzb());
            return zzed0.zza();
        }
        catch(IOException iOException0) {
            throw new RuntimeException("Serializing " + this.getClass().getName() + " to a " + "ByteString" + " threw an IOException (should never happen).", iOException0);
        }
    }

    public final byte[] zzbh() {
        try {
            byte[] arr_b = new byte[this.zzbl()];
            zzek zzek0 = zzek.zza(arr_b);
            this.zza(zzek0);
            zzek0.zzb();
            return arr_b;
        }
        catch(IOException iOException0) {
            throw new RuntimeException("Serializing " + this.getClass().getName() + " to a " + "byte array" + " threw an IOException (should never happen).", iOException0);
        }
    }

    int zzbi() {
        throw new UnsupportedOperationException();
    }

    void zzc(int v) {
        throw new UnsupportedOperationException();
    }
}

