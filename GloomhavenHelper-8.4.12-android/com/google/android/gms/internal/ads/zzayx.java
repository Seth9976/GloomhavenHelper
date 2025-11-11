package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;

public final class zzayx {
    private static final String zzdwn;
    private static final String zzdwo;
    private static final String zzdwp;
    private static final String zzdwq;
    private static final String zzdwr;
    private static final String zzdws;
    private float zzdwt;
    public static final Handler zzyy;

    static {
        zzayx.zzyy = new zzdkp(Looper.getMainLooper());
        zzayx.zzdwn = "com.google.android.gms.ads.AdView";
        zzayx.zzdwo = "com.google.android.gms.ads.InterstitialAd";
        zzayx.zzdwp = "com.google.android.gms.ads.doubleclick.PublisherAdView";
        zzayx.zzdwq = "com.google.android.gms.ads.doubleclick.PublisherInterstitialAd";
        zzayx.zzdwr = "com.google.android.gms.ads.search.SearchAdView";
        zzayx.zzdws = "com.google.android.gms.ads.AdLoader";
    }

    public zzayx() {
        this.zzdwt = -1.0f;
    }

    public static int zza(DisplayMetrics displayMetrics0, int v) {
        return (int)TypedValue.applyDimension(1, ((float)v), displayMetrics0);
    }

    public static AdSize zza(Context context0, int v, int v1, int v2) {
        int v3;
        if(context0 == null) {
            v3 = -1;
        }
        else {
            if(context0.getApplicationContext() != null) {
                context0 = context0.getApplicationContext();
            }
            Resources resources0 = context0.getResources();
            if(resources0 == null) {
                v3 = -1;
            }
            else {
                DisplayMetrics displayMetrics0 = resources0.getDisplayMetrics();
                if(displayMetrics0 == null) {
                    v3 = -1;
                }
                else {
                    Configuration configuration0 = resources0.getConfiguration();
                    if(configuration0 == null) {
                        v3 = -1;
                    }
                    else {
                        int v4 = configuration0.orientation;
                        if(v2 == 0) {
                            v2 = v4;
                        }
                        v3 = v2 == v4 ? Math.round(((float)displayMetrics0.heightPixels) / displayMetrics0.density) : Math.round(((float)displayMetrics0.widthPixels) / displayMetrics0.density);
                    }
                }
            }
        }
        if(v3 == -1) {
            return AdSize.INVALID;
        }
        int v5 = Math.min(90, Math.round(((float)v3) * 0.15f));
        if(v > 0x28F) {
            return new AdSize(v, Math.max(Math.min(Math.round(((float)v) / 728.0f * 90.0f), v5), 50));
        }
        if(v > 632) {
            return new AdSize(v, Math.max(Math.min(81, v5), 50));
        }
        if(v > 0x20E) {
            return new AdSize(v, Math.max(Math.min(Math.round(((float)v) / 468.0f * 60.0f), v5), 50));
        }
        return v <= 0x1B0 ? new AdSize(v, Math.max(Math.min(Math.round(((float)v) / 320.0f * 50.0f), v5), 50)) : new AdSize(v, Math.max(Math.min(68, v5), 50));
    }

    @Nullable
    @VisibleForTesting
    public static String zza(StackTraceElement[] arr_stackTraceElement, String s) {
        String s1;
        for(int v = 0; true; ++v) {
            s1 = null;
            if(v + 1 >= arr_stackTraceElement.length) {
                break;
            }
            StackTraceElement stackTraceElement0 = arr_stackTraceElement[v];
            String s2 = stackTraceElement0.getClassName();
            if("loadAd".equalsIgnoreCase(stackTraceElement0.getMethodName()) && (zzayx.zzdwn.equalsIgnoreCase(s2) || zzayx.zzdwo.equalsIgnoreCase(s2) || zzayx.zzdwp.equalsIgnoreCase(s2) || zzayx.zzdwq.equalsIgnoreCase(s2) || zzayx.zzdwr.equalsIgnoreCase(s2) || zzayx.zzdws.equalsIgnoreCase(s2))) {
                s1 = arr_stackTraceElement[v + 1].getClassName();
                break;
            }
        }
        if(s != null) {
            StringTokenizer stringTokenizer0 = new StringTokenizer(s, ".");
            StringBuilder stringBuilder0 = new StringBuilder();
            if(stringTokenizer0.hasMoreElements()) {
                stringBuilder0.append(stringTokenizer0.nextToken());
                for(int v1 = 2; v1 > 0 && stringTokenizer0.hasMoreElements(); --v1) {
                    stringBuilder0.append(".");
                    stringBuilder0.append(stringTokenizer0.nextToken());
                }
                s = stringBuilder0.toString();
            }
            return s1 == null || s1.contains(s) ? null : s1;
        }
        return null;
    }

    public static void zza(Context context0, @Nullable String s, String s1, Bundle bundle0, boolean z, zzazc zzazc0) {
        boolean z1 = context0.getApplicationContext() != null;
        bundle0.putString("os", Build.VERSION.RELEASE);
        bundle0.putString("api", "33");
        bundle0.putString("appid", "com.esotericsoftware.gloomhavenhelper");
        if(s == null) {
            s = GoogleApiAvailabilityLight.getInstance().getApkVersion(context0) + ".20089000";
        }
        bundle0.putString("js", s);
        Uri.Builder uri$Builder0 = new Uri.Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", s1);
        for(Object object0: bundle0.keySet()) {
            uri$Builder0.appendQueryParameter(((String)object0), bundle0.getString(((String)object0)));
        }
        zzazc0.zzeo(uri$Builder0.toString());
    }

    private final void zza(ViewGroup viewGroup0, zzuk zzuk0, String s, int v, int v1) {
        if(viewGroup0.getChildCount() != 0) {
            return;
        }
        Context context0 = viewGroup0.getContext();
        TextView textView0 = new TextView(context0);
        textView0.setGravity(17);
        textView0.setText(s);
        textView0.setTextColor(v);
        textView0.setBackgroundColor(v1);
        FrameLayout frameLayout0 = new FrameLayout(context0);
        frameLayout0.setBackgroundColor(v);
        int v2 = zzayx.zzb(context0, 3);
        frameLayout0.addView(textView0, new FrameLayout.LayoutParams(zzuk0.widthPixels - v2, zzuk0.heightPixels - v2, 17));
        viewGroup0.addView(frameLayout0, zzuk0.widthPixels, zzuk0.heightPixels);
    }

    public static void zza(boolean z, HttpURLConnection httpURLConnection0, @Nullable String s) {
        httpURLConnection0.setConnectTimeout(60000);
        httpURLConnection0.setInstanceFollowRedirects(true);
        httpURLConnection0.setReadTimeout(60000);
        if(s != null) {
            httpURLConnection0.setRequestProperty("User-Agent", s);
        }
        httpURLConnection0.setUseCaches(false);
    }

    public final void zza(Context context0, @Nullable String s, String s1, Bundle bundle0, boolean z) {
        zzayx.zza(context0, s, s1, bundle0, true, new zzaza(this));
    }

    public final void zza(ViewGroup viewGroup0, zzuk zzuk0, String s) {
        this.zza(viewGroup0, zzuk0, s, 0xFF000000, -1);
    }

    public final void zza(ViewGroup viewGroup0, zzuk zzuk0, String s, String s1) {
        zzazh.zzfa(s1);
        this.zza(viewGroup0, zzuk0, s, 0xFFFF0000, 0xFF000000);
    }

    public static int zzb(Context context0, int v) {
        return zzayx.zza(context0.getResources().getDisplayMetrics(), v);
    }

    public static int zzb(DisplayMetrics displayMetrics0, int v) {
        return Math.round(((float)v) / displayMetrics0.density);
    }

    public static String zzbl(Context context0) {
        ContentResolver contentResolver0 = context0.getContentResolver();
        String s = contentResolver0 == null ? null : Settings.Secure.getString(contentResolver0, "android_id");
        if(s == null || zzayx.zzxi()) {
            s = "emulator";
        }
        return zzayx.zzet(s);
    }

    @Nullable
    public static String zzbm(Context context0) {
        ContentResolver contentResolver0 = context0.getContentResolver();
        return contentResolver0 == null ? null : Settings.Secure.getString(contentResolver0, "android_id");
    }

    public static boolean zzbn(Context context0) {
        switch(GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context0, 12451000)) {
            case 0: 
            case 2: {
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static boolean zzbo(Context context0) {
        if(context0.getResources().getConfiguration().orientation != 2) {
            return false;
        }
        DisplayMetrics displayMetrics0 = context0.getResources().getDisplayMetrics();
        return ((int)(((float)displayMetrics0.heightPixels) / displayMetrics0.density)) < 600;
    }

    @TargetApi(17)
    public static boolean zzbp(Context context0) {
        DisplayMetrics displayMetrics0 = context0.getResources().getDisplayMetrics();
        Display display0 = ((WindowManager)context0.getSystemService("window")).getDefaultDisplay();
        display0.getRealMetrics(displayMetrics0);
        int v = displayMetrics0.heightPixels;
        int v1 = displayMetrics0.widthPixels;
        display0.getMetrics(displayMetrics0);
        return displayMetrics0.heightPixels == v && displayMetrics0.widthPixels == v1;
    }

    public static int zzbq(Context context0) {
        int v = context0.getResources().getIdentifier("navigation_bar_width", "dimen", "android");
        return v <= 0 ? 0 : context0.getResources().getDimensionPixelSize(v);
    }

    public static Throwable zzc(Throwable throwable0) {
        if(((Boolean)zzabo.zzcvm.get()).booleanValue()) {
            return throwable0;
        }
        LinkedList linkedList0 = new LinkedList();
        while(throwable0 != null) {
            linkedList0.push(throwable0);
            throwable0 = throwable0.getCause();
        }
        Throwable throwable1 = null;
        while(!linkedList0.isEmpty()) {
            Throwable throwable2 = (Throwable)linkedList0.pop();
            StackTraceElement[] arr_stackTraceElement = throwable2.getStackTrace();
            ArrayList arrayList0 = new ArrayList();
            arrayList0.add(new StackTraceElement(throwable2.getClass().getName(), "<filtered>", "<filtered>", 1));
            boolean z = false;
            for(int v = 0; v < arr_stackTraceElement.length; ++v) {
                StackTraceElement stackTraceElement0 = arr_stackTraceElement[v];
                if(zzayx.zzeu(stackTraceElement0.getClassName())) {
                    arrayList0.add(stackTraceElement0);
                    z = true;
                }
                else {
                    String s = stackTraceElement0.getClassName();
                    if(TextUtils.isEmpty(s) || !s.startsWith("android.") && !s.startsWith("java.")) {
                        arrayList0.add(new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1));
                    }
                    else {
                        arrayList0.add(stackTraceElement0);
                    }
                }
            }
            if(z) {
                throwable1 = throwable1 == null ? new Throwable(throwable2.getMessage()) : new Throwable(throwable2.getMessage(), throwable1);
                throwable1.setStackTrace(((StackTraceElement[])arrayList0.toArray(new StackTraceElement[0])));
            }
        }
        return throwable1;
    }

    public final int zzc(Context context0, int v) {
        if(this.zzdwt < 0.0f) {
            synchronized(this) {
                if(this.zzdwt < 0.0f) {
                    Display display0 = ((WindowManager)context0.getSystemService("window")).getDefaultDisplay();
                    DisplayMetrics displayMetrics0 = new DisplayMetrics();
                    display0.getMetrics(displayMetrics0);
                    this.zzdwt = displayMetrics0.density;
                }
            }
            return Math.round(((float)v) / this.zzdwt);
        }
        return Math.round(((float)v) / this.zzdwt);
    }

    public static boolean zzd(Context context0, int v) {
        return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context0, v) == 0;
    }

    public static String zzet(String s) {
        for(int v = 0; true; ++v) {
            try {
                MessageDigest messageDigest0 = MessageDigest.getInstance("MD5");
                messageDigest0.update(s.getBytes());
                return String.format(Locale.US, "%032X", new BigInteger(1, messageDigest0.digest()));
            }
            catch(NoSuchAlgorithmException unused_ex) {
            }
            catch(ArithmeticException unused_ex) {
                break;
            }
        }
        return null;
    }

    // 去混淆评级： 低(20)
    @VisibleForTesting
    public static boolean zzeu(String s) {
        return TextUtils.isEmpty(s) ? false : s.startsWith(((String)zzabo.zzcvk.get()));
    }

    public static boolean zzxi() {
        return Build.DEVICE.startsWith("generic");
    }

    public static boolean zzxj() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static String zzxk() [...] // 潜在的解密器
}

