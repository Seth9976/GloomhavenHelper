package com.esotericsoftware.gloomhavenhelper.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.esotericsoftware.gloomhavenhelper.App;

public abstract class HPAdjust extends DragAdjust {
    public Container changeContainer;
    private Actor listenerActor;
    public float minX;

    public HPAdjust(Actor actor0, String s) {
        super(new Label("", App.skin, s, Color.WHITE), new Actor[]{actor0});
        this.minX = -2147483648.0f;
        this.listenerActor = actor0;
        this.onlyHorizontal = true;
        this.changeContainer = new Container(this.label) {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Container
            public void draw(Batch batch0, float f) {
                if(this.getColor().a == 1.0f) {
                    HPAdjust.this.getPosition(App.v2);
                    this.setPosition(App.v2.x, App.v2.y);
                    this.pack();
                }
                super.draw(batch0, f);
            }
        };
        this.changeContainer.background(App.drawable("rounded", new Color(0.0f, 0.0f, 0.0f, 0.9f)));
        this.changeContainer.pad(-7.0f, 11.0f, 5.0f, 11.0f);
        this.changeContainer.setTouchable(Touchable.disabled);
        this.changeContainer.setVisible(false);
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
    public void adjust(int v) {
        this.apply();
        this.reset();
        super.adjust(v);
        this.hideLabel();
    }

    protected void apply() {
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
    public void dragStart(InputEvent inputEvent0, float f, float f1, int v) {
        if(!App.config.hpDrag) {
            this.cancel();
            return;
        }
        if(!this.label.getParent().isVisible()) {
            this.label.setText("");
        }
        this.reset();
        super.dragStart(inputEvent0, f, f1, v);
        App.stage.cancelTouchFocusExcept(this, this.listenerActor);
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
    public void dragStop(InputEvent inputEvent0, float f, float f1, int v) {
        super.dragStop(inputEvent0, f, f1, v);
        this.hideLabel();
    }

    protected abstract void getPosition(Vector2 arg1);

    void hideLabel() {
        Group group0 = this.label.getParent();
        if(!group0.isVisible()) {
            return;
        }
        group0.clearActions();
        group0.addAction(Actions.sequence(Actions.delay(2.0f), new Action() {
            @Override  // com.badlogic.gdx.scenes.scene2d.Action
            public boolean act(float f) {
                group0.getColor().a = 0.99f;
                HPAdjust.this.apply();
                SequenceAction sequenceAction0 = Actions.sequence(Actions.parallel(Actions.sequence(Actions.delay(0.2f), Actions.fadeOut(0.6f, Interpolation.slowFast)), Actions.moveTo(group0.getX(), group0.getY() + 50.0f, 1.0f, Interpolation.fastSlow)), Actions.hide());
                group0.addAction(sequenceAction0);
                return true;
            }
        }));
    }

    private void reset() {
        this.label.getParent().clearActions();
        this.label.getParent().getColor().a = 1.0f;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
    protected void setValue(int v) {
        this.label.setColor((v + this.extra >= this.start ? App.healthGreen : App.healthRed));
        this.reset();
        this.label.getParent().setVisible(true);
        this.label.getParent().toFront();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragListener
    public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
        if(f <= this.minX) {
            return false;
        }
        this.reset();
        return super.touchDown(inputEvent0, f, f1, v, v1);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragListener
    public void touchUp(InputEvent inputEvent0, float f, float f1, int v, int v1) {
        super.touchUp(inputEvent0, f, f1, v, v1);
        this.dragStop(inputEvent0, f, f1, v);
    }
}

