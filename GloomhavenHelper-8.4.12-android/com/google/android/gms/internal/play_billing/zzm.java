package com.google.android.gms.internal.play_billing;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public abstract class zzm extends AbstractCollection implements Serializable {
    private static final Object[] zza;

    static {
        zzm.zza = new Object[0];
    }

    @Override
    @Deprecated
    public final boolean add(Object object0) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final boolean addAll(Collection collection0) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        return this.zzd();
    }

    @Override
    @Deprecated
    public final boolean remove(Object object0) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final boolean removeAll(Collection collection0) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final boolean retainAll(Collection collection0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final Object[] toArray() {
        return this.toArray(zzm.zza);
    }

    @Override
    public final Object[] toArray(Object[] arr_object) {
        if(arr_object == null) {
            throw null;
        }
        int v = this.size();
        if(arr_object.length < v) {
            Object[] arr_object1 = this.zze();
            if(arr_object1 == null) {
                arr_object = (Object[])Array.newInstance(arr_object.getClass().getComponentType(), v);
                this.zza(arr_object, 0);
                return arr_object;
            }
            return Arrays.copyOfRange(arr_object1, this.zzc(), this.zzb(), arr_object.getClass());
        }
        else if(arr_object.length > v) {
            arr_object[v] = null;
        }
        this.zza(arr_object, 0);
        return arr_object;
    }

    int zza(Object[] arr_object, int v) {
        throw null;
    }

    int zzb() {
        throw null;
    }

    int zzc() {
        throw null;
    }

    public abstract zzr zzd();

    @NullableDecl
    Object[] zze() {
        throw null;
    }
}

