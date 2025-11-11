package androidx.core.view;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.View;

public class DragStartHelper {
    public interface OnDragStartListener {
        boolean onDragStart(View arg1, DragStartHelper arg2);
    }

    private boolean mDragging;
    private int mLastTouchX;
    private int mLastTouchY;
    private final OnDragStartListener mListener;
    private final View.OnLongClickListener mLongClickListener;
    private final View.OnTouchListener mTouchListener;
    private final View mView;

    public DragStartHelper(View view0, OnDragStartListener dragStartHelper$OnDragStartListener0) {
        this.mLongClickListener = (View view0) -> DragStartHelper.this.mListener.onDragStart(view0, DragStartHelper.this);
        this.mTouchListener = (View view0, MotionEvent motionEvent0) -> {
            int v = (int)motionEvent0.getX();
            int v1 = (int)motionEvent0.getY();
            switch(motionEvent0.getAction()) {
                case 0: {
                    DragStartHelper.this.mLastTouchX = v;
                    DragStartHelper.this.mLastTouchY = v1;
                    return false;
                }
                case 2: {
                    if(MotionEventCompat.isFromSource(motionEvent0, 0x2002) && (motionEvent0.getButtonState() & 1) != 0 && !DragStartHelper.this.mDragging && (DragStartHelper.this.mLastTouchX != v || DragStartHelper.this.mLastTouchY != v1)) {
                        DragStartHelper.this.mLastTouchX = v;
                        DragStartHelper.this.mLastTouchY = v1;
                        DragStartHelper.this.mDragging = DragStartHelper.this.mListener.onDragStart(view0, DragStartHelper.this);
                        return DragStartHelper.this.mDragging;
                    }
                    return false;
                }
                case 1: 
                case 3: {
                    DragStartHelper.this.mDragging = false;
                    return false;
                }
                default: {
                    return false;
                }
            }
        };
        this.mView = view0;
        this.mListener = dragStartHelper$OnDragStartListener0;
    }

    public void attach() {
        this.mView.setOnLongClickListener(this.mLongClickListener);
        this.mView.setOnTouchListener(this.mTouchListener);
    }

    public void detach() {
        this.mView.setOnLongClickListener(null);
        this.mView.setOnTouchListener(null);
    }

    public void getTouchPosition(Point point0) {
        point0.set(this.mLastTouchX, this.mLastTouchY);
    }

    // 检测为 Lambda 实现
    public boolean onLongClick(View view0) [...]

    // 检测为 Lambda 实现
    public boolean onTouch(View view0, MotionEvent motionEvent0) [...]

    class androidx.core.view.DragStartHelper.1 implements View.OnLongClickListener {
        @Override  // android.view.View$OnLongClickListener
        public boolean onLongClick(View view0) {
            return DragStartHelper.this.onLongClick(view0);
        }
    }


    class androidx.core.view.DragStartHelper.2 implements View.OnTouchListener {
        @Override  // android.view.View$OnTouchListener
        public boolean onTouch(View view0, MotionEvent motionEvent0) {
            return DragStartHelper.this.onTouch(view0, motionEvent0);
        }
    }

}

