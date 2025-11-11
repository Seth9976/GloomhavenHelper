package com.google.android.gms.internal.ads;

public final class zzdsa extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdsa.zzhgj);
        }

        zza(zzdsb zzdsb0) {
        }

        public final zza zzd(zzdse zzdse0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdsa)this.zzhsu).zzc(zzdse0);
            return this;
        }

        public final zza zzec(int v) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            zzdsa.zza(((zzdsa)this.zzhsu), 0);
            return this;
        }

        public final zza zzt(zzdxn zzdxn0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdsa)this.zzhsu).zzs(zzdxn0);
            return this;
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgg;
    private zzdxn zzhgh;
    private zzdse zzhgi;
    private static final zzdsa zzhgj;

    static {
        zzdsa zzdsa0 = new zzdsa();
        zzdsa.zzhgj = zzdsa0;
        zzdyz.zza(zzdsa.class, zzdsa0);
    }

    private zzdsa() {
        this.zzhgh = zzdxn.zzhoe;
    }

    public final int getVersion() {
        return this.zzhgg;
    }

    private final void setVersion(int v) {
        this.zzhgg = v;
    }

    static void zza(zzdsa zzdsa0, int v) {
        zzdsa0.setVersion(0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdsb.zzdk[v - 1]) {
            case 1: {
                return new zzdsa();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdsa.zza(zzdsa.zzhgj, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000B\u0002\n\u0003\t", new Object[]{"zzhgg", "zzhgh", "zzhgi"});
            }
            case 4: {
                return zzdsa.zzhgj;
            }
            case 5: {
                zzeau zzeau0 = zzdsa.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdsa.class;
                    synchronized(class0) {
                        zzeau0 = zzdsa.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdsa.zzhgj);
                            zzdsa.zzdz = zzeau0;
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

    public final zzdse zzavs() {
        return this.zzhgi == null ? zzdse.zzavx() : this.zzhgi;
    }

    public static zza zzavt() {
        return (zza)zzdsa.zzhgj.zzbcz();
    }

    public static zzdsa zzb(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzdsa)zzdyz.zza(zzdsa.zzhgj, zzdxn0, zzdym0);
    }

    private final void zzc(zzdse zzdse0) {
        zzdse0.getClass();
        this.zzhgi = zzdse0;
    }

    private final void zzs(zzdxn zzdxn0) {
        zzdxn0.getClass();
        this.zzhgh = zzdxn0;
    }
}

