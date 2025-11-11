package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import javax.annotation.Nullable;

@KeepForSdk
public class ProcessUtils {
    private static String zzhf;
    private static int zzhg;

    static {
    }

    @KeepForSdk
    @Nullable
    public static String getMyProcessName() {
        if(ProcessUtils.zzhf == null) {
            if(ProcessUtils.zzhg == 0) {
                ProcessUtils.zzhg = Process.myPid();
            }
            ProcessUtils.zzhf = ProcessUtils.zzd(ProcessUtils.zzhg);
        }
        return ProcessUtils.zzhf;
    }

    @Nullable
    private static String zzd(int v) {
        BufferedReader bufferedReader0;
        String s = null;
        if(v <= 0) {
            return null;
        }
        try {
            bufferedReader0 = null;
            bufferedReader0 = ProcessUtils.zzk(("/proc/" + v + "/cmdline"));
            s = bufferedReader0;
            s = bufferedReader0.readLine().trim();
            goto label_17;
        }
        catch(IOException unused_ex) {
            IOUtils.closeQuietly(bufferedReader0);
            return null;
        }
        catch(Throwable throwable0) {
        }
        IOUtils.closeQuietly(((Closeable)s));
        throw throwable0;
        try {
            s = bufferedReader0;
            s = bufferedReader0.readLine().trim();
            goto label_17;
        }
        catch(IOException unused_ex) {
            IOUtils.closeQuietly(bufferedReader0);
            return null;
        }
        catch(Throwable throwable1) {
            throwable0 = throwable1;
        }
        IOUtils.closeQuietly(((Closeable)s));
        throw throwable0;
    label_17:
        IOUtils.closeQuietly(bufferedReader0);
        return s;
    }

    private static BufferedReader zzk(String s) throws IOException {
        StrictMode.ThreadPolicy strictMode$ThreadPolicy0 = StrictMode.allowThreadDiskReads();
        try {
            return new BufferedReader(new FileReader(s));
        }
        finally {
            StrictMode.setThreadPolicy(strictMode$ThreadPolicy0);
        }
    }
}

