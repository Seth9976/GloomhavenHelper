package com.google.android.gms.internal.ads;

public final class zzdve extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdve.zzhla);
        }

        zza(zzdvd zzdvd0) {
        }
    }

    private static volatile zzeau zzdz;
    private static final zzdve zzhla;

    static {
        zzdve zzdve0 = new zzdve();
        zzdve.zzhla = zzdve0;
        zzdyz.zza(zzdve.class, zzdve0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdvd.zzdk[v - 1]) {
            case 1: {
                return new zzdve();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdve.zza(zzdve.zzhla, "\u0000\u0000", null);
            }
            case 4: {
                return zzdve.zzhla;
            }
            case 5: {
                zzeau zzeau0 = zzdve.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdve.class;
                    synchronized(class0) {
                        zzeau0 = zzdve.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdve.zzhla);
                            zzdve.zzdz = zzeau0;
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

    public static zzdve zzx(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdve)zzdyz.zza(zzdve.zzhla, zzdxn0, zzdym0);
    }
}

