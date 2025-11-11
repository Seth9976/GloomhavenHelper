package com.google.android.gms.internal.ads;

public final class zzdtc extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdtc.zzhhh);
        }

        zza(zzdtd zzdtd0) {
        }
    }

    private static volatile zzeau zzdz;
    private static final zzdtc zzhhh;

    static {
        zzdtc zzdtc0 = new zzdtc();
        zzdtc.zzhhh = zzdtc0;
        zzdyz.zza(zzdtc.class, zzdtc0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdtd.zzdk[v - 1]) {
            case 1: {
                return new zzdtc();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdtc.zza(zzdtc.zzhhh, "\u0000\u0000", null);
            }
            case 4: {
                return zzdtc.zzhhh;
            }
            case 5: {
                zzeau zzeau0 = zzdtc.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdtc.class;
                    synchronized(class0) {
                        zzeau0 = zzdtc.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdtc.zzhhh);
                            zzdtc.zzdz = zzeau0;
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

    public static zzdtc zzm(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdtc)zzdyz.zza(zzdtc.zzhhh, zzdxn0, zzdym0);
    }
}

