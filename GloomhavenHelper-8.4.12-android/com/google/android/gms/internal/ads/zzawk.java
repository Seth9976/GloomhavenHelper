package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.security.NetworkSecurityPolicy;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzawk implements zzawh {
    private final Object lock;
    @Nullable
    @GuardedBy("lock")
    private SharedPreferences zzcgv;
    @GuardedBy("lock")
    private boolean zzdka;
    @GuardedBy("lock")
    private boolean zzdkn;
    @GuardedBy("lock")
    private String zzdkq;
    @GuardedBy("lock")
    private boolean zzdlz;
    @GuardedBy("lock")
    private boolean zzdmk;
    private boolean zzdtc;
    private final List zzdtd;
    private zzdof zzdte;
    @Nullable
    @GuardedBy("lock")
    private zzqj zzdtf;
    @Nullable
    @GuardedBy("lock")
    private SharedPreferences.Editor zzdtg;
    @GuardedBy("lock")
    private boolean zzdth;
    @Nullable
    @GuardedBy("lock")
    private String zzdti;
    @Nullable
    @GuardedBy("lock")
    private String zzdtj;
    @GuardedBy("lock")
    private long zzdtk;
    @GuardedBy("lock")
    private long zzdtl;
    @GuardedBy("lock")
    private long zzdtm;
    @GuardedBy("lock")
    private int zzdtn;
    @GuardedBy("lock")
    private int zzdto;
    @GuardedBy("lock")
    private Set zzdtp;
    @GuardedBy("lock")
    private JSONObject zzdtq;
    @GuardedBy("lock")
    private String zzdtr;
    @GuardedBy("lock")
    private int zzdts;

    public zzawk() {
        this.lock = new Object();
        this.zzdtd = new ArrayList();
        this.zzdtf = null;
        this.zzdth = false;
        this.zzdka = true;
        this.zzdkn = false;
        this.zzdkq = "";
        this.zzdtk = 0L;
        this.zzdtl = 0L;
        this.zzdtm = 0L;
        this.zzdtn = -1;
        this.zzdto = 0;
        this.zzdtp = Collections.emptySet();
        this.zzdtq = new JSONObject();
        this.zzdlz = true;
        this.zzdmk = true;
        this.zzdtr = null;
        this.zzdts = -1;
    }

    public final void zza(Context context0, String s, boolean z) {
        String s1;
        synchronized(this.lock) {
            if(this.zzcgv != null) {
                return;
            }
        }
        if(s == null) {
            s1 = "admob";
        }
        else {
            String s2 = String.valueOf(s);
            s1 = s2.length() == 0 ? new String("admob__") : "admob__" + s2;
        }
        zzawj zzawj0 = () -> {
            boolean z = false;
            SharedPreferences sharedPreferences0 = context0.getSharedPreferences(s1, 0);
            SharedPreferences.Editor sharedPreferences$Editor0 = sharedPreferences0.edit();
            synchronized(this.lock) {
                this.zzcgv = sharedPreferences0;
                this.zzdtg = sharedPreferences$Editor0;
                if(!NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted()) {
                    z = true;
                }
                this.zzdth = z;
                this.zzdka = this.zzcgv.getBoolean("use_https", this.zzdka);
                this.zzdlz = this.zzcgv.getBoolean("content_url_opted_out", this.zzdlz);
                this.zzdti = this.zzcgv.getString("content_url_hashes", this.zzdti);
                this.zzdkn = this.zzcgv.getBoolean("auto_collect_location", this.zzdkn);
                this.zzdmk = this.zzcgv.getBoolean("content_vertical_opted_out", this.zzdmk);
                this.zzdtj = this.zzcgv.getString("content_vertical_hashes", this.zzdtj);
                this.zzdto = this.zzcgv.getInt("version_code", this.zzdto);
                this.zzdkq = this.zzcgv.getString("app_settings_json", this.zzdkq);
                this.zzdtk = this.zzcgv.getLong("app_settings_last_update_ms", this.zzdtk);
                this.zzdtl = this.zzcgv.getLong("app_last_background_time_ms", this.zzdtl);
                this.zzdtn = this.zzcgv.getInt("request_in_session_count", this.zzdtn);
                this.zzdtm = this.zzcgv.getLong("first_ad_req_time_ms", this.zzdtm);
                this.zzdtp = this.zzcgv.getStringSet("never_pool_slots", this.zzdtp);
                this.zzdtr = this.zzcgv.getString("display_cutout", this.zzdtr);
                this.zzdts = this.zzcgv.getInt("app_measurement_npa", this.zzdts);
                try {
                    this.zzdtq = new JSONObject(this.zzcgv.getString("native_advanced_settings", "{}"));
                }
                catch(JSONException jSONException0) {
                    zzawf.zzd("Could not convert native advanced settings to json object", jSONException0);
                }
                this.zzc(this.zzwn());
            }
        };
        this.zzdte = zzazq.zzdxk.zzf(zzawj0);
        this.zzdtc = z;
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final void zza(String s, String s1, boolean z) {
        this.zzwm();
        synchronized(this.lock) {
            JSONArray jSONArray0 = this.zzdtq.optJSONArray(s);
            if(jSONArray0 == null) {
                jSONArray0 = new JSONArray();
            }
            int v1 = jSONArray0.length();
            for(int v2 = 0; v2 < jSONArray0.length(); ++v2) {
                JSONObject jSONObject0 = jSONArray0.optJSONObject(v2);
                if(jSONObject0 == null) {
                    return;
                }
                if(s1.equals(jSONObject0.optString("template_id"))) {
                    if(z && jSONObject0.optBoolean("uses_media_view", false)) {
                        return;
                    }
                    v1 = v2;
                    break;
                }
            }
            try {
                JSONObject jSONObject1 = new JSONObject();
                jSONObject1.put("template_id", s1);
                jSONObject1.put("uses_media_view", z);
                jSONObject1.put("timestamp_ms", zzq.zzlc().currentTimeMillis());
                jSONArray0.put(v1, jSONObject1);
                this.zzdtq.put(s, jSONArray0);
            }
            catch(JSONException jSONException0) {
                zzawf.zzd("Could not update native advanced settings", jSONException0);
            }
            if(this.zzdtg != null) {
                this.zzdtg.putString("native_advanced_settings", this.zzdtq.toString());
                this.zzdtg.apply();
            }
            Bundle bundle0 = new Bundle();
            bundle0.putString("native_advanced_settings", this.zzdtq.toString());
            this.zzc(bundle0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final void zzao(boolean z) {
        this.zzwm();
        synchronized(this.lock) {
            if(this.zzdlz == z) {
                return;
            }
            this.zzdlz = z;
            if(this.zzdtg != null) {
                this.zzdtg.putBoolean("content_url_opted_out", z);
                this.zzdtg.apply();
            }
            Bundle bundle0 = new Bundle();
            bundle0.putBoolean("content_url_opted_out", this.zzdlz);
            bundle0.putBoolean("content_vertical_opted_out", this.zzdmk);
            this.zzc(bundle0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final void zzap(boolean z) {
        this.zzwm();
        synchronized(this.lock) {
            if(this.zzdmk == z) {
                return;
            }
            this.zzdmk = z;
            if(this.zzdtg != null) {
                this.zzdtg.putBoolean("content_vertical_opted_out", z);
                this.zzdtg.apply();
            }
            Bundle bundle0 = new Bundle();
            bundle0.putBoolean("content_url_opted_out", this.zzdlz);
            bundle0.putBoolean("content_vertical_opted_out", this.zzdmk);
            this.zzc(bundle0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final void zzaq(boolean z) {
        this.zzwm();
        synchronized(this.lock) {
            if(this.zzdkn == z) {
                return;
            }
            this.zzdkn = z;
            if(this.zzdtg != null) {
                this.zzdtg.putBoolean("auto_collect_location", z);
                this.zzdtg.apply();
            }
            Bundle bundle0 = new Bundle();
            bundle0.putBoolean("auto_collect_location", z);
            this.zzc(bundle0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final void zzb(Runnable runnable0) {
        this.zzdtd.add(runnable0);
    }

    private final void zzc(Bundle bundle0) {
        zzawm zzawm0 = () -> {
            if(!this.zzdtc) {
                return null;
            }
            if(this.zzvz() && this.zzwb()) {
                return null;
            }
            if(!((Boolean)zzabc.zzctu.get()).booleanValue()) {
                return null;
            }
            synchronized(this.lock) {
                if(Looper.getMainLooper() == null) {
                    return null;
                }
                if(this.zzdtf == null) {
                    this.zzdtf = new zzqj();
                }
                this.zzdtf.zzmg();
                zzawf.zzez("start fetching content...");
                return this.zzdtf;
            }
        };
        zzazq.zzdxk.execute(zzawm0);
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final void zzcp(int v) {
        this.zzwm();
        synchronized(this.lock) {
            if(this.zzdto == v) {
                return;
            }
            this.zzdto = v;
            if(this.zzdtg != null) {
                this.zzdtg.putInt("version_code", v);
                this.zzdtg.apply();
            }
            Bundle bundle0 = new Bundle();
            bundle0.putInt("version_code", v);
            this.zzc(bundle0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final void zzcq(int v) {
        this.zzwm();
        synchronized(this.lock) {
            if(this.zzdtn == v) {
                return;
            }
            this.zzdtn = v;
            if(this.zzdtg != null) {
                this.zzdtg.putInt("request_in_session_count", v);
                this.zzdtg.apply();
            }
            Bundle bundle0 = new Bundle();
            bundle0.putInt("request_in_session_count", v);
            this.zzc(bundle0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final void zzef(@Nullable String s) {
        this.zzwm();
        synchronized(this.lock) {
            if(s != null && !s.equals(this.zzdti)) {
                this.zzdti = s;
                if(this.zzdtg != null) {
                    this.zzdtg.putString("content_url_hashes", s);
                    this.zzdtg.apply();
                }
                Bundle bundle0 = new Bundle();
                bundle0.putString("content_url_hashes", s);
                this.zzc(bundle0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final void zzeg(@Nullable String s) {
        this.zzwm();
        synchronized(this.lock) {
            if(s != null && !s.equals(this.zzdtj)) {
                this.zzdtj = s;
                if(this.zzdtg != null) {
                    this.zzdtg.putString("content_vertical_hashes", s);
                    this.zzdtg.apply();
                }
                Bundle bundle0 = new Bundle();
                bundle0.putString("content_vertical_hashes", s);
                this.zzc(bundle0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final void zzeh(String s) {
        this.zzwm();
        synchronized(this.lock) {
            long v1 = zzq.zzlc().currentTimeMillis();
            this.zzdtk = v1;
            if(s != null && !s.equals(this.zzdkq)) {
                this.zzdkq = s;
                if(this.zzdtg != null) {
                    this.zzdtg.putString("app_settings_json", s);
                    this.zzdtg.putLong("app_settings_last_update_ms", v1);
                    this.zzdtg.apply();
                }
                Bundle bundle0 = new Bundle();
                bundle0.putString("app_settings_json", s);
                bundle0.putLong("app_settings_last_update_ms", v1);
                this.zzc(bundle0);
                for(Object object1: this.zzdtd) {
                    ((Runnable)object1).run();
                }
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final void zzei(String s) {
        this.zzwm();
        synchronized(this.lock) {
            if(TextUtils.equals(this.zzdtr, s)) {
                return;
            }
            this.zzdtr = s;
            if(this.zzdtg != null) {
                this.zzdtg.putString("display_cutout", s);
                this.zzdtg.apply();
            }
            Bundle bundle0 = new Bundle();
            bundle0.putString("display_cutout", s);
            this.zzc(bundle0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final void zzez(long v) {
        this.zzwm();
        synchronized(this.lock) {
            if(this.zzdtl == v) {
                return;
            }
            this.zzdtl = v;
            if(this.zzdtg != null) {
                this.zzdtg.putLong("app_last_background_time_ms", v);
                this.zzdtg.apply();
            }
            Bundle bundle0 = new Bundle();
            bundle0.putLong("app_last_background_time_ms", v);
            this.zzc(bundle0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final void zzfa(long v) {
        this.zzwm();
        synchronized(this.lock) {
            if(this.zzdtm == v) {
                return;
            }
            this.zzdtm = v;
            if(this.zzdtg != null) {
                this.zzdtg.putLong("first_ad_req_time_ms", v);
                this.zzdtg.apply();
            }
            Bundle bundle0 = new Bundle();
            bundle0.putLong("first_ad_req_time_ms", v);
            this.zzc(bundle0);
        }
    }

    // 检测为 Lambda 实现
    final void zzp(Context context0, String s) [...]

    // 检测为 Lambda 实现
    @Override  // com.google.android.gms.internal.ads.zzawh
    @Nullable
    public final zzqj zzvy() [...]

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final boolean zzvz() {
        this.zzwm();
        synchronized(this.lock) {
        }
        return this.zzdlz;
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    @Nullable
    public final String zzwa() {
        this.zzwm();
        synchronized(this.lock) {
        }
        return this.zzdti;
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final boolean zzwb() {
        this.zzwm();
        synchronized(this.lock) {
        }
        return this.zzdmk;
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    @Nullable
    public final String zzwc() {
        this.zzwm();
        synchronized(this.lock) {
        }
        return this.zzdtj;
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final boolean zzwd() {
        this.zzwm();
        synchronized(this.lock) {
        }
        return this.zzdkn;
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final int zzwe() {
        this.zzwm();
        synchronized(this.lock) {
        }
        return this.zzdto;
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final zzavs zzwf() {
        this.zzwm();
        synchronized(this.lock) {
        }
        return new zzavs(this.zzdkq, this.zzdtk);
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final long zzwg() {
        this.zzwm();
        synchronized(this.lock) {
        }
        return this.zzdtl;
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final int zzwh() {
        this.zzwm();
        synchronized(this.lock) {
        }
        return this.zzdtn;
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final long zzwi() {
        this.zzwm();
        synchronized(this.lock) {
        }
        return this.zzdtm;
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final JSONObject zzwj() {
        this.zzwm();
        synchronized(this.lock) {
        }
        return this.zzdtq;
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final void zzwk() {
        this.zzwm();
        synchronized(this.lock) {
            this.zzdtq = new JSONObject();
            if(this.zzdtg != null) {
                this.zzdtg.remove("native_advanced_settings");
                this.zzdtg.apply();
            }
            Bundle bundle0 = new Bundle();
            bundle0.putString("native_advanced_settings", "{}");
            this.zzc(bundle0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzawh
    public final String zzwl() {
        this.zzwm();
        synchronized(this.lock) {
        }
        return this.zzdtr;
    }

    private final void zzwm() {
        zzdof zzdof0 = this.zzdte;
        if(zzdof0 == null) {
            return;
        }
        if(zzdof0.isDone()) {
            return;
        }
        try {
            this.zzdte.get(1L, TimeUnit.SECONDS);
        }
        catch(InterruptedException interruptedException0) {
            Thread.currentThread().interrupt();
            zzawf.zzd("Interrupted while waiting for preferences loaded.", interruptedException0);
        }
        catch(CancellationException | ExecutionException | TimeoutException cancellationException0) {
            zzawf.zzc("Fail to initialize AdSharedPreferenceManager.", cancellationException0);
        }
    }

    private final Bundle zzwn() {
        Bundle bundle0 = new Bundle();
        bundle0.putBoolean("listener_registration_bundle", true);
        synchronized(this.lock) {
            bundle0.putBoolean("use_https", this.zzdka);
            bundle0.putBoolean("content_url_opted_out", this.zzdlz);
            bundle0.putBoolean("content_vertical_opted_out", this.zzdmk);
            bundle0.putBoolean("auto_collect_location", this.zzdkn);
            bundle0.putInt("version_code", this.zzdto);
            bundle0.putStringArray("never_pool_slots", ((String[])this.zzdtp.toArray(new String[0])));
            bundle0.putString("app_settings_json", this.zzdkq);
            bundle0.putLong("app_settings_last_update_ms", this.zzdtk);
            bundle0.putLong("app_last_background_time_ms", this.zzdtl);
            bundle0.putInt("request_in_session_count", this.zzdtn);
            bundle0.putLong("first_ad_req_time_ms", this.zzdtm);
            bundle0.putString("native_advanced_settings", this.zzdtq.toString());
            bundle0.putString("display_cutout", this.zzdtr);
            bundle0.putInt("app_measurement_npa", this.zzdts);
            if(this.zzdti != null) {
                bundle0.putString("content_url_hashes", this.zzdti);
            }
            if(this.zzdtj != null) {
                bundle0.putString("content_vertical_hashes", this.zzdtj);
            }
            return bundle0;
        }
    }
}

