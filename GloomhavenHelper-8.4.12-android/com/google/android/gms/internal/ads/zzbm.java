package com.google.android.gms.internal.ads;

public final class zzbm {
    public static final class zza extends zzdyz implements zzeaj {
        public static final class com.google.android.gms.internal.ads.zzbm.zza.zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
            private com.google.android.gms.internal.ads.zzbm.zza.zza() {
                super(zza.zzdy);
            }

            com.google.android.gms.internal.ads.zzbm.zza.zza(zzbl zzbl0) {
            }

            public final com.google.android.gms.internal.ads.zzbm.zza.zza zza(com.google.android.gms.internal.ads.zzbm.zza.zzb.zza zzbm$zza$zzb$zza0) {
                if(this.zzhsv) {
                    this.zzbct();
                    this.zzhsv = false;
                }
                ((zza)this.zzhsu).zza(((zzb)(((zzdyz)zzbm$zza$zzb$zza0.zzbcx()))));
                return this;
            }

            public final com.google.android.gms.internal.ads.zzbm.zza.zza zzc(long v) {
                if(this.zzhsv) {
                    this.zzbct();
                    this.zzhsv = false;
                }
                ((zza)this.zzhsu).zzd(v);
                return this;
            }

            public final com.google.android.gms.internal.ads.zzbm.zza.zza zzi(String s) {
                if(this.zzhsv) {
                    this.zzbct();
                    this.zzhsv = false;
                }
                ((zza)this.zzhsu).zzn(s);
                return this;
            }

            public final com.google.android.gms.internal.ads.zzbm.zza.zza zzj(String s) {
                if(this.zzhsv) {
                    this.zzbct();
                    this.zzhsv = false;
                }
                ((zza)this.zzhsu).zzo(s);
                return this;
            }

            public final com.google.android.gms.internal.ads.zzbm.zza.zza zzk(String s) {
                if(this.zzhsv) {
                    this.zzbct();
                    this.zzhsv = false;
                }
                ((zza)this.zzhsu).zzp(s);
                return this;
            }

            public final com.google.android.gms.internal.ads.zzbm.zza.zza zzl(String s) {
                if(this.zzhsv) {
                    this.zzbct();
                    this.zzhsv = false;
                }
                ((zza)this.zzhsu).zzq(s);
                return this;
            }

            public final com.google.android.gms.internal.ads.zzbm.zza.zza zzm(String s) {
                if(this.zzhsv) {
                    this.zzbct();
                    this.zzhsv = false;
                }
                ((zza)this.zzhsu).zzr(s);
                return this;
            }
        }

        public static final class zzb extends zzdyz implements zzeaj {
            public static final class com.google.android.gms.internal.ads.zzbm.zza.zzb.zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
                private com.google.android.gms.internal.ads.zzbm.zza.zzb.zza() {
                    super(zzb.zzec);
                }

                com.google.android.gms.internal.ads.zzbm.zza.zzb.zza(zzbl zzbl0) {
                }

                public final com.google.android.gms.internal.ads.zzbm.zza.zzb.zza zzs(String s) {
                    if(this.zzhsv) {
                        this.zzbct();
                        this.zzhsv = false;
                    }
                    ((zzb)this.zzhsu).zzu(s);
                    return this;
                }

                public final com.google.android.gms.internal.ads.zzbm.zza.zzb.zza zzt(String s) {
                    if(this.zzhsv) {
                        this.zzbct();
                        this.zzhsv = false;
                    }
                    ((zzb)this.zzhsu).zzv(s);
                    return this;
                }
            }

            private int zzdl;
            private static volatile zzeau zzdz;
            private String zzea;
            private String zzeb;
            private static final zzb zzec;

            static {
                zzb zzbm$zza$zzb0 = new zzb();
                zzb.zzec = zzbm$zza$zzb0;
                zzdyz.zza(zzb.class, zzbm$zza$zzb0);
            }

            private zzb() {
                this.zzea = "";
                this.zzeb = "";
            }

            @Override  // com.google.android.gms.internal.ads.zzdyz
            protected final Object zza(int v, Object object0, Object object1) {
                switch(zzbl.zzdk[v - 1]) {
                    case 1: {
                        return new zzb();
                    }
                    case 2: {
                        return new com.google.android.gms.internal.ads.zzbm.zza.zzb.zza(null);
                    }
                    case 3: {
                        return zzb.zza(zzb.zzec, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001", new Object[]{"zzdl", "zzea", "zzeb"});
                    }
                    case 4: {
                        return zzb.zzec;
                    }
                    case 5: {
                        zzeau zzeau0 = zzb.zzdz;
                        if(zzeau0 == null) {
                            Class class0 = zzb.class;
                            synchronized(class0) {
                                zzeau0 = zzb.zzdz;
                                if(zzeau0 == null) {
                                    zzeau0 = new zzc(zzb.zzec);
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

            private final void zzu(String s) {
                s.getClass();
                this.zzdl |= 1;
                this.zzea = s;
            }

            public static com.google.android.gms.internal.ads.zzbm.zza.zzb.zza zzv() {
                return (com.google.android.gms.internal.ads.zzbm.zza.zzb.zza)zzb.zzec.zzbcz();
            }

            private final void zzv(String s) {
                s.getClass();
                this.zzdl |= 2;
                this.zzeb = s;
            }
        }

        private int zzdl;
        private String zzdm;
        private long zzdn;
        private String zzdo;
        private String zzdp;
        private String zzdq;
        private long zzdr;
        private long zzds;
        private String zzdt;
        private long zzdu;
        private String zzdv;
        private String zzdw;
        private zzdzi zzdx;
        private static final zza zzdy;
        private static volatile zzeau zzdz;

        static {
            zza zzbm$zza0 = new zza();
            zza.zzdy = zzbm$zza0;
            zzdyz.zza(zza.class, zzbm$zza0);
        }

        private zza() {
            this.zzdm = "";
            this.zzdo = "";
            this.zzdp = "";
            this.zzdq = "";
            this.zzdt = "";
            this.zzdv = "";
            this.zzdw = "";
            this.zzdx = zza.zzbdc();
        }

        private final void zza(zzb zzbm$zza$zzb0) {
            zzbm$zza$zzb0.getClass();
            if(!this.zzdx.zzbam()) {
                this.zzdx = zzdyz.zza(this.zzdx);
            }
            this.zzdx.add(zzbm$zza$zzb0);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyz
        protected final Object zza(int v, Object object0, Object object1) {
            switch(zzbl.zzdk[v - 1]) {
                case 1: {
                    return new zza();
                }
                case 2: {
                    return new com.google.android.gms.internal.ads.zzbm.zza.zza(null);
                }
                case 3: {
                    return zza.zza(zza.zzdy, "\u0001\f\u0000\u0001\u0001\f\f\u0000\u0001\u0000\u0001\b\u0000\u0002\u0002\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\u0002\u0005\u0007\u0002\u0006\b\b\u0007\t\u0002\b\n\b\t\u000B\b\n\f\u001B", new Object[]{"zzdl", "zzdm", "zzdn", "zzdo", "zzdp", "zzdq", "zzdr", "zzds", "zzdt", "zzdu", "zzdv", "zzdw", "zzdx", zzb.class});
                }
                case 4: {
                    return zza.zzdy;
                }
                case 5: {
                    zzeau zzeau0 = zza.zzdz;
                    if(zzeau0 == null) {
                        Class class0 = zza.class;
                        synchronized(class0) {
                            zzeau0 = zza.zzdz;
                            if(zzeau0 == null) {
                                zzeau0 = new zzc(zza.zzdy);
                                zza.zzdz = zzeau0;
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

        private final void zzd(long v) {
            this.zzdl |= 2;
            this.zzdn = v;
        }

        private final void zzn(String s) {
            s.getClass();
            this.zzdl |= 1;
            this.zzdm = s;
        }

        private final void zzo(String s) {
            s.getClass();
            this.zzdl |= 4;
            this.zzdo = s;
        }

        private final void zzp(String s) {
            s.getClass();
            this.zzdl |= 8;
            this.zzdp = s;
        }

        private final void zzq(String s) {
            s.getClass();
            this.zzdl |= 16;
            this.zzdq = s;
        }

        private final void zzr(String s) {
            s.getClass();
            this.zzdl |= 0x400;
            this.zzdw = s;
        }

        public static com.google.android.gms.internal.ads.zzbm.zza.zza zzt() {
            return (com.google.android.gms.internal.ads.zzbm.zza.zza)zza.zzdy.zzbcz();
        }
    }

}

