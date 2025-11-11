package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface LifecycleFragment {
    @KeepForSdk
    void addCallback(String arg1, @NonNull LifecycleCallback arg2);

    @KeepForSdk
    LifecycleCallback getCallbackOrNull(String arg1, Class arg2);

    @KeepForSdk
    Activity getLifecycleActivity();

    @KeepForSdk
    boolean isCreated();

    @KeepForSdk
    boolean isStarted();

    @KeepForSdk
    void startActivityForResult(Intent arg1, int arg2);
}

