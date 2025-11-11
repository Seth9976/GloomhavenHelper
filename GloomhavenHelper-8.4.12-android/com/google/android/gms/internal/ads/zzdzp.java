package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzdzp extends zzdxh implements zzdzs, RandomAccess {
    private static final zzdzp zzhuk;
    private static final zzdzs zzhul;
    private final List zzhum;

    static {
        zzdzp zzdzp0 = new zzdzp();
        zzdzp.zzhuk = zzdzp0;
        zzdzp0.zzban();
        zzdzp.zzhul = zzdzp.zzhuk;
    }

    public zzdzp() {
        this(10);
    }

    public zzdzp(int v) {
        this(new ArrayList(v));
    }

    private zzdzp(ArrayList arrayList0) {
        this.zzhum = arrayList0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final void add(int v, Object object0) {
        this.zzbao();
        this.zzhum.add(v, ((String)object0));
        ++this.modCount;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean add(Object object0) {
        return super.add(object0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean addAll(int v, Collection collection0) {
        this.zzbao();
        if(collection0 instanceof zzdzs) {
            collection0 = ((zzdzs)collection0).zzbdu();
        }
        boolean z = this.zzhum.addAll(v, collection0);
        ++this.modCount;
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean addAll(Collection collection0) {
        return this.addAll(this.size(), collection0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final void clear() {
        this.zzbao();
        this.zzhum.clear();
        ++this.modCount;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean equals(Object object0) {
        return super.equals(object0);
    }

    @Override
    public final Object get(int v) {
        Object object0 = this.zzhum.get(v);
        if(object0 instanceof String) {
            return (String)object0;
        }
        if(object0 instanceof zzdxn) {
            String s = ((zzdxn)object0).zzbas();
            if(((zzdxn)object0).zzbat()) {
                this.zzhum.set(v, s);
            }
            return s;
        }
        String s1 = zzdzc.zzy(((byte[])object0));
        if(zzdzc.zzx(((byte[])object0))) {
            this.zzhum.set(v, s1);
        }
        return s1;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final int hashCode() {
        return super.hashCode();
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final Object remove(int v) {
        this.zzbao();
        Object object0 = this.zzhum.remove(v);
        ++this.modCount;
        return zzdzp.zzap(object0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean remove(Object object0) {
        return super.remove(object0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean removeAll(Collection collection0) {
        return super.removeAll(collection0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean retainAll(Collection collection0) {
        return super.retainAll(collection0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final Object set(int v, Object object0) {
        this.zzbao();
        return zzdzp.zzap(this.zzhum.set(v, ((String)object0)));
    }

    @Override
    public final int size() {
        return this.zzhum.size();
    }

    @Override  // com.google.android.gms.internal.ads.zzdzs
    public final void zzaj(zzdxn zzdxn0) {
        this.zzbao();
        this.zzhum.add(zzdxn0);
        ++this.modCount;
    }

    private static String zzap(Object object0) {
        if(object0 instanceof String) {
            return (String)object0;
        }
        return object0 instanceof zzdxn ? ((zzdxn)object0).zzbas() : zzdzc.zzy(((byte[])object0));
    }

    @Override  // com.google.android.gms.internal.ads.zzdxh
    public final boolean zzbam() {
        return super.zzbam();
    }

    @Override  // com.google.android.gms.internal.ads.zzdzs
    public final List zzbdu() {
        return Collections.unmodifiableList(this.zzhum);
    }

    @Override  // com.google.android.gms.internal.ads.zzdzs
    public final zzdzs zzbdv() {
        return this.zzbam() ? new zzeca(this) : this;
    }

    @Override  // com.google.android.gms.internal.ads.zzdzi
    public final zzdzi zzfd(int v) {
        if(v < this.size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList0 = new ArrayList(v);
        arrayList0.addAll(this.zzhum);
        return new zzdzp(arrayList0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdzs
    public final Object zzgm(int v) {
        return this.zzhum.get(v);
    }
}

