package com.badlogic.gdx;

public interface InputProcessor {
    boolean keyDown(int arg1);

    boolean keyTyped(char arg1);

    boolean keyUp(int arg1);

    boolean mouseMoved(int arg1, int arg2);

    boolean scrolled(float arg1, float arg2);

    boolean touchDown(int arg1, int arg2, int arg3, int arg4);

    boolean touchDragged(int arg1, int arg2, int arg3);

    boolean touchUp(int arg1, int arg2, int arg3, int arg4);
}

