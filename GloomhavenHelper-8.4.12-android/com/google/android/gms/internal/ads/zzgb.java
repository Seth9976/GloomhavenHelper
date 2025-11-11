package com.google.android.gms.internal.ads;

public final class zzgb extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzgb.zzaax);
        }

        zza(zzga zzga0) {
        }

        public final zza zzaz(String s) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzgb)this.zzhsu).zzax(s);
            return this;
        }

        public final zza zzba(String s) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzgb)this.zzhsu).zzay(s);
            return this;
        }

        public final zza zzdj(long v) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzgb)this.zzhsu).zzdg(v);
            return this;
        }

        public final zza zzdk(long v) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzgb)this.zzhsu).zzdh(v);
            return this;
        }

        public final zza zzdl(long v) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzgb)this.zzhsu).zzdi(v);
            return this;
        }
    }

    private String zzaas;
    private String zzaat;
    private long zzaau;
    private long zzaav;
    private long zzaaw;
    private static final zzgb zzaax;
    private int zzdl;
    private static volatile zzeau zzdz;

    static {
        zzgb zzgb0 = new zzgb();
        zzgb.zzaax = zzgb0;
        zzdyz.zza(zzgb.class, zzgb0);
    }

    private zzgb() {
        this.zzaas = "";
        this.zzaat = "";
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzga.zzdk[v - 1]) {
            case 1: {
                return new zzgb();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzgb.zza(zzgb.zzaax, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\u0003\u0002\u0004\u0003\u0003\u0005\u0003\u0004", new Object[]{"zzdl", "zzaas", "zzaat", "zzaau", "zzaav", "zzaaw"});
            }
            case 4: {
                return zzgb.zzaax;
            }
            case 5: {
                zzeau zzeau0 = zzgb.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzgb.class;
                    synchronized(class0) {
                        zzeau0 = zzgb.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzgb.zzaax);
                            zzgb.zzdz = zzeau0;
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

    private final void zzax(String s) {
        s.getClass();
        this.zzdl |= 1;
        this.zzaas = s;
    }

    private final void zzay(String s) {
        s.getClass();
        this.zzdl |= 2;
        this.zzaat = s;
    }

    public final String zzdc() [...] // 潜在的解密器

    public final String zzdd() [...] // 潜在的解密器

    public final long zzde() {
        return this.zzaau;
    }

    public final long zzdf() {
        return this.zzaav;
    }

    private final void zzdg(long v) {
        this.zzdl |= 4;
        this.zzaau = v;
    }

    public final long zzdg() {
        return this.zzaaw;
    }

    public static zza zzdh() {
        return (zza)zzgb.zzaax.zzbcz();
    }

    private final void zzdh(long v) {
        this.zzdl |= 8;
        this.zzaav = v;
    }

    public static zzgb zzdi() {
        return zzgb.zzaax;
    }

    private final void zzdi(long v) {
        this.zzdl |= 16;
        this.zzaaw = v;
    }

    public static zzgb zzl(zzdxn zzdxn0) throws zzdzh {
        return (zzgb)zzdyz.zza(zzgb.zzaax, zzdxn0);
    }
}

