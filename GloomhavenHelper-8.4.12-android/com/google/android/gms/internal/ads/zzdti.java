package com.google.android.gms.internal.ads;

public final class zzdti extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdti.zzhhr);
        }

        zza(zzdtj zzdtj0) {
        }
    }

    private static volatile zzeau zzdz;
    private zzdtl zzhhq;
    private static final zzdti zzhhr;

    static {
        zzdti zzdti0 = new zzdti();
        zzdti.zzhhr = zzdti0;
        zzdyz.zza(zzdti.class, zzdti0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdtj.zzdk[v - 1]) {
            case 1: {
                return new zzdti();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdti.zza(zzdti.zzhhr, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"zzhhq"});
            }
            case 4: {
                return zzdti.zzhhr;
            }
            case 5: {
                zzeau zzeau0 = zzdti.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdti.class;
                    synchronized(class0) {
                        zzeau0 = zzdti.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdti.zzhhr);
                            zzdti.zzdz = zzeau0;
                        }
                        return zzeau0;
                    }
                }
                return zzeau0;
            }
            case 6: {
                return (byte)1;
            }
            case 7: {
                return null;
            }
            default: {
                throw new UnsupportedOperationException();
            }
        }
    }

    public final zzdtl zzaxe() {
        return this.zzhhq == null ? zzdtl.zzaxj() : this.zzhhq;
    }

    public static zzdti zzn(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdti)zzdyz.zza(zzdti.zzhhr, zzdxn0, zzdym0);
    }
}

