package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;

public final class zzahz {
    private final zzui zzabf;
    private final zzvp zzabg;
    private final Context zzur;

    zzahz(Context context0, zzvp zzvp0) {
        this(context0, zzvp0, zzui.zzcdb);
    }

    private zzahz(Context context0, zzvp zzvp0, zzui zzui0) {
        this.zzur = context0;
        this.zzabg = zzvp0;
        this.zzabf = zzui0;
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(AdRequest adRequest0) {
        this.zza(adRequest0.zzdl());
    }

    @RequiresPermission("android.permission.INTERNET")
    public final void loadAd(PublisherAdRequest publisherAdRequest0) {
        this.zza(publisherAdRequest0.zzdl());
    }

    private final void zza(zzxr zzxr0) {
        try {
            zzuh zzuh0 = zzui.zza(this.zzur, zzxr0);
            this.zzabg.zzb(zzuh0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }
}

