package com.google.android.gms.ads.formats;

import android.view.View;
import com.google.android.gms.ads.VideoController;
import java.util.List;

public interface NativeCustomTemplateAd {
    public interface DisplayOpenMeasurement {
        void setView(View arg1);

        boolean start();
    }

    public interface OnCustomClickListener {
        void onCustomClick(NativeCustomTemplateAd arg1, String arg2);
    }

    public interface OnCustomTemplateAdLoadedListener {
        void onCustomTemplateAdLoaded(NativeCustomTemplateAd arg1);
    }

    public static final String ASSET_NAME_VIDEO = "_videoMediaView";

    void destroy();

    List getAvailableAssetNames();

    String getCustomTemplateId();

    DisplayOpenMeasurement getDisplayOpenMeasurement();

    Image getImage(String arg1);

    CharSequence getText(String arg1);

    VideoController getVideoController();

    MediaView getVideoMediaView();

    void performClick(String arg1);

    void recordImpression();
}

