package com.google.android.gms.internal.ads;

final class zzdyx implements zzeai {
    private static final zzdyx zzhsr;

    static {
        zzdyx.zzhsr = new zzdyx();
    }

    public static zzdyx zzbcr() {
        return zzdyx.zzhsr;
    }

    @Override  // com.google.android.gms.internal.ads.zzeai
    public final boolean zzc(Class class0) {
        return zzdyz.class.isAssignableFrom(class0);
    }

    @Override  // com.google.android.gms.internal.ads.zzeai
    public final zzeaf zzd(Class class0) {
        if(!zzdyz.class.isAssignableFrom(class0)) {
            String s = class0.getName();
            throw new IllegalArgumentException((s.length() == 0 ? new String("Unsupported message type: ") : "Unsupported message type: " + s));
        }
        try {
            return (zzeaf)zzdyz.zzf(class0.asSubclass(zzdyz.class)).zza(zzf.zzhte, null, null);
        }
        catch(Exception exception0) {
            String s1 = class0.getName();
            throw new RuntimeException((s1.length() == 0 ? new String("Unable to get message info for ") : "Unable to get message info for " + s1), exception0);
        }
    }
}

