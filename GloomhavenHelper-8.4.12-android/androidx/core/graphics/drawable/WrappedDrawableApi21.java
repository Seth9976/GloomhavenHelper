package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Method;

@RequiresApi(21)
class WrappedDrawableApi21 extends WrappedDrawableApi14 {
    static class DrawableWrapperStateLollipop extends DrawableWrapperState {
        DrawableWrapperStateLollipop(@Nullable DrawableWrapperState wrappedDrawableApi14$DrawableWrapperState0, @Nullable Resources resources0) {
            super(wrappedDrawableApi14$DrawableWrapperState0, resources0);
        }

        @Override  // androidx.core.graphics.drawable.WrappedDrawableApi14$DrawableWrapperState
        @NonNull
        public Drawable newDrawable(@Nullable Resources resources0) {
            return new WrappedDrawableApi21(this, resources0);
        }
    }

    private static final String TAG = "WrappedDrawableApi21";
    private static Method sIsProjectedDrawableMethod;

    WrappedDrawableApi21(Drawable drawable0) {
        super(drawable0);
        this.findAndCacheIsProjectedDrawableMethod();
    }

    WrappedDrawableApi21(DrawableWrapperState wrappedDrawableApi14$DrawableWrapperState0, Resources resources0) {
        super(wrappedDrawableApi14$DrawableWrapperState0, resources0);
        this.findAndCacheIsProjectedDrawableMethod();
    }

    private void findAndCacheIsProjectedDrawableMethod() {
        if(WrappedDrawableApi21.sIsProjectedDrawableMethod == null) {
            try {
                WrappedDrawableApi21.sIsProjectedDrawableMethod = Drawable.class.getDeclaredMethod("isProjected");
            }
            catch(Exception exception0) {
                Log.w("WrappedDrawableApi21", "Failed to retrieve Drawable#isProjected() method", exception0);
            }
        }
    }

    @Override  // android.graphics.drawable.Drawable
    @NonNull
    public Rect getDirtyBounds() {
        return this.mDrawable.getDirtyBounds();
    }

    @Override  // android.graphics.drawable.Drawable
    public void getOutline(@NonNull Outline outline0) {
        this.mDrawable.getOutline(outline0);
    }

    // 去混淆评级： 低(40)
    @Override  // androidx.core.graphics.drawable.WrappedDrawableApi14
    protected boolean isCompatTintEnabled() [...] // 潜在的解密器

    @Override  // android.graphics.drawable.Drawable
    public boolean isProjected() {
        if(this.mDrawable != null) {
            Method method0 = WrappedDrawableApi21.sIsProjectedDrawableMethod;
            if(method0 != null) {
                try {
                    return ((Boolean)method0.invoke(this.mDrawable)).booleanValue();
                }
                catch(Exception exception0) {
                    Log.w("WrappedDrawableApi21", "Error calling Drawable#isProjected() method", exception0);
                }
            }
        }
        return false;
    }

    @Override  // androidx.core.graphics.drawable.WrappedDrawableApi14
    @NonNull
    DrawableWrapperState mutateConstantState() {
        return new DrawableWrapperStateLollipop(this.mState, null);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setHotspot(float f, float f1) {
        this.mDrawable.setHotspot(f, f1);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setHotspotBounds(int v, int v1, int v2, int v3) {
        this.mDrawable.setHotspotBounds(v, v1, v2, v3);
    }

    @Override  // androidx.core.graphics.drawable.WrappedDrawableApi14
    public boolean setState(@NonNull int[] arr_v) {
        if(super.setState(arr_v)) {
            this.invalidateSelf();
            return true;
        }
        return false;
    }

    @Override  // androidx.core.graphics.drawable.WrappedDrawableApi14
    public void setTint(int v) {
        this.mDrawable.setTint(v);
    }

    @Override  // androidx.core.graphics.drawable.WrappedDrawableApi14
    public void setTintList(ColorStateList colorStateList0) {
        this.mDrawable.setTintList(colorStateList0);
    }

    @Override  // androidx.core.graphics.drawable.WrappedDrawableApi14
    public void setTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.mDrawable.setTintMode(porterDuff$Mode0);
    }
}

