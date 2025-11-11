package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
public final class zzats implements zzaub {
    private final Object lock;
    private final zzaua zzdmi;
    private static List zzdpi;
    @GuardedBy("lock")
    private final zzedo zzdpj;
    @GuardedBy("lock")
    private final LinkedHashMap zzdpk;
    @GuardedBy("lock")
    private final List zzdpl;
    @GuardedBy("lock")
    private final List zzdpm;
    private final zzaud zzdpn;
    @VisibleForTesting
    private boolean zzdpo;
    private final zzaug zzdpp;
    private HashSet zzdpq;
    private boolean zzdpr;
    private boolean zzdps;
    private boolean zzdpt;
    private final Context zzur;

    static {
        zzats.zzdpi = Collections.synchronizedList(new ArrayList());
    }

    public zzats(Context context0, zzazo zzazo0, zzaua zzaua0, String s, zzaud zzaud0) {
        this.zzdpl = new ArrayList();
        this.zzdpm = new ArrayList();
        this.lock = new Object();
        this.zzdpq = new HashSet();
        this.zzdpr = false;
        this.zzdps = false;
        this.zzdpt = false;
        Preconditions.checkNotNull(zzaua0, "SafeBrowsing config is not present.");
        if(context0.getApplicationContext() != null) {
            context0 = context0.getApplicationContext();
        }
        this.zzur = context0;
        this.zzdpk = new LinkedHashMap();
        this.zzdpn = zzaud0;
        this.zzdmi = zzaua0;
        for(Object object0: this.zzdmi.zzdqb) {
            this.zzdpq.add(((String)object0).toLowerCase(Locale.ENGLISH));
        }
        this.zzdpq.remove("cookie");
        zzedo zzedo0 = new zzedo();
        zzedo0.zziee = zzg.zzide;
        zzedo0.url = s;
        zzedo0.zzieg = s;
        zza zzede$zzb$zza$zza0 = com.google.android.gms.internal.ads.zzede.zzb.zza.zzbfv();
        if(this.zzdmi.zzdpx != null) {
            zzede$zzb$zza$zza0.zzhp(this.zzdmi.zzdpx);
        }
        zzedo0.zziei = (com.google.android.gms.internal.ads.zzede.zzb.zza)(((zzdyz)zzede$zzb$zza$zza0.zzbcx()));
        com.google.android.gms.internal.ads.zzede.zzb.zzi.zza zzede$zzb$zzi$zza0 = zzi.zzbgf().zzbt(Wrappers.packageManager(this.zzur).isCallerInstantApp());
        if(zzazo0.zzbmj != null) {
            zzede$zzb$zzi$zza0.zzhq(zzazo0.zzbmj);
        }
        long v = (long)GoogleApiAvailabilityLight.getInstance().getApkVersion(this.zzur);
        if(v > 0L) {
            zzede$zzb$zzi$zza0.zzfu(v);
        }
        zzedo0.zzies = (zzi)(((zzdyz)zzede$zzb$zzi$zza0.zzbcx()));
        this.zzdpj = zzedo0;
        this.zzdpp = new zzaug(this.zzur, this.zzdmi.zzdqe, this);
    }

    @Override  // com.google.android.gms.internal.ads.zzaub
    public final void zza(String s, Map map0, int v) {
        synchronized(this.lock) {
            if(v == 3) {
                this.zzdpt = true;
            }
            if(this.zzdpk.containsKey(s)) {
                if(v == 3) {
                    ((zzedp)this.zzdpk.get(s)).zzifc = com.google.android.gms.internal.ads.zzede.zzb.zzh.zza.zzhh(3);
                }
                return;
            }
            zzedp zzedp0 = new zzedp();
            zzedp0.zzifc = com.google.android.gms.internal.ads.zzede.zzb.zzh.zza.zzhh(v);
            zzedp0.zziew = this.zzdpk.size();
            zzedp0.url = s;
            zzedp0.zziex = new zzedn();
            if(this.zzdpq.size() > 0 && map0 != null) {
                ArrayList arrayList0 = new ArrayList();
                for(Object object1: map0.entrySet()) {
                    Map.Entry map$Entry0 = (Map.Entry)object1;
                    String s1 = map$Entry0.getKey() == null ? "" : ((String)map$Entry0.getKey());
                    String s2 = map$Entry0.getValue() == null ? "" : ((String)map$Entry0.getValue());
                    if(this.zzdpq.contains(s1.toLowerCase(Locale.ENGLISH))) {
                        arrayList0.add(((zzc)(((zzdyz)zzc.zzbfx().zzao(zzdxn.zzhi(s1)).zzap(zzdxn.zzhi(s2)).zzbcx()))));
                    }
                }
                zzc[] arr_zzede$zzb$zzc = new zzc[arrayList0.size()];
                arrayList0.toArray(arr_zzede$zzb$zzc);
                zzedp0.zziex.zziea = arr_zzede$zzb$zzc;
            }
            this.zzdpk.put(s, zzedp0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzaub
    public final String[] zza(String[] arr_s) {
        return (String[])this.zzdpp.zzb(arr_s).toArray(new String[0]);
    }

    @Override  // com.google.android.gms.internal.ads.zzaub
    public final void zzdw(String s) {
        synchronized(this.lock) {
            this.zzdpj.zziek = s;
        }
    }

    final void zzdx(String s) {
        synchronized(this.lock) {
            this.zzdpl.add(s);
        }
    }

    final void zzdy(String s) {
        synchronized(this.lock) {
            this.zzdpm.add(s);
        }
    }

    @Nullable
    private final zzedp zzdz(String s) {
        synchronized(this.lock) {
        }
        return (zzedp)this.zzdpk.get(s);
    }

    static final Void zzea(String s) {
        return null;
    }

    // 检测为 Lambda 实现
    final zzdof zzh(Map map0) throws Exception [...]

    @Override  // com.google.android.gms.internal.ads.zzaub
    public final void zzj(View view0) {
        if(!this.zzdmi.zzdpz) {
            return;
        }
        if(this.zzdps) {
            return;
        }
        Bitmap bitmap0 = zzawo.zzl(view0);
        if(bitmap0 == null) {
            zzauc.zzeb("Failed to capture the webview bitmap.");
            return;
        }
        this.zzdps = true;
        zzawo.zzc(new zzatt(this, bitmap0));
    }

    @Override  // com.google.android.gms.internal.ads.zzaub
    public final zzaua zzup() {
        return this.zzdmi;
    }

    // 去混淆评级： 中等(50)
    @Override  // com.google.android.gms.internal.ads.zzaub
    public final boolean zzuq() {
        return this.zzdmi.zzdpz && !this.zzdps;
    }

    @Override  // com.google.android.gms.internal.ads.zzaub
    public final void zzur() {
        this.zzdpr = true;
    }

    @Override  // com.google.android.gms.internal.ads.zzaub
    public final void zzus() {
        synchronized(this.lock) {
            Set set0 = this.zzdpk.keySet();
            zzdof zzdof0 = zzdnt.zzb(this.zzdpn.zza(this.zzur, set0), (Map map0) -> try {
                if(map0 != null) {
                    for(Object object0: map0.keySet()) {
                        String s = (String)object0;
                        JSONArray jSONArray0 = new JSONObject(((String)map0.get(s))).optJSONArray("matches");
                        if(jSONArray0 != null) {
                            Object object1 = this.lock;
                            synchronized(object1) {
                                int v1 = jSONArray0.length();
                                zzedp zzedp0 = this.zzdz(s);
                                if(zzedp0 == null) {
                                    String s1 = String.valueOf(s);
                                    zzauc.zzeb((s1.length() == 0 ? new String("Cannot find the corresponding resource object for ") : "Cannot find the corresponding resource object for " + s1));
                                }
                                else {
                                    zzedp0.zzifd = new String[v1];
                                    boolean z = false;
                                    for(int v2 = 0; v2 < v1; ++v2) {
                                        String[] arr_s = zzedp0.zzifd;
                                        arr_s[v2] = jSONArray0.getJSONObject(v2).getString("threat_type");
                                    }
                                    boolean z1 = this.zzdpo;
                                    if(v1 > 0) {
                                        z = true;
                                    }
                                    this.zzdpo = z | z1;
                                }
                            }
                        }
                    }
                }
                if(this.zzdpo) {
                    synchronized(this.lock) {
                        this.zzdpj.zziee = zzg.zzidf;
                    }
                }
                return this.zzut();
            }
            catch(JSONException jSONException0) {
                if(((Boolean)zzabp.zzcvp.get()).booleanValue()) {
                    zzawf.zzb("Failed to get SafeBrowsing metadata", jSONException0);
                }
                return zzdnt.immediateFailedFuture(new Exception("Safebrowsing report transmission failed."));
            }, zzazq.zzdxp);
            zzdof zzdof1 = zzdnt.zza(zzdof0, 10L, TimeUnit.SECONDS, zzazq.zzdxn);
            zzdnt.zza(zzdof0, new zzatw(this, zzdof1), zzazq.zzdxp);
            zzats.zzdpi.add(zzdof1);
        }
    }

    @VisibleForTesting
    private final zzdof zzut() {
        zzdof zzdof0;
        if((!this.zzdpo || !this.zzdmi.zzdqd) && (!this.zzdpt || !this.zzdmi.zzdqc) && (this.zzdpo || !this.zzdmi.zzdqa)) {
            return zzdnt.zzaj(null);
        }
        synchronized(this.lock) {
            this.zzdpj.zziej = new zzedp[this.zzdpk.size()];
            this.zzdpk.values().toArray(this.zzdpj.zziej);
            this.zzdpj.zziet = (String[])this.zzdpl.toArray(new String[0]);
            this.zzdpj.zzieu = (String[])this.zzdpm.toArray(new String[0]);
            if(zzauc.isEnabled()) {
                StringBuilder stringBuilder0 = new StringBuilder("Sending SB report\n  url: " + this.zzdpj.url + "\n  clickUrl: " + this.zzdpj.zziek + "\n  resources: \n");
                zzedp[] arr_zzedp = this.zzdpj.zziej;
                for(int v = 0; v < arr_zzedp.length; ++v) {
                    zzedp zzedp0 = arr_zzedp[v];
                    stringBuilder0.append("    [");
                    stringBuilder0.append(zzedp0.zzifd.length);
                    stringBuilder0.append("] ");
                    stringBuilder0.append(zzedp0.url);
                }
                zzauc.zzeb(stringBuilder0.toString());
            }
            byte[] arr_b = zzeda.zzb(this.zzdpj);
            zzdof0 = new zzaxx(this.zzur).zza(1, this.zzdmi.zzdpy, null, arr_b);
            if(zzauc.isEnabled()) {
                zzdof0.addListener(new zzatv(this), zzazq.zzdxk);
            }
        }
        return zzdnt.zzb(zzdof0, zzatu.zzdpv, zzazq.zzdxp);
    }
}

