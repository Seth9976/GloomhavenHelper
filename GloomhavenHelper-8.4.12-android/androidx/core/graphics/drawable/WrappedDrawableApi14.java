package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

class WrappedDrawableApi14 extends Drawable implements Drawable.Callback, TintAwareDrawable, WrappedDrawable {
    public static abstract class DrawableWrapperState extends Drawable.ConstantState {
        int mChangingConfigurations;
        Drawable.ConstantState mDrawableState;
        ColorStateList mTint;
        PorterDuff.Mode mTintMode;

        DrawableWrapperState(@Nullable DrawableWrapperState wrappedDrawableApi14$DrawableWrapperState0, @Nullable Resources resources0) {
            this.mTint = null;
            this.mTintMode = WrappedDrawableApi14.DEFAULT_TINT_MODE;
            if(wrappedDrawableApi14$DrawableWrapperState0 != null) {
                this.mChangingConfigurations = wrappedDrawableApi14$DrawableWrapperState0.mChangingConfigurations;
                this.mDrawableState = wrappedDrawableApi14$DrawableWrapperState0.mDrawableState;
                this.mTint = wrappedDrawableApi14$DrawableWrapperState0.mTint;
                this.mTintMode = wrappedDrawableApi14$DrawableWrapperState0.mTintMode;
            }
        }

        boolean canConstantState() {
            return this.mDrawableState != null;
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public int getChangingConfigurations() {
            return this.mDrawableState == null ? this.mChangingConfigurations : this.mChangingConfigurations | this.mDrawableState.getChangingConfigurations();
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        @NonNull
        public Drawable newDrawable() {
            return this.newDrawable(null);
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        @NonNull
        public abstract Drawable newDrawable(@Nullable Resources arg1);
    }

    static class DrawableWrapperStateBase extends DrawableWrapperState {
        DrawableWrapperStateBase(@Nullable DrawableWrapperState wrappedDrawableApi14$DrawableWrapperState0, @Nullable Resources resources0) {
            super(wrappedDrawableApi14$DrawableWrapperState0, resources0);
        }

        @Override  // androidx.core.graphics.drawable.WrappedDrawableApi14$DrawableWrapperState
        @NonNull
        public Drawable newDrawable(@Nullable Resources resources0) {
            return new WrappedDrawableApi14(this, resources0);
        }
    }

    static final PorterDuff.Mode DEFAULT_TINT_MODE;
    private boolean mColorFilterSet;
    private int mCurrentColor;
    private PorterDuff.Mode mCurrentMode;
    Drawable mDrawable;
    private boolean mMutated;
    DrawableWrapperState mState;

    static {
        WrappedDrawableApi14.DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    }

    WrappedDrawableApi14(@Nullable Drawable drawable0) {
        this.mState = this.mutateConstantState();
        this.setWrappedDrawable(drawable0);
    }

    WrappedDrawableApi14(@NonNull DrawableWrapperState wrappedDrawableApi14$DrawableWrapperState0, @Nullable Resources resources0) {
        this.mState = wrappedDrawableApi14$DrawableWrapperState0;
        this.updateLocalState(resources0);
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas0) {
        this.mDrawable.draw(canvas0);
    }

    @Override  // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        int v = super.getChangingConfigurations();
        DrawableWrapperState wrappedDrawableApi14$DrawableWrapperState0 = this.mState;
        return wrappedDrawableApi14$DrawableWrapperState0 == null ? v | this.mDrawable.getChangingConfigurations() : v | wrappedDrawableApi14$DrawableWrapperState0.getChangingConfigurations() | this.mDrawable.getChangingConfigurations();
    }

    @Override  // android.graphics.drawable.Drawable
    @Nullable
    public Drawable.ConstantState getConstantState() {
        if(this.mState != null && this.mState.canConstantState()) {
            DrawableWrapperState wrappedDrawableApi14$DrawableWrapperState0 = this.mState;
            wrappedDrawableApi14$DrawableWrapperState0.mChangingConfigurations = this.getChangingConfigurations();
            return this.mState;
        }
        return null;
    }

    @Override  // android.graphics.drawable.Drawable
    @NonNull
    public Drawable getCurrent() {
        return this.mDrawable.getCurrent();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mDrawable.getIntrinsicHeight();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mDrawable.getIntrinsicWidth();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.mDrawable.getMinimumHeight();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.mDrawable.getMinimumWidth();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.mDrawable.getOpacity();
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean getPadding(@NonNull Rect rect0) {
        return this.mDrawable.getPadding(rect0);
    }

    @Override  // android.graphics.drawable.Drawable
    @NonNull
    public int[] getState() {
        return this.mDrawable.getState();
    }

    @Override  // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        return this.mDrawable.getTransparentRegion();
    }

    @Override  // androidx.core.graphics.drawable.WrappedDrawable
    public final Drawable getWrappedDrawable() {
        return this.mDrawable;
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public void invalidateDrawable(@NonNull Drawable drawable0) {
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable
    @RequiresApi(19)
    public boolean isAutoMirrored() {
        return this.mDrawable.isAutoMirrored();
    }

    protected boolean isCompatTintEnabled() {
        return true;
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean isStateful() {
        if(this.isCompatTintEnabled()) {
            DrawableWrapperState wrappedDrawableApi14$DrawableWrapperState0 = this.mState;
            if(wrappedDrawableApi14$DrawableWrapperState0 != null) {
                return wrappedDrawableApi14$DrawableWrapperState0.mTint != null && wrappedDrawableApi14$DrawableWrapperState0.mTint.isStateful() || this.mDrawable.isStateful();
            }
        }
        throw new NullPointerException();
    }

    @Override  // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.mDrawable.jumpToCurrentState();
    }

    @Override  // android.graphics.drawable.Drawable
    @NonNull
    public Drawable mutate() {
        if(!this.mMutated && super.mutate() == this) {
            this.mState = this.mutateConstantState();
            Drawable drawable0 = this.mDrawable;
            if(drawable0 != null) {
                drawable0.mutate();
            }
            DrawableWrapperState wrappedDrawableApi14$DrawableWrapperState0 = this.mState;
            if(wrappedDrawableApi14$DrawableWrapperState0 != null) {
                wrappedDrawableApi14$DrawableWrapperState0.mDrawableState = this.mDrawable == null ? null : this.mDrawable.getConstantState();
            }
            this.mMutated = true;
        }
        return this;
    }

    @NonNull
    DrawableWrapperState mutateConstantState() {
        return new DrawableWrapperStateBase(this.mState, null);
    }

    @Override  // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect0) {
        Drawable drawable0 = this.mDrawable;
        if(drawable0 != null) {
            drawable0.setBounds(rect0);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int v) {
        return this.mDrawable.setLevel(v);
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public void scheduleDrawable(@NonNull Drawable drawable0, @NonNull Runnable runnable0, long v) {
        this.scheduleSelf(runnable0, v);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAlpha(int v) {
        this.mDrawable.setAlpha(v);
    }

    @Override  // android.graphics.drawable.Drawable
    @RequiresApi(19)
    public void setAutoMirrored(boolean z) {
        this.mDrawable.setAutoMirrored(z);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setChangingConfigurations(int v) {
        this.mDrawable.setChangingConfigurations(v);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter0) {
        this.mDrawable.setColorFilter(colorFilter0);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        this.mDrawable.setDither(z);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.mDrawable.setFilterBitmap(z);
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean setState(@NonNull int[] arr_v) {
        boolean z = this.mDrawable.setState(arr_v);
        return this.updateTint(arr_v) || z;
    }

    @Override  // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTint(int v) {
        this.setTintList(ColorStateList.valueOf(v));
    }

    @Override  // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(ColorStateList colorStateList0) {
        this.mState.mTint = colorStateList0;
        this.updateTint(this.getState());
    }

    @Override  // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(@NonNull PorterDuff.Mode porterDuff$Mode0) {
        this.mState.mTintMode = porterDuff$Mode0;
        this.updateTint(this.getState());
    }

    // 去混淆评级： 低(20)
    @Override  // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z1) {
        return super.setVisible(z, z1) || this.mDrawable.setVisible(z, z1);
    }

    @Override  // androidx.core.graphics.drawable.WrappedDrawable
    public final void setWrappedDrawable(Drawable drawable0) {
        Drawable drawable1 = this.mDrawable;
        if(drawable1 != null) {
            drawable1.setCallback(null);
        }
        this.mDrawable = drawable0;
        if(drawable0 != null) {
            drawable0.setCallback(this);
            this.setVisible(drawable0.isVisible(), true);
            this.setState(drawable0.getState());
            this.setLevel(drawable0.getLevel());
            this.setBounds(drawable0.getBounds());
            DrawableWrapperState wrappedDrawableApi14$DrawableWrapperState0 = this.mState;
            if(wrappedDrawableApi14$DrawableWrapperState0 != null) {
                wrappedDrawableApi14$DrawableWrapperState0.mDrawableState = drawable0.getConstantState();
            }
        }
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public void unscheduleDrawable(@NonNull Drawable drawable0, @NonNull Runnable runnable0) {
        this.unscheduleSelf(runnable0);
    }

    private void updateLocalState(@Nullable Resources resources0) {
        if(this.mState != null && this.mState.mDrawableState != null) {
            this.setWrappedDrawable(this.mState.mDrawableState.newDrawable(resources0));
        }
    }

    private boolean updateTint(int[] arr_v) {
        if(!this.isCompatTintEnabled()) {
            return false;
        }
        ColorStateList colorStateList0 = this.mState.mTint;
        PorterDuff.Mode porterDuff$Mode0 = this.mState.mTintMode;
        if(colorStateList0 == null || porterDuff$Mode0 == null) {
            this.mColorFilterSet = false;
            this.clearColorFilter();
        }
        else {
            int v = colorStateList0.getColorForState(arr_v, colorStateList0.getDefaultColor());
            if(!this.mColorFilterSet || v != this.mCurrentColor || porterDuff$Mode0 != this.mCurrentMode) {
                this.setColorFilter(v, porterDuff$Mode0);
                this.mCurrentColor = v;
                this.mCurrentMode = porterDuff$Mode0;
                this.mColorFilterSet = true;
                return true;
            }
        }
        return false;
    }
}

