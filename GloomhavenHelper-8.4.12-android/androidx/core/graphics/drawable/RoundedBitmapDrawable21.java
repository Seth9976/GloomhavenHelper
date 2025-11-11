package androidx.core.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Rect;
import android.view.Gravity;
import androidx.annotation.RequiresApi;

@RequiresApi(21)
class RoundedBitmapDrawable21 extends RoundedBitmapDrawable {
    protected RoundedBitmapDrawable21(Resources resources0, Bitmap bitmap0) {
        super(resources0, bitmap0);
    }

    @Override  // android.graphics.drawable.Drawable
    public void getOutline(Outline outline0) {
        this.updateDstRect();
        outline0.setRoundRect(this.mDstRect, this.getCornerRadius());
    }

    @Override  // androidx.core.graphics.drawable.RoundedBitmapDrawable
    void gravityCompatApply(int v, int v1, int v2, Rect rect0, Rect rect1) {
        Gravity.apply(v, v1, v2, rect0, rect1, 0);
    }

    @Override  // androidx.core.graphics.drawable.RoundedBitmapDrawable
    public boolean hasMipMap() {
        return this.mBitmap != null && this.mBitmap.hasMipMap();
    }

    @Override  // androidx.core.graphics.drawable.RoundedBitmapDrawable
    public void setMipMap(boolean z) {
        if(this.mBitmap != null) {
            this.mBitmap.setHasMipMap(z);
            this.invalidateSelf();
        }
    }
}

