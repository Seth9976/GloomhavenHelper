package com.google.android.gms.internal.ads;

final class zzbaq implements Runnable {
    private final zzbak zzdyt;
    private final int zzdyw;
    private final int zzdyx;

    zzbaq(zzbak zzbak0, int v, int v1) {
        this.zzdyt = zzbak0;
        this.zzdyw = v;
        this.zzdyx = v1;
        super();
    }

    @Override
    public final void run() {
        if(this.zzdyt.zzdys != null) {
            this.zzdyt.zzdys.zzk(this.zzdyw, this.zzdyx);
        }
    }
}

