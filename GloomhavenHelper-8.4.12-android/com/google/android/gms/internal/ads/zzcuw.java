package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.AudioManager;
import com.google.android.gms.ads.internal.zzq;

public final class zzcuw implements zzcye {
    private final zzdoe zzfrv;
    private final Context zzur;

    public zzcuw(zzdoe zzdoe0, Context context0) {
        this.zzfrv = zzdoe0;
        this.zzur = context0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        zzcuv zzcuv0 = () -> {
            AudioManager audioManager0 = (AudioManager)this.zzur.getSystemService("audio");
            return new zzcut(audioManager0.getMode(), audioManager0.isMusicActive(), audioManager0.isSpeakerphoneOn(), audioManager0.getStreamVolume(3), audioManager0.getRingerMode(), audioManager0.getStreamVolume(2), zzq.zzla().zzpj(), zzq.zzla().zzpk());
        };
        return this.zzfrv.zzd(zzcuv0);
    }

    // 检测为 Lambda 实现
    final zzcut zzape() throws Exception [...]
}

