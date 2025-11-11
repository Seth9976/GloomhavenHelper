package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    private static final Object zzdc;
    private static ClassLoader zzdd;
    private static Integer zzde;
    private boolean zzdf;

    static {
        DowngradeableSafeParcel.zzdc = new Object();
        DowngradeableSafeParcel.zzdd = null;
        DowngradeableSafeParcel.zzde = null;
    }

    public DowngradeableSafeParcel() {
        this.zzdf = false;
    }

    @KeepForSdk
    protected static boolean canUnparcelSafely(String s) {
        return true;
    }

    @KeepForSdk
    protected static Integer getUnparcelClientVersion() {
        synchronized(DowngradeableSafeParcel.zzdc) {
        }
        return null;
    }

    @KeepForSdk
    protected abstract boolean prepareForClientVersion(int arg1);

    @KeepForSdk
    public void setShouldDowngrade(boolean z) {
        this.zzdf = z;
    }

    @KeepForSdk
    protected boolean shouldDowngrade() {
        return this.zzdf;
    }

    private static ClassLoader zzp() {
        synchronized(DowngradeableSafeParcel.zzdc) {
        }
        return null;
    }
}

