package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzej extends zzcj {
    public Long zzye;
    public Long zzyf;

    public zzej() {
    }

    public zzej(String s) {
        this.zzap(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzcj
    protected final void zzap(String s) {
        HashMap hashMap0 = zzej.zzaq(s);
        if(hashMap0 != null) {
            this.zzye = (Long)hashMap0.get(0);
            this.zzyf = (Long)hashMap0.get(1);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcj
    protected final HashMap zzbl() {
        HashMap hashMap0 = new HashMap();
        hashMap0.put(0, this.zzye);
        hashMap0.put(1, this.zzyf);
        return hashMap0;
    }
}

