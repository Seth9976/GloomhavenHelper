package com.google.android.gms.internal.ads;

public final class zzede {
    public static final class zza extends zzdyz implements zzeaj {
        public static final class com.google.android.gms.internal.ads.zzede.zza.zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
            private com.google.android.gms.internal.ads.zzede.zza.zza() {
                super(zza.zziau);
            }

            com.google.android.gms.internal.ads.zzede.zza.zza(zzedd zzedd0) {
            }
        }

        public static final class zzb extends zzdyz implements zzeaj {
            public static final class com.google.android.gms.internal.ads.zzede.zza.zzb.zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
                private com.google.android.gms.internal.ads.zzede.zza.zzb.zza() {
                    super(zzb.zziaz);
                }

                com.google.android.gms.internal.ads.zzede.zza.zzb.zza(zzedd zzedd0) {
                }
            }

            private int zzdl;
            private static volatile zzeau zzdz;
            private String zziav;
            private String zziaw;
            private String zziax;
            private int zziay;
            private static final zzb zziaz;

            static {
                zzb zzede$zza$zzb0 = new zzb();
                zzb.zziaz = zzede$zza$zzb0;
                zzdyz.zza(zzb.class, zzede$zza$zzb0);
            }

            private zzb() {
                this.zziav = "";
                this.zziaw = "";
                this.zziax = "";
            }

            @Override  // com.google.android.gms.internal.ads.zzdyz
            protected final Object zza(int v, Object object0, Object object1) {
                switch(zzedd.zzdk[v - 1]) {
                    case 1: {
                        return new zzb();
                    }
                    case 2: {
                        return new com.google.android.gms.internal.ads.zzede.zza.zzb.zza(null);
                    }
                    case 3: {
                        return zzb.zza(zzb.zziaz, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\u0004\u0003", new Object[]{"zzdl", "zziav", "zziaw", "zziax", "zziay"});
                    }
                    case 4: {
                        return zzb.zziaz;
                    }
                    case 5: {
                        zzeau zzeau0 = zzb.zzdz;
                        if(zzeau0 == null) {
                            Class class0 = zzb.class;
                            synchronized(class0) {
                                zzeau0 = zzb.zzdz;
                                if(zzeau0 == null) {
                                    zzeau0 = new zzc(zzb.zziaz);
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
        }

        public static enum com.google.android.gms.internal.ads.zzede.zza.zzc implements zzdzb {
            SAFE(0),
            DANGEROUS(1),
            UNKNOWN(2),
            POTENTIALLY_UNWANTED(3),
            DANGEROUS_HOST(4);

            private final int value;
            private static final zzdze zzen;

            static {
                com.google.android.gms.internal.ads.zzede.zza.zzc.zzen = new zzedf();
            }

            private com.google.android.gms.internal.ads.zzede.zza.zzc(int v1) {
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
                return zzedg.zzew;
            }

            public static com.google.android.gms.internal.ads.zzede.zza.zzc zzhe(int v) {
                switch(v) {
                    case 0: {
                        return com.google.android.gms.internal.ads.zzede.zza.zzc.zziba;
                    }
                    case 1: {
                        return com.google.android.gms.internal.ads.zzede.zza.zzc.zzibb;
                    }
                    case 2: {
                        return com.google.android.gms.internal.ads.zzede.zza.zzc.zzibc;
                    }
                    case 3: {
                        return com.google.android.gms.internal.ads.zzede.zza.zzc.zzibd;
                    }
                    case 4: {
                        return com.google.android.gms.internal.ads.zzede.zza.zzc.zzibe;
                    }
                    default: {
                        return null;
                    }
                }
            }
        }

        private int zzdl;
        private static volatile zzeau zzdz;
        private int zzian;
        private zzb zziao;
        private zzdxn zziap;
        private zzdxn zziaq;
        private boolean zziar;
        private boolean zzias;
        private byte zziat;
        private static final zza zziau;

        static {
            zza zzede$zza0 = new zza();
            zza.zziau = zzede$zza0;
            zzdyz.zza(zza.class, zzede$zza0);
        }

        private zza() {
            this.zziat = 2;
            this.zziap = zzdxn.zzhoe;
            this.zziaq = zzdxn.zzhoe;
        }

        @Override  // com.google.android.gms.internal.ads.zzdyz
        protected final Object zza(int v, Object object0, Object object1) {
            int v1 = 0;
            switch(zzedd.zzdk[v - 1]) {
                case 1: {
                    return new zza();
                }
                case 2: {
                    return new com.google.android.gms.internal.ads.zzede.zza.zza(null);
                }
                case 3: {
                    return zza.zza(zza.zziau, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0001\u0001Ԍ\u0000\u0002\t\u0001\u0003\n\u0002\u0004\n\u0003\u0005\u0007\u0004\u0006\u0007\u0005", new Object[]{"zzdl", "zzian", com.google.android.gms.internal.ads.zzede.zza.zzc.zzag(), "zziao", "zziap", "zziaq", "zziar", "zzias"});
                }
                case 4: {
                    return zza.zziau;
                }
                case 5: {
                    zzeau zzeau0 = zza.zzdz;
                    if(zzeau0 == null) {
                        synchronized(zza.class) {
                            zzeau0 = zza.zzdz;
                            if(zzeau0 == null) {
                                zzeau0 = new zzc(zza.zziau);
                                zza.zzdz = zzeau0;
                            }
                            return zzeau0;
                        }
                    }
                    return zzeau0;
                }
                case 6: {
                    return this.zziat;
                }
                case 7: {
                    if(object0 != null) {
                        v1 = 1;
                    }
                    this.zziat = (byte)v1;
                    return null;
                }
                default: {
                    throw new UnsupportedOperationException();
                }
            }
        }
    }

    public static final class com.google.android.gms.internal.ads.zzede.zzb extends zzdyz implements zzeaj {
        public static final class com.google.android.gms.internal.ads.zzede.zzb.zza extends zzdyz implements zzeaj {
            public static final class com.google.android.gms.internal.ads.zzede.zzb.zza.zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
                private com.google.android.gms.internal.ads.zzede.zzb.zza.zza() {
                    super(com.google.android.gms.internal.ads.zzede.zzb.zza.zzibx);
                }

                com.google.android.gms.internal.ads.zzede.zzb.zza.zza(zzedd zzedd0) {
                }

                public final com.google.android.gms.internal.ads.zzede.zzb.zza.zza zzhp(String s) {
                    if(this.zzhsv) {
                        this.zzbct();
                        this.zzhsv = false;
                    }
                    ((com.google.android.gms.internal.ads.zzede.zzb.zza)this.zzhsu).zzho(s);
                    return this;
                }
            }

            private int zzdl;
            private static volatile zzeau zzdz;
            private String zzibw;
            private static final com.google.android.gms.internal.ads.zzede.zzb.zza zzibx;

            static {
                com.google.android.gms.internal.ads.zzede.zzb.zza zzede$zzb$zza0 = new com.google.android.gms.internal.ads.zzede.zzb.zza();
                com.google.android.gms.internal.ads.zzede.zzb.zza.zzibx = zzede$zzb$zza0;
                zzdyz.zza(com.google.android.gms.internal.ads.zzede.zzb.zza.class, zzede$zzb$zza0);
            }

            private com.google.android.gms.internal.ads.zzede.zzb.zza() {
                this.zzibw = "";
            }

            @Override  // com.google.android.gms.internal.ads.zzdyz
            protected final Object zza(int v, Object object0, Object object1) {
                switch(zzedd.zzdk[v - 1]) {
                    case 1: {
                        return new com.google.android.gms.internal.ads.zzede.zzb.zza();
                    }
                    case 2: {
                        return new com.google.android.gms.internal.ads.zzede.zzb.zza.zza(null);
                    }
                    case 3: {
                        return com.google.android.gms.internal.ads.zzede.zzb.zza.zza(com.google.android.gms.internal.ads.zzede.zzb.zza.zzibx, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\b\u0000", new Object[]{"zzdl", "zzibw"});
                    }
                    case 4: {
                        return com.google.android.gms.internal.ads.zzede.zzb.zza.zzibx;
                    }
                    case 5: {
                        zzeau zzeau0 = com.google.android.gms.internal.ads.zzede.zzb.zza.zzdz;
                        if(zzeau0 == null) {
                            Class class0 = com.google.android.gms.internal.ads.zzede.zzb.zza.class;
                            synchronized(class0) {
                                zzeau0 = com.google.android.gms.internal.ads.zzede.zzb.zza.zzdz;
                                if(zzeau0 == null) {
                                    zzeau0 = new zzc(com.google.android.gms.internal.ads.zzede.zzb.zza.zzibx);
                                    com.google.android.gms.internal.ads.zzede.zzb.zza.zzdz = zzeau0;
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

            public static com.google.android.gms.internal.ads.zzede.zzb.zza.zza zzbfv() {
                return (com.google.android.gms.internal.ads.zzede.zzb.zza.zza)com.google.android.gms.internal.ads.zzede.zzb.zza.zzibx.zzbcz();
            }

            private final void zzho(String s) {
                s.getClass();
                this.zzdl |= 1;
                this.zzibw = s;
            }
        }

        public static final class com.google.android.gms.internal.ads.zzede.zzb.zzb extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
            private com.google.android.gms.internal.ads.zzede.zzb.zzb() {
                super(com.google.android.gms.internal.ads.zzede.zzb.zzibv);
            }

            com.google.android.gms.internal.ads.zzede.zzb.zzb(zzedd zzedd0) {
            }
        }

        public static final class com.google.android.gms.internal.ads.zzede.zzb.zzc extends zzdyz implements zzeaj {
            public static final class com.google.android.gms.internal.ads.zzede.zzb.zzc.zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
                private com.google.android.gms.internal.ads.zzede.zzb.zzc.zza() {
                    super(com.google.android.gms.internal.ads.zzede.zzb.zzc.zzibz);
                }

                com.google.android.gms.internal.ads.zzede.zzb.zzc.zza(zzedd zzedd0) {
                }

                public final com.google.android.gms.internal.ads.zzede.zzb.zzc.zza zzao(zzdxn zzdxn0) {
                    if(this.zzhsv) {
                        this.zzbct();
                        this.zzhsv = false;
                    }
                    ((com.google.android.gms.internal.ads.zzede.zzb.zzc)this.zzhsu).zzan(zzdxn0);
                    return this;
                }

                public final com.google.android.gms.internal.ads.zzede.zzb.zzc.zza zzap(zzdxn zzdxn0) {
                    if(this.zzhsv) {
                        this.zzbct();
                        this.zzhsv = false;
                    }
                    ((com.google.android.gms.internal.ads.zzede.zzb.zzc)this.zzhsu).zzae(zzdxn0);
                    return this;
                }
            }

            private int zzdl;
            private static volatile zzeau zzdz;
            private zzdxn zzhiz;
            private byte zziat;
            private zzdxn zziby;
            private static final com.google.android.gms.internal.ads.zzede.zzb.zzc zzibz;

            static {
                com.google.android.gms.internal.ads.zzede.zzb.zzc zzede$zzb$zzc0 = new com.google.android.gms.internal.ads.zzede.zzb.zzc();
                com.google.android.gms.internal.ads.zzede.zzb.zzc.zzibz = zzede$zzb$zzc0;
                zzdyz.zza(com.google.android.gms.internal.ads.zzede.zzb.zzc.class, zzede$zzb$zzc0);
            }

            private com.google.android.gms.internal.ads.zzede.zzb.zzc() {
                this.zziat = 2;
                this.zziby = zzdxn.zzhoe;
                this.zzhiz = zzdxn.zzhoe;
            }

            @Override  // com.google.android.gms.internal.ads.zzdyz
            protected final Object zza(int v, Object object0, Object object1) {
                int v1 = 0;
                switch(zzedd.zzdk[v - 1]) {
                    case 1: {
                        return new com.google.android.gms.internal.ads.zzede.zzb.zzc();
                    }
                    case 2: {
                        return new com.google.android.gms.internal.ads.zzede.zzb.zzc.zza(null);
                    }
                    case 3: {
                        return com.google.android.gms.internal.ads.zzede.zzb.zzc.zza(com.google.android.gms.internal.ads.zzede.zzb.zzc.zzibz, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001Ԋ\u0000\u0002\n\u0001", new Object[]{"zzdl", "zziby", "zzhiz"});
                    }
                    case 4: {
                        return com.google.android.gms.internal.ads.zzede.zzb.zzc.zzibz;
                    }
                    case 5: {
                        zzeau zzeau0 = com.google.android.gms.internal.ads.zzede.zzb.zzc.zzdz;
                        if(zzeau0 == null) {
                            synchronized(com.google.android.gms.internal.ads.zzede.zzb.zzc.class) {
                                zzeau0 = com.google.android.gms.internal.ads.zzede.zzb.zzc.zzdz;
                                if(zzeau0 == null) {
                                    zzeau0 = new zzc(com.google.android.gms.internal.ads.zzede.zzb.zzc.zzibz);
                                    com.google.android.gms.internal.ads.zzede.zzb.zzc.zzdz = zzeau0;
                                }
                                return zzeau0;
                            }
                        }
                        return zzeau0;
                    }
                    case 6: {
                        return this.zziat;
                    }
                    case 7: {
                        if(object0 != null) {
                            v1 = 1;
                        }
                        this.zziat = (byte)v1;
                        return null;
                    }
                    default: {
                        throw new UnsupportedOperationException();
                    }
                }
            }

            private final void zzae(zzdxn zzdxn0) {
                zzdxn0.getClass();
                this.zzdl |= 2;
                this.zzhiz = zzdxn0;
            }

            private final void zzan(zzdxn zzdxn0) {
                zzdxn0.getClass();
                this.zzdl |= 1;
                this.zziby = zzdxn0;
            }

            public static com.google.android.gms.internal.ads.zzede.zzb.zzc.zza zzbfx() {
                return (com.google.android.gms.internal.ads.zzede.zzb.zzc.zza)com.google.android.gms.internal.ads.zzede.zzb.zzc.zzibz.zzbcz();
            }
        }

        public static final class zzd extends zzdyz implements zzeaj {
            public static final class com.google.android.gms.internal.ads.zzede.zzb.zzd.zza extends zzdyz implements zzeaj {
                public static final class com.google.android.gms.internal.ads.zzede.zzb.zzd.zza.zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
                    private com.google.android.gms.internal.ads.zzede.zzb.zzd.zza.zza() {
                        super(com.google.android.gms.internal.ads.zzede.zzb.zzd.zza.zzicj);
                    }

                    com.google.android.gms.internal.ads.zzede.zzb.zzd.zza.zza(zzedd zzedd0) {
                    }
                }

                private int zzdl;
                private static volatile zzeau zzdz;
                private zzdxn zzicg;
                private zzdxn zzich;
                private zzdxn zzici;
                private static final com.google.android.gms.internal.ads.zzede.zzb.zzd.zza zzicj;

                static {
                    com.google.android.gms.internal.ads.zzede.zzb.zzd.zza zzede$zzb$zzd$zza0 = new com.google.android.gms.internal.ads.zzede.zzb.zzd.zza();
                    com.google.android.gms.internal.ads.zzede.zzb.zzd.zza.zzicj = zzede$zzb$zzd$zza0;
                    zzdyz.zza(com.google.android.gms.internal.ads.zzede.zzb.zzd.zza.class, zzede$zzb$zzd$zza0);
                }

                private com.google.android.gms.internal.ads.zzede.zzb.zzd.zza() {
                    this.zzicg = zzdxn.zzhoe;
                    this.zzich = zzdxn.zzhoe;
                    this.zzici = zzdxn.zzhoe;
                }

                @Override  // com.google.android.gms.internal.ads.zzdyz
                protected final Object zza(int v, Object object0, Object object1) {
                    switch(zzedd.zzdk[v - 1]) {
                        case 1: {
                            return new com.google.android.gms.internal.ads.zzede.zzb.zzd.zza();
                        }
                        case 2: {
                            return new com.google.android.gms.internal.ads.zzede.zzb.zzd.zza.zza(null);
                        }
                        case 3: {
                            return com.google.android.gms.internal.ads.zzede.zzb.zzd.zza.zza(com.google.android.gms.internal.ads.zzede.zzb.zzd.zza.zzicj, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\n\u0000\u0002\n\u0001\u0003\n\u0002", new Object[]{"zzdl", "zzicg", "zzich", "zzici"});
                        }
                        case 4: {
                            return com.google.android.gms.internal.ads.zzede.zzb.zzd.zza.zzicj;
                        }
                        case 5: {
                            zzeau zzeau0 = com.google.android.gms.internal.ads.zzede.zzb.zzd.zza.zzdz;
                            if(zzeau0 == null) {
                                Class class0 = com.google.android.gms.internal.ads.zzede.zzb.zzd.zza.class;
                                synchronized(class0) {
                                    zzeau0 = com.google.android.gms.internal.ads.zzede.zzb.zzd.zza.zzdz;
                                    if(zzeau0 == null) {
                                        zzeau0 = new zzc(com.google.android.gms.internal.ads.zzede.zzb.zzd.zza.zzicj);
                                        com.google.android.gms.internal.ads.zzede.zzb.zzd.zza.zzdz = zzeau0;
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
            }

            public static final class com.google.android.gms.internal.ads.zzede.zzb.zzd.zzb extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
                private com.google.android.gms.internal.ads.zzede.zzb.zzd.zzb() {
                    super(zzd.zzicf);
                }

                com.google.android.gms.internal.ads.zzede.zzb.zzd.zzb(zzedd zzedd0) {
                }
            }

            private int zzdl;
            private static volatile zzeau zzdz;
            private byte zziat;
            private com.google.android.gms.internal.ads.zzede.zzb.zzd.zza zzica;
            private zzdzi zzicb;
            private zzdxn zzicc;
            private zzdxn zzicd;
            private int zzice;
            private static final zzd zzicf;

            static {
                zzd zzede$zzb$zzd0 = new zzd();
                zzd.zzicf = zzede$zzb$zzd0;
                zzdyz.zza(zzd.class, zzede$zzb$zzd0);
            }

            private zzd() {
                this.zziat = 2;
                this.zzicb = zzd.zzbdc();
                this.zzicc = zzdxn.zzhoe;
                this.zzicd = zzdxn.zzhoe;
            }

            @Override  // com.google.android.gms.internal.ads.zzdyz
            protected final Object zza(int v, Object object0, Object object1) {
                int v1 = 0;
                switch(zzedd.zzdk[v - 1]) {
                    case 1: {
                        return new zzd();
                    }
                    case 2: {
                        return new com.google.android.gms.internal.ads.zzede.zzb.zzd.zzb(null);
                    }
                    case 3: {
                        return zzd.zza(zzd.zzicf, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0001\u0001\t\u0000\u0002Л\u0003\n\u0001\u0004\n\u0002\u0005\u0004\u0003", new Object[]{"zzdl", "zzica", "zzicb", com.google.android.gms.internal.ads.zzede.zzb.zzc.class, "zzicc", "zzicd", "zzice"});
                    }
                    case 4: {
                        return zzd.zzicf;
                    }
                    case 5: {
                        zzeau zzeau0 = zzd.zzdz;
                        if(zzeau0 == null) {
                            synchronized(zzd.class) {
                                zzeau0 = zzd.zzdz;
                                if(zzeau0 == null) {
                                    zzeau0 = new zzc(zzd.zzicf);
                                    zzd.zzdz = zzeau0;
                                }
                                return zzeau0;
                            }
                        }
                        return zzeau0;
                    }
                    case 6: {
                        return this.zziat;
                    }
                    case 7: {
                        if(object0 != null) {
                            v1 = 1;
                        }
                        this.zziat = (byte)v1;
                        return null;
                    }
                    default: {
                        throw new UnsupportedOperationException();
                    }
                }
            }
        }

        public static final class zze extends zzdyz implements zzeaj {
            public static final class com.google.android.gms.internal.ads.zzede.zzb.zze.zza extends zzdyz implements zzeaj {
                public static final class com.google.android.gms.internal.ads.zzede.zzb.zze.zza.zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
                    private com.google.android.gms.internal.ads.zzede.zzb.zze.zza.zza() {
                        super(com.google.android.gms.internal.ads.zzede.zzb.zze.zza.zzicp);
                    }

                    com.google.android.gms.internal.ads.zzede.zzb.zze.zza.zza(zzedd zzedd0) {
                    }
                }

                private int zzdl;
                private static volatile zzeau zzdz;
                private zzdxn zzici;
                private int zzicn;
                private zzdxn zzico;
                private static final com.google.android.gms.internal.ads.zzede.zzb.zze.zza zzicp;

                static {
                    com.google.android.gms.internal.ads.zzede.zzb.zze.zza zzede$zzb$zze$zza0 = new com.google.android.gms.internal.ads.zzede.zzb.zze.zza();
                    com.google.android.gms.internal.ads.zzede.zzb.zze.zza.zzicp = zzede$zzb$zze$zza0;
                    zzdyz.zza(com.google.android.gms.internal.ads.zzede.zzb.zze.zza.class, zzede$zzb$zze$zza0);
                }

                private com.google.android.gms.internal.ads.zzede.zzb.zze.zza() {
                    this.zzico = zzdxn.zzhoe;
                    this.zzici = zzdxn.zzhoe;
                }

                @Override  // com.google.android.gms.internal.ads.zzdyz
                protected final Object zza(int v, Object object0, Object object1) {
                    switch(zzedd.zzdk[v - 1]) {
                        case 1: {
                            return new com.google.android.gms.internal.ads.zzede.zzb.zze.zza();
                        }
                        case 2: {
                            return new com.google.android.gms.internal.ads.zzede.zzb.zze.zza.zza(null);
                        }
                        case 3: {
                            return com.google.android.gms.internal.ads.zzede.zzb.zze.zza.zza(com.google.android.gms.internal.ads.zzede.zzb.zze.zza.zzicp, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0000\u0002\n\u0001\u0003\n\u0002", new Object[]{"zzdl", "zzicn", "zzico", "zzici"});
                        }
                        case 4: {
                            return com.google.android.gms.internal.ads.zzede.zzb.zze.zza.zzicp;
                        }
                        case 5: {
                            zzeau zzeau0 = com.google.android.gms.internal.ads.zzede.zzb.zze.zza.zzdz;
                            if(zzeau0 == null) {
                                Class class0 = com.google.android.gms.internal.ads.zzede.zzb.zze.zza.class;
                                synchronized(class0) {
                                    zzeau0 = com.google.android.gms.internal.ads.zzede.zzb.zze.zza.zzdz;
                                    if(zzeau0 == null) {
                                        zzeau0 = new zzc(com.google.android.gms.internal.ads.zzede.zzb.zze.zza.zzicp);
                                        com.google.android.gms.internal.ads.zzede.zzb.zze.zza.zzdz = zzeau0;
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
            }

            public static final class com.google.android.gms.internal.ads.zzede.zzb.zze.zzb extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
                private com.google.android.gms.internal.ads.zzede.zzb.zze.zzb() {
                    super(zze.zzicm);
                }

                com.google.android.gms.internal.ads.zzede.zzb.zze.zzb(zzedd zzedd0) {
                }
            }

            private int zzdl;
            private static volatile zzeau zzdz;
            private byte zziat;
            private zzdzi zzicb;
            private zzdxn zzicc;
            private zzdxn zzicd;
            private int zzice;
            private com.google.android.gms.internal.ads.zzede.zzb.zze.zza zzick;
            private zzdxn zzicl;
            private static final zze zzicm;

            static {
                zze zzede$zzb$zze0 = new zze();
                zze.zzicm = zzede$zzb$zze0;
                zzdyz.zza(zze.class, zzede$zzb$zze0);
            }

            private zze() {
                this.zziat = 2;
                this.zzicb = zze.zzbdc();
                this.zzicc = zzdxn.zzhoe;
                this.zzicd = zzdxn.zzhoe;
                this.zzicl = zzdxn.zzhoe;
            }

            @Override  // com.google.android.gms.internal.ads.zzdyz
            protected final Object zza(int v, Object object0, Object object1) {
                int v1 = 0;
                switch(zzedd.zzdk[v - 1]) {
                    case 1: {
                        return new zze();
                    }
                    case 2: {
                        return new com.google.android.gms.internal.ads.zzede.zzb.zze.zzb(null);
                    }
                    case 3: {
                        return zze.zza(zze.zzicm, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0001\u0001\t\u0000\u0002Л\u0003\n\u0001\u0004\n\u0002\u0005\u0004\u0003\u0006\n\u0004", new Object[]{"zzdl", "zzick", "zzicb", com.google.android.gms.internal.ads.zzede.zzb.zzc.class, "zzicc", "zzicd", "zzice", "zzicl"});
                    }
                    case 4: {
                        return zze.zzicm;
                    }
                    case 5: {
                        zzeau zzeau0 = zze.zzdz;
                        if(zzeau0 == null) {
                            synchronized(zze.class) {
                                zzeau0 = zze.zzdz;
                                if(zzeau0 == null) {
                                    zzeau0 = new zzc(zze.zzicm);
                                    zze.zzdz = zzeau0;
                                }
                                return zzeau0;
                            }
                        }
                        return zzeau0;
                    }
                    case 6: {
                        return this.zziat;
                    }
                    case 7: {
                        if(object0 != null) {
                            v1 = 1;
                        }
                        this.zziat = (byte)v1;
                        return null;
                    }
                    default: {
                        throw new UnsupportedOperationException();
                    }
                }
            }
        }

        public static final class zzf extends zzdyz implements zzeaj {
            public static enum com.google.android.gms.internal.ads.zzede.zzb.zzf.zza implements zzdzb {
                TYPE_UNKNOWN(0),
                TYPE_CREATIVE(1);

                private final int value;
                private static final zzdze zzen;

                static {
                    com.google.android.gms.internal.ads.zzede.zzb.zzf.zza.zzen = new zzedi();
                }

                private com.google.android.gms.internal.ads.zzede.zzb.zzf.zza(int v1) {
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
                    return zzedh.zzew;
                }

                public static com.google.android.gms.internal.ads.zzede.zzb.zzf.zza zzhf(int v) {
                    switch(v) {
                        case 0: {
                            return com.google.android.gms.internal.ads.zzede.zzb.zzf.zza.zzict;
                        }
                        case 1: {
                            return com.google.android.gms.internal.ads.zzede.zzb.zzf.zza.zzicu;
                        }
                        default: {
                            return null;
                        }
                    }
                }
            }

            public static final class com.google.android.gms.internal.ads.zzede.zzb.zzf.zzb extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
                private com.google.android.gms.internal.ads.zzede.zzb.zzf.zzb() {
                    super(zzf.zzics);
                }

                com.google.android.gms.internal.ads.zzede.zzb.zzf.zzb(zzedd zzedd0) {
                }
            }

            private int zzbvh;
            private int zzdl;
            private static volatile zzeau zzdz;
            private String zzicq;
            private zzdxn zzicr;
            private static final zzf zzics;

            static {
                zzf zzede$zzb$zzf0 = new zzf();
                zzf.zzics = zzede$zzb$zzf0;
                zzdyz.zza(zzf.class, zzede$zzb$zzf0);
            }

            private zzf() {
                this.zzicq = "";
                this.zzicr = zzdxn.zzhoe;
            }

            @Override  // com.google.android.gms.internal.ads.zzdyz
            protected final Object zza(int v, Object object0, Object object1) {
                switch(zzedd.zzdk[v - 1]) {
                    case 1: {
                        return new zzf();
                    }
                    case 2: {
                        return new com.google.android.gms.internal.ads.zzede.zzb.zzf.zzb(null);
                    }
                    case 3: {
                        return zzf.zza(zzf.zzics, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\b\u0001\u0003\n\u0002", new Object[]{"zzdl", "zzbvh", com.google.android.gms.internal.ads.zzede.zzb.zzf.zza.zzag(), "zzicq", "zzicr"});
                    }
                    case 4: {
                        return zzf.zzics;
                    }
                    case 5: {
                        zzeau zzeau0 = zzf.zzdz;
                        if(zzeau0 == null) {
                            Class class0 = zzf.class;
                            synchronized(class0) {
                                zzeau0 = zzf.zzdz;
                                if(zzeau0 == null) {
                                    zzeau0 = new zzc(zzf.zzics);
                                    zzf.zzdz = zzeau0;
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
        }

        public static enum zzg implements zzdzb {
            UNKNOWN(0),
            URL_PHISHING(1),
            URL_MALWARE(2),
            URL_UNWANTED(3),
            CLIENT_SIDE_PHISHING_URL(4),
            CLIENT_SIDE_MALWARE_URL(5),
            DANGEROUS_DOWNLOAD_RECOVERY(6),
            DANGEROUS_DOWNLOAD_WARNING(7),
            OCTAGON_AD(8),
            OCTAGON_AD_SB_MATCH(9);

            private final int value;
            private static final zzdze zzen;

            static {
                zzg.zzen = new zzedj();
            }

            private zzg(int v1) {
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
                return zzedk.zzew;
            }

            public static zzg zzhg(int v) {
                switch(v) {
                    case 0: {
                        return zzg.zzicw;
                    }
                    case 1: {
                        return zzg.zzicx;
                    }
                    case 2: {
                        return zzg.zzicy;
                    }
                    case 3: {
                        return zzg.zzicz;
                    }
                    case 4: {
                        return zzg.zzida;
                    }
                    case 5: {
                        return zzg.zzidb;
                    }
                    case 6: {
                        return zzg.zzidc;
                    }
                    case 7: {
                        return zzg.zzidd;
                    }
                    case 8: {
                        return zzg.zzide;
                    }
                    case 9: {
                        return zzg.zzidf;
                    }
                    default: {
                        return null;
                    }
                }
            }
        }

        public static final class zzh extends zzdyz implements zzeaj {
            public static enum com.google.android.gms.internal.ads.zzede.zzb.zzh.zza implements zzdzb {
                AD_RESOURCE_UNKNOWN(0),
                AD_RESOURCE_CREATIVE(1),
                AD_RESOURCE_POST_CLICK(2),
                AD_RESOURCE_AUTO_CLICK_DESTINATION(3);

                private final int value;
                private static final zzdze zzen;

                static {
                    com.google.android.gms.internal.ads.zzede.zzb.zzh.zza.zzen = new zzedl();
                }

                private com.google.android.gms.internal.ads.zzede.zzb.zzh.zza(int v1) {
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
                    return zzedm.zzew;
                }

                public static com.google.android.gms.internal.ads.zzede.zzb.zzh.zza zzhh(int v) {
                    switch(v) {
                        case 0: {
                            return com.google.android.gms.internal.ads.zzede.zzb.zzh.zza.zzidq;
                        }
                        case 1: {
                            return com.google.android.gms.internal.ads.zzede.zzb.zzh.zza.zzidr;
                        }
                        case 2: {
                            return com.google.android.gms.internal.ads.zzede.zzb.zzh.zza.zzids;
                        }
                        case 3: {
                            return com.google.android.gms.internal.ads.zzede.zzb.zzh.zza.zzidt;
                        }
                        default: {
                            return null;
                        }
                    }
                }
            }

            public static final class com.google.android.gms.internal.ads.zzede.zzb.zzh.zzb extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
                private com.google.android.gms.internal.ads.zzede.zzb.zzh.zzb() {
                    super(zzh.zzidp);
                }

                com.google.android.gms.internal.ads.zzede.zzb.zzh.zzb(zzedd zzedd0) {
                }
            }

            private int zzdl;
            private static volatile zzeau zzdz;
            private byte zziat;
            private String zziaw;
            private int zzidh;
            private zzd zzidi;
            private zze zzidj;
            private int zzidk;
            private zzdzg zzidl;
            private String zzidm;
            private int zzidn;
            private zzdzi zzido;
            private static final zzh zzidp;

            static {
                zzh zzede$zzb$zzh0 = new zzh();
                zzh.zzidp = zzede$zzb$zzh0;
                zzdyz.zza(zzh.class, zzede$zzb$zzh0);
            }

            private zzh() {
                this.zziat = 2;
                this.zziaw = "";
                this.zzidl = zzh.zzbdb();
                this.zzidm = "";
                this.zzido = zzdyz.zzbdc();
            }

            @Override  // com.google.android.gms.internal.ads.zzdyz
            protected final Object zza(int v, Object object0, Object object1) {
                int v1 = 0;
                switch(zzedd.zzdk[v - 1]) {
                    case 1: {
                        return new zzh();
                    }
                    case 2: {
                        return new com.google.android.gms.internal.ads.zzede.zzb.zzh.zzb(null);
                    }
                    case 3: {
                        return zzh.zza(zzh.zzidp, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0002\u0003\u0001Ԅ\u0000\u0002\b\u0001\u0003Љ\u0002\u0004Љ\u0003\u0005\u0004\u0004\u0006\u0016\u0007\b\u0005\b\f\u0006\t\u001A", new Object[]{"zzdl", "zzidh", "zziaw", "zzidi", "zzidj", "zzidk", "zzidl", "zzidm", "zzidn", com.google.android.gms.internal.ads.zzede.zzb.zzh.zza.zzag(), "zzido"});
                    }
                    case 4: {
                        return zzh.zzidp;
                    }
                    case 5: {
                        zzeau zzeau0 = zzh.zzdz;
                        if(zzeau0 == null) {
                            synchronized(zzh.class) {
                                zzeau0 = zzh.zzdz;
                                if(zzeau0 == null) {
                                    zzeau0 = new zzc(zzh.zzidp);
                                    zzh.zzdz = zzeau0;
                                }
                                return zzeau0;
                            }
                        }
                        return zzeau0;
                    }
                    case 6: {
                        return this.zziat;
                    }
                    case 7: {
                        if(object0 != null) {
                            v1 = 1;
                        }
                        this.zziat = (byte)v1;
                        return null;
                    }
                    default: {
                        throw new UnsupportedOperationException();
                    }
                }
            }
        }

        public static final class zzi extends zzdyz implements zzeaj {
            public static final class com.google.android.gms.internal.ads.zzede.zzb.zzi.zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
                private com.google.android.gms.internal.ads.zzede.zzb.zzi.zza() {
                    super(zzi.zzidy);
                }

                com.google.android.gms.internal.ads.zzede.zzb.zzi.zza(zzedd zzedd0) {
                }

                public final com.google.android.gms.internal.ads.zzede.zzb.zzi.zza zzbt(boolean z) {
                    if(this.zzhsv) {
                        this.zzbct();
                        this.zzhsv = false;
                    }
                    ((zzi)this.zzhsu).zzbu(z);
                    return this;
                }

                public final com.google.android.gms.internal.ads.zzede.zzb.zzi.zza zzfu(long v) {
                    if(this.zzhsv) {
                        this.zzbct();
                        this.zzhsv = false;
                    }
                    ((zzi)this.zzhsu).zzfv(v);
                    return this;
                }

                public final com.google.android.gms.internal.ads.zzede.zzb.zzi.zza zzhq(String s) {
                    if(this.zzhsv) {
                        this.zzbct();
                        this.zzhsv = false;
                    }
                    ((zzi)this.zzhsu).zzhr(s);
                    return this;
                }
            }

            private int zzdl;
            private static volatile zzeau zzdz;
            private String zzidv;
            private long zzidw;
            private boolean zzidx;
            private static final zzi zzidy;

            static {
                zzi zzede$zzb$zzi0 = new zzi();
                zzi.zzidy = zzede$zzb$zzi0;
                zzdyz.zza(zzi.class, zzede$zzb$zzi0);
            }

            private zzi() {
                this.zzidv = "";
            }

            @Override  // com.google.android.gms.internal.ads.zzdyz
            protected final Object zza(int v, Object object0, Object object1) {
                switch(zzedd.zzdk[v - 1]) {
                    case 1: {
                        return new zzi();
                    }
                    case 2: {
                        return new com.google.android.gms.internal.ads.zzede.zzb.zzi.zza(null);
                    }
                    case 3: {
                        return zzi.zza(zzi.zzidy, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\b\u0000\u0002\u0002\u0001\u0003\u0007\u0002", new Object[]{"zzdl", "zzidv", "zzidw", "zzidx"});
                    }
                    case 4: {
                        return zzi.zzidy;
                    }
                    case 5: {
                        zzeau zzeau0 = zzi.zzdz;
                        if(zzeau0 == null) {
                            Class class0 = zzi.class;
                            synchronized(class0) {
                                zzeau0 = zzi.zzdz;
                                if(zzeau0 == null) {
                                    zzeau0 = new zzc(zzi.zzidy);
                                    zzi.zzdz = zzeau0;
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

            public static com.google.android.gms.internal.ads.zzede.zzb.zzi.zza zzbgf() {
                return (com.google.android.gms.internal.ads.zzede.zzb.zzi.zza)zzi.zzidy.zzbcz();
            }

            private final void zzbu(boolean z) {
                this.zzdl |= 4;
                this.zzidx = z;
            }

            private final void zzfv(long v) {
                this.zzdl |= 2;
                this.zzidw = v;
            }

            private final void zzhr(String s) {
                s.getClass();
                this.zzdl |= 1;
                this.zzidv = s;
            }
        }

        private int zzbvh;
        private int zzdl;
        private static volatile zzeau zzdz;
        private zzdxn zziap;
        private byte zziat;
        private String zziaw;
        private int zzibg;
        private String zzibh;
        private String zzibi;
        private com.google.android.gms.internal.ads.zzede.zzb.zza zzibj;
        private zzdzi zzibk;
        private String zzibl;
        private zzf zzibm;
        private boolean zzibn;
        private zzdzi zzibo;
        private String zzibp;
        private boolean zzibq;
        private boolean zzibr;
        private zzi zzibs;
        private zzdzi zzibt;
        private zzdzi zzibu;
        private static final com.google.android.gms.internal.ads.zzede.zzb zzibv;

        static {
            com.google.android.gms.internal.ads.zzede.zzb zzede$zzb0 = new com.google.android.gms.internal.ads.zzede.zzb();
            com.google.android.gms.internal.ads.zzede.zzb.zzibv = zzede$zzb0;
            zzdyz.zza(com.google.android.gms.internal.ads.zzede.zzb.class, zzede$zzb0);
        }

        private com.google.android.gms.internal.ads.zzede.zzb() {
            this.zziat = 2;
            this.zziaw = "";
            this.zzibh = "";
            this.zzibi = "";
            this.zzibk = com.google.android.gms.internal.ads.zzede.zzb.zzbdc();
            this.zzibl = "";
            this.zzibo = zzdyz.zzbdc();
            this.zzibp = "";
            this.zziap = zzdxn.zzhoe;
            this.zzibt = zzdyz.zzbdc();
            this.zzibu = zzdyz.zzbdc();
        }

        @Override  // com.google.android.gms.internal.ads.zzdyz
        protected final Object zza(int v, Object object0, Object object1) {
            int v1 = 0;
            switch(zzedd.zzdk[v - 1]) {
                case 1: {
                    return new com.google.android.gms.internal.ads.zzede.zzb();
                }
                case 2: {
                    return new com.google.android.gms.internal.ads.zzede.zzb.zzb(null);
                }
                case 3: {
                    return com.google.android.gms.internal.ads.zzede.zzb.zza(com.google.android.gms.internal.ads.zzede.zzb.zzibv, "\u0001\u0012\u0000\u0001\u0001\u0015\u0012\u0000\u0004\u0001\u0001\b\u0002\u0002\b\u0003\u0003\b\u0004\u0004Л\u0005\u0007\b\u0006\u001A\u0007\b\t\b\u0007\n\t\u0007\u000B\n\f\u0000\u000B\f\u0001\f\t\u0005\r\b\u0006\u000E\t\u0007\u000F\n\f\u0011\t\r\u0014\u001A\u0015\u001A", new Object[]{"zzdl", "zziaw", "zzibh", "zzibi", "zzibk", zzh.class, "zzibn", "zzibo", "zzibp", "zzibq", "zzibr", "zzbvh", zzg.zzag(), "zzibg", com.google.android.gms.internal.ads.zzede.zza.zzc.zzag(), "zzibj", "zzibl", "zzibm", "zziap", "zzibs", "zzibt", "zzibu"});
                }
                case 4: {
                    return com.google.android.gms.internal.ads.zzede.zzb.zzibv;
                }
                case 5: {
                    zzeau zzeau0 = com.google.android.gms.internal.ads.zzede.zzb.zzdz;
                    if(zzeau0 == null) {
                        synchronized(com.google.android.gms.internal.ads.zzede.zzb.class) {
                            zzeau0 = com.google.android.gms.internal.ads.zzede.zzb.zzdz;
                            if(zzeau0 == null) {
                                zzeau0 = new zzc(com.google.android.gms.internal.ads.zzede.zzb.zzibv);
                                com.google.android.gms.internal.ads.zzede.zzb.zzdz = zzeau0;
                            }
                            return zzeau0;
                        }
                    }
                    return zzeau0;
                }
                case 6: {
                    return this.zziat;
                }
                case 7: {
                    if(object0 != null) {
                        v1 = 1;
                    }
                    this.zziat = (byte)v1;
                    return null;
                }
                default: {
                    throw new UnsupportedOperationException();
                }
            }
        }
    }

}

