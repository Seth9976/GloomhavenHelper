package com.google.android.gms.internal.ads;

import android.database.sqlite.SQLiteDatabase;

final class zzclx implements zzdhq {
    private final zzclu zzgaz;

    zzclx(zzclu zzclu0) {
        this.zzgaz = zzclu0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdhq
    public final Object apply(Object object0) {
        return this.zzgaz.zza(((SQLiteDatabase)object0));
    }
}

