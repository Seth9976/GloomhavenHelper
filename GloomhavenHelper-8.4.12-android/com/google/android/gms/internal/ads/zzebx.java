package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zzebx extends zzebv {
    private static void zza(Object object0, zzeby zzeby0) {
        ((zzdyz)object0).zzhsw = zzeby0;
    }

    @Override  // com.google.android.gms.internal.ads.zzebv
    final void zza(Object object0, int v, long v1) {
        ((zzeby)object0).zzd(v << 3, v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzebv
    final void zza(Object object0, int v, zzdxn zzdxn0) {
        ((zzeby)object0).zzd(v << 3 | 2, zzdxn0);
    }

    @Override  // com.google.android.gms.internal.ads.zzebv
    final void zza(Object object0, int v, Object object1) {
        ((zzeby)object0).zzd(v << 3 | 3, ((zzeby)object1));
    }

    @Override  // com.google.android.gms.internal.ads.zzebv
    final void zza(Object object0, zzecs zzecs0) throws IOException {
        ((zzeby)object0).zzb(zzecs0);
    }

    @Override  // com.google.android.gms.internal.ads.zzebv
    final boolean zza(zzeax zzeax0) {
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzebv
    final void zzan(Object object0) {
        ((zzdyz)object0).zzhsw.zzban();
    }

    @Override  // com.google.android.gms.internal.ads.zzebv
    final Object zzav(Object object0) {
        ((zzeby)object0).zzban();
        return (zzeby)object0;
    }

    @Override  // com.google.android.gms.internal.ads.zzebv
    final int zzax(Object object0) {
        return ((zzeby)object0).zzbda();
    }

    @Override  // com.google.android.gms.internal.ads.zzebv
    final void zzb(Object object0, int v, long v1) {
        ((zzeby)object0).zzd(v << 3 | 1, v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzebv
    final Object zzbb(Object object0) {
        return ((zzdyz)object0).zzhsw;
    }

    @Override  // com.google.android.gms.internal.ads.zzebv
    final Object zzbc(Object object0) {
        zzeby zzeby0 = ((zzdyz)object0).zzhsw;
        if(zzeby0 == zzeby.zzbff()) {
            zzeby0 = zzeby.zzbfg();
            zzebx.zza(object0, zzeby0);
        }
        return zzeby0;
    }

    @Override  // com.google.android.gms.internal.ads.zzebv
    final int zzbd(Object object0) {
        return ((zzeby)object0).zzbfh();
    }

    @Override  // com.google.android.gms.internal.ads.zzebv
    final Object zzbfe() {
        return zzeby.zzbfg();
    }

    @Override  // com.google.android.gms.internal.ads.zzebv
    final void zzc(Object object0, int v, int v1) {
        ((zzeby)object0).zzd(v << 3 | 5, v1);
    }

    @Override  // com.google.android.gms.internal.ads.zzebv
    final void zzc(Object object0, zzecs zzecs0) throws IOException {
        ((zzeby)object0).zza(zzecs0);
    }

    @Override  // com.google.android.gms.internal.ads.zzebv
    final void zzh(Object object0, Object object1) {
        zzebx.zza(object0, ((zzeby)object1));
    }

    @Override  // com.google.android.gms.internal.ads.zzebv
    final void zzi(Object object0, Object object1) {
        zzebx.zza(object0, ((zzeby)object1));
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.ads.zzebv
    final Object zzj(Object object0, Object object1) {
        return ((zzeby)object1).equals(zzeby.zzbff()) ? ((zzeby)object0) : zzeby.zza(((zzeby)object0), ((zzeby)object1));
    }
}

