package com.google.android.gms.internal.ads;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public abstract class zzdlv implements Serializable, Map {
    private static final Map.Entry[] zzhag;
    private transient zzdly zzhah;
    private transient zzdly zzhai;
    private transient zzdlq zzhaj;

    static {
        zzdlv.zzhag = new Map.Entry[0];
    }

    @Override
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsKey(@NullableDecl Object object0) {
        return this.get(object0) != null;
    }

    @Override
    public boolean containsValue(@NullableDecl Object object0) {
        return ((zzdlq)this.values()).contains(object0);
    }

    @Override
    public Set entrySet() {
        Set set0 = this.zzhah;
        if(set0 == null) {
            zzdly zzdly0 = this.zzaud();
            this.zzhah = zzdly0;
            return zzdly0;
        }
        return set0;
    }

    @Override
    public boolean equals(@NullableDecl Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof Map ? this.entrySet().equals(((Map)object0).entrySet()) : false;
    }

    @Override
    public abstract Object get(@NullableDecl Object arg1);

    @Override
    public final Object getOrDefault(@NullableDecl Object object0, @NullableDecl Object object1) {
        Object object2 = this.get(object0);
        return object2 == null ? object1 : object2;
    }

    @Override
    public int hashCode() {
        return zzdmk.zzg(((zzdly)this.entrySet()));
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Set keySet() {
        Set set0 = this.zzhai;
        if(set0 == null) {
            zzdly zzdly0 = this.zzaue();
            this.zzhai = zzdly0;
            return zzdly0;
        }
        return set0;
    }

    @Override
    @Deprecated
    public final Object put(Object object0, Object object1) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final void putAll(Map map0) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final Object remove(Object object0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        int v = this.size();
        zzdll.zze(v, "size");
        StringBuilder stringBuilder0 = new StringBuilder(((int)Math.min(((long)v) << 3, 0x40000000L)));
        stringBuilder0.append('{');
        boolean z = true;
        for(Object object0: this.entrySet()) {
            if(!z) {
                stringBuilder0.append(", ");
            }
            z = false;
            stringBuilder0.append(((Map.Entry)object0).getKey());
            stringBuilder0.append('=');
            stringBuilder0.append(((Map.Entry)object0).getValue());
        }
        stringBuilder0.append('}');
        return stringBuilder0.toString();
    }

    @Override
    public Collection values() {
        Collection collection0 = this.zzhaj;
        if(collection0 == null) {
            zzdlq zzdlq0 = this.zzauf();
            this.zzhaj = zzdlq0;
            return zzdlq0;
        }
        return collection0;
    }

    public static zzdlv zza(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9) {
        zzdll.zzb(object0, object1);
        zzdll.zzb(object2, object3);
        zzdll.zzb(object4, object5);
        zzdll.zzb(object6, object7);
        zzdll.zzb(object8, object9);
        return zzdme.zzc(5, new Object[]{object0, object1, object2, object3, object4, object5, object6, object7, object8, object9});
    }

    abstract zzdly zzaud();

    abstract zzdly zzaue();

    abstract zzdlq zzauf();
}

