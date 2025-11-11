package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController.VideoLifecycleCallbacks;

public final class zzzd extends zzxn {
    private final VideoLifecycleCallbacks zzacc;

    public zzzd(VideoLifecycleCallbacks videoController$VideoLifecycleCallbacks0) {
        this.zzacc = videoController$VideoLifecycleCallbacks0;
    }

    @Override  // com.google.android.gms.internal.ads.zzxk
    public final void onVideoEnd() {
        this.zzacc.onVideoEnd();
    }

    @Override  // com.google.android.gms.internal.ads.zzxk
    public final void onVideoMute(boolean z) {
    }

    @Override  // com.google.android.gms.internal.ads.zzxk
    public final void onVideoPause() {
        this.zzacc.onVideoPause();
    }

    @Override  // com.google.android.gms.internal.ads.zzxk
    public final void onVideoPlay() {
    }

    @Override  // com.google.android.gms.internal.ads.zzxk
    public final void onVideoStart() {
        this.zzacc.onVideoStart();
    }
}

