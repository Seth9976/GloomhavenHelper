package com.google.android.gms.ads.mediation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface MediationAdLoadCallback {
    void onFailure(String arg1);

    Object onSuccess(Object arg1);
}

