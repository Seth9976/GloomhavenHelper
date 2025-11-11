package com.google.android.gms.internal.ads;

import java.io.IOException;

public abstract class zzdxz {
    int zzhom;
    int zzhon;
    int zzhoo;
    zzdyg zzhop;
    private boolean zzhoq;

    private zzdxz() {
        this.zzhon = 100;
        this.zzhoo = 0x7FFFFFFF;
        this.zzhoq = false;
    }

    zzdxz(zzdyc zzdyc0) {
    }

    public abstract double readDouble() throws IOException;

    public abstract float readFloat() throws IOException;

    public abstract String readString() throws IOException;

    static zzdxz zzb(byte[] arr_b, int v, int v1, boolean z) {
        zzdxz zzdxz0 = new zzdyb(arr_b, v, v1, z, null);
        try {
            zzdxz0.zzfj(v1);
            return zzdxz0;
        }
        catch(zzdzh zzdzh0) {
            throw new IllegalArgumentException(zzdzh0);
        }
    }

    public abstract int zzbbb() throws IOException;

    public abstract long zzbbc() throws IOException;

    public abstract long zzbbd() throws IOException;

    public abstract int zzbbe() throws IOException;

    public abstract long zzbbf() throws IOException;

    public abstract int zzbbg() throws IOException;

    public abstract boolean zzbbh() throws IOException;

    public abstract String zzbbi() throws IOException;

    public abstract zzdxn zzbbj() throws IOException;

    public abstract int zzbbk() throws IOException;

    public abstract int zzbbl() throws IOException;

    public abstract int zzbbm() throws IOException;

    public abstract long zzbbn() throws IOException;

    public abstract int zzbbo() throws IOException;

    public abstract long zzbbp() throws IOException;

    abstract long zzbbq() throws IOException;

    public abstract boolean zzbbr() throws IOException;

    public abstract int zzbbs();

    public static long zzfg(long v) [...] // Inlined contents

    public abstract void zzfh(int arg1) throws zzdzh;

    public abstract boolean zzfi(int arg1) throws IOException;

    public abstract int zzfj(int arg1) throws zzdzh;

    public abstract void zzfk(int arg1);

    public static int zzfl(int v) [...] // Inlined contents
}

