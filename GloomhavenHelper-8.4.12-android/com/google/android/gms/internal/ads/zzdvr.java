package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

abstract class zzdvr implements zzdwp {
    private static final int[] zzhlo;
    int[] zzhlp;
    private final int zzhlq;

    static {
        zzdvr.zzhlo = zzdvr.zzq(new byte[]{101, 120, 0x70, 97, 110, 100, 0x20, 51, 50, 45, 98, 0x79, 0x74, 101, 0x20, 107});
    }

    zzdvr(byte[] arr_b, int v) throws InvalidKeyException {
        if(arr_b.length != 0x20) {
            throw new InvalidKeyException("The key length in bytes must be 32.");
        }
        this.zzhlp = zzdvr.zzq(arr_b);
        this.zzhlq = v;
    }

    private static int rotateLeft(int v, int v1) {
        return v >>> -v1 | v << v1;
    }

    private static void zza(int[] arr_v, int v, int v1, int v2, int v3) {
        arr_v[v] += arr_v[v1];
        arr_v[v3] = zzdvr.rotateLeft(arr_v[v3] ^ arr_v[v], 16);
        arr_v[v2] += arr_v[v3];
        arr_v[v1] = zzdvr.rotateLeft(arr_v[v1] ^ arr_v[v2], 12);
        arr_v[v] += arr_v[v1];
        arr_v[v3] = zzdvr.rotateLeft(arr_v[v] ^ arr_v[v3], 8);
        arr_v[v2] += arr_v[v3];
        arr_v[v1] = zzdvr.rotateLeft(arr_v[v1] ^ arr_v[v2], 7);
    }

    static void zza(int[] arr_v, int[] arr_v1) {
        System.arraycopy(zzdvr.zzhlo, 0, arr_v, 0, zzdvr.zzhlo.length);
        System.arraycopy(arr_v1, 0, arr_v, zzdvr.zzhlo.length, 8);
    }

    final void zza(ByteBuffer byteBuffer0, byte[] arr_b) throws GeneralSecurityException {
        if(byteBuffer0.remaining() - this.zzbab() < arr_b.length) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        byte[] arr_b1 = zzdwq.zzey(this.zzbab());
        byteBuffer0.put(arr_b1);
        ByteBuffer byteBuffer1 = ByteBuffer.wrap(arr_b);
        int v = byteBuffer1.remaining();
        int v1 = v / 0x40 + 1;
        for(int v2 = 0; v2 < v1; ++v2) {
            ByteBuffer byteBuffer2 = this.zzd(arr_b1, this.zzhlq + v2);
            if(v2 == v1 - 1) {
                zzdvp.zza(byteBuffer0, byteBuffer1, byteBuffer2, v % 0x40);
            }
            else {
                zzdvp.zza(byteBuffer0, byteBuffer1, byteBuffer2, 0x40);
            }
        }
    }

    abstract int[] zzb(int[] arg1, int arg2);

    abstract int zzbab();

    static void zzc(int[] arr_v) {
        for(int v = 0; v < 10; ++v) {
            zzdvr.zza(arr_v, 0, 4, 8, 12);
            zzdvr.zza(arr_v, 1, 5, 9, 13);
            zzdvr.zza(arr_v, 2, 6, 10, 14);
            zzdvr.zza(arr_v, 3, 7, 11, 15);
            zzdvr.zza(arr_v, 0, 5, 10, 15);
            zzdvr.zza(arr_v, 1, 6, 11, 12);
            zzdvr.zza(arr_v, 2, 7, 8, 13);
            zzdvr.zza(arr_v, 3, 4, 9, 14);
        }
    }

    final ByteBuffer zzd(byte[] arr_b, int v) {
        int[] arr_v = this.zzb(zzdvr.zzq(arr_b), v);
        int[] arr_v1 = (int[])arr_v.clone();
        zzdvr.zzc(arr_v1);
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            arr_v[v1] += arr_v1[v1];
        }
        ByteBuffer byteBuffer0 = ByteBuffer.allocate(0x40).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer0.asIntBuffer().put(arr_v, 0, 16);
        return byteBuffer0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdwp
    public final byte[] zzn(byte[] arr_b) throws GeneralSecurityException {
        int v = this.zzbab();
        if(arr_b.length > 0x7FFFFFFF - v) {
            throw new GeneralSecurityException("plaintext too long");
        }
        ByteBuffer byteBuffer0 = ByteBuffer.allocate(this.zzbab() + arr_b.length);
        this.zza(byteBuffer0, arr_b);
        return byteBuffer0.array();
    }

    private static int[] zzq(byte[] arr_b) {
        IntBuffer intBuffer0 = ByteBuffer.wrap(arr_b).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
        int[] arr_v = new int[intBuffer0.remaining()];
        intBuffer0.get(arr_v);
        return arr_v;
    }
}

