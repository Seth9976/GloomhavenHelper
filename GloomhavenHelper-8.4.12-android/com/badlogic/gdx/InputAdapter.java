package com.badlogic.gdx;

public class InputAdapter implements InputProcessor {
    @Override  // com.badlogic.gdx.InputProcessor
    public boolean keyDown(int v) {
        return false;
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean keyTyped(char c) {
        return false;
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean keyUp(int v) {
        return false;
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean mouseMoved(int v, int v1) {
        return false;
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean scrolled(float f, float f1) {
        return false;
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean touchDown(int v, int v1, int v2, int v3) {
        return false;
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean touchDragged(int v, int v1, int v2) {
        return false;
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean touchUp(int v, int v1, int v2, int v3) {
        return false;
    }
}

