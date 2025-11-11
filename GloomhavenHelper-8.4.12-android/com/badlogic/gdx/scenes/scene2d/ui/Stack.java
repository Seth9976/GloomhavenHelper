package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.SnapshotArray;

public class Stack extends WidgetGroup {
    private float maxHeight;
    private float maxWidth;
    private float minHeight;
    private float minWidth;
    private float prefHeight;
    private float prefWidth;
    private boolean sizeInvalid;

    public Stack() {
        this.sizeInvalid = true;
        this.setTransform(false);
        this.setWidth(150.0f);
        this.setHeight(150.0f);
        this.setTouchable(Touchable.childrenOnly);
    }

    public Stack(Actor[] arr_actor) {
        for(int v = 0; v < arr_actor.length; ++v) {
            this.addActor(arr_actor[v]);
        }
    }

    public void add(Actor actor0) {
        this.addActor(actor0);
    }

    private void computeSize() {
        float f1;
        float f;
        this.sizeInvalid = false;
        this.prefWidth = 0.0f;
        this.prefHeight = 0.0f;
        this.minWidth = 0.0f;
        this.minHeight = 0.0f;
        this.maxWidth = 0.0f;
        this.maxHeight = 0.0f;
        SnapshotArray snapshotArray0 = this.getChildren();
        int v1 = snapshotArray0.size;
        for(int v = 0; v < v1; ++v) {
            Actor actor0 = (Actor)snapshotArray0.get(v);
            if(actor0 instanceof Layout) {
                this.prefWidth = Math.max(this.prefWidth, ((Layout)actor0).getPrefWidth());
                this.prefHeight = Math.max(this.prefHeight, ((Layout)actor0).getPrefHeight());
                this.minWidth = Math.max(this.minWidth, ((Layout)actor0).getMinWidth());
                this.minHeight = Math.max(this.minHeight, ((Layout)actor0).getMinHeight());
                f = ((Layout)actor0).getMaxWidth();
                f1 = ((Layout)actor0).getMaxHeight();
            }
            else {
                this.prefWidth = Math.max(this.prefWidth, actor0.getWidth());
                this.prefHeight = Math.max(this.prefHeight, actor0.getHeight());
                this.minWidth = Math.max(this.minWidth, actor0.getWidth());
                this.minHeight = Math.max(this.minHeight, actor0.getHeight());
                f1 = 0.0f;
                f = 0.0f;
            }
            if(f > 0.0f) {
                float f2 = this.maxWidth;
                if(f2 != 0.0f) {
                    f = Math.min(f2, f);
                }
                this.maxWidth = f;
            }
            if(f1 > 0.0f) {
                float f3 = this.maxHeight;
                if(f3 != 0.0f) {
                    f1 = Math.min(f3, f1);
                }
                this.maxHeight = f1;
            }
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getMaxHeight() {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.maxHeight;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getMaxWidth() {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.maxWidth;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getMinHeight() {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.minHeight;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getMinWidth() {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.minWidth;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getPrefHeight() {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.prefHeight;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getPrefWidth() {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.prefWidth;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public void invalidate() {
        super.invalidate();
        this.sizeInvalid = true;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public void layout() {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        float f = this.getWidth();
        float f1 = this.getHeight();
        SnapshotArray snapshotArray0 = this.getChildren();
        int v1 = snapshotArray0.size;
        for(int v = 0; v < v1; ++v) {
            Actor actor0 = (Actor)snapshotArray0.get(v);
            actor0.setBounds(0.0f, 0.0f, f, f1);
            if(actor0 instanceof Layout) {
                ((Layout)actor0).validate();
            }
        }
    }
}

