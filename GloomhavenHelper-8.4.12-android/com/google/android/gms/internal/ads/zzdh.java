package com.google.android.gms.internal.ads;

final class zzdh implements Runnable {
    private final zzde zzve;

    zzdh(zzde zzde0) {
        this.zzve = zzde0;
        super();
    }

    @Override
    public final void run() {
        boolean z1;
        boolean z;
        if(this.zzve.zzvb != null) {
            return;
        }
        synchronized(zzde.zzuz) {
            if(this.zzve.zzvb != null) {
                return;
            }
            try {
                z = false;
                z1 = false;
                z1 = ((Boolean)zzzx.zzclr.get()).booleanValue();
            }
            catch(IllegalStateException unused_ex) {
            }
            if(z1) {
                try {
                    zzde.zzva = new zzss(this.zzve.zzuy.zzur, "ADSHIELD", null);
                label_15:
                    z = z1;
                }
                catch(Throwable unused_ex) {
                }
            }
            else {
                goto label_15;
            }
            this.zzve.zzvb = Boolean.valueOf(z);
            zzde.zzuz.open();
        }
    }
}

