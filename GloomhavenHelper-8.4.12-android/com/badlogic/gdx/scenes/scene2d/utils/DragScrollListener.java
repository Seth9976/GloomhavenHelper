package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.Timer;

public class DragScrollListener extends DragListener {
    Interpolation interpolation;
    float maxSpeed;
    float minSpeed;
    float padBottom;
    float padTop;
    long rampTime;
    private ScrollPane scroll;
    private Task scrollDown;
    private Task scrollUp;
    long startTime;
    float tickSecs;
    static final Vector2 tmpCoords;

    static {
        DragScrollListener.tmpCoords = new Vector2();
    }

    public DragScrollListener(ScrollPane scrollPane0) {
        this.interpolation = Interpolation.exp5In;
        this.minSpeed = 15.0f;
        this.maxSpeed = 75.0f;
        this.tickSecs = 0.05f;
        this.rampTime = 1750L;
        this.scroll = scrollPane0;
        this.scrollUp = new Task() {
            @Override  // com.badlogic.gdx.utils.Timer$Task
            public void run() {
                float f = scrollPane0.getScrollY();
                float f1 = DragScrollListener.this.getScrollPixels();
                DragScrollListener.this.scroll(f - f1);
            }
        };
        this.scrollDown = new Task() {
            @Override  // com.badlogic.gdx.utils.Timer$Task
            public void run() {
                float f = DragScrollListener.this.getScrollPixels();
                DragScrollListener.this.scroll(scrollPane0.getScrollY() + f);
            }
        };
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragListener
    public void drag(InputEvent inputEvent0, float f, float f1, int v) {
        inputEvent0.getListenerActor().localToActorCoordinates(this.scroll, DragScrollListener.tmpCoords.set(f, f1));
        if(this.isAbove(DragScrollListener.tmpCoords.y)) {
            this.scrollDown.cancel();
            if(!this.scrollUp.isScheduled()) {
                this.startTime = System.currentTimeMillis();
                Timer.schedule(this.scrollUp, this.tickSecs, this.tickSecs);
            }
            return;
        }
        if(this.isBelow(DragScrollListener.tmpCoords.y)) {
            this.scrollUp.cancel();
            if(!this.scrollDown.isScheduled()) {
                this.startTime = System.currentTimeMillis();
                Timer.schedule(this.scrollDown, this.tickSecs, this.tickSecs);
            }
            return;
        }
        this.scrollUp.cancel();
        this.scrollDown.cancel();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragListener
    public void dragStop(InputEvent inputEvent0, float f, float f1, int v) {
        this.scrollUp.cancel();
        this.scrollDown.cancel();
    }

    float getScrollPixels() {
        return this.interpolation.apply(this.minSpeed, this.maxSpeed, Math.min(1.0f, ((float)(System.currentTimeMillis() - this.startTime)) / ((float)this.rampTime)));
    }

    protected boolean isAbove(float f) {
        return f >= this.scroll.getHeight() - this.padTop;
    }

    protected boolean isBelow(float f) {
        return f < this.padBottom;
    }

    protected void scroll(float f) {
        this.scroll.setScrollY(f);
    }

    public void setPadding(float f, float f1) {
        this.padTop = f;
        this.padBottom = f1;
    }

    public void setup(float f, float f1, float f2, float f3) {
        this.minSpeed = f;
        this.maxSpeed = f1;
        this.tickSecs = f2;
        this.rampTime = (long)(f3 * 1000.0f);
    }
}

