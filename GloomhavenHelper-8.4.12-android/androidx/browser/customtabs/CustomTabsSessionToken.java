package androidx.browser.customtabs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback.Stub;
import android.support.customtabs.ICustomTabsCallback;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.BundleCompat;

public class CustomTabsSessionToken {
    static class MockCallback extends Stub {
        @Override  // android.support.customtabs.ICustomTabsCallback$Stub
        public IBinder asBinder() {
            return this;
        }

        @Override  // android.support.customtabs.ICustomTabsCallback
        public void extraCallback(String s, Bundle bundle0) {
        }

        @Override  // android.support.customtabs.ICustomTabsCallback
        public void onMessageChannelReady(Bundle bundle0) {
        }

        @Override  // android.support.customtabs.ICustomTabsCallback
        public void onNavigationEvent(int v, Bundle bundle0) {
        }

        @Override  // android.support.customtabs.ICustomTabsCallback
        public void onPostMessage(String s, Bundle bundle0) {
        }

        @Override  // android.support.customtabs.ICustomTabsCallback
        public void onRelationshipValidationResult(int v, Uri uri0, boolean z, Bundle bundle0) {
        }
    }

    private static final String TAG = "CustomTabsSessionToken";
    private final CustomTabsCallback mCallback;
    final ICustomTabsCallback mCallbackBinder;

    CustomTabsSessionToken(ICustomTabsCallback iCustomTabsCallback0) {
        this.mCallbackBinder = iCustomTabsCallback0;
        this.mCallback = new CustomTabsCallback() {
            @Override  // androidx.browser.customtabs.CustomTabsCallback
            public void extraCallback(String s, Bundle bundle0) {
                try {
                    CustomTabsSessionToken.this.mCallbackBinder.extraCallback(s, bundle0);
                }
                catch(RemoteException unused_ex) {
                    Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
                }
            }

            @Override  // androidx.browser.customtabs.CustomTabsCallback
            public void onMessageChannelReady(Bundle bundle0) {
                try {
                    CustomTabsSessionToken.this.mCallbackBinder.onMessageChannelReady(bundle0);
                }
                catch(RemoteException unused_ex) {
                    Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
                }
            }

            @Override  // androidx.browser.customtabs.CustomTabsCallback
            public void onNavigationEvent(int v, Bundle bundle0) {
                try {
                    CustomTabsSessionToken.this.mCallbackBinder.onNavigationEvent(v, bundle0);
                }
                catch(RemoteException unused_ex) {
                    Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
                }
            }

            @Override  // androidx.browser.customtabs.CustomTabsCallback
            public void onPostMessage(String s, Bundle bundle0) {
                try {
                    CustomTabsSessionToken.this.mCallbackBinder.onPostMessage(s, bundle0);
                }
                catch(RemoteException unused_ex) {
                    Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
                }
            }

            @Override  // androidx.browser.customtabs.CustomTabsCallback
            public void onRelationshipValidationResult(int v, Uri uri0, boolean z, Bundle bundle0) {
                try {
                    CustomTabsSessionToken.this.mCallbackBinder.onRelationshipValidationResult(v, uri0, z, bundle0);
                }
                catch(RemoteException unused_ex) {
                    Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
                }
            }
        };
    }

    @NonNull
    public static CustomTabsSessionToken createMockSessionTokenForTesting() {
        return new CustomTabsSessionToken(new MockCallback());
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof CustomTabsSessionToken ? ((CustomTabsSessionToken)object0).getCallbackBinder().equals(this.mCallbackBinder.asBinder()) : false;
    }

    public CustomTabsCallback getCallback() {
        return this.mCallback;
    }

    IBinder getCallbackBinder() {
        return this.mCallbackBinder.asBinder();
    }

    public static CustomTabsSessionToken getSessionTokenFromIntent(Intent intent0) {
        IBinder iBinder0 = BundleCompat.getBinder(intent0.getExtras(), "android.support.customtabs.extra.SESSION");
        return iBinder0 == null ? null : new CustomTabsSessionToken(Stub.asInterface(iBinder0));
    }

    @Override
    public int hashCode() {
        return this.getCallbackBinder().hashCode();
    }

    public boolean isAssociatedWith(CustomTabsSession customTabsSession0) {
        return customTabsSession0.getBinder().equals(this.mCallbackBinder);
    }
}

