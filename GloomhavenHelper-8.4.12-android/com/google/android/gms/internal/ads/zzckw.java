package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.IOUtils;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONObject;

public final class zzckw implements zzdhq {
    private final String zzdko;
    private final zzaro zzfza;
    private final String zzfzm;
    private final Context zzur;

    public zzckw(Context context0, String s, zzaro zzaro0, String s1) {
        this.zzur = context0;
        this.zzfzm = s;
        this.zzfza = zzaro0;
        this.zzdko = s1;
    }

    @Override  // com.google.android.gms.internal.ads.zzdhq
    public final Object apply(Object object0) throws Exception {
        return this.zza(((zzckz)object0).zzfzh.getUrl(), ((zzckz)object0).zzfzh, ((zzckz)object0).zzfzi, this.zzdko);
    }

    private final zzcky zza(String s, zzard zzard0, JSONObject jSONObject0, String s1) throws zzcke {
        String s5;
        InputStreamReader inputStreamReader0;
        URL uRL1;
        BufferedOutputStream bufferedOutputStream1;
        BufferedOutputStream bufferedOutputStream0;
        byte[] arr_b;
        try {
            switch(zzard0.getErrorCode()) {
                case -2: {
                    zzcky zzcky0 = new zzcky();
                    String s2 = String.valueOf(this.zzfzm);
                    zzawf.zzez((s2.length() == 0 ? new String("SDK version: ") : "SDK version: " + s2));
                    String s3 = String.valueOf(s);
                    zzawf.zzeb((s3.length() == 0 ? new String("AdRequestServiceImpl: Sending request: ") : "AdRequestServiceImpl: Sending request: " + s3));
                    URL uRL0 = new URL(s);
                    HashMap hashMap0 = new HashMap();
                    long v = zzq.zzlc().elapsedRealtime();
                    int v1 = 0;
                    while(true) {
                        this.zzfza.zzun();
                        HttpURLConnection httpURLConnection0 = (HttpURLConnection)uRL0.openConnection();
                        try {
                            zzq.zzkv().zza(this.zzur, this.zzfzm, false, httpURLConnection0);
                            if(!TextUtils.isEmpty(s1)) {
                                httpURLConnection0.addRequestProperty("Cookie", s1);
                            }
                            if(zzard0.zzui()) {
                                JSONObject jSONObject1 = jSONObject0.optJSONObject("pii");
                                if(jSONObject1 == null) {
                                    zzawf.zzee("DSID signal does not exist.");
                                }
                                else {
                                    if(!TextUtils.isEmpty(jSONObject1.optString("doritos", ""))) {
                                        httpURLConnection0.addRequestProperty("x-afma-drt-cookie", jSONObject1.optString("doritos", ""));
                                    }
                                    if(!TextUtils.isEmpty(jSONObject1.optString("doritos_v2", ""))) {
                                        httpURLConnection0.addRequestProperty("x-afma-drt-v2-cookie", jSONObject1.optString("doritos_v2", ""));
                                    }
                                }
                            }
                            if(TextUtils.isEmpty(zzard0.zzuh())) {
                                arr_b = null;
                            }
                            else {
                                httpURLConnection0.setDoOutput(true);
                                arr_b = zzard0.zzuh().getBytes();
                                httpURLConnection0.setFixedLengthStreamingMode(arr_b.length);
                                try {
                                    bufferedOutputStream0 = new BufferedOutputStream(httpURLConnection0.getOutputStream());
                                }
                                catch(Throwable throwable0) {
                                    bufferedOutputStream1 = null;
                                    IOUtils.closeQuietly(bufferedOutputStream1);
                                    throw throwable0;
                                }
                                try {
                                    bufferedOutputStream0.write(arr_b);
                                }
                                catch(Throwable throwable0) {
                                    bufferedOutputStream1 = bufferedOutputStream0;
                                    IOUtils.closeQuietly(bufferedOutputStream1);
                                    throw throwable0;
                                }
                                IOUtils.closeQuietly(bufferedOutputStream0);
                            }
                            zzazb zzazb0 = new zzazb();
                            zzazb0.zza(httpURLConnection0, arr_b);
                            int v3 = httpURLConnection0.getResponseCode();
                            for(Object object0: httpURLConnection0.getHeaderFields().entrySet()) {
                                String s4 = (String)((Map.Entry)object0).getKey();
                                List list0 = (List)((Map.Entry)object0).getValue();
                                if(hashMap0.containsKey(s4)) {
                                    ((List)hashMap0.get(s4)).addAll(list0);
                                }
                                else {
                                    hashMap0.put(s4, new ArrayList(list0));
                                }
                            }
                            zzazb0.zza(httpURLConnection0, v3);
                            if(v3 >= 200 && v3 < 300) {
                                try {
                                    inputStreamReader0 = new InputStreamReader(httpURLConnection0.getInputStream());
                                    s5 = zzawo.zza(inputStreamReader0);
                                }
                                finally {
                                    IOUtils.closeQuietly(inputStreamReader0);
                                }
                                zzazb0.zzev(s5);
                                zzcky0.zzfzr = v3;
                                zzcky0.zzdln = s5;
                                zzcky0.zzab = hashMap0;
                                if(TextUtils.isEmpty(s5) && !((Boolean)zzvh.zzpd().zzd(zzzx.zzcpr)).booleanValue()) {
                                    throw new zzcke("No Fill", 3);
                                }
                                zzcky0.zzfzs = zzq.zzlc().elapsedRealtime() - v;
                                return zzcky0;
                            }
                            if(v3 < 300 || v3 >= 400) {
                                zzawf.zzfa(("Received error HTTP response code: " + v3));
                                throw new zzcke("Received error HTTP response code: " + v3);
                            }
                            String s6 = httpURLConnection0.getHeaderField("Location");
                            if(TextUtils.isEmpty(s6)) {
                                zzawf.zzfa("No location header to follow redirect.");
                                throw new zzcke("No location header to follow redirect");
                            }
                            uRL1 = new URL(s6);
                            ++v1;
                            if(v1 > ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcoy))))) {
                                zzawf.zzfa("Too many redirects.");
                                throw new zzcke("Too many redirects");
                            }
                        }
                        finally {
                            httpURLConnection0.disconnect();
                            this.zzfza.zzuo();
                        }
                        uRL0 = uRL1;
                    }
                }
                case 1: {
                    if(zzard0.zzuf() != null) {
                        zzawf.zzey(TextUtils.join(", ", zzard0.zzuf()));
                    }
                    throw new zzcke("Error building request URL.", zzard0.getErrorCode());
                }
                default: {
                    throw new zzcke("Internal error.", 0);
                }
            }
        }
        catch(IOException iOException0) {
            String s7 = iOException0.getMessage();
            zzawf.zzfa((s7.length() == 0 ? new String("Error while connecting to ad server: ") : "Error while connecting to ad server: " + s7));
            String s8 = iOException0.getMessage();
            throw new zzcke((s8.length() == 0 ? new String("Error connecting to ad server:") : "Error connecting to ad server:" + s8), 2);
        }
    }
}

