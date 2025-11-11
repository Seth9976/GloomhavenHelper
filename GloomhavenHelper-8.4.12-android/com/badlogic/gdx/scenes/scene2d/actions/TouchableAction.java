package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class TouchableAction extends Action {
    private Touchable touchable;

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f) {
        this.target.setTouchable(this.touchable);
        return true;
    }

    public Touchable getTouchable() {
        return this.touchable;
    }

    public void setTouchable(Touchable touchable0) {
        this.touchable = touchable0;
    }
}

