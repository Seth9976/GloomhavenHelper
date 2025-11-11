package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicReference;

public final class zzdbh implements zzbqm, zzbrw, zzdcl {
    private final zzdfw zzgmy;
    private final AtomicReference zzgmz;
    private final AtomicReference zzgna;
    private final AtomicReference zzgnb;
    private zzdbh zzgnc;

    public zzdbh(zzdfw zzdfw0) {
        this.zzgmz = new AtomicReference();
        this.zzgna = new AtomicReference();
        this.zzgnb = new AtomicReference();
        this.zzgnc = null;
        this.zzgmy = zzdfw0;
    }

    public final void onAdClosed() {
        zzdbh zzdbh0;
        while((zzdbh0 = this.zzgnc) != null) {
            this = zzdbh0;
        }
        this.zzgmy.onAdClosed();
        zzdce.zza(this.zzgna, zzdbm.zzgne);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqm
    public final void onAdFailedToLoad(int v) {
        zzdbh zzdbh0;
        while((zzdbh0 = this.zzgnc) != null) {
            this = zzdbh0;
        }
        zzdbj zzdbj0 = new zzdbj(v);
        zzdce.zza(this.zzgmz, zzdbj0);
    }

    public static zzdbh zza(zzdbh zzdbh0) {
        zzdbh zzdbh1 = new zzdbh(zzdbh0.zzgmy);
        zzdbh1.zzb(zzdbh0);
        return zzdbh1;
    }

    public final void zza(zzbrw zzbrw0) {
        this.zzgnb.set(zzbrw0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbrw
    public final void zzaia() {
        zzdbh zzdbh0;
        while((zzdbh0 = this.zzgnc) != null) {
            this = zzdbh0;
        }
        zzdce.zza(this.zzgnb, zzdbl.zzgne);
    }

    @Override  // com.google.android.gms.internal.ads.zzdcl
    public final void zzb(zzdcl zzdcl0) {
        this.zzgnc = (zzdbh)zzdcl0;
    }

    public final void zzb(zzrg zzrg0) {
        zzdbh zzdbh0;
        while((zzdbh0 = this.zzgnc) != null) {
            this = zzdbh0;
        }
        zzdbk zzdbk0 = new zzdbk(zzrg0);
        zzdce.zza(this.zzgmz, zzdbk0);
    }

    public final void zzb(zzrh zzrh0) {
        this.zzgmz.set(zzrh0);
    }

    public final void zzb(zzrm zzrm0) {
        this.zzgna.set(zzrm0);
    }
}

