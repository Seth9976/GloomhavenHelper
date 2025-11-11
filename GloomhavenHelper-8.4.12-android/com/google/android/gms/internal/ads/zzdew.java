package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class zzdew {
    private boolean zzbkp;
    private zzuk zzblv;
    private zzach zzdff;
    private zzahl zzdlk;
    private int zzggu;
    private zzwl zzgqo;
    private zzzc zzgqp;
    private zzuh zzgqq;
    private String zzgqr;
    private ArrayList zzgqs;
    private ArrayList zzgqt;
    private zzur zzgqu;
    private PublisherAdViewOptions zzgqv;
    @Nullable
    private zzwf zzgqw;
    public final Set zzgqx;

    public zzdew() {
        this.zzggu = 1;
        this.zzgqx = new HashSet();
    }

    public final zzuh zzaqz() {
        return this.zzgqq;
    }

    public final String zzara() {
        return this.zzgqr;
    }

    public final zzdeu zzarb() {
        Preconditions.checkNotNull(this.zzgqr, "ad unit must not be null");
        Preconditions.checkNotNull(this.zzblv, "ad size must not be null");
        Preconditions.checkNotNull(this.zzgqq, "ad request must not be null");
        return new zzdeu(this, null);
    }

    public final zzdew zzb(PublisherAdViewOptions publisherAdViewOptions0) {
        this.zzgqv = publisherAdViewOptions0;
        if(publisherAdViewOptions0 != null) {
            this.zzbkp = publisherAdViewOptions0.getManualImpressionsEnabled();
            this.zzgqw = publisherAdViewOptions0.zzjr();
        }
        return this;
    }

    public final zzdew zzb(zzach zzach0) {
        this.zzdff = zzach0;
        return this;
    }

    public final zzdew zzb(zzahl zzahl0) {
        this.zzdlk = zzahl0;
        this.zzgqp = new zzzc(false, true, false);
        return this;
    }

    public final zzdew zzb(zzur zzur0) {
        this.zzgqu = zzur0;
        return this;
    }

    public final zzdew zzb(ArrayList arrayList0) {
        this.zzgqs = arrayList0;
        return this;
    }

    public final zzdew zzbo(boolean z) {
        this.zzbkp = z;
        return this;
    }

    public final zzdew zzc(zzwl zzwl0) {
        this.zzgqo = zzwl0;
        return this;
    }

    public final zzdew zzc(zzzc zzzc0) {
        this.zzgqp = zzzc0;
        return this;
    }

    public final zzdew zzc(ArrayList arrayList0) {
        this.zzgqt = arrayList0;
        return this;
    }

    public final zzdew zzd(zzuk zzuk0) {
        this.zzblv = zzuk0;
        return this;
    }

    public final zzdew zzdl(int v) {
        this.zzggu = v;
        return this;
    }

    public final zzdew zzg(zzuh zzuh0) {
        this.zzgqq = zzuh0;
        return this;
    }

    public final zzdew zzgn(String s) {
        this.zzgqr = s;
        return this;
    }

    public final zzuk zzke() {
        return this.zzblv;
    }
}

