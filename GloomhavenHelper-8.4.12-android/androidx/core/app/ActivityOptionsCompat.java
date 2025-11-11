package androidx.core.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.util.Pair;

public class ActivityOptionsCompat {
    @RequiresApi(16)
    static class ActivityOptionsCompatImpl extends ActivityOptionsCompat {
        private final ActivityOptions mActivityOptions;

        ActivityOptionsCompatImpl(ActivityOptions activityOptions0) {
            this.mActivityOptions = activityOptions0;
        }

        @Override  // androidx.core.app.ActivityOptionsCompat
        public Rect getLaunchBounds() {
            return Build.VERSION.SDK_INT >= 24 ? this.mActivityOptions.getLaunchBounds() : null;
        }

        @Override  // androidx.core.app.ActivityOptionsCompat
        public void requestUsageTimeReport(PendingIntent pendingIntent0) {
            if(Build.VERSION.SDK_INT >= 23) {
                this.mActivityOptions.requestUsageTimeReport(pendingIntent0);
            }
        }

        @Override  // androidx.core.app.ActivityOptionsCompat
        public ActivityOptionsCompat setLaunchBounds(@Nullable Rect rect0) {
            return Build.VERSION.SDK_INT >= 24 ? new ActivityOptionsCompatImpl(this.mActivityOptions.setLaunchBounds(rect0)) : this;
        }

        @Override  // androidx.core.app.ActivityOptionsCompat
        public Bundle toBundle() {
            return this.mActivityOptions.toBundle();
        }

        @Override  // androidx.core.app.ActivityOptionsCompat
        public void update(ActivityOptionsCompat activityOptionsCompat0) {
            if(activityOptionsCompat0 instanceof ActivityOptionsCompatImpl) {
                this.mActivityOptions.update(((ActivityOptionsCompatImpl)activityOptionsCompat0).mActivityOptions);
            }
        }
    }

    public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";
    public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";

    @Nullable
    public Rect getLaunchBounds() {
        return null;
    }

    @NonNull
    public static ActivityOptionsCompat makeBasic() {
        return Build.VERSION.SDK_INT >= 23 ? new ActivityOptionsCompatImpl(ActivityOptions.makeBasic()) : new ActivityOptionsCompat();
    }

    @NonNull
    public static ActivityOptionsCompat makeClipRevealAnimation(@NonNull View view0, int v, int v1, int v2, int v3) {
        return Build.VERSION.SDK_INT >= 23 ? new ActivityOptionsCompatImpl(ActivityOptions.makeClipRevealAnimation(view0, v, v1, v2, v3)) : new ActivityOptionsCompat();
    }

    @NonNull
    public static ActivityOptionsCompat makeCustomAnimation(@NonNull Context context0, int v, int v1) {
        return Build.VERSION.SDK_INT >= 16 ? new ActivityOptionsCompatImpl(ActivityOptions.makeCustomAnimation(context0, v, v1)) : new ActivityOptionsCompat();
    }

    @NonNull
    public static ActivityOptionsCompat makeScaleUpAnimation(@NonNull View view0, int v, int v1, int v2, int v3) {
        return Build.VERSION.SDK_INT >= 16 ? new ActivityOptionsCompatImpl(ActivityOptions.makeScaleUpAnimation(view0, v, v1, v2, v3)) : new ActivityOptionsCompat();
    }

    @NonNull
    public static ActivityOptionsCompat makeSceneTransitionAnimation(@NonNull Activity activity0, @NonNull View view0, @NonNull String s) {
        return Build.VERSION.SDK_INT >= 21 ? new ActivityOptionsCompatImpl(ActivityOptions.makeSceneTransitionAnimation(activity0, view0, s)) : new ActivityOptionsCompat();
    }

    @NonNull
    public static ActivityOptionsCompat makeSceneTransitionAnimation(@NonNull Activity activity0, Pair[] arr_pair) {
        android.util.Pair[] arr_pair1 = null;
        if(Build.VERSION.SDK_INT >= 21) {
            if(arr_pair != null) {
                arr_pair1 = new android.util.Pair[arr_pair.length];
                for(int v = 0; v < arr_pair.length; ++v) {
                    arr_pair1[v] = android.util.Pair.create(arr_pair[v].first, arr_pair[v].second);
                }
            }
            return new ActivityOptionsCompatImpl(ActivityOptions.makeSceneTransitionAnimation(activity0, arr_pair1));
        }
        return new ActivityOptionsCompat();
    }

    @NonNull
    public static ActivityOptionsCompat makeTaskLaunchBehind() {
        return Build.VERSION.SDK_INT >= 21 ? new ActivityOptionsCompatImpl(ActivityOptions.makeTaskLaunchBehind()) : new ActivityOptionsCompat();
    }

    @NonNull
    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(@NonNull View view0, @NonNull Bitmap bitmap0, int v, int v1) {
        return Build.VERSION.SDK_INT >= 16 ? new ActivityOptionsCompatImpl(ActivityOptions.makeThumbnailScaleUpAnimation(view0, bitmap0, v, v1)) : new ActivityOptionsCompat();
    }

    public void requestUsageTimeReport(@NonNull PendingIntent pendingIntent0) {
    }

    @NonNull
    public ActivityOptionsCompat setLaunchBounds(@Nullable Rect rect0) {
        return this;
    }

    @Nullable
    public Bundle toBundle() {
        return null;
    }

    public void update(@NonNull ActivityOptionsCompat activityOptionsCompat0) {
    }
}

