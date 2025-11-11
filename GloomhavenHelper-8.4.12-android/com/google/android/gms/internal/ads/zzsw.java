package com.google.android.gms.internal.ads;

import android.os.RemoteException;

public final class zzsw {
    private final byte[] zzbuo;
    private int zzbup;
    private int zzbuq;
    private final zzss zzbur;

    private zzsw(zzss zzss0, byte[] arr_b) {
        this.zzbur = zzss0;
        super();
        this.zzbuo = arr_b;
    }

    zzsw(zzss zzss0, byte[] arr_b, zzsx zzsx0) {
        this(zzss0, arr_b);
    }

    public final zzsw zzbq(int v) {
        this.zzbup = v;
        return this;
    }

    public final zzsw zzbr(int v) {
        this.zzbuq = v;
        return this;
    }

    public final void zzdt() {
        synchronized(this) {
            if(this.zzbur.zzbum) {
                try {
                    this.zzbur.zzbul.zzc(this.zzbuo);
                    this.zzbur.zzbul.zzm(this.zzbup);
                    this.zzbur.zzbul.zzn(this.zzbuq);
                    this.zzbur.zzbul.zza(null);
                    this.zzbur.zzbul.zzdt();
                }
                catch(RemoteException remoteException0) {
                    zzazh.zzb("Clearcut log failed", remoteException0);
                }
            }
        }
    }
}

