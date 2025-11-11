package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.instream.InstreamAd.InstreamAdLoadCallback;
import com.google.android.gms.common.internal.Preconditions;

public final class zzahy {
    private final zzvq zzabe;
    private final Context zzur;

    private zzahy(Context context0, zzvq zzvq0) {
        this.zzur = context0;
        this.zzabe = zzvq0;
    }

    public zzahy(Context context0, String s) {
        this(((Context)Preconditions.checkNotNull(context0, "context cannot be null")), zzvh.zzpa().zzb(context0, s, new zzall()));
    }

    public final zzahy zza(InstreamAdLoadCallback instreamAd$InstreamAdLoadCallback0) {
        try {
            zzahw zzahw0 = new zzahw(instreamAd$InstreamAdLoadCallback0);
            this.zzabe.zza(zzahw0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
        return this;
    }

    public final zzahy zza(zzahx zzahx0) {
        try {
            zzahl zzahl0 = new zzahl(zzahx0);
            this.zzabe.zza(zzahl0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
        return this;
    }

    public final zzahz zzsd() {
        try {
            zzvp zzvp0 = this.zzabe.zzpi();
            return new zzahz(this.zzur, zzvp0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
            return null;
        }
    }
}

