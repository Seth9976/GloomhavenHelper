package com.google.android.gms.internal.ads;

public final class zzdsm extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdsm.zzhgx);
        }

        zza(zzdsn zzdsn0) {
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgl;
    private zzdsp zzhgv;
    private static final zzdsm zzhgx;

    static {
        zzdsm zzdsm0 = new zzdsm();
        zzdsm.zzhgx = zzdsm0;
        zzdyz.zza(zzdsm.class, zzdsm0);
    }

    public final int getKeySize() {
        return this.zzhgl;
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdsn.zzdk[v - 1]) {
            case 1: {
                return new zzdsm();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdsm.zza(zzdsm.zzhgx, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000B", new Object[]{"zzhgv", "zzhgl"});
            }
            case 4: {
                return zzdsm.zzhgx;
            }
            case 5: {
                zzeau zzeau0 = zzdsm.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdsm.class;
                    synchronized(class0) {
                        zzeau0 = zzdsm.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdsm.zzhgx);
                            zzdsm.zzdz = zzeau0;
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

    public final zzdsp zzawg() {
        return this.zzhgv == null ? zzdsp.zzawn() : this.zzhgv;
    }

    public static zzdsm zzawk() {
        return zzdsm.zzhgx;
    }

    public static zzdsm zzg(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdsm)zzdyz.zza(zzdsm.zzhgx, zzdxn0, zzdym0);
    }
}

