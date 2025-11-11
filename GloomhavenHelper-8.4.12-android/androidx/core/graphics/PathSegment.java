package androidx.core.graphics;

import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;

public final class PathSegment {
    private final PointF mEnd;
    private final float mEndFraction;
    private final PointF mStart;
    private final float mStartFraction;

    public PathSegment(@NonNull PointF pointF0, float f, @NonNull PointF pointF1, float f1) {
        this.mStart = (PointF)Preconditions.checkNotNull(pointF0, "start == null");
        this.mStartFraction = f;
        this.mEnd = (PointF)Preconditions.checkNotNull(pointF1, "end == null");
        this.mEndFraction = f1;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof PathSegment ? Float.compare(this.mStartFraction, ((PathSegment)object0).mStartFraction) == 0 && Float.compare(this.mEndFraction, ((PathSegment)object0).mEndFraction) == 0 && this.mStart.equals(((PathSegment)object0).mStart) && this.mEnd.equals(((PathSegment)object0).mEnd) : false;
    }

    @NonNull
    public PointF getEnd() {
        return this.mEnd;
    }

    public float getEndFraction() {
        return this.mEndFraction;
    }

    @NonNull
    public PointF getStart() {
        return this.mStart;
    }

    public float getStartFraction() {
        return this.mStartFraction;
    }

    @Override
    public int hashCode() {
        int v = this.mStart.hashCode();
        int v1 = 0;
        int v2 = this.mStartFraction == 0.0f ? 0 : Float.floatToIntBits(this.mStartFraction);
        int v3 = this.mEnd.hashCode();
        float f = this.mEndFraction;
        if(f != 0.0f) {
            v1 = Float.floatToIntBits(f);
        }
        return ((v * 0x1F + v2) * 0x1F + v3) * 0x1F + v1;
    }

    @Override
    public String toString() {
        return "PathSegment{start=" + this.mStart + ", startFraction=" + this.mStartFraction + ", end=" + this.mEnd + ", endFraction=" + this.mEndFraction + '}';
    }
}

