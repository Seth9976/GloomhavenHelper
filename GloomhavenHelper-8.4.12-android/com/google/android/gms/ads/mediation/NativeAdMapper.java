package com.google.android.gms.ads.mediation;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.VideoController;
import java.util.Map;

@Deprecated
public class NativeAdMapper {
    protected View mAdChoicesContent;
    protected Bundle mExtras;
    protected boolean mOverrideClickHandling;
    protected boolean mOverrideImpressionRecording;
    private VideoController zzcfe;
    private View zzejz;
    private boolean zzeka;

    public NativeAdMapper() {
        this.mExtras = new Bundle();
    }

    public View getAdChoicesContent() {
        return this.mAdChoicesContent;
    }

    public final Bundle getExtras() {
        return this.mExtras;
    }

    public final boolean getOverrideClickHandling() {
        return this.mOverrideClickHandling;
    }

    public final boolean getOverrideImpressionRecording() {
        return this.mOverrideImpressionRecording;
    }

    public final VideoController getVideoController() {
        return this.zzcfe;
    }

    public void handleClick(View view0) {
    }

    public boolean hasVideoContent() {
        return this.zzeka;
    }

    public void recordImpression() {
    }

    public void setAdChoicesContent(View view0) {
        this.mAdChoicesContent = view0;
    }

    public final void setExtras(Bundle bundle0) {
        this.mExtras = bundle0;
    }

    public void setHasVideoContent(boolean z) {
        this.zzeka = z;
    }

    public void setMediaView(View view0) {
        this.zzejz = view0;
    }

    public final void setOverrideClickHandling(boolean z) {
        this.mOverrideClickHandling = z;
    }

    public final void setOverrideImpressionRecording(boolean z) {
        this.mOverrideImpressionRecording = z;
    }

    @Deprecated
    public void trackView(View view0) {
    }

    public void trackViews(View view0, Map map0, Map map1) {
    }

    public void untrackView(View view0) {
    }

    public final void zza(VideoController videoController0) {
        this.zzcfe = videoController0;
    }

    public final View zzace() {
        return this.zzejz;
    }
}

