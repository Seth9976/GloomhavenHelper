package com.google.android.gms.internal.ads;

public final class zzdsh extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdsh.zzhgr);
        }

        zza(zzdsg zzdsg0) {
        }

        public final zza zzb(zzdsl zzdsl0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdsh)this.zzhsu).zza(zzdsl0);
            return this;
        }

        public final zza zzb(zzdtx zzdtx0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdsh)this.zzhsu).zza(zzdtx0);
            return this;
        }

        public final zza zzed(int v) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdsh)this.zzhsu).setVersion(v);
            return this;
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgg;
    private zzdsl zzhgp;
    private zzdtx zzhgq;
    private static final zzdsh zzhgr;

    static {
        zzdsh zzdsh0 = new zzdsh();
        zzdsh.zzhgr = zzdsh0;
        zzdyz.zza(zzdsh.class, zzdsh0);
    }

    public final int getVersion() {
        return this.zzhgg;
    }

    private final void setVersion(int v) {
        this.zzhgg = v;
    }

    private final void zza(zzdsl zzdsl0) {
        zzdsl0.getClass();
        this.zzhgp = zzdsl0;
    }

    private final void zza(zzdtx zzdtx0) {
        zzdtx0.getClass();
        this.zzhgq = zzdtx0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdsg.zzdk[v - 1]) {
            case 1: {
                return new zzdsh();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdsh.zza(zzdsh.zzhgr, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000B\u0002\t\u0003\t", new Object[]{"zzhgg", "zzhgp", "zzhgq"});
            }
            case 4: {
                return zzdsh.zzhgr;
            }
            case 5: {
                zzeau zzeau0 = zzdsh.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdsh.class;
                    synchronized(class0) {
                        zzeau0 = zzdsh.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdsh.zzhgr);
                            zzdsh.zzdz = zzeau0;
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

    public final zzdsl zzavz() {
        return this.zzhgp == null ? zzdsl.zzawi() : this.zzhgp;
    }

    public final zzdtx zzawa() {
        return this.zzhgq == null ? zzdtx.zzaya() : this.zzhgq;
    }

    public static zza zzawb() {
        return (zza)zzdsh.zzhgr.zzbcz();
    }

    public static zzdsh zzd(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdsh)zzdyz.zza(zzdsh.zzhgr, zzdxn0, zzdym0);
    }
}

