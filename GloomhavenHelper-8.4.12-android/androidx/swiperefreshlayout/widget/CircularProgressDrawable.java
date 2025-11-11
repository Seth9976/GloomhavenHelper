package androidx.swiperefreshlayout.widget;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Path.FillType;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CircularProgressDrawable extends Drawable implements Animatable {
    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ProgressDrawableSize {
    }

    static class Ring {
        int mAlpha;
        Path mArrow;
        int mArrowHeight;
        final Paint mArrowPaint;
        float mArrowScale;
        int mArrowWidth;
        final Paint mCirclePaint;
        int mColorIndex;
        int[] mColors;
        int mCurrentColor;
        float mEndTrim;
        final Paint mPaint;
        float mRingCenterRadius;
        float mRotation;
        boolean mShowArrow;
        float mStartTrim;
        float mStartingEndTrim;
        float mStartingRotation;
        float mStartingStartTrim;
        float mStrokeWidth;
        final RectF mTempBounds;

        Ring() {
            this.mTempBounds = new RectF();
            this.mPaint = new Paint();
            this.mArrowPaint = new Paint();
            this.mCirclePaint = new Paint();
            this.mStartTrim = 0.0f;
            this.mEndTrim = 0.0f;
            this.mRotation = 0.0f;
            this.mStrokeWidth = 5.0f;
            this.mArrowScale = 1.0f;
            this.mAlpha = 0xFF;
            this.mPaint.setStrokeCap(Paint.Cap.SQUARE);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mArrowPaint.setStyle(Paint.Style.FILL);
            this.mArrowPaint.setAntiAlias(true);
            this.mCirclePaint.setColor(0);
        }

        void draw(Canvas canvas0, Rect rect0) {
            float f = this.mRingCenterRadius <= 0.0f ? ((float)Math.min(rect0.width(), rect0.height())) / 2.0f - Math.max(((float)this.mArrowWidth) * this.mArrowScale / 2.0f, this.mStrokeWidth / 2.0f) : this.mStrokeWidth / 2.0f + this.mRingCenterRadius;
            this.mTempBounds.set(((float)rect0.centerX()) - f, ((float)rect0.centerY()) - f, ((float)rect0.centerX()) + f, ((float)rect0.centerY()) + f);
            float f1 = (this.mStartTrim + this.mRotation) * 360.0f;
            float f2 = (this.mEndTrim + this.mRotation) * 360.0f - f1;
            this.mPaint.setColor(this.mCurrentColor);
            this.mPaint.setAlpha(this.mAlpha);
            float f3 = this.mStrokeWidth / 2.0f;
            this.mTempBounds.inset(f3, f3);
            canvas0.drawCircle(this.mTempBounds.centerX(), this.mTempBounds.centerY(), this.mTempBounds.width() / 2.0f, this.mCirclePaint);
            this.mTempBounds.inset(-f3, -f3);
            canvas0.drawArc(this.mTempBounds, f1, f2, false, this.mPaint);
            this.drawTriangle(canvas0, f1, f2, this.mTempBounds);
        }

        void drawTriangle(Canvas canvas0, float f, float f1, RectF rectF0) {
            if(this.mShowArrow) {
                Path path0 = this.mArrow;
                if(path0 == null) {
                    this.mArrow = new Path();
                    this.mArrow.setFillType(Path.FillType.EVEN_ODD);
                }
                else {
                    path0.reset();
                }
                float f2 = Math.min(rectF0.width(), rectF0.height());
                float f3 = ((float)this.mArrowWidth) * this.mArrowScale / 2.0f;
                this.mArrow.moveTo(0.0f, 0.0f);
                this.mArrow.lineTo(((float)this.mArrowWidth) * this.mArrowScale, 0.0f);
                this.mArrow.lineTo(((float)this.mArrowWidth) * this.mArrowScale / 2.0f, ((float)this.mArrowHeight) * this.mArrowScale);
                this.mArrow.offset(f2 / 2.0f + rectF0.centerX() - f3, rectF0.centerY() + this.mStrokeWidth / 2.0f);
                this.mArrow.close();
                this.mArrowPaint.setColor(this.mCurrentColor);
                this.mArrowPaint.setAlpha(this.mAlpha);
                canvas0.save();
                canvas0.rotate(f + f1, rectF0.centerX(), rectF0.centerY());
                canvas0.drawPath(this.mArrow, this.mArrowPaint);
                canvas0.restore();
            }
        }

        int getAlpha() {
            return this.mAlpha;
        }

        float getArrowHeight() {
            return (float)this.mArrowHeight;
        }

        float getArrowScale() {
            return this.mArrowScale;
        }

        float getArrowWidth() {
            return (float)this.mArrowWidth;
        }

        int getBackgroundColor() {
            return this.mCirclePaint.getColor();
        }

        float getCenterRadius() {
            return this.mRingCenterRadius;
        }

        int[] getColors() {
            return this.mColors;
        }

        float getEndTrim() {
            return this.mEndTrim;
        }

        int getNextColor() {
            int[] arr_v = this.mColors;
            return arr_v[this.getNextColorIndex()];
        }

        int getNextColorIndex() {
            return (this.mColorIndex + 1) % this.mColors.length;
        }

        float getRotation() {
            return this.mRotation;
        }

        boolean getShowArrow() {
            return this.mShowArrow;
        }

        float getStartTrim() {
            return this.mStartTrim;
        }

        int getStartingColor() {
            return this.mColors[this.mColorIndex];
        }

        float getStartingEndTrim() {
            return this.mStartingEndTrim;
        }

        float getStartingRotation() {
            return this.mStartingRotation;
        }

        float getStartingStartTrim() {
            return this.mStartingStartTrim;
        }

        Paint.Cap getStrokeCap() {
            return this.mPaint.getStrokeCap();
        }

        float getStrokeWidth() {
            return this.mStrokeWidth;
        }

        void goToNextColor() {
            this.setColorIndex(this.getNextColorIndex());
        }

        void resetOriginals() {
            this.mStartingStartTrim = 0.0f;
            this.mStartingEndTrim = 0.0f;
            this.mStartingRotation = 0.0f;
            this.setStartTrim(0.0f);
            this.setEndTrim(0.0f);
            this.setRotation(0.0f);
        }

        void setAlpha(int v) {
            this.mAlpha = v;
        }

        void setArrowDimensions(float f, float f1) {
            this.mArrowWidth = (int)f;
            this.mArrowHeight = (int)f1;
        }

        void setArrowScale(float f) {
            if(f != this.mArrowScale) {
                this.mArrowScale = f;
            }
        }

        void setBackgroundColor(int v) {
            this.mCirclePaint.setColor(v);
        }

        void setCenterRadius(float f) {
            this.mRingCenterRadius = f;
        }

        void setColor(int v) {
            this.mCurrentColor = v;
        }

        void setColorFilter(ColorFilter colorFilter0) {
            this.mPaint.setColorFilter(colorFilter0);
        }

        void setColorIndex(int v) {
            this.mColorIndex = v;
            this.mCurrentColor = this.mColors[this.mColorIndex];
        }

        void setColors(@NonNull int[] arr_v) {
            this.mColors = arr_v;
            this.setColorIndex(0);
        }

        void setEndTrim(float f) {
            this.mEndTrim = f;
        }

        void setRotation(float f) {
            this.mRotation = f;
        }

        void setShowArrow(boolean z) {
            if(this.mShowArrow != z) {
                this.mShowArrow = z;
            }
        }

        void setStartTrim(float f) {
            this.mStartTrim = f;
        }

        void setStrokeCap(Paint.Cap paint$Cap0) {
            this.mPaint.setStrokeCap(paint$Cap0);
        }

        void setStrokeWidth(float f) {
            this.mStrokeWidth = f;
            this.mPaint.setStrokeWidth(f);
        }

        void storeOriginals() {
            this.mStartingStartTrim = this.mStartTrim;
            this.mStartingEndTrim = this.mEndTrim;
            this.mStartingRotation = this.mRotation;
        }
    }

    private static final int ANIMATION_DURATION = 0x534;
    private static final int ARROW_HEIGHT = 5;
    private static final int ARROW_HEIGHT_LARGE = 6;
    private static final int ARROW_WIDTH = 10;
    private static final int ARROW_WIDTH_LARGE = 12;
    private static final float CENTER_RADIUS = 7.5f;
    private static final float CENTER_RADIUS_LARGE = 11.0f;
    private static final int[] COLORS = null;
    private static final float COLOR_CHANGE_OFFSET = 0.75f;
    public static final int DEFAULT = 1;
    private static final float GROUP_FULL_ROTATION = 216.0f;
    public static final int LARGE = 0;
    private static final Interpolator LINEAR_INTERPOLATOR = null;
    private static final Interpolator MATERIAL_INTERPOLATOR = null;
    private static final float MAX_PROGRESS_ARC = 0.8f;
    private static final float MIN_PROGRESS_ARC = 0.01f;
    private static final float RING_ROTATION = 0.21f;
    private static final float SHRINK_OFFSET = 0.5f;
    private static final float STROKE_WIDTH = 2.5f;
    private static final float STROKE_WIDTH_LARGE = 3.0f;
    private Animator mAnimator;
    boolean mFinishing;
    private Resources mResources;
    private final Ring mRing;
    private float mRotation;
    float mRotationCount;

    static {
        CircularProgressDrawable.LINEAR_INTERPOLATOR = new LinearInterpolator();
        CircularProgressDrawable.MATERIAL_INTERPOLATOR = new FastOutSlowInInterpolator();
        CircularProgressDrawable.COLORS = new int[]{0xFF000000};
    }

    public CircularProgressDrawable(@NonNull Context context0) {
        this.mResources = ((Context)Preconditions.checkNotNull(context0)).getResources();
        this.mRing = new Ring();
        this.mRing.setColors(CircularProgressDrawable.COLORS);
        this.setStrokeWidth(2.5f);
        this.setupAnimators();
    }

    private void applyFinishTranslation(float f, Ring circularProgressDrawable$Ring0) {
        this.updateRingColor(f, circularProgressDrawable$Ring0);
        circularProgressDrawable$Ring0.setStartTrim(circularProgressDrawable$Ring0.getStartingStartTrim() + (circularProgressDrawable$Ring0.getStartingEndTrim() - 0.01f - circularProgressDrawable$Ring0.getStartingStartTrim()) * f);
        circularProgressDrawable$Ring0.setEndTrim(circularProgressDrawable$Ring0.getStartingEndTrim());
        circularProgressDrawable$Ring0.setRotation(circularProgressDrawable$Ring0.getStartingRotation() + (((float)(Math.floor(circularProgressDrawable$Ring0.getStartingRotation() / 0.8f) + 1.0)) - circularProgressDrawable$Ring0.getStartingRotation()) * f);
    }

    void applyTransformation(float f, Ring circularProgressDrawable$Ring0, boolean z) {
        float f4;
        float f3;
        if(this.mFinishing) {
            this.applyFinishTranslation(f, circularProgressDrawable$Ring0);
            return;
        }
        if(f != 1.0f || z) {
            float f1 = circularProgressDrawable$Ring0.getStartingRotation();
            if(f < 0.5f) {
                float f2 = circularProgressDrawable$Ring0.getStartingStartTrim();
                f3 = CircularProgressDrawable.MATERIAL_INTERPOLATOR.getInterpolation(f / 0.5f) * 0.79f + 0.01f + f2;
                f4 = f2;
            }
            else {
                f3 = circularProgressDrawable$Ring0.getStartingStartTrim() + 0.79f;
                f4 = f3 - ((1.0f - CircularProgressDrawable.MATERIAL_INTERPOLATOR.getInterpolation((f - 0.5f) / 0.5f)) * 0.79f + 0.01f);
            }
            float f5 = (f + this.mRotationCount) * 216.0f;
            circularProgressDrawable$Ring0.setStartTrim(f4);
            circularProgressDrawable$Ring0.setEndTrim(f3);
            circularProgressDrawable$Ring0.setRotation(f1 + 0.21f * f);
            this.setRotation(f5);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(Canvas canvas0) {
        Rect rect0 = this.getBounds();
        canvas0.save();
        canvas0.rotate(this.mRotation, rect0.exactCenterX(), rect0.exactCenterY());
        this.mRing.draw(canvas0, rect0);
        canvas0.restore();
    }

    private int evaluateColorChange(float f, int v, int v1) {
        int v2 = v >> 24 & 0xFF;
        int v3 = v >> 16 & 0xFF;
        int v4 = v >> 8 & 0xFF;
        return v2 + ((int)(((float)((v1 >> 24 & 0xFF) - v2)) * f)) << 24 | v3 + ((int)(((float)((v1 >> 16 & 0xFF) - v3)) * f)) << 16 | v4 + ((int)(((float)((v1 >> 8 & 0xFF) - v4)) * f)) << 8 | (v & 0xFF) + ((int)(f * ((float)((v1 & 0xFF) - (v & 0xFF)))));
    }

    @Override  // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mRing.getAlpha();
    }

    public boolean getArrowEnabled() {
        return this.mRing.getShowArrow();
    }

    public float getArrowHeight() {
        return this.mRing.getArrowHeight();
    }

    public float getArrowScale() {
        return this.mRing.getArrowScale();
    }

    public float getArrowWidth() {
        return this.mRing.getArrowWidth();
    }

    public int getBackgroundColor() {
        return this.mRing.getBackgroundColor();
    }

    public float getCenterRadius() {
        return this.mRing.getCenterRadius();
    }

    @NonNull
    public int[] getColorSchemeColors() {
        return this.mRing.getColors();
    }

    public float getEndTrim() {
        return this.mRing.getEndTrim();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public float getProgressRotation() {
        return this.mRing.getRotation();
    }

    private float getRotation() {
        return this.mRotation;
    }

    public float getStartTrim() {
        return this.mRing.getStartTrim();
    }

    @NonNull
    public Paint.Cap getStrokeCap() {
        return this.mRing.getStrokeCap();
    }

    public float getStrokeWidth() {
        return this.mRing.getStrokeWidth();
    }

    @Override  // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.mAnimator.isRunning();
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAlpha(int v) {
        this.mRing.setAlpha(v);
        this.invalidateSelf();
    }

    public void setArrowDimensions(float f, float f1) {
        this.mRing.setArrowDimensions(f, f1);
        this.invalidateSelf();
    }

    public void setArrowEnabled(boolean z) {
        this.mRing.setShowArrow(z);
        this.invalidateSelf();
    }

    public void setArrowScale(float f) {
        this.mRing.setArrowScale(f);
        this.invalidateSelf();
    }

    public void setBackgroundColor(int v) {
        this.mRing.setBackgroundColor(v);
        this.invalidateSelf();
    }

    public void setCenterRadius(float f) {
        this.mRing.setCenterRadius(f);
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter0) {
        this.mRing.setColorFilter(colorFilter0);
        this.invalidateSelf();
    }

    public void setColorSchemeColors(@NonNull int[] arr_v) {
        this.mRing.setColors(arr_v);
        this.mRing.setColorIndex(0);
        this.invalidateSelf();
    }

    public void setProgressRotation(float f) {
        this.mRing.setRotation(f);
        this.invalidateSelf();
    }

    private void setRotation(float f) {
        this.mRotation = f;
    }

    private void setSizeParameters(float f, float f1, float f2, float f3) {
        float f4 = this.mResources.getDisplayMetrics().density;
        this.mRing.setStrokeWidth(f1 * f4);
        this.mRing.setCenterRadius(f * f4);
        this.mRing.setColorIndex(0);
        this.mRing.setArrowDimensions(f2 * f4, f3 * f4);
    }

    public void setStartEndTrim(float f, float f1) {
        this.mRing.setStartTrim(f);
        this.mRing.setEndTrim(f1);
        this.invalidateSelf();
    }

    public void setStrokeCap(@NonNull Paint.Cap paint$Cap0) {
        this.mRing.setStrokeCap(paint$Cap0);
        this.invalidateSelf();
    }

    public void setStrokeWidth(float f) {
        this.mRing.setStrokeWidth(f);
        this.invalidateSelf();
    }

    public void setStyle(int v) {
        if(v == 0) {
            this.setSizeParameters(11.0f, 3.0f, 12.0f, 6.0f);
        }
        else {
            this.setSizeParameters(7.5f, 2.5f, 10.0f, 5.0f);
        }
        this.invalidateSelf();
    }

    private void setupAnimators() {
        ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        valueAnimator0.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                float f = (float)(((Float)valueAnimator0.getAnimatedValue()));
                CircularProgressDrawable.this.updateRingColor(f, this.mRing);
                CircularProgressDrawable.this.applyTransformation(f, this.mRing, false);
                CircularProgressDrawable.this.invalidateSelf();
            }
        });
        valueAnimator0.setRepeatCount(-1);
        valueAnimator0.setRepeatMode(1);
        valueAnimator0.setInterpolator(CircularProgressDrawable.LINEAR_INTERPOLATOR);
        valueAnimator0.addListener(new Animator.AnimatorListener() {
            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationCancel(Animator animator0) {
            }

            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationEnd(Animator animator0) {
            }

            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationRepeat(Animator animator0) {
                CircularProgressDrawable.this.applyTransformation(1.0f, this.mRing, true);
                this.mRing.storeOriginals();
                this.mRing.goToNextColor();
                if(CircularProgressDrawable.this.mFinishing) {
                    CircularProgressDrawable.this.mFinishing = false;
                    animator0.cancel();
                    animator0.setDuration(0x534L);
                    animator0.start();
                    this.mRing.setShowArrow(false);
                    return;
                }
                ++CircularProgressDrawable.this.mRotationCount;
            }

            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationStart(Animator animator0) {
                CircularProgressDrawable.this.mRotationCount = 0.0f;
            }
        });
        this.mAnimator = valueAnimator0;
    }

    @Override  // android.graphics.drawable.Animatable
    public void start() {
        this.mAnimator.cancel();
        this.mRing.storeOriginals();
        if(this.mRing.getEndTrim() != this.mRing.getStartTrim()) {
            this.mFinishing = true;
            this.mAnimator.setDuration(666L);
            this.mAnimator.start();
            return;
        }
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
        this.mAnimator.setDuration(0x534L);
        this.mAnimator.start();
    }

    @Override  // android.graphics.drawable.Animatable
    public void stop() {
        this.mAnimator.cancel();
        this.setRotation(0.0f);
        this.mRing.setShowArrow(false);
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
        this.invalidateSelf();
    }

    void updateRingColor(float f, Ring circularProgressDrawable$Ring0) {
        if(f > 0.75f) {
            circularProgressDrawable$Ring0.setColor(this.evaluateColorChange((f - 0.75f) / 0.25f, circularProgressDrawable$Ring0.getStartingColor(), circularProgressDrawable$Ring0.getNextColor()));
            return;
        }
        circularProgressDrawable$Ring0.setColor(circularProgressDrawable$Ring0.getStartingColor());
    }
}

