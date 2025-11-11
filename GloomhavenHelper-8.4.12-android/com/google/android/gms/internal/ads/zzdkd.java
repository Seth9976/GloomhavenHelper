package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.android.gms.common.util.IOUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class zzdkd {
    public static File zza(@NonNull File file0, boolean z) {
        if(z && file0.exists() && !file0.isDirectory()) {
            file0.delete();
        }
        if(!file0.exists()) {
            file0.mkdirs();
        }
        return file0;
    }

    // 去混淆评级： 低(20)
    public static File zza(String s, @NonNull File file0) {
        return TextUtils.isEmpty(s) ? null : zzdkd.zza(new File(file0, s), false);
    }

    // 去混淆评级： 低(20)
    public static File zza(String s, String s1, @NonNull File file0) {
        return TextUtils.isEmpty(s) || TextUtils.isEmpty(s1) ? null : new File(zzdkd.zza(s, file0), s1);
    }

    public static boolean zza(@NonNull File file0, @NonNull byte[] arr_b) {
        FileOutputStream fileOutputStream0 = null;
        try {
            fileOutputStream0 = new FileOutputStream(file0);
            fileOutputStream0.write(arr_b);
            fileOutputStream0.flush();
            return true;
        }
        catch(IOException unused_ex) {
            return false;
        }
        finally {
            IOUtils.closeQuietly(fileOutputStream0);
        }
    }

    public static boolean zzd(@NonNull File file0) {
        if(!file0.exists()) {
            return true;
        }
        if(file0.isDirectory()) {
            File[] arr_file = file0.listFiles();
            for(int v = 0; v < arr_file.length; ++v) {
                zzdkd.zzd(arr_file[v]);
            }
        }
        return file0.delete();
    }

    public static byte[] zze(@NonNull File file0) {
        FileInputStream fileInputStream0 = null;
        try {
            fileInputStream0 = new FileInputStream(file0);
            return zzdxn.zzf(fileInputStream0).toByteArray();
        }
        catch(IOException unused_ex) {
            return null;
        }
        finally {
            IOUtils.closeQuietly(fileInputStream0);
        }
    }
}

