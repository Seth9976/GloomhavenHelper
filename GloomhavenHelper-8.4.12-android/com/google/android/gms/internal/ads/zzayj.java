package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;

public final class zzayj {
    private Handler handler;
    private final Object lock;
    private HandlerThread zzdvv;
    private int zzdvw;

    public zzayj() {
        this.zzdvv = null;
        this.handler = null;
        this.zzdvw = 0;
        this.lock = new Object();
    }

    public final Handler getHandler() {
        return this.handler;
    }

    public final Looper zzxg() {
        synchronized(this.lock) {
            if(this.zzdvw != 0) {
                Preconditions.checkNotNull(this.zzdvv, "Invalid state: mHandlerThread should already been initialized.");
            }
            else if(this.zzdvv == null) {
                zzawf.zzee("Starting the looper thread.");
                this.zzdvv = new HandlerThread("LooperProvider");
                this.zzdvv.start();
                this.handler = new zzdkp(this.zzdvv.getLooper());
                zzawf.zzee("Looper thread started.");
            }
            else {
                zzawf.zzee("Resuming the looper thread");
                this.lock.notifyAll();
            }
            ++this.zzdvw;
        }
        return this.zzdvv.getLooper();
    }
}

