package com.google.android.gms.ads.formats;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.MuteThisAdListener;
import com.google.android.gms.ads.MuteThisAdReason;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.List;

public abstract class UnifiedNativeAd {
    public interface OnUnifiedNativeAdLoadedListener {
        void onUnifiedNativeAdLoaded(UnifiedNativeAd arg1);
    }

    public interface UnconfirmedClickListener {
        void onUnconfirmedClickCancelled();

        void onUnconfirmedClickReceived(String arg1);
    }

    public abstract void cancelUnconfirmedClick();

    public abstract void destroy();

    public abstract void enableCustomClickGesture();

    public abstract AdChoicesInfo getAdChoicesInfo();

    public abstract String getAdvertiser();

    public abstract String getBody();

    public abstract String getCallToAction();

    public abstract Bundle getExtras();

    public abstract String getHeadline();

    public abstract Image getIcon();

    public abstract List getImages();

    public abstract MediaContent getMediaContent();

    @Deprecated
    public abstract String getMediationAdapterClassName();

    public abstract List getMuteThisAdReasons();

    public abstract String getPrice();

    @Nullable
    public abstract ResponseInfo getResponseInfo();

    public abstract Double getStarRating();

    public abstract String getStore();

    @Deprecated
    public abstract VideoController getVideoController();

    public abstract boolean isCustomClickGestureEnabled();

    public abstract boolean isCustomMuteThisAdEnabled();

    public abstract void muteThisAd(MuteThisAdReason arg1);

    @KeepForSdk
    public abstract void performClick(Bundle arg1);

    public abstract void recordCustomClickGesture();

    @KeepForSdk
    public abstract boolean recordImpression(Bundle arg1);

    @KeepForSdk
    public abstract void reportTouchEvent(Bundle arg1);

    public abstract void setMuteThisAdListener(MuteThisAdListener arg1);

    public abstract void setOnPaidEventListener(@Nullable OnPaidEventListener arg1);

    public abstract void setUnconfirmedClickListener(UnconfirmedClickListener arg1);

    protected abstract Object zzjo();

    public abstract Object zzjt();
}

