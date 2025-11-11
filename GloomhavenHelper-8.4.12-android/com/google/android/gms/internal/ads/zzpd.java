package com.google.android.gms.internal.ads;

import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer.FrameCallback;
import android.view.Choreographer;

final class zzpd implements Handler.Callback, Choreographer.FrameCallback {
    private final zzdkp zzadx;
    public volatile long zzbiy;
    private static final zzpd zzbiz;
    private final HandlerThread zzbja;
    private Choreographer zzbjb;
    private int zzbjc;

    static {
        zzpd.zzbiz = new zzpd();
    }

    private zzpd() {
        this.zzbja = new HandlerThread("ChoreographerOwner:Handler");
        this.zzbja.start();
        this.zzadx = new zzdkp(this.zzbja.getLooper(), this);
        this.zzadx.sendEmptyMessage(0);
    }

    @Override  // android.view.Choreographer$FrameCallback
    public final void doFrame(long v) {
        this.zzbiy = v;
        this.zzbjb.postFrameCallbackDelayed(this, 500L);
    }

    @Override  // android.os.Handler$Callback
    public final boolean handleMessage(Message message0) {
        switch(message0.what) {
            case 0: {
                this.zzbjb = Choreographer.getInstance();
                return true;
            }
            case 1: {
                ++this.zzbjc;
                if(this.zzbjc == 1) {
                    this.zzbjb.postFrameCallback(this);
                }
                return true;
            }
            case 2: {
                --this.zzbjc;
                if(this.zzbjc == 0) {
                    this.zzbjb.removeFrameCallback(this);
                    this.zzbiy = 0L;
                }
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static zzpd zzjl() {
        return zzpd.zzbiz;
    }

    public final void zzjm() {
        this.zzadx.sendEmptyMessage(1);
    }

    public final void zzjn() {
        this.zzadx.sendEmptyMessage(2);
    }
}

