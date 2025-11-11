package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ImageViewCompat {
    @Nullable
    public static ColorStateList getImageTintList(@NonNull ImageView imageView0) {
        if(Build.VERSION.SDK_INT >= 21) {
            return imageView0.getImageTintList();
        }
        return imageView0 instanceof TintableImageSourceView ? ((TintableImageSourceView)imageView0).getSupportImageTintList() : null;
    }

    @Nullable
    public static PorterDuff.Mode getImageTintMode(@NonNull ImageView imageView0) {
        if(Build.VERSION.SDK_INT >= 21) {
            return imageView0.getImageTintMode();
        }
        return imageView0 instanceof TintableImageSourceView ? ((TintableImageSourceView)imageView0).getSupportImageTintMode() : null;
    }

    public static void setImageTintList(@NonNull ImageView imageView0, @Nullable ColorStateList colorStateList0) {
        if(Build.VERSION.SDK_INT >= 21) {
            imageView0.setImageTintList(colorStateList0);
            if(Build.VERSION.SDK_INT == 21) {
                Drawable drawable0 = imageView0.getDrawable();
                if(drawable0 != null && (imageView0.getImageTintList() != null && imageView0.getImageTintMode() != null)) {
                    if(drawable0.isStateful()) {
                        drawable0.setState(imageView0.getDrawableState());
                    }
                    imageView0.setImageDrawable(drawable0);
                }
            }
        }
        else if(imageView0 instanceof TintableImageSourceView) {
            ((TintableImageSourceView)imageView0).setSupportImageTintList(colorStateList0);
        }
    }

    public static void setImageTintMode(@NonNull ImageView imageView0, @Nullable PorterDuff.Mode porterDuff$Mode0) {
        if(Build.VERSION.SDK_INT >= 21) {
            imageView0.setImageTintMode(porterDuff$Mode0);
            if(Build.VERSION.SDK_INT == 21) {
                Drawable drawable0 = imageView0.getDrawable();
                if(drawable0 != null && (imageView0.getImageTintList() != null && imageView0.getImageTintMode() != null)) {
                    if(drawable0.isStateful()) {
                        drawable0.setState(imageView0.getDrawableState());
                    }
                    imageView0.setImageDrawable(drawable0);
                }
            }
        }
        else if(imageView0 instanceof TintableImageSourceView) {
            ((TintableImageSourceView)imageView0).setSupportImageTintMode(porterDuff$Mode0);
        }
    }
}

