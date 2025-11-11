package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.utils.Pool.Poolable;

public class Event implements Poolable {
    private boolean bubbles;
    private boolean cancelled;
    private boolean capture;
    private boolean handled;
    private Actor listenerActor;
    private Stage stage;
    private boolean stopped;
    private Actor targetActor;

    public Event() {
        this.bubbles = true;
    }

    public void cancel() {
        this.cancelled = true;
        this.stopped = true;
        this.handled = true;
    }

    public boolean getBubbles() {
        return this.bubbles;
    }

    public Actor getListenerActor() {
        return this.listenerActor;
    }

    public Stage getStage() {
        return this.stage;
    }

    public Actor getTarget() {
        return this.targetActor;
    }

    public void handle() {
        this.handled = true;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public boolean isCapture() {
        return this.capture;
    }

    public boolean isHandled() {
        return this.handled;
    }

    public boolean isStopped() {
        return this.stopped;
    }

    @Override  // com.badlogic.gdx.utils.Pool$Poolable
    public void reset() {
        this.stage = null;
        this.targetActor = null;
        this.listenerActor = null;
        this.capture = false;
        this.bubbles = true;
        this.handled = false;
        this.stopped = false;
        this.cancelled = false;
    }

    public void setBubbles(boolean z) {
        this.bubbles = z;
    }

    public void setCapture(boolean z) {
        this.capture = z;
    }

    public void setListenerActor(Actor actor0) {
        this.listenerActor = actor0;
    }

    public void setStage(Stage stage0) {
        this.stage = stage0;
    }

    public void setTarget(Actor actor0) {
        this.targetActor = actor0;
    }

    public void stop() {
        this.stopped = true;
    }
}

