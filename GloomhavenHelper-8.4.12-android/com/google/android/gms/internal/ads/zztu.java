package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zztu extends zzect {
    public Integer zzcba;
    public Integer zzcbb;
    public Integer zzcbc;

    public zztu() {
        this.zzcba = null;
        this.zzcbb = null;
        this.zzcbc = null;
        this.zzhzu = null;
        this.zzhnu = -1;
    }

    @Override  // com.google.android.gms.internal.ads.zzect
    public final void zza(zzecr zzecr0) throws IOException {
        Integer integer0 = this.zzcba;
        if(integer0 != null) {
            zzecr0.zzac(1, ((int)integer0));
        }
        Integer integer1 = this.zzcbb;
        if(integer1 != null) {
            zzecr0.zzac(2, ((int)integer1));
        }
        Integer integer2 = this.zzcbc;
        if(integer2 != null) {
            zzecr0.zzac(3, ((int)integer2));
        }
        super.zza(zzecr0);
    }

    @Override  // com.google.android.gms.internal.ads.zzect
    protected final int zzon() {
        int v = super.zzon();
        Integer integer0 = this.zzcba;
        if(integer0 != null) {
            v += zzecr.zzag(1, ((int)integer0));
        }
        Integer integer1 = this.zzcbb;
        if(integer1 != null) {
            v += zzecr.zzag(2, ((int)integer1));
        }
        return this.zzcbc == null ? v : v + zzecr.zzag(3, ((int)this.zzcbc));
    }
}

