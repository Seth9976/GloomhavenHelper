package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.util.Date;

public final class zzbk extends zzedu {
    private Date zzct;
    private Date zzcu;
    private long zzcv;
    private long zzcw;
    private double zzcx;
    private float zzcy;
    private zzeee zzcz;
    private long zzda;
    private int zzdb;
    private int zzdc;
    private int zzdd;
    private int zzde;
    private int zzdf;
    private int zzdg;

    public zzbk() {
        super("mvhd");
        this.zzcx = 1.0;
        this.zzcy = 1.0f;
        this.zzcz = zzeee.zzigb;
    }

    public final long getDuration() {
        return this.zzcw;
    }

    @Override
    public final String toString() {
        return "MovieHeaderBox[creationTime=" + this.zzct + ";" + "modificationTime=" + this.zzcu + ";" + "timescale=" + this.zzcv + ";" + "duration=" + this.zzcw + ";" + "rate=" + this.zzcx + ";" + "volume=" + this.zzcy + ";" + "matrix=" + this.zzcz + ";" + "nextTrackId=" + this.zzda + "]";
    }

    @Override  // com.google.android.gms.internal.ads.zzeds
    public final void zzg(ByteBuffer byteBuffer0) {
        this.zzo(byteBuffer0);
        if(this.getVersion() == 1) {
            this.zzct = zzedx.zzfw(zzbg.zzc(byteBuffer0));
            this.zzcu = zzedx.zzfw(zzbg.zzc(byteBuffer0));
            this.zzcv = zzbg.zza(byteBuffer0);
            this.zzcw = zzbg.zzc(byteBuffer0);
        }
        else {
            this.zzct = zzedx.zzfw(zzbg.zza(byteBuffer0));
            this.zzcu = zzedx.zzfw(zzbg.zza(byteBuffer0));
            this.zzcv = zzbg.zza(byteBuffer0);
            this.zzcw = zzbg.zza(byteBuffer0);
        }
        this.zzcx = zzbg.zzd(byteBuffer0);
        byte[] arr_b = new byte[2];
        byteBuffer0.get(arr_b);
        this.zzcy = ((float)(((short)(arr_b[1] & 0xFF | ((short)(arr_b[0] << 8 & 0xFF00)))))) / 256.0f;
        zzbg.zzb(byteBuffer0);
        zzbg.zza(byteBuffer0);
        zzbg.zza(byteBuffer0);
        this.zzcz = zzeee.zzp(byteBuffer0);
        this.zzdb = byteBuffer0.getInt();
        this.zzdc = byteBuffer0.getInt();
        this.zzdd = byteBuffer0.getInt();
        this.zzde = byteBuffer0.getInt();
        this.zzdf = byteBuffer0.getInt();
        this.zzdg = byteBuffer0.getInt();
        this.zzda = zzbg.zza(byteBuffer0);
    }

    public final long zzs() {
        return this.zzcv;
    }
}

