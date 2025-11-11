package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Null;

public class Tooltip extends InputListener {
    boolean always;
    final Container container;
    boolean instant;
    private final TooltipManager manager;
    Actor targetActor;
    static Vector2 tmp;

    static {
        Tooltip.tmp = new Vector2();
    }

    public Tooltip(@Null Actor actor0) {
        this(actor0, TooltipManager.getInstance());
    }

    public Tooltip(@Null Actor actor0, TooltipManager tooltipManager0) {
        this.manager = tooltipManager0;
        this.container = new Container(actor0) {
            @Override  // com.badlogic.gdx.scenes.scene2d.Group
            public void act(float f) {
                super.act(f);
                if(Tooltip.this.targetActor != null && Tooltip.this.targetActor.getStage() == null) {
                    this.remove();
                }
            }
        };
        this.container.setTouchable(Touchable.disabled);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
    public void enter(InputEvent inputEvent0, float f, float f1, int v, @Null Actor actor0) {
        if(v != -1) {
            return;
        }
        if(Gdx.input.isTouched()) {
            return;
        }
        Actor actor1 = inputEvent0.getListenerActor();
        if(actor0 != null && actor0.isDescendantOf(actor1)) {
            return;
        }
        this.setContainerPosition(actor1, f, f1);
        this.manager.enter(this);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
    public void exit(InputEvent inputEvent0, float f, float f1, int v, @Null Actor actor0) {
        if(actor0 != null && actor0.isDescendantOf(inputEvent0.getListenerActor())) {
            return;
        }
        this.hide();
    }

    @Null
    public Actor getActor() {
        return this.container.getActor();
    }

    public Container getContainer() {
        return this.container;
    }

    public TooltipManager getManager() {
        return this.manager;
    }

    public void hide() {
        this.manager.hide(this);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
    public boolean mouseMoved(InputEvent inputEvent0, float f, float f1) {
        if(this.container.hasParent()) {
            return false;
        }
        this.setContainerPosition(inputEvent0.getListenerActor(), f, f1);
        return true;
    }

    public void setActor(@Null Actor actor0) {
        this.container.setActor(actor0);
    }

    public void setAlways(boolean z) {
        this.always = z;
    }

    private void setContainerPosition(Actor actor0, float f, float f1) {
        this.targetActor = actor0;
        Stage stage0 = actor0.getStage();
        if(stage0 == null) {
            return;
        }
        this.container.pack();
        float f2 = this.manager.offsetY;
        float f3 = this.manager.edgeDistance;
        float f4 = f + this.manager.offsetX;
        Vector2 vector20 = actor0.localToStageCoordinates(Tooltip.tmp.set(f4, f1 - f2 - this.container.getHeight()));
        if(vector20.y < f3) {
            vector20 = actor0.localToStageCoordinates(Tooltip.tmp.set(f4, f1 + f2));
        }
        if(vector20.x < f3) {
            vector20.x = f3;
        }
        if(vector20.x + this.container.getWidth() > stage0.getWidth() - f3) {
            vector20.x = stage0.getWidth() - f3 - this.container.getWidth();
        }
        if(vector20.y + this.container.getHeight() > stage0.getHeight() - f3) {
            vector20.y = stage0.getHeight() - f3 - this.container.getHeight();
        }
        this.container.setPosition(vector20.x, vector20.y);
        Vector2 vector21 = actor0.localToStageCoordinates(Tooltip.tmp.set(actor0.getWidth() / 2.0f, actor0.getHeight() / 2.0f));
        vector21.sub(this.container.getX(), this.container.getY());
        this.container.setOrigin(vector21.x, vector21.y);
    }

    public void setInstant(boolean z) {
        this.instant = z;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
    public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
        if(this.instant) {
            this.container.toFront();
            return false;
        }
        this.manager.touchDown(this);
        return false;
    }
}

