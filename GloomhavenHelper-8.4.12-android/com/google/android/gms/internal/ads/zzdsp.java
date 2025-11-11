package com.google.android.gms.internal.ads;

public final class zzdsp extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdsp.zzhgz);
        }

        zza(zzdso zzdso0) {
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgy;
    private static final zzdsp zzhgz;

    static {
        zzdsp zzdsp0 = new zzdsp();
        zzdsp.zzhgz = zzdsp0;
        zzdyz.zza(zzdsp.class, zzdsp0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdso.zzdk[v - 1]) {
            case 1: {
                return new zzdsp();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdsp.zza(zzdsp.zzhgz, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000B", new Object[]{"zzhgy"});
            }
            case 4: {
                return zzdsp.zzhgz;
            }
            case 5: {
                zzeau zzeau0 = zzdsp.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdsp.class;
                    synchronized(class0) {
                        zzeau0 = zzdsp.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdsp.zzhgz);
                            zzdsp.zzdz = zzeau0;
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

    public static zzdsp zzawn() {
        return zzdsp.zzhgz;
    }
}

