package com.google.android.gms.internal.ads;

public final class zzduv extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzduv.zzhko);
        }

        zza(zzduw zzduw0) {
        }
    }

    private static volatile zzeau zzdz;
    private String zzhkm;
    private zzdui zzhkn;
    private static final zzduv zzhko;

    static {
        zzduv zzduv0 = new zzduv();
        zzduv.zzhko = zzduv0;
        zzdyz.zza(zzduv.class, zzduv0);
    }

    private zzduv() {
        this.zzhkm = "";
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzduw.zzdk[v - 1]) {
            case 1: {
                return new zzduv();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzduv.zza(zzduv.zzhko, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"zzhkm", "zzhkn"});
            }
            case 4: {
                return zzduv.zzhko;
            }
            case 5: {
                zzeau zzeau0 = zzduv.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzduv.class;
                    synchronized(class0) {
                        zzeau0 = zzduv.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzduv.zzhko);
                            zzduv.zzdz = zzeau0;
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

    public final String zzazp() [...] // 潜在的解密器

    public final zzdui zzazq() {
        return this.zzhkn == null ? zzdui.zzayn() : this.zzhkn;
    }

    public static zzduv zzazr() {
        return zzduv.zzhko;
    }

    public static zzduv zzv(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzduv)zzdyz.zza(zzduv.zzhko, zzdxn0, zzdym0);
    }
}

