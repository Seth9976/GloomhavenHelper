package com.google.android.gms.ads.instream;

import android.content.Context;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MediaAspectRatio;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzahx;
import com.google.android.gms.internal.ads.zzahy;

public abstract class InstreamAd {
    public static class InstreamAdLoadCallback {
        public void onInstreamAdFailedToLoad(int v) {
        }

        public void onInstreamAdLoaded(InstreamAd instreamAd0) {
        }
    }

    public abstract void destroy();

    @Deprecated
    public abstract float getAspectRatio();

    public abstract MediaContent getMediaContent();

    @Deprecated
    public abstract VideoController getVideoController();

    @Deprecated
    public abstract float getVideoCurrentTime();

    @Deprecated
    public abstract float getVideoDuration();

    public static void load(Context context0, String s, AdRequest adRequest0, @MediaAspectRatio int v, InstreamAdLoadCallback instreamAd$InstreamAdLoadCallback0) {
        Preconditions.checkArgument(v == 2 || v == 3, "Instream ads only support Landscape and Portrait media aspect ratios");
        new zzahy(context0, s).zza(instreamAd$InstreamAdLoadCallback0).zza(new zzahx(v)).zzsd().loadAd(adRequest0);
    }

    public static void load(Context context0, String s, InstreamAdLoadCallback instreamAd$InstreamAdLoadCallback0) {
        new zzahy(context0, "").zza(instreamAd$InstreamAdLoadCallback0).zza(new zzahx(s)).zzsd().loadAd(new Builder().build());
    }

    protected abstract void zza(InstreamAdView arg1);
}

