package com.google.android.gms.internal.ads;

import jeb.synthetic.FIN;

final class zzajl implements Runnable {
    private final zzair zzdax;
    private final zzaja zzdaz;
    private final zzajv zzdba;

    zzajl(zzaja zzaja0, zzajv zzajv0, zzair zzair0) {
        this.zzdaz = zzaja0;
        this.zzdba = zzajv0;
        this.zzdax = zzair0;
        super();
    }

    @Override
    public final void run() {
        Object object0 = this.zzdaz.lock;
        __monitor_enter(object0);
        int v = FIN.finallyOpen$NT();
        switch(this.zzdba.getStatus()) {
            case -1: 
            case 1: {
                FIN.finallyCodeBegin$NT(v);
                __monitor_exit(object0);
                FIN.finallyCodeEnd$NT(v);
                return;
            }
            default: {
                this.zzdba.reject();
                this.zzdax.getClass();
                Runnable runnable0 = zzajk.zzb(this.zzdax);
                zzazq.zzdxo.execute(runnable0);
                zzawf.zzee("Could not receive loaded message in a timely manner. Rejecting.");
                FIN.finallyExec$NT(v);
            }
        }
    }
}

