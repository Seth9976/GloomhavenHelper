package androidx.core.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.BitmapCompat;
import androidx.core.view.GravityCompat;
import java.io.InputStream;

public final class RoundedBitmapDrawableFactory {
    static class DefaultRoundedBitmapDrawable extends RoundedBitmapDrawable {
        DefaultRoundedBitmapDrawable(Resources resources0, Bitmap bitmap0) {
            super(resources0, bitmap0);
        }

        @Override  // androidx.core.graphics.drawable.RoundedBitmapDrawable
        void gravityCompatApply(int v, int v1, int v2, Rect rect0, Rect rect1) {
            GravityCompat.apply(v, v1, v2, rect0, rect1, 0);
        }

        @Override  // androidx.core.graphics.drawable.RoundedBitmapDrawable
        public boolean hasMipMap() {
            return this.mBitmap != null && BitmapCompat.hasMipMap(this.mBitmap);
        }

        @Override  // androidx.core.graphics.drawable.RoundedBitmapDrawable
        public void setMipMap(boolean z) {
            if(this.mBitmap != null) {
                BitmapCompat.setHasMipMap(this.mBitmap, z);
                this.invalidateSelf();
            }
        }
    }

    private static final String TAG = "RoundedBitmapDrawableFa";

    @NonNull
    public static RoundedBitmapDrawable create(@NonNull Resources resources0, @Nullable Bitmap bitmap0) {
        return Build.VERSION.SDK_INT >= 21 ? new RoundedBitmapDrawable21(resources0, bitmap0) : new DefaultRoundedBitmapDrawable(resources0, bitmap0);
    }

    @NonNull
    public static RoundedBitmapDrawable create(@NonNull Resources resources0, @NonNull InputStream inputStream0) {
        RoundedBitmapDrawable roundedBitmapDrawable0 = RoundedBitmapDrawableFactory.create(resources0, BitmapFactory.decodeStream(inputStream0));
        if(roundedBitmapDrawable0.getBitmap() == null) {
            Log.w("RoundedBitmapDrawableFa", "RoundedBitmapDrawable cannot decode " + inputStream0);
        }
        return roundedBitmapDrawable0;
    }

    @NonNull
    public static RoundedBitmapDrawable create(@NonNull Resources resources0, @NonNull String s) {
        RoundedBitmapDrawable roundedBitmapDrawable0 = RoundedBitmapDrawableFactory.create(resources0, BitmapFactory.decodeFile(s));
        if(roundedBitmapDrawable0.getBitmap() == null) {
            Log.w("RoundedBitmapDrawableFa", "RoundedBitmapDrawable cannot decode " + s);
        }
        return roundedBitmapDrawable0;
    }
}

