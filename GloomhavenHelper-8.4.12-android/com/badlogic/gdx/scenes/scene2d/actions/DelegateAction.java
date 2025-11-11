package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;

public abstract class DelegateAction extends Action {
    protected Action action;

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public final boolean act(float f) {
        Pool pool0 = this.getPool();
        this.setPool(null);
        try {
            return this.delegate(f);
        }
        finally {
            this.setPool(pool0);
        }
    }

    protected abstract boolean delegate(float arg1);

    public Action getAction() {
        return this.action;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public void reset() {
        super.reset();
        this.action = null;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public void restart() {
        Action action0 = this.action;
        if(action0 != null) {
            action0.restart();
        }
    }

    public void setAction(Action action0) {
        this.action = action0;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public void setActor(Actor actor0) {
        Action action0 = this.action;
        if(action0 != null) {
            action0.setActor(actor0);
        }
        super.setActor(actor0);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public void setTarget(Actor actor0) {
        Action action0 = this.action;
        if(action0 != null) {
            action0.setTarget(actor0);
        }
        super.setTarget(actor0);
    }

    // 去混淆评级： 低(30)
    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public String toString() {
        return "" + (this.action == null ? "" : "(" + this.action + ")");
    }
}

