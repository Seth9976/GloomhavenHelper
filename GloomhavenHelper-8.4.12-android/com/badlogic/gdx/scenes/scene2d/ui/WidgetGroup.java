package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.SnapshotArray;

public class WidgetGroup extends Group implements Layout {
    private boolean fillParent;
    private boolean layoutEnabled;
    private boolean needsLayout;

    public WidgetGroup() {
        this.needsLayout = true;
        this.layoutEnabled = true;
    }

    public WidgetGroup(Actor[] arr_actor) {
        this.needsLayout = true;
        this.layoutEnabled = true;
        for(int v = 0; v < arr_actor.length; ++v) {
            this.addActor(arr_actor[v]);
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    protected void childrenChanged() {
        this.invalidateHierarchy();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public void draw(Batch batch0, float f) {
        this.validate();
        super.draw(batch0, f);
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
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
        this.validate();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void setFillParent(boolean z) {
        this.fillParent = z;
    }

    private void setLayoutEnabled(Group group0, boolean z) {
        SnapshotArray snapshotArray0 = group0.getChildren();
        int v = snapshotArray0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Actor actor0 = (Actor)snapshotArray0.get(v1);
            if(actor0 instanceof Layout) {
                ((Layout)actor0).setLayoutEnabled(z);
            }
            else if(actor0 instanceof Group) {
                this.setLayoutEnabled(((Group)actor0), z);
            }
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void setLayoutEnabled(boolean z) {
        this.layoutEnabled = z;
        this.setLayoutEnabled(this, z);
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
                f = group0.getWidth();
                f1 = group0.getHeight();
            }
            else {
                f = stage0.getWidth();
                f1 = stage0.getHeight();
            }
            if(this.getWidth() != f || this.getHeight() != f1) {
                this.setWidth(f);
                this.setHeight(f1);
                this.invalidate();
            }
        }
        if(!this.needsLayout) {
            return;
        }
        this.needsLayout = false;
        this.layout();
        if(!this.needsLayout || group0 instanceof WidgetGroup) {
            return;
        }
        for(int v = 0; v < 5; ++v) {
            this.needsLayout = false;
            this.layout();
            if(!this.needsLayout) {
                break;
            }
        }
    }
}

