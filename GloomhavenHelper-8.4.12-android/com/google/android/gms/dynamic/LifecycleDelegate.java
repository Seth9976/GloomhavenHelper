package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface LifecycleDelegate {
    @KeepForSdk
    void onCreate(Bundle arg1);

    @KeepForSdk
    View onCreateView(LayoutInflater arg1, ViewGroup arg2, Bundle arg3);

    @KeepForSdk
    void onDestroy();

    @KeepForSdk
    void onDestroyView();

    @KeepForSdk
    void onInflate(Activity arg1, Bundle arg2, Bundle arg3);

    @KeepForSdk
    void onLowMemory();

    @KeepForSdk
    void onPause();

    @KeepForSdk
    void onResume();

    @KeepForSdk
    void onSaveInstanceState(Bundle arg1);

    @KeepForSdk
    void onStart();

    @KeepForSdk
    void onStop();
}

