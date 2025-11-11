package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzfv extends zzdp implements zzfu, RandomAccess {
    private static final zzfv zza;
    private static final zzfu zzb;
    private final List zzc;

    static {
        zzfv zzfv0 = new zzfv();
        zzfv.zza = zzfv0;
        zzfv0.j_();
        zzfv.zzb = zzfv.zza;
    }

    public zzfv() {
        this(10);
    }

    public zzfv(int v) {
        this(new ArrayList(v));
    }

    private zzfv(ArrayList arrayList0) {
        this.zzc = arrayList0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final void add(int v, Object object0) {
        this.zzc();
        this.zzc.add(v, ((String)object0));
        ++this.modCount;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean addAll(int v, Collection collection0) {
        this.zzc();
        if(collection0 instanceof zzfu) {
            collection0 = ((zzfu)collection0).zzb();
        }
        boolean z = this.zzc.addAll(v, collection0);
        ++this.modCount;
        return z;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean addAll(Collection collection0) {
        return this.addAll(this.size(), collection0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final void clear() {
        this.zzc();
        this.zzc.clear();
        ++this.modCount;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean equals(Object object0) {
        return super.equals(object0);
    }

    @Override
    public final Object get(int v) {
        Object object0 = this.zzc.get(v);
        if(object0 instanceof String) {
            return (String)object0;
        }
        if(object0 instanceof zzdv) {
            String s = ((zzdv)object0).zzb();
            if(((zzdv)object0).zzc()) {
                this.zzc.set(v, s);
            }
            return s;
        }
        String s1 = zzfe.zzb(((byte[])object0));
        if(zzfe.zza(((byte[])object0))) {
            this.zzc.set(v, s1);
        }
        return s1;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final int hashCode() {
        return super.hashCode();
    }

    @Override  // com.google.android.gms.internal.measurement.zzfu
    public final zzfu i_() {
        return this.zza() ? new zzhz(this) : this;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final Object remove(int v) {
        this.zzc();
        Object object0 = this.zzc.remove(v);
        ++this.modCount;
        return zzfv.zza(object0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean remove(Object object0) {
        return super.remove(object0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean removeAll(Collection collection0) {
        return super.removeAll(collection0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean retainAll(Collection collection0) {
        return super.retainAll(collection0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final Object set(int v, Object object0) {
        this.zzc();
        return zzfv.zza(this.zzc.set(v, ((String)object0)));
    }

    @Override
    public final int size() {
        return this.zzc.size();
    }

    private static String zza(Object object0) {
        if(object0 instanceof String) {
            return (String)object0;
        }
        return object0 instanceof zzdv ? ((zzdv)object0).zzb() : zzfe.zzb(((byte[])object0));
    }

    @Override  // com.google.android.gms.internal.measurement.zzfk
    public final zzfk zza(int v) {
        if(v < this.size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList0 = new ArrayList(v);
        arrayList0.addAll(this.zzc);
        return new zzfv(arrayList0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzfu
    public final void zza(zzdv zzdv0) {
        this.zzc();
        this.zzc.add(zzdv0);
        ++this.modCount;
    }

    @Override  // com.google.android.gms.internal.measurement.zzdp
    public final boolean zza() {
        return super.zza();
    }

    @Override  // com.google.android.gms.internal.measurement.zzfu
    public final Object zzb(int v) {
        return this.zzc.get(v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzfu
    public final List zzb() {
        return Collections.unmodifiableList(this.zzc);
    }
}

