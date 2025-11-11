package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONObject;

public final class zzbik extends zzwm {
    private final zzazo zzblu;
    private final zzaui zzbnp;
    private final zzcnk zzfct;
    private final zzcmc zzfcu;
    private final zzcrq zzfcv;
    private final zzcho zzfcw;
    private final zzceu zzfcx;
    private final Context zzur;
    @GuardedBy("this")
    private boolean zzyb;

    zzbik(Context context0, zzazo zzazo0, zzcnk zzcnk0, zzcmc zzcmc0, zzcrq zzcrq0, zzcho zzcho0, zzaui zzaui0, zzceu zzceu0) {
        this.zzur = context0;
        this.zzblu = zzazo0;
        this.zzfct = zzcnk0;
        this.zzfcu = zzcmc0;
        this.zzfcv = zzcrq0;
        this.zzfcw = zzcho0;
        this.zzbnp = zzaui0;
        this.zzfcx = zzceu0;
        this.zzyb = false;
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final String getVersionString() {
        return this.zzblu.zzbmj;
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void initialize() {
        synchronized(this) {
            if(this.zzyb) {
                zzawf.zzfa("Mobile ads is initialized already.");
                return;
            }
            zzzx.initialize(this.zzur);
            zzq.zzkz().zzd(this.zzur, this.zzblu);
            zzq.zzlb().initialize(this.zzur);
            this.zzyb = true;
            this.zzfcw.zzanf();
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzclh)).booleanValue()) {
                this.zzfcv.zzamh();
            }
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcno)).booleanValue()) {
                this.zzfcx.zzamh();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void setAppMuted(boolean z) {
        synchronized(this) {
            zzq.zzla().setAppMuted(z);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void setAppVolume(float f) {
        synchronized(this) {
            zzq.zzla().setAppVolume(f);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zza(zzahc zzahc0) throws RemoteException {
        this.zzfcw.zzb(zzahc0);
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zza(zzalk zzalk0) throws RemoteException {
        this.zzfct.zzb(zzalk0);
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zza(zzyw zzyw0) throws RemoteException {
        this.zzbnp.zza(this.zzur, zzyw0);
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zza(@Nullable String s, IObjectWrapper iObjectWrapper0) {
        zzzx.initialize(this.zzur);
        String s1 = ((Boolean)zzvh.zzpd().zzd(zzzx.zzcnp)).booleanValue() ? zzawo.zzbe(this.zzur) : "";
        if(!TextUtils.isEmpty(s1)) {
            s = s1;
        }
        if(TextUtils.isEmpty(s)) {
            return;
        }
        int v = ((Boolean)zzvh.zzpd().zzd(zzzx.zzcnn)).booleanValue() | ((Boolean)zzvh.zzpd().zzd(zzzx.zzcjq)).booleanValue();
        zzbin zzbin0 = null;
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcjq)).booleanValue()) {
            zzbin0 = new zzbin(this, ((Runnable)ObjectWrapper.unwrap(iObjectWrapper0)));
            v = 1;
        }
        if(v != 0) {
            zzq.zzld().zza(this.zzur, this.zzblu, s, zzbin0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zzb(IObjectWrapper iObjectWrapper0, String s) {
        if(iObjectWrapper0 == null) {
            zzawf.zzey("Wrapped context is null. Failed to open debug menu.");
            return;
        }
        Context context0 = (Context)ObjectWrapper.unwrap(iObjectWrapper0);
        if(context0 == null) {
            zzawf.zzey("Context is null. Failed to open debug menu.");
            return;
        }
        zzaxg zzaxg0 = new zzaxg(context0);
        zzaxg0.setAdUnitId(s);
        zzaxg0.zzx(this.zzblu.zzbmj);
        zzaxg0.showDialog();
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zzce(String s) {
        synchronized(this) {
            zzzx.initialize(this.zzur);
            if(!TextUtils.isEmpty(s) && ((Boolean)zzvh.zzpd().zzd(zzzx.zzcnn)).booleanValue()) {
                zzq.zzld().zza(this.zzur, this.zzblu, s, null);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final void zzcf(String s) {
        this.zzfcv.zzgl(s);
    }

    final void zzd(Runnable runnable0) {
        Preconditions.checkMainThread("Adapters must be initialized on the main thread.");
        Map map0 = zzq.zzkz().zzvk().zzwf().zzvs();
        if(map0 != null && !map0.isEmpty()) {
            if(runnable0 != null) {
                try {
                    runnable0.run();
                }
                catch(Throwable throwable0) {
                    zzawf.zzd("Could not initialize rewarded ads.", throwable0);
                    return;
                }
            }
            if(this.zzfct.zzaod()) {
                HashMap hashMap0 = new HashMap();
                for(Object object0: map0.values()) {
                    for(Object object1: ((zzalj)object0).zzddo) {
                        String s = ((zzalg)object1).zzddb;
                        for(Object object2: ((zzalg)object1).zzdct) {
                            String s1 = (String)object2;
                            if(!hashMap0.containsKey(s1)) {
                                hashMap0.put(s1, new ArrayList());
                            }
                            if(s != null) {
                                ((Collection)hashMap0.get(s1)).add(s);
                            }
                        }
                    }
                    if(false) {
                        break;
                    }
                }
                JSONObject jSONObject0 = new JSONObject();
                for(Object object3: hashMap0.entrySet()) {
                    Map.Entry map$Entry0 = (Map.Entry)object3;
                    String s2 = (String)map$Entry0.getKey();
                    try {
                        zzcmd zzcmd0 = this.zzfcu.zzd(s2, jSONObject0);
                        if(zzcmd0 == null) {
                            continue;
                        }
                        zzdfb zzdfb0 = (zzdfb)zzcmd0.zzdel;
                        if(zzdfb0.isInitialized() || !zzdfb0.zzsu()) {
                            continue;
                        }
                        List list0 = (List)map$Entry0.getValue();
                        zzdfb0.zza(this.zzur, ((zzcnl)zzcmd0.zzgbd), list0);
                        String s3 = String.valueOf(s2);
                        zzawf.zzeb((s3.length() == 0 ? new String("Initialized rewarded video mediation adapter ") : "Initialized rewarded video mediation adapter " + s3));
                    }
                    catch(zzdfa zzdfa0) {
                        zzawf.zzd(("Failed to initialize rewarded video mediation adapter \"" + s2 + "\""), zzdfa0);
                    }
                }
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final float zzpj() {
        synchronized(this) {
            return zzq.zzla().zzpj();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final boolean zzpk() {
        synchronized(this) {
            return zzq.zzla().zzpk();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzwn
    public final List zzpl() throws RemoteException {
        return this.zzfcw.zzang();
    }
}

