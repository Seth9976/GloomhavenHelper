package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.utils.reflect.ClassReflection;

public abstract class EventAction extends Action {
    boolean active;
    final Class eventClass;
    private final EventListener listener;
    boolean result;

    public EventAction(Class class0) {
        this.listener = new EventListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.EventListener
            public boolean handle(Event event0) {
                if(EventAction.this.active && ClassReflection.isInstance(EventAction.this.eventClass, event0)) {
                    EventAction.this.result = EventAction.this.handle(event0);
                    return EventAction.this.result;
                }
                return false;
            }
        };
        this.eventClass = class0;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f) {
        this.active = true;
        return this.result;
    }

    public abstract boolean handle(Event arg1);

    public boolean isActive() {
        return this.active;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public void restart() {
        this.result = false;
        this.active = false;
    }

    public void setActive(boolean z) {
        this.active = z;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Action
    public void setTarget(Actor actor0) {
        if(this.target != null) {
            this.target.removeListener(this.listener);
        }
        super.setTarget(actor0);
        if(actor0 != null) {
            actor0.addListener(this.listener);
        }
    }
}

