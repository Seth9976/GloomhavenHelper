package com.google.android.gms.common;

import java.lang.ref.WeakReference;

abstract class zzg extends zze {
    private WeakReference zzv;
    private static final WeakReference zzw;

    static {
        zzg.zzw = new WeakReference(null);
    }

    zzg(byte[] arr_b) {
        super(arr_b);
        this.zzv = zzg.zzw;
    }

    @Override  // com.google.android.gms.common.zze
    final byte[] getBytes() {
        synchronized(this) {
            byte[] arr_b = (byte[])this.zzv.get();
            if(arr_b == null) {
                arr_b = this.zzd();
                this.zzv = new WeakReference(arr_b);
            }
            return arr_b;
        }
    }

    protected abstract byte[] zzd();
}

