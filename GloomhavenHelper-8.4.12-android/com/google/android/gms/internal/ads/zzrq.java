package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.appopen.AppOpenAd.AppOpenAdLoadCallback;
import com.google.android.gms.ads.appopen.AppOpenAd.AppOpenAdOrientation;

public final class zzrq {
    @AppOpenAdOrientation
    private final int orientation;
    private final zzui zzabf;
    private final zzxr zzabh;
    private zzvx zzbrh;
    private final String zzbri;
    private final AppOpenAdLoadCallback zzbrj;
    private final zzall zzbrk;
    private final Context zzur;

    public zzrq(Context context0, String s, zzxr zzxr0, @AppOpenAdOrientation int v, AppOpenAdLoadCallback appOpenAd$AppOpenAdLoadCallback0) {
        this.zzbrk = new zzall();
        this.zzur = context0;
        this.zzbri = s;
        this.zzabh = zzxr0;
        this.orientation = v;
        this.zzbrj = appOpenAd$AppOpenAdLoadCallback0;
        this.zzabf = zzui.zzcdb;
    }

    public final void zzms() {
        try {
            zzuk zzuk0 = zzuk.zzor();
            this.zzbrh = zzvh.zzpa().zza(this.zzur, zzuk0, this.zzbri, this.zzbrk);
            zzur zzur0 = new zzur(this.orientation);
            this.zzbrh.zza(zzur0);
            this.zzbrh.zza(new zzre(this.zzbrj));
            this.zzbrh.zza(zzui.zza(this.zzur, this.zzabh));
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }
}

