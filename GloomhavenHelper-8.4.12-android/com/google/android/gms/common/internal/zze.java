package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler.Callback;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.HashMap;
import javax.annotation.concurrent.GuardedBy;

final class zze extends GmsClientSupervisor implements Handler.Callback {
    private final Handler mHandler;
    @GuardedBy("mConnectionStatus")
    private final HashMap zzdu;
    private final Context zzdv;
    private final ConnectionTracker zzdw;
    private final long zzdx;
    private final long zzdy;

    zze(Context context0) {
        this.zzdu = new HashMap();
        this.zzdv = context0.getApplicationContext();
        this.mHandler = new com.google.android.gms.internal.common.zze(context0.getMainLooper(), this);
        this.zzdw = ConnectionTracker.getInstance();
        this.zzdx = 5000L;
        this.zzdy = 300000L;
    }

    @Override  // android.os.Handler$Callback
    public final boolean handleMessage(Message message0) {
        switch(message0.what) {
            case 0: {
                HashMap hashMap0 = this.zzdu;
                synchronized(hashMap0) {
                    zza gmsClientSupervisor$zza0 = (zza)message0.obj;
                    zzf zzf0 = (zzf)this.zzdu.get(gmsClientSupervisor$zza0);
                    if(zzf0 != null && zzf0.zzr()) {
                        if(zzf0.isBound()) {
                            zzf0.zzf("GmsClientSupervisor");
                        }
                        this.zzdu.remove(gmsClientSupervisor$zza0);
                    }
                    return true;
                }
            }
            case 1: {
                HashMap hashMap1 = this.zzdu;
                synchronized(hashMap1) {
                    zza gmsClientSupervisor$zza1 = (zza)message0.obj;
                    zzf zzf1 = (zzf)this.zzdu.get(gmsClientSupervisor$zza1);
                    if(zzf1 != null && zzf1.getState() == 3) {
                        Log.e("GmsClientSupervisor", "Timeout waiting for ServiceConnection callback " + gmsClientSupervisor$zza1, new Exception());
                        ComponentName componentName0 = zzf1.getComponentName() == null ? gmsClientSupervisor$zza1.getComponentName() : zzf1.getComponentName();
                        if(componentName0 == null) {
                            componentName0 = new ComponentName(gmsClientSupervisor$zza1.getPackage(), "unknown");
                        }
                        zzf1.onServiceDisconnected(componentName0);
                    }
                    return true;
                }
            }
            default: {
                return false;
            }
        }
    }

    @Override  // com.google.android.gms.common.internal.GmsClientSupervisor
    protected final boolean zza(zza gmsClientSupervisor$zza0, ServiceConnection serviceConnection0, String s) {
        Preconditions.checkNotNull(serviceConnection0, "ServiceConnection must not be null");
        synchronized(this.zzdu) {
            zzf zzf0 = (zzf)this.zzdu.get(gmsClientSupervisor$zza0);
            if(zzf0 == null) {
                zzf0 = new zzf(this, gmsClientSupervisor$zza0);
                zzf0.zza(serviceConnection0, s);
                zzf0.zze(s);
                this.zzdu.put(gmsClientSupervisor$zza0, zzf0);
                return zzf0.isBound();
            }
            this.mHandler.removeMessages(0, gmsClientSupervisor$zza0);
            if(!zzf0.zza(serviceConnection0)) {
                zzf0.zza(serviceConnection0, s);
                switch(zzf0.getState()) {
                    case 1: {
                        serviceConnection0.onServiceConnected(zzf0.getComponentName(), zzf0.getBinder());
                        break;
                    }
                    case 2: {
                        zzf0.zze(s);
                    }
                }
                return zzf0.isBound();
            }
        }
        throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=" + gmsClientSupervisor$zza0);
    }

    @Override  // com.google.android.gms.common.internal.GmsClientSupervisor
    protected final void zzb(zza gmsClientSupervisor$zza0, ServiceConnection serviceConnection0, String s) {
        Preconditions.checkNotNull(serviceConnection0, "ServiceConnection must not be null");
        synchronized(this.zzdu) {
            zzf zzf0 = (zzf)this.zzdu.get(gmsClientSupervisor$zza0);
            if(zzf0 != null) {
                if(!zzf0.zza(serviceConnection0)) {
                    throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + gmsClientSupervisor$zza0);
                }
                zzf0.zzb(serviceConnection0, s);
                if(zzf0.zzr()) {
                    Message message0 = this.mHandler.obtainMessage(0, gmsClientSupervisor$zza0);
                    this.mHandler.sendMessageDelayed(message0, this.zzdx);
                }
                return;
            }
        }
        throw new IllegalStateException("Nonexistent connection status for service config: " + gmsClientSupervisor$zza0);
    }
}

