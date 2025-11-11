package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Null;

public class InputEvent extends Event {
    public static enum Type {
        touchDown,
        touchUp,
        touchDragged,
        mouseMoved,
        enter,
        exit,
        scrolled,
        keyDown,
        keyUp,
        keyTyped;

    }

    private int button;
    private char character;
    private int keyCode;
    private int pointer;
    @Null
    private Actor relatedActor;
    private float scrollAmountX;
    private float scrollAmountY;
    private float stageX;
    private float stageY;
    private boolean touchFocus;
    private Type type;

    public InputEvent() {
        this.touchFocus = true;
    }

    public int getButton() {
        return this.button;
    }

    public char getCharacter() {
        return this.character;
    }

    public int getKeyCode() {
        return this.keyCode;
    }

    public int getPointer() {
        return this.pointer;
    }

    @Null
    public Actor getRelatedActor() {
        return this.relatedActor;
    }

    public float getScrollAmountX() {
        return this.scrollAmountX;
    }

    public float getScrollAmountY() {
        return this.scrollAmountY;
    }

    public float getStageX() {
        return this.stageX;
    }

    public float getStageY() {
        return this.stageY;
    }

    public boolean getTouchFocus() {
        return this.touchFocus;
    }

    public Type getType() {
        return this.type;
    }

    public boolean isTouchFocusCancel() {
        return this.stageX == -2147483648.0f || this.stageY == -2147483648.0f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Event
    public void reset() {
        super.reset();
        this.relatedActor = null;
        this.button = -1;
    }

    public void setButton(int v) {
        this.button = v;
    }

    public void setCharacter(char c) {
        this.character = c;
    }

    public void setKeyCode(int v) {
        this.keyCode = v;
    }

    public void setPointer(int v) {
        this.pointer = v;
    }

    public void setRelatedActor(@Null Actor actor0) {
        this.relatedActor = actor0;
    }

    public void setScrollAmountX(float f) {
        this.scrollAmountX = f;
    }

    public void setScrollAmountY(float f) {
        this.scrollAmountY = f;
    }

    public void setStageX(float f) {
        this.stageX = f;
    }

    public void setStageY(float f) {
        this.stageY = f;
    }

    public void setTouchFocus(boolean z) {
        this.touchFocus = z;
    }

    public void setType(Type inputEvent$Type0) {
        this.type = inputEvent$Type0;
    }

    public Vector2 toCoordinates(Actor actor0, Vector2 vector20) {
        vector20.set(this.stageX, this.stageY);
        actor0.stageToLocalCoordinates(vector20);
        return vector20;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }
}

