package com.google.android.gms.internal.ads;

public final class zzdsu extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdsu.zzhhd);
        }

        zza(zzdsv zzdsv0) {
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgy;
    private static final zzdsu zzhhd;

    static {
        zzdsu zzdsu0 = new zzdsu();
        zzdsu.zzhhd = zzdsu0;
        zzdyz.zza(zzdsu.class, zzdsu0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdsv.zzdk[v - 1]) {
            case 1: {
                return new zzdsu();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdsu.zza(zzdsu.zzhhd, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000B", new Object[]{"zzhgy"});
            }
            case 4: {
                return zzdsu.zzhhd;
            }
            case 5: {
                zzeau zzeau0 = zzdsu.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdsu.class;
                    synchronized(class0) {
                        zzeau0 = zzdsu.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdsu.zzhhd);
                            zzdsu.zzdz = zzeau0;
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

    public final int zzawm() {
        return this.zzhgy;
    }

    public static zzdsu zzawt() {
        return zzdsu.zzhhd;
    }
}

