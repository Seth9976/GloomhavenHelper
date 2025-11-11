package com.google.android.gms.internal.ads;

public final class zzdxe {
    private final byte[] zzhnq;
    private int zzhnr;
    private int zzhns;

    public zzdxe(byte[] arr_b) {
        this.zzhnq = new byte[0x100];
        for(int v = 0; v < 0x100; ++v) {
            this.zzhnq[v] = (byte)v;
        }
        int v1 = 0;
        for(int v2 = 0; v2 < 0x100; ++v2) {
            v1 = v1 + this.zzhnq[v2] + arr_b[v2 % arr_b.length] & 0xFF;
            byte b = this.zzhnq[v2];
            this.zzhnq[v2] = this.zzhnq[v1];
            this.zzhnq[v1] = b;
        }
        this.zzhnr = 0;
        this.zzhns = 0;
    }

    public final void zzs(byte[] arr_b) {
        int v = this.zzhnr;
        int v1 = this.zzhns;
        for(int v2 = 0; v2 < arr_b.length; ++v2) {
            v = v + 1 & 0xFF;
            v1 = v1 + this.zzhnq[v] & 0xFF;
            byte b = this.zzhnq[v];
            this.zzhnq[v] = this.zzhnq[v1];
            this.zzhnq[v1] = b;
            arr_b[v2] = (byte)(this.zzhnq[this.zzhnq[v] + this.zzhnq[v1] & 0xFF] ^ arr_b[v2]);
        }
        this.zzhnr = v;
        this.zzhns = v1;
    }
}

