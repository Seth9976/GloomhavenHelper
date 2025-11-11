package com.google.android.gms.internal.measurement;

final class zzfb implements zzgk {
    private static final zzfb zza;

    static {
        zzfb.zza = new zzfb();
    }

    public static zzfb zza() {
        return zzfb.zza;
    }

    @Override  // com.google.android.gms.internal.measurement.zzgk
    public final boolean zza(Class class0) {
        return zzfd.class.isAssignableFrom(class0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzgk
    public final zzgl zzb(Class class0) {
        if(!zzfd.class.isAssignableFrom(class0)) {
            String s = class0.getName();
            throw new IllegalArgumentException((s.length() == 0 ? new String("Unsupported message type: ") : "Unsupported message type: " + s));
        }
        try {
            return (zzgl)zzfd.zza(class0.asSubclass(zzfd.class)).zza(zzd.zzc, null, null);
        }
        catch(Exception exception0) {
            String s1 = class0.getName();
            throw new RuntimeException((s1.length() == 0 ? new String("Unable to get message info for ") : "Unable to get message info for " + s1), exception0);
        }
    }
}

