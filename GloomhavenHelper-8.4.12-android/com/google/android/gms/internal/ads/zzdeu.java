package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.formats.NativeAdOptions.Builder;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import java.util.ArrayList;
import java.util.Set;

public final class zzdeu {
    public final zzuk zzblv;
    public final zzach zzdff;
    @Nullable
    public final zzahl zzdlk;
    public final int zzggu;
    @Nullable
    public final zzwl zzgqo;
    @Nullable
    public final zzzc zzgqp;
    public final zzuh zzgqq;
    public final String zzgqr;
    public final ArrayList zzgqs;
    public final ArrayList zzgqt;
    public final zzur zzgqu;
    public final PublisherAdViewOptions zzgqv;
    public final zzwf zzgqw;
    public final Set zzgqx;

    private zzdeu(zzdew zzdew0) {
        zzzc zzzc0;
        this.zzblv = zzdew0.zzblv;
        this.zzgqr = zzdew0.zzgqr;
        this.zzgqo = zzdew0.zzgqo;
        this.zzgqq = new zzuh(zzdew0.zzgqq.versionCode, zzdew0.zzgqq.zzccm, zzdew0.zzgqq.extras, zzdew0.zzgqq.zzccn, zzdew0.zzgqq.zzcco, zzdew0.zzgqq.zzccp, zzdew0.zzgqq.zzabv, zzdew0.zzgqq.zzbkp || zzdew0.zzbkp, zzdew0.zzgqq.zzccq, zzdew0.zzgqq.zzccr, zzdew0.zzgqq.zzmk, zzdew0.zzgqq.zzccs, zzdew0.zzgqq.zzcct, zzdew0.zzgqq.zzccu, zzdew0.zzgqq.zzccv, zzdew0.zzgqq.zzccw, zzdew0.zzgqq.zzccx, zzdew0.zzgqq.zzccy, zzdew0.zzgqq.zzcda, zzdew0.zzgqq.zzabw, zzdew0.zzgqq.zzabx, zzdew0.zzgqq.zzccz);
        zzach zzach0 = null;
        if(zzdew0.zzgqp == null) {
            zzzc0 = zzdew0.zzdff == null ? null : zzdew0.zzdff.zzcwt;
        }
        else {
            zzzc0 = zzdew0.zzgqp;
        }
        this.zzgqp = zzzc0;
        this.zzgqs = zzdew0.zzgqs;
        this.zzgqt = zzdew0.zzgqt;
        if(zzdew0.zzgqs != null) {
            zzach0 = zzdew0.zzdff == null ? new zzach(new Builder().build()) : zzdew0.zzdff;
        }
        this.zzdff = zzach0;
        this.zzgqu = zzdew0.zzgqu;
        this.zzggu = zzdew0.zzggu;
        this.zzgqv = zzdew0.zzgqv;
        this.zzgqw = zzdew0.zzgqw;
        this.zzdlk = zzdew0.zzdlk;
        this.zzgqx = zzdew0.zzgqx;
    }

    zzdeu(zzdew zzdew0, zzdet zzdet0) {
        this(zzdew0);
    }

    public final zzael zzaqx() {
        return this.zzgqv == null ? null : this.zzgqv.zzjs();
    }
}

