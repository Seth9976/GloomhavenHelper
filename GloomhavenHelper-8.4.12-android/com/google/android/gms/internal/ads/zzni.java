package com.google.android.gms.internal.ads;

public abstract class zzni {
    private zznh zzbem;

    protected final void invalidate() {
        zznh zznh0 = this.zzbem;
        if(zznh0 != null) {
            zznh0.zzek();
        }
    }

    public abstract zznk zza(zzhh[] arg1, zzmu arg2) throws zzgk;

    public final void zza(zznh zznh0) {
        this.zzbem = zznh0;
    }

    public abstract void zzd(Object arg1);
}

