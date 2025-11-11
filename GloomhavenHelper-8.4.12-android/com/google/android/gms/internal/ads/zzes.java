package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzes extends zzcj {
    public Long zzzj;
    public Long zzzk;
    public Long zzzl;
    public Long zzzm;

    public zzes() {
    }

    public zzes(String s) {
        this.zzap(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzcj
    protected final void zzap(String s) {
        HashMap hashMap0 = zzes.zzaq(s);
        if(hashMap0 != null) {
            this.zzzj = (Long)hashMap0.get(0);
            this.zzzk = (Long)hashMap0.get(1);
            this.zzzl = (Long)hashMap0.get(2);
            this.zzzm = (Long)hashMap0.get(3);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcj
    protected final HashMap zzbl() {
        HashMap hashMap0 = new HashMap();
        hashMap0.put(0, this.zzzj);
        hashMap0.put(1, this.zzzk);
        hashMap0.put(2, this.zzzl);
        hashMap0.put(3, this.zzzm);
        return hashMap0;
    }
}

