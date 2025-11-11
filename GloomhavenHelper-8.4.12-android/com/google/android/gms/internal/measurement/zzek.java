package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzek extends zzds {
    static final class zza extends zzek {
        private final byte[] zzb;
        private final int zzc;
        private final int zzd;
        private int zze;

        zza(byte[] arr_b, int v, int v1) {
            super(null);
            if(arr_b == null) {
                throw new NullPointerException("buffer");
            }
            if((v1 | arr_b.length - v1) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", ((int)arr_b.length), 0, v1));
            }
            this.zzb = arr_b;
            this.zzc = 0;
            this.zze = 0;
            this.zzd = v1;
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final int zza() {
            return this.zzd - this.zze;
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zza(byte b) throws IOException {
            try {
                int v = this.zze;
                this.zze = v + 1;
                this.zzb[v] = b;
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", this.zze, this.zzd, 1), indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zza(int v) throws IOException {
            if(v >= 0) {
                this.zzb(v);
                return;
            }
            this.zza(((long)v));
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zza(int v, int v1) throws IOException {
            this.zzb(v << 3 | v1);
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zza(int v, long v1) throws IOException {
            this.zza(v, 0);
            this.zza(v1);
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zza(int v, zzdv zzdv0) throws IOException {
            this.zza(v, 2);
            this.zza(zzdv0);
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zza(int v, zzgn zzgn0) throws IOException {
            this.zza(1, 3);
            this.zzc(2, v);
            this.zza(3, 2);
            this.zza(zzgn0);
            this.zza(1, 4);
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        final void zza(int v, zzgn zzgn0, zzhc zzhc0) throws IOException {
            this.zza(v, 2);
            int v1 = ((zzdl)zzgn0).zzbi();
            if(v1 == -1) {
                v1 = zzhc0.zzb(((zzdl)zzgn0));
                ((zzdl)zzgn0).zzc(v1);
            }
            this.zzb(v1);
            zzhc0.zza(zzgn0, this.zza);
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zza(int v, String s) throws IOException {
            this.zza(v, 2);
            this.zza(s);
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zza(int v, boolean z) throws IOException {
            this.zza(v, 0);
            this.zza(((byte)z));
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zza(long v) throws IOException {
            if(this.zza() >= 10) {
                while((v & 0xFFFFFFFFFFFFFF80L) != 0L) {
                    int v1 = this.zze;
                    this.zze = v1 + 1;
                    zzia.zza(this.zzb, ((long)v1), ((byte)(((int)v) & 0x7F | 0x80)));
                    v >>>= 7;
                }
                int v2 = this.zze;
                this.zze = v2 + 1;
                zzia.zza(this.zzb, ((long)v2), ((byte)(((int)v))));
                return;
            }
            try {
                while(true) {
                    if((v & 0xFFFFFFFFFFFFFF80L) == 0L) {
                        int v3 = this.zze;
                        this.zze = v3 + 1;
                        this.zzb[v3] = (byte)(((int)v));
                        return;
                    }
                    int v4 = this.zze;
                    this.zze = v4 + 1;
                    this.zzb[v4] = (byte)(((int)v) & 0x7F | 0x80);
                    v >>>= 7;
                }
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", this.zze, this.zzd, 1), indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zza(zzdv zzdv0) throws IOException {
            this.zzb(zzdv0.zza());
            zzdv0.zza(this);
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zza(zzgn zzgn0) throws IOException {
            this.zzb(zzgn0.zzbl());
            zzgn0.zza(this);
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zza(String s) throws IOException {
            int v;
            try {
                v = this.zze;
                int v1 = zza.zzg(s.length() * 3);
                int v2 = zza.zzg(s.length());
                if(v2 == v1) {
                    this.zze = v + v2;
                    int v3 = this.zze;
                    int v4 = this.zza();
                    int v5 = zzid.zza(s, this.zzb, v3, v4);
                    this.zze = v;
                    this.zzb(v5 - v - v2);
                    this.zze = v5;
                    return;
                }
                this.zzb(zzid.zza(s));
                int v6 = this.zze;
                int v7 = this.zza();
                this.zze = zzid.zza(s, this.zzb, v6, v7);
            }
            catch(zzig zzig0) {
                this.zze = v;
                this.zza(s, zzig0);
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new zzb(indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.android.gms.internal.measurement.zzds
        public final void zza(byte[] arr_b, int v, int v1) throws IOException {
            this.zzc(arr_b, v, v1);
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zzb(int v) throws IOException {
            if(this.zza() >= 5) {
                if((v & 0xFFFFFF80) == 0) {
                    int v1 = this.zze;
                    this.zze = v1 + 1;
                    zzia.zza(this.zzb, ((long)v1), ((byte)v));
                    return;
                }
                int v2 = this.zze;
                this.zze = v2 + 1;
                zzia.zza(this.zzb, ((long)v2), ((byte)(v | 0x80)));
                if((v >>> 7 & 0xFFFFFF80) == 0) {
                    int v3 = this.zze;
                    this.zze = v3 + 1;
                    zzia.zza(this.zzb, ((long)v3), ((byte)(v >>> 7)));
                    return;
                }
                int v4 = this.zze;
                this.zze = v4 + 1;
                zzia.zza(this.zzb, ((long)v4), ((byte)(v >>> 7 | 0x80)));
                int v5 = v >>> 7 >>> 7;
                if((v5 & 0xFFFFFF80) == 0) {
                    int v6 = this.zze;
                    this.zze = v6 + 1;
                    zzia.zza(this.zzb, ((long)v6), ((byte)v5));
                    return;
                }
                int v7 = this.zze;
                this.zze = v7 + 1;
                zzia.zza(this.zzb, ((long)v7), ((byte)(v5 | 0x80)));
                if((v5 >>> 7 & 0xFFFFFF80) == 0) {
                    int v8 = this.zze;
                    this.zze = v8 + 1;
                    zzia.zza(this.zzb, ((long)v8), ((byte)(v5 >>> 7)));
                    return;
                }
                int v9 = this.zze;
                this.zze = v9 + 1;
                zzia.zza(this.zzb, ((long)v9), ((byte)(v5 >>> 7 | 0x80)));
                int v10 = this.zze;
                this.zze = v10 + 1;
                zzia.zza(this.zzb, ((long)v10), ((byte)(v5 >>> 7 >>> 7)));
                return;
            }
            try {
                while(true) {
                    if((v & 0xFFFFFF80) == 0) {
                        int v11 = this.zze;
                        this.zze = v11 + 1;
                        this.zzb[v11] = (byte)v;
                        return;
                    }
                    int v12 = this.zze;
                    this.zze = v12 + 1;
                    this.zzb[v12] = (byte)(v & 0x7F | 0x80);
                    v >>>= 7;
                }
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", this.zze, this.zzd, 1), indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zzb(int v, int v1) throws IOException {
            this.zza(v, 0);
            this.zza(v1);
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zzb(int v, zzdv zzdv0) throws IOException {
            this.zza(1, 3);
            this.zzc(2, v);
            this.zza(3, zzdv0);
            this.zza(1, 4);
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zzb(byte[] arr_b, int v, int v1) throws IOException {
            this.zzb(v1);
            this.zzc(arr_b, 0, v1);
        }

        private final void zzc(byte[] arr_b, int v, int v1) throws IOException {
            try {
                System.arraycopy(arr_b, v, this.zzb, this.zze, v1);
                this.zze += v1;
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", this.zze, this.zzd, v1), indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zzc(int v, int v1) throws IOException {
            this.zza(v, 0);
            this.zzb(v1);
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zzc(int v, long v1) throws IOException {
            this.zza(v, 1);
            this.zzc(v1);
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zzc(long v) throws IOException {
            try {
                int v1 = this.zze;
                this.zze = v1 + 1;
                this.zzb[v1] = (byte)(((int)v));
                int v2 = this.zze;
                this.zze = v2 + 1;
                this.zzb[v2] = (byte)(((int)(v >> 8)));
                int v3 = this.zze;
                this.zze = v3 + 1;
                this.zzb[v3] = (byte)(((int)(v >> 16)));
                int v4 = this.zze;
                this.zze = v4 + 1;
                this.zzb[v4] = (byte)(((int)(v >> 24)));
                int v5 = this.zze;
                this.zze = v5 + 1;
                this.zzb[v5] = (byte)(((int)(v >> 0x20)));
                int v6 = this.zze;
                this.zze = v6 + 1;
                this.zzb[v6] = (byte)(((int)(v >> 40)));
                int v7 = this.zze;
                this.zze = v7 + 1;
                this.zzb[v7] = (byte)(((int)(v >> 0x30)));
                int v8 = this.zze;
                this.zze = v8 + 1;
                this.zzb[v8] = (byte)(((int)(v >> 56)));
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", this.zze, this.zzd, 1), indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zzd(int v) throws IOException {
            try {
                int v1 = this.zze;
                this.zze = v1 + 1;
                this.zzb[v1] = (byte)v;
                int v2 = this.zze;
                this.zze = v2 + 1;
                this.zzb[v2] = (byte)(v >> 8);
                int v3 = this.zze;
                this.zze = v3 + 1;
                this.zzb[v3] = (byte)(v >> 16);
                int v4 = this.zze;
                this.zze = v4 + 1;
                this.zzb[v4] = (byte)(v >>> 24);
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", this.zze, this.zzd, 1), indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.android.gms.internal.measurement.zzek
        public final void zze(int v, int v1) throws IOException {
            this.zza(v, 5);
            this.zzd(v1);
        }
    }

    public static final class zzb extends IOException {
        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzb(String s, Throwable throwable0) {
            String s1 = String.valueOf(s);
            super((s1.length() == 0 ? new String("CodedOutputStream was writing to a flat byte array and ran out of space.: ") : "CodedOutputStream was writing to a flat byte array and ran out of space.: " + s1), throwable0);
        }

        zzb(Throwable throwable0) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", throwable0);
        }
    }

    zzen zza;
    private static final Logger zzb;
    private static final boolean zzc;

    static {
        zzek.zzb = Logger.getLogger(zzek.class.getName());
        zzek.zzc = true;
    }

    private zzek() {
    }

    zzek(zzem zzem0) {
    }

    public static int zza(int v, zzfs zzfs0) {
        int v1 = zzfs0.zzb();
        return zzek.zze(v) + (zzek.zzg(v1) + v1);
    }

    public static int zza(zzfs zzfs0) {
        int v = zzfs0.zzb();
        return zzek.zzg(v) + v;
    }

    static int zza(zzgn zzgn0, zzhc zzhc0) {
        int v = ((zzdl)zzgn0).zzbi();
        if(v == -1) {
            v = zzhc0.zzb(((zzdl)zzgn0));
            ((zzdl)zzgn0).zzc(v);
        }
        return zzek.zzg(v) + v;
    }

    public static zzek zza(byte[] arr_b) {
        return new zza(arr_b, 0, arr_b.length);
    }

    public abstract int zza();

    public abstract void zza(byte arg1) throws IOException;

    public final void zza(double f) throws IOException {
        this.zzc(Double.doubleToRawLongBits(f));
    }

    public final void zza(float f) throws IOException {
        this.zzd(Float.floatToRawIntBits(f));
    }

    public abstract void zza(int arg1) throws IOException;

    public final void zza(int v, double f) throws IOException {
        this.zzc(v, Double.doubleToRawLongBits(f));
    }

    public final void zza(int v, float f) throws IOException {
        this.zze(v, Float.floatToRawIntBits(f));
    }

    public abstract void zza(int arg1, int arg2) throws IOException;

    public abstract void zza(int arg1, long arg2) throws IOException;

    public abstract void zza(int arg1, zzdv arg2) throws IOException;

    public abstract void zza(int arg1, zzgn arg2) throws IOException;

    abstract void zza(int arg1, zzgn arg2, zzhc arg3) throws IOException;

    public abstract void zza(int arg1, String arg2) throws IOException;

    public abstract void zza(int arg1, boolean arg2) throws IOException;

    public abstract void zza(long arg1) throws IOException;

    public abstract void zza(zzdv arg1) throws IOException;

    public abstract void zza(zzgn arg1) throws IOException;

    public abstract void zza(String arg1) throws IOException;

    final void zza(String s, zzig zzig0) throws IOException {
        zzek.zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzig0);
        byte[] arr_b = s.getBytes(zzfe.zza);
        try {
            this.zzb(arr_b.length);
            this.zza(arr_b, 0, arr_b.length);
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
            throw new zzb(indexOutOfBoundsException0);
        }
    }

    public final void zza(boolean z) throws IOException {
        this.zza(((byte)z));
    }

    public static int zzb(double f) {
        return 8;
    }

    public static int zzb(float f) {
        return 4;
    }

    public static int zzb(int v, double f) {
        return zzek.zze(v) + 8;
    }

    public static int zzb(int v, float f) {
        return zzek.zze(v) + 4;
    }

    // 去混淆评级： 低(20)
    public static int zzb(int v, zzfs zzfs0) {
        return zzek.zzg(2, v) + 2 + zzek.zza(3, zzfs0);
    }

    // 去混淆评级： 低(40)
    public static int zzb(int v, zzgn zzgn0) {
        return zzek.zzg(2, v) + 2 + (zzek.zzb(zzgn0) + 1);
    }

    static int zzb(int v, zzgn zzgn0, zzhc zzhc0) {
        return zzek.zze(v) + zzek.zza(zzgn0, zzhc0);
    }

    public static int zzb(int v, String s) {
        return zzek.zze(v) + zzek.zzb(s);
    }

    public static int zzb(int v, boolean z) {
        return zzek.zze(v) + 1;
    }

    public static int zzb(zzdv zzdv0) {
        int v = zzdv0.zza();
        return zzek.zzg(v) + v;
    }

    public static int zzb(zzgn zzgn0) {
        int v = zzgn0.zzbl();
        return zzek.zzg(v) + v;
    }

    public static int zzb(String s) {
        int v;
        try {
            v = zzid.zza(s);
            return zzek.zzg(v) + v;
        }
        catch(zzig unused_ex) {
            v = s.getBytes(zzfe.zza).length;
            return zzek.zzg(v) + v;
        }
    }

    public static int zzb(boolean z) {
        return 1;
    }

    public static int zzb(byte[] arr_b) {
        return zzek.zzg(arr_b.length) + arr_b.length;
    }

    public final void zzb() {
        if(this.zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public abstract void zzb(int arg1) throws IOException;

    public abstract void zzb(int arg1, int arg2) throws IOException;

    public final void zzb(int v, long v1) throws IOException {
        this.zza(v, v1 >> 0x3F ^ v1 << 1);
    }

    public abstract void zzb(int arg1, zzdv arg2) throws IOException;

    public final void zzb(long v) throws IOException {
        this.zza(v >> 0x3F ^ v << 1);
    }

    abstract void zzb(byte[] arg1, int arg2, int arg3) throws IOException;

    public static int zzc(int v, zzdv zzdv0) {
        int v1 = zzdv0.zza();
        return zzek.zze(v) + (zzek.zzg(v1) + v1);
    }

    @Deprecated
    static int zzc(int v, zzgn zzgn0, zzhc zzhc0) {
        int v1 = zzek.zze(v);
        int v2 = ((zzdl)zzgn0).zzbi();
        if(v2 == -1) {
            v2 = zzhc0.zzb(((zzdl)zzgn0));
            ((zzdl)zzgn0).zzc(v2);
        }
        return (v1 << 1) + v2;
    }

    @Deprecated
    public static int zzc(zzgn zzgn0) {
        return zzgn0.zzbl();
    }

    static boolean zzc() [...] // 潜在的解密器

    public final void zzc(int v) throws IOException {
        this.zzb(v >> 0x1F ^ v << 1);
    }

    public abstract void zzc(int arg1, int arg2) throws IOException;

    public abstract void zzc(int arg1, long arg2) throws IOException;

    public abstract void zzc(long arg1) throws IOException;

    public static int zzd(int v, long v1) {
        return zzek.zze(v) + zzek.zze(v1);
    }

    // 去混淆评级： 低(20)
    public static int zzd(int v, zzdv zzdv0) {
        return zzek.zzg(2, v) + 2 + zzek.zzc(3, zzdv0);
    }

    public static int zzd(long v) {
        return zzek.zze(v);
    }

    public abstract void zzd(int arg1) throws IOException;

    public final void zzd(int v, int v1) throws IOException {
        this.zzc(v, v1 >> 0x1F ^ v1 << 1);
    }

    public static int zze(int v) [...] // 潜在的解密器

    public static int zze(int v, long v1) {
        return zzek.zze(v) + zzek.zze(v1);
    }

    public static int zze(long v) {
        int v1;
        if((0xFFFFFFFFFFFFFF80L & v) == 0L) {
            return 1;
        }
        if(v < 0L) {
            return 10;
        }
        if((0xFFFFFFF800000000L & v) == 0L) {
            v1 = 2;
        }
        else {
            v1 = 6;
            v >>>= 28;
        }
        if((0xFFFFFFFFFFE00000L & v) != 0L) {
            v1 += 2;
            v >>>= 14;
        }
        return (v & 0xFFFFFFFFFFFFC000L) == 0L ? v1 : v1 + 1;
    }

    public abstract void zze(int arg1, int arg2) throws IOException;

    public static int zzf(int v) {
        return v < 0 ? 10 : zzek.zzg(v);
    }

    public static int zzf(int v, int v1) {
        return zzek.zze(v) + zzek.zzf(v1);
    }

    public static int zzf(int v, long v1) {
        return zzek.zze(v) + zzek.zze(v1 >> 0x3F ^ v1 << 1);
    }

    public static int zzf(long v) {
        return zzek.zze(v >> 0x3F ^ v << 1);
    }

    public static int zzg(int v) {
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

    public static int zzg(int v, int v1) {
        return zzek.zze(v) + zzek.zzg(v1);
    }

    public static int zzg(int v, long v1) {
        return zzek.zze(v) + 8;
    }

    public static int zzg(long v) {
        return 8;
    }

    public static int zzh(int v) {
        return zzek.zzg(v >> 0x1F ^ v << 1);
    }

    public static int zzh(int v, int v1) {
        return zzek.zze(v) + zzek.zzg(v1 >> 0x1F ^ v1 << 1);
    }

    public static int zzh(int v, long v1) {
        return zzek.zze(v) + 8;
    }

    public static int zzh(long v) {
        return 8;
    }

    public static int zzi(int v) {
        return 4;
    }

    public static int zzi(int v, int v1) {
        return zzek.zze(v) + 4;
    }

    private static long zzi(long v) [...] // Inlined contents

    public static int zzj(int v) {
        return 4;
    }

    public static int zzj(int v, int v1) {
        return zzek.zze(v) + 4;
    }

    public static int zzk(int v) {
        return zzek.zzf(v);
    }

    public static int zzk(int v, int v1) {
        return zzek.zze(v) + zzek.zzf(v1);
    }

    @Deprecated
    public static int zzl(int v) {
        return zzek.zzg(v);
    }

    private static int zzm(int v) [...] // Inlined contents
}

