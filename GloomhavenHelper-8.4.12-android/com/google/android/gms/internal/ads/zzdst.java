package com.google.android.gms.internal.ads;

public final class zzdst extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdst.zzhhc);
        }

        zza(zzdss zzdss0) {
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgl;
    private zzdsu zzhha;
    private static final zzdst zzhhc;

    static {
        zzdst zzdst0 = new zzdst();
        zzdst.zzhhc = zzdst0;
        zzdyz.zza(zzdst.class, zzdst0);
    }

    public final int getKeySize() {
        return this.zzhgl;
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdss.zzdk[v - 1]) {
            case 1: {
                return new zzdst();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdst.zza(zzdst.zzhhc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000B", new Object[]{"zzhha", "zzhgl"});
            }
            case 4: {
                return zzdst.zzhhc;
            }
            case 5: {
                zzeau zzeau0 = zzdst.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdst.class;
                    synchronized(class0) {
                        zzeau0 = zzdst.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdst.zzhhc);
                            zzdst.zzdz = zzeau0;
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

    public final zzdsu zzawp() {
        return this.zzhha == null ? zzdsu.zzawt() : this.zzhha;
    }

    public static zzdst zzi(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdst)zzdyz.zza(zzdst.zzhhc, zzdxn0, zzdym0);
    }
}

