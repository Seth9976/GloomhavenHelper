package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView.ScaleType;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.formats.AdChoicesView;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Map;
import java.util.concurrent.Executor;

public final class zzbzv {
    private final Executor executor;
    private final zzach zzdff;
    private final zzawh zzdsq;
    private final Executor zzfeo;
    private final zzdeu zzfir;
    private final zzbyz zzfne;
    private final zzbzd zzfof;
    private final zzbyu zzfon;
    @Nullable
    private final zzcad zzfqt;

    public zzbzv(zzawh zzawh0, zzdeu zzdeu0, zzbzd zzbzd0, zzbyz zzbyz0, @Nullable zzcad zzcad0, Executor executor0, Executor executor1, zzbyu zzbyu0) {
        this.zzdsq = zzawh0;
        this.zzfir = zzdeu0;
        this.zzdff = zzdeu0.zzdff;
        this.zzfof = zzbzd0;
        this.zzfne = zzbyz0;
        this.zzfqt = zzcad0;
        this.zzfeo = executor0;
        this.executor = executor1;
        this.zzfon = zzbyu0;
    }

    private static void zza(RelativeLayout.LayoutParams relativeLayout$LayoutParams0, int v) {
        if(v != 0) {
            switch(v) {
                case 2: {
                    relativeLayout$LayoutParams0.addRule(12);
                    relativeLayout$LayoutParams0.addRule(11);
                    return;
                }
                case 3: {
                    relativeLayout$LayoutParams0.addRule(12);
                    relativeLayout$LayoutParams0.addRule(9);
                    return;
                }
                default: {
                    relativeLayout$LayoutParams0.addRule(10);
                    relativeLayout$LayoutParams0.addRule(11);
                    return;
                }
            }
        }
        relativeLayout$LayoutParams0.addRule(10);
        relativeLayout$LayoutParams0.addRule(9);
    }

    static boolean zza(zzbzv zzbzv0, zzcal zzcal0, String[] arr_s) {
        return zzbzv.zza(zzcal0, arr_s);
    }

    private static boolean zza(zzcal zzcal0, String[] arr_s) {
        Map map0 = zzcal0.zzalh();
        if(map0 == null) {
            return false;
        }
        for(int v = 0; v < arr_s.length; ++v) {
            if(map0.get(arr_s[v]) != null) {
                return true;
            }
        }
        return false;
    }

    public final void zza(zzcal zzcal0) {
        zzbzu zzbzu0 = () -> {
            IObjectWrapper iObjectWrapper0;
            ViewGroup viewGroup1;
            View view1;
            ViewGroup viewGroup0;
            if(this.zzfof.zzaks() || this.zzfof.zzakr()) {
                viewGroup0 = null;
                for(int v1 = 0; v1 < 2; ++v1) {
                    View view0 = zzcal0.zzgc(new String[]{"1098", "3011"}[v1]);
                    if(view0 != null && view0 instanceof ViewGroup) {
                        viewGroup0 = (ViewGroup)view0;
                        break;
                    }
                }
            }
            else {
                viewGroup0 = null;
            }
            Context context0 = zzcal0.zzagm().getContext();
            RelativeLayout.LayoutParams relativeLayout$LayoutParams0 = new RelativeLayout.LayoutParams(-2, -2);
            if(this.zzfne.zzakf() != null) {
                view1 = this.zzfne.zzakf();
                zzach zzach0 = this.zzdff;
                if(zzach0 != null && viewGroup0 == null) {
                    zzbzv.zza(relativeLayout$LayoutParams0, zzach0.zzbkh);
                    view1.setLayoutParams(relativeLayout$LayoutParams0);
                }
            }
            else if(this.zzfne.zzrl() instanceof zzaby) {
                zzaby zzaby0 = (zzaby)this.zzfne.zzrl();
                if(viewGroup0 == null) {
                    zzbzv.zza(relativeLayout$LayoutParams0, zzaby0.zzre());
                }
                zzacb zzacb0 = new zzacb(context0, zzaby0, relativeLayout$LayoutParams0);
                zzacb0.setContentDescription(((CharSequence)zzvh.zzpd().zzd(zzzx.zzcna)));
                view1 = zzacb0;
            }
            else {
                view1 = null;
            }
            if(view1 != null) {
                if(view1.getParent() instanceof ViewGroup) {
                    ((ViewGroup)view1.getParent()).removeView(view1);
                }
                if(viewGroup0 == null) {
                    AdChoicesView adChoicesView0 = new AdChoicesView(zzcal0.zzagm().getContext());
                    adChoicesView0.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                    adChoicesView0.addView(view1);
                    FrameLayout frameLayout0 = zzcal0.zzale();
                    if(frameLayout0 != null) {
                        frameLayout0.addView(adChoicesView0);
                    }
                }
                else {
                    viewGroup0.removeAllViews();
                    viewGroup0.addView(view1);
                }
                zzcal0.zza(zzcal0.zzalj(), view1, true);
            }
            if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcrb)).booleanValue()) {
                this.zzc(zzcal0);
            }
            String[] arr_s = zzbzt.zzfqk;
            for(int v = 0; true; ++v) {
                viewGroup1 = null;
                if(v >= arr_s.length) {
                    break;
                }
                View view2 = zzcal0.zzgc(arr_s[v]);
                if(view2 instanceof ViewGroup) {
                    viewGroup1 = (ViewGroup)view2;
                    break;
                }
            }
            zzbzx zzbzx0 = () -> if(this.zzfne.zzaki() != null) {
                if(2 == this.zzfne.zzake() || 1 == this.zzfne.zzake()) {
                    String s = String.valueOf(this.zzfne.zzake());
                    this.zzdsq.zza(this.zzfir.zzgqr, s, viewGroup1 != null);
                }
                else if(6 == this.zzfne.zzake()) {
                    this.zzdsq.zza(this.zzfir.zzgqr, "2", viewGroup1 != null);
                    this.zzdsq.zza(this.zzfir.zzgqr, "1", viewGroup1 != null);
                }
            };
            this.executor.execute(zzbzx0);
            if(viewGroup1 != null) {
                if(!this.zza(viewGroup1)) {
                    viewGroup1.removeAllViews();
                    View view3 = zzcal0.zzagm();
                    Context context1 = view3 == null ? null : view3.getContext();
                    if(context1 != null) {
                        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcmz)).booleanValue()) {
                            zzacm zzacm0 = this.zzfon.zzrv();
                            if(zzacm0 != null) {
                                try {
                                    iObjectWrapper0 = zzacm0.zzri();
                                    goto label_80;
                                }
                                catch(RemoteException unused_ex) {
                                    zzawf.zzfa("Could not get main image drawable");
                                }
                            }
                        }
                        else {
                            zzacr zzacr0 = this.zzfne.zzakg();
                            if(zzacr0 != null) {
                                try {
                                    iObjectWrapper0 = zzacr0.zzrg();
                                }
                                catch(RemoteException unused_ex) {
                                    zzawf.zzfa("Could not get drawable from image");
                                    return;
                                }
                            label_80:
                                if(iObjectWrapper0 != null) {
                                    Drawable drawable0 = (Drawable)ObjectWrapper.unwrap(iObjectWrapper0);
                                    if(drawable0 != null) {
                                        ImageView imageView0 = new ImageView(context1);
                                        imageView0.setImageDrawable(drawable0);
                                        IObjectWrapper iObjectWrapper1 = zzcal0.zzalk();
                                        if(iObjectWrapper1 == null || !((Boolean)zzvh.zzpd().zzd(zzzx.zzcrd)).booleanValue()) {
                                            imageView0.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                        }
                                        else {
                                            imageView0.setScaleType(((ImageView.ScaleType)ObjectWrapper.unwrap(iObjectWrapper1)));
                                        }
                                        imageView0.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                                        viewGroup1.addView(imageView0);
                                    }
                                }
                            }
                        }
                    }
                }
                else if(this.zzfne.zzakj() != null) {
                    this.zzfne.zzakj().zza(new zzbzw(this, zzcal0, viewGroup1));
                }
            }
        };
        this.zzfeo.execute(zzbzu0);
    }

    public final boolean zza(@NonNull ViewGroup viewGroup0) {
        View view0 = this.zzfne.zzaki();
        if(view0 == null) {
            return false;
        }
        viewGroup0.removeAllViews();
        if(view0.getParent() instanceof ViewGroup) {
            ((ViewGroup)view0.getParent()).removeView(view0);
        }
        viewGroup0.addView(view0, (((Boolean)zzvh.zzpd().zzd(zzzx.zzcnd)).booleanValue() ? new FrameLayout.LayoutParams(-1, -1, 17) : new FrameLayout.LayoutParams(-2, -2, 17)));
        return true;
    }

    // 检测为 Lambda 实现
    final void zzb(ViewGroup viewGroup0) [...]

    public final void zzc(@Nullable zzcal zzcal0) {
        if(zzcal0 == null || this.zzfqt == null || zzcal0.zzale() == null || ((Boolean)zzvh.zzpd().zzd(zzzx.zzcrc)).booleanValue() && !this.zzfof.zzakq()) {
            return;
        }
        try {
            zzcal0.zzale().addView(this.zzfqt.zzalp());
        }
        catch(zzbei zzbei0) {
            zzawf.zza("web view can not be obtained", zzbei0);
        }
    }

    // 检测为 Lambda 实现
    final void zzd(zzcal zzcal0) [...]
}

