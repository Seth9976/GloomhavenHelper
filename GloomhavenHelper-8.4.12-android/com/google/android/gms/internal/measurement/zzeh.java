package com.google.android.gms.internal.measurement;

import java.io.IOException;

public abstract class zzeh {
    int zza;
    int zzb;
    zzei zzc;
    private int zzd;
    private boolean zze;

    private zzeh() {
        this.zzb = 100;
        this.zzd = 0x7FFFFFFF;
        this.zze = false;
    }

    zzeh(zzeg zzeg0) {
    }

    public static long zza(long v) [...] // Inlined contents

    static zzeh zza(byte[] arr_b, int v, int v1, boolean z) {
        zzeh zzeh0 = new zzej(arr_b, 0, v1, false, null);
        try {
            zzeh0.zzc(v1);
            return zzeh0;
        }
        catch(zzfn zzfn0) {
            throw new IllegalArgumentException(zzfn0);
        }
    }

    public abstract int zza() throws IOException;

    public abstract void zza(int arg1) throws zzfn;

    public abstract double zzb() throws IOException;

    public abstract boolean zzb(int arg1) throws IOException;

    public abstract float zzc() throws IOException;

    public abstract int zzc(int arg1) throws zzfn;

    public abstract long zzd() throws IOException;

    public abstract void zzd(int arg1);

    public static int zze(int v) [...] // Inlined contents

    public abstract long zze() throws IOException;

    public abstract int zzf() throws IOException;

    public abstract long zzg() throws IOException;

    public abstract int zzh() throws IOException;

    public abstract boolean zzi() throws IOException;

    public abstract String zzj() throws IOException;

    public abstract String zzk() throws IOException;

    public abstract zzdv zzl() throws IOException;

    public abstract int zzm() throws IOException;

    public abstract int zzn() throws IOException;

    public abstract int zzo() throws IOException;

    public abstract long zzp() throws IOException;

    public abstract int zzq() throws IOException;

    public abstract long zzr() throws IOException;

    abstract long zzs() throws IOException;

    public abstract boolean zzt() throws IOException;

    public abstract int zzu();
}

