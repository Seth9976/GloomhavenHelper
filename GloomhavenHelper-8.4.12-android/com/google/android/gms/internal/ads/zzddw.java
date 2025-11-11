package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.concurrent.GuardedBy;

public final class zzddw extends zzasw {
    private final String zzbri;
    private final zzdep zzfkb;
    private final zzddq zzgoy;
    private final zzdct zzgoz;
    @Nullable
    @GuardedBy("this")
    private zzcdn zzgpa;

    public zzddw(@Nullable String s, zzddq zzddq0, zzdct zzdct0, zzdep zzdep0) {
        this.zzbri = s;
        this.zzgoy = zzddq0;
        this.zzgoz = zzdct0;
        this.zzfkb = zzdep0;
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final Bundle getAdMetadata() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzcdn zzcdn0 = this.zzgpa;
        return zzcdn0 == null ? new Bundle() : zzcdn0.getAdMetadata();
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final String getMediationAdapterClassName() throws RemoteException {
        synchronized(this) {
            return this.zzgpa != null && this.zzgpa.zzahi() != null ? this.zzgpa.zzahi().getMediationAdapterClassName() : null;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final boolean isLoaded() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        return this.zzgpa != null && !this.zzgpa.zzaly();
    }

    static zzcdn zza(zzddw zzddw0) {
        return zzddw0.zzgpa;
    }

    static zzcdn zza(zzddw zzddw0, zzcdn zzcdn0) {
        zzddw0.zzgpa = zzcdn0;
        return zzcdn0;
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(IObjectWrapper iObjectWrapper0, boolean z) throws RemoteException {
        synchronized(this) {
            Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
            if(this.zzgpa == null) {
                zzawf.zzfa("Rewarded can not be shown before loaded");
                this.zzgoz.zzco(2);
                return;
            }
            Activity activity0 = (Activity)ObjectWrapper.unwrap(iObjectWrapper0);
            this.zzgpa.zzb(z, activity0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(zzasy zzasy0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        this.zzgoz.zzb(zzasy0);
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(zzatg zzatg0) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        this.zzgoz.zzb(zzatg0);
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(zzato zzato0) {
        synchronized(this) {
            Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
            zzdep zzdep0 = this.zzfkb;
            zzdep0.zzdpa = zzato0.zzdpa;
            if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcjt)).booleanValue()) {
                zzdep0.zzdpb = zzato0.zzdpb;
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(zzuh zzuh0, zzatb zzatb0) throws RemoteException {
        synchronized(this) {
            Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
            this.zzgoz.zza(zzatb0);
            if(this.zzgpa != null) {
                return;
            }
            zzddn zzddn0 = new zzddn(null);
            this.zzgoy.zzaqq();
            zzddv zzddv0 = new zzddv(this);
            this.zzgoy.zza(zzuh0, this.zzbri, zzddn0, zzddv0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(zzwy zzwy0) {
        if(zzwy0 == null) {
            this.zzgoz.zza(null);
            return;
        }
        zzddy zzddy0 = new zzddy(this, zzwy0);
        this.zzgoz.zza(zzddy0);
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zza(zzxd zzxd0) {
        Preconditions.checkMainThread("setOnPaidEventListener must be called on the main UI thread.");
        this.zzgoz.zzc(zzxd0);
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final void zzh(IObjectWrapper iObjectWrapper0) throws RemoteException {
        synchronized(this) {
            this.zza(iObjectWrapper0, false);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    public final zzxe zzkg() {
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcsf)).booleanValue()) {
            return null;
        }
        zzcdn zzcdn0 = this.zzgpa;
        return zzcdn0 != null ? zzcdn0.zzahi() : null;
    }

    @Override  // com.google.android.gms.internal.ads.zzast
    @Nullable
    public final zzass zzqc() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        return this.zzgpa == null ? null : this.zzgpa.zzqc();
    }
}

