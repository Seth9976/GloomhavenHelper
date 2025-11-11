package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.View;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.UUID;

public final class zzep {
    private static final char[] zzym;

    static {
        zzep.zzym = "0123456789abcdef".toCharArray();
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static long zza(double f, DisplayMetrics displayMetrics0) {
        return Math.round(f / ((double)displayMetrics0.density));
    }

    public static String zza(Throwable throwable0) {
        StringWriter stringWriter0 = new StringWriter();
        zzdww.zza(throwable0, new PrintWriter(stringWriter0));
        return stringWriter0.toString();
    }

    public static boolean zza(DisplayMetrics displayMetrics0) {
        return displayMetrics0 != null && displayMetrics0.density != 0.0f;
    }

    public static String zzau(String s) {
        if(s != null && s.matches("^[a-fA-F0-9]{8}-([a-fA-F0-9]{4}-){3}[a-fA-F0-9]{12}$")) {
            UUID uUID0 = UUID.fromString(s);
            byte[] arr_b = new byte[16];
            ByteBuffer byteBuffer0 = ByteBuffer.wrap(arr_b);
            byteBuffer0.putLong(uUID0.getMostSignificantBits());
            byteBuffer0.putLong(uUID0.getLeastSignificantBits());
            return zzci.zza(arr_b, true);
        }
        return s;
    }

    public static boolean zzav(String s) [...] // 潜在的解密器

    public static Activity zzc(View view0) {
        View view1 = view0.getRootView();
        if(view1 != null) {
            view0 = view1;
        }
        Context context0 = view0.getContext();
        for(int v = 0; context0 instanceof ContextWrapper && v < 10; ++v) {
            if(context0 instanceof Activity) {
                return (Activity)context0;
            }
            context0 = ((ContextWrapper)context0).getBaseContext();
        }
        return null;
    }
}

