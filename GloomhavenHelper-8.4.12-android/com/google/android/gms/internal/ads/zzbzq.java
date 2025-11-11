package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@ParametersAreNonnullByDefault
public final class zzbzq extends zzadb implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, zzcal {
    private final WeakReference zzfqc;
    private final Map zzfqd;
    private final Map zzfqe;
    private final Map zzfqf;
    @GuardedBy("this")
    private zzbyo zzfqg;
    private zzpp zzfqh;

    public zzbzq(View view0, HashMap hashMap0, HashMap hashMap1) {
        this.zzfqd = new HashMap();
        this.zzfqe = new HashMap();
        this.zzfqf = new HashMap();
        view0.setOnTouchListener(this);
        view0.setOnClickListener(this);
        zzbag.zza(view0, this);
        zzbag.zza(view0, this);
        this.zzfqc = new WeakReference(view0);
        for(Object object0: hashMap0.entrySet()) {
            String s = (String)((Map.Entry)object0).getKey();
            View view1 = (View)((Map.Entry)object0).getValue();
            if(view1 != null) {
                WeakReference weakReference0 = new WeakReference(view1);
                this.zzfqd.put(s, weakReference0);
                if(!"1098".equals(s) && !"3011".equals(s)) {
                    view1.setOnTouchListener(this);
                    view1.setClickable(true);
                    view1.setOnClickListener(this);
                }
            }
        }
        this.zzfqf.putAll(this.zzfqd);
        for(Object object1: hashMap1.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object1;
            View view2 = (View)map$Entry0.getValue();
            if(view2 != null) {
                String s1 = (String)map$Entry0.getKey();
                WeakReference weakReference1 = new WeakReference(view2);
                this.zzfqe.put(s1, weakReference1);
                view2.setOnTouchListener(this);
                view2.setClickable(false);
            }
        }
        this.zzfqf.putAll(this.zzfqe);
        this.zzfqh = new zzpp(view0.getContext(), view0);
    }

    @Override  // android.view.View$OnClickListener
    public final void onClick(View view0) {
        synchronized(this) {
            if(this.zzfqg != null) {
                this.zzfqg.zza(view0, this.zzagm(), this.zzalg(), this.zzalh(), true);
            }
        }
    }

    @Override  // android.view.ViewTreeObserver$OnGlobalLayoutListener
    public final void onGlobalLayout() {
        synchronized(this) {
            if(this.zzfqg != null) {
                this.zzfqg.zzb(this.zzagm(), this.zzalg(), this.zzalh(), zzbyo.zzy(this.zzagm()));
            }
        }
    }

    @Override  // android.view.ViewTreeObserver$OnScrollChangedListener
    public final void onScrollChanged() {
        synchronized(this) {
            if(this.zzfqg != null) {
                this.zzfqg.zzb(this.zzagm(), this.zzalg(), this.zzalh(), zzbyo.zzy(this.zzagm()));
            }
        }
    }

    @Override  // android.view.View$OnTouchListener
    public final boolean onTouch(View view0, MotionEvent motionEvent0) {
        synchronized(this) {
            if(this.zzfqg != null) {
                this.zzfqg.zza(view0, motionEvent0, this.zzagm());
            }
            return false;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzacy
    public final void unregisterNativeAd() {
        synchronized(this) {
            if(this.zzfqg != null) {
                this.zzfqg.zzb(this);
                this.zzfqg = null;
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzacy
    public final void zza(IObjectWrapper iObjectWrapper0) {
        synchronized(this) {
            Object object0 = ObjectWrapper.unwrap(iObjectWrapper0);
            if(!(object0 instanceof zzbyo)) {
                zzawf.zzfa("Not an instance of InternalNativeAd. This is most likely a transient error");
                return;
            }
            if(this.zzfqg != null) {
                this.zzfqg.zzb(this);
            }
            if(((zzbyo)object0).zzajz()) {
                this.zzfqg = (zzbyo)object0;
                this.zzfqg.zza(this);
                this.zzfqg.zzz(this.zzagm());
                return;
            }
            zzawf.zzey("Your account must be enabled to use this feature. Talk to your account manager to request this feature for your account.");
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    public final void zza(String s, View view0, boolean z) {
        synchronized(this) {
            if(view0 == null) {
                this.zzfqf.remove(s);
                this.zzfqd.remove(s);
                this.zzfqe.remove(s);
                return;
            }
            WeakReference weakReference0 = new WeakReference(view0);
            this.zzfqf.put(s, weakReference0);
            if(!"1098".equals(s) && !"3011".equals(s)) {
                WeakReference weakReference1 = new WeakReference(view0);
                this.zzfqd.put(s, weakReference1);
                view0.setClickable(true);
                view0.setOnClickListener(this);
                view0.setOnTouchListener(this);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    @Nullable
    public final View zzagm() {
        return (View)this.zzfqc.get();
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    @Nullable
    public final FrameLayout zzale() {
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    public final zzpp zzalf() {
        return this.zzfqh;
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    public final Map zzalg() {
        synchronized(this) {
        }
        return this.zzfqf;
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    public final Map zzalh() {
        synchronized(this) {
        }
        return this.zzfqd;
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    @Nullable
    public final Map zzali() {
        synchronized(this) {
        }
        return this.zzfqe;
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    public final String zzalj() {
        synchronized(this) {
        }
        return "1007";
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    @Nullable
    public final IObjectWrapper zzalk() {
        synchronized(this) {
        }
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzacy
    public final void zze(IObjectWrapper iObjectWrapper0) {
        synchronized(this) {
            if(this.zzfqg != null) {
                Object object0 = ObjectWrapper.unwrap(iObjectWrapper0);
                if(!(object0 instanceof View)) {
                    zzawf.zzfa("Calling NativeAdViewHolderNonagonDelegate.setClickConfirmingView with wrong wrapped object");
                }
                this.zzfqg.setClickConfirmingView(((View)object0));
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    public final View zzgc(String s) {
        synchronized(this) {
            WeakReference weakReference0 = (WeakReference)this.zzfqf.get(s);
            return weakReference0 == null ? null : ((View)weakReference0.get());
        }
    }
}

