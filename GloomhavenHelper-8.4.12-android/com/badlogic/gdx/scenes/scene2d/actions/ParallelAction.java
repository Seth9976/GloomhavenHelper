package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class ParallelAction extends Action {
    Array actions;
    private boolean complete;

    public ParallelAction() {
        this.actions = new Array(4);
    }

    public ParallelAction(Action action0) {
        this.actions = new Array(4);
        this.addAction(action0);
    }

    public ParallelAction(Action action0, Action action1) {
        this.actions = new Array(4);
        this.addAction(action0);
        this.addAction(action1);
    }

    public ParallelAction(Action action0, Action action1, Action action2) {
        this.actions = new Array(4);
        this.addAction(action0);
        this.addAction(action1);
        this.addAction(action2);
    }

    public ParallelAction(Action action0, Action action1, Action action2, Action action3) {
        this.actions = new Array(4);
        this.addAction(action0);
        this.addAction(action1);
        this.addAction(action2);
        this.addAction(action3);
    }

    public ParallelAction(Action action0, Action action1, Action action2, Action action3, Action action4) {
        this.actions = new Array(4);
        this.addAction(action0);
        this.addAction(action1);
        this.addAction(action2);
        this.addAction(action3);
        this.addAction(action4);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f) {
        if(this.complete) {
            return true;
        }
        this.complete = true;
        Pool pool0 = this.getPool();
        this.setPool(null);
        try {
            Array array0 = this.actions;
            int v1 = array0.size;
            for(int v2 = 0; v2 < v1 && this.actor != null; ++v2) {
                Action action0 = (Action)array0.get(v2);
                if(action0.getActor() != null && !action0.act(f)) {
                    this.complete = false;
                }
                if(this.actor == null) {
                    return true;
                }
            }
            return this.complete;
        }
        finally {
            this.setPool(pool0);
        }
    }

    public void addAction(Action action0) {
        this.actions.add(action0);
        if(this.actor != null) {
            action0.setActor(this.actor);
        }
    }

    public Array getActions() {
        return this.actions;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public void reset() {
        super.reset();
        this.actions.clear();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public void restart() {
        this.complete = false;
        Array array0 = this.actions;
        int v1 = array0.size;
        for(int v = 0; v < v1; ++v) {
            ((Action)array0.get(v)).restart();
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public void setActor(Actor actor0) {
        Array array0 = this.actions;
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((Action)array0.get(v1)).setActor(actor0);
        }
        super.setActor(actor0);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder(0x40);
        stringBuilder0.append("");
        stringBuilder0.append('(');
        Array array0 = this.actions;
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(v1 > 0) {
                stringBuilder0.append(", ");
            }
            stringBuilder0.append(array0.get(v1));
        }
        stringBuilder0.append(')');
        return stringBuilder0.toString();
    }
}

