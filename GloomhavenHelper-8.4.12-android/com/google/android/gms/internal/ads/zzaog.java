package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.browser.customtabs.CustomTabsIntent.Builder;
import androidx.browser.customtabs.CustomTabsIntent;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzb;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;

public final class zzaog implements MediationInterstitialAdapter {
    private Uri uri;
    private Activity zzdfz;
    private MediationInterstitialListener zzdga;

    @Override  // com.google.android.gms.ads.mediation.MediationAdapter
    public final void onDestroy() {
        zzazh.zzeb("Destroying AdMobCustomTabsAdapter adapter.");
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdapter
    public final void onPause() {
        zzazh.zzeb("Pausing AdMobCustomTabsAdapter adapter.");
    }

    @Override  // com.google.android.gms.ads.mediation.MediationAdapter
    public final void onResume() {
        zzazh.zzeb("Resuming AdMobCustomTabsAdapter adapter.");
    }

    @Override  // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public final void requestInterstitialAd(Context context0, MediationInterstitialListener mediationInterstitialListener0, Bundle bundle0, MediationAdRequest mediationAdRequest0, Bundle bundle1) {
        this.zzdga = mediationInterstitialListener0;
        if(this.zzdga == null) {
            zzazh.zzfa("Listener not set for mediation. Returning.");
            return;
        }
        if(!(context0 instanceof Activity)) {
            zzazh.zzfa("AdMobCustomTabs can only work with Activity context. Bailing out.");
            this.zzdga.onAdFailedToLoad(this, 0);
            return;
        }
        if(!zzaau.zzl(context0)) {
            zzazh.zzfa("Default browser does not support custom tabs. Bailing out.");
            this.zzdga.onAdFailedToLoad(this, 0);
            return;
        }
        String s = bundle0.getString("tab_url");
        if(TextUtils.isEmpty(s)) {
            zzazh.zzfa("The tab_url retrieved from mediation metadata is empty. Bailing out.");
            this.zzdga.onAdFailedToLoad(this, 0);
            return;
        }
        this.zzdfz = (Activity)context0;
        this.uri = Uri.parse(s);
        this.zzdga.onAdLoaded(this);
    }

    @Override  // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public final void showInterstitial() {
        CustomTabsIntent customTabsIntent0 = new Builder().build();
        customTabsIntent0.intent.setData(this.uri);
        zzaoi zzaoi0 = new zzaoi(this, new AdOverlayInfoParcel(new zzb(customTabsIntent0.intent), null, new zzaoj(this), null, new zzazo(0, 0, false)));
        zzawo.zzdtx.post(zzaoi0);
        zzq.zzkz().zzvg();
    }

    static MediationInterstitialListener zza(zzaog zzaog0) {
        return zzaog0.zzdga;
    }
}

