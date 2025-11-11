package androidx.swiperefreshlayout.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

class CircleImageView extends ImageView {
    class OvalShadow extends OvalShape {
        private RadialGradient mRadialGradient;
        private Paint mShadowPaint;

        OvalShadow(int v) {
            this.mShadowPaint = new Paint();
            circleImageView0.mShadowRadius = v;
            this.updateRadialGradient(((int)this.rect().width()));
        }

        @Override  // android.graphics.drawable.shapes.OvalShape
        public void draw(Canvas canvas0, Paint paint0) {
            int v = CircleImageView.this.getWidth();
            int v1 = CircleImageView.this.getHeight();
            float f = (float)(v / 2);
            canvas0.drawCircle(f, ((float)(v1 / 2)), f, this.mShadowPaint);
            canvas0.drawCircle(f, ((float)(v1 / 2)), ((float)(v / 2 - CircleImageView.this.mShadowRadius)), paint0);
        }

        @Override  // android.graphics.drawable.shapes.RectShape
        protected void onResize(float f, float f1) {
            super.onResize(f, f1);
            this.updateRadialGradient(((int)f));
        }

        private void updateRadialGradient(int v) {
            this.mRadialGradient = new RadialGradient(((float)(v / 2)), ((float)(v / 2)), ((float)CircleImageView.this.mShadowRadius), new int[]{0x3D000000, 0}, null, Shader.TileMode.CLAMP);
            this.mShadowPaint.setShader(this.mRadialGradient);
        }
    }

    private static final int FILL_SHADOW_COLOR = 0x3D000000;
    private static final int KEY_SHADOW_COLOR = 0x1E000000;
    private static final int SHADOW_ELEVATION = 4;
    private static final float SHADOW_RADIUS = 3.5f;
    private static final float X_OFFSET = 0.0f;
    private static final float Y_OFFSET = 1.75f;
    private Animation.AnimationListener mListener;
    int mShadowRadius;

    CircleImageView(Context context0, int v) {
        super(context0);
        float f = this.getContext().getResources().getDisplayMetrics().density;
        this.mShadowRadius = (int)(3.5f * f);
        ShapeDrawable shapeDrawable0 = new ShapeDrawable(new OvalShape());
        ViewCompat.setElevation(this, f * 4.0f);
        shapeDrawable0.getPaint().setColor(v);
        ViewCompat.setBackground(this, shapeDrawable0);
    }

    private boolean elevationSupported() [...] // 潜在的解密器

    @Override  // android.view.View
    public void onAnimationEnd() {
        super.onAnimationEnd();
        Animation.AnimationListener animation$AnimationListener0 = this.mListener;
        if(animation$AnimationListener0 != null) {
            animation$AnimationListener0.onAnimationEnd(this.getAnimation());
        }
    }

    @Override  // android.view.View
    public void onAnimationStart() {
        super.onAnimationStart();
        Animation.AnimationListener animation$AnimationListener0 = this.mListener;
        if(animation$AnimationListener0 != null) {
            animation$AnimationListener0.onAnimationStart(this.getAnimation());
        }
    }

    @Override  // android.widget.ImageView
    protected void onMeasure(int v, int v1) {
        super.onMeasure(v, v1);
    }

    public void setAnimationListener(Animation.AnimationListener animation$AnimationListener0) {
        this.mListener = animation$AnimationListener0;
    }

    @Override  // android.view.View
    public void setBackgroundColor(int v) {
        if(this.getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable)this.getBackground()).getPaint().setColor(v);
        }
    }

    public void setBackgroundColorRes(int v) {
        this.setBackgroundColor(ContextCompat.getColor(this.getContext(), v));
    }
}

