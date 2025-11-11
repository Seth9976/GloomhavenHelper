package com.google.android.gms.internal.ads;

public enum zzdzj {
    VOID(Void.class, Void.class, null),
    INT(Integer.TYPE, Integer.class, 0),
    LONG(Long.TYPE, Long.class, 0L),
    FLOAT(Float.TYPE, Float.class, 0.0f),
    DOUBLE(Double.TYPE, Double.class, 0.0),
    BOOLEAN(Boolean.TYPE, Boolean.class, Boolean.FALSE),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzdxn.class, zzdxn.class, zzdxn.zzhoe),
    ENUM(Integer.TYPE, Integer.class, null),
    MESSAGE(Object.class, Object.class, null);

    private final Class zzhue;
    private final Class zzhuf;
    private final Object zzhug;

    private zzdzj(Class class0, Class class1, Object object0) {
        this.zzhue = class0;
        this.zzhuf = class1;
        this.zzhug = object0;
    }

    public final Class zzbdr() {
        return this.zzhuf;
    }
}

