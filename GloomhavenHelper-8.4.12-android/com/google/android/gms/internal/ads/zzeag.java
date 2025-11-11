package com.google.android.gms.internal.ads;

final class zzeag {
    private static final zzeae zzhvc;
    private static final zzeae zzhvd;

    static {
        zzeag.zzhvc = zzeag.zzbeh();
        zzeag.zzhvd = new zzead();
    }

    static zzeae zzbef() {
        return zzeag.zzhvc;
    }

    static zzeae zzbeg() {
        return zzeag.zzhvd;
    }

    private static zzeae zzbeh() {
        try {
            return (zzeae)Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor().newInstance();
        }
        catch(Exception unused_ex) {
            return null;
        }
    }
}

