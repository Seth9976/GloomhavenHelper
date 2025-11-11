package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;

public abstract class TemporalAction extends Action {
    private boolean began;
    private boolean complete;
    private float duration;
    @Null
    private Interpolation interpolation;
    private boolean reverse;
    private float time;

    public TemporalAction() {
    }

    public TemporalAction(float f) {
        this.duration = f;
    }

    public TemporalAction(float f, @Null Interpolation interpolation0) {
        this.duration = f;
        this.interpolation = interpolation0;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f) {
        boolean z = true;
        if(this.complete) {
            return true;
        }
        Pool pool0 = this.getPool();
        this.setPool(null);
        try {
            if(!this.began) {
                this.begin();
                this.began = true;
            }
            this.time += f;
            if(this.time < this.duration) {
                z = false;
            }
            this.complete = z;
            float f1 = this.complete ? 1.0f : this.time / this.duration;
            if(this.interpolation != null) {
                f1 = this.interpolation.apply(f1);
            }
            if(this.reverse) {
                f1 = 1.0f - f1;
            }
            this.update(f1);
            if(this.complete) {
                this.end();
            }
            return this.complete;
        }
        finally {
            this.setPool(pool0);
        }
    }

    protected void begin() {
    }

    protected void end() {
    }

    public void finish() {
        this.time = this.duration;
    }

    public float getDuration() {
        return this.duration;
    }

    @Null
    public Interpolation getInterpolation() {
        return this.interpolation;
    }

    public float getTime() {
        return this.time;
    }

    public boolean isComplete() {
        return this.complete;
    }

    public boolean isReverse() {
        return this.reverse;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public void reset() {
        super.reset();
        this.reverse = false;
        this.interpolation = null;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public void restart() {
        this.time = 0.0f;
        this.began = false;
        this.complete = false;
    }

    public void setDuration(float f) {
        this.duration = f;
    }

    public void setInterpolation(@Null Interpolation interpolation0) {
        this.interpolation = interpolation0;
    }

    public void setReverse(boolean z) {
        this.reverse = z;
    }

    public void setTime(float f) {
        this.time = f;
    }

    protected abstract void update(float arg1);
}

