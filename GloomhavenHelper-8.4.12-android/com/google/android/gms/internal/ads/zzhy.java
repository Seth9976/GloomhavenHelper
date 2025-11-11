package com.google.android.gms.internal.ads;

import android.media.AudioTrack;

final class zzhy extends Thread {
    private final AudioTrack zzahy;
    private final zzhz zzahz;

    zzhy(zzhz zzhz0, AudioTrack audioTrack0) {
        this.zzahz = zzhz0;
        this.zzahy = audioTrack0;
        super();
    }

    @Override
    public final void run() {
        try {
            this.zzahy.flush();
            this.zzahy.release();
        }
        finally {
            this.zzahz.zzaih.open();
        }
    }
}

