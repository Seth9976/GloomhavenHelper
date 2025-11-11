package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

public class RemoveAction extends Action {
    private Action action;

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f) {
        this.target.removeAction(this.action);
        return true;
    }

    public Action getAction() {
        return this.action;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public void reset() {
        super.reset();
        this.action = null;
    }

    public void setAction(Action action0) {
        this.action = action0;
    }
}

