package com.google.android.gms.internal.measurement;

public class zzfs {
    private static final zzeq zza;
    private zzdv zzb;
    private volatile zzgn zzc;
    private volatile zzdv zzd;

    static {
        zzfs.zza = zzeq.zza();
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzfs)) {
            return false;
        }
        zzgn zzgn0 = this.zzc;
        zzgn zzgn1 = ((zzfs)object0).zzc;
        if(zzgn0 == null && zzgn1 == null) {
            return this.zzc().equals(((zzfs)object0).zzc());
        }
        if(zzgn0 != null && zzgn1 != null) {
            return zzgn0.equals(zzgn1);
        }
        return zzgn0 == null ? this.zzb(zzgn1.zzv()).equals(zzgn1) : zzgn0.equals(((zzfs)object0).zzb(zzgn0.zzv()));
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public final zzgn zza(zzgn zzgn0) {
        zzgn zzgn1 = this.zzc;
        this.zzb = null;
        this.zzd = null;
        this.zzc = zzgn0;
        return zzgn1;
    }

    private final zzgn zzb(zzgn zzgn0) {
        if(this.zzc == null) {
            synchronized(this) {
                if(this.zzc != null) {
                    return this.zzc;
                }
                this.zzc = zzgn0;
                this.zzd = zzdv.zza;
            }
        }
        return this.zzc;
    }

    public final int zzb() {
        if(this.zzd != null) {
            return this.zzd.zza();
        }
        return this.zzc == null ? 0 : this.zzc.zzbl();
    }

    public final zzdv zzc() {
        if(this.zzd != null) {
            return this.zzd;
        }
        synchronized(this) {
            if(this.zzd != null) {
                return this.zzd;
            }
            this.zzd = this.zzc == null ? zzdv.zza : this.zzc.zzbg();
            return this.zzd;
        }
    }
}

