package com.google.android.gms.internal.measurement;

import java.io.IOException;

final class zzhw extends zzhu {
    private static void zza(Object object0, zzhx zzhx0) {
        ((zzfd)object0).zzb = zzhx0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhu
    final Object zza() {
        return zzhx.zzb();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhu
    final Object zza(Object object0) {
        ((zzhx)object0).zzc();
        return (zzhx)object0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhu
    final void zza(Object object0, int v, int v1) {
        ((zzhx)object0).zza(v << 3 | 5, v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzhu
    final void zza(Object object0, int v, long v1) {
        ((zzhx)object0).zza(v << 3, v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzhu
    final void zza(Object object0, int v, zzdv zzdv0) {
        ((zzhx)object0).zza(v << 3 | 2, zzdv0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzhu
    final void zza(Object object0, int v, Object object1) {
        ((zzhx)object0).zza(v << 3 | 3, ((zzhx)object1));
    }

    @Override  // com.google.android.gms.internal.measurement.zzhu
    final void zza(Object object0, zzir zzir0) throws IOException {
        ((zzhx)object0).zzb(zzir0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzhu
    final void zza(Object object0, Object object1) {
        zzhw.zza(object0, ((zzhx)object1));
    }

    @Override  // com.google.android.gms.internal.measurement.zzhu
    final boolean zza(zzhd zzhd0) {
        return false;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhu
    final Object zzb(Object object0) {
        return ((zzfd)object0).zzb;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhu
    final void zzb(Object object0, int v, long v1) {
        ((zzhx)object0).zza(v << 3 | 1, v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzhu
    final void zzb(Object object0, zzir zzir0) throws IOException {
        ((zzhx)object0).zza(zzir0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzhu
    final void zzb(Object object0, Object object1) {
        zzhw.zza(object0, ((zzhx)object1));
    }

    @Override  // com.google.android.gms.internal.measurement.zzhu
    final Object zzc(Object object0) {
        zzhx zzhx0 = ((zzfd)object0).zzb;
        if(zzhx0 == zzhx.zza()) {
            zzhx0 = zzhx.zzb();
            zzhw.zza(object0, zzhx0);
        }
        return zzhx0;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.internal.measurement.zzhu
    final Object zzc(Object object0, Object object1) {
        return ((zzhx)object1).equals(zzhx.zza()) ? ((zzhx)object0) : zzhx.zza(((zzhx)object0), ((zzhx)object1));
    }

    @Override  // com.google.android.gms.internal.measurement.zzhu
    final void zzd(Object object0) {
        ((zzfd)object0).zzb.zzc();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhu
    final int zze(Object object0) {
        return ((zzhx)object0).zzd();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhu
    final int zzf(Object object0) {
        return ((zzhx)object0).zze();
    }
}

