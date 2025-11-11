package com.google.android.gms.internal.ads;

public final class zzbdk extends zzbda {
    public zzbdk(zzbbm zzbbm0) {
        super(zzbbm0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbda
    public final void abort() {
    }

    @Override  // com.google.android.gms.internal.ads.zzbda
    public final boolean zzfj(String s) {
        zzbbm zzbbm0 = (zzbbm)this.zzeem.get();
        if(zzbbm0 != null) {
            zzbbm0.zza(this.zzfk(s), this);
        }
        zzawf.zzfa("VideoStreamNoopCache is doing nothing.");
        this.zza(s, this.zzfk(s), "noop", "Noop cache is a noop.");
        return false;
    }
}

