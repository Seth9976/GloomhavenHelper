package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri.Builder;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzaxl {
    private final Object lock;
    @GuardedBy("lock")
    private String zzdup;
    @GuardedBy("lock")
    private String zzduq;
    @GuardedBy("lock")
    private boolean zzdur;
    @VisibleForTesting
    private String zzdus;

    public zzaxl() {
        this.lock = new Object();
        this.zzdup = "";
        this.zzduq = "";
        this.zzdur = false;
        this.zzdus = "";
    }

    @VisibleForTesting
    private final void zza(Context context0, String s, boolean z, boolean z1) {
        if(!(context0 instanceof Activity)) {
            zzawf.zzez("Can not create dialog without Activity Context");
            return;
        }
        zzaxo zzaxo0 = new zzaxo(this, context0, s, z, z1);
        zzawo.zzdtx.post(zzaxo0);
    }

    public final void zza(Context context0, String s, String s1, @Nullable String s2) {
        boolean z = this.zzxd();
        if(this.zzg(context0, s, s1)) {
            if(!z && !TextUtils.isEmpty(s2)) {
                this.zzc(context0, s1, s2, s);
            }
            zzawf.zzeb("Device is linked for debug signals.");
            this.zza(context0, "The device is successfully linked for troubleshooting.", false, true);
            return;
        }
        this.zzi(context0, s, s1);
    }

    public final boolean zzb(Context context0, String s, String s1, String s2) {
        if(!TextUtils.isEmpty(s1) && zzq.zzlf().zzxd()) {
            zzawf.zzeb("Sending troubleshooting signals to the server.");
            this.zzc(context0, s, s1, s2);
            return true;
        }
        return false;
    }

    private final String zzbi(Context context0) {
        synchronized(this.lock) {
            if(TextUtils.isEmpty(this.zzdup)) {
                this.zzdup = zzawo.zzs(context0, "debug_signals_id.txt");
                if(TextUtils.isEmpty(this.zzdup)) {
                    this.zzdup = "4bd58494-ac2d-402d-9f91-6ca7ae71bae9";
                    zzawo.zzc(context0, "debug_signals_id.txt", "4bd58494-ac2d-402d-9f91-6ca7ae71bae9");
                }
            }
            return this.zzdup;
        }
    }

    private final void zzc(Context context0, String s, String s1, String s2) {
        Uri.Builder uri$Builder0 = this.zzd(context0, ((String)zzvh.zzpd().zzd(zzzx.zzcom)), s2, s).buildUpon();
        uri$Builder0.appendQueryParameter("debugData", s1);
        zzawo.zzb(context0, s, uri$Builder0.build().toString());
    }

    private final Uri zzd(Context context0, String s, String s1, String s2) {
        Uri.Builder uri$Builder0 = Uri.parse(s).buildUpon();
        uri$Builder0.appendQueryParameter("linkedDeviceId", this.zzbi(context0));
        uri$Builder0.appendQueryParameter("adSlotPath", s1);
        uri$Builder0.appendQueryParameter("afmaVersion", s2);
        return uri$Builder0.build();
    }

    public final void zze(Context context0, String s, String s1) {
        if(!this.zzf(context0, s, s1)) {
            this.zza(context0, "In-app preview failed to load because of a system error. Please try again later.", true, true);
            return;
        }
        if("2".equals(this.zzdus)) {
            zzawf.zzeb("Creative is not pushed for this device.");
            this.zza(context0, "There was no creative pushed from DFP to the device.", false, false);
            return;
        }
        if("1".equals(this.zzdus)) {
            zzawf.zzeb("The app is not linked for creative preview.");
            this.zzi(context0, s, s1);
            return;
        }
        if("0".equals(this.zzdus)) {
            zzawf.zzeb("Device is linked for in app preview.");
            this.zza(context0, "The device is successfully linked for creative preview.", false, true);
        }
    }

    @VisibleForTesting
    private final boolean zzf(Context context0, String s, String s1) {
        String s4;
        String s2 = zzaxl.zzh(context0, this.zzd(context0, ((String)zzvh.zzpd().zzd(zzzx.zzcok)), s, s1).toString(), s1);
        if(TextUtils.isEmpty(s2)) {
            zzawf.zzeb("Not linked for in app preview.");
            return false;
        }
        String s3 = s2.trim();
        try {
            JSONObject jSONObject0 = new JSONObject(s3);
            s4 = jSONObject0.optString("gct");
            this.zzdus = jSONObject0.optString("status");
        }
        catch(JSONException jSONException0) {
            zzawf.zzd("Fail to get in app preview response json.", jSONException0);
            return false;
        }
        synchronized(this.lock) {
            this.zzduq = s4;
        }
        return true;
    }

    @VisibleForTesting
    private final boolean zzg(Context context0, String s, String s1) {
        String s4;
        String s2 = zzaxl.zzh(context0, this.zzd(context0, ((String)zzvh.zzpd().zzd(zzzx.zzcol)), s, s1).toString(), s1);
        if(TextUtils.isEmpty(s2)) {
            zzawf.zzeb("Not linked for debug signals.");
            return false;
        }
        String s3 = s2.trim();
        try {
            s4 = new JSONObject(s3).optString("debug_mode");
        }
        catch(JSONException jSONException0) {
            zzawf.zzd("Fail to get debug mode response json.", jSONException0);
            return false;
        }
        boolean z = "1".equals(s4);
        synchronized(this.lock) {
            this.zzdur = z;
        }
        return z;
    }

    @VisibleForTesting
    private static String zzh(Context context0, String s, String s1) {
        HashMap hashMap0 = new HashMap();
        hashMap0.put("User-Agent", zzq.zzkv().zzr(context0, s1));
        zzdof zzdof0 = new zzaxx(context0).zzc(s, hashMap0);
        try {
            return (String)zzdof0.get(((long)(((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcon)))))), TimeUnit.MILLISECONDS);
        }
        catch(TimeoutException timeoutException0) {
            String s2 = String.valueOf(s);
            zzawf.zzc((s2.length() == 0 ? new String("Timeout while retriving a response from: ") : "Timeout while retriving a response from: " + s2), timeoutException0);
            zzdof0.cancel(true);
            return null;
        }
        catch(InterruptedException interruptedException0) {
            String s3 = String.valueOf(s);
            zzawf.zzc((s3.length() == 0 ? new String("Interrupted while retriving a response from: ") : "Interrupted while retriving a response from: " + s3), interruptedException0);
            zzdof0.cancel(true);
            return null;
        }
        catch(Exception exception0) {
            String s4 = String.valueOf(s);
            zzawf.zzc((s4.length() == 0 ? new String("Error retriving a response from: ") : "Error retriving a response from: " + s4), exception0);
            return null;
        }
    }

    private final void zzi(Context context0, String s, String s1) {
        zzawo.zza(context0, this.zzd(context0, ((String)zzvh.zzpd().zzd(zzzx.zzcoj)), s, s1));
    }

    public final String zzxc() [...] // 潜在的解密器

    public final boolean zzxd() {
        synchronized(this.lock) {
        }
        return this.zzdur;
    }
}

