package com.google.android.gms.internal.ads;

public final class zzdth extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdth.zzhhp);
        }

        zza(zzdtg zzdtg0) {
        }
    }

    private static volatile zzeau zzdz;
    private zzdui zzhho;
    private static final zzdth zzhhp;

    static {
        zzdth zzdth0 = new zzdth();
        zzdth.zzhhp = zzdth0;
        zzdyz.zza(zzdth.class, zzdth0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdtg.zzdk[v - 1]) {
            case 1: {
                return new zzdth();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdth.zza(zzdth.zzhhp, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002\t", new Object[]{"zzhho"});
            }
            case 4: {
                return zzdth.zzhhp;
            }
            case 5: {
                zzeau zzeau0 = zzdth.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdth.class;
                    synchronized(class0) {
                        zzeau0 = zzdth.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdth.zzhhp);
                            zzdth.zzdz = zzeau0;
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

    public final zzdui zzaxb() {
        return this.zzhho == null ? zzdui.zzayn() : this.zzhho;
    }

    public static zzdth zzaxc() {
        return zzdth.zzhhp;
    }
}

