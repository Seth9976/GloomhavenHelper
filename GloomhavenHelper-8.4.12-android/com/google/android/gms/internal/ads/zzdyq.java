package com.google.android.gms.internal.ads;

final class zzdyq {
    private static final zzdyo zzhpy;
    private static final zzdyo zzhpz;

    static {
        zzdyq.zzhpy = new zzdyn();
        zzdyq.zzhpz = zzdyq.zzbck();
    }

    private static zzdyo zzbck() {
        try {
            return (zzdyo)Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor().newInstance();
        }
        catch(Exception unused_ex) {
            return null;
        }
    }

    static zzdyo zzbcl() {
        return zzdyq.zzhpy;
    }

    static zzdyo zzbcm() {
        zzdyo zzdyo0 = zzdyq.zzhpz;
        if(zzdyo0 == null) {
            throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
        }
        return zzdyo0;
    }
}

