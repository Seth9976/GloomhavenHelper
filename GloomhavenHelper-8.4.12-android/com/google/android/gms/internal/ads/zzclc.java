package com.google.android.gms.internal.ads;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class zzclc extends SQLiteOpenHelper {
    public zzclc(Context context0) {
        super(context0, "OfflineUpload.db", null, 1);
    }

    @Override  // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase0) {
        sQLiteDatabase0.execSQL("CREATE TABLE offline_signal_contents (timestamp INTEGER PRIMARY_KEY, serialized_proto_data BLOB)");
        sQLiteDatabase0.execSQL("CREATE TABLE offline_signal_statistics (statistic_name TEXT PRIMARY_KEY, value INTEGER)");
        ContentValues contentValues0 = new ContentValues();
        contentValues0.put("statistic_name", "failed_requests");
        contentValues0.put("value", 0);
        sQLiteDatabase0.insert("offline_signal_statistics", null, contentValues0);
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("statistic_name", "total_requests");
        contentValues1.put("value", 0);
        sQLiteDatabase0.insert("offline_signal_statistics", null, contentValues1);
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("statistic_name", "last_successful_request_time");
        contentValues2.put("value", 0L);
        sQLiteDatabase0.insert("offline_signal_statistics", null, contentValues2);
    }

    @Override  // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase0, int v, int v1) {
        sQLiteDatabase0.execSQL("DROP TABLE IF EXISTS offline_signal_contents");
        sQLiteDatabase0.execSQL("DROP TABLE IF EXISTS offline_signal_statistics");
    }
}

