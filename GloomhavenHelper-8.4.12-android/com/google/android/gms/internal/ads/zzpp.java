package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.view.View.OnAttachStateChangeListener;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@TargetApi(14)
public final class zzpp implements Application.ActivityLifecycleCallbacks, View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private static final long zzbnt;
    private final WindowManager zzbnu;
    @Nullable
    @VisibleForTesting
    private BroadcastReceiver zzbnv;
    private WeakReference zzbnw;
    private zzpw zzbnx;
    private zzayn zzbny;
    private boolean zzbnz;
    private final HashSet zzboa;
    private final Rect zzbob;
    private final DisplayMetrics zzwi;
    private Application zzxe;
    private final Context zzyz;
    private final PowerManager zzza;
    private final KeyguardManager zzzb;
    private WeakReference zzzd;
    private int zzzh;

    static {
        zzpp.zzbnt = (long)(((Long)zzvh.zzpd().zzd(zzzx.zzcla)));
    }

    public zzpp(Context context0, View view0) {
        this.zzbny = new zzayn(zzpp.zzbnt);
        this.zzbnz = false;
        this.zzzh = -1;
        this.zzboa = new HashSet();
        this.zzyz = context0.getApplicationContext();
        this.zzbnu = (WindowManager)context0.getSystemService("window");
        this.zzza = (PowerManager)this.zzyz.getSystemService("power");
        this.zzzb = (KeyguardManager)context0.getSystemService("keyguard");
        Context context1 = this.zzyz;
        if(context1 instanceof Application) {
            this.zzxe = (Application)context1;
            this.zzbnx = new zzpw(((Application)context1), this);
        }
        this.zzwi = context0.getResources().getDisplayMetrics();
        this.zzbob = new Rect();
        this.zzbob.right = this.zzbnu.getDefaultDisplay().getWidth();
        this.zzbob.bottom = this.zzbnu.getDefaultDisplay().getHeight();
        View view1 = this.zzbnw == null ? null : ((View)this.zzbnw.get());
        if(view1 != null) {
            view1.removeOnAttachStateChangeListener(this);
            this.zzf(view1);
        }
        this.zzbnw = new WeakReference(view0);
        if(view0 != null) {
            if(zzq.zzkx().isAttachedToWindow(view0)) {
                this.zze(view0);
            }
            view0.addOnAttachStateChangeListener(this);
        }
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity0, Bundle bundle0) {
        this.zza(activity0, 0);
        this.zzbn(3);
        this.zzcp();
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity0) {
        this.zzbn(3);
        this.zzcp();
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity0) {
        this.zza(activity0, 4);
        this.zzbn(3);
        this.zzcp();
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity0) {
        this.zza(activity0, 0);
        this.zzbn(3);
        this.zzcp();
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity0, Bundle bundle0) {
        this.zzbn(3);
        this.zzcp();
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity0) {
        this.zza(activity0, 0);
        this.zzbn(3);
        this.zzcp();
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity0) {
        this.zzbn(3);
        this.zzcp();
    }

    @Override  // android.view.ViewTreeObserver$OnGlobalLayoutListener
    public final void onGlobalLayout() {
        this.zzbn(2);
        this.zzcp();
    }

    @Override  // android.view.ViewTreeObserver$OnScrollChangedListener
    public final void onScrollChanged() {
        this.zzbn(1);
    }

    @Override  // android.view.View$OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view0) {
        this.zzzh = -1;
        this.zze(view0);
        this.zzbn(3);
    }

    @Override  // android.view.View$OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view0) {
        this.zzzh = -1;
        this.zzbn(3);
        this.zzcp();
        this.zzf(view0);
    }

    private final Rect zza(Rect rect0) {
        return new Rect(this.zzbo(rect0.left), this.zzbo(rect0.top), this.zzbo(rect0.right), this.zzbo(rect0.bottom));
    }

    private final void zza(Activity activity0, int v) {
        if(this.zzbnw == null) {
            return;
        }
        Window window0 = activity0.getWindow();
        if(window0 == null) {
            return;
        }
        View view0 = window0.peekDecorView();
        View view1 = (View)this.zzbnw.get();
        if(view1 != null && view0 != null && view1.getRootView() == view0.getRootView()) {
            this.zzzh = v;
        }
    }

    static void zza(zzpp zzpp0, int v) {
        zzpp0.zzbn(3);
    }

    public final void zza(zzpt zzpt0) {
        this.zzboa.add(zzpt0);
        this.zzbn(3);
    }

    public final void zzb(zzpt zzpt0) {
        this.zzboa.remove(zzpt0);
    }

    private final void zzbn(int v) {
        boolean z3;
        boolean z2;
        if(this.zzboa.size() == 0) {
            return;
        }
        WeakReference weakReference0 = this.zzbnw;
        if(weakReference0 == null) {
            return;
        }
        View view0 = (View)weakReference0.get();
        Rect rect0 = new Rect();
        Rect rect1 = new Rect();
        Rect rect2 = new Rect();
        Rect rect3 = new Rect();
        int[] arr_v = new int[2];
        if(view0 == null) {
            z2 = false;
            z3 = false;
        }
        else {
            boolean z = view0.getGlobalVisibleRect(rect1);
            boolean z1 = view0.getLocalVisibleRect(rect2);
            view0.getHitRect(rect3);
            try {
                view0.getLocationOnScreen(arr_v);
                view0.getLocationInWindow(new int[2]);
            }
            catch(Exception exception0) {
                zzawf.zzc("Failure getting view location.", exception0);
            }
            rect0.left = arr_v[0];
            rect0.top = arr_v[1];
            rect0.right = rect0.left + view0.getWidth();
            rect0.bottom = rect0.top + view0.getHeight();
            z2 = z;
            z3 = z1;
        }
        List list0 = !((Boolean)zzvh.zzpd().zzd(zzzx.zzcld)).booleanValue() || view0 == null ? Collections.emptyList() : this.zzh(view0);
        int v1 = view0 == null ? 8 : view0.getWindowVisibility();
        int v2 = this.zzzh;
        if(v2 != -1) {
            v1 = v2;
        }
        boolean z4 = view0 != null && zzq.zzkv().zza(view0, this.zzza, this.zzzb) && z2 && z3 && v1 == 0;
        if(v == 1 && !this.zzbny.tryAcquire() && z4 == this.zzbnz) {
            return;
        }
        if(!z4 && !this.zzbnz && v == 1) {
            return;
        }
        zzpu zzpu0 = new zzpu(zzq.zzlc().elapsedRealtime(), this.zzza.isScreenOn(), view0 != null && zzq.zzkx().isAttachedToWindow(view0), (view0 == null ? 8 : view0.getWindowVisibility()), this.zza(this.zzbob), this.zza(rect0), this.zza(rect1), z2, this.zza(rect2), z3, this.zza(rect3), this.zzwi.density, z4, list0);
        for(Object object0: this.zzboa) {
            ((zzpt)object0).zza(zzpu0);
        }
        this.zzbnz = z4;
    }

    private final int zzbo(int v) {
        return (int)(((float)v) / this.zzwi.density);
    }

    private final void zzcp() {
        zzps zzps0 = () -> this.zzbn(3);
        zzawo.zzdtx.post(zzps0);
    }

    private final void zze(View view0) {
        ViewTreeObserver viewTreeObserver0 = view0.getViewTreeObserver();
        if(viewTreeObserver0.isAlive()) {
            this.zzzd = new WeakReference(viewTreeObserver0);
            viewTreeObserver0.addOnScrollChangedListener(this);
            viewTreeObserver0.addOnGlobalLayoutListener(this);
        }
        if(this.zzbnv == null) {
            IntentFilter intentFilter0 = new IntentFilter();
            intentFilter0.addAction("android.intent.action.SCREEN_ON");
            intentFilter0.addAction("android.intent.action.SCREEN_OFF");
            intentFilter0.addAction("android.intent.action.USER_PRESENT");
            this.zzbnv = new zzpr(this);
            zzq.zzlq().zza(this.zzyz, this.zzbnv, intentFilter0);
        }
        Application application0 = this.zzxe;
        if(application0 != null) {
            try {
                application0.registerActivityLifecycleCallbacks(this.zzbnx);
            }
            catch(Exception exception0) {
                zzawf.zzc("Error registering activity lifecycle callbacks.", exception0);
            }
        }
    }

    public final void zzen(long v) {
        this.zzbny.zzfb(v);
    }

    private final void zzf(View view0) {
        try {
            if(this.zzzd != null) {
                ViewTreeObserver viewTreeObserver0 = (ViewTreeObserver)this.zzzd.get();
                if(viewTreeObserver0 != null && viewTreeObserver0.isAlive()) {
                    viewTreeObserver0.removeOnScrollChangedListener(this);
                    viewTreeObserver0.removeGlobalOnLayoutListener(this);
                }
                this.zzzd = null;
            }
        }
        catch(Exception exception0) {
            zzawf.zzc("Error while unregistering listeners from the last ViewTreeObserver.", exception0);
        }
        try {
            ViewTreeObserver viewTreeObserver1 = view0.getViewTreeObserver();
            if(viewTreeObserver1.isAlive()) {
                viewTreeObserver1.removeOnScrollChangedListener(this);
                viewTreeObserver1.removeGlobalOnLayoutListener(this);
            }
        }
        catch(Exception exception1) {
            zzawf.zzc("Error while unregistering listeners from the ViewTreeObserver.", exception1);
        }
        if(this.zzbnv != null) {
            try {
                zzq.zzlq().zza(this.zzyz, this.zzbnv);
            }
            catch(IllegalStateException illegalStateException0) {
                zzawf.zzc("Failed trying to unregister the receiver", illegalStateException0);
            }
            catch(Exception exception2) {
                zzq.zzkz().zza(exception2, "ActiveViewUnit.stopScreenStatusMonitoring");
            }
            this.zzbnv = null;
        }
        Application application0 = this.zzxe;
        if(application0 != null) {
            try {
                application0.unregisterActivityLifecycleCallbacks(this.zzbnx);
            }
            catch(Exception exception3) {
                zzawf.zzc("Error registering activity lifecycle callbacks.", exception3);
            }
        }
    }

    private final List zzh(View view0) {
        try {
            List list0 = new ArrayList();
            for(ViewParent viewParent0 = view0.getParent(); viewParent0 instanceof View; viewParent0 = viewParent0.getParent()) {
                Rect rect0 = new Rect();
                if(((View)viewParent0).isScrollContainer() && ((View)viewParent0).getGlobalVisibleRect(rect0)) {
                    list0.add(this.zza(rect0));
                }
            }
            return list0;
        }
        catch(Exception exception0) {
            zzq.zzkz().zza(exception0, "PositionWatcher.getParentScrollViewRects");
            return Collections.emptyList();
        }
    }

    public final void zzlu() {
        this.zzbny.zzfb(zzpp.zzbnt);
    }

    // 检测为 Lambda 实现
    final void zzlv() [...]
}

