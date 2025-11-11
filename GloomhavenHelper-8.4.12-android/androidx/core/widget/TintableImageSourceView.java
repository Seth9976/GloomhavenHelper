package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface TintableImageSourceView {
    @Nullable
    ColorStateList getSupportImageTintList();

    @Nullable
    PorterDuff.Mode getSupportImageTintMode();

    void setSupportImageTintList(@Nullable ColorStateList arg1);

    void setSupportImageTintMode(@Nullable PorterDuff.Mode arg1);
}

