package androidx.core.app;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class BundleCompat {
    static class BundleCompatBaseImpl {
        private static final String TAG = "BundleCompatBaseImpl";
        private static Method sGetIBinderMethod;
        private static boolean sGetIBinderMethodFetched;
        private static Method sPutIBinderMethod;
        private static boolean sPutIBinderMethodFetched;

        public static IBinder getBinder(Bundle bundle0, String s) {
            if(!BundleCompatBaseImpl.sGetIBinderMethodFetched) {
                try {
                    BundleCompatBaseImpl.sGetIBinderMethod = Bundle.class.getMethod("getIBinder", String.class);
                    BundleCompatBaseImpl.sGetIBinderMethod.setAccessible(true);
                }
                catch(NoSuchMethodException noSuchMethodException0) {
                    Log.i("BundleCompatBaseImpl", "Failed to retrieve getIBinder method", noSuchMethodException0);
                }
                BundleCompatBaseImpl.sGetIBinderMethodFetched = true;
            }
            Method method0 = BundleCompatBaseImpl.sGetIBinderMethod;
            if(method0 != null) {
                try {
                    return (IBinder)method0.invoke(bundle0, s);
                }
                catch(InvocationTargetException | IllegalAccessException | IllegalArgumentException invocationTargetException0) {
                    Log.i("BundleCompatBaseImpl", "Failed to invoke getIBinder via reflection", invocationTargetException0);
                    BundleCompatBaseImpl.sGetIBinderMethod = null;
                }
            }
            return null;
        }

        public static void putBinder(Bundle bundle0, String s, IBinder iBinder0) {
            if(!BundleCompatBaseImpl.sPutIBinderMethodFetched) {
                try {
                    BundleCompatBaseImpl.sPutIBinderMethod = Bundle.class.getMethod("putIBinder", String.class, IBinder.class);
                    BundleCompatBaseImpl.sPutIBinderMethod.setAccessible(true);
                }
                catch(NoSuchMethodException noSuchMethodException0) {
                    Log.i("BundleCompatBaseImpl", "Failed to retrieve putIBinder method", noSuchMethodException0);
                }
                BundleCompatBaseImpl.sPutIBinderMethodFetched = true;
            }
            Method method0 = BundleCompatBaseImpl.sPutIBinderMethod;
            if(method0 != null) {
                try {
                    method0.invoke(bundle0, s, iBinder0);
                }
                catch(InvocationTargetException | IllegalAccessException | IllegalArgumentException invocationTargetException0) {
                    Log.i("BundleCompatBaseImpl", "Failed to invoke putIBinder via reflection", invocationTargetException0);
                    BundleCompatBaseImpl.sPutIBinderMethod = null;
                }
            }
        }
    }

    @Nullable
    public static IBinder getBinder(@NonNull Bundle bundle0, @Nullable String s) {
        return Build.VERSION.SDK_INT < 18 ? BundleCompatBaseImpl.getBinder(bundle0, s) : bundle0.getBinder(s);
    }

    public static void putBinder(@NonNull Bundle bundle0, @Nullable String s, @Nullable IBinder iBinder0) {
        if(Build.VERSION.SDK_INT >= 18) {
            bundle0.putBinder(s, iBinder0);
            return;
        }
        BundleCompatBaseImpl.putBinder(bundle0, s, iBinder0);
    }
}

