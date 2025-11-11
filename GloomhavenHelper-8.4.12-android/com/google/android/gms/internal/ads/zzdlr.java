package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public abstract class zzdlr extends zzdlq implements List, RandomAccess {
    private static final zzdml zzhae;

    static {
        zzdlr.zzhae = new zzdlt(zzdmb.zzhap, 0);
    }

    @Override
    @Deprecated
    public final void add(int v, Object object0) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final boolean addAll(int v, Collection collection0) {
        throw new UnsupportedOperationException();
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    public boolean contains(@NullableDecl Object object0) {
        return this.indexOf(object0) >= 0;
    }

    @Override
    public boolean equals(@NullableDecl Object object0) {
        if(object0 == zzdlg.checkNotNull(this)) {
            return true;
        }
        if(object0 instanceof List) {
            int v = this.size();
            if(v == ((List)object0).size()) {
                if(!(this instanceof RandomAccess) || !(((List)object0) instanceof RandomAccess)) {
                    int v2 = this.size();
                    Iterator iterator0 = ((List)object0).iterator();
                    int v3 = 0;
                    while(v3 < v2) {
                        if(!iterator0.hasNext()) {
                            return false;
                        }
                        Object object1 = this.get(v3);
                        ++v3;
                        Object object2 = iterator0.next();
                        if(!zzdlb.equal(object1, object2)) {
                            return false;
                        }
                    }
                    return !iterator0.hasNext();
                }
                else {
                    for(int v1 = 0; v1 < v; ++v1) {
                        if(!zzdlb.equal(this.get(v1), ((List)object0).get(v1))) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int v = this.size();
        int v1 = 1;
        for(int v2 = 0; v2 < v; ++v2) {
            v1 = v1 * 0x1F + this.get(v2).hashCode();
        }
        return v1;
    }

    @Override
    public int indexOf(@NullableDecl Object object0) {
        if(object0 == null) {
            return -1;
        }
        if(this instanceof RandomAccess) {
            int v = this.size();
            int v1 = 0;
            if(object0 == null) {
                while(v1 < v) {
                    if(this.get(v1) == null) {
                        return v1;
                    }
                    ++v1;
                }
                return -1;
            }
            while(v1 < v) {
                if(object0.equals(this.get(v1))) {
                    return v1;
                }
                ++v1;
            }
            return -1;
        }
        ListIterator listIterator0 = this.listIterator();
        while(listIterator0.hasNext()) {
            if(zzdlb.equal(object0, listIterator0.next())) {
                return listIterator0.previousIndex();
            }
            if(false) {
                break;
            }
        }
        return -1;
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    public Iterator iterator() {
        return this.zzatw();
    }

    @Override
    public int lastIndexOf(@NullableDecl Object object0) {
        if(object0 == null) {
            return -1;
        }
        if(this instanceof RandomAccess) {
            if(object0 == null) {
                for(int v = this.size() - 1; v >= 0; --v) {
                    if(this.get(v) == null) {
                        return v;
                    }
                }
                return -1;
            }
            for(int v1 = this.size() - 1; v1 >= 0; --v1) {
                if(object0.equals(this.get(v1))) {
                    return v1;
                }
            }
            return -1;
        }
        ListIterator listIterator0 = this.listIterator(this.size());
        while(listIterator0.hasPrevious()) {
            if(zzdlb.equal(object0, listIterator0.previous())) {
                return listIterator0.nextIndex();
            }
            if(false) {
                break;
            }
        }
        return -1;
    }

    @Override
    public ListIterator listIterator() {
        return (zzdml)this.listIterator(0);
    }

    @Override
    public ListIterator listIterator(int v) {
        zzdlg.zzt(v, this.size());
        return this.isEmpty() ? zzdlr.zzhae : new zzdlt(this, v);
    }

    @Override
    @Deprecated
    public final Object remove(int v) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final Object set(int v, Object object0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List subList(int v, int v1) {
        return this.zzu(v, v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    int zza(Object[] arr_object, int v) {
        int v1 = this.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            arr_object[v + v2] = this.get(v2);
        }
        return v + v1;
    }

    public static zzdlr zzag(Object object0) {
        Object[] arr_object = {object0};
        for(int v = 0; v <= 0; ++v) {
            zzdmc.zzd(arr_object[0], 0);
        }
        return zzdlr.zzb(arr_object, 1);
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    public final zzdmm zzatw() {
        return (zzdml)this.listIterator();
    }

    @Override  // com.google.android.gms.internal.ads.zzdlq
    public final zzdlr zzaua() {
        return this;
    }

    public static zzdlr zzauc() {
        return zzdmb.zzhap;
    }

    public static zzdlr zzb(Object[] arr_object) {
        if(arr_object.length == 0) {
            return zzdmb.zzhap;
        }
        Object[] arr_object1 = (Object[])arr_object.clone();
        for(int v = 0; v < arr_object1.length; ++v) {
            zzdmc.zzd(arr_object1[v], v);
        }
        return zzdlr.zzb(arr_object1, arr_object1.length);
    }

    static zzdlr zzb(Object[] arr_object, int v) {
        return v == 0 ? zzdmb.zzhap : new zzdmb(arr_object, v);
    }

    static zzdlr zzc(Object[] arr_object) {
        return zzdlr.zzb(arr_object, arr_object.length);
    }

    public static zzdlr zzf(Iterable iterable0) {
        zzdlg.checkNotNull(iterable0);
        if(iterable0 instanceof Collection) {
            if(((Collection)iterable0) instanceof zzdlq) {
                zzdlr zzdlr0 = ((zzdlq)(((Collection)iterable0))).zzaua();
                if(zzdlr0.zzaub()) {
                    Object[] arr_object = zzdlr0.toArray();
                    return zzdlr.zzb(arr_object, arr_object.length);
                }
                return zzdlr0;
            }
            Object[] arr_object1 = ((Collection)iterable0).toArray();
            for(int v = 0; v < arr_object1.length; ++v) {
                zzdmc.zzd(arr_object1[v], v);
            }
            return zzdlr.zzb(arr_object1, arr_object1.length);
        }
        Iterator iterator0 = iterable0.iterator();
        if(!iterator0.hasNext()) {
            return zzdmb.zzhap;
        }
        Object object0 = iterator0.next();
        if(!iterator0.hasNext()) {
            return zzdlr.zzag(object0);
        }
        zzdlu zzdlu0 = (zzdlu)((zzdlu)new zzdlu().zzaf(object0)).zza(iterator0);
        zzdlu0.zzhac = true;
        return zzdlr.zzb(zzdlu0.zzhab, zzdlu0.size);
    }

    public zzdlr zzu(int v, int v1) {
        zzdlg.zzf(v, v1, this.size());
        int v2 = v1 - v;
        if(v2 == this.size()) {
            return this;
        }
        return v2 == 0 ? zzdmb.zzhap : new zzdlw(this, v, v2);
    }
}

