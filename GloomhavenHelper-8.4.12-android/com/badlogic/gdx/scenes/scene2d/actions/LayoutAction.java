package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class LayoutAction extends Action {
    private boolean enabled;

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f) {
        ((Layout)this.target).setLayoutEnabled(this.enabled);
        return true;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setLayoutEnabled(boolean z) {
        this.enabled = z;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public void setTarget(Actor actor0) {
        if(actor0 != null && !(actor0 instanceof Layout)) {
            throw new GdxRuntimeException("Actor must implement layout: " + actor0);
        }
        super.setTarget(actor0);
    }
}

