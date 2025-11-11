package com.google.android.gms.ads.reward;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;

public interface RewardedVideoAd {
    @Deprecated
    void destroy();

    void destroy(Context arg1);

    Bundle getAdMetadata();

    String getCustomData();

    @Deprecated
    String getMediationAdapterClassName();

    @Nullable
    ResponseInfo getResponseInfo();

    RewardedVideoAdListener getRewardedVideoAdListener();

    String getUserId();

    boolean isLoaded();

    void loadAd(String arg1, AdRequest arg2);

    void loadAd(String arg1, PublisherAdRequest arg2);

    @Deprecated
    void pause();

    void pause(Context arg1);

    @Deprecated
    void resume();

    void resume(Context arg1);

    void setAdMetadataListener(AdMetadataListener arg1);

    void setCustomData(String arg1);

    void setImmersiveMode(boolean arg1);

    void setRewardedVideoAdListener(RewardedVideoAdListener arg1);

    void setUserId(String arg1);

    void show();
}

