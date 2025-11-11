package androidx.browser.customtabs;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.core.app.BundleCompat;

public class TrustedWebUtils {
    public static final String EXTRA_LAUNCH_AS_TRUSTED_WEB_ACTIVITY = "android.support.customtabs.extra.LAUNCH_AS_TRUSTED_WEB_ACTIVITY";

    public static void launchAsTrustedWebActivity(@NonNull Context context0, @NonNull CustomTabsIntent customTabsIntent0, @NonNull Uri uri0) {
        if(BundleCompat.getBinder(customTabsIntent0.intent.getExtras(), "android.support.customtabs.extra.SESSION") == null) {
            throw new IllegalArgumentException("Given CustomTabsIntent should be associated with a valid CustomTabsSession");
        }
        customTabsIntent0.intent.putExtra("android.support.customtabs.extra.LAUNCH_AS_TRUSTED_WEB_ACTIVITY", true);
        customTabsIntent0.launchUrl(context0, uri0);
    }
}

