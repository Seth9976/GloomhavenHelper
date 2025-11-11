package androidx.legacy.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@Deprecated
public class Space extends View {
    @Deprecated
    public Space(@NonNull Context context0) {
        this(context0, null);
    }

    @Deprecated
    public Space(@NonNull Context context0, @Nullable AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    @Deprecated
    public Space(@NonNull Context context0, @Nullable AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        if(this.getVisibility() == 0) {
            this.setVisibility(4);
        }
    }

    @Override  // android.view.View
    @SuppressLint({"MissingSuperCall"})
    @Deprecated
    public void draw(Canvas canvas0) {
    }

    private static int getDefaultSize2(int v, int v1) {
        int v2 = View.MeasureSpec.getMode(v1);
        int v3 = View.MeasureSpec.getSize(v1);
        switch(v2) {
            case 0x80000000: {
                return Math.min(v, v3);
            }
            case 0x40000000: {
                return v3;
            }
            default: {
                return v;
            }
        }
    }

    @Override  // android.view.View
    @Deprecated
    protected void onMeasure(int v, int v1) {
        this.setMeasuredDimension(Space.getDefaultSize2(this.getSuggestedMinimumWidth(), v), Space.getDefaultSize2(this.getSuggestedMinimumHeight(), v1));
    }
}

