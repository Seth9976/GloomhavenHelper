package com.google.android.gms.internal.ads;

import java.io.IOException;

public class zzect extends zzeda {
    protected zzecv zzhzu;

    @Override  // com.google.android.gms.internal.ads.zzeda
    public Object clone() throws CloneNotSupportedException {
        zzect zzect0 = (zzect)super.zzbfq();
        zzecx.zza(this, zzect0);
        return zzect0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeda
    public void zza(zzecr zzecr0) throws IOException {
        if(this.zzhzu == null) {
            return;
        }
        for(int v = 0; v < this.zzhzu.size(); ++v) {
            this.zzhzu.zzhd(v).zza(zzecr0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzeda
    public final zzeda zzbfq() throws CloneNotSupportedException {
        return (zzect)this.clone();
    }

    @Override  // com.google.android.gms.internal.ads.zzeda
    protected int zzon() {
        if(this.zzhzu != null) {
            for(int v = 0; v < this.zzhzu.size(); ++v) {
                this.zzhzu.zzhd(v).zzon();
            }
        }
        return 0;
    }
}

