package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class AfterAction extends DelegateAction {
    private Array waitForActions;

    public AfterAction() {
        this.waitForActions = new Array(false, 4);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.DelegateAction
    protected boolean delegate(float f) {
        Array array0 = this.target.getActions();
        if(array0.size == 1) {
            this.waitForActions.clear();
        }
        for(int v = this.waitForActions.size - 1; v >= 0; --v) {
            if(array0.indexOf(((Action)this.waitForActions.get(v)), true) == -1) {
                this.waitForActions.removeIndex(v);
            }
        }
        return this.waitForActions.size <= 0 ? this.action.act(f) : false;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.DelegateAction
    public void restart() {
        super.restart();
        this.waitForActions.clear();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.DelegateAction
    public void setTarget(Actor actor0) {
        if(actor0 != null) {
            this.waitForActions.addAll(actor0.getActions());
        }
        super.setTarget(actor0);
    }
}

