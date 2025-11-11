package com.badlogic.gdx;

public interface Screen {
    void dispose();

    void hide();

    void pause();

    void render(float arg1);

    void resize(int arg1, int arg2);

    void resume();

    void show();
}

