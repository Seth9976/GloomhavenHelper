package com.google.android.gms.internal.ads;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import com.google.android.gms.ads.internal.zzq;
import java.util.ArrayList;

public final class zzclu {
    private zzazo zzdjo;
    private zzcle zzgap;
    private zzsn zzgax;
    private Context zzur;

    public zzclu(Context context0, zzazo zzazo0, zzsn zzsn0, zzcle zzcle0) {
        this.zzur = context0;
        this.zzdjo = zzazo0;
        this.zzgax = zzsn0;
        this.zzgap = zzcle0;
    }

    // 检测为 Lambda 实现
    final Void zza(SQLiteDatabase sQLiteDatabase0) throws Exception [...]

    public final void zzaob() {
        try {
            this.zzgap.zza((SQLiteDatabase sQLiteDatabase0) -> {
                ArrayList arrayList0 = zzclv.zzb(sQLiteDatabase0);
                int v = 2;
                zzj zzsz$zzj0 = (zzj)(((zzdyz)zzj.zznt().zzcb("com.esotericsoftware.gloomhavenhelper").zzcc(Build.MODEL).zzcd(zzclv.zza(sQLiteDatabase0, 0)).zzc(arrayList0).zzce(zzclv.zza(sQLiteDatabase0, 1)).zzes(zzq.zzlc().currentTimeMillis()).zzet(zzclv.zzb(sQLiteDatabase0, 2)).zzbcx()));
                int v1 = arrayList0.size();
                long v2 = 0L;
                int v3 = 0;
                while(v3 < v1) {
                    Object object0 = arrayList0.get(v3);
                    ++v3;
                    zza zzsz$zzj$zza0 = (zza)object0;
                    if(zzsz$zzj$zza0.zznv() == zztf.zzbwi && zzsz$zzj$zza0.getTimestamp() > v2) {
                        v2 = zzsz$zzj$zza0.getTimestamp();
                    }
                }
                if(v2 != 0L) {
                    ContentValues contentValues0 = new ContentValues();
                    contentValues0.put("value", v2);
                    sQLiteDatabase0.update("offline_signal_statistics", contentValues0, "statistic_name = \'last_successful_request_time\'", null);
                }
                this.zzgax.zza(new zzclw(zzsz$zzj0));
                zztu zztu0 = new zztu();
                zztu0.zzcba = this.zzdjo.zzdxf;
                zztu0.zzcbb = this.zzdjo.zzdxg;
                if(this.zzdjo.zzdxh) {
                    v = 0;
                }
                zztu0.zzcbc = v;
                this.zzgax.zza(new zzclz(zztu0));
                this.zzgax.zza(com.google.android.gms.internal.ads.zzsp.zza.zza.zzbui);
                sQLiteDatabase0.delete("offline_signal_contents", null, null);
                ContentValues contentValues1 = new ContentValues();
                contentValues1.put("value", 0);
                sQLiteDatabase0.update("offline_signal_statistics", contentValues1, "statistic_name = ?", new String[]{"failed_requests"});
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("value", 0);
                sQLiteDatabase0.update("offline_signal_statistics", contentValues2, "statistic_name = ?", new String[]{"total_requests"});
                return null;
            });
        }
        catch(Exception exception0) {
            String s = exception0.getMessage();
            zzawf.zzey((s.length() == 0 ? new String("Error in offline signals database startup: ") : "Error in offline signals database startup: " + s));
        }
    }
}

