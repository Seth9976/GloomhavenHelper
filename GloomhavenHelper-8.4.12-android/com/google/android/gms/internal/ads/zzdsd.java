package com.google.android.gms.internal.ads;

public final class zzdsd extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdsd.zzhgm);
        }

        zza(zzdsc zzdsc0) {
        }
    }

    private static volatile zzeau zzdz;
    private zzdse zzhgi;
    private int zzhgl;
    private static final zzdsd zzhgm;

    static {
        zzdsd zzdsd0 = new zzdsd();
        zzdsd.zzhgm = zzdsd0;
        zzdyz.zza(zzdsd.class, zzdsd0);
    }

    public final int getKeySize() {
        return this.zzhgl;
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdsc.zzdk[v - 1]) {
            case 1: {
                return new zzdsd();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdsd.zza(zzdsd.zzhgm, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000B\u0002\t", new Object[]{"zzhgl", "zzhgi"});
            }
            case 4: {
                return zzdsd.zzhgm;
            }
            case 5: {
                zzeau zzeau0 = zzdsd.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdsd.class;
                    synchronized(class0) {
                        zzeau0 = zzdsd.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdsd.zzhgm);
                            zzdsd.zzdz = zzeau0;
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

    public final zzdse zzavs() {
        return this.zzhgi == null ? zzdse.zzavx() : this.zzhgi;
    }

    public static zzdsd zzc(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdsd)zzdyz.zza(zzdsd.zzhgm, zzdxn0, zzdym0);
    }
}

