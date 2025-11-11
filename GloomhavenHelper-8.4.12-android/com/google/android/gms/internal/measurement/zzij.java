package com.google.android.gms.internal.measurement;

final class zzij extends zzie {
    private static int zza(byte[] arr_b, int v, long v1, int v2) {
        switch(v2) {
            case 0: {
                return zzid.zzb(v);
            }
            case 1: {
                return zzid.zzb(v, zzia.zza(arr_b, v1));
            }
            case 2: {
                return zzid.zzb(v, zzia.zza(arr_b, v1), zzia.zza(arr_b, v1 + 1L));
            }
            default: {
                throw new AssertionError();
            }
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzie
    final int zza(int v, byte[] arr_b, int v1, int v2) {
        long v9;
        int v4;
        if((v1 | v2 | arr_b.length - v2) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", ((int)arr_b.length), v1, v2));
        }
        int v3 = (int)(((long)v2) - ((long)v1));
        if(v3 < 16) {
            v4 = 0;
        }
        else {
            long v5 = (long)v1;
            v4 = 0;
            while(true) {
                if(v4 >= v3) {
                    v4 = v3;
                    break;
                }
                if(zzia.zza(arr_b, v5) < 0) {
                    break;
                }
                ++v4;
                ++v5;
            }
        }
        int v6 = v3 - v4;
        long v7 = ((long)v1) + ((long)v4);
        while(true) {
            int v8 = 0;
            while(true) {
                if(v6 > 0) {
                    v9 = v7 + 1L;
                    v8 = zzia.zza(arr_b, v7);
                    if(v8 >= 0) {
                        --v6;
                        v7 = v9;
                        continue;
                    }
                    else {
                        break;
                    }
                }
                v9 = v7;
                break;
            }
            if(v6 == 0) {
                return 0;
            }
            if(v8 < 0xFFFFFFE0) {
                if(v6 - 1 == 0) {
                    return v8;
                }
                v6 -= 2;
                if(v8 >= -62 && zzia.zza(arr_b, v9) <= -65) {
                    v7 = v9 + 1L;
                    continue;
                }
                return -1;
            }
            if(v8 < -16) {
                if(v6 - 1 < 2) {
                    return zzij.zza(arr_b, v8, v9, v6 - 1);
                }
                v6 -= 3;
                int v10 = zzia.zza(arr_b, v9);
                if(v10 <= -65 && (v8 != 0xFFFFFFE0 || v10 >= 0xFFFFFFA0) && (v8 != -19 || v10 < 0xFFFFFFA0) && zzia.zza(arr_b, v9 + 1L) <= -65) {
                    v7 = v9 + 2L;
                    continue;
                }
                return -1;
            }
            if(v6 - 1 < 3) {
                return zzij.zza(arr_b, v8, v9, v6 - 1);
            }
            v6 -= 4;
            int v11 = zzia.zza(arr_b, v9);
            if(v11 > -65 || (v8 << 28) + (v11 + 0x70) >> 30 != 0 || zzia.zza(arr_b, v9 + 1L) > -65 || zzia.zza(arr_b, v9 + 2L) > -65) {
                break;
            }
            v7 = v9 + 3L;
        }
        return -1;
    }

    @Override  // com.google.android.gms.internal.measurement.zzie
    final int zza(CharSequence charSequence0, byte[] arr_b, int v, int v1) {
        int v9;
        long v2 = (long)v;
        long v3 = ((long)v1) + v2;
        int v4 = charSequence0.length();
        if(v4 > v1 || arr_b.length - v1 < v) {
            throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence0.charAt(v4 - 1) + " at index " + (v + v1));
        }
        int v5 = 0;
        while(v5 < v4) {
            int v6 = charSequence0.charAt(v5);
            if(v6 >= 0x80) {
                break;
            }
            zzia.zza(arr_b, v2, ((byte)v6));
            ++v5;
            ++v2;
        }
        if(v5 == v4) {
            return (int)v2;
        }
        while(v5 < v4) {
            int v7 = charSequence0.charAt(v5);
            if(v7 < 0x80 && v2 < v3) {
                zzia.zza(arr_b, v2, ((byte)v7));
                ++v2;
            }
            else if(v7 < 0x800 && v2 <= v3 - 2L) {
                long v8 = v2 + 1L;
                zzia.zza(arr_b, v2, ((byte)(v7 >>> 6 | 960)));
                v2 = v8 + 1L;
                zzia.zza(arr_b, v8, ((byte)(v7 & 0x3F | 0x80)));
            }
            else if(v7 >= 0xD800 && 0xDFFF >= v7 || v2 > v3 - 3L) {
                if(v2 > v3 - 4L) {
                    goto label_49;
                }
                v9 = v5 + 1;
                if(v9 == v4) {
                    goto label_47;
                }
                int v10 = charSequence0.charAt(v9);
                if(!Character.isSurrogatePair(((char)v7), ((char)v10))) {
                    throw new zzig(v9 - 1, v4);
                }
                int v11 = Character.toCodePoint(((char)v7), ((char)v10));
                zzia.zza(arr_b, v2, ((byte)(v11 >>> 18 | 0xF0)));
                long v12 = v2 + 2L;
                zzia.zza(arr_b, v2 + 1L, ((byte)(v11 >>> 12 & 0x3F | 0x80)));
                zzia.zza(arr_b, v12, ((byte)(v11 >>> 6 & 0x3F | 0x80)));
                v2 = v12 + 2L;
                zzia.zza(arr_b, v12 + 1L, ((byte)(v11 & 0x3F | 0x80)));
                v5 = v9;
            }
            else {
                zzia.zza(arr_b, v2, ((byte)(v7 >>> 12 | 480)));
                zzia.zza(arr_b, v2 + 1L, ((byte)(v7 >>> 6 & 0x3F | 0x80)));
                zzia.zza(arr_b, v2 + 2L, ((byte)(v7 & 0x3F | 0x80)));
                v2 += 3L;
            }
            ++v5;
            continue;
        label_47:
            v9 = v5;
            throw new zzig(v9 - 1, v4);
        label_49:
            if(!(0xD800 <= v7 && v7 <= 0xDFFF && (v5 + 1 == v4 || !Character.isSurrogatePair(((char)v7), charSequence0.charAt(v5 + 1))))) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + ((char)v7) + " at index " + v2);
            }
            throw new zzig(v5, v4);
        }
        return (int)v2;
    }

    @Override  // com.google.android.gms.internal.measurement.zzie
    final String zzb(byte[] arr_b, int v, int v1) throws zzfn {
        if((v | v1 | arr_b.length - v - v1) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", ((int)arr_b.length), v, v1));
        }
        int v2 = v + v1;
        char[] arr_c = new char[v1];
        int v3;
        for(v3 = 0; v < v2; ++v3) {
            int v4 = zzia.zza(arr_b, ((long)v));
            if(!zzif.zzd(((byte)v4))) {
                break;
            }
            ++v;
            zzif.zzb(((byte)v4), arr_c, v3);
        }
        int v5 = v3;
        while(v < v2) {
            int v6 = v + 1;
            int v7 = zzia.zza(arr_b, ((long)v));
            if(zzif.zzd(((byte)v7))) {
                int v8 = v5 + 1;
                zzif.zzb(((byte)v7), arr_c, v5);
                while(v6 < v2) {
                    int v9 = zzia.zza(arr_b, ((long)v6));
                    if(!zzif.zzd(((byte)v9))) {
                        break;
                    }
                    ++v6;
                    zzif.zzb(((byte)v9), arr_c, v8);
                    ++v8;
                }
                v = v6;
                v5 = v8;
                continue;
            }
            if(zzif.zze(((byte)v7))) {
                if(v6 < v2) {
                    zzif.zzb(((byte)v7), zzia.zza(arr_b, ((long)v6)), arr_c, v5);
                    v = v6 + 1;
                    ++v5;
                    continue;
                }
            }
            else if(!zzif.zzf(((byte)v7))) {
                if(v6 < v2 - 2) {
                    zzif.zzb(((byte)v7), zzia.zza(arr_b, ((long)v6)), zzia.zza(arr_b, ((long)(v6 + 1))), zzia.zza(arr_b, ((long)(v6 + 2))), arr_c, v5);
                    v = v6 + 3;
                    v5 += 2;
                    continue;
                }
            }
            else if(v6 < v2 - 1) {
                zzif.zzb(((byte)v7), zzia.zza(arr_b, ((long)v6)), zzia.zza(arr_b, ((long)(v6 + 1))), arr_c, v5);
                v = v6 + 2;
                ++v5;
                continue;
            }
            throw zzfn.zzh();
        }
        return new String(arr_c, 0, v5);
    }
}

