package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.media.MediaCodecInfo.AudioCapabilities;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaCodecInfo.VideoCapabilities;
import android.util.Log;
import android.util.Pair;

@TargetApi(16)
public final class zzkw {
    private final String mimeType;
    public final String name;
    public final boolean zzajz;
    public final boolean zzazh;
    public final boolean zzazi;
    private final MediaCodecInfo.CodecCapabilities zzazj;

    private zzkw(String s, String s1, MediaCodecInfo.CodecCapabilities mediaCodecInfo$CodecCapabilities0, boolean z, boolean z1) {
        this.name = (String)zzob.checkNotNull(s);
        this.mimeType = s1;
        this.zzazj = mediaCodecInfo$CodecCapabilities0;
        boolean z2 = true;
        this.zzazh = !z && mediaCodecInfo$CodecCapabilities0 != null && mediaCodecInfo$CodecCapabilities0.isFeatureSupported("adaptive-playback");
        this.zzajz = mediaCodecInfo$CodecCapabilities0 != null && mediaCodecInfo$CodecCapabilities0.isFeatureSupported("tunneled-playback");
        if(!z1 && (mediaCodecInfo$CodecCapabilities0 == null || !mediaCodecInfo$CodecCapabilities0.isFeatureSupported("secure-playback"))) {
            z2 = false;
        }
        this.zzazi = z2;
    }

    public static zzkw zza(String s, String s1, MediaCodecInfo.CodecCapabilities mediaCodecInfo$CodecCapabilities0, boolean z, boolean z1) {
        return new zzkw(s, s1, mediaCodecInfo$CodecCapabilities0, z, z1);
    }

    @TargetApi(21)
    private static boolean zza(MediaCodecInfo.VideoCapabilities mediaCodecInfo$VideoCapabilities0, int v, int v1, double f) {
        return f == -1.0 || f <= 0.0 ? mediaCodecInfo$VideoCapabilities0.isSizeSupported(v, v1) : mediaCodecInfo$VideoCapabilities0.areSizeAndRateSupported(v, v1, f);
    }

    @TargetApi(21)
    public final boolean zza(int v, int v1, double f) {
        MediaCodecInfo.CodecCapabilities mediaCodecInfo$CodecCapabilities0 = this.zzazj;
        if(mediaCodecInfo$CodecCapabilities0 == null) {
            this.zzbg("sizeAndRate.caps");
            return false;
        }
        MediaCodecInfo.VideoCapabilities mediaCodecInfo$VideoCapabilities0 = mediaCodecInfo$CodecCapabilities0.getVideoCapabilities();
        if(mediaCodecInfo$VideoCapabilities0 == null) {
            this.zzbg("sizeAndRate.vCaps");
            return false;
        }
        if(!zzkw.zza(mediaCodecInfo$VideoCapabilities0, v, v1, f)) {
            if(v < v1 && zzkw.zza(mediaCodecInfo$VideoCapabilities0, v1, v, f)) {
                Log.d("MediaCodecInfo", "AssumedSupport [" + ("sizeAndRate.rotated, " + v + "x" + v1 + "x" + f) + "] [" + this.name + ", " + this.mimeType + "] [" + zzop.zzbhc + "]");
                return true;
            }
            this.zzbg("sizeAndRate.support, " + v + "x" + v1 + "x" + f);
            return false;
        }
        return true;
    }

    @TargetApi(21)
    public final boolean zzaq(int v) {
        MediaCodecInfo.CodecCapabilities mediaCodecInfo$CodecCapabilities0 = this.zzazj;
        if(mediaCodecInfo$CodecCapabilities0 == null) {
            this.zzbg("sampleRate.caps");
            return false;
        }
        MediaCodecInfo.AudioCapabilities mediaCodecInfo$AudioCapabilities0 = mediaCodecInfo$CodecCapabilities0.getAudioCapabilities();
        if(mediaCodecInfo$AudioCapabilities0 == null) {
            this.zzbg("sampleRate.aCaps");
            return false;
        }
        if(!mediaCodecInfo$AudioCapabilities0.isSampleRateSupported(v)) {
            this.zzbg("sampleRate.support, " + v);
            return false;
        }
        return true;
    }

    @TargetApi(21)
    public final boolean zzar(int v) {
        MediaCodecInfo.CodecCapabilities mediaCodecInfo$CodecCapabilities0 = this.zzazj;
        if(mediaCodecInfo$CodecCapabilities0 == null) {
            this.zzbg("channelCount.caps");
            return false;
        }
        MediaCodecInfo.AudioCapabilities mediaCodecInfo$AudioCapabilities0 = mediaCodecInfo$CodecCapabilities0.getAudioCapabilities();
        if(mediaCodecInfo$AudioCapabilities0 == null) {
            this.zzbg("channelCount.aCaps");
            return false;
        }
        if(mediaCodecInfo$AudioCapabilities0.getMaxInputChannelCount() < v) {
            this.zzbg("channelCount.support, " + v);
            return false;
        }
        return true;
    }

    public static zzkw zzbe(String s) {
        return new zzkw(s, null, null, false, false);
    }

    public final boolean zzbf(String s) {
        String s2;
        if(s != null && this.mimeType != null) {
            if(s == null) {
                s2 = null;
            }
            else {
                String s1 = s.trim();
                if(s1.startsWith("avc1") || s1.startsWith("avc3")) {
                    s2 = "video/avc";
                }
                else if(s1.startsWith("hev1") || s1.startsWith("hvc1")) {
                    s2 = "video/hevc";
                }
                else if(s1.startsWith("vp9")) {
                    s2 = "video/x-vnd.on2.vp9";
                }
                else if(s1.startsWith("vp8")) {
                    s2 = "video/x-vnd.on2.vp8";
                }
                else if(s1.startsWith("mp4a")) {
                    s2 = "audio/mp4a-latm";
                }
                else if(s1.startsWith("ac-3") || s1.startsWith("dac3")) {
                    s2 = "audio/ac3";
                }
                else if(s1.startsWith("ec-3") || s1.startsWith("dec3")) {
                    s2 = "audio/eac3";
                }
                else if(s1.startsWith("dtsc") || s1.startsWith("dtse")) {
                    s2 = "audio/vnd.dts";
                }
                else if(s1.startsWith("dtsh") || s1.startsWith("dtsl")) {
                    s2 = "audio/vnd.dts.hd";
                }
                else if(s1.startsWith("opus")) {
                    s2 = "audio/opus";
                }
                else if(s1.startsWith("vorbis")) {
                    s2 = "audio/vorbis";
                }
                else {
                    s2 = null;
                }
            }
            if(s2 == null) {
                return true;
            }
            if(!this.mimeType.equals(s2)) {
                this.zzbg("codec.mime " + s + ", " + s2);
                return false;
            }
            Pair pair0 = zzkz.zzbh(s);
            if(pair0 == null) {
                return true;
            }
            MediaCodecInfo.CodecProfileLevel[] arr_mediaCodecInfo$CodecProfileLevel = this.zzhf();
            for(int v = 0; v < arr_mediaCodecInfo$CodecProfileLevel.length; ++v) {
                MediaCodecInfo.CodecProfileLevel mediaCodecInfo$CodecProfileLevel0 = arr_mediaCodecInfo$CodecProfileLevel[v];
                if(mediaCodecInfo$CodecProfileLevel0.profile == ((int)(((Integer)pair0.first))) && mediaCodecInfo$CodecProfileLevel0.level >= ((int)(((Integer)pair0.second)))) {
                    return true;
                }
            }
            this.zzbg("codec.profileLevel, " + s + ", " + s2);
            return false;
        }
        return true;
    }

    private final void zzbg(String s) {
        Log.d("MediaCodecInfo", "NoSupport [" + s + "] [" + this.name + ", " + this.mimeType + "] [" + zzop.zzbhc + "]");
    }

    @TargetApi(21)
    public final Point zzd(int v, int v1) {
        MediaCodecInfo.CodecCapabilities mediaCodecInfo$CodecCapabilities0 = this.zzazj;
        if(mediaCodecInfo$CodecCapabilities0 == null) {
            this.zzbg("align.caps");
            return null;
        }
        MediaCodecInfo.VideoCapabilities mediaCodecInfo$VideoCapabilities0 = mediaCodecInfo$CodecCapabilities0.getVideoCapabilities();
        if(mediaCodecInfo$VideoCapabilities0 == null) {
            this.zzbg("align.vCaps");
            return null;
        }
        int v2 = mediaCodecInfo$VideoCapabilities0.getWidthAlignment();
        int v3 = mediaCodecInfo$VideoCapabilities0.getHeightAlignment();
        return new Point((v + v2 - 1) / v2 * v2, (v1 + v3 - 1) / v3 * v3);
    }

    public final MediaCodecInfo.CodecProfileLevel[] zzhf() {
        return this.zzazj == null || this.zzazj.profileLevels == null ? new MediaCodecInfo.CodecProfileLevel[0] : this.zzazj.profileLevels;
    }
}

