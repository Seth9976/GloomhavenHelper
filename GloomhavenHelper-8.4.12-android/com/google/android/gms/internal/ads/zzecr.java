package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzecr {
    private final ByteBuffer zzakt;
    private zzdyi zzhzs;
    private int zzhzt;

    private zzecr(ByteBuffer byteBuffer0) {
        this.zzakt = byteBuffer0;
        this.zzakt.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzecr(byte[] arr_b, int v, int v1) {
        this(ByteBuffer.wrap(arr_b, v, v1));
    }

    private static int zza(CharSequence charSequence0) {
        int v = charSequence0.length();
        int v1 = 0;
        int v2;
        for(v2 = 0; v2 < v && charSequence0.charAt(v2) < 0x80; ++v2) {
        }
        int v3 = v;
        while(v2 < v) {
            int v4 = charSequence0.charAt(v2);
            if(v4 < 0x800) {
                v3 += 0x7F - v4 >>> 0x1F;
                ++v2;
            }
            else {
                int v5 = charSequence0.length();
                while(v2 < v5) {
                    int v6 = charSequence0.charAt(v2);
                    if(v6 < 0x800) {
                        v1 += 0x7F - v6 >>> 0x1F;
                    }
                    else {
                        v1 += 2;
                        if(0xD800 <= v6 && v6 <= 0xDFFF) {
                            if(Character.codePointAt(charSequence0, v2) < 0x10000) {
                                throw new IllegalArgumentException("Unpaired surrogate at index " + v2);
                            }
                            ++v2;
                        }
                    }
                    ++v2;
                }
                v3 += v1;
                if(true) {
                    break;
                }
            }
        }
        if(v3 < v) {
            throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long)v3) + 0x100000000L));
        }
        return v3;
    }

    public final void zza(int v, zzeda zzeda0) throws IOException {
        this.zzab(v, 2);
        if(zzeda0.zzhnu < 0) {
            zzeda0.zzbda();
        }
        this.zzhc(zzeda0.zzhnu);
        zzeda0.zza(this);
    }

    public final void zza(int v, byte[] arr_b) throws IOException {
        this.zzab(3, 2);
        this.zzhc(arr_b.length);
        if(this.zzakt.remaining() < arr_b.length) {
            throw new zzecu(this.zzakt.position(), this.zzakt.limit());
        }
        this.zzakt.put(arr_b, 0, arr_b.length);
    }

    public final void zzab(int v, int v1) throws IOException {
        this.zzhc(v << 3 | v1);
    }

    public final void zzac(int v, int v1) throws IOException {
        this.zzab(v, 0);
        if(v1 >= 0) {
            this.zzhc(v1);
            return;
        }
        this.zzft(((long)v1));
    }

    public static int zzag(int v, int v1) {
        return zzecr.zzfz(v) + zzecr.zzga(v1);
    }

    public static int zzb(int v, zzeda zzeda0) {
        int v1 = zzeda0.zzbda();
        return zzecr.zzfz(v) + (zzecr.zzgh(v1) + v1);
    }

    public final void zzbcc() {
        if(this.zzakt.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", this.zzakt.remaining()));
        }
    }

    private static void zzd(CharSequence charSequence0, ByteBuffer byteBuffer0) {
        int v6;
        int v = 0;
        if(byteBuffer0.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        if(byteBuffer0.hasArray()) {
            try {
                byte[] arr_b = byteBuffer0.array();
                int v1 = byteBuffer0.arrayOffset() + byteBuffer0.position();
                int v2 = charSequence0.length();
                int v3 = byteBuffer0.remaining() + v1;
                while(v < v2) {
                    int v4 = v + v1;
                    if(v4 >= v3) {
                        break;
                    }
                    int v5 = charSequence0.charAt(v);
                    if(v5 >= 0x80) {
                        break;
                    }
                    arr_b[v4] = (byte)v5;
                    ++v;
                }
                if(v == v2) {
                    v6 = v1 + v2;
                }
                else {
                    v6 = v1 + v;
                    while(v < v2) {
                        int v7 = charSequence0.charAt(v);
                        if(v7 < 0x80 && v6 < v3) {
                            arr_b[v6] = (byte)v7;
                            ++v6;
                        }
                        else if(v7 < 0x800 && v6 <= v3 - 2) {
                            int v8 = v6 + 1;
                            arr_b[v6] = (byte)(v7 >>> 6 | 960);
                            v6 = v8 + 1;
                            arr_b[v8] = (byte)(v7 & 0x3F | 0x80);
                        }
                        else if(v7 >= 0xD800 && 0xDFFF >= v7 || v6 > v3 - 3) {
                            if(v6 > v3 - 4) {
                                throw new ArrayIndexOutOfBoundsException("Failed writing " + ((char)v7) + " at index " + v6);
                            }
                            if(v + 1 == charSequence0.length()) {
                                throw new IllegalArgumentException("Unpaired surrogate at index " + (v - 1));
                            }
                            int v9 = charSequence0.charAt(v + 1);
                            if(!Character.isSurrogatePair(((char)v7), ((char)v9))) {
                                ++v;
                                throw new IllegalArgumentException("Unpaired surrogate at index " + (v - 1));
                            }
                            int v10 = Character.toCodePoint(((char)v7), ((char)v9));
                            arr_b[v6] = (byte)(v10 >>> 18 | 0xF0);
                            int v11 = v6 + 2;
                            arr_b[v6 + 1] = (byte)(v10 >>> 12 & 0x3F | 0x80);
                            arr_b[v11] = (byte)(v10 >>> 6 & 0x3F | 0x80);
                            v6 = v11 + 2;
                            arr_b[v11 + 1] = (byte)(v10 & 0x3F | 0x80);
                            ++v;
                        }
                        else {
                            arr_b[v6] = (byte)(v7 >>> 12 | 480);
                            arr_b[v6 + 1] = (byte)(v7 >>> 6 & 0x3F | 0x80);
                            arr_b[v6 + 2] = (byte)(v7 & 0x3F | 0x80);
                            v6 += 3;
                        }
                        ++v;
                    }
                }
                byteBuffer0.position(v6 - byteBuffer0.arrayOffset());
                return;
            }
            catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException0) {
                BufferOverflowException bufferOverflowException0 = new BufferOverflowException();
                bufferOverflowException0.initCause(arrayIndexOutOfBoundsException0);
                throw bufferOverflowException0;
            }
        }
        int v12 = charSequence0.length();
        while(v < v12) {
            int v13 = charSequence0.charAt(v);
            if(v13 < 0x80) {
                byteBuffer0.put(((byte)v13));
            }
            else if(v13 < 0x800) {
                byteBuffer0.put(((byte)(v13 >>> 6 | 960)));
                byteBuffer0.put(((byte)(v13 & 0x3F | 0x80)));
            }
            else if(v13 < 0xD800 || 0xDFFF < v13) {
                byteBuffer0.put(((byte)(v13 >>> 12 | 480)));
                byteBuffer0.put(((byte)(v13 >>> 6 & 0x3F | 0x80)));
                byteBuffer0.put(((byte)(v13 & 0x3F | 0x80)));
            }
            else {
                if(v + 1 != charSequence0.length()) {
                    int v14 = charSequence0.charAt(v + 1);
                    if(Character.isSurrogatePair(((char)v13), ((char)v14))) {
                        int v15 = Character.toCodePoint(((char)v13), ((char)v14));
                        byteBuffer0.put(((byte)(v15 >>> 18 | 0xF0)));
                        byteBuffer0.put(((byte)(v15 >>> 12 & 0x3F | 0x80)));
                        byteBuffer0.put(((byte)(v15 >>> 6 & 0x3F | 0x80)));
                        byteBuffer0.put(((byte)(v15 & 0x3F | 0x80)));
                        ++v;
                        goto label_86;
                    }
                    else {
                        ++v;
                    }
                }
                throw new IllegalArgumentException("Unpaired surrogate at index " + (v - 1));
            }
        label_86:
            ++v;
        }
    }

    public final void zze(int v, zzeah zzeah0) throws IOException {
        if(this.zzhzs == null) {
            this.zzhzs = zzdyi.zzm(this.zzakt);
            this.zzhzt = this.zzakt.position();
        }
        else if(this.zzhzt != this.zzakt.position()) {
            this.zzhzs.write(this.zzakt.array(), this.zzhzt, this.zzakt.position() - this.zzhzt);
            this.zzhzt = this.zzakt.position();
        }
        zzdyi zzdyi0 = this.zzhzs;
        zzdyi0.zza(v, zzeah0);
        zzdyi0.flush();
        this.zzhzt = this.zzakt.position();
    }

    public final void zzf(int v, String s) throws IOException {
        this.zzab(v, 2);
        try {
            int v1 = zzecr.zzgh(s.length());
            if(v1 == zzecr.zzgh(s.length() * 3)) {
                int v2 = this.zzakt.position();
                if(this.zzakt.remaining() < v1) {
                    throw new zzecu(v2 + v1, this.zzakt.limit());
                }
                this.zzakt.position(v2 + v1);
                zzecr.zzd(s, this.zzakt);
                int v3 = this.zzakt.position();
                this.zzakt.position(v2);
                this.zzhc(v3 - v2 - v1);
                this.zzakt.position(v3);
                return;
            }
            this.zzhc(zzecr.zza(s));
            zzecr.zzd(s, this.zzakt);
        }
        catch(BufferOverflowException bufferOverflowException0) {
            zzecu zzecu0 = new zzecu(this.zzakt.position(), this.zzakt.limit());
            zzecu0.initCause(bufferOverflowException0);
            throw zzecu0;
        }
    }

    public final void zzft(long v) throws IOException {
        while((0xFFFFFFFFFFFFFF80L & v) != 0L) {
            this.zzhb(((int)v) & 0x7F | 0x80);
            v >>>= 7;
        }
        this.zzhb(((int)v));
    }

    public static int zzfz(int v) [...] // 潜在的解密器

    public static int zzg(int v, String s) {
        return zzecr.zzfz(v) + zzecr.zzhk(s);
    }

    public static int zzga(int v) {
        return v < 0 ? 10 : zzecr.zzgh(v);
    }

    public static int zzgh(int v) {
        if((v & 0xFFFFFF80) == 0) {
            return 1;
        }
        if((v & 0xFFFFC000) == 0) {
            return 2;
        }
        if((0xFFE00000 & v) == 0) {
            return 3;
        }
        return (v & 0xF0000000) == 0 ? 4 : 5;
    }

    private final void zzhb(int v) throws IOException {
        if(!this.zzakt.hasRemaining()) {
            throw new zzecu(this.zzakt.position(), this.zzakt.limit());
        }
        this.zzakt.put(((byte)v));
    }

    private final void zzhc(int v) throws IOException {
        while((v & 0xFFFFFF80) != 0) {
            this.zzhb(v & 0x7F | 0x80);
            v >>>= 7;
        }
        this.zzhb(v);
    }

    public static int zzhk(String s) {
        int v = zzecr.zza(s);
        return zzecr.zzgh(v) + v;
    }

    public static zzecr zzq(byte[] arr_b, int v, int v1) {
        return new zzecr(arr_b, 0, v1);
    }

    public static zzecr zzz(byte[] arr_b) {
        return zzecr.zzq(arr_b, 0, arr_b.length);
    }
}

