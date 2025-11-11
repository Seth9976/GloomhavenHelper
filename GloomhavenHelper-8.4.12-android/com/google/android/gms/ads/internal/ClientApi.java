package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzq;
import com.google.android.gms.ads.internal.overlay.zzs;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.overlay.zzy;
import com.google.android.gms.ads.internal.overlay.zzz;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzacv;
import com.google.android.gms.internal.ads.zzacy;
import com.google.android.gms.internal.ads.zzalk;
import com.google.android.gms.internal.ads.zzapb;
import com.google.android.gms.internal.ads.zzapq;
import com.google.android.gms.internal.ads.zzary;
import com.google.android.gms.internal.ads.zzast;
import com.google.android.gms.internal.ads.zzazo;
import com.google.android.gms.internal.ads.zzbgk;
import com.google.android.gms.internal.ads.zzbzq;
import com.google.android.gms.internal.ads.zzbzt;
import com.google.android.gms.internal.ads.zzcsb;
import com.google.android.gms.internal.ads.zzcsd;
import com.google.android.gms.internal.ads.zzcsi;
import com.google.android.gms.internal.ads.zzuk;
import com.google.android.gms.internal.ads.zzvq;
import com.google.android.gms.internal.ads.zzvx;
import com.google.android.gms.internal.ads.zzwj;
import com.google.android.gms.internal.ads.zzwn;
import java.util.HashMap;

public class ClientApi extends zzwj {
    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzacv zza(IObjectWrapper iObjectWrapper0, IObjectWrapper iObjectWrapper1) {
        return new zzbzt(((FrameLayout)ObjectWrapper.unwrap(iObjectWrapper0)), ((FrameLayout)ObjectWrapper.unwrap(iObjectWrapper1)), 20089000);
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzacy zza(IObjectWrapper iObjectWrapper0, IObjectWrapper iObjectWrapper1, IObjectWrapper iObjectWrapper2) {
        return new zzbzq(((View)ObjectWrapper.unwrap(iObjectWrapper0)), ((HashMap)ObjectWrapper.unwrap(iObjectWrapper1)), ((HashMap)ObjectWrapper.unwrap(iObjectWrapper2)));
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzary zza(IObjectWrapper iObjectWrapper0, zzalk zzalk0, int v) {
        Context context0 = (Context)ObjectWrapper.unwrap(iObjectWrapper0);
        return zzbgk.zza(context0, zzalk0, v).zzacs().zzbx(context0).zzaew().zzafb();
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzvq zza(IObjectWrapper iObjectWrapper0, String s, zzalk zzalk0, int v) {
        Context context0 = (Context)ObjectWrapper.unwrap(iObjectWrapper0);
        return new zzcsb(zzbgk.zza(context0, zzalk0, v), context0, s);
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzvx zza(IObjectWrapper iObjectWrapper0, zzuk zzuk0, String s, int v) {
        return new zzl(((Context)ObjectWrapper.unwrap(iObjectWrapper0)), zzuk0, s, new zzazo(20089000, v, true, false));
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzvx zza(IObjectWrapper iObjectWrapper0, zzuk zzuk0, String s, zzalk zzalk0, int v) {
        Context context0 = (Context)ObjectWrapper.unwrap(iObjectWrapper0);
        return new zzcsd(zzbgk.zza(context0, zzalk0, v), context0, zzuk0, s);
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzwn zza(IObjectWrapper iObjectWrapper0, int v) {
        return zzbgk.zze(((Context)ObjectWrapper.unwrap(iObjectWrapper0)), v).zzacl();
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzapb zzb(IObjectWrapper iObjectWrapper0) {
        Activity activity0 = (Activity)ObjectWrapper.unwrap(iObjectWrapper0);
        AdOverlayInfoParcel adOverlayInfoParcel0 = AdOverlayInfoParcel.zzc(activity0.getIntent());
        if(adOverlayInfoParcel0 == null) {
            return new zzt(activity0);
        }
        switch(adOverlayInfoParcel0.zzdiy) {
            case 1: {
                return new zzq(activity0);
            }
            case 2: {
                return new zzz(activity0);
            }
            case 3: {
                return new zzy(activity0);
            }
            case 4: {
                return new zzs(activity0, adOverlayInfoParcel0);
            }
            default: {
                return new zzt(activity0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzast zzb(IObjectWrapper iObjectWrapper0, String s, zzalk zzalk0, int v) {
        Context context0 = (Context)ObjectWrapper.unwrap(iObjectWrapper0);
        return zzbgk.zza(context0, zzalk0, v).zzacs().zzbx(context0).zzfs(s).zzaew().zzafc();
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzvx zzb(IObjectWrapper iObjectWrapper0, zzuk zzuk0, String s, zzalk zzalk0, int v) {
        Context context0 = (Context)ObjectWrapper.unwrap(iObjectWrapper0);
        return new zzcsi(zzbgk.zza(context0, zzalk0, v), context0, zzuk0, s);
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzvx zzc(IObjectWrapper iObjectWrapper0, zzuk zzuk0, String s, zzalk zzalk0, int v) {
        Context context0 = (Context)ObjectWrapper.unwrap(iObjectWrapper0);
        return zzbgk.zza(context0, zzalk0, v).zzaco().zzfr(s).zzbw(context0).zzaei().zzaem();
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzwn zzc(IObjectWrapper iObjectWrapper0) {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzwg
    public final zzapq zzd(IObjectWrapper iObjectWrapper0) {
        return null;
    }
}

