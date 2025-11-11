package androidx.core.content;

import android.accessibilityservice.AccessibilityService;
import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.app.admin.DevicePolicyManager;
import android.app.job.JobScheduler;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.RestrictionsManager;
import android.content.pm.LauncherApps;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.tv.TvInputManager;
import android.net.ConnectivityManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.os.PowerManager;
import android.os.Process;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.CaptioningManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.TextServicesManager;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.File;
import java.util.HashMap;

public class ContextCompat {
    static final class LegacyServiceMapHolder {
        static final HashMap SERVICES;

        static {
            LegacyServiceMapHolder.SERVICES = new HashMap();
            if(Build.VERSION.SDK_INT > 22) {
                LegacyServiceMapHolder.SERVICES.put(SubscriptionManager.class, "telephony_subscription_service");
                LegacyServiceMapHolder.SERVICES.put(UsageStatsManager.class, "usagestats");
            }
            if(Build.VERSION.SDK_INT > 21) {
                LegacyServiceMapHolder.SERVICES.put(AppWidgetManager.class, "appwidget");
                LegacyServiceMapHolder.SERVICES.put(BatteryManager.class, "batterymanager");
                LegacyServiceMapHolder.SERVICES.put(CameraManager.class, "camera");
                LegacyServiceMapHolder.SERVICES.put(JobScheduler.class, "jobscheduler");
                LegacyServiceMapHolder.SERVICES.put(LauncherApps.class, "launcherapps");
                LegacyServiceMapHolder.SERVICES.put(MediaProjectionManager.class, "media_projection");
                LegacyServiceMapHolder.SERVICES.put(MediaSessionManager.class, "media_session");
                LegacyServiceMapHolder.SERVICES.put(RestrictionsManager.class, "restrictions");
                LegacyServiceMapHolder.SERVICES.put(TelecomManager.class, "telecom");
                LegacyServiceMapHolder.SERVICES.put(TvInputManager.class, "tv_input");
            }
            if(Build.VERSION.SDK_INT > 19) {
                LegacyServiceMapHolder.SERVICES.put(AppOpsManager.class, "appops");
                LegacyServiceMapHolder.SERVICES.put(CaptioningManager.class, "captioning");
                LegacyServiceMapHolder.SERVICES.put(ConsumerIrManager.class, "consumer_ir");
                LegacyServiceMapHolder.SERVICES.put(PrintManager.class, "print");
            }
            if(Build.VERSION.SDK_INT > 18) {
                LegacyServiceMapHolder.SERVICES.put(BluetoothManager.class, "bluetooth");
            }
            if(Build.VERSION.SDK_INT > 17) {
                LegacyServiceMapHolder.SERVICES.put(DisplayManager.class, "display");
                LegacyServiceMapHolder.SERVICES.put(UserManager.class, "user");
            }
            if(Build.VERSION.SDK_INT > 16) {
                LegacyServiceMapHolder.SERVICES.put(InputManager.class, "input");
                LegacyServiceMapHolder.SERVICES.put(MediaRouter.class, "media_router");
                LegacyServiceMapHolder.SERVICES.put(NsdManager.class, "servicediscovery");
            }
            LegacyServiceMapHolder.SERVICES.put(AccessibilityService.class, "accessibility");
            LegacyServiceMapHolder.SERVICES.put(AccountManager.class, "account");
            LegacyServiceMapHolder.SERVICES.put(ActivityManager.class, "activity");
            LegacyServiceMapHolder.SERVICES.put(AlarmManager.class, "alarm");
            LegacyServiceMapHolder.SERVICES.put(AudioManager.class, "audio");
            LegacyServiceMapHolder.SERVICES.put(ClipboardManager.class, "clipboard");
            LegacyServiceMapHolder.SERVICES.put(ConnectivityManager.class, "connectivity");
            LegacyServiceMapHolder.SERVICES.put(DevicePolicyManager.class, "device_policy");
            LegacyServiceMapHolder.SERVICES.put(DownloadManager.class, "download");
            LegacyServiceMapHolder.SERVICES.put(DropBoxManager.class, "dropbox");
            LegacyServiceMapHolder.SERVICES.put(InputMethodManager.class, "input_method");
            LegacyServiceMapHolder.SERVICES.put(KeyguardManager.class, "keyguard");
            LegacyServiceMapHolder.SERVICES.put(LayoutInflater.class, "layout_inflater");
            LegacyServiceMapHolder.SERVICES.put(LocationManager.class, "location");
            LegacyServiceMapHolder.SERVICES.put(NfcManager.class, "nfc");
            LegacyServiceMapHolder.SERVICES.put(NotificationManager.class, "notification");
            LegacyServiceMapHolder.SERVICES.put(PowerManager.class, "power");
            LegacyServiceMapHolder.SERVICES.put(SearchManager.class, "search");
            LegacyServiceMapHolder.SERVICES.put(SensorManager.class, "sensor");
            LegacyServiceMapHolder.SERVICES.put(StorageManager.class, "storage");
            LegacyServiceMapHolder.SERVICES.put(TelephonyManager.class, "phone");
            LegacyServiceMapHolder.SERVICES.put(TextServicesManager.class, "textservices");
            LegacyServiceMapHolder.SERVICES.put(UiModeManager.class, "uimode");
            LegacyServiceMapHolder.SERVICES.put(UsbManager.class, "usb");
            LegacyServiceMapHolder.SERVICES.put(Vibrator.class, "vibrator");
            LegacyServiceMapHolder.SERVICES.put(WallpaperManager.class, "wallpaper");
            LegacyServiceMapHolder.SERVICES.put(WifiP2pManager.class, "wifip2p");
            LegacyServiceMapHolder.SERVICES.put(WifiManager.class, "wifi");
            LegacyServiceMapHolder.SERVICES.put(WindowManager.class, "window");
        }
    }

    private static final String TAG = "ContextCompat";
    private static final Object sLock;
    private static TypedValue sTempValue;

    static {
        ContextCompat.sLock = new Object();
    }

    private static File buildPath(File file0, String[] arr_s) {
        for(int v = 0; v < arr_s.length; ++v) {
            String s = arr_s[v];
            if(file0 == null) {
                file0 = new File(s);
            }
            else if(s != null) {
                file0 = new File(file0, s);
            }
        }
        return file0;
    }

    public static int checkSelfPermission(@NonNull Context context0, @NonNull String s) {
        if(s == null) {
            throw new IllegalArgumentException("permission is null");
        }
        return context0.checkPermission(s, Process.myPid(), Process.myUid());
    }

    @Nullable
    public static Context createDeviceProtectedStorageContext(@NonNull Context context0) {
        return Build.VERSION.SDK_INT < 24 ? null : context0.createDeviceProtectedStorageContext();
    }

    private static File createFilesDir(File file0) {
        synchronized(ContextCompat.class) {
            if(!file0.exists() && !file0.mkdirs()) {
                if(file0.exists()) {
                    return file0;
                }
                Log.w("ContextCompat", "Unable to create files subdir " + file0.getPath());
                return null;
            }
            return file0;
        }
    }

    public static File getCodeCacheDir(@NonNull Context context0) {
        return Build.VERSION.SDK_INT < 21 ? ContextCompat.createFilesDir(new File(context0.getApplicationInfo().dataDir, "code_cache")) : context0.getCodeCacheDir();
    }

    @ColorInt
    public static int getColor(@NonNull Context context0, @ColorRes int v) {
        return Build.VERSION.SDK_INT < 23 ? context0.getResources().getColor(v) : context0.getColor(v);
    }

    @Nullable
    public static ColorStateList getColorStateList(@NonNull Context context0, @ColorRes int v) {
        return Build.VERSION.SDK_INT < 23 ? context0.getResources().getColorStateList(v) : context0.getColorStateList(v);
    }

    @Nullable
    public static File getDataDir(@NonNull Context context0) {
        if(Build.VERSION.SDK_INT >= 24) {
            return context0.getDataDir();
        }
        String s = context0.getApplicationInfo().dataDir;
        return s == null ? null : new File(s);
    }

    @Nullable
    public static Drawable getDrawable(@NonNull Context context0, @DrawableRes int v) {
        int v2;
        if(Build.VERSION.SDK_INT >= 21) {
            return context0.getDrawable(v);
        }
        if(Build.VERSION.SDK_INT >= 16) {
            return context0.getResources().getDrawable(v);
        }
        synchronized(ContextCompat.sLock) {
            if(ContextCompat.sTempValue == null) {
                ContextCompat.sTempValue = new TypedValue();
            }
            context0.getResources().getValue(v, ContextCompat.sTempValue, true);
            v2 = ContextCompat.sTempValue.resourceId;
        }
        return context0.getResources().getDrawable(v2);
    }

    @NonNull
    public static File[] getExternalCacheDirs(@NonNull Context context0) {
        return Build.VERSION.SDK_INT < 19 ? new File[]{context0.getExternalCacheDir()} : context0.getExternalCacheDirs();
    }

    @NonNull
    public static File[] getExternalFilesDirs(@NonNull Context context0, @Nullable String s) {
        return Build.VERSION.SDK_INT < 19 ? new File[]{context0.getExternalFilesDir(s)} : context0.getExternalFilesDirs(s);
    }

    @Nullable
    public static File getNoBackupFilesDir(@NonNull Context context0) {
        return Build.VERSION.SDK_INT < 21 ? ContextCompat.createFilesDir(new File(context0.getApplicationInfo().dataDir, "no_backup")) : context0.getNoBackupFilesDir();
    }

    @NonNull
    public static File[] getObbDirs(@NonNull Context context0) {
        return Build.VERSION.SDK_INT < 19 ? new File[]{context0.getObbDir()} : context0.getObbDirs();
    }

    @Nullable
    public static Object getSystemService(@NonNull Context context0, @NonNull Class class0) {
        if(Build.VERSION.SDK_INT >= 23) {
            return context0.getSystemService(class0);
        }
        String s = ContextCompat.getSystemServiceName(context0, class0);
        return s == null ? null : context0.getSystemService(s);
    }

    @Nullable
    public static String getSystemServiceName(@NonNull Context context0, @NonNull Class class0) {
        return Build.VERSION.SDK_INT < 23 ? ((String)LegacyServiceMapHolder.SERVICES.get(class0)) : context0.getSystemServiceName(class0);
    }

    public static boolean isDeviceProtectedStorage(@NonNull Context context0) {
        return Build.VERSION.SDK_INT < 24 ? false : context0.isDeviceProtectedStorage();
    }

    public static boolean startActivities(@NonNull Context context0, @NonNull Intent[] arr_intent) {
        return ContextCompat.startActivities(context0, arr_intent, null);
    }

    public static boolean startActivities(@NonNull Context context0, @NonNull Intent[] arr_intent, @Nullable Bundle bundle0) {
        if(Build.VERSION.SDK_INT >= 16) {
            context0.startActivities(arr_intent, bundle0);
            return true;
        }
        context0.startActivities(arr_intent);
        return true;
    }

    public static void startActivity(@NonNull Context context0, @NonNull Intent intent0, @Nullable Bundle bundle0) {
        if(Build.VERSION.SDK_INT >= 16) {
            context0.startActivity(intent0, bundle0);
            return;
        }
        context0.startActivity(intent0);
    }

    public static void startForegroundService(@NonNull Context context0, @NonNull Intent intent0) {
        if(Build.VERSION.SDK_INT >= 26) {
            context0.startForegroundService(intent0);
            return;
        }
        context0.startService(intent0);
    }
}

