package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzebf {
    private static final Class zzhwt;
    private static final zzebv zzhwu;
    private static final zzebv zzhwv;
    private static final zzebv zzhww;

    static {
        zzebf.zzhwt = zzebf.zzbeu();
        zzebf.zzhwu = zzebf.zzbs(false);
        zzebf.zzhwv = zzebf.zzbs(true);
        zzebf.zzhww = new zzebx();
    }

    static Object zza(int v, int v1, Object object0, zzebv zzebv0) {
        if(object0 == null) {
            object0 = zzebv0.zzbfe();
        }
        zzebv0.zza(object0, v, ((long)v1));
        return object0;
    }

    static Object zza(int v, List list0, zzdzd zzdzd0, Object object0, zzebv zzebv0) {
        Object object1;
        if(zzdzd0 == null) {
            return object0;
        }
        if(list0 instanceof RandomAccess) {
            int v1 = list0.size();
            object1 = object0;
            int v3 = 0;
            for(int v2 = 0; v2 < v1; ++v2) {
                int v4 = (int)(((Integer)list0.get(v2)));
                if(zzdzd0.zzf(v4)) {
                    if(v2 != v3) {
                        list0.set(v3, v4);
                    }
                    ++v3;
                }
                else {
                    object1 = zzebf.zza(v, v4, object1, zzebv0);
                }
            }
            if(v3 != v1) {
                list0.subList(v3, v1).clear();
                return object1;
            }
        }
        else {
            object1 = object0;
            Iterator iterator0 = list0.iterator();
            while(iterator0.hasNext()) {
                Object object2 = iterator0.next();
                int v5 = (int)(((Integer)object2));
                if(!zzdzd0.zzf(v5)) {
                    Object object3 = zzebf.zza(v, v5, object1, zzebv0);
                    iterator0.remove();
                    object1 = object3;
                }
            }
        }
        return object1;
    }

    public static void zza(int v, List list0, zzecs zzecs0) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzecs0.zza(v, list0);
        }
    }

    public static void zza(int v, List list0, zzecs zzecs0, zzebd zzebd0) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzecs0.zza(v, list0, zzebd0);
        }
    }

    public static void zza(int v, List list0, zzecs zzecs0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzecs0.zzg(v, list0, z);
        }
    }

    static void zza(zzdyo zzdyo0, Object object0, Object object1) {
        zzdyp zzdyp0 = zzdyo0.zzal(object1);
        if(!zzdyp0.zzhpu.isEmpty()) {
            zzdyo0.zzam(object0).zza(zzdyp0);
        }
    }

    static void zza(zzeae zzeae0, Object object0, Object object1, long v) {
        zzecb.zza(object0, v, zzeae0.zze(zzecb.zzp(object0, v), zzecb.zzp(object1, v)));
    }

    static void zza(zzebv zzebv0, Object object0, Object object1) {
        zzebv0.zzh(object0, zzebv0.zzj(zzebv0.zzbb(object0), zzebv0.zzbb(object1)));
    }

    static int zzaa(List list0) {
        int v2;
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzdzv) {
            v2 = 0;
            while(v1 < v) {
                v2 += zzdyi.zzfl(((zzdzv)list0).getLong(v1));
                ++v1;
            }
            return v2;
        }
        v2 = 0;
        while(v1 < v) {
            v2 += zzdyi.zzfl(((long)(((Long)list0.get(v1)))));
            ++v1;
        }
        return v2;
    }

    static int zzab(List list0) {
        int v2;
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzdzv) {
            v2 = 0;
            while(v1 < v) {
                v2 += zzdyi.zzfm(((zzdzv)list0).getLong(v1));
                ++v1;
            }
            return v2;
        }
        v2 = 0;
        while(v1 < v) {
            v2 += zzdyi.zzfm(((long)(((Long)list0.get(v1)))));
            ++v1;
        }
        return v2;
    }

    static int zzac(List list0) {
        int v2;
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzdza) {
            v2 = 0;
            while(v1 < v) {
                v2 += zzdyi.zzgf(((zzdza)list0).getInt(v1));
                ++v1;
            }
            return v2;
        }
        v2 = 0;
        while(v1 < v) {
            v2 += zzdyi.zzgf(((int)(((Integer)list0.get(v1)))));
            ++v1;
        }
        return v2;
    }

    static int zzad(List list0) {
        int v2;
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzdza) {
            v2 = 0;
            while(v1 < v) {
                v2 += zzdyi.zzga(((zzdza)list0).getInt(v1));
                ++v1;
            }
            return v2;
        }
        v2 = 0;
        while(v1 < v) {
            v2 += zzdyi.zzga(((int)(((Integer)list0.get(v1)))));
            ++v1;
        }
        return v2;
    }

    static int zzae(List list0) {
        int v2;
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzdza) {
            v2 = 0;
            while(v1 < v) {
                v2 += zzdyi.zzgb(((zzdza)list0).getInt(v1));
                ++v1;
            }
            return v2;
        }
        v2 = 0;
        while(v1 < v) {
            v2 += zzdyi.zzgb(((int)(((Integer)list0.get(v1)))));
            ++v1;
        }
        return v2;
    }

    static int zzaf(List list0) {
        int v2;
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzdza) {
            v2 = 0;
            while(v1 < v) {
                v2 += zzdyi.zzgc(((zzdza)list0).getInt(v1));
                ++v1;
            }
            return v2;
        }
        v2 = 0;
        while(v1 < v) {
            v2 += zzdyi.zzgc(((int)(((Integer)list0.get(v1)))));
            ++v1;
        }
        return v2;
    }

    static int zzag(List list0) {
        return list0.size() << 2;
    }

    static int zzah(List list0) {
        return list0.size() << 3;
    }

    static int zzai(List list0) {
        return list0.size();
    }

    public static void zzb(int v, List list0, zzecs zzecs0) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzecs0.zzb(v, list0);
        }
    }

    public static void zzb(int v, List list0, zzecs zzecs0, zzebd zzebd0) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzecs0.zzb(v, list0, zzebd0);
        }
    }

    public static void zzb(int v, List list0, zzecs zzecs0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzecs0.zzf(v, list0, z);
        }
    }

    public static zzebv zzber() {
        return zzebf.zzhwu;
    }

    public static zzebv zzbes() {
        return zzebf.zzhwv;
    }

    public static zzebv zzbet() {
        return zzebf.zzhww;
    }

    private static Class zzbeu() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    private static Class zzbev() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    private static zzebv zzbs(boolean z) {
        try {
            Class class0 = zzebf.zzbev();
            return class0 == null ? null : ((zzebv)class0.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z)));
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    // 去混淆评级： 低(20)
    static int zzc(int v, Object object0, zzebd zzebd0) {
        return object0 instanceof zzdzq ? zzdyi.zza(v, ((zzdzq)object0)) : zzdyi.zzb(v, ((zzeah)object0), zzebd0);
    }

    static int zzc(int v, List list0) {
        int v1 = list0.size();
        int v2 = 0;
        if(v1 == 0) {
            return 0;
        }
        int v3 = zzdyi.zzfz(v) * v1;
        if(list0 instanceof zzdzs) {
            while(v2 < v1) {
                Object object0 = ((zzdzs)list0).zzgm(v2);
                v3 = object0 instanceof zzdxn ? v3 + zzdyi.zzai(((zzdxn)object0)) : v3 + zzdyi.zzhk(((String)object0));
                ++v2;
            }
            return v3;
        }
        while(v2 < v1) {
            Object object1 = list0.get(v2);
            v3 = object1 instanceof zzdxn ? v3 + zzdyi.zzai(((zzdxn)object1)) : v3 + zzdyi.zzhk(((String)object1));
            ++v2;
        }
        return v3;
    }

    static int zzc(int v, List list0, zzebd zzebd0) {
        int v1 = list0.size();
        if(v1 == 0) {
            return 0;
        }
        int v3 = zzdyi.zzfz(v) * v1;
        for(int v2 = 0; v2 < v1; ++v2) {
            Object object0 = list0.get(v2);
            v3 = object0 instanceof zzdzq ? v3 + zzdyi.zza(((zzdzq)object0)) : v3 + zzdyi.zzb(((zzeah)object0), zzebd0);
        }
        return v3;
    }

    public static void zzc(int v, List list0, zzecs zzecs0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzecs0.zzc(v, list0, z);
        }
    }

    static int zzd(int v, List list0) {
        int v1 = list0.size();
        if(v1 == 0) {
            return 0;
        }
        int v3 = v1 * zzdyi.zzfz(v);
        for(int v2 = 0; v2 < list0.size(); ++v2) {
            v3 += zzdyi.zzai(((zzdxn)list0.get(v2)));
        }
        return v3;
    }

    static int zzd(int v, List list0, zzebd zzebd0) {
        int v1 = list0.size();
        if(v1 == 0) {
            return 0;
        }
        int v3 = 0;
        for(int v2 = 0; v2 < v1; ++v2) {
            v3 += zzdyi.zzc(v, ((zzeah)list0.get(v2)), zzebd0);
        }
        return v3;
    }

    public static void zzd(int v, List list0, zzecs zzecs0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzecs0.zzd(v, list0, z);
        }
    }

    public static void zze(int v, List list0, zzecs zzecs0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzecs0.zzn(v, list0, z);
        }
    }

    public static void zzf(int v, List list0, zzecs zzecs0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzecs0.zze(v, list0, z);
        }
    }

    public static void zzg(int v, List list0, zzecs zzecs0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzecs0.zzl(v, list0, z);
        }
    }

    // 去混淆评级： 低(20)
    static boolean zzg(Object object0, Object object1) {
        return object0 == object1 || object0 != null && object0.equals(object1);
    }

    public static void zzh(int v, List list0, zzecs zzecs0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzecs0.zza(v, list0, z);
        }
    }

    public static void zzi(int v, List list0, zzecs zzecs0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzecs0.zzj(v, list0, z);
        }
    }

    public static void zzi(Class class0) {
        if(!zzdyz.class.isAssignableFrom(class0) && (zzebf.zzhwt != null && !zzebf.zzhwt.isAssignableFrom(class0))) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzj(int v, List list0, zzecs zzecs0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzecs0.zzm(v, list0, z);
        }
    }

    public static void zzk(int v, List list0, zzecs zzecs0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzecs0.zzb(v, list0, z);
        }
    }

    public static void zzl(int v, List list0, zzecs zzecs0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzecs0.zzk(v, list0, z);
        }
    }

    public static void zzm(int v, List list0, zzecs zzecs0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzecs0.zzh(v, list0, z);
        }
    }

    public static void zzn(int v, List list0, zzecs zzecs0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzecs0.zzi(v, list0, z);
        }
    }

    static int zzo(int v, List list0, boolean z) {
        return list0.size() == 0 ? 0 : zzebf.zzz(list0) + list0.size() * zzdyi.zzfz(v);
    }

    static int zzp(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : zzebf.zzaa(list0) + v1 * zzdyi.zzfz(v);
    }

    static int zzq(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : zzebf.zzab(list0) + v1 * zzdyi.zzfz(v);
    }

    static int zzr(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : zzebf.zzac(list0) + v1 * zzdyi.zzfz(v);
    }

    static int zzs(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : zzebf.zzad(list0) + v1 * zzdyi.zzfz(v);
    }

    static int zzt(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : zzebf.zzae(list0) + v1 * zzdyi.zzfz(v);
    }

    static int zzu(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : zzebf.zzaf(list0) + v1 * zzdyi.zzfz(v);
    }

    static int zzv(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : v1 * zzdyi.zzaj(v, 0);
    }

    static int zzw(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : v1 * zzdyi.zzn(v, 0L);
    }

    static int zzx(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : v1 * zzdyi.zzi(v, true);
    }

    static int zzz(List list0) {
        int v2;
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzdzv) {
            v2 = 0;
            while(v1 < v) {
                v2 += zzdyi.zzfk(((zzdzv)list0).getLong(v1));
                ++v1;
            }
            return v2;
        }
        v2 = 0;
        while(v1 < v) {
            v2 += zzdyi.zzfk(((long)(((Long)list0.get(v1)))));
            ++v1;
        }
        return v2;
    }
}

