package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import sun.misc.Unsafe;

final class zzgr implements zzhc {
    private static final int[] zza;
    private static final Unsafe zzb;
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzgn zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final boolean zzk;
    private final int[] zzl;
    private final int zzm;
    private final int zzn;
    private final zzgv zzo;
    private final zzfx zzp;
    private final zzhu zzq;
    private final zzes zzr;
    private final zzgg zzs;

    static {
        zzgr.zza = new int[0];
        zzgr.zzb = zzia.zzc();
    }

    private zzgr(int[] arr_v, Object[] arr_object, int v, int v1, zzgn zzgn0, boolean z, boolean z1, int[] arr_v1, int v2, int v3, zzgv zzgv0, zzfx zzfx0, zzhu zzhu0, zzes zzes0, zzgg zzgg0) {
        this.zzc = arr_v;
        this.zzd = arr_object;
        this.zze = v;
        this.zzf = v1;
        this.zzi = zzgn0 instanceof zzfd;
        this.zzj = z;
        this.zzh = zzes0 != null && zzes0.zza(zzgn0);
        this.zzk = false;
        this.zzl = arr_v1;
        this.zzm = v2;
        this.zzn = v3;
        this.zzo = zzgv0;
        this.zzp = zzfx0;
        this.zzq = zzhu0;
        this.zzr = zzes0;
        this.zzg = zzgn0;
        this.zzs = zzgg0;
    }

    private final int zza(int v, int v1) {
        return v < this.zze || v > this.zzf ? -1 : this.zzb(v, v1);
    }

    private static int zza(zzhu zzhu0, Object object0) {
        return zzhu0.zzf(zzhu0.zzb(object0));
    }

    private final int zza(Object object0, byte[] arr_b, int v, int v1, int v2, int v3, int v4, int v5, int v6, long v7, int v8, zzdq zzdq0) throws IOException {
        int v10;
        Unsafe unsafe0 = zzgr.zzb;
        long v9 = (long)(this.zzc[v8 + 2] & 0xFFFFF);
        switch(v6) {
            case 51: {
                if(v4 == 1) {
                    unsafe0.putObject(object0, v7, zzdr.zzc(arr_b, v));
                    v10 = v + 8;
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 52: {
                if(v4 == 5) {
                    unsafe0.putObject(object0, v7, zzdr.zzd(arr_b, v));
                    v10 = v + 4;
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 53: 
            case 54: {
                if(v4 == 0) {
                    v10 = zzdr.zzb(arr_b, v, zzdq0);
                    unsafe0.putObject(object0, v7, zzdq0.zzb);
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 58: {
                if(v4 == 0) {
                    v10 = zzdr.zzb(arr_b, v, zzdq0);
                    unsafe0.putObject(object0, v7, Boolean.valueOf(zzdq0.zzb != 0L));
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 59: {
                if(v4 == 2) {
                    v10 = zzdr.zza(arr_b, v, zzdq0);
                    int v11 = zzdq0.zza;
                    if(v11 == 0) {
                        unsafe0.putObject(object0, v7, "");
                    }
                    else {
                        if((v5 & 0x20000000) != 0 && !zzid.zza(arr_b, v10, v10 + v11)) {
                            throw zzfn.zzh();
                        }
                        unsafe0.putObject(object0, v7, new String(arr_b, v10, v11, zzfe.zza));
                        v10 += v11;
                    }
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 60: {
                if(v4 == 2) {
                    v10 = zzdr.zza(this.zza(v8), arr_b, v, v1, zzdq0);
                    Object object1 = unsafe0.getInt(object0, v9) == v3 ? unsafe0.getObject(object0, v7) : null;
                    if(object1 == null) {
                        unsafe0.putObject(object0, v7, zzdq0.zzc);
                    }
                    else {
                        unsafe0.putObject(object0, v7, zzfe.zza(object1, zzdq0.zzc));
                    }
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 61: {
                if(v4 == 2) {
                    v10 = zzdr.zze(arr_b, v, zzdq0);
                    unsafe0.putObject(object0, v7, zzdq0.zzc);
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 55: 
            case 62: {
                if(v4 == 0) {
                    v10 = zzdr.zza(arr_b, v, zzdq0);
                    unsafe0.putObject(object0, v7, zzdq0.zza);
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 0x3F: {
                if(v4 == 0) {
                    int v12 = zzdr.zza(arr_b, v, zzdq0);
                    int v13 = zzdq0.zza;
                    zzfj zzfj0 = this.zzc(v8);
                    if(zzfj0 != null && !zzfj0.zza(v13)) {
                        zzgr.zze(object0).zza(v2, ((long)v13));
                        return v12;
                    }
                    unsafe0.putObject(object0, v7, v13);
                    unsafe0.putInt(object0, v9, v3);
                    return v12;
                }
                break;
            }
            case 57: 
            case 0x40: {
                if(v4 == 5) {
                    unsafe0.putObject(object0, v7, zzdr.zza(arr_b, v));
                    v10 = v + 4;
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 56: 
            case 65: {
                if(v4 == 1) {
                    unsafe0.putObject(object0, v7, zzdr.zzb(arr_b, v));
                    v10 = v + 8;
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 66: {
                if(v4 == 0) {
                    v10 = zzdr.zza(arr_b, v, zzdq0);
                    unsafe0.putObject(object0, v7, ((int)(-(zzdq0.zza & 1) ^ zzdq0.zza >>> 1)));
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 67: {
                if(v4 == 0) {
                    v10 = zzdr.zzb(arr_b, v, zzdq0);
                    unsafe0.putObject(object0, v7, ((long)(-(zzdq0.zzb & 1L) ^ zzdq0.zzb >>> 1)));
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 68: {
                if(v4 == 3) {
                    v10 = zzdr.zza(this.zza(v8), arr_b, v, v1, v2 & -8 | 4, zzdq0);
                    Object object2 = unsafe0.getInt(object0, v9) == v3 ? unsafe0.getObject(object0, v7) : null;
                    if(object2 == null) {
                        unsafe0.putObject(object0, v7, zzdq0.zzc);
                    }
                    else {
                        unsafe0.putObject(object0, v7, zzfe.zza(object2, zzdq0.zzc));
                    }
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            default: {
                return v;
            }
        }
        return v;
    }

    private final int zza(Object object0, byte[] arr_b, int v, int v1, int v2, int v3, int v4, int v5, long v6, int v7, long v8, zzdq zzdq0) throws IOException {
        int v36;
        int v14;
        zzfk zzfk0 = (zzfk)zzgr.zzb.getObject(object0, v8);
        if(!zzfk0.zza()) {
            int v9 = zzfk0.size();
            zzfk0 = zzfk0.zza((v9 == 0 ? 10 : v9 << 1));
            zzgr.zzb.putObject(object0, v8, zzfk0);
        }
        switch(v7) {
            case 26: {
                if(v4 == 2) {
                    if((v6 & 0x20000000L) == 0L) {
                        v14 = zzdr.zza(arr_b, v, zzdq0);
                        int v25 = zzdq0.zza;
                        if(v25 >= 0) {
                            if(v25 == 0) {
                                zzfk0.add("");
                            }
                            else {
                                zzfk0.add(new String(arr_b, v14, v25, zzfe.zza));
                                v14 += v25;
                            }
                            while(true) {
                                if(v14 >= v1) {
                                    return v14;
                                }
                                int v26 = zzdr.zza(arr_b, v14, zzdq0);
                                if(v2 != zzdq0.zza) {
                                    return v14;
                                }
                                v14 = zzdr.zza(arr_b, v26, zzdq0);
                                int v27 = zzdq0.zza;
                                if(v27 < 0) {
                                    throw zzfn.zzb();
                                }
                                if(v27 == 0) {
                                    zzfk0.add("");
                                }
                                else {
                                    zzfk0.add(new String(arr_b, v14, v27, zzfe.zza));
                                    v14 += v27;
                                }
                            }
                        }
                        throw zzfn.zzb();
                    }
                    v14 = zzdr.zza(arr_b, v, zzdq0);
                    int v28 = zzdq0.zza;
                    if(v28 < 0) {
                        throw zzfn.zzb();
                    }
                    if(v28 == 0) {
                        zzfk0.add("");
                        goto label_160;
                    }
                    int v29 = v14 + v28;
                    if(zzid.zza(arr_b, v14, v29)) {
                        zzfk0.add(new String(arr_b, v14, v28, zzfe.zza));
                        v14 = v29;
                        while(true) {
                        label_160:
                            if(v14 >= v1) {
                                return v14;
                            }
                            int v30 = zzdr.zza(arr_b, v14, zzdq0);
                            if(v2 != zzdq0.zza) {
                                return v14;
                            }
                            v14 = zzdr.zza(arr_b, v30, zzdq0);
                            int v31 = zzdq0.zza;
                            if(v31 < 0) {
                                throw zzfn.zzb();
                            }
                            if(v31 == 0) {
                                zzfk0.add("");
                            }
                            else {
                                int v32 = v14 + v31;
                                if(!zzid.zza(arr_b, v14, v32)) {
                                    throw zzfn.zzh();
                                }
                                zzfk0.add(new String(arr_b, v14, v31, zzfe.zza));
                                v14 = v32;
                            }
                        }
                    }
                    throw zzfn.zzh();
                }
                break;
            }
            case 27: {
                return v4 == 2 ? zzdr.zza(this.zza(v5), v2, arr_b, v, v1, zzfk0, zzdq0) : v;
            }
            case 28: {
                if(v4 == 2) {
                    v14 = zzdr.zza(arr_b, v, zzdq0);
                    int v33 = zzdq0.zza;
                    if(v33 < 0) {
                        throw zzfn.zzb();
                    }
                    if(v33 <= arr_b.length - v14) {
                        if(v33 == 0) {
                            zzfk0.add(zzdv.zza);
                        }
                        else {
                            zzfk0.add(zzdv.zza(arr_b, v14, v33));
                            v14 += v33;
                        }
                        while(true) {
                            if(v14 >= v1) {
                                return v14;
                            }
                            int v34 = zzdr.zza(arr_b, v14, zzdq0);
                            if(v2 != zzdq0.zza) {
                                return v14;
                            }
                            v14 = zzdr.zza(arr_b, v34, zzdq0);
                            int v35 = zzdq0.zza;
                            if(v35 < 0) {
                                throw zzfn.zzb();
                            }
                            if(v35 > arr_b.length - v14) {
                                throw zzfn.zza();
                            }
                            if(v35 == 0) {
                                zzfk0.add(zzdv.zza);
                            }
                            else {
                                zzfk0.add(zzdv.zza(arr_b, v14, v35));
                                v14 += v35;
                            }
                        }
                    }
                    throw zzfn.zza();
                }
                break;
            }
            case 18: 
            case 35: {
                if(v4 == 2) {
                    v14 = zzdr.zza(arr_b, v, zzdq0);
                    int v15 = zzdq0.zza + v14;
                    while(v14 < v15) {
                        ((zzep)zzfk0).zza(zzdr.zzc(arr_b, v14));
                        v14 += 8;
                    }
                    if(v14 != v15) {
                        throw zzfn.zza();
                    }
                    return v14;
                }
                if(v4 == 1) {
                    ((zzep)zzfk0).zza(zzdr.zzc(arr_b, v));
                    for(v14 = v + 8; v14 < v1; v14 = v16 + 8) {
                        int v16 = zzdr.zza(arr_b, v14, zzdq0);
                        if(v2 != zzdq0.zza) {
                            return v14;
                        }
                        ((zzep)zzfk0).zza(zzdr.zzc(arr_b, v16));
                    }
                    return v14;
                }
                break;
            }
            case 19: 
            case 36: {
                if(v4 == 2) {
                    v14 = zzdr.zza(arr_b, v, zzdq0);
                    int v17 = zzdq0.zza + v14;
                    while(v14 < v17) {
                        ((zzez)zzfk0).zza(zzdr.zzd(arr_b, v14));
                        v14 += 4;
                    }
                    if(v14 != v17) {
                        throw zzfn.zza();
                    }
                    return v14;
                }
                if(v4 == 5) {
                    ((zzez)zzfk0).zza(zzdr.zzd(arr_b, v));
                    for(v14 = v + 4; v14 < v1; v14 = v18 + 4) {
                        int v18 = zzdr.zza(arr_b, v14, zzdq0);
                        if(v2 != zzdq0.zza) {
                            return v14;
                        }
                        ((zzez)zzfk0).zza(zzdr.zzd(arr_b, v18));
                    }
                    return v14;
                }
                break;
            }
            case 20: 
            case 21: 
            case 37: 
            case 38: {
                if(v4 == 2) {
                    v14 = zzdr.zza(arr_b, v, zzdq0);
                    int v19 = zzdq0.zza + v14;
                    while(v14 < v19) {
                        v14 = zzdr.zzb(arr_b, v14, zzdq0);
                        ((zzgb)zzfk0).zza(zzdq0.zzb);
                    }
                    if(v14 != v19) {
                        throw zzfn.zza();
                    }
                    return v14;
                }
                if(v4 == 0) {
                    v14 = zzdr.zzb(arr_b, v, zzdq0);
                    ((zzgb)zzfk0).zza(zzdq0.zzb);
                    while(v14 < v1) {
                        int v20 = zzdr.zza(arr_b, v14, zzdq0);
                        if(v2 != zzdq0.zza) {
                            return v14;
                        }
                        v14 = zzdr.zzb(arr_b, v20, zzdq0);
                        ((zzgb)zzfk0).zza(zzdq0.zzb);
                    }
                    return v14;
                }
                break;
            }
            case 25: 
            case 42: {
                switch(v4) {
                    case 0: {
                        int v10 = zzdr.zzb(arr_b, v, zzdq0);
                        ((zzdt)zzfk0).zza(zzdq0.zzb != 0L);
                        while(v10 < v1) {
                            int v11 = zzdr.zza(arr_b, v10, zzdq0);
                            if(v2 != zzdq0.zza) {
                                break;
                            }
                            v10 = zzdr.zzb(arr_b, v11, zzdq0);
                            ((zzdt)zzfk0).zza(zzdq0.zzb != 0L);
                        }
                        return v10;
                    }
                    case 2: {
                        int v12 = zzdr.zza(arr_b, v, zzdq0);
                        int v13 = zzdq0.zza + v12;
                        while(v12 < v13) {
                            v12 = zzdr.zzb(arr_b, v12, zzdq0);
                            ((zzdt)zzfk0).zza(zzdq0.zzb != 0L);
                        }
                        if(v12 != v13) {
                            throw zzfn.zza();
                        }
                        return v12;
                    }
                    default: {
                        return v;
                    }
                }
            }
            case 22: 
            case 29: 
            case 39: 
            case 43: {
                if(v4 == 2) {
                    return zzdr.zza(arr_b, v, zzfk0, zzdq0);
                }
                return v4 == 0 ? zzdr.zza(v2, arr_b, v, v1, zzfk0, zzdq0) : v;
            }
            case 30: 
            case 44: {
                if(v4 == 2) {
                    v36 = zzdr.zza(arr_b, v, zzfk0, zzdq0);
                }
                else if(v4 == 0) {
                    v36 = zzdr.zza(v2, arr_b, v, v1, zzfk0, zzdq0);
                }
                else {
                    break;
                }
                zzhx zzhx0 = ((zzfd)object0).zzb;
                if(zzhx0 == zzhx.zza()) {
                    zzhx0 = null;
                }
                zzhx zzhx1 = (zzhx)zzhe.zza(v3, zzfk0, this.zzc(v5), zzhx0, this.zzq);
                if(zzhx1 != null) {
                    ((zzfd)object0).zzb = zzhx1;
                }
                return v36;
            }
            case 24: 
            case 0x1F: 
            case 41: 
            case 45: {
                if(v4 == 2) {
                    v14 = zzdr.zza(arr_b, v, zzdq0);
                    int v23 = zzdq0.zza + v14;
                    while(v14 < v23) {
                        ((zzff)zzfk0).zzd(zzdr.zza(arr_b, v14));
                        v14 += 4;
                    }
                    if(v14 != v23) {
                        throw zzfn.zza();
                    }
                    return v14;
                }
                if(v4 == 5) {
                    ((zzff)zzfk0).zzd(zzdr.zza(arr_b, v));
                    for(v14 = v + 4; v14 < v1; v14 = v24 + 4) {
                        int v24 = zzdr.zza(arr_b, v14, zzdq0);
                        if(v2 != zzdq0.zza) {
                            return v14;
                        }
                        ((zzff)zzfk0).zzd(zzdr.zza(arr_b, v24));
                    }
                    return v14;
                }
                break;
            }
            case 23: 
            case 0x20: 
            case 40: 
            case 46: {
                if(v4 == 2) {
                    v14 = zzdr.zza(arr_b, v, zzdq0);
                    int v21 = zzdq0.zza + v14;
                    while(v14 < v21) {
                        ((zzgb)zzfk0).zza(zzdr.zzb(arr_b, v14));
                        v14 += 8;
                    }
                    if(v14 != v21) {
                        throw zzfn.zza();
                    }
                    return v14;
                }
                if(v4 == 1) {
                    ((zzgb)zzfk0).zza(zzdr.zzb(arr_b, v));
                    for(v14 = v + 8; v14 < v1; v14 = v22 + 8) {
                        int v22 = zzdr.zza(arr_b, v14, zzdq0);
                        if(v2 != zzdq0.zza) {
                            return v14;
                        }
                        ((zzgb)zzfk0).zza(zzdr.zzb(arr_b, v22));
                    }
                    return v14;
                }
                break;
            }
            case 33: 
            case 0x2F: {
                if(v4 == 2) {
                    v14 = zzdr.zza(arr_b, v, zzdq0);
                    int v37 = zzdq0.zza + v14;
                    while(v14 < v37) {
                        v14 = zzdr.zza(arr_b, v14, zzdq0);
                        ((zzff)zzfk0).zzd(-(zzdq0.zza & 1) ^ zzdq0.zza >>> 1);
                    }
                    if(v14 != v37) {
                        throw zzfn.zza();
                    }
                    return v14;
                }
                if(v4 == 0) {
                    v14 = zzdr.zza(arr_b, v, zzdq0);
                    ((zzff)zzfk0).zzd(-(zzdq0.zza & 1) ^ zzdq0.zza >>> 1);
                    while(v14 < v1) {
                        int v38 = zzdr.zza(arr_b, v14, zzdq0);
                        if(v2 != zzdq0.zza) {
                            return v14;
                        }
                        v14 = zzdr.zza(arr_b, v38, zzdq0);
                        ((zzff)zzfk0).zzd(-(zzdq0.zza & 1) ^ zzdq0.zza >>> 1);
                    }
                    return v14;
                }
                break;
            }
            case 34: 
            case 0x30: {
                if(v4 == 2) {
                    v14 = zzdr.zza(arr_b, v, zzdq0);
                    int v39 = zzdq0.zza + v14;
                    while(v14 < v39) {
                        v14 = zzdr.zzb(arr_b, v14, zzdq0);
                        ((zzgb)zzfk0).zza(-(zzdq0.zzb & 1L) ^ zzdq0.zzb >>> 1);
                    }
                    if(v14 != v39) {
                        throw zzfn.zza();
                    }
                    return v14;
                }
                if(v4 == 0) {
                    v14 = zzdr.zzb(arr_b, v, zzdq0);
                    ((zzgb)zzfk0).zza(-(zzdq0.zzb & 1L) ^ zzdq0.zzb >>> 1);
                    while(v14 < v1) {
                        int v40 = zzdr.zza(arr_b, v14, zzdq0);
                        if(v2 != zzdq0.zza) {
                            break;
                        }
                        v14 = zzdr.zzb(arr_b, v40, zzdq0);
                        ((zzgb)zzfk0).zza(-(zzdq0.zzb & 1L) ^ zzdq0.zzb >>> 1);
                    }
                    return v14;
                }
                break;
            }
            case 49: {
                if(v4 == 3) {
                    zzhc zzhc0 = this.zza(v5);
                    int v41 = v2 & -8 | 4;
                    int v42 = zzdr.zza(zzhc0, arr_b, v, v1, v41, zzdq0);
                    zzfk0.add(zzdq0.zzc);
                    while(v42 < v1) {
                        int v43 = zzdr.zza(arr_b, v42, zzdq0);
                        if(v2 != zzdq0.zza) {
                            break;
                        }
                        v42 = zzdr.zza(zzhc0, arr_b, v43, v1, v41, zzdq0);
                        zzfk0.add(zzdq0.zzc);
                    }
                    return v42;
                }
                break;
            }
            default: {
                return v;
            }
        }
        return v;
    }

    private final int zza(Object object0, byte[] arr_b, int v, int v1, int v2, long v3, zzdq zzdq0) throws IOException {
        int v9;
        Unsafe unsafe0 = zzgr.zzb;
        Object object1 = this.zzb(v2);
        Object object2 = unsafe0.getObject(object0, v3);
        if(this.zzs.zzc(object2)) {
            Object object3 = this.zzs.zze(object1);
            this.zzs.zza(object3, object2);
            unsafe0.putObject(object0, v3, object3);
            object2 = object3;
        }
        zzge zzge0 = this.zzs.zzf(object1);
        Map map0 = this.zzs.zza(object2);
        int v4 = zzdr.zza(arr_b, v, zzdq0);
        int v5 = zzdq0.zza;
        if(v5 < 0 || v5 > v1 - v4) {
            throw zzfn.zza();
        }
        int v6 = v5 + v4;
        Object object4 = zzge0.zzb;
        Object object5 = zzge0.zzd;
        while(v4 < v6) {
            int v7 = arr_b[v4];
            if(v7 < 0) {
                int v8 = zzdr.zza(v7, arr_b, v4 + 1, zzdq0);
                v7 = zzdq0.zza;
                v9 = v8;
            }
            else {
                v9 = v4 + 1;
            }
            switch(v7 >>> 3) {
                case 1: {
                    if((v7 & 7) == zzge0.zza.zzb()) {
                        v4 = zzgr.zza(arr_b, v9, v1, zzge0.zza, null, zzdq0);
                        object4 = zzdq0.zzc;
                        continue;
                    }
                    break;
                }
                case 2: {
                    if((v7 & 7) == zzge0.zzc.zzb()) {
                        Class class0 = zzge0.zzd.getClass();
                        v4 = zzgr.zza(arr_b, v9, v1, zzge0.zzc, class0, zzdq0);
                        object5 = zzdq0.zzc;
                        continue;
                    }
                }
            }
            v4 = zzdr.zza(v7, arr_b, v9, v1, zzdq0);
        }
        if(v4 != v6) {
            throw zzfn.zzg();
        }
        map0.put(object4, object5);
        return v6;
    }

    private static int zza(byte[] arr_b, int v, int v1, zzil zzil0, Class class0, zzdq zzdq0) throws IOException {
        int v2;
        switch(zzgq.zza[zzil0.ordinal()]) {
            case 1: {
                v2 = zzdr.zzb(arr_b, v, zzdq0);
                zzdq0.zzc = Boolean.valueOf(zzdq0.zzb != 0L);
                return v2;
            }
            case 2: {
                return zzdr.zze(arr_b, v, zzdq0);
            }
            case 3: {
                zzdq0.zzc = zzdr.zzc(arr_b, v);
                return v + 8;
            }
            case 4: 
            case 5: {
                zzdq0.zzc = zzdr.zza(arr_b, v);
                return v + 4;
            }
            case 6: 
            case 7: {
                zzdq0.zzc = zzdr.zzb(arr_b, v);
                return v + 8;
            }
            case 8: {
                zzdq0.zzc = zzdr.zzd(arr_b, v);
                return v + 4;
            }
            case 9: 
            case 10: 
            case 11: {
                v2 = zzdr.zza(arr_b, v, zzdq0);
                zzdq0.zzc = zzdq0.zza;
                return v2;
            }
            case 12: 
            case 13: {
                v2 = zzdr.zzb(arr_b, v, zzdq0);
                zzdq0.zzc = zzdq0.zzb;
                return v2;
            }
            case 14: {
                return zzdr.zza(zzgy.zza().zza(class0), arr_b, v, v1, zzdq0);
            }
            case 15: {
                v2 = zzdr.zza(arr_b, v, zzdq0);
                zzdq0.zzc = (int)(-(zzdq0.zza & 1) ^ zzdq0.zza >>> 1);
                return v2;
            }
            case 16: {
                v2 = zzdr.zzb(arr_b, v, zzdq0);
                zzdq0.zzc = (long)(-(zzdq0.zzb & 1L) ^ zzdq0.zzb >>> 1);
                return v2;
            }
            case 17: {
                return zzdr.zzd(arr_b, v, zzdq0);
            }
            default: {
                throw new RuntimeException("unsupported field type.");
            }
        }
    }

    static zzgr zza(Class class0, zzgl zzgl0, zzgv zzgv0, zzfx zzfx0, zzhu zzhu0, zzes zzes0, zzgg zzgg0) {
        Field field3;
        int v98;
        int v94;
        int v93;
        int v92;
        int v91;
        int v90;
        int v89;
        Class class2;
        int v88;
        String s1;
        Field field1;
        int v85;
        Field field0;
        int v79;
        int v73;
        int v56;
        int v42;
        int v41;
        int v37;
        int v36;
        int v31;
        int v30;
        int v25;
        int v24;
        int v19;
        int v18;
        int v17;
        int v16;
        int v15;
        int v14;
        int[] arr_v;
        int v13;
        int v12;
        int v7;
        int v6;
        int v = 0;
        if(zzgl0 instanceof zzha) {
            boolean z = ((zzha)zzgl0).zza() == zzd.zzi;
            String s = ((zzha)zzgl0).zzd();
            int v1 = s.length();
            int v2 = s.charAt(0);
            if(v2 >= 0xD800) {
                int v3 = v2 & 0x1FFF;
                int v5 = 13;
                for(int v4 = 1; true; v4 = v6) {
                    v6 = v4 + 1;
                    v7 = s.charAt(v4);
                    if(v7 < 0xD800) {
                        break;
                    }
                    v3 |= (v7 & 0x1FFF) << v5;
                    v5 += 13;
                }
                v2 = v7 << v5 | v3;
            }
            else {
                v6 = 1;
            }
            int v8 = v6 + 1;
            int v9 = s.charAt(v6);
            if(v9 >= 0xD800) {
                int v10 = v9 & 0x1FFF;
                int v11 = 13;
                while(true) {
                    v12 = v8 + 1;
                    v13 = s.charAt(v8);
                    if(v13 < 0xD800) {
                        break;
                    }
                    v10 |= (v13 & 0x1FFF) << v11;
                    v11 += 13;
                    v8 = v12;
                }
                v9 = v10 | v13 << v11;
            }
            else {
                v12 = v8;
            }
            if(v9 == 0) {
                arr_v = zzgr.zza;
                v14 = 0;
                v15 = 0;
                v16 = 0;
                v17 = 0;
                v18 = 0;
                v19 = 0;
            }
            else {
                int v20 = v12 + 1;
                int v21 = s.charAt(v12);
                if(v21 >= 0xD800) {
                    int v22 = v21 & 0x1FFF;
                    int v23 = 13;
                    while(true) {
                        v24 = v20 + 1;
                        v25 = s.charAt(v20);
                        if(v25 < 0xD800) {
                            break;
                        }
                        v22 |= (v25 & 0x1FFF) << v23;
                        v23 += 13;
                        v20 = v24;
                    }
                    v21 = v25 << v23 | v22;
                }
                else {
                    v24 = v20;
                }
                int v26 = v24 + 1;
                int v27 = s.charAt(v24);
                if(v27 >= 0xD800) {
                    int v28 = v27 & 0x1FFF;
                    int v29 = 13;
                    while(true) {
                        v30 = v26 + 1;
                        v31 = s.charAt(v26);
                        if(v31 < 0xD800) {
                            break;
                        }
                        v28 |= (v31 & 0x1FFF) << v29;
                        v29 += 13;
                        v26 = v30;
                    }
                    v27 = v28 | v31 << v29;
                }
                else {
                    v30 = v26;
                }
                int v32 = v30 + 1;
                int v33 = s.charAt(v30);
                if(v33 >= 0xD800) {
                    int v34 = v33 & 0x1FFF;
                    int v35 = 13;
                    while(true) {
                        v36 = v32 + 1;
                        v37 = s.charAt(v32);
                        if(v37 < 0xD800) {
                            break;
                        }
                        v34 |= (v37 & 0x1FFF) << v35;
                        v35 += 13;
                        v32 = v36;
                    }
                    v33 = v37 << v35 | v34;
                }
                else {
                    v36 = v32;
                }
                int v38 = v36 + 1;
                v17 = s.charAt(v36);
                if(v17 >= 0xD800) {
                    int v39 = v17 & 0x1FFF;
                    int v40 = 13;
                    while(true) {
                        v41 = v38 + 1;
                        v42 = s.charAt(v38);
                        if(v42 < 0xD800) {
                            break;
                        }
                        v39 |= (v42 & 0x1FFF) << v40;
                        v40 += 13;
                        v38 = v41;
                    }
                    v17 = v42 << v40 | v39;
                }
                else {
                    v41 = v38;
                }
                int v43 = v41 + 1;
                v18 = s.charAt(v41);
                if(v18 >= 0xD800) {
                    int v44 = v18 & 0x1FFF;
                    int v45 = 13;
                    int v46;
                    while((v46 = s.charAt(v43)) >= 0xD800) {
                        v44 |= (v46 & 0x1FFF) << v45;
                        v45 += 13;
                        ++v43;
                    }
                    v18 = v46 << v45 | v44;
                    ++v43;
                }
                int v47 = v43 + 1;
                v14 = s.charAt(v43);
                if(v14 >= 0xD800) {
                    int v48 = v14 & 0x1FFF;
                    int v49 = 13;
                    int v50;
                    while((v50 = s.charAt(v47)) >= 0xD800) {
                        v48 |= (v50 & 0x1FFF) << v49;
                        v49 += 13;
                        ++v47;
                    }
                    v14 = v48 | v50 << v49;
                    ++v47;
                }
                int v51 = s.charAt(v47);
                if(v51 >= 0xD800) {
                    int v52 = 13;
                    int v53 = v51 & 0x1FFF;
                    int v54 = v47 + 1;
                    int v55;
                    while((v55 = s.charAt(v54)) >= 0xD800) {
                        v53 |= (v55 & 0x1FFF) << v52;
                        v52 += 13;
                        ++v54;
                    }
                    v51 = v53 | v55 << v52;
                    v56 = v54 + 1;
                }
                else {
                    v56 = v47 + 1;
                }
                int v57 = v56 + 1;
                v = s.charAt(v56);
                if(v >= 0xD800) {
                    int v58 = 13;
                    int v59 = v & 0x1FFF;
                    int v60 = v57;
                    int v61;
                    while((v61 = s.charAt(v60)) >= 0xD800) {
                        v59 |= (v61 & 0x1FFF) << v58;
                        v58 += 13;
                        ++v60;
                    }
                    v = v59 | v61 << v58;
                    v57 = v60 + 1;
                }
                arr_v = new int[v + v14 + v51];
                v16 = (v21 << 1) + v27;
                v19 = v21;
                v15 = v33;
                v12 = v57;
            }
            Unsafe unsafe0 = zzgr.zzb;
            Object[] arr_object = ((zzha)zzgl0).zze();
            Class class1 = ((zzha)zzgl0).zzc().getClass();
            int v62 = v16;
            int[] arr_v1 = new int[v18 * 3];
            Object[] arr_object1 = new Object[v18 << 1];
            int v63 = v + v14;
            int v64 = v;
            int v65 = v63;
            int v66 = 0;
            int v67 = 0;
            while(v12 < v1) {
                int v68 = s.charAt(v12);
                if(v68 >= 0xD800) {
                    int v69 = 13;
                    int v70 = v68 & 0x1FFF;
                    int v71 = v12 + 1;
                    int v72;
                    while((v72 = s.charAt(v71)) >= 0xD800) {
                        v70 |= (v72 & 0x1FFF) << v69;
                        v69 += 13;
                        ++v71;
                    }
                    v68 = v70 | v72 << v69;
                    v73 = v71 + 1;
                }
                else {
                    v73 = v12 + 1;
                }
                int v74 = s.charAt(v73);
                if(v74 >= 0xD800) {
                    int v75 = 13;
                    int v76 = v74 & 0x1FFF;
                    int v77 = v73 + 1;
                    int v78;
                    while((v78 = s.charAt(v77)) >= 0xD800) {
                        v76 |= (v78 & 0x1FFF) << v75;
                        v75 += 13;
                        ++v77;
                    }
                    v74 = v76 | v78 << v75;
                    v79 = v77 + 1;
                }
                else {
                    v79 = v73 + 1;
                }
                if((v74 & 0x400) != 0) {
                    arr_v[v66] = v67;
                    ++v66;
                }
                if((v74 & 0xFF) >= 51) {
                    int v80 = v79 + 1;
                    int v81 = s.charAt(v79);
                    if(v81 >= 0xD800) {
                        int v82 = v81 & 0x1FFF;
                        int v83 = 13;
                        int v84;
                        while((v84 = s.charAt(v80)) >= 0xD800) {
                            v82 |= (v84 & 0x1FFF) << v83;
                            v83 += 13;
                            ++v80;
                        }
                        v81 = v82 | v84 << v83;
                        ++v80;
                    }
                    switch((v74 & 0xFF) - 51) {
                        case 12: {
                            if((v2 & 1) == 1) {
                                arr_object1[(v67 / 3 << 1) + 1] = arr_object[v62];
                                ++v62;
                            }
                            break;
                        }
                        case 9: 
                        case 17: {
                            arr_object1[(v67 / 3 << 1) + 1] = arr_object[v62];
                            ++v62;
                        }
                    }
                    Object object0 = arr_object[v81 << 1];
                    if(object0 instanceof Field) {
                        field0 = (Field)object0;
                    }
                    else {
                        field0 = zzgr.zza(class1, ((String)object0));
                        arr_object[v81 << 1] = field0;
                    }
                    v85 = v15;
                    int v86 = (int)unsafe0.objectFieldOffset(field0);
                    int v87 = (v81 << 1) + 1;
                    Object object1 = arr_object[v87];
                    if(object1 instanceof Field) {
                        field1 = (Field)object1;
                    }
                    else {
                        field1 = zzgr.zza(class1, ((String)object1));
                        arr_object[v87] = field1;
                    }
                    s1 = s;
                    v88 = (int)unsafe0.objectFieldOffset(field1);
                    class2 = class1;
                    v89 = v62;
                    v90 = v86;
                    v91 = 0;
                    v92 = v85;
                    v93 = v17;
                    v94 = v68;
                    v12 = v80;
                }
                else {
                    int v95 = v62 + 1;
                    Field field2 = zzgr.zza(class1, ((String)arr_object[v62]));
                    v93 = v17;
                    switch(v74 & 0xFF) {
                        case 9: 
                        case 17: {
                            v92 = v15;
                            arr_object1[(v67 / 3 << 1) + 1] = field2.getType();
                            v94 = v68;
                            break;
                        }
                        case 12: 
                        case 30: 
                        case 44: {
                            v92 = v15;
                            if((v2 & 1) == 1) {
                                arr_object1[(v67 / 3 << 1) + 1] = arr_object[v95];
                                v94 = v68;
                                ++v95;
                                break;
                            }
                            v94 = v68;
                            break;
                        }
                        case 27: 
                        case 49: {
                            v92 = v15;
                            arr_object1[(v67 / 3 << 1) + 1] = arr_object[v95];
                            v94 = v68;
                            ++v95;
                            break;
                        }
                        case 50: {
                            arr_v[v64] = v67;
                            int v96 = v67 / 3 << 1;
                            int v97 = v95 + 1;
                            arr_object1[v96] = arr_object[v95];
                            if((v74 & 0x800) == 0) {
                                ++v64;
                                v95 = v97;
                                v92 = v15;
                            }
                            else {
                                v95 = v97 + 1;
                                arr_object1[v96 + 1] = arr_object[v97];
                                v92 = v15;
                                ++v64;
                            }
                            v94 = v68;
                            break;
                        }
                        default: {
                            v92 = v15;
                            v94 = v68;
                            break;
                        }
                    }
                    v90 = (int)unsafe0.objectFieldOffset(field2);
                    if((v2 & 1) != 1 || (v74 & 0xFF) > 17) {
                        s1 = s;
                        class2 = class1;
                        v89 = v95;
                        v98 = v79;
                        v91 = 0;
                        v88 = 0;
                    }
                    else {
                        v98 = v79 + 1;
                        int v99 = s.charAt(v79);
                        if(v99 >= 0xD800) {
                            int v100 = v99 & 0x1FFF;
                            int v101 = 13;
                            int v102;
                            while((v102 = s.charAt(v98)) >= 0xD800) {
                                v100 |= (v102 & 0x1FFF) << v101;
                                v101 += 13;
                                ++v98;
                            }
                            v99 = v100 | v102 << v101;
                            ++v98;
                        }
                        int v103 = (v19 << 1) + v99 / 0x20;
                        Object object2 = arr_object[v103];
                        s1 = s;
                        if(object2 instanceof Field) {
                            field3 = (Field)object2;
                        }
                        else {
                            field3 = zzgr.zza(class1, ((String)object2));
                            arr_object[v103] = field3;
                        }
                        class2 = class1;
                        v89 = v95;
                        v88 = (int)unsafe0.objectFieldOffset(field3);
                        v91 = v99 % 0x20;
                    }
                    if((v74 & 0xFF) >= 18 && (v74 & 0xFF) <= 49) {
                        arr_v[v65] = v90;
                        ++v65;
                    }
                    v12 = v98;
                }
                arr_v1[v67] = v94;
                int v104 = v67 + 2;
                arr_v1[v67 + 1] = (v74 & 0xFF) << 20 | (((v74 & 0x100) == 0 ? 0 : 0x10000000) | ((v74 & 0x200) == 0 ? 0 : 0x20000000)) | v90;
                v67 = v104 + 1;
                arr_v1[v104] = v91 << 20 | v88;
                class1 = class2;
                v17 = v93;
                v62 = v89;
                v15 = v92;
                s = s1;
            }
            return new zzgr(arr_v1, arr_object1, v15, v17, ((zzha)zzgl0).zzc(), z, false, arr_v, v, v63, zzgv0, zzfx0, zzhu0, zzes0, zzgg0);
        }
        ((zzhr)zzgl0).zza();
        throw new NoSuchMethodError();
    }

    private final zzhc zza(int v) {
        int v1 = v / 3 << 1;
        zzhc zzhc0 = (zzhc)this.zzd[v1];
        if(zzhc0 != null) {
            return zzhc0;
        }
        zzhc zzhc1 = zzgy.zza().zza(((Class)this.zzd[v1 + 1]));
        this.zzd[v1] = zzhc1;
        return zzhc1;
    }

    private final Object zza(int v, int v1, Map map0, zzfj zzfj0, Object object0, zzhu zzhu0) {
        Object object1 = this.zzb(v);
        zzge zzge0 = this.zzs.zzf(object1);
        Iterator iterator0 = map0.entrySet().iterator();
        while(iterator0.hasNext()) {
            Object object2 = iterator0.next();
            Map.Entry map$Entry0 = (Map.Entry)object2;
            if(!zzfj0.zza(((int)(((Integer)map$Entry0.getValue()))))) {
                if(object0 == null) {
                    object0 = zzhu0.zza();
                }
                zzed zzed0 = zzdv.zzc(zzgf.zza(zzge0, map$Entry0.getKey(), map$Entry0.getValue()));
                zzek zzek0 = zzed0.zzb();
                try {
                    zzgf.zza(zzek0, zzge0, map$Entry0.getKey(), map$Entry0.getValue());
                }
                catch(IOException iOException0) {
                    throw new RuntimeException(iOException0);
                }
                zzhu0.zza(object0, v1, zzed0.zza());
                iterator0.remove();
            }
        }
        return object0;
    }

    private final Object zza(Object object0, int v, Object object1, zzhu zzhu0) {
        int v1 = this.zzc[v];
        Object object2 = zzia.zzf(object0, ((long)(this.zzd(v) & 0xFFFFF)));
        if(object2 == null) {
            return object1;
        }
        zzfj zzfj0 = this.zzc(v);
        return zzfj0 == null ? object1 : this.zza(v, v1, this.zzs.zza(object2), zzfj0, object1, zzhu0);
    }

    private static Field zza(Class class0, String s) {
        try {
            return class0.getDeclaredField(s);
        }
        catch(NoSuchFieldException unused_ex) {
            Field[] arr_field = class0.getDeclaredFields();
            for(int v = 0; v < arr_field.length; ++v) {
                Field field0 = arr_field[v];
                if(s.equals(field0.getName())) {
                    return field0;
                }
            }
            throw new RuntimeException("Field " + s + " for " + class0.getName() + " not found. Known fields are " + Arrays.toString(arr_field));
        }
    }

    private static List zza(Object object0, long v) {
        return (List)zzia.zzf(object0, v);
    }

    private static void zza(int v, Object object0, zzir zzir0) throws IOException {
        if(object0 instanceof String) {
            zzir0.zza(v, ((String)object0));
            return;
        }
        zzir0.zza(v, ((zzdv)object0));
    }

    private static void zza(zzhu zzhu0, Object object0, zzir zzir0) throws IOException {
        zzhu0.zza(zzhu0.zzb(object0), zzir0);
    }

    private final void zza(zzir zzir0, int v, Object object0, int v1) throws IOException {
        if(object0 != null) {
            Object object1 = this.zzb(v1);
            zzir0.zza(v, this.zzs.zzf(object1), this.zzs.zzb(object0));
        }
    }

    private final void zza(Object object0, int v, zzhd zzhd0) throws IOException {
        if(zzgr.zzf(v)) {
            zzia.zza(object0, ((long)(v & 0xFFFFF)), zzhd0.zzm());
            return;
        }
        if(this.zzi) {
            zzia.zza(object0, ((long)(v & 0xFFFFF)), zzhd0.zzl());
            return;
        }
        zzia.zza(object0, ((long)(v & 0xFFFFF)), zzhd0.zzn());
    }

    private final void zza(Object object0, Object object1, int v) {
        int v1 = this.zzd(v);
        if(!this.zza(object1, v)) {
            return;
        }
        Object object2 = zzia.zzf(object0, ((long)(v1 & 0xFFFFF)));
        Object object3 = zzia.zzf(object1, ((long)(v1 & 0xFFFFF)));
        if(object2 != null && object3 != null) {
            zzia.zza(object0, ((long)(v1 & 0xFFFFF)), zzfe.zza(object2, object3));
            this.zzb(object0, v);
            return;
        }
        if(object3 != null) {
            zzia.zza(object0, ((long)(v1 & 0xFFFFF)), object3);
            this.zzb(object0, v);
        }
    }

    private final boolean zza(Object object0, int v) {
        if(this.zzj) {
            int v1 = this.zzd(v);
            switch((v1 & 0xFF00000) >>> 20) {
                case 0: {
                    return zzia.zze(object0, ((long)(v1 & 0xFFFFF))) != 0.0;
                }
                case 1: {
                    return zzia.zzd(object0, ((long)(v1 & 0xFFFFF))) != 0.0f;
                }
                case 2: {
                    return zzia.zzb(object0, ((long)(v1 & 0xFFFFF))) != 0L;
                }
                case 3: {
                    return zzia.zzb(object0, ((long)(v1 & 0xFFFFF))) != 0L;
                }
                case 4: {
                    return zzia.zza(object0, ((long)(v1 & 0xFFFFF))) != 0;
                }
                case 5: {
                    return zzia.zzb(object0, ((long)(v1 & 0xFFFFF))) != 0L;
                }
                case 6: {
                    return zzia.zza(object0, ((long)(v1 & 0xFFFFF))) != 0;
                }
                case 7: {
                    return zzia.zzc(object0, ((long)(v1 & 0xFFFFF)));
                }
                case 8: {
                    Object object1 = zzia.zzf(object0, ((long)(v1 & 0xFFFFF)));
                    if(object1 instanceof String) {
                        return !((String)object1).isEmpty();
                    }
                    if(!(object1 instanceof zzdv)) {
                        throw new IllegalArgumentException();
                    }
                    return !zzdv.zza.equals(object1);
                }
                case 9: {
                    return zzia.zzf(object0, ((long)(v1 & 0xFFFFF))) != null;
                }
                case 10: {
                    Object object2 = zzia.zzf(object0, ((long)(v1 & 0xFFFFF)));
                    return !zzdv.zza.equals(object2);
                }
                case 11: {
                    return zzia.zza(object0, ((long)(v1 & 0xFFFFF))) != 0;
                }
                case 12: {
                    return zzia.zza(object0, ((long)(v1 & 0xFFFFF))) != 0;
                }
                case 13: {
                    return zzia.zza(object0, ((long)(v1 & 0xFFFFF))) != 0;
                }
                case 14: {
                    return zzia.zzb(object0, ((long)(v1 & 0xFFFFF))) != 0L;
                }
                case 15: {
                    return zzia.zza(object0, ((long)(v1 & 0xFFFFF))) != 0;
                }
                case 16: {
                    return zzia.zzb(object0, ((long)(v1 & 0xFFFFF))) != 0L;
                }
                case 17: {
                    return zzia.zzf(object0, ((long)(v1 & 0xFFFFF))) != null;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }
        int v2 = this.zze(v);
        return (zzia.zza(object0, ((long)(v2 & 0xFFFFF))) & 1 << (v2 >>> 20)) != 0;
    }

    private final boolean zza(Object object0, int v, int v1) {
        return zzia.zza(object0, ((long)(this.zze(v1) & 0xFFFFF))) == v;
    }

    // 去混淆评级： 低(20)
    private final boolean zza(Object object0, int v, int v1, int v2) {
        return this.zzj ? this.zza(object0, v) : (v1 & v2) != 0;
    }

    private static boolean zza(Object object0, int v, zzhc zzhc0) {
        return zzhc0.zzd(zzia.zzf(object0, ((long)(v & 0xFFFFF))));
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final int zza(Object object0) {
        int v1 = 0;
        for(int v = 0; v < this.zzc.length; v += 3) {
            int v2 = this.zzd(v);
            int v3 = this.zzc[v];
            long v4 = (long)(0xFFFFF & v2);
            int v5 = 37;
            switch((v2 & 0xFF00000) >>> 20) {
                case 0: {
                    v1 = v1 * 53 + zzfe.zza(Double.doubleToLongBits(zzia.zze(object0, v4)));
                    break;
                }
                case 1: {
                    v1 = v1 * 53 + Float.floatToIntBits(zzia.zzd(object0, v4));
                    break;
                }
                case 2: {
                    v1 = v1 * 53 + zzfe.zza(zzia.zzb(object0, v4));
                    break;
                }
                case 3: {
                    v1 = v1 * 53 + zzfe.zza(zzia.zzb(object0, v4));
                    break;
                }
                case 4: {
                    v1 = v1 * 53 + zzia.zza(object0, v4);
                    break;
                }
                case 5: {
                    v1 = v1 * 53 + zzfe.zza(zzia.zzb(object0, v4));
                    break;
                }
                case 6: {
                    v1 = v1 * 53 + zzia.zza(object0, v4);
                    break;
                }
                case 7: {
                    v1 = v1 * 53 + zzfe.zza(zzia.zzc(object0, v4));
                    break;
                }
                case 8: {
                    v1 = v1 * 53 + ((String)zzia.zzf(object0, v4)).hashCode();
                    break;
                }
                case 9: {
                    Object object1 = zzia.zzf(object0, v4);
                    if(object1 != null) {
                        v5 = object1.hashCode();
                    }
                    v1 = v1 * 53 + v5;
                    break;
                }
                case 10: {
                    v1 = v1 * 53 + zzia.zzf(object0, v4).hashCode();
                    break;
                }
                case 11: {
                    v1 = v1 * 53 + zzia.zza(object0, v4);
                    break;
                }
                case 12: {
                    v1 = v1 * 53 + zzia.zza(object0, v4);
                    break;
                }
                case 13: {
                    v1 = v1 * 53 + zzia.zza(object0, v4);
                    break;
                }
                case 14: {
                    v1 = v1 * 53 + zzfe.zza(zzia.zzb(object0, v4));
                    break;
                }
                case 15: {
                    v1 = v1 * 53 + zzia.zza(object0, v4);
                    break;
                }
                case 16: {
                    v1 = v1 * 53 + zzfe.zza(zzia.zzb(object0, v4));
                    break;
                }
                case 17: {
                    Object object2 = zzia.zzf(object0, v4);
                    if(object2 != null) {
                        v5 = object2.hashCode();
                    }
                    v1 = v1 * 53 + v5;
                    break;
                }
                case 18: 
                case 19: 
                case 20: 
                case 21: 
                case 22: 
                case 23: 
                case 24: 
                case 25: 
                case 26: 
                case 27: 
                case 28: 
                case 29: 
                case 30: 
                case 0x1F: 
                case 0x20: 
                case 33: 
                case 34: 
                case 35: 
                case 36: 
                case 37: 
                case 38: 
                case 39: 
                case 40: 
                case 41: 
                case 42: 
                case 43: 
                case 44: 
                case 45: 
                case 46: 
                case 0x2F: 
                case 0x30: 
                case 49: {
                    v1 = v1 * 53 + zzia.zzf(object0, v4).hashCode();
                    break;
                }
                case 50: {
                    v1 = v1 * 53 + zzia.zzf(object0, v4).hashCode();
                    break;
                }
                case 51: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzfe.zza(Double.doubleToLongBits(zzgr.zzb(object0, v4)));
                    }
                    break;
                }
                case 52: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + Float.floatToIntBits(zzgr.zzc(object0, v4));
                    }
                    break;
                }
                case 53: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzfe.zza(zzgr.zze(object0, v4));
                    }
                    break;
                }
                case 54: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzfe.zza(zzgr.zze(object0, v4));
                    }
                    break;
                }
                case 55: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzgr.zzd(object0, v4);
                    }
                    break;
                }
                case 56: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzfe.zza(zzgr.zze(object0, v4));
                    }
                    break;
                }
                case 57: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzgr.zzd(object0, v4);
                    }
                    break;
                }
                case 58: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzfe.zza(zzgr.zzf(object0, v4));
                    }
                    break;
                }
                case 59: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + ((String)zzia.zzf(object0, v4)).hashCode();
                    }
                    break;
                }
                case 60: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzia.zzf(object0, v4).hashCode();
                    }
                    break;
                }
                case 61: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzia.zzf(object0, v4).hashCode();
                    }
                    break;
                }
                case 62: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzgr.zzd(object0, v4);
                    }
                    break;
                }
                case 0x3F: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzgr.zzd(object0, v4);
                    }
                    break;
                }
                case 0x40: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzgr.zzd(object0, v4);
                    }
                    break;
                }
                case 65: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzfe.zza(zzgr.zze(object0, v4));
                    }
                    break;
                }
                case 66: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzgr.zzd(object0, v4);
                    }
                    break;
                }
                case 67: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzfe.zza(zzgr.zze(object0, v4));
                    }
                    break;
                }
                case 68: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzia.zzf(object0, v4).hashCode();
                    }
                }
            }
        }
        int v6 = v1 * 53 + this.zzq.zzb(object0).hashCode();
        return this.zzh ? v6 * 53 + this.zzr.zza(object0).hashCode() : v6;
    }

    final int zza(Object object0, byte[] arr_b, int v, int v1, int v2, zzdq zzdq0) throws IOException {
        int v43;
        int v42;
        int v41;
        int v40;
        int v39;
        zzfk zzfk2;
        int v33;
        int v32;
        int v31;
        int v30;
        int v29;
        int v21;
        int v20;
        int v19;
        int v18;
        int v17;
        int v16;
        int v15;
        int v12;
        int v11;
        int v3 = v2;
        Unsafe unsafe0 = zzgr.zzb;
        int v4 = v;
        int v5 = -1;
        int v6 = 0;
        int v7 = 0;
        int v8 = 0;
        int v9 = -1;
        while(true) {
            if(v4 < v1) {
                int v10 = arr_b[v4];
                if(v10 < 0) {
                    v11 = zzdr.zza(v10, arr_b, v4 + 1, zzdq0);
                    v12 = zzdq0.zza;
                }
                else {
                    v12 = v10;
                    v11 = v4 + 1;
                }
                int v13 = v12 >>> 3;
                int v14 = v13 <= v5 ? this.zzg(v13) : this.zza(v13, v6 / 3);
                if(v14 == -1) {
                    v15 = v13;
                    v16 = v11;
                    v17 = v8;
                    v18 = v9;
                    v19 = v3;
                    v20 = 0;
                    v21 = v12;
                    goto label_352;
                }
                else {
                    int[] arr_v = this.zzc;
                    int v22 = arr_v[v14 + 1];
                    int v23 = (v22 & 0xFF00000) >>> 20;
                    long v24 = (long)(v22 & 0xFFFFF);
                    int v25 = v22;
                    if(v23 <= 17) {
                        int v26 = arr_v[v14 + 2];
                        int v27 = 1 << (v26 >>> 20);
                        int v28 = v26 & 0xFFFFF;
                        if(v28 != v9) {
                            if(v9 != -1) {
                                unsafe0.putInt(object0, ((long)v9), v8);
                            }
                            v8 = unsafe0.getInt(object0, ((long)v28));
                            v9 = v28;
                        }
                        switch(v23) {
                            case 0: {
                                v29 = v14;
                                v30 = v13;
                                v31 = v9;
                                v32 = v12;
                                v33 = v11;
                                if((v12 & 7) == 1) {
                                    zzia.zza(object0, v24, zzdr.zzc(arr_b, v33));
                                    v4 = v33 + 8;
                                    v8 |= v27;
                                    v9 = v31;
                                    v7 = v32;
                                    v6 = v29;
                                    v5 = v30;
                                    v3 = v2;
                                    continue;
                                }
                                break;
                            }
                            case 1: {
                                v29 = v14;
                                v30 = v13;
                                v31 = v9;
                                v32 = v12;
                                v33 = v11;
                                if((v12 & 7) == 5) {
                                    zzia.zza(object0, v24, zzdr.zzd(arr_b, v33));
                                    v4 = v33 + 4;
                                    v8 |= v27;
                                    v9 = v31;
                                    v7 = v32;
                                    v6 = v29;
                                    v5 = v30;
                                    v3 = v2;
                                    continue;
                                }
                                break;
                            }
                            case 2: 
                            case 3: {
                                v29 = v14;
                                v30 = v13;
                                v31 = v9;
                                v32 = v12;
                                v33 = v11;
                                if((v12 & 7) == 0) {
                                    int v34 = zzdr.zzb(arr_b, v33, zzdq0);
                                    unsafe0.putLong(object0, v24, zzdq0.zzb);
                                    v8 |= v27;
                                    v4 = v34;
                                    v7 = v32;
                                    v6 = v29;
                                    v5 = v30;
                                    v3 = v2;
                                    v9 = v31;
                                    continue;
                                }
                                break;
                            }
                            case 7: {
                            label_139:
                                v29 = v14;
                                v30 = v13;
                                v32 = v12;
                                if((v12 & 7) == 0) {
                                    int v35 = zzdr.zzb(arr_b, v11, zzdq0);
                                    zzia.zza(object0, v24, zzdq0.zzb != 0L);
                                    v8 |= v27;
                                    v4 = v35;
                                    v7 = v32;
                                    v6 = v29;
                                    v5 = v30;
                                    v3 = v2;
                                    continue;
                                }
                                else {
                                    v31 = v9;
                                    v33 = v11;
                                    break;
                                }
                                goto label_155;
                            }
                            case 8: {
                            label_155:
                                v29 = v14;
                                v30 = v13;
                                v32 = v12;
                                if((v12 & 7) == 2) {
                                    v4 = (v25 & 0x20000000) == 0 ? zzdr.zzc(arr_b, v11, zzdq0) : zzdr.zzd(arr_b, v11, zzdq0);
                                    unsafe0.putObject(object0, v24, zzdq0.zzc);
                                    v8 |= v27;
                                    v7 = v32;
                                    v6 = v29;
                                    v5 = v30;
                                    v3 = v2;
                                    continue;
                                }
                                else {
                                    v31 = v9;
                                    v33 = v11;
                                    break;
                                }
                                goto label_170;
                            }
                            case 9: {
                            label_170:
                                v29 = v14;
                                v30 = v13;
                                v32 = v12;
                                if((v12 & 7) == 2) {
                                    v4 = zzdr.zza(this.zza(v29), arr_b, v11, v1, zzdq0);
                                    if((v8 & v27) == 0) {
                                        unsafe0.putObject(object0, v24, zzdq0.zzc);
                                    }
                                    else {
                                        unsafe0.putObject(object0, v24, zzfe.zza(unsafe0.getObject(object0, v24), zzdq0.zzc));
                                    }
                                    v8 |= v27;
                                    v7 = v32;
                                    v6 = v29;
                                    v5 = v30;
                                    v3 = v2;
                                    continue;
                                }
                                else {
                                    v31 = v9;
                                    v33 = v11;
                                    break;
                                }
                                goto label_188;
                            }
                            case 10: {
                            label_188:
                                v29 = v14;
                                v30 = v13;
                                v32 = v12;
                                if((v12 & 7) == 2) {
                                    v4 = zzdr.zze(arr_b, v11, zzdq0);
                                    unsafe0.putObject(object0, v24, zzdq0.zzc);
                                    v8 |= v27;
                                    v7 = v32;
                                    v6 = v29;
                                    v5 = v30;
                                    v3 = v2;
                                    continue;
                                }
                                else {
                                    v31 = v9;
                                    v33 = v11;
                                    break;
                                }
                                goto label_203;
                            }
                            case 4: 
                            case 11: {
                                v29 = v14;
                                v30 = v13;
                                v31 = v9;
                                v32 = v12;
                                v33 = v11;
                                if((v12 & 7) == 0) {
                                    v4 = zzdr.zza(arr_b, v33, zzdq0);
                                    unsafe0.putInt(object0, v24, zzdq0.zza);
                                    v8 |= v27;
                                    v9 = v31;
                                    v7 = v32;
                                    v6 = v29;
                                    v5 = v30;
                                    v3 = v2;
                                    continue;
                                }
                                break;
                            }
                            case 12: {
                            label_203:
                                v29 = v14;
                                v30 = v13;
                                v32 = v12;
                                if((v12 & 7) == 0) {
                                    v4 = zzdr.zza(arr_b, v11, zzdq0);
                                    int v36 = zzdq0.zza;
                                    zzfj zzfj0 = this.zzc(v29);
                                    if(zzfj0 == null || zzfj0.zza(v36)) {
                                        unsafe0.putInt(object0, v24, v36);
                                        v8 |= v27;
                                    }
                                    else {
                                        zzgr.zze(object0).zza(v32, ((long)v36));
                                    }
                                    v7 = v32;
                                    v6 = v29;
                                    v5 = v30;
                                    v3 = v2;
                                    continue;
                                }
                                else {
                                    v31 = v9;
                                    v33 = v11;
                                    break;
                                }
                                goto label_227;
                            }
                            case 6: 
                            case 13: {
                            label_124:
                                v29 = v14;
                                v30 = v13;
                                v32 = v12;
                                if((v12 & 7) == 5) {
                                    unsafe0.putInt(object0, v24, zzdr.zza(arr_b, v11));
                                    v4 = v11 + 4;
                                    v8 |= v27;
                                    v7 = v32;
                                    v6 = v29;
                                    v5 = v30;
                                    v3 = v2;
                                    continue;
                                }
                                else {
                                    v31 = v9;
                                    v33 = v11;
                                    break;
                                }
                                goto label_139;
                            }
                            case 5: 
                            case 14: {
                                v29 = v14;
                                v30 = v13;
                                v32 = v12;
                                if((v12 & 7) == 1) {
                                    unsafe0.putLong(object0, v24, zzdr.zzb(arr_b, v11));
                                    v4 = v11 + 8;
                                    v8 |= v27;
                                    v7 = v32;
                                    v6 = v29;
                                    v5 = v30;
                                    v3 = v2;
                                    continue;
                                }
                                else {
                                    v31 = v9;
                                    v33 = v11;
                                    break;
                                }
                                goto label_124;
                            }
                            case 15: {
                            label_227:
                                v29 = v14;
                                v30 = v13;
                                v32 = v12;
                                if((v12 & 7) == 0) {
                                    v4 = zzdr.zza(arr_b, v11, zzdq0);
                                    unsafe0.putInt(object0, v24, -(zzdq0.zza & 1) ^ zzdq0.zza >>> 1);
                                    v8 |= v27;
                                    v7 = v32;
                                    v6 = v29;
                                    v5 = v30;
                                    v3 = v2;
                                    continue;
                                }
                                else {
                                    v31 = v9;
                                    v33 = v11;
                                    break;
                                }
                                goto label_242;
                            }
                            case 16: {
                            label_242:
                                v29 = v14;
                                v30 = v13;
                                v32 = v12;
                                if((v12 & 7) == 0) {
                                    int v37 = zzdr.zzb(arr_b, v11, zzdq0);
                                    unsafe0.putLong(object0, v24, -(zzdq0.zzb & 1L) ^ zzdq0.zzb >>> 1);
                                    v8 |= v27;
                                    v4 = v37;
                                    v7 = v32;
                                    v6 = v29;
                                    v5 = v30;
                                    v3 = v2;
                                    continue;
                                }
                                else {
                                    v31 = v9;
                                    v33 = v11;
                                    break;
                                }
                                goto label_258;
                            }
                            case 17: {
                            label_258:
                                if((v12 & 7) == 3) {
                                    v4 = zzdr.zza(this.zza(v14), arr_b, v11, v1, v13 << 3 | 4, zzdq0);
                                    if((v8 & v27) == 0) {
                                        unsafe0.putObject(object0, v24, zzdq0.zzc);
                                    }
                                    else {
                                        unsafe0.putObject(object0, v24, zzfe.zza(unsafe0.getObject(object0, v24), zzdq0.zzc));
                                    }
                                    v8 |= v27;
                                    v7 = v12;
                                    v6 = v14;
                                    v5 = v13;
                                    v3 = v2;
                                    continue;
                                }
                                else {
                                    v29 = v14;
                                    v30 = v13;
                                    v32 = v12;
                                    v31 = v9;
                                    v33 = v11;
                                }
                                break;
                            }
                            default: {
                                v29 = v14;
                                v30 = v13;
                                v31 = v9;
                                v32 = v12;
                                v33 = v11;
                            }
                        }
                        v18 = v31;
                        v17 = v8;
                        v16 = v33;
                        v21 = v32;
                        v20 = v29;
                        v15 = v30;
                        v19 = v2;
                        goto label_352;
                    }
                    else {
                        v18 = v9;
                        if(v23 == 27) {
                            if((v12 & 7) == 2) {
                                zzfk zzfk0 = (zzfk)unsafe0.getObject(object0, v24);
                                if(zzfk0.zza()) {
                                    zzfk2 = zzfk0;
                                }
                                else {
                                    int v38 = zzfk0.size();
                                    zzfk zzfk1 = zzfk0.zza((v38 == 0 ? 10 : v38 << 1));
                                    unsafe0.putObject(object0, v24, zzfk1);
                                    zzfk2 = zzfk1;
                                }
                                v4 = zzdr.zza(this.zza(v14), v12, arr_b, v11, v1, zzfk2, zzdq0);
                                v5 = v13;
                                v7 = v12;
                                v6 = v14;
                                v9 = v18;
                                v3 = v2;
                                continue;
                            }
                            else {
                                v17 = v8;
                                v15 = v13;
                                v39 = v11;
                                v40 = v12;
                                v20 = v14;
                                v16 = v39;
                                v21 = v40;
                                v19 = v2;
                                goto label_352;
                            }
                            goto label_307;
                        }
                        else {
                        label_307:
                            v17 = v8;
                            if(v23 <= 49) {
                                v15 = v13;
                                v20 = v14;
                                v4 = this.zza(object0, arr_b, v11, v1, v12, v13, v12 & 7, v14, ((long)v25), v23, v24, zzdq0);
                                if(v4 == v11) {
                                    v16 = v4;
                                    v21 = v12;
                                    v19 = v2;
                                    goto label_352;
                                }
                                else {
                                    v9 = v18;
                                    v6 = v20;
                                    v8 = v17;
                                    v5 = v15;
                                    v7 = v12;
                                    v3 = v2;
                                    continue;
                                }
                                goto label_324;
                            }
                            else {
                            label_324:
                                v15 = v13;
                                v39 = v11;
                                v40 = v12;
                                v20 = v14;
                                if(v23 == 50) {
                                    if((v12 & 7) == 2) {
                                        v4 = this.zza(object0, arr_b, v39, v1, v20, v24, zzdq0);
                                        if(v4 == v39) {
                                            v16 = v4;
                                            v21 = v40;
                                            v19 = v2;
                                            goto label_352;
                                        }
                                        else {
                                            v9 = v18;
                                            v6 = v20;
                                            v8 = v17;
                                            v5 = v15;
                                            v7 = v40;
                                            v3 = v2;
                                            continue;
                                        }
                                    }
                                    v16 = v39;
                                    v21 = v40;
                                    v19 = v2;
                                    goto label_352;
                                }
                                else {
                                    v4 = this.zza(object0, arr_b, v39, v1, v40, v15, v12 & 7, v25, v23, v24, v20, zzdq0);
                                    if(v4 == v39) {
                                        v16 = v4;
                                        v21 = v40;
                                        v19 = v2;
                                    label_352:
                                        if(v21 == v19 && v19 != 0) {
                                            v7 = v21;
                                            v41 = v18;
                                            v42 = v17;
                                            break;
                                        }
                                        if(this.zzh) {
                                            zzeq zzeq0 = zzeq.zza();
                                            if(zzdq0.zzd == zzeq0) {
                                                v43 = v15;
                                                goto label_374;
                                            }
                                            else {
                                                if(zzdq0.zzd.zza(this.zzg, v15) == null) {
                                                    v4 = zzdr.zza(v21, arr_b, v16, v1, zzgr.zze(object0), zzdq0);
                                                    v3 = v19;
                                                    v7 = v21;
                                                    v5 = v15;
                                                    v9 = v18;
                                                    v6 = v20;
                                                    v8 = v17;
                                                    continue;
                                                }
                                                ((zzb)object0).zza();
                                                throw new NoSuchMethodError();
                                            }
                                            goto label_373;
                                        }
                                        else {
                                        label_373:
                                            v43 = v15;
                                        }
                                    label_374:
                                        v4 = zzdr.zza(v21, arr_b, v16, v1, zzgr.zze(object0), zzdq0);
                                        v7 = v21;
                                        v5 = v43;
                                        v9 = v18;
                                        v6 = v20;
                                        v3 = v19;
                                        v8 = v17;
                                    }
                                    else {
                                        v7 = v40;
                                        v5 = v15;
                                        v9 = v18;
                                        v6 = v20;
                                        v8 = v17;
                                        v3 = v2;
                                    }
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
            v19 = v3;
            v16 = v4;
            v41 = v9;
            v42 = v8;
            break;
        }
        if(v41 != -1) {
            unsafe0.putInt(object0, ((long)v41), v42);
        }
        zzhx zzhx0 = null;
        for(int v44 = this.zzm; v44 < this.zzn; ++v44) {
            zzhx0 = (zzhx)this.zza(object0, this.zzl[v44], zzhx0, this.zzq);
        }
        if(zzhx0 != null) {
            this.zzq.zzb(object0, zzhx0);
        }
        if(v19 == 0) {
            if(v16 != v1) {
                throw zzfn.zzg();
            }
            return v16;
        }
        if(v16 > v1 || v7 != v19) {
            throw zzfn.zzg();
        }
        return v16;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final Object zza() {
        return this.zzo.zza(this.zzg);
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final void zza(Object object0, zzhd zzhd0, zzeq zzeq0) throws IOException {
        int v4;
        int v3;
        int v1;
        int v;
        if(zzeq0 == null) {
            throw new NullPointerException();
        }
        zzhu zzhu0 = this.zzq;
        zzes zzes0 = this.zzr;
        zzet zzet0 = null;
        Object object1 = null;
        while(true) {
        alab1:
            while(true) {
                try {
                    while(true) {
                    label_5:
                        v = zzhd0.zza();
                        v1 = this.zzg(v);
                        if(v1 >= 0) {
                            goto label_35;
                        }
                        if(v == 0x7FFFFFFF) {
                            goto label_27;
                        }
                        Object object2 = this.zzh ? zzes0.zza(zzeq0, this.zzg, v) : null;
                        if(object2 == null) {
                            zzhu0.zza(zzhd0);
                            if(object1 == null) {
                                object1 = zzhu0.zzc(object0);
                            }
                            if(!zzhu0.zza(object1, zzhd0)) {
                                break;
                            }
                        }
                        else {
                            zzet zzet1 = zzet0 == null ? zzes0.zzb(object0) : zzet0;
                            object1 = zzes0.zza(zzhd0, object2, zzeq0, zzet1, object1, zzhu0);
                            zzet0 = zzet1;
                        }
                    }
                }
                catch(Throwable throwable0) {
                    goto label_276;
                }
                for(int v2 = this.zzm; v2 < this.zzn; ++v2) {
                    object1 = this.zza(object0, this.zzl[v2], object1, zzhu0);
                }
                if(object1 != null) {
                    zzhu0.zzb(object0, object1);
                }
                return;
                try {
                label_27:
                    v3 = this.zzm;
                }
                catch(Throwable throwable0) {
                    goto label_276;
                }
                while(v3 < this.zzn) {
                    object1 = this.zza(object0, this.zzl[v3], object1, zzhu0);
                    ++v3;
                }
                if(object1 != null) {
                    zzhu0.zzb(object0, object1);
                }
                return;
                try {
                label_35:
                    v4 = this.zzd(v1);
                    switch((0xFF00000 & v4) >>> 20) {
                        case 0: {
                            goto label_48;
                        }
                        case 1: {
                            goto label_51;
                        }
                        case 2: {
                            goto label_54;
                        }
                        case 3: {
                            goto label_57;
                        }
                        case 4: {
                            goto label_60;
                        }
                        case 5: {
                            goto label_63;
                        }
                        case 6: {
                            goto label_66;
                        }
                        case 7: {
                            goto label_69;
                        }
                        case 8: {
                            goto label_72;
                        }
                        case 9: {
                            goto label_75;
                        }
                        case 10: {
                            goto label_81;
                        }
                        case 11: {
                            goto label_84;
                        }
                        case 12: {
                            goto label_87;
                        }
                        case 13: {
                            goto label_95;
                        }
                        case 14: {
                            goto label_98;
                        }
                        case 15: {
                            goto label_101;
                        }
                        case 16: {
                            goto label_104;
                        }
                        case 17: {
                            goto label_107;
                        }
                        case 18: {
                            goto label_113;
                        }
                        case 19: {
                            goto label_115;
                        }
                        case 20: {
                            goto label_117;
                        }
                        case 21: {
                            goto label_119;
                        }
                        case 22: {
                            goto label_121;
                        }
                        case 23: {
                            goto label_123;
                        }
                        case 24: {
                            goto label_125;
                        }
                        case 25: {
                            goto label_127;
                        }
                        case 26: {
                            goto label_129;
                        }
                        case 27: {
                            goto label_134;
                        }
                        case 28: {
                            goto label_137;
                        }
                        case 29: {
                            goto label_139;
                        }
                        case 30: {
                            goto label_141;
                        }
                        case 0x1F: {
                            goto label_145;
                        }
                        case 0x20: {
                            goto label_147;
                        }
                        case 33: {
                            goto label_149;
                        }
                        case 34: {
                            goto label_151;
                        }
                        case 35: {
                            goto label_153;
                        }
                        case 36: {
                            goto label_155;
                        }
                        case 37: {
                            goto label_157;
                        }
                        case 38: {
                            goto label_159;
                        }
                        case 39: {
                            goto label_161;
                        }
                        case 40: {
                            goto label_163;
                        }
                        case 41: {
                            goto label_165;
                        }
                        case 42: {
                            goto label_167;
                        }
                        case 43: {
                            goto label_169;
                        }
                        case 44: {
                            goto label_171;
                        }
                        case 45: {
                            goto label_175;
                        }
                        case 46: {
                            goto label_177;
                        }
                        case 0x2F: {
                            goto label_179;
                        }
                        case 0x30: {
                            goto label_181;
                        }
                        case 49: {
                            goto label_183;
                        }
                        case 50: {
                            goto label_186;
                        }
                        case 51: {
                            goto label_200;
                        }
                        case 52: {
                            goto label_203;
                        }
                        case 53: {
                            goto label_206;
                        }
                        case 54: {
                            goto label_209;
                        }
                        case 55: {
                            goto label_212;
                        }
                        case 56: {
                            goto label_215;
                        }
                        case 57: {
                            goto label_218;
                        }
                        case 58: {
                            goto label_221;
                        }
                        case 59: {
                            goto label_224;
                        }
                        case 60: {
                            goto label_227;
                        }
                        case 61: {
                            goto label_234;
                        }
                        case 62: {
                            goto label_237;
                        }
                        case 0x3F: {
                            goto label_240;
                        }
                        case 0x40: {
                            goto label_248;
                        }
                        case 65: {
                            goto label_251;
                        }
                        case 66: {
                            goto label_254;
                        }
                        case 67: {
                            goto label_257;
                        }
                        case 68: {
                            goto label_260;
                        }
                        default: {
                            try {
                                if(object1 == null) {
                                    object1 = zzhu0.zza();
                                }
                                if(zzhu0.zza(object1, zzhd0)) {
                                    break;
                                }
                                break alab1;
                            }
                            catch(zzfm unused_ex) {
                                goto label_263;
                            }
                        }
                    }
                }
                catch(Throwable throwable0) {
                    goto label_276;
                }
            }
            for(int v5 = this.zzm; v5 < this.zzn; ++v5) {
                object1 = this.zza(object0, this.zzl[v5], object1, zzhu0);
            }
            if(object1 != null) {
                zzhu0.zzb(object0, object1);
            }
            return;
            try {
                try {
                label_48:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzd());
                    this.zzb(object0, v1);
                    goto label_5;
                label_51:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zze());
                    this.zzb(object0, v1);
                    goto label_5;
                label_54:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzg());
                    this.zzb(object0, v1);
                    goto label_5;
                label_57:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzf());
                    this.zzb(object0, v1);
                    goto label_5;
                label_60:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzh());
                    this.zzb(object0, v1);
                    goto label_5;
                label_63:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzi());
                    this.zzb(object0, v1);
                    goto label_5;
                label_66:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzj());
                    this.zzb(object0, v1);
                    goto label_5;
                label_69:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzk());
                    this.zzb(object0, v1);
                    goto label_5;
                label_72:
                    this.zza(object0, v4, zzhd0);
                    this.zzb(object0, v1);
                    goto label_5;
                label_75:
                    if(this.zza(object0, v1)) {
                        zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzfe.zza(zzia.zzf(object0, ((long)(v4 & 0xFFFFF))), zzhd0.zza(this.zza(v1), zzeq0)));
                    }
                    else {
                        zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zza(this.zza(v1), zzeq0));
                        this.zzb(object0, v1);
                    }
                    goto label_5;
                label_81:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzn());
                    this.zzb(object0, v1);
                    goto label_5;
                label_84:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzo());
                    this.zzb(object0, v1);
                    goto label_5;
                label_87:
                    int v6 = zzhd0.zzp();
                    zzfj zzfj0 = this.zzc(v1);
                    if(zzfj0 == null || zzfj0.zza(v6)) {
                        zzia.zza(object0, ((long)(v4 & 0xFFFFF)), v6);
                        this.zzb(object0, v1);
                    }
                    else {
                        object1 = zzhe.zza(v, v6, object1, zzhu0);
                    }
                    goto label_5;
                label_95:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzq());
                    this.zzb(object0, v1);
                    goto label_5;
                label_98:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzr());
                    this.zzb(object0, v1);
                    goto label_5;
                label_101:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzs());
                    this.zzb(object0, v1);
                    goto label_5;
                label_104:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzt());
                    this.zzb(object0, v1);
                    goto label_5;
                label_107:
                    if(this.zza(object0, v1)) {
                        zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzfe.zza(zzia.zzf(object0, ((long)(v4 & 0xFFFFF))), zzhd0.zzb(this.zza(v1), zzeq0)));
                    }
                    else {
                        zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzb(this.zza(v1), zzeq0));
                        this.zzb(object0, v1);
                    }
                    goto label_5;
                label_113:
                    zzhd0.zza(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_115:
                    zzhd0.zzb(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_117:
                    zzhd0.zzd(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_119:
                    zzhd0.zzc(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_121:
                    zzhd0.zze(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_123:
                    zzhd0.zzf(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_125:
                    zzhd0.zzg(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_127:
                    zzhd0.zzh(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_129:
                    if(zzgr.zzf(v4)) {
                        zzhd0.zzj(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    }
                    else {
                        zzhd0.zzi(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    }
                    goto label_5;
                label_134:
                    zzhc zzhc0 = this.zza(v1);
                    zzhd0.zza(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))), zzhc0, zzeq0);
                    goto label_5;
                label_137:
                    zzhd0.zzk(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_139:
                    zzhd0.zzl(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_141:
                    List list0 = this.zzp.zza(object0, ((long)(v4 & 0xFFFFF)));
                    zzhd0.zzm(list0);
                    object1 = zzhe.zza(v, list0, this.zzc(v1), object1, zzhu0);
                    goto label_5;
                label_145:
                    zzhd0.zzn(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_147:
                    zzhd0.zzo(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_149:
                    zzhd0.zzp(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_151:
                    zzhd0.zzq(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_153:
                    zzhd0.zza(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_155:
                    zzhd0.zzb(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_157:
                    zzhd0.zzd(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_159:
                    zzhd0.zzc(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_161:
                    zzhd0.zze(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_163:
                    zzhd0.zzf(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_165:
                    zzhd0.zzg(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_167:
                    zzhd0.zzh(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_169:
                    zzhd0.zzl(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_171:
                    List list1 = this.zzp.zza(object0, ((long)(v4 & 0xFFFFF)));
                    zzhd0.zzm(list1);
                    object1 = zzhe.zza(v, list1, this.zzc(v1), object1, zzhu0);
                    goto label_5;
                label_175:
                    zzhd0.zzn(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_177:
                    zzhd0.zzo(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_179:
                    zzhd0.zzp(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_181:
                    zzhd0.zzq(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_183:
                    zzhc zzhc1 = this.zza(v1);
                    zzhd0.zzb(this.zzp.zza(object0, ((long)(v4 & 0xFFFFF))), zzhc1, zzeq0);
                    goto label_5;
                label_186:
                    Object object3 = this.zzb(v1);
                    long v7 = (long)(this.zzd(v1) & 0xFFFFF);
                    Object object4 = zzia.zzf(object0, v7);
                    if(object4 == null) {
                        object4 = this.zzs.zze(object3);
                        zzia.zza(object0, v7, object4);
                    }
                    else if(this.zzs.zzc(object4)) {
                        Object object5 = this.zzs.zze(object3);
                        this.zzs.zza(object5, object4);
                        zzia.zza(object0, v7, object5);
                        object4 = object5;
                    }
                    zzhd0.zza(this.zzs.zza(object4), this.zzs.zzf(object3), zzeq0);
                    goto label_5;
                label_200:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzd());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_203:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zze());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_206:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzg());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_209:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzf());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_212:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzh());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_215:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzi());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_218:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzj());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_221:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), Boolean.valueOf(zzhd0.zzk()));
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_224:
                    this.zza(object0, v4, zzhd0);
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_227:
                    if(this.zza(object0, v, v1)) {
                        zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzfe.zza(zzia.zzf(object0, ((long)(v4 & 0xFFFFF))), zzhd0.zza(this.zza(v1), zzeq0)));
                    }
                    else {
                        zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zza(this.zza(v1), zzeq0));
                        this.zzb(object0, v1);
                    }
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_234:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzn());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_237:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzo());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_240:
                    int v8 = zzhd0.zzp();
                    zzfj zzfj1 = this.zzc(v1);
                    if(zzfj1 == null || zzfj1.zza(v8)) {
                        zzia.zza(object0, ((long)(v4 & 0xFFFFF)), v8);
                        this.zzb(object0, v, v1);
                    }
                    else {
                        object1 = zzhe.zza(v, v8, object1, zzhu0);
                    }
                    goto label_5;
                label_248:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzq());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_251:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzr());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_254:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzs());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_257:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzt());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_260:
                    zzia.zza(object0, ((long)(v4 & 0xFFFFF)), zzhd0.zzb(this.zza(v1), zzeq0));
                    this.zzb(object0, v, v1);
                    goto label_5;
                }
                catch(zzfm unused_ex) {
                }
            label_263:
                zzhu0.zza(zzhd0);
                if(object1 == null) {
                    object1 = zzhu0.zzc(object0);
                }
                if(zzhu0.zza(object1, zzhd0)) {
                    goto label_5;
                }
                break;
            }
            catch(Throwable throwable0) {
                goto label_276;
            }
        }
        for(int v9 = this.zzm; v9 < this.zzn; ++v9) {
            object1 = this.zza(object0, this.zzl[v9], object1, zzhu0);
        }
        if(object1 != null) {
            zzhu0.zzb(object0, object1);
        }
        return;
    label_276:
        for(int v10 = this.zzm; v10 < this.zzn; ++v10) {
            object1 = this.zza(object0, this.zzl[v10], object1, zzhu0);
        }
        if(object1 != null) {
            zzhu0.zzb(object0, object1);
        }
        throw throwable0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final void zza(Object object0, zzir zzir0) throws IOException {
        Map.Entry map$Entry1;
        Iterator iterator1;
        Map.Entry map$Entry0;
        Iterator iterator0;
        if(zzir0.zza() == zzd.zzk) {
            zzgr.zza(this.zzq, object0, zzir0);
            if(this.zzh) {
                zzet zzet0 = this.zzr.zza(object0);
                if(zzet0.zza.isEmpty()) {
                    iterator0 = null;
                    map$Entry0 = null;
                }
                else {
                    iterator0 = zzet0.zze();
                    Object object1 = iterator0.next();
                    map$Entry0 = (Map.Entry)object1;
                }
            }
            else {
                iterator0 = null;
                map$Entry0 = null;
            }
            for(int v = this.zzc.length - 3; v >= 0; v -= 3) {
                int v1 = this.zzd(v);
                int v2 = this.zzc[v];
                while(map$Entry0 != null && this.zzr.zza(map$Entry0) > v2) {
                    this.zzr.zza(zzir0, map$Entry0);
                    if(iterator0.hasNext()) {
                        Object object2 = iterator0.next();
                        map$Entry0 = (Map.Entry)object2;
                    }
                    else {
                        map$Entry0 = null;
                    }
                }
                switch((v1 & 0xFF00000) >>> 20) {
                    case 0: {
                        if(this.zza(object0, v)) {
                            zzir0.zza(v2, zzia.zze(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 1: {
                        if(this.zza(object0, v)) {
                            zzir0.zza(v2, zzia.zzd(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 2: {
                        if(this.zza(object0, v)) {
                            zzir0.zza(v2, zzia.zzb(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 3: {
                        if(this.zza(object0, v)) {
                            zzir0.zzc(v2, zzia.zzb(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 4: {
                        if(this.zza(object0, v)) {
                            zzir0.zzc(v2, zzia.zza(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 5: {
                        if(this.zza(object0, v)) {
                            zzir0.zzd(v2, zzia.zzb(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 6: {
                        if(this.zza(object0, v)) {
                            zzir0.zzd(v2, zzia.zza(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 7: {
                        if(this.zza(object0, v)) {
                            zzir0.zza(v2, zzia.zzc(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 8: {
                        if(this.zza(object0, v)) {
                            zzgr.zza(v2, zzia.zzf(object0, ((long)(v1 & 0xFFFFF))), zzir0);
                        }
                        break;
                    }
                    case 9: {
                        if(this.zza(object0, v)) {
                            zzir0.zza(v2, zzia.zzf(object0, ((long)(v1 & 0xFFFFF))), this.zza(v));
                        }
                        break;
                    }
                    case 10: {
                        if(this.zza(object0, v)) {
                            zzir0.zza(v2, ((zzdv)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))));
                        }
                        break;
                    }
                    case 11: {
                        if(this.zza(object0, v)) {
                            zzir0.zze(v2, zzia.zza(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 12: {
                        if(this.zza(object0, v)) {
                            zzir0.zzb(v2, zzia.zza(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 13: {
                        if(this.zza(object0, v)) {
                            zzir0.zza(v2, zzia.zza(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 14: {
                        if(this.zza(object0, v)) {
                            zzir0.zzb(v2, zzia.zzb(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 15: {
                        if(this.zza(object0, v)) {
                            zzir0.zzf(v2, zzia.zza(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 16: {
                        if(this.zza(object0, v)) {
                            zzir0.zze(v2, zzia.zzb(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 17: {
                        if(this.zza(object0, v)) {
                            zzir0.zzb(v2, zzia.zzf(object0, ((long)(v1 & 0xFFFFF))), this.zza(v));
                        }
                        break;
                    }
                    case 18: {
                        zzhe.zza(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 19: {
                        zzhe.zzb(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 20: {
                        zzhe.zzc(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 21: {
                        zzhe.zzd(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 22: {
                        zzhe.zzh(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 23: {
                        zzhe.zzf(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 24: {
                        zzhe.zzk(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 25: {
                        zzhe.zzn(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 26: {
                        zzhe.zza(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0);
                        break;
                    }
                    case 27: {
                        zzhe.zza(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, this.zza(v));
                        break;
                    }
                    case 28: {
                        zzhe.zzb(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0);
                        break;
                    }
                    case 29: {
                        zzhe.zzi(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 30: {
                        zzhe.zzm(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 0x1F: {
                        zzhe.zzl(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 0x20: {
                        zzhe.zzg(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 33: {
                        zzhe.zzj(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 34: {
                        zzhe.zze(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 35: {
                        zzhe.zza(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 36: {
                        zzhe.zzb(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 37: {
                        zzhe.zzc(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 38: {
                        zzhe.zzd(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 39: {
                        zzhe.zzh(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 40: {
                        zzhe.zzf(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 41: {
                        zzhe.zzk(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 42: {
                        zzhe.zzn(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 43: {
                        zzhe.zzi(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 44: {
                        zzhe.zzm(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 45: {
                        zzhe.zzl(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 46: {
                        zzhe.zzg(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 0x2F: {
                        zzhe.zzj(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 0x30: {
                        zzhe.zze(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 49: {
                        zzhe.zzb(this.zzc[v], ((List)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))), zzir0, this.zza(v));
                        break;
                    }
                    case 50: {
                        this.zza(zzir0, v2, zzia.zzf(object0, ((long)(v1 & 0xFFFFF))), v);
                        break;
                    }
                    case 51: {
                        if(this.zza(object0, v2, v)) {
                            zzir0.zza(v2, zzgr.zzb(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 52: {
                        if(this.zza(object0, v2, v)) {
                            zzir0.zza(v2, zzgr.zzc(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 53: {
                        if(this.zza(object0, v2, v)) {
                            zzir0.zza(v2, zzgr.zze(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 54: {
                        if(this.zza(object0, v2, v)) {
                            zzir0.zzc(v2, zzgr.zze(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 55: {
                        if(this.zza(object0, v2, v)) {
                            zzir0.zzc(v2, zzgr.zzd(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 56: {
                        if(this.zza(object0, v2, v)) {
                            zzir0.zzd(v2, zzgr.zze(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 57: {
                        if(this.zza(object0, v2, v)) {
                            zzir0.zzd(v2, zzgr.zzd(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 58: {
                        if(this.zza(object0, v2, v)) {
                            zzir0.zza(v2, zzgr.zzf(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 59: {
                        if(this.zza(object0, v2, v)) {
                            zzgr.zza(v2, zzia.zzf(object0, ((long)(v1 & 0xFFFFF))), zzir0);
                        }
                        break;
                    }
                    case 60: {
                        if(this.zza(object0, v2, v)) {
                            zzir0.zza(v2, zzia.zzf(object0, ((long)(v1 & 0xFFFFF))), this.zza(v));
                        }
                        break;
                    }
                    case 61: {
                        if(this.zza(object0, v2, v)) {
                            zzir0.zza(v2, ((zzdv)zzia.zzf(object0, ((long)(v1 & 0xFFFFF)))));
                        }
                        break;
                    }
                    case 62: {
                        if(this.zza(object0, v2, v)) {
                            zzir0.zze(v2, zzgr.zzd(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 0x3F: {
                        if(this.zza(object0, v2, v)) {
                            zzir0.zzb(v2, zzgr.zzd(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 0x40: {
                        if(this.zza(object0, v2, v)) {
                            zzir0.zza(v2, zzgr.zzd(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 65: {
                        if(this.zza(object0, v2, v)) {
                            zzir0.zzb(v2, zzgr.zze(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 66: {
                        if(this.zza(object0, v2, v)) {
                            zzir0.zzf(v2, zzgr.zzd(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 67: {
                        if(this.zza(object0, v2, v)) {
                            zzir0.zze(v2, zzgr.zze(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 68: {
                        if(this.zza(object0, v2, v)) {
                            zzir0.zzb(v2, zzia.zzf(object0, ((long)(v1 & 0xFFFFF))), this.zza(v));
                        }
                    }
                }
            }
            while(map$Entry0 != null) {
                this.zzr.zza(zzir0, map$Entry0);
                if(iterator0.hasNext()) {
                    Object object3 = iterator0.next();
                    map$Entry0 = (Map.Entry)object3;
                }
                else {
                    map$Entry0 = null;
                }
            }
            return;
        }
        if(this.zzj) {
            if(this.zzh) {
                zzet zzet1 = this.zzr.zza(object0);
                if(zzet1.zza.isEmpty()) {
                    iterator1 = null;
                    map$Entry1 = null;
                }
                else {
                    iterator1 = zzet1.zzd();
                    Object object4 = iterator1.next();
                    map$Entry1 = (Map.Entry)object4;
                }
            }
            else {
                iterator1 = null;
                map$Entry1 = null;
            }
            Map.Entry map$Entry2 = map$Entry1;
            for(int v3 = 0; v3 < this.zzc.length; v3 += 3) {
                int v4 = this.zzd(v3);
                int v5 = this.zzc[v3];
                while(map$Entry2 != null && this.zzr.zza(map$Entry2) <= v5) {
                    this.zzr.zza(zzir0, map$Entry2);
                    if(iterator1.hasNext()) {
                        Object object5 = iterator1.next();
                        map$Entry2 = (Map.Entry)object5;
                    }
                    else {
                        map$Entry2 = null;
                    }
                }
                switch((v4 & 0xFF00000) >>> 20) {
                    case 0: {
                        if(this.zza(object0, v3)) {
                            zzir0.zza(v5, zzia.zze(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 1: {
                        if(this.zza(object0, v3)) {
                            zzir0.zza(v5, zzia.zzd(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 2: {
                        if(this.zza(object0, v3)) {
                            zzir0.zza(v5, zzia.zzb(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 3: {
                        if(this.zza(object0, v3)) {
                            zzir0.zzc(v5, zzia.zzb(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 4: {
                        if(this.zza(object0, v3)) {
                            zzir0.zzc(v5, zzia.zza(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 5: {
                        if(this.zza(object0, v3)) {
                            zzir0.zzd(v5, zzia.zzb(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 6: {
                        if(this.zza(object0, v3)) {
                            zzir0.zzd(v5, zzia.zza(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 7: {
                        if(this.zza(object0, v3)) {
                            zzir0.zza(v5, zzia.zzc(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 8: {
                        if(this.zza(object0, v3)) {
                            zzgr.zza(v5, zzia.zzf(object0, ((long)(v4 & 0xFFFFF))), zzir0);
                        }
                        break;
                    }
                    case 9: {
                        if(this.zza(object0, v3)) {
                            zzir0.zza(v5, zzia.zzf(object0, ((long)(v4 & 0xFFFFF))), this.zza(v3));
                        }
                        break;
                    }
                    case 10: {
                        if(this.zza(object0, v3)) {
                            zzir0.zza(v5, ((zzdv)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))));
                        }
                        break;
                    }
                    case 11: {
                        if(this.zza(object0, v3)) {
                            zzir0.zze(v5, zzia.zza(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 12: {
                        if(this.zza(object0, v3)) {
                            zzir0.zzb(v5, zzia.zza(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 13: {
                        if(this.zza(object0, v3)) {
                            zzir0.zza(v5, zzia.zza(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 14: {
                        if(this.zza(object0, v3)) {
                            zzir0.zzb(v5, zzia.zzb(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 15: {
                        if(this.zza(object0, v3)) {
                            zzir0.zzf(v5, zzia.zza(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 16: {
                        if(this.zza(object0, v3)) {
                            zzir0.zze(v5, zzia.zzb(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 17: {
                        if(this.zza(object0, v3)) {
                            zzir0.zzb(v5, zzia.zzf(object0, ((long)(v4 & 0xFFFFF))), this.zza(v3));
                        }
                        break;
                    }
                    case 18: {
                        zzhe.zza(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 19: {
                        zzhe.zzb(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 20: {
                        zzhe.zzc(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 21: {
                        zzhe.zzd(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 22: {
                        zzhe.zzh(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 23: {
                        zzhe.zzf(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 24: {
                        zzhe.zzk(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 25: {
                        zzhe.zzn(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 26: {
                        zzhe.zza(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0);
                        break;
                    }
                    case 27: {
                        zzhe.zza(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, this.zza(v3));
                        break;
                    }
                    case 28: {
                        zzhe.zzb(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0);
                        break;
                    }
                    case 29: {
                        zzhe.zzi(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 30: {
                        zzhe.zzm(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 0x1F: {
                        zzhe.zzl(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 0x20: {
                        zzhe.zzg(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 33: {
                        zzhe.zzj(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 34: {
                        zzhe.zze(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, false);
                        break;
                    }
                    case 35: {
                        zzhe.zza(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 36: {
                        zzhe.zzb(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 37: {
                        zzhe.zzc(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 38: {
                        zzhe.zzd(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 39: {
                        zzhe.zzh(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 40: {
                        zzhe.zzf(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 41: {
                        zzhe.zzk(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 42: {
                        zzhe.zzn(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 43: {
                        zzhe.zzi(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 44: {
                        zzhe.zzm(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 45: {
                        zzhe.zzl(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 46: {
                        zzhe.zzg(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 0x2F: {
                        zzhe.zzj(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 0x30: {
                        zzhe.zze(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, true);
                        break;
                    }
                    case 49: {
                        zzhe.zzb(this.zzc[v3], ((List)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))), zzir0, this.zza(v3));
                        break;
                    }
                    case 50: {
                        this.zza(zzir0, v5, zzia.zzf(object0, ((long)(v4 & 0xFFFFF))), v3);
                        break;
                    }
                    case 51: {
                        if(this.zza(object0, v5, v3)) {
                            zzir0.zza(v5, zzgr.zzb(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 52: {
                        if(this.zza(object0, v5, v3)) {
                            zzir0.zza(v5, zzgr.zzc(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 53: {
                        if(this.zza(object0, v5, v3)) {
                            zzir0.zza(v5, zzgr.zze(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 54: {
                        if(this.zza(object0, v5, v3)) {
                            zzir0.zzc(v5, zzgr.zze(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 55: {
                        if(this.zza(object0, v5, v3)) {
                            zzir0.zzc(v5, zzgr.zzd(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 56: {
                        if(this.zza(object0, v5, v3)) {
                            zzir0.zzd(v5, zzgr.zze(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 57: {
                        if(this.zza(object0, v5, v3)) {
                            zzir0.zzd(v5, zzgr.zzd(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 58: {
                        if(this.zza(object0, v5, v3)) {
                            zzir0.zza(v5, zzgr.zzf(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 59: {
                        if(this.zza(object0, v5, v3)) {
                            zzgr.zza(v5, zzia.zzf(object0, ((long)(v4 & 0xFFFFF))), zzir0);
                        }
                        break;
                    }
                    case 60: {
                        if(this.zza(object0, v5, v3)) {
                            zzir0.zza(v5, zzia.zzf(object0, ((long)(v4 & 0xFFFFF))), this.zza(v3));
                        }
                        break;
                    }
                    case 61: {
                        if(this.zza(object0, v5, v3)) {
                            zzir0.zza(v5, ((zzdv)zzia.zzf(object0, ((long)(v4 & 0xFFFFF)))));
                        }
                        break;
                    }
                    case 62: {
                        if(this.zza(object0, v5, v3)) {
                            zzir0.zze(v5, zzgr.zzd(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 0x3F: {
                        if(this.zza(object0, v5, v3)) {
                            zzir0.zzb(v5, zzgr.zzd(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 0x40: {
                        if(this.zza(object0, v5, v3)) {
                            zzir0.zza(v5, zzgr.zzd(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 65: {
                        if(this.zza(object0, v5, v3)) {
                            zzir0.zzb(v5, zzgr.zze(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 66: {
                        if(this.zza(object0, v5, v3)) {
                            zzir0.zzf(v5, zzgr.zzd(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 67: {
                        if(this.zza(object0, v5, v3)) {
                            zzir0.zze(v5, zzgr.zze(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 68: {
                        if(this.zza(object0, v5, v3)) {
                            zzir0.zzb(v5, zzia.zzf(object0, ((long)(v4 & 0xFFFFF))), this.zza(v3));
                        }
                    }
                }
            }
            while(map$Entry2 != null) {
                this.zzr.zza(zzir0, map$Entry2);
                if(iterator1.hasNext()) {
                    Object object6 = iterator1.next();
                    map$Entry2 = (Map.Entry)object6;
                }
                else {
                    map$Entry2 = null;
                }
            }
            zzgr.zza(this.zzq, object0, zzir0);
            return;
        }
        this.zzb(object0, zzir0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final void zza(Object object0, byte[] arr_b, int v, int v1, zzdq zzdq0) throws IOException {
        zzfk zzfk2;
        int v16;
        int v12;
        Unsafe unsafe1;
        int v11;
        int v10;
        int v7;
        int v6;
        if(this.zzj) {
            Unsafe unsafe0 = zzgr.zzb;
            int v2 = v;
            int v3 = -1;
            int v4 = 0;
            while(v2 < v1) {
                int v5 = arr_b[v2];
                if(v5 < 0) {
                    v6 = zzdr.zza(v5, arr_b, v2 + 1, zzdq0);
                    v7 = zzdq0.zza;
                }
                else {
                    v7 = v5;
                    v6 = v2 + 1;
                }
                int v8 = v7 >>> 3;
                int v9 = v8 <= v3 ? this.zzg(v8) : this.zza(v8, v4 / 3);
                if(v9 == -1) {
                    v10 = v8;
                    v11 = v6;
                    unsafe1 = unsafe0;
                    v12 = 0;
                }
                else {
                    int v13 = this.zzc[v9 + 1];
                    int v14 = (0xFF00000 & v13) >>> 20;
                    long v15 = (long)(0xFFFFF & v13);
                    if(v14 <= 17) {
                        boolean z = true;
                        switch(v14) {
                            case 0: {
                                if((v7 & 7) == 1) {
                                    zzia.zza(object0, v15, zzdr.zzc(arr_b, v6));
                                    v2 = v6 + 8;
                                    v3 = v8;
                                    v4 = v9;
                                    continue;
                                }
                                else {
                                    v10 = v8;
                                    v16 = v6;
                                    unsafe1 = unsafe0;
                                    v12 = v9;
                                    break;
                                }
                                goto label_43;
                            }
                            case 1: {
                            label_43:
                                if((v7 & 7) == 5) {
                                    zzia.zza(object0, v15, zzdr.zzd(arr_b, v6));
                                    v2 = v6 + 4;
                                    v3 = v8;
                                    v4 = v9;
                                    continue;
                                }
                                else {
                                    v10 = v8;
                                    v16 = v6;
                                    unsafe1 = unsafe0;
                                    v12 = v9;
                                    break;
                                }
                                goto label_54;
                            }
                            case 2: 
                            case 3: {
                            label_54:
                                if((v7 & 7) == 0) {
                                    int v17 = zzdr.zzb(arr_b, v6, zzdq0);
                                    unsafe0.putLong(object0, v15, zzdq0.zzb);
                                    v2 = v17;
                                    v3 = v8;
                                    v4 = v9;
                                    continue;
                                }
                                else {
                                    v10 = v8;
                                    v16 = v6;
                                    unsafe1 = unsafe0;
                                    v12 = v9;
                                    break;
                                }
                                goto label_66;
                            }
                            case 7: {
                            label_99:
                                if((v7 & 7) == 0) {
                                    int v18 = zzdr.zzb(arr_b, v6, zzdq0);
                                    if(zzdq0.zzb == 0L) {
                                        z = false;
                                    }
                                    zzia.zza(object0, v15, z);
                                    v2 = v18;
                                    v4 = v9;
                                    v3 = v8;
                                    continue;
                                }
                                else {
                                    v12 = v9;
                                    v10 = v8;
                                    v16 = v6;
                                    unsafe1 = unsafe0;
                                    break;
                                }
                                goto label_113;
                            }
                            case 8: {
                            label_113:
                                if((v7 & 7) == 2) {
                                    v2 = (0x20000000 & v13) == 0 ? zzdr.zzc(arr_b, v6, zzdq0) : zzdr.zzd(arr_b, v6, zzdq0);
                                    unsafe0.putObject(object0, v15, zzdq0.zzc);
                                    v4 = v9;
                                    v3 = v8;
                                    continue;
                                }
                                else {
                                    v12 = v9;
                                    v10 = v8;
                                    v16 = v6;
                                    unsafe1 = unsafe0;
                                    break;
                                }
                                goto label_124;
                            }
                            case 9: {
                            label_124:
                                if((v7 & 7) == 2) {
                                    v2 = zzdr.zza(this.zza(v9), arr_b, v6, v1, zzdq0);
                                    Object object1 = unsafe0.getObject(object0, v15);
                                    if(object1 == null) {
                                        unsafe0.putObject(object0, v15, zzdq0.zzc);
                                    }
                                    else {
                                        unsafe0.putObject(object0, v15, zzfe.zza(object1, zzdq0.zzc));
                                    }
                                    v4 = v9;
                                    v3 = v8;
                                    continue;
                                }
                                else {
                                    v12 = v9;
                                    v10 = v8;
                                    v16 = v6;
                                    unsafe1 = unsafe0;
                                    break;
                                }
                                goto label_139;
                            }
                            case 10: {
                            label_139:
                                if((v7 & 7) == 2) {
                                    v2 = zzdr.zze(arr_b, v6, zzdq0);
                                    unsafe0.putObject(object0, v15, zzdq0.zzc);
                                    v4 = v9;
                                    v3 = v8;
                                    continue;
                                }
                                else {
                                    v12 = v9;
                                    v10 = v8;
                                    v16 = v6;
                                    unsafe1 = unsafe0;
                                    break;
                                }
                                goto label_150;
                            }
                            case 4: 
                            case 11: {
                            label_66:
                                if((v7 & 7) == 0) {
                                    v2 = zzdr.zza(arr_b, v6, zzdq0);
                                    unsafe0.putInt(object0, v15, zzdq0.zza);
                                    v3 = v8;
                                    v4 = v9;
                                    continue;
                                }
                                else {
                                    v10 = v8;
                                    v16 = v6;
                                    unsafe1 = unsafe0;
                                    v12 = v9;
                                    break;
                                }
                                goto label_77;
                            }
                            case 12: {
                            label_150:
                                if((v7 & 7) == 0) {
                                    v2 = zzdr.zza(arr_b, v6, zzdq0);
                                    unsafe0.putInt(object0, v15, zzdq0.zza);
                                    v3 = v8;
                                    v4 = v9;
                                    continue;
                                }
                                else {
                                    v10 = v8;
                                    v16 = v6;
                                    unsafe1 = unsafe0;
                                    v12 = v9;
                                    break;
                                }
                                goto label_161;
                            }
                            case 6: 
                            case 13: {
                            label_88:
                                if((v7 & 7) == 5) {
                                    unsafe0.putInt(object0, v15, zzdr.zza(arr_b, v6));
                                    v2 = v6 + 4;
                                    v4 = v9;
                                    v3 = v8;
                                    continue;
                                }
                                else {
                                    v12 = v9;
                                    v10 = v8;
                                    v16 = v6;
                                    unsafe1 = unsafe0;
                                    break;
                                }
                                goto label_99;
                            }
                            case 5: 
                            case 14: {
                            label_77:
                                if((v7 & 7) == 1) {
                                    unsafe0.putLong(object0, v15, zzdr.zzb(arr_b, v6));
                                    v2 = v6 + 8;
                                    v3 = v8;
                                    v4 = v9;
                                    continue;
                                }
                                else {
                                    v12 = v9;
                                    v10 = v8;
                                    v16 = v6;
                                    unsafe1 = unsafe0;
                                    break;
                                }
                                goto label_88;
                            }
                            case 15: {
                            label_161:
                                if((v7 & 7) == 0) {
                                    v2 = zzdr.zza(arr_b, v6, zzdq0);
                                    unsafe0.putInt(object0, v15, -(zzdq0.zza & 1) ^ zzdq0.zza >>> 1);
                                    v3 = v8;
                                    v4 = v9;
                                    continue;
                                }
                                else {
                                    v10 = v8;
                                    v16 = v6;
                                    unsafe1 = unsafe0;
                                    v12 = v9;
                                    break;
                                }
                                goto label_172;
                            }
                            case 16: {
                            label_172:
                                if((v7 & 7) == 0) {
                                    int v19 = zzdr.zzb(arr_b, v6, zzdq0);
                                    unsafe0.putLong(object0, v15, -(zzdq0.zzb & 1L) ^ zzdq0.zzb >>> 1);
                                    v2 = v19;
                                    v3 = v8;
                                    v4 = v9;
                                    continue;
                                }
                                else {
                                    v12 = v9;
                                    v10 = v8;
                                    v16 = v6;
                                    unsafe1 = unsafe0;
                                }
                                break;
                            }
                            default: {
                                v12 = v9;
                                v10 = v8;
                                v16 = v6;
                                unsafe1 = unsafe0;
                            }
                        }
                        v11 = v16;
                    }
                    else if(v14 == 27) {
                        if((v7 & 7) == 2) {
                            zzfk zzfk0 = (zzfk)unsafe0.getObject(object0, v15);
                            if(zzfk0.zza()) {
                                zzfk2 = zzfk0;
                            }
                            else {
                                int v20 = zzfk0.size();
                                zzfk zzfk1 = zzfk0.zza((v20 == 0 ? 10 : v20 << 1));
                                unsafe0.putObject(object0, v15, zzfk1);
                                zzfk2 = zzfk1;
                            }
                            v2 = zzdr.zza(this.zza(v9), v7, arr_b, v6, v1, zzfk2, zzdq0);
                            v3 = v8;
                            v4 = v9;
                            continue;
                        }
                        else {
                            v12 = v9;
                            v10 = v8;
                            unsafe1 = unsafe0;
                            v11 = v6;
                            goto label_234;
                        }
                        goto label_205;
                    }
                    else {
                    label_205:
                        v12 = v9;
                        if(v14 <= 49) {
                            v10 = v8;
                            unsafe1 = unsafe0;
                            v2 = this.zza(object0, arr_b, v6, v1, v7, v8, v7 & 7, v12, ((long)v13), v14, v15, zzdq0);
                            if(v2 == v6) {
                                v11 = v2;
                                goto label_234;
                            }
                            else {
                                unsafe0 = unsafe1;
                                v4 = v12;
                                v3 = v10;
                                continue;
                            }
                            goto label_217;
                        }
                        else {
                        label_217:
                            v10 = v8;
                            unsafe1 = unsafe0;
                            if(v14 == 50) {
                                if((v7 & 7) == 2) {
                                    v2 = this.zza(object0, arr_b, v6, v1, v12, v15, zzdq0);
                                    if(v2 == v6) {
                                        v11 = v2;
                                        goto label_234;
                                    }
                                    else {
                                        unsafe0 = unsafe1;
                                        v4 = v12;
                                        v3 = v10;
                                        continue;
                                    }
                                }
                                v11 = v6;
                            }
                            else {
                                v2 = this.zza(object0, arr_b, v6, v1, v7, v10, v7 & 7, v13, v14, v15, v12, zzdq0);
                                if(v2 == v6) {
                                    v11 = v2;
                                }
                                else {
                                    goto label_239;
                                }
                            }
                        }
                    }
                }
            label_234:
                v2 = zzdr.zza(v7, arr_b, v11, v1, zzgr.zze(object0), zzdq0);
                unsafe0 = unsafe1;
                v4 = v12;
                v3 = v10;
                continue;
            label_239:
                unsafe0 = unsafe1;
                v4 = v12;
                v3 = v10;
            }
            if(v2 != v1) {
                throw zzfn.zzg();
            }
            return;
        }
        this.zza(object0, arr_b, v, v1, 0, zzdq0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final boolean zza(Object object0, Object object1) {
        for(int v = 0; true; v += 3) {
            boolean z = true;
            if(v >= this.zzc.length) {
                break;
            }
            int v1 = this.zzd(v);
            long v2 = (long)(v1 & 0xFFFFF);
            switch((v1 & 0xFF00000) >>> 20) {
                case 0: {
                    if(!this.zzc(object0, object1, v) || Double.doubleToLongBits(zzia.zze(object0, v2)) != Double.doubleToLongBits(zzia.zze(object1, v2))) {
                        z = false;
                    }
                    break;
                }
                case 1: {
                    if(!this.zzc(object0, object1, v) || Float.floatToIntBits(zzia.zzd(object0, v2)) != Float.floatToIntBits(zzia.zzd(object1, v2))) {
                        z = false;
                    }
                    break;
                }
                case 2: {
                    if(!this.zzc(object0, object1, v) || zzia.zzb(object0, v2) != zzia.zzb(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 3: {
                    if(!this.zzc(object0, object1, v) || zzia.zzb(object0, v2) != zzia.zzb(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 4: {
                    if(!this.zzc(object0, object1, v) || zzia.zza(object0, v2) != zzia.zza(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 5: {
                    if(!this.zzc(object0, object1, v) || zzia.zzb(object0, v2) != zzia.zzb(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 6: {
                    if(!this.zzc(object0, object1, v) || zzia.zza(object0, v2) != zzia.zza(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 7: {
                    if(!this.zzc(object0, object1, v) || zzia.zzc(object0, v2) != zzia.zzc(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 8: {
                    if(!this.zzc(object0, object1, v) || !zzhe.zza(zzia.zzf(object0, v2), zzia.zzf(object1, v2))) {
                        z = false;
                    }
                    break;
                }
                case 9: {
                    if(!this.zzc(object0, object1, v) || !zzhe.zza(zzia.zzf(object0, v2), zzia.zzf(object1, v2))) {
                        z = false;
                    }
                    break;
                }
                case 10: {
                    if(!this.zzc(object0, object1, v) || !zzhe.zza(zzia.zzf(object0, v2), zzia.zzf(object1, v2))) {
                        z = false;
                    }
                    break;
                }
                case 11: {
                    if(!this.zzc(object0, object1, v) || zzia.zza(object0, v2) != zzia.zza(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 12: {
                    if(!this.zzc(object0, object1, v) || zzia.zza(object0, v2) != zzia.zza(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 13: {
                    if(!this.zzc(object0, object1, v) || zzia.zza(object0, v2) != zzia.zza(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 14: {
                    if(!this.zzc(object0, object1, v) || zzia.zzb(object0, v2) != zzia.zzb(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 15: {
                    if(!this.zzc(object0, object1, v) || zzia.zza(object0, v2) != zzia.zza(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 16: {
                    if(!this.zzc(object0, object1, v) || zzia.zzb(object0, v2) != zzia.zzb(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 17: {
                    if(!this.zzc(object0, object1, v) || !zzhe.zza(zzia.zzf(object0, v2), zzia.zzf(object1, v2))) {
                        z = false;
                    }
                    break;
                }
                case 18: 
                case 19: 
                case 20: 
                case 21: 
                case 22: 
                case 23: 
                case 24: 
                case 25: 
                case 26: 
                case 27: 
                case 28: 
                case 29: 
                case 30: 
                case 0x1F: 
                case 0x20: 
                case 33: 
                case 34: 
                case 35: 
                case 36: 
                case 37: 
                case 38: 
                case 39: 
                case 40: 
                case 41: 
                case 42: 
                case 43: 
                case 44: 
                case 45: 
                case 46: 
                case 0x2F: 
                case 0x30: 
                case 49: {
                    z = zzhe.zza(zzia.zzf(object0, v2), zzia.zzf(object1, v2));
                    break;
                }
                case 50: {
                    z = zzhe.zza(zzia.zzf(object0, v2), zzia.zzf(object1, v2));
                    break;
                }
                case 51: 
                case 52: 
                case 53: 
                case 54: 
                case 55: 
                case 56: 
                case 57: 
                case 58: 
                case 59: 
                case 60: 
                case 61: 
                case 62: 
                case 0x3F: 
                case 0x40: 
                case 65: 
                case 66: 
                case 67: 
                case 68: {
                    int v3 = this.zze(v);
                    if(zzia.zza(object0, ((long)(v3 & 0xFFFFF))) != zzia.zza(object1, ((long)(v3 & 0xFFFFF))) || !zzhe.zza(zzia.zzf(object0, v2), zzia.zzf(object1, v2))) {
                        z = false;
                    }
                }
            }
            if(!z) {
                return false;
            }
        }
        if(!this.zzq.zzb(object0).equals(this.zzq.zzb(object1))) {
            return false;
        }
        return this.zzh ? this.zzr.zza(object0).equals(this.zzr.zza(object1)) : true;
    }

    private static double zzb(Object object0, long v) {
        return (double)(((Double)zzia.zzf(object0, v)));
    }

    private final int zzb(int v, int v1) {
        int v2 = this.zzc.length / 3 - 1;
        while(v1 <= v2) {
            int v3 = v2 + v1 >>> 1;
            int v4 = v3 * 3;
            int v5 = this.zzc[v4];
            if(v == v5) {
                return v4;
            }
            if(v < v5) {
                v2 = v3 - 1;
            }
            else {
                v1 = v3 + 1;
            }
        }
        return -1;
    }

    private final Object zzb(int v) {
        return this.zzd[v / 3 << 1];
    }

    private final void zzb(Object object0, int v) {
        if(this.zzj) {
            return;
        }
        int v1 = this.zze(v);
        zzia.zza(object0, ((long)(v1 & 0xFFFFF)), zzia.zza(object0, ((long)(v1 & 0xFFFFF))) | 1 << (v1 >>> 20));
    }

    private final void zzb(Object object0, int v, int v1) {
        zzia.zza(object0, ((long)(this.zze(v1) & 0xFFFFF)), v);
    }

    private final void zzb(Object object0, zzir zzir0) throws IOException {
        int v8;
        Map.Entry map$Entry2;
        Map.Entry map$Entry0;
        Iterator iterator0;
        if(this.zzh) {
            zzet zzet0 = this.zzr.zza(object0);
            if(zzet0.zza.isEmpty()) {
                iterator0 = null;
                map$Entry0 = null;
            }
            else {
                iterator0 = zzet0.zzd();
                Object object1 = iterator0.next();
                map$Entry0 = (Map.Entry)object1;
            }
        }
        else {
            iterator0 = null;
            map$Entry0 = null;
        }
        int v = -1;
        Unsafe unsafe0 = zzgr.zzb;
        Map.Entry map$Entry1 = map$Entry0;
        int v2 = 0;
        for(int v1 = 0; v1 < this.zzc.length; v1 += 3) {
            int v3 = this.zzd(v1);
            int[] arr_v = this.zzc;
            int v4 = arr_v[v1];
            int v5 = (0xFF00000 & v3) >>> 20;
            if(this.zzj || v5 > 17) {
                v8 = 0;
            }
            else {
                int v6 = arr_v[v1 + 2];
                int v7 = v6 & 0xFFFFF;
                if(v7 == v) {
                    map$Entry2 = map$Entry1;
                    v7 = v;
                }
                else {
                    map$Entry2 = map$Entry1;
                    v2 = unsafe0.getInt(object0, ((long)v7));
                }
                v8 = 1 << (v6 >>> 20);
                v = v7;
                map$Entry1 = map$Entry2;
            }
            while(map$Entry1 != null && this.zzr.zza(map$Entry1) <= v4) {
                this.zzr.zza(zzir0, map$Entry1);
                if(iterator0.hasNext()) {
                    Object object2 = iterator0.next();
                    map$Entry1 = (Map.Entry)object2;
                }
                else {
                    map$Entry1 = null;
                }
            }
            long v9 = (long)(v3 & 0xFFFFF);
            switch(v5) {
                case 0: {
                    if((v2 & v8) != 0) {
                        zzir0.zza(v4, zzia.zze(object0, v9));
                    }
                    break;
                }
                case 1: {
                    if((v2 & v8) != 0) {
                        zzir0.zza(v4, zzia.zzd(object0, v9));
                    }
                    break;
                }
                case 2: {
                    if((v2 & v8) != 0) {
                        zzir0.zza(v4, unsafe0.getLong(object0, v9));
                    }
                    break;
                }
                case 3: {
                    if((v2 & v8) != 0) {
                        zzir0.zzc(v4, unsafe0.getLong(object0, v9));
                    }
                    break;
                }
                case 4: {
                    if((v2 & v8) != 0) {
                        zzir0.zzc(v4, unsafe0.getInt(object0, v9));
                    }
                    break;
                }
                case 5: {
                    if((v2 & v8) != 0) {
                        zzir0.zzd(v4, unsafe0.getLong(object0, v9));
                    }
                    break;
                }
                case 6: {
                    if((v2 & v8) != 0) {
                        zzir0.zzd(v4, unsafe0.getInt(object0, v9));
                    }
                    break;
                }
                case 7: {
                    if((v2 & v8) != 0) {
                        zzir0.zza(v4, zzia.zzc(object0, v9));
                    }
                    break;
                }
                case 8: {
                    if((v2 & v8) != 0) {
                        zzgr.zza(v4, unsafe0.getObject(object0, v9), zzir0);
                    }
                    break;
                }
                case 9: {
                    if((v2 & v8) != 0) {
                        zzir0.zza(v4, unsafe0.getObject(object0, v9), this.zza(v1));
                    }
                    break;
                }
                case 10: {
                    if((v2 & v8) != 0) {
                        zzir0.zza(v4, ((zzdv)unsafe0.getObject(object0, v9)));
                    }
                    break;
                }
                case 11: {
                    if((v2 & v8) != 0) {
                        zzir0.zze(v4, unsafe0.getInt(object0, v9));
                    }
                    break;
                }
                case 12: {
                    if((v2 & v8) != 0) {
                        zzir0.zzb(v4, unsafe0.getInt(object0, v9));
                    }
                    break;
                }
                case 13: {
                    if((v2 & v8) != 0) {
                        zzir0.zza(v4, unsafe0.getInt(object0, v9));
                    }
                    break;
                }
                case 14: {
                    if((v2 & v8) != 0) {
                        zzir0.zzb(v4, unsafe0.getLong(object0, v9));
                    }
                    break;
                }
                case 15: {
                    if((v2 & v8) != 0) {
                        zzir0.zzf(v4, unsafe0.getInt(object0, v9));
                    }
                    break;
                }
                case 16: {
                    if((v2 & v8) != 0) {
                        zzir0.zze(v4, unsafe0.getLong(object0, v9));
                    }
                    break;
                }
                case 17: {
                    if((v2 & v8) != 0) {
                        zzir0.zzb(v4, unsafe0.getObject(object0, v9), this.zza(v1));
                    }
                    break;
                }
                case 18: {
                    zzhe.zza(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, false);
                    break;
                }
                case 19: {
                    zzhe.zzb(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, false);
                    break;
                }
                case 20: {
                    zzhe.zzc(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, false);
                    break;
                }
                case 21: {
                    zzhe.zzd(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, false);
                    break;
                }
                case 22: {
                    zzhe.zzh(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, false);
                    break;
                }
                case 23: {
                    zzhe.zzf(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, false);
                    break;
                }
                case 24: {
                    zzhe.zzk(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, false);
                    break;
                }
                case 25: {
                    zzhe.zzn(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, false);
                    break;
                }
                case 26: {
                    zzhe.zza(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0);
                    break;
                }
                case 27: {
                    zzhe.zza(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, this.zza(v1));
                    break;
                }
                case 28: {
                    zzhe.zzb(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0);
                    break;
                }
                case 29: {
                    zzhe.zzi(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, false);
                    break;
                }
                case 30: {
                    zzhe.zzm(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, false);
                    break;
                }
                case 0x1F: {
                    zzhe.zzl(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, false);
                    break;
                }
                case 0x20: {
                    zzhe.zzg(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, false);
                    break;
                }
                case 33: {
                    zzhe.zzj(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, false);
                    break;
                }
                case 34: {
                    zzhe.zze(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, false);
                    break;
                }
                case 35: {
                    zzhe.zza(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, true);
                    break;
                }
                case 36: {
                    zzhe.zzb(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, true);
                    break;
                }
                case 37: {
                    zzhe.zzc(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, true);
                    break;
                }
                case 38: {
                    zzhe.zzd(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, true);
                    break;
                }
                case 39: {
                    zzhe.zzh(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, true);
                    break;
                }
                case 40: {
                    zzhe.zzf(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, true);
                    break;
                }
                case 41: {
                    zzhe.zzk(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, true);
                    break;
                }
                case 42: {
                    zzhe.zzn(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, true);
                    break;
                }
                case 43: {
                    zzhe.zzi(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, true);
                    break;
                }
                case 44: {
                    zzhe.zzm(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, true);
                    break;
                }
                case 45: {
                    zzhe.zzl(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, true);
                    break;
                }
                case 46: {
                    zzhe.zzg(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, true);
                    break;
                }
                case 0x2F: {
                    zzhe.zzj(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, true);
                    break;
                }
                case 0x30: {
                    zzhe.zze(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, true);
                    break;
                }
                case 49: {
                    zzhe.zzb(this.zzc[v1], ((List)unsafe0.getObject(object0, v9)), zzir0, this.zza(v1));
                    break;
                }
                case 50: {
                    this.zza(zzir0, v4, unsafe0.getObject(object0, v9), v1);
                    break;
                }
                case 51: {
                    if(this.zza(object0, v4, v1)) {
                        zzir0.zza(v4, zzgr.zzb(object0, v9));
                    }
                    break;
                }
                case 52: {
                    if(this.zza(object0, v4, v1)) {
                        zzir0.zza(v4, zzgr.zzc(object0, v9));
                    }
                    break;
                }
                case 53: {
                    if(this.zza(object0, v4, v1)) {
                        zzir0.zza(v4, zzgr.zze(object0, v9));
                    }
                    break;
                }
                case 54: {
                    if(this.zza(object0, v4, v1)) {
                        zzir0.zzc(v4, zzgr.zze(object0, v9));
                    }
                    break;
                }
                case 55: {
                    if(this.zza(object0, v4, v1)) {
                        zzir0.zzc(v4, zzgr.zzd(object0, v9));
                    }
                    break;
                }
                case 56: {
                    if(this.zza(object0, v4, v1)) {
                        zzir0.zzd(v4, zzgr.zze(object0, v9));
                    }
                    break;
                }
                case 57: {
                    if(this.zza(object0, v4, v1)) {
                        zzir0.zzd(v4, zzgr.zzd(object0, v9));
                    }
                    break;
                }
                case 58: {
                    if(this.zza(object0, v4, v1)) {
                        zzir0.zza(v4, zzgr.zzf(object0, v9));
                    }
                    break;
                }
                case 59: {
                    if(this.zza(object0, v4, v1)) {
                        zzgr.zza(v4, unsafe0.getObject(object0, v9), zzir0);
                    }
                    break;
                }
                case 60: {
                    if(this.zza(object0, v4, v1)) {
                        zzir0.zza(v4, unsafe0.getObject(object0, v9), this.zza(v1));
                    }
                    break;
                }
                case 61: {
                    if(this.zza(object0, v4, v1)) {
                        zzir0.zza(v4, ((zzdv)unsafe0.getObject(object0, v9)));
                    }
                    break;
                }
                case 62: {
                    if(this.zza(object0, v4, v1)) {
                        zzir0.zze(v4, zzgr.zzd(object0, v9));
                    }
                    break;
                }
                case 0x3F: {
                    if(this.zza(object0, v4, v1)) {
                        zzir0.zzb(v4, zzgr.zzd(object0, v9));
                    }
                    break;
                }
                case 0x40: {
                    if(this.zza(object0, v4, v1)) {
                        zzir0.zza(v4, zzgr.zzd(object0, v9));
                    }
                    break;
                }
                case 65: {
                    if(this.zza(object0, v4, v1)) {
                        zzir0.zzb(v4, zzgr.zze(object0, v9));
                    }
                    break;
                }
                case 66: {
                    if(this.zza(object0, v4, v1)) {
                        zzir0.zzf(v4, zzgr.zzd(object0, v9));
                    }
                    break;
                }
                case 67: {
                    if(this.zza(object0, v4, v1)) {
                        zzir0.zze(v4, zzgr.zze(object0, v9));
                    }
                    break;
                }
                case 68: {
                    if(this.zza(object0, v4, v1)) {
                        zzir0.zzb(v4, unsafe0.getObject(object0, v9), this.zza(v1));
                    }
                }
            }
        }
        Map.Entry map$Entry3 = map$Entry1;
        while(map$Entry3 != null) {
            this.zzr.zza(zzir0, map$Entry3);
            if(iterator0.hasNext()) {
                Object object3 = iterator0.next();
                map$Entry3 = (Map.Entry)object3;
            }
            else {
                map$Entry3 = null;
            }
        }
        zzgr.zza(this.zzq, object0, zzir0);
    }

    private final void zzb(Object object0, Object object1, int v) {
        int v1 = this.zzd(v);
        int v2 = this.zzc[v];
        if(!this.zza(object1, v2, v)) {
            return;
        }
        Object object2 = zzia.zzf(object0, ((long)(v1 & 0xFFFFF)));
        Object object3 = zzia.zzf(object1, ((long)(v1 & 0xFFFFF)));
        if(object2 != null && object3 != null) {
            zzia.zza(object0, ((long)(v1 & 0xFFFFF)), zzfe.zza(object2, object3));
            this.zzb(object0, v2, v);
            return;
        }
        if(object3 != null) {
            zzia.zza(object0, ((long)(v1 & 0xFFFFF)), object3);
            this.zzb(object0, v2, v);
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final int zzb(Object object0) {
        int v30;
        int v28;
        if(this.zzj) {
            Unsafe unsafe0 = zzgr.zzb;
            int v1 = 0;
            for(int v = 0; v < this.zzc.length; v += 3) {
                int v2 = this.zzd(v);
                int v3 = (v2 & 0xFF00000) >>> 20;
                int v4 = this.zzc[v];
                long v5 = (long)(v2 & 0xFFFFF);
                int v6 = v3 < zzey.zza.zza() || v3 > zzey.zzb.zza() ? 0 : this.zzc[v + 2] & 0xFFFFF;
                switch(v3) {
                    case 0: {
                        if(this.zza(object0, v)) {
                            v1 += zzek.zzb(v4, 0.0);
                        }
                        break;
                    }
                    case 1: {
                        if(this.zza(object0, v)) {
                            v1 += zzek.zzb(v4, 0.0f);
                        }
                        break;
                    }
                    case 2: {
                        if(this.zza(object0, v)) {
                            v1 += zzek.zzd(v4, zzia.zzb(object0, v5));
                        }
                        break;
                    }
                    case 3: {
                        if(this.zza(object0, v)) {
                            v1 += zzek.zze(v4, zzia.zzb(object0, v5));
                        }
                        break;
                    }
                    case 4: {
                        if(this.zza(object0, v)) {
                            v1 += zzek.zzf(v4, zzia.zza(object0, v5));
                        }
                        break;
                    }
                    case 5: {
                        if(this.zza(object0, v)) {
                            v1 += zzek.zzg(v4, 0L);
                        }
                        break;
                    }
                    case 6: {
                        if(this.zza(object0, v)) {
                            v1 += zzek.zzi(v4, 0);
                        }
                        break;
                    }
                    case 7: {
                        if(this.zza(object0, v)) {
                            v1 += zzek.zzb(v4, true);
                        }
                        break;
                    }
                    case 8: {
                        if(this.zza(object0, v)) {
                            Object object1 = zzia.zzf(object0, v5);
                            v1 += (object1 instanceof zzdv ? zzek.zzc(v4, ((zzdv)object1)) : zzek.zzb(v4, ((String)object1)));
                        }
                        break;
                    }
                    case 9: {
                        if(this.zza(object0, v)) {
                            v1 += zzhe.zza(v4, zzia.zzf(object0, v5), this.zza(v));
                        }
                        break;
                    }
                    case 10: {
                        if(this.zza(object0, v)) {
                            v1 += zzek.zzc(v4, ((zzdv)zzia.zzf(object0, v5)));
                        }
                        break;
                    }
                    case 11: {
                        if(this.zza(object0, v)) {
                            v1 += zzek.zzg(v4, zzia.zza(object0, v5));
                        }
                        break;
                    }
                    case 12: {
                        if(this.zza(object0, v)) {
                            v1 += zzek.zzk(v4, zzia.zza(object0, v5));
                        }
                        break;
                    }
                    case 13: {
                        if(this.zza(object0, v)) {
                            v1 += zzek.zzj(v4, 0);
                        }
                        break;
                    }
                    case 14: {
                        if(this.zza(object0, v)) {
                            v1 += zzek.zzh(v4, 0L);
                        }
                        break;
                    }
                    case 15: {
                        if(this.zza(object0, v)) {
                            v1 += zzek.zzh(v4, zzia.zza(object0, v5));
                        }
                        break;
                    }
                    case 16: {
                        if(this.zza(object0, v)) {
                            v1 += zzek.zzf(v4, zzia.zzb(object0, v5));
                        }
                        break;
                    }
                    case 17: {
                        if(this.zza(object0, v)) {
                            v1 += zzek.zzc(v4, ((zzgn)zzia.zzf(object0, v5)), this.zza(v));
                        }
                        break;
                    }
                    case 18: {
                        v1 += zzhe.zzi(v4, zzgr.zza(object0, v5), false);
                        break;
                    }
                    case 19: {
                        v1 += zzhe.zzh(v4, zzgr.zza(object0, v5), false);
                        break;
                    }
                    case 20: {
                        v1 += zzhe.zza(v4, zzgr.zza(object0, v5), false);
                        break;
                    }
                    case 21: {
                        v1 += zzhe.zzb(v4, zzgr.zza(object0, v5), false);
                        break;
                    }
                    case 22: {
                        v1 += zzhe.zze(v4, zzgr.zza(object0, v5), false);
                        break;
                    }
                    case 23: {
                        v1 += zzhe.zzi(v4, zzgr.zza(object0, v5), false);
                        break;
                    }
                    case 24: {
                        v1 += zzhe.zzh(v4, zzgr.zza(object0, v5), false);
                        break;
                    }
                    case 25: {
                        v1 += zzhe.zzj(v4, zzgr.zza(object0, v5), false);
                        break;
                    }
                    case 26: {
                        v1 += zzhe.zza(v4, zzgr.zza(object0, v5));
                        break;
                    }
                    case 27: {
                        v1 += zzhe.zza(v4, zzgr.zza(object0, v5), this.zza(v));
                        break;
                    }
                    case 28: {
                        v1 += zzhe.zzb(v4, zzgr.zza(object0, v5));
                        break;
                    }
                    case 29: {
                        v1 += zzhe.zzf(v4, zzgr.zza(object0, v5), false);
                        break;
                    }
                    case 30: {
                        v1 += zzhe.zzd(v4, zzgr.zza(object0, v5), false);
                        break;
                    }
                    case 0x1F: {
                        v1 += zzhe.zzh(v4, zzgr.zza(object0, v5), false);
                        break;
                    }
                    case 0x20: {
                        v1 += zzhe.zzi(v4, zzgr.zza(object0, v5), false);
                        break;
                    }
                    case 33: {
                        v1 += zzhe.zzg(v4, zzgr.zza(object0, v5), false);
                        break;
                    }
                    case 34: {
                        v1 += zzhe.zzc(v4, zzgr.zza(object0, v5), false);
                        break;
                    }
                    case 35: {
                        int v7 = zzhe.zzi(((List)unsafe0.getObject(object0, v5)));
                        if(v7 > 0) {
                            if(this.zzk) {
                                unsafe0.putInt(object0, ((long)v6), v7);
                            }
                            v1 += zzek.zze(v4) + zzek.zzg(v7) + v7;
                        }
                        break;
                    }
                    case 36: {
                        int v8 = zzhe.zzh(((List)unsafe0.getObject(object0, v5)));
                        if(v8 > 0) {
                            if(this.zzk) {
                                unsafe0.putInt(object0, ((long)v6), v8);
                            }
                            v1 += zzek.zze(v4) + zzek.zzg(v8) + v8;
                        }
                        break;
                    }
                    case 37: {
                        int v9 = zzhe.zza(((List)unsafe0.getObject(object0, v5)));
                        if(v9 > 0) {
                            if(this.zzk) {
                                unsafe0.putInt(object0, ((long)v6), v9);
                            }
                            v1 += zzek.zze(v4) + zzek.zzg(v9) + v9;
                        }
                        break;
                    }
                    case 38: {
                        int v10 = zzhe.zzb(((List)unsafe0.getObject(object0, v5)));
                        if(v10 > 0) {
                            if(this.zzk) {
                                unsafe0.putInt(object0, ((long)v6), v10);
                            }
                            v1 += zzek.zze(v4) + zzek.zzg(v10) + v10;
                        }
                        break;
                    }
                    case 39: {
                        int v11 = zzhe.zze(((List)unsafe0.getObject(object0, v5)));
                        if(v11 > 0) {
                            if(this.zzk) {
                                unsafe0.putInt(object0, ((long)v6), v11);
                            }
                            v1 += zzek.zze(v4) + zzek.zzg(v11) + v11;
                        }
                        break;
                    }
                    case 40: {
                        int v12 = zzhe.zzi(((List)unsafe0.getObject(object0, v5)));
                        if(v12 > 0) {
                            if(this.zzk) {
                                unsafe0.putInt(object0, ((long)v6), v12);
                            }
                            v1 += zzek.zze(v4) + zzek.zzg(v12) + v12;
                        }
                        break;
                    }
                    case 41: {
                        int v13 = zzhe.zzh(((List)unsafe0.getObject(object0, v5)));
                        if(v13 > 0) {
                            if(this.zzk) {
                                unsafe0.putInt(object0, ((long)v6), v13);
                            }
                            v1 += zzek.zze(v4) + zzek.zzg(v13) + v13;
                        }
                        break;
                    }
                    case 42: {
                        int v14 = zzhe.zzj(((List)unsafe0.getObject(object0, v5)));
                        if(v14 > 0) {
                            if(this.zzk) {
                                unsafe0.putInt(object0, ((long)v6), v14);
                            }
                            v1 += zzek.zze(v4) + zzek.zzg(v14) + v14;
                        }
                        break;
                    }
                    case 43: {
                        int v15 = zzhe.zzf(((List)unsafe0.getObject(object0, v5)));
                        if(v15 > 0) {
                            if(this.zzk) {
                                unsafe0.putInt(object0, ((long)v6), v15);
                            }
                            v1 += zzek.zze(v4) + zzek.zzg(v15) + v15;
                        }
                        break;
                    }
                    case 44: {
                        int v16 = zzhe.zzd(((List)unsafe0.getObject(object0, v5)));
                        if(v16 > 0) {
                            if(this.zzk) {
                                unsafe0.putInt(object0, ((long)v6), v16);
                            }
                            v1 += zzek.zze(v4) + zzek.zzg(v16) + v16;
                        }
                        break;
                    }
                    case 45: {
                        int v17 = zzhe.zzh(((List)unsafe0.getObject(object0, v5)));
                        if(v17 > 0) {
                            if(this.zzk) {
                                unsafe0.putInt(object0, ((long)v6), v17);
                            }
                            v1 += zzek.zze(v4) + zzek.zzg(v17) + v17;
                        }
                        break;
                    }
                    case 46: {
                        int v18 = zzhe.zzi(((List)unsafe0.getObject(object0, v5)));
                        if(v18 > 0) {
                            if(this.zzk) {
                                unsafe0.putInt(object0, ((long)v6), v18);
                            }
                            v1 += zzek.zze(v4) + zzek.zzg(v18) + v18;
                        }
                        break;
                    }
                    case 0x2F: {
                        int v19 = zzhe.zzg(((List)unsafe0.getObject(object0, v5)));
                        if(v19 > 0) {
                            if(this.zzk) {
                                unsafe0.putInt(object0, ((long)v6), v19);
                            }
                            v1 += zzek.zze(v4) + zzek.zzg(v19) + v19;
                        }
                        break;
                    }
                    case 0x30: {
                        int v20 = zzhe.zzc(((List)unsafe0.getObject(object0, v5)));
                        if(v20 > 0) {
                            if(this.zzk) {
                                unsafe0.putInt(object0, ((long)v6), v20);
                            }
                            v1 += zzek.zze(v4) + zzek.zzg(v20) + v20;
                        }
                        break;
                    }
                    case 49: {
                        v1 += zzhe.zzb(v4, zzgr.zza(object0, v5), this.zza(v));
                        break;
                    }
                    case 50: {
                        Object object2 = zzia.zzf(object0, v5);
                        Object object3 = this.zzb(v);
                        v1 += this.zzs.zza(v4, object2, object3);
                        break;
                    }
                    case 51: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzek.zzb(v4, 0.0);
                        }
                        break;
                    }
                    case 52: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzek.zzb(v4, 0.0f);
                        }
                        break;
                    }
                    case 53: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzek.zzd(v4, zzgr.zze(object0, v5));
                        }
                        break;
                    }
                    case 54: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzek.zze(v4, zzgr.zze(object0, v5));
                        }
                        break;
                    }
                    case 55: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzek.zzf(v4, zzgr.zzd(object0, v5));
                        }
                        break;
                    }
                    case 56: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzek.zzg(v4, 0L);
                        }
                        break;
                    }
                    case 57: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzek.zzi(v4, 0);
                        }
                        break;
                    }
                    case 58: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzek.zzb(v4, true);
                        }
                        break;
                    }
                    case 59: {
                        if(this.zza(object0, v4, v)) {
                            Object object4 = zzia.zzf(object0, v5);
                            v1 += (object4 instanceof zzdv ? zzek.zzc(v4, ((zzdv)object4)) : zzek.zzb(v4, ((String)object4)));
                        }
                        break;
                    }
                    case 60: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzhe.zza(v4, zzia.zzf(object0, v5), this.zza(v));
                        }
                        break;
                    }
                    case 61: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzek.zzc(v4, ((zzdv)zzia.zzf(object0, v5)));
                        }
                        break;
                    }
                    case 62: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzek.zzg(v4, zzgr.zzd(object0, v5));
                        }
                        break;
                    }
                    case 0x3F: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzek.zzk(v4, zzgr.zzd(object0, v5));
                        }
                        break;
                    }
                    case 0x40: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzek.zzj(v4, 0);
                        }
                        break;
                    }
                    case 65: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzek.zzh(v4, 0L);
                        }
                        break;
                    }
                    case 66: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzek.zzh(v4, zzgr.zzd(object0, v5));
                        }
                        break;
                    }
                    case 67: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzek.zzf(v4, zzgr.zze(object0, v5));
                        }
                        break;
                    }
                    case 68: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzek.zzc(v4, ((zzgn)zzia.zzf(object0, v5)), this.zza(v));
                        }
                    }
                }
            }
            return v1 + zzgr.zza(this.zzq, object0);
        }
        Unsafe unsafe1 = zzgr.zzb;
        int v22 = 0;
        int v23 = -1;
        int v24 = 0;
        for(int v21 = 0; v21 < this.zzc.length; v21 += 3) {
            int v25 = this.zzd(v21);
            int[] arr_v = this.zzc;
            int v26 = arr_v[v21];
            int v27 = (v25 & 0xFF00000) >>> 20;
            if(v27 <= 17) {
                v28 = arr_v[v21 + 2];
                int v29 = v28 & 0xFFFFF;
                v30 = 1 << (v28 >>> 20);
                if(v29 == v23) {
                    v29 = v23;
                }
                else {
                    v24 = unsafe1.getInt(object0, ((long)v29));
                }
                v23 = v29;
            }
            else {
                v28 = !this.zzk || v27 < zzey.zza.zza() || v27 > zzey.zzb.zza() ? 0 : this.zzc[v21 + 2] & 0xFFFFF;
                v30 = 0;
            }
            long v31 = (long)(v25 & 0xFFFFF);
            switch(v27) {
                case 0: {
                    if((v24 & v30) != 0) {
                        v22 += zzek.zzb(v26, 0.0);
                    }
                    break;
                }
                case 1: {
                    if((v24 & v30) != 0) {
                        v22 += zzek.zzb(v26, 0.0f);
                    }
                    break;
                }
                case 2: {
                    if((v24 & v30) != 0) {
                        v22 += zzek.zzd(v26, unsafe1.getLong(object0, v31));
                    }
                    break;
                }
                case 3: {
                    if((v24 & v30) != 0) {
                        v22 += zzek.zze(v26, unsafe1.getLong(object0, v31));
                    }
                    break;
                }
                case 4: {
                    if((v24 & v30) != 0) {
                        v22 += zzek.zzf(v26, unsafe1.getInt(object0, v31));
                    }
                    break;
                }
                case 5: {
                    if((v24 & v30) != 0) {
                        v22 += zzek.zzg(v26, 0L);
                    }
                    break;
                }
                case 6: {
                    if((v24 & v30) != 0) {
                        v22 += zzek.zzi(v26, 0);
                    }
                    break;
                }
                case 7: {
                    if((v24 & v30) != 0) {
                        v22 += zzek.zzb(v26, true);
                    }
                    break;
                }
                case 8: {
                    if((v24 & v30) != 0) {
                        Object object5 = unsafe1.getObject(object0, v31);
                        v22 += (object5 instanceof zzdv ? zzek.zzc(v26, ((zzdv)object5)) : zzek.zzb(v26, ((String)object5)));
                    }
                    break;
                }
                case 9: {
                    if((v24 & v30) != 0) {
                        v22 += zzhe.zza(v26, unsafe1.getObject(object0, v31), this.zza(v21));
                    }
                    break;
                }
                case 10: {
                    if((v24 & v30) != 0) {
                        v22 += zzek.zzc(v26, ((zzdv)unsafe1.getObject(object0, v31)));
                    }
                    break;
                }
                case 11: {
                    if((v24 & v30) != 0) {
                        v22 += zzek.zzg(v26, unsafe1.getInt(object0, v31));
                    }
                    break;
                }
                case 12: {
                    if((v24 & v30) != 0) {
                        v22 += zzek.zzk(v26, unsafe1.getInt(object0, v31));
                    }
                    break;
                }
                case 13: {
                    if((v24 & v30) != 0) {
                        v22 += zzek.zzj(v26, 0);
                    }
                    break;
                }
                case 14: {
                    if((v24 & v30) != 0) {
                        v22 += zzek.zzh(v26, 0L);
                    }
                    break;
                }
                case 15: {
                    if((v24 & v30) != 0) {
                        v22 += zzek.zzh(v26, unsafe1.getInt(object0, v31));
                    }
                    break;
                }
                case 16: {
                    if((v24 & v30) != 0) {
                        v22 += zzek.zzf(v26, unsafe1.getLong(object0, v31));
                    }
                    break;
                }
                case 17: {
                    if((v24 & v30) != 0) {
                        v22 += zzek.zzc(v26, ((zzgn)unsafe1.getObject(object0, v31)), this.zza(v21));
                    }
                    break;
                }
                case 18: {
                    v22 += zzhe.zzi(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 19: {
                    v22 += zzhe.zzh(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 20: {
                    v22 += zzhe.zza(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 21: {
                    v22 += zzhe.zzb(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 22: {
                    v22 += zzhe.zze(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 23: {
                    v22 += zzhe.zzi(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 24: {
                    v22 += zzhe.zzh(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 25: {
                    v22 += zzhe.zzj(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 26: {
                    v22 += zzhe.zza(v26, ((List)unsafe1.getObject(object0, v31)));
                    break;
                }
                case 27: {
                    v22 += zzhe.zza(v26, ((List)unsafe1.getObject(object0, v31)), this.zza(v21));
                    break;
                }
                case 28: {
                    v22 += zzhe.zzb(v26, ((List)unsafe1.getObject(object0, v31)));
                    break;
                }
                case 29: {
                    v22 += zzhe.zzf(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 30: {
                    v22 += zzhe.zzd(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 0x1F: {
                    v22 += zzhe.zzh(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 0x20: {
                    v22 += zzhe.zzi(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 33: {
                    v22 += zzhe.zzg(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 34: {
                    v22 += zzhe.zzc(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 35: {
                    int v32 = zzhe.zzi(((List)unsafe1.getObject(object0, v31)));
                    if(v32 > 0) {
                        if(this.zzk) {
                            unsafe1.putInt(object0, ((long)v28), v32);
                        }
                        v22 += zzek.zze(v26) + zzek.zzg(v32) + v32;
                    }
                    break;
                }
                case 36: {
                    int v33 = zzhe.zzh(((List)unsafe1.getObject(object0, v31)));
                    if(v33 > 0) {
                        if(this.zzk) {
                            unsafe1.putInt(object0, ((long)v28), v33);
                        }
                        v22 += zzek.zze(v26) + zzek.zzg(v33) + v33;
                    }
                    break;
                }
                case 37: {
                    int v34 = zzhe.zza(((List)unsafe1.getObject(object0, v31)));
                    if(v34 > 0) {
                        if(this.zzk) {
                            unsafe1.putInt(object0, ((long)v28), v34);
                        }
                        v22 += zzek.zze(v26) + zzek.zzg(v34) + v34;
                    }
                    break;
                }
                case 38: {
                    int v35 = zzhe.zzb(((List)unsafe1.getObject(object0, v31)));
                    if(v35 > 0) {
                        if(this.zzk) {
                            unsafe1.putInt(object0, ((long)v28), v35);
                        }
                        v22 += zzek.zze(v26) + zzek.zzg(v35) + v35;
                    }
                    break;
                }
                case 39: {
                    int v36 = zzhe.zze(((List)unsafe1.getObject(object0, v31)));
                    if(v36 > 0) {
                        if(this.zzk) {
                            unsafe1.putInt(object0, ((long)v28), v36);
                        }
                        v22 += zzek.zze(v26) + zzek.zzg(v36) + v36;
                    }
                    break;
                }
                case 40: {
                    int v37 = zzhe.zzi(((List)unsafe1.getObject(object0, v31)));
                    if(v37 > 0) {
                        if(this.zzk) {
                            unsafe1.putInt(object0, ((long)v28), v37);
                        }
                        v22 += zzek.zze(v26) + zzek.zzg(v37) + v37;
                    }
                    break;
                }
                case 41: {
                    int v38 = zzhe.zzh(((List)unsafe1.getObject(object0, v31)));
                    if(v38 > 0) {
                        if(this.zzk) {
                            unsafe1.putInt(object0, ((long)v28), v38);
                        }
                        v22 += zzek.zze(v26) + zzek.zzg(v38) + v38;
                    }
                    break;
                }
                case 42: {
                    int v39 = zzhe.zzj(((List)unsafe1.getObject(object0, v31)));
                    if(v39 > 0) {
                        if(this.zzk) {
                            unsafe1.putInt(object0, ((long)v28), v39);
                        }
                        v22 += zzek.zze(v26) + zzek.zzg(v39) + v39;
                    }
                    break;
                }
                case 43: {
                    int v40 = zzhe.zzf(((List)unsafe1.getObject(object0, v31)));
                    if(v40 > 0) {
                        if(this.zzk) {
                            unsafe1.putInt(object0, ((long)v28), v40);
                        }
                        v22 += zzek.zze(v26) + zzek.zzg(v40) + v40;
                    }
                    break;
                }
                case 44: {
                    int v41 = zzhe.zzd(((List)unsafe1.getObject(object0, v31)));
                    if(v41 > 0) {
                        if(this.zzk) {
                            unsafe1.putInt(object0, ((long)v28), v41);
                        }
                        v22 += zzek.zze(v26) + zzek.zzg(v41) + v41;
                    }
                    break;
                }
                case 45: {
                    int v42 = zzhe.zzh(((List)unsafe1.getObject(object0, v31)));
                    if(v42 > 0) {
                        if(this.zzk) {
                            unsafe1.putInt(object0, ((long)v28), v42);
                        }
                        v22 += zzek.zze(v26) + zzek.zzg(v42) + v42;
                    }
                    break;
                }
                case 46: {
                    int v43 = zzhe.zzi(((List)unsafe1.getObject(object0, v31)));
                    if(v43 > 0) {
                        if(this.zzk) {
                            unsafe1.putInt(object0, ((long)v28), v43);
                        }
                        v22 += zzek.zze(v26) + zzek.zzg(v43) + v43;
                    }
                    break;
                }
                case 0x2F: {
                    int v44 = zzhe.zzg(((List)unsafe1.getObject(object0, v31)));
                    if(v44 > 0) {
                        if(this.zzk) {
                            unsafe1.putInt(object0, ((long)v28), v44);
                        }
                        v22 += zzek.zze(v26) + zzek.zzg(v44) + v44;
                    }
                    break;
                }
                case 0x30: {
                    int v45 = zzhe.zzc(((List)unsafe1.getObject(object0, v31)));
                    if(v45 > 0) {
                        if(this.zzk) {
                            unsafe1.putInt(object0, ((long)v28), v45);
                        }
                        v22 += zzek.zze(v26) + zzek.zzg(v45) + v45;
                    }
                    break;
                }
                case 49: {
                    v22 += zzhe.zzb(v26, ((List)unsafe1.getObject(object0, v31)), this.zza(v21));
                    break;
                }
                case 50: {
                    Object object6 = unsafe1.getObject(object0, v31);
                    Object object7 = this.zzb(v21);
                    v22 += this.zzs.zza(v26, object6, object7);
                    break;
                }
                case 51: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzek.zzb(v26, 0.0);
                    }
                    break;
                }
                case 52: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzek.zzb(v26, 0.0f);
                    }
                    break;
                }
                case 53: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzek.zzd(v26, zzgr.zze(object0, v31));
                    }
                    break;
                }
                case 54: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzek.zze(v26, zzgr.zze(object0, v31));
                    }
                    break;
                }
                case 55: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzek.zzf(v26, zzgr.zzd(object0, v31));
                    }
                    break;
                }
                case 56: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzek.zzg(v26, 0L);
                    }
                    break;
                }
                case 57: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzek.zzi(v26, 0);
                    }
                    break;
                }
                case 58: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzek.zzb(v26, true);
                    }
                    break;
                }
                case 59: {
                    if(this.zza(object0, v26, v21)) {
                        Object object8 = unsafe1.getObject(object0, v31);
                        v22 += (object8 instanceof zzdv ? zzek.zzc(v26, ((zzdv)object8)) : zzek.zzb(v26, ((String)object8)));
                    }
                    break;
                }
                case 60: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzhe.zza(v26, unsafe1.getObject(object0, v31), this.zza(v21));
                    }
                    break;
                }
                case 61: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzek.zzc(v26, ((zzdv)unsafe1.getObject(object0, v31)));
                    }
                    break;
                }
                case 62: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzek.zzg(v26, zzgr.zzd(object0, v31));
                    }
                    break;
                }
                case 0x3F: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzek.zzk(v26, zzgr.zzd(object0, v31));
                    }
                    break;
                }
                case 0x40: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzek.zzj(v26, 0);
                    }
                    break;
                }
                case 65: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzek.zzh(v26, 0L);
                    }
                    break;
                }
                case 66: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzek.zzh(v26, zzgr.zzd(object0, v31));
                    }
                    break;
                }
                case 67: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzek.zzf(v26, zzgr.zze(object0, v31));
                    }
                    break;
                }
                case 68: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzek.zzc(v26, ((zzgn)unsafe1.getObject(object0, v31)), this.zza(v21));
                    }
                }
            }
        }
        int v47 = v22 + zzgr.zza(this.zzq, object0);
        if(this.zzh) {
            zzet zzet0 = this.zzr.zza(object0);
            int v48 = 0;
            for(int v46 = 0; v46 < zzet0.zza.zzc(); ++v46) {
                Map.Entry map$Entry0 = zzet0.zza.zzb(v46);
                v48 += zzet.zza(((zzev)map$Entry0.getKey()), map$Entry0.getValue());
            }
            for(Object object9: zzet0.zza.zzd()) {
                v48 += zzet.zza(((zzev)((Map.Entry)object9).getKey()), ((Map.Entry)object9).getValue());
            }
            return v47 + v48;
        }
        return v47;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final void zzb(Object object0, Object object1) {
        if(object1 == null) {
            throw new NullPointerException();
        }
        for(int v = 0; v < this.zzc.length; v += 3) {
            int v1 = this.zzd(v);
            long v2 = (long)(0xFFFFF & v1);
            int v3 = this.zzc[v];
            switch((v1 & 0xFF00000) >>> 20) {
                case 0: {
                    if(this.zza(object1, v)) {
                        zzia.zza(object0, v2, zzia.zze(object1, v2));
                        this.zzb(object0, v);
                    }
                    break;
                }
                case 1: {
                    if(this.zza(object1, v)) {
                        zzia.zza(object0, v2, zzia.zzd(object1, v2));
                        this.zzb(object0, v);
                    }
                    break;
                }
                case 2: {
                    if(this.zza(object1, v)) {
                        zzia.zza(object0, v2, zzia.zzb(object1, v2));
                        this.zzb(object0, v);
                    }
                    break;
                }
                case 3: {
                    if(this.zza(object1, v)) {
                        zzia.zza(object0, v2, zzia.zzb(object1, v2));
                        this.zzb(object0, v);
                    }
                    break;
                }
                case 4: {
                    if(this.zza(object1, v)) {
                        zzia.zza(object0, v2, zzia.zza(object1, v2));
                        this.zzb(object0, v);
                    }
                    break;
                }
                case 5: {
                    if(this.zza(object1, v)) {
                        zzia.zza(object0, v2, zzia.zzb(object1, v2));
                        this.zzb(object0, v);
                    }
                    break;
                }
                case 6: {
                    if(this.zza(object1, v)) {
                        zzia.zza(object0, v2, zzia.zza(object1, v2));
                        this.zzb(object0, v);
                    }
                    break;
                }
                case 7: {
                    if(this.zza(object1, v)) {
                        zzia.zza(object0, v2, zzia.zzc(object1, v2));
                        this.zzb(object0, v);
                    }
                    break;
                }
                case 8: {
                    if(this.zza(object1, v)) {
                        zzia.zza(object0, v2, zzia.zzf(object1, v2));
                        this.zzb(object0, v);
                    }
                    break;
                }
                case 9: {
                    this.zza(object0, object1, v);
                    break;
                }
                case 10: {
                    if(this.zza(object1, v)) {
                        zzia.zza(object0, v2, zzia.zzf(object1, v2));
                        this.zzb(object0, v);
                    }
                    break;
                }
                case 11: {
                    if(this.zza(object1, v)) {
                        zzia.zza(object0, v2, zzia.zza(object1, v2));
                        this.zzb(object0, v);
                    }
                    break;
                }
                case 12: {
                    if(this.zza(object1, v)) {
                        zzia.zza(object0, v2, zzia.zza(object1, v2));
                        this.zzb(object0, v);
                    }
                    break;
                }
                case 13: {
                    if(this.zza(object1, v)) {
                        zzia.zza(object0, v2, zzia.zza(object1, v2));
                        this.zzb(object0, v);
                    }
                    break;
                }
                case 14: {
                    if(this.zza(object1, v)) {
                        zzia.zza(object0, v2, zzia.zzb(object1, v2));
                        this.zzb(object0, v);
                    }
                    break;
                }
                case 15: {
                    if(this.zza(object1, v)) {
                        zzia.zza(object0, v2, zzia.zza(object1, v2));
                        this.zzb(object0, v);
                    }
                    break;
                }
                case 16: {
                    if(this.zza(object1, v)) {
                        zzia.zza(object0, v2, zzia.zzb(object1, v2));
                        this.zzb(object0, v);
                    }
                    break;
                }
                case 17: {
                    this.zza(object0, object1, v);
                    break;
                }
                case 18: 
                case 19: 
                case 20: 
                case 21: 
                case 22: 
                case 23: 
                case 24: 
                case 25: 
                case 26: 
                case 27: 
                case 28: 
                case 29: 
                case 30: 
                case 0x1F: 
                case 0x20: 
                case 33: 
                case 34: 
                case 35: 
                case 36: 
                case 37: 
                case 38: 
                case 39: 
                case 40: 
                case 41: 
                case 42: 
                case 43: 
                case 44: 
                case 45: 
                case 46: 
                case 0x2F: 
                case 0x30: 
                case 49: {
                    this.zzp.zza(object0, object1, v2);
                    break;
                }
                case 50: {
                    zzhe.zza(this.zzs, object0, object1, v2);
                    break;
                }
                case 51: 
                case 52: 
                case 53: 
                case 54: 
                case 55: 
                case 56: 
                case 57: 
                case 58: 
                case 59: {
                    if(this.zza(object1, v3, v)) {
                        zzia.zza(object0, v2, zzia.zzf(object1, v2));
                        this.zzb(object0, v3, v);
                    }
                    break;
                }
                case 60: {
                    this.zzb(object0, object1, v);
                    break;
                }
                case 61: 
                case 62: 
                case 0x3F: 
                case 0x40: 
                case 65: 
                case 66: 
                case 67: {
                    if(this.zza(object1, v3, v)) {
                        zzia.zza(object0, v2, zzia.zzf(object1, v2));
                        this.zzb(object0, v3, v);
                    }
                    break;
                }
                case 68: {
                    this.zzb(object0, object1, v);
                }
            }
        }
        if(!this.zzj) {
            zzhe.zza(this.zzq, object0, object1);
            if(this.zzh) {
                zzhe.zza(this.zzr, object0, object1);
            }
        }
    }

    private static float zzc(Object object0, long v) {
        return (float)(((Float)zzia.zzf(object0, v)));
    }

    private final zzfj zzc(int v) {
        return (zzfj)this.zzd[(v / 3 << 1) + 1];
    }

    private final boolean zzc(Object object0, Object object1, int v) {
        return this.zza(object0, v) == this.zza(object1, v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final void zzc(Object object0) {
        int v1;
        for(int v = this.zzm; true; ++v) {
            v1 = this.zzn;
            if(v >= v1) {
                break;
            }
            long v2 = (long)(this.zzd(this.zzl[v]) & 0xFFFFF);
            Object object1 = zzia.zzf(object0, v2);
            if(object1 != null) {
                zzia.zza(object0, v2, this.zzs.zzd(object1));
            }
        }
        while(v1 < this.zzl.length) {
            this.zzp.zzb(object0, ((long)this.zzl[v1]));
            ++v1;
        }
        this.zzq.zzd(object0);
        if(this.zzh) {
            this.zzr.zzc(object0);
        }
    }

    private final int zzd(int v) {
        return this.zzc[v + 1];
    }

    private static int zzd(Object object0, long v) {
        return (int)(((Integer)zzia.zzf(object0, v)));
    }

    // This method was un-flattened
    @Override  // com.google.android.gms.internal.measurement.zzhc
    public final boolean zzd(Object object0) {
        int v8;
        int v = 0;
        int v1 = -1;
        int v2 = 0;
        while(v < this.zzm) {
            int v3 = this.zzl[v];
            int v4 = this.zzc[v3];
            int v5 = this.zzd(v3);
            if(this.zzj) {
                v8 = 0;
            }
            else {
                int v6 = this.zzc[v3 + 2];
                int v7 = v6 & 0xFFFFF;
                v8 = 1 << (v6 >>> 20);
                if(v7 != v1) {
                    v2 = zzgr.zzb.getInt(object0, ((long)v7));
                    v1 = v7;
                }
            }
            if((0x10000000 & v5) != 0 && !this.zza(object0, v3, v2, v8)) {
                return false;
            }
            switch((0xFF00000 & v5) >>> 20) {
                case 9: 
                case 17: {
                    if(this.zza(object0, v3, v2, v8) && !zzgr.zza(object0, v5, this.zza(v3))) {
                        return false;
                    }
                    break;
                }
                case 27: 
                case 49: {
                    List list0 = (List)zzia.zzf(object0, ((long)(v5 & 0xFFFFF)));
                    if(!list0.isEmpty()) {
                        zzhc zzhc0 = this.zza(v3);
                        for(int v9 = 0; v9 < list0.size(); ++v9) {
                            if(!zzhc0.zzd(list0.get(v9))) {
                                return false;
                            }
                        }
                    }
                    break;
                }
                case 50: {
                    Object object1 = zzia.zzf(object0, ((long)(v5 & 0xFFFFF)));
                    Map map0 = this.zzs.zzb(object1);
                    if(!map0.isEmpty()) {
                        Object object2 = this.zzb(v3);
                        if(this.zzs.zzf(object2).zzc.zza() == zzio.zzi) {
                            zzhc zzhc1 = null;
                            Iterator iterator0 = map0.values().iterator();
                            while(true) {
                                if(!iterator0.hasNext()) {
                                    break;
                                }
                                Object object3 = iterator0.next();
                                if(zzhc1 == null) {
                                    zzhc1 = zzgy.zza().zza(object3.getClass());
                                }
                                if(zzhc1.zzd(object3)) {
                                    continue;
                                }
                                return false;
                            }
                        }
                    }
                    break;
                }
                case 60: 
                case 68: {
                    if(this.zza(object0, v4, v3) && !zzgr.zza(object0, v5, this.zza(v3))) {
                        return false;
                    }
                }
            }
            ++v;
        }
        return !this.zzh || this.zzr.zza(object0).zzf();
    }

    private final int zze(int v) {
        return this.zzc[v + 2];
    }

    private static long zze(Object object0, long v) {
        return (long)(((Long)zzia.zzf(object0, v)));
    }

    private static zzhx zze(Object object0) {
        zzhx zzhx0 = ((zzfd)object0).zzb;
        if(zzhx0 == zzhx.zza()) {
            zzhx0 = zzhx.zzb();
            ((zzfd)object0).zzb = zzhx0;
        }
        return zzhx0;
    }

    private static boolean zzf(int v) {
        return (v & 0x20000000) != 0;
    }

    private static boolean zzf(Object object0, long v) {
        return ((Boolean)zzia.zzf(object0, v)).booleanValue();
    }

    private final int zzg(int v) {
        return v < this.zze || v > this.zzf ? -1 : this.zzb(v, 0);
    }
}

