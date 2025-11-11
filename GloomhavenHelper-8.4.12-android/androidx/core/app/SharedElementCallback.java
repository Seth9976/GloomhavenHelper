package androidx.core.app;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.ImageView;
import java.util.List;
import java.util.Map;

public abstract class SharedElementCallback {
    public interface OnSharedElementsReadyListener {
        void onSharedElementsReady();
    }

    private static final String BUNDLE_SNAPSHOT_BITMAP = "sharedElement:snapshot:bitmap";
    private static final String BUNDLE_SNAPSHOT_IMAGE_MATRIX = "sharedElement:snapshot:imageMatrix";
    private static final String BUNDLE_SNAPSHOT_IMAGE_SCALETYPE = "sharedElement:snapshot:imageScaleType";
    private static final int MAX_IMAGE_SIZE = 0x100000;
    private Matrix mTempMatrix;

    private static Bitmap createDrawableBitmap(Drawable drawable0) {
        int v = drawable0.getIntrinsicWidth();
        int v1 = drawable0.getIntrinsicHeight();
        if(v > 0 && v1 > 0) {
            float f = Math.min(1.0f, 1048576.0f / ((float)(v * v1)));
            if(drawable0 instanceof BitmapDrawable && f == 1.0f) {
                return ((BitmapDrawable)drawable0).getBitmap();
            }
            int v2 = (int)(((float)v) * f);
            int v3 = (int)(((float)v1) * f);
            Bitmap bitmap0 = Bitmap.createBitmap(v2, v3, Bitmap.Config.ARGB_8888);
            Canvas canvas0 = new Canvas(bitmap0);
            Rect rect0 = drawable0.getBounds();
            int v4 = rect0.left;
            int v5 = rect0.top;
            int v6 = rect0.right;
            int v7 = rect0.bottom;
            drawable0.setBounds(0, 0, v2, v3);
            drawable0.draw(canvas0);
            drawable0.setBounds(v4, v5, v6, v7);
            return bitmap0;
        }
        return null;
    }

    public Parcelable onCaptureSharedElementSnapshot(View view0, Matrix matrix0, RectF rectF0) {
        if(view0 instanceof ImageView) {
            Drawable drawable0 = ((ImageView)view0).getDrawable();
            if(drawable0 != null && ((ImageView)view0).getBackground() == null) {
                Bitmap bitmap0 = SharedElementCallback.createDrawableBitmap(drawable0);
                if(bitmap0 != null) {
                    Parcelable parcelable0 = new Bundle();
                    ((Bundle)parcelable0).putParcelable("sharedElement:snapshot:bitmap", bitmap0);
                    ((Bundle)parcelable0).putString("sharedElement:snapshot:imageScaleType", ((ImageView)view0).getScaleType().toString());
                    if(((ImageView)view0).getScaleType() == ImageView.ScaleType.MATRIX) {
                        Matrix matrix1 = ((ImageView)view0).getImageMatrix();
                        float[] arr_f = new float[9];
                        matrix1.getValues(arr_f);
                        ((Bundle)parcelable0).putFloatArray("sharedElement:snapshot:imageMatrix", arr_f);
                    }
                    return parcelable0;
                }
            }
        }
        int v = Math.round(rectF0.width());
        int v1 = Math.round(rectF0.height());
        Parcelable parcelable1 = null;
        if(v > 0 && v1 > 0) {
            float f = Math.min(1.0f, 1048576.0f / ((float)(v * v1)));
            if(this.mTempMatrix == null) {
                this.mTempMatrix = new Matrix();
            }
            this.mTempMatrix.set(matrix0);
            this.mTempMatrix.postTranslate(-rectF0.left, -rectF0.top);
            this.mTempMatrix.postScale(f, f);
            parcelable1 = Bitmap.createBitmap(((int)(((float)v) * f)), ((int)(((float)v1) * f)), Bitmap.Config.ARGB_8888);
            Canvas canvas0 = new Canvas(((Bitmap)parcelable1));
            canvas0.concat(this.mTempMatrix);
            view0.draw(canvas0);
        }
        return parcelable1;
    }

    public View onCreateSnapshotView(Context context0, Parcelable parcelable0) {
        View view0 = null;
        if(parcelable0 instanceof Bundle) {
            Bitmap bitmap0 = (Bitmap)((Bundle)parcelable0).getParcelable("sharedElement:snapshot:bitmap");
            if(bitmap0 == null) {
                return null;
            }
            view0 = new ImageView(context0);
            ((ImageView)view0).setImageBitmap(bitmap0);
            ((ImageView)view0).setScaleType(ImageView.ScaleType.valueOf(((Bundle)parcelable0).getString("sharedElement:snapshot:imageScaleType")));
            if(((ImageView)view0).getScaleType() == ImageView.ScaleType.MATRIX) {
                float[] arr_f = ((Bundle)parcelable0).getFloatArray("sharedElement:snapshot:imageMatrix");
                Matrix matrix0 = new Matrix();
                matrix0.setValues(arr_f);
                ((ImageView)view0).setImageMatrix(matrix0);
                return view0;
            }
        }
        else if(parcelable0 instanceof Bitmap) {
            view0 = new ImageView(context0);
            ((ImageView)view0).setImageBitmap(((Bitmap)parcelable0));
        }
        return view0;
    }

    public void onMapSharedElements(List list0, Map map0) {
    }

    public void onRejectSharedElements(List list0) {
    }

    public void onSharedElementEnd(List list0, List list1, List list2) {
    }

    public void onSharedElementStart(List list0, List list1, List list2) {
    }

    public void onSharedElementsArrived(List list0, List list1, OnSharedElementsReadyListener sharedElementCallback$OnSharedElementsReadyListener0) {
        sharedElementCallback$OnSharedElementsReadyListener0.onSharedElementsReady();
    }
}

