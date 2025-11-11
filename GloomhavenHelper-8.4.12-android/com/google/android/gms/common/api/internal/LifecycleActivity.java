package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.ContextWrapper;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
public class LifecycleActivity {
    private final Object zzbd;

    public LifecycleActivity(Activity activity0) {
        Preconditions.checkNotNull(activity0, "Activity must not be null");
        this.zzbd = activity0;
    }

    @KeepForSdk
    public LifecycleActivity(ContextWrapper contextWrapper0) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk
    public Activity asActivity() {
        return (Activity)this.zzbd;
    }

    @KeepForSdk
    public FragmentActivity asFragmentActivity() {
        return (FragmentActivity)this.zzbd;
    }

    @KeepForSdk
    public Object asObject() {
        return this.zzbd;
    }

    @KeepForSdk
    public boolean isChimera() {
        return false;
    }

    @KeepForSdk
    public boolean isSupport() {
        return this.zzbd instanceof FragmentActivity;
    }

    public final boolean zzh() {
        return this.zzbd instanceof Activity;
    }
}

