package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;
import java.util.List;

public final class zzbyz {
    private Bundle extras;
    private zzacj zzcwu;
    private List zzcxj;
    private List zzejt;
    private double zzejw;
    private float zzeki;
    @Nullable
    private IObjectWrapper zzfhh;
    private int zzfpb;
    private zzxj zzfpc;
    private View zzfpd;
    private zzyc zzfpe;
    private zzbdv zzfpf;
    @Nullable
    private zzbdv zzfpg;
    private View zzfph;
    private IObjectWrapper zzfpi;
    private zzacr zzfpj;
    private zzacr zzfpk;
    private String zzfpl;
    private SimpleArrayMap zzfpm;
    private SimpleArrayMap zzfpn;
    @Nullable
    private String zzfpo;

    public zzbyz() {
        this.zzfpm = new SimpleArrayMap();
        this.zzfpn = new SimpleArrayMap();
        this.zzcxj = Collections.emptyList();
    }

    public final void destroy() {
        synchronized(this) {
            if(this.zzfpf != null) {
                this.zzfpf.destroy();
                this.zzfpf = null;
            }
            if(this.zzfpg != null) {
                this.zzfpg.destroy();
                this.zzfpg = null;
            }
            this.zzfhh = null;
            this.zzfpm.clear();
            this.zzfpn.clear();
            this.zzfpc = null;
            this.zzcwu = null;
            this.zzfpd = null;
            this.zzejt = null;
            this.extras = null;
            this.zzfph = null;
            this.zzfpi = null;
            this.zzfpj = null;
            this.zzfpk = null;
            this.zzfpl = null;
        }
    }

    public final String getAdvertiser() {
        synchronized(this) {
            return this.zzfz("advertiser");
        }
    }

    public final String getBody() {
        synchronized(this) {
            return this.zzfz("body");
        }
    }

    public final String getCallToAction() {
        synchronized(this) {
            return this.zzfz("call_to_action");
        }
    }

    public final String getCustomTemplateId() {
        synchronized(this) {
        }
        return this.zzfpl;
    }

    public final Bundle getExtras() {
        synchronized(this) {
            if(this.extras == null) {
                this.extras = new Bundle();
            }
            return this.extras;
        }
    }

    public final String getHeadline() {
        synchronized(this) {
            return this.zzfz("headline");
        }
    }

    public final List getImages() {
        synchronized(this) {
        }
        return this.zzejt;
    }

    public final float getMediaContentAspectRatio() {
        synchronized(this) {
        }
        return this.zzeki;
    }

    public final List getMuteThisAdReasons() {
        synchronized(this) {
        }
        return this.zzcxj;
    }

    public final String getPrice() {
        synchronized(this) {
            return this.zzfz("price");
        }
    }

    public final double getStarRating() {
        synchronized(this) {
        }
        return this.zzejw;
    }

    public final String getStore() {
        synchronized(this) {
            return this.zzfz("store");
        }
    }

    public final zzxj getVideoController() {
        synchronized(this) {
        }
        return this.zzfpc;
    }

    public final void setImages(List list0) {
        synchronized(this) {
            this.zzejt = list0;
        }
    }

    private final void setMediaContentAspectRatio(float f) {
        synchronized(this) {
            this.zzeki = f;
        }
    }

    public final void setStarRating(double f) {
        synchronized(this) {
            this.zzejw = f;
        }
    }

    private static zzbyw zza(zzxj zzxj0, @Nullable zzamd zzamd0) {
        return zzxj0 == null ? null : new zzbyw(zzxj0, zzamd0);
    }

    public static zzbyz zza(zzalx zzalx0) {
        try {
            zzbyw zzbyw0 = zzbyz.zza(zzalx0.getVideoController(), null);
            zzacj zzacj0 = zzalx0.zzrl();
            View view0 = (View)zzbyz.zzat(zzalx0.zzsz());
            String s = zzalx0.getHeadline();
            List list0 = zzalx0.getImages();
            String s1 = zzalx0.getBody();
            Bundle bundle0 = zzalx0.getExtras();
            String s2 = zzalx0.getCallToAction();
            View view1 = (View)zzbyz.zzat(zzalx0.zzta());
            IObjectWrapper iObjectWrapper0 = zzalx0.zzrm();
            String s3 = zzalx0.getStore();
            String s4 = zzalx0.getPrice();
            double f = zzalx0.getStarRating();
            zzacr zzacr0 = zzalx0.zzrk();
            zzbyz zzbyz0 = new zzbyz();
            zzbyz0.zzfpb = 2;
            zzbyz0.zzfpc = zzbyw0;
            zzbyz0.zzcwu = zzacj0;
            zzbyz0.zzfpd = view0;
            zzbyz0.zzn("headline", s);
            zzbyz0.zzejt = list0;
            zzbyz0.zzn("body", s1);
            zzbyz0.extras = bundle0;
            zzbyz0.zzn("call_to_action", s2);
            zzbyz0.zzfph = view1;
            zzbyz0.zzfpi = iObjectWrapper0;
            zzbyz0.zzn("store", s3);
            zzbyz0.zzn("price", s4);
            zzbyz0.zzejw = f;
            zzbyz0.zzfpj = zzacr0;
            return zzbyz0;
        }
        catch(RemoteException remoteException0) {
            zzawf.zzd("Failed to get native ad from app install ad mapper", remoteException0);
            return null;
        }
    }

    public static zzbyz zza(zzaly zzaly0) {
        try {
            zzbyw zzbyw0 = zzbyz.zza(zzaly0.getVideoController(), null);
            zzacj zzacj0 = zzaly0.zzrl();
            View view0 = (View)zzbyz.zzat(zzaly0.zzsz());
            String s = zzaly0.getHeadline();
            List list0 = zzaly0.getImages();
            String s1 = zzaly0.getBody();
            Bundle bundle0 = zzaly0.getExtras();
            String s2 = zzaly0.getCallToAction();
            View view1 = (View)zzbyz.zzat(zzaly0.zzta());
            IObjectWrapper iObjectWrapper0 = zzaly0.zzrm();
            String s3 = zzaly0.getAdvertiser();
            zzacr zzacr0 = zzaly0.zzrn();
            zzbyz zzbyz0 = new zzbyz();
            zzbyz0.zzfpb = 1;
            zzbyz0.zzfpc = zzbyw0;
            zzbyz0.zzcwu = zzacj0;
            zzbyz0.zzfpd = view0;
            zzbyz0.zzn("headline", s);
            zzbyz0.zzejt = list0;
            zzbyz0.zzn("body", s1);
            zzbyz0.extras = bundle0;
            zzbyz0.zzn("call_to_action", s2);
            zzbyz0.zzfph = view1;
            zzbyz0.zzfpi = iObjectWrapper0;
            zzbyz0.zzn("advertiser", s3);
            zzbyz0.zzfpk = zzacr0;
            return zzbyz0;
        }
        catch(RemoteException remoteException0) {
            zzawf.zzd("Failed to get native ad from content ad mapper", remoteException0);
            return null;
        }
    }

    private static zzbyz zza(zzxj zzxj0, zzacj zzacj0, View view0, String s, List list0, String s1, Bundle bundle0, String s2, View view1, IObjectWrapper iObjectWrapper0, String s3, String s4, double f, zzacr zzacr0, String s5, float f1) {
        zzbyz zzbyz0 = new zzbyz();
        zzbyz0.zzfpb = 6;
        zzbyz0.zzfpc = zzxj0;
        zzbyz0.zzcwu = zzacj0;
        zzbyz0.zzfpd = view0;
        zzbyz0.zzn("headline", s);
        zzbyz0.zzejt = list0;
        zzbyz0.zzn("body", s1);
        zzbyz0.extras = bundle0;
        zzbyz0.zzn("call_to_action", s2);
        zzbyz0.zzfph = view1;
        zzbyz0.zzfpi = iObjectWrapper0;
        zzbyz0.zzn("store", s3);
        zzbyz0.zzn("price", s4);
        zzbyz0.zzejw = f;
        zzbyz0.zzfpj = zzacr0;
        zzbyz0.zzn("advertiser", s5);
        zzbyz0.setMediaContentAspectRatio(f1);
        return zzbyz0;
    }

    public final void zza(zzacj zzacj0) {
        synchronized(this) {
            this.zzcwu = zzacj0;
        }
    }

    public final void zza(zzacr zzacr0) {
        synchronized(this) {
            this.zzfpj = zzacr0;
        }
    }

    public final void zza(@Nullable zzyc zzyc0) {
        synchronized(this) {
            this.zzfpe = zzyc0;
        }
    }

    public final void zza(String s, zzacd zzacd0) {
        synchronized(this) {
            if(zzacd0 == null) {
                this.zzfpm.remove(s);
                return;
            }
            this.zzfpm.put(s, zzacd0);
        }
    }

    public final void zzab(View view0) {
        synchronized(this) {
            this.zzfph = view0;
        }
    }

    public final int zzake() {
        synchronized(this) {
        }
        return this.zzfpb;
    }

    public final View zzakf() {
        synchronized(this) {
        }
        return this.zzfpd;
    }

    @Nullable
    public final zzacr zzakg() {
        if(this.zzejt != null && this.zzejt.size() != 0) {
            Object object0 = this.zzejt.get(0);
            return object0 instanceof IBinder ? zzacq.zzo(((IBinder)object0)) : null;
        }
        return null;
    }

    @Nullable
    public final zzyc zzakh() {
        synchronized(this) {
        }
        return this.zzfpe;
    }

    public final View zzaki() {
        synchronized(this) {
        }
        return this.zzfph;
    }

    public final zzbdv zzakj() {
        synchronized(this) {
        }
        return this.zzfpf;
    }

    @Nullable
    public final zzbdv zzakk() {
        synchronized(this) {
        }
        return this.zzfpg;
    }

    @Nullable
    public final IObjectWrapper zzakl() {
        synchronized(this) {
        }
        return this.zzfhh;
    }

    public final SimpleArrayMap zzakm() {
        synchronized(this) {
        }
        return this.zzfpm;
    }

    @Nullable
    public final String zzakn() {
        synchronized(this) {
        }
        return this.zzfpo;
    }

    public final SimpleArrayMap zzako() {
        synchronized(this) {
        }
        return this.zzfpn;
    }

    public final void zzas(IObjectWrapper iObjectWrapper0) {
        synchronized(this) {
            this.zzfhh = iObjectWrapper0;
        }
    }

    private static Object zzat(@Nullable IObjectWrapper iObjectWrapper0) {
        return iObjectWrapper0 == null ? null : ObjectWrapper.unwrap(iObjectWrapper0);
    }

    public static zzbyz zzb(zzalx zzalx0) {
        try {
            return zzbyz.zza(zzbyz.zza(zzalx0.getVideoController(), null), zzalx0.zzrl(), ((View)zzbyz.zzat(zzalx0.zzsz())), zzalx0.getHeadline(), zzalx0.getImages(), zzalx0.getBody(), zzalx0.getExtras(), zzalx0.getCallToAction(), ((View)zzbyz.zzat(zzalx0.zzta())), zzalx0.zzrm(), zzalx0.getStore(), zzalx0.getPrice(), zzalx0.getStarRating(), zzalx0.zzrk(), null, 0.0f);
        }
        catch(RemoteException remoteException0) {
            zzawf.zzd("Failed to get native ad assets from app install ad mapper", remoteException0);
            return null;
        }
    }

    public static zzbyz zzb(zzaly zzaly0) {
        try {
            return zzbyz.zza(zzbyz.zza(zzaly0.getVideoController(), null), zzaly0.zzrl(), ((View)zzbyz.zzat(zzaly0.zzsz())), zzaly0.getHeadline(), zzaly0.getImages(), zzaly0.getBody(), zzaly0.getExtras(), zzaly0.getCallToAction(), ((View)zzbyz.zzat(zzaly0.zzta())), zzaly0.zzrm(), null, null, -1.0, zzaly0.zzrn(), zzaly0.getAdvertiser(), 0.0f);
        }
        catch(RemoteException remoteException0) {
            zzawf.zzd("Failed to get native ad assets from content ad mapper", remoteException0);
            return null;
        }
    }

    public static zzbyz zzb(zzamd zzamd0) {
        try {
            return zzbyz.zza(zzbyz.zza(zzamd0.getVideoController(), zzamd0), zzamd0.zzrl(), ((View)zzbyz.zzat(zzamd0.zzsz())), zzamd0.getHeadline(), zzamd0.getImages(), zzamd0.getBody(), zzamd0.getExtras(), zzamd0.getCallToAction(), ((View)zzbyz.zzat(zzamd0.zzta())), zzamd0.zzrm(), zzamd0.getStore(), zzamd0.getPrice(), zzamd0.getStarRating(), zzamd0.zzrk(), zzamd0.getAdvertiser(), zzamd0.getMediaContentAspectRatio());
        }
        catch(RemoteException remoteException0) {
            zzawf.zzd("Failed to get native ad assets from unified ad mapper", remoteException0);
            return null;
        }
    }

    public final void zzb(zzacr zzacr0) {
        synchronized(this) {
            this.zzfpk = zzacr0;
        }
    }

    public final void zzb(zzxj zzxj0) {
        synchronized(this) {
            this.zzfpc = zzxj0;
        }
    }

    public final void zzdj(int v) {
        synchronized(this) {
            this.zzfpb = v;
        }
    }

    public final void zzfx(String s) {
        synchronized(this) {
            this.zzfpl = s;
        }
    }

    public final void zzfy(@Nullable String s) {
        synchronized(this) {
            this.zzfpo = s;
        }
    }

    private final String zzfz(String s) {
        synchronized(this) {
            return (String)this.zzfpn.get(s);
        }
    }

    public final void zzg(List list0) {
        synchronized(this) {
            this.zzcxj = list0;
        }
    }

    public final void zzi(zzbdv zzbdv0) {
        synchronized(this) {
            this.zzfpf = zzbdv0;
        }
    }

    public final void zzj(zzbdv zzbdv0) {
        synchronized(this) {
            this.zzfpg = zzbdv0;
        }
    }

    public final void zzn(String s, String s1) {
        synchronized(this) {
            if(s1 == null) {
                this.zzfpn.remove(s);
                return;
            }
            this.zzfpn.put(s, s1);
        }
    }

    public final zzacr zzrk() {
        synchronized(this) {
        }
        return this.zzfpj;
    }

    public final zzacj zzrl() {
        synchronized(this) {
        }
        return this.zzcwu;
    }

    public final IObjectWrapper zzrm() {
        synchronized(this) {
        }
        return this.zzfpi;
    }

    public final zzacr zzrn() {
        synchronized(this) {
        }
        return this.zzfpk;
    }
}

