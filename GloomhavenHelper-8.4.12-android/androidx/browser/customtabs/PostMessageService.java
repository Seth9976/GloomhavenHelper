package androidx.browser.customtabs;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback;
import android.support.customtabs.IPostMessageService.Stub;

public class PostMessageService extends Service {
    private Stub mBinder;

    public PostMessageService() {
        this.mBinder = new Stub() {
            @Override  // android.support.customtabs.IPostMessageService
            public void onMessageChannelReady(ICustomTabsCallback iCustomTabsCallback0, Bundle bundle0) throws RemoteException {
                iCustomTabsCallback0.onMessageChannelReady(bundle0);
            }

            @Override  // android.support.customtabs.IPostMessageService
            public void onPostMessage(ICustomTabsCallback iCustomTabsCallback0, String s, Bundle bundle0) throws RemoteException {
                iCustomTabsCallback0.onPostMessage(s, bundle0);
            }
        };
    }

    @Override  // android.app.Service
    public IBinder onBind(Intent intent0) {
        return this.mBinder;
    }
}

