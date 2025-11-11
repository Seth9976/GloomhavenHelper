package androidx.browser.customtabs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback.Stub;
import android.support.customtabs.ICustomTabsService;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.List;

public class CustomTabsClient {
    private final ICustomTabsService mService;
    private final ComponentName mServiceComponentName;

    @RestrictTo({Scope.LIBRARY_GROUP})
    CustomTabsClient(ICustomTabsService iCustomTabsService0, ComponentName componentName0) {
        this.mService = iCustomTabsService0;
        this.mServiceComponentName = componentName0;
    }

    public static boolean bindCustomTabsService(Context context0, String s, CustomTabsServiceConnection customTabsServiceConnection0) {
        Intent intent0 = new Intent("android.support.customtabs.action.CustomTabsService");
        if(!TextUtils.isEmpty(s)) {
            intent0.setPackage(s);
        }
        return context0.bindService(intent0, customTabsServiceConnection0, 33);
    }

    public static boolean connectAndInitialize(Context context0, String s) {
        if(s == null) {
            return false;
        }
        Context context1 = context0.getApplicationContext();
        androidx.browser.customtabs.CustomTabsClient.1 customTabsClient$10 = new CustomTabsServiceConnection() {
            @Override  // androidx.browser.customtabs.CustomTabsServiceConnection
            public final void onCustomTabsServiceConnected(ComponentName componentName0, CustomTabsClient customTabsClient0) {
                customTabsClient0.warmup(0L);
                context1.unbindService(this);
            }

            @Override  // android.content.ServiceConnection
            public final void onServiceDisconnected(ComponentName componentName0) {
            }
        };
        try {
            return CustomTabsClient.bindCustomTabsService(context1, s, customTabsClient$10);
        }
        catch(SecurityException unused_ex) {
            return false;
        }
    }

    public Bundle extraCommand(String s, Bundle bundle0) {
        try {
            return this.mService.extraCommand(s, bundle0);
        }
        catch(RemoteException unused_ex) {
            return null;
        }
    }

    public static String getPackageName(Context context0, @Nullable List list0) {
        return CustomTabsClient.getPackageName(context0, list0, false);
    }

    public static String getPackageName(Context context0, @Nullable List list0, boolean z) {
        PackageManager packageManager0 = context0.getPackageManager();
        List list1 = list0 == null ? new ArrayList() : list0;
        Intent intent0 = new Intent("android.intent.action.VIEW", Uri.parse("http://"));
        if(!z) {
            ResolveInfo resolveInfo0 = packageManager0.resolveActivity(intent0, 0);
            if(resolveInfo0 != null) {
                String s = resolveInfo0.activityInfo.packageName;
                ArrayList arrayList0 = new ArrayList(list1.size() + 1);
                arrayList0.add(s);
                if(list0 != null) {
                    arrayList0.addAll(list0);
                }
                list1 = arrayList0;
            }
        }
        Intent intent1 = new Intent("android.support.customtabs.action.CustomTabsService");
        for(Object object0: list1) {
            String s1 = (String)object0;
            intent1.setPackage(s1);
            if(packageManager0.resolveService(intent1, 0) != null) {
                return s1;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    public CustomTabsSession newSession(CustomTabsCallback customTabsCallback0) {
        androidx.browser.customtabs.CustomTabsClient.2 customTabsClient$20 = new Stub() {
            private Handler mHandler;

            {
                CustomTabsCallback customTabsCallback0 = customTabsCallback0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                this.mHandler = new Handler(Looper.getMainLooper());
            }

            @Override  // android.support.customtabs.ICustomTabsCallback
            public void extraCallback(String s, Bundle bundle0) throws RemoteException {
                if(customTabsCallback0 == null) {
                    return;
                }
                this.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        androidx.browser.customtabs.CustomTabsClient.2.this.val$callback.extraCallback(s, bundle0);
                    }
                });
            }

            @Override  // android.support.customtabs.ICustomTabsCallback
            public void onMessageChannelReady(Bundle bundle0) throws RemoteException {
                if(customTabsCallback0 == null) {
                    return;
                }
                this.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        androidx.browser.customtabs.CustomTabsClient.2.this.val$callback.onMessageChannelReady(bundle0);
                    }
                });
            }

            @Override  // android.support.customtabs.ICustomTabsCallback
            public void onNavigationEvent(int v, Bundle bundle0) {
                if(customTabsCallback0 == null) {
                    return;
                }
                this.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        androidx.browser.customtabs.CustomTabsClient.2.this.val$callback.onNavigationEvent(v, bundle0);
                    }
                });
            }

            @Override  // android.support.customtabs.ICustomTabsCallback
            public void onPostMessage(String s, Bundle bundle0) throws RemoteException {
                if(customTabsCallback0 == null) {
                    return;
                }
                this.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        androidx.browser.customtabs.CustomTabsClient.2.this.val$callback.onPostMessage(s, bundle0);
                    }
                });
            }

            @Override  // android.support.customtabs.ICustomTabsCallback
            public void onRelationshipValidationResult(int v, Uri uri0, boolean z, @Nullable Bundle bundle0) throws RemoteException {
                if(customTabsCallback0 == null) {
                    return;
                }
                this.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        androidx.browser.customtabs.CustomTabsClient.2.this.val$callback.onRelationshipValidationResult(v, uri0, z, bundle0);
                    }
                });
            }
        };
        try {
            if(!this.mService.newSession(customTabsClient$20)) {
                return null;
            }
        }
        catch(RemoteException unused_ex) {
            return null;
        }
        return new CustomTabsSession(this.mService, customTabsClient$20, this.mServiceComponentName);
    }

    public boolean warmup(long v) {
        try {
            return this.mService.warmup(v);
        }
        catch(RemoteException unused_ex) {
            return false;
        }
    }
}

