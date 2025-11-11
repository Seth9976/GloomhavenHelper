package com.google.android.gms.internal.ads;

public final class zzdun extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzdun.zzhke);
        }

        zza(zzduo zzduo0) {
        }

        public final zza zzb(zzb zzdun$zzb0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdun)this.zzhsu).zza(zzdun$zzb0);
            return this;
        }

        public final zza zzer(int v) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            ((zzdun)this.zzhsu).zzeq(v);
            return this;
        }
    }

    public static final class zzb extends zzdyz implements zzeaj {
        public static final class com.google.android.gms.internal.ads.zzdun.zzb.zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
            private com.google.android.gms.internal.ads.zzdun.zzb.zza() {
                super(zzb.zzhkf);
            }

            com.google.android.gms.internal.ads.zzdun.zzb.zza(zzduo zzduo0) {
            }

            public final com.google.android.gms.internal.ads.zzdun.zzb.zza zza(zzdug zzdug0) {
                if(this.zzhsv) {
                    this.zzbct();
                    this.zzhsv = false;
                }
                ((zzb)this.zzhsu).zzb(zzdug0);
                return this;
            }

            public final com.google.android.gms.internal.ads.zzdun.zzb.zza zza(zzduy zzduy0) {
                if(this.zzhsv) {
                    this.zzbct();
                    this.zzhsv = false;
                }
                ((zzb)this.zzhsu).zzb(zzduy0);
                return this;
            }

            public final com.google.android.gms.internal.ads.zzdun.zzb.zza zzes(int v) {
                if(this.zzhsv) {
                    this.zzbct();
                    this.zzhsv = false;
                }
                ((zzb)this.zzhsu).zzet(v);
                return this;
            }

            public final com.google.android.gms.internal.ads.zzdun.zzb.zza zzhf(String s) {
                if(this.zzhsv) {
                    this.zzbct();
                    this.zzhsv = false;
                }
                ((zzb)this.zzhsu).zzhd(s);
                return this;
            }
        }

        private static volatile zzeau zzdz;
        private String zzhiy;
        private int zzhjp;
        private int zzhka;
        private int zzhkb;
        private static final zzb zzhkf;

        static {
            zzb zzdun$zzb0 = new zzb();
            zzb.zzhkf = zzdun$zzb0;
            zzdyz.zza(zzb.class, zzdun$zzb0);
        }

        private zzb() {
            this.zzhiy = "";
        }

        @Override  // com.google.android.gms.internal.ads.zzdyz
        protected final Object zza(int v, Object object0, Object object1) {
            switch(zzduo.zzdk[v - 1]) {
                case 1: {
                    return new zzb();
                }
                case 2: {
                    return new com.google.android.gms.internal.ads.zzdun.zzb.zza(null);
                }
                case 3: {
                    return zzb.zza(zzb.zzhkf, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000B\u0004\f", new Object[]{"zzhiy", "zzhka", "zzhkb", "zzhjp"});
                }
                case 4: {
                    return zzb.zzhkf;
                }
                case 5: {
                    zzeau zzeau0 = zzb.zzdz;
                    if(zzeau0 == null) {
                        Class class0 = zzb.class;
                        synchronized(class0) {
                            zzeau0 = zzb.zzdz;
                            if(zzeau0 == null) {
                                zzeau0 = new zzc(zzb.zzhkf);
                                zzb.zzdz = zzeau0;
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

        public static com.google.android.gms.internal.ads.zzdun.zzb.zza zzaze() {
            return (com.google.android.gms.internal.ads.zzdun.zzb.zza)zzb.zzhkf.zzbcz();
        }

        private final void zzb(zzdug zzdug0) {
            this.zzhka = zzdug0.zzaf();
        }

        private final void zzb(zzduy zzduy0) {
            this.zzhjp = zzduy0.zzaf();
        }

        private final void zzet(int v) {
            this.zzhkb = v;
        }

        private final void zzhd(String s) {
            s.getClass();
            this.zzhiy = s;
        }
    }

    private static volatile zzeau zzdz;
    private int zzhjw;
    private zzdzi zzhkd;
    private static final zzdun zzhke;

    static {
        zzdun zzdun0 = new zzdun();
        zzdun.zzhke = zzdun0;
        zzdyz.zza(zzdun.class, zzdun0);
    }

    private zzdun() {
        this.zzhkd = zzdun.zzbdc();
    }

    private final void zza(zzb zzdun$zzb0) {
        zzdun$zzb0.getClass();
        if(!this.zzhkd.zzbam()) {
            this.zzhkd = zzdyz.zza(this.zzhkd);
        }
        this.zzhkd.add(zzdun$zzb0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzduo.zzdk[v - 1]) {
            case 1: {
                return new zzdun();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzdun.zza(zzdun.zzhke, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000B\u0002\u001B", new Object[]{"zzhjw", "zzhkd", zzb.class});
            }
            case 4: {
                return zzdun.zzhke;
            }
            case 5: {
                zzeau zzeau0 = zzdun.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdun.class;
                    synchronized(class0) {
                        zzeau0 = zzdun.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdun.zzhke);
                            zzdun.zzdz = zzeau0;
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

    public static zza zzazc() {
        return (zza)zzdun.zzhke.zzbcz();
    }

    private final void zzeq(int v) {
        this.zzhjw = v;
    }
}

