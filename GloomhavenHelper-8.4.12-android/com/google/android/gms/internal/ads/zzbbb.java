package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zzbbb {
    private final Context zzcgw;
    private final zzbbm zzdza;
    private final ViewGroup zzdzv;
    private zzbav zzdzw;

    @VisibleForTesting
    private zzbbb(Context context0, ViewGroup viewGroup0, zzbbm zzbbm0, zzbav zzbav0) {
        if(context0.getApplicationContext() != null) {
            context0 = context0.getApplicationContext();
        }
        this.zzcgw = context0;
        this.zzdzv = viewGroup0;
        this.zzdza = zzbbm0;
        this.zzdzw = null;
    }

    public zzbbb(Context context0, ViewGroup viewGroup0, zzbdv zzbdv0) {
        this(context0, viewGroup0, zzbdv0, null);
    }

    public final void onDestroy() {
        Preconditions.checkMainThread("onDestroy must be called from the UI thread.");
        zzbav zzbav0 = this.zzdzw;
        if(zzbav0 != null) {
            zzbav0.destroy();
            this.zzdzv.removeView(this.zzdzw);
            this.zzdzw = null;
        }
    }

    public final void onPause() {
        Preconditions.checkMainThread("onPause must be called from the UI thread.");
        zzbav zzbav0 = this.zzdzw;
        if(zzbav0 != null) {
            zzbav0.pause();
        }
    }

    public final void zza(int v, int v1, int v2, int v3, int v4, boolean z, zzbbj zzbbj0) {
        if(this.zzdzw != null) {
            return;
        }
        zzaaf.zza(this.zzdza.zzyv().zzqw(), this.zzdza.zzyr(), new String[]{"vpr2"});
        zzaak zzaak0 = this.zzdza.zzyv().zzqw();
        this.zzdzw = new zzbav(this.zzcgw, this.zzdza, v4, z, zzaak0, zzbbj0);
        zzbav zzbav0 = this.zzdzw;
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = new ViewGroup.LayoutParams(-1, -1);
        this.zzdzv.addView(zzbav0, 0, viewGroup$LayoutParams0);
        this.zzdzw.zzd(v, v1, v2, v3);
        this.zzdza.zzav(false);
    }

    public final void zze(int v, int v1, int v2, int v3) {
        Preconditions.checkMainThread("The underlay may only be modified from the UI thread.");
        zzbav zzbav0 = this.zzdzw;
        if(zzbav0 != null) {
            zzbav0.zzd(v, v1, v2, v3);
        }
    }

    public final zzbav zzyj() {
        Preconditions.checkMainThread("getAdVideoUnderlay must be called from the UI thread.");
        return this.zzdzw;
    }
}

