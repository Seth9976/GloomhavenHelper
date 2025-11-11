package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzcl extends zzcj {
    public String zzmx;
    public long zzmy;
    public String zzmz;
    public String zzna;
    public String zznb;

    public zzcl() {
        this.zzmx = "E";
        this.zzmy = -1L;
        this.zzmz = "E";
        this.zzna = "E";
        this.zznb = "E";
    }

    public zzcl(String s) {
        this.zzap(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzcj
    protected final void zzap(String s) {
        HashMap hashMap0 = zzcl.zzaq(s);
        if(hashMap0 != null) {
            this.zzmx = hashMap0.get(0) == null ? "E" : ((String)hashMap0.get(0));
            this.zzmy = hashMap0.get(1) == null ? -1L : ((long)(((Long)hashMap0.get(1))));
            this.zzmz = hashMap0.get(2) == null ? "E" : ((String)hashMap0.get(2));
            this.zzna = hashMap0.get(3) == null ? "E" : ((String)hashMap0.get(3));
            this.zznb = hashMap0.get(4) == null ? "E" : ((String)hashMap0.get(4));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcj
    protected final HashMap zzbl() {
        HashMap hashMap0 = new HashMap();
        hashMap0.put(0, this.zzmx);
        hashMap0.put(4, this.zznb);
        hashMap0.put(3, this.zzna);
        hashMap0.put(2, this.zzmz);
        hashMap0.put(1, this.zzmy);
        return hashMap0;
    }
}

