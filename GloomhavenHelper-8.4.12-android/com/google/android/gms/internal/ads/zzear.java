package com.google.android.gms.internal.ads;

final class zzear {
    private static final zzeap zzhvw;
    private static final zzeap zzhvx;

    static {
        zzear.zzhvw = zzear.zzbek();
        zzear.zzhvx = new zzeas();
    }

    static zzeap zzbei() {
        return zzear.zzhvw;
    }

    static zzeap zzbej() {
        return zzear.zzhvx;
    }

    private static zzeap zzbek() {
        try {
            return (zzeap)Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor().newInstance();
        }
        catch(Exception unused_ex) {
            return null;
        }
    }
}

