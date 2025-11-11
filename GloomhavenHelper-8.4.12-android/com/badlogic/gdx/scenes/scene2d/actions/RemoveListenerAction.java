package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.EventListener;

public class RemoveListenerAction extends Action {
    private boolean capture;
    private EventListener listener;

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f) {
        if(this.capture) {
            this.target.removeCaptureListener(this.listener);
            return true;
        }
        this.target.removeListener(this.listener);
        return true;
    }

    public boolean getCapture() {
        return this.capture;
    }

    public EventListener getListener() {
        return this.listener;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public void reset() {
        super.reset();
        this.listener = null;
    }

    public void setCapture(boolean z) {
        this.capture = z;
    }

    public void setListener(EventListener eventListener0) {
        this.listener = eventListener0;
    }
}

