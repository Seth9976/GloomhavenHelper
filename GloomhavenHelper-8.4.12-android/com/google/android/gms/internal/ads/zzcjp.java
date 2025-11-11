package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.ConnectionResult;

public final class zzcjp extends zzcji {
    private String zzfyp;
    private int zzfyq;

    public zzcjp(Context context0) {
        this.zzfyq = zzcjq.zzfyr;
        this.zzfyk = new zzaqf(context0, zzq.zzlj().zzxg(), this, this);
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle0) {
        synchronized(this.mLock) {
            try {
                if(!this.zzfyi) {
                    try {
                        this.zzfyi = true;
                        if(this.zzfyq == zzcjq.zzfys) {
                            this.zzfyk.zzuc().zzc(this.zzfyj, new zzcjl(this));
                        }
                        else if(this.zzfyq == zzcjq.zzfyt) {
                            this.zzfyk.zzuc().zza(this.zzfyp, new zzcjl(this));
                        }
                        else {
                            this.zzdcg.setException(new zzcjv(0));
                        }
                    }
                    catch(RemoteException | IllegalArgumentException unused_ex) {
                        goto label_13;
                    }
                }
            }
            catch(Throwable throwable0) {
                zzq.zzkz().zza(throwable0, "RemoteUrlAndCacheKeyClientTask.onConnected");
                this.zzdcg.setException(new zzcjv(0));
            }
            return;
        }
    label_13:
        this.zzdcg.setException(new zzcjv(0));
    }

    @Override  // com.google.android.gms.internal.ads.zzcji
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult0) {
        zzawf.zzeb("Cannot connect to remote service, fallback to local instance.");
        this.zzdcg.setException(new zzcjv(0));
    }

    public final zzdof zzgh(String s) {
        synchronized(this.mLock) {
            if(this.zzfyq != zzcjq.zzfyr && this.zzfyq != zzcjq.zzfyt) {
                return zzdnt.immediateFailedFuture(new zzcjv(1));
            }
            if(this.zzfyh) {
                return this.zzdcg;
            }
            this.zzfyq = zzcjq.zzfyt;
            this.zzfyh = true;
            this.zzfyp = s;
            this.zzfyk.checkAvailabilityAndConnect();
            this.zzdcg.addListener(new zzcjr(this), zzazq.zzdxp);
            return this.zzdcg;
        }
    }

    public final zzdof zzi(zzaqx zzaqx0) {
        synchronized(this.mLock) {
            if(this.zzfyq != zzcjq.zzfyr && this.zzfyq != zzcjq.zzfys) {
                return zzdnt.immediateFailedFuture(new zzcjv(1));
            }
            if(this.zzfyh) {
                return this.zzdcg;
            }
            this.zzfyq = zzcjq.zzfys;
            this.zzfyh = true;
            this.zzfyj = zzaqx0;
            this.zzfyk.checkAvailabilityAndConnect();
            this.zzdcg.addListener(new zzcjo(this), zzazq.zzdxp);
            return this.zzdcg;
        }
    }
}

