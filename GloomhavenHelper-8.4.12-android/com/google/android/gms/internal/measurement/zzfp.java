package com.google.android.gms.internal.measurement;

public enum zzfp {
    VOID(Void.class, Void.class, null),
    INT(Integer.TYPE, Integer.class, 0),
    LONG(Long.TYPE, Long.class, 0L),
    FLOAT(Float.TYPE, Float.class, 0.0f),
    DOUBLE(Double.TYPE, Double.class, 0.0),
    BOOLEAN(Boolean.TYPE, Boolean.class, Boolean.FALSE),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzdv.class, zzdv.class, zzdv.zza),
    ENUM(Integer.TYPE, Integer.class, null),
    MESSAGE(Object.class, Object.class, null);

    private final Class zzk;
    private final Class zzl;
    private final Object zzm;

    private zzfp(Class class0, Class class1, Object object0) {
        this.zzk = class0;
        this.zzl = class1;
        this.zzm = object0;
    }

    public final Class zza() {
        return this.zzl;
    }
}

