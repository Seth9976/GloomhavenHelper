package com.google.android.gms.internal.ads;

public class zzio {
    private int flags;

    public void clear() {
        this.flags = 0;
    }

    public final void setFlags(int v) {
        this.flags = v;
    }

    public final boolean zzgc() {
        return this.zzx(0x80000000);
    }

    public final boolean zzgd() {
        return this.zzx(4);
    }

    public final boolean zzge() {
        return this.zzx(1);
    }

    public final void zzw(int v) {
        this.flags |= 0x80000000;
    }

    protected final boolean zzx(int v) {
        return (this.flags & v) == v;
    }
}

