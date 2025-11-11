package com.badlogic.gdx;

public interface ApplicationListener {
    void create();

    void dispose();

    void pause();

    void render();

    void resize(int arg1, int arg2);

    void resume();
}

