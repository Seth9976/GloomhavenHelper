package com.google.android.gms.internal.ads;

import android.media.AudioTrack;
import android.os.SystemClock;

class zzib {
    private int zzafy;
    protected AudioTrack zzail;
    private boolean zzakg;
    private long zzakh;
    private long zzaki;
    private long zzakj;
    private long zzakk;
    private long zzakl;
    private long zzakm;

    private zzib() {
    }

    zzib(zzhy zzhy0) {
    }

    public final void pause() {
        if(this.zzakk != 0x8000000000000001L) {
            return;
        }
        this.zzail.pause();
    }

    public void zza(AudioTrack audioTrack0, boolean z) {
        this.zzail = audioTrack0;
        this.zzakg = z;
        this.zzakk = 0x8000000000000001L;
        this.zzakh = 0L;
        this.zzaki = 0L;
        this.zzakj = 0L;
        if(audioTrack0 != null) {
            this.zzafy = audioTrack0.getSampleRate();
        }
    }

    public final void zzdy(long v) {
        this.zzakl = this.zzfw();
        this.zzakk = SystemClock.elapsedRealtime() * 1000L;
        this.zzakm = v;
        this.zzail.stop();
    }

    public boolean zzft() {
        return false;
    }

    public long zzfu() {
        throw new UnsupportedOperationException();
    }

    public long zzfv() {
        throw new UnsupportedOperationException();
    }

    public final long zzfw() {
        if(this.zzakk != 0x8000000000000001L) {
            long v = SystemClock.elapsedRealtime();
            return Math.min(this.zzakm, this.zzakl + (v * 1000L - this.zzakk) * ((long)this.zzafy) / 1000000L);
        }
        int v1 = this.zzail.getPlayState();
        if(v1 == 1) {
            return 0L;
        }
        long v2 = 0xFFFFFFFFL & ((long)this.zzail.getPlaybackHeadPosition());
        if(this.zzakg) {
            if(v1 == 2 && v2 == 0L) {
                this.zzakj = this.zzakh;
            }
            v2 += this.zzakj;
        }
        if(this.zzakh > v2) {
            ++this.zzaki;
        }
        this.zzakh = v2;
        return v2 + (this.zzaki << 0x20);
    }

    public final long zzfx() {
        return this.zzfw() * 1000000L / ((long)this.zzafy);
    }
}

