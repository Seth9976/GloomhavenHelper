package com.google.android.gms.internal.measurement;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzhh extends AbstractMap {
    private final int zza;
    private List zzb;
    private Map zzc;
    private boolean zzd;
    private volatile zzho zze;
    private Map zzf;
    private volatile zzhi zzg;

    private zzhh(int v) {
        this.zza = v;
        this.zzb = Collections.emptyList();
        this.zzc = Collections.emptyMap();
        this.zzf = Collections.emptyMap();
    }

    zzhh(int v, zzhg zzhg0) {
        this(v);
    }

    @Override
    public void clear() {
        this.zzf();
        if(!this.zzb.isEmpty()) {
            this.zzb.clear();
        }
        if(!this.zzc.isEmpty()) {
            this.zzc.clear();
        }
    }

    @Override
    public boolean containsKey(Object object0) {
        return this.zza(((Comparable)object0)) >= 0 || this.zzc.containsKey(((Comparable)object0));
    }

    @Override
    public Set entrySet() {
        if(this.zze == null) {
            this.zze = new zzho(this, null);
        }
        return this.zze;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzhh)) {
            return super.equals(object0);
        }
        int v = this.size();
        if(v != ((zzhh)object0).size()) {
            return false;
        }
        int v1 = this.zzc();
        if(v1 != ((zzhh)object0).zzc()) {
            return this.entrySet().equals(((zzhh)object0).entrySet());
        }
        for(int v2 = 0; v2 < v1; ++v2) {
            if(!this.zzb(v2).equals(((zzhh)object0).zzb(v2))) {
                return false;
            }
        }
        return v1 == v ? true : this.zzc.equals(((zzhh)object0).zzc);
    }

    @Override
    public Object get(Object object0) {
        int v = this.zza(((Comparable)object0));
        return v < 0 ? this.zzc.get(((Comparable)object0)) : ((zzhm)this.zzb.get(v)).getValue();
    }

    @Override
    public int hashCode() {
        int v = this.zzc();
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            v2 += ((zzhm)this.zzb.get(v1)).hashCode();
        }
        return this.zzc.size() <= 0 ? v2 : v2 + this.zzc.hashCode();
    }

    @Override
    public Object put(Object object0, Object object1) {
        return this.zza(((Comparable)object0), object1);
    }

    @Override
    public Object remove(Object object0) {
        this.zzf();
        int v = this.zza(((Comparable)object0));
        if(v >= 0) {
            return this.zzc(v);
        }
        return this.zzc.isEmpty() ? null : this.zzc.remove(((Comparable)object0));
    }

    @Override
    public int size() {
        return this.zzb.size() + this.zzc.size();
    }

    private final int zza(Comparable comparable0) {
        int v = this.zzb.size() - 1;
        if(v >= 0) {
            int v1 = comparable0.compareTo(((Comparable)((zzhm)this.zzb.get(v)).getKey()));
            if(v1 > 0) {
                return -(v + 2);
            }
            if(v1 == 0) {
                return v;
            }
        }
        int v2 = 0;
        while(v2 <= v) {
            int v3 = (v2 + v) / 2;
            int v4 = comparable0.compareTo(((Comparable)((zzhm)this.zzb.get(v3)).getKey()));
            if(v4 < 0) {
                v = v3 - 1;
                continue;
            }
            if(v4 > 0) {
                v2 = v3 + 1;
                continue;
            }
            return v3;
        }
        return -(v2 + 1);
    }

    static zzhh zza(int v) {
        return new zzhg(v);
    }

    public final Object zza(Comparable comparable0, Object object0) {
        this.zzf();
        int v = this.zza(comparable0);
        if(v >= 0) {
            return ((zzhm)this.zzb.get(v)).setValue(object0);
        }
        this.zzf();
        if(this.zzb.isEmpty() && !(this.zzb instanceof ArrayList)) {
            this.zzb = new ArrayList(this.zza);
        }
        if(-(v + 1) >= this.zza) {
            return this.zzg().put(comparable0, object0);
        }
        int v1 = this.zza;
        if(this.zzb.size() == v1) {
            zzhm zzhm0 = (zzhm)this.zzb.remove(v1 - 1);
            this.zzg().put(((Comparable)zzhm0.getKey()), zzhm0.getValue());
        }
        this.zzb.add(-(v + 1), new zzhm(this, comparable0, object0));
        return null;
    }

    public void zza() {
        if(!this.zzd) {
            this.zzc = this.zzc.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzc);
            this.zzf = this.zzf.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzf);
            this.zzd = true;
        }
    }

    public final Map.Entry zzb(int v) {
        return (Map.Entry)this.zzb.get(v);
    }

    public final boolean zzb() {
        return this.zzd;
    }

    private final Object zzc(int v) {
        this.zzf();
        Object object0 = ((zzhm)this.zzb.remove(v)).getValue();
        if(!this.zzc.isEmpty()) {
            Iterator iterator0 = this.zzg().entrySet().iterator();
            List list0 = this.zzb;
            Object object1 = iterator0.next();
            list0.add(new zzhm(this, ((Map.Entry)object1)));
            iterator0.remove();
        }
        return object0;
    }

    public final int zzc() {
        return this.zzb.size();
    }

    public final Iterable zzd() {
        return this.zzc.isEmpty() ? zzhl.zza() : this.zzc.entrySet();
    }

    final Set zze() {
        if(this.zzg == null) {
            this.zzg = new zzhi(this, null);
        }
        return this.zzg;
    }

    private final void zzf() {
        if(this.zzd) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap zzg() {
        this.zzf();
        if(this.zzc.isEmpty() && !(this.zzc instanceof TreeMap)) {
            this.zzc = new TreeMap();
            this.zzf = ((TreeMap)this.zzc).descendingMap();
        }
        return (SortedMap)this.zzc;
    }
}

