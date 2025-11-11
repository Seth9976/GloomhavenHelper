package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.AudioTimestamp;
import android.media.AudioTrack;

@TargetApi(19)
final class zzia extends zzib {
    private final AudioTimestamp zzakc;
    private long zzakd;
    private long zzake;
    private long zzakf;

    public zzia() {
        super(null);
        this.zzakc = new AudioTimestamp();
    }

    @Override  // com.google.android.gms.internal.ads.zzib
    public final void zza(AudioTrack audioTrack0, boolean z) {
        super.zza(audioTrack0, z);
        this.zzakd = 0L;
        this.zzake = 0L;
        this.zzakf = 0L;
    }

    @Override  // com.google.android.gms.internal.ads.zzib
    public final boolean zzft() {
        boolean z = this.zzail.getTimestamp(this.zzakc);
        if(z) {
            long v = this.zzakc.framePosition;
            if(this.zzake > v) {
                ++this.zzakd;
            }
            this.zzake = v;
            this.zzakf = v + (this.zzakd << 0x20);
        }
        return z;
    }

    @Override  // com.google.android.gms.internal.ads.zzib
    public final long zzfu() {
        return this.zzakc.nanoTime;
    }

    @Override  // com.google.android.gms.internal.ads.zzib
    public final long zzfv() {
        return this.zzakf;
    }
}

