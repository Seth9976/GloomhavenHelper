package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class DragListener extends InputListener {
    private int button;
    private float dragLastX;
    private float dragLastY;
    private float dragStartX;
    private float dragStartY;
    private float dragX;
    private float dragY;
    private boolean dragging;
    private int pressedPointer;
    private float stageTouchDownX;
    private float stageTouchDownY;
    private float tapSquareSize;
    private float touchDownX;
    private float touchDownY;

    public DragListener() {
        this.tapSquareSize = 14.0f;
        this.touchDownX = -1.0f;
        this.touchDownY = -1.0f;
        this.stageTouchDownX = -1.0f;
        this.stageTouchDownY = -1.0f;
        this.pressedPointer = -1;
    }

    public void cancel() {
        this.dragging = false;
        this.pressedPointer = -1;
    }

    public void drag(InputEvent inputEvent0, float f, float f1, int v) {
    }

    public void dragStart(InputEvent inputEvent0, float f, float f1, int v) {
    }

    public void dragStop(InputEvent inputEvent0, float f, float f1, int v) {
    }

    public int getButton() {
        return this.button;
    }

    public float getDeltaX() {
        return this.dragX - this.dragLastX;
    }

    public float getDeltaY() {
        return this.dragY - this.dragLastY;
    }

    public float getDragDistance() {
        return Vector2.len(this.dragX - this.dragStartX, this.dragY - this.dragStartY);
    }

    public float getDragStartX() {
        return this.dragStartX;
    }

    public float getDragStartY() {
        return this.dragStartY;
    }

    public float getDragX() {
        return this.dragX;
    }

    public float getDragY() {
        return this.dragY;
    }

    public float getStageTouchDownX() {
        return this.stageTouchDownX;
    }

    public float getStageTouchDownY() {
        return this.stageTouchDownY;
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

    public boolean isDragging() {
        return this.dragging;
    }

    public void setButton(int v) {
        this.button = v;
    }

    public void setDragStartX(float f) {
        this.dragStartX = f;
    }

    public void setDragStartY(float f) {
        this.dragStartY = f;
    }

    public void setTapSquareSize(float f) {
        this.tapSquareSize = f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
    public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
        if(this.pressedPointer != -1) {
            return false;
        }
        if(v == 0 && (this.button != -1 && v1 != this.button)) {
            return false;
        }
        this.pressedPointer = v;
        this.touchDownX = f;
        this.touchDownY = f1;
        this.stageTouchDownX = inputEvent0.getStageX();
        this.stageTouchDownY = inputEvent0.getStageY();
        return true;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
    public void touchDragged(InputEvent inputEvent0, float f, float f1, int v) {
        if(v != this.pressedPointer) {
            return;
        }
        if(!this.dragging && (Math.abs(this.touchDownX - f) > this.tapSquareSize || Math.abs(this.touchDownY - f1) > this.tapSquareSize)) {
            this.dragging = true;
            this.dragStartX = f;
            this.dragStartY = f1;
            this.dragStart(inputEvent0, f, f1, v);
            this.dragX = f;
            this.dragY = f1;
        }
        if(this.dragging) {
            this.dragLastX = this.dragX;
            this.dragLastY = this.dragY;
            this.dragX = f;
            this.dragY = f1;
            this.drag(inputEvent0, f, f1, v);
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
    public void touchUp(InputEvent inputEvent0, float f, float f1, int v, int v1) {
        if(v == this.pressedPointer) {
            if(this.dragging) {
                this.dragStop(inputEvent0, f, f1, v);
            }
            this.cancel();
        }
    }
}

