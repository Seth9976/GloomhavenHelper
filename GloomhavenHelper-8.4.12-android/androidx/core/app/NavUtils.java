package androidx.core.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class NavUtils {
    public static final String PARENT_ACTIVITY = "android.support.PARENT_ACTIVITY";
    private static final String TAG = "NavUtils";

    @Nullable
    public static Intent getParentActivityIntent(@NonNull Activity activity0) {
        if(Build.VERSION.SDK_INT >= 16) {
            Intent intent0 = activity0.getParentActivityIntent();
            if(intent0 != null) {
                return intent0;
            }
        }
        String s = NavUtils.getParentActivityName(activity0);
        if(s == null) {
            return null;
        }
        ComponentName componentName0 = new ComponentName(activity0, s);
        try {
            return NavUtils.getParentActivityName(activity0, componentName0) == null ? Intent.makeMainActivity(componentName0) : new Intent().setComponent(componentName0);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName \'" + s + "\' in manifest");
            return null;
        }
    }

    @Nullable
    public static Intent getParentActivityIntent(@NonNull Context context0, @NonNull ComponentName componentName0) throws PackageManager.NameNotFoundException {
        String s = NavUtils.getParentActivityName(context0, componentName0);
        if(s == null) {
            return null;
        }
        ComponentName componentName1 = new ComponentName(componentName0.getPackageName(), s);
        return NavUtils.getParentActivityName(context0, componentName1) == null ? Intent.makeMainActivity(componentName1) : new Intent().setComponent(componentName1);
    }

    @Nullable
    public static Intent getParentActivityIntent(@NonNull Context context0, @NonNull Class class0) throws PackageManager.NameNotFoundException {
        String s = NavUtils.getParentActivityName(context0, new ComponentName(context0, class0));
        if(s == null) {
            return null;
        }
        ComponentName componentName0 = new ComponentName(context0, s);
        return NavUtils.getParentActivityName(context0, componentName0) == null ? Intent.makeMainActivity(componentName0) : new Intent().setComponent(componentName0);
    }

    @Nullable
    public static String getParentActivityName(@NonNull Activity activity0) {
        try {
            return NavUtils.getParentActivityName(activity0, activity0.getComponentName());
        }
        catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
            throw new IllegalArgumentException(packageManager$NameNotFoundException0);
        }
    }

    @Nullable
    public static String getParentActivityName(@NonNull Context context0, @NonNull ComponentName componentName0) throws PackageManager.NameNotFoundException {
        ActivityInfo activityInfo0 = context0.getPackageManager().getActivityInfo(componentName0, 0x80);
        if(Build.VERSION.SDK_INT >= 16) {
            String s = activityInfo0.parentActivityName;
            if(s != null) {
                return s;
            }
        }
        if(activityInfo0.metaData == null) {
            return null;
        }
        String s1 = activityInfo0.metaData.getString("android.support.PARENT_ACTIVITY");
        if(s1 == null) {
            return null;
        }
        return s1.charAt(0) == 46 ? "com.esotericsoftware.gloomhavenhelper" + s1 : s1;
    }

    public static void navigateUpFromSameTask(@NonNull Activity activity0) {
        Intent intent0 = NavUtils.getParentActivityIntent(activity0);
        if(intent0 == null) {
            throw new IllegalArgumentException("Activity " + activity0.getClass().getSimpleName() + " does not have a parent activity name specified." + " (Did you forget to add the android.support.PARENT_ACTIVITY <meta-data> " + " element in your manifest?)");
        }
        NavUtils.navigateUpTo(activity0, intent0);
    }

    public static void navigateUpTo(@NonNull Activity activity0, @NonNull Intent intent0) {
        if(Build.VERSION.SDK_INT >= 16) {
            activity0.navigateUpTo(intent0);
            return;
        }
        intent0.addFlags(0x4000000);
        activity0.startActivity(intent0);
        activity0.finish();
    }

    public static boolean shouldUpRecreateTask(@NonNull Activity activity0, @NonNull Intent intent0) {
        if(Build.VERSION.SDK_INT >= 16) {
            return activity0.shouldUpRecreateTask(intent0);
        }
        String s = activity0.getIntent().getAction();
        return s != null && !s.equals("android.intent.action.MAIN");
    }
}

