package androidx.core.content.pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo.Builder;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.drawable.IconCompat;
import java.util.Arrays;

public class ShortcutInfoCompat {
    public static class Builder {
        private final ShortcutInfoCompat mInfo;

        public Builder(@NonNull Context context0, @NonNull String s) {
            this.mInfo = new ShortcutInfoCompat();
            this.mInfo.mContext = context0;
            this.mInfo.mId = s;
        }

        @NonNull
        public ShortcutInfoCompat build() {
            if(TextUtils.isEmpty(this.mInfo.mLabel)) {
                throw new IllegalArgumentException("Shortcut must have a non-empty label");
            }
            if(this.mInfo.mIntents == null || this.mInfo.mIntents.length == 0) {
                throw new IllegalArgumentException("Shortcut must have an intent");
            }
            return this.mInfo;
        }

        @NonNull
        public Builder setActivity(@NonNull ComponentName componentName0) {
            this.mInfo.mActivity = componentName0;
            return this;
        }

        public Builder setAlwaysBadged() {
            this.mInfo.mIsAlwaysBadged = true;
            return this;
        }

        @NonNull
        public Builder setDisabledMessage(@NonNull CharSequence charSequence0) {
            this.mInfo.mDisabledMessage = charSequence0;
            return this;
        }

        @NonNull
        public Builder setIcon(IconCompat iconCompat0) {
            this.mInfo.mIcon = iconCompat0;
            return this;
        }

        @NonNull
        public Builder setIntent(@NonNull Intent intent0) {
            return this.setIntents(new Intent[]{intent0});
        }

        @NonNull
        public Builder setIntents(@NonNull Intent[] arr_intent) {
            this.mInfo.mIntents = arr_intent;
            return this;
        }

        @NonNull
        public Builder setLongLabel(@NonNull CharSequence charSequence0) {
            this.mInfo.mLongLabel = charSequence0;
            return this;
        }

        @NonNull
        public Builder setShortLabel(@NonNull CharSequence charSequence0) {
            this.mInfo.mLabel = charSequence0;
            return this;
        }
    }

    ComponentName mActivity;
    Context mContext;
    CharSequence mDisabledMessage;
    IconCompat mIcon;
    String mId;
    Intent[] mIntents;
    boolean mIsAlwaysBadged;
    CharSequence mLabel;
    CharSequence mLongLabel;

    Intent addToIntent(Intent intent0) {
        Drawable drawable0 = null;
        intent0.putExtra("android.intent.extra.shortcut.INTENT", this.mIntents[this.mIntents.length - 1]).putExtra("android.intent.extra.shortcut.NAME", this.mLabel.toString());
        if(this.mIcon != null) {
            if(this.mIsAlwaysBadged) {
                PackageManager packageManager0 = this.mContext.getPackageManager();
                ComponentName componentName0 = this.mActivity;
                if(componentName0 != null) {
                    try {
                        drawable0 = packageManager0.getActivityIcon(componentName0);
                    }
                    catch(PackageManager.NameNotFoundException unused_ex) {
                    }
                }
                if(drawable0 == null) {
                    drawable0 = this.mContext.getApplicationInfo().loadIcon(packageManager0);
                }
            }
            this.mIcon.addToShortcutIntent(intent0, drawable0, this.mContext);
        }
        return intent0;
    }

    @Nullable
    public ComponentName getActivity() {
        return this.mActivity;
    }

    @Nullable
    public CharSequence getDisabledMessage() {
        return this.mDisabledMessage;
    }

    @NonNull
    public String getId() {
        return this.mId;
    }

    @NonNull
    public Intent getIntent() {
        return this.mIntents[this.mIntents.length - 1];
    }

    @NonNull
    public Intent[] getIntents() {
        return (Intent[])Arrays.copyOf(this.mIntents, this.mIntents.length);
    }

    @Nullable
    public CharSequence getLongLabel() {
        return this.mLongLabel;
    }

    @NonNull
    public CharSequence getShortLabel() {
        return this.mLabel;
    }

    @RequiresApi(25)
    public ShortcutInfo toShortcutInfo() {
        ShortcutInfo.Builder shortcutInfo$Builder0 = new ShortcutInfo.Builder(this.mContext, this.mId).setShortLabel(this.mLabel).setIntents(this.mIntents);
        IconCompat iconCompat0 = this.mIcon;
        if(iconCompat0 != null) {
            shortcutInfo$Builder0.setIcon(iconCompat0.toIcon());
        }
        if(!TextUtils.isEmpty(this.mLongLabel)) {
            shortcutInfo$Builder0.setLongLabel(this.mLongLabel);
        }
        if(!TextUtils.isEmpty(this.mDisabledMessage)) {
            shortcutInfo$Builder0.setDisabledMessage(this.mDisabledMessage);
        }
        ComponentName componentName0 = this.mActivity;
        if(componentName0 != null) {
            shortcutInfo$Builder0.setActivity(componentName0);
        }
        return shortcutInfo$Builder0.build();
    }
}

