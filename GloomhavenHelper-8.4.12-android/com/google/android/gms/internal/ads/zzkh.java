package com.google.android.gms.internal.ads;

final class zzkh implements zzkf {
    private final zzom zzavd;
    private final int zzavl;
    private final int zzavm;

    public zzkh(zzke zzke0) {
        this.zzavd = zzke0.zzavd;
        this.zzavd.zzbh(12);
        this.zzavl = this.zzavd.zzjc();
        this.zzavm = this.zzavd.zzjc();
    }

    @Override  // com.google.android.gms.internal.ads.zzkf
    public final int zzgu() {
        return this.zzavm;
    }

    @Override  // com.google.android.gms.internal.ads.zzkf
    public final int zzgv() {
        return this.zzavl == 0 ? this.zzavd.zzjc() : this.zzavl;
    }

    @Override  // com.google.android.gms.internal.ads.zzkf
    public final boolean zzgw() {
        return this.zzavl != 0;
    }
}

