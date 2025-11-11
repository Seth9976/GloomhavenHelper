package androidx.viewpager.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class PagerTabStrip extends PagerTitleStrip {
    private static final int FULL_UNDERLINE_HEIGHT = 1;
    private static final int INDICATOR_HEIGHT = 3;
    private static final int MIN_PADDING_BOTTOM = 6;
    private static final int MIN_STRIP_HEIGHT = 0x20;
    private static final int MIN_TEXT_SPACING = 0x40;
    private static final int TAB_PADDING = 16;
    private static final int TAB_SPACING = 0x20;
    private static final String TAG = "PagerTabStrip";
    private boolean mDrawFullUnderline;
    private boolean mDrawFullUnderlineSet;
    private int mFullUnderlineHeight;
    private boolean mIgnoreTap;
    private int mIndicatorColor;
    private int mIndicatorHeight;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private int mMinPaddingBottom;
    private int mMinStripHeight;
    private int mMinTextSpacing;
    private int mTabAlpha;
    private int mTabPadding;
    private final Paint mTabPaint;
    private final Rect mTempRect;
    private int mTouchSlop;

    public PagerTabStrip(@NonNull Context context0) {
        this(context0, null);
    }

    public PagerTabStrip(@NonNull Context context0, @Nullable AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mTabPaint = new Paint();
        this.mTempRect = new Rect();
        this.mTabAlpha = 0xFF;
        this.mDrawFullUnderline = false;
        this.mDrawFullUnderlineSet = false;
        this.mIndicatorColor = this.mTextColor;
        this.mTabPaint.setColor(this.mIndicatorColor);
        DisplayMetrics displayMetrics0 = context0.getResources().getDisplayMetrics();
        this.mIndicatorHeight = (int)(3.0f * displayMetrics0.density + 0.5f);
        this.mMinPaddingBottom = (int)(6.0f * displayMetrics0.density + 0.5f);
        this.mMinTextSpacing = (int)(64.0f * displayMetrics0.density);
        this.mTabPadding = (int)(16.0f * displayMetrics0.density + 0.5f);
        this.mFullUnderlineHeight = (int)(1.0f * displayMetrics0.density + 0.5f);
        this.mMinStripHeight = (int)(displayMetrics0.density * 32.0f + 0.5f);
        this.mTouchSlop = ViewConfiguration.get(context0).getScaledTouchSlop();
        this.setPadding(this.getPaddingLeft(), this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
        this.setTextSpacing(this.getTextSpacing());
        this.setWillNotDraw(false);
        this.mPrevText.setFocusable(true);
        this.mPrevText.setOnClickListener(new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                PagerTabStrip.this.mPager.setCurrentItem(PagerTabStrip.this.mPager.getCurrentItem() - 1);
            }
        });
        this.mNextText.setFocusable(true);
        this.mNextText.setOnClickListener(new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                PagerTabStrip.this.mPager.setCurrentItem(PagerTabStrip.this.mPager.getCurrentItem() + 1);
            }
        });
        if(this.getBackground() == null) {
            this.mDrawFullUnderline = true;
        }
    }

    public boolean getDrawFullUnderline() {
        return this.mDrawFullUnderline;
    }

    @Override  // androidx.viewpager.widget.PagerTitleStrip
    int getMinHeight() {
        return Math.max(super.getMinHeight(), this.mMinStripHeight);
    }

    @ColorInt
    public int getTabIndicatorColor() {
        return this.mIndicatorColor;
    }

    @Override  // android.view.View
    protected void onDraw(Canvas canvas0) {
        super.onDraw(canvas0);
        int v = this.getHeight();
        int v1 = this.mCurrText.getLeft() - this.mTabPadding;
        int v2 = this.mCurrText.getRight() + this.mTabPadding;
        int v3 = v - this.mIndicatorHeight;
        this.mTabPaint.setColor(this.mTabAlpha << 24 | this.mIndicatorColor & 0xFFFFFF);
        canvas0.drawRect(((float)v1), ((float)v3), ((float)v2), ((float)v), this.mTabPaint);
        if(this.mDrawFullUnderline) {
            this.mTabPaint.setColor(0xFF000000 | this.mIndicatorColor);
            canvas0.drawRect(((float)this.getPaddingLeft()), ((float)(v - this.mFullUnderlineHeight)), ((float)(this.getWidth() - this.getPaddingRight())), ((float)v), this.mTabPaint);
        }
    }

    @Override  // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        int v = motionEvent0.getAction();
        if(v != 0 && this.mIgnoreTap) {
            return false;
        }
        float f = motionEvent0.getX();
        float f1 = motionEvent0.getY();
        switch(v) {
            case 0: {
                this.mInitialMotionX = f;
                this.mInitialMotionY = f1;
                this.mIgnoreTap = false;
                return true;
            }
            case 1: {
                if(f < ((float)(this.mCurrText.getLeft() - this.mTabPadding))) {
                    this.mPager.setCurrentItem(this.mPager.getCurrentItem() - 1);
                    return true;
                }
                if(f > ((float)(this.mCurrText.getRight() + this.mTabPadding))) {
                    this.mPager.setCurrentItem(this.mPager.getCurrentItem() + 1);
                    return true;
                }
                break;
            }
            case 2: {
                if(Math.abs(f - this.mInitialMotionX) > ((float)this.mTouchSlop) || Math.abs(f1 - this.mInitialMotionY) > ((float)this.mTouchSlop)) {
                    this.mIgnoreTap = true;
                    return true;
                }
                break;
            }
            default: {
                return true;
            }
        }
        return true;
    }

    @Override  // android.view.View
    public void setBackgroundColor(@ColorInt int v) {
        super.setBackgroundColor(v);
        if(!this.mDrawFullUnderlineSet) {
            this.mDrawFullUnderline = (v & 0xFF000000) == 0;
        }
    }

    @Override  // android.view.View
    public void setBackgroundDrawable(Drawable drawable0) {
        super.setBackgroundDrawable(drawable0);
        if(!this.mDrawFullUnderlineSet) {
            this.mDrawFullUnderline = drawable0 == null;
        }
    }

    @Override  // android.view.View
    public void setBackgroundResource(@DrawableRes int v) {
        super.setBackgroundResource(v);
        if(!this.mDrawFullUnderlineSet) {
            this.mDrawFullUnderline = v == 0;
        }
    }

    public void setDrawFullUnderline(boolean z) {
        this.mDrawFullUnderline = z;
        this.mDrawFullUnderlineSet = true;
        this.invalidate();
    }

    @Override  // android.view.View
    public void setPadding(int v, int v1, int v2, int v3) {
        int v4 = this.mMinPaddingBottom;
        if(v3 < v4) {
            v3 = v4;
        }
        super.setPadding(v, v1, v2, v3);
    }

    public void setTabIndicatorColor(@ColorInt int v) {
        this.mIndicatorColor = v;
        this.mTabPaint.setColor(this.mIndicatorColor);
        this.invalidate();
    }

    public void setTabIndicatorColorResource(@ColorRes int v) {
        this.setTabIndicatorColor(ContextCompat.getColor(this.getContext(), v));
    }

    @Override  // androidx.viewpager.widget.PagerTitleStrip
    public void setTextSpacing(int v) {
        int v1 = this.mMinTextSpacing;
        if(v < v1) {
            v = v1;
        }
        super.setTextSpacing(v);
    }

    @Override  // androidx.viewpager.widget.PagerTitleStrip
    void updateTextPositions(int v, float f, boolean z) {
        int v1 = this.getHeight();
        int v2 = this.mCurrText.getLeft() - this.mTabPadding;
        int v3 = this.mCurrText.getRight();
        int v4 = v1 - this.mIndicatorHeight;
        this.mTempRect.set(v2, v4, v3 + this.mTabPadding, v1);
        super.updateTextPositions(v, f, z);
        this.mTabAlpha = (int)(Math.abs(f - 0.5f) * 510.0f);
        int v5 = this.mCurrText.getLeft() - this.mTabPadding;
        int v6 = this.mCurrText.getRight();
        this.mTempRect.union(v5, v4, v6 + this.mTabPadding, v1);
        this.invalidate(this.mTempRect);
    }
}

