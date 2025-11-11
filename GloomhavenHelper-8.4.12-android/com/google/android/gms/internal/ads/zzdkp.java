package com.google.android.gms.internal.ads;

import android.os.Handler.Callback;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.CallSuper;

public class zzdkp extends Handler {
    private static zzdko zzgyo;

    static {
    }

    public zzdkp() {
    }

    public zzdkp(Looper looper0) {
        super(looper0);
    }

    public zzdkp(Looper looper0, Handler.Callback handler$Callback0) {
        super(looper0, handler$Callback0);
    }

    @Override  // android.os.Handler
    public final void dispatchMessage(Message message0) {
        this.zzb(message0);
    }

    @CallSuper
    protected void zzb(Message message0) {
        super.dispatchMessage(message0);
    }
}

