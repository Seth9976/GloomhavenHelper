package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzhe {
    private static final Class zza;
    private static final zzhu zzb;
    private static final zzhu zzc;
    private static final zzhu zzd;

    static {
        zzhe.zza = zzhe.zzd();
        zzhe.zzb = zzhe.zza(false);
        zzhe.zzc = zzhe.zza(true);
        zzhe.zzd = new zzhw();
    }

    // 去混淆评级： 低(20)
    static int zza(int v, Object object0, zzhc zzhc0) {
        return object0 instanceof zzfs ? zzek.zza(v, ((zzfs)object0)) : zzek.zzb(v, ((zzgn)object0), zzhc0);
    }

    static int zza(int v, List list0) {
        int v1 = list0.size();
        int v2 = 0;
        if(v1 == 0) {
            return 0;
        }
        int v3 = zzek.zze(v) * v1;
        if(list0 instanceof zzfu) {
            while(v2 < v1) {
                Object object0 = ((zzfu)list0).zzb(v2);
                v3 = object0 instanceof zzdv ? v3 + zzek.zzb(((zzdv)object0)) : v3 + zzek.zzb(((String)object0));
                ++v2;
            }
            return v3;
        }
        while(v2 < v1) {
            Object object1 = list0.get(v2);
            v3 = object1 instanceof zzdv ? v3 + zzek.zzb(((zzdv)object1)) : v3 + zzek.zzb(((String)object1));
            ++v2;
        }
        return v3;
    }

    static int zza(int v, List list0, zzhc zzhc0) {
        int v1 = list0.size();
        if(v1 == 0) {
            return 0;
        }
        int v3 = zzek.zze(v) * v1;
        for(int v2 = 0; v2 < v1; ++v2) {
            Object object0 = list0.get(v2);
            v3 = object0 instanceof zzfs ? v3 + zzek.zza(((zzfs)object0)) : v3 + zzek.zza(((zzgn)object0), zzhc0);
        }
        return v3;
    }

    static int zza(int v, List list0, boolean z) {
        return list0.size() == 0 ? 0 : zzhe.zza(list0) + list0.size() * zzek.zze(v);
    }

    static int zza(List list0) {
        int v2;
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzgb) {
            v2 = 0;
            while(v1 < v) {
                v2 += zzek.zzd(((zzgb)list0).zzb(v1));
                ++v1;
            }
            return v2;
        }
        v2 = 0;
        while(v1 < v) {
            v2 += zzek.zzd(((long)(((Long)list0.get(v1)))));
            ++v1;
        }
        return v2;
    }

    public static zzhu zza() {
        return zzhe.zzb;
    }

    private static zzhu zza(boolean z) {
        try {
            Class class0 = zzhe.zze();
            return class0 == null ? null : ((zzhu)class0.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z)));
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    static Object zza(int v, int v1, Object object0, zzhu zzhu0) {
        if(object0 == null) {
            object0 = zzhu0.zza();
        }
        zzhu0.zza(object0, v, ((long)v1));
        return object0;
    }

    static Object zza(int v, List list0, zzfj zzfj0, Object object0, zzhu zzhu0) {
        Object object1;
        if(zzfj0 == null) {
            return object0;
        }
        if(list0 instanceof RandomAccess) {
            int v1 = list0.size();
            object1 = object0;
            int v3 = 0;
            for(int v2 = 0; v2 < v1; ++v2) {
                int v4 = (int)(((Integer)list0.get(v2)));
                if(zzfj0.zza(v4)) {
                    if(v2 != v3) {
                        list0.set(v3, v4);
                    }
                    ++v3;
                }
                else {
                    object1 = zzhe.zza(v, v4, object1, zzhu0);
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
                if(!zzfj0.zza(v5)) {
                    Object object3 = zzhe.zza(v, v5, object1, zzhu0);
                    iterator0.remove();
                    object1 = object3;
                }
            }
        }
        return object1;
    }

    public static void zza(int v, List list0, zzir zzir0) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzir0.zza(v, list0);
        }
    }

    public static void zza(int v, List list0, zzir zzir0, zzhc zzhc0) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzir0.zza(v, list0, zzhc0);
        }
    }

    public static void zza(int v, List list0, zzir zzir0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzir0.zzg(v, list0, z);
        }
    }

    static void zza(zzes zzes0, Object object0, Object object1) {
        zzet zzet0 = zzes0.zza(object1);
        if(!zzet0.zza.isEmpty()) {
            zzes0.zzb(object0).zza(zzet0);
        }
    }

    static void zza(zzgg zzgg0, Object object0, Object object1, long v) {
        zzia.zza(object0, v, zzgg0.zza(zzia.zzf(object0, v), zzia.zzf(object1, v)));
    }

    static void zza(zzhu zzhu0, Object object0, Object object1) {
        zzhu0.zza(object0, zzhu0.zzc(zzhu0.zzb(object0), zzhu0.zzb(object1)));
    }

    public static void zza(Class class0) {
        if(!zzfd.class.isAssignableFrom(class0) && (zzhe.zza != null && !zzhe.zza.isAssignableFrom(class0))) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    // 去混淆评级： 低(20)
    static boolean zza(Object object0, Object object1) {
        return object0 == object1 || object0 != null && object0.equals(object1);
    }

    static int zzb(int v, List list0) {
        int v1 = list0.size();
        if(v1 == 0) {
            return 0;
        }
        int v3 = v1 * zzek.zze(v);
        for(int v2 = 0; v2 < list0.size(); ++v2) {
            v3 += zzek.zzb(((zzdv)list0.get(v2)));
        }
        return v3;
    }

    static int zzb(int v, List list0, zzhc zzhc0) {
        int v1 = list0.size();
        if(v1 == 0) {
            return 0;
        }
        int v3 = 0;
        for(int v2 = 0; v2 < v1; ++v2) {
            v3 += zzek.zzc(v, ((zzgn)list0.get(v2)), zzhc0);
        }
        return v3;
    }

    static int zzb(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : zzhe.zzb(list0) + v1 * zzek.zze(v);
    }

    static int zzb(List list0) {
        int v2;
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzgb) {
            v2 = 0;
            while(v1 < v) {
                v2 += zzek.zze(((zzgb)list0).zzb(v1));
                ++v1;
            }
            return v2;
        }
        v2 = 0;
        while(v1 < v) {
            v2 += zzek.zze(((long)(((Long)list0.get(v1)))));
            ++v1;
        }
        return v2;
    }

    public static zzhu zzb() {
        return zzhe.zzc;
    }

    public static void zzb(int v, List list0, zzir zzir0) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzir0.zzb(v, list0);
        }
    }

    public static void zzb(int v, List list0, zzir zzir0, zzhc zzhc0) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzir0.zzb(v, list0, zzhc0);
        }
    }

    public static void zzb(int v, List list0, zzir zzir0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzir0.zzf(v, list0, z);
        }
    }

    static int zzc(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : zzhe.zzc(list0) + v1 * zzek.zze(v);
    }

    static int zzc(List list0) {
        int v2;
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzgb) {
            v2 = 0;
            while(v1 < v) {
                v2 += zzek.zzf(((zzgb)list0).zzb(v1));
                ++v1;
            }
            return v2;
        }
        v2 = 0;
        while(v1 < v) {
            v2 += zzek.zzf(((long)(((Long)list0.get(v1)))));
            ++v1;
        }
        return v2;
    }

    public static zzhu zzc() {
        return zzhe.zzd;
    }

    public static void zzc(int v, List list0, zzir zzir0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzir0.zzc(v, list0, z);
        }
    }

    static int zzd(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : zzhe.zzd(list0) + v1 * zzek.zze(v);
    }

    static int zzd(List list0) {
        int v2;
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzff) {
            v2 = 0;
            while(v1 < v) {
                v2 += zzek.zzk(((zzff)list0).zzc(v1));
                ++v1;
            }
            return v2;
        }
        v2 = 0;
        while(v1 < v) {
            v2 += zzek.zzk(((int)(((Integer)list0.get(v1)))));
            ++v1;
        }
        return v2;
    }

    private static Class zzd() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    public static void zzd(int v, List list0, zzir zzir0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzir0.zzd(v, list0, z);
        }
    }

    static int zze(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : zzhe.zze(list0) + v1 * zzek.zze(v);
    }

    static int zze(List list0) {
        int v2;
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzff) {
            v2 = 0;
            while(v1 < v) {
                v2 += zzek.zzf(((zzff)list0).zzc(v1));
                ++v1;
            }
            return v2;
        }
        v2 = 0;
        while(v1 < v) {
            v2 += zzek.zzf(((int)(((Integer)list0.get(v1)))));
            ++v1;
        }
        return v2;
    }

    private static Class zze() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    public static void zze(int v, List list0, zzir zzir0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzir0.zzn(v, list0, z);
        }
    }

    static int zzf(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : zzhe.zzf(list0) + v1 * zzek.zze(v);
    }

    static int zzf(List list0) {
        int v2;
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzff) {
            v2 = 0;
            while(v1 < v) {
                v2 += zzek.zzg(((zzff)list0).zzc(v1));
                ++v1;
            }
            return v2;
        }
        v2 = 0;
        while(v1 < v) {
            v2 += zzek.zzg(((int)(((Integer)list0.get(v1)))));
            ++v1;
        }
        return v2;
    }

    public static void zzf(int v, List list0, zzir zzir0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzir0.zze(v, list0, z);
        }
    }

    static int zzg(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : zzhe.zzg(list0) + v1 * zzek.zze(v);
    }

    static int zzg(List list0) {
        int v2;
        int v = list0.size();
        int v1 = 0;
        if(v == 0) {
            return 0;
        }
        if(list0 instanceof zzff) {
            v2 = 0;
            while(v1 < v) {
                v2 += zzek.zzh(((zzff)list0).zzc(v1));
                ++v1;
            }
            return v2;
        }
        v2 = 0;
        while(v1 < v) {
            v2 += zzek.zzh(((int)(((Integer)list0.get(v1)))));
            ++v1;
        }
        return v2;
    }

    public static void zzg(int v, List list0, zzir zzir0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzir0.zzl(v, list0, z);
        }
    }

    static int zzh(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : v1 * zzek.zzi(v, 0);
    }

    static int zzh(List list0) {
        return list0.size() << 2;
    }

    public static void zzh(int v, List list0, zzir zzir0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzir0.zza(v, list0, z);
        }
    }

    static int zzi(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : v1 * zzek.zzg(v, 0L);
    }

    static int zzi(List list0) {
        return list0.size() << 3;
    }

    public static void zzi(int v, List list0, zzir zzir0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzir0.zzj(v, list0, z);
        }
    }

    static int zzj(int v, List list0, boolean z) {
        int v1 = list0.size();
        return v1 == 0 ? 0 : v1 * zzek.zzb(v, true);
    }

    static int zzj(List list0) {
        return list0.size();
    }

    public static void zzj(int v, List list0, zzir zzir0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzir0.zzm(v, list0, z);
        }
    }

    public static void zzk(int v, List list0, zzir zzir0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzir0.zzb(v, list0, z);
        }
    }

    public static void zzl(int v, List list0, zzir zzir0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzir0.zzk(v, list0, z);
        }
    }

    public static void zzm(int v, List list0, zzir zzir0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzir0.zzh(v, list0, z);
        }
    }

    public static void zzn(int v, List list0, zzir zzir0, boolean z) throws IOException {
        if(list0 != null && !list0.isEmpty()) {
            zzir0.zzi(v, list0, z);
        }
    }
}

