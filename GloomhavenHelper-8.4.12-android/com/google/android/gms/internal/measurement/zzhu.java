package com.google.android.gms.internal.measurement;

import java.io.IOException;

abstract class zzhu {
    abstract Object zza();

    abstract Object zza(Object arg1);

    abstract void zza(Object arg1, int arg2, int arg3);

    abstract void zza(Object arg1, int arg2, long arg3);

    abstract void zza(Object arg1, int arg2, zzdv arg3);

    abstract void zza(Object arg1, int arg2, Object arg3);

    abstract void zza(Object arg1, zzir arg2) throws IOException;

    abstract void zza(Object arg1, Object arg2);

    abstract boolean zza(zzhd arg1);

    final boolean zza(Object object0, zzhd zzhd0) throws IOException {
        int v = zzhd0.zzb();
        switch(v & 7) {
            case 0: {
                this.zza(object0, v >>> 3, zzhd0.zzg());
                return true;
            }
            case 1: {
                this.zzb(object0, v >>> 3, zzhd0.zzi());
                return true;
            }
            case 2: {
                this.zza(object0, v >>> 3, zzhd0.zzn());
                return true;
            }
            case 3: {
                Object object1 = this.zza();
                while(zzhd0.zza() != 0x7FFFFFFF && this.zza(object1, zzhd0)) {
                }
                if((v >>> 3 << 3 | 4) != zzhd0.zzb()) {
                    throw zzfn.zze();
                }
                this.zza(object0, v >>> 3, this.zza(object1));
                return true;
            }
            case 4: {
                return false;
            }
            case 5: {
                this.zza(object0, v >>> 3, zzhd0.zzj());
                return true;
            }
            default: {
                throw zzfn.zzf();
            }
        }
    }

    abstract Object zzb(Object arg1);

    abstract void zzb(Object arg1, int arg2, long arg3);

    abstract void zzb(Object arg1, zzir arg2) throws IOException;

    abstract void zzb(Object arg1, Object arg2);

    abstract Object zzc(Object arg1);

    abstract Object zzc(Object arg1, Object arg2);

    abstract void zzd(Object arg1);

    abstract int zze(Object arg1);

    abstract int zzf(Object arg1);
}

