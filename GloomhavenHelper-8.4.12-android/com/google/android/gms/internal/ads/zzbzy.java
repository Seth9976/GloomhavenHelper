package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View.OnClickListener;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.util.Clock;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public final class zzbzy implements View.OnClickListener {
    private final Clock zzbmz;
    private final zzccv zzfqy;
    @Nullable
    private zzaem zzfqz;
    @Nullable
    private zzafz zzfra;
    @Nullable
    @VisibleForTesting
    String zzfrb;
    @Nullable
    @VisibleForTesting
    Long zzfrc;
    @Nullable
    @VisibleForTesting
    WeakReference zzfrd;

    public zzbzy(zzccv zzccv0, Clock clock0) {
        this.zzfqy = zzccv0;
        this.zzbmz = clock0;
    }

    public final void cancelUnconfirmedClick() {
        if(this.zzfqz == null) {
            return;
        }
        if(this.zzfrc == null) {
            return;
        }
        this.zzalo();
        try {
            this.zzfqz.onUnconfirmedClickCancelled();
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // android.view.View$OnClickListener
    public final void onClick(View view0) {
        if(this.zzfrd != null && this.zzfrd.get() == view0) {
            if(this.zzfrb != null && this.zzfrc != null) {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("id", this.zzfrb);
                hashMap0.put("time_interval", String.valueOf(this.zzbmz.currentTimeMillis() - ((long)this.zzfrc)));
                hashMap0.put("messageType", "onePointFiveClick");
                this.zzfqy.zza("sendMessageToNativeJs", hashMap0);
            }
            this.zzalo();
        }
    }

    public final void zza(zzaem zzaem0) {
        this.zzfqz = zzaem0;
        zzafz zzafz0 = this.zzfra;
        if(zzafz0 != null) {
            this.zzfqy.zzb("/unconfirmedClick", zzafz0);
        }
        this.zzfra = new zzcab(this, zzaem0);
        this.zzfqy.zza("/unconfirmedClick", this.zzfra);
    }

    @Nullable
    public final zzaem zzaln() {
        return this.zzfqz;
    }

    private final void zzalo() {
        this.zzfrb = null;
        this.zzfrc = null;
        WeakReference weakReference0 = this.zzfrd;
        if(weakReference0 == null) {
            return;
        }
        View view0 = (View)weakReference0.get();
        if(view0 == null) {
            return;
        }
        view0.setClickable(false);
        view0.setOnClickListener(null);
        this.zzfrd = null;
    }
}

