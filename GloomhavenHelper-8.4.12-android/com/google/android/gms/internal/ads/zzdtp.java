package com.google.android.gms.internal.ads;

public final class zzdtp extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdtp.zzhia);
        }

        zza(zzdto zzdto0) {
        }

        public final zza zzab(zzdxn zzdxn0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdtp)this.zzhsu).zzz(zzdxn0);
            return this;
        }

        public final zza zzac(zzdxn zzdxn0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdtp)this.zzhsu).zzaa(zzdxn0);
            return this;
        }

        public final zza zzc(zzdtl zzdtl0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdtp)this.zzhsu).zzb(zzdtl0);
            return this;
        }

        public final zza zzek(int v) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdtp)this.zzhsu).setVersion(0);
            return this;
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgg;
    private zzdtl zzhhq;
    private zzdxn zzhhy;
    private zzdxn zzhhz;
    private static final zzdtp zzhia;

    static {
        zzdtp zzdtp0 = new zzdtp();
        zzdtp.zzhia = zzdtp0;
        zzdyz.zza(zzdtp.class, zzdtp0);
    }

    private zzdtp() {
        this.zzhhy = zzdxn.zzhoe;
        this.zzhhz = zzdxn.zzhoe;
    }

    public final int getVersion() {
        return this.zzhgg;
    }

    private final void setVersion(int v) {
        this.zzhgg = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdto.zzdk[v - 1]) {
            case 1: {
                return new zzdtp();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdtp.zza(zzdtp.zzhia, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000B\u0002\t\u0003\n\u0004\n", new Object[]{"zzhgg", "zzhhq", "zzhhy", "zzhhz"});
            }
            case 4: {
                return zzdtp.zzhia;
            }
            case 5: {
                zzeau zzeau0 = zzdtp.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdtp.class;
                    synchronized(class0) {
                        zzeau0 = zzdtp.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdtp.zzhia);
                            zzdtp.zzdz = zzeau0;
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

    private final void zzaa(zzdxn zzdxn0) {
        zzdxn0.getClass();
        this.zzhhz = zzdxn0;
    }

    public final zzdtl zzaxe() {
        return this.zzhhq == null ? zzdtl.zzaxj() : this.zzhhq;
    }

    public final zzdxn zzaxo() {
        return this.zzhhy;
    }

    public final zzdxn zzaxp() {
        return this.zzhhz;
    }

    public static zza zzaxq() {
        return (zza)zzdtp.zzhia.zzbcz();
    }

    public static zzdtp zzaxr() {
        return zzdtp.zzhia;
    }

    private final void zzb(zzdtl zzdtl0) {
        zzdtl0.getClass();
        this.zzhhq = zzdtl0;
    }

    public static zzdtp zzp(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdtp)zzdyz.zza(zzdtp.zzhia, zzdxn0, zzdym0);
    }

    private final void zzz(zzdxn zzdxn0) {
        zzdxn0.getClass();
        this.zzhhy = zzdxn0;
    }
}

