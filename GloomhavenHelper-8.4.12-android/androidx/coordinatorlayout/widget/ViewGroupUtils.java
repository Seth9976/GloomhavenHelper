package androidx.coordinatorlayout.widget;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;

@RestrictTo({Scope.LIBRARY})
public class ViewGroupUtils {
    private static final ThreadLocal sMatrix;
    private static final ThreadLocal sRectF;

    static {
        ViewGroupUtils.sMatrix = new ThreadLocal();
        ViewGroupUtils.sRectF = new ThreadLocal();
    }

    public static void getDescendantRect(ViewGroup viewGroup0, View view0, Rect rect0) {
        rect0.set(0, 0, view0.getWidth(), view0.getHeight());
        ViewGroupUtils.offsetDescendantRect(viewGroup0, view0, rect0);
    }

    private static void offsetDescendantMatrix(ViewParent viewParent0, View view0, Matrix matrix0) {
        ViewParent viewParent1 = view0.getParent();
        if(viewParent1 instanceof View && viewParent1 != viewParent0) {
            ViewGroupUtils.offsetDescendantMatrix(viewParent0, ((View)viewParent1), matrix0);
            matrix0.preTranslate(((float)(-((View)viewParent1).getScrollX())), ((float)(-((View)viewParent1).getScrollY())));
        }
        matrix0.preTranslate(((float)view0.getLeft()), ((float)view0.getTop()));
        if(!view0.getMatrix().isIdentity()) {
            matrix0.preConcat(view0.getMatrix());
        }
    }

    static void offsetDescendantRect(ViewGroup viewGroup0, View view0, Rect rect0) {
        Matrix matrix0 = (Matrix)ViewGroupUtils.sMatrix.get();
        if(matrix0 == null) {
            matrix0 = new Matrix();
            ViewGroupUtils.sMatrix.set(matrix0);
        }
        else {
            matrix0.reset();
        }
        ViewGroupUtils.offsetDescendantMatrix(viewGroup0, view0, matrix0);
        RectF rectF0 = (RectF)ViewGroupUtils.sRectF.get();
        if(rectF0 == null) {
            rectF0 = new RectF();
            ViewGroupUtils.sRectF.set(rectF0);
        }
        rectF0.set(rect0);
        matrix0.mapRect(rectF0);
        rect0.set(((int)(rectF0.left + 0.5f)), ((int)(rectF0.top + 0.5f)), ((int)(rectF0.right + 0.5f)), ((int)(rectF0.bottom + 0.5f)));
    }
}

