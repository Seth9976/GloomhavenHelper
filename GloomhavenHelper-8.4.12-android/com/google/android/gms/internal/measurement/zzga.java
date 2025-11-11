package com.google.android.gms.internal.measurement;

final class zzga implements zzhf {
    private final zzgk zza;
    private static final zzgk zzb;

    static {
        zzga.zzb = new zzgd();
    }

    public zzga() {
        this(new zzgc(new zzgk[]{zzfb.zza(), zzga.zza()}));
    }

    private zzga(zzgk zzgk0) {
        this.zza = (zzgk)zzfe.zza(zzgk0, "messageInfoFactory");
    }

    private static zzgk zza() {
        try {
            return (zzgk)Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance").invoke(null);
        }
        catch(Exception unused_ex) {
            return zzga.zzb;
        }
    }

    private static boolean zza(zzgl zzgl0) {
        return zzgl0.zza() == zzd.zzh;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhf
    public final zzhc zza(Class class0) {
        zzhe.zza(class0);
        zzgl zzgl0 = this.zza.zzb(class0);
        if(zzgl0.zzb()) {
            return zzfd.class.isAssignableFrom(class0) ? zzgt.zza(zzhe.zzc(), zzeu.zza(), zzgl0.zzc()) : zzgt.zza(zzhe.zza(), zzeu.zzb(), zzgl0.zzc());
        }
        if(zzfd.class.isAssignableFrom(class0)) {
            return zzga.zza(zzgl0) ? zzgr.zza(class0, zzgl0, zzgx.zzb(), zzfx.zzb(), zzhe.zzc(), zzeu.zza(), zzgi.zzb()) : zzgr.zza(class0, zzgl0, zzgx.zzb(), zzfx.zzb(), zzhe.zzc(), null, zzgi.zzb());
        }
        return zzga.zza(zzgl0) ? zzgr.zza(class0, zzgl0, zzgx.zza(), zzfx.zza(), zzhe.zza(), zzeu.zzb(), zzgi.zza()) : zzgr.zza(class0, zzgl0, zzgx.zza(), zzfx.zza(), zzhe.zzb(), null, zzgi.zza());
    }
}

