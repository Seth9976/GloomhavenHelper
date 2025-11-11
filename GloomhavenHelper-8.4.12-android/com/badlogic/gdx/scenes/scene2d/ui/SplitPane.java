package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Null;

public class SplitPane extends WidgetGroup {
    public static class SplitPaneStyle {
        public Drawable handle;

        public SplitPaneStyle() {
        }

        public SplitPaneStyle(SplitPaneStyle splitPane$SplitPaneStyle0) {
            this.handle = splitPane$SplitPaneStyle0.handle;
        }

        public SplitPaneStyle(Drawable drawable0) {
            this.handle = drawable0;
        }
    }

    boolean cursorOverHandle;
    @Null
    private Actor firstWidget;
    private final Rectangle firstWidgetBounds;
    final Rectangle handleBounds;
    Vector2 handlePosition;
    Vector2 lastPoint;
    float maxAmount;
    float minAmount;
    @Null
    private Actor secondWidget;
    private final Rectangle secondWidgetBounds;
    float splitAmount;
    SplitPaneStyle style;
    private final Rectangle tempScissors;
    boolean vertical;

    public SplitPane(@Null Actor actor0, @Null Actor actor1, boolean z, Skin skin0) {
        this(actor0, actor1, z, skin0, "default-" + (z ? "vertical" : "horizontal"));
    }

    public SplitPane(@Null Actor actor0, @Null Actor actor1, boolean z, Skin skin0, String s) {
        this(actor0, actor1, z, ((SplitPaneStyle)skin0.get(s, SplitPaneStyle.class)));
    }

    public SplitPane(@Null Actor actor0, @Null Actor actor1, boolean z, SplitPaneStyle splitPane$SplitPaneStyle0) {
        this.splitAmount = 0.5f;
        this.maxAmount = 1.0f;
        this.firstWidgetBounds = new Rectangle();
        this.secondWidgetBounds = new Rectangle();
        this.handleBounds = new Rectangle();
        this.tempScissors = new Rectangle();
        this.lastPoint = new Vector2();
        this.handlePosition = new Vector2();
        this.vertical = z;
        this.setStyle(splitPane$SplitPaneStyle0);
        this.setFirstWidget(actor0);
        this.setSecondWidget(actor1);
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
        this.initialize();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public void addActor(Actor actor0) {
        throw new UnsupportedOperationException("Use SplitPane#setWidget.");
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public void addActorAt(int v, Actor actor0) {
        throw new UnsupportedOperationException("Use SplitPane#setWidget.");
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public void addActorBefore(Actor actor0, Actor actor1) {
        throw new UnsupportedOperationException("Use SplitPane#setWidget.");
    }

    private void calculateHorizBoundsAndPositions() {
        Drawable drawable0 = this.style.handle;
        float f = this.getHeight();
        float f1 = this.getWidth() - drawable0.getMinWidth();
        float f2 = (float)(((int)(this.splitAmount * f1)));
        float f3 = drawable0.getMinWidth();
        this.firstWidgetBounds.set(0.0f, 0.0f, f2, f);
        this.secondWidgetBounds.set(f2 + f3, 0.0f, f1 - f2, f);
        this.handleBounds.set(f2, 0.0f, f3, f);
    }

    private void calculateVertBoundsAndPositions() {
        Drawable drawable0 = this.style.handle;
        float f = this.getWidth();
        float f1 = this.getHeight();
        float f2 = f1 - drawable0.getMinHeight();
        float f3 = (float)(((int)(this.splitAmount * f2)));
        float f4 = f2 - f3;
        float f5 = drawable0.getMinHeight();
        this.firstWidgetBounds.set(0.0f, f1 - f3, f, f3);
        this.secondWidgetBounds.set(0.0f, 0.0f, f, f4);
        this.handleBounds.set(0.0f, f4, f, f5);
    }

    protected void clampSplitAmount() {
        float f = this.minAmount;
        float f1 = this.maxAmount;
        if(this.vertical) {
            float f2 = this.getHeight() - this.style.handle.getMinHeight();
            Actor actor0 = this.firstWidget;
            if(actor0 instanceof Layout) {
                f = Math.max(f, Math.min(((Layout)actor0).getMinHeight() / f2, 1.0f));
            }
            Actor actor1 = this.secondWidget;
            if(actor1 instanceof Layout) {
                f1 = Math.min(f1, 1.0f - Math.min(((Layout)actor1).getMinHeight() / f2, 1.0f));
            }
        }
        else {
            float f3 = this.getWidth() - this.style.handle.getMinWidth();
            Actor actor2 = this.firstWidget;
            if(actor2 instanceof Layout) {
                f = Math.max(f, Math.min(((Layout)actor2).getMinWidth() / f3, 1.0f));
            }
            Actor actor3 = this.secondWidget;
            if(actor3 instanceof Layout) {
                f1 = Math.min(f1, 1.0f - Math.min(((Layout)actor3).getMinWidth() / f3, 1.0f));
            }
        }
        if(f > f1) {
            this.splitAmount = (f + f1) * 0.5f;
            return;
        }
        this.splitAmount = Math.max(Math.min(this.splitAmount, f1), f);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public void draw(Batch batch0, float f) {
        Stage stage0 = this.getStage();
        if(stage0 == null) {
            return;
        }
        this.validate();
        Color color0 = this.getColor();
        float f1 = color0.a * f;
        this.applyTransform(batch0, this.computeTransform());
        if(this.firstWidget != null && this.firstWidget.isVisible()) {
            batch0.flush();
            stage0.calculateScissors(this.firstWidgetBounds, this.tempScissors);
            if(ScissorStack.pushScissors(this.tempScissors)) {
                this.firstWidget.draw(batch0, f1);
                batch0.flush();
                ScissorStack.popScissors();
            }
        }
        if(this.secondWidget != null && this.secondWidget.isVisible()) {
            batch0.flush();
            stage0.calculateScissors(this.secondWidgetBounds, this.tempScissors);
            if(ScissorStack.pushScissors(this.tempScissors)) {
                this.secondWidget.draw(batch0, f1);
                batch0.flush();
                ScissorStack.popScissors();
            }
        }
        batch0.setColor(color0.r, color0.g, color0.b, f1);
        this.style.handle.draw(batch0, this.handleBounds.x, this.handleBounds.y, this.handleBounds.width, this.handleBounds.height);
        this.resetTransform(batch0);
    }

    public float getMaxSplitAmount() {
        return this.maxAmount;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getMinHeight() {
        float f = 0.0f;
        float f1 = this.firstWidget instanceof Layout ? ((Layout)this.firstWidget).getMinHeight() : 0.0f;
        Actor actor0 = this.secondWidget;
        if(actor0 instanceof Layout) {
            f = ((Layout)actor0).getMinHeight();
        }
        return this.vertical ? f1 + this.style.handle.getMinHeight() + f : Math.max(f1, f);
    }

    public float getMinSplitAmount() {
        return this.minAmount;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getMinWidth() {
        float f = 0.0f;
        float f1 = this.firstWidget instanceof Layout ? ((Layout)this.firstWidget).getMinWidth() : 0.0f;
        Actor actor0 = this.secondWidget;
        if(actor0 instanceof Layout) {
            f = ((Layout)actor0).getMinWidth();
        }
        return this.vertical ? Math.max(f1, f) : f1 + this.style.handle.getMinWidth() + f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getPrefHeight() {
        float f1;
        Actor actor0 = this.firstWidget;
        float f = 0.0f;
        if(actor0 == null) {
            f1 = 0.0f;
        }
        else {
            f1 = actor0 instanceof Layout ? ((Layout)actor0).getPrefHeight() : actor0.getHeight();
        }
        Actor actor1 = this.secondWidget;
        if(actor1 != null) {
            f = actor1 instanceof Layout ? ((Layout)actor1).getPrefHeight() : actor1.getHeight();
        }
        return this.vertical ? f1 + this.style.handle.getMinHeight() + f : Math.max(f1, f);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getPrefWidth() {
        float f1;
        Actor actor0 = this.firstWidget;
        float f = 0.0f;
        if(actor0 == null) {
            f1 = 0.0f;
        }
        else {
            f1 = actor0 instanceof Layout ? ((Layout)actor0).getPrefWidth() : actor0.getWidth();
        }
        Actor actor1 = this.secondWidget;
        if(actor1 != null) {
            f = actor1 instanceof Layout ? ((Layout)actor1).getPrefWidth() : actor1.getWidth();
        }
        return this.vertical ? Math.max(f1, f) : f1 + this.style.handle.getMinWidth() + f;
    }

    public float getSplitAmount() {
        return this.splitAmount;
    }

    public SplitPaneStyle getStyle() {
        return this.style;
    }

    private void initialize() {
        this.addListener(new InputListener() {
            int draggingPointer;

            {
                this.draggingPointer = -1;
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean mouseMoved(InputEvent inputEvent0, float f, float f1) {
                SplitPane.this.cursorOverHandle = SplitPane.this.handleBounds.contains(f, f1);
                return false;
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                if(this.draggingPointer != -1) {
                    return false;
                }
                if(v == 0 && v1 != 0) {
                    return false;
                }
                if(SplitPane.this.handleBounds.contains(f, f1)) {
                    this.draggingPointer = v;
                    SplitPane.this.lastPoint.set(f, f1);
                    SplitPane.this.handlePosition.set(SplitPane.this.handleBounds.x, SplitPane.this.handleBounds.y);
                    return true;
                }
                return false;
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent0, float f, float f1, int v) {
                if(v != this.draggingPointer) {
                    return;
                }
                Drawable drawable0 = SplitPane.this.style.handle;
                if(SplitPane.this.vertical) {
                    float f5 = f1 - SplitPane.this.lastPoint.y;
                    float f6 = SplitPane.this.getHeight() - drawable0.getMinHeight();
                    float f7 = SplitPane.this.handlePosition.y + f5;
                    SplitPane.this.handlePosition.y = f7;
                    SplitPane.this.splitAmount = 1.0f - Math.min(f6, Math.max(0.0f, f7)) / f6;
                }
                else {
                    float f2 = f - SplitPane.this.lastPoint.x;
                    float f3 = SplitPane.this.getWidth() - drawable0.getMinWidth();
                    float f4 = SplitPane.this.handlePosition.x + f2;
                    SplitPane.this.handlePosition.x = f4;
                    SplitPane.this.splitAmount = Math.min(f3, Math.max(0.0f, f4)) / f3;
                }
                SplitPane.this.lastPoint.set(f, f1);
                SplitPane.this.invalidate();
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchUp(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                if(v == this.draggingPointer) {
                    this.draggingPointer = -1;
                }
            }
        });
    }

    public boolean isCursorOverHandle() {
        return this.cursorOverHandle;
    }

    public boolean isVertical() {
        return this.vertical;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public void layout() {
        this.clampSplitAmount();
        if(this.vertical) {
            this.calculateVertBoundsAndPositions();
        }
        else {
            this.calculateHorizBoundsAndPositions();
        }
        Actor actor0 = this.firstWidget;
        if(actor0 != null) {
            actor0.setBounds(this.firstWidgetBounds.x, this.firstWidgetBounds.y, this.firstWidgetBounds.width, this.firstWidgetBounds.height);
            if(actor0 instanceof Layout) {
                ((Layout)actor0).validate();
            }
        }
        Actor actor1 = this.secondWidget;
        if(actor1 != null) {
            actor1.setBounds(this.secondWidgetBounds.x, this.secondWidgetBounds.y, this.secondWidgetBounds.width, this.secondWidgetBounds.height);
            if(actor1 instanceof Layout) {
                ((Layout)actor1).validate();
            }
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor0) {
        if(actor0 == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if(actor0 == this.firstWidget) {
            this.setFirstWidget(null);
            return true;
        }
        if(actor0 == this.secondWidget) {
            this.setSecondWidget(null);
            return true;
        }
        return true;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor0, boolean z) {
        if(actor0 == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if(actor0 == this.firstWidget) {
            super.removeActor(actor0, z);
            this.firstWidget = null;
            this.invalidate();
            return true;
        }
        if(actor0 == this.secondWidget) {
            super.removeActor(actor0, z);
            this.secondWidget = null;
            this.invalidate();
            return true;
        }
        return false;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public Actor removeActorAt(int v, boolean z) {
        Actor actor0 = super.removeActorAt(v, z);
        if(actor0 == this.firstWidget) {
            super.removeActor(actor0, z);
            this.firstWidget = null;
            this.invalidate();
            return actor0;
        }
        if(actor0 == this.secondWidget) {
            super.removeActor(actor0, z);
            this.secondWidget = null;
            this.invalidate();
        }
        return actor0;
    }

    public void setFirstWidget(@Null Actor actor0) {
        Actor actor1 = this.firstWidget;
        if(actor1 != null) {
            super.removeActor(actor1);
        }
        this.firstWidget = actor0;
        if(actor0 != null) {
            super.addActor(actor0);
        }
        this.invalidate();
    }

    public void setMaxSplitAmount(float f) {
        if(f < 0.0f || f > 1.0f) {
            throw new GdxRuntimeException("maxAmount has to be >= 0 and <= 1");
        }
        this.maxAmount = f;
    }

    public void setMinSplitAmount(float f) {
        if(f < 0.0f || f > 1.0f) {
            throw new GdxRuntimeException("minAmount has to be >= 0 and <= 1");
        }
        this.minAmount = f;
    }

    public void setSecondWidget(@Null Actor actor0) {
        Actor actor1 = this.secondWidget;
        if(actor1 != null) {
            super.removeActor(actor1);
        }
        this.secondWidget = actor0;
        if(actor0 != null) {
            super.addActor(actor0);
        }
        this.invalidate();
    }

    public void setSplitAmount(float f) {
        this.splitAmount = f;
        this.invalidate();
    }

    public void setStyle(SplitPaneStyle splitPane$SplitPaneStyle0) {
        this.style = splitPane$SplitPaneStyle0;
        this.invalidateHierarchy();
    }

    public void setVertical(boolean z) {
        if(this.vertical == z) {
            return;
        }
        this.vertical = z;
        this.invalidateHierarchy();
    }
}

