package com.android.billingclient.api;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import com.google.android.gms.internal.play_billing.zza;
import com.google.android.gms.internal.play_billing.zzc;
import com.google.android.gms.internal.play_billing.zzd;

final class zzaf implements ServiceConnection {
    final BillingClientImpl zza;
    private final Object zzb;
    private boolean zzc;
    private BillingClientStateListener zzd;

    zzaf(BillingClientImpl billingClientImpl0, BillingClientStateListener billingClientStateListener0, zzy zzy0) {
        this.zza = billingClientImpl0;
        super();
        this.zzb = new Object();
        this.zzc = false;
        this.zzd = billingClientStateListener0;
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
        zza.zzj("BillingClient", "Billing service connected.");
        zzd zzd0 = zzc.zzn(iBinder0);
        this.zza.zzg = zzd0;
        zzae zzae0 = new zzae(this);
        zzad zzad0 = () -> {
            this.zza.zza = 0;
            this.zza.zzg = null;
            this.zzd(zzak.zzr);
        };
        Handler handler0 = this.zza.zzD();
        if(BillingClientImpl.zzr(this.zza, zzae0, 30000L, zzad0, handler0) == null) {
            this.zzd(this.zza.zzF());
        }
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName0) {
        zza.zzk("BillingClient", "Billing service disconnected.");
        this.zza.zzg = null;
        this.zza.zza = 0;
        synchronized(this.zzb) {
            BillingClientStateListener billingClientStateListener0 = this.zzd;
            if(billingClientStateListener0 != null) {
                billingClientStateListener0.onBillingServiceDisconnected();
            }
        }
    }

    public final Object zza() throws Exception {
        int v2;
        synchronized(this.zzb) {
            if(this.zzc) {
                return null;
            }
        }
        int v = 3;
        int v1 = 16;
        try {
            while(true) {
                if(v1 < 3) {
                    v1 = 0;
                    break;
                }
                v = this.zza.zzg.zzb(v1, "com.esotericsoftware.gloomhavenhelper", "subs");
                if(v == 0) {
                    break;
                }
                --v1;
            }
            boolean z = true;
            this.zza.zzj = v1 >= 5;
            this.zza.zzi = v1 >= 3;
            if(v1 < 3) {
                zza.zzj("BillingClient", "In-app billing API does not support subscription on this device.");
            }
            v2 = 16;
            while(v2 >= 3) {
                v = this.zza.zzg.zzb(v2, "com.esotericsoftware.gloomhavenhelper", "inapp");
                if(v == 0) {
                    this.zza.zzk = v2;
                    if(true) {
                        break;
                    }
                }
                else {
                    --v2;
                }
            }
            this.zza.zzs = this.zza.zzk >= 16;
            this.zza.zzr = this.zza.zzk >= 15;
            this.zza.zzq = this.zza.zzk >= 14;
            this.zza.zzp = this.zza.zzk >= 12;
            this.zza.zzo = this.zza.zzk >= 10;
            this.zza.zzn = this.zza.zzk >= 9;
            this.zza.zzm = this.zza.zzk >= 8;
            BillingClientImpl billingClientImpl0 = this.zza;
            if(billingClientImpl0.zzk < 6) {
                z = false;
            }
            billingClientImpl0.zzl = z;
            if(this.zza.zzk < 3) {
                zza.zzk("BillingClient", "In-app billing API version 3 is not supported on this device.");
            }
            if(v == 0) {
                this.zza.zza = 2;
            }
            else {
                this.zza.zza = 0;
                this.zza.zzg = null;
            }
        }
        catch(Exception unused_ex) {
            zza.zzk("BillingClient", "Exception while checking if billing is supported; try to reconnect");
            this.zza.zza = 0;
            this.zza.zzg = null;
        }
        if(v == 0) {
            this.zzd(zzak.zzp);
            return null;
        }
        this.zzd(zzak.zza);
        return null;
    }

    // 检测为 Lambda 实现
    public final void zzb() [...]

    final void zzc() {
        synchronized(this.zzb) {
            this.zzd = null;
            this.zzc = true;
        }
    }

    private final void zzd(BillingResult billingResult0) {
        synchronized(this.zzb) {
            BillingClientStateListener billingClientStateListener0 = this.zzd;
            if(billingClientStateListener0 != null) {
                billingClientStateListener0.onBillingSetupFinished(billingResult0);
            }
        }
    }
}

