package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zzdxj {
    static int zza(int v, byte[] arr_b, int v1, int v2, zzdxm zzdxm0) throws zzdzh {
        if(v >>> 3 == 0) {
            throw zzdzh.zzbdl();
        }
        if((v & 7) != 5) {
            switch(v & 7) {
                case 0: {
                    return zzdxj.zzb(arr_b, v1, zzdxm0);
                }
                case 1: {
                    return v1 + 8;
                }
                case 2: {
                    return zzdxj.zza(arr_b, v1, zzdxm0) + zzdxm0.zzhoa;
                }
                case 3: {
                    int v3 = v & -8 | 4;
                    int v4 = 0;
                    while(v1 < v2) {
                        v1 = zzdxj.zza(arr_b, v1, zzdxm0);
                        v4 = zzdxm0.zzhoa;
                        if(v4 == v3) {
                            break;
                        }
                        v1 = zzdxj.zza(v4, arr_b, v1, v2, zzdxm0);
                    }
                    if(v1 > v2 || v4 != v3) {
                        throw zzdzh.zzbdp();
                    }
                    return v1;
                }
                default: {
                    throw zzdzh.zzbdl();
                }
            }
        }
        return v1 + 4;
    }

    static int zza(int v, byte[] arr_b, int v1, int v2, zzdzi zzdzi0, zzdxm zzdxm0) {
        int v3 = zzdxj.zza(arr_b, v1, zzdxm0);
        ((zzdza)zzdzi0).zzgl(zzdxm0.zzhoa);
        while(v3 < v2) {
            int v4 = zzdxj.zza(arr_b, v3, zzdxm0);
            if(v != zzdxm0.zzhoa) {
                break;
            }
            v3 = zzdxj.zza(arr_b, v4, zzdxm0);
            ((zzdza)zzdzi0).zzgl(zzdxm0.zzhoa);
        }
        return v3;
    }

    static int zza(int v, byte[] arr_b, int v1, int v2, zzeby zzeby0, zzdxm zzdxm0) throws zzdzh {
        if(v >>> 3 == 0) {
            throw zzdzh.zzbdl();
        }
        if((v & 7) != 5) {
            switch(v & 7) {
                case 0: {
                    int v3 = zzdxj.zzb(arr_b, v1, zzdxm0);
                    zzeby0.zzd(v, zzdxm0.zzhob);
                    return v3;
                }
                case 1: {
                    zzeby0.zzd(v, zzdxj.zzg(arr_b, v1));
                    return v1 + 8;
                }
                case 2: {
                    int v4 = zzdxj.zza(arr_b, v1, zzdxm0);
                    int v5 = zzdxm0.zzhoa;
                    if(v5 < 0) {
                        throw zzdzh.zzbdj();
                    }
                    if(v5 > arr_b.length - v4) {
                        throw zzdzh.zzbdi();
                    }
                    if(v5 == 0) {
                        zzeby0.zzd(v, zzdxn.zzhoe);
                        return v4;
                    }
                    zzeby0.zzd(v, zzdxn.zzh(arr_b, v4, v5));
                    return v4 + v5;
                }
                case 3: {
                    zzeby zzeby1 = zzeby.zzbfg();
                    int v6 = v & -8 | 4;
                    int v7 = 0;
                    while(v1 < v2) {
                        int v8 = zzdxj.zza(arr_b, v1, zzdxm0);
                        int v9 = zzdxm0.zzhoa;
                        if(v9 == v6) {
                            v7 = v9;
                            v1 = v8;
                            if(true) {
                                break;
                            }
                        }
                        else {
                            v7 = v9;
                            v1 = zzdxj.zza(v9, arr_b, v8, v2, zzeby1, zzdxm0);
                        }
                    }
                    if(v1 > v2 || v7 != v6) {
                        throw zzdzh.zzbdp();
                    }
                    zzeby0.zzd(v, zzeby1);
                    return v1;
                }
                default: {
                    throw zzdzh.zzbdl();
                }
            }
        }
        zzeby0.zzd(v, zzdxj.zzf(arr_b, v1));
        return v1 + 4;
    }

    static int zza(int v, byte[] arr_b, int v1, zzdxm zzdxm0) {
        int v2 = arr_b[v1];
        if(v2 >= 0) {
            zzdxm0.zzhoa = v & 0x7F | v2 << 7;
            return v1 + 1;
        }
        int v3 = v & 0x7F | (v2 & 0x7F) << 7;
        int v4 = arr_b[v1 + 1];
        if(v4 >= 0) {
            zzdxm0.zzhoa = v3 | v4 << 14;
            return v1 + 2;
        }
        int v5 = v3 | (v4 & 0x7F) << 14;
        int v6 = arr_b[v1 + 2];
        if(v6 >= 0) {
            zzdxm0.zzhoa = v5 | v6 << 21;
            return v1 + 3;
        }
        int v7 = v5 | (v6 & 0x7F) << 21;
        int v8 = v1 + 4;
        int v9 = arr_b[v1 + 3];
        if(v9 >= 0) {
            zzdxm0.zzhoa = v7 | v9 << 28;
            return v8;
        }
        while(arr_b[v8] < 0) {
            ++v8;
        }
        zzdxm0.zzhoa = v7 | (v9 & 0x7F) << 28;
        return v8 + 1;
    }

    static int zza(zzebd zzebd0, int v, byte[] arr_b, int v1, int v2, zzdzi zzdzi0, zzdxm zzdxm0) throws IOException {
        int v3 = zzdxj.zza(zzebd0, arr_b, v1, v2, zzdxm0);
        zzdzi0.add(zzdxm0.zzhoc);
        while(v3 < v2) {
            int v4 = zzdxj.zza(arr_b, v3, zzdxm0);
            if(v != zzdxm0.zzhoa) {
                break;
            }
            v3 = zzdxj.zza(zzebd0, arr_b, v4, v2, zzdxm0);
            zzdzi0.add(zzdxm0.zzhoc);
        }
        return v3;
    }

    static int zza(zzebd zzebd0, byte[] arr_b, int v, int v1, int v2, zzdxm zzdxm0) throws IOException {
        Object object0 = ((zzeal)zzebd0).newInstance();
        int v3 = ((zzeal)zzebd0).zza(object0, arr_b, v, v1, v2, zzdxm0);
        ((zzeal)zzebd0).zzan(object0);
        zzdxm0.zzhoc = object0;
        return v3;
    }

    static int zza(zzebd zzebd0, byte[] arr_b, int v, int v1, zzdxm zzdxm0) throws IOException {
        int v4;
        int v2 = arr_b[v];
        if(v2 < 0) {
            int v3 = zzdxj.zza(v2, arr_b, v + 1, zzdxm0);
            v2 = zzdxm0.zzhoa;
            v4 = v3;
        }
        else {
            v4 = v + 1;
        }
        if(v2 < 0 || v2 > v1 - v4) {
            throw zzdzh.zzbdi();
        }
        Object object0 = zzebd0.newInstance();
        int v5 = v2 + v4;
        zzebd0.zza(object0, arr_b, v4, v5, zzdxm0);
        zzebd0.zzan(object0);
        zzdxm0.zzhoc = object0;
        return v5;
    }

    static int zza(byte[] arr_b, int v, zzdxm zzdxm0) {
        int v1 = arr_b[v];
        if(v1 >= 0) {
            zzdxm0.zzhoa = v1;
            return v + 1;
        }
        return zzdxj.zza(v1, arr_b, v + 1, zzdxm0);
    }

    static int zza(byte[] arr_b, int v, zzdzi zzdzi0, zzdxm zzdxm0) throws IOException {
        int v1 = zzdxj.zza(arr_b, v, zzdxm0);
        int v2 = zzdxm0.zzhoa + v1;
        while(v1 < v2) {
            v1 = zzdxj.zza(arr_b, v1, zzdxm0);
            ((zzdza)zzdzi0).zzgl(zzdxm0.zzhoa);
        }
        if(v1 != v2) {
            throw zzdzh.zzbdi();
        }
        return v1;
    }

    static int zzb(byte[] arr_b, int v, zzdxm zzdxm0) {
        long v1 = (long)arr_b[v];
        if(v1 >= 0L) {
            zzdxm0.zzhob = v1;
            return v + 1;
        }
        int v2 = v + 2;
        int v3 = arr_b[v + 1];
        long v4 = v1 & 0x7FL | ((long)(v3 & 0x7F)) << 7;
        int v5 = 7;
        while(v3 < 0) {
            int v6 = arr_b[v2];
            v5 += 7;
            v4 |= ((long)(v6 & 0x7F)) << v5;
            v3 = v6;
            ++v2;
        }
        zzdxm0.zzhob = v4;
        return v2;
    }

    static int zzc(byte[] arr_b, int v, zzdxm zzdxm0) throws zzdzh {
        int v1 = zzdxj.zza(arr_b, v, zzdxm0);
        int v2 = zzdxm0.zzhoa;
        if(v2 < 0) {
            throw zzdzh.zzbdj();
        }
        if(v2 == 0) {
            zzdxm0.zzhoc = "";
            return v1;
        }
        zzdxm0.zzhoc = new String(arr_b, v1, v2, zzdzc.UTF_8);
        return v1 + v2;
    }

    static int zzd(byte[] arr_b, int v, zzdxm zzdxm0) throws zzdzh {
        int v1 = zzdxj.zza(arr_b, v, zzdxm0);
        int v2 = zzdxm0.zzhoa;
        if(v2 < 0) {
            throw zzdzh.zzbdj();
        }
        if(v2 == 0) {
            zzdxm0.zzhoc = "";
            return v1;
        }
        zzdxm0.zzhoc = zzece.zzo(arr_b, v1, v2);
        return v1 + v2;
    }

    static int zze(byte[] arr_b, int v, zzdxm zzdxm0) throws zzdzh {
        int v1 = zzdxj.zza(arr_b, v, zzdxm0);
        int v2 = zzdxm0.zzhoa;
        if(v2 < 0) {
            throw zzdzh.zzbdj();
        }
        if(v2 > arr_b.length - v1) {
            throw zzdzh.zzbdi();
        }
        if(v2 == 0) {
            zzdxm0.zzhoc = zzdxn.zzhoe;
            return v1;
        }
        zzdxm0.zzhoc = zzdxn.zzh(arr_b, v1, v2);
        return v1 + v2;
    }

    static int zzf(byte[] arr_b, int v) {
        return (arr_b[v + 3] & 0xFF) << 24 | (arr_b[v] & 0xFF | (arr_b[v + 1] & 0xFF) << 8 | (arr_b[v + 2] & 0xFF) << 16);
    }

    static long zzg(byte[] arr_b, int v) {
        return (((long)arr_b[v + 7]) & 0xFFL) << 56 | (((long)arr_b[v]) & 0xFFL | (((long)arr_b[v + 1]) & 0xFFL) << 8 | (((long)arr_b[v + 2]) & 0xFFL) << 16 | (((long)arr_b[v + 3]) & 0xFFL) << 24 | (((long)arr_b[v + 4]) & 0xFFL) << 0x20 | (((long)arr_b[v + 5]) & 0xFFL) << 40 | (((long)arr_b[v + 6]) & 0xFFL) << 0x30);
    }

    static double zzh(byte[] arr_b, int v) {
        return Double.longBitsToDouble(zzdxj.zzg(arr_b, v));
    }

    static float zzi(byte[] arr_b, int v) {
        return Float.intBitsToFloat(zzdxj.zzf(arr_b, v));
    }
}

