package com.google.android.gms.internal.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.ActivityManager;
import android.app.AlertDialog.Builder;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Process;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.WindowManager;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.impl.R.string;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzawo {
    @GuardedBy("userAgentLock")
    private String zzbfa;
    private AtomicReference zzdtv;
    private AtomicReference zzdtw;
    public static final zzdkp zzdtx;
    private final Object zzdty;
    private boolean zzdtz;
    private boolean zzdua;
    private boolean zzyc;

    static {
        zzawo.zzdtx = new zzawi(Looper.getMainLooper());
    }

    public zzawo() {
        this.zzdtv = new AtomicReference(null);
        this.zzdtw = new AtomicReference(null);
        this.zzyc = true;
        this.zzdty = new Object();
        this.zzdtz = false;
        this.zzdua = false;
    }

    private static int zza(char c) {
        if(c >= 0x30 && c <= 57) {
            return c - 0x30;
        }
        if(c >= 65 && c <= 70) {
            return c - 55;
        }
        if(c < 97 || c > 102) {
            throw new IllegalArgumentException("Invalid Hex.");
        }
        return c - 87;
    }

    public static DisplayMetrics zza(WindowManager windowManager0) {
        DisplayMetrics displayMetrics0 = new DisplayMetrics();
        windowManager0.getDefaultDisplay().getMetrics(displayMetrics0);
        return displayMetrics0;
    }

    public static PopupWindow zza(View view0, int v, int v1, boolean z) {
        return new PopupWindow(view0, v, v1, false);
    }

    public static String zza(InputStreamReader inputStreamReader0) throws IOException {
        StringBuilder stringBuilder0 = new StringBuilder(0x2000);
        char[] arr_c = new char[0x800];
        int v;
        while((v = inputStreamReader0.read(arr_c)) != -1) {
            stringBuilder0.append(arr_c, 0, v);
        }
        return stringBuilder0.toString();
    }

    private final JSONArray zza(Collection collection0) throws JSONException {
        JSONArray jSONArray0 = new JSONArray();
        for(Object object0: collection0) {
            this.zza(jSONArray0, object0);
        }
        return jSONArray0;
    }

    public static void zza(Context context0, Intent intent0) {
        try {
            context0.startActivity(intent0);
        }
        catch(Throwable unused_ex) {
            intent0.addFlags(0x10000000);
            context0.startActivity(intent0);
        }
    }

    @TargetApi(18)
    public static void zza(Context context0, Uri uri0) {
        try {
            Intent intent0 = new Intent("android.intent.action.VIEW", uri0);
            Bundle bundle0 = new Bundle();
            intent0.putExtras(bundle0);
            zzawo.zzb(context0, intent0);
            bundle0.putString("com.android.browser.application_id", "com.esotericsoftware.gloomhavenhelper");
            context0.startActivity(intent0);
            zzawf.zzeb(("Opening " + uri0.toString() + " in a new browser."));
        }
        catch(ActivityNotFoundException activityNotFoundException0) {
            zzawf.zzc("No browser is found.", activityNotFoundException0);
        }
    }

    public static void zza(Context context0, Throwable throwable0) {
        if(context0 == null) {
            return;
        }
        try {
            boolean z = false;
            z = ((Boolean)zzabo.zzcvi.get()).booleanValue();
        }
        catch(IllegalStateException unused_ex) {
        }
        if(z) {
            CrashUtils.addDynamiteErrorToDropBox(context0, throwable0);
        }
    }

    private final void zza(JSONArray jSONArray0, Object object0) throws JSONException {
        if(object0 instanceof Bundle) {
            jSONArray0.put(this.zzd(((Bundle)object0)));
            return;
        }
        if(object0 instanceof Map) {
            jSONArray0.put(this.zzi(((Map)object0)));
            return;
        }
        if(object0 instanceof Collection) {
            jSONArray0.put(this.zza(((Collection)object0)));
            return;
        }
        if(object0 instanceof Object[]) {
            JSONArray jSONArray1 = new JSONArray();
            for(int v = 0; v < ((Object[])object0).length; ++v) {
                this.zza(jSONArray1, ((Object[])object0)[v]);
            }
            jSONArray0.put(jSONArray1);
            return;
        }
        jSONArray0.put(object0);
    }

    private final void zza(JSONObject jSONObject0, String s, Object object0) throws JSONException {
        if(object0 instanceof Bundle) {
            jSONObject0.put(s, this.zzd(((Bundle)object0)));
            return;
        }
        if(object0 instanceof Map) {
            jSONObject0.put(s, this.zzi(((Map)object0)));
            return;
        }
        if(object0 instanceof Collection) {
            if(s == null) {
                s = "null";
            }
            jSONObject0.put(s, this.zza(((Collection)object0)));
            return;
        }
        if(object0 instanceof Object[]) {
            jSONObject0.put(s, this.zza(Arrays.asList(((Object[])object0))));
            return;
        }
        jSONObject0.put(s, object0);
    }

    private static boolean zza(String s, AtomicReference atomicReference0, String s1) {
        if(TextUtils.isEmpty(s)) {
            return false;
        }
        try {
            Pattern pattern0 = (Pattern)atomicReference0.get();
            if(pattern0 == null || !s1.equals(pattern0.pattern())) {
                pattern0 = Pattern.compile(s1);
                atomicReference0.set(pattern0);
            }
            return pattern0.matcher(s).matches();
        }
        catch(PatternSyntaxException unused_ex) {
            return false;
        }
    }

    public final JSONObject zza(@Nullable Bundle bundle0, JSONObject jSONObject0) {
        if(bundle0 != null) {
            try {
                return this.zzd(bundle0);
            }
            catch(JSONException jSONException0) {
                zzawf.zzc("Error converting Bundle to JSON", jSONException0);
                return null;
            }
        }
        return null;
    }

    public final void zza(Context context0, String s, WebSettings webSettings0) {
        webSettings0.setUserAgentString(this.zzr(context0, s));
    }

    public final void zza(Context context0, @Nullable String s, String s1, Bundle bundle0, boolean z) {
        bundle0.putString("device", zzawo.zzwq());
        bundle0.putString("eids", TextUtils.join(",", zzzx.zzqj()));
        zzayx.zza(context0, s, s1, bundle0, true, new zzawp(this, context0, s));
    }

    public final void zza(Context context0, String s, boolean z, HttpURLConnection httpURLConnection0) {
        httpURLConnection0.setConnectTimeout(60000);
        httpURLConnection0.setInstanceFollowRedirects(false);
        httpURLConnection0.setReadTimeout(60000);
        httpURLConnection0.setRequestProperty("User-Agent", this.zzr(context0, s));
        httpURLConnection0.setUseCaches(false);
    }

    public final boolean zza(View view0, Context context0) {
        Context context1 = context0.getApplicationContext();
        return context1 == null ? this.zza(view0, null, zzawo.zzay(context0)) : this.zza(view0, ((PowerManager)context1.getSystemService("power")), zzawo.zzay(context0));
    }

    // 去混淆评级： 中等(55)
    public final boolean zza(View view0, PowerManager powerManager0, KeyguardManager keyguardManager0) {
        boolean z = zzq.zzkv().zzyc || (!(keyguardManager0 == null ? false : keyguardManager0.inKeyguardRestrictedInputMode()) || zzawo.zzo(view0));
        return view0.getVisibility() != 0 || !view0.isShown() ? false : (powerManager0 == null || powerManager0.isScreenOn()) && z && (!((Boolean)zzvh.zzpd().zzd(zzzx.zzckz)).booleanValue() || view0.getLocalVisibleRect(new Rect()) || view0.getGlobalVisibleRect(new Rect()));
    }

    public final boolean zzaq(Context context0) {
        if(this.zzdtz) {
            return false;
        }
        IntentFilter intentFilter0 = new IntentFilter();
        intentFilter0.addAction("android.intent.action.USER_PRESENT");
        intentFilter0.addAction("android.intent.action.SCREEN_OFF");
        context0.getApplicationContext().registerReceiver(new zzawr(this, null), intentFilter0);
        this.zzdtz = true;
        return true;
    }

    public final boolean zzar(Context context0) {
        if(this.zzdua) {
            return false;
        }
        IntentFilter intentFilter0 = new IntentFilter();
        intentFilter0.addAction("com.google.android.ads.intent.DEBUG_LOGGING_ENABLEMENT_CHANGED");
        context0.getApplicationContext().registerReceiver(new zzaws(this, null), intentFilter0);
        this.zzdua = true;
        return true;
    }

    @VisibleForTesting
    protected static String zzas(Context context0) {
        try {
            return new WebView(context0).getSettings().getUserAgentString();
        }
        catch(Throwable unused_ex) {
            return zzawo.zzwo();
        }
    }

    public static AlertDialog.Builder zzat(Context context0) {
        return new AlertDialog.Builder(context0);
    }

    public static zzze zzau(Context context0) {
        return new zzze(context0);
    }

    @SuppressLint({"NewApi"})
    public static String zzav(Context context0) {
        try {
            ActivityManager activityManager0 = (ActivityManager)context0.getSystemService("activity");
            if(activityManager0 == null) {
                return null;
            }
            List list0 = activityManager0.getRunningTasks(1);
            if(list0 != null && !list0.isEmpty()) {
                ActivityManager.RunningTaskInfo activityManager$RunningTaskInfo0 = (ActivityManager.RunningTaskInfo)list0.get(0);
                return activityManager$RunningTaskInfo0 == null || activityManager$RunningTaskInfo0.topActivity == null ? null : activityManager$RunningTaskInfo0.topActivity.getClassName();
            }
        }
        catch(Exception unused_ex) {
        }
        return null;
    }

    public static boolean zzaw(Context context0) {
        try {
            ActivityManager activityManager0 = (ActivityManager)context0.getSystemService("activity");
            KeyguardManager keyguardManager0 = (KeyguardManager)context0.getSystemService("keyguard");
            if(activityManager0 == null || keyguardManager0 == null) {
                return false;
            }
            List list0 = activityManager0.getRunningAppProcesses();
            if(list0 == null) {
                return false;
            }
            for(Object object0: list0) {
                ActivityManager.RunningAppProcessInfo activityManager$RunningAppProcessInfo0 = (ActivityManager.RunningAppProcessInfo)object0;
                if(Process.myPid() == activityManager$RunningAppProcessInfo0.pid) {
                    if(activityManager$RunningAppProcessInfo0.importance != 100 || keyguardManager0.inKeyguardRestrictedInputMode()) {
                        break;
                    }
                    PowerManager powerManager0 = (PowerManager)context0.getSystemService("power");
                    return !(powerManager0 == null ? false : powerManager0.isScreenOn());
                }
                if(false) {
                    break;
                }
            }
            return true;
        }
        catch(Throwable unused_ex) {
            return false;
        }
    }

    public static int zzax(Context context0) {
        return context0.getApplicationInfo() == null ? 0 : 30;
    }

    @Nullable
    private static KeyguardManager zzay(Context context0) {
        Object object0 = context0.getSystemService("keyguard");
        return object0 == null || !(object0 instanceof KeyguardManager) ? null : ((KeyguardManager)object0);
    }

    @TargetApi(16)
    public static boolean zzaz(Context context0) {
        if(context0 != null) {
            KeyguardManager keyguardManager0 = zzawo.zzay(context0);
            return keyguardManager0 != null && keyguardManager0.isKeyguardLocked();
        }
        return false;
    }

    @TargetApi(18)
    public static void zzb(Context context0, Intent intent0) {
        if(intent0 == null) {
            return;
        }
        Bundle bundle0 = intent0.getExtras() == null ? new Bundle() : intent0.getExtras();
        bundle0.putBinder("android.support.customtabs.extra.SESSION", null);
        bundle0.putString("com.android.browser.application_id", "com.esotericsoftware.gloomhavenhelper");
        intent0.putExtras(bundle0);
    }

    public static void zzb(Context context0, String s, String s1) {
        ArrayList arrayList0 = new ArrayList();
        arrayList0.add(s1);
        int v = arrayList0.size();
        int v1 = 0;
        while(v1 < v) {
            Object object0 = arrayList0.get(v1);
            ++v1;
            new zzayo(context0, s, ((String)object0)).zzvw();
        }
    }

    public static boolean zzba(Context context0) {
        try {
            context0.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi");
            return false;
        }
        catch(ClassNotFoundException unused_ex) {
            return true;
        }
        catch(Throwable throwable0) {
            zzawf.zzc("Error loading class.", throwable0);
            zzq.zzkz().zza(throwable0, "AdUtil.isLiteSdk");
            return false;
        }
    }

    public static String zzbb(Context context0) {
        return ((Boolean)zzvh.zzpd().zzd(zzzx.zzcqv)).booleanValue() ? context0.getSharedPreferences("mobileads_consent", 0).getString("consent_string", "") : "";
    }

    public static Bundle zzbc(Context context0) {
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcqw)).booleanValue()) {
            return null;
        }
        SharedPreferences sharedPreferences0 = PreferenceManager.getDefaultSharedPreferences(context0);
        Bundle bundle0 = new Bundle();
        if(sharedPreferences0.contains("IABConsent_CMPPresent")) {
            bundle0.putBoolean("IABConsent_CMPPresent", sharedPreferences0.getBoolean("IABConsent_CMPPresent", false));
        }
        for(int v = 0; v < 4; ++v) {
            String s = new String[]{"IABConsent_SubjectToGDPR", "IABConsent_ConsentString", "IABConsent_ParsedPurposeConsents", "IABConsent_ParsedVendorConsents"}[v];
            if(sharedPreferences0.contains(s)) {
                bundle0.putString(s, sharedPreferences0.getString(s, null));
            }
        }
        return bundle0;
    }

    public static boolean zzbd(Context context0) {
        if(!(context0 instanceof Activity)) {
            return false;
        }
        Window window0 = ((Activity)context0).getWindow();
        if(window0 != null && window0.getDecorView() != null) {
            Rect rect0 = new Rect();
            Rect rect1 = new Rect();
            window0.getDecorView().getGlobalVisibleRect(rect0, null);
            window0.getDecorView().getWindowVisibleDisplayFrame(rect1);
            return rect0.bottom != 0 && rect1.bottom != 0 && rect0.top == rect1.top;
        }
        return false;
    }

    public static String zzbe(Context context0) {
        Bundle bundle0;
        if(context0.getApplicationContext() != null) {
            context0 = context0.getApplicationContext();
        }
        try {
            bundle0 = Wrappers.packageManager(context0).getApplicationInfo("com.esotericsoftware.gloomhavenhelper", 0x80).metaData;
        }
        catch(PackageManager.NameNotFoundException | NullPointerException packageManager$NameNotFoundException0) {
            zzawf.zza("Error getting metadata", packageManager$NameNotFoundException0);
            return "";
        }
        if(bundle0 == null) {
            return "";
        }
        String s = bundle0.getString("com.google.android.gms.ads.APPLICATION_ID");
        if(TextUtils.isEmpty(s)) {
            return "";
        }
        return s.matches("^ca-app-pub-[0-9]{16}~[0-9]{10}$") || s.matches("^/\\d+~.+$") ? s : "";
    }

    public static void zzc(Context context0, String s, String s1) {
        try {
            FileOutputStream fileOutputStream0 = context0.openFileOutput(s, 0);
            fileOutputStream0.write(s1.getBytes("UTF-8"));
            fileOutputStream0.close();
        }
        catch(Exception exception0) {
            zzawf.zzc("Error writing to file in internal storage.", exception0);
        }
    }

    public static void zzc(Runnable runnable0) {
        if(Looper.getMainLooper().getThread() != Thread.currentThread()) {
            runnable0.run();
            return;
        }
        zzazq.zzdxk.execute(runnable0);
    }

    @Nullable
    public static WebResourceResponse zzd(Context context0, String s, String s1) {
        try {
            HashMap hashMap0 = new HashMap();
            hashMap0.put("User-Agent", zzq.zzkv().zzr(context0, s));
            hashMap0.put("Cache-Control", "max-stale=3600");
            String s2 = (String)new zzaxx(context0).zzc(s1, hashMap0).get(60L, TimeUnit.SECONDS);
            if(s2 != null) {
                return new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream(s2.getBytes("UTF-8")));
            }
        }
        catch(IOException | ExecutionException | InterruptedException | TimeoutException iOException0) {
            zzawf.zzd("Could not fetch MRAID JS.", iOException0);
        }
        return null;
    }

    public static WebResourceResponse zzd(HttpURLConnection httpURLConnection0) throws IOException {
        String s = "";
        String s1 = httpURLConnection0.getContentType();
        String s2 = TextUtils.isEmpty(s1) ? "" : s1.split(";")[0].trim();
        String s3 = httpURLConnection0.getContentType();
        if(!TextUtils.isEmpty(s3)) {
            String[] arr_s = s3.split(";");
            if(arr_s.length != 1) {
                for(int v = 1; v < arr_s.length; ++v) {
                    if(arr_s[v].trim().startsWith("charset")) {
                        String[] arr_s1 = arr_s[v].trim().split("=");
                        if(arr_s1.length > 1) {
                            s = arr_s1[1].trim();
                            break;
                        }
                    }
                }
            }
        }
        Map map0 = httpURLConnection0.getHeaderFields();
        HashMap hashMap0 = new HashMap(map0.size());
        for(Object object0: map0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            if(map$Entry0.getKey() != null && map$Entry0.getValue() != null && ((List)map$Entry0.getValue()).size() > 0) {
                hashMap0.put(((String)map$Entry0.getKey()), ((String)((List)map$Entry0.getValue()).get(0)));
            }
        }
        return zzq.zzkx().zza(s2, s, httpURLConnection0.getResponseCode(), httpURLConnection0.getResponseMessage(), hashMap0, httpURLConnection0.getInputStream());
    }

    public static int[] zzd(Activity activity0) {
        Window window0 = activity0.getWindow();
        if(window0 != null) {
            View view0 = window0.findViewById(0x1020002);
            return view0 == null ? zzawo.zzwr() : new int[]{view0.getWidth(), view0.getHeight()};
        }
        return zzawo.zzwr();
    }

    public final JSONObject zzd(Bundle bundle0) throws JSONException {
        JSONObject jSONObject0 = new JSONObject();
        for(Object object0: bundle0.keySet()) {
            this.zza(jSONObject0, ((String)object0), bundle0.get(((String)object0)));
        }
        return jSONObject0;
    }

    public final int[] zze(Activity activity0) {
        int[] arr_v = zzawo.zzd(activity0);
        return new int[]{zzvh.zzoz().zzc(activity0, arr_v[0]), zzvh.zzoz().zzc(activity0, arr_v[1])};
    }

    public static int zzej(String s) {
        try {
            return Integer.parseInt(s);
        }
        catch(NumberFormatException numberFormatException0) {
            zzawf.zzfa(("Could not parse value:" + numberFormatException0));
            return 0;
        }
    }

    // 去混淆评级： 低(20)
    public static boolean zzek(String s) {
        return TextUtils.isEmpty(s) ? false : s.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
    }

    public final boolean zzel(String s) {
        return zzawo.zza(s, this.zzdtv, ((String)zzvh.zzpd().zzd(zzzx.zzcit)));
    }

    public final boolean zzem(String s) {
        return zzawo.zza(s, this.zzdtw, ((String)zzvh.zzpd().zzd(zzzx.zzciu)));
    }

    public static boolean zzen(String s) {
        if(!zzazb.isEnabled()) {
            return false;
        }
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcov)).booleanValue()) {
            return false;
        }
        String s1 = (String)zzvh.zzpd().zzd(zzzx.zzcox);
        if(!s1.isEmpty()) {
            String[] arr_s = s1.split(";");
            for(int v = 0; v < arr_s.length; ++v) {
                if(arr_s[v].equals(s)) {
                    return false;
                }
            }
        }
        String s2 = (String)zzvh.zzpd().zzd(zzzx.zzcow);
        if(s2.isEmpty()) {
            return true;
        }
        String[] arr_s1 = s2.split(";");
        for(int v1 = 0; v1 < arr_s1.length; ++v1) {
            if(arr_s1[v1].equals(s)) {
                return true;
            }
        }
        return false;
    }

    public final int[] zzf(Activity activity0) {
        Window window0 = activity0.getWindow();
        if(window0 != null) {
            View view0 = window0.findViewById(0x1020002);
            if(view0 != null) {
                new int[]{view0.getTop(), view0.getBottom()};
                int v = view0.getTop();
                int v1 = view0.getBottom();
                return new int[]{zzvh.zzoz().zzc(activity0, v), zzvh.zzoz().zzc(activity0, v1)};
            }
        }
        int[] arr_v = zzawo.zzwr();
        return new int[]{zzvh.zzoz().zzc(activity0, arr_v[0]), zzvh.zzoz().zzc(activity0, arr_v[1])};
    }

    public static Map zzi(Uri uri0) {
        if(!((Boolean)zzabe.zzcuf.get()).booleanValue()) {
            if(uri0 == null) {
                return null;
            }
            Map map0 = new HashMap();
            for(Object object0: uri0.getQueryParameterNames()) {
                ((HashMap)map0).put(((String)object0), uri0.getQueryParameter(((String)object0)));
            }
            return map0;
        }
        if(uri0 == null) {
            return null;
        }
        Map map1 = new HashMap(20);
        String s = uri0.getEncodedQuery();
        if(s == null) {
            return map1;
        }
        int v = s.length();
        StringBuilder stringBuilder0 = new StringBuilder(100);
        StringBuilder stringBuilder1 = new StringBuilder(v);
        StringBuilder stringBuilder2 = stringBuilder0;
        int v1 = 0;
        while(v1 < v) {
            int v2 = s.charAt(v1);
            switch(v2) {
                case 37: {
                    byte[] arr_b = new byte[(v - v1) / 3];
                    int v3 = 0;
                    while(v1 < v - 2 && v2 == 37) {
                        try {
                            arr_b[v3] = (byte)((zzawo.zza(s.charAt(v1 + 1)) << 4) + zzawo.zza(s.charAt(v1 + 2)));
                            ++v3;
                        }
                        catch(IllegalArgumentException unused_ex) {
                        }
                        v1 += 3;
                        if(v1 < v) {
                            v2 = s.charAt(v1);
                        }
                    }
                    try {
                        stringBuilder2.append(new String(arr_b, 0, v3, "UTF-8"));
                    }
                    catch(UnsupportedEncodingException unused_ex) {
                    }
                    if(v2 != 37) {
                        continue;
                    }
                    break;
                }
                case 38: {
                    if(stringBuilder0.length() > 0 && stringBuilder2 != stringBuilder0) {
                        ((HashMap)map1).put(stringBuilder0.toString(), stringBuilder1.toString());
                        stringBuilder0.setLength(0);
                        stringBuilder1.setLength(0);
                    }
                    stringBuilder2 = stringBuilder0;
                    break;
                }
                case 43: {
                    stringBuilder2.append(' ');
                    break;
                }
                case 61: {
                    if(stringBuilder2 == stringBuilder1) {
                        stringBuilder2.append('=');
                    }
                    else {
                        stringBuilder2 = stringBuilder1;
                    }
                    break;
                }
                default: {
                    stringBuilder2.append(((char)v2));
                }
            }
            ++v1;
        }
        if(stringBuilder0.length() > 0 && stringBuilder2 != stringBuilder0) {
            ((HashMap)map1).put(stringBuilder0.toString(), stringBuilder1.toString());
        }
        return map1;
    }

    public final JSONObject zzi(Map map0) throws JSONException {
        try {
            JSONObject jSONObject0 = new JSONObject();
            for(Object object0: map0.keySet()) {
                this.zza(jSONObject0, ((String)object0), map0.get(((String)object0)));
            }
            return jSONObject0;
        }
        catch(ClassCastException classCastException0) {
            String s = classCastException0.getMessage();
            throw new JSONException((s.length() == 0 ? new String("Could not convert map to JSON: ") : "Could not convert map to JSON: " + s));
        }
    }

    public static Bitmap zzk(View view0) {
        view0.setDrawingCacheEnabled(true);
        Bitmap bitmap0 = Bitmap.createBitmap(view0.getDrawingCache());
        view0.setDrawingCacheEnabled(false);
        return bitmap0;
    }

    public static Bitmap zzl(View view0) {
        if(view0 == null) {
            return null;
        }
        Bitmap bitmap0 = zzawo.zzn(view0);
        return bitmap0 == null ? zzawo.zzm(view0) : bitmap0;
    }

    private static Bitmap zzm(@NonNull View view0) {
        try {
            int v = view0.getWidth();
            int v1 = view0.getHeight();
            if(v != 0 && v1 != 0) {
                Bitmap bitmap0 = Bitmap.createBitmap(view0.getWidth(), view0.getHeight(), Bitmap.Config.RGB_565);
                Canvas canvas0 = new Canvas(bitmap0);
                view0.layout(0, 0, v, v1);
                view0.draw(canvas0);
                return bitmap0;
            }
            zzawf.zzfa("Width or height of view is zero");
        }
        catch(RuntimeException runtimeException0) {
            zzawf.zzc("Fail to capture the webview", runtimeException0);
        }
        return null;
    }

    private static Bitmap zzn(@NonNull View view0) {
        Bitmap bitmap0 = null;
        try {
            boolean z = view0.isDrawingCacheEnabled();
            view0.setDrawingCacheEnabled(true);
            Bitmap bitmap1 = view0.getDrawingCache();
            if(bitmap1 != null) {
                bitmap0 = Bitmap.createBitmap(bitmap1);
            }
            view0.setDrawingCacheEnabled(z);
        }
        catch(RuntimeException runtimeException0) {
            zzawf.zzc("Fail to capture the web view", runtimeException0);
        }
        return bitmap0;
    }

    public static boolean zzo(View view0) {
        Activity activity0;
        View view1 = view0.getRootView();
        WindowManager.LayoutParams windowManager$LayoutParams0 = null;
        if(view1 == null) {
            activity0 = null;
        }
        else {
            Context context0 = view1.getContext();
            activity0 = context0 instanceof Activity ? ((Activity)context0) : null;
        }
        if(activity0 == null) {
            return false;
        }
        Window window0 = activity0.getWindow();
        if(window0 != null) {
            windowManager$LayoutParams0 = window0.getAttributes();
        }
        return windowManager$LayoutParams0 != null && (windowManager$LayoutParams0.flags & 0x80000) != 0;
    }

    public static int zzp(@Nullable View view0) {
        if(view0 == null) {
            return -1;
        }
        ViewParent viewParent0;
        for(viewParent0 = view0.getParent(); viewParent0 != null && !(viewParent0 instanceof AdapterView); viewParent0 = viewParent0.getParent()) {
        }
        return viewParent0 == null ? -1 : ((AdapterView)viewParent0).getPositionForView(view0);
    }

    public static boolean zzq(Context context0, String s) {
        return Wrappers.packageManager(zzars.zzac(context0)).checkPermission(s, "com.esotericsoftware.gloomhavenhelper") == 0;
    }

    public final String zzr(Context context0, String s) {
        synchronized(this.zzdty) {
            if(this.zzbfa != null) {
                return this.zzbfa;
            }
            if(s == null) {
                return zzawo.zzwo();
            }
            try {
                this.zzbfa = zzq.zzkx().getDefaultUserAgent(context0);
            }
            catch(Exception unused_ex) {
            }
            if(TextUtils.isEmpty(this.zzbfa)) {
                if(zzayx.zzxj()) {
                    this.zzbfa = zzawo.zzas(context0);
                }
                else {
                    this.zzbfa = null;
                    zzawq zzawq0 = new zzawq(this, context0);
                    zzawo.zzdtx.post(zzawq0);
                    while(this.zzbfa == null) {
                        try {
                            this.zzdty.wait();
                        }
                        catch(InterruptedException unused_ex) {
                            this.zzbfa = zzawo.zzwo();
                            String s2 = String.valueOf(this.zzbfa);
                            zzawf.zzfa((s2.length() == 0 ? new String("Interrupted, use default user agent: ") : "Interrupted, use default user agent: " + s2));
                        }
                    }
                }
            }
            try {
                this.zzbfa = this.zzbfa + " (Mobile; " + s;
                if(Wrappers.packageManager(context0).isCallerInstantApp()) {
                    this.zzbfa = this.zzbfa + ";aia";
                }
            }
            catch(Exception exception0) {
                zzq.zzkz().zza(exception0, "AdUtil.getUserAgent");
            }
            this.zzbfa = this.zzbfa + ")";
            return this.zzbfa;
        }
    }

    public static String zzs(Context context0, String s) {
        try {
            return new String(IOUtils.readInputStreamFully(context0.openFileInput(s), true), "UTF-8");
        }
        catch(IOException unused_ex) {
            zzawf.zzeb("Error reading from internal storage.");
            return "";
        }
    }

    private static String zzwo() {
        StringBuilder stringBuilder0 = new StringBuilder(0x100);
        stringBuilder0.append("Mozilla/5.0 (Linux; U; Android");
        if(Build.VERSION.RELEASE != null) {
            stringBuilder0.append(" ");
            stringBuilder0.append(Build.VERSION.RELEASE);
        }
        stringBuilder0.append("; ");
        stringBuilder0.append(Locale.getDefault());
        if(Build.DEVICE != null) {
            stringBuilder0.append("; ");
            stringBuilder0.append(Build.DEVICE);
            if(Build.DISPLAY != null) {
                stringBuilder0.append(" Build/");
                stringBuilder0.append(Build.DISPLAY);
            }
        }
        stringBuilder0.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return stringBuilder0.toString();
    }

    public static String zzwp() [...] // 潜在的解密器

    // 去混淆评级： 低(20)
    public static String zzwq() {
        return Build.MODEL.startsWith(Build.MANUFACTURER) ? Build.MODEL : Build.MANUFACTURER + " " + Build.MODEL;
    }

    private static int[] zzwr() {
        return new int[]{0, 0};
    }

    public static String zzws() {
        Resources resources0 = zzq.zzkz().getResources();
        return resources0 == null ? "Test Ad" : resources0.getString(string.s7);
    }
}

