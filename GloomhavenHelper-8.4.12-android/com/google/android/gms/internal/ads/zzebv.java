package com.google.android.gms.internal.ads;

import java.io.IOException;

abstract class zzebv {
    abstract void zza(Object arg1, int arg2, long arg3);

    abstract void zza(Object arg1, int arg2, zzdxn arg3);

    abstract void zza(Object arg1, int arg2, Object arg3);

    abstract void zza(Object arg1, zzecs arg2) throws IOException;

    abstract boolean zza(zzeax arg1);

    final boolean zza(Object object0, zzeax zzeax0) throws IOException {
        int v = zzeax0.getTag();
        switch(v & 7) {
            case 0: {
                this.zza(object0, v >>> 3, zzeax0.zzbbd());
                return true;
            }
            case 1: {
                this.zzb(object0, v >>> 3, zzeax0.zzbbf());
                return true;
            }
            case 2: {
                this.zza(object0, v >>> 3, zzeax0.zzbbj());
                return true;
            }
            case 3: {
                Object object1 = this.zzbfe();
                while(zzeax0.zzbbz() != 0x7FFFFFFF && this.zza(object1, zzeax0)) {
                }
                if((v >>> 3 << 3 | 4) != zzeax0.getTag()) {
                    throw zzdzh.zzbdm();
                }
                this.zza(object0, v >>> 3, this.zzav(object1));
                return true;
            }
            case 4: {
                return false;
            }
            case 5: {
                this.zzc(object0, v >>> 3, zzeax0.zzbbg());
                return true;
            }
            default: {
                throw zzdzh.zzbdn();
            }
        }
    }

    abstract void zzan(Object arg1);

    abstract Object zzav(Object arg1);

    abstract int zzax(Object arg1);

    abstract void zzb(Object arg1, int arg2, long arg3);

    abstract Object zzbb(Object arg1);

    abstract Object zzbc(Object arg1);

    abstract int zzbd(Object arg1);

    abstract Object zzbfe();

    abstract void zzc(Object arg1, int arg2, int arg3);

    abstract void zzc(Object arg1, zzecs arg2) throws IOException;

    abstract void zzh(Object arg1, Object arg2);

    abstract void zzi(Object arg1, Object arg2);

    abstract Object zzj(Object arg1, Object arg2);
}

