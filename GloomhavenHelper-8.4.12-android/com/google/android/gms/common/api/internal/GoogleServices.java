package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.R.string;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
@Deprecated
public final class GoogleServices {
    private static final Object sLock;
    @GuardedBy("sLock")
    private static GoogleServices zzay;
    private final String zzaz;
    private final Status zzba;
    private final boolean zzbb;
    private final boolean zzbc;

    static {
        GoogleServices.sLock = new Object();
    }

    @KeepForSdk
    @VisibleForTesting
    GoogleServices(Context context0) {
        Resources resources0 = context0.getResources();
        int v = resources0.getIdentifier("google_app_measurement_enable", "integer", resources0.getResourcePackageName(string.common_google_play_services_unknown_issue));
        boolean z = true;
        if(v == 0) {
            this.zzbc = false;
        }
        else {
            if(resources0.getInteger(v) == 0) {
                z = false;
            }
            this.zzbc = !z;
        }
        this.zzbb = z;
        String s = zzp.zzc(context0);
        if(s == null) {
            s = new StringResourceValueReader(context0).getString("google_app_id");
        }
        if(TextUtils.isEmpty(s)) {
            this.zzba = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.zzaz = null;
            return;
        }
        this.zzaz = s;
        this.zzba = Status.RESULT_SUCCESS;
    }

    @KeepForSdk
    @VisibleForTesting
    GoogleServices(String s, boolean z) {
        this.zzaz = s;
        this.zzba = Status.RESULT_SUCCESS;
        this.zzbb = z;
        this.zzbc = !z;
    }

    @KeepForSdk
    @VisibleForTesting
    final Status checkGoogleAppId(String s) {
        return this.zzaz == null || this.zzaz.equals(s) ? Status.RESULT_SUCCESS : new Status(10, "Initialize was called with two different Google App IDs.  Only the first app ID will be used: \'" + this.zzaz + "\'.");
    }

    @KeepForSdk
    private static GoogleServices checkInitialized(String s) {
        synchronized(GoogleServices.sLock) {
            if(GoogleServices.zzay != null) {
                return GoogleServices.zzay;
            }
        }
        throw new IllegalStateException("Initialize must be called before " + s + ".");
    }

    @KeepForSdk
    @VisibleForTesting
    static void clearInstanceForTest() {
        synchronized(GoogleServices.sLock) {
            GoogleServices.zzay = null;
        }
    }

    @KeepForSdk
    public static String getGoogleAppId() {
        return GoogleServices.checkInitialized("getGoogleAppId").zzaz;
    }

    @KeepForSdk
    public static Status initialize(Context context0) {
        Preconditions.checkNotNull(context0, "Context must not be null.");
        synchronized(GoogleServices.sLock) {
            if(GoogleServices.zzay == null) {
                GoogleServices.zzay = new GoogleServices(context0);
            }
            return GoogleServices.zzay.zzba;
        }
    }

    @KeepForSdk
    public static Status initialize(Context context0, String s, boolean z) {
        Preconditions.checkNotNull(context0, "Context must not be null.");
        Preconditions.checkNotEmpty(s, "App ID must be nonempty.");
        synchronized(GoogleServices.sLock) {
            if(GoogleServices.zzay != null) {
                return GoogleServices.zzay.checkGoogleAppId(s);
            }
            GoogleServices googleServices0 = new GoogleServices(s, z);
            GoogleServices.zzay = googleServices0;
            return googleServices0.zzba;
        }
    }

    @KeepForSdk
    public static boolean isMeasurementEnabled() {
        GoogleServices googleServices0 = GoogleServices.checkInitialized("isMeasurementEnabled");
        return googleServices0.zzba.isSuccess() && googleServices0.zzbb;
    }

    @KeepForSdk
    public static boolean isMeasurementExplicitlyDisabled() {
        return GoogleServices.checkInitialized("isMeasurementExplicitlyDisabled").zzbc;
    }
}

