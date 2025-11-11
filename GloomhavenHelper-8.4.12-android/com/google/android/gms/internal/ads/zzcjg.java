package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.ConnectionResult;

public final class zzcjg extends zzcji {
    public zzcjg(Context context0) {
        this.zzfyk = new zzaqf(context0, zzq.zzlj().zzxg(), this, this);
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public final void onConnected(Bundle bundle0) {
        synchronized(this.mLock) {
            try {
                if(!this.zzfyi) {
                    try {
                        this.zzfyi = true;
                        this.zzfyk.zzuc().zza(this.zzfyj, new zzcjl(this));
                        return;
                    }
                    catch(RemoteException | IllegalArgumentException unused_ex) {
                    }
                    goto label_7;
                }
            }
            catch(Throwable throwable0) {
                zzq.zzkz().zza(throwable0, "RemoteAdRequestClientTask.onConnected");
                this.zzdcg.setException(new zzcjv(0));
            }
            return;
        }
    label_7:
        this.zzdcg.setException(new zzcjv(0));
    }

    @Override  // com.google.android.gms.internal.ads.zzcji
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult0) {
        zzawf.zzeb("Cannot connect to remote service, fallback to local instance.");
        this.zzdcg.setException(new zzcjv(0));
    }

    public final zzdof zzg(zzaqx zzaqx0) {
        synchronized(this.mLock) {
            if(this.zzfyh) {
                return this.zzdcg;
            }
            this.zzfyh = true;
            this.zzfyj = zzaqx0;
            this.zzfyk.checkAvailabilityAndConnect();
            this.zzdcg.addListener(new zzcjj(this), zzazq.zzdxp);
            return this.zzdcg;
        }
    }
}

