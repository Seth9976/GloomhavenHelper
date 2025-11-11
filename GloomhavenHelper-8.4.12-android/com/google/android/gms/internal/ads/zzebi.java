package com.google.android.gms.internal.ads;

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

class zzebi extends AbstractMap {
    private boolean zzhpv;
    private final int zzhwx;
    private List zzhwy;
    private Map zzhwz;
    private volatile zzebp zzhxa;
    private Map zzhxb;
    private volatile zzebj zzhxc;

    private zzebi(int v) {
        this.zzhwx = v;
        this.zzhwy = Collections.emptyList();
        this.zzhwz = Collections.emptyMap();
        this.zzhxb = Collections.emptyMap();
    }

    zzebi(int v, zzebh zzebh0) {
        this(v);
    }

    @Override
    public void clear() {
        this.zzbez();
        if(!this.zzhwy.isEmpty()) {
            this.zzhwy.clear();
        }
        if(!this.zzhwz.isEmpty()) {
            this.zzhwz.clear();
        }
    }

    @Override
    public boolean containsKey(Object object0) {
        return this.zza(((Comparable)object0)) >= 0 || this.zzhwz.containsKey(((Comparable)object0));
    }

    @Override
    public Set entrySet() {
        if(this.zzhxa == null) {
            this.zzhxa = new zzebp(this, null);
        }
        return this.zzhxa;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzebi)) {
            return super.equals(object0);
        }
        int v = this.size();
        if(v != ((zzebi)object0).size()) {
            return false;
        }
        int v1 = this.zzbew();
        if(v1 != ((zzebi)object0).zzbew()) {
            return this.entrySet().equals(((zzebi)object0).entrySet());
        }
        for(int v2 = 0; v2 < v1; ++v2) {
            if(!this.zzgx(v2).equals(((zzebi)object0).zzgx(v2))) {
                return false;
            }
        }
        return v1 == v ? true : this.zzhwz.equals(((zzebi)object0).zzhwz);
    }

    @Override
    public Object get(Object object0) {
        int v = this.zza(((Comparable)object0));
        return v < 0 ? this.zzhwz.get(((Comparable)object0)) : ((zzebn)this.zzhwy.get(v)).getValue();
    }

    @Override
    public int hashCode() {
        int v = this.zzbew();
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            v2 += ((zzebn)this.zzhwy.get(v1)).hashCode();
        }
        return this.zzhwz.size() <= 0 ? v2 : v2 + this.zzhwz.hashCode();
    }

    public final boolean isImmutable() {
        return this.zzhpv;
    }

    @Override
    public Object put(Object object0, Object object1) {
        return this.zza(((Comparable)object0), object1);
    }

    @Override
    public Object remove(Object object0) {
        this.zzbez();
        int v = this.zza(((Comparable)object0));
        if(v >= 0) {
            return this.zzgy(v);
        }
        return this.zzhwz.isEmpty() ? null : this.zzhwz.remove(((Comparable)object0));
    }

    @Override
    public int size() {
        return this.zzhwy.size() + this.zzhwz.size();
    }

    private final int zza(Comparable comparable0) {
        int v = this.zzhwy.size() - 1;
        if(v >= 0) {
            int v1 = comparable0.compareTo(((Comparable)((zzebn)this.zzhwy.get(v)).getKey()));
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
            int v4 = comparable0.compareTo(((Comparable)((zzebn)this.zzhwy.get(v3)).getKey()));
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

    public final Object zza(Comparable comparable0, Object object0) {
        this.zzbez();
        int v = this.zza(comparable0);
        if(v >= 0) {
            return ((zzebn)this.zzhwy.get(v)).setValue(object0);
        }
        this.zzbez();
        if(this.zzhwy.isEmpty() && !(this.zzhwy instanceof ArrayList)) {
            this.zzhwy = new ArrayList(this.zzhwx);
        }
        if(-(v + 1) >= this.zzhwx) {
            return this.zzbfa().put(comparable0, object0);
        }
        int v1 = this.zzhwx;
        if(this.zzhwy.size() == v1) {
            zzebn zzebn0 = (zzebn)this.zzhwy.remove(v1 - 1);
            this.zzbfa().put(((Comparable)zzebn0.getKey()), zzebn0.getValue());
        }
        this.zzhwy.add(-(v + 1), new zzebn(this, comparable0, object0));
        return null;
    }

    public void zzban() {
        if(!this.zzhpv) {
            this.zzhwz = this.zzhwz.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzhwz);
            this.zzhxb = this.zzhxb.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzhxb);
            this.zzhpv = true;
        }
    }

    public final int zzbew() {
        return this.zzhwy.size();
    }

    public final Iterable zzbex() {
        return this.zzhwz.isEmpty() ? zzebm.zzbfc() : this.zzhwz.entrySet();
    }

    final Set zzbey() {
        if(this.zzhxc == null) {
            this.zzhxc = new zzebj(this, null);
        }
        return this.zzhxc;
    }

    private final void zzbez() {
        if(this.zzhpv) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap zzbfa() {
        this.zzbez();
        if(this.zzhwz.isEmpty() && !(this.zzhwz instanceof TreeMap)) {
            this.zzhwz = new TreeMap();
            this.zzhxb = ((TreeMap)this.zzhwz).descendingMap();
        }
        return (SortedMap)this.zzhwz;
    }

    static zzebi zzgw(int v) {
        return new zzebh(v);
    }

    public final Map.Entry zzgx(int v) {
        return (Map.Entry)this.zzhwy.get(v);
    }

    private final Object zzgy(int v) {
        this.zzbez();
        Object object0 = ((zzebn)this.zzhwy.remove(v)).getValue();
        if(!this.zzhwz.isEmpty()) {
            Iterator iterator0 = this.zzbfa().entrySet().iterator();
            List list0 = this.zzhwy;
            Object object1 = iterator0.next();
            list0.add(new zzebn(this, ((Map.Entry)object1)));
            iterator0.remove();
        }
        return object0;
    }
}

