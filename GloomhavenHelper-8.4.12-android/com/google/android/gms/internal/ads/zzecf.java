package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

abstract class zzecf {
    abstract int zzb(int arg1, byte[] arg2, int arg3, int arg4);

    abstract int zzb(CharSequence arg1, byte[] arg2, int arg3, int arg4);

    abstract void zzb(CharSequence arg1, ByteBuffer arg2);

    static void zzc(CharSequence charSequence0, ByteBuffer byteBuffer0) {
        int v8;
        int v = charSequence0.length();
        int v1 = byteBuffer0.position();
        int v2 = 0;
        try {
            while(v2 < v) {
                int v3 = charSequence0.charAt(v2);
                if(v3 >= 0x80) {
                    break;
                }
                byteBuffer0.put(v1 + v2, ((byte)v3));
                ++v2;
            }
            if(v2 == v) {
                byteBuffer0.position(v1 + v2);
                return;
            }
            v1 += v2;
            while(true) {
                if(v2 >= v) {
                    byteBuffer0.position(v1);
                    return;
                }
                int v4 = charSequence0.charAt(v2);
                if(v4 < 0x80) {
                    byteBuffer0.put(v1, ((byte)v4));
                }
                else if(v4 < 0x800) {
                    try {
                        byteBuffer0.put(v1, ((byte)(v4 >>> 6 | 0xC0)));
                        byteBuffer0.put(v1 + 1, ((byte)(v4 & 0x3F | 0x80)));
                        ++v1;
                    }
                    catch(IndexOutOfBoundsException unused_ex) {
                        ++v1;
                        break;
                    }
                }
                else if(v4 < 0xD800 || 0xDFFF < v4) {
                    try {
                        v8 = v1 + 1;
                        byteBuffer0.put(v1, ((byte)(v4 >>> 12 | 0xE0)));
                        v1 = v8 + 1;
                    }
                    catch(IndexOutOfBoundsException unused_ex) {
                        v1 = v8;
                        break;
                    }
                    byteBuffer0.put(v8, ((byte)(v4 >>> 6 & 0x3F | 0x80)));
                    byteBuffer0.put(v1, ((byte)(v4 & 0x3F | 0x80)));
                }
                else {
                    if(v2 + 1 == v) {
                        throw new zzech(v2, v);
                    }
                    try {
                        int v5 = charSequence0.charAt(v2 + 1);
                        if(Character.isSurrogatePair(((char)v4), ((char)v5))) {
                            int v6 = Character.toCodePoint(((char)v4), ((char)v5));
                            int v7 = v1 + 1;
                            try {
                                byteBuffer0.put(v1, ((byte)(v6 >>> 18 | 0xF0)));
                                v1 = v7 + 1;
                            }
                            catch(IndexOutOfBoundsException unused_ex) {
                                v1 = v7;
                                ++v2;
                                break;
                            }
                            try {
                                byteBuffer0.put(v7, ((byte)(v6 >>> 12 & 0x3F | 0x80)));
                                goto label_42;
                            }
                            catch(IndexOutOfBoundsException unused_ex) {
                            }
                            ++v2;
                            break;
                            try {
                            label_42:
                                byteBuffer0.put(v1, ((byte)(v6 >>> 6 & 0x3F | 0x80)));
                                byteBuffer0.put(v1 + 1, ((byte)(v6 & 0x3F | 0x80)));
                                ++v1;
                                ++v2;
                                goto label_62;
                            }
                            catch(IndexOutOfBoundsException unused_ex) {
                                ++v1;
                                ++v2;
                                break;
                            }
                        }
                        else {
                            ++v2;
                        }
                        throw new zzech(v2, v);
                    }
                    catch(IndexOutOfBoundsException unused_ex) {
                    }
                    ++v2;
                    break;
                }
            label_62:
                ++v2;
                ++v1;
            }
        }
        catch(IndexOutOfBoundsException unused_ex) {
        }
        throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence0.charAt(v2) + " at index " + (byteBuffer0.position() + Math.max(v2, v1 - byteBuffer0.position() + 1)));
    }

    final boolean zzm(byte[] arr_b, int v, int v1) {
        return this.zzb(0, arr_b, v, v1) == 0;
    }

    abstract String zzo(byte[] arg1, int arg2, int arg3) throws zzdzh;
}

