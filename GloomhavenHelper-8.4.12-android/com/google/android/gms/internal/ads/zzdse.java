package com.google.android.gms.internal.ads;

public final class zzdse extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdse.zzhgo);
        }

        zza(zzdsf zzdsf0) {
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgn;
    private static final zzdse zzhgo;

    static {
        zzdse zzdse0 = new zzdse();
        zzdse.zzhgo = zzdse0;
        zzdyz.zza(zzdse.class, zzdse0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdsf.zzdk[v - 1]) {
            case 1: {
                return new zzdse();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdse.zza(zzdse.zzhgo, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000B", new Object[]{"zzhgn"});
            }
            case 4: {
                return zzdse.zzhgo;
            }
            case 5: {
                zzeau zzeau0 = zzdse.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdse.class;
                    synchronized(class0) {
                        zzeau0 = zzdse.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdse.zzhgo);
                            zzdse.zzdz = zzeau0;
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

    public final int zzavw() {
        return this.zzhgn;
    }

    public static zzdse zzavx() {
        return zzdse.zzhgo;
    }
}

