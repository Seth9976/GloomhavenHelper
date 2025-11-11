package com.google.android.gms.common.stats;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager.WakeLock;
import android.os.Process;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.List;

@KeepForSdk
public class StatsUtils {
    @KeepForSdk
    public static String getEventKey(Context context0, Intent intent0) {
        return String.valueOf(((long)System.identityHashCode(intent0)) | ((long)System.identityHashCode(context0)) << 0x20);
    }

    @KeepForSdk
    public static String getEventKey(PowerManager.WakeLock powerManager$WakeLock0, String s) {
        String s1 = String.valueOf(((long)Process.myPid()) << 0x20 | ((long)System.identityHashCode(powerManager$WakeLock0)));
        if(TextUtils.isEmpty(s)) {
            s = "";
        }
        String s2 = String.valueOf(s);
        return s2.length() == 0 ? new String(s1) : s1 + s2;
    }

    @Nullable
    static List zza(@Nullable List list0) {
        return list0 == null || list0.size() != 1 || !"com.google.android.gms".equals(list0.get(0)) ? list0 : null;
    }

    // 去混淆评级： 低(20)
    @Nullable
    static String zzi(String s) [...] // Inlined contents
}

