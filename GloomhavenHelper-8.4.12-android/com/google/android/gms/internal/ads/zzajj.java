package com.google.android.gms.internal.ads;

import java.util.Map;
import jeb.synthetic.FIN;

final class zzajj implements zzafz {
    private final zzair zzdax;
    private final zzaja zzdaz;
    private final zzajv zzdba;

    zzajj(zzaja zzaja0, zzajv zzajv0, zzair zzair0) {
        this.zzdaz = zzaja0;
        this.zzdba = zzajv0;
        this.zzdax = zzair0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzafz
    public final void zza(Object object0, Map map0) {
        zzajy zzajy0 = (zzajy)object0;
        Object object1 = this.zzdaz.lock;
        __monitor_enter(object1);
        int v = FIN.finallyOpen$NT();
        switch(this.zzdba.getStatus()) {
            case -1: 
            case 1: {
                FIN.finallyCodeBegin$NT(v);
                __monitor_exit(object1);
                FIN.finallyCodeEnd$NT(v);
                return;
            }
            default: {
                this.zzdaz.status = 0;
                this.zzdaz.zzdam.zzh(this.zzdax);
                this.zzdba.zzm(this.zzdax);
                this.zzdaz.zzdao = this.zzdba;
                zzawf.zzee("Successfully loaded JS Engine.");
                FIN.finallyExec$NT(v);
            }
        }
    }
}

