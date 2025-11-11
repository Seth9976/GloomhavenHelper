package com.google.android.gms.internal.ads;

public final class zzhf {
    public static final zzhf zzagj;
    public final float zzagk;
    public final float zzagl;
    private final int zzagm;

    static {
        zzhf.zzagj = new zzhf(1.0f, 1.0f);
    }

    public zzhf(float f, float f1) {
        this.zzagk = f;
        this.zzagl = f1;
        this.zzagm = Math.round(f * 1000.0f);
    }

    @Override
    public final boolean equals(Object object0) {
        return this == object0 ? true : object0 != null && this.getClass() == object0.getClass() && this.zzagk == ((zzhf)object0).zzagk && this.zzagl == ((zzhf)object0).zzagl;
    }

    @Override
    public final int hashCode() {
        return (Float.floatToRawIntBits(this.zzagk) + 0x20F) * 0x1F + Float.floatToRawIntBits(this.zzagl);
    }

    public final long zzdu(long v) {
        return v * ((long)this.zzagm);
    }
}

