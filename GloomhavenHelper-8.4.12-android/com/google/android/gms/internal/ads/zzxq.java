package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.android.gms.ads.query.AdInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public final class zzxq {
    private int zzabv;
    private int zzabw;
    private String zzabx;
    private boolean zzbkp;
    private int zzccn;
    private String zzccq;
    private String zzccs;
    private final Bundle zzccu;
    private String zzccw;
    private boolean zzccy;
    private final List zzccz;
    private final HashSet zzces;
    private final Bundle zzcet;
    private final HashMap zzceu;
    private final HashSet zzcev;
    private final HashSet zzcew;
    private AdInfo zzcex;
    private Date zzmg;
    private Location zzmk;

    public zzxq() {
        this.zzces = new HashSet();
        this.zzcet = new Bundle();
        this.zzceu = new HashMap();
        this.zzcev = new HashSet();
        this.zzccu = new Bundle();
        this.zzcew = new HashSet();
        this.zzccz = new ArrayList();
        this.zzccn = -1;
        this.zzbkp = false;
        this.zzabv = -1;
        this.zzabw = -1;
    }

    public final void setManualImpressionsEnabled(boolean z) {
        this.zzbkp = z;
    }

    public final void zza(Location location0) {
        this.zzmk = location0;
    }

    @Deprecated
    public final void zza(NetworkExtras networkExtras0) {
        if(networkExtras0 instanceof AdMobExtras) {
            Bundle bundle0 = ((AdMobExtras)networkExtras0).getExtras();
            this.zza(AdMobAdapter.class, bundle0);
            return;
        }
        Class class0 = networkExtras0.getClass();
        this.zzceu.put(class0, networkExtras0);
    }

    public final void zza(AdInfo adInfo0) {
        this.zzcex = adInfo0;
    }

    public final void zza(Class class0, Bundle bundle0) {
        this.zzcet.putBundle(class0.getName(), bundle0);
    }

    @Deprecated
    public final void zza(Date date0) {
        this.zzmg = date0;
    }

    @Deprecated
    public final void zzaa(boolean z) {
        this.zzccy = z;
    }

    public final void zzb(Class class0, Bundle bundle0) {
        if(this.zzcet.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
            Bundle bundle1 = new Bundle();
            this.zzcet.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", bundle1);
        }
        this.zzcet.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter").putBundle(class0.getName(), bundle0);
    }

    public final void zzb(List list0) {
        this.zzccz.clear();
        for(Object object0: list0) {
            String s = (String)object0;
            if(TextUtils.isEmpty(s)) {
                zzazh.zzfa("neighboring content URL should not be null or empty");
            }
            else {
                this.zzccz.add(s);
            }
        }
    }

    public final void zzcg(String s) {
        this.zzces.add(s);
    }

    public final void zzch(String s) {
        this.zzcev.add(s);
    }

    public final void zzci(String s) {
        this.zzcev.remove(s);
    }

    public final void zzcj(String s) {
        this.zzccs = s;
    }

    @Deprecated
    public final void zzck(int v) {
        this.zzccn = v;
    }

    public final void zzck(String s) {
        this.zzccq = s;
    }

    @Deprecated
    public final void zzcl(int v) {
        if(v == -1 || v == 0 || v == 1) {
            this.zzabw = v;
        }
    }

    public final void zzcl(String s) {
        this.zzccw = s;
    }

    public final void zzcm(String s) {
        this.zzcew.add(s);
    }

    @Deprecated
    public final void zzcn(String s) {
        if("G".equals(s) || "PG".equals(s) || "T".equals(s) || "MA".equals(s)) {
            this.zzabx = s;
        }
    }

    public final void zze(String s, String s1) {
        this.zzccu.putString(s, s1);
    }

    static boolean zzp(zzxq zzxq0) {
        return zzxq0.zzccy;
    }

    static AdInfo zzq(zzxq zzxq0) {
        return zzxq0.zzcex;
    }

    static int zzr(zzxq zzxq0) {
        return zzxq0.zzabw;
    }

    static String zzs(zzxq zzxq0) {
        return zzxq0.zzabx;
    }

    @Deprecated
    public final void zzz(boolean z) {
        this.zzabv = z;
    }
}

