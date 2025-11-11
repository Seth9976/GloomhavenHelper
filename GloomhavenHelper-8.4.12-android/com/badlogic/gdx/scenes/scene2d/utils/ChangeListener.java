package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

public abstract class ChangeListener implements EventListener {
    public static class ChangeEvent extends Event {
    }

    public abstract void changed(ChangeEvent arg1, Actor arg2);

    @Override  // com.badlogic.gdx.scenes.scene2d.EventListener
    public boolean handle(Event event0) {
        if(!(event0 instanceof ChangeEvent)) {
            return false;
        }
        this.changed(((ChangeEvent)event0), event0.getTarget());
        return false;
    }
}

