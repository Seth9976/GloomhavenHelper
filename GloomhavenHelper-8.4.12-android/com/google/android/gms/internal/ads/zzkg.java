package com.google.android.gms.internal.ads;

final class zzkg {
    public int index;
    public final int length;
    public int zzave;
    public long zzavf;
    private final boolean zzavg;
    private final zzom zzavh;
    private final zzom zzavi;
    private int zzavj;
    private int zzavk;

    public zzkg(zzom zzom0, zzom zzom1, boolean z) {
        this.zzavi = zzom0;
        this.zzavh = zzom1;
        this.zzavg = z;
        zzom1.zzbh(12);
        this.length = zzom1.zzjc();
        zzom0.zzbh(12);
        this.zzavk = zzom0.zzjc();
        zzob.checkState(zzom0.readInt() == 1, "first_chunk must be 1");
        this.index = -1;
    }

    public final boolean zzgx() {
        int v = this.index + 1;
        this.index = v;
        if(v == this.length) {
            return false;
        }
        this.zzavf = this.zzavg ? this.zzavh.zzjd() : this.zzavh.zziz();
        if(this.index == this.zzavj) {
            this.zzave = this.zzavi.zzjc();
            this.zzavi.zzbi(4);
            int v1 = this.zzavk - 1;
            this.zzavk = v1;
            this.zzavj = v1 <= 0 ? -1 : this.zzavi.zzjc() - 1;
        }
        return true;
    }
}

