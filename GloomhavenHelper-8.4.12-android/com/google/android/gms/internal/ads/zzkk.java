package com.google.android.gms.internal.ads;

final class zzkk implements zzkf {
    private final zzom zzavd;
    private final int zzavm;
    private final int zzavp;
    private int zzavq;
    private int zzavr;

    public zzkk(zzke zzke0) {
        this.zzavd = zzke0.zzavd;
        this.zzavd.zzbh(12);
        this.zzavp = this.zzavd.zzjc() & 0xFF;
        this.zzavm = this.zzavd.zzjc();
    }

    @Override  // com.google.android.gms.internal.ads.zzkf
    public final int zzgu() {
        return this.zzavm;
    }

    @Override  // com.google.android.gms.internal.ads.zzkf
    public final int zzgv() {
        int v = this.zzavp;
        if(v == 8) {
            return this.zzavd.readUnsignedByte();
        }
        if(v == 16) {
            return this.zzavd.readUnsignedShort();
        }
        int v1 = this.zzavq;
        this.zzavq = v1 + 1;
        if(v1 % 2 == 0) {
            this.zzavr = this.zzavd.readUnsignedByte();
            return (this.zzavr & 0xF0) >> 4;
        }
        return this.zzavr & 15;
    }

    @Override  // com.google.android.gms.internal.ads.zzkf
    public final boolean zzgw() {
        return false;
    }
}

