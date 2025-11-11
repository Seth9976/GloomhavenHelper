package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;

public final class zzcch extends zzahm implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, zzaca {
    private boolean zzehn;
    private zzxj zzfpc;
    private View zzfph;
    private zzbyo zzfqg;
    private boolean zzftd;

    public zzcch(zzbyo zzbyo0, zzbyz zzbyz0) {
        this.zzfph = zzbyz0.zzaki();
        this.zzfpc = zzbyz0.getVideoController();
        this.zzfqg = zzbyo0;
        this.zzehn = false;
        this.zzftd = false;
        if(zzbyz0.zzakj() != null) {
            zzbyz0.zzakj().zza(this);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzahn
    public final void destroy() throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        this.zzalt();
        zzbyo zzbyo0 = this.zzfqg;
        if(zzbyo0 != null) {
            zzbyo0.destroy();
        }
        this.zzfqg = null;
        this.zzfph = null;
        this.zzfpc = null;
        this.zzehn = true;
    }

    @Override  // com.google.android.gms.internal.ads.zzahn
    public final zzxj getVideoController() throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if(this.zzehn) {
            zzawf.zzey("getVideoController: Instream ad should not be used after destroyed");
            return null;
        }
        return this.zzfpc;
    }

    @Override  // android.view.ViewTreeObserver$OnGlobalLayoutListener
    public final void onGlobalLayout() {
        this.zzalu();
    }

    @Override  // android.view.ViewTreeObserver$OnScrollChangedListener
    public final void onScrollChanged() {
        this.zzalu();
    }

    private static void zza(zzaho zzaho0, int v) {
        try {
            zzaho0.zzcn(v);
        }
        catch(RemoteException remoteException0) {
            zzawf.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzahn
    public final void zza(IObjectWrapper iObjectWrapper0, zzaho zzaho0) throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if(this.zzehn) {
            zzawf.zzey("Instream ad can not be shown after destroy().");
            zzcch.zza(zzaho0, 2);
            return;
        }
        if(this.zzfph != null && this.zzfpc != null) {
            if(this.zzftd) {
                zzawf.zzey("Instream ad should not be used again.");
                zzcch.zza(zzaho0, 1);
                return;
            }
            this.zzftd = true;
            this.zzalt();
            ((ViewGroup)ObjectWrapper.unwrap(iObjectWrapper0)).addView(this.zzfph, new ViewGroup.LayoutParams(-1, -1));
            zzbag.zza(this.zzfph, this);
            zzbag.zza(this.zzfph, this);
            this.zzalu();
            try {
                zzaho0.zzrz();
            }
            catch(RemoteException remoteException0) {
                zzawf.zze("#007 Could not call remote method.", remoteException0);
            }
            return;
        }
        String s = String.valueOf((this.zzfph == null ? "can not get video view." : "can not get video controller."));
        zzawf.zzey((s.length() == 0 ? new String("Instream internal error: ") : "Instream internal error: " + s));
        zzcch.zza(zzaho0, 0);
    }

    private final void zzalt() {
        View view0 = this.zzfph;
        if(view0 == null) {
            return;
        }
        ViewParent viewParent0 = view0.getParent();
        if(viewParent0 instanceof ViewGroup) {
            ((ViewGroup)viewParent0).removeView(this.zzfph);
        }
    }

    private final void zzalu() {
        zzbyo zzbyo0 = this.zzfqg;
        if(zzbyo0 != null) {
            View view0 = this.zzfph;
            if(view0 != null) {
                zzbyo0.zzb(view0, Collections.emptyMap(), Collections.emptyMap(), zzbyo.zzy(this.zzfph));
            }
        }
    }

    // 检测为 Lambda 实现
    final void zzalv() [...]

    @Override  // com.google.android.gms.internal.ads.zzahn
    public final void zzr(IObjectWrapper iObjectWrapper0) throws RemoteException {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        this.zza(iObjectWrapper0, new zzccj(this));
    }

    @Override  // com.google.android.gms.internal.ads.zzaca
    public final void zzrf() {
        zzccg zzccg0 = () -> try {
            this.destroy();
        }
        catch(RemoteException remoteException0) {
            zzawf.zze("#007 Could not call remote method.", remoteException0);
        };
        zzawo.zzdtx.post(zzccg0);
    }

    @Override  // com.google.android.gms.internal.ads.zzahn
    public final zzacm zzrv() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        if(this.zzehn) {
            zzawf.zzey("getVideoController: Instream ad should not be used after destroyed");
            return null;
        }
        return this.zzfqg == null || this.zzfqg.zzakb() == null ? null : this.zzfqg.zzakb().zzrv();
    }
}

