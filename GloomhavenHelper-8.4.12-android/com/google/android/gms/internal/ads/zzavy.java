package com.google.android.gms.internal.ads;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;

public final class zzavy {
    private final Object lock;
    @VisibleForTesting
    private long zzdsk;
    @VisibleForTesting
    private long zzdsl;
    @VisibleForTesting
    @GuardedBy("lock")
    private int zzdsm;
    @VisibleForTesting
    int zzdsn;
    @VisibleForTesting
    private long zzdso;
    @VisibleForTesting
    private final String zzdsp;
    private final zzawh zzdsq;
    @VisibleForTesting
    @GuardedBy("lock")
    private int zzdsr;
    @VisibleForTesting
    @GuardedBy("lock")
    private int zzdss;

    public zzavy(String s, zzawh zzawh0) {
        this.zzdsk = -1L;
        this.zzdsl = -1L;
        this.zzdsm = -1;
        this.zzdsn = -1;
        this.zzdso = 0L;
        this.lock = new Object();
        this.zzdsr = 0;
        this.zzdss = 0;
        this.zzdsp = s;
        this.zzdsq = zzawh0;
    }

    public final void zza(zzuh zzuh0, long v) {
        synchronized(this.lock) {
            long v2 = this.zzdsq.zzwg();
            long v3 = zzq.zzlc().currentTimeMillis();
            if(this.zzdsl == -1L) {
                this.zzdsn = v3 - v2 > ((long)(((Long)zzvh.zzpd().zzd(zzzx.zzcjv)))) ? -1 : this.zzdsq.zzwh();
                this.zzdsl = v;
                this.zzdsk = this.zzdsl;
            }
            else {
                this.zzdsk = v;
            }
            if(zzuh0 != null && zzuh0.extras != null && zzuh0.extras.getInt("gw", 2) == 1) {
                return;
            }
            ++this.zzdsm;
            ++this.zzdsn;
            if(this.zzdsn == 0) {
                this.zzdso = 0L;
                this.zzdsq.zzfa(v3);
            }
            else {
                this.zzdso = v3 - this.zzdsq.zzwi();
            }
        }
    }

    private static boolean zzao(Context context0) {
        Context context1 = zzars.zzac(context0);
        int v = context1.getResources().getIdentifier("Theme.Translucent", "style", "android");
        if(v == 0) {
            zzawf.zzez("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        ComponentName componentName0 = new ComponentName("com.esotericsoftware.gloomhavenhelper", "com.google.android.gms.ads.AdActivity");
        try {
            if(v == context1.getPackageManager().getActivityInfo(componentName0, 0).theme) {
                return true;
            }
            zzawf.zzez("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            zzawf.zzfa("Fail to fetch AdActivity theme");
            zzawf.zzez("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
        }
        return false;
    }

    public final Bundle zzo(Context context0, String s) {
        synchronized(this.lock) {
            Bundle bundle0 = new Bundle();
            bundle0.putString("session_id", this.zzdsp);
            bundle0.putLong("basets", this.zzdsl);
            bundle0.putLong("currts", this.zzdsk);
            bundle0.putString("seq_num", s);
            bundle0.putInt("preqs", this.zzdsm);
            bundle0.putInt("preqs_in_session", this.zzdsn);
            bundle0.putLong("time_in_session", this.zzdso);
            bundle0.putInt("pclick", this.zzdsr);
            bundle0.putInt("pimp", this.zzdss);
            bundle0.putBoolean("support_transparent_background", zzavy.zzao(context0));
            return bundle0;
        }
    }

    public final void zzva() {
        synchronized(this.lock) {
            ++this.zzdss;
        }
    }

    public final void zzvb() {
        synchronized(this.lock) {
            ++this.zzdsr;
        }
    }
}

