package com.google.android.gms.internal.ads;

import android.view.View;
import com.google.android.gms.ads.internal.zze;
import java.util.concurrent.atomic.AtomicBoolean;

public final class zzcqz implements zze {
    private final zzbuz zzfgf;
    private final zzbqp zzfnf;
    private final zzbqg zzfng;
    private final zzbkb zzfnh;
    private final zzbuu zzfnj;
    private AtomicBoolean zzgep;

    zzcqz(zzbqg zzbqg0, zzbqp zzbqp0, zzbuz zzbuz0, zzbuu zzbuu0, zzbkb zzbkb0) {
        this.zzgep = new AtomicBoolean(false);
        this.zzfng = zzbqg0;
        this.zzfnf = zzbqp0;
        this.zzfgf = zzbuz0;
        this.zzfnj = zzbuu0;
        this.zzfnh = zzbkb0;
    }

    @Override  // com.google.android.gms.ads.internal.zze
    public final void zzg(View view0) {
        synchronized(this) {
            if(!this.zzgep.compareAndSet(false, true)) {
                return;
            }
            this.zzfnh.onAdImpression();
            this.zzfnj.zzq(view0);
        }
    }

    @Override  // com.google.android.gms.ads.internal.zze
    public final void zzjw() {
        if(this.zzgep.get()) {
            this.zzfng.onAdClicked();
        }
    }

    @Override  // com.google.android.gms.ads.internal.zze
    public final void zzjx() {
        if(this.zzgep.get()) {
            this.zzfnf.onAdImpression();
            this.zzfgf.zzaix();
        }
    }
}

