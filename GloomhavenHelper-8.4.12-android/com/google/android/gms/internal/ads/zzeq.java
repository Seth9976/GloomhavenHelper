package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzeq extends zzcj {
    public Long zzyn;
    public Long zzyo;
    public Long zzyp;
    public Long zzyq;
    public Long zzyr;
    public Long zzys;
    public Long zzyt;
    public Long zzyu;
    public Long zzyv;
    public Long zzyw;
    public Long zzyx;

    public zzeq() {
    }

    public zzeq(String s) {
        this.zzap(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzcj
    protected final void zzap(String s) {
        HashMap hashMap0 = zzeq.zzaq(s);
        if(hashMap0 != null) {
            this.zzyn = (Long)hashMap0.get(0);
            this.zzyo = (Long)hashMap0.get(1);
            this.zzyp = (Long)hashMap0.get(2);
            this.zzyq = (Long)hashMap0.get(3);
            this.zzyr = (Long)hashMap0.get(4);
            this.zzys = (Long)hashMap0.get(5);
            this.zzyt = (Long)hashMap0.get(6);
            this.zzyu = (Long)hashMap0.get(7);
            this.zzyv = (Long)hashMap0.get(8);
            this.zzyw = (Long)hashMap0.get(9);
            this.zzyx = (Long)hashMap0.get(10);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcj
    protected final HashMap zzbl() {
        HashMap hashMap0 = new HashMap();
        hashMap0.put(0, this.zzyn);
        hashMap0.put(1, this.zzyo);
        hashMap0.put(2, this.zzyp);
        hashMap0.put(3, this.zzyq);
        hashMap0.put(4, this.zzyr);
        hashMap0.put(5, this.zzys);
        hashMap0.put(6, this.zzyt);
        hashMap0.put(7, this.zzyu);
        hashMap0.put(8, this.zzyv);
        hashMap0.put(9, this.zzyw);
        hashMap0.put(10, this.zzyx);
        return hashMap0;
    }
}

