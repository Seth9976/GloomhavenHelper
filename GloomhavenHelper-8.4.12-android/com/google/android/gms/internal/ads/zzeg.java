package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzeg extends zzcj {
    public Long zzxl;
    public Boolean zzxm;
    public Boolean zzxn;

    public zzeg() {
    }

    public zzeg(String s) {
        this.zzap(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzcj
    protected final void zzap(String s) {
        HashMap hashMap0 = zzeg.zzaq(s);
        if(hashMap0 != null) {
            this.zzxl = (Long)hashMap0.get(0);
            this.zzxm = (Boolean)hashMap0.get(1);
            this.zzxn = (Boolean)hashMap0.get(2);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcj
    protected final HashMap zzbl() {
        HashMap hashMap0 = new HashMap();
        hashMap0.put(0, this.zzxl);
        hashMap0.put(1, this.zzxm);
        hashMap0.put(2, this.zzxn);
        return hashMap0;
    }
}

