package com.google.android.gms.internal.ads;

public final class zzduq extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzduq.zzhkh);
        }

        zza(zzdup zzdup0) {
        }

        public final zza zzb(zzdur zzdur0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzduq)this.zzhsu).zza(zzdur0);
            return this;
        }

        public final zza zzeu(int v) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzduq)this.zzhsu).setVersion(0);
            return this;
        }
    }

    private static volatile zzeau zzdz;
    private int zzhgg;
    private zzdur zzhkg;
    private static final zzduq zzhkh;

    static {
        zzduq zzduq0 = new zzduq();
        zzduq.zzhkh = zzduq0;
        zzdyz.zza(zzduq.class, zzduq0);
    }

    public final int getVersion() {
        return this.zzhgg;
    }

    private final void setVersion(int v) {
        this.zzhgg = v;
    }

    private final void zza(zzdur zzdur0) {
        zzdur0.getClass();
        this.zzhkg = zzdur0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdup.zzdk[v - 1]) {
            case 1: {
                return new zzduq();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzduq.zza(zzduq.zzhkh, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000B\u0002\t", new Object[]{"zzhgg", "zzhkg"});
            }
            case 4: {
                return zzduq.zzhkh;
            }
            case 5: {
                zzeau zzeau0 = zzduq.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzduq.class;
                    synchronized(class0) {
                        zzeau0 = zzduq.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzduq.zzhkh);
                            zzduq.zzdz = zzeau0;
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

    public final zzdur zzazg() {
        return this.zzhkg == null ? zzdur.zzazk() : this.zzhkg;
    }

    public static zza zzazh() {
        return (zza)zzduq.zzhkh.zzbcz();
    }

    public static zzduq zzs(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzduq)zzdyz.zza(zzduq.zzhkh, zzdxn0, zzdym0);
    }
}

