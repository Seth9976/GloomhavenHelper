package com.badlogic.gdx;

public abstract class Game implements ApplicationListener {
    protected Screen screen;

    @Override  // com.badlogic.gdx.ApplicationListener
    public void dispose() {
        Screen screen0 = this.screen;
        if(screen0 != null) {
            screen0.hide();
        }
    }

    public Screen getScreen() {
        return this.screen;
    }

    @Override  // com.badlogic.gdx.ApplicationListener
    public void pause() {
        Screen screen0 = this.screen;
        if(screen0 != null) {
            screen0.pause();
        }
    }

    @Override  // com.badlogic.gdx.ApplicationListener
    public void render() {
        Screen screen0 = this.screen;
        if(screen0 != null) {
            screen0.render(Gdx.graphics.getDeltaTime());
        }
    }

    @Override  // com.badlogic.gdx.ApplicationListener
    public void resize(int v, int v1) {
        Screen screen0 = this.screen;
        if(screen0 != null) {
            screen0.resize(v, v1);
        }
    }

    @Override  // com.badlogic.gdx.ApplicationListener
    public void resume() {
        Screen screen0 = this.screen;
        if(screen0 != null) {
            screen0.resume();
        }
    }

    public void setScreen(Screen screen0) {
        Screen screen1 = this.screen;
        if(screen1 != null) {
            screen1.hide();
        }
        this.screen = screen0;
        Screen screen2 = this.screen;
        if(screen2 != null) {
            screen2.show();
            this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }
}

