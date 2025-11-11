package com.esotericsoftware.gloomhavenhelper.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.esotericsoftware.gloomhavenhelper.App;

public abstract class DragAdjust extends DragListener {
    private long dragStartTime;
    private boolean dragging;
    public int extra;
    protected final Label label;
    public int min;
    public boolean onlyHorizontal;
    public int start;

    public DragAdjust(Label label0, Actor[] arr_actor) {
        this.label = label0;
        this.setTapSquareSize(33.0f);
        for(int v = 0; v < arr_actor.length; ++v) {
            arr_actor[v].addListener(this);
        }
    }

    public void adjust(int v) {
        this.start = this.getValue();
        this.extra = 0;
        int v1 = this.getMax();
        int v2 = this.start;
        if(v2 + v > v1) {
            v = v1 - v2;
        }
        int v3 = this.start;
        int v4 = this.min;
        if(v3 + v < v4) {
            v = v4 - v3;
        }
        switch(v) {
            case -4: {
                return;
            }
            case 0: {
                return;
            }
            default: {
                this.setValue(this.start + v);
                int v5 = v + this.extra;
                Label label0 = this.label;
                if(label0 != null) {
                    label0.setText((v5 < 0 ? "" : "+") + v5);
                }
            }
        }
    }

    protected int amount(float f, float f1) {
        if(Math.abs(f) <= Math.abs(f1)) {
            f = f1;
        }
        return Math.round(f / 50.0f);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragListener
    public void drag(InputEvent inputEvent0, float f, float f1, int v) {
        if(!this.dragging) {
            if(System.currentTimeMillis() - this.dragStartTime < 0xAFL) {
                return;
            }
            this.dragging = true;
        }
        int v1 = this.amount(this.getDragX() - this.getDragStartX(), this.getDragY() - this.getDragStartY());
        int v2 = this.getMax();
        int v3 = this.start;
        if(v3 + v1 > v2) {
            v1 = v2 - v3;
        }
        int v4 = this.start;
        int v5 = this.min;
        if(v4 + v1 < v5) {
            v1 = v5 - v4;
        }
        this.setValue(this.start + v1);
        int v6 = v1 + this.extra;
        Label label0 = this.label;
        if(label0 != null) {
            label0.setText((v6 < 0 ? "" : "+") + v6);
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragListener
    public void dragStart(InputEvent inputEvent0, float f, float f1, int v) {
        this.dragStartTime = System.currentTimeMillis();
        App.stage.cancelTouchFocus(App.gloom.rowsScroll);
        this.start = this.getValue();
        Label label0 = this.label;
        int v1 = 0;
        if(label0 == null) {
            this.extra = 0;
            return;
        }
        if(label0.getText().length != 0) {
            v1 = App.parseInt("");
        }
        this.extra = v1;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragListener
    public void dragStop(InputEvent inputEvent0, float f, float f1, int v) {
        this.dragging = false;
    }

    protected int getMax() {
        return 0x7FFFFFFF;
    }

    protected abstract int getValue();

    protected abstract void setValue(int arg1);

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragListener
    public void touchDragged(InputEvent inputEvent0, float f, float f1, int v) {
        if(this.onlyHorizontal) {
            f1 = this.getTouchDownY();
        }
        super.touchDragged(inputEvent0, f, f1, v);
    }
}

