package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import com.google.android.gms.ads.internal.zzq;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzcho {
    private final Executor executor;
    private final zzazo zzblr;
    private final Context zzcgw;
    private final Executor zzfeo;
    private final ScheduledExecutorService zzffm;
    private boolean zzfwn;
    @GuardedBy("this")
    private boolean zzfwo;
    private final long zzfwp;
    private final zzazy zzfwq;
    private final WeakReference zzfwr;
    private final zzcnk zzfws;
    private final zzcgy zzfwt;
    private Map zzfwu;

    public zzcho(Executor executor0, Context context0, WeakReference weakReference0, Executor executor1, zzcnk zzcnk0, ScheduledExecutorService scheduledExecutorService0, zzcgy zzcgy0, zzazo zzazo0) {
        this.zzfwn = false;
        this.zzfwo = false;
        this.zzfwq = new zzazy();
        this.zzfwu = new ConcurrentHashMap();
        this.zzfws = zzcnk0;
        this.zzcgw = context0;
        this.zzfwr = weakReference0;
        this.executor = executor1;
        this.zzffm = scheduledExecutorService0;
        this.zzfeo = executor0;
        this.zzfwt = zzcgy0;
        this.zzblr = zzazo0;
        this.zzfwp = zzq.zzlc().elapsedRealtime();
        this.zza("com.google.android.gms.ads.MobileAds", false, "", 0);
    }

    private final void zza(String s, boolean z, String s1, int v) {
        this.zzfwu.put(s, new zzagz(s, z, v, s1));
    }

    static boolean zza(zzcho zzcho0, boolean z) {
        zzcho0.zzfwo = true;
        return true;
    }

    // 检测为 Lambda 实现
    final void zza(zzazy zzazy0) [...]

    // 检测为 Lambda 实现
    final void zza(zzdfb zzdfb0, zzahb zzahb0, List list0, String s) [...]

    // 检测为 Lambda 实现
    final void zza(Object object0, zzazy zzazy0, String s, long v) [...]

    public final void zzanf() {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzclk)).booleanValue() && !((Boolean)zzabk.zzcuz.get()).booleanValue() && this.zzblr.zzdxg >= ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcll))))) {
            if(this.zzfwn) {
                return;
            }
            synchronized(this) {
                if(this.zzfwn) {
                    return;
                }
                this.zzfwt.zzanc();
                zzchq zzchq0 = () -> this.zzfwt.zzand();
                this.zzfwq.addListener(zzchq0, this.executor);
                this.zzfwn = true;
                zzdof zzdof0 = this.zzanh();
                zzchs zzchs0 = () -> synchronized(this) {
                    if(this.zzfwo) {
                        return;
                    }
                    this.zza("com.google.android.gms.ads.MobileAds", false, "Timeout.", ((int)(zzq.zzlc().elapsedRealtime() - this.zzfwp)));
                    this.zzfwq.setException(new Exception());
                };
                long v1 = (long)(((Long)zzvh.zzpd().zzd(zzzx.zzcln)));
                this.zzffm.schedule(zzchs0, v1, TimeUnit.SECONDS);
                zzdnt.zza(zzdof0, new zzchz(this), this.executor);
            }
            return;
        }
        this.zza("com.google.android.gms.ads.MobileAds", true, "", 0);
        this.zzfwq.set(Boolean.FALSE);
    }

    public final List zzang() {
        List list0 = new ArrayList();
        for(Object object0: this.zzfwu.keySet()) {
            zzagz zzagz0 = (zzagz)this.zzfwu.get(((String)object0));
            list0.add(new zzagz(((String)object0), zzagz0.zzczg, zzagz0.zzczh, zzagz0.description));
        }
        return list0;
    }

    private final zzdof zzanh() {
        synchronized(this) {
            String s = zzq.zzkz().zzvk().zzwf().zzvq();
            if(!TextUtils.isEmpty(s)) {
                return zzdnt.zzaj(s);
            }
            zzdof zzdof1 = new zzazy();
            zzq.zzkz().zzvk().zzb(() -> {
                zzchw zzchw0 = new zzchw(this, ((zzazy)zzdof1));
                this.executor.execute(zzchw0);
            });
            return zzdof1;
        }
    }

    // 检测为 Lambda 实现
    final Object zzani() throws Exception [...]

    // 检测为 Lambda 实现
    final void zzanj() [...]

    // 检测为 Lambda 实现
    final void zzank() [...]

    public final void zzb(zzahc zzahc0) {
        zzchr zzchr0 = () -> try {
            zzahc0.zzd(this.zzang());
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
        };
        this.zzfwq.addListener(zzchr0, this.zzfeo);
    }

    // 检测为 Lambda 实现
    final void zzc(zzahc zzahc0) [...]

    private final void zzgg(String s) {
        try {
            ArrayList arrayList0 = new ArrayList();
            JSONObject jSONObject0 = new JSONObject(s).getJSONObject("initializer_settings").getJSONObject("config");
            Iterator iterator0 = jSONObject0.keys();
            while(true) {
                if(!iterator0.hasNext()) {
                    zzdnt.zzh(arrayList0).zza(() -> {
                        this.zzfwq.set(Boolean.TRUE);
                        return null;
                    }, this.executor);
                    return;
                }
                Object object0 = iterator0.next();
                String s1 = (String)object0;
                Object object1 = new Object();
                zzazy zzazy0 = new zzazy();
                zzdof zzdof0 = zzdnt.zza(zzazy0, ((long)(((Long)zzvh.zzpd().zzd(zzzx.zzclm)))), TimeUnit.SECONDS, this.zzffm);
                this.zzfwt.zzge(s1);
                long v = zzq.zzlc().elapsedRealtime();
                zzdof0.addListener(() -> synchronized(object1) {
                    if(!zzazy0.isDone()) {
                        this.zza(s1, false, "Timeout.", ((int)(zzq.zzlc().elapsedRealtime() - v)));
                        this.zzfwt.zzq(s1, "timeout");
                        zzazy0.set(Boolean.FALSE);
                    }
                }, this.executor);
                arrayList0.add(zzdof0);
                zzcib zzcib0 = new zzcib(this, object1, s1, v, zzazy0);
                JSONObject jSONObject1 = jSONObject0.optJSONObject(s1);
                ArrayList arrayList1 = new ArrayList();
                if(jSONObject1 != null) {
                    try {
                        JSONArray jSONArray0 = jSONObject1.getJSONArray("data");
                        for(int v1 = 0; v1 < jSONArray0.length(); ++v1) {
                            JSONObject jSONObject2 = jSONArray0.getJSONObject(v1);
                            String s2 = jSONObject2.optString("format", "");
                            JSONObject jSONObject3 = jSONObject2.optJSONObject("data");
                            Bundle bundle0 = new Bundle();
                            if(jSONObject3 != null) {
                                Iterator iterator1 = jSONObject3.keys();
                                while(iterator1.hasNext()) {
                                    Object object2 = iterator1.next();
                                    bundle0.putString(((String)object2), jSONObject3.optString(((String)object2), ""));
                                }
                            }
                            arrayList1.add(new zzahj(s2, bundle0));
                        }
                    }
                    catch(JSONException unused_ex) {
                    }
                }
                this.zza(s1, false, "", 0);
                try {
                    JSONObject jSONObject4 = new JSONObject();
                    zzchx zzchx0 = () -> try {
                        Context context0 = (Context)this.zzfwr.get();
                        if(context0 == null) {
                            context0 = this.zzcgw;
                        }
                        this.zzfws.zze(s1, jSONObject4).zza(context0, zzcib0, arrayList1);
                    }
                    catch(zzdfa unused_ex) {
                        try {
                            zzcib0.onInitializationFailed("Failed to initialize adapter. " + s1 + " does not implement the initialize() method.");
                        }
                        catch(RemoteException remoteException0) {
                            zzazh.zzc("", remoteException0);
                        }
                    };
                    this.zzfeo.execute(zzchx0);
                }
                catch(zzdfa unused_ex) {
                    try {
                        zzcib0.onInitializationFailed("Failed to create Adapter.");
                    }
                    catch(RemoteException remoteException0) {
                        zzazh.zzc("", remoteException0);
                    }
                }
            }
        }
        catch(JSONException jSONException0) {
            zzawf.zza("Malformed CLD response", jSONException0);
        }
    }
}

