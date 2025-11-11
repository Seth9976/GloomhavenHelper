package com.google.android.gms.ads.query;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface QueryInfoGenerationCallback {
    @KeepForSdk
    void onFailure(String arg1);

    @KeepForSdk
    void onSuccess(QueryInfo arg1);
}

