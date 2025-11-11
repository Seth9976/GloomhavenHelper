package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.Log;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.Field;

public final class CompoundButtonCompat {
    private static final String TAG = "CompoundButtonCompat";
    private static Field sButtonDrawableField;
    private static boolean sButtonDrawableFieldFetched;

    @Nullable
    public static Drawable getButtonDrawable(@NonNull CompoundButton compoundButton0) {
        if(Build.VERSION.SDK_INT >= 23) {
            return compoundButton0.getButtonDrawable();
        }
        if(!CompoundButtonCompat.sButtonDrawableFieldFetched) {
            try {
                CompoundButtonCompat.sButtonDrawableField = CompoundButton.class.getDeclaredField("mButtonDrawable");
                CompoundButtonCompat.sButtonDrawableField.setAccessible(true);
            }
            catch(NoSuchFieldException noSuchFieldException0) {
                Log.i("CompoundButtonCompat", "Failed to retrieve mButtonDrawable field", noSuchFieldException0);
            }
            CompoundButtonCompat.sButtonDrawableFieldFetched = true;
        }
        Field field0 = CompoundButtonCompat.sButtonDrawableField;
        if(field0 != null) {
            try {
                return (Drawable)field0.get(compoundButton0);
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.i("CompoundButtonCompat", "Failed to get button drawable via reflection", illegalAccessException0);
                CompoundButtonCompat.sButtonDrawableField = null;
            }
        }
        return null;
    }

    @Nullable
    public static ColorStateList getButtonTintList(@NonNull CompoundButton compoundButton0) {
        if(Build.VERSION.SDK_INT >= 21) {
            return compoundButton0.getButtonTintList();
        }
        return compoundButton0 instanceof TintableCompoundButton ? ((TintableCompoundButton)compoundButton0).getSupportButtonTintList() : null;
    }

    @Nullable
    public static PorterDuff.Mode getButtonTintMode(@NonNull CompoundButton compoundButton0) {
        if(Build.VERSION.SDK_INT >= 21) {
            return compoundButton0.getButtonTintMode();
        }
        return compoundButton0 instanceof TintableCompoundButton ? ((TintableCompoundButton)compoundButton0).getSupportButtonTintMode() : null;
    }

    public static void setButtonTintList(@NonNull CompoundButton compoundButton0, @Nullable ColorStateList colorStateList0) {
        if(Build.VERSION.SDK_INT >= 21) {
            compoundButton0.setButtonTintList(colorStateList0);
            return;
        }
        if(compoundButton0 instanceof TintableCompoundButton) {
            ((TintableCompoundButton)compoundButton0).setSupportButtonTintList(colorStateList0);
        }
    }

    public static void setButtonTintMode(@NonNull CompoundButton compoundButton0, @Nullable PorterDuff.Mode porterDuff$Mode0) {
        if(Build.VERSION.SDK_INT >= 21) {
            compoundButton0.setButtonTintMode(porterDuff$Mode0);
            return;
        }
        if(compoundButton0 instanceof TintableCompoundButton) {
            ((TintableCompoundButton)compoundButton0).setSupportButtonTintMode(porterDuff$Mode0);
        }
    }
}

