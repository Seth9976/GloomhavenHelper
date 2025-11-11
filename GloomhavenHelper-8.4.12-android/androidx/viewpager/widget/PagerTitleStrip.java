package androidx.viewpager.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.text.TextUtils.TruncateAt;
import android.text.method.SingleLineTransformationMethod;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.TextViewCompat;
import java.lang.ref.WeakReference;
import java.util.Locale;

@DecorView
public class PagerTitleStrip extends ViewGroup {
    class PageListener extends DataSetObserver implements OnAdapterChangeListener, OnPageChangeListener {
        private int mScrollState;

        @Override  // androidx.viewpager.widget.ViewPager$OnAdapterChangeListener
        public void onAdapterChanged(ViewPager viewPager0, PagerAdapter pagerAdapter0, PagerAdapter pagerAdapter1) {
            PagerTitleStrip.this.updateAdapter(pagerAdapter0, pagerAdapter1);
        }

        @Override  // android.database.DataSetObserver
        public void onChanged() {
            PagerTitleStrip.this.updateText(PagerTitleStrip.this.mPager.getCurrentItem(), PagerTitleStrip.this.mPager.getAdapter());
            PagerTitleStrip.this.updateTextPositions(PagerTitleStrip.this.mPager.getCurrentItem(), (PagerTitleStrip.this.mLastKnownPositionOffset >= 0.0f ? PagerTitleStrip.this.mLastKnownPositionOffset : 0.0f), true);
        }

        @Override  // androidx.viewpager.widget.ViewPager$OnPageChangeListener
        public void onPageScrollStateChanged(int v) {
            this.mScrollState = v;
        }

        @Override  // androidx.viewpager.widget.ViewPager$OnPageChangeListener
        public void onPageScrolled(int v, float f, int v1) {
            if(f > 0.5f) {
                ++v;
            }
            PagerTitleStrip.this.updateTextPositions(v, f, false);
        }

        @Override  // androidx.viewpager.widget.ViewPager$OnPageChangeListener
        public void onPageSelected(int v) {
            if(this.mScrollState == 0) {
                PagerTitleStrip.this.updateText(PagerTitleStrip.this.mPager.getCurrentItem(), PagerTitleStrip.this.mPager.getAdapter());
                PagerTitleStrip.this.updateTextPositions(PagerTitleStrip.this.mPager.getCurrentItem(), (PagerTitleStrip.this.mLastKnownPositionOffset >= 0.0f ? PagerTitleStrip.this.mLastKnownPositionOffset : 0.0f), true);
            }
        }
    }

    static class SingleLineAllCapsTransform extends SingleLineTransformationMethod {
        private Locale mLocale;

        SingleLineAllCapsTransform(Context context0) {
            this.mLocale = context0.getResources().getConfiguration().locale;
        }

        @Override  // android.text.method.ReplacementTransformationMethod
        public CharSequence getTransformation(CharSequence charSequence0, View view0) {
            CharSequence charSequence1 = super.getTransformation(charSequence0, view0);
            return charSequence1 != null ? charSequence1.toString().toUpperCase(this.mLocale) : null;
        }
    }

    private static final int[] ATTRS = null;
    private static final float SIDE_ALPHA = 0.6f;
    private static final int[] TEXT_ATTRS = null;
    private static final int TEXT_SPACING = 16;
    TextView mCurrText;
    private int mGravity;
    private int mLastKnownCurrentPage;
    float mLastKnownPositionOffset;
    TextView mNextText;
    private int mNonPrimaryAlpha;
    private final PageListener mPageListener;
    ViewPager mPager;
    TextView mPrevText;
    private int mScaledTextSpacing;
    int mTextColor;
    private boolean mUpdatingPositions;
    private boolean mUpdatingText;
    private WeakReference mWatchingAdapter;

    static {
        PagerTitleStrip.ATTRS = new int[]{0x1010034, 0x1010095, 0x1010098, 0x10100AF};
        PagerTitleStrip.TEXT_ATTRS = new int[]{0x101038C};
    }

    public PagerTitleStrip(@NonNull Context context0) {
        this(context0, null);
    }

    public PagerTitleStrip(@NonNull Context context0, @Nullable AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mLastKnownCurrentPage = -1;
        this.mLastKnownPositionOffset = -1.0f;
        this.mPageListener = new PageListener(this);
        TextView textView0 = new TextView(context0);
        this.mPrevText = textView0;
        this.addView(textView0);
        TextView textView1 = new TextView(context0);
        this.mCurrText = textView1;
        this.addView(textView1);
        TextView textView2 = new TextView(context0);
        this.mNextText = textView2;
        this.addView(textView2);
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, PagerTitleStrip.ATTRS);
        boolean z = false;
        int v = typedArray0.getResourceId(0, 0);
        if(v != 0) {
            TextViewCompat.setTextAppearance(this.mPrevText, v);
            TextViewCompat.setTextAppearance(this.mCurrText, v);
            TextViewCompat.setTextAppearance(this.mNextText, v);
        }
        int v1 = typedArray0.getDimensionPixelSize(1, 0);
        if(v1 != 0) {
            this.setTextSize(0, ((float)v1));
        }
        if(typedArray0.hasValue(2)) {
            int v2 = typedArray0.getColor(2, 0);
            this.mPrevText.setTextColor(v2);
            this.mCurrText.setTextColor(v2);
            this.mNextText.setTextColor(v2);
        }
        this.mGravity = typedArray0.getInteger(3, 80);
        typedArray0.recycle();
        this.mTextColor = this.mCurrText.getTextColors().getDefaultColor();
        this.setNonPrimaryAlpha(0.6f);
        this.mPrevText.setEllipsize(TextUtils.TruncateAt.END);
        this.mCurrText.setEllipsize(TextUtils.TruncateAt.END);
        this.mNextText.setEllipsize(TextUtils.TruncateAt.END);
        if(v != 0) {
            TypedArray typedArray1 = context0.obtainStyledAttributes(v, PagerTitleStrip.TEXT_ATTRS);
            z = typedArray1.getBoolean(0, false);
            typedArray1.recycle();
        }
        if(z) {
            PagerTitleStrip.setSingleLineAllCaps(this.mPrevText);
            PagerTitleStrip.setSingleLineAllCaps(this.mCurrText);
            PagerTitleStrip.setSingleLineAllCaps(this.mNextText);
        }
        else {
            this.mPrevText.setSingleLine();
            this.mCurrText.setSingleLine();
            this.mNextText.setSingleLine();
        }
        this.mScaledTextSpacing = (int)(context0.getResources().getDisplayMetrics().density * 16.0f);
    }

    int getMinHeight() {
        Drawable drawable0 = this.getBackground();
        return drawable0 == null ? 0 : drawable0.getIntrinsicHeight();
    }

    public int getTextSpacing() {
        return this.mScaledTextSpacing;
    }

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent viewParent0 = this.getParent();
        if(!(viewParent0 instanceof ViewPager)) {
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        }
        ((ViewPager)viewParent0).setInternalPageChangeListener(this.mPageListener);
        ((ViewPager)viewParent0).addOnAdapterChangeListener(this.mPageListener);
        this.mPager = (ViewPager)viewParent0;
        this.updateAdapter((this.mWatchingAdapter == null ? null : ((PagerAdapter)this.mWatchingAdapter.get())), ((ViewPager)viewParent0).getAdapter());
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager0 = this.mPager;
        if(viewPager0 != null) {
            this.updateAdapter(viewPager0.getAdapter(), null);
            this.mPager.setInternalPageChangeListener(null);
            this.mPager.removeOnAdapterChangeListener(this.mPageListener);
            this.mPager = null;
        }
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        if(this.mPager != null) {
            this.updateTextPositions(this.mLastKnownCurrentPage, (this.mLastKnownPositionOffset >= 0.0f ? this.mLastKnownPositionOffset : 0.0f), true);
        }
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        int v6;
        if(View.MeasureSpec.getMode(v) != 0x40000000) {
            throw new IllegalStateException("Must measure with an exact width");
        }
        int v2 = this.getPaddingTop() + this.getPaddingBottom();
        int v3 = PagerTitleStrip.getChildMeasureSpec(v1, v2, -2);
        int v4 = View.MeasureSpec.getSize(v);
        int v5 = PagerTitleStrip.getChildMeasureSpec(v, ((int)(((float)v4) * 0.2f)), -2);
        this.mPrevText.measure(v5, v3);
        this.mCurrText.measure(v5, v3);
        this.mNextText.measure(v5, v3);
        if(View.MeasureSpec.getMode(v1) == 0x40000000) {
            v6 = View.MeasureSpec.getSize(v1);
        }
        else {
            int v7 = this.mCurrText.getMeasuredHeight();
            v6 = Math.max(this.getMinHeight(), v7 + v2);
        }
        this.setMeasuredDimension(v4, View.resolveSizeAndState(v6, v1, this.mCurrText.getMeasuredState() << 16));
    }

    @Override  // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if(!this.mUpdatingText) {
            super.requestLayout();
        }
    }

    public void setGravity(int v) {
        this.mGravity = v;
        this.requestLayout();
    }

    public void setNonPrimaryAlpha(@FloatRange(from = 0.0, to = 1.0) float f) {
        this.mNonPrimaryAlpha = ((int)(f * 255.0f)) & 0xFF;
        int v = this.mNonPrimaryAlpha << 24 | this.mTextColor & 0xFFFFFF;
        this.mPrevText.setTextColor(v);
        this.mNextText.setTextColor(v);
    }

    private static void setSingleLineAllCaps(TextView textView0) {
        textView0.setTransformationMethod(new SingleLineAllCapsTransform(textView0.getContext()));
    }

    public void setTextColor(@ColorInt int v) {
        this.mTextColor = v;
        this.mCurrText.setTextColor(v);
        int v1 = this.mNonPrimaryAlpha << 24 | this.mTextColor & 0xFFFFFF;
        this.mPrevText.setTextColor(v1);
        this.mNextText.setTextColor(v1);
    }

    public void setTextSize(int v, float f) {
        this.mPrevText.setTextSize(v, f);
        this.mCurrText.setTextSize(v, f);
        this.mNextText.setTextSize(v, f);
    }

    public void setTextSpacing(int v) {
        this.mScaledTextSpacing = v;
        this.requestLayout();
    }

    void updateAdapter(PagerAdapter pagerAdapter0, PagerAdapter pagerAdapter1) {
        if(pagerAdapter0 != null) {
            pagerAdapter0.unregisterDataSetObserver(this.mPageListener);
            this.mWatchingAdapter = null;
        }
        if(pagerAdapter1 != null) {
            pagerAdapter1.registerDataSetObserver(this.mPageListener);
            this.mWatchingAdapter = new WeakReference(pagerAdapter1);
        }
        ViewPager viewPager0 = this.mPager;
        if(viewPager0 != null) {
            this.mLastKnownCurrentPage = -1;
            this.mLastKnownPositionOffset = -1.0f;
            this.updateText(viewPager0.getCurrentItem(), pagerAdapter1);
            this.requestLayout();
        }
    }

    void updateText(int v, PagerAdapter pagerAdapter0) {
        if(pagerAdapter0 != null) {
            pagerAdapter0.getCount();
        }
        this.mUpdatingText = true;
        this.mPrevText.setText(null);
        this.mCurrText.setText(null);
        this.mNextText.setText(null);
        int v1 = View.MeasureSpec.makeMeasureSpec(Math.max(0, ((int)(((float)(this.getWidth() - this.getPaddingLeft() - this.getPaddingRight())) * 0.8f))), 0x80000000);
        int v2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, this.getHeight() - this.getPaddingTop() - this.getPaddingBottom()), 0x80000000);
        this.mPrevText.measure(v1, v2);
        this.mCurrText.measure(v1, v2);
        this.mNextText.measure(v1, v2);
        this.mLastKnownCurrentPage = v;
        if(!this.mUpdatingPositions) {
            this.updateTextPositions(v, this.mLastKnownPositionOffset, false);
        }
        this.mUpdatingText = false;
    }

    void updateTextPositions(int v, float f, boolean z) {
        int v23;
        int v22;
        int v21;
        if(v != this.mLastKnownCurrentPage) {
            this.updateText(v, this.mPager.getAdapter());
        }
        else if(!z && f == this.mLastKnownPositionOffset) {
            return;
        }
        this.mUpdatingPositions = true;
        int v1 = this.mPrevText.getMeasuredWidth();
        int v2 = this.mCurrText.getMeasuredWidth();
        int v3 = this.mNextText.getMeasuredWidth();
        int v4 = this.getWidth();
        int v5 = this.getHeight();
        int v6 = this.getPaddingLeft();
        int v7 = this.getPaddingRight();
        int v8 = this.getPaddingTop();
        int v9 = this.getPaddingBottom();
        int v10 = v7 + v2 / 2;
        float f1 = f + 0.5f;
        int v11 = v4 - v10 - ((int)(((float)(v4 - (v6 + v2 / 2) - v10)) * (f1 > 1.0f ? f1 - 1.0f : f + 0.5f))) - v2 / 2;
        int v12 = v2 + v11;
        int v13 = this.mPrevText.getBaseline();
        int v14 = this.mCurrText.getBaseline();
        int v15 = this.mNextText.getBaseline();
        int v16 = Math.max(Math.max(v13, v14), v15);
        int v17 = v16 - v13;
        int v18 = v16 - v14;
        int v19 = v16 - v15;
        int v20 = Math.max(Math.max(this.mPrevText.getMeasuredHeight() + v17, this.mCurrText.getMeasuredHeight() + v18), this.mNextText.getMeasuredHeight() + v19);
        switch(this.mGravity & 0x70) {
            case 16: {
                int v24 = (v5 - v8 - v9 - v20) / 2;
                v21 = v17 + v24;
                v22 = v18 + v24;
                v23 = v24 + v19;
                break;
            }
            case 80: {
                int v25 = v5 - v9 - v20;
                v21 = v17 + v25;
                v22 = v18 + v25;
                v23 = v25 + v19;
                break;
            }
            default: {
                v21 = v17 + v8;
                v22 = v18 + v8;
                v23 = v8 + v19;
            }
        }
        this.mCurrText.layout(v11, v22, v12, this.mCurrText.getMeasuredHeight() + v22);
        int v26 = Math.min(v6, v11 - this.mScaledTextSpacing - v1);
        this.mPrevText.layout(v26, v21, v1 + v26, this.mPrevText.getMeasuredHeight() + v21);
        int v27 = Math.max(v4 - v7 - v3, v12 + this.mScaledTextSpacing);
        this.mNextText.layout(v27, v23, v27 + v3, this.mNextText.getMeasuredHeight() + v23);
        this.mLastKnownPositionOffset = f;
        this.mUpdatingPositions = false;
    }
}

