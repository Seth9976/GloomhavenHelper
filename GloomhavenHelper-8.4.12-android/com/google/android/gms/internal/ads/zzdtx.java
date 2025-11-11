package com.google.android.gms.internal.ads;

public final class zzdtx extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdtx.zzhiu);
        }

        zza(zzdtw zzdtw0) {
        }

        public final zza zzad(zzdxn zzdxn0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdtx)this.zzhsu).zzs(zzdxn0);
            return this;
        }

        public final zza zzd(zzdub zzdub0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdtx)this.zzhsu).zzc(zzdub0);
            return this;
        }

        public final zza zzen(int v) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            zzdtx.zza(((zzdtx)this.zzhsu), 0);
            return this;
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgg;
    private zzdxn zzhgh;
    private zzdub zzhit;
    private static final zzdtx zzhiu;

    static {
        zzdtx zzdtx0 = new zzdtx();
        zzdtx.zzhiu = zzdtx0;
        zzdyz.zza(zzdtx.class, zzdtx0);
    }

    private zzdtx() {
        this.zzhgh = zzdxn.zzhoe;
    }

    public final int getVersion() {
        return this.zzhgg;
    }

    private final void setVersion(int v) {
        this.zzhgg = v;
    }

    static void zza(zzdtx zzdtx0, int v) {
        zzdtx0.setVersion(0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdtw.zzdk[v - 1]) {
            case 1: {
                return new zzdtx();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdtx.zza(zzdtx.zzhiu, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000B\u0002\t\u0003\n", new Object[]{"zzhgg", "zzhit", "zzhgh"});
            }
            case 4: {
                return zzdtx.zzhiu;
            }
            case 5: {
                zzeau zzeau0 = zzdtx.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdtx.class;
                    synchronized(class0) {
                        zzeau0 = zzdtx.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdtx.zzhiu);
                            zzdtx.zzdz = zzeau0;
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

    public final zzdub zzaxy() {
        return this.zzhit == null ? zzdub.zzayf() : this.zzhit;
    }

    public static zza zzaxz() {
        return (zza)zzdtx.zzhiu.zzbcz();
    }

    public static zzdtx zzaya() {
        return zzdtx.zzhiu;
    }

    private final void zzc(zzdub zzdub0) {
        zzdub0.getClass();
        this.zzhit = zzdub0;
    }

    public static zzdtx zzq(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdtx)zzdyz.zza(zzdtx.zzhiu, zzdxn0, zzdym0);
    }

    private final void zzs(zzdxn zzdxn0) {
        zzdxn0.getClass();
        this.zzhgh = zzdxn0;
    }
}

