package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzcnk {
    private final AtomicReference zzgcd;

    zzcnk() {
        this.zzgcd = new AtomicReference();
    }

    public final boolean zzaod() {
        return this.zzgcd.get() != null;
    }

    private final zzalk zzaoe() throws RemoteException {
        zzalk zzalk0 = (zzalk)this.zzgcd.get();
        if(zzalk0 != null) {
            return zzalk0;
        }
        zzawf.zzfa("Unexpected call to adapter creator.");
        throw new RemoteException();
    }

    public final void zzb(zzalk zzalk0) {
        this.zzgcd.compareAndSet(null, zzalk0);
    }

    public final zzanq zzdh(String s) throws RemoteException {
        return this.zzaoe().zzdh(s);
    }

    public final zzdfb zze(String s, JSONObject jSONObject0) throws zzdfa {
        zzalp zzalp0;
        try {
            if("com.google.ads.mediation.admob.AdMobAdapter".equals(s)) {
                zzalp0 = new zzamg(new AdMobAdapter());
            }
            else if("com.google.ads.mediation.AdUrlAdapter".equals(s)) {
                zzalp0 = new zzamg(new AdUrlAdapter());
            }
            else if("com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(s)) {
                zzalp0 = new zzamg(new zzaog());
            }
            else {
                zzalp0 = this.zzf(s, jSONObject0);
            }
            return new zzdfb(zzalp0);
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    private final zzalp zzf(String s, JSONObject jSONObject0) throws RemoteException {
        zzalk zzalk0 = this.zzaoe();
        if("com.google.ads.mediation.customevent.CustomEventAdapter".equals(s) || "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(s)) {
            try {
                return zzalk0.zzdg(jSONObject0.getString("class_name")) ? zzalk0.zzdf("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") : zzalk0.zzdf("com.google.ads.mediation.customevent.CustomEventAdapter");
            }
            catch(JSONException jSONException0) {
                zzawf.zzc("Invalid custom event.", jSONException0);
            }
        }
        return zzalk0.zzdf(s);
    }
}

