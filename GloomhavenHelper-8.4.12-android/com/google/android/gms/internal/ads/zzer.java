package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.view.View.OnAttachStateChangeListener;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;

public final class zzer implements Application.ActivityLifecycleCallbacks, View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private final zzei zzuy;
    private Application zzxe;
    private static final Handler zzyy;
    private final Context zzyz;
    @Nullable
    private final PowerManager zzza;
    @Nullable
    private final KeyguardManager zzzb;
    private BroadcastReceiver zzzc;
    private WeakReference zzzd;
    private WeakReference zzze;
    private zzdx zzzf;
    private byte zzzg;
    private int zzzh;
    private long zzzi;

    static {
        zzer.zzyy = new Handler(Looper.getMainLooper());
    }

    public zzer(zzei zzei0, View view0) {
        this.zzzg = -1;
        this.zzzh = -1;
        this.zzzi = -3L;
        this.zzuy = zzei0;
        this.zzyz = zzei0.zzur;
        this.zzza = (PowerManager)this.zzyz.getSystemService("power");
        this.zzzb = (KeyguardManager)this.zzyz.getSystemService("keyguard");
        Context context0 = this.zzyz;
        if(context0 instanceof Application) {
            this.zzxe = (Application)context0;
            this.zzzf = new zzdx(((Application)context0), this);
        }
        this.zzd(view0);
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity0, Bundle bundle0) {
        this.zza(activity0, 0);
        this.zzcr();
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity0) {
        this.zzcr();
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity0) {
        this.zza(activity0, 4);
        this.zzcr();
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity0) {
        this.zza(activity0, 0);
        this.zzcr();
        this.zzcp();
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity0, Bundle bundle0) {
        this.zzcr();
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity0) {
        this.zza(activity0, 0);
        this.zzcr();
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity0) {
        this.zzcr();
    }

    @Override  // android.view.ViewTreeObserver$OnGlobalLayoutListener
    public final void onGlobalLayout() {
        this.zzcr();
    }

    @Override  // android.view.ViewTreeObserver$OnScrollChangedListener
    public final void onScrollChanged() {
        this.zzcr();
    }

    @Override  // android.view.View$OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view0) {
        this.zzzh = -1;
        this.zze(view0);
        this.zzcr();
    }

    @Override  // android.view.View$OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view0) {
        this.zzzh = -1;
        this.zzcr();
        this.zzcp();
        this.zzf(view0);
    }

    private final void zza(Activity activity0, int v) {
        if(this.zzze == null) {
            return;
        }
        Window window0 = activity0.getWindow();
        if(window0 == null) {
            return;
        }
        View view0 = window0.peekDecorView();
        View view1 = (View)this.zzze.get();
        if(view1 != null && view0 != null && view1.getRootView() == view0.getRootView()) {
            this.zzzh = v;
        }
    }

    private final void zzcp() {
        zzeu zzeu0 = new zzeu(this);
        zzer.zzyy.post(zzeu0);
    }

    public final long zzcq() {
        if(this.zzzi <= -2L && this.zzze.get() == null) {
            this.zzzi = -3L;
        }
        return this.zzzi;
    }

    private final void zzcr() {
        boolean z1;
        WeakReference weakReference0 = this.zzze;
        if(weakReference0 == null) {
            return;
        }
        boolean z = true;
        View view0 = (View)weakReference0.get();
        if(view0 == null) {
            this.zzzi = -3L;
            this.zzzg = -1;
            return;
        }
        byte b = view0.getVisibility() == 0 ? 0 : 1;
        if(!view0.isShown()) {
            b = (byte)(b | 2);
        }
        if(this.zzza != null && !this.zzza.isScreenOn()) {
            b = (byte)(b | 4);
        }
        if(!this.zzuy.zzcj()) {
            if(this.zzzb == null || !this.zzzb.inKeyguardRestrictedInputMode()) {
                z = false;
            }
            else {
                Activity activity0 = zzep.zzc(view0);
                if(activity0 == null) {
                    z1 = false;
                }
                else {
                    Window window0 = activity0.getWindow();
                    WindowManager.LayoutParams windowManager$LayoutParams0 = window0 == null ? null : window0.getAttributes();
                    z1 = windowManager$LayoutParams0 == null || (windowManager$LayoutParams0.flags & 0x80000) == 0 ? false : true;
                }
                if(!z1) {
                    z = false;
                }
            }
        }
        if(!z) {
            b = (byte)(b | 8);
        }
        if(!view0.getGlobalVisibleRect(new Rect())) {
            b = (byte)(b | 16);
        }
        if(!view0.getLocalVisibleRect(new Rect())) {
            b = (byte)(b | 0x20);
        }
        int v = view0.getWindowVisibility();
        int v1 = this.zzzh;
        if(v1 != -1) {
            v = v1;
        }
        if(v != 0) {
            b = (byte)(b | 0x40);
        }
        if(this.zzzg != b) {
            this.zzzg = b;
            this.zzzi = this.zzzg == 0 ? SystemClock.elapsedRealtime() : -3L - ((long)this.zzzg);
        }
    }

    final void zzd(View view0) {
        View view1 = this.zzze == null ? null : ((View)this.zzze.get());
        if(view1 != null) {
            view1.removeOnAttachStateChangeListener(this);
            this.zzf(view1);
        }
        this.zzze = new WeakReference(view0);
        if(view0 != null) {
            if(view0.getWindowToken() != null || view0.getWindowVisibility() != 8) {
                this.zze(view0);
            }
            view0.addOnAttachStateChangeListener(this);
            this.zzzi = -2L;
            return;
        }
        this.zzzi = -3L;
    }

    private final void zze(View view0) {
        ViewTreeObserver viewTreeObserver0 = view0.getViewTreeObserver();
        if(viewTreeObserver0.isAlive()) {
            this.zzzd = new WeakReference(viewTreeObserver0);
            viewTreeObserver0.addOnScrollChangedListener(this);
            viewTreeObserver0.addOnGlobalLayoutListener(this);
        }
        if(this.zzzc == null) {
            IntentFilter intentFilter0 = new IntentFilter();
            intentFilter0.addAction("android.intent.action.SCREEN_ON");
            intentFilter0.addAction("android.intent.action.SCREEN_OFF");
            intentFilter0.addAction("android.intent.action.USER_PRESENT");
            this.zzzc = new zzet(this);
            this.zzyz.registerReceiver(this.zzzc, intentFilter0);
        }
        Application application0 = this.zzxe;
        if(application0 != null) {
            try {
                application0.registerActivityLifecycleCallbacks(this.zzzf);
            }
            catch(Exception unused_ex) {
            }
        }
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
        catch(Exception unused_ex) {
        }
        try {
            ViewTreeObserver viewTreeObserver1 = view0.getViewTreeObserver();
            if(viewTreeObserver1.isAlive()) {
                viewTreeObserver1.removeOnScrollChangedListener(this);
                viewTreeObserver1.removeGlobalOnLayoutListener(this);
            }
        }
        catch(Exception unused_ex) {
        }
        BroadcastReceiver broadcastReceiver0 = this.zzzc;
        if(broadcastReceiver0 != null) {
            try {
                this.zzyz.unregisterReceiver(broadcastReceiver0);
            }
            catch(Exception unused_ex) {
            }
            this.zzzc = null;
        }
        Application application0 = this.zzxe;
        if(application0 != null) {
            try {
                application0.unregisterActivityLifecycleCallbacks(this.zzzf);
            }
            catch(Exception unused_ex) {
            }
        }
    }
}

