package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import android.webkit.WebView;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.concurrent.GuardedBy;
import jeb.synthetic.FIN;

public final class zzaoy {
    private static final Object lock;
    @VisibleForTesting
    @GuardedBy("lock")
    private static boolean zzdhn;
    @VisibleForTesting
    private zzdit zzdho;
    @VisibleForTesting
    @GuardedBy("lock")
    private static boolean zzyb;

    static {
        zzaoy.lock = new Object();
        zzaoy.zzyb = false;
        zzaoy.zzdhn = false;
    }

    @Nullable
    public final String getVersion(Context context0) {
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcoz)).booleanValue()) {
            return null;
        }
        try {
            this.zzr(context0);
            String s = this.zzdho.getVersion();
            return s.length() == 0 ? new String("a.") : "a." + s;
        }
        catch(RemoteException | NullPointerException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
            return null;
        }
    }

    @Nullable
    public final IObjectWrapper zza(String s, WebView webView0, String s1, String s2, String s3) {
        return this.zza(s, webView0, s1, s2, s3, "Google");
    }

    @Nullable
    public final IObjectWrapper zza(String s, WebView webView0, String s1, String s2, String s3, String s4) {
        synchronized(zzaoy.lock) {
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcoz)).booleanValue() && zzaoy.zzyb) {
                try {
                    return this.zzdho.zza(s, ObjectWrapper.wrap(webView0), s1, s2, s3, s4);
                }
                catch(RemoteException | NullPointerException remoteException0) {
                    zzazh.zze("#007 Could not call remote method.", remoteException0);
                    return null;
                }
            }
            return null;
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper0, View view0) {
        synchronized(zzaoy.lock) {
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcoz)).booleanValue() && zzaoy.zzyb) {
                try {
                    this.zzdho.zzc(iObjectWrapper0, ObjectWrapper.wrap(view0));
                }
                catch(RemoteException | NullPointerException remoteException0) {
                    zzazh.zze("#007 Could not call remote method.", remoteException0);
                }
            }
        }
    }

    public final void zzab(IObjectWrapper iObjectWrapper0) {
        synchronized(zzaoy.lock) {
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcoz)).booleanValue() && zzaoy.zzyb) {
                try {
                    this.zzdho.zzab(iObjectWrapper0);
                }
                catch(RemoteException | NullPointerException remoteException0) {
                    zzazh.zze("#007 Could not call remote method.", remoteException0);
                }
            }
        }
    }

    public final void zzac(IObjectWrapper iObjectWrapper0) {
        synchronized(zzaoy.lock) {
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcoz)).booleanValue() && zzaoy.zzyb) {
                try {
                    this.zzdho.zzac(iObjectWrapper0);
                }
                catch(RemoteException | NullPointerException remoteException0) {
                    zzazh.zze("#007 Could not call remote method.", remoteException0);
                }
            }
        }
    }

    public final void zzb(IObjectWrapper iObjectWrapper0, View view0) {
        synchronized(zzaoy.lock) {
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcoz)).booleanValue() && zzaoy.zzyb) {
                try {
                    this.zzdho.zzd(iObjectWrapper0, ObjectWrapper.wrap(view0));
                }
                catch(RemoteException | NullPointerException remoteException0) {
                    zzazh.zze("#007 Could not call remote method.", remoteException0);
                }
            }
        }
    }

    public final boolean zzq(Context context0) {
        Object object0 = zzaoy.lock;
        __monitor_enter(object0);
        int v = FIN.finallyOpen$NT();
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcoz)).booleanValue()) {
            FIN.finallyCodeBegin$NT(v);
            __monitor_exit(object0);
            FIN.finallyCodeEnd$NT(v);
            return false;
        }
        if(zzaoy.zzyb) {
            FIN.finallyExec$NT(v);
            return true;
        }
        try {
            this.zzr(context0);
            boolean z = this.zzdho.zzau(ObjectWrapper.wrap(context0));
            zzaoy.zzyb = z;
            FIN.finallyExec$NT(v);
            return z;
        }
        catch(RemoteException | NullPointerException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
            FIN.finallyExec$NT(v);
            return false;
        }
    }

    @VisibleForTesting
    private final void zzr(Context context0) {
        synchronized(zzaoy.lock) {
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcoz)).booleanValue() && !zzaoy.zzdhn) {
                try {
                    zzaoy.zzdhn = true;
                    this.zzdho = (zzdit)zzazk.zza(context0, "com.google.android.gms.ads.omid.DynamiteOmid", zzapa.zzbun);
                }
                catch(zzazm zzazm0) {
                    zzazh.zze("#007 Could not call remote method.", zzazm0);
                }
            }
        }
    }
}

