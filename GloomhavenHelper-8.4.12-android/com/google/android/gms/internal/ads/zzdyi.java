package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzdyi extends zzdxo {
    static final class zza extends zzb {
        private final ByteBuffer zzhpf;
        private int zzhpg;

        zza(ByteBuffer byteBuffer0) {
            super(byteBuffer0.array(), byteBuffer0.arrayOffset() + byteBuffer0.position(), byteBuffer0.remaining());
            this.zzhpf = byteBuffer0;
            this.zzhpg = byteBuffer0.position();
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi$zzb
        public final void flush() {
            this.zzhpf.position(this.zzhpg + this.zzbce());
        }
    }

    static class zzb extends zzdyi {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zzb(byte[] arr_b, int v, int v1) {
            super(null);
            if(arr_b == null) {
                throw new NullPointerException("buffer");
            }
            int v2 = v + v1;
            if((v | v1 | arr_b.length - v2) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", ((int)arr_b.length), v, v1));
            }
            this.buffer = arr_b;
            this.offset = v;
            this.position = v;
            this.limit = v2;
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public void flush() {
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void write(byte[] arr_b, int v, int v1) throws IOException {
            try {
                System.arraycopy(arr_b, v, this.buffer, this.position, v1);
                this.position += v1;
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new zzd(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, v1), indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zza(int v, zzdxn zzdxn0) throws IOException {
            this.zzab(v, 2);
            this.zzah(zzdxn0);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zza(int v, zzeah zzeah0) throws IOException {
            this.zzab(v, 2);
            this.zzg(zzeah0);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        final void zza(int v, zzeah zzeah0, zzebd zzebd0) throws IOException {
            this.zzab(v, 2);
            int v1 = ((zzdxd)zzeah0).zzbaj();
            if(v1 == -1) {
                v1 = zzebd0.zzax(((zzdxd)zzeah0));
                ((zzdxd)zzeah0).zzfa(v1);
            }
            this.zzfw(v1);
            zzebd0.zza(zzeah0, this.zzhpe);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        final void zza(zzeah zzeah0, zzebd zzebd0) throws IOException {
            int v = ((zzdxd)zzeah0).zzbaj();
            if(v == -1) {
                v = zzebd0.zzax(((zzdxd)zzeah0));
                ((zzdxd)zzeah0).zzfa(v);
            }
            this.zzfw(v);
            zzebd0.zza(zzeah0, this.zzhpe);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzab(int v, int v1) throws IOException {
            this.zzfw(v << 3 | v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzac(int v, int v1) throws IOException {
            this.zzab(v, 0);
            this.zzfv(v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzad(int v, int v1) throws IOException {
            this.zzab(v, 0);
            this.zzfw(v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzaf(int v, int v1) throws IOException {
            this.zzab(v, 5);
            this.zzfy(v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzah(zzdxn zzdxn0) throws IOException {
            this.zzfw(zzdxn0.size());
            zzdxn0.zza(this);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzb(int v, zzdxn zzdxn0) throws IOException {
            this.zzab(1, 3);
            this.zzad(2, v);
            this.zza(3, zzdxn0);
            this.zzab(1, 4);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzb(int v, zzeah zzeah0) throws IOException {
            this.zzab(1, 3);
            this.zzad(2, v);
            this.zza(3, zzeah0);
            this.zzab(1, 4);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final int zzbcb() {
            return this.limit - this.position;
        }

        public final int zzbce() {
            return this.position - this.offset;
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzd(byte b) throws IOException {
            try {
                int v = this.position;
                this.position = v + 1;
                this.buffer[v] = b;
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new zzd(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, 1), indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzf(int v, String s) throws IOException {
            this.zzab(v, 2);
            this.zzhj(s);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzfh(long v) throws IOException {
            if(this.zzbcb() >= 10) {
                while((v & 0xFFFFFFFFFFFFFF80L) != 0L) {
                    int v1 = this.position;
                    this.position = v1 + 1;
                    zzecb.zza(this.buffer, ((long)v1), ((byte)(((int)v) & 0x7F | 0x80)));
                    v >>>= 7;
                }
                int v2 = this.position;
                this.position = v2 + 1;
                zzecb.zza(this.buffer, ((long)v2), ((byte)(((int)v))));
                return;
            }
            try {
                while(true) {
                    if((v & 0xFFFFFFFFFFFFFF80L) == 0L) {
                        int v3 = this.position;
                        this.position = v3 + 1;
                        this.buffer[v3] = (byte)(((int)v));
                        return;
                    }
                    int v4 = this.position;
                    this.position = v4 + 1;
                    this.buffer[v4] = (byte)(((int)v) & 0x7F | 0x80);
                    v >>>= 7;
                }
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new zzd(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, 1), indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzfj(long v) throws IOException {
            try {
                int v1 = this.position;
                this.position = v1 + 1;
                this.buffer[v1] = (byte)(((int)v));
                int v2 = this.position;
                this.position = v2 + 1;
                this.buffer[v2] = (byte)(((int)(v >> 8)));
                int v3 = this.position;
                this.position = v3 + 1;
                this.buffer[v3] = (byte)(((int)(v >> 16)));
                int v4 = this.position;
                this.position = v4 + 1;
                this.buffer[v4] = (byte)(((int)(v >> 24)));
                int v5 = this.position;
                this.position = v5 + 1;
                this.buffer[v5] = (byte)(((int)(v >> 0x20)));
                int v6 = this.position;
                this.position = v6 + 1;
                this.buffer[v6] = (byte)(((int)(v >> 40)));
                int v7 = this.position;
                this.position = v7 + 1;
                this.buffer[v7] = (byte)(((int)(v >> 0x30)));
                int v8 = this.position;
                this.position = v8 + 1;
                this.buffer[v8] = (byte)(((int)(v >> 56)));
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new zzd(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, 1), indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzfv(int v) throws IOException {
            if(v >= 0) {
                this.zzfw(v);
                return;
            }
            this.zzfh(((long)v));
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzfw(int v) throws IOException {
            if(this.zzbcb() >= 5) {
                if((v & 0xFFFFFF80) == 0) {
                    int v1 = this.position;
                    this.position = v1 + 1;
                    zzecb.zza(this.buffer, ((long)v1), ((byte)v));
                    return;
                }
                int v2 = this.position;
                this.position = v2 + 1;
                zzecb.zza(this.buffer, ((long)v2), ((byte)(v | 0x80)));
                if((v >>> 7 & 0xFFFFFF80) == 0) {
                    int v3 = this.position;
                    this.position = v3 + 1;
                    zzecb.zza(this.buffer, ((long)v3), ((byte)(v >>> 7)));
                    return;
                }
                int v4 = this.position;
                this.position = v4 + 1;
                zzecb.zza(this.buffer, ((long)v4), ((byte)(v >>> 7 | 0x80)));
                int v5 = v >>> 7 >>> 7;
                if((v5 & 0xFFFFFF80) == 0) {
                    int v6 = this.position;
                    this.position = v6 + 1;
                    zzecb.zza(this.buffer, ((long)v6), ((byte)v5));
                    return;
                }
                int v7 = this.position;
                this.position = v7 + 1;
                zzecb.zza(this.buffer, ((long)v7), ((byte)(v5 | 0x80)));
                if((v5 >>> 7 & 0xFFFFFF80) == 0) {
                    int v8 = this.position;
                    this.position = v8 + 1;
                    zzecb.zza(this.buffer, ((long)v8), ((byte)(v5 >>> 7)));
                    return;
                }
                int v9 = this.position;
                this.position = v9 + 1;
                zzecb.zza(this.buffer, ((long)v9), ((byte)(v5 >>> 7 | 0x80)));
                int v10 = this.position;
                this.position = v10 + 1;
                zzecb.zza(this.buffer, ((long)v10), ((byte)(v5 >>> 7 >>> 7)));
                return;
            }
            try {
                while(true) {
                    if((v & 0xFFFFFF80) == 0) {
                        int v11 = this.position;
                        this.position = v11 + 1;
                        this.buffer[v11] = (byte)v;
                        return;
                    }
                    int v12 = this.position;
                    this.position = v12 + 1;
                    this.buffer[v12] = (byte)(v & 0x7F | 0x80);
                    v >>>= 7;
                }
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new zzd(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, 1), indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzfy(int v) throws IOException {
            try {
                int v1 = this.position;
                this.position = v1 + 1;
                this.buffer[v1] = (byte)v;
                int v2 = this.position;
                this.position = v2 + 1;
                this.buffer[v2] = (byte)(v >> 8);
                int v3 = this.position;
                this.position = v3 + 1;
                this.buffer[v3] = (byte)(v >> 16);
                int v4 = this.position;
                this.position = v4 + 1;
                this.buffer[v4] = (byte)(v >>> 24);
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new zzd(String.format("Pos: %d, limit: %d, len: %d", this.position, this.limit, 1), indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzg(zzeah zzeah0) throws IOException {
            this.zzfw(zzeah0.zzbda());
            zzeah0.zzb(this);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzh(int v, long v1) throws IOException {
            this.zzab(v, 0);
            this.zzfh(v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzh(int v, boolean z) throws IOException {
            this.zzab(v, 0);
            this.zzd(((byte)z));
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzhj(String s) throws IOException {
            int v;
            try {
                v = this.position;
                int v1 = zzb.zzgb(s.length() * 3);
                int v2 = zzb.zzgb(s.length());
                if(v2 == v1) {
                    this.position = v + v2;
                    int v3 = this.position;
                    int v4 = this.zzbcb();
                    int v5 = zzece.zza(s, this.buffer, v3, v4);
                    this.position = v;
                    this.zzfw(v5 - v - v2);
                    this.position = v5;
                    return;
                }
                this.zzfw(zzece.zza(s));
                int v6 = this.position;
                int v7 = this.zzbcb();
                this.position = zzece.zza(s, this.buffer, v6, v7);
            }
            catch(zzech zzech0) {
                this.position = v;
                this.zza(s, zzech0);
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new zzd(indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdxo
        public final void zzi(byte[] arr_b, int v, int v1) throws IOException {
            this.write(arr_b, v, v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzj(int v, long v1) throws IOException {
            this.zzab(v, 1);
            this.zzfj(v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzk(byte[] arr_b, int v, int v1) throws IOException {
            this.zzfw(v1);
            this.write(arr_b, 0, v1);
        }
    }

    static final class zzc extends zzdyi {
        private final ByteBuffer zzakt;
        private final int zzhpg;
        private final ByteBuffer zzhph;

        zzc(ByteBuffer byteBuffer0) {
            super(null);
            this.zzhph = byteBuffer0;
            this.zzakt = byteBuffer0.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzhpg = byteBuffer0.position();
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void flush() {
            this.zzhph.position(this.zzakt.position());
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void write(byte[] arr_b, int v, int v1) throws IOException {
            try {
                this.zzakt.put(arr_b, v, v1);
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new zzd(indexOutOfBoundsException0);
            }
            catch(BufferOverflowException bufferOverflowException0) {
                throw new zzd(bufferOverflowException0);
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zza(int v, zzdxn zzdxn0) throws IOException {
            this.zzab(v, 2);
            this.zzah(zzdxn0);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zza(int v, zzeah zzeah0) throws IOException {
            this.zzab(v, 2);
            this.zzg(zzeah0);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        final void zza(int v, zzeah zzeah0, zzebd zzebd0) throws IOException {
            this.zzab(v, 2);
            this.zza(zzeah0, zzebd0);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        final void zza(zzeah zzeah0, zzebd zzebd0) throws IOException {
            int v = ((zzdxd)zzeah0).zzbaj();
            if(v == -1) {
                v = zzebd0.zzax(((zzdxd)zzeah0));
                ((zzdxd)zzeah0).zzfa(v);
            }
            this.zzfw(v);
            zzebd0.zza(zzeah0, this.zzhpe);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzab(int v, int v1) throws IOException {
            this.zzfw(v << 3 | v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzac(int v, int v1) throws IOException {
            this.zzab(v, 0);
            this.zzfv(v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzad(int v, int v1) throws IOException {
            this.zzab(v, 0);
            this.zzfw(v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzaf(int v, int v1) throws IOException {
            this.zzab(v, 5);
            this.zzfy(v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzah(zzdxn zzdxn0) throws IOException {
            this.zzfw(zzdxn0.size());
            zzdxn0.zza(this);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzb(int v, zzdxn zzdxn0) throws IOException {
            this.zzab(1, 3);
            this.zzad(2, v);
            this.zza(3, zzdxn0);
            this.zzab(1, 4);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzb(int v, zzeah zzeah0) throws IOException {
            this.zzab(1, 3);
            this.zzad(2, v);
            this.zza(3, zzeah0);
            this.zzab(1, 4);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final int zzbcb() {
            return this.zzakt.remaining();
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzd(byte b) throws IOException {
            try {
                this.zzakt.put(b);
            }
            catch(BufferOverflowException bufferOverflowException0) {
                throw new zzd(bufferOverflowException0);
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzf(int v, String s) throws IOException {
            this.zzab(v, 2);
            this.zzhj(s);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzfh(long v) throws IOException {
            try {
                while(true) {
                    if((0xFFFFFFFFFFFFFF80L & v) == 0L) {
                        this.zzakt.put(((byte)(((int)v))));
                        return;
                    }
                    this.zzakt.put(((byte)(((int)v) & 0x7F | 0x80)));
                    v >>>= 7;
                }
            }
            catch(BufferOverflowException bufferOverflowException0) {
                throw new zzd(bufferOverflowException0);
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzfj(long v) throws IOException {
            try {
                this.zzakt.putLong(v);
            }
            catch(BufferOverflowException bufferOverflowException0) {
                throw new zzd(bufferOverflowException0);
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzfv(int v) throws IOException {
            if(v >= 0) {
                this.zzfw(v);
                return;
            }
            this.zzfh(((long)v));
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzfw(int v) throws IOException {
            try {
                while(true) {
                    if((v & 0xFFFFFF80) == 0) {
                        this.zzakt.put(((byte)v));
                        return;
                    }
                    this.zzakt.put(((byte)(v & 0x7F | 0x80)));
                    v >>>= 7;
                }
            }
            catch(BufferOverflowException bufferOverflowException0) {
                throw new zzd(bufferOverflowException0);
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzfy(int v) throws IOException {
            try {
                this.zzakt.putInt(v);
            }
            catch(BufferOverflowException bufferOverflowException0) {
                throw new zzd(bufferOverflowException0);
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzg(zzeah zzeah0) throws IOException {
            this.zzfw(zzeah0.zzbda());
            zzeah0.zzb(this);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzh(int v, long v1) throws IOException {
            this.zzab(v, 0);
            this.zzfh(v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzh(int v, boolean z) throws IOException {
            this.zzab(v, 0);
            this.zzd(((byte)z));
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzhj(String s) throws IOException {
            int v = this.zzakt.position();
            try {
                int v1 = zzc.zzgb(s.length() * 3);
                int v2 = zzc.zzgb(s.length());
                if(v2 == v1) {
                    int v3 = this.zzakt.position() + v2;
                    this.zzakt.position(v3);
                    this.zzhl(s);
                    int v4 = this.zzakt.position();
                    this.zzakt.position(v);
                    this.zzfw(v4 - v3);
                    this.zzakt.position(v4);
                    return;
                }
                this.zzfw(zzece.zza(s));
                this.zzhl(s);
            }
            catch(zzech zzech0) {
                this.zzakt.position(v);
                this.zza(s, zzech0);
            }
            catch(IllegalArgumentException illegalArgumentException0) {
                throw new zzd(illegalArgumentException0);
            }
        }

        private final void zzhl(String s) throws IOException {
            try {
                zzece.zza(s, this.zzakt);
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new zzd(indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdxo
        public final void zzi(byte[] arr_b, int v, int v1) throws IOException {
            this.write(arr_b, v, v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzj(int v, long v1) throws IOException {
            this.zzab(v, 1);
            this.zzfj(v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzk(byte[] arr_b, int v, int v1) throws IOException {
            this.zzfw(v1);
            this.write(arr_b, 0, v1);
        }
    }

    public static final class zzd extends IOException {
        zzd() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzd(String s) {
            String s1 = String.valueOf(s);
            super((s1.length() == 0 ? new String("CodedOutputStream was writing to a flat byte array and ran out of space.: ") : "CodedOutputStream was writing to a flat byte array and ran out of space.: " + s1));
        }

        zzd(String s, Throwable throwable0) {
            String s1 = String.valueOf(s);
            super((s1.length() == 0 ? new String("CodedOutputStream was writing to a flat byte array and ran out of space.: ") : "CodedOutputStream was writing to a flat byte array and ran out of space.: " + s1), throwable0);
        }

        zzd(Throwable throwable0) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", throwable0);
        }
    }

    static final class zze extends zzdyi {
        private final ByteBuffer zzakt;
        private long zzana;
        private final ByteBuffer zzhph;
        private final long zzhpi;
        private final long zzhpj;
        private final long zzhpk;
        private final long zzhpl;

        zze(ByteBuffer byteBuffer0) {
            super(null);
            this.zzhph = byteBuffer0;
            this.zzakt = byteBuffer0.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzhpi = zzecb.zzn(byteBuffer0);
            this.zzhpj = this.zzhpi + ((long)byteBuffer0.position());
            this.zzhpk = this.zzhpi + ((long)byteBuffer0.limit());
            this.zzhpl = this.zzhpk - 10L;
            this.zzana = this.zzhpj;
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void flush() {
            this.zzhph.position(((int)(this.zzana - this.zzhpi)));
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void write(byte[] arr_b, int v, int v1) throws IOException {
            if(arr_b != null && v >= 0 && v1 >= 0 && arr_b.length - v1 >= v) {
                long v2 = this.zzana;
                if(this.zzhpk - ((long)v1) >= v2) {
                    zzecb.zza(arr_b, ((long)v), v2, ((long)v1));
                    this.zzana += (long)v1;
                    return;
                }
            }
            if(arr_b == null) {
                throw new NullPointerException("value");
            }
            throw new zzd(String.format("Pos: %d, limit: %d, len: %d", this.zzana, this.zzhpk, v1));
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zza(int v, zzdxn zzdxn0) throws IOException {
            this.zzab(v, 2);
            this.zzah(zzdxn0);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zza(int v, zzeah zzeah0) throws IOException {
            this.zzab(v, 2);
            this.zzg(zzeah0);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        final void zza(int v, zzeah zzeah0, zzebd zzebd0) throws IOException {
            this.zzab(v, 2);
            this.zza(zzeah0, zzebd0);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        final void zza(zzeah zzeah0, zzebd zzebd0) throws IOException {
            int v = ((zzdxd)zzeah0).zzbaj();
            if(v == -1) {
                v = zzebd0.zzax(((zzdxd)zzeah0));
                ((zzdxd)zzeah0).zzfa(v);
            }
            this.zzfw(v);
            zzebd0.zza(zzeah0, this.zzhpe);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzab(int v, int v1) throws IOException {
            this.zzfw(v << 3 | v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzac(int v, int v1) throws IOException {
            this.zzab(v, 0);
            this.zzfv(v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzad(int v, int v1) throws IOException {
            this.zzab(v, 0);
            this.zzfw(v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzaf(int v, int v1) throws IOException {
            this.zzab(v, 5);
            this.zzfy(v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzah(zzdxn zzdxn0) throws IOException {
            this.zzfw(zzdxn0.size());
            zzdxn0.zza(this);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzb(int v, zzdxn zzdxn0) throws IOException {
            this.zzab(1, 3);
            this.zzad(2, v);
            this.zza(3, zzdxn0);
            this.zzab(1, 4);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzb(int v, zzeah zzeah0) throws IOException {
            this.zzab(1, 3);
            this.zzad(2, v);
            this.zza(3, zzeah0);
            this.zzab(1, 4);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final int zzbcb() {
            return (int)(this.zzhpk - this.zzana);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzd(byte b) throws IOException {
            long v = this.zzana;
            if(v >= this.zzhpk) {
                throw new zzd(String.format("Pos: %d, limit: %d, len: %d", v, this.zzhpk, 1));
            }
            this.zzana = v + 1L;
            zzecb.zza(v, b);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzf(int v, String s) throws IOException {
            this.zzab(v, 2);
            this.zzhj(s);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzfh(long v) throws IOException {
            if(this.zzana <= this.zzhpl) {
                while((v & 0xFFFFFFFFFFFFFF80L) != 0L) {
                    long v1 = this.zzana;
                    this.zzana = v1 + 1L;
                    zzecb.zza(v1, ((byte)(((int)v) & 0x7F | 0x80)));
                    v >>>= 7;
                }
                long v2 = this.zzana;
                this.zzana = v2 + 1L;
                zzecb.zza(v2, ((byte)(((int)v))));
                return;
            }
            long v3;
            while((v3 = this.zzana) < this.zzhpk) {
                if((v & 0xFFFFFFFFFFFFFF80L) == 0L) {
                    this.zzana = v3 + 1L;
                    zzecb.zza(v3, ((byte)(((int)v))));
                    return;
                }
                this.zzana = v3 + 1L;
                zzecb.zza(v3, ((byte)(((int)v) & 0x7F | 0x80)));
                v >>>= 7;
            }
            throw new zzd(String.format("Pos: %d, limit: %d, len: %d", v3, this.zzhpk, 1));
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzfj(long v) throws IOException {
            this.zzakt.putLong(((int)(this.zzana - this.zzhpi)), v);
            this.zzana += 8L;
        }

        private final void zzfq(long v) {
            this.zzakt.position(((int)(v - this.zzhpi)));
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzfv(int v) throws IOException {
            if(v >= 0) {
                this.zzfw(v);
                return;
            }
            this.zzfh(((long)v));
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzfw(int v) throws IOException {
            if(this.zzana <= this.zzhpl) {
                while((v & 0xFFFFFF80) != 0) {
                    long v1 = this.zzana;
                    this.zzana = v1 + 1L;
                    zzecb.zza(v1, ((byte)(v & 0x7F | 0x80)));
                    v >>>= 7;
                }
                long v2 = this.zzana;
                this.zzana = v2 + 1L;
                zzecb.zza(v2, ((byte)v));
                return;
            }
            long v3;
            while((v3 = this.zzana) < this.zzhpk) {
                if((v & 0xFFFFFF80) == 0) {
                    this.zzana = v3 + 1L;
                    zzecb.zza(v3, ((byte)v));
                    return;
                }
                this.zzana = v3 + 1L;
                zzecb.zza(v3, ((byte)(v & 0x7F | 0x80)));
                v >>>= 7;
            }
            throw new zzd(String.format("Pos: %d, limit: %d, len: %d", v3, this.zzhpk, 1));
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzfy(int v) throws IOException {
            this.zzakt.putInt(((int)(this.zzana - this.zzhpi)), v);
            this.zzana += 4L;
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzg(zzeah zzeah0) throws IOException {
            this.zzfw(zzeah0.zzbda());
            zzeah0.zzb(this);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzh(int v, long v1) throws IOException {
            this.zzab(v, 0);
            this.zzfh(v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzh(int v, boolean z) throws IOException {
            this.zzab(v, 0);
            this.zzd(((byte)z));
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzhj(String s) throws IOException {
            long v;
            try {
                v = this.zzana;
                int v1 = zze.zzgb(s.length() * 3);
                int v2 = zze.zzgb(s.length());
                if(v2 == v1) {
                    int v3 = ((int)(this.zzana - this.zzhpi)) + v2;
                    this.zzakt.position(v3);
                    zzece.zza(s, this.zzakt);
                    int v4 = this.zzakt.position() - v3;
                    this.zzfw(v4);
                    this.zzana += (long)v4;
                    return;
                }
                int v5 = zzece.zza(s);
                this.zzfw(v5);
                this.zzfq(this.zzana);
                zzece.zza(s, this.zzakt);
                this.zzana += (long)v5;
            }
            catch(zzech zzech0) {
                this.zzana = v;
                this.zzfq(this.zzana);
                this.zza(s, zzech0);
            }
            catch(IllegalArgumentException illegalArgumentException0) {
                throw new zzd(illegalArgumentException0);
            }
            catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
                throw new zzd(indexOutOfBoundsException0);
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdxo
        public final void zzi(byte[] arr_b, int v, int v1) throws IOException {
            this.write(arr_b, v, v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzj(int v, long v1) throws IOException {
            this.zzab(v, 1);
            this.zzfj(v1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyi
        public final void zzk(byte[] arr_b, int v, int v1) throws IOException {
            this.zzfw(v1);
            this.write(arr_b, 0, v1);
        }
    }

    private static final Logger logger;
    private static final boolean zzhpd;
    zzdyj zzhpe;

    static {
        zzdyi.logger = Logger.getLogger(zzdyi.class.getName());
        zzdyi.zzhpd = true;
    }

    private zzdyi() {
    }

    zzdyi(zzdyh zzdyh0) {
    }

    public abstract void flush() throws IOException;

    public abstract void write(byte[] arg1, int arg2, int arg3) throws IOException;

    public static int zza(int v, zzdzq zzdzq0) {
        int v1 = zzdzq0.zzbda();
        return zzdyi.zzfz(v) + (zzdyi.zzgb(v1) + v1);
    }

    public static int zza(zzdzq zzdzq0) {
        int v = zzdzq0.zzbda();
        return zzdyi.zzgb(v) + v;
    }

    public final void zza(int v, float f) throws IOException {
        this.zzaf(v, Float.floatToRawIntBits(f));
    }

    public abstract void zza(int arg1, zzdxn arg2) throws IOException;

    public abstract void zza(int arg1, zzeah arg2) throws IOException;

    abstract void zza(int arg1, zzeah arg2, zzebd arg3) throws IOException;

    abstract void zza(zzeah arg1, zzebd arg2) throws IOException;

    final void zza(String s, zzech zzech0) throws IOException {
        zzdyi.logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzech0);
        byte[] arr_b = s.getBytes(zzdzc.UTF_8);
        try {
            this.zzfw(arr_b.length);
            this.zzi(arr_b, 0, arr_b.length);
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
            throw new zzd(indexOutOfBoundsException0);
        }
    }

    public abstract void zzab(int arg1, int arg2) throws IOException;

    public abstract void zzac(int arg1, int arg2) throws IOException;

    public abstract void zzad(int arg1, int arg2) throws IOException;

    public final void zzae(int v, int v1) throws IOException {
        this.zzad(v, v1 >> 0x1F ^ v1 << 1);
    }

    public abstract void zzaf(int arg1, int arg2) throws IOException;

    public static int zzag(int v, int v1) {
        return zzdyi.zzfz(v) + zzdyi.zzga(v1);
    }

    public static int zzah(int v, int v1) {
        return zzdyi.zzfz(v) + zzdyi.zzgb(v1);
    }

    public abstract void zzah(zzdxn arg1) throws IOException;

    public static int zzai(int v, int v1) {
        return zzdyi.zzfz(v) + zzdyi.zzgb(v1 >> 0x1F ^ v1 << 1);
    }

    public static int zzai(zzdxn zzdxn0) {
        int v = zzdxn0.size();
        return zzdyi.zzgb(v) + v;
    }

    public static int zzaj(int v, int v1) {
        return zzdyi.zzfz(v) + 4;
    }

    public static int zzak(int v, int v1) {
        return zzdyi.zzfz(v) + 4;
    }

    public static int zzal(int v, int v1) {
        return zzdyi.zzfz(v) + zzdyi.zzga(v1);
    }

    public static int zzb(int v, float f) {
        return zzdyi.zzfz(v) + 4;
    }

    // 去混淆评级： 低(20)
    public static int zzb(int v, zzdzq zzdzq0) {
        return zzdyi.zzah(2, v) + 2 + zzdyi.zza(3, zzdzq0);
    }

    static int zzb(int v, zzeah zzeah0, zzebd zzebd0) {
        return zzdyi.zzfz(v) + zzdyi.zzb(zzeah0, zzebd0);
    }

    static int zzb(zzeah zzeah0, zzebd zzebd0) {
        int v = ((zzdxd)zzeah0).zzbaj();
        if(v == -1) {
            v = zzebd0.zzax(((zzdxd)zzeah0));
            ((zzdxd)zzeah0).zzfa(v);
        }
        return zzdyi.zzgb(v) + v;
    }

    public final void zzb(double f) throws IOException {
        this.zzfj(Double.doubleToRawLongBits(f));
    }

    public final void zzb(int v, double f) throws IOException {
        this.zzj(v, Double.doubleToRawLongBits(f));
    }

    public abstract void zzb(int arg1, zzdxn arg2) throws IOException;

    public abstract void zzb(int arg1, zzeah arg2) throws IOException;

    public abstract int zzbcb();

    public final void zzbcc() {
        if(this.zzbcb() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    static boolean zzbcd() [...] // 潜在的解密器

    public final void zzbp(boolean z) throws IOException {
        this.zzd(((byte)z));
    }

    public static int zzbq(boolean z) {
        return 1;
    }

    public static int zzc(double f) {
        return 8;
    }

    public static int zzc(int v, double f) {
        return zzdyi.zzfz(v) + 8;
    }

    public static int zzc(int v, zzdxn zzdxn0) {
        int v1 = zzdxn0.size();
        return zzdyi.zzfz(v) + (zzdyi.zzgb(v1) + v1);
    }

    public static int zzc(int v, zzeah zzeah0) {
        return zzdyi.zzfz(v) + zzdyi.zzh(zzeah0);
    }

    @Deprecated
    static int zzc(int v, zzeah zzeah0, zzebd zzebd0) {
        int v1 = zzdyi.zzfz(v);
        int v2 = ((zzdxd)zzeah0).zzbaj();
        if(v2 == -1) {
            v2 = zzebd0.zzax(((zzdxd)zzeah0));
            ((zzdxd)zzeah0).zzfa(v2);
        }
        return (v1 << 1) + v2;
    }

    // 去混淆评级： 低(20)
    public static int zzd(int v, zzdxn zzdxn0) {
        return zzdyi.zzah(2, v) + 2 + zzdyi.zzc(3, zzdxn0);
    }

    // 去混淆评级： 低(20)
    public static int zzd(int v, zzeah zzeah0) {
        return zzdyi.zzah(2, v) + 2 + zzdyi.zzc(3, zzeah0);
    }

    public abstract void zzd(byte arg1) throws IOException;

    public final void zzf(float f) throws IOException {
        this.zzfy(Float.floatToRawIntBits(f));
    }

    public abstract void zzf(int arg1, String arg2) throws IOException;

    public abstract void zzfh(long arg1) throws IOException;

    public final void zzfi(long v) throws IOException {
        this.zzfh(v >> 0x3F ^ v << 1);
    }

    public abstract void zzfj(long arg1) throws IOException;

    public static int zzfk(long v) {
        return zzdyi.zzfl(v);
    }

    public static int zzfl(long v) {
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

    public static int zzfm(long v) {
        return zzdyi.zzfl(v >> 0x3F ^ v << 1);
    }

    public static int zzfn(long v) {
        return 8;
    }

    public static int zzfo(long v) {
        return 8;
    }

    private static long zzfp(long v) [...] // Inlined contents

    public abstract void zzfv(int arg1) throws IOException;

    public abstract void zzfw(int arg1) throws IOException;

    public final void zzfx(int v) throws IOException {
        this.zzfw(v >> 0x1F ^ v << 1);
    }

    public abstract void zzfy(int arg1) throws IOException;

    public static int zzfz(int v) [...] // 潜在的解密器

    public static int zzg(float f) {
        return 4;
    }

    public static int zzg(int v, String s) {
        return zzdyi.zzfz(v) + zzdyi.zzhk(s);
    }

    public abstract void zzg(zzeah arg1) throws IOException;

    public static int zzga(int v) {
        return v < 0 ? 10 : zzdyi.zzgb(v);
    }

    public static int zzgb(int v) {
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

    public static int zzgc(int v) {
        return zzdyi.zzgb(v >> 0x1F ^ v << 1);
    }

    public static int zzgd(int v) {
        return 4;
    }

    public static int zzge(int v) {
        return 4;
    }

    public static int zzgf(int v) {
        return zzdyi.zzga(v);
    }

    private static int zzgg(int v) [...] // Inlined contents

    @Deprecated
    public static int zzgh(int v) {
        return zzdyi.zzgb(v);
    }

    public static int zzh(zzeah zzeah0) {
        int v = zzeah0.zzbda();
        return zzdyi.zzgb(v) + v;
    }

    public abstract void zzh(int arg1, long arg2) throws IOException;

    public abstract void zzh(int arg1, boolean arg2) throws IOException;

    public abstract void zzhj(String arg1) throws IOException;

    public static int zzhk(String s) {
        int v;
        try {
            v = zzece.zza(s);
            return zzdyi.zzgb(v) + v;
        }
        catch(zzech unused_ex) {
            v = s.getBytes(zzdzc.UTF_8).length;
            return zzdyi.zzgb(v) + v;
        }
    }

    public static int zzi(int v, boolean z) {
        return zzdyi.zzfz(v) + 1;
    }

    @Deprecated
    public static int zzi(zzeah zzeah0) {
        return zzeah0.zzbda();
    }

    public final void zzi(int v, long v1) throws IOException {
        this.zzh(v, v1 >> 0x3F ^ v1 << 1);
    }

    public abstract void zzj(int arg1, long arg2) throws IOException;

    public static int zzk(int v, long v1) {
        return zzdyi.zzfz(v) + zzdyi.zzfl(v1);
    }

    abstract void zzk(byte[] arg1, int arg2, int arg3) throws IOException;

    public static int zzl(int v, long v1) {
        return zzdyi.zzfz(v) + zzdyi.zzfl(v1);
    }

    public static int zzm(int v, long v1) {
        return zzdyi.zzfz(v) + zzdyi.zzfl(v1 >> 0x3F ^ v1 << 1);
    }

    public static zzdyi zzm(ByteBuffer byteBuffer0) {
        if(byteBuffer0.hasArray()) {
            return new zza(byteBuffer0);
        }
        if(!byteBuffer0.isDirect() || byteBuffer0.isReadOnly()) {
            throw new IllegalArgumentException("ByteBuffer is read-only");
        }
        return new zze(byteBuffer0);
    }

    public static int zzn(int v, long v1) {
        return zzdyi.zzfz(v) + 8;
    }

    public static int zzo(int v, long v1) {
        return zzdyi.zzfz(v) + 8;
    }

    public static zzdyi zzv(byte[] arr_b) {
        return new zzb(arr_b, 0, arr_b.length);
    }

    public static int zzw(byte[] arr_b) {
        return zzdyi.zzgb(arr_b.length) + arr_b.length;
    }
}

