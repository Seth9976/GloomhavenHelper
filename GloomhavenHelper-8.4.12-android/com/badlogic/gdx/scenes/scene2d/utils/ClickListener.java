package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Null;

public class ClickListener extends InputListener {
    private int button;
    private boolean cancelled;
    private long lastTapTime;
    private boolean over;
    private boolean pressed;
    private int pressedButton;
    private int pressedPointer;
    private int tapCount;
    private long tapCountInterval;
    private float tapSquareSize;
    private float touchDownX;
    private float touchDownY;
    public static float visualPressedDuration = 0.1f;
    private long visualPressedTime;

    static {
    }

    public ClickListener() {
        this.tapSquareSize = 14.0f;
        this.touchDownX = -1.0f;
        this.touchDownY = -1.0f;
        this.pressedPointer = -1;
        this.pressedButton = -1;
        this.tapCountInterval = 400000000L;
    }

    public ClickListener(int v) {
        this.tapSquareSize = 14.0f;
        this.touchDownX = -1.0f;
        this.touchDownY = -1.0f;
        this.pressedPointer = -1;
        this.pressedButton = -1;
        this.tapCountInterval = 400000000L;
        this.button = v;
    }

    public void cancel() {
        if(this.pressedPointer == -1) {
            return;
        }
        this.cancelled = true;
        this.pressed = false;
    }

    public void clicked(InputEvent inputEvent0, float f, float f1) {
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
    public void enter(InputEvent inputEvent0, float f, float f1, int v, @Null Actor actor0) {
        if(v == -1 && !this.cancelled) {
            this.over = true;
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
    public void exit(InputEvent inputEvent0, float f, float f1, int v, @Null Actor actor0) {
        if(v == -1 && !this.cancelled) {
            this.over = false;
        }
    }

    public int getButton() {
        return this.button;
    }

    public int getPressedButton() {
        return this.pressedButton;
    }

    public int getPressedPointer() {
        return this.pressedPointer;
    }

    public int getTapCount() {
        return this.tapCount;
    }

    public float getTapSquareSize() {
        return this.tapSquareSize;
    }

    public float getTouchDownX() {
        return this.touchDownX;
    }

    public float getTouchDownY() {
        return this.touchDownY;
    }

    public boolean inTapSquare() {
        return this.touchDownX != -1.0f;
    }

    public boolean inTapSquare(float f, float f1) {
        return this.touchDownX != -1.0f || this.touchDownY != -1.0f ? Math.abs(f - this.touchDownX) < this.tapSquareSize && Math.abs(f1 - this.touchDownY) < this.tapSquareSize : false;
    }

    public void invalidateTapSquare() {
        this.touchDownX = -1.0f;
        this.touchDownY = -1.0f;
    }

    // 去混淆评级： 低(20)
    public boolean isOver() {
        return this.over || this.pressed;
    }

    public boolean isOver(Actor actor0, float f, float f1) {
        Actor actor1 = actor0.hit(f, f1, true);
        return actor1 == null || !actor1.isDescendantOf(actor0) ? this.inTapSquare(f, f1) : true;
    }

    public boolean isPressed() {
        return this.pressed;
    }

    public boolean isVisualPressed() {
        if(this.pressed) {
            return true;
        }
        long v = this.visualPressedTime;
        if(v <= 0L) {
            return false;
        }
        if(v > 0x19A725B1F6FL) {
            return true;
        }
        this.visualPressedTime = 0L;
        return false;
    }

    public void setButton(int v) {
        this.button = v;
    }

    public void setTapCount(int v) {
        this.tapCount = v;
    }

    public void setTapCountInterval(float f) {
        this.tapCountInterval = (long)(f * 1000000000.0f);
    }

    public void setTapSquareSize(float f) {
        this.tapSquareSize = f;
    }

    public void setVisualPressed(boolean z) {
        if(z) {
            this.visualPressedTime = 0x19A725B1F8EL + ((long)(ClickListener.visualPressedDuration * 1000.0f));
            return;
        }
        this.visualPressedTime = 0L;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
    public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
        if(this.pressed) {
            return false;
        }
        if(v == 0 && (this.button != -1 && v1 != this.button)) {
            return false;
        }
        this.pressed = true;
        this.pressedPointer = v;
        this.pressedButton = v1;
        this.touchDownX = f;
        this.touchDownY = f1;
        this.setVisualPressed(true);
        return true;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
    public void touchDragged(InputEvent inputEvent0, float f, float f1, int v) {
        if(v == this.pressedPointer && !this.cancelled) {
            this.pressed = this.isOver(inputEvent0.getListenerActor(), f, f1);
            if(!this.pressed) {
                this.invalidateTapSquare();
            }
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
    public void touchUp(InputEvent inputEvent0, float f, float f1, int v, int v1) {
        if(v == this.pressedPointer) {
            if(!this.cancelled) {
                boolean z = this.isOver(inputEvent0.getListenerActor(), f, f1);
                if(z && v == 0 && (this.button != -1 && v1 != this.button)) {
                    z = false;
                }
                if(z) {
                    if(37204640143000L - this.lastTapTime > this.tapCountInterval) {
                        this.tapCount = 0;
                    }
                    ++this.tapCount;
                    this.lastTapTime = 37204640143000L;
                    this.clicked(inputEvent0, f, f1);
                }
            }
            this.pressed = false;
            this.pressedPointer = -1;
            this.pressedButton = -1;
            this.cancelled = false;
        }
    }
}

