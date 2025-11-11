package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.Map;
import java.util.Set;

public final class zzaom extends zzaow {
    private int height;
    private final Object lock;
    private int width;
    private zzaoz zzcyr;
    private final zzbdv zzdae;
    private final Activity zzdfz;
    private static final Set zzdgj;
    private String zzdgk;
    private boolean zzdgl;
    private int zzdgm;
    private int zzdgn;
    private int zzdgo;
    private int zzdgp;
    private zzbfl zzdgq;
    private ImageView zzdgr;
    private LinearLayout zzdgs;
    private PopupWindow zzdgt;
    private RelativeLayout zzdgu;
    private ViewGroup zzdgv;

    static {
        zzaom.zzdgj = CollectionUtils.setOf(new String[]{"top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center"});
    }

    public zzaom(zzbdv zzbdv0, zzaoz zzaoz0) {
        super(zzbdv0, "resize");
        this.zzdgk = "top-right";
        this.zzdgl = true;
        this.zzdgm = 0;
        this.zzdgn = 0;
        this.height = -1;
        this.zzdgo = 0;
        this.zzdgp = 0;
        this.width = -1;
        this.lock = new Object();
        this.zzdae = zzbdv0;
        this.zzdfz = zzbdv0.zzys();
        this.zzcyr = zzaoz0;
    }

    public final void zza(int v, int v1, boolean z) {
        synchronized(this.lock) {
            this.zzdgm = v;
            this.zzdgn = v1;
        }
    }

    public final void zzac(boolean z) {
        synchronized(this.lock) {
            if(this.zzdgt != null) {
                this.zzdgt.dismiss();
                this.zzdgu.removeView(this.zzdae.getView());
                if(this.zzdgv != null) {
                    this.zzdgv.removeView(this.zzdgr);
                    this.zzdgv.addView(this.zzdae.getView());
                    this.zzdae.zza(this.zzdgq);
                }
                if(z) {
                    this.zzdv("default");
                    if(this.zzcyr != null) {
                        this.zzcyr.zztn();
                    }
                }
                this.zzdgt = null;
                this.zzdgu = null;
                this.zzdgv = null;
                this.zzdgs = null;
            }
        }
    }

    public final void zzg(Map map0) {
        int v8;
        int[] arr_v2;
        int v4;
        int v3;
        boolean z;
        synchronized(this.lock) {
            if(this.zzdfz == null) {
                this.zzdt("Not an activity context. Cannot resize.");
                return;
            }
            if(this.zzdae.zzaad() == null) {
                this.zzdt("Webview is not yet available, size is not set.");
                return;
            }
            if(this.zzdae.zzaad().zzaby()) {
                this.zzdt("Is interstitial. Cannot resize an interstitial.");
                return;
            }
            if(this.zzdae.zzaak()) {
                this.zzdt("Cannot resize an expanded banner.");
                return;
            }
            if(!TextUtils.isEmpty(((CharSequence)map0.get("width")))) {
                this.width = zzawo.zzej(((String)map0.get("width")));
            }
            if(!TextUtils.isEmpty(((CharSequence)map0.get("height")))) {
                this.height = zzawo.zzej(((String)map0.get("height")));
            }
            if(!TextUtils.isEmpty(((CharSequence)map0.get("offsetX")))) {
                this.zzdgo = zzawo.zzej(((String)map0.get("offsetX")));
            }
            if(!TextUtils.isEmpty(((CharSequence)map0.get("offsetY")))) {
                this.zzdgp = zzawo.zzej(((String)map0.get("offsetY")));
            }
            if(!TextUtils.isEmpty(((CharSequence)map0.get("allowOffscreen")))) {
                this.zzdgl = Boolean.parseBoolean(((String)map0.get("allowOffscreen")));
            }
            String s = (String)map0.get("customClosePosition");
            if(!TextUtils.isEmpty(s)) {
                this.zzdgk = s;
            }
            if(this.width < 0 || this.height < 0) {
                this.zzdt("Invalid width and height options. Cannot resize.");
                return;
            }
            Window window0 = this.zzdfz.getWindow();
            if(window0 != null && window0.getDecorView() != null) {
                int[] arr_v = zzq.zzkv().zze(this.zzdfz);
                int[] arr_v1 = zzq.zzkv().zzf(this.zzdfz);
                int v1 = arr_v[0];
                int v2 = arr_v[1];
                if(this.width < 50 || this.width > v1) {
                    zzawf.zzfa("Width is too small or too large.");
                    z = false;
                }
                else if(this.height < 50 || this.height > v2) {
                    zzawf.zzfa("Height is too small or too large.");
                    z = false;
                }
                else if(this.height == v2 && this.width == v1) {
                    zzawf.zzfa("Cannot resize to a full-screen ad.");
                    z = false;
                }
                else if(this.zzdgl) {
                    switch(this.zzdgk) {
                        case "bottom-center": {
                            v3 = this.zzdgm + this.zzdgo + this.width / 2 - 25;
                            v4 = this.zzdgn + this.zzdgp + this.height - 50;
                            break;
                        }
                        case "bottom-left": {
                            v3 = this.zzdgm + this.zzdgo;
                            v4 = this.zzdgn + this.zzdgp + this.height - 50;
                            break;
                        }
                        case "bottom-right": {
                            v3 = this.zzdgm + this.zzdgo + this.width - 50;
                            v4 = this.zzdgn + this.zzdgp + this.height - 50;
                            break;
                        }
                        case "center": {
                            v3 = this.zzdgm + this.zzdgo + this.width / 2 - 25;
                            v4 = this.zzdgn + this.zzdgp + this.height / 2 - 25;
                            break;
                        }
                        case "top-center": {
                            v3 = this.zzdgm + this.zzdgo + this.width / 2 - 25;
                            v4 = this.zzdgn + this.zzdgp;
                            break;
                        }
                        case "top-left": {
                            v3 = this.zzdgm + this.zzdgo;
                            v4 = this.zzdgn + this.zzdgp;
                            break;
                        }
                        default: {
                            v3 = this.zzdgm + this.zzdgo + this.width - 50;
                            v4 = this.zzdgn + this.zzdgp;
                        }
                    }
                    z = v3 >= 0 && v3 + 50 <= v1 && v4 >= arr_v1[0] && v4 + 50 <= arr_v1[1];
                }
                else {
                    z = true;
                }
                if(!z) {
                    arr_v2 = null;
                }
                else if(this.zzdgl) {
                    arr_v2 = new int[]{this.zzdgm + this.zzdgo, this.zzdgn + this.zzdgp};
                }
                else {
                    int[] arr_v3 = zzq.zzkv().zze(this.zzdfz);
                    int[] arr_v4 = zzq.zzkv().zzf(this.zzdfz);
                    int v5 = arr_v3[0];
                    int v6 = this.zzdgm + this.zzdgo;
                    int v7 = this.zzdgn + this.zzdgp;
                    if(v6 < 0) {
                        v8 = 0;
                    }
                    else {
                        v8 = this.width + v6 <= v5 ? v6 : v5 - this.width;
                    }
                    if(v7 < arr_v4[0]) {
                        v7 = arr_v4[0];
                    }
                    else if(this.height + v7 > arr_v4[1]) {
                        v7 = arr_v4[1] - this.height;
                    }
                    arr_v2 = new int[]{v8, v7};
                }
                if(arr_v2 == null) {
                    this.zzdt("Resize location out of screen or close button is not visible.");
                    return;
                }
                int v9 = zzayx.zzb(this.zzdfz, this.width);
                int v10 = zzayx.zzb(this.zzdfz, this.height);
                ViewParent viewParent0 = this.zzdae.getView().getParent();
                if(viewParent0 != null && viewParent0 instanceof ViewGroup) {
                    ((ViewGroup)viewParent0).removeView(this.zzdae.getView());
                    if(this.zzdgt == null) {
                        this.zzdgv = (ViewGroup)viewParent0;
                        Bitmap bitmap0 = zzawo.zzk(this.zzdae.getView());
                        this.zzdgr = new ImageView(this.zzdfz);
                        this.zzdgr.setImageBitmap(bitmap0);
                        this.zzdgq = this.zzdae.zzaad();
                        this.zzdgv.addView(this.zzdgr);
                    }
                    else {
                        this.zzdgt.dismiss();
                    }
                    this.zzdgu = new RelativeLayout(this.zzdfz);
                    this.zzdgu.setBackgroundColor(0);
                    this.zzdgu.setLayoutParams(new ViewGroup.LayoutParams(v9, v10));
                    this.zzdgt = zzawo.zza(this.zzdgu, v9, v10, false);
                    this.zzdgt.setOutsideTouchable(true);
                    this.zzdgt.setTouchable(true);
                    this.zzdgt.setClippingEnabled(!this.zzdgl);
                    this.zzdgu.addView(this.zzdae.getView(), -1, -1);
                    this.zzdgs = new LinearLayout(this.zzdfz);
                    RelativeLayout.LayoutParams relativeLayout$LayoutParams0 = new RelativeLayout.LayoutParams(zzayx.zzb(this.zzdfz, 50), zzayx.zzb(this.zzdfz, 50));
                    switch(this.zzdgk) {
                        case "bottom-center": {
                            relativeLayout$LayoutParams0.addRule(12);
                            relativeLayout$LayoutParams0.addRule(14);
                            break;
                        }
                        case "bottom-left": {
                            relativeLayout$LayoutParams0.addRule(12);
                            relativeLayout$LayoutParams0.addRule(9);
                            break;
                        }
                        case "bottom-right": {
                            relativeLayout$LayoutParams0.addRule(12);
                            relativeLayout$LayoutParams0.addRule(11);
                            break;
                        }
                        case "center": {
                            relativeLayout$LayoutParams0.addRule(13);
                            break;
                        }
                        case "top-center": {
                            relativeLayout$LayoutParams0.addRule(10);
                            relativeLayout$LayoutParams0.addRule(14);
                            break;
                        }
                        case "top-left": {
                            relativeLayout$LayoutParams0.addRule(10);
                            relativeLayout$LayoutParams0.addRule(9);
                            break;
                        }
                        default: {
                            relativeLayout$LayoutParams0.addRule(10);
                            relativeLayout$LayoutParams0.addRule(11);
                        }
                    }
                    this.zzdgs.setOnClickListener(new zzaop(this));
                    this.zzdgs.setContentDescription("Close button");
                    this.zzdgu.addView(this.zzdgs, relativeLayout$LayoutParams0);
                    try {
                        this.zzdgt.showAtLocation(window0.getDecorView(), 0, zzayx.zzb(this.zzdfz, arr_v2[0]), zzayx.zzb(this.zzdfz, arr_v2[1]));
                    }
                    catch(RuntimeException runtimeException0) {
                        String s1 = runtimeException0.getMessage();
                        this.zzdt((s1.length() == 0 ? new String("Cannot show popup window: ") : "Cannot show popup window: " + s1));
                        this.zzdgu.removeView(this.zzdae.getView());
                        if(this.zzdgv != null) {
                            this.zzdgv.removeView(this.zzdgr);
                            this.zzdgv.addView(this.zzdae.getView());
                            this.zzdae.zza(this.zzdgq);
                        }
                        return;
                    }
                    int v11 = arr_v2[0];
                    int v12 = arr_v2[1];
                    if(this.zzcyr != null) {
                        this.zzcyr.zzc(v11, v12, this.width, this.height);
                    }
                    zzbfl zzbfl0 = zzbfl.zzq(v9, v10);
                    this.zzdae.zza(zzbfl0);
                    this.zza(arr_v2[0], arr_v2[1] - zzq.zzkv().zzf(this.zzdfz)[0], this.width, this.height);
                    this.zzdv("resized");
                    return;
                }
                this.zzdt("Webview is detached, probably in the middle of a resize or expand.");
                return;
            }
            this.zzdt("Activity context is not ready, cannot get window or decor view.");
        }
    }

    public final void zzi(int v, int v1) {
        this.zzdgm = v;
        this.zzdgn = v1;
    }

    public final boolean zztl() {
        synchronized(this.lock) {
        }
        return this.zzdgt != null;
    }
}

