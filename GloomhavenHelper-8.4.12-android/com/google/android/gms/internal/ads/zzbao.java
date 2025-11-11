package com.google.android.gms.internal.ads;

final class zzbao implements Runnable {
    private final zzbak zzdyt;
    private final String zzdyu;
    private final String zzdyv;

    zzbao(zzbak zzbak0, String s, String s1) {
        this.zzdyt = zzbak0;
        this.zzdyu = s;
        this.zzdyv = s1;
        super();
    }

    @Override
    public final void run() {
        if(this.zzdyt.zzdys != null) {
            this.zzdyt.zzdys.zzm(this.zzdyu, this.zzdyv);
        }
    }
}

