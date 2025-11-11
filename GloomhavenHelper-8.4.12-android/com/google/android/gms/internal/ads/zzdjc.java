package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@VisibleForTesting
final class zzdjc implements BaseConnectionCallbacks, BaseOnConnectionFailedListener {
    private final String packageName;
    private final HandlerThread zzdvv;
    @VisibleForTesting
    private zzdjs zzgwu;
    private final String zzgwv;
    private final LinkedBlockingQueue zzgww;

    public zzdjc(Context context0, String s, String s1) {
        this.packageName = s;
        this.zzgwv = s1;
        this.zzdvv = new HandlerThread("GassClient");
        this.zzdvv.start();
        this.zzgwu = new zzdjs(context0, this.zzdvv.getLooper(), this, this);
        this.zzgww = new LinkedBlockingQueue();
        this.zzgwu.checkAvailabilityAndConnect();
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public final void onConnected(Bundle bundle0) {
        zzdjz zzdjz0 = this.zzatc();
        if(zzdjz0 != null) {
            try {
                zza zzbs$zza0 = zzdjz0.zza(new zzdjv(this.packageName, this.zzgwv)).zzatl();
                this.zzgww.put(zzbs$zza0);
            }
            catch(Throwable unused_ex) {
                try {
                    zza zzbs$zza1 = zzdjc.zzatd();
                    this.zzgww.put(zzbs$zza1);
                }
                catch(InterruptedException unused_ex) {
                }
            }
            finally {
                this.zzans();
                this.zzdvv.quit();
            }
        }
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult0) {
        try {
            zza zzbs$zza0 = zzdjc.zzatd();
            this.zzgww.put(zzbs$zza0);
        }
        catch(InterruptedException unused_ex) {
        }
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public final void onConnectionSuspended(int v) {
        try {
            zza zzbs$zza0 = zzdjc.zzatd();
            this.zzgww.put(zzbs$zza0);
        }
        catch(InterruptedException unused_ex) {
        }
    }

    private final void zzans() {
        if(this.zzgwu != null && (this.zzgwu.isConnected() || this.zzgwu.isConnecting())) {
            this.zzgwu.disconnect();
        }
    }

    private final zzdjz zzatc() {
        try {
            return this.zzgwu.zzatk();
        }
        catch(IllegalStateException | DeadObjectException unused_ex) {
            return null;
        }
    }

    @VisibleForTesting
    private static zza zzatd() {
        return (zza)(((zzdyz)zza.zzao().zzau(0x8000L).zzbcx()));
    }

    public final zza zzdn(int v) {
        zza zzbs$zza0 = null;
        try {
            zzbs$zza0 = (zza)this.zzgww.poll(5000L, TimeUnit.MILLISECONDS);
        }
        catch(InterruptedException unused_ex) {
        }
        return zzbs$zza0 == null ? zzdjc.zzatd() : zzbs$zza0;
    }
}

