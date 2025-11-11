package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;

final class zzdjg implements BaseConnectionCallbacks, BaseOnConnectionFailedListener {
    private final Object lock;
    private boolean zzfyh;
    private boolean zzfyi;
    private final zzdjs zzgxa;
    private final zzdjn zzgxb;

    zzdjg(@NonNull Context context0, @NonNull Looper looper0, @NonNull zzdjn zzdjn0) {
        this.lock = new Object();
        this.zzfyh = false;
        this.zzfyi = false;
        this.zzgxb = zzdjn0;
        this.zzgxa = new zzdjs(context0, looper0, this, this);
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle0) {
        synchronized(this.lock) {
            if(this.zzfyi) {
                return;
            }
            try {
                this.zzfyi = true;
                this.zzgxa.zzatk().zza(new zzdjq(this.zzgxb.toByteArray()));
            }
            catch(Exception unused_ex) {
                this.zzans();
            }
            finally {
                this.zzans();
            }
        }
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult0) {
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks
    public final void onConnectionSuspended(int v) {
    }

    private final void zzans() {
        synchronized(this.lock) {
            if(this.zzgxa.isConnected() || this.zzgxa.isConnecting()) {
                this.zzgxa.disconnect();
            }
            Binder.flushPendingCommands();
        }
    }

    final void zzatf() {
        synchronized(this.lock) {
            if(!this.zzfyh) {
                this.zzfyh = true;
                this.zzgxa.checkAvailabilityAndConnect();
            }
        }
    }
}

