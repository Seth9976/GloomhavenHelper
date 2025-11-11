package com.google.android.gms.common.api;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;

public final class UnsupportedApiCallException extends UnsupportedOperationException {
    private final Feature zzas;

    @KeepForSdk
    public UnsupportedApiCallException(Feature feature0) {
        this.zzas = feature0;
    }

    @Override
    public final String getMessage() {
        return "Missing " + this.zzas;
    }
}

