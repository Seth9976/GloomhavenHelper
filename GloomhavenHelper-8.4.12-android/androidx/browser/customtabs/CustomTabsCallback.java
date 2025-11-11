package androidx.browser.customtabs;

import android.net.Uri;
import android.os.Bundle;

public class CustomTabsCallback {
    public static final int NAVIGATION_ABORTED = 4;
    public static final int NAVIGATION_FAILED = 3;
    public static final int NAVIGATION_FINISHED = 2;
    public static final int NAVIGATION_STARTED = 1;
    public static final int TAB_HIDDEN = 6;
    public static final int TAB_SHOWN = 5;

    public void extraCallback(String s, Bundle bundle0) {
    }

    public void onMessageChannelReady(Bundle bundle0) {
    }

    public void onNavigationEvent(int v, Bundle bundle0) {
    }

    public void onPostMessage(String s, Bundle bundle0) {
    }

    public void onRelationshipValidationResult(int v, Uri uri0, boolean z, Bundle bundle0) {
    }
}

