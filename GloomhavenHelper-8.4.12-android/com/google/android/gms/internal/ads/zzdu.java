package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzdu extends zzcj {
    public long zzxb;
    public long zzxc;

    public zzdu() {
        this.zzxb = -1L;
        this.zzxc = -1L;
    }

    public zzdu(String s) {
        this.zzap(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzcj
    protected final void zzap(String s) {
        HashMap hashMap0 = zzdu.zzaq(s);
        if(hashMap0 != null) {
            this.zzxb = (long)(((Long)hashMap0.get(0)));
            this.zzxc = (long)(((Long)hashMap0.get(1)));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcj
    protected final HashMap zzbl() {
        HashMap hashMap0 = new HashMap();
        hashMap0.put(0, this.zzxb);
        hashMap0.put(1, this.zzxc);
        return hashMap0;
    }
}

