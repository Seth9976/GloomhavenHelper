package com.google.android.gms.internal.ads;

public final class zzdjn extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdjn.zzgxp);
        }

        zza(zzdjo zzdjo0) {
        }

        public final zza zza(zzb zzdjj$zzb0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdjn)this.zzhsu).zza(((zzdjj)(((zzdyz)zzdjj$zzb0.zzbcx()))));
            return this;
        }

        public final zza zzb(com.google.android.gms.internal.ads.zzdjn.zzb zzdjn$zzb0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdjn)this.zzhsu).zza(zzdjn$zzb0);
            return this;
        }

        public final zza zzgw(String s) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdjn)this.zzhsu).zzn(s);
            return this;
        }
    }

    public static enum com.google.android.gms.internal.ads.zzdjn.zzb implements zzdzb {
        EVENT_TYPE_UNKNOWN(0),
        BLOCKED_IMPRESSION(1);

        private final int value;
        private static final zzdze zzen;

        static {
            com.google.android.gms.internal.ads.zzdjn.zzb.zzen = new zzdjp();
        }

        private com.google.android.gms.internal.ads.zzdjn.zzb(int v1) {
            this.value = v1;
        }

        @Override
        public final String toString() {
            return "<" + this.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + this.name() + '>';
        }

        @Override  // com.google.android.gms.internal.ads.zzdzb
        public final int zzaf() {
            return this.value;
        }

        public static zzdzd zzag() {
            return zzdjr.zzew;
        }

        public static com.google.android.gms.internal.ads.zzdjn.zzb zzdq(int v) {
            switch(v) {
                case 0: {
                    return com.google.android.gms.internal.ads.zzdjn.zzb.zzgxq;
                }
                case 1: {
                    return com.google.android.gms.internal.ads.zzdjn.zzb.zzgxr;
                }
                default: {
                    return null;
                }
            }
        }
    }

    private int zzdl;
    private String zzdm;
    private static volatile zzeau zzdz;
    private int zzgxm;
    private String zzgxn;
    private zzdjj zzgxo;
    private static final zzdjn zzgxp;

    static {
        zzdjn zzdjn0 = new zzdjn();
        zzdjn.zzgxp = zzdjn0;
        zzdyz.zza(zzdjn.class, zzdjn0);
    }

    private zzdjn() {
        this.zzdm = "";
        this.zzgxn = "";
    }

    private final void zza(zzdjj zzdjj0) {
        zzdjj0.getClass();
        this.zzgxo = zzdjj0;
        this.zzdl |= 8;
    }

    private final void zza(com.google.android.gms.internal.ads.zzdjn.zzb zzdjn$zzb0) {
        this.zzgxm = zzdjn$zzb0.zzaf();
        this.zzdl |= 1;
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdjo.zzdk[v - 1]) {
            case 1: {
                return new zzdjn();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdjn.zza(zzdjn.zzgxp, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\f\u0000\u0002\b\u0001\u0003\b\u0002\u0004\t\u0003", new Object[]{"zzdl", "zzgxm", com.google.android.gms.internal.ads.zzdjn.zzb.zzag(), "zzdm", "zzgxn", "zzgxo"});
            }
            case 4: {
                return zzdjn.zzgxp;
            }
            case 5: {
                zzeau zzeau0 = zzdjn.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdjn.class;
                    synchronized(class0) {
                        zzeau0 = zzdjn.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdjn.zzgxp);
                            zzdjn.zzdz = zzeau0;
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

    public static zza zzati() {
        return (zza)zzdjn.zzgxp.zzbcz();
    }

    private final void zzn(String s) {
        s.getClass();
        this.zzdl |= 2;
        this.zzdm = s;
    }
}

