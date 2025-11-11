package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import java.util.HashSet;
import java.util.Set;

final class zzf implements ServiceConnection {
    private ComponentName mComponentName;
    private int mState;
    private IBinder zzcz;
    private final Set zzdz;
    private boolean zzea;
    private final zza zzeb;
    private final zze zzec;

    public zzf(zze zze0, zza gmsClientSupervisor$zza0) {
        this.zzec = zze0;
        super();
        this.zzeb = gmsClientSupervisor$zza0;
        this.zzdz = new HashSet();
        this.mState = 2;
    }

    public final IBinder getBinder() {
        return this.zzcz;
    }

    public final ComponentName getComponentName() {
        return this.mComponentName;
    }

    public final int getState() {
        return this.mState;
    }

    public final boolean isBound() {
        return this.zzea;
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
        synchronized(this.zzec.zzdu) {
            this.zzec.mHandler.removeMessages(1, this.zzeb);
            this.zzcz = iBinder0;
            this.mComponentName = componentName0;
            for(Object object0: this.zzdz) {
                ((ServiceConnection)object0).onServiceConnected(componentName0, iBinder0);
            }
            this.mState = 1;
        }
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName0) {
        synchronized(this.zzec.zzdu) {
            this.zzec.mHandler.removeMessages(1, this.zzeb);
            this.zzcz = null;
            this.mComponentName = componentName0;
            for(Object object0: this.zzdz) {
                ((ServiceConnection)object0).onServiceDisconnected(componentName0);
            }
            this.mState = 2;
        }
    }

    public final void zza(ServiceConnection serviceConnection0, String s) {
        this.zzeb.zzb(this.zzec.zzdv);
        this.zzdz.add(serviceConnection0);
    }

    public final boolean zza(ServiceConnection serviceConnection0) {
        return this.zzdz.contains(serviceConnection0);
    }

    public final void zzb(ServiceConnection serviceConnection0, String s) {
        this.zzdz.remove(serviceConnection0);
    }

    public final void zze(String s) {
        this.mState = 3;
        Intent intent0 = this.zzeb.zzb(this.zzec.zzdv);
        this.zzea = this.zzec.zzdw.zza(this.zzec.zzdv, s, intent0, this, this.zzeb.zzq());
        if(this.zzea) {
            Message message0 = this.zzec.mHandler.obtainMessage(1, this.zzeb);
            this.zzec.mHandler.sendMessageDelayed(message0, this.zzec.zzdy);
            return;
        }
        try {
            this.mState = 2;
            this.zzec.zzdw.unbindService(this.zzec.zzdv, this);
        }
        catch(IllegalArgumentException unused_ex) {
        }
    }

    public final void zzf(String s) {
        this.zzec.mHandler.removeMessages(1, this.zzeb);
        this.zzec.zzdw.unbindService(this.zzec.zzdv, this);
        this.zzea = false;
        this.mState = 2;
    }

    public final boolean zzr() {
        return this.zzdz.isEmpty();
    }
}

