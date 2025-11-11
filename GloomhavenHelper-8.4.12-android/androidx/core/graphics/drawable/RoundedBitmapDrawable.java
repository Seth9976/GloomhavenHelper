package androidx.core.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class RoundedBitmapDrawable extends Drawable {
    private static final int DEFAULT_PAINT_FLAGS = 3;
    private boolean mApplyGravity;
    final Bitmap mBitmap;
    private int mBitmapHeight;
    private final BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private float mCornerRadius;
    final Rect mDstRect;
    private final RectF mDstRectF;
    private int mGravity;
    private boolean mIsCircular;
    private final Paint mPaint;
    private final Matrix mShaderMatrix;
    private int mTargetDensity;

    RoundedBitmapDrawable(Resources resources0, Bitmap bitmap0) {
        this.mTargetDensity = 0xA0;
        this.mGravity = 0x77;
        this.mPaint = new Paint(3);
        this.mShaderMatrix = new Matrix();
        this.mDstRect = new Rect();
        this.mDstRectF = new RectF();
        this.mApplyGravity = true;
        if(resources0 != null) {
            this.mTargetDensity = resources0.getDisplayMetrics().densityDpi;
        }
        this.mBitmap = bitmap0;
        if(this.mBitmap != null) {
            this.computeBitmapSize();
            this.mBitmapShader = new BitmapShader(this.mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            return;
        }
        this.mBitmapHeight = -1;
        this.mBitmapWidth = -1;
        this.mBitmapShader = null;
    }

    private void computeBitmapSize() {
        this.mBitmapWidth = this.mBitmap.getScaledWidth(this.mTargetDensity);
        this.mBitmapHeight = this.mBitmap.getScaledHeight(this.mTargetDensity);
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas0) {
        Bitmap bitmap0 = this.mBitmap;
        if(bitmap0 == null) {
            return;
        }
        this.updateDstRect();
        if(this.mPaint.getShader() == null) {
            canvas0.drawBitmap(bitmap0, null, this.mDstRect, this.mPaint);
            return;
        }
        canvas0.drawRoundRect(this.mDstRectF, this.mCornerRadius, this.mCornerRadius, this.mPaint);
    }

    @Override  // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mPaint.getAlpha();
    }

    @Nullable
    public final Bitmap getBitmap() {
        return this.mBitmap;
    }

    @Override  // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.mPaint.getColorFilter();
    }

    public float getCornerRadius() {
        return this.mCornerRadius;
    }

    public int getGravity() {
        return this.mGravity;
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mBitmapHeight;
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mBitmapWidth;
    }

    // 去混淆评级： 低(30)
    @Override  // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.mGravity != 0x77 || this.mIsCircular || (this.mBitmap == null || this.mBitmap.hasAlpha() || this.mPaint.getAlpha() < 0xFF || RoundedBitmapDrawable.isGreaterThanZero(this.mCornerRadius)) ? -3 : -1;
    }

    @NonNull
    public final Paint getPaint() {
        return this.mPaint;
    }

    void gravityCompatApply(int v, int v1, int v2, Rect rect0, Rect rect1) {
        throw new UnsupportedOperationException();
    }

    public boolean hasAntiAlias() {
        return this.mPaint.isAntiAlias();
    }

    public boolean hasMipMap() {
        throw new UnsupportedOperationException();
    }

    public boolean isCircular() {
        return this.mIsCircular;
    }

    private static boolean isGreaterThanZero(float f) {
        return f > 0.05f;
    }

    @Override  // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect0) {
        super.onBoundsChange(rect0);
        if(this.mIsCircular) {
            this.updateCircularCornerRadius();
        }
        this.mApplyGravity = true;
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAlpha(int v) {
        if(v != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(v);
            this.invalidateSelf();
        }
    }

    public void setAntiAlias(boolean z) {
        this.mPaint.setAntiAlias(z);
        this.invalidateSelf();
    }

    public void setCircular(boolean z) {
        this.mIsCircular = z;
        this.mApplyGravity = true;
        if(z) {
            this.updateCircularCornerRadius();
            this.mPaint.setShader(this.mBitmapShader);
            this.invalidateSelf();
            return;
        }
        this.setCornerRadius(0.0f);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter0) {
        this.mPaint.setColorFilter(colorFilter0);
        this.invalidateSelf();
    }

    public void setCornerRadius(float f) {
        if(this.mCornerRadius == f) {
            return;
        }
        this.mIsCircular = false;
        if(RoundedBitmapDrawable.isGreaterThanZero(f)) {
            this.mPaint.setShader(this.mBitmapShader);
        }
        else {
            this.mPaint.setShader(null);
        }
        this.mCornerRadius = f;
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        this.mPaint.setDither(z);
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.mPaint.setFilterBitmap(z);
        this.invalidateSelf();
    }

    public void setGravity(int v) {
        if(this.mGravity != v) {
            this.mGravity = v;
            this.mApplyGravity = true;
            this.invalidateSelf();
        }
    }

    public void setMipMap(boolean z) {
        throw new UnsupportedOperationException();
    }

    public void setTargetDensity(int v) {
        if(this.mTargetDensity != v) {
            if(v == 0) {
                v = 0xA0;
            }
            this.mTargetDensity = v;
            if(this.mBitmap != null) {
                this.computeBitmapSize();
            }
            this.invalidateSelf();
        }
    }

    public void setTargetDensity(@NonNull Canvas canvas0) {
        this.setTargetDensity(canvas0.getDensity());
    }

    public void setTargetDensity(@NonNull DisplayMetrics displayMetrics0) {
        this.setTargetDensity(displayMetrics0.densityDpi);
    }

    private void updateCircularCornerRadius() {
        this.mCornerRadius = (float)(Math.min(this.mBitmapHeight, this.mBitmapWidth) / 2);
    }

    void updateDstRect() {
        if(this.mApplyGravity) {
            if(this.mIsCircular) {
                int v = Math.min(this.mBitmapWidth, this.mBitmapHeight);
                this.gravityCompatApply(this.mGravity, v, v, this.getBounds(), this.mDstRect);
                int v1 = Math.min(this.mDstRect.width(), this.mDstRect.height());
                int v2 = Math.max(0, (this.mDstRect.width() - v1) / 2);
                int v3 = Math.max(0, (this.mDstRect.height() - v1) / 2);
                this.mDstRect.inset(v2, v3);
                this.mCornerRadius = ((float)v1) * 0.5f;
            }
            else {
                this.gravityCompatApply(this.mGravity, this.mBitmapWidth, this.mBitmapHeight, this.getBounds(), this.mDstRect);
            }
            this.mDstRectF.set(this.mDstRect);
            if(this.mBitmapShader != null) {
                this.mShaderMatrix.setTranslate(this.mDstRectF.left, this.mDstRectF.top);
                float f = this.mDstRectF.width() / ((float)this.mBitmap.getWidth());
                float f1 = this.mDstRectF.height() / ((float)this.mBitmap.getHeight());
                this.mShaderMatrix.preScale(f, f1);
                this.mBitmapShader.setLocalMatrix(this.mShaderMatrix);
                this.mPaint.setShader(this.mBitmapShader);
            }
            this.mApplyGravity = false;
        }
    }
}

