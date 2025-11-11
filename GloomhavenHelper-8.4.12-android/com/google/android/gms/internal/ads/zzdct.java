package com.google.android.gms.internal.ads;

import androidx.annotation.NonNull;
import com.google.android.gms.ads.reward.AdMetadataListener;
import java.util.concurrent.atomic.AtomicReference;

public final class zzdct extends AdMetadataListener implements zzbqh, zzbqm, zzbqq, zzbrn, zzbsg, zzdcl {
    private final zzdfw zzgmy;
    private final AtomicReference zzgoj;
    private final AtomicReference zzgok;
    private final AtomicReference zzgol;
    private final AtomicReference zzgom;
    private final AtomicReference zzgon;
    private final AtomicReference zzgoo;
    private final AtomicReference zzgop;
    private zzdct zzgoq;

    public zzdct(zzdfw zzdfw0) {
        this.zzgoj = new AtomicReference();
        this.zzgok = new AtomicReference();
        this.zzgol = new AtomicReference();
        this.zzgom = new AtomicReference();
        this.zzgon = new AtomicReference();
        this.zzgoo = new AtomicReference();
        this.zzgop = new AtomicReference();
        this.zzgoq = null;
        this.zzgmy = zzdfw0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdClosed() {
        zzdct zzdct0;
        while((zzdct0 = this.zzgoq) != null) {
            this = zzdct0;
        }
        this.zzgmy.onAdClosed();
        zzdce.zza(this.zzgol, zzddk.zzgne);
        zzdce.zza(this.zzgom, zzddj.zzgne);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqm
    public final void onAdFailedToLoad(int v) {
        zzdct zzdct0;
        while((zzdct0 = this.zzgoq) != null) {
            this = zzdct0;
        }
        zzddg zzddg0 = new zzddg(v);
        zzdce.zza(this.zzgok, zzddg0);
        zzddf zzddf0 = new zzddf(v);
        zzdce.zza(this.zzgom, zzddf0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdLeftApplication() {
        zzdct zzdct0;
        while((zzdct0 = this.zzgoq) != null) {
            this = zzdct0;
        }
        zzdce.zza(this.zzgom, zzddm.zzgne);
    }

    @Override  // com.google.android.gms.internal.ads.zzbrn
    public final void onAdLoaded() {
        zzdct zzdct0;
        while((zzdct0 = this.zzgoq) != null) {
            this = zzdct0;
        }
        zzdce.zza(this.zzgok, zzdcw.zzgne);
        zzdce.zza(this.zzgom, zzdcv.zzgne);
    }

    @Override  // com.google.android.gms.ads.reward.AdMetadataListener
    public final void onAdMetadataChanged() {
        zzdct zzdct0 = this.zzgoq;
        if(zzdct0 != null) {
            zzdct0.onAdMetadataChanged();
            return;
        }
        zzdce.zza(this.zzgoj, zzdde.zzgne);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdOpened() {
        zzdct zzdct0;
        while((zzdct0 = this.zzgoq) != null) {
            this = zzdct0;
        }
        zzdce.zza(this.zzgol, zzddi.zzgne);
        zzdce.zza(this.zzgom, zzddh.zzgne);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onRewardedVideoCompleted() {
        zzdct zzdct0;
        while((zzdct0 = this.zzgoq) != null) {
            this = zzdct0;
        }
        zzdce.zza(this.zzgom, zzddc.zzgne);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onRewardedVideoStarted() {
        zzdct zzdct0;
        while((zzdct0 = this.zzgoq) != null) {
            this = zzdct0;
        }
        zzdce.zza(this.zzgom, zzddl.zzgne);
    }

    public static zzdct zza(zzdct zzdct0) {
        zzdct zzdct1 = new zzdct(zzdct0.zzgmy);
        zzdct1.zzb(zzdct0);
        return zzdct1;
    }

    public final void zza(AdMetadataListener adMetadataListener0) {
        this.zzgoj.set(adMetadataListener0);
    }

    public final void zza(zzatb zzatb0) {
        this.zzgok.set(zzatb0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void zzb(zzarr zzarr0, String s, String s1) {
        zzdct zzdct0;
        while((zzdct0 = this.zzgoq) != null) {
            this = zzdct0;
        }
        zzdcy zzdcy0 = new zzdcy(zzarr0);
        zzdce.zza(this.zzgol, zzdcy0);
        zzdcx zzdcx0 = new zzdcx(zzarr0, s, s1);
        zzdce.zza(this.zzgon, zzdcx0);
        zzdda zzdda0 = new zzdda(zzarr0);
        zzdce.zza(this.zzgom, zzdda0);
        zzdcz zzdcz0 = new zzdcz(zzarr0, s, s1);
        zzdce.zza(this.zzgoo, zzdcz0);
    }

    @Deprecated
    public final void zzb(zzarw zzarw0) {
        this.zzgoo.set(zzarw0);
    }

    @Deprecated
    public final void zzb(zzasb zzasb0) {
        this.zzgom.set(zzasb0);
    }

    public final void zzb(zzasy zzasy0) {
        this.zzgol.set(zzasy0);
    }

    public final void zzb(zzatg zzatg0) {
        this.zzgon.set(zzatg0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdcl
    public final void zzb(zzdcl zzdcl0) {
        this.zzgoq = (zzdct)zzdcl0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbsg
    public final void zzb(@NonNull zzum zzum0) {
        zzdct zzdct0;
        while((zzdct0 = this.zzgoq) != null) {
            this = zzdct0;
        }
        zzddd zzddd0 = new zzddd(zzum0);
        zzdce.zza(this.zzgop, zzddd0);
    }

    public final void zzc(zzxd zzxd0) {
        this.zzgop.set(zzxd0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqq
    public final void zzco(int v) {
        zzdct zzdct0;
        while((zzdct0 = this.zzgoq) != null) {
            this = zzdct0;
        }
        zzddb zzddb0 = new zzddb(v);
        zzdce.zza(this.zzgol, zzddb0);
    }
}

