package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.ViewTreeObserver;
import android.view.Window;

public final class zzayy {
    private final View view;
    private boolean zzboi;
    private boolean zzbsm;
    private Activity zzdwu;
    private boolean zzdwv;
    private ViewTreeObserver.OnGlobalLayoutListener zzdww;
    private ViewTreeObserver.OnScrollChangedListener zzdwx;

    public zzayy(Activity activity0, View view0, ViewTreeObserver.OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener0, ViewTreeObserver.OnScrollChangedListener viewTreeObserver$OnScrollChangedListener0) {
        this.zzdwu = activity0;
        this.view = view0;
        this.zzdww = viewTreeObserver$OnGlobalLayoutListener0;
        this.zzdwx = null;
    }

    public final void onAttachedToWindow() {
        this.zzboi = true;
        if(this.zzbsm) {
            this.zzxn();
        }
    }

    public final void onDetachedFromWindow() {
        this.zzboi = false;
        this.zzxo();
    }

    public final void zzh(Activity activity0) {
        this.zzdwu = activity0;
    }

    private static ViewTreeObserver zzi(Activity activity0) {
        if(activity0 == null) {
            return null;
        }
        Window window0 = activity0.getWindow();
        if(window0 == null) {
            return null;
        }
        View view0 = window0.getDecorView();
        return view0 == null ? null : view0.getViewTreeObserver();
    }

    public final void zzxl() {
        this.zzbsm = true;
        if(this.zzboi) {
            this.zzxn();
        }
    }

    public final void zzxm() {
        this.zzbsm = false;
        this.zzxo();
    }

    private final void zzxn() {
        if(!this.zzdwv) {
            ViewTreeObserver.OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener0 = this.zzdww;
            if(viewTreeObserver$OnGlobalLayoutListener0 != null) {
                Activity activity0 = this.zzdwu;
                if(activity0 != null) {
                    ViewTreeObserver viewTreeObserver0 = zzayy.zzi(activity0);
                    if(viewTreeObserver0 != null) {
                        viewTreeObserver0.addOnGlobalLayoutListener(viewTreeObserver$OnGlobalLayoutListener0);
                    }
                }
                zzbag.zza(this.view, this.zzdww);
            }
            this.zzdwv = true;
        }
    }

    private final void zzxo() {
        Activity activity0 = this.zzdwu;
        if(activity0 == null) {
            return;
        }
        if(this.zzdwv) {
            ViewTreeObserver.OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener0 = this.zzdww;
            if(viewTreeObserver$OnGlobalLayoutListener0 != null) {
                ViewTreeObserver viewTreeObserver0 = zzayy.zzi(activity0);
                if(viewTreeObserver0 != null) {
                    viewTreeObserver0.removeOnGlobalLayoutListener(viewTreeObserver$OnGlobalLayoutListener0);
                }
            }
            this.zzdwv = false;
        }
    }
}

