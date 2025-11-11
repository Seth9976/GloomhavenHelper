package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;

@TargetApi(21)
final class zzle implements zzlc {
    private final int zzazv;
    private MediaCodecInfo[] zzazw;

    public zzle(boolean z) {
        this.zzazv = z;
    }

    @Override  // com.google.android.gms.internal.ads.zzlc
    public final int getCodecCount() {
        this.zzhj();
        return this.zzazw.length;
    }

    @Override  // com.google.android.gms.internal.ads.zzlc
    public final MediaCodecInfo getCodecInfoAt(int v) {
        this.zzhj();
        return this.zzazw[v];
    }

    @Override  // com.google.android.gms.internal.ads.zzlc
    public final boolean zza(String s, MediaCodecInfo.CodecCapabilities mediaCodecInfo$CodecCapabilities0) {
        return mediaCodecInfo$CodecCapabilities0.isFeatureSupported("secure-playback");
    }

    @Override  // com.google.android.gms.internal.ads.zzlc
    public final boolean zzhi() {
        return true;
    }

    private final void zzhj() {
        if(this.zzazw == null) {
            this.zzazw = new MediaCodecList(this.zzazv).getCodecInfos();
        }
    }
}

