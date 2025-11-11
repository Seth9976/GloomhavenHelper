package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.util.JsonWriter;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import java.io.IOException;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

public final class zzazb {
    @GuardedBy("lock")
    private static boolean enabled;
    private static Object lock;
    private static Clock zzbmz;
    @GuardedBy("lock")
    private static boolean zzdwy;
    private static final Set zzdwz;
    private final List zzdxa;

    static {
        zzazb.lock = new Object();
        zzazb.zzdwy = false;
        zzazb.enabled = false;
        zzazb.zzbmz = DefaultClock.getInstance();
        zzazb.zzdwz = new HashSet(Arrays.asList(new String[0]));
    }

    public zzazb() {
        this(null);
    }

    public zzazb(@Nullable String s) {
        List list0 = zzazb.isEnabled() ? Arrays.asList(new String[]{"network_request_b30f1431-25b8-4044-b8be-6401ce716a0a"}) : new ArrayList();
        this.zzdxa = list0;
    }

    public static boolean isEnabled() {
        synchronized(zzazb.lock) {
        }
        return zzazb.zzdwy && zzazb.enabled;
    }

    // 检测为 Lambda 实现
    static final void zza(int v, Map map0, JsonWriter jsonWriter0) throws IOException [...]

    private static void zza(JsonWriter jsonWriter0, @Nullable Map map0) throws IOException {
        if(map0 == null) {
            return;
        }
        jsonWriter0.name("headers").beginArray();
        for(Object object0: map0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            String s = (String)map$Entry0.getKey();
            if(zzazb.zzdwz.contains(s)) {
            }
            else if(map$Entry0.getValue() instanceof List) {
                for(Object object1: ((List)map$Entry0.getValue())) {
                    jsonWriter0.beginObject();
                    jsonWriter0.name("name").value(s);
                    jsonWriter0.name("value").value(((String)object1));
                    jsonWriter0.endObject();
                }
            }
            else if(map$Entry0.getValue() instanceof String) {
                jsonWriter0.beginObject();
                jsonWriter0.name("name").value(s);
                jsonWriter0.name("value").value(((String)map$Entry0.getValue()));
                jsonWriter0.endObject();
            }
            else {
                zzazh.zzey("Connection headers should be either Map<String, String> or Map<String, List<String>>");
                if(true) {
                    break;
                }
            }
        }
        jsonWriter0.endArray();
    }

    // 检测为 Lambda 实现
    static final void zza(String s, JsonWriter jsonWriter0) throws IOException [...]

    private final void zza(String s, zzazi zzazi0) {
        StringWriter stringWriter0 = new StringWriter();
        JsonWriter jsonWriter0 = new JsonWriter(stringWriter0);
        try {
            jsonWriter0.beginObject();
            jsonWriter0.name("timestamp").value(zzazb.zzbmz.currentTimeMillis());
            jsonWriter0.name("event").value(s);
            jsonWriter0.name("components").beginArray();
            for(Object object0: this.zzdxa) {
                jsonWriter0.value(((String)object0));
            }
            jsonWriter0.endArray();
            zzazi0.zzb(jsonWriter0);
            jsonWriter0.endObject();
            jsonWriter0.flush();
            jsonWriter0.close();
        }
        catch(IOException iOException0) {
            zzazh.zzc("unable to log", iOException0);
        }
        zzazb.zzex(stringWriter0.toString());
    }

    // 检测为 Lambda 实现
    static final void zza(String s, String s1, Map map0, byte[] arr_b, JsonWriter jsonWriter0) throws IOException [...]

    // 检测为 Lambda 实现
    static final void zza(byte[] arr_b, JsonWriter jsonWriter0) throws IOException [...]

    public final void zza(String s, String s1, @Nullable Map map0, @Nullable byte[] arr_b) {
        if(!zzazb.isEnabled()) {
            return;
        }
        this.zzb(s, s1, map0, arr_b);
    }

    public final void zza(HttpURLConnection httpURLConnection0, int v) {
        String s = null;
        if(!zzazb.isEnabled()) {
            return;
        }
        this.zzb((httpURLConnection0.getHeaderFields() == null ? null : new HashMap(httpURLConnection0.getHeaderFields())), v);
        if(v < 200 || v >= 300) {
            try {
                s = httpURLConnection0.getResponseMessage();
            }
            catch(IOException iOException0) {
                String s1 = iOException0.getMessage();
                zzazh.zzfa((s1.length() == 0 ? new String("Can not get error message from error HttpURLConnection\n") : "Can not get error message from error HttpURLConnection\n" + s1));
            }
            this.zzew(s);
        }
    }

    public final void zza(HttpURLConnection httpURLConnection0, @Nullable byte[] arr_b) {
        if(!zzazb.isEnabled()) {
            return;
        }
        HashMap hashMap0 = httpURLConnection0.getRequestProperties() == null ? null : new HashMap(httpURLConnection0.getRequestProperties());
        this.zzb(new String(httpURLConnection0.getURL().toString()), new String(httpURLConnection0.getRequestMethod()), hashMap0, arr_b);
    }

    public final void zza(@Nullable Map map0, int v) {
        if(!zzazb.isEnabled()) {
            return;
        }
        this.zzb(map0, v);
        if(v < 200 || v >= 300) {
            this.zzew(null);
        }
    }

    public static void zzar(boolean z) {
        synchronized(zzazb.lock) {
            zzazb.zzdwy = true;
            zzazb.enabled = z;
        }
    }

    private final void zzb(String s, String s1, @Nullable Map map0, @Nullable byte[] arr_b) {
        this.zza("onNetworkRequest", (JsonWriter jsonWriter0) -> {
            jsonWriter0.name("params").beginObject();
            jsonWriter0.name("firstline").beginObject();
            jsonWriter0.name("uri").value(s);
            jsonWriter0.name("verb").value(s1);
            jsonWriter0.endObject();
            zzazb.zza(jsonWriter0, map0);
            if(arr_b != null) {
                jsonWriter0.name("body").value(Base64Utils.encode(arr_b));
            }
            jsonWriter0.endObject();
        });
    }

    private final void zzb(@Nullable Map map0, int v) {
        this.zza("onNetworkResponse", (JsonWriter jsonWriter0) -> {
            jsonWriter0.name("params").beginObject();
            jsonWriter0.name("firstline").beginObject();
            jsonWriter0.name("code").value(((long)v));
            jsonWriter0.endObject();
            zzazb.zza(jsonWriter0, map0);
            jsonWriter0.endObject();
        });
    }

    public static boolean zzbr(Context context0) {
        if(Build.VERSION.SDK_INT < 17) {
            return false;
        }
        if(!((Boolean)zzabh.zzcuu.get()).booleanValue()) {
            return false;
        }
        try {
            if(Settings.Global.getInt(context0.getContentResolver(), "development_settings_enabled", 0) != 0) {
                return true;
            }
        }
        catch(Exception exception0) {
            zzazh.zzd("Fail to determine debug setting.", exception0);
        }
        return false;
    }

    public final void zzev(@Nullable String s) {
        if(!zzazb.isEnabled()) {
            return;
        }
        if(s == null) {
            return;
        }
        this.zzi(s.getBytes());
    }

    private final void zzew(@Nullable String s) {
        this.zza("onNetworkRequestError", (JsonWriter jsonWriter0) -> {
            jsonWriter0.name("params").beginObject();
            if(s != null) {
                jsonWriter0.name("error_description").value(s);
            }
            jsonWriter0.endObject();
        });
    }

    private static void zzex(String s) {
        synchronized(zzazb.class) {
            zzazh.zzez("GMA Debug BEGIN");
            for(int v1 = 0; v1 < s.length(); v1 += 4000) {
                String s1 = s.substring(v1, Math.min(v1 + 4000, s.length()));
                zzazh.zzez((s1.length() == 0 ? new String("GMA Debug CONTENT ") : "GMA Debug CONTENT " + s1));
            }
            zzazh.zzez("GMA Debug FINISH");
        }
    }

    public final void zzi(byte[] arr_b) {
        this.zza("onNetworkResponseBody", (JsonWriter jsonWriter0) -> {
            jsonWriter0.name("params").beginObject();
            String s = Base64Utils.encode(arr_b);
            if(arr_b.length < 10000) {
                jsonWriter0.name("body").value(s);
            }
            else {
                String s1 = zzayx.zzet(s);
                if(s1 != null) {
                    jsonWriter0.name("bodydigest").value(s1);
                }
            }
            jsonWriter0.name("bodylength").value(((long)arr_b.length));
            jsonWriter0.endObject();
        });
    }

    public static void zzxp() {
        synchronized(zzazb.lock) {
            zzazb.zzdwy = false;
            zzazb.enabled = false;
            zzazh.zzfa("Ad debug logging enablement is out of date.");
        }
    }

    public static boolean zzxq() {
        synchronized(zzazb.lock) {
        }
        return zzazb.zzdwy;
    }
}

