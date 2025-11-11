package com.google.android.gms.internal.ads;

public final class zzdvz {
    private final zzdwm zzhmb;
    private final zzdwm zzhmc;

    public zzdvz(byte[] arr_b, byte[] arr_b1) {
        this.zzhmb = zzdwm.zzr(arr_b);
        this.zzhmc = zzdwm.zzr(arr_b1);
    }

    public final byte[] zzbac() {
        return this.zzhmb == null ? null : this.zzhmb.getBytes();
    }

    public final byte[] zzbad() {
        return this.zzhmc == null ? null : this.zzhmc.getBytes();
    }
}

