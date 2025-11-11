package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzet {
    final zzhh zza;
    private boolean zzb;
    private boolean zzc;
    private static final zzet zzd;

    static {
        zzet.zzd = new zzet(true);
    }

    private zzet() {
        this.zza = zzhh.zza(16);
    }

    private zzet(zzhh zzhh0) {
        this.zza = zzhh0;
        this.zzb();
    }

    private zzet(boolean z) {
        this(zzhh.zza(0));
        this.zzb();
    }

    @Override
    public final Object clone() throws CloneNotSupportedException {
        zzet zzet0 = new zzet();
        for(int v = 0; v < this.zza.zzc(); ++v) {
            Map.Entry map$Entry0 = this.zza.zzb(v);
            zzet0.zzb(((zzev)map$Entry0.getKey()), map$Entry0.getValue());
        }
        for(Object object0: this.zza.zzd()) {
            zzet0.zzb(((zzev)((Map.Entry)object0).getKey()), ((Map.Entry)object0).getValue());
        }
        zzet0.zzc = this.zzc;
        return zzet0;
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof zzet ? this.zza.equals(((zzet)object0).zza) : false;
    }

    @Override
    public final int hashCode() {
        return this.zza.hashCode();
    }

    public static int zza(zzev zzev0, Object object0) {
        int v = 0;
        zzil zzil0 = zzev0.zzb();
        int v1 = zzev0.zza();
        if(zzev0.zzd()) {
            if(zzev0.zze()) {
                for(Object object1: ((List)object0)) {
                    v += zzet.zzb(zzil0, object1);
                }
                return zzek.zze(v1) + v + zzek.zzl(v);
            }
            for(Object object2: ((List)object0)) {
                v += zzet.zza(zzil0, v1, object2);
            }
            return v;
        }
        return zzet.zza(zzil0, v1, object0);
    }

    static int zza(zzil zzil0, int v, Object object0) {
        int v1 = zzek.zze(v);
        if(zzil0 == zzil.zzj) {
            zzgn zzgn0 = (zzgn)object0;
            v1 <<= 1;
        }
        return v1 + zzet.zzb(zzil0, object0);
    }

    public static zzet zza() {
        return zzet.zzd;
    }

    private final Object zza(zzev zzev0) {
        Object object0 = this.zza.get(zzev0);
        return object0 instanceof zzfo ? zzfo.zza() : object0;
    }

    private static Object zza(Object object0) {
        if(object0 instanceof zzgs) {
            return ((zzgs)object0).zza();
        }
        if(object0 instanceof byte[]) {
            byte[] arr_b = new byte[((byte[])object0).length];
            System.arraycopy(((byte[])object0), 0, arr_b, 0, ((byte[])object0).length);
            return arr_b;
        }
        return object0;
    }

    static void zza(zzek zzek0, zzil zzil0, int v, Object object0) throws IOException {
        if(zzil0 == zzil.zzj) {
            zzek0.zza(v, 3);
            ((zzgn)object0).zza(zzek0);
            zzek0.zza(v, 4);
            return;
        }
        zzek0.zza(v, zzil0.zzb());
        switch(zzew.zzb[zzil0.ordinal()]) {
            case 1: {
                zzek0.zza(((double)(((Double)object0))));
                return;
            }
            case 2: {
                zzek0.zza(((float)(((Float)object0))));
                return;
            }
            case 3: {
                zzek0.zza(((long)(((Long)object0))));
                return;
            }
            case 4: {
                zzek0.zza(((long)(((Long)object0))));
                return;
            }
            case 5: {
                zzek0.zza(((int)(((Integer)object0))));
                return;
            }
            case 6: {
                zzek0.zzc(((long)(((Long)object0))));
                return;
            }
            case 7: {
                zzek0.zzd(((int)(((Integer)object0))));
                return;
            }
            case 8: {
                zzek0.zza(((Boolean)object0).booleanValue());
                return;
            }
            case 9: {
                ((zzgn)object0).zza(zzek0);
                return;
            }
            case 10: {
                zzek0.zza(((zzgn)object0));
                return;
            }
            case 11: {
                if(object0 instanceof zzdv) {
                    zzek0.zza(((zzdv)object0));
                    return;
                }
                zzek0.zza(((String)object0));
                return;
            }
            case 12: {
                if(object0 instanceof zzdv) {
                    zzek0.zza(((zzdv)object0));
                    return;
                }
                zzek0.zzb(((byte[])object0), 0, ((byte[])object0).length);
                return;
            }
            case 13: {
                zzek0.zzb(((int)(((Integer)object0))));
                return;
            }
            case 14: {
                zzek0.zzd(((int)(((Integer)object0))));
                return;
            }
            case 15: {
                zzek0.zzc(((long)(((Long)object0))));
                return;
            }
            case 16: {
                zzek0.zzc(((int)(((Integer)object0))));
                return;
            }
            case 17: {
                zzek0.zzb(((long)(((Long)object0))));
                return;
            }
            case 18: {
                if(object0 instanceof zzfh) {
                    zzek0.zza(((zzfh)object0).zza());
                    return;
                }
                zzek0.zza(((int)(((Integer)object0))));
            }
        }
    }

    private static void zza(zzil zzil0, Object object0) {
        zzfe.zza(object0);
        boolean z = true;
        switch(zzew.zza[zzil0.zza().ordinal()]) {
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
                if(!(object0 instanceof zzdv) && !(object0 instanceof byte[])) {
                    z = false;
                }
                break;
            }
            case 8: {
                if(!(object0 instanceof Integer) && !(object0 instanceof zzfh)) {
                    z = false;
                }
                break;
            }
            case 9: {
                if(!(object0 instanceof zzgn) && !(object0 instanceof zzfo)) {
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

    private static boolean zza(Map.Entry map$Entry0) {
        zzev zzev0 = (zzev)map$Entry0.getKey();
        if(zzev0.zzc() == zzio.zzi) {
            if(zzev0.zzd()) {
                Iterator iterator0 = ((List)map$Entry0.getValue()).iterator();
                while(true) {
                    if(!iterator0.hasNext()) {
                        return true;
                    }
                    Object object0 = iterator0.next();
                    if(((zzgn)object0).h_()) {
                        continue;
                    }
                    return false;
                }
            }
            Object object1 = map$Entry0.getValue();
            if(object1 instanceof zzgn) {
                return ((zzgn)object1).h_();
            }
            if(!(object1 instanceof zzfo)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            return true;
        }
        return true;
    }

    public final void zza(zzet zzet0) {
        for(int v = 0; v < zzet0.zza.zzc(); ++v) {
            this.zzb(zzet0.zza.zzb(v));
        }
        for(Object object0: zzet0.zza.zzd()) {
            this.zzb(((Map.Entry)object0));
        }
    }

    private static int zzb(zzil zzil0, Object object0) {
        switch(zzew.zzb[zzil0.ordinal()]) {
            case 1: {
                return zzek.zzb(((double)(((Double)object0))));
            }
            case 2: {
                return zzek.zzb(((float)(((Float)object0))));
            }
            case 3: {
                return zzek.zzd(((long)(((Long)object0))));
            }
            case 4: {
                return zzek.zze(((long)(((Long)object0))));
            }
            case 5: {
                return zzek.zzf(((int)(((Integer)object0))));
            }
            case 6: {
                return zzek.zzg(((long)(((Long)object0))));
            }
            case 7: {
                return zzek.zzi(((int)(((Integer)object0))));
            }
            case 8: {
                return zzek.zzb(((Boolean)object0).booleanValue());
            }
            case 9: {
                return zzek.zzc(((zzgn)object0));
            }
            case 10: {
                return object0 instanceof zzfo ? zzek.zza(((zzfo)object0)) : zzek.zzb(((zzgn)object0));
            }
            case 11: {
                return object0 instanceof zzdv ? zzek.zzb(((zzdv)object0)) : zzek.zzb(((String)object0));
            }
            case 12: {
                return object0 instanceof zzdv ? zzek.zzb(((zzdv)object0)) : zzek.zzb(((byte[])object0));
            }
            case 13: {
                return zzek.zzg(((int)(((Integer)object0))));
            }
            case 14: {
                return zzek.zzj(((int)(((Integer)object0))));
            }
            case 15: {
                return zzek.zzh(((long)(((Long)object0))));
            }
            case 16: {
                return zzek.zzh(((int)(((Integer)object0))));
            }
            case 17: {
                return zzek.zzf(((long)(((Long)object0))));
            }
            case 18: {
                return object0 instanceof zzfh ? zzek.zzk(((zzfh)object0).zza()) : zzek.zzk(((int)(((Integer)object0))));
            }
            default: {
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
            }
        }
    }

    private final void zzb(zzev zzev0, Object object0) {
        if(zzev0.zzd()) {
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
                zzet.zza(zzev0.zzb(), object1);
            }
            object0 = arrayList0;
        }
        else {
            zzet.zza(zzev0.zzb(), object0);
        }
        if(object0 instanceof zzfo) {
            this.zzc = true;
        }
        this.zza.zza(zzev0, object0);
    }

    private final void zzb(Map.Entry map$Entry0) {
        zzev zzev0 = (zzev)map$Entry0.getKey();
        zzgn zzgn0 = map$Entry0.getValue();
        if(zzgn0 instanceof zzfo) {
            zzgn0 = zzfo.zza();
        }
        if(zzev0.zzd()) {
            ArrayList arrayList0 = this.zza(zzev0);
            if(arrayList0 == null) {
                arrayList0 = new ArrayList();
            }
            for(Object object0: ((List)zzgn0)) {
                arrayList0.add(zzet.zza(object0));
            }
            this.zza.zza(zzev0, arrayList0);
            return;
        }
        if(zzev0.zzc() == zzio.zzi) {
            Object object1 = this.zza(zzev0);
            if(object1 == null) {
                Object object2 = zzet.zza(zzgn0);
                this.zza.zza(zzev0, object2);
                return;
            }
            zzgn zzgn1 = object1 instanceof zzgs ? zzev0.zza(((zzgs)object1), ((zzgs)zzgn0)) : zzev0.zza(((zzgn)object1).zzbp(), zzgn0).zzu();
            this.zza.zza(zzev0, zzgn1);
            return;
        }
        Object object3 = zzet.zza(zzgn0);
        this.zza.zza(zzev0, object3);
    }

    public final void zzb() {
        if(this.zzb) {
            return;
        }
        this.zza.zza();
        this.zzb = true;
    }

    private static int zzc(Map.Entry map$Entry0) {
        zzev zzev0 = (zzev)map$Entry0.getKey();
        Object object0 = map$Entry0.getValue();
        if(zzev0.zzc() == zzio.zzi && !zzev0.zzd() && !zzev0.zze()) {
            return object0 instanceof zzfo ? zzek.zzb(((zzev)map$Entry0.getKey()).zza(), ((zzfo)object0)) : zzek.zzb(((zzev)map$Entry0.getKey()).zza(), ((zzgn)object0));
        }
        return zzet.zza(zzev0, object0);
    }

    public final boolean zzc() {
        return this.zzb;
    }

    public final Iterator zzd() {
        return this.zzc ? new zzft(this.zza.entrySet().iterator()) : this.zza.entrySet().iterator();
    }

    final Iterator zze() {
        return this.zzc ? new zzft(this.zza.zze().iterator()) : this.zza.zze().iterator();
    }

    public final boolean zzf() {
        for(int v = 0; v < this.zza.zzc(); ++v) {
            if(!zzet.zza(this.zza.zzb(v))) {
                return false;
            }
        }
        for(Object object0: this.zza.zzd()) {
            if(!zzet.zza(((Map.Entry)object0))) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    public final int zzg() {
        int v1 = 0;
        for(int v = 0; v < this.zza.zzc(); ++v) {
            v1 += zzet.zzc(this.zza.zzb(v));
        }
        for(Object object0: this.zza.zzd()) {
            v1 += zzet.zzc(((Map.Entry)object0));
        }
        return v1;
    }
}

