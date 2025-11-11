package com.google.android.gms.ads;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.internal.ads.zzya;

@KeepForSdkWithMembers
public class MobileAdsInitProvider extends ContentProvider {
    private final zzya zzabu;

    public MobileAdsInitProvider() {
        this.zzabu = new zzya();
    }

    @Override  // android.content.ContentProvider
    public void attachInfo(Context context0, ProviderInfo providerInfo0) {
        this.zzabu.attachInfo(context0, providerInfo0);
    }

    @Override  // android.content.ContentProvider
    public int delete(@NonNull Uri uri0, String s, String[] arr_s) {
        return 0;
    }

    @Override  // android.content.ContentProvider
    @Nullable
    public String getType(@NonNull Uri uri0) {
        return null;
    }

    @Override  // android.content.ContentProvider
    @Nullable
    public Uri insert(@NonNull Uri uri0, ContentValues contentValues0) {
        return null;
    }

    @Override  // android.content.ContentProvider
    public boolean onCreate() {
        return false;
    }

    @Override  // android.content.ContentProvider
    @Nullable
    public Cursor query(@NonNull Uri uri0, String[] arr_s, String s, String[] arr_s1, String s1) {
        return null;
    }

    @Override  // android.content.ContentProvider
    public int update(@NonNull Uri uri0, ContentValues contentValues0, String s, String[] arr_s) {
        return 0;
    }
}

