package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import androidx.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public final class zzbxg implements zzbqh, zzbuk {
    @Nullable
    private final View view;
    private final zzaui zzbnp;
    private final zzauf zzfhr;
    private final int zzfmh;
    private String zzfmn;
    private final Context zzur;

    public zzbxg(zzauf zzauf0, Context context0, zzaui zzaui0, @Nullable View view0, int v) {
        this.zzfhr = zzauf0;
        this.zzur = context0;
        this.zzbnp = zzaui0;
        this.view = view0;
        this.zzfmh = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdClosed() {
        this.zzfhr.zzam(false);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdLeftApplication() {
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdOpened() {
        View view0 = this.view;
        if(view0 != null && this.zzfmn != null) {
            Context context0 = view0.getContext();
            this.zzbnp.zzg(context0, this.zzfmn);
        }
        this.zzfhr.zzam(true);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onRewardedVideoCompleted() {
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onRewardedVideoStarted() {
    }

    @Override  // com.google.android.gms.internal.ads.zzbuk
    public final void zzait() {
        this.zzfmn = this.zzbnp.zzaf(this.zzur);
        String s = String.valueOf(this.zzfmn);
        String s1 = String.valueOf((this.zzfmh == 7 ? "/Rewarded" : "/Interstitial"));
        this.zzfmn = s1.length() == 0 ? new String(s) : s + s1;
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    @ParametersAreNonnullByDefault
    public final void zzb(zzarr zzarr0, String s, String s1) {
        if(this.zzbnp.zzad(this.zzur)) {
            try {
                String s2 = this.zzbnp.zzai(this.zzur);
                String s3 = zzarr0.getType();
                int v = zzarr0.getAmount();
                this.zzbnp.zza(this.zzur, s2, this.zzfhr.getAdUnitId(), s3, v);
            }
            catch(RemoteException remoteException0) {
                zzawf.zzd("Remote Exception to get reward item.", remoteException0);
            }
        }
    }
}

