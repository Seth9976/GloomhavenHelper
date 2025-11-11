package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.concurrent.GuardedBy;

public final class zzdec extends zzarx {
    private final zzdep zzfkb;
    @GuardedBy("this")
    private boolean zzggh;
    private final zzddq zzgoy;
    private final zzdct zzgoz;
    @Nullable
    @GuardedBy("this")
    private zzcdn zzgpa;

    public zzdec(zzddq zzddq0, zzdct zzdct0, zzdep zzdep0) {
        this.zzggh = false;
        this.zzgoy = zzddq0;
        this.zzgoz = zzdct0;
        this.zzfkb = zzdep0;
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void destroy() throws RemoteException {
        this.zzl(null);
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final Bundle getAdMetadata() {
        Preconditions.checkMainThread("getAdMetadata can only be called from the UI thread.");
        zzcdn zzcdn0 = this.zzgpa;
        return zzcdn0 == null ? new Bundle() : zzcdn0.getAdMetadata();
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final String getMediationAdapterClassName() throws RemoteException {
        synchronized(this) {
            return this.zzgpa != null && this.zzgpa.zzahi() != null ? this.zzgpa.zzahi().getMediationAdapterClassName() : null;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final boolean isLoaded() throws RemoteException {
        Preconditions.checkMainThread("isLoaded must be called on the main UI thread.");
        return this.zzaoo();
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void pause() {
        this.zzj(null);
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void resume() {
        this.zzk(null);
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void setAppPackageName(String s) throws RemoteException {
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void setCustomData(String s) throws RemoteException {
        synchronized(this) {
            if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcjt)).booleanValue()) {
                return;
            }
            Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setCustomData");
            this.zzfkb.zzdpb = s;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void setImmersiveMode(boolean z) {
        synchronized(this) {
            Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
            this.zzggh = z;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void setUserId(String s) throws RemoteException {
        synchronized(this) {
            Preconditions.checkMainThread("setUserId must be called on the main UI thread.");
            this.zzfkb.zzdpa = s;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void show() throws RemoteException {
        synchronized(this) {
            this.zzi(null);
        }
    }

    static zzcdn zza(zzdec zzdec0) {
        return zzdec0.zzgpa;
    }

    static zzcdn zza(zzdec zzdec0, zzcdn zzcdn0) {
        zzdec0.zzgpa = zzcdn0;
        return zzcdn0;
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zza(zzarw zzarw0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setRewardedAdSkuListener");
        this.zzgoz.zzb(zzarw0);
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zza(zzasb zzasb0) throws RemoteException {
        Preconditions.checkMainThread("setRewardedVideoAdListener can only be called from the UI thread.");
        this.zzgoz.zzb(zzasb0);
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zza(zzash zzash0) throws RemoteException {
        synchronized(this) {
            Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
            if(zzzz.zzcq(zzash0.zzbri)) {
                return;
            }
            if(this.zzaoo() && !((Boolean)zzvh.zzpd().zzd(zzzx.zzcpo)).booleanValue()) {
                return;
            }
            zzddn zzddn0 = new zzddn(null);
            this.zzgpa = null;
            zzdeb zzdeb0 = new zzdeb(this);
            this.zzgoy.zza(zzash0.zzdjt, zzash0.zzbri, zzddn0, zzdeb0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zza(zzwa zzwa0) {
        Preconditions.checkMainThread("setAdMetadataListener can only be called from the UI thread.");
        if(zzwa0 == null) {
            this.zzgoz.zza(null);
            return;
        }
        zzdee zzdee0 = new zzdee(this, zzwa0);
        this.zzgoz.zza(zzdee0);
    }

    private final boolean zzaoo() {
        synchronized(this) {
            return this.zzgpa != null && !this.zzgpa.isClosed();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zzi(@Nullable IObjectWrapper iObjectWrapper0) throws RemoteException {
        Activity activity0;
        synchronized(this) {
            Preconditions.checkMainThread("showAd must be called on the main UI thread.");
            if(this.zzgpa == null) {
                return;
            }
            if(iObjectWrapper0 == null) {
                activity0 = null;
            }
            else {
                Object object0 = ObjectWrapper.unwrap(iObjectWrapper0);
                activity0 = object0 instanceof Activity ? ((Activity)object0) : null;
            }
            this.zzgpa.zzb(this.zzggh, activity0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zzj(IObjectWrapper iObjectWrapper0) {
        synchronized(this) {
            Preconditions.checkMainThread("pause must be called on the main UI thread.");
            if(this.zzgpa != null) {
                Context context0 = iObjectWrapper0 == null ? null : ((Context)ObjectWrapper.unwrap(iObjectWrapper0));
                this.zzgpa.zzahh().zzby(context0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zzk(IObjectWrapper iObjectWrapper0) {
        synchronized(this) {
            Preconditions.checkMainThread("resume must be called on the main UI thread.");
            if(this.zzgpa != null) {
                Context context0 = iObjectWrapper0 == null ? null : ((Context)ObjectWrapper.unwrap(iObjectWrapper0));
                this.zzgpa.zzahh().zzbz(context0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final zzxe zzkg() throws RemoteException {
        synchronized(this) {
            if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcsf)).booleanValue()) {
                return null;
            }
            return this.zzgpa != null ? this.zzgpa.zzahi() : null;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final void zzl(IObjectWrapper iObjectWrapper0) {
        synchronized(this) {
            Preconditions.checkMainThread("destroy must be called on the main UI thread.");
            Context context0 = null;
            this.zzgoz.zza(null);
            if(this.zzgpa != null) {
                if(iObjectWrapper0 != null) {
                    context0 = (Context)ObjectWrapper.unwrap(iObjectWrapper0);
                }
                this.zzgpa.zzahh().zzca(context0);
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzary
    public final boolean zzqd() {
        return this.zzgpa != null && this.zzgpa.zzqd();
    }
}

