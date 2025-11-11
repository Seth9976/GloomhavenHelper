package com.google.android.gms.ads.mediation;

import android.content.Context;
import android.os.Bundle;

public interface MediationNativeAdapter extends MediationAdapter {
    void requestNativeAd(Context arg1, MediationNativeListener arg2, Bundle arg3, NativeMediationAdRequest arg4, Bundle arg5);
}

