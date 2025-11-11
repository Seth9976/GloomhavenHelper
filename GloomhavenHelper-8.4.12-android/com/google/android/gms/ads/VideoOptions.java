package com.google.android.gms.ads;

import com.google.android.gms.internal.ads.zzzc;

public final class VideoOptions {
    public static final class Builder {
        private boolean zzacd;
        private boolean zzace;
        private boolean zzacf;

        public Builder() {
            this.zzacd = true;
            this.zzace = false;
            this.zzacf = false;
        }

        public final VideoOptions build() {
            return new VideoOptions(this, null);
        }

        public final Builder setClickToExpandRequested(boolean z) {
            this.zzacf = z;
            return this;
        }

        public final Builder setCustomControlsRequested(boolean z) {
            this.zzace = z;
            return this;
        }

        public final Builder setStartMuted(boolean z) {
            this.zzacd = z;
            return this;
        }
    }

    private final boolean zzacd;
    private final boolean zzace;
    private final boolean zzacf;

    private VideoOptions(Builder videoOptions$Builder0) {
        this.zzacd = videoOptions$Builder0.zzacd;
        this.zzace = videoOptions$Builder0.zzace;
        this.zzacf = videoOptions$Builder0.zzacf;
    }

    VideoOptions(Builder videoOptions$Builder0, zzd zzd0) {
        this(videoOptions$Builder0);
    }

    public VideoOptions(zzzc zzzc0) {
        this.zzacd = zzzc0.zzacd;
        this.zzace = zzzc0.zzace;
        this.zzacf = zzzc0.zzacf;
    }

    public final boolean getClickToExpandRequested() {
        return this.zzacf;
    }

    public final boolean getCustomControlsRequested() {
        return this.zzace;
    }

    public final boolean getStartMuted() {
        return this.zzacd;
    }
}

