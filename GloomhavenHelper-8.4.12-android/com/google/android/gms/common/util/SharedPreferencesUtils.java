package com.google.android.gms.common.util;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.File;

@KeepForSdk
public class SharedPreferencesUtils {
    @KeepForSdk
    @Deprecated
    public static void publishWorldReadableSharedPreferences(Context context0, SharedPreferences.Editor sharedPreferences$Editor0, String s) {
        File file0 = new File(context0.getApplicationInfo().dataDir, "shared_prefs");
        File file1 = file0.getParentFile();
        if(file1 != null) {
            file1.setExecutable(true, false);
        }
        file0.setExecutable(true, false);
        sharedPreferences$Editor0.commit();
        new File(file0, s + ".xml").setReadable(true, false);
    }
}

