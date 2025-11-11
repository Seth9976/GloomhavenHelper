package com.google.android.gms.internal.ads;

public class zzaax {
    private final String zzcc;
    private final Object zzcgl;
    private final int zzcti;

    protected zzaax(String s, Object object0, int v) {
        this.zzcc = s;
        this.zzcgl = object0;
        this.zzcti = v;
    }

    public Object get() {
        zzabx zzabx0 = zzabw.zzra();
        if(zzabx0 != null) {
            switch(zzaaw.zzcth[this.zzcti - 1]) {
                case 1: {
                    return zzabx0.zze(this.zzcc, ((Boolean)this.zzcgl).booleanValue());
                }
                case 2: {
                    return zzabx0.getLong(this.zzcc, ((long)(((Long)this.zzcgl))));
                }
                case 3: {
                    return zzabx0.zza(this.zzcc, ((double)(((Double)this.zzcgl))));
                }
                case 4: {
                    return zzabx0.get(this.zzcc, ((String)this.zzcgl));
                }
                default: {
                    throw new IllegalStateException();
                }
            }
        }
        throw new IllegalStateException("Flag is not initialized.");
    }

    public static zzaax zzb(String s, long v) {
        return new zzaax(s, v, zzaaz.zzctk);
    }

    public static zzaax zzf(String s, boolean z) {
        return new zzaax(s, Boolean.valueOf(z), zzaaz.zzctj);
    }

    public static zzaax zzi(String s, String s1) {
        return new zzaax(s, s1, zzaaz.zzctm);
    }
}

