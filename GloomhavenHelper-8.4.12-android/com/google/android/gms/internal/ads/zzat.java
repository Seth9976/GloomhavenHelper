package com.google.android.gms.internal.ads;

import androidx.annotation.VisibleForTesting;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;

public final class zzat extends zzah {
    private final zzav zzck;
    private final SSLSocketFactory zzcl;

    public zzat() {
        this(null);
    }

    private zzat(zzav zzav0) {
        this(null, null);
    }

    private zzat(zzav zzav0, SSLSocketFactory sSLSocketFactory0) {
        this.zzck = null;
        this.zzcl = null;
    }

    private static InputStream zza(HttpURLConnection httpURLConnection0) {
        try {
            return httpURLConnection0.getInputStream();
        }
        catch(IOException unused_ex) {
            return httpURLConnection0.getErrorStream();
        }
    }

    @VisibleForTesting
    private static List zza(Map map0) {
        List list0 = new ArrayList(map0.size());
        for(Object object0: map0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            if(map$Entry0.getKey() != null) {
                for(Object object1: ((List)map$Entry0.getValue())) {
                    list0.add(new zzk(((String)map$Entry0.getKey()), ((String)object1)));
                }
            }
        }
        return list0;
    }

    private static void zza(HttpURLConnection httpURLConnection0, zzq zzq0) throws IOException, zzb {
        byte[] arr_b = zzq0.zzg();
        if(arr_b != null) {
            httpURLConnection0.setDoOutput(true);
            if(!httpURLConnection0.getRequestProperties().containsKey("Content-Type")) {
                httpURLConnection0.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            }
            DataOutputStream dataOutputStream0 = new DataOutputStream(httpURLConnection0.getOutputStream());
            dataOutputStream0.write(arr_b);
            dataOutputStream0.close();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzah
    public final zzar zza(zzq zzq0, Map map0) throws IOException, zzb {
        zzar zzar0;
        int v1;
        String s1;
        String s = zzq0.getUrl();
        HashMap hashMap0 = new HashMap();
        hashMap0.putAll(map0);
        hashMap0.putAll(zzq0.getHeaders());
        zzav zzav0 = this.zzck;
        if(zzav0 == null) {
            s1 = s;
        }
        else {
            s1 = zzav0.zzg(s);
            if(s1 == null) {
                String s2 = String.valueOf(s);
                throw new IOException((s2.length() == 0 ? new String("URL blocked by rewriter: ") : "URL blocked by rewriter: " + s2));
            }
        }
        URL uRL0 = new URL(s1);
        HttpURLConnection httpURLConnection0 = (HttpURLConnection)uRL0.openConnection();
        httpURLConnection0.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        int v = zzq0.zzi();
        httpURLConnection0.setConnectTimeout(v);
        httpURLConnection0.setReadTimeout(v);
        boolean z = false;
        httpURLConnection0.setUseCaches(false);
        httpURLConnection0.setDoInput(true);
        "https".equals(uRL0.getProtocol());
        try {
            for(Object object0: hashMap0.keySet()) {
                httpURLConnection0.setRequestProperty(((String)object0), ((String)hashMap0.get(((String)object0))));
            }
            switch(zzq0.getMethod()) {
                case -1: {
                    break;
                }
                case 0: {
                    httpURLConnection0.setRequestMethod("GET");
                    break;
                }
                case 1: {
                    httpURLConnection0.setRequestMethod("POST");
                    zzat.zza(httpURLConnection0, zzq0);
                    break;
                }
                case 2: {
                    httpURLConnection0.setRequestMethod("PUT");
                    zzat.zza(httpURLConnection0, zzq0);
                    break;
                }
                case 3: {
                    httpURLConnection0.setRequestMethod("DELETE");
                    break;
                }
                case 4: {
                    httpURLConnection0.setRequestMethod("HEAD");
                    break;
                }
                case 5: {
                    httpURLConnection0.setRequestMethod("OPTIONS");
                    break;
                }
                case 6: {
                    httpURLConnection0.setRequestMethod("TRACE");
                    break;
                }
                case 7: {
                    httpURLConnection0.setRequestMethod("PATCH");
                    zzat.zza(httpURLConnection0, zzq0);
                    break;
                }
                default: {
                    throw new IllegalStateException("Unknown method type.");
                }
            }
            v1 = httpURLConnection0.getResponseCode();
            if(v1 == -1) {
                throw new IOException("Could not retrieve response code from HttpUrlConnection.");
            }
            if(zzq0.getMethod() == 4 || 100 <= v1 && v1 < 200 || (v1 == 204 || v1 == 304)) {
                zzar0 = new zzar(v1, zzat.zza(httpURLConnection0.getHeaderFields()));
                goto label_54;
            }
            return new zzar(v1, zzat.zza(httpURLConnection0.getHeaderFields()), httpURLConnection0.getContentLength(), new zzaw(httpURLConnection0));
        }
        catch(Throwable throwable0) {
            goto label_59;
        }
    label_54:
        httpURLConnection0.disconnect();
        return zzar0;
        try {
        }
        catch(Throwable throwable0) {
            z = true;
        }
    label_59:
        if(!z) {
            httpURLConnection0.disconnect();
        }
        throw throwable0;
    }
}

