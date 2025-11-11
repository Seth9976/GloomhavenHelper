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
final class zzdje implements BaseConnectionCallbacks, BaseOnConnectionFailedListener {
    private final long startTime;
    private final HandlerThread zzdvv;
    @VisibleForTesting
    private zzdjs zzgwu;
    private final LinkedBlockingQueue zzgww;
    private final String zzgwx;
    private final String zzgwy;
    private final int zzgwz;
    private final zzdix zzuu;

    public zzdje(Context context0, int v, String s, String s1, String s2, zzdix zzdix0) {
        this.zzgwx = s;
        this.zzgwz = 1;
        this.zzgwy = s1;
        this.zzuu = zzdix0;
        this.zzdvv = new HandlerThread("GassDGClient");
        this.zzdvv.start();
        this.startTime = System.currentTimeMillis();
        this.zzgwu = new zzdjs(context0, this.zzdvv.getLooper(), this, this);
        this.zzgww = new LinkedBlockingQueue();
        this.zzgwu.checkAvailabilityAndConnect();
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public final void onConnected(Bundle bundle0) {
        zzdjz zzdjz0 = this.zzatc();
        if(zzdjz0 != null) {
            try {
                zzdke zzdke0 = zzdjz0.zza(new zzdkc(this.zzgwz, this.zzgwx, this.zzgwy));
                this.zzgww.put(zzdke0);
            }
            catch(Throwable throwable0) {
                Exception exception0 = new Exception(throwable0);
                this.zzb(2010, this.startTime, exception0);
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
            zzdke zzdke0 = zzdje.zzate();
            this.zzgww.put(zzdke0);
        }
        catch(InterruptedException unused_ex) {
        }
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public final void onConnectionSuspended(int v) {
        try {
            zzdke zzdke0 = zzdje.zzate();
            this.zzgww.put(zzdke0);
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
    private static zzdke zzate() {
        return new zzdke(null, 1);
    }

    private final void zzb(int v, long v1, Exception exception0) {
        zzdix zzdix0 = this.zzuu;
        if(zzdix0 != null) {
            zzdix0.zza(v, System.currentTimeMillis() - v1, exception0);
        }
    }

    public final zzdke zzdo(int v) {
        zzdke zzdke0;
        try {
            zzdke0 = (zzdke)this.zzgww.poll(50000L, TimeUnit.MILLISECONDS);
        }
        catch(InterruptedException interruptedException0) {
            this.zzb(2009, this.startTime, interruptedException0);
            zzdke0 = null;
        }
        this.zzb(3004, this.startTime, null);
        return zzdke0 == null ? zzdje.zzate() : zzdke0;
    }
}

