package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.annotation.VisibleForTesting;

@TargetApi(24)
public class zzawz extends zzaxa {
    @Override  // com.google.android.gms.internal.ads.zzawu
    public final boolean zza(Activity activity0, Configuration configuration0) {
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcop)).booleanValue()) {
            return false;
        }
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcor)).booleanValue()) {
            return activity0.isInMultiWindowMode();
        }
        int v = zzayx.zzb(activity0, configuration0.screenHeightDp);
        int v1 = zzayx.zzb(activity0, configuration0.screenWidthDp);
        DisplayMetrics displayMetrics0 = zzawo.zza(((WindowManager)activity0.getApplicationContext().getSystemService("window")));
        int v2 = displayMetrics0.heightPixels;
        int v3 = displayMetrics0.widthPixels;
        int v4 = activity0.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int v5 = v4 <= 0 ? 0 : activity0.getResources().getDimensionPixelSize(v4);
        int v6 = ((int)Math.round(((double)activity0.getResources().getDisplayMetrics().density) + 0.5)) * ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcoo))));
        return !zzawz.zze(v2, v + v5, v6) || !zzawz.zze(v3, v1, v6);
    }

    @VisibleForTesting
    private static boolean zze(int v, int v1, int v2) {
        return Math.abs(v - v1) <= v2;
    }
}

