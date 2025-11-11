package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzdyp {
    final zzebi zzhpu;
    private boolean zzhpv;
    private boolean zzhpw;
    private static final zzdyp zzhpx;

    static {
        zzdyp.zzhpx = new zzdyp(true);
    }

    private zzdyp() {
        this.zzhpu = zzebi.zzgw(16);
    }

    private zzdyp(zzebi zzebi0) {
        this.zzhpu = zzebi0;
        this.zzban();
    }

    private zzdyp(boolean z) {
        this(zzebi.zzgw(0));
        this.zzban();
    }

    @Override
    public final Object clone() throws CloneNotSupportedException {
        zzdyp zzdyp0 = new zzdyp();
        for(int v = 0; v < this.zzhpu.zzbew(); ++v) {
            Map.Entry map$Entry0 = this.zzhpu.zzgx(v);
            zzdyp0.zza(((zzdyr)map$Entry0.getKey()), map$Entry0.getValue());
        }
        for(Object object0: this.zzhpu.zzbex()) {
            zzdyp0.zza(((zzdyr)((Map.Entry)object0).getKey()), ((Map.Entry)object0).getValue());
        }
        zzdyp0.zzhpw = this.zzhpw;
        return zzdyp0;
    }

    final Iterator descendingIterator() {
        return this.zzhpw ? new zzdzn(this.zzhpu.zzbey().iterator()) : this.zzhpu.zzbey().iterator();
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof zzdyp ? this.zzhpu.equals(((zzdyp)object0).zzhpu) : false;
    }

    @Override
    public final int hashCode() {
        return this.zzhpu.hashCode();
    }

    public final boolean isImmutable() {
        return this.zzhpv;
    }

    public final boolean isInitialized() {
        for(int v = 0; v < this.zzhpu.zzbew(); ++v) {
            if(!zzdyp.zzb(this.zzhpu.zzgx(v))) {
                return false;
            }
        }
        for(Object object0: this.zzhpu.zzbex()) {
            if(!zzdyp.zzb(((Map.Entry)object0))) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    public final Iterator iterator() {
        return this.zzhpw ? new zzdzn(this.zzhpu.entrySet().iterator()) : this.zzhpu.entrySet().iterator();
    }

    static int zza(zzecm zzecm0, int v, Object object0) {
        return (zzecm0 == zzecm.zzhyw ? zzdyi.zzfz(v) << 1 : zzdyi.zzfz(v)) + zzdyp.zzb(zzecm0, object0);
    }

    private final Object zza(zzdyr zzdyr0) {
        Object object0 = this.zzhpu.get(zzdyr0);
        if(object0 instanceof zzdzm) {
            zzdzm zzdzm0 = (zzdzm)object0;
            return zzdzm.zzbds();
        }
        return object0;
    }

    static void zza(zzdyi zzdyi0, zzecm zzecm0, int v, Object object0) throws IOException {
        if(zzecm0 == zzecm.zzhyw) {
            zzdyi0.zzab(v, 3);
            ((zzeah)object0).zzb(zzdyi0);
            zzdyi0.zzab(v, 4);
            return;
        }
        zzdyi0.zzab(v, zzecm0.zzbfp());
        switch(zzdys.zzhoz[zzecm0.ordinal()]) {
            case 1: {
                zzdyi0.zzb(((double)(((Double)object0))));
                return;
            }
            case 2: {
                zzdyi0.zzf(((float)(((Float)object0))));
                return;
            }
            case 3: {
                zzdyi0.zzfh(((long)(((Long)object0))));
                return;
            }
            case 4: {
                zzdyi0.zzfh(((long)(((Long)object0))));
                return;
            }
            case 5: {
                zzdyi0.zzfv(((int)(((Integer)object0))));
                return;
            }
            case 6: {
                zzdyi0.zzfj(((long)(((Long)object0))));
                return;
            }
            case 7: {
                zzdyi0.zzfy(((int)(((Integer)object0))));
                return;
            }
            case 8: {
                zzdyi0.zzbp(((Boolean)object0).booleanValue());
                return;
            }
            case 9: {
                ((zzeah)object0).zzb(zzdyi0);
                return;
            }
            case 10: {
                zzdyi0.zzg(((zzeah)object0));
                return;
            }
            case 11: {
                if(object0 instanceof zzdxn) {
                    zzdyi0.zzah(((zzdxn)object0));
                    return;
                }
                zzdyi0.zzhj(((String)object0));
                return;
            }
            case 12: {
                if(object0 instanceof zzdxn) {
                    zzdyi0.zzah(((zzdxn)object0));
                    return;
                }
                zzdyi0.zzk(((byte[])object0), 0, ((byte[])object0).length);
                return;
            }
            case 13: {
                zzdyi0.zzfw(((int)(((Integer)object0))));
                return;
            }
            case 14: {
                zzdyi0.zzfy(((int)(((Integer)object0))));
                return;
            }
            case 15: {
                zzdyi0.zzfj(((long)(((Long)object0))));
                return;
            }
            case 16: {
                zzdyi0.zzfx(((int)(((Integer)object0))));
                return;
            }
            case 17: {
                zzdyi0.zzfi(((long)(((Long)object0))));
                return;
            }
            case 18: {
                if(object0 instanceof zzdzb) {
                    zzdyi0.zzfv(((zzdzb)object0).zzaf());
                    return;
                }
                zzdyi0.zzfv(((int)(((Integer)object0))));
            }
        }
    }

    private final void zza(zzdyr zzdyr0, Object object0) {
        if(zzdyr0.zzbcp()) {
            if(!(object0 instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList0 = new ArrayList();
            arrayList0.addAll(((List)object0));
            int v = arrayList0.size();
            int v1 = 0;
            while(v1 < v) {
                Object object1 = arrayList0.get(v1);
                ++v1;
                zzdyp.zza(zzdyr0.zzbcn(), object1);
            }
            object0 = arrayList0;
        }
        else {
            zzdyp.zza(zzdyr0.zzbcn(), object0);
        }
        if(object0 instanceof zzdzm) {
            this.zzhpw = true;
        }
        this.zzhpu.zza(zzdyr0, object0);
    }

    private static void zza(zzecm zzecm0, Object object0) {
        zzdzc.checkNotNull(object0);
        boolean z = true;
        switch(zzdys.zzhqa[zzecm0.zzbfo().ordinal()]) {
            case 1: {
                z = object0 instanceof Integer;
                break;
            }
            case 2: {
                z = object0 instanceof Long;
                break;
            }
            case 3: {
                z = object0 instanceof Float;
                break;
            }
            case 4: {
                z = object0 instanceof Double;
                break;
            }
            case 5: {
                z = object0 instanceof Boolean;
                break;
            }
            case 6: {
                z = object0 instanceof String;
                break;
            }
            case 7: {
                if(!(object0 instanceof zzdxn) && !(object0 instanceof byte[])) {
                    z = false;
                }
                break;
            }
            case 8: {
                if(!(object0 instanceof Integer) && !(object0 instanceof zzdzb)) {
                    z = false;
                }
                break;
            }
            case 9: {
                if(!(object0 instanceof zzeah) && !(object0 instanceof zzdzm)) {
                    z = false;
                }
                break;
            }
            default: {
                z = false;
            }
        }
        if(!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public final void zza(zzdyp zzdyp0) {
        for(int v = 0; v < zzdyp0.zzhpu.zzbew(); ++v) {
            this.zzc(zzdyp0.zzhpu.zzgx(v));
        }
        for(Object object0: zzdyp0.zzhpu.zzbex()) {
            this.zzc(((Map.Entry)object0));
        }
    }

    private static Object zzao(Object object0) {
        if(object0 instanceof zzeaq) {
            return ((zzeaq)object0).zzbak();
        }
        if(object0 instanceof byte[]) {
            byte[] arr_b = new byte[((byte[])object0).length];
            System.arraycopy(((byte[])object0), 0, arr_b, 0, ((byte[])object0).length);
            return arr_b;
        }
        return object0;
    }

    public static int zzb(zzdyr zzdyr0, Object object0) {
        int v = 0;
        zzecm zzecm0 = zzdyr0.zzbcn();
        int v1 = zzdyr0.zzaf();
        if(zzdyr0.zzbcp()) {
            if(zzdyr0.zzbcq()) {
                for(Object object1: ((List)object0)) {
                    v += zzdyp.zzb(zzecm0, object1);
                }
                return zzdyi.zzfz(v1) + v + zzdyi.zzgh(v);
            }
            for(Object object2: ((List)object0)) {
                v += zzdyp.zza(zzecm0, v1, object2);
            }
            return v;
        }
        return zzdyp.zza(zzecm0, v1, object0);
    }

    private static int zzb(zzecm zzecm0, Object object0) {
        switch(zzdys.zzhoz[zzecm0.ordinal()]) {
            case 1: {
                return zzdyi.zzc(((double)(((Double)object0))));
            }
            case 2: {
                return zzdyi.zzg(((float)(((Float)object0))));
            }
            case 3: {
                return zzdyi.zzfk(((long)(((Long)object0))));
            }
            case 4: {
                return zzdyi.zzfl(((long)(((Long)object0))));
            }
            case 5: {
                return zzdyi.zzga(((int)(((Integer)object0))));
            }
            case 6: {
                return zzdyi.zzfn(((long)(((Long)object0))));
            }
            case 7: {
                return zzdyi.zzgd(((int)(((Integer)object0))));
            }
            case 8: {
                return zzdyi.zzbq(((Boolean)object0).booleanValue());
            }
            case 9: {
                return zzdyi.zzi(((zzeah)object0));
            }
            case 10: {
                return object0 instanceof zzdzm ? zzdyi.zza(((zzdzm)object0)) : zzdyi.zzh(((zzeah)object0));
            }
            case 11: {
                return object0 instanceof zzdxn ? zzdyi.zzai(((zzdxn)object0)) : zzdyi.zzhk(((String)object0));
            }
            case 12: {
                return object0 instanceof zzdxn ? zzdyi.zzai(((zzdxn)object0)) : zzdyi.zzw(((byte[])object0));
            }
            case 13: {
                return zzdyi.zzgb(((int)(((Integer)object0))));
            }
            case 14: {
                return zzdyi.zzge(((int)(((Integer)object0))));
            }
            case 15: {
                return zzdyi.zzfo(((long)(((Long)object0))));
            }
            case 16: {
                return zzdyi.zzgc(((int)(((Integer)object0))));
            }
            case 17: {
                return zzdyi.zzfm(((long)(((Long)object0))));
            }
            case 18: {
                return object0 instanceof zzdzb ? zzdyi.zzgf(((zzdzb)object0).zzaf()) : zzdyi.zzgf(((int)(((Integer)object0))));
            }
            default: {
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
            }
        }
    }

    private static boolean zzb(Map.Entry map$Entry0) {
        zzdyr zzdyr0 = (zzdyr)map$Entry0.getKey();
        if(zzdyr0.zzbco() == zzecp.zzhzq) {
            if(zzdyr0.zzbcp()) {
                Iterator iterator0 = ((List)map$Entry0.getValue()).iterator();
                while(true) {
                    if(!iterator0.hasNext()) {
                        return true;
                    }
                    Object object0 = iterator0.next();
                    if(((zzeah)object0).isInitialized()) {
                        continue;
                    }
                    return false;
                }
            }
            Object object1 = map$Entry0.getValue();
            if(object1 instanceof zzeah) {
                return ((zzeah)object1).isInitialized();
            }
            if(!(object1 instanceof zzdzm)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            return true;
        }
        return true;
    }

    public final void zzban() {
        if(this.zzhpv) {
            return;
        }
        this.zzhpu.zzban();
        this.zzhpv = true;
    }

    public static zzdyp zzbci() {
        return zzdyp.zzhpx;
    }

    public final int zzbcj() {
        int v1 = 0;
        for(int v = 0; v < this.zzhpu.zzbew(); ++v) {
            v1 += zzdyp.zzd(this.zzhpu.zzgx(v));
        }
        for(Object object0: this.zzhpu.zzbex()) {
            v1 += zzdyp.zzd(((Map.Entry)object0));
        }
        return v1;
    }

    private final void zzc(Map.Entry map$Entry0) {
        zzdyr zzdyr0 = (zzdyr)map$Entry0.getKey();
        zzeah zzeah0 = map$Entry0.getValue();
        if(zzeah0 instanceof zzdzm) {
            zzdzm zzdzm0 = (zzdzm)zzeah0;
            zzeah0 = zzdzm.zzbds();
        }
        if(zzdyr0.zzbcp()) {
            ArrayList arrayList0 = this.zza(zzdyr0);
            if(arrayList0 == null) {
                arrayList0 = new ArrayList();
            }
            for(Object object0: ((List)zzeah0)) {
                arrayList0.add(zzdyp.zzao(object0));
            }
            this.zzhpu.zza(zzdyr0, arrayList0);
            return;
        }
        if(zzdyr0.zzbco() == zzecp.zzhzq) {
            Object object1 = this.zza(zzdyr0);
            if(object1 == null) {
                Object object2 = zzdyp.zzao(zzeah0);
                this.zzhpu.zza(zzdyr0, object2);
                return;
            }
            zzeah zzeah1 = object1 instanceof zzeaq ? zzdyr0.zza(((zzeaq)object1), ((zzeaq)zzeah0)) : zzdyr0.zza(((zzeah)object1).zzbdd(), zzeah0).zzbcx();
            this.zzhpu.zza(zzdyr0, zzeah1);
            return;
        }
        Object object3 = zzdyp.zzao(zzeah0);
        this.zzhpu.zza(zzdyr0, object3);
    }

    private static int zzd(Map.Entry map$Entry0) {
        zzdyr zzdyr0 = (zzdyr)map$Entry0.getKey();
        Object object0 = map$Entry0.getValue();
        if(zzdyr0.zzbco() == zzecp.zzhzq && !zzdyr0.zzbcp() && !zzdyr0.zzbcq()) {
            return object0 instanceof zzdzm ? zzdyi.zzb(((zzdyr)map$Entry0.getKey()).zzaf(), ((zzdzm)object0)) : zzdyi.zzd(((zzdyr)map$Entry0.getKey()).zzaf(), ((zzeah)object0));
        }
        return zzdyp.zzb(zzdyr0, object0);
    }
}

