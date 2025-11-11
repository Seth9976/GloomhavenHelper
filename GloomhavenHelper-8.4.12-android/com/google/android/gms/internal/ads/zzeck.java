package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

final class zzeck extends zzecf {
    private static int zza(byte[] arr_b, int v, long v1, int v2) {
        switch(v2) {
            case 0: {
                return zzece.zzgz(v);
            }
            case 1: {
                return zzece.zzaq(v, zzecb.zza(arr_b, v1));
            }
            case 2: {
                return zzece.zzj(v, zzecb.zza(arr_b, v1), zzecb.zza(arr_b, v1 + 1L));
            }
            default: {
                throw new AssertionError();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecf
    final int zzb(int v, byte[] arr_b, int v1, int v2) {
        int v16;
        long v15;
        int v9;
        int v7;
        long v4;
        if((v1 | v2 | arr_b.length - v2) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", ((int)arr_b.length), v1, v2));
        }
        long v3 = (long)v1;
        if(v == 0) {
            v4 = v3;
        }
        else {
            if(v3 >= ((long)v2)) {
                return v;
            }
            if(((byte)v) < 0xFFFFFFE0) {
                if(((byte)v) < -62) {
                    return -1;
                }
                v4 = v3 + 1L;
                if(zzecb.zza(arr_b, v3) > -65) {
                    return -1;
                }
            }
            else if(((byte)v) < -16) {
                int v5 = (byte)(~(v >> 8));
                if(v5 == 0) {
                    v5 = zzecb.zza(arr_b, v3);
                    if(v3 + 1L >= ((long)v2)) {
                        return zzece.zzaq(((byte)v), v5);
                    }
                    ++v3;
                }
                if(v5 > -65 || ((byte)v) == 0xFFFFFFE0 && v5 < 0xFFFFFFA0 || ((byte)v) == -19 && v5 >= 0xFFFFFFA0) {
                    return -1;
                }
                v4 = v3 + 1L;
                if(zzecb.zza(arr_b, v3) > -65) {
                    return -1;
                }
            }
            else {
                int v6 = (byte)(~(v >> 8));
                if(v6 == 0) {
                    v6 = zzecb.zza(arr_b, v3);
                    if(v3 + 1L >= ((long)v2)) {
                        return zzece.zzaq(((byte)v), v6);
                    }
                    ++v3;
                    v7 = 0;
                }
                else {
                    v7 = (byte)(v >> 16);
                }
                if(v7 == 0) {
                    v7 = zzecb.zza(arr_b, v3);
                    if(v3 + 1L >= ((long)v2)) {
                        return zzece.zzj(((byte)v), v6, v7);
                    }
                    ++v3;
                }
                if(v6 <= -65 && (((byte)v) << 28) + (v6 + 0x70) >> 30 == 0 && v7 <= -65 && zzecb.zza(arr_b, v3) <= -65) {
                    v4 = v3 + 1L;
                    goto label_40;
                }
                return -1;
            }
        }
    label_40:
        int v8 = (int)(((long)v2) - v4);
        if(v8 < 16) {
            v9 = 0;
        }
        else {
            long v10 = v4;
            v9 = 0;
            while(true) {
                if(v9 >= v8) {
                    v9 = v8;
                    break;
                }
                if(zzecb.zza(arr_b, v10) < 0) {
                    break;
                }
                ++v9;
                ++v10;
            }
        }
        int v11 = v8 - v9;
        long v12 = v4 + ((long)v9);
        while(true) {
            long v13 = v12;
            int v14 = 0;
            while(true) {
                if(v11 > 0) {
                    v15 = v13 + 1L;
                    v16 = zzecb.zza(arr_b, v13);
                    if(v16 >= 0) {
                        --v11;
                        v14 = v16;
                        v13 = v15;
                        continue;
                    }
                    else {
                        break;
                    }
                }
                v16 = v14;
                v15 = v13;
                break;
            }
            if(v11 == 0) {
                return 0;
            }
            if(v16 < 0xFFFFFFE0) {
                if(v11 - 1 == 0) {
                    return v16;
                }
                v11 -= 2;
                if(v16 >= -62 && zzecb.zza(arr_b, v15) <= -65) {
                    v12 = v15 + 1L;
                    continue;
                }
                return -1;
            }
            if(v16 < -16) {
                if(v11 - 1 < 2) {
                    return zzeck.zza(arr_b, v16, v15, v11 - 1);
                }
                v11 -= 3;
                int v17 = zzecb.zza(arr_b, v15);
                if(v17 <= -65 && (v16 != 0xFFFFFFE0 || v17 >= 0xFFFFFFA0) && (v16 != -19 || v17 < 0xFFFFFFA0)) {
                    v12 = v15 + 2L;
                    if(zzecb.zza(arr_b, v15 + 1L) > -65) {
                        return -1;
                    }
                    continue;
                }
                return -1;
            }
            if(v11 - 1 < 3) {
                return zzeck.zza(arr_b, v16, v15, v11 - 1);
            }
            v11 -= 4;
            int v18 = zzecb.zza(arr_b, v15);
            if(v18 > -65 || (v16 << 28) + (v18 + 0x70) >> 30 != 0 || zzecb.zza(arr_b, v15 + 1L) > -65 || zzecb.zza(arr_b, v15 + 2L) > -65) {
                break;
            }
            v12 = v15 + 3L;
        }
        return -1;
    }

    @Override  // com.google.android.gms.internal.ads.zzecf
    final int zzb(CharSequence charSequence0, byte[] arr_b, int v, int v1) {
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
            zzecb.zza(arr_b, v2, ((byte)v6));
            ++v5;
            ++v2;
        }
        if(v5 == v4) {
            return (int)v2;
        }
        while(v5 < v4) {
            int v7 = charSequence0.charAt(v5);
            if(v7 < 0x80 && v2 < v3) {
                zzecb.zza(arr_b, v2, ((byte)v7));
                ++v2;
            }
            else if(v7 < 0x800 && v2 <= v3 - 2L) {
                long v8 = v2 + 1L;
                zzecb.zza(arr_b, v2, ((byte)(v7 >>> 6 | 960)));
                v2 = v8 + 1L;
                zzecb.zza(arr_b, v8, ((byte)(v7 & 0x3F | 0x80)));
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
                    throw new zzech(v9 - 1, v4);
                }
                int v11 = Character.toCodePoint(((char)v7), ((char)v10));
                zzecb.zza(arr_b, v2, ((byte)(v11 >>> 18 | 0xF0)));
                long v12 = v2 + 2L;
                zzecb.zza(arr_b, v2 + 1L, ((byte)(v11 >>> 12 & 0x3F | 0x80)));
                zzecb.zza(arr_b, v12, ((byte)(v11 >>> 6 & 0x3F | 0x80)));
                v2 = v12 + 2L;
                zzecb.zza(arr_b, v12 + 1L, ((byte)(v11 & 0x3F | 0x80)));
                v5 = v9;
            }
            else {
                zzecb.zza(arr_b, v2, ((byte)(v7 >>> 12 | 480)));
                zzecb.zza(arr_b, v2 + 1L, ((byte)(v7 >>> 6 & 0x3F | 0x80)));
                zzecb.zza(arr_b, v2 + 2L, ((byte)(v7 & 0x3F | 0x80)));
                v2 += 3L;
            }
            ++v5;
            continue;
        label_47:
            v9 = v5;
            throw new zzech(v9 - 1, v4);
        label_49:
            if(!(0xD800 <= v7 && v7 <= 0xDFFF && (v5 + 1 == v4 || !Character.isSurrogatePair(((char)v7), charSequence0.charAt(v5 + 1))))) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + ((char)v7) + " at index " + v2);
            }
            throw new zzech(v5, v4);
        }
        return (int)v2;
    }

    @Override  // com.google.android.gms.internal.ads.zzecf
    final void zzb(CharSequence charSequence0, ByteBuffer byteBuffer0) {
        long v7;
        long v = zzecb.zzn(byteBuffer0);
        long v1 = ((long)byteBuffer0.position()) + v;
        long v2 = ((long)byteBuffer0.limit()) + v;
        int v3 = charSequence0.length();
        if(((long)v3) > v2 - v1) {
            throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence0.charAt(v3 - 1) + " at index " + byteBuffer0.limit());
        }
        int v4 = 0;
        while(v4 < v3) {
            int v5 = charSequence0.charAt(v4);
            if(v5 >= 0x80) {
                break;
            }
            zzecb.zza(v1, ((byte)v5));
            ++v4;
            ++v1;
        }
        if(v4 == v3) {
            byteBuffer0.position(((int)(v1 - v)));
            return;
        }
        while(v4 < v3) {
            int v6 = charSequence0.charAt(v4);
            if(v6 < 0x80 && v1 < v2) {
                v7 = v1 + 1L;
                zzecb.zza(v1, ((byte)v6));
            }
            else if(v6 < 0x800 && v1 <= v2 - 2L) {
                zzecb.zza(v1, ((byte)(v6 >>> 6 | 960)));
                zzecb.zza(v1 + 1L, ((byte)(v6 & 0x3F | 0x80)));
                v7 = v1 + 2L;
            }
            else if(v6 >= 0xD800 && 0xDFFF >= v6 || v1 > v2 - 3L) {
                if(v1 > v2 - 4L) {
                    goto label_49;
                }
                if(v4 + 1 == v3) {
                    throw new zzech(v4 - 1, v3);
                }
                int v8 = charSequence0.charAt(v4 + 1);
                if(!Character.isSurrogatePair(((char)v6), ((char)v8))) {
                    goto label_47;
                }
                int v9 = Character.toCodePoint(((char)v6), ((char)v8));
                zzecb.zza(v1, ((byte)(v9 >>> 18 | 0xF0)));
                zzecb.zza(v1 + 1L, ((byte)(v9 >>> 12 & 0x3F | 0x80)));
                zzecb.zza(v1 + 2L, ((byte)(v9 >>> 6 & 0x3F | 0x80)));
                zzecb.zza(v1 + 3L, ((byte)(v9 & 0x3F | 0x80)));
                ++v4;
                v7 = v1 + 4L;
            }
            else {
                zzecb.zza(v1, ((byte)(v6 >>> 12 | 480)));
                zzecb.zza(v1 + 1L, ((byte)(v6 >>> 6 & 0x3F | 0x80)));
                zzecb.zza(v1 + 2L, ((byte)(v6 & 0x3F | 0x80)));
                v7 = v1 + 3L;
            }
            ++v4;
            v1 = v7;
            continue;
        label_47:
            ++v4;
            throw new zzech(v4 - 1, v3);
        label_49:
            if(!(0xD800 <= v6 && v6 <= 0xDFFF && (v4 + 1 == v3 || !Character.isSurrogatePair(((char)v6), charSequence0.charAt(v4 + 1))))) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + ((char)v6) + " at index " + v1);
            }
            throw new zzech(v4, v3);
        }
        byteBuffer0.position(((int)(v1 - v)));
        return;
    }

    @Override  // com.google.android.gms.internal.ads.zzecf
    final String zzo(byte[] arr_b, int v, int v1) throws zzdzh {
        if((v | v1 | arr_b.length - v - v1) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", ((int)arr_b.length), v, v1));
        }
        int v2 = v + v1;
        char[] arr_c = new char[v1];
        int v3;
        for(v3 = 0; v < v2; ++v3) {
            int v4 = zzecb.zza(arr_b, ((long)v));
            if(!zzecg.zze(((byte)v4))) {
                break;
            }
            ++v;
            zzecg.zza(((byte)v4), arr_c, v3);
        }
        int v5 = v3;
        while(v < v2) {
            int v6 = v + 1;
            int v7 = zzecb.zza(arr_b, ((long)v));
            if(zzecg.zze(((byte)v7))) {
                int v8 = v5 + 1;
                zzecg.zza(((byte)v7), arr_c, v5);
                while(v6 < v2) {
                    int v9 = zzecb.zza(arr_b, ((long)v6));
                    if(!zzecg.zze(((byte)v9))) {
                        break;
                    }
                    ++v6;
                    zzecg.zza(((byte)v9), arr_c, v8);
                    ++v8;
                }
                v = v6;
                v5 = v8;
                continue;
            }
            if(zzecg.zzf(((byte)v7))) {
                if(v6 < v2) {
                    zzecg.zza(((byte)v7), zzecb.zza(arr_b, ((long)v6)), arr_c, v5);
                    v = v6 + 1;
                    ++v5;
                    continue;
                }
            }
            else if(!zzecg.zzg(((byte)v7))) {
                if(v6 < v2 - 2) {
                    zzecg.zza(((byte)v7), zzecb.zza(arr_b, ((long)v6)), zzecb.zza(arr_b, ((long)(v6 + 1))), zzecb.zza(arr_b, ((long)(v6 + 2))), arr_c, v5);
                    v = v6 + 3;
                    v5 += 2;
                    continue;
                }
            }
            else if(v6 < v2 - 1) {
                zzecg.zza(((byte)v7), zzecb.zza(arr_b, ((long)v6)), zzecb.zza(arr_b, ((long)(v6 + 1))), arr_c, v5);
                v = v6 + 2;
                ++v5;
                continue;
            }
            throw zzdzh.zzbdq();
        }
        return new String(arr_c, 0, v5);
    }
}

