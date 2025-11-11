package com.google.android.gms.internal.ads;

public final class zzom {
    public byte[] data;
    private int limit;
    private int position;

    public zzom() {
    }

    public zzom(int v) {
        this.data = new byte[v];
        this.limit = v;
    }

    public zzom(byte[] arr_b) {
        this.data = arr_b;
        this.limit = arr_b.length;
    }

    public final int capacity() {
        return this.data == null ? 0 : this.data.length;
    }

    public final int getPosition() {
        return this.position;
    }

    public final int limit() {
        return this.limit;
    }

    public final int readInt() {
        int v = this.position;
        int v1 = this.position;
        int v2 = this.position;
        int v3 = this.position;
        this.position = v3 + 1;
        return this.data[v3] & 0xFF | ((this.data[v] & 0xFF) << 24 | (this.data[v1] & 0xFF) << 16 | (this.data[v2] & 0xFF) << 8);
    }

    public final long readLong() {
        int v = this.position;
        int v1 = this.position;
        int v2 = this.position;
        int v3 = this.position;
        int v4 = this.position;
        int v5 = this.position;
        int v6 = this.position;
        int v7 = this.position;
        this.position = v7 + 1;
        return (((long)this.data[v]) & 0xFFL) << 56 | (((long)this.data[v1]) & 0xFFL) << 0x30 | (((long)this.data[v2]) & 0xFFL) << 40 | (((long)this.data[v3]) & 0xFFL) << 0x20 | (((long)this.data[v4]) & 0xFFL) << 24 | (((long)this.data[v5]) & 0xFFL) << 16 | (((long)this.data[v6]) & 0xFFL) << 8 | 0xFFL & ((long)this.data[v7]);
    }

    public final short readShort() {
        int v = this.position;
        int v1 = this.position;
        this.position = v1 + 1;
        return (short)(this.data[v1] & 0xFF | (this.data[v] & 0xFF) << 8);
    }

    public final int readUnsignedByte() {
        int v = this.position;
        this.position = v + 1;
        return this.data[v] & 0xFF;
    }

    public final int readUnsignedShort() {
        int v = this.position;
        int v1 = this.position;
        this.position = v1 + 1;
        return this.data[v1] & 0xFF | (this.data[v] & 0xFF) << 8;
    }

    public final void reset() {
        this.position = 0;
        this.limit = 0;
    }

    public final void reset(int v) {
        this.zzb((this.capacity() >= v ? this.data : new byte[v]), v);
    }

    public final void zzb(byte[] arr_b, int v) {
        this.data = arr_b;
        this.limit = v;
        this.position = 0;
    }

    public final void zzbg(int v) {
        zzob.checkArgument(v >= 0 && v <= this.data.length);
        this.limit = v;
    }

    public final void zzbh(int v) {
        zzob.checkArgument(v >= 0 && v <= this.limit);
        this.position = v;
    }

    public final void zzbi(int v) {
        this.zzbh(this.position + v);
    }

    public final String zzbj(int v) {
        if(v == 0) {
            return "";
        }
        int v1 = this.position + v - 1;
        String s = new String(this.data, this.position, (v1 >= this.limit || this.data[v1] != 0 ? v : v - 1));
        this.position += v;
        return s;
    }

    public final void zze(byte[] arr_b, int v, int v1) {
        System.arraycopy(this.data, this.position, arr_b, v, v1);
        this.position += v1;
    }

    public final int zzix() {
        return this.limit - this.position;
    }

    public final int zziy() {
        int v = this.position;
        int v1 = this.position;
        this.position = v1 + 1;
        return (this.data[v1] & 0xFF) << 8 | this.data[v] & 0xFF;
    }

    public final long zziz() {
        int v = this.position;
        int v1 = this.position;
        int v2 = this.position;
        int v3 = this.position;
        this.position = v3 + 1;
        return (((long)this.data[v]) & 0xFFL) << 24 | (((long)this.data[v1]) & 0xFFL) << 16 | (((long)this.data[v2]) & 0xFFL) << 8 | 0xFFL & ((long)this.data[v3]);
    }

    public final long zzja() {
        int v = this.position;
        int v1 = this.position;
        int v2 = this.position;
        int v3 = this.position;
        this.position = v3 + 1;
        return ((long)this.data[v]) & 0xFFL | (((long)this.data[v1]) & 0xFFL) << 8 | (((long)this.data[v2]) & 0xFFL) << 16 | (0xFFL & ((long)this.data[v3])) << 24;
    }

    public final int zzjb() {
        int v = this.position;
        int v1 = this.position;
        this.position += 2;
        return this.data[v1] & 0xFF | (this.data[v] & 0xFF) << 8;
    }

    public final int zzjc() {
        int v = this.readInt();
        if(v < 0) {
            throw new IllegalStateException("Top bit not zero: " + v);
        }
        return v;
    }

    public final long zzjd() {
        long v = this.readLong();
        if(v < 0L) {
            throw new IllegalStateException("Top bit not zero: " + v);
        }
        return v;
    }
}

