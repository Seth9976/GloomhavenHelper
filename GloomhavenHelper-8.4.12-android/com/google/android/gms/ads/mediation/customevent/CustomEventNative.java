package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;

public interface CustomEventNative extends CustomEvent {
    void requestNativeAd(Context arg1, CustomEventNativeListener arg2, String arg3, NativeMediationAdRequest arg4, Bundle arg5);
}

