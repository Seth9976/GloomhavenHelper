package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.HashMap;

public final class zzbxa implements zzp, zzbrn {
    private final zzazo zzblu;
    @Nullable
    private final zzbdv zzdae;
    private final zzdei zzfhg;
    @Nullable
    @VisibleForTesting
    private IObjectWrapper zzfhh;
    private final int zzfmh;
    private final Context zzur;

    public zzbxa(Context context0, @Nullable zzbdv zzbdv0, zzdei zzdei0, zzazo zzazo0, int v) {
        this.zzur = context0;
        this.zzdae = zzbdv0;
        this.zzfhg = zzdei0;
        this.zzblu = zzazo0;
        this.zzfmh = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzbrn
    public final void onAdLoaded() {
        if((this.zzfmh == 3 || this.zzfmh == 7) && this.zzfhg.zzdmn && this.zzdae != null && zzq.zzlk().zzq(this.zzur)) {
            String s = this.zzblu.zzdxf + "." + this.zzblu.zzdxg;
            String s1 = this.zzfhg.zzgqa.optInt("media_type", -1) == 0 ? null : "javascript";
            this.zzfhh = zzq.zzlk().zza(s, this.zzdae.getWebView(), "", "javascript", s1);
            if(this.zzfhh != null && this.zzdae.getView() != null) {
                zzq.zzlk().zza(this.zzfhh, this.zzdae.getView());
                this.zzdae.zzap(this.zzfhh);
                zzq.zzlk().zzab(this.zzfhh);
            }
        }
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void onPause() {
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void onResume() {
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void zztj() {
        this.zzfhh = null;
    }

    @Override  // com.google.android.gms.ads.internal.overlay.zzp
    public final void zztk() {
        if(this.zzfhh != null) {
            zzbdv zzbdv0 = this.zzdae;
            if(zzbdv0 != null) {
                zzbdv0.zza("onSdkImpression", new HashMap());
            }
        }
    }
}

