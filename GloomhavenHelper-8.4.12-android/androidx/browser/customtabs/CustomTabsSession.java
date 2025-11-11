package androidx.browser.customtabs;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback;
import android.support.customtabs.ICustomTabsService;
import android.widget.RemoteViews;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.util.List;

public final class CustomTabsSession {
    private static final String TAG = "CustomTabsSession";
    private final ICustomTabsCallback mCallback;
    private final ComponentName mComponentName;
    private final Object mLock;
    private final ICustomTabsService mService;

    CustomTabsSession(ICustomTabsService iCustomTabsService0, ICustomTabsCallback iCustomTabsCallback0, ComponentName componentName0) {
        this.mLock = new Object();
        this.mService = iCustomTabsService0;
        this.mCallback = iCustomTabsCallback0;
        this.mComponentName = componentName0;
    }

    @NonNull
    @VisibleForTesting
    public static CustomTabsSession createMockSessionForTesting(@NonNull ComponentName componentName0) {
        return new CustomTabsSession(null, new MockCallback(), componentName0);
    }

    IBinder getBinder() {
        return this.mCallback.asBinder();
    }

    ComponentName getComponentName() {
        return this.mComponentName;
    }

    public boolean mayLaunchUrl(Uri uri0, Bundle bundle0, List list0) {
        try {
            return this.mService.mayLaunchUrl(this.mCallback, uri0, bundle0, list0);
        }
        catch(RemoteException unused_ex) {
            return false;
        }
    }

    public int postMessage(String s, Bundle bundle0) {
        synchronized(this.mLock) {
            try {
                return this.mService.postMessage(this.mCallback, s, bundle0);
            }
            catch(RemoteException unused_ex) {
                return -2;
            }
        }
    }

    public boolean requestPostMessageChannel(Uri uri0) {
        try {
            return this.mService.requestPostMessageChannel(this.mCallback, uri0);
        }
        catch(RemoteException unused_ex) {
            return false;
        }
    }

    public boolean setActionButton(@NonNull Bitmap bitmap0, @NonNull String s) {
        Bundle bundle0 = new Bundle();
        bundle0.putParcelable("android.support.customtabs.customaction.ICON", bitmap0);
        bundle0.putString("android.support.customtabs.customaction.DESCRIPTION", s);
        Bundle bundle1 = new Bundle();
        bundle1.putBundle("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE", bundle0);
        try {
            return this.mService.updateVisuals(this.mCallback, bundle1);
        }
        catch(RemoteException unused_ex) {
            return false;
        }
    }

    public boolean setSecondaryToolbarViews(@Nullable RemoteViews remoteViews0, @Nullable int[] arr_v, @Nullable PendingIntent pendingIntent0) {
        Bundle bundle0 = new Bundle();
        bundle0.putParcelable("android.support.customtabs.extra.EXTRA_REMOTEVIEWS", remoteViews0);
        bundle0.putIntArray("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_VIEW_IDS", arr_v);
        bundle0.putParcelable("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_PENDINGINTENT", pendingIntent0);
        try {
            return this.mService.updateVisuals(this.mCallback, bundle0);
        }
        catch(RemoteException unused_ex) {
            return false;
        }
    }

    @Deprecated
    public boolean setToolbarItem(int v, @NonNull Bitmap bitmap0, @NonNull String s) {
        Bundle bundle0 = new Bundle();
        bundle0.putInt("android.support.customtabs.customaction.ID", v);
        bundle0.putParcelable("android.support.customtabs.customaction.ICON", bitmap0);
        bundle0.putString("android.support.customtabs.customaction.DESCRIPTION", s);
        Bundle bundle1 = new Bundle();
        bundle1.putBundle("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE", bundle0);
        try {
            return this.mService.updateVisuals(this.mCallback, bundle1);
        }
        catch(RemoteException unused_ex) {
            return false;
        }
    }

    public boolean validateRelationship(int v, @NonNull Uri uri0, @Nullable Bundle bundle0) {
        if(v >= 1 && v <= 2) {
            try {
                return this.mService.validateRelationship(this.mCallback, v, uri0, bundle0);
            }
            catch(RemoteException unused_ex) {
                return false;
            }
        }
        return false;
    }
}

