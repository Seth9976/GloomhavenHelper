package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import androidx.annotation.ColorInt;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface TintAwareDrawable {
    void setTint(@ColorInt int arg1);

    void setTintList(ColorStateList arg1);

    void setTintMode(PorterDuff.Mode arg1);
}

