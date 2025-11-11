package com.google.android.gms.internal.ads;

public final class zzdty extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdty.zzhiv);
        }

        zza(zzdtz zzdtz0) {
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgl;
    private zzdub zzhit;
    private static final zzdty zzhiv;

    static {
        zzdty zzdty0 = new zzdty();
        zzdty.zzhiv = zzdty0;
        zzdyz.zza(zzdty.class, zzdty0);
    }

    public final int getKeySize() {
        return this.zzhgl;
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdtz.zzdk[v - 1]) {
            case 1: {
                return new zzdty();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdty.zza(zzdty.zzhiv, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000B", new Object[]{"zzhit", "zzhgl"});
            }
            case 4: {
                return zzdty.zzhiv;
            }
            case 5: {
                zzeau zzeau0 = zzdty.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdty.class;
                    synchronized(class0) {
                        zzeau0 = zzdty.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdty.zzhiv);
                            zzdty.zzdz = zzeau0;
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

    public final zzdub zzaxy() {
        return this.zzhit == null ? zzdub.zzayf() : this.zzhit;
    }

    public static zzdty zzayc() {
        return zzdty.zzhiv;
    }

    public static zzdty zzr(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdty)zzdyz.zza(zzdty.zzhiv, zzdxn0, zzdym0);
    }
}

