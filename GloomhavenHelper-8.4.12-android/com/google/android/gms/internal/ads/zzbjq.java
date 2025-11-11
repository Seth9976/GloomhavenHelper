package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import javax.annotation.concurrent.GuardedBy;

public final class zzbjq implements zzbqh, zzbqu, zzbrn, zztz {
    @Nullable
    private final View view;
    private final zzdeq zzfdp;
    private final zzdei zzfdq;
    private final zzdis zzfdr;
    private final zzdq zzfds;
    @GuardedBy("this")
    private boolean zzfdt;
    @GuardedBy("this")
    private boolean zzfdu;
    private final Context zzur;

    public zzbjq(Context context0, zzdeq zzdeq0, zzdei zzdei0, zzdis zzdis0, @Nullable View view0, zzdq zzdq0) {
        this.zzur = context0;
        this.zzfdp = zzdeq0;
        this.zzfdq = zzdei0;
        this.zzfdr = zzdis0;
        this.zzfds = zzdq0;
        this.view = view0;
    }

    @Override  // com.google.android.gms.internal.ads.zztz
    public final void onAdClicked() {
        this.zzfdr.zza(this.zzfdp, this.zzfdq, this.zzfdq.zzddp);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdClosed() {
    }

    @Override  // com.google.android.gms.internal.ads.zzbqu
    public final void onAdImpression() {
        synchronized(this) {
            if(!this.zzfdu) {
                String s = ((Boolean)zzvh.zzpd().zzd(zzzx.zzcmo)).booleanValue() ? this.zzfds.zzcb().zza(this.zzur, this.view, null) : null;
                this.zzfdr.zza(this.zzfdp, this.zzfdq, false, s, this.zzfdq.zzddq);
                this.zzfdu = true;
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdLeftApplication() {
    }

    @Override  // com.google.android.gms.internal.ads.zzbrn
    public final void onAdLoaded() {
        synchronized(this) {
            if(this.zzfdt) {
                ArrayList arrayList0 = new ArrayList(this.zzfdq.zzddq);
                arrayList0.addAll(this.zzfdq.zzgpm);
                this.zzfdr.zza(this.zzfdp, this.zzfdq, true, null, arrayList0);
            }
            else {
                this.zzfdr.zza(this.zzfdp, this.zzfdq, this.zzfdq.zzgpo);
                this.zzfdr.zza(this.zzfdp, this.zzfdq, this.zzfdq.zzgpm);
            }
            this.zzfdt = true;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdOpened() {
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onRewardedVideoCompleted() {
        this.zzfdr.zza(this.zzfdp, this.zzfdq, this.zzfdq.zzgpn);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onRewardedVideoStarted() {
        this.zzfdr.zza(this.zzfdp, this.zzfdq, this.zzfdq.zzdme);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void zzb(zzarr zzarr0, String s, String s1) {
        this.zzfdr.zza(this.zzfdp, this.zzfdq, this.zzfdq.zzdmf, zzarr0);
    }
}

