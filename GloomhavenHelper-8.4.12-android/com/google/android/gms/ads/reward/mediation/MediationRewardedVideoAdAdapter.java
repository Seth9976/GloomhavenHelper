package com.google.android.gms.ads.reward.mediation;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationAdapter;

public interface MediationRewardedVideoAdAdapter extends MediationAdapter {
    public static final String CUSTOM_EVENT_SERVER_PARAMETER_FIELD = "parameter";

    void initialize(Context arg1, MediationAdRequest arg2, String arg3, MediationRewardedVideoAdListener arg4, Bundle arg5, Bundle arg6);

    boolean isInitialized();

    void loadAd(MediationAdRequest arg1, Bundle arg2, Bundle arg3);

    void showVideo();
}

