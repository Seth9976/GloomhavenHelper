package com.google.android.gms.internal.ads;

final class zzdmz implements Runnable {
    private final zzdof zzhcb;
    private final int zzhcc;
    private final zzdna zzhcd;

    zzdmz(zzdna zzdna0, zzdof zzdof0, int v) {
        this.zzhcd = zzdna0;
        this.zzhcb = zzdof0;
        this.zzhcc = v;
        super();
    }

    @Override
    public final void run() {
        try {
            if(this.zzhcb.isCancelled()) {
                zzdna.zza(this.zzhcd, null);
                this.zzhcd.cancel(false);
            }
            else {
                zzdna.zza(this.zzhcd, this.zzhcc, this.zzhcb);
            }
        }
        finally {
            zzdna.zzb(this.zzhcd, null);
        }
    }
}

