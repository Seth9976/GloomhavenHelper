package com.google.android.gms.internal.ads;

import android.view.View;
import androidx.annotation.Nullable;

public final class zzbla extends zzbnf {
    private final View view;
    @Nullable
    private final zzbdv zzdae;
    private final zzdeh zzffr;
    private final int zzffs;
    private final boolean zzfft;
    @Nullable
    private zzrm zzffx;

    zzbla(zzbne zzbne0, View view0, @Nullable zzbdv zzbdv0, zzdeh zzdeh0, int v, boolean z) {
        super(zzbne0);
        this.view = view0;
        this.zzdae = zzbdv0;
        this.zzffr = zzdeh0;
        this.zzffs = v;
        this.zzfft = z;
    }

    public final void zza(zzrb zzrb0) {
        zzbdv zzbdv0 = this.zzdae;
        if(zzbdv0 != null) {
            zzbdv0.zza(zzrb0);
        }
    }

    public final void zza(zzrm zzrm0) {
        this.zzffx = zzrm0;
    }

    public final boolean zzaay() {
        return this.zzdae != null && this.zzdae.zzaaf() != null && this.zzdae.zzaaf().zzaay();
    }

    public final int zzagf() {
        return this.zzffs;
    }

    public final boolean zzagg() {
        return this.zzfft;
    }

    public final zzdeh zzagl() {
        return zzdex.zza(this.zzfhg.zzgpp, this.zzffr);
    }

    public final View zzagm() {
        return this.view;
    }

    public final boolean zzagn() {
        return this.zzdae != null && this.zzdae.zzaah();
    }

    @Nullable
    public final zzrm zzago() {
        return this.zzffx;
    }
}

