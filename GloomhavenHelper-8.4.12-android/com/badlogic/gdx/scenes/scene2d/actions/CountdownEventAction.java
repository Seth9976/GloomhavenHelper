package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Event;

public class CountdownEventAction extends EventAction {
    int count;
    int current;

    public CountdownEventAction(Class class0, int v) {
        super(class0);
        this.count = v;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.EventAction
    public boolean handle(Event event0) {
        ++this.current;
        return this.current >= this.count;
    }
}

