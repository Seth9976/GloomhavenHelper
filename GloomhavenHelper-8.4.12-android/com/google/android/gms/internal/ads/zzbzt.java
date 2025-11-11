package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

public final class zzbzt extends zzacu implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, zzcal {
    private FrameLayout zzbkk;
    private zzacm zzcfx;
    private final int zzdxg;
    private boolean zzehn;
    @GuardedBy("this")
    private zzbyo zzfqg;
    private zzpp zzfqh;
    private final String zzfqj;
    public static final String[] zzfqk;
    @GuardedBy("this")
    private Map zzfql;
    private FrameLayout zzfqm;
    private zzdoe zzfqn;
    private View zzfqo;
    private IObjectWrapper zzfqp;
    private boolean zzfqq;

    static {
        zzbzt.zzfqk = new String[]{"2011", "1009", "3010"};
    }

    public zzbzt(FrameLayout frameLayout0, FrameLayout frameLayout1, int v) {
        String s1;
        this.zzfql = new HashMap();
        this.zzfqp = null;
        this.zzehn = false;
        this.zzfqm = frameLayout0;
        this.zzbkk = frameLayout1;
        this.zzdxg = v;
        String s = frameLayout0.getClass().getCanonicalName();
        if("com.google.android.gms.ads.formats.NativeContentAdView".equals(s)) {
            s1 = "1007";
        }
        else if("com.google.android.gms.ads.formats.NativeAppInstallAdView".equals(s)) {
            s1 = "2009";
        }
        else {
            "com.google.android.gms.ads.formats.UnifiedNativeAdView".equals(s);
            s1 = "3012";
        }
        this.zzfqj = s1;
        zzbag.zza(frameLayout0, this);
        zzbag.zza(frameLayout0, this);
        this.zzfqn = zzazq.zzdxo;
        this.zzfqh = new zzpp(this.zzfqm.getContext(), this.zzfqm);
        frameLayout0.setOnTouchListener(this);
        frameLayout0.setOnClickListener(this);
    }

    @Override  // com.google.android.gms.internal.ads.zzacv
    public final void destroy() {
        synchronized(this) {
            if(this.zzehn) {
                return;
            }
            if(this.zzfqg != null) {
                this.zzfqg.zzb(this);
                this.zzfqg = null;
            }
            this.zzfql.clear();
            this.zzfqm.removeAllViews();
            this.zzbkk.removeAllViews();
            this.zzfql = null;
            this.zzfqm = null;
            this.zzbkk = null;
            this.zzfqo = null;
            this.zzfqh = null;
            this.zzehn = true;
        }
    }

    @Override  // android.view.View$OnClickListener
    public final void onClick(View view0) {
        synchronized(this) {
            if(this.zzfqg != null) {
                this.zzfqg.cancelUnconfirmedClick();
                this.zzfqg.zza(view0, this.zzfqm, this.zzalg(), this.zzalh(), false);
            }
        }
    }

    @Override  // android.view.ViewTreeObserver$OnGlobalLayoutListener
    public final void onGlobalLayout() {
        synchronized(this) {
            if(this.zzfqg != null) {
                this.zzfqg.zzb(this.zzfqm, this.zzalg(), this.zzalh(), zzbyo.zzy(this.zzfqm));
            }
        }
    }

    @Override  // android.view.ViewTreeObserver$OnScrollChangedListener
    public final void onScrollChanged() {
        synchronized(this) {
            if(this.zzfqg != null) {
                this.zzfqg.zzb(this.zzfqm, this.zzalg(), this.zzalh(), zzbyo.zzy(this.zzfqm));
            }
        }
    }

    @Override  // android.view.View$OnTouchListener
    public final boolean onTouch(View view0, MotionEvent motionEvent0) {
        synchronized(this) {
            if(this.zzfqg != null) {
                this.zzfqg.zza(view0, motionEvent0, this.zzfqm);
            }
            return false;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzacv
    public final void zza(IObjectWrapper iObjectWrapper0) {
        synchronized(this) {
            if(this.zzehn) {
                return;
            }
            Object object0 = ObjectWrapper.unwrap(iObjectWrapper0);
            if(!(object0 instanceof zzbyo)) {
                zzawf.zzfa("Not an instance of native engine. This is most likely a transient error");
                return;
            }
            if(this.zzfqg != null) {
                this.zzfqg.zzb(this);
            }
            this.zzall();
            this.zzfqg = (zzbyo)object0;
            this.zzfqg.zza(this);
            this.zzfqg.zzz(this.zzfqm);
            this.zzfqg.zzaa(this.zzbkk);
            if(this.zzfqq) {
                this.zzfqg.zzakb().zza(this.zzcfx);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzacv
    public final void zza(zzacm zzacm0) {
        synchronized(this) {
            if(this.zzehn) {
                return;
            }
            this.zzfqq = true;
            this.zzcfx = zzacm0;
            if(this.zzfqg != null) {
                this.zzfqg.zzakb().zza(zzacm0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    public final void zza(String s, View view0, boolean z) {
        synchronized(this) {
            if(!this.zzehn) {
                if(view0 == null) {
                    this.zzfql.remove(s);
                    return;
                }
                goto label_6;
            }
            return;
        }
        return;
    label_6:
        this.zzfql.put(s, new WeakReference(view0));
        if(!"1098".equals(s) && !"3011".equals(s)) {
            if(zzayl.zzcs(this.zzdxg)) {
                view0.setOnTouchListener(this);
            }
            view0.setClickable(true);
            view0.setOnClickListener(this);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    public final View zzagm() {
        return this.zzfqm;
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    public final FrameLayout zzale() {
        return this.zzbkk;
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    public final zzpp zzalf() {
        return this.zzfqh;
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    public final Map zzalg() {
        synchronized(this) {
        }
        return this.zzfql;
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    public final Map zzalh() {
        synchronized(this) {
        }
        return this.zzfql;
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    @Nullable
    public final Map zzali() {
        synchronized(this) {
        }
        return null;
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    public final String zzalj() {
        synchronized(this) {
        }
        return this.zzfqj;
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    @Nullable
    public final IObjectWrapper zzalk() {
        return this.zzfqp;
    }

    private final void zzall() {
        synchronized(this) {
            this.zzfqn.execute(() -> {
                if(this.zzfqo == null) {
                    this.zzfqo = new View(this.zzfqm.getContext());
                    this.zzfqo.setLayoutParams(new FrameLayout.LayoutParams(-1, 0));
                }
                if(this.zzfqm != this.zzfqo.getParent()) {
                    this.zzfqm.addView(this.zzfqo);
                }
            });
        }
    }

    // 检测为 Lambda 实现
    final void zzalm() [...]

    @Override  // com.google.android.gms.internal.ads.zzacv
    public final void zzb(String s, IObjectWrapper iObjectWrapper0) {
        synchronized(this) {
            this.zza(s, ((View)ObjectWrapper.unwrap(iObjectWrapper0)), true);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzacv
    public final void zzc(IObjectWrapper iObjectWrapper0, int v) {
        synchronized(this) {
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzacv
    public final IObjectWrapper zzcp(String s) {
        synchronized(this) {
            return ObjectWrapper.wrap(this.zzgc(s));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzacv
    public final void zze(IObjectWrapper iObjectWrapper0) {
        synchronized(this) {
            this.zzfqg.setClickConfirmingView(((View)ObjectWrapper.unwrap(iObjectWrapper0)));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzacv
    public final void zzf(IObjectWrapper iObjectWrapper0) {
        MotionEvent motionEvent0 = (MotionEvent)ObjectWrapper.unwrap(iObjectWrapper0);
        this.onTouch(this.zzfqm, motionEvent0);
    }

    @Override  // com.google.android.gms.internal.ads.zzacv
    public final void zzg(IObjectWrapper iObjectWrapper0) {
        synchronized(this) {
            if(this.zzehn) {
                return;
            }
            this.zzfqp = iObjectWrapper0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcal
    public final View zzgc(String s) {
        synchronized(this) {
            if(this.zzehn) {
                return null;
            }
            WeakReference weakReference0 = (WeakReference)this.zzfql.get(s);
            return weakReference0 == null ? null : ((View)weakReference0.get());
        }
    }
}

