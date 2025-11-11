package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.dynamic.IObjectWrapper;
import javax.annotation.concurrent.GuardedBy;

public final class zzbmu implements zzbqu, zzbrn {
    private final zzazo zzblu;
    @Nullable
    private final zzbdv zzdae;
    private final zzdei zzfhg;
    @Nullable
    @GuardedBy("this")
    private IObjectWrapper zzfhh;
    @GuardedBy("this")
    private boolean zzfhi;
    private final Context zzur;

    public zzbmu(Context context0, @Nullable zzbdv zzbdv0, zzdei zzdei0, zzazo zzazo0) {
        this.zzur = context0;
        this.zzdae = zzbdv0;
        this.zzfhg = zzdei0;
        this.zzblu = zzazo0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbqu
    public final void onAdImpression() {
        synchronized(this) {
            if(!this.zzfhi) {
                this.zzahe();
            }
            if(this.zzfhg.zzdmn && this.zzfhh != null && this.zzdae != null) {
                ArrayMap arrayMap0 = new ArrayMap();
                this.zzdae.zza("onSdkImpression", arrayMap0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbrn
    public final void onAdLoaded() {
        synchronized(this) {
            if(this.zzfhi) {
                return;
            }
            this.zzahe();
        }
    }

    private final void zzahe() {
        __monitor_enter(this);
        if(!this.zzfhg.zzdmn) {
            __monitor_exit(this);
            return;
        }
        if(this.zzdae == null) {
            __monitor_exit(this);
            return;
        }
        try {
            if(zzq.zzlk().zzq(this.zzur)) {
                String s = this.zzblu.zzdxf + "." + this.zzblu.zzdxg;
                String s1 = this.zzfhg.zzgqa.optInt("media_type", -1) == 0 ? null : "javascript";
                this.zzfhh = zzq.zzlk().zza(s, this.zzdae.getWebView(), "", "javascript", s1);
                View view0 = this.zzdae.getView();
                if(this.zzfhh != null && view0 != null) {
                    zzq.zzlk().zza(this.zzfhh, view0);
                    this.zzdae.zzap(this.zzfhh);
                    zzq.zzlk().zzab(this.zzfhh);
                    this.zzfhi = true;
                }
                goto label_21;
            }
            goto label_23;
        }
        catch(Throwable throwable0) {
            __monitor_exit(this);
            throw throwable0;
        }
    label_21:
        __monitor_exit(this);
        return;
    label_23:
        __monitor_exit(this);
    }
}

