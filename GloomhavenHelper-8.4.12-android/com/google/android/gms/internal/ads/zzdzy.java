package com.google.android.gms.internal.ads;

final class zzdzy implements zzebg {
    private final zzeai zzhuv;
    private static final zzeai zzhuw;

    static {
        zzdzy.zzhuw = new zzdzx();
    }

    public zzdzy() {
        this(new zzeaa(new zzeai[]{zzdyx.zzbcr(), zzdzy.zzbdy()}));
    }

    private zzdzy(zzeai zzeai0) {
        this.zzhuv = (zzeai)zzdzc.zza(zzeai0, "messageInfoFactory");
    }

    private static boolean zza(zzeaf zzeaf0) {
        return zzeaf0.zzbec() == zzf.zzhtk;
    }

    private static zzeai zzbdy() {
        try {
            return (zzeai)Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance").invoke(null);
        }
        catch(Exception unused_ex) {
            return zzdzy.zzhuw;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzebg
    public final zzebd zzg(Class class0) {
        zzebf.zzi(class0);
        zzeaf zzeaf0 = this.zzhuv.zzd(class0);
        if(zzeaf0.zzbed()) {
            return zzdyz.class.isAssignableFrom(class0) ? zzean.zza(zzebf.zzbet(), zzdyq.zzbcl(), zzeaf0.zzbee()) : zzean.zza(zzebf.zzber(), zzdyq.zzbcm(), zzeaf0.zzbee());
        }
        if(zzdyz.class.isAssignableFrom(class0)) {
            return zzdzy.zza(zzeaf0) ? zzeal.zza(class0, zzeaf0, zzear.zzbej(), zzdzr.zzbdx(), zzebf.zzbet(), zzdyq.zzbcl(), zzeag.zzbeg()) : zzeal.zza(class0, zzeaf0, zzear.zzbej(), zzdzr.zzbdx(), zzebf.zzbet(), null, zzeag.zzbeg());
        }
        return zzdzy.zza(zzeaf0) ? zzeal.zza(class0, zzeaf0, zzear.zzbei(), zzdzr.zzbdw(), zzebf.zzber(), zzdyq.zzbcm(), zzeag.zzbef()) : zzeal.zza(class0, zzeaf0, zzear.zzbei(), zzdzr.zzbdw(), zzebf.zzbes(), null, zzeag.zzbef());
    }
}

