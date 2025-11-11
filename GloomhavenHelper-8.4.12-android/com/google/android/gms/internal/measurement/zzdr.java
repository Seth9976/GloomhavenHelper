package com.google.android.gms.internal.measurement;

import java.io.IOException;

final class zzdr {
    static int zza(int v, byte[] arr_b, int v1, int v2, zzdq zzdq0) throws zzfn {
        if(v >>> 3 == 0) {
            throw zzfn.zzd();
        }
        if((v & 7) != 5) {
            switch(v & 7) {
                case 0: {
                    return zzdr.zzb(arr_b, v1, zzdq0);
                }
                case 1: {
                    return v1 + 8;
                }
                case 2: {
                    return zzdr.zza(arr_b, v1, zzdq0) + zzdq0.zza;
                }
                case 3: {
                    int v3 = v & -8 | 4;
                    int v4 = 0;
                    while(v1 < v2) {
                        v1 = zzdr.zza(arr_b, v1, zzdq0);
                        v4 = zzdq0.zza;
                        if(v4 == v3) {
                            break;
                        }
                        v1 = zzdr.zza(v4, arr_b, v1, v2, zzdq0);
                    }
                    if(v1 > v2 || v4 != v3) {
                        throw zzfn.zzg();
                    }
                    return v1;
                }
                default: {
                    throw zzfn.zzd();
                }
            }
        }
        return v1 + 4;
    }

    static int zza(int v, byte[] arr_b, int v1, int v2, zzfk zzfk0, zzdq zzdq0) {
        int v3 = zzdr.zza(arr_b, v1, zzdq0);
        ((zzff)zzfk0).zzd(zzdq0.zza);
        while(v3 < v2) {
            int v4 = zzdr.zza(arr_b, v3, zzdq0);
            if(v != zzdq0.zza) {
                break;
            }
            v3 = zzdr.zza(arr_b, v4, zzdq0);
            ((zzff)zzfk0).zzd(zzdq0.zza);
        }
        return v3;
    }

    static int zza(int v, byte[] arr_b, int v1, int v2, zzhx zzhx0, zzdq zzdq0) throws zzfn {
        if(v >>> 3 == 0) {
            throw zzfn.zzd();
        }
        if((v & 7) != 5) {
            switch(v & 7) {
                case 0: {
                    int v3 = zzdr.zzb(arr_b, v1, zzdq0);
                    zzhx0.zza(v, zzdq0.zzb);
                    return v3;
                }
                case 1: {
                    zzhx0.zza(v, zzdr.zzb(arr_b, v1));
                    return v1 + 8;
                }
                case 2: {
                    int v4 = zzdr.zza(arr_b, v1, zzdq0);
                    int v5 = zzdq0.zza;
                    if(v5 < 0) {
                        throw zzfn.zzb();
                    }
                    if(v5 > arr_b.length - v4) {
                        throw zzfn.zza();
                    }
                    if(v5 == 0) {
                        zzhx0.zza(v, zzdv.zza);
                        return v4;
                    }
                    zzhx0.zza(v, zzdv.zza(arr_b, v4, v5));
                    return v4 + v5;
                }
                case 3: {
                    zzhx zzhx1 = zzhx.zzb();
                    int v6 = v & -8 | 4;
                    int v7 = 0;
                    while(v1 < v2) {
                        int v8 = zzdr.zza(arr_b, v1, zzdq0);
                        int v9 = zzdq0.zza;
                        if(v9 == v6) {
                            v7 = v9;
                            v1 = v8;
                            if(true) {
                                break;
                            }
                        }
                        else {
                            v7 = v9;
                            v1 = zzdr.zza(v9, arr_b, v8, v2, zzhx1, zzdq0);
                        }
                    }
                    if(v1 > v2 || v7 != v6) {
                        throw zzfn.zzg();
                    }
                    zzhx0.zza(v, zzhx1);
                    return v1;
                }
                default: {
                    throw zzfn.zzd();
                }
            }
        }
        zzhx0.zza(v, zzdr.zza(arr_b, v1));
        return v1 + 4;
    }

    static int zza(int v, byte[] arr_b, int v1, zzdq zzdq0) {
        int v2 = arr_b[v1];
        if(v2 >= 0) {
            zzdq0.zza = v & 0x7F | v2 << 7;
            return v1 + 1;
        }
        int v3 = v & 0x7F | (v2 & 0x7F) << 7;
        int v4 = arr_b[v1 + 1];
        if(v4 >= 0) {
            zzdq0.zza = v3 | v4 << 14;
            return v1 + 2;
        }
        int v5 = v3 | (v4 & 0x7F) << 14;
        int v6 = arr_b[v1 + 2];
        if(v6 >= 0) {
            zzdq0.zza = v5 | v6 << 21;
            return v1 + 3;
        }
        int v7 = v5 | (v6 & 0x7F) << 21;
        int v8 = v1 + 4;
        int v9 = arr_b[v1 + 3];
        if(v9 >= 0) {
            zzdq0.zza = v7 | v9 << 28;
            return v8;
        }
        while(arr_b[v8] < 0) {
            ++v8;
        }
        zzdq0.zza = v7 | (v9 & 0x7F) << 28;
        return v8 + 1;
    }

    static int zza(zzhc zzhc0, int v, byte[] arr_b, int v1, int v2, zzfk zzfk0, zzdq zzdq0) throws IOException {
        int v3 = zzdr.zza(zzhc0, arr_b, v1, v2, zzdq0);
        zzfk0.add(zzdq0.zzc);
        while(v3 < v2) {
            int v4 = zzdr.zza(arr_b, v3, zzdq0);
            if(v != zzdq0.zza) {
                break;
            }
            v3 = zzdr.zza(zzhc0, arr_b, v4, v2, zzdq0);
            zzfk0.add(zzdq0.zzc);
        }
        return v3;
    }

    static int zza(zzhc zzhc0, byte[] arr_b, int v, int v1, int v2, zzdq zzdq0) throws IOException {
        Object object0 = ((zzgr)zzhc0).zza();
        int v3 = ((zzgr)zzhc0).zza(object0, arr_b, v, v1, v2, zzdq0);
        ((zzgr)zzhc0).zzc(object0);
        zzdq0.zzc = object0;
        return v3;
    }

    static int zza(zzhc zzhc0, byte[] arr_b, int v, int v1, zzdq zzdq0) throws IOException {
        int v4;
        int v2 = arr_b[v];
        if(v2 < 0) {
            int v3 = zzdr.zza(v2, arr_b, v + 1, zzdq0);
            v2 = zzdq0.zza;
            v4 = v3;
        }
        else {
            v4 = v + 1;
        }
        if(v2 < 0 || v2 > v1 - v4) {
            throw zzfn.zza();
        }
        Object object0 = zzhc0.zza();
        int v5 = v2 + v4;
        zzhc0.zza(object0, arr_b, v4, v5, zzdq0);
        zzhc0.zzc(object0);
        zzdq0.zzc = object0;
        return v5;
    }

    static int zza(byte[] arr_b, int v) {
        return (arr_b[v + 3] & 0xFF) << 24 | (arr_b[v] & 0xFF | (arr_b[v + 1] & 0xFF) << 8 | (arr_b[v + 2] & 0xFF) << 16);
    }

    static int zza(byte[] arr_b, int v, zzdq zzdq0) {
        int v1 = arr_b[v];
        if(v1 >= 0) {
            zzdq0.zza = v1;
            return v + 1;
        }
        return zzdr.zza(v1, arr_b, v + 1, zzdq0);
    }

    static int zza(byte[] arr_b, int v, zzfk zzfk0, zzdq zzdq0) throws IOException {
        int v1 = zzdr.zza(arr_b, v, zzdq0);
        int v2 = zzdq0.zza + v1;
        while(v1 < v2) {
            v1 = zzdr.zza(arr_b, v1, zzdq0);
            ((zzff)zzfk0).zzd(zzdq0.zza);
        }
        if(v1 != v2) {
            throw zzfn.zza();
        }
        return v1;
    }

    static int zzb(byte[] arr_b, int v, zzdq zzdq0) {
        long v1 = (long)arr_b[v];
        if(v1 >= 0L) {
            zzdq0.zzb = v1;
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
        zzdq0.zzb = v4;
        return v2;
    }

    static long zzb(byte[] arr_b, int v) {
        return (((long)arr_b[v + 7]) & 0xFFL) << 56 | (((long)arr_b[v]) & 0xFFL | (((long)arr_b[v + 1]) & 0xFFL) << 8 | (((long)arr_b[v + 2]) & 0xFFL) << 16 | (((long)arr_b[v + 3]) & 0xFFL) << 24 | (((long)arr_b[v + 4]) & 0xFFL) << 0x20 | (((long)arr_b[v + 5]) & 0xFFL) << 40 | (((long)arr_b[v + 6]) & 0xFFL) << 0x30);
    }

    static double zzc(byte[] arr_b, int v) {
        return Double.longBitsToDouble(zzdr.zzb(arr_b, v));
    }

    static int zzc(byte[] arr_b, int v, zzdq zzdq0) throws zzfn {
        int v1 = zzdr.zza(arr_b, v, zzdq0);
        int v2 = zzdq0.zza;
        if(v2 < 0) {
            throw zzfn.zzb();
        }
        if(v2 == 0) {
            zzdq0.zzc = "";
            return v1;
        }
        zzdq0.zzc = new String(arr_b, v1, v2, zzfe.zza);
        return v1 + v2;
    }

    static float zzd(byte[] arr_b, int v) {
        return Float.intBitsToFloat(zzdr.zza(arr_b, v));
    }

    static int zzd(byte[] arr_b, int v, zzdq zzdq0) throws zzfn {
        int v1 = zzdr.zza(arr_b, v, zzdq0);
        int v2 = zzdq0.zza;
        if(v2 < 0) {
            throw zzfn.zzb();
        }
        if(v2 == 0) {
            zzdq0.zzc = "";
            return v1;
        }
        zzdq0.zzc = zzid.zzb(arr_b, v1, v2);
        return v1 + v2;
    }

    static int zze(byte[] arr_b, int v, zzdq zzdq0) throws zzfn {
        int v1 = zzdr.zza(arr_b, v, zzdq0);
        int v2 = zzdq0.zza;
        if(v2 < 0) {
            throw zzfn.zzb();
        }
        if(v2 > arr_b.length - v1) {
            throw zzfn.zza();
        }
        if(v2 == 0) {
            zzdq0.zzc = zzdv.zza;
            return v1;
        }
        zzdq0.zzc = zzdv.zza(arr_b, v1, v2);
        return v1 + v2;
    }
}

