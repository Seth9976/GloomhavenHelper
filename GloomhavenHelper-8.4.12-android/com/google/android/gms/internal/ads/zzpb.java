package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodec.OnFrameRenderedListener;
import android.media.MediaCodec;
import androidx.annotation.NonNull;

@TargetApi(23)
final class zzpb implements MediaCodec.OnFrameRenderedListener {
    private final zzpa zzbiw;

    private zzpb(zzpa zzpa0, MediaCodec mediaCodec0) {
        this.zzbiw = zzpa0;
        super();
        mediaCodec0.setOnFrameRenderedListener(this, new zzdkp());
    }

    zzpb(zzpa zzpa0, MediaCodec mediaCodec0, zzoz zzoz0) {
        this(zzpa0, mediaCodec0);
    }

    @Override  // android.media.MediaCodec$OnFrameRenderedListener
    public final void onFrameRendered(@NonNull MediaCodec mediaCodec0, long v, long v1) {
        if(this != this.zzbiw.zzbit) {
            return;
        }
        this.zzbiw.zzjg();
    }
}

