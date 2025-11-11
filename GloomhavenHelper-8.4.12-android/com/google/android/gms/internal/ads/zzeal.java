package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import sun.misc.Unsafe;

final class zzeal implements zzebd {
    private static final Unsafe zzhbt;
    private static final int[] zzhve;
    private final int[] zzhvf;
    private final Object[] zzhvg;
    private final int zzhvh;
    private final int zzhvi;
    private final zzeah zzhvj;
    private final boolean zzhvk;
    private final boolean zzhvl;
    private final boolean zzhvm;
    private final boolean zzhvn;
    private final int[] zzhvo;
    private final int zzhvp;
    private final int zzhvq;
    private final zzeap zzhvr;
    private final zzdzr zzhvs;
    private final zzebv zzhvt;
    private final zzdyo zzhvu;
    private final zzeae zzhvv;

    static {
        zzeal.zzhve = new int[0];
        zzeal.zzhbt = zzecb.zzbfk();
    }

    private zzeal(int[] arr_v, Object[] arr_object, int v, int v1, zzeah zzeah0, boolean z, boolean z1, int[] arr_v1, int v2, int v3, zzeap zzeap0, zzdzr zzdzr0, zzebv zzebv0, zzdyo zzdyo0, zzeae zzeae0) {
        this.zzhvf = arr_v;
        this.zzhvg = arr_object;
        this.zzhvh = v;
        this.zzhvi = v1;
        this.zzhvl = zzeah0 instanceof zzdyz;
        this.zzhvm = z;
        this.zzhvk = zzdyo0 != null && zzdyo0.zzj(zzeah0);
        this.zzhvn = false;
        this.zzhvo = arr_v1;
        this.zzhvp = v2;
        this.zzhvq = v3;
        this.zzhvr = zzeap0;
        this.zzhvs = zzdzr0;
        this.zzhvt = zzebv0;
        this.zzhvu = zzdyo0;
        this.zzhvj = zzeah0;
        this.zzhvv = zzeae0;
    }

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final boolean equals(Object object0, Object object1) {
        for(int v = 0; true; v += 3) {
            boolean z = true;
            if(v >= this.zzhvf.length) {
                break;
            }
            int v1 = this.zzgq(v);
            long v2 = (long)(v1 & 0xFFFFF);
            switch((v1 & 0xFF00000) >>> 20) {
                case 0: {
                    if(!this.zzc(object0, object1, v) || Double.doubleToLongBits(zzecb.zzo(object0, v2)) != Double.doubleToLongBits(zzecb.zzo(object1, v2))) {
                        z = false;
                    }
                    break;
                }
                case 1: {
                    if(!this.zzc(object0, object1, v) || Float.floatToIntBits(zzecb.zzn(object0, v2)) != Float.floatToIntBits(zzecb.zzn(object1, v2))) {
                        z = false;
                    }
                    break;
                }
                case 2: {
                    if(!this.zzc(object0, object1, v) || zzecb.zzl(object0, v2) != zzecb.zzl(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 3: {
                    if(!this.zzc(object0, object1, v) || zzecb.zzl(object0, v2) != zzecb.zzl(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 4: {
                    if(!this.zzc(object0, object1, v) || zzecb.zzk(object0, v2) != zzecb.zzk(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 5: {
                    if(!this.zzc(object0, object1, v) || zzecb.zzl(object0, v2) != zzecb.zzl(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 6: {
                    if(!this.zzc(object0, object1, v) || zzecb.zzk(object0, v2) != zzecb.zzk(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 7: {
                    if(!this.zzc(object0, object1, v) || zzecb.zzm(object0, v2) != zzecb.zzm(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 8: {
                    if(!this.zzc(object0, object1, v) || !zzebf.zzg(zzecb.zzp(object0, v2), zzecb.zzp(object1, v2))) {
                        z = false;
                    }
                    break;
                }
                case 9: {
                    if(!this.zzc(object0, object1, v) || !zzebf.zzg(zzecb.zzp(object0, v2), zzecb.zzp(object1, v2))) {
                        z = false;
                    }
                    break;
                }
                case 10: {
                    if(!this.zzc(object0, object1, v) || !zzebf.zzg(zzecb.zzp(object0, v2), zzecb.zzp(object1, v2))) {
                        z = false;
                    }
                    break;
                }
                case 11: {
                    if(!this.zzc(object0, object1, v) || zzecb.zzk(object0, v2) != zzecb.zzk(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 12: {
                    if(!this.zzc(object0, object1, v) || zzecb.zzk(object0, v2) != zzecb.zzk(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 13: {
                    if(!this.zzc(object0, object1, v) || zzecb.zzk(object0, v2) != zzecb.zzk(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 14: {
                    if(!this.zzc(object0, object1, v) || zzecb.zzl(object0, v2) != zzecb.zzl(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 15: {
                    if(!this.zzc(object0, object1, v) || zzecb.zzk(object0, v2) != zzecb.zzk(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 16: {
                    if(!this.zzc(object0, object1, v) || zzecb.zzl(object0, v2) != zzecb.zzl(object1, v2)) {
                        z = false;
                    }
                    break;
                }
                case 17: {
                    if(!this.zzc(object0, object1, v) || !zzebf.zzg(zzecb.zzp(object0, v2), zzecb.zzp(object1, v2))) {
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
                    z = zzebf.zzg(zzecb.zzp(object0, v2), zzecb.zzp(object1, v2));
                    break;
                }
                case 50: {
                    z = zzebf.zzg(zzecb.zzp(object0, v2), zzecb.zzp(object1, v2));
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
                    int v3 = this.zzgr(v);
                    if(zzecb.zzk(object0, ((long)(v3 & 0xFFFFF))) != zzecb.zzk(object1, ((long)(v3 & 0xFFFFF))) || !zzebf.zzg(zzecb.zzp(object0, v2), zzecb.zzp(object1, v2))) {
                        z = false;
                    }
                }
            }
            if(!z) {
                return false;
            }
        }
        if(!this.zzhvt.zzbb(object0).equals(this.zzhvt.zzbb(object1))) {
            return false;
        }
        return this.zzhvk ? this.zzhvu.zzal(object0).equals(this.zzhvu.zzal(object1)) : true;
    }

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final int hashCode(Object object0) {
        int v1 = 0;
        for(int v = 0; v < this.zzhvf.length; v += 3) {
            int v2 = this.zzgq(v);
            int v3 = this.zzhvf[v];
            long v4 = (long)(0xFFFFF & v2);
            int v5 = 37;
            switch((v2 & 0xFF00000) >>> 20) {
                case 0: {
                    v1 = v1 * 53 + zzdzc.zzfr(Double.doubleToLongBits(zzecb.zzo(object0, v4)));
                    break;
                }
                case 1: {
                    v1 = v1 * 53 + Float.floatToIntBits(zzecb.zzn(object0, v4));
                    break;
                }
                case 2: {
                    v1 = v1 * 53 + zzdzc.zzfr(zzecb.zzl(object0, v4));
                    break;
                }
                case 3: {
                    v1 = v1 * 53 + zzdzc.zzfr(zzecb.zzl(object0, v4));
                    break;
                }
                case 4: {
                    v1 = v1 * 53 + zzecb.zzk(object0, v4);
                    break;
                }
                case 5: {
                    v1 = v1 * 53 + zzdzc.zzfr(zzecb.zzl(object0, v4));
                    break;
                }
                case 6: {
                    v1 = v1 * 53 + zzecb.zzk(object0, v4);
                    break;
                }
                case 7: {
                    v1 = v1 * 53 + zzdzc.zzbr(zzecb.zzm(object0, v4));
                    break;
                }
                case 8: {
                    v1 = v1 * 53 + ((String)zzecb.zzp(object0, v4)).hashCode();
                    break;
                }
                case 9: {
                    Object object1 = zzecb.zzp(object0, v4);
                    if(object1 != null) {
                        v5 = object1.hashCode();
                    }
                    v1 = v1 * 53 + v5;
                    break;
                }
                case 10: {
                    v1 = v1 * 53 + zzecb.zzp(object0, v4).hashCode();
                    break;
                }
                case 11: {
                    v1 = v1 * 53 + zzecb.zzk(object0, v4);
                    break;
                }
                case 12: {
                    v1 = v1 * 53 + zzecb.zzk(object0, v4);
                    break;
                }
                case 13: {
                    v1 = v1 * 53 + zzecb.zzk(object0, v4);
                    break;
                }
                case 14: {
                    v1 = v1 * 53 + zzdzc.zzfr(zzecb.zzl(object0, v4));
                    break;
                }
                case 15: {
                    v1 = v1 * 53 + zzecb.zzk(object0, v4);
                    break;
                }
                case 16: {
                    v1 = v1 * 53 + zzdzc.zzfr(zzecb.zzl(object0, v4));
                    break;
                }
                case 17: {
                    Object object2 = zzecb.zzp(object0, v4);
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
                    v1 = v1 * 53 + zzecb.zzp(object0, v4).hashCode();
                    break;
                }
                case 50: {
                    v1 = v1 * 53 + zzecb.zzp(object0, v4).hashCode();
                    break;
                }
                case 51: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzdzc.zzfr(Double.doubleToLongBits(zzeal.zzf(object0, v4)));
                    }
                    break;
                }
                case 52: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + Float.floatToIntBits(zzeal.zzg(object0, v4));
                    }
                    break;
                }
                case 53: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzdzc.zzfr(zzeal.zzi(object0, v4));
                    }
                    break;
                }
                case 54: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzdzc.zzfr(zzeal.zzi(object0, v4));
                    }
                    break;
                }
                case 55: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzeal.zzh(object0, v4);
                    }
                    break;
                }
                case 56: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzdzc.zzfr(zzeal.zzi(object0, v4));
                    }
                    break;
                }
                case 57: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzeal.zzh(object0, v4);
                    }
                    break;
                }
                case 58: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzdzc.zzbr(zzeal.zzj(object0, v4));
                    }
                    break;
                }
                case 59: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + ((String)zzecb.zzp(object0, v4)).hashCode();
                    }
                    break;
                }
                case 60: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzecb.zzp(object0, v4).hashCode();
                    }
                    break;
                }
                case 61: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzecb.zzp(object0, v4).hashCode();
                    }
                    break;
                }
                case 62: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzeal.zzh(object0, v4);
                    }
                    break;
                }
                case 0x3F: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzeal.zzh(object0, v4);
                    }
                    break;
                }
                case 0x40: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzeal.zzh(object0, v4);
                    }
                    break;
                }
                case 65: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzdzc.zzfr(zzeal.zzi(object0, v4));
                    }
                    break;
                }
                case 66: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzeal.zzh(object0, v4);
                    }
                    break;
                }
                case 67: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzdzc.zzfr(zzeal.zzi(object0, v4));
                    }
                    break;
                }
                case 68: {
                    if(this.zza(object0, v3, v)) {
                        v1 = v1 * 53 + zzecb.zzp(object0, v4).hashCode();
                    }
                }
            }
        }
        int v6 = v1 * 53 + this.zzhvt.zzbb(object0).hashCode();
        return this.zzhvk ? v6 * 53 + this.zzhvu.zzal(object0).hashCode() : v6;
    }

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final Object newInstance() {
        return this.zzhvr.newInstance(this.zzhvj);
    }

    private static int zza(zzebv zzebv0, Object object0) {
        return zzebv0.zzax(zzebv0.zzbb(object0));
    }

    private final int zza(Object object0, byte[] arr_b, int v, int v1, int v2, int v3, int v4, int v5, int v6, long v7, int v8, zzdxm zzdxm0) throws IOException {
        int v10;
        Unsafe unsafe0 = zzeal.zzhbt;
        long v9 = (long)(this.zzhvf[v8 + 2] & 0xFFFFF);
        switch(v6) {
            case 51: {
                if(v4 == 1) {
                    unsafe0.putObject(object0, v7, zzdxj.zzh(arr_b, v));
                    v10 = v + 8;
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 52: {
                if(v4 == 5) {
                    unsafe0.putObject(object0, v7, zzdxj.zzi(arr_b, v));
                    v10 = v + 4;
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 53: 
            case 54: {
                if(v4 == 0) {
                    v10 = zzdxj.zzb(arr_b, v, zzdxm0);
                    unsafe0.putObject(object0, v7, zzdxm0.zzhob);
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 58: {
                if(v4 == 0) {
                    v10 = zzdxj.zzb(arr_b, v, zzdxm0);
                    unsafe0.putObject(object0, v7, Boolean.valueOf(zzdxm0.zzhob != 0L));
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 59: {
                if(v4 == 2) {
                    v10 = zzdxj.zza(arr_b, v, zzdxm0);
                    int v11 = zzdxm0.zzhoa;
                    if(v11 == 0) {
                        unsafe0.putObject(object0, v7, "");
                    }
                    else {
                        if((v5 & 0x20000000) != 0 && !zzece.zzm(arr_b, v10, v10 + v11)) {
                            throw zzdzh.zzbdq();
                        }
                        unsafe0.putObject(object0, v7, new String(arr_b, v10, v11, zzdzc.UTF_8));
                        v10 += v11;
                    }
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 60: {
                if(v4 == 2) {
                    v10 = zzdxj.zza(this.zzgn(v8), arr_b, v, v1, zzdxm0);
                    Object object1 = unsafe0.getInt(object0, v9) == v3 ? unsafe0.getObject(object0, v7) : null;
                    if(object1 == null) {
                        unsafe0.putObject(object0, v7, zzdxm0.zzhoc);
                    }
                    else {
                        unsafe0.putObject(object0, v7, zzdzc.zzd(object1, zzdxm0.zzhoc));
                    }
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 61: {
                if(v4 == 2) {
                    v10 = zzdxj.zze(arr_b, v, zzdxm0);
                    unsafe0.putObject(object0, v7, zzdxm0.zzhoc);
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 55: 
            case 62: {
                if(v4 == 0) {
                    v10 = zzdxj.zza(arr_b, v, zzdxm0);
                    unsafe0.putObject(object0, v7, zzdxm0.zzhoa);
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 0x3F: {
                if(v4 == 0) {
                    int v12 = zzdxj.zza(arr_b, v, zzdxm0);
                    int v13 = zzdxm0.zzhoa;
                    zzdzd zzdzd0 = this.zzgp(v8);
                    if(zzdzd0 != null && !zzdzd0.zzf(v13)) {
                        zzeal.zzay(object0).zzd(v2, ((long)v13));
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
                    unsafe0.putObject(object0, v7, zzdxj.zzf(arr_b, v));
                    v10 = v + 4;
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 56: 
            case 65: {
                if(v4 == 1) {
                    unsafe0.putObject(object0, v7, zzdxj.zzg(arr_b, v));
                    v10 = v + 8;
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 66: {
                if(v4 == 0) {
                    v10 = zzdxj.zza(arr_b, v, zzdxm0);
                    unsafe0.putObject(object0, v7, ((int)(-(zzdxm0.zzhoa & 1) ^ zzdxm0.zzhoa >>> 1)));
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 67: {
                if(v4 == 0) {
                    v10 = zzdxj.zzb(arr_b, v, zzdxm0);
                    unsafe0.putObject(object0, v7, ((long)(-(zzdxm0.zzhob & 1L) ^ zzdxm0.zzhob >>> 1)));
                    unsafe0.putInt(object0, v9, v3);
                    return v10;
                }
                break;
            }
            case 68: {
                if(v4 == 3) {
                    v10 = zzdxj.zza(this.zzgn(v8), arr_b, v, v1, v2 & -8 | 4, zzdxm0);
                    Object object2 = unsafe0.getInt(object0, v9) == v3 ? unsafe0.getObject(object0, v7) : null;
                    if(object2 == null) {
                        unsafe0.putObject(object0, v7, zzdxm0.zzhoc);
                    }
                    else {
                        unsafe0.putObject(object0, v7, zzdzc.zzd(object2, zzdxm0.zzhoc));
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

    private final int zza(Object object0, byte[] arr_b, int v, int v1, int v2, int v3, int v4, int v5, long v6, int v7, long v8, zzdxm zzdxm0) throws IOException {
        int v36;
        int v14;
        zzdzi zzdzi0 = (zzdzi)zzeal.zzhbt.getObject(object0, v8);
        if(!zzdzi0.zzbam()) {
            int v9 = zzdzi0.size();
            zzdzi0 = zzdzi0.zzfd((v9 == 0 ? 10 : v9 << 1));
            zzeal.zzhbt.putObject(object0, v8, zzdzi0);
        }
        switch(v7) {
            case 26: {
                if(v4 == 2) {
                    if((v6 & 0x20000000L) == 0L) {
                        v14 = zzdxj.zza(arr_b, v, zzdxm0);
                        int v25 = zzdxm0.zzhoa;
                        if(v25 >= 0) {
                            if(v25 == 0) {
                                zzdzi0.add("");
                            }
                            else {
                                zzdzi0.add(new String(arr_b, v14, v25, zzdzc.UTF_8));
                                v14 += v25;
                            }
                            while(true) {
                                if(v14 >= v1) {
                                    return v14;
                                }
                                int v26 = zzdxj.zza(arr_b, v14, zzdxm0);
                                if(v2 != zzdxm0.zzhoa) {
                                    return v14;
                                }
                                v14 = zzdxj.zza(arr_b, v26, zzdxm0);
                                int v27 = zzdxm0.zzhoa;
                                if(v27 < 0) {
                                    throw zzdzh.zzbdj();
                                }
                                if(v27 == 0) {
                                    zzdzi0.add("");
                                }
                                else {
                                    zzdzi0.add(new String(arr_b, v14, v27, zzdzc.UTF_8));
                                    v14 += v27;
                                }
                            }
                        }
                        throw zzdzh.zzbdj();
                    }
                    v14 = zzdxj.zza(arr_b, v, zzdxm0);
                    int v28 = zzdxm0.zzhoa;
                    if(v28 < 0) {
                        throw zzdzh.zzbdj();
                    }
                    if(v28 == 0) {
                        zzdzi0.add("");
                        goto label_160;
                    }
                    int v29 = v14 + v28;
                    if(zzece.zzm(arr_b, v14, v29)) {
                        zzdzi0.add(new String(arr_b, v14, v28, zzdzc.UTF_8));
                        v14 = v29;
                        while(true) {
                        label_160:
                            if(v14 >= v1) {
                                return v14;
                            }
                            int v30 = zzdxj.zza(arr_b, v14, zzdxm0);
                            if(v2 != zzdxm0.zzhoa) {
                                return v14;
                            }
                            v14 = zzdxj.zza(arr_b, v30, zzdxm0);
                            int v31 = zzdxm0.zzhoa;
                            if(v31 < 0) {
                                throw zzdzh.zzbdj();
                            }
                            if(v31 == 0) {
                                zzdzi0.add("");
                            }
                            else {
                                int v32 = v14 + v31;
                                if(!zzece.zzm(arr_b, v14, v32)) {
                                    throw zzdzh.zzbdq();
                                }
                                zzdzi0.add(new String(arr_b, v14, v31, zzdzc.UTF_8));
                                v14 = v32;
                            }
                        }
                    }
                    throw zzdzh.zzbdq();
                }
                break;
            }
            case 27: {
                return v4 == 2 ? zzdxj.zza(this.zzgn(v5), v2, arr_b, v, v1, zzdzi0, zzdxm0) : v;
            }
            case 28: {
                if(v4 == 2) {
                    v14 = zzdxj.zza(arr_b, v, zzdxm0);
                    int v33 = zzdxm0.zzhoa;
                    if(v33 < 0) {
                        throw zzdzh.zzbdj();
                    }
                    if(v33 <= arr_b.length - v14) {
                        if(v33 == 0) {
                            zzdzi0.add(zzdxn.zzhoe);
                        }
                        else {
                            zzdzi0.add(zzdxn.zzh(arr_b, v14, v33));
                            v14 += v33;
                        }
                        while(true) {
                            if(v14 >= v1) {
                                return v14;
                            }
                            int v34 = zzdxj.zza(arr_b, v14, zzdxm0);
                            if(v2 != zzdxm0.zzhoa) {
                                return v14;
                            }
                            v14 = zzdxj.zza(arr_b, v34, zzdxm0);
                            int v35 = zzdxm0.zzhoa;
                            if(v35 < 0) {
                                throw zzdzh.zzbdj();
                            }
                            if(v35 > arr_b.length - v14) {
                                throw zzdzh.zzbdi();
                            }
                            if(v35 == 0) {
                                zzdzi0.add(zzdxn.zzhoe);
                            }
                            else {
                                zzdzi0.add(zzdxn.zzh(arr_b, v14, v35));
                                v14 += v35;
                            }
                        }
                    }
                    throw zzdzh.zzbdi();
                }
                break;
            }
            case 18: 
            case 35: {
                if(v4 == 2) {
                    v14 = zzdxj.zza(arr_b, v, zzdxm0);
                    int v15 = zzdxm0.zzhoa + v14;
                    while(v14 < v15) {
                        ((zzdyl)zzdzi0).zzd(zzdxj.zzh(arr_b, v14));
                        v14 += 8;
                    }
                    if(v14 != v15) {
                        throw zzdzh.zzbdi();
                    }
                    return v14;
                }
                if(v4 == 1) {
                    ((zzdyl)zzdzi0).zzd(zzdxj.zzh(arr_b, v));
                    for(v14 = v + 8; v14 < v1; v14 = v16 + 8) {
                        int v16 = zzdxj.zza(arr_b, v14, zzdxm0);
                        if(v2 != zzdxm0.zzhoa) {
                            return v14;
                        }
                        ((zzdyl)zzdzi0).zzd(zzdxj.zzh(arr_b, v16));
                    }
                    return v14;
                }
                break;
            }
            case 19: 
            case 36: {
                if(v4 == 2) {
                    v14 = zzdxj.zza(arr_b, v, zzdxm0);
                    int v17 = zzdxm0.zzhoa + v14;
                    while(v14 < v17) {
                        ((zzdyv)zzdzi0).zzh(zzdxj.zzi(arr_b, v14));
                        v14 += 4;
                    }
                    if(v14 != v17) {
                        throw zzdzh.zzbdi();
                    }
                    return v14;
                }
                if(v4 == 5) {
                    ((zzdyv)zzdzi0).zzh(zzdxj.zzi(arr_b, v));
                    for(v14 = v + 4; v14 < v1; v14 = v18 + 4) {
                        int v18 = zzdxj.zza(arr_b, v14, zzdxm0);
                        if(v2 != zzdxm0.zzhoa) {
                            return v14;
                        }
                        ((zzdyv)zzdzi0).zzh(zzdxj.zzi(arr_b, v18));
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
                    v14 = zzdxj.zza(arr_b, v, zzdxm0);
                    int v19 = zzdxm0.zzhoa + v14;
                    while(v14 < v19) {
                        v14 = zzdxj.zzb(arr_b, v14, zzdxm0);
                        ((zzdzv)zzdzi0).zzfs(zzdxm0.zzhob);
                    }
                    if(v14 != v19) {
                        throw zzdzh.zzbdi();
                    }
                    return v14;
                }
                if(v4 == 0) {
                    v14 = zzdxj.zzb(arr_b, v, zzdxm0);
                    ((zzdzv)zzdzi0).zzfs(zzdxm0.zzhob);
                    while(v14 < v1) {
                        int v20 = zzdxj.zza(arr_b, v14, zzdxm0);
                        if(v2 != zzdxm0.zzhoa) {
                            return v14;
                        }
                        v14 = zzdxj.zzb(arr_b, v20, zzdxm0);
                        ((zzdzv)zzdzi0).zzfs(zzdxm0.zzhob);
                    }
                    return v14;
                }
                break;
            }
            case 25: 
            case 42: {
                switch(v4) {
                    case 0: {
                        int v10 = zzdxj.zzb(arr_b, v, zzdxm0);
                        ((zzdxl)zzdzi0).addBoolean(zzdxm0.zzhob != 0L);
                        while(v10 < v1) {
                            int v11 = zzdxj.zza(arr_b, v10, zzdxm0);
                            if(v2 != zzdxm0.zzhoa) {
                                break;
                            }
                            v10 = zzdxj.zzb(arr_b, v11, zzdxm0);
                            ((zzdxl)zzdzi0).addBoolean(zzdxm0.zzhob != 0L);
                        }
                        return v10;
                    }
                    case 2: {
                        int v12 = zzdxj.zza(arr_b, v, zzdxm0);
                        int v13 = zzdxm0.zzhoa + v12;
                        while(v12 < v13) {
                            v12 = zzdxj.zzb(arr_b, v12, zzdxm0);
                            ((zzdxl)zzdzi0).addBoolean(zzdxm0.zzhob != 0L);
                        }
                        if(v12 != v13) {
                            throw zzdzh.zzbdi();
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
                    return zzdxj.zza(arr_b, v, zzdzi0, zzdxm0);
                }
                return v4 == 0 ? zzdxj.zza(v2, arr_b, v, v1, zzdzi0, zzdxm0) : v;
            }
            case 30: 
            case 44: {
                if(v4 == 2) {
                    v36 = zzdxj.zza(arr_b, v, zzdzi0, zzdxm0);
                }
                else if(v4 == 0) {
                    v36 = zzdxj.zza(v2, arr_b, v, v1, zzdzi0, zzdxm0);
                }
                else {
                    break;
                }
                zzeby zzeby0 = ((zzdyz)object0).zzhsw;
                if(zzeby0 == zzeby.zzbff()) {
                    zzeby0 = null;
                }
                zzeby zzeby1 = (zzeby)zzebf.zza(v3, zzdzi0, this.zzgp(v5), zzeby0, this.zzhvt);
                if(zzeby1 != null) {
                    ((zzdyz)object0).zzhsw = zzeby1;
                }
                return v36;
            }
            case 24: 
            case 0x1F: 
            case 41: 
            case 45: {
                if(v4 == 2) {
                    v14 = zzdxj.zza(arr_b, v, zzdxm0);
                    int v23 = zzdxm0.zzhoa + v14;
                    while(v14 < v23) {
                        ((zzdza)zzdzi0).zzgl(zzdxj.zzf(arr_b, v14));
                        v14 += 4;
                    }
                    if(v14 != v23) {
                        throw zzdzh.zzbdi();
                    }
                    return v14;
                }
                if(v4 == 5) {
                    ((zzdza)zzdzi0).zzgl(zzdxj.zzf(arr_b, v));
                    for(v14 = v + 4; v14 < v1; v14 = v24 + 4) {
                        int v24 = zzdxj.zza(arr_b, v14, zzdxm0);
                        if(v2 != zzdxm0.zzhoa) {
                            return v14;
                        }
                        ((zzdza)zzdzi0).zzgl(zzdxj.zzf(arr_b, v24));
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
                    v14 = zzdxj.zza(arr_b, v, zzdxm0);
                    int v21 = zzdxm0.zzhoa + v14;
                    while(v14 < v21) {
                        ((zzdzv)zzdzi0).zzfs(zzdxj.zzg(arr_b, v14));
                        v14 += 8;
                    }
                    if(v14 != v21) {
                        throw zzdzh.zzbdi();
                    }
                    return v14;
                }
                if(v4 == 1) {
                    ((zzdzv)zzdzi0).zzfs(zzdxj.zzg(arr_b, v));
                    for(v14 = v + 8; v14 < v1; v14 = v22 + 8) {
                        int v22 = zzdxj.zza(arr_b, v14, zzdxm0);
                        if(v2 != zzdxm0.zzhoa) {
                            return v14;
                        }
                        ((zzdzv)zzdzi0).zzfs(zzdxj.zzg(arr_b, v22));
                    }
                    return v14;
                }
                break;
            }
            case 33: 
            case 0x2F: {
                if(v4 == 2) {
                    v14 = zzdxj.zza(arr_b, v, zzdxm0);
                    int v37 = zzdxm0.zzhoa + v14;
                    while(v14 < v37) {
                        v14 = zzdxj.zza(arr_b, v14, zzdxm0);
                        ((zzdza)zzdzi0).zzgl(-(zzdxm0.zzhoa & 1) ^ zzdxm0.zzhoa >>> 1);
                    }
                    if(v14 != v37) {
                        throw zzdzh.zzbdi();
                    }
                    return v14;
                }
                if(v4 == 0) {
                    v14 = zzdxj.zza(arr_b, v, zzdxm0);
                    ((zzdza)zzdzi0).zzgl(-(zzdxm0.zzhoa & 1) ^ zzdxm0.zzhoa >>> 1);
                    while(v14 < v1) {
                        int v38 = zzdxj.zza(arr_b, v14, zzdxm0);
                        if(v2 != zzdxm0.zzhoa) {
                            return v14;
                        }
                        v14 = zzdxj.zza(arr_b, v38, zzdxm0);
                        ((zzdza)zzdzi0).zzgl(-(zzdxm0.zzhoa & 1) ^ zzdxm0.zzhoa >>> 1);
                    }
                    return v14;
                }
                break;
            }
            case 34: 
            case 0x30: {
                if(v4 == 2) {
                    v14 = zzdxj.zza(arr_b, v, zzdxm0);
                    int v39 = zzdxm0.zzhoa + v14;
                    while(v14 < v39) {
                        v14 = zzdxj.zzb(arr_b, v14, zzdxm0);
                        ((zzdzv)zzdzi0).zzfs(-(zzdxm0.zzhob & 1L) ^ zzdxm0.zzhob >>> 1);
                    }
                    if(v14 != v39) {
                        throw zzdzh.zzbdi();
                    }
                    return v14;
                }
                if(v4 == 0) {
                    v14 = zzdxj.zzb(arr_b, v, zzdxm0);
                    ((zzdzv)zzdzi0).zzfs(-(zzdxm0.zzhob & 1L) ^ zzdxm0.zzhob >>> 1);
                    while(v14 < v1) {
                        int v40 = zzdxj.zza(arr_b, v14, zzdxm0);
                        if(v2 != zzdxm0.zzhoa) {
                            break;
                        }
                        v14 = zzdxj.zzb(arr_b, v40, zzdxm0);
                        ((zzdzv)zzdzi0).zzfs(-(zzdxm0.zzhob & 1L) ^ zzdxm0.zzhob >>> 1);
                    }
                    return v14;
                }
                break;
            }
            case 49: {
                if(v4 == 3) {
                    zzebd zzebd0 = this.zzgn(v5);
                    int v41 = v2 & -8 | 4;
                    int v42 = zzdxj.zza(zzebd0, arr_b, v, v1, v41, zzdxm0);
                    zzdzi0.add(zzdxm0.zzhoc);
                    while(v42 < v1) {
                        int v43 = zzdxj.zza(arr_b, v42, zzdxm0);
                        if(v2 != zzdxm0.zzhoa) {
                            break;
                        }
                        v42 = zzdxj.zza(zzebd0, arr_b, v43, v1, v41, zzdxm0);
                        zzdzi0.add(zzdxm0.zzhoc);
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

    private final int zza(Object object0, byte[] arr_b, int v, int v1, int v2, long v3, zzdxm zzdxm0) throws IOException {
        int v9;
        Unsafe unsafe0 = zzeal.zzhbt;
        Object object1 = this.zzgo(v2);
        Object object2 = unsafe0.getObject(object0, v3);
        if(this.zzhvv.zzau(object2)) {
            Object object3 = this.zzhvv.zzaw(object1);
            this.zzhvv.zze(object3, object2);
            unsafe0.putObject(object0, v3, object3);
            object2 = object3;
        }
        zzeac zzeac0 = this.zzhvv.zzas(object1);
        Map map0 = this.zzhvv.zzar(object2);
        int v4 = zzdxj.zza(arr_b, v, zzdxm0);
        int v5 = zzdxm0.zzhoa;
        if(v5 < 0 || v5 > v1 - v4) {
            throw zzdzh.zzbdi();
        }
        int v6 = v5 + v4;
        Object object4 = zzeac0.zzhva;
        Object object5 = zzeac0.zzcgl;
        while(v4 < v6) {
            int v7 = arr_b[v4];
            if(v7 < 0) {
                int v8 = zzdxj.zza(v7, arr_b, v4 + 1, zzdxm0);
                v7 = zzdxm0.zzhoa;
                v9 = v8;
            }
            else {
                v9 = v4 + 1;
            }
            switch(v7 >>> 3) {
                case 1: {
                    if((v7 & 7) == zzeac0.zzhuz.zzbfp()) {
                        v4 = zzeal.zza(arr_b, v9, v1, zzeac0.zzhuz, null, zzdxm0);
                        object4 = zzdxm0.zzhoc;
                        continue;
                    }
                    break;
                }
                case 2: {
                    if((v7 & 7) == zzeac0.zzhvb.zzbfp()) {
                        Class class0 = zzeac0.zzcgl.getClass();
                        v4 = zzeal.zza(arr_b, v9, v1, zzeac0.zzhvb, class0, zzdxm0);
                        object5 = zzdxm0.zzhoc;
                        continue;
                    }
                }
            }
            v4 = zzdxj.zza(v7, arr_b, v9, v1, zzdxm0);
        }
        if(v4 != v6) {
            throw zzdzh.zzbdp();
        }
        map0.put(object4, object5);
        return v6;
    }

    private static int zza(byte[] arr_b, int v, int v1, zzecm zzecm0, Class class0, zzdxm zzdxm0) throws IOException {
        int v2;
        switch(zzeao.zzhoz[zzecm0.ordinal()]) {
            case 1: {
                v2 = zzdxj.zzb(arr_b, v, zzdxm0);
                zzdxm0.zzhoc = Boolean.valueOf(zzdxm0.zzhob != 0L);
                return v2;
            }
            case 2: {
                return zzdxj.zze(arr_b, v, zzdxm0);
            }
            case 3: {
                zzdxm0.zzhoc = zzdxj.zzh(arr_b, v);
                return v + 8;
            }
            case 4: 
            case 5: {
                zzdxm0.zzhoc = zzdxj.zzf(arr_b, v);
                return v + 4;
            }
            case 6: 
            case 7: {
                zzdxm0.zzhoc = zzdxj.zzg(arr_b, v);
                return v + 8;
            }
            case 8: {
                zzdxm0.zzhoc = zzdxj.zzi(arr_b, v);
                return v + 4;
            }
            case 9: 
            case 10: 
            case 11: {
                v2 = zzdxj.zza(arr_b, v, zzdxm0);
                zzdxm0.zzhoc = zzdxm0.zzhoa;
                return v2;
            }
            case 12: 
            case 13: {
                v2 = zzdxj.zzb(arr_b, v, zzdxm0);
                zzdxm0.zzhoc = zzdxm0.zzhob;
                return v2;
            }
            case 14: {
                return zzdxj.zza(zzeaw.zzbem().zzh(class0), arr_b, v, v1, zzdxm0);
            }
            case 15: {
                v2 = zzdxj.zza(arr_b, v, zzdxm0);
                zzdxm0.zzhoc = (int)(-(zzdxm0.zzhoa & 1) ^ zzdxm0.zzhoa >>> 1);
                return v2;
            }
            case 16: {
                v2 = zzdxj.zzb(arr_b, v, zzdxm0);
                zzdxm0.zzhoc = (long)(-(zzdxm0.zzhob & 1L) ^ zzdxm0.zzhob >>> 1);
                return v2;
            }
            case 17: {
                return zzdxj.zzd(arr_b, v, zzdxm0);
            }
            default: {
                throw new RuntimeException("unsupported field type.");
            }
        }
    }

    static zzeal zza(Class class0, zzeaf zzeaf0, zzeap zzeap0, zzdzr zzdzr0, zzebv zzebv0, zzdyo zzdyo0, zzeae zzeae0) {
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
        if(zzeaf0 instanceof zzeay) {
            boolean z = ((zzeay)zzeaf0).zzbec() == zzf.zzhtl;
            String s = ((zzeay)zzeaf0).zzben();
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
                arr_v = zzeal.zzhve;
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
            Unsafe unsafe0 = zzeal.zzhbt;
            Object[] arr_object = ((zzeay)zzeaf0).zzbeo();
            Class class1 = ((zzeay)zzeaf0).zzbee().getClass();
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
                        field0 = zzeal.zza(class1, ((String)object0));
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
                        field1 = zzeal.zza(class1, ((String)object1));
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
                    Field field2 = zzeal.zza(class1, ((String)arr_object[v62]));
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
                            field3 = zzeal.zza(class1, ((String)object2));
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
            return new zzeal(arr_v1, arr_object1, v15, v17, ((zzeay)zzeaf0).zzbee(), z, false, arr_v, v, v63, zzeap0, zzdzr0, zzebv0, zzdyo0, zzeae0);
        }
        ((zzebs)zzeaf0).zzbec();
        throw new NoSuchMethodError();
    }

    private final Object zza(int v, int v1, Map map0, zzdzd zzdzd0, Object object0, zzebv zzebv0) {
        Object object1 = this.zzgo(v);
        zzeac zzeac0 = this.zzhvv.zzas(object1);
        Iterator iterator0 = map0.entrySet().iterator();
        while(iterator0.hasNext()) {
            Object object2 = iterator0.next();
            Map.Entry map$Entry0 = (Map.Entry)object2;
            if(!zzdzd0.zzf(((int)(((Integer)map$Entry0.getValue()))))) {
                if(object0 == null) {
                    object0 = zzebv0.zzbfe();
                }
                zzdxv zzdxv0 = zzdxn.zzfg(zzdzz.zza(zzeac0, map$Entry0.getKey(), map$Entry0.getValue()));
                zzdyi zzdyi0 = zzdxv0.zzbba();
                try {
                    zzdzz.zza(zzdyi0, zzeac0, map$Entry0.getKey(), map$Entry0.getValue());
                }
                catch(IOException iOException0) {
                    throw new RuntimeException(iOException0);
                }
                zzebv0.zza(object0, v1, zzdxv0.zzbaz());
                iterator0.remove();
            }
        }
        return object0;
    }

    private final Object zza(Object object0, int v, Object object1, zzebv zzebv0) {
        int v1 = this.zzhvf[v];
        Object object2 = zzecb.zzp(object0, ((long)(this.zzgq(v) & 0xFFFFF)));
        if(object2 == null) {
            return object1;
        }
        zzdzd zzdzd0 = this.zzgp(v);
        return zzdzd0 == null ? object1 : this.zza(v, v1, this.zzhvv.zzar(object2), zzdzd0, object1, zzebv0);
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

    private static void zza(int v, Object object0, zzecs zzecs0) throws IOException {
        if(object0 instanceof String) {
            zzecs0.zzf(v, ((String)object0));
            return;
        }
        zzecs0.zza(v, ((zzdxn)object0));
    }

    private static void zza(zzebv zzebv0, Object object0, zzecs zzecs0) throws IOException {
        zzebv0.zza(zzebv0.zzbb(object0), zzecs0);
    }

    private final void zza(zzecs zzecs0, int v, Object object0, int v1) throws IOException {
        if(object0 != null) {
            Object object1 = this.zzgo(v1);
            zzecs0.zza(v, this.zzhvv.zzas(object1), this.zzhvv.zzat(object0));
        }
    }

    private final void zza(Object object0, int v, zzeax zzeax0) throws IOException {
        if(zzeal.zzgs(v)) {
            zzecb.zza(object0, ((long)(v & 0xFFFFF)), zzeax0.zzbbi());
            return;
        }
        if(this.zzhvl) {
            zzecb.zza(object0, ((long)(v & 0xFFFFF)), zzeax0.readString());
            return;
        }
        zzecb.zza(object0, ((long)(v & 0xFFFFF)), zzeax0.zzbbj());
    }

    private final void zza(Object object0, Object object1, int v) {
        int v1 = this.zzgq(v);
        if(!this.zze(object1, v)) {
            return;
        }
        Object object2 = zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)));
        Object object3 = zzecb.zzp(object1, ((long)(v1 & 0xFFFFF)));
        if(object2 != null && object3 != null) {
            zzecb.zza(object0, ((long)(v1 & 0xFFFFF)), zzdzc.zzd(object2, object3));
            this.zzf(object0, v);
            return;
        }
        if(object3 != null) {
            zzecb.zza(object0, ((long)(v1 & 0xFFFFF)), object3);
            this.zzf(object0, v);
        }
    }

    private final boolean zza(Object object0, int v, int v1) {
        return zzecb.zzk(object0, ((long)(this.zzgr(v1) & 0xFFFFF))) == v;
    }

    // 去混淆评级： 低(20)
    private final boolean zza(Object object0, int v, int v1, int v2) {
        return this.zzhvm ? this.zze(object0, v) : (v1 & v2) != 0;
    }

    private static boolean zza(Object object0, int v, zzebd zzebd0) {
        return zzebd0.zzaz(zzecb.zzp(object0, ((long)(v & 0xFFFFF))));
    }

    final int zza(Object object0, byte[] arr_b, int v, int v1, int v2, zzdxm zzdxm0) throws IOException {
        int v43;
        int v42;
        int v41;
        int v40;
        int v39;
        zzdzi zzdzi2;
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
        Unsafe unsafe0 = zzeal.zzhbt;
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
                    v11 = zzdxj.zza(v10, arr_b, v4 + 1, zzdxm0);
                    v12 = zzdxm0.zzhoa;
                }
                else {
                    v12 = v10;
                    v11 = v4 + 1;
                }
                int v13 = v12 >>> 3;
                int v14 = v13 <= v5 ? this.zzgt(v13) : this.zzao(v13, v6 / 3);
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
                    int[] arr_v = this.zzhvf;
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
                                    zzecb.zza(object0, v24, zzdxj.zzh(arr_b, v33));
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
                                    zzecb.zza(object0, v24, zzdxj.zzi(arr_b, v33));
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
                                    int v34 = zzdxj.zzb(arr_b, v33, zzdxm0);
                                    unsafe0.putLong(object0, v24, zzdxm0.zzhob);
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
                                    int v35 = zzdxj.zzb(arr_b, v11, zzdxm0);
                                    zzecb.zza(object0, v24, zzdxm0.zzhob != 0L);
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
                                    v4 = (v25 & 0x20000000) == 0 ? zzdxj.zzc(arr_b, v11, zzdxm0) : zzdxj.zzd(arr_b, v11, zzdxm0);
                                    unsafe0.putObject(object0, v24, zzdxm0.zzhoc);
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
                                    v4 = zzdxj.zza(this.zzgn(v29), arr_b, v11, v1, zzdxm0);
                                    if((v8 & v27) == 0) {
                                        unsafe0.putObject(object0, v24, zzdxm0.zzhoc);
                                    }
                                    else {
                                        unsafe0.putObject(object0, v24, zzdzc.zzd(unsafe0.getObject(object0, v24), zzdxm0.zzhoc));
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
                                    v4 = zzdxj.zze(arr_b, v11, zzdxm0);
                                    unsafe0.putObject(object0, v24, zzdxm0.zzhoc);
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
                                    v4 = zzdxj.zza(arr_b, v33, zzdxm0);
                                    unsafe0.putInt(object0, v24, zzdxm0.zzhoa);
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
                                    v4 = zzdxj.zza(arr_b, v11, zzdxm0);
                                    int v36 = zzdxm0.zzhoa;
                                    zzdzd zzdzd0 = this.zzgp(v29);
                                    if(zzdzd0 == null || zzdzd0.zzf(v36)) {
                                        unsafe0.putInt(object0, v24, v36);
                                        v8 |= v27;
                                    }
                                    else {
                                        zzeal.zzay(object0).zzd(v32, ((long)v36));
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
                                    unsafe0.putInt(object0, v24, zzdxj.zzf(arr_b, v11));
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
                                    unsafe0.putLong(object0, v24, zzdxj.zzg(arr_b, v11));
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
                                    v4 = zzdxj.zza(arr_b, v11, zzdxm0);
                                    unsafe0.putInt(object0, v24, -(zzdxm0.zzhoa & 1) ^ zzdxm0.zzhoa >>> 1);
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
                                    int v37 = zzdxj.zzb(arr_b, v11, zzdxm0);
                                    unsafe0.putLong(object0, v24, -(zzdxm0.zzhob & 1L) ^ zzdxm0.zzhob >>> 1);
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
                                    v4 = zzdxj.zza(this.zzgn(v14), arr_b, v11, v1, v13 << 3 | 4, zzdxm0);
                                    if((v8 & v27) == 0) {
                                        unsafe0.putObject(object0, v24, zzdxm0.zzhoc);
                                    }
                                    else {
                                        unsafe0.putObject(object0, v24, zzdzc.zzd(unsafe0.getObject(object0, v24), zzdxm0.zzhoc));
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
                                zzdzi zzdzi0 = (zzdzi)unsafe0.getObject(object0, v24);
                                if(zzdzi0.zzbam()) {
                                    zzdzi2 = zzdzi0;
                                }
                                else {
                                    int v38 = zzdzi0.size();
                                    zzdzi zzdzi1 = zzdzi0.zzfd((v38 == 0 ? 10 : v38 << 1));
                                    unsafe0.putObject(object0, v24, zzdzi1);
                                    zzdzi2 = zzdzi1;
                                }
                                v4 = zzdxj.zza(this.zzgn(v14), v12, arr_b, v11, v1, zzdzi2, zzdxm0);
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
                                v4 = this.zza(object0, arr_b, v11, v1, v12, v13, v12 & 7, v14, ((long)v25), v23, v24, zzdxm0);
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
                                        v4 = this.zza(object0, arr_b, v39, v1, v20, v24, zzdxm0);
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
                                    v4 = this.zza(object0, arr_b, v39, v1, v40, v15, v12 & 7, v25, v23, v24, v20, zzdxm0);
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
                                        if(this.zzhvk) {
                                            zzdym zzdym0 = zzdym.zzbcg();
                                            if(zzdxm0.zzhod == zzdym0) {
                                                v43 = v15;
                                                goto label_374;
                                            }
                                            else {
                                                if(zzdxm0.zzhod.zza(this.zzhvj, v15) == null) {
                                                    v4 = zzdxj.zza(v21, arr_b, v16, v1, zzeal.zzay(object0), zzdxm0);
                                                    v3 = v19;
                                                    v7 = v21;
                                                    v5 = v15;
                                                    v9 = v18;
                                                    v6 = v20;
                                                    v8 = v17;
                                                    continue;
                                                }
                                                ((zzb)object0).zzbdf();
                                                throw new NoSuchMethodError();
                                            }
                                            goto label_373;
                                        }
                                        else {
                                        label_373:
                                            v43 = v15;
                                        }
                                    label_374:
                                        v4 = zzdxj.zza(v21, arr_b, v16, v1, zzeal.zzay(object0), zzdxm0);
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
        zzeby zzeby0 = null;
        for(int v44 = this.zzhvp; v44 < this.zzhvq; ++v44) {
            zzeby0 = (zzeby)this.zza(object0, this.zzhvo[v44], zzeby0, this.zzhvt);
        }
        if(zzeby0 != null) {
            this.zzhvt.zzi(object0, zzeby0);
        }
        if(v19 == 0) {
            if(v16 != v1) {
                throw zzdzh.zzbdp();
            }
            return v16;
        }
        if(v16 > v1 || v7 != v19) {
            throw zzdzh.zzbdp();
        }
        return v16;
    }

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final void zza(Object object0, zzeax zzeax0, zzdym zzdym0) throws IOException {
        int v4;
        int v3;
        int v1;
        int v;
        if(zzdym0 == null) {
            throw new NullPointerException();
        }
        zzebv zzebv0 = this.zzhvt;
        zzdyo zzdyo0 = this.zzhvu;
        zzdyp zzdyp0 = null;
        Object object1 = null;
        while(true) {
        alab1:
            while(true) {
                try {
                    while(true) {
                    label_5:
                        v = zzeax0.zzbbz();
                        v1 = this.zzgt(v);
                        if(v1 >= 0) {
                            goto label_35;
                        }
                        if(v == 0x7FFFFFFF) {
                            goto label_27;
                        }
                        Object object2 = this.zzhvk ? zzdyo0.zza(zzdym0, this.zzhvj, v) : null;
                        if(object2 == null) {
                            zzebv0.zza(zzeax0);
                            if(object1 == null) {
                                object1 = zzebv0.zzbc(object0);
                            }
                            if(!zzebv0.zza(object1, zzeax0)) {
                                break;
                            }
                        }
                        else {
                            zzdyp zzdyp1 = zzdyp0 == null ? zzdyo0.zzam(object0) : zzdyp0;
                            object1 = zzdyo0.zza(zzeax0, object2, zzdym0, zzdyp1, object1, zzebv0);
                            zzdyp0 = zzdyp1;
                        }
                    }
                }
                catch(Throwable throwable0) {
                    goto label_276;
                }
                for(int v2 = this.zzhvp; v2 < this.zzhvq; ++v2) {
                    object1 = this.zza(object0, this.zzhvo[v2], object1, zzebv0);
                }
                if(object1 != null) {
                    zzebv0.zzi(object0, object1);
                }
                return;
                try {
                label_27:
                    v3 = this.zzhvp;
                }
                catch(Throwable throwable0) {
                    goto label_276;
                }
                while(v3 < this.zzhvq) {
                    object1 = this.zza(object0, this.zzhvo[v3], object1, zzebv0);
                    ++v3;
                }
                if(object1 != null) {
                    zzebv0.zzi(object0, object1);
                }
                return;
                try {
                label_35:
                    v4 = this.zzgq(v1);
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
                                    object1 = zzebv0.zzbfe();
                                }
                                if(zzebv0.zza(object1, zzeax0)) {
                                    break;
                                }
                                break alab1;
                            }
                            catch(zzdzk unused_ex) {
                                goto label_263;
                            }
                        }
                    }
                }
                catch(Throwable throwable0) {
                    goto label_276;
                }
            }
            for(int v5 = this.zzhvp; v5 < this.zzhvq; ++v5) {
                object1 = this.zza(object0, this.zzhvo[v5], object1, zzebv0);
            }
            if(object1 != null) {
                zzebv0.zzi(object0, object1);
            }
            return;
            try {
                try {
                label_48:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.readDouble());
                    this.zzf(object0, v1);
                    goto label_5;
                label_51:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.readFloat());
                    this.zzf(object0, v1);
                    goto label_5;
                label_54:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbd());
                    this.zzf(object0, v1);
                    goto label_5;
                label_57:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbc());
                    this.zzf(object0, v1);
                    goto label_5;
                label_60:
                    zzecb.zzb(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbe());
                    this.zzf(object0, v1);
                    goto label_5;
                label_63:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbf());
                    this.zzf(object0, v1);
                    goto label_5;
                label_66:
                    zzecb.zzb(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbg());
                    this.zzf(object0, v1);
                    goto label_5;
                label_69:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbh());
                    this.zzf(object0, v1);
                    goto label_5;
                label_72:
                    this.zza(object0, v4, zzeax0);
                    this.zzf(object0, v1);
                    goto label_5;
                label_75:
                    if(this.zze(object0, v1)) {
                        zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzdzc.zzd(zzecb.zzp(object0, ((long)(v4 & 0xFFFFF))), zzeax0.zza(this.zzgn(v1), zzdym0)));
                    }
                    else {
                        zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zza(this.zzgn(v1), zzdym0));
                        this.zzf(object0, v1);
                    }
                    goto label_5;
                label_81:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbj());
                    this.zzf(object0, v1);
                    goto label_5;
                label_84:
                    zzecb.zzb(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbk());
                    this.zzf(object0, v1);
                    goto label_5;
                label_87:
                    int v6 = zzeax0.zzbbl();
                    zzdzd zzdzd0 = this.zzgp(v1);
                    if(zzdzd0 == null || zzdzd0.zzf(v6)) {
                        zzecb.zzb(object0, ((long)(v4 & 0xFFFFF)), v6);
                        this.zzf(object0, v1);
                    }
                    else {
                        object1 = zzebf.zza(v, v6, object1, zzebv0);
                    }
                    goto label_5;
                label_95:
                    zzecb.zzb(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbm());
                    this.zzf(object0, v1);
                    goto label_5;
                label_98:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbn());
                    this.zzf(object0, v1);
                    goto label_5;
                label_101:
                    zzecb.zzb(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbo());
                    this.zzf(object0, v1);
                    goto label_5;
                label_104:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbp());
                    this.zzf(object0, v1);
                    goto label_5;
                label_107:
                    if(this.zze(object0, v1)) {
                        zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzdzc.zzd(zzecb.zzp(object0, ((long)(v4 & 0xFFFFF))), zzeax0.zzb(this.zzgn(v1), zzdym0)));
                    }
                    else {
                        zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzb(this.zzgn(v1), zzdym0));
                        this.zzf(object0, v1);
                    }
                    goto label_5;
                label_113:
                    zzeax0.zzj(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_115:
                    zzeax0.zzk(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_117:
                    zzeax0.zzm(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_119:
                    zzeax0.zzl(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_121:
                    zzeax0.zzn(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_123:
                    zzeax0.zzo(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_125:
                    zzeax0.zzp(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_127:
                    zzeax0.zzq(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_129:
                    if(zzeal.zzgs(v4)) {
                        zzeax0.zzr(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    }
                    else {
                        zzeax0.readStringList(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    }
                    goto label_5;
                label_134:
                    zzebd zzebd0 = this.zzgn(v1);
                    zzeax0.zza(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))), zzebd0, zzdym0);
                    goto label_5;
                label_137:
                    zzeax0.zzs(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_139:
                    zzeax0.zzt(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_141:
                    List list0 = this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF)));
                    zzeax0.zzu(list0);
                    object1 = zzebf.zza(v, list0, this.zzgp(v1), object1, zzebv0);
                    goto label_5;
                label_145:
                    zzeax0.zzv(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_147:
                    zzeax0.zzw(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_149:
                    zzeax0.zzx(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_151:
                    zzeax0.zzy(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_153:
                    zzeax0.zzj(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_155:
                    zzeax0.zzk(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_157:
                    zzeax0.zzm(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_159:
                    zzeax0.zzl(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_161:
                    zzeax0.zzn(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_163:
                    zzeax0.zzo(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_165:
                    zzeax0.zzp(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_167:
                    zzeax0.zzq(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_169:
                    zzeax0.zzt(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_171:
                    List list1 = this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF)));
                    zzeax0.zzu(list1);
                    object1 = zzebf.zza(v, list1, this.zzgp(v1), object1, zzebv0);
                    goto label_5;
                label_175:
                    zzeax0.zzv(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_177:
                    zzeax0.zzw(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_179:
                    zzeax0.zzx(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_181:
                    zzeax0.zzy(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_5;
                label_183:
                    zzebd zzebd1 = this.zzgn(v1);
                    zzeax0.zzb(this.zzhvs.zza(object0, ((long)(v4 & 0xFFFFF))), zzebd1, zzdym0);
                    goto label_5;
                label_186:
                    Object object3 = this.zzgo(v1);
                    long v7 = (long)(this.zzgq(v1) & 0xFFFFF);
                    Object object4 = zzecb.zzp(object0, v7);
                    if(object4 == null) {
                        object4 = this.zzhvv.zzaw(object3);
                        zzecb.zza(object0, v7, object4);
                    }
                    else if(this.zzhvv.zzau(object4)) {
                        Object object5 = this.zzhvv.zzaw(object3);
                        this.zzhvv.zze(object5, object4);
                        zzecb.zza(object0, v7, object5);
                        object4 = object5;
                    }
                    zzeax0.zza(this.zzhvv.zzar(object4), this.zzhvv.zzas(object3), zzdym0);
                    goto label_5;
                label_200:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.readDouble());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_203:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.readFloat());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_206:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbd());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_209:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbc());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_212:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbe());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_215:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbf());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_218:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbg());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_221:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), Boolean.valueOf(zzeax0.zzbbh()));
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_224:
                    this.zza(object0, v4, zzeax0);
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_227:
                    if(this.zza(object0, v, v1)) {
                        zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzdzc.zzd(zzecb.zzp(object0, ((long)(v4 & 0xFFFFF))), zzeax0.zza(this.zzgn(v1), zzdym0)));
                    }
                    else {
                        zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zza(this.zzgn(v1), zzdym0));
                        this.zzf(object0, v1);
                    }
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_234:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbj());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_237:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbk());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_240:
                    int v8 = zzeax0.zzbbl();
                    zzdzd zzdzd1 = this.zzgp(v1);
                    if(zzdzd1 == null || zzdzd1.zzf(v8)) {
                        zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), v8);
                        this.zzb(object0, v, v1);
                    }
                    else {
                        object1 = zzebf.zza(v, v8, object1, zzebv0);
                    }
                    goto label_5;
                label_248:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbm());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_251:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbn());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_254:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbo());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_257:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzbbp());
                    this.zzb(object0, v, v1);
                    goto label_5;
                label_260:
                    zzecb.zza(object0, ((long)(v4 & 0xFFFFF)), zzeax0.zzb(this.zzgn(v1), zzdym0));
                    this.zzb(object0, v, v1);
                    goto label_5;
                }
                catch(zzdzk unused_ex) {
                }
            label_263:
                zzebv0.zza(zzeax0);
                if(object1 == null) {
                    object1 = zzebv0.zzbc(object0);
                }
                if(zzebv0.zza(object1, zzeax0)) {
                    goto label_5;
                }
                break;
            }
            catch(Throwable throwable0) {
                goto label_276;
            }
        }
        for(int v9 = this.zzhvp; v9 < this.zzhvq; ++v9) {
            object1 = this.zza(object0, this.zzhvo[v9], object1, zzebv0);
        }
        if(object1 != null) {
            zzebv0.zzi(object0, object1);
        }
        return;
    label_276:
        for(int v10 = this.zzhvp; v10 < this.zzhvq; ++v10) {
            object1 = this.zza(object0, this.zzhvo[v10], object1, zzebv0);
        }
        if(object1 != null) {
            zzebv0.zzi(object0, object1);
        }
        throw throwable0;
    }

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final void zza(Object object0, zzecs zzecs0) throws IOException {
        Map.Entry map$Entry1;
        Iterator iterator1;
        Map.Entry map$Entry0;
        Iterator iterator0;
        if(zzecs0.zzbcf() == zzf.zzhto) {
            zzeal.zza(this.zzhvt, object0, zzecs0);
            if(this.zzhvk) {
                zzdyp zzdyp0 = this.zzhvu.zzal(object0);
                if(zzdyp0.zzhpu.isEmpty()) {
                    iterator0 = null;
                    map$Entry0 = null;
                }
                else {
                    iterator0 = zzdyp0.descendingIterator();
                    Object object1 = iterator0.next();
                    map$Entry0 = (Map.Entry)object1;
                }
            }
            else {
                iterator0 = null;
                map$Entry0 = null;
            }
            for(int v = this.zzhvf.length - 3; v >= 0; v -= 3) {
                int v1 = this.zzgq(v);
                int v2 = this.zzhvf[v];
                while(map$Entry0 != null && this.zzhvu.zza(map$Entry0) > v2) {
                    this.zzhvu.zza(zzecs0, map$Entry0);
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
                        if(this.zze(object0, v)) {
                            zzecs0.zzb(v2, zzecb.zzo(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 1: {
                        if(this.zze(object0, v)) {
                            zzecs0.zza(v2, zzecb.zzn(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 2: {
                        if(this.zze(object0, v)) {
                            zzecs0.zzp(v2, zzecb.zzl(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 3: {
                        if(this.zze(object0, v)) {
                            zzecs0.zzh(v2, zzecb.zzl(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 4: {
                        if(this.zze(object0, v)) {
                            zzecs0.zzac(v2, zzecb.zzk(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 5: {
                        if(this.zze(object0, v)) {
                            zzecs0.zzj(v2, zzecb.zzl(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 6: {
                        if(this.zze(object0, v)) {
                            zzecs0.zzaf(v2, zzecb.zzk(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 7: {
                        if(this.zze(object0, v)) {
                            zzecs0.zzh(v2, zzecb.zzm(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 8: {
                        if(this.zze(object0, v)) {
                            zzeal.zza(v2, zzecb.zzp(object0, ((long)(v1 & 0xFFFFF))), zzecs0);
                        }
                        break;
                    }
                    case 9: {
                        if(this.zze(object0, v)) {
                            zzecs0.zza(v2, zzecb.zzp(object0, ((long)(v1 & 0xFFFFF))), this.zzgn(v));
                        }
                        break;
                    }
                    case 10: {
                        if(this.zze(object0, v)) {
                            zzecs0.zza(v2, ((zzdxn)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))));
                        }
                        break;
                    }
                    case 11: {
                        if(this.zze(object0, v)) {
                            zzecs0.zzad(v2, zzecb.zzk(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 12: {
                        if(this.zze(object0, v)) {
                            zzecs0.zzan(v2, zzecb.zzk(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 13: {
                        if(this.zze(object0, v)) {
                            zzecs0.zzam(v2, zzecb.zzk(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 14: {
                        if(this.zze(object0, v)) {
                            zzecs0.zzq(v2, zzecb.zzl(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 15: {
                        if(this.zze(object0, v)) {
                            zzecs0.zzae(v2, zzecb.zzk(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 16: {
                        if(this.zze(object0, v)) {
                            zzecs0.zzi(v2, zzecb.zzl(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 17: {
                        if(this.zze(object0, v)) {
                            zzecs0.zzb(v2, zzecb.zzp(object0, ((long)(v1 & 0xFFFFF))), this.zzgn(v));
                        }
                        break;
                    }
                    case 18: {
                        zzebf.zza(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 19: {
                        zzebf.zzb(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 20: {
                        zzebf.zzc(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 21: {
                        zzebf.zzd(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 22: {
                        zzebf.zzh(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 23: {
                        zzebf.zzf(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 24: {
                        zzebf.zzk(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 25: {
                        zzebf.zzn(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 26: {
                        zzebf.zza(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0);
                        break;
                    }
                    case 27: {
                        zzebf.zza(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, this.zzgn(v));
                        break;
                    }
                    case 28: {
                        zzebf.zzb(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0);
                        break;
                    }
                    case 29: {
                        zzebf.zzi(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 30: {
                        zzebf.zzm(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 0x1F: {
                        zzebf.zzl(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 0x20: {
                        zzebf.zzg(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 33: {
                        zzebf.zzj(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 34: {
                        zzebf.zze(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 35: {
                        zzebf.zza(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 36: {
                        zzebf.zzb(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 37: {
                        zzebf.zzc(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 38: {
                        zzebf.zzd(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 39: {
                        zzebf.zzh(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 40: {
                        zzebf.zzf(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 41: {
                        zzebf.zzk(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 42: {
                        zzebf.zzn(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 43: {
                        zzebf.zzi(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 44: {
                        zzebf.zzm(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 45: {
                        zzebf.zzl(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 46: {
                        zzebf.zzg(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 0x2F: {
                        zzebf.zzj(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 0x30: {
                        zzebf.zze(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 49: {
                        zzebf.zzb(this.zzhvf[v], ((List)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))), zzecs0, this.zzgn(v));
                        break;
                    }
                    case 50: {
                        this.zza(zzecs0, v2, zzecb.zzp(object0, ((long)(v1 & 0xFFFFF))), v);
                        break;
                    }
                    case 51: {
                        if(this.zza(object0, v2, v)) {
                            zzecs0.zzb(v2, zzeal.zzf(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 52: {
                        if(this.zza(object0, v2, v)) {
                            zzecs0.zza(v2, zzeal.zzg(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 53: {
                        if(this.zza(object0, v2, v)) {
                            zzecs0.zzp(v2, zzeal.zzi(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 54: {
                        if(this.zza(object0, v2, v)) {
                            zzecs0.zzh(v2, zzeal.zzi(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 55: {
                        if(this.zza(object0, v2, v)) {
                            zzecs0.zzac(v2, zzeal.zzh(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 56: {
                        if(this.zza(object0, v2, v)) {
                            zzecs0.zzj(v2, zzeal.zzi(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 57: {
                        if(this.zza(object0, v2, v)) {
                            zzecs0.zzaf(v2, zzeal.zzh(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 58: {
                        if(this.zza(object0, v2, v)) {
                            zzecs0.zzh(v2, zzeal.zzj(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 59: {
                        if(this.zza(object0, v2, v)) {
                            zzeal.zza(v2, zzecb.zzp(object0, ((long)(v1 & 0xFFFFF))), zzecs0);
                        }
                        break;
                    }
                    case 60: {
                        if(this.zza(object0, v2, v)) {
                            zzecs0.zza(v2, zzecb.zzp(object0, ((long)(v1 & 0xFFFFF))), this.zzgn(v));
                        }
                        break;
                    }
                    case 61: {
                        if(this.zza(object0, v2, v)) {
                            zzecs0.zza(v2, ((zzdxn)zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)))));
                        }
                        break;
                    }
                    case 62: {
                        if(this.zza(object0, v2, v)) {
                            zzecs0.zzad(v2, zzeal.zzh(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 0x3F: {
                        if(this.zza(object0, v2, v)) {
                            zzecs0.zzan(v2, zzeal.zzh(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 0x40: {
                        if(this.zza(object0, v2, v)) {
                            zzecs0.zzam(v2, zzeal.zzh(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 65: {
                        if(this.zza(object0, v2, v)) {
                            zzecs0.zzq(v2, zzeal.zzi(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 66: {
                        if(this.zza(object0, v2, v)) {
                            zzecs0.zzae(v2, zzeal.zzh(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 67: {
                        if(this.zza(object0, v2, v)) {
                            zzecs0.zzi(v2, zzeal.zzi(object0, ((long)(v1 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 68: {
                        if(this.zza(object0, v2, v)) {
                            zzecs0.zzb(v2, zzecb.zzp(object0, ((long)(v1 & 0xFFFFF))), this.zzgn(v));
                        }
                    }
                }
            }
            while(map$Entry0 != null) {
                this.zzhvu.zza(zzecs0, map$Entry0);
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
        if(this.zzhvm) {
            if(this.zzhvk) {
                zzdyp zzdyp1 = this.zzhvu.zzal(object0);
                if(zzdyp1.zzhpu.isEmpty()) {
                    iterator1 = null;
                    map$Entry1 = null;
                }
                else {
                    iterator1 = zzdyp1.iterator();
                    Object object4 = iterator1.next();
                    map$Entry1 = (Map.Entry)object4;
                }
            }
            else {
                iterator1 = null;
                map$Entry1 = null;
            }
            Map.Entry map$Entry2 = map$Entry1;
            for(int v3 = 0; v3 < this.zzhvf.length; v3 += 3) {
                int v4 = this.zzgq(v3);
                int v5 = this.zzhvf[v3];
                while(map$Entry2 != null && this.zzhvu.zza(map$Entry2) <= v5) {
                    this.zzhvu.zza(zzecs0, map$Entry2);
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
                        if(this.zze(object0, v3)) {
                            zzecs0.zzb(v5, zzecb.zzo(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 1: {
                        if(this.zze(object0, v3)) {
                            zzecs0.zza(v5, zzecb.zzn(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 2: {
                        if(this.zze(object0, v3)) {
                            zzecs0.zzp(v5, zzecb.zzl(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 3: {
                        if(this.zze(object0, v3)) {
                            zzecs0.zzh(v5, zzecb.zzl(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 4: {
                        if(this.zze(object0, v3)) {
                            zzecs0.zzac(v5, zzecb.zzk(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 5: {
                        if(this.zze(object0, v3)) {
                            zzecs0.zzj(v5, zzecb.zzl(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 6: {
                        if(this.zze(object0, v3)) {
                            zzecs0.zzaf(v5, zzecb.zzk(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 7: {
                        if(this.zze(object0, v3)) {
                            zzecs0.zzh(v5, zzecb.zzm(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 8: {
                        if(this.zze(object0, v3)) {
                            zzeal.zza(v5, zzecb.zzp(object0, ((long)(v4 & 0xFFFFF))), zzecs0);
                        }
                        break;
                    }
                    case 9: {
                        if(this.zze(object0, v3)) {
                            zzecs0.zza(v5, zzecb.zzp(object0, ((long)(v4 & 0xFFFFF))), this.zzgn(v3));
                        }
                        break;
                    }
                    case 10: {
                        if(this.zze(object0, v3)) {
                            zzecs0.zza(v5, ((zzdxn)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))));
                        }
                        break;
                    }
                    case 11: {
                        if(this.zze(object0, v3)) {
                            zzecs0.zzad(v5, zzecb.zzk(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 12: {
                        if(this.zze(object0, v3)) {
                            zzecs0.zzan(v5, zzecb.zzk(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 13: {
                        if(this.zze(object0, v3)) {
                            zzecs0.zzam(v5, zzecb.zzk(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 14: {
                        if(this.zze(object0, v3)) {
                            zzecs0.zzq(v5, zzecb.zzl(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 15: {
                        if(this.zze(object0, v3)) {
                            zzecs0.zzae(v5, zzecb.zzk(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 16: {
                        if(this.zze(object0, v3)) {
                            zzecs0.zzi(v5, zzecb.zzl(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 17: {
                        if(this.zze(object0, v3)) {
                            zzecs0.zzb(v5, zzecb.zzp(object0, ((long)(v4 & 0xFFFFF))), this.zzgn(v3));
                        }
                        break;
                    }
                    case 18: {
                        zzebf.zza(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 19: {
                        zzebf.zzb(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 20: {
                        zzebf.zzc(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 21: {
                        zzebf.zzd(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 22: {
                        zzebf.zzh(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 23: {
                        zzebf.zzf(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 24: {
                        zzebf.zzk(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 25: {
                        zzebf.zzn(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 26: {
                        zzebf.zza(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0);
                        break;
                    }
                    case 27: {
                        zzebf.zza(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, this.zzgn(v3));
                        break;
                    }
                    case 28: {
                        zzebf.zzb(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0);
                        break;
                    }
                    case 29: {
                        zzebf.zzi(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 30: {
                        zzebf.zzm(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 0x1F: {
                        zzebf.zzl(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 0x20: {
                        zzebf.zzg(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 33: {
                        zzebf.zzj(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 34: {
                        zzebf.zze(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, false);
                        break;
                    }
                    case 35: {
                        zzebf.zza(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 36: {
                        zzebf.zzb(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 37: {
                        zzebf.zzc(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 38: {
                        zzebf.zzd(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 39: {
                        zzebf.zzh(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 40: {
                        zzebf.zzf(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 41: {
                        zzebf.zzk(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 42: {
                        zzebf.zzn(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 43: {
                        zzebf.zzi(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 44: {
                        zzebf.zzm(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 45: {
                        zzebf.zzl(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 46: {
                        zzebf.zzg(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 0x2F: {
                        zzebf.zzj(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 0x30: {
                        zzebf.zze(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, true);
                        break;
                    }
                    case 49: {
                        zzebf.zzb(this.zzhvf[v3], ((List)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))), zzecs0, this.zzgn(v3));
                        break;
                    }
                    case 50: {
                        this.zza(zzecs0, v5, zzecb.zzp(object0, ((long)(v4 & 0xFFFFF))), v3);
                        break;
                    }
                    case 51: {
                        if(this.zza(object0, v5, v3)) {
                            zzecs0.zzb(v5, zzeal.zzf(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 52: {
                        if(this.zza(object0, v5, v3)) {
                            zzecs0.zza(v5, zzeal.zzg(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 53: {
                        if(this.zza(object0, v5, v3)) {
                            zzecs0.zzp(v5, zzeal.zzi(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 54: {
                        if(this.zza(object0, v5, v3)) {
                            zzecs0.zzh(v5, zzeal.zzi(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 55: {
                        if(this.zza(object0, v5, v3)) {
                            zzecs0.zzac(v5, zzeal.zzh(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 56: {
                        if(this.zza(object0, v5, v3)) {
                            zzecs0.zzj(v5, zzeal.zzi(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 57: {
                        if(this.zza(object0, v5, v3)) {
                            zzecs0.zzaf(v5, zzeal.zzh(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 58: {
                        if(this.zza(object0, v5, v3)) {
                            zzecs0.zzh(v5, zzeal.zzj(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 59: {
                        if(this.zza(object0, v5, v3)) {
                            zzeal.zza(v5, zzecb.zzp(object0, ((long)(v4 & 0xFFFFF))), zzecs0);
                        }
                        break;
                    }
                    case 60: {
                        if(this.zza(object0, v5, v3)) {
                            zzecs0.zza(v5, zzecb.zzp(object0, ((long)(v4 & 0xFFFFF))), this.zzgn(v3));
                        }
                        break;
                    }
                    case 61: {
                        if(this.zza(object0, v5, v3)) {
                            zzecs0.zza(v5, ((zzdxn)zzecb.zzp(object0, ((long)(v4 & 0xFFFFF)))));
                        }
                        break;
                    }
                    case 62: {
                        if(this.zza(object0, v5, v3)) {
                            zzecs0.zzad(v5, zzeal.zzh(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 0x3F: {
                        if(this.zza(object0, v5, v3)) {
                            zzecs0.zzan(v5, zzeal.zzh(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 0x40: {
                        if(this.zza(object0, v5, v3)) {
                            zzecs0.zzam(v5, zzeal.zzh(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 65: {
                        if(this.zza(object0, v5, v3)) {
                            zzecs0.zzq(v5, zzeal.zzi(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 66: {
                        if(this.zza(object0, v5, v3)) {
                            zzecs0.zzae(v5, zzeal.zzh(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 67: {
                        if(this.zza(object0, v5, v3)) {
                            zzecs0.zzi(v5, zzeal.zzi(object0, ((long)(v4 & 0xFFFFF))));
                        }
                        break;
                    }
                    case 68: {
                        if(this.zza(object0, v5, v3)) {
                            zzecs0.zzb(v5, zzecb.zzp(object0, ((long)(v4 & 0xFFFFF))), this.zzgn(v3));
                        }
                    }
                }
            }
            while(map$Entry2 != null) {
                this.zzhvu.zza(zzecs0, map$Entry2);
                if(iterator1.hasNext()) {
                    Object object6 = iterator1.next();
                    map$Entry2 = (Map.Entry)object6;
                }
                else {
                    map$Entry2 = null;
                }
            }
            zzeal.zza(this.zzhvt, object0, zzecs0);
            return;
        }
        this.zzb(object0, zzecs0);
    }

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final void zza(Object object0, byte[] arr_b, int v, int v1, zzdxm zzdxm0) throws IOException {
        zzdzi zzdzi2;
        int v16;
        int v12;
        Unsafe unsafe1;
        int v11;
        int v10;
        int v7;
        int v6;
        if(this.zzhvm) {
            Unsafe unsafe0 = zzeal.zzhbt;
            int v2 = v;
            int v3 = -1;
            int v4 = 0;
            while(v2 < v1) {
                int v5 = arr_b[v2];
                if(v5 < 0) {
                    v6 = zzdxj.zza(v5, arr_b, v2 + 1, zzdxm0);
                    v7 = zzdxm0.zzhoa;
                }
                else {
                    v7 = v5;
                    v6 = v2 + 1;
                }
                int v8 = v7 >>> 3;
                int v9 = v8 <= v3 ? this.zzgt(v8) : this.zzao(v8, v4 / 3);
                if(v9 == -1) {
                    v10 = v8;
                    v11 = v6;
                    unsafe1 = unsafe0;
                    v12 = 0;
                }
                else {
                    int v13 = this.zzhvf[v9 + 1];
                    int v14 = (0xFF00000 & v13) >>> 20;
                    long v15 = (long)(0xFFFFF & v13);
                    if(v14 <= 17) {
                        boolean z = true;
                        switch(v14) {
                            case 0: {
                                if((v7 & 7) == 1) {
                                    zzecb.zza(object0, v15, zzdxj.zzh(arr_b, v6));
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
                                    zzecb.zza(object0, v15, zzdxj.zzi(arr_b, v6));
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
                                    int v17 = zzdxj.zzb(arr_b, v6, zzdxm0);
                                    unsafe0.putLong(object0, v15, zzdxm0.zzhob);
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
                                    int v18 = zzdxj.zzb(arr_b, v6, zzdxm0);
                                    if(zzdxm0.zzhob == 0L) {
                                        z = false;
                                    }
                                    zzecb.zza(object0, v15, z);
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
                                    v2 = (0x20000000 & v13) == 0 ? zzdxj.zzc(arr_b, v6, zzdxm0) : zzdxj.zzd(arr_b, v6, zzdxm0);
                                    unsafe0.putObject(object0, v15, zzdxm0.zzhoc);
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
                                    v2 = zzdxj.zza(this.zzgn(v9), arr_b, v6, v1, zzdxm0);
                                    Object object1 = unsafe0.getObject(object0, v15);
                                    if(object1 == null) {
                                        unsafe0.putObject(object0, v15, zzdxm0.zzhoc);
                                    }
                                    else {
                                        unsafe0.putObject(object0, v15, zzdzc.zzd(object1, zzdxm0.zzhoc));
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
                                    v2 = zzdxj.zze(arr_b, v6, zzdxm0);
                                    unsafe0.putObject(object0, v15, zzdxm0.zzhoc);
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
                                    v2 = zzdxj.zza(arr_b, v6, zzdxm0);
                                    unsafe0.putInt(object0, v15, zzdxm0.zzhoa);
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
                                    v2 = zzdxj.zza(arr_b, v6, zzdxm0);
                                    unsafe0.putInt(object0, v15, zzdxm0.zzhoa);
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
                                    unsafe0.putInt(object0, v15, zzdxj.zzf(arr_b, v6));
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
                                    unsafe0.putLong(object0, v15, zzdxj.zzg(arr_b, v6));
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
                                    v2 = zzdxj.zza(arr_b, v6, zzdxm0);
                                    unsafe0.putInt(object0, v15, -(zzdxm0.zzhoa & 1) ^ zzdxm0.zzhoa >>> 1);
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
                                    int v19 = zzdxj.zzb(arr_b, v6, zzdxm0);
                                    unsafe0.putLong(object0, v15, -(zzdxm0.zzhob & 1L) ^ zzdxm0.zzhob >>> 1);
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
                            zzdzi zzdzi0 = (zzdzi)unsafe0.getObject(object0, v15);
                            if(zzdzi0.zzbam()) {
                                zzdzi2 = zzdzi0;
                            }
                            else {
                                int v20 = zzdzi0.size();
                                zzdzi zzdzi1 = zzdzi0.zzfd((v20 == 0 ? 10 : v20 << 1));
                                unsafe0.putObject(object0, v15, zzdzi1);
                                zzdzi2 = zzdzi1;
                            }
                            v2 = zzdxj.zza(this.zzgn(v9), v7, arr_b, v6, v1, zzdzi2, zzdxm0);
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
                            v2 = this.zza(object0, arr_b, v6, v1, v7, v8, v7 & 7, v12, ((long)v13), v14, v15, zzdxm0);
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
                                    v2 = this.zza(object0, arr_b, v6, v1, v12, v15, zzdxm0);
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
                                v2 = this.zza(object0, arr_b, v6, v1, v7, v10, v7 & 7, v13, v14, v15, v12, zzdxm0);
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
                v2 = zzdxj.zza(v7, arr_b, v11, v1, zzeal.zzay(object0), zzdxm0);
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
                throw zzdzh.zzbdp();
            }
            return;
        }
        this.zza(object0, arr_b, v, v1, 0, zzdxm0);
    }

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final void zzan(Object object0) {
        int v1;
        for(int v = this.zzhvp; true; ++v) {
            v1 = this.zzhvq;
            if(v >= v1) {
                break;
            }
            long v2 = (long)(this.zzgq(this.zzhvo[v]) & 0xFFFFF);
            Object object1 = zzecb.zzp(object0, v2);
            if(object1 != null) {
                zzecb.zza(object0, v2, this.zzhvv.zzav(object1));
            }
        }
        while(v1 < this.zzhvo.length) {
            this.zzhvs.zzb(object0, ((long)this.zzhvo[v1]));
            ++v1;
        }
        this.zzhvt.zzan(object0);
        if(this.zzhvk) {
            this.zzhvu.zzan(object0);
        }
    }

    private final int zzao(int v, int v1) {
        return v < this.zzhvh || v > this.zzhvi ? -1 : this.zzap(v, v1);
    }

    private final int zzap(int v, int v1) {
        int v2 = this.zzhvf.length / 3 - 1;
        while(v1 <= v2) {
            int v3 = v2 + v1 >>> 1;
            int v4 = v3 * 3;
            int v5 = this.zzhvf[v4];
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

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final int zzax(Object object0) {
        int v30;
        int v28;
        if(this.zzhvm) {
            Unsafe unsafe0 = zzeal.zzhbt;
            int v1 = 0;
            for(int v = 0; v < this.zzhvf.length; v += 3) {
                int v2 = this.zzgq(v);
                int v3 = (v2 & 0xFF00000) >>> 20;
                int v4 = this.zzhvf[v];
                long v5 = (long)(v2 & 0xFFFFF);
                int v6 = v3 < zzdyu.zzhrm.id() || v3 > zzdyu.zzhrz.id() ? 0 : this.zzhvf[v + 2] & 0xFFFFF;
                switch(v3) {
                    case 0: {
                        if(this.zze(object0, v)) {
                            v1 += zzdyi.zzc(v4, 0.0);
                        }
                        break;
                    }
                    case 1: {
                        if(this.zze(object0, v)) {
                            v1 += zzdyi.zzb(v4, 0.0f);
                        }
                        break;
                    }
                    case 2: {
                        if(this.zze(object0, v)) {
                            v1 += zzdyi.zzk(v4, zzecb.zzl(object0, v5));
                        }
                        break;
                    }
                    case 3: {
                        if(this.zze(object0, v)) {
                            v1 += zzdyi.zzl(v4, zzecb.zzl(object0, v5));
                        }
                        break;
                    }
                    case 4: {
                        if(this.zze(object0, v)) {
                            v1 += zzdyi.zzag(v4, zzecb.zzk(object0, v5));
                        }
                        break;
                    }
                    case 5: {
                        if(this.zze(object0, v)) {
                            v1 += zzdyi.zzn(v4, 0L);
                        }
                        break;
                    }
                    case 6: {
                        if(this.zze(object0, v)) {
                            v1 += zzdyi.zzaj(v4, 0);
                        }
                        break;
                    }
                    case 7: {
                        if(this.zze(object0, v)) {
                            v1 += zzdyi.zzi(v4, true);
                        }
                        break;
                    }
                    case 8: {
                        if(this.zze(object0, v)) {
                            Object object1 = zzecb.zzp(object0, v5);
                            v1 += (object1 instanceof zzdxn ? zzdyi.zzc(v4, ((zzdxn)object1)) : zzdyi.zzg(v4, ((String)object1)));
                        }
                        break;
                    }
                    case 9: {
                        if(this.zze(object0, v)) {
                            v1 += zzebf.zzc(v4, zzecb.zzp(object0, v5), this.zzgn(v));
                        }
                        break;
                    }
                    case 10: {
                        if(this.zze(object0, v)) {
                            v1 += zzdyi.zzc(v4, ((zzdxn)zzecb.zzp(object0, v5)));
                        }
                        break;
                    }
                    case 11: {
                        if(this.zze(object0, v)) {
                            v1 += zzdyi.zzah(v4, zzecb.zzk(object0, v5));
                        }
                        break;
                    }
                    case 12: {
                        if(this.zze(object0, v)) {
                            v1 += zzdyi.zzal(v4, zzecb.zzk(object0, v5));
                        }
                        break;
                    }
                    case 13: {
                        if(this.zze(object0, v)) {
                            v1 += zzdyi.zzak(v4, 0);
                        }
                        break;
                    }
                    case 14: {
                        if(this.zze(object0, v)) {
                            v1 += zzdyi.zzo(v4, 0L);
                        }
                        break;
                    }
                    case 15: {
                        if(this.zze(object0, v)) {
                            v1 += zzdyi.zzai(v4, zzecb.zzk(object0, v5));
                        }
                        break;
                    }
                    case 16: {
                        if(this.zze(object0, v)) {
                            v1 += zzdyi.zzm(v4, zzecb.zzl(object0, v5));
                        }
                        break;
                    }
                    case 17: {
                        if(this.zze(object0, v)) {
                            v1 += zzdyi.zzc(v4, ((zzeah)zzecb.zzp(object0, v5)), this.zzgn(v));
                        }
                        break;
                    }
                    case 18: {
                        v1 += zzebf.zzw(v4, zzeal.zze(object0, v5), false);
                        break;
                    }
                    case 19: {
                        v1 += zzebf.zzv(v4, zzeal.zze(object0, v5), false);
                        break;
                    }
                    case 20: {
                        v1 += zzebf.zzo(v4, zzeal.zze(object0, v5), false);
                        break;
                    }
                    case 21: {
                        v1 += zzebf.zzp(v4, zzeal.zze(object0, v5), false);
                        break;
                    }
                    case 22: {
                        v1 += zzebf.zzs(v4, zzeal.zze(object0, v5), false);
                        break;
                    }
                    case 23: {
                        v1 += zzebf.zzw(v4, zzeal.zze(object0, v5), false);
                        break;
                    }
                    case 24: {
                        v1 += zzebf.zzv(v4, zzeal.zze(object0, v5), false);
                        break;
                    }
                    case 25: {
                        v1 += zzebf.zzx(v4, zzeal.zze(object0, v5), false);
                        break;
                    }
                    case 26: {
                        v1 += zzebf.zzc(v4, zzeal.zze(object0, v5));
                        break;
                    }
                    case 27: {
                        v1 += zzebf.zzc(v4, zzeal.zze(object0, v5), this.zzgn(v));
                        break;
                    }
                    case 28: {
                        v1 += zzebf.zzd(v4, zzeal.zze(object0, v5));
                        break;
                    }
                    case 29: {
                        v1 += zzebf.zzt(v4, zzeal.zze(object0, v5), false);
                        break;
                    }
                    case 30: {
                        v1 += zzebf.zzr(v4, zzeal.zze(object0, v5), false);
                        break;
                    }
                    case 0x1F: {
                        v1 += zzebf.zzv(v4, zzeal.zze(object0, v5), false);
                        break;
                    }
                    case 0x20: {
                        v1 += zzebf.zzw(v4, zzeal.zze(object0, v5), false);
                        break;
                    }
                    case 33: {
                        v1 += zzebf.zzu(v4, zzeal.zze(object0, v5), false);
                        break;
                    }
                    case 34: {
                        v1 += zzebf.zzq(v4, zzeal.zze(object0, v5), false);
                        break;
                    }
                    case 35: {
                        int v7 = zzebf.zzah(((List)unsafe0.getObject(object0, v5)));
                        if(v7 > 0) {
                            if(this.zzhvn) {
                                unsafe0.putInt(object0, ((long)v6), v7);
                            }
                            v1 += zzdyi.zzfz(v4) + zzdyi.zzgb(v7) + v7;
                        }
                        break;
                    }
                    case 36: {
                        int v8 = zzebf.zzag(((List)unsafe0.getObject(object0, v5)));
                        if(v8 > 0) {
                            if(this.zzhvn) {
                                unsafe0.putInt(object0, ((long)v6), v8);
                            }
                            v1 += zzdyi.zzfz(v4) + zzdyi.zzgb(v8) + v8;
                        }
                        break;
                    }
                    case 37: {
                        int v9 = zzebf.zzz(((List)unsafe0.getObject(object0, v5)));
                        if(v9 > 0) {
                            if(this.zzhvn) {
                                unsafe0.putInt(object0, ((long)v6), v9);
                            }
                            v1 += zzdyi.zzfz(v4) + zzdyi.zzgb(v9) + v9;
                        }
                        break;
                    }
                    case 38: {
                        int v10 = zzebf.zzaa(((List)unsafe0.getObject(object0, v5)));
                        if(v10 > 0) {
                            if(this.zzhvn) {
                                unsafe0.putInt(object0, ((long)v6), v10);
                            }
                            v1 += zzdyi.zzfz(v4) + zzdyi.zzgb(v10) + v10;
                        }
                        break;
                    }
                    case 39: {
                        int v11 = zzebf.zzad(((List)unsafe0.getObject(object0, v5)));
                        if(v11 > 0) {
                            if(this.zzhvn) {
                                unsafe0.putInt(object0, ((long)v6), v11);
                            }
                            v1 += zzdyi.zzfz(v4) + zzdyi.zzgb(v11) + v11;
                        }
                        break;
                    }
                    case 40: {
                        int v12 = zzebf.zzah(((List)unsafe0.getObject(object0, v5)));
                        if(v12 > 0) {
                            if(this.zzhvn) {
                                unsafe0.putInt(object0, ((long)v6), v12);
                            }
                            v1 += zzdyi.zzfz(v4) + zzdyi.zzgb(v12) + v12;
                        }
                        break;
                    }
                    case 41: {
                        int v13 = zzebf.zzag(((List)unsafe0.getObject(object0, v5)));
                        if(v13 > 0) {
                            if(this.zzhvn) {
                                unsafe0.putInt(object0, ((long)v6), v13);
                            }
                            v1 += zzdyi.zzfz(v4) + zzdyi.zzgb(v13) + v13;
                        }
                        break;
                    }
                    case 42: {
                        int v14 = zzebf.zzai(((List)unsafe0.getObject(object0, v5)));
                        if(v14 > 0) {
                            if(this.zzhvn) {
                                unsafe0.putInt(object0, ((long)v6), v14);
                            }
                            v1 += zzdyi.zzfz(v4) + zzdyi.zzgb(v14) + v14;
                        }
                        break;
                    }
                    case 43: {
                        int v15 = zzebf.zzae(((List)unsafe0.getObject(object0, v5)));
                        if(v15 > 0) {
                            if(this.zzhvn) {
                                unsafe0.putInt(object0, ((long)v6), v15);
                            }
                            v1 += zzdyi.zzfz(v4) + zzdyi.zzgb(v15) + v15;
                        }
                        break;
                    }
                    case 44: {
                        int v16 = zzebf.zzac(((List)unsafe0.getObject(object0, v5)));
                        if(v16 > 0) {
                            if(this.zzhvn) {
                                unsafe0.putInt(object0, ((long)v6), v16);
                            }
                            v1 += zzdyi.zzfz(v4) + zzdyi.zzgb(v16) + v16;
                        }
                        break;
                    }
                    case 45: {
                        int v17 = zzebf.zzag(((List)unsafe0.getObject(object0, v5)));
                        if(v17 > 0) {
                            if(this.zzhvn) {
                                unsafe0.putInt(object0, ((long)v6), v17);
                            }
                            v1 += zzdyi.zzfz(v4) + zzdyi.zzgb(v17) + v17;
                        }
                        break;
                    }
                    case 46: {
                        int v18 = zzebf.zzah(((List)unsafe0.getObject(object0, v5)));
                        if(v18 > 0) {
                            if(this.zzhvn) {
                                unsafe0.putInt(object0, ((long)v6), v18);
                            }
                            v1 += zzdyi.zzfz(v4) + zzdyi.zzgb(v18) + v18;
                        }
                        break;
                    }
                    case 0x2F: {
                        int v19 = zzebf.zzaf(((List)unsafe0.getObject(object0, v5)));
                        if(v19 > 0) {
                            if(this.zzhvn) {
                                unsafe0.putInt(object0, ((long)v6), v19);
                            }
                            v1 += zzdyi.zzfz(v4) + zzdyi.zzgb(v19) + v19;
                        }
                        break;
                    }
                    case 0x30: {
                        int v20 = zzebf.zzab(((List)unsafe0.getObject(object0, v5)));
                        if(v20 > 0) {
                            if(this.zzhvn) {
                                unsafe0.putInt(object0, ((long)v6), v20);
                            }
                            v1 += zzdyi.zzfz(v4) + zzdyi.zzgb(v20) + v20;
                        }
                        break;
                    }
                    case 49: {
                        v1 += zzebf.zzd(v4, zzeal.zze(object0, v5), this.zzgn(v));
                        break;
                    }
                    case 50: {
                        Object object2 = zzecb.zzp(object0, v5);
                        Object object3 = this.zzgo(v);
                        v1 += this.zzhvv.zzb(v4, object2, object3);
                        break;
                    }
                    case 51: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzdyi.zzc(v4, 0.0);
                        }
                        break;
                    }
                    case 52: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzdyi.zzb(v4, 0.0f);
                        }
                        break;
                    }
                    case 53: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzdyi.zzk(v4, zzeal.zzi(object0, v5));
                        }
                        break;
                    }
                    case 54: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzdyi.zzl(v4, zzeal.zzi(object0, v5));
                        }
                        break;
                    }
                    case 55: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzdyi.zzag(v4, zzeal.zzh(object0, v5));
                        }
                        break;
                    }
                    case 56: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzdyi.zzn(v4, 0L);
                        }
                        break;
                    }
                    case 57: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzdyi.zzaj(v4, 0);
                        }
                        break;
                    }
                    case 58: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzdyi.zzi(v4, true);
                        }
                        break;
                    }
                    case 59: {
                        if(this.zza(object0, v4, v)) {
                            Object object4 = zzecb.zzp(object0, v5);
                            v1 += (object4 instanceof zzdxn ? zzdyi.zzc(v4, ((zzdxn)object4)) : zzdyi.zzg(v4, ((String)object4)));
                        }
                        break;
                    }
                    case 60: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzebf.zzc(v4, zzecb.zzp(object0, v5), this.zzgn(v));
                        }
                        break;
                    }
                    case 61: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzdyi.zzc(v4, ((zzdxn)zzecb.zzp(object0, v5)));
                        }
                        break;
                    }
                    case 62: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzdyi.zzah(v4, zzeal.zzh(object0, v5));
                        }
                        break;
                    }
                    case 0x3F: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzdyi.zzal(v4, zzeal.zzh(object0, v5));
                        }
                        break;
                    }
                    case 0x40: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzdyi.zzak(v4, 0);
                        }
                        break;
                    }
                    case 65: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzdyi.zzo(v4, 0L);
                        }
                        break;
                    }
                    case 66: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzdyi.zzai(v4, zzeal.zzh(object0, v5));
                        }
                        break;
                    }
                    case 67: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzdyi.zzm(v4, zzeal.zzi(object0, v5));
                        }
                        break;
                    }
                    case 68: {
                        if(this.zza(object0, v4, v)) {
                            v1 += zzdyi.zzc(v4, ((zzeah)zzecb.zzp(object0, v5)), this.zzgn(v));
                        }
                    }
                }
            }
            return v1 + zzeal.zza(this.zzhvt, object0);
        }
        Unsafe unsafe1 = zzeal.zzhbt;
        int v22 = 0;
        int v23 = -1;
        int v24 = 0;
        for(int v21 = 0; v21 < this.zzhvf.length; v21 += 3) {
            int v25 = this.zzgq(v21);
            int[] arr_v = this.zzhvf;
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
                v28 = !this.zzhvn || v27 < zzdyu.zzhrm.id() || v27 > zzdyu.zzhrz.id() ? 0 : this.zzhvf[v21 + 2] & 0xFFFFF;
                v30 = 0;
            }
            long v31 = (long)(v25 & 0xFFFFF);
            switch(v27) {
                case 0: {
                    if((v24 & v30) != 0) {
                        v22 += zzdyi.zzc(v26, 0.0);
                    }
                    break;
                }
                case 1: {
                    if((v24 & v30) != 0) {
                        v22 += zzdyi.zzb(v26, 0.0f);
                    }
                    break;
                }
                case 2: {
                    if((v24 & v30) != 0) {
                        v22 += zzdyi.zzk(v26, unsafe1.getLong(object0, v31));
                    }
                    break;
                }
                case 3: {
                    if((v24 & v30) != 0) {
                        v22 += zzdyi.zzl(v26, unsafe1.getLong(object0, v31));
                    }
                    break;
                }
                case 4: {
                    if((v24 & v30) != 0) {
                        v22 += zzdyi.zzag(v26, unsafe1.getInt(object0, v31));
                    }
                    break;
                }
                case 5: {
                    if((v24 & v30) != 0) {
                        v22 += zzdyi.zzn(v26, 0L);
                    }
                    break;
                }
                case 6: {
                    if((v24 & v30) != 0) {
                        v22 += zzdyi.zzaj(v26, 0);
                    }
                    break;
                }
                case 7: {
                    if((v24 & v30) != 0) {
                        v22 += zzdyi.zzi(v26, true);
                    }
                    break;
                }
                case 8: {
                    if((v24 & v30) != 0) {
                        Object object5 = unsafe1.getObject(object0, v31);
                        v22 += (object5 instanceof zzdxn ? zzdyi.zzc(v26, ((zzdxn)object5)) : zzdyi.zzg(v26, ((String)object5)));
                    }
                    break;
                }
                case 9: {
                    if((v24 & v30) != 0) {
                        v22 += zzebf.zzc(v26, unsafe1.getObject(object0, v31), this.zzgn(v21));
                    }
                    break;
                }
                case 10: {
                    if((v24 & v30) != 0) {
                        v22 += zzdyi.zzc(v26, ((zzdxn)unsafe1.getObject(object0, v31)));
                    }
                    break;
                }
                case 11: {
                    if((v24 & v30) != 0) {
                        v22 += zzdyi.zzah(v26, unsafe1.getInt(object0, v31));
                    }
                    break;
                }
                case 12: {
                    if((v24 & v30) != 0) {
                        v22 += zzdyi.zzal(v26, unsafe1.getInt(object0, v31));
                    }
                    break;
                }
                case 13: {
                    if((v24 & v30) != 0) {
                        v22 += zzdyi.zzak(v26, 0);
                    }
                    break;
                }
                case 14: {
                    if((v24 & v30) != 0) {
                        v22 += zzdyi.zzo(v26, 0L);
                    }
                    break;
                }
                case 15: {
                    if((v24 & v30) != 0) {
                        v22 += zzdyi.zzai(v26, unsafe1.getInt(object0, v31));
                    }
                    break;
                }
                case 16: {
                    if((v24 & v30) != 0) {
                        v22 += zzdyi.zzm(v26, unsafe1.getLong(object0, v31));
                    }
                    break;
                }
                case 17: {
                    if((v24 & v30) != 0) {
                        v22 += zzdyi.zzc(v26, ((zzeah)unsafe1.getObject(object0, v31)), this.zzgn(v21));
                    }
                    break;
                }
                case 18: {
                    v22 += zzebf.zzw(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 19: {
                    v22 += zzebf.zzv(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 20: {
                    v22 += zzebf.zzo(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 21: {
                    v22 += zzebf.zzp(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 22: {
                    v22 += zzebf.zzs(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 23: {
                    v22 += zzebf.zzw(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 24: {
                    v22 += zzebf.zzv(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 25: {
                    v22 += zzebf.zzx(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 26: {
                    v22 += zzebf.zzc(v26, ((List)unsafe1.getObject(object0, v31)));
                    break;
                }
                case 27: {
                    v22 += zzebf.zzc(v26, ((List)unsafe1.getObject(object0, v31)), this.zzgn(v21));
                    break;
                }
                case 28: {
                    v22 += zzebf.zzd(v26, ((List)unsafe1.getObject(object0, v31)));
                    break;
                }
                case 29: {
                    v22 += zzebf.zzt(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 30: {
                    v22 += zzebf.zzr(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 0x1F: {
                    v22 += zzebf.zzv(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 0x20: {
                    v22 += zzebf.zzw(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 33: {
                    v22 += zzebf.zzu(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 34: {
                    v22 += zzebf.zzq(v26, ((List)unsafe1.getObject(object0, v31)), false);
                    break;
                }
                case 35: {
                    int v32 = zzebf.zzah(((List)unsafe1.getObject(object0, v31)));
                    if(v32 > 0) {
                        if(this.zzhvn) {
                            unsafe1.putInt(object0, ((long)v28), v32);
                        }
                        v22 += zzdyi.zzfz(v26) + zzdyi.zzgb(v32) + v32;
                    }
                    break;
                }
                case 36: {
                    int v33 = zzebf.zzag(((List)unsafe1.getObject(object0, v31)));
                    if(v33 > 0) {
                        if(this.zzhvn) {
                            unsafe1.putInt(object0, ((long)v28), v33);
                        }
                        v22 += zzdyi.zzfz(v26) + zzdyi.zzgb(v33) + v33;
                    }
                    break;
                }
                case 37: {
                    int v34 = zzebf.zzz(((List)unsafe1.getObject(object0, v31)));
                    if(v34 > 0) {
                        if(this.zzhvn) {
                            unsafe1.putInt(object0, ((long)v28), v34);
                        }
                        v22 += zzdyi.zzfz(v26) + zzdyi.zzgb(v34) + v34;
                    }
                    break;
                }
                case 38: {
                    int v35 = zzebf.zzaa(((List)unsafe1.getObject(object0, v31)));
                    if(v35 > 0) {
                        if(this.zzhvn) {
                            unsafe1.putInt(object0, ((long)v28), v35);
                        }
                        v22 += zzdyi.zzfz(v26) + zzdyi.zzgb(v35) + v35;
                    }
                    break;
                }
                case 39: {
                    int v36 = zzebf.zzad(((List)unsafe1.getObject(object0, v31)));
                    if(v36 > 0) {
                        if(this.zzhvn) {
                            unsafe1.putInt(object0, ((long)v28), v36);
                        }
                        v22 += zzdyi.zzfz(v26) + zzdyi.zzgb(v36) + v36;
                    }
                    break;
                }
                case 40: {
                    int v37 = zzebf.zzah(((List)unsafe1.getObject(object0, v31)));
                    if(v37 > 0) {
                        if(this.zzhvn) {
                            unsafe1.putInt(object0, ((long)v28), v37);
                        }
                        v22 += zzdyi.zzfz(v26) + zzdyi.zzgb(v37) + v37;
                    }
                    break;
                }
                case 41: {
                    int v38 = zzebf.zzag(((List)unsafe1.getObject(object0, v31)));
                    if(v38 > 0) {
                        if(this.zzhvn) {
                            unsafe1.putInt(object0, ((long)v28), v38);
                        }
                        v22 += zzdyi.zzfz(v26) + zzdyi.zzgb(v38) + v38;
                    }
                    break;
                }
                case 42: {
                    int v39 = zzebf.zzai(((List)unsafe1.getObject(object0, v31)));
                    if(v39 > 0) {
                        if(this.zzhvn) {
                            unsafe1.putInt(object0, ((long)v28), v39);
                        }
                        v22 += zzdyi.zzfz(v26) + zzdyi.zzgb(v39) + v39;
                    }
                    break;
                }
                case 43: {
                    int v40 = zzebf.zzae(((List)unsafe1.getObject(object0, v31)));
                    if(v40 > 0) {
                        if(this.zzhvn) {
                            unsafe1.putInt(object0, ((long)v28), v40);
                        }
                        v22 += zzdyi.zzfz(v26) + zzdyi.zzgb(v40) + v40;
                    }
                    break;
                }
                case 44: {
                    int v41 = zzebf.zzac(((List)unsafe1.getObject(object0, v31)));
                    if(v41 > 0) {
                        if(this.zzhvn) {
                            unsafe1.putInt(object0, ((long)v28), v41);
                        }
                        v22 += zzdyi.zzfz(v26) + zzdyi.zzgb(v41) + v41;
                    }
                    break;
                }
                case 45: {
                    int v42 = zzebf.zzag(((List)unsafe1.getObject(object0, v31)));
                    if(v42 > 0) {
                        if(this.zzhvn) {
                            unsafe1.putInt(object0, ((long)v28), v42);
                        }
                        v22 += zzdyi.zzfz(v26) + zzdyi.zzgb(v42) + v42;
                    }
                    break;
                }
                case 46: {
                    int v43 = zzebf.zzah(((List)unsafe1.getObject(object0, v31)));
                    if(v43 > 0) {
                        if(this.zzhvn) {
                            unsafe1.putInt(object0, ((long)v28), v43);
                        }
                        v22 += zzdyi.zzfz(v26) + zzdyi.zzgb(v43) + v43;
                    }
                    break;
                }
                case 0x2F: {
                    int v44 = zzebf.zzaf(((List)unsafe1.getObject(object0, v31)));
                    if(v44 > 0) {
                        if(this.zzhvn) {
                            unsafe1.putInt(object0, ((long)v28), v44);
                        }
                        v22 += zzdyi.zzfz(v26) + zzdyi.zzgb(v44) + v44;
                    }
                    break;
                }
                case 0x30: {
                    int v45 = zzebf.zzab(((List)unsafe1.getObject(object0, v31)));
                    if(v45 > 0) {
                        if(this.zzhvn) {
                            unsafe1.putInt(object0, ((long)v28), v45);
                        }
                        v22 += zzdyi.zzfz(v26) + zzdyi.zzgb(v45) + v45;
                    }
                    break;
                }
                case 49: {
                    v22 += zzebf.zzd(v26, ((List)unsafe1.getObject(object0, v31)), this.zzgn(v21));
                    break;
                }
                case 50: {
                    Object object6 = unsafe1.getObject(object0, v31);
                    Object object7 = this.zzgo(v21);
                    v22 += this.zzhvv.zzb(v26, object6, object7);
                    break;
                }
                case 51: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzdyi.zzc(v26, 0.0);
                    }
                    break;
                }
                case 52: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzdyi.zzb(v26, 0.0f);
                    }
                    break;
                }
                case 53: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzdyi.zzk(v26, zzeal.zzi(object0, v31));
                    }
                    break;
                }
                case 54: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzdyi.zzl(v26, zzeal.zzi(object0, v31));
                    }
                    break;
                }
                case 55: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzdyi.zzag(v26, zzeal.zzh(object0, v31));
                    }
                    break;
                }
                case 56: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzdyi.zzn(v26, 0L);
                    }
                    break;
                }
                case 57: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzdyi.zzaj(v26, 0);
                    }
                    break;
                }
                case 58: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzdyi.zzi(v26, true);
                    }
                    break;
                }
                case 59: {
                    if(this.zza(object0, v26, v21)) {
                        Object object8 = unsafe1.getObject(object0, v31);
                        v22 += (object8 instanceof zzdxn ? zzdyi.zzc(v26, ((zzdxn)object8)) : zzdyi.zzg(v26, ((String)object8)));
                    }
                    break;
                }
                case 60: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzebf.zzc(v26, unsafe1.getObject(object0, v31), this.zzgn(v21));
                    }
                    break;
                }
                case 61: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzdyi.zzc(v26, ((zzdxn)unsafe1.getObject(object0, v31)));
                    }
                    break;
                }
                case 62: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzdyi.zzah(v26, zzeal.zzh(object0, v31));
                    }
                    break;
                }
                case 0x3F: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzdyi.zzal(v26, zzeal.zzh(object0, v31));
                    }
                    break;
                }
                case 0x40: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzdyi.zzak(v26, 0);
                    }
                    break;
                }
                case 65: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzdyi.zzo(v26, 0L);
                    }
                    break;
                }
                case 66: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzdyi.zzai(v26, zzeal.zzh(object0, v31));
                    }
                    break;
                }
                case 67: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzdyi.zzm(v26, zzeal.zzi(object0, v31));
                    }
                    break;
                }
                case 68: {
                    if(this.zza(object0, v26, v21)) {
                        v22 += zzdyi.zzc(v26, ((zzeah)unsafe1.getObject(object0, v31)), this.zzgn(v21));
                    }
                }
            }
        }
        int v47 = v22 + zzeal.zza(this.zzhvt, object0);
        if(this.zzhvk) {
            zzdyp zzdyp0 = this.zzhvu.zzal(object0);
            int v48 = 0;
            for(int v46 = 0; v46 < zzdyp0.zzhpu.zzbew(); ++v46) {
                Map.Entry map$Entry0 = zzdyp0.zzhpu.zzgx(v46);
                v48 += zzdyp.zzb(((zzdyr)map$Entry0.getKey()), map$Entry0.getValue());
            }
            for(Object object9: zzdyp0.zzhpu.zzbex()) {
                v48 += zzdyp.zzb(((zzdyr)((Map.Entry)object9).getKey()), ((Map.Entry)object9).getValue());
            }
            return v47 + v48;
        }
        return v47;
    }

    private static zzeby zzay(Object object0) {
        zzeby zzeby0 = ((zzdyz)object0).zzhsw;
        if(zzeby0 == zzeby.zzbff()) {
            zzeby0 = zzeby.zzbfg();
            ((zzdyz)object0).zzhsw = zzeby0;
        }
        return zzeby0;
    }

    // This method was un-flattened
    @Override  // com.google.android.gms.internal.ads.zzebd
    public final boolean zzaz(Object object0) {
        int v8;
        int v = 0;
        int v1 = -1;
        int v2 = 0;
        while(v < this.zzhvp) {
            int v3 = this.zzhvo[v];
            int v4 = this.zzhvf[v3];
            int v5 = this.zzgq(v3);
            if(this.zzhvm) {
                v8 = 0;
            }
            else {
                int v6 = this.zzhvf[v3 + 2];
                int v7 = v6 & 0xFFFFF;
                v8 = 1 << (v6 >>> 20);
                if(v7 != v1) {
                    v2 = zzeal.zzhbt.getInt(object0, ((long)v7));
                    v1 = v7;
                }
            }
            if((0x10000000 & v5) != 0 && !this.zza(object0, v3, v2, v8)) {
                return false;
            }
            switch((0xFF00000 & v5) >>> 20) {
                case 9: 
                case 17: {
                    if(this.zza(object0, v3, v2, v8) && !zzeal.zza(object0, v5, this.zzgn(v3))) {
                        return false;
                    }
                    break;
                }
                case 27: 
                case 49: {
                    List list0 = (List)zzecb.zzp(object0, ((long)(v5 & 0xFFFFF)));
                    if(!list0.isEmpty()) {
                        zzebd zzebd0 = this.zzgn(v3);
                        for(int v9 = 0; v9 < list0.size(); ++v9) {
                            if(!zzebd0.zzaz(list0.get(v9))) {
                                return false;
                            }
                        }
                    }
                    break;
                }
                case 50: {
                    Object object1 = zzecb.zzp(object0, ((long)(v5 & 0xFFFFF)));
                    Map map0 = this.zzhvv.zzat(object1);
                    if(!map0.isEmpty()) {
                        Object object2 = this.zzgo(v3);
                        if(this.zzhvv.zzas(object2).zzhvb.zzbfo() == zzecp.zzhzq) {
                            zzebd zzebd1 = null;
                            Iterator iterator0 = map0.values().iterator();
                            while(true) {
                                if(!iterator0.hasNext()) {
                                    break;
                                }
                                Object object3 = iterator0.next();
                                if(zzebd1 == null) {
                                    zzebd1 = zzeaw.zzbem().zzh(object3.getClass());
                                }
                                if(zzebd1.zzaz(object3)) {
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
                    if(this.zza(object0, v4, v3) && !zzeal.zza(object0, v5, this.zzgn(v3))) {
                        return false;
                    }
                }
            }
            ++v;
        }
        return !this.zzhvk || this.zzhvu.zzal(object0).isInitialized();
    }

    private final void zzb(Object object0, int v, int v1) {
        zzecb.zzb(object0, ((long)(this.zzgr(v1) & 0xFFFFF)), v);
    }

    private final void zzb(Object object0, zzecs zzecs0) throws IOException {
        int v8;
        Map.Entry map$Entry2;
        Map.Entry map$Entry0;
        Iterator iterator0;
        if(this.zzhvk) {
            zzdyp zzdyp0 = this.zzhvu.zzal(object0);
            if(zzdyp0.zzhpu.isEmpty()) {
                iterator0 = null;
                map$Entry0 = null;
            }
            else {
                iterator0 = zzdyp0.iterator();
                Object object1 = iterator0.next();
                map$Entry0 = (Map.Entry)object1;
            }
        }
        else {
            iterator0 = null;
            map$Entry0 = null;
        }
        int v = -1;
        Unsafe unsafe0 = zzeal.zzhbt;
        Map.Entry map$Entry1 = map$Entry0;
        int v2 = 0;
        for(int v1 = 0; v1 < this.zzhvf.length; v1 += 3) {
            int v3 = this.zzgq(v1);
            int[] arr_v = this.zzhvf;
            int v4 = arr_v[v1];
            int v5 = (0xFF00000 & v3) >>> 20;
            if(this.zzhvm || v5 > 17) {
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
            while(map$Entry1 != null && this.zzhvu.zza(map$Entry1) <= v4) {
                this.zzhvu.zza(zzecs0, map$Entry1);
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
                        zzecs0.zzb(v4, zzecb.zzo(object0, v9));
                    }
                    break;
                }
                case 1: {
                    if((v2 & v8) != 0) {
                        zzecs0.zza(v4, zzecb.zzn(object0, v9));
                    }
                    break;
                }
                case 2: {
                    if((v2 & v8) != 0) {
                        zzecs0.zzp(v4, unsafe0.getLong(object0, v9));
                    }
                    break;
                }
                case 3: {
                    if((v2 & v8) != 0) {
                        zzecs0.zzh(v4, unsafe0.getLong(object0, v9));
                    }
                    break;
                }
                case 4: {
                    if((v2 & v8) != 0) {
                        zzecs0.zzac(v4, unsafe0.getInt(object0, v9));
                    }
                    break;
                }
                case 5: {
                    if((v2 & v8) != 0) {
                        zzecs0.zzj(v4, unsafe0.getLong(object0, v9));
                    }
                    break;
                }
                case 6: {
                    if((v2 & v8) != 0) {
                        zzecs0.zzaf(v4, unsafe0.getInt(object0, v9));
                    }
                    break;
                }
                case 7: {
                    if((v2 & v8) != 0) {
                        zzecs0.zzh(v4, zzecb.zzm(object0, v9));
                    }
                    break;
                }
                case 8: {
                    if((v2 & v8) != 0) {
                        zzeal.zza(v4, unsafe0.getObject(object0, v9), zzecs0);
                    }
                    break;
                }
                case 9: {
                    if((v2 & v8) != 0) {
                        zzecs0.zza(v4, unsafe0.getObject(object0, v9), this.zzgn(v1));
                    }
                    break;
                }
                case 10: {
                    if((v2 & v8) != 0) {
                        zzecs0.zza(v4, ((zzdxn)unsafe0.getObject(object0, v9)));
                    }
                    break;
                }
                case 11: {
                    if((v2 & v8) != 0) {
                        zzecs0.zzad(v4, unsafe0.getInt(object0, v9));
                    }
                    break;
                }
                case 12: {
                    if((v2 & v8) != 0) {
                        zzecs0.zzan(v4, unsafe0.getInt(object0, v9));
                    }
                    break;
                }
                case 13: {
                    if((v2 & v8) != 0) {
                        zzecs0.zzam(v4, unsafe0.getInt(object0, v9));
                    }
                    break;
                }
                case 14: {
                    if((v2 & v8) != 0) {
                        zzecs0.zzq(v4, unsafe0.getLong(object0, v9));
                    }
                    break;
                }
                case 15: {
                    if((v2 & v8) != 0) {
                        zzecs0.zzae(v4, unsafe0.getInt(object0, v9));
                    }
                    break;
                }
                case 16: {
                    if((v2 & v8) != 0) {
                        zzecs0.zzi(v4, unsafe0.getLong(object0, v9));
                    }
                    break;
                }
                case 17: {
                    if((v2 & v8) != 0) {
                        zzecs0.zzb(v4, unsafe0.getObject(object0, v9), this.zzgn(v1));
                    }
                    break;
                }
                case 18: {
                    zzebf.zza(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, false);
                    break;
                }
                case 19: {
                    zzebf.zzb(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, false);
                    break;
                }
                case 20: {
                    zzebf.zzc(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, false);
                    break;
                }
                case 21: {
                    zzebf.zzd(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, false);
                    break;
                }
                case 22: {
                    zzebf.zzh(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, false);
                    break;
                }
                case 23: {
                    zzebf.zzf(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, false);
                    break;
                }
                case 24: {
                    zzebf.zzk(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, false);
                    break;
                }
                case 25: {
                    zzebf.zzn(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, false);
                    break;
                }
                case 26: {
                    zzebf.zza(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0);
                    break;
                }
                case 27: {
                    zzebf.zza(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, this.zzgn(v1));
                    break;
                }
                case 28: {
                    zzebf.zzb(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0);
                    break;
                }
                case 29: {
                    zzebf.zzi(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, false);
                    break;
                }
                case 30: {
                    zzebf.zzm(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, false);
                    break;
                }
                case 0x1F: {
                    zzebf.zzl(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, false);
                    break;
                }
                case 0x20: {
                    zzebf.zzg(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, false);
                    break;
                }
                case 33: {
                    zzebf.zzj(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, false);
                    break;
                }
                case 34: {
                    zzebf.zze(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, false);
                    break;
                }
                case 35: {
                    zzebf.zza(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, true);
                    break;
                }
                case 36: {
                    zzebf.zzb(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, true);
                    break;
                }
                case 37: {
                    zzebf.zzc(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, true);
                    break;
                }
                case 38: {
                    zzebf.zzd(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, true);
                    break;
                }
                case 39: {
                    zzebf.zzh(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, true);
                    break;
                }
                case 40: {
                    zzebf.zzf(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, true);
                    break;
                }
                case 41: {
                    zzebf.zzk(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, true);
                    break;
                }
                case 42: {
                    zzebf.zzn(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, true);
                    break;
                }
                case 43: {
                    zzebf.zzi(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, true);
                    break;
                }
                case 44: {
                    zzebf.zzm(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, true);
                    break;
                }
                case 45: {
                    zzebf.zzl(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, true);
                    break;
                }
                case 46: {
                    zzebf.zzg(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, true);
                    break;
                }
                case 0x2F: {
                    zzebf.zzj(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, true);
                    break;
                }
                case 0x30: {
                    zzebf.zze(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, true);
                    break;
                }
                case 49: {
                    zzebf.zzb(this.zzhvf[v1], ((List)unsafe0.getObject(object0, v9)), zzecs0, this.zzgn(v1));
                    break;
                }
                case 50: {
                    this.zza(zzecs0, v4, unsafe0.getObject(object0, v9), v1);
                    break;
                }
                case 51: {
                    if(this.zza(object0, v4, v1)) {
                        zzecs0.zzb(v4, zzeal.zzf(object0, v9));
                    }
                    break;
                }
                case 52: {
                    if(this.zza(object0, v4, v1)) {
                        zzecs0.zza(v4, zzeal.zzg(object0, v9));
                    }
                    break;
                }
                case 53: {
                    if(this.zza(object0, v4, v1)) {
                        zzecs0.zzp(v4, zzeal.zzi(object0, v9));
                    }
                    break;
                }
                case 54: {
                    if(this.zza(object0, v4, v1)) {
                        zzecs0.zzh(v4, zzeal.zzi(object0, v9));
                    }
                    break;
                }
                case 55: {
                    if(this.zza(object0, v4, v1)) {
                        zzecs0.zzac(v4, zzeal.zzh(object0, v9));
                    }
                    break;
                }
                case 56: {
                    if(this.zza(object0, v4, v1)) {
                        zzecs0.zzj(v4, zzeal.zzi(object0, v9));
                    }
                    break;
                }
                case 57: {
                    if(this.zza(object0, v4, v1)) {
                        zzecs0.zzaf(v4, zzeal.zzh(object0, v9));
                    }
                    break;
                }
                case 58: {
                    if(this.zza(object0, v4, v1)) {
                        zzecs0.zzh(v4, zzeal.zzj(object0, v9));
                    }
                    break;
                }
                case 59: {
                    if(this.zza(object0, v4, v1)) {
                        zzeal.zza(v4, unsafe0.getObject(object0, v9), zzecs0);
                    }
                    break;
                }
                case 60: {
                    if(this.zza(object0, v4, v1)) {
                        zzecs0.zza(v4, unsafe0.getObject(object0, v9), this.zzgn(v1));
                    }
                    break;
                }
                case 61: {
                    if(this.zza(object0, v4, v1)) {
                        zzecs0.zza(v4, ((zzdxn)unsafe0.getObject(object0, v9)));
                    }
                    break;
                }
                case 62: {
                    if(this.zza(object0, v4, v1)) {
                        zzecs0.zzad(v4, zzeal.zzh(object0, v9));
                    }
                    break;
                }
                case 0x3F: {
                    if(this.zza(object0, v4, v1)) {
                        zzecs0.zzan(v4, zzeal.zzh(object0, v9));
                    }
                    break;
                }
                case 0x40: {
                    if(this.zza(object0, v4, v1)) {
                        zzecs0.zzam(v4, zzeal.zzh(object0, v9));
                    }
                    break;
                }
                case 65: {
                    if(this.zza(object0, v4, v1)) {
                        zzecs0.zzq(v4, zzeal.zzi(object0, v9));
                    }
                    break;
                }
                case 66: {
                    if(this.zza(object0, v4, v1)) {
                        zzecs0.zzae(v4, zzeal.zzh(object0, v9));
                    }
                    break;
                }
                case 67: {
                    if(this.zza(object0, v4, v1)) {
                        zzecs0.zzi(v4, zzeal.zzi(object0, v9));
                    }
                    break;
                }
                case 68: {
                    if(this.zza(object0, v4, v1)) {
                        zzecs0.zzb(v4, unsafe0.getObject(object0, v9), this.zzgn(v1));
                    }
                }
            }
        }
        Map.Entry map$Entry3 = map$Entry1;
        while(map$Entry3 != null) {
            this.zzhvu.zza(zzecs0, map$Entry3);
            if(iterator0.hasNext()) {
                Object object3 = iterator0.next();
                map$Entry3 = (Map.Entry)object3;
            }
            else {
                map$Entry3 = null;
            }
        }
        zzeal.zza(this.zzhvt, object0, zzecs0);
    }

    private final void zzb(Object object0, Object object1, int v) {
        int v1 = this.zzgq(v);
        int v2 = this.zzhvf[v];
        if(!this.zza(object1, v2, v)) {
            return;
        }
        Object object2 = zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)));
        Object object3 = zzecb.zzp(object1, ((long)(v1 & 0xFFFFF)));
        if(object2 != null && object3 != null) {
            zzecb.zza(object0, ((long)(v1 & 0xFFFFF)), zzdzc.zzd(object2, object3));
            this.zzb(object0, v2, v);
            return;
        }
        if(object3 != null) {
            zzecb.zza(object0, ((long)(v1 & 0xFFFFF)), object3);
            this.zzb(object0, v2, v);
        }
    }

    private final boolean zzc(Object object0, Object object1, int v) {
        return this.zze(object0, v) == this.zze(object1, v);
    }

    private static List zze(Object object0, long v) {
        return (List)zzecb.zzp(object0, v);
    }

    private final boolean zze(Object object0, int v) {
        if(this.zzhvm) {
            int v1 = this.zzgq(v);
            switch((v1 & 0xFF00000) >>> 20) {
                case 0: {
                    return zzecb.zzo(object0, ((long)(v1 & 0xFFFFF))) != 0.0;
                }
                case 1: {
                    return zzecb.zzn(object0, ((long)(v1 & 0xFFFFF))) != 0.0f;
                }
                case 2: {
                    return zzecb.zzl(object0, ((long)(v1 & 0xFFFFF))) != 0L;
                }
                case 3: {
                    return zzecb.zzl(object0, ((long)(v1 & 0xFFFFF))) != 0L;
                }
                case 4: {
                    return zzecb.zzk(object0, ((long)(v1 & 0xFFFFF))) != 0;
                }
                case 5: {
                    return zzecb.zzl(object0, ((long)(v1 & 0xFFFFF))) != 0L;
                }
                case 6: {
                    return zzecb.zzk(object0, ((long)(v1 & 0xFFFFF))) != 0;
                }
                case 7: {
                    return zzecb.zzm(object0, ((long)(v1 & 0xFFFFF)));
                }
                case 8: {
                    Object object1 = zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)));
                    if(object1 instanceof String) {
                        return !((String)object1).isEmpty();
                    }
                    if(!(object1 instanceof zzdxn)) {
                        throw new IllegalArgumentException();
                    }
                    return !zzdxn.zzhoe.equals(object1);
                }
                case 9: {
                    return zzecb.zzp(object0, ((long)(v1 & 0xFFFFF))) != null;
                }
                case 10: {
                    Object object2 = zzecb.zzp(object0, ((long)(v1 & 0xFFFFF)));
                    return !zzdxn.zzhoe.equals(object2);
                }
                case 11: {
                    return zzecb.zzk(object0, ((long)(v1 & 0xFFFFF))) != 0;
                }
                case 12: {
                    return zzecb.zzk(object0, ((long)(v1 & 0xFFFFF))) != 0;
                }
                case 13: {
                    return zzecb.zzk(object0, ((long)(v1 & 0xFFFFF))) != 0;
                }
                case 14: {
                    return zzecb.zzl(object0, ((long)(v1 & 0xFFFFF))) != 0L;
                }
                case 15: {
                    return zzecb.zzk(object0, ((long)(v1 & 0xFFFFF))) != 0;
                }
                case 16: {
                    return zzecb.zzl(object0, ((long)(v1 & 0xFFFFF))) != 0L;
                }
                case 17: {
                    return zzecb.zzp(object0, ((long)(v1 & 0xFFFFF))) != null;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }
        int v2 = this.zzgr(v);
        return (zzecb.zzk(object0, ((long)(v2 & 0xFFFFF))) & 1 << (v2 >>> 20)) != 0;
    }

    private static double zzf(Object object0, long v) {
        return (double)(((Double)zzecb.zzp(object0, v)));
    }

    private final void zzf(Object object0, int v) {
        if(this.zzhvm) {
            return;
        }
        int v1 = this.zzgr(v);
        zzecb.zzb(object0, ((long)(v1 & 0xFFFFF)), zzecb.zzk(object0, ((long)(v1 & 0xFFFFF))) | 1 << (v1 >>> 20));
    }

    @Override  // com.google.android.gms.internal.ads.zzebd
    public final void zzf(Object object0, Object object1) {
        if(object1 == null) {
            throw new NullPointerException();
        }
        for(int v = 0; v < this.zzhvf.length; v += 3) {
            int v1 = this.zzgq(v);
            long v2 = (long)(0xFFFFF & v1);
            int v3 = this.zzhvf[v];
            switch((v1 & 0xFF00000) >>> 20) {
                case 0: {
                    if(this.zze(object1, v)) {
                        zzecb.zza(object0, v2, zzecb.zzo(object1, v2));
                        this.zzf(object0, v);
                    }
                    break;
                }
                case 1: {
                    if(this.zze(object1, v)) {
                        zzecb.zza(object0, v2, zzecb.zzn(object1, v2));
                        this.zzf(object0, v);
                    }
                    break;
                }
                case 2: {
                    if(this.zze(object1, v)) {
                        zzecb.zza(object0, v2, zzecb.zzl(object1, v2));
                        this.zzf(object0, v);
                    }
                    break;
                }
                case 3: {
                    if(this.zze(object1, v)) {
                        zzecb.zza(object0, v2, zzecb.zzl(object1, v2));
                        this.zzf(object0, v);
                    }
                    break;
                }
                case 4: {
                    if(this.zze(object1, v)) {
                        zzecb.zzb(object0, v2, zzecb.zzk(object1, v2));
                        this.zzf(object0, v);
                    }
                    break;
                }
                case 5: {
                    if(this.zze(object1, v)) {
                        zzecb.zza(object0, v2, zzecb.zzl(object1, v2));
                        this.zzf(object0, v);
                    }
                    break;
                }
                case 6: {
                    if(this.zze(object1, v)) {
                        zzecb.zzb(object0, v2, zzecb.zzk(object1, v2));
                        this.zzf(object0, v);
                    }
                    break;
                }
                case 7: {
                    if(this.zze(object1, v)) {
                        zzecb.zza(object0, v2, zzecb.zzm(object1, v2));
                        this.zzf(object0, v);
                    }
                    break;
                }
                case 8: {
                    if(this.zze(object1, v)) {
                        zzecb.zza(object0, v2, zzecb.zzp(object1, v2));
                        this.zzf(object0, v);
                    }
                    break;
                }
                case 9: {
                    this.zza(object0, object1, v);
                    break;
                }
                case 10: {
                    if(this.zze(object1, v)) {
                        zzecb.zza(object0, v2, zzecb.zzp(object1, v2));
                        this.zzf(object0, v);
                    }
                    break;
                }
                case 11: {
                    if(this.zze(object1, v)) {
                        zzecb.zzb(object0, v2, zzecb.zzk(object1, v2));
                        this.zzf(object0, v);
                    }
                    break;
                }
                case 12: {
                    if(this.zze(object1, v)) {
                        zzecb.zzb(object0, v2, zzecb.zzk(object1, v2));
                        this.zzf(object0, v);
                    }
                    break;
                }
                case 13: {
                    if(this.zze(object1, v)) {
                        zzecb.zzb(object0, v2, zzecb.zzk(object1, v2));
                        this.zzf(object0, v);
                    }
                    break;
                }
                case 14: {
                    if(this.zze(object1, v)) {
                        zzecb.zza(object0, v2, zzecb.zzl(object1, v2));
                        this.zzf(object0, v);
                    }
                    break;
                }
                case 15: {
                    if(this.zze(object1, v)) {
                        zzecb.zzb(object0, v2, zzecb.zzk(object1, v2));
                        this.zzf(object0, v);
                    }
                    break;
                }
                case 16: {
                    if(this.zze(object1, v)) {
                        zzecb.zza(object0, v2, zzecb.zzl(object1, v2));
                        this.zzf(object0, v);
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
                    this.zzhvs.zza(object0, object1, v2);
                    break;
                }
                case 50: {
                    zzebf.zza(this.zzhvv, object0, object1, v2);
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
                        zzecb.zza(object0, v2, zzecb.zzp(object1, v2));
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
                        zzecb.zza(object0, v2, zzecb.zzp(object1, v2));
                        this.zzb(object0, v3, v);
                    }
                    break;
                }
                case 68: {
                    this.zzb(object0, object1, v);
                }
            }
        }
        zzebf.zza(this.zzhvt, object0, object1);
        if(this.zzhvk) {
            zzebf.zza(this.zzhvu, object0, object1);
        }
    }

    private static float zzg(Object object0, long v) {
        return (float)(((Float)zzecb.zzp(object0, v)));
    }

    private final zzebd zzgn(int v) {
        int v1 = v / 3 << 1;
        zzebd zzebd0 = (zzebd)this.zzhvg[v1];
        if(zzebd0 != null) {
            return zzebd0;
        }
        zzebd zzebd1 = zzeaw.zzbem().zzh(((Class)this.zzhvg[v1 + 1]));
        this.zzhvg[v1] = zzebd1;
        return zzebd1;
    }

    private final Object zzgo(int v) {
        return this.zzhvg[v / 3 << 1];
    }

    private final zzdzd zzgp(int v) {
        return (zzdzd)this.zzhvg[(v / 3 << 1) + 1];
    }

    private final int zzgq(int v) {
        return this.zzhvf[v + 1];
    }

    private final int zzgr(int v) {
        return this.zzhvf[v + 2];
    }

    private static boolean zzgs(int v) {
        return (v & 0x20000000) != 0;
    }

    private final int zzgt(int v) {
        return v < this.zzhvh || v > this.zzhvi ? -1 : this.zzap(v, 0);
    }

    private static int zzh(Object object0, long v) {
        return (int)(((Integer)zzecb.zzp(object0, v)));
    }

    private static long zzi(Object object0, long v) {
        return (long)(((Long)zzecb.zzp(object0, v)));
    }

    private static boolean zzj(Object object0, long v) {
        return ((Boolean)zzecb.zzp(object0, v)).booleanValue();
    }
}

