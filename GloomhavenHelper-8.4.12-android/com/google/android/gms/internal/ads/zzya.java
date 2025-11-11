package com.google.android.gms.internal.ads;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.wrappers.Wrappers;

public final class zzya extends ContentProvider {
    @Override  // android.content.ContentProvider
    public final void attachInfo(Context context0, ProviderInfo providerInfo0) {
        String s;
        Bundle bundle0 = zzya.zzi(context0);
        zzalc zzalc0 = zzalc.zzso();
        if(bundle0 == null) {
            zzazh.zzey("Metadata was null.");
        }
        else {
            try {
                s = (String)bundle0.get("com.google.android.gms.ads.APPLICATION_ID");
            }
            catch(ClassCastException classCastException0) {
                throw new IllegalStateException("The com.google.android.gms.ads.APPLICATION_ID metadata must have a String value.", classCastException0);
            }
            try {
                Boolean boolean0 = (Boolean)bundle0.get("com.google.android.gms.ads.AD_MANAGER_APP");
            }
            catch(ClassCastException classCastException1) {
                throw new IllegalStateException("The com.google.android.gms.ads.AD_MANAGER_APP metadata must have a boolean value.", classCastException1);
            }
            try {
                Boolean boolean1 = (Boolean)bundle0.get("com.google.android.gms.ads.DELAY_APP_MEASUREMENT_INIT");
            }
            catch(ClassCastException classCastException2) {
                throw new IllegalStateException("The com.google.android.gms.ads.DELAY_APP_MEASUREMENT_INIT metadata must have a boolean value.", classCastException2);
            }
            if(s != null && !s.matches("^/\\d+~.+$")) {
                if(!s.matches("^ca-app-pub-[0-9]{16}~[0-9]{10}$")) {
                    throw new IllegalStateException("\n\n******************************************************************************\n* Invalid application ID. Follow instructions here:                          *\n* https://googlemobileadssdk.page.link/admob-android-update-manifest         *\n* to find your app ID.                                                       *\n******************************************************************************\n\n");
                }
                String s1 = String.valueOf(s);
                zzazh.zzeb((s1.length() == 0 ? new String("Publisher provided Google AdMob App ID in manifest: ") : "Publisher provided Google AdMob App ID in manifest: " + s1));
                if(boolean1 != null && boolean1.booleanValue()) {
                    super.attachInfo(context0, providerInfo0);
                    return;
                }
                zzalc0.zzc(context0, s);
                super.attachInfo(context0, providerInfo0);
                return;
            }
            if(boolean0 == null || !boolean0.booleanValue()) {
                throw new IllegalStateException("\n\n******************************************************************************\n* The Google Mobile Ads SDK was initialized incorrectly. AdMob publishers    *\n* should follow the instructions here:                                       *\n* https://googlemobileadssdk.page.link/admob-android-update-manifest         *\n* to add a valid App ID inside the AndroidManifest.                          *\n* Google Ad Manager publishers should follow instructions here:              *\n* https://googlemobileadssdk.page.link/ad-manager-android-update-manifest.   *\n******************************************************************************\n\n");
            }
            if(boolean1 == null || !boolean1.booleanValue()) {
                zzalc0.zzn(context0);
            }
        }
        super.attachInfo(context0, providerInfo0);
    }

    @Override  // android.content.ContentProvider
    public final int delete(@NonNull Uri uri0, String s, String[] arr_s) [...] // Inlined contents

    @Override  // android.content.ContentProvider
    @Nullable
    public final String getType(@NonNull Uri uri0) [...] // Inlined contents

    @Override  // android.content.ContentProvider
    @Nullable
    public final Uri insert(@NonNull Uri uri0, ContentValues contentValues0) [...] // Inlined contents

    @Override  // android.content.ContentProvider
    public final boolean onCreate() [...] // Inlined contents

    @Override  // android.content.ContentProvider
    @Nullable
    public final Cursor query(@NonNull Uri uri0, String[] arr_s, String s, String[] arr_s1, String s1) [...] // Inlined contents

    @Override  // android.content.ContentProvider
    public final int update(@NonNull Uri uri0, ContentValues contentValues0, String s, String[] arr_s) [...] // Inlined contents

    @Nullable
    private static Bundle zzi(Context context0) {
        try {
            return Wrappers.packageManager(context0).getApplicationInfo("com.esotericsoftware.gloomhavenhelper", 0x80).metaData;
        }
        catch(NullPointerException nullPointerException0) {
            zzazh.zzc("Failed to load metadata: Null pointer exception.", nullPointerException0);
            return null;
        }
        catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
            zzazh.zzc("Failed to load metadata: Package name not found.", packageManager$NameNotFoundException0);
            return null;
        }
    }
}

