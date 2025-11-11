package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

final class zzeci extends zzecf {
    @Override  // com.google.android.gms.internal.ads.zzecf
    final int zzb(int v, byte[] arr_b, int v1, int v2) {
        int v7;
        int v3;
        if(v == 0) {
            v3 = v1;
        }
        else {
            if(v1 >= v2) {
                return v;
            }
            if(((byte)v) < 0xFFFFFFE0) {
                if(((byte)v) < -62) {
                    return -1;
                }
                v3 = v1 + 1;
                if(arr_b[v1] > -65) {
                    return -1;
                }
            }
            else if(((byte)v) < -16) {
                int v4 = (byte)(~(v >> 8));
                if(v4 == 0) {
                    int v5 = arr_b[v1];
                    if(v1 + 1 >= v2) {
                        return zzece.zzaq(((byte)v), v5);
                    }
                    ++v1;
                    v4 = v5;
                }
                if(v4 > -65 || ((byte)v) == 0xFFFFFFE0 && v4 < 0xFFFFFFA0 || ((byte)v) == -19 && v4 >= 0xFFFFFFA0) {
                    return -1;
                }
                v3 = v1 + 1;
                if(arr_b[v1] > -65) {
                    return -1;
                }
            }
            else {
                int v6 = (byte)(~(v >> 8));
                if(v6 == 0) {
                    v6 = arr_b[v1];
                    if(v1 + 1 >= v2) {
                        return zzece.zzaq(((byte)v), v6);
                    }
                    ++v1;
                    v7 = 0;
                }
                else {
                    v7 = (byte)(v >> 16);
                }
                if(v7 == 0) {
                    int v8 = arr_b[v1];
                    if(v1 + 1 >= v2) {
                        return zzece.zzj(((byte)v), v6, v8);
                    }
                    ++v1;
                    v7 = v8;
                }
                if(v6 > -65 || (((byte)v) << 28) + (v6 + 0x70) >> 30 != 0 || v7 > -65) {
                    return -1;
                }
                v3 = v1 + 1;
                if(arr_b[v1] > -65) {
                    return -1;
                }
            }
        }
        while(v3 < v2 && arr_b[v3] >= 0) {
            ++v3;
        }
        if(v3 >= v2) {
            return 0;
        }
        while(true) {
            if(v3 >= v2) {
                return 0;
            }
            int v9 = v3 + 1;
            int v10 = arr_b[v3];
            if(v10 < 0) {
                if(v10 < 0xFFFFFFE0) {
                    if(v9 >= v2) {
                        return v10;
                    }
                    if(v10 >= -62) {
                        v3 = v9 + 1;
                        if(arr_b[v9] > -65) {
                            return -1;
                        }
                        continue;
                    }
                }
                else if(v10 < -16) {
                    if(v9 >= v2 - 1) {
                        return zzece.zzn(arr_b, v9, v2);
                    }
                    int v11 = arr_b[v9];
                    if(v11 <= -65 && (v10 != 0xFFFFFFE0 || v11 >= 0xFFFFFFA0) && (v10 != -19 || v11 < 0xFFFFFFA0)) {
                        v3 = v9 + 2;
                        if(arr_b[v9 + 1] > -65) {
                            return -1;
                        }
                        continue;
                    }
                }
                else {
                    if(v9 >= v2 - 2) {
                        return zzece.zzn(arr_b, v9, v2);
                    }
                    int v12 = arr_b[v9];
                    if(v12 <= -65 && (v10 << 28) + (v12 + 0x70) >> 30 == 0 && arr_b[v9 + 1] <= -65 && arr_b[v9 + 2] <= -65) {
                        v3 = v9 + 3;
                        continue;
                    }
                }
                return -1;
            }
            v3 = v9;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzecf
    final int zzb(CharSequence charSequence0, byte[] arr_b, int v, int v1) {
        int v2 = charSequence0.length();
        int v3 = v1 + v;
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            int v5 = v4 + v;
            if(v5 >= v3) {
                break;
            }
            int v6 = charSequence0.charAt(v4);
            if(v6 >= 0x80) {
                break;
            }
            arr_b[v5] = (byte)v6;
        }
        if(v4 == v2) {
            return v + v2;
        }
        int v7 = v + v4;
        while(v4 < v2) {
            int v8 = charSequence0.charAt(v4);
            if(v8 < 0x80 && v7 < v3) {
                arr_b[v7] = (byte)v8;
                ++v7;
            }
            else if(v8 < 0x800 && v7 <= v3 - 2) {
                int v9 = v7 + 1;
                arr_b[v7] = (byte)(v8 >>> 6 | 960);
                v7 = v9 + 1;
                arr_b[v9] = (byte)(v8 & 0x3F | 0x80);
            }
            else if(v8 >= 0xD800 && 0xDFFF >= v8 || v7 > v3 - 3) {
                if(v7 > v3 - 4) {
                    goto label_48;
                }
                if(v4 + 1 == charSequence0.length()) {
                    throw new zzech(v4 - 1, v2);
                }
                int v10 = charSequence0.charAt(v4 + 1);
                if(!Character.isSurrogatePair(((char)v8), ((char)v10))) {
                    goto label_46;
                }
                int v11 = Character.toCodePoint(((char)v8), ((char)v10));
                arr_b[v7] = (byte)(v11 >>> 18 | 0xF0);
                int v12 = v7 + 2;
                arr_b[v7 + 1] = (byte)(v11 >>> 12 & 0x3F | 0x80);
                arr_b[v12] = (byte)(v11 >>> 6 & 0x3F | 0x80);
                v7 = v12 + 2;
                arr_b[v12 + 1] = (byte)(v11 & 0x3F | 0x80);
                ++v4;
            }
            else {
                arr_b[v7] = (byte)(v8 >>> 12 | 480);
                arr_b[v7 + 1] = (byte)(v8 >>> 6 & 0x3F | 0x80);
                arr_b[v7 + 2] = (byte)(v8 & 0x3F | 0x80);
                v7 += 3;
            }
            ++v4;
            continue;
        label_46:
            ++v4;
            throw new zzech(v4 - 1, v2);
        label_48:
            if(!(0xD800 <= v8 && v8 <= 0xDFFF && (v4 + 1 == charSequence0.length() || !Character.isSurrogatePair(((char)v8), charSequence0.charAt(v4 + 1))))) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + ((char)v8) + " at index " + v7);
            }
            throw new zzech(v4, v2);
        }
        return v7;
    }

    @Override  // com.google.android.gms.internal.ads.zzecf
    final void zzb(CharSequence charSequence0, ByteBuffer byteBuffer0) {
        zzeci.zzc(charSequence0, byteBuffer0);
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
            int v4 = arr_b[v];
            if(!zzecg.zze(((byte)v4))) {
                break;
            }
            ++v;
            zzecg.zza(((byte)v4), arr_c, v3);
        }
        int v5 = v3;
        while(v < v2) {
            int v6 = v + 1;
            int v7 = arr_b[v];
            if(zzecg.zze(((byte)v7))) {
                int v8 = v5 + 1;
                zzecg.zza(((byte)v7), arr_c, v5);
                while(v6 < v2) {
                    int v9 = arr_b[v6];
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
                    zzecg.zza(((byte)v7), arr_b[v6], arr_c, v5);
                    v = v6 + 1;
                    ++v5;
                    continue;
                }
            }
            else if(!zzecg.zzg(((byte)v7))) {
                if(v6 < v2 - 2) {
                    zzecg.zza(((byte)v7), arr_b[v6], arr_b[v6 + 1], arr_b[v6 + 2], arr_c, v5);
                    v = v6 + 3;
                    v5 += 2;
                    continue;
                }
            }
            else if(v6 < v2 - 1) {
                zzecg.zza(((byte)v7), arr_b[v6], arr_b[v6 + 1], arr_c, v5);
                v = v6 + 2;
                ++v5;
                continue;
            }
            throw zzdzh.zzbdq();
        }
        return new String(arr_c, 0, v5);
    }
}

