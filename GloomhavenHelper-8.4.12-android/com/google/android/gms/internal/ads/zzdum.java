package com.google.android.gms.internal.ads;

import java.util.List;

public final class zzdum extends zzdyz implements zzeaj {
    public static final class zza extends zzdyz implements zzeaj {
        public static final class com.google.android.gms.internal.ads.zzdum.zza.zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
            private com.google.android.gms.internal.ads.zzdum.zza.zza() {
                super(zza.zzhkc);
            }

            com.google.android.gms.internal.ads.zzdum.zza.zza(zzdul zzdul0) {
            }
        }

        private static volatile zzeau zzdz;
        private int zzhjp;
        private zzduc zzhjz;
        private int zzhka;
        private int zzhkb;
        private static final zza zzhkc;

        static {
            zza zzdum$zza0 = new zza();
            zza.zzhkc = zzdum$zza0;
            zzdyz.zza(zza.class, zzdum$zza0);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyz
        protected final Object zza(int v, Object object0, Object object1) {
            switch(zzdul.zzdk[v - 1]) {
                case 1: {
                    return new zza();
                }
                case 2: {
                    return new com.google.android.gms.internal.ads.zzdum.zza.zza(null);
                }
                case 3: {
                    return zza.zza(zza.zzhkc, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0002\f\u0003\u000B\u0004\f", new Object[]{"zzhjz", "zzhka", "zzhkb", "zzhjp"});
                }
                case 4: {
                    return zza.zzhkc;
                }
                case 5: {
                    zzeau zzeau0 = zza.zzdz;
                    if(zzeau0 == null) {
                        Class class0 = zza.class;
                        synchronized(class0) {
                            zzeau0 = zza.zzdz;
                            if(zzeau0 == null) {
                                zzeau0 = new zzc(zza.zzhkc);
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

        public final zzdug zzavi() {
            zzdug zzdug0 = zzdug.zzep(this.zzhka);
            return zzdug0 == null ? zzdug.zzhjn : zzdug0;
        }

        public final zzduy zzavj() {
            zzduy zzduy0 = zzduy.zzew(this.zzhjp);
            return zzduy0 == null ? zzduy.zzhku : zzduy0;
        }

        public final boolean zzayy() {
            return this.zzhjz != null;
        }

        public final zzduc zzayz() {
            return this.zzhjz == null ? zzduc.zzayl() : this.zzhjz;
        }

        public final int zzaza() {
            return this.zzhkb;
        }
    }

    public static final class zzb extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zzb() {
            super(zzdum.zzhjy);
        }

        zzb(zzdul zzdul0) {
        }
    }

    private static volatile zzeau zzdz;
    private int zzhjw;
    private zzdzi zzhjx;
    private static final zzdum zzhjy;

    static {
        zzdum zzdum0 = new zzdum();
        zzdum.zzhjy = zzdum0;
        zzdyz.zza(zzdum.class, zzdum0);
    }

    private zzdum() {
        this.zzhjx = zzdum.zzbdc();
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzdul.zzdk[v - 1]) {
            case 1: {
                return new zzdum();
            }
            case 2: {
                return new zzb(null);
            }
            case 3: {
                return zzdum.zza(zzdum.zzhjy, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000B\u0002\u001B", new Object[]{"zzhjw", "zzhjx", zza.class});
            }
            case 4: {
                return zzdum.zzhjy;
            }
            case 5: {
                zzeau zzeau0 = zzdum.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzdum.class;
                    synchronized(class0) {
                        zzeau0 = zzdum.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzdum.zzhjy);
                            zzdum.zzdz = zzeau0;
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

    public final int zzayu() {
        return this.zzhjw;
    }

    public final List zzayv() {
        return this.zzhjx;
    }

    public final int zzayw() {
        return this.zzhjx.size();
    }

    public static zzdum zzc(byte[] arr_b, zzdym zzdym0) throws zzdzh {
        return (zzdum)zzdyz.zza(zzdum.zzhjy, arr_b, zzdym0);
    }
}

