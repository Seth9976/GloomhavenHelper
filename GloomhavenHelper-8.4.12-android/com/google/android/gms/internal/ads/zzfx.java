package com.google.android.gms.internal.ads;

public final class zzfx extends zzdyz implements zzeaj {
    public static final class zza extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
        private zza() {
            super(zzfx.zzaaq);
        }

        zza(zzfz zzfz0) {
        }
    }

    private zzgb zzaan;
    private zzdxn zzaao;
    private zzdxn zzaap;
    private static final zzfx zzaaq;
    private int zzdl;
    private static volatile zzeau zzdz;

    static {
        zzfx zzfx0 = new zzfx();
        zzfx.zzaaq = zzfx0;
        zzdyz.zza(zzfx.class, zzfx0);
    }

    private zzfx() {
        this.zzaao = zzdxn.zzhoe;
        this.zzaap = zzdxn.zzhoe;
    }

    public static zzfx zza(zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return (zzfx)zzdyz.zza(zzfx.zzaaq, zzdxn0, zzdym0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdyz
    protected final Object zza(int v, Object object0, Object object1) {
        switch(zzfz.zzdk[v - 1]) {
            case 1: {
                return new zzfx();
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zzfx.zza(zzfx.zzaaq, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0000\u0002\n\u0001\u0003\n\u0002", new Object[]{"zzdl", "zzaan", "zzaao", "zzaap"});
            }
            case 4: {
                return zzfx.zzaaq;
            }
            case 5: {
                zzeau zzeau0 = zzfx.zzdz;
                if(zzeau0 == null) {
                    Class class0 = zzfx.class;
                    synchronized(class0) {
                        zzeau0 = zzfx.zzdz;
                        if(zzeau0 == null) {
                            zzeau0 = new zzc(zzfx.zzaaq);
                            zzfx.zzdz = zzeau0;
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

    public final zzgb zzcy() {
        return this.zzaan == null ? zzgb.zzdi() : this.zzaan;
    }

    public final zzdxn zzcz() {
        return this.zzaao;
    }

    public final zzdxn zzda() {
        return this.zzaap;
    }
}

