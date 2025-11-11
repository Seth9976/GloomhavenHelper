package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pool;

public abstract class Action implements Poolable {
    protected Actor actor;
    @Null
    private Pool pool;
    protected Actor target;

    public abstract boolean act(float arg1);

    public Actor getActor() {
        return this.actor;
    }

    @Null
    public Pool getPool() {
        return this.pool;
    }

    public Actor getTarget() {
        return this.target;
    }

    @Override  // com.badlogic.gdx.utils.Pool$Poolable
    public void reset() {
        this.actor = null;
        this.target = null;
        this.pool = null;
        this.restart();
    }

    public void restart() {
    }

    public void setActor(Actor actor0) {
        this.actor = actor0;
        if(this.target == null) {
            this.setTarget(actor0);
        }
        if(actor0 == null) {
            Pool pool0 = this.pool;
            if(pool0 != null) {
                pool0.free(this);
                this.pool = null;
            }
        }
    }

    public void setPool(@Null Pool pool0) {
        this.pool = pool0;
    }

    public void setTarget(Actor actor0) {
        this.target = actor0;
    }

    @Override
    public String toString() [...] // 潜在的解密器
}

