package com.google.android.gms.internal.ads;

import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;

final class zzlf implements zzlc {
    private zzlf() {
    }

    zzlf(zzlb zzlb0) {
    }

    @Override  // com.google.android.gms.internal.ads.zzlc
    public final int getCodecCount() {
        return MediaCodecList.getCodecCount();
    }

    @Override  // com.google.android.gms.internal.ads.zzlc
    public final MediaCodecInfo getCodecInfoAt(int v) {
        return MediaCodecList.getCodecInfoAt(v);
    }

    @Override  // com.google.android.gms.internal.ads.zzlc
    public final boolean zza(String s, MediaCodecInfo.CodecCapabilities mediaCodecInfo$CodecCapabilities0) {
        return "video/avc".equals(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzlc
    public final boolean zzhi() {
        return false;
    }
}

