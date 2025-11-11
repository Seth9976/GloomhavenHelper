package com.google.android.gms.internal.ads;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.gms.ads.internal.zzq;
import java.util.ArrayList;

final class zzclt implements zzdhq {
    private final boolean zzdzs;
    private final zzclq zzgat;
    private final ArrayList zzgau;
    private final zzh zzgav;
    private final zzc zzgaw;

    zzclt(zzclq zzclq0, boolean z, ArrayList arrayList0, zzh zzsz$zzh0, zzc zzsz$zzj$zzc0) {
        this.zzgat = zzclq0;
        this.zzdzs = z;
        this.zzgau = arrayList0;
        this.zzgav = zzsz$zzh0;
        this.zzgaw = zzsz$zzj$zzc0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdhq
    public final Object apply(Object object0) {
        byte[] arr_b = this.zzgat.zzgan.zza(this.zzdzs, this.zzgau, this.zzgav, this.zzgaw);
        ContentValues contentValues0 = new ContentValues();
        contentValues0.put("timestamp", zzq.zzlc().currentTimeMillis());
        contentValues0.put("serialized_proto_data", arr_b);
        ((SQLiteDatabase)object0).insert("offline_signal_contents", null, contentValues0);
        ((SQLiteDatabase)object0).execSQL("UPDATE offline_signal_statistics SET value = value+1 WHERE statistic_name = \'total_requests\'");
        if(!this.zzdzs) {
            ((SQLiteDatabase)object0).execSQL("UPDATE offline_signal_statistics SET value = value+1 WHERE statistic_name = \'failed_requests\'");
        }
        return null;
    }
}

