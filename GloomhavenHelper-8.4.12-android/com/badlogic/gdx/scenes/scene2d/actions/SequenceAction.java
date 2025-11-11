package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Pool;

public class SequenceAction extends ParallelAction {
    private int index;

    public SequenceAction() {
    }

    public SequenceAction(Action action0) {
        this.addAction(action0);
    }

    public SequenceAction(Action action0, Action action1) {
        this.addAction(action0);
        this.addAction(action1);
    }

    public SequenceAction(Action action0, Action action1, Action action2) {
        this.addAction(action0);
        this.addAction(action1);
        this.addAction(action2);
    }

    public SequenceAction(Action action0, Action action1, Action action2, Action action3) {
        this.addAction(action0);
        this.addAction(action1);
        this.addAction(action2);
        this.addAction(action3);
    }

    public SequenceAction(Action action0, Action action1, Action action2, Action action3, Action action4) {
        this.addAction(action0);
        this.addAction(action1);
        this.addAction(action2);
        this.addAction(action3);
        this.addAction(action4);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.ParallelAction
    public boolean act(float f) {
        if(this.index >= this.actions.size) {
            return true;
        }
        Pool pool0 = this.getPool();
        this.setPool(null);
        try {
            if(((Action)this.actions.get(this.index)).act(f)) {
                if(this.actor == null) {
                    return true;
                }
                ++this.index;
                if(this.index >= this.actions.size) {
                    return true;
                }
            }
            return false;
        }
        finally {
            this.setPool(pool0);
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.ParallelAction
    public void restart() {
        super.restart();
        this.index = 0;
    }
}

