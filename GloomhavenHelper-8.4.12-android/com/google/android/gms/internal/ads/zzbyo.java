package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;
import java.util.concurrent.Executor;

public final class zzbyo extends zzbnf {
    private final zzazo zzblu;
    private final zzdq zzehb;
    private final Executor zzfeo;
    private final zzbzg zzfge;
    private final zzauf zzfhr;
    private final zzbyz zzfne;
    private final zzbzh zzfod;
    private final zzbzv zzfoe;
    private final zzbzd zzfof;
    private final zzeed zzfog;
    private final zzeed zzfoh;
    private final zzeed zzfoi;
    private final zzeed zzfoj;
    private final zzeed zzfok;
    private zzcal zzfol;
    private boolean zzfom;
    private final zzbyu zzfon;
    private final zzcsf zzfoo;
    private final Context zzur;

    public zzbyo(zzbne zzbne0, Executor executor0, zzbyz zzbyz0, zzbzh zzbzh0, zzbzv zzbzv0, zzbzd zzbzd0, zzbzg zzbzg0, zzeed zzeed0, zzeed zzeed1, zzeed zzeed2, zzeed zzeed3, zzeed zzeed4, zzauf zzauf0, zzdq zzdq0, zzazo zzazo0, Context context0, zzbyu zzbyu0, zzcsf zzcsf0) {
        super(zzbne0);
        this.zzfeo = executor0;
        this.zzfne = zzbyz0;
        this.zzfod = zzbzh0;
        this.zzfoe = zzbzv0;
        this.zzfof = zzbzd0;
        this.zzfge = zzbzg0;
        this.zzfog = zzeed0;
        this.zzfoh = zzeed1;
        this.zzfoi = zzeed2;
        this.zzfoj = zzeed3;
        this.zzfok = zzeed4;
        this.zzfhr = zzauf0;
        this.zzehb = zzdq0;
        this.zzblu = zzazo0;
        this.zzur = context0;
        this.zzfon = zzbyu0;
        this.zzfoo = zzcsf0;
    }

    public final void cancelUnconfirmedClick() {
        synchronized(this) {
            this.zzfod.cancelUnconfirmedClick();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbnf
    public final void destroy() {
        synchronized(this) {
            zzbyt zzbyt0 = () -> {
                this.zzfod.destroy();
                this.zzfne.destroy();
            };
            this.zzfeo.execute(zzbyt0);
            super.destroy();
        }
    }

    public final boolean isCustomClickGestureEnabled() {
        synchronized(this) {
            return this.zzfod.isCustomClickGestureEnabled();
        }
    }

    public final void recordCustomClickGesture() {
        synchronized(this) {
            if(this.zzfol == null) {
                zzawf.zzeb("Ad should be associated with an ad view before calling recordCustomClickGesture()");
                return;
            }
            zzbys zzbys0 = () -> {
                View view0 = this.zzfol.zzagm();
                Map map0 = this.zzfol.zzalg();
                Map map1 = this.zzfol.zzalh();
                this.zzfod.zza(view0, map0, map1, this.zzfol instanceof zzbzq);
            };
            this.zzfeo.execute(zzbys0);
        }
    }

    public final void setClickConfirmingView(View view0) {
        synchronized(this) {
            this.zzfod.setClickConfirmingView(view0);
        }
    }

    public final void zza(View view0, MotionEvent motionEvent0, View view1) {
        synchronized(this) {
            this.zzfod.zza(view0, motionEvent0, view1);
        }
    }

    public final void zza(View view0, View view1, Map map0, Map map1, boolean z) {
        synchronized(this) {
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcrb)).booleanValue()) {
                this.zzfoe.zzc(this.zzfol);
            }
            this.zzfod.zza(view0, view1, map0, map1, z);
        }
    }

    public final void zza(zzaem zzaem0) {
        synchronized(this) {
            this.zzfod.zza(zzaem0);
        }
    }

    public final void zza(zzcal zzcal0) {
        synchronized(this) {
            this.zzfol = zzcal0;
            this.zzfoe.zza(zzcal0);
            View view0 = zzcal0.zzagm();
            Map map0 = zzcal0.zzalh();
            Map map1 = zzcal0.zzali();
            this.zzfod.zza(view0, map0, map1, zzcal0, zzcal0);
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzclw)).booleanValue()) {
                zzdg zzdg0 = this.zzehb.zzcb();
                if(zzdg0 != null) {
                    zzdg0.zzb(zzcal0.zzagm());
                }
            }
            if(zzcal0.zzalf() != null) {
                zzcal0.zzalf().zza(this.zzfhr);
            }
        }
    }

    public final void zza(zzwq zzwq0) {
        synchronized(this) {
            this.zzfod.zza(zzwq0);
        }
    }

    public final void zza(@Nullable zzwu zzwu0) {
        synchronized(this) {
            this.zzfod.zza(zzwu0);
        }
    }

    public final void zza(zzxd zzxd0) {
        synchronized(this) {
            this.zzfoo.zzb(zzxd0);
        }
    }

    public final void zzaa(View view0) {
        IObjectWrapper iObjectWrapper0 = this.zzfne.zzakl();
        if(this.zzfof.zzaka() && iObjectWrapper0 != null && view0 != null) {
            zzq.zzlk().zzb(iObjectWrapper0, view0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzbnf
    @AnyThread
    public final void zzags() {
        zzbyr zzbyr0 = () -> try {
            switch(this.zzfne.zzake()) {
                case 1: {
                    if(this.zzfge.zzakt() != null) {
                        this.zzg("Google", true);
                        this.zzfge.zzakt().zza(((zzadj)this.zzfog.get()));
                    }
                    return;
                }
                case 2: {
                    if(this.zzfge.zzaku() != null) {
                        this.zzg("Google", true);
                        this.zzfge.zzaku().zza(((zzadf)this.zzfoh.get()));
                    }
                    return;
                }
                case 3: {
                    String s = this.zzfne.getCustomTemplateId();
                    if(this.zzfge.zzga(s) != null) {
                        if(this.zzfne.zzakj() != null) {
                            this.zzg("Google", true);
                        }
                        String s1 = this.zzfne.getCustomTemplateId();
                        this.zzfge.zzga(s1).zzb(((zzadn)this.zzfok.get()));
                    }
                    return;
                }
                case 6: {
                    if(this.zzfge.zzakv() != null) {
                        this.zzg("Google", true);
                        this.zzfge.zzakv().zza(((zzaer)this.zzfoi.get()));
                    }
                    return;
                }
                case 7: {
                    if(this.zzfge.zzakx() != null) {
                        this.zzfge.zzakx().zza(((zzahn)this.zzfoj.get()));
                    }
                    return;
                }
                default: {
                    zzawf.zzey("Wrong native template id!");
                }
            }
        }
        catch(RemoteException remoteException0) {
            zzawf.zzc("RemoteException when notifyAdLoad is called", remoteException0);
        };
        this.zzfeo.execute(zzbyr0);
        if(this.zzfne.zzake() != 7) {
            this.zzfod.getClass();
            Runnable runnable0 = zzbyq.zza(this.zzfod);
            this.zzfeo.execute(runnable0);
        }
        super.zzags();
    }

    public final void zzajs() {
        synchronized(this) {
            if(this.zzfom) {
                return;
            }
            this.zzfod.zzajs();
        }
    }

    public final boolean zzajz() {
        return this.zzfof.zzakr();
    }

    public final boolean zzaka() {
        return this.zzfof.zzaka();
    }

    public final zzbyu zzakb() {
        return this.zzfon;
    }

    // 检测为 Lambda 实现
    final void zzakc() [...]

    // 检测为 Lambda 实现
    final void zzakd() [...]

    public final void zzb(View view0, Map map0, Map map1, boolean z) {
        synchronized(this) {
            if(!this.zzfom) {
                if(z) {
                    this.zzfod.zza(view0, map0, map1);
                    this.zzfom = true;
                    return;
                }
                goto label_7;
            }
            return;
        }
        return;
    label_7:
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcne)).booleanValue() && map0 != null) {
            Iterator iterator0 = map0.entrySet().iterator();
            while(true) {
                if(!iterator0.hasNext()) {
                    break;
                }
                Object object0 = iterator0.next();
                View view1 = (View)((WeakReference)((Map.Entry)object0).getValue()).get();
                if(view1 != null && zzbyo.zzy(view1)) {
                    this.zzfod.zza(view0, map0, map1);
                    this.zzfom = true;
                    return;
                }
            }
        }
    }

    public final void zzb(zzcal zzcal0) {
        synchronized(this) {
            View view0 = zzcal0.zzagm();
            Map map0 = zzcal0.zzalg();
            this.zzfod.zza(view0, map0);
            if(zzcal0.zzale() != null) {
                zzcal0.zzale().setClickable(false);
                zzcal0.zzale().removeAllViews();
            }
            if(zzcal0.zzalf() != null) {
                zzcal0.zzalf().zzb(this.zzfhr);
            }
            this.zzfol = null;
        }
    }

    // 检测为 Lambda 实现
    final void zzbj(boolean z) [...]

    public final void zzf(Bundle bundle0) {
        synchronized(this) {
            this.zzfod.zzf(bundle0);
        }
    }

    public final void zzfv(String s) {
        synchronized(this) {
            this.zzfod.zzfv(s);
        }
    }

    public final void zzg(Bundle bundle0) {
        synchronized(this) {
            this.zzfod.zzg(bundle0);
        }
    }

    public final void zzg(String s, boolean z) {
        String s1;
        if(!this.zzfof.zzaka()) {
            return;
        }
        boolean z1 = true;
        zzbdv zzbdv0 = this.zzfne.zzakk();
        zzbdv zzbdv1 = this.zzfne.zzakj();
        if(zzbdv0 == null && zzbdv1 == null) {
            return;
        }
        if(zzbdv1 == null) {
            z1 = false;
        }
        if(zzbdv0 != null) {
            s1 = null;
        }
        else if(z1) {
            zzbdv0 = zzbdv1;
            s1 = "javascript";
        }
        else {
            zzbdv0 = null;
            s1 = null;
        }
        if(zzbdv0.getWebView() == null) {
            return;
        }
        if(zzq.zzlk().zzq(this.zzur)) {
            IObjectWrapper iObjectWrapper0 = zzq.zzlk().zza(this.zzblu.zzdxf + "." + this.zzblu.zzdxg, zzbdv0.getWebView(), "", "javascript", s1, s);
            if(iObjectWrapper0 == null) {
                return;
            }
            this.zzfne.zzas(iObjectWrapper0);
            zzbdv0.zzap(iObjectWrapper0);
            if(z1) {
                View view0 = zzbdv1.getView();
                if(view0 != null) {
                    zzq.zzlk().zza(iObjectWrapper0, view0);
                }
            }
            if(z) {
                zzq.zzlk().zzab(iObjectWrapper0);
            }
        }
    }

    public final boolean zzh(Bundle bundle0) {
        synchronized(this) {
            if(this.zzfom) {
                return true;
            }
            boolean z = this.zzfod.zzh(bundle0);
            this.zzfom = z;
            return z;
        }
    }

    public final void zzru() {
        synchronized(this) {
            this.zzfod.zzru();
        }
    }

    // 去混淆评级： 低(20)
    public static boolean zzy(View view0) {
        return view0.isShown() && view0.getGlobalVisibleRect(new Rect(), null);
    }

    public final void zzz(View view0) {
        IObjectWrapper iObjectWrapper0 = this.zzfne.zzakl();
        boolean z = this.zzfne.zzakk() != null;
        if(this.zzfof.zzaka() && iObjectWrapper0 != null && z && view0 != null) {
            zzq.zzlk().zza(iObjectWrapper0, view0);
        }
    }
}

