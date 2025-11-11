package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Keep;
import androidx.annotation.MainThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.FileDescriptor;
import java.io.PrintWriter;

@KeepForSdk
public class LifecycleCallback {
    @KeepForSdk
    protected final LifecycleFragment mLifecycleFragment;

    @KeepForSdk
    protected LifecycleCallback(LifecycleFragment lifecycleFragment0) {
        this.mLifecycleFragment = lifecycleFragment0;
    }

    @MainThread
    @KeepForSdk
    public void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
    }

    @KeepForSdk
    public Activity getActivity() {
        return this.mLifecycleFragment.getLifecycleActivity();
    }

    @Keep
    private static LifecycleFragment getChimeraLifecycleFragmentImpl(LifecycleActivity lifecycleActivity0) {
        throw new IllegalStateException("Method not available in SDK.");
    }

    @KeepForSdk
    public static LifecycleFragment getFragment(Activity activity0) {
        return LifecycleCallback.getFragment(new LifecycleActivity(activity0));
    }

    @KeepForSdk
    public static LifecycleFragment getFragment(ContextWrapper contextWrapper0) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    protected static LifecycleFragment getFragment(LifecycleActivity lifecycleActivity0) {
        if(lifecycleActivity0.isSupport()) {
            return zzc.zza(lifecycleActivity0.asFragmentActivity());
        }
        if(!lifecycleActivity0.zzh()) {
            throw new IllegalArgumentException("Can\'t get fragment for unexpected activity.");
        }
        return zza.zza(lifecycleActivity0.asActivity());
    }

    @MainThread
    @KeepForSdk
    public void onActivityResult(int v, int v1, Intent intent0) {
    }

    @MainThread
    @KeepForSdk
    public void onCreate(Bundle bundle0) {
    }

    @MainThread
    @KeepForSdk
    public void onDestroy() {
    }

    @MainThread
    @KeepForSdk
    public void onResume() {
    }

    @MainThread
    @KeepForSdk
    public void onSaveInstanceState(Bundle bundle0) {
    }

    @MainThread
    @KeepForSdk
    public void onStart() {
    }

    @MainThread
    @KeepForSdk
    public void onStop() {
    }
}

