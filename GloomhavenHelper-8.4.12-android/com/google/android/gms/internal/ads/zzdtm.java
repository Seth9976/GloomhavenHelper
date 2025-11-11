package com.google.android.gms.internal.ads;

public final class zzdtm extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdtm.zzhhx);
        }

        zza(zzdtn zzdtn0) {
        }

        public final zza zzb(zzdtp zzdtp0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdtm)this.zzhsu).zza(zzdtp0);
            return this;
        }

        public final zza zzej(int v) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdtm)this.zzhsu).setVersion(0);
            return this;
        }

        public final zza zzy(zzdxn zzdxn0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdtm)this.zzhsu).zzs(zzdxn0);
            return this;
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgg;
    private zzdxn zzhgh;
    private zzdtp zzhhw;
    private static final zzdtm zzhhx;

    static {
        zzdtm zzdtm0 = new zzdtm();
        zzdtm.zzhhx = zzdtm0;
        zzdyz.zza(zzdtm.class, zzdtm0);
    }

    private zzdtm() {
        this.zzhgh = zzdxn.zzhoe;
    }

    public final int getVersion() {
        return this.zzhgg;
    }

    private final void setVersion(int v) {
        this.zzhgg = v;
    }

    private final void zza(zzdtp zzdtp0) {
        zzdtp0.getClass();
        this.zzhhw = zzdtp0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdtn.zzdk[v - 1]) {
            case 1: {
                return new zzdtm();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdtm.zza(zzdtm.zzhhx, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000B\u0002\t\u0003\n", new Object[]{"zzhgg", "zzhhw", "zzhgh"});
            }
            case 4: {
                return zzdtm.zzhhx;
            }
            case 5: {
                zzeau zzeau0 = zzdtm.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdtm.class;
                    synchronized(class0) {
                        zzeau0 = zzdtm.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdtm.zzhhx);
                            zzdtm.zzdz = zzeau0;
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

    public final zzdxn zzavr() {
        return this.zzhgh;
    }

    public final zzdtp zzaxl() {
        return this.zzhhw == null ? zzdtp.zzaxr() : this.zzhhw;
    }

    public static zza zzaxm() {
        return (zza)zzdtm.zzhhx.zzbcz();
    }

    public static zzdtm zzo(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdtm)zzdyz.zza(zzdtm.zzhhx, zzdxn0, zzdym0);
    }

    private final void zzs(zzdxn zzdxn0) {
        zzdxn0.getClass();
        this.zzhgh = zzdxn0;
    }
}

