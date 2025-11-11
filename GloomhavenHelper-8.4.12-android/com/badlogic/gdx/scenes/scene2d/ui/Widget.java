package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;

public class Widget extends Actor implements Layout {
    private boolean fillParent;
    private boolean layoutEnabled;
    private boolean needsLayout;

    public Widget() {
        this.needsLayout = true;
        this.layoutEnabled = true;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch0, float f) {
        this.validate();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMaxHeight() {
        return 0.0f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMaxWidth() {
        return 0.0f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinHeight() {
        return this.getPrefHeight();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinWidth() {
        return this.getPrefWidth();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        return 0.0f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        return 0.0f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void invalidate() {
        this.needsLayout = true;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void invalidateHierarchy() {
        if(!this.layoutEnabled) {
            return;
        }
        this.invalidate();
        Group group0 = this.getParent();
        if(group0 instanceof Layout) {
            ((Layout)group0).invalidateHierarchy();
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
    }

    public boolean needsLayout() {
        return this.needsLayout;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void pack() {
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
        this.validate();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void setFillParent(boolean z) {
        this.fillParent = z;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void setLayoutEnabled(boolean z) {
        this.layoutEnabled = z;
        if(z) {
            this.invalidateHierarchy();
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    protected void sizeChanged() {
        this.invalidate();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void validate() {
        float f1;
        float f;
        if(!this.layoutEnabled) {
            return;
        }
        Group group0 = this.getParent();
        if(this.fillParent && group0 != null) {
            Stage stage0 = this.getStage();
            if(stage0 == null || group0 != stage0.getRoot()) {
                float f2 = group0.getWidth();
                f1 = group0.getHeight();
                f = f2;
            }
            else {
                f = stage0.getWidth();
                f1 = stage0.getHeight();
            }
            this.setSize(f, f1);
        }
        if(!this.needsLayout) {
            return;
        }
        this.needsLayout = false;
        this.layout();
    }
}

