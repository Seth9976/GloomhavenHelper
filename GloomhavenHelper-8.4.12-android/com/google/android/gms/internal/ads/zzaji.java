package com.google.android.gms.internal.ads;

import java.util.Map;

final class zzaji implements zzafz {
    private final zzdq zzdaw;
    private final zzair zzdax;
    private final zzayq zzday;
    private final zzaja zzdaz;

    zzaji(zzaja zzaja0, zzdq zzdq0, zzair zzair0, zzayq zzayq0) {
        this.zzdaz = zzaja0;
        this.zzdaw = zzdq0;
        this.zzdax = zzair0;
        this.zzday = zzayq0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        zzajy zzajy0 = (zzajy)object0;
        synchronized(this.zzdaz.lock) {
            zzawf.zzez("JS Engine is requesting an update");
            if(this.zzdaz.status == 0) {
                zzawf.zzez("Starting reload.");
                this.zzdaz.status = 2;
                this.zzdaz.zza(this.zzdaw);
            }
            this.zzdax.zzb("/requestReload", ((zzafz)this.zzday.get()));
        }
    }
}

