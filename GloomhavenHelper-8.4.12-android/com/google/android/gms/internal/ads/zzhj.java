package com.google.android.gms.internal.ads;

public abstract class zzhj {
    public static final zzhj zzagp;

    static {
        zzhj.zzagp = new zzhi();
    }

    public final boolean isEmpty() {
        return this.zzfa() == 0;
    }

    public final int zza(int v, zzhl zzhl0, zzhk zzhk0, int v1) {
        this.zza(v, zzhl0, false);
        this.zza(0, zzhk0, false);
        int v2 = 1;
        if(v == 0) {
            switch(v1) {
                case 0: {
                    if(this.zzfa() - 1 == 0) {
                        v2 = -1;
                    }
                    break;
                }
                case 1: {
                    v2 = 0;
                    break;
                }
                case 2: {
                    if(this.zzfa() - 1 == 0) {
                        v2 = 0;
                    }
                    break;
                }
                default: {
                    throw new IllegalStateException();
                }
            }
            if(v2 == -1) {
                return -1;
            }
            this.zza(v2, zzhk0, false);
            return 0;
        }
        return v + 1;
    }

    public final zzhk zza(int v, zzhk zzhk0, boolean z) {
        return this.zza(v, zzhk0, false, 0L);
    }

    public abstract zzhk zza(int arg1, zzhk arg2, boolean arg3, long arg4);

    public abstract zzhl zza(int arg1, zzhl arg2, boolean arg3);

    public abstract int zzc(Object arg1);

    public abstract int zzfa();

    public abstract int zzfb();
}

