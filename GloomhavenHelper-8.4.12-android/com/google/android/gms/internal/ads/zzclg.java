package com.google.android.gms.internal.ads;

import android.database.sqlite.SQLiteDatabase;

final class zzclg implements zzdnu {
    private final zzdhq zzfzv;

    zzclg(zzcle zzcle0, zzdhq zzdhq0) {
        this.zzfzv = zzdhq0;
        super();
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void onSuccess(Object object0) {
        SQLiteDatabase sQLiteDatabase0 = (SQLiteDatabase)object0;
        try {
            this.zzfzv.apply(sQLiteDatabase0);
        }
        catch(Exception exception0) {
            String s = exception0.getMessage();
            zzawf.zzey((s.length() == 0 ? new String("Error executing function on offline signal database: ") : "Error executing function on offline signal database: " + s));
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdnu
    public final void zzb(Throwable throwable0) {
        String s = throwable0.getMessage();
        zzawf.zzey((s.length() == 0 ? new String("Failed to get offline signal database: ") : "Failed to get offline signal database: " + s));
    }
}

