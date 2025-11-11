package androidx.core.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.widget.EdgeEffect;
import androidx.annotation.NonNull;

public final class EdgeEffectCompat {
    private EdgeEffect mEdgeEffect;

    @Deprecated
    public EdgeEffectCompat(Context context0) {
        this.mEdgeEffect = new EdgeEffect(context0);
    }

    @Deprecated
    public boolean draw(Canvas canvas0) {
        return this.mEdgeEffect.draw(canvas0);
    }

    @Deprecated
    public void finish() {
        this.mEdgeEffect.finish();
    }

    @Deprecated
    public boolean isFinished() {
        return this.mEdgeEffect.isFinished();
    }

    @Deprecated
    public boolean onAbsorb(int v) {
        this.mEdgeEffect.onAbsorb(v);
        return true;
    }

    public static void onPull(@NonNull EdgeEffect edgeEffect0, float f, float f1) {
        if(Build.VERSION.SDK_INT >= 21) {
            edgeEffect0.onPull(f, f1);
            return;
        }
        edgeEffect0.onPull(f);
    }

    @Deprecated
    public boolean onPull(float f) {
        this.mEdgeEffect.onPull(f);
        return true;
    }

    @Deprecated
    public boolean onPull(float f, float f1) {
        EdgeEffectCompat.onPull(this.mEdgeEffect, f, f1);
        return true;
    }

    @Deprecated
    public boolean onRelease() {
        this.mEdgeEffect.onRelease();
        return this.mEdgeEffect.isFinished();
    }

    @Deprecated
    public void setSize(int v, int v1) {
        this.mEdgeEffect.setSize(v, v1);
    }
}

