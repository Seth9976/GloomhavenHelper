package com.google.android.gms.internal.ads;

public final class zzdsi extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdsi.zzhgu);
        }

        zza(zzdsj zzdsj0) {
        }
    }

    private static volatile zzeau zzdz;
    private zzdsm zzhgs;
    private zzdty zzhgt;
    private static final zzdsi zzhgu;

    static {
        zzdsi zzdsi0 = new zzdsi();
        zzdsi.zzhgu = zzdsi0;
        zzdyz.zza(zzdsi.class, zzdsi0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdsj.zzdk[v - 1]) {
            case 1: {
                return new zzdsi();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdsi.zza(zzdsi.zzhgu, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"zzhgs", "zzhgt"});
            }
            case 4: {
                return zzdsi.zzhgu;
            }
            case 5: {
                zzeau zzeau0 = zzdsi.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdsi.class;
                    synchronized(class0) {
                        zzeau0 = zzdsi.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdsi.zzhgu);
                            zzdsi.zzdz = zzeau0;
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

    public final zzdsm zzawd() {
        return this.zzhgs == null ? zzdsm.zzawk() : this.zzhgs;
    }

    public final zzdty zzawe() {
        return this.zzhgt == null ? zzdty.zzayc() : this.zzhgt;
    }

    public static zzdsi zze(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdsi)zzdyz.zza(zzdsi.zzhgu, zzdxn0, zzdym0);
    }
}

