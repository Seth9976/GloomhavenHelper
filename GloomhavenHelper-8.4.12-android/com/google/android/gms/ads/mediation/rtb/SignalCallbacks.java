package com.google.android.gms.ads.mediation.rtb;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface SignalCallbacks {
    void onFailure(String arg1);

    void onSuccess(String arg1);
}

