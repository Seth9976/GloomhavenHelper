package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzedq extends zzect {
    public String mimeType;
    public zza zzife;
    public byte[] zziff;

    public zzedq() {
        this.zzife = null;
        this.mimeType = null;
        this.zziff = null;
        this.zzhzu = null;
        this.zzhnu = -1;
    }

    @Override  // com.google.android.gms.internal.ads.zzect
    public final void zza(zzecr zzecr0) throws IOException {
        zza zzede$zzb$zzf$zza0 = this.zzife;
        if(zzede$zzb$zzf$zza0 != null) {
            zzecr0.zzac(1, zzede$zzb$zzf$zza0.zzaf());
        }
        String s = this.mimeType;
        if(s != null) {
            zzecr0.zzf(2, s);
        }
        byte[] arr_b = this.zziff;
        if(arr_b != null) {
            zzecr0.zza(3, arr_b);
        }
        super.zza(zzecr0);
    }

    @Override  // com.google.android.gms.internal.ads.zzect
    protected final int zzon() {
        int v = super.zzon();
        zza zzede$zzb$zzf$zza0 = this.zzife;
        if(zzede$zzb$zzf$zza0 != null) {
            v += zzecr.zzag(1, zzede$zzb$zzf$zza0.zzaf());
        }
        String s = this.mimeType;
        if(s != null) {
            v += zzecr.zzg(2, s);
        }
        return this.zziff == null ? v : v + (1 + (zzecr.zzgh(this.zziff.length) + this.zziff.length));
    }
}

