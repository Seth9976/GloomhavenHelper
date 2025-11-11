package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzedn extends zzect {
    private zza zzidz;
    public zzc[] zziea;
    private byte[] zzieb;
    private byte[] zziec;
    private Integer zzied;

    public zzedn() {
        this.zzidz = null;
        this.zziea = new zzc[0];
        this.zzieb = null;
        this.zziec = null;
        this.zzied = null;
        this.zzhzu = null;
        this.zzhnu = -1;
    }

    @Override  // com.google.android.gms.internal.ads.zzect
    public final void zza(zzecr zzecr0) throws IOException {
        if(this.zziea != null && this.zziea.length > 0) {
            for(int v = 0; true; ++v) {
                zzc[] arr_zzede$zzb$zzc = this.zziea;
                if(v >= arr_zzede$zzb$zzc.length) {
                    break;
                }
                zzc zzede$zzb$zzc0 = arr_zzede$zzb$zzc[v];
                if(zzede$zzb$zzc0 != null) {
                    zzecr0.zze(2, zzede$zzb$zzc0);
                }
            }
        }
        super.zza(zzecr0);
    }

    @Override  // com.google.android.gms.internal.ads.zzect
    protected final int zzon() {
        int v = super.zzon();
        if(this.zziea != null && this.zziea.length > 0) {
            for(int v1 = 0; true; ++v1) {
                zzc[] arr_zzede$zzb$zzc = this.zziea;
                if(v1 >= arr_zzede$zzb$zzc.length) {
                    break;
                }
                zzc zzede$zzb$zzc0 = arr_zzede$zzb$zzc[v1];
                if(zzede$zzb$zzc0 != null) {
                    v += zzdyi.zzc(2, zzede$zzb$zzc0);
                }
            }
        }
        return v;
    }
}

