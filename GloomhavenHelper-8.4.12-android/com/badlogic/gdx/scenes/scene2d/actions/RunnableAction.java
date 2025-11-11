package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Pool;

public class RunnableAction extends Action {
    private boolean ran;
    private Runnable runnable;

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f) {
        if(!this.ran) {
            this.ran = true;
            this.run();
        }
        return true;
    }

    public Runnable getRunnable() {
        return this.runnable;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public void reset() {
        super.reset();
        this.runnable = null;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public void restart() {
        this.ran = false;
    }

    public void run() {
        Pool pool0 = this.getPool();
        this.setPool(null);
        try {
            this.runnable.run();
        }
        finally {
            this.setPool(pool0);
        }
    }

    public void setRunnable(Runnable runnable0) {
        this.runnable = runnable0;
    }
}

