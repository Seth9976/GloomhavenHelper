package androidx.browser.customtabs;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder.DeathRecipient;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback;
import android.support.customtabs.ICustomTabsService.Stub;
import androidx.collection.ArrayMap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class CustomTabsService extends Service {
    @Retention(RetentionPolicy.SOURCE)
    public @interface Relation {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Result {
    }

    public static final String ACTION_CUSTOM_TABS_CONNECTION = "android.support.customtabs.action.CustomTabsService";
    public static final String KEY_URL = "android.support.customtabs.otherurls.URL";
    public static final int RELATION_HANDLE_ALL_URLS = 2;
    public static final int RELATION_USE_AS_ORIGIN = 1;
    public static final int RESULT_FAILURE_DISALLOWED = -1;
    public static final int RESULT_FAILURE_MESSAGING_ERROR = -3;
    public static final int RESULT_FAILURE_REMOTE_ERROR = -2;
    public static final int RESULT_SUCCESS;
    private Stub mBinder;
    final Map mDeathRecipientMap;

    public CustomTabsService() {
        this.mDeathRecipientMap = new ArrayMap();
        this.mBinder = new Stub() {
            @Override  // android.support.customtabs.ICustomTabsService
            public Bundle extraCommand(String s, Bundle bundle0) {
                return CustomTabsService.this.extraCommand(s, bundle0);
            }

            @Override  // android.support.customtabs.ICustomTabsService
            public boolean mayLaunchUrl(ICustomTabsCallback iCustomTabsCallback0, Uri uri0, Bundle bundle0, List list0) {
                CustomTabsSessionToken customTabsSessionToken0 = new CustomTabsSessionToken(iCustomTabsCallback0);
                return CustomTabsService.this.mayLaunchUrl(customTabsSessionToken0, uri0, bundle0, list0);
            }

            @Override  // android.support.customtabs.ICustomTabsService
            public boolean newSession(ICustomTabsCallback iCustomTabsCallback0) {
                CustomTabsSessionToken customTabsSessionToken0 = new CustomTabsSessionToken(iCustomTabsCallback0);
                try {
                    androidx.browser.customtabs.CustomTabsService.1.1 customTabsService$1$10 = new IBinder.DeathRecipient() {
                        @Override  // android.os.IBinder$DeathRecipient
                        public void binderDied() {
                            CustomTabsService.this.cleanUpSession(customTabsSessionToken0);
                        }
                    };
                    synchronized(CustomTabsService.this.mDeathRecipientMap) {
                        iCustomTabsCallback0.asBinder().linkToDeath(customTabsService$1$10, 0);
                        IBinder iBinder0 = iCustomTabsCallback0.asBinder();
                        CustomTabsService.this.mDeathRecipientMap.put(iBinder0, customTabsService$1$10);
                    }
                    return CustomTabsService.this.newSession(customTabsSessionToken0);
                }
                catch(RemoteException unused_ex) {
                    return false;
                }
            }

            @Override  // android.support.customtabs.ICustomTabsService
            public int postMessage(ICustomTabsCallback iCustomTabsCallback0, String s, Bundle bundle0) {
                CustomTabsSessionToken customTabsSessionToken0 = new CustomTabsSessionToken(iCustomTabsCallback0);
                return CustomTabsService.this.postMessage(customTabsSessionToken0, s, bundle0);
            }

            @Override  // android.support.customtabs.ICustomTabsService
            public boolean requestPostMessageChannel(ICustomTabsCallback iCustomTabsCallback0, Uri uri0) {
                CustomTabsSessionToken customTabsSessionToken0 = new CustomTabsSessionToken(iCustomTabsCallback0);
                return CustomTabsService.this.requestPostMessageChannel(customTabsSessionToken0, uri0);
            }

            @Override  // android.support.customtabs.ICustomTabsService
            public boolean updateVisuals(ICustomTabsCallback iCustomTabsCallback0, Bundle bundle0) {
                CustomTabsSessionToken customTabsSessionToken0 = new CustomTabsSessionToken(iCustomTabsCallback0);
                return CustomTabsService.this.updateVisuals(customTabsSessionToken0, bundle0);
            }

            @Override  // android.support.customtabs.ICustomTabsService
            public boolean validateRelationship(ICustomTabsCallback iCustomTabsCallback0, int v, Uri uri0, Bundle bundle0) {
                CustomTabsSessionToken customTabsSessionToken0 = new CustomTabsSessionToken(iCustomTabsCallback0);
                return CustomTabsService.this.validateRelationship(customTabsSessionToken0, v, uri0, bundle0);
            }

            @Override  // android.support.customtabs.ICustomTabsService
            public boolean warmup(long v) {
                return CustomTabsService.this.warmup(v);
            }
        };
    }

    protected boolean cleanUpSession(CustomTabsSessionToken customTabsSessionToken0) {
        try {
            synchronized(this.mDeathRecipientMap) {
                IBinder iBinder0 = customTabsSessionToken0.getCallbackBinder();
                iBinder0.unlinkToDeath(((IBinder.DeathRecipient)this.mDeathRecipientMap.get(iBinder0)), 0);
                this.mDeathRecipientMap.remove(iBinder0);
                return true;
            }
        }
        catch(NoSuchElementException unused_ex) {
            return false;
        }
    }

    protected abstract Bundle extraCommand(String arg1, Bundle arg2);

    protected abstract boolean mayLaunchUrl(CustomTabsSessionToken arg1, Uri arg2, Bundle arg3, List arg4);

    protected abstract boolean newSession(CustomTabsSessionToken arg1);

    @Override  // android.app.Service
    public IBinder onBind(Intent intent0) {
        return this.mBinder;
    }

    protected abstract int postMessage(CustomTabsSessionToken arg1, String arg2, Bundle arg3);

    protected abstract boolean requestPostMessageChannel(CustomTabsSessionToken arg1, Uri arg2);

    protected abstract boolean updateVisuals(CustomTabsSessionToken arg1, Bundle arg2);

    protected abstract boolean validateRelationship(CustomTabsSessionToken arg1, int arg2, Uri arg3, Bundle arg4);

    protected abstract boolean warmup(long arg1);
}

