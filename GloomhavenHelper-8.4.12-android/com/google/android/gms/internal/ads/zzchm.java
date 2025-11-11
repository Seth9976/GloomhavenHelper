package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.internal.zzq;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

public final class zzchm implements AppEventListener, zzbqh, zzbqm, zzbqt, zzbqu, zzbrn, zzbsn, zzdil, zztz {
    private long startTime;
    private final List zzdxa;
    private final zzcha zzfwm;

    public zzchm(zzcha zzcha0, zzbgk zzbgk0) {
        this.zzfwm = zzcha0;
        this.zzdxa = Collections.singletonList(zzbgk0);
    }

    @Override  // com.google.android.gms.internal.ads.zztz
    public final void onAdClicked() {
        this.zza(zztz.class, "onAdClicked", new Object[0]);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdClosed() {
        this.zza(zzbqh.class, "onAdClosed", new Object[0]);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqm
    public final void onAdFailedToLoad(int v) {
        this.zza(zzbqm.class, "onAdFailedToLoad", new Object[]{v});
    }

    @Override  // com.google.android.gms.internal.ads.zzbqu
    public final void onAdImpression() {
        this.zza(zzbqu.class, "onAdImpression", new Object[0]);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdLeftApplication() {
        this.zza(zzbqh.class, "onAdLeftApplication", new Object[0]);
    }

    @Override  // com.google.android.gms.internal.ads.zzbrn
    public final void onAdLoaded() {
        zzawf.zzee(("Ad Request Latency : " + (zzq.zzlc().elapsedRealtime() - this.startTime)));
        this.zza(zzbrn.class, "onAdLoaded", new Object[0]);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onAdOpened() {
        this.zza(zzbqh.class, "onAdOpened", new Object[0]);
    }

    @Override  // com.google.android.gms.ads.doubleclick.AppEventListener
    public final void onAppEvent(String s, String s1) {
        this.zza(AppEventListener.class, "onAppEvent", new Object[]{s, s1});
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onRewardedVideoCompleted() {
        this.zza(zzbqh.class, "onRewardedVideoCompleted", new Object[0]);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    public final void onRewardedVideoStarted() {
        this.zza(zzbqh.class, "onRewardedVideoStarted", new Object[0]);
    }

    private final void zza(Class class0, String s, Object[] arr_object) {
        String s1 = class0.getSimpleName();
        this.zzfwm.zza(this.zzdxa, (s1.length() == 0 ? new String("Event-") : "Event-" + s1), s, arr_object);
    }

    @Override  // com.google.android.gms.internal.ads.zzdil
    public final void zza(zzdig zzdig0, String s) {
        this.zza(zzdid.class, "onTaskCreated", new Object[]{s});
    }

    @Override  // com.google.android.gms.internal.ads.zzdil
    public final void zza(zzdig zzdig0, String s, Throwable throwable0) {
        Object[] arr_object = {s, throwable0.getClass().getSimpleName()};
        this.zza(zzdid.class, "onTaskFailed", arr_object);
    }

    @Override  // com.google.android.gms.internal.ads.zzbqh
    @ParametersAreNonnullByDefault
    public final void zzb(zzarr zzarr0, String s, String s1) {
        this.zza(zzbqh.class, "onRewarded", new Object[]{zzarr0, s, s1});
    }

    @Override  // com.google.android.gms.internal.ads.zzbsn
    public final void zzb(zzdeq zzdeq0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzdil
    public final void zzb(zzdig zzdig0, String s) {
        this.zza(zzdid.class, "onTaskStarted", new Object[]{s});
    }

    @Override  // com.google.android.gms.internal.ads.zzbqt
    public final void zzby(Context context0) {
        this.zza(zzbqt.class, "onPause", new Object[]{context0});
    }

    @Override  // com.google.android.gms.internal.ads.zzbqt
    public final void zzbz(Context context0) {
        this.zza(zzbqt.class, "onResume", new Object[]{context0});
    }

    @Override  // com.google.android.gms.internal.ads.zzdil
    public final void zzc(zzdig zzdig0, String s) {
        this.zza(zzdid.class, "onTaskSucceeded", new Object[]{s});
    }

    @Override  // com.google.android.gms.internal.ads.zzbqt
    public final void zzca(Context context0) {
        this.zza(zzbqt.class, "onDestroy", new Object[]{context0});
    }

    @Override  // com.google.android.gms.internal.ads.zzbsn
    public final void zzd(zzaqx zzaqx0) {
        this.startTime = zzq.zzlc().elapsedRealtime();
        this.zza(zzbsn.class, "onAdRequest", new Object[0]);
    }
}

