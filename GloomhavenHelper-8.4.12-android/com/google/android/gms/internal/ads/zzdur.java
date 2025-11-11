package com.google.android.gms.internal.ads;

public final class zzdur extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdur.zzhkj);
        }

        zza(zzdus zzdus0) {
        }
    }

    private static volatile zzeau zzdz;
    private String zzhki;
    private static final zzdur zzhkj;

    static {
        zzdur zzdur0 = new zzdur();
        zzdur.zzhkj = zzdur0;
        zzdyz.zza(zzdur.class, zzdur0);
    }

    private zzdur() {
        this.zzhki = "";
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdus.zzdk[v - 1]) {
            case 1: {
                return new zzdur();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdur.zza(zzdur.zzhkj, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"zzhki"});
            }
            case 4: {
                return zzdur.zzhkj;
            }
            case 5: {
                zzeau zzeau0 = zzdur.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdur.class;
                    synchronized(class0) {
                        zzeau0 = zzdur.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdur.zzhkj);
                            zzdur.zzdz = zzeau0;
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

    public final String zzazj() [...] // 潜在的解密器

    public static zzdur zzazk() {
        return zzdur.zzhkj;
    }

    public static zzdur zzt(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdur)zzdyz.zza(zzdur.zzhkj, zzdxn0, zzdym0);
    }
}

