package androidx.core.app;

import android.app.Activity;
import android.app.SharedElementCallback.OnSharedElementsReadyListener;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.DragEvent;
import android.view.View;
import androidx.annotation.IdRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.core.content.ContextCompat;
import androidx.core.view.DragAndDropPermissionsCompat;
import java.util.List;
import java.util.Map;

public class ActivityCompat extends ContextCompat {
    public interface OnRequestPermissionsResultCallback {
        void onRequestPermissionsResult(int arg1, @NonNull String[] arg2, @NonNull int[] arg3);
    }

    public interface PermissionCompatDelegate {
        boolean onActivityResult(@NonNull Activity arg1, @IntRange(from = 0L) int arg2, int arg3, @Nullable Intent arg4);

        boolean requestPermissions(@NonNull Activity arg1, @NonNull String[] arg2, @IntRange(from = 0L) int arg3);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public interface RequestPermissionsRequestCodeValidator {
        void validateRequestPermissionsRequestCode(int arg1);
    }

    @RequiresApi(21)
    static class SharedElementCallback21Impl extends SharedElementCallback {
        private final androidx.core.app.SharedElementCallback mCallback;

        SharedElementCallback21Impl(androidx.core.app.SharedElementCallback sharedElementCallback0) {
            this.mCallback = sharedElementCallback0;
        }

        @Override  // android.app.SharedElementCallback
        public Parcelable onCaptureSharedElementSnapshot(View view0, Matrix matrix0, RectF rectF0) {
            return this.mCallback.onCaptureSharedElementSnapshot(view0, matrix0, rectF0);
        }

        @Override  // android.app.SharedElementCallback
        public View onCreateSnapshotView(Context context0, Parcelable parcelable0) {
            return this.mCallback.onCreateSnapshotView(context0, parcelable0);
        }

        @Override  // android.app.SharedElementCallback
        public void onMapSharedElements(List list0, Map map0) {
        }

        @Override  // android.app.SharedElementCallback
        public void onRejectSharedElements(List list0) {
        }

        @Override  // android.app.SharedElementCallback
        public void onSharedElementEnd(List list0, List list1, List list2) {
        }

        @Override  // android.app.SharedElementCallback
        public void onSharedElementStart(List list0, List list1, List list2) {
        }

        @Override  // android.app.SharedElementCallback
        @RequiresApi(23)
        public void onSharedElementsArrived(List list0, List list1, SharedElementCallback.OnSharedElementsReadyListener sharedElementCallback$OnSharedElementsReadyListener0) {
            androidx.core.app.ActivityCompat.SharedElementCallback21Impl.1 activityCompat$SharedElementCallback21Impl$10 = new OnSharedElementsReadyListener() {
                @Override  // androidx.core.app.SharedElementCallback$OnSharedElementsReadyListener
                public void onSharedElementsReady() {
                    sharedElementCallback$OnSharedElementsReadyListener0.onSharedElementsReady();
                }
            };
            this.mCallback.onSharedElementsArrived(list0, list1, activityCompat$SharedElementCallback21Impl$10);
        }
    }

    private static PermissionCompatDelegate sDelegate;

    public static void finishAffinity(@NonNull Activity activity0) {
        if(Build.VERSION.SDK_INT >= 16) {
            activity0.finishAffinity();
            return;
        }
        activity0.finish();
    }

    public static void finishAfterTransition(@NonNull Activity activity0) {
        if(Build.VERSION.SDK_INT >= 21) {
            activity0.finishAfterTransition();
            return;
        }
        activity0.finish();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static PermissionCompatDelegate getPermissionCompatDelegate() {
        return ActivityCompat.sDelegate;
    }

    @Nullable
    public static Uri getReferrer(@NonNull Activity activity0) {
        if(Build.VERSION.SDK_INT >= 22) {
            return activity0.getReferrer();
        }
        Intent intent0 = activity0.getIntent();
        Uri uri0 = (Uri)intent0.getParcelableExtra("android.intent.extra.REFERRER");
        if(uri0 != null) {
            return uri0;
        }
        String s = intent0.getStringExtra("android.intent.extra.REFERRER_NAME");
        return s == null ? null : Uri.parse(s);
    }

    @Deprecated
    public static boolean invalidateOptionsMenu(Activity activity0) {
        activity0.invalidateOptionsMenu();
        return true;
    }

    public static void postponeEnterTransition(@NonNull Activity activity0) {
        if(Build.VERSION.SDK_INT >= 21) {
            activity0.postponeEnterTransition();
        }
    }

    @Nullable
    public static DragAndDropPermissionsCompat requestDragAndDropPermissions(Activity activity0, DragEvent dragEvent0) {
        return DragAndDropPermissionsCompat.request(activity0, dragEvent0);
    }

    public static void requestPermissions(@NonNull Activity activity0, @NonNull String[] arr_s, @IntRange(from = 0L) int v) {
        if(ActivityCompat.sDelegate != null && ActivityCompat.sDelegate.requestPermissions(activity0, arr_s, v)) {
            return;
        }
        if(Build.VERSION.SDK_INT >= 23) {
            if(activity0 instanceof RequestPermissionsRequestCodeValidator) {
                ((RequestPermissionsRequestCodeValidator)activity0).validateRequestPermissionsRequestCode(v);
            }
            activity0.requestPermissions(arr_s, v);
            return;
        }
        if(activity0 instanceof OnRequestPermissionsResultCallback) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    int[] arr_v = new int[arr_s.length];
                    PackageManager packageManager0 = activity0.getPackageManager();
                    for(int v = 0; v < arr_s.length; ++v) {
                        arr_v[v] = packageManager0.checkPermission(arr_s[v], "com.esotericsoftware.gloomhavenhelper");
                    }
                    ((OnRequestPermissionsResultCallback)activity0).onRequestPermissionsResult(v, arr_s, arr_v);
                }
            });
        }
    }

    @NonNull
    public static View requireViewById(@NonNull Activity activity0, @IdRes int v) {
        if(Build.VERSION.SDK_INT >= 28) {
            return activity0.requireViewById(v);
        }
        View view0 = activity0.findViewById(v);
        if(view0 == null) {
            throw new IllegalArgumentException("ID does not reference a View inside this Activity");
        }
        return view0;
    }

    public static void setEnterSharedElementCallback(@NonNull Activity activity0, @Nullable androidx.core.app.SharedElementCallback sharedElementCallback0) {
        if(Build.VERSION.SDK_INT >= 21) {
            activity0.setEnterSharedElementCallback((sharedElementCallback0 == null ? null : new SharedElementCallback21Impl(sharedElementCallback0)));
        }
    }

    public static void setExitSharedElementCallback(@NonNull Activity activity0, @Nullable androidx.core.app.SharedElementCallback sharedElementCallback0) {
        if(Build.VERSION.SDK_INT >= 21) {
            activity0.setExitSharedElementCallback((sharedElementCallback0 == null ? null : new SharedElementCallback21Impl(sharedElementCallback0)));
        }
    }

    public static void setPermissionCompatDelegate(@Nullable PermissionCompatDelegate activityCompat$PermissionCompatDelegate0) {
        ActivityCompat.sDelegate = activityCompat$PermissionCompatDelegate0;
    }

    public static boolean shouldShowRequestPermissionRationale(@NonNull Activity activity0, @NonNull String s) {
        return Build.VERSION.SDK_INT < 23 ? false : activity0.shouldShowRequestPermissionRationale(s);
    }

    public static void startActivityForResult(@NonNull Activity activity0, @NonNull Intent intent0, int v, @Nullable Bundle bundle0) {
        if(Build.VERSION.SDK_INT >= 16) {
            activity0.startActivityForResult(intent0, v, bundle0);
            return;
        }
        activity0.startActivityForResult(intent0, v);
    }

    public static void startIntentSenderForResult(@NonNull Activity activity0, @NonNull IntentSender intentSender0, int v, @Nullable Intent intent0, int v1, int v2, int v3, @Nullable Bundle bundle0) throws IntentSender.SendIntentException {
        if(Build.VERSION.SDK_INT >= 16) {
            activity0.startIntentSenderForResult(intentSender0, v, intent0, v1, v2, v3, bundle0);
            return;
        }
        activity0.startIntentSenderForResult(intentSender0, v, intent0, v1, v2, v3);
    }

    public static void startPostponedEnterTransition(@NonNull Activity activity0) {
        if(Build.VERSION.SDK_INT >= 21) {
            activity0.startPostponedEnterTransition();
        }
    }
}

