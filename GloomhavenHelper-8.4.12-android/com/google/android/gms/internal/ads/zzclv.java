package com.google.android.gms.internal.ads;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public final class zzclv {
    public static int zza(SQLiteDatabase sQLiteDatabase0, int v) {
        int v1 = 0;
        if(v == 2) {
            return 0;
        }
        Cursor cursor0 = zzclv.zzc(sQLiteDatabase0, v);
        if(cursor0.getCount() > 0) {
            cursor0.moveToNext();
            v1 = cursor0.getInt(cursor0.getColumnIndexOrThrow("value"));
        }
        cursor0.close();
        return v1;
    }

    public static long zzb(SQLiteDatabase sQLiteDatabase0, int v) {
        Cursor cursor0 = zzclv.zzc(sQLiteDatabase0, 2);
        long v1 = 0L;
        if(cursor0.getCount() > 0) {
            cursor0.moveToNext();
            v1 = cursor0.getLong(cursor0.getColumnIndexOrThrow("value"));
        }
        cursor0.close();
        return v1;
    }

    public static ArrayList zzb(SQLiteDatabase sQLiteDatabase0) {
        ArrayList arrayList0 = new ArrayList();
        Cursor cursor0 = sQLiteDatabase0.query("offline_signal_contents", new String[]{"serialized_proto_data"}, null, null, null, null, null);
        while(cursor0.moveToNext()) {
            byte[] arr_b = cursor0.getBlob(cursor0.getColumnIndexOrThrow("serialized_proto_data"));
            try {
                arrayList0.add(zza.zzg(arr_b));
            }
            catch(zzdzh zzdzh0) {
                zzawf.zzey("Unable to deserialize proto from offline signals database:");
                zzawf.zzey(zzdzh0.getMessage());
            }
        }
        cursor0.close();
        return arrayList0;
    }

    private static Cursor zzc(SQLiteDatabase sQLiteDatabase0, int v) {
        String[] arr_s = new String[1];
        switch(v) {
            case 0: {
                arr_s[0] = "failed_requests";
                return sQLiteDatabase0.query("offline_signal_statistics", new String[]{"value"}, "statistic_name = ?", arr_s, null, null, null);
            }
            case 1: {
                arr_s[0] = "total_requests";
                return sQLiteDatabase0.query("offline_signal_statistics", new String[]{"value"}, "statistic_name = ?", arr_s, null, null, null);
            }
            case 2: {
                arr_s[0] = "last_successful_request_time";
                return sQLiteDatabase0.query("offline_signal_statistics", new String[]{"value"}, "statistic_name = ?", arr_s, null, null, null);
            }
            default: {
                return sQLiteDatabase0.query("offline_signal_statistics", new String[]{"value"}, "statistic_name = ?", arr_s, null, null, null);
            }
        }
    }
}

