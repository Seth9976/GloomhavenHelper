package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.Cullable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Null;

public class ScrollPane extends WidgetGroup {
    public static class ScrollPaneStyle {
        @Null
        public Drawable background;
        @Null
        public Drawable corner;
        @Null
        public Drawable hScroll;
        @Null
        public Drawable hScrollKnob;
        @Null
        public Drawable vScroll;
        @Null
        public Drawable vScrollKnob;

        public ScrollPaneStyle() {
        }

        public ScrollPaneStyle(ScrollPaneStyle scrollPane$ScrollPaneStyle0) {
            this.background = scrollPane$ScrollPaneStyle0.background;
            this.corner = scrollPane$ScrollPaneStyle0.corner;
            this.hScroll = scrollPane$ScrollPaneStyle0.hScroll;
            this.hScrollKnob = scrollPane$ScrollPaneStyle0.hScrollKnob;
            this.vScroll = scrollPane$ScrollPaneStyle0.vScroll;
            this.vScrollKnob = scrollPane$ScrollPaneStyle0.vScrollKnob;
        }

        public ScrollPaneStyle(@Null Drawable drawable0, @Null Drawable drawable1, @Null Drawable drawable2, @Null Drawable drawable3, @Null Drawable drawable4) {
            this.background = drawable0;
            this.hScroll = drawable1;
            this.hScrollKnob = drawable2;
            this.vScroll = drawable3;
            this.vScrollKnob = drawable4;
        }
    }

    float amountX;
    float amountY;
    boolean cancelTouchFocus;
    private boolean clamp;
    boolean disableX;
    boolean disableY;
    int draggingPointer;
    float fadeAlpha;
    float fadeAlphaSeconds;
    float fadeDelay;
    float fadeDelaySeconds;
    boolean fadeScrollBars;
    boolean flickScroll;
    private ActorGestureListener flickScrollListener;
    float flingTime;
    float flingTimer;
    private boolean forceScrollX;
    private boolean forceScrollY;
    final Rectangle hKnobBounds;
    final Rectangle hScrollBounds;
    boolean hScrollOnBottom;
    final Vector2 lastPoint;
    float maxX;
    float maxY;
    private float overscrollDistance;
    private float overscrollSpeedMax;
    private float overscrollSpeedMin;
    private boolean overscrollX;
    private boolean overscrollY;
    boolean scrollBarTouch;
    boolean scrollX;
    boolean scrollY;
    private boolean scrollbarsOnTop;
    boolean smoothScrolling;
    private ScrollPaneStyle style;
    boolean touchScrollH;
    boolean touchScrollV;
    final Rectangle vKnobBounds;
    final Rectangle vScrollBounds;
    boolean vScrollOnRight;
    private boolean variableSizeKnobs;
    float velocityX;
    float velocityY;
    float visualAmountX;
    float visualAmountY;
    private Actor widget;
    final Rectangle widgetArea;
    private final Rectangle widgetCullingArea;

    public ScrollPane(@Null Actor actor0) {
        this(actor0, new ScrollPaneStyle());
    }

    public ScrollPane(@Null Actor actor0, ScrollPaneStyle scrollPane$ScrollPaneStyle0) {
        this.widgetArea = new Rectangle();
        this.hScrollBounds = new Rectangle();
        this.hKnobBounds = new Rectangle();
        this.vScrollBounds = new Rectangle();
        this.vKnobBounds = new Rectangle();
        this.widgetCullingArea = new Rectangle();
        this.vScrollOnRight = true;
        this.hScrollOnBottom = true;
        this.lastPoint = new Vector2();
        this.fadeScrollBars = true;
        this.smoothScrolling = true;
        this.scrollBarTouch = true;
        this.fadeAlphaSeconds = 1.0f;
        this.fadeDelaySeconds = 1.0f;
        this.cancelTouchFocus = true;
        this.flickScroll = true;
        this.flingTime = 1.0f;
        this.overscrollX = true;
        this.overscrollY = true;
        this.overscrollDistance = 50.0f;
        this.overscrollSpeedMin = 30.0f;
        this.overscrollSpeedMax = 200.0f;
        this.clamp = true;
        this.variableSizeKnobs = true;
        this.draggingPointer = -1;
        if(scrollPane$ScrollPaneStyle0 == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = scrollPane$ScrollPaneStyle0;
        this.setActor(actor0);
        this.setSize(150.0f, 150.0f);
        this.addCaptureListener();
        this.flickScrollListener = this.getFlickScrollListener();
        this.addListener(this.flickScrollListener);
        this.addScrollListener();
    }

    public ScrollPane(@Null Actor actor0, Skin skin0) {
        this(actor0, ((ScrollPaneStyle)skin0.get(ScrollPaneStyle.class)));
    }

    public ScrollPane(@Null Actor actor0, Skin skin0, String s) {
        this(actor0, ((ScrollPaneStyle)skin0.get(s, ScrollPaneStyle.class)));
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public void act(float f) {
        boolean z1;
        super.act(f);
        boolean z = this.flickScrollListener.getGestureDetector().isPanning();
        float f1 = this.fadeAlpha;
        if(f1 <= 0.0f || !this.fadeScrollBars || z || this.touchScrollH || this.touchScrollV) {
            z1 = false;
        }
        else {
            this.fadeDelay -= f;
            if(this.fadeDelay <= 0.0f) {
                this.fadeAlpha = Math.max(0.0f, f1 - f);
            }
            z1 = true;
        }
        if(this.flingTimer > 0.0f) {
            this.setScrollbarsVisible(true);
            float f2 = this.flingTimer / this.flingTime;
            this.amountX -= this.velocityX * f2 * f;
            this.amountY -= this.velocityY * f2 * f;
            this.clamp();
            if(this.amountX == -this.overscrollDistance) {
                this.velocityX = 0.0f;
            }
            if(this.amountX >= this.maxX + this.overscrollDistance) {
                this.velocityX = 0.0f;
            }
            if(this.amountY == -this.overscrollDistance) {
                this.velocityY = 0.0f;
            }
            if(this.amountY >= this.maxY + this.overscrollDistance) {
                this.velocityY = 0.0f;
            }
            this.flingTimer -= f;
            if(this.flingTimer <= 0.0f) {
                this.velocityX = 0.0f;
                this.velocityY = 0.0f;
            }
            z1 = true;
        }
        if(!this.smoothScrolling || this.flingTimer > 0.0f || z || this.touchScrollH && (!this.scrollX || this.maxX / (this.hScrollBounds.width - this.hKnobBounds.width) <= this.widgetArea.width * 0.1f) || this.touchScrollV && (!this.scrollY || this.maxY / (this.vScrollBounds.height - this.vKnobBounds.height) <= this.widgetArea.height * 0.1f)) {
            float f7 = this.amountX;
            if(this.visualAmountX != f7) {
                this.visualScrollX(f7);
            }
            float f8 = this.amountY;
            if(this.visualAmountY != f8) {
                this.visualScrollY(f8);
            }
        }
        else {
            float f3 = this.visualAmountX;
            float f4 = this.amountX;
            if(f3 != f4) {
                if(f3 < f4) {
                    this.visualScrollX(Math.min(f4, f3 + Math.max(f * 200.0f, (f4 - f3) * 7.0f * f)));
                }
                else {
                    this.visualScrollX(Math.max(f4, f3 - Math.max(f * 200.0f, (f3 - f4) * 7.0f * f)));
                }
                z1 = true;
            }
            float f5 = this.visualAmountY;
            float f6 = this.amountY;
            if(f5 != f6) {
                if(f5 < f6) {
                    this.visualScrollY(Math.min(f6, f5 + Math.max(200.0f * f, (f6 - f5) * 7.0f * f)));
                }
                else {
                    this.visualScrollY(Math.max(f6, f5 - Math.max(200.0f * f, (f5 - f6) * 7.0f * f)));
                }
                z1 = true;
            }
        }
        if(!z) {
            if(this.overscrollX && this.scrollX) {
                float f9 = this.amountX;
                if(f9 < 0.0f) {
                    this.setScrollbarsVisible(true);
                    this.amountX += (this.overscrollSpeedMin + (this.overscrollSpeedMax - this.overscrollSpeedMin) * -this.amountX / this.overscrollDistance) * f;
                    if(this.amountX > 0.0f) {
                        this.scrollX(0.0f);
                    }
                    z1 = true;
                }
                else if(f9 > this.maxX) {
                    this.setScrollbarsVisible(true);
                    float f10 = this.maxX;
                    this.amountX -= (this.overscrollSpeedMin + (this.overscrollSpeedMax - this.overscrollSpeedMin) * -(f10 - this.amountX) / this.overscrollDistance) * f;
                    if(this.amountX < f10) {
                        this.scrollX(f10);
                    }
                    z1 = true;
                }
            }
            if(this.overscrollY && this.scrollY) {
                float f11 = this.amountY;
                if(f11 < 0.0f) {
                    this.setScrollbarsVisible(true);
                    this.amountY += (this.overscrollSpeedMin + (this.overscrollSpeedMax - this.overscrollSpeedMin) * -this.amountY / this.overscrollDistance) * f;
                    if(this.amountY > 0.0f) {
                        this.scrollY(0.0f);
                    }
                    z1 = true;
                }
                else if(f11 > this.maxY) {
                    this.setScrollbarsVisible(true);
                    float f12 = this.maxY;
                    this.amountY -= (this.overscrollSpeedMin + (this.overscrollSpeedMax - this.overscrollSpeedMin) * -(f12 - this.amountY) / this.overscrollDistance) * f;
                    if(this.amountY < f12) {
                        this.scrollY(f12);
                    }
                    z1 = true;
                }
            }
        }
        if(z1) {
            Stage stage0 = this.getStage();
            if(stage0 != null && stage0.getActionsRequestRendering()) {
                Gdx.graphics.requestRendering();
            }
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActor(Actor actor0) {
        throw new UnsupportedOperationException("Use ScrollPane#setWidget.");
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActorAfter(Actor actor0, Actor actor1) {
        throw new UnsupportedOperationException("Use ScrollPane#setWidget.");
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActorAt(int v, Actor actor0) {
        throw new UnsupportedOperationException("Use ScrollPane#setWidget.");
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActorBefore(Actor actor0, Actor actor1) {
        throw new UnsupportedOperationException("Use ScrollPane#setWidget.");
    }

    protected void addCaptureListener() {
        this.addCaptureListener(new InputListener() {
            private float handlePosition;

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean mouseMoved(InputEvent inputEvent0, float f, float f1) {
                if(!ScrollPane.this.flickScroll) {
                    ScrollPane.this.setScrollbarsVisible(true);
                }
                return false;
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                int v2 = -1;
                if(ScrollPane.this.draggingPointer != -1) {
                    return false;
                }
                if(v == 0 && v1 != 0) {
                    return false;
                }
                if(ScrollPane.this.getStage() != null) {
                    ScrollPane.this.getStage().setScrollFocus(ScrollPane.this);
                }
                if(!ScrollPane.this.flickScroll) {
                    ScrollPane.this.setScrollbarsVisible(true);
                }
                if(ScrollPane.this.fadeAlpha == 0.0f) {
                    return false;
                }
                if(ScrollPane.this.scrollBarTouch && ScrollPane.this.scrollX && ScrollPane.this.hScrollBounds.contains(f, f1)) {
                    inputEvent0.stop();
                    ScrollPane.this.setScrollbarsVisible(true);
                    if(ScrollPane.this.hKnobBounds.contains(f, f1)) {
                        ScrollPane.this.lastPoint.set(f, f1);
                        this.handlePosition = ScrollPane.this.hKnobBounds.x;
                        ScrollPane.this.touchScrollH = true;
                        ScrollPane.this.draggingPointer = v;
                        return true;
                    }
                    ScrollPane scrollPane0 = ScrollPane.this;
                    float f2 = scrollPane0.amountX;
                    float f3 = ScrollPane.this.widgetArea.width;
                    if(f >= ScrollPane.this.hKnobBounds.x) {
                        v2 = 1;
                    }
                    scrollPane0.setScrollX(f2 + f3 * ((float)v2));
                    return true;
                }
                if(ScrollPane.this.scrollBarTouch && ScrollPane.this.scrollY && ScrollPane.this.vScrollBounds.contains(f, f1)) {
                    inputEvent0.stop();
                    ScrollPane.this.setScrollbarsVisible(true);
                    if(ScrollPane.this.vKnobBounds.contains(f, f1)) {
                        ScrollPane.this.lastPoint.set(f, f1);
                        this.handlePosition = ScrollPane.this.vKnobBounds.y;
                        ScrollPane.this.touchScrollV = true;
                        ScrollPane.this.draggingPointer = v;
                        return true;
                    }
                    ScrollPane scrollPane1 = ScrollPane.this;
                    float f4 = scrollPane1.amountY;
                    float f5 = ScrollPane.this.widgetArea.height;
                    if(f1 < ScrollPane.this.vKnobBounds.y) {
                        v2 = 1;
                    }
                    scrollPane1.setScrollY(f4 + f5 * ((float)v2));
                    return true;
                }
                return false;
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent0, float f, float f1, int v) {
                if(v != ScrollPane.this.draggingPointer) {
                    return;
                }
                if(ScrollPane.this.touchScrollH) {
                    float f2 = this.handlePosition + (f - ScrollPane.this.lastPoint.x);
                    this.handlePosition = f2;
                    float f3 = Math.min(ScrollPane.this.hScrollBounds.x + ScrollPane.this.hScrollBounds.width - ScrollPane.this.hKnobBounds.width, Math.max(ScrollPane.this.hScrollBounds.x, f2));
                    float f4 = ScrollPane.this.hScrollBounds.width - ScrollPane.this.hKnobBounds.width;
                    if(f4 != 0.0f) {
                        ScrollPane.this.setScrollPercentX((f3 - ScrollPane.this.hScrollBounds.x) / f4);
                    }
                    ScrollPane.this.lastPoint.set(f, f1);
                    return;
                }
                if(ScrollPane.this.touchScrollV) {
                    float f5 = this.handlePosition + (f1 - ScrollPane.this.lastPoint.y);
                    this.handlePosition = f5;
                    float f6 = Math.min(ScrollPane.this.vScrollBounds.y + ScrollPane.this.vScrollBounds.height - ScrollPane.this.vKnobBounds.height, Math.max(ScrollPane.this.vScrollBounds.y, f5));
                    float f7 = ScrollPane.this.vScrollBounds.height - ScrollPane.this.vKnobBounds.height;
                    if(f7 != 0.0f) {
                        ScrollPane.this.setScrollPercentY(1.0f - (f6 - ScrollPane.this.vScrollBounds.y) / f7);
                    }
                    ScrollPane.this.lastPoint.set(f, f1);
                }
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchUp(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                if(v != ScrollPane.this.draggingPointer) {
                    return;
                }
                ScrollPane.this.cancel();
            }
        });
    }

    protected void addScrollListener() {
        this.addListener(new InputListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean scrolled(InputEvent inputEvent0, float f, float f1, float f2, float f3) {
                ScrollPane.this.setScrollbarsVisible(true);
                if(!ScrollPane.this.scrollY && !ScrollPane.this.scrollX) {
                    return false;
                }
                if(!ScrollPane.this.scrollY) {
                    if(ScrollPane.this.scrollX && f2 == 0.0f) {
                        f2 = f3;
                    }
                }
                else if(!ScrollPane.this.scrollX && f3 == 0.0f) {
                    f3 = f2;
                }
                float f4 = ScrollPane.this.amountY;
                float f5 = ScrollPane.this.getMouseWheelY();
                ScrollPane.this.setScrollY(f4 + f5 * f3);
                ScrollPane.this.setScrollX(ScrollPane.this.amountX + ScrollPane.this.getMouseWheelX() * f2);
                return true;
            }
        });
    }

    public void cancel() {
        this.draggingPointer = -1;
        this.touchScrollH = false;
        this.touchScrollV = false;
        this.flickScrollListener.getGestureDetector().cancel();
    }

    public void cancelTouchFocus() {
        Stage stage0 = this.getStage();
        if(stage0 != null) {
            stage0.cancelTouchFocusExcept(this.flickScrollListener, this);
        }
    }

    void clamp() {
        if(!this.clamp) {
            return;
        }
        this.scrollX((this.overscrollX ? MathUtils.clamp(this.amountX, -this.overscrollDistance, this.maxX + this.overscrollDistance) : MathUtils.clamp(this.amountX, 0.0f, this.maxX)));
        this.scrollY((this.overscrollY ? MathUtils.clamp(this.amountY, -this.overscrollDistance, this.maxY + this.overscrollDistance) : MathUtils.clamp(this.amountY, 0.0f, this.maxY)));
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public void draw(Batch batch0, float f) {
        if(this.widget == null) {
            return;
        }
        this.validate();
        this.applyTransform(batch0, this.computeTransform());
        if(this.scrollX) {
            this.hKnobBounds.x = this.hScrollBounds.x + ((float)(((int)((this.hScrollBounds.width - this.hKnobBounds.width) * this.getVisualScrollPercentX()))));
        }
        if(this.scrollY) {
            this.vKnobBounds.y = this.vScrollBounds.y + ((float)(((int)((this.vScrollBounds.height - this.vKnobBounds.height) * (1.0f - this.getVisualScrollPercentY())))));
        }
        this.updateWidgetPosition();
        Color color0 = this.getColor();
        float f1 = color0.a * f;
        if(this.style.background != null) {
            batch0.setColor(color0.r, color0.g, color0.b, f1);
            this.style.background.draw(batch0, 0.0f, 0.0f, this.getWidth(), this.getHeight());
        }
        batch0.flush();
        if(this.clipBegin(this.widgetArea.x, this.widgetArea.y, this.widgetArea.width, this.widgetArea.height)) {
            this.drawChildren(batch0, f);
            batch0.flush();
            this.clipEnd();
        }
        batch0.setColor(color0.r, color0.g, color0.b, f1);
        if(this.fadeScrollBars) {
            f1 *= Interpolation.fade.apply(this.fadeAlpha / this.fadeAlphaSeconds);
        }
        this.drawScrollBars(batch0, color0.r, color0.g, color0.b, f1);
        this.resetTransform(batch0);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public void drawDebug(ShapeRenderer shapeRenderer0) {
        this.drawDebugBounds(shapeRenderer0);
        this.applyTransform(shapeRenderer0, this.computeTransform());
        if(this.clipBegin(this.widgetArea.x, this.widgetArea.y, this.widgetArea.width, this.widgetArea.height)) {
            this.drawDebugChildren(shapeRenderer0);
            shapeRenderer0.flush();
            this.clipEnd();
        }
        this.resetTransform(shapeRenderer0);
    }

    protected void drawScrollBars(Batch batch0, float f, float f1, float f2, float f3) {
        if(f3 <= 0.0f) {
            return;
        }
        batch0.setColor(f, f1, f2, f3);
        boolean z = true;
        boolean z1 = this.scrollX && this.hKnobBounds.width > 0.0f;
        if(!this.scrollY || this.vKnobBounds.height <= 0.0f) {
            z = false;
        }
        if(z1 && z && this.style.corner != null) {
            this.style.corner.draw(batch0, this.hScrollBounds.x + this.hScrollBounds.width, this.hScrollBounds.y, this.vScrollBounds.width, this.vScrollBounds.y);
        }
        if(z1) {
            if(this.style.hScroll != null) {
                this.style.hScroll.draw(batch0, this.hScrollBounds.x, this.hScrollBounds.y, this.hScrollBounds.width, this.hScrollBounds.height);
            }
            if(this.style.hScrollKnob != null) {
                this.style.hScrollKnob.draw(batch0, this.hKnobBounds.x, this.hKnobBounds.y, this.hKnobBounds.width, this.hKnobBounds.height);
            }
        }
        if(z) {
            if(this.style.vScroll != null) {
                this.style.vScroll.draw(batch0, this.vScrollBounds.x, this.vScrollBounds.y, this.vScrollBounds.width, this.vScrollBounds.height);
            }
            if(this.style.vScrollKnob != null) {
                this.style.vScrollKnob.draw(batch0, this.vKnobBounds.x, this.vKnobBounds.y, this.vKnobBounds.width, this.vKnobBounds.height);
            }
        }
    }

    public void fling(float f, float f1, float f2) {
        this.flingTimer = f;
        this.velocityX = f1;
        this.velocityY = f2;
    }

    @Null
    public Actor getActor() {
        return this.widget;
    }

    public boolean getFadeScrollBars() {
        return this.fadeScrollBars;
    }

    protected ActorGestureListener getFlickScrollListener() {
        return new ActorGestureListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener
            public void fling(InputEvent inputEvent0, float f, float f1, int v) {
                if(Math.abs(f) > 150.0f && ScrollPane.this.scrollX) {
                    ScrollPane.this.flingTimer = ScrollPane.this.flingTime;
                    ScrollPane.this.velocityX = f;
                    if(ScrollPane.this.cancelTouchFocus) {
                        ScrollPane.this.cancelTouchFocus();
                    }
                }
                if(Math.abs(f1) > 150.0f && ScrollPane.this.scrollY) {
                    ScrollPane.this.flingTimer = ScrollPane.this.flingTime;
                    ScrollPane.this.velocityY = -f1;
                    if(ScrollPane.this.cancelTouchFocus) {
                        ScrollPane.this.cancelTouchFocus();
                    }
                }
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener
            public boolean handle(Event event0) {
                if(super.handle(event0)) {
                    if(((InputEvent)event0).getType() == Type.touchDown) {
                        ScrollPane.this.flingTimer = 0.0f;
                    }
                    return true;
                }
                if(event0 instanceof InputEvent && ((InputEvent)event0).isTouchFocusCancel()) {
                    ScrollPane.this.cancel();
                }
                return false;
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener
            public void pan(InputEvent inputEvent0, float f, float f1, float f2, float f3) {
                ScrollPane.this.setScrollbarsVisible(true);
                ScrollPane.this.amountX -= f2;
                ScrollPane.this.amountY += f3;
                ScrollPane.this.clamp();
                if(ScrollPane.this.cancelTouchFocus && (ScrollPane.this.scrollX && f2 != 0.0f || ScrollPane.this.scrollY && f3 != 0.0f)) {
                    ScrollPane.this.cancelTouchFocus();
                }
            }
        };
    }

    public float getMaxX() {
        return this.maxX;
    }

    public float getMaxY() {
        return this.maxY;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getMinHeight() {
        return 0.0f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getMinWidth() {
        return 0.0f;
    }

    protected float getMouseWheelX() {
        return Math.min(this.widgetArea.width, Math.max(this.widgetArea.width * 0.9f, this.maxX * 0.1f) / 4.0f);
    }

    protected float getMouseWheelY() {
        return Math.min(this.widgetArea.height, Math.max(this.widgetArea.height * 0.9f, this.maxY * 0.1f) / 4.0f);
    }

    public float getOverscrollDistance() {
        return this.overscrollDistance;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getPrefHeight() {
        float f1;
        Actor actor0 = this.widget;
        float f = 0.0f;
        if(actor0 instanceof Layout) {
            f1 = ((Layout)actor0).getPrefHeight();
        }
        else {
            f1 = actor0 == null ? 0.0f : actor0.getHeight();
        }
        Drawable drawable0 = this.style.background;
        if(drawable0 != null) {
            f1 = Math.max(f1 + drawable0.getTopHeight() + drawable0.getBottomHeight(), drawable0.getMinHeight());
        }
        if(this.scrollX) {
            if(this.style.hScrollKnob != null) {
                f = this.style.hScrollKnob.getMinHeight();
            }
            if(this.style.hScroll != null) {
                f = Math.max(f, this.style.hScroll.getMinHeight());
            }
            return f1 + f;
        }
        return f1;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getPrefWidth() {
        float f1;
        Actor actor0 = this.widget;
        float f = 0.0f;
        if(actor0 instanceof Layout) {
            f1 = ((Layout)actor0).getPrefWidth();
        }
        else {
            f1 = actor0 == null ? 0.0f : actor0.getWidth();
        }
        Drawable drawable0 = this.style.background;
        if(drawable0 != null) {
            f1 = Math.max(f1 + drawable0.getLeftWidth() + drawable0.getRightWidth(), drawable0.getMinWidth());
        }
        if(this.scrollY) {
            if(this.style.vScrollKnob != null) {
                f = this.style.vScrollKnob.getMinWidth();
            }
            if(this.style.vScroll != null) {
                f = Math.max(f, this.style.vScroll.getMinWidth());
            }
            return f1 + f;
        }
        return f1;
    }

    public float getScrollBarHeight() {
        float f = 0.0f;
        if(!this.scrollX) {
            return 0.0f;
        }
        if(this.style.hScrollKnob != null) {
            f = this.style.hScrollKnob.getMinHeight();
        }
        return this.style.hScroll == null ? f : Math.max(f, this.style.hScroll.getMinHeight());
    }

    public float getScrollBarWidth() {
        float f = 0.0f;
        if(!this.scrollY) {
            return 0.0f;
        }
        if(this.style.vScrollKnob != null) {
            f = this.style.vScrollKnob.getMinWidth();
        }
        return this.style.vScroll == null ? f : Math.max(f, this.style.vScroll.getMinWidth());
    }

    public float getScrollHeight() {
        return this.widgetArea.height;
    }

    public float getScrollPercentX() {
        return this.maxX == 0.0f ? 0.0f : MathUtils.clamp(this.amountX / this.maxX, 0.0f, 1.0f);
    }

    public float getScrollPercentY() {
        return this.maxY == 0.0f ? 0.0f : MathUtils.clamp(this.amountY / this.maxY, 0.0f, 1.0f);
    }

    public float getScrollWidth() {
        return this.widgetArea.width;
    }

    public float getScrollX() {
        return this.amountX;
    }

    public float getScrollY() {
        return this.amountY;
    }

    public ScrollPaneStyle getStyle() {
        return this.style;
    }

    public boolean getVariableSizeKnobs() {
        return this.variableSizeKnobs;
    }

    public float getVelocityX() {
        return this.velocityX;
    }

    public float getVelocityY() {
        return this.velocityY;
    }

    public float getVisualScrollPercentX() {
        return this.maxX == 0.0f ? 0.0f : MathUtils.clamp(this.visualAmountX / this.maxX, 0.0f, 1.0f);
    }

    public float getVisualScrollPercentY() {
        return this.maxY == 0.0f ? 0.0f : MathUtils.clamp(this.visualAmountY / this.maxY, 0.0f, 1.0f);
    }

    public float getVisualScrollX() {
        return this.scrollX ? this.visualAmountX : 0.0f;
    }

    public float getVisualScrollY() {
        return this.scrollY ? this.visualAmountY : 0.0f;
    }

    @Null
    @Deprecated
    public Actor getWidget() {
        return this.widget;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    @Null
    public Actor hit(float f, float f1, boolean z) {
        if(f >= 0.0f && f < this.getWidth() && f1 >= 0.0f && f1 < this.getHeight()) {
            if(z && this.getTouchable() == Touchable.enabled && this.isVisible()) {
                if(this.scrollX && this.touchScrollH && this.hScrollBounds.contains(f, f1)) {
                    return this;
                }
                if(this.scrollY && this.touchScrollV && this.vScrollBounds.contains(f, f1)) {
                    return this;
                }
            }
            return super.hit(f, f1, z);
        }
        return null;
    }

    public boolean isBottomEdge() {
        return !this.scrollY || this.amountY >= this.maxY;
    }

    public boolean isDragging() {
        return this.draggingPointer != -1;
    }

    public boolean isFlinging() {
        return this.flingTimer > 0.0f;
    }

    public boolean isForceScrollX() {
        return this.forceScrollX;
    }

    public boolean isForceScrollY() {
        return this.forceScrollY;
    }

    public boolean isLeftEdge() {
        return !this.scrollX || this.amountX <= 0.0f;
    }

    public boolean isPanning() {
        return this.flickScrollListener.getGestureDetector().isPanning();
    }

    public boolean isRightEdge() {
        return !this.scrollX || this.amountX >= this.maxX;
    }

    public boolean isScrollX() {
        return this.scrollX;
    }

    public boolean isScrollY() {
        return this.scrollY;
    }

    public boolean isScrollingDisabledX() {
        return this.disableX;
    }

    public boolean isScrollingDisabledY() {
        return this.disableY;
    }

    public boolean isTopEdge() {
        return !this.scrollY || this.amountY <= 0.0f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public void layout() {
        float f9;
        float f8;
        float f3;
        float f2;
        float f1;
        float f;
        Drawable drawable0 = this.style.background;
        Drawable drawable1 = this.style.hScrollKnob;
        Drawable drawable2 = this.style.vScrollKnob;
        if(drawable0 == null) {
            f3 = 0.0f;
            f = 0.0f;
            f1 = 0.0f;
            f2 = 0.0f;
        }
        else {
            f = drawable0.getLeftWidth();
            f1 = drawable0.getRightWidth();
            f2 = drawable0.getTopHeight();
            f3 = drawable0.getBottomHeight();
        }
        float f4 = this.getWidth();
        float f5 = this.getHeight() - f2;
        this.widgetArea.set(f, f3, f4 - f - f1, f5 - f3);
        if(this.widget == null) {
            return;
        }
        float f6 = drawable1 == null ? 0.0f : drawable1.getMinHeight();
        if(this.style.hScroll != null) {
            f6 = Math.max(f6, this.style.hScroll.getMinHeight());
        }
        float f7 = drawable2 == null ? 0.0f : drawable2.getMinWidth();
        if(this.style.vScroll != null) {
            f7 = Math.max(f7, this.style.vScroll.getMinWidth());
        }
        Actor actor0 = this.widget;
        if(actor0 instanceof Layout) {
            f8 = ((Layout)actor0).getPrefWidth();
            f9 = ((Layout)actor0).getPrefHeight();
        }
        else {
            f8 = actor0.getWidth();
            f9 = this.widget.getHeight();
        }
        boolean z = false;
        this.scrollX = this.forceScrollX || f8 > this.widgetArea.width && !this.disableX;
        if(this.forceScrollY || f9 > this.widgetArea.height && !this.disableY) {
            z = true;
        }
        this.scrollY = z;
        if(!this.scrollbarsOnTop) {
            if(this.scrollY) {
                this.widgetArea.width -= f7;
                if(!this.vScrollOnRight) {
                    this.widgetArea.x += f7;
                }
                if(!this.scrollX && f8 > this.widgetArea.width && !this.disableX) {
                    this.scrollX = true;
                }
            }
            if(this.scrollX) {
                this.widgetArea.height -= f6;
                if(this.hScrollOnBottom) {
                    this.widgetArea.y += f6;
                }
                if(!this.scrollY && f9 > this.widgetArea.height && !this.disableY) {
                    this.scrollY = true;
                    this.widgetArea.width -= f7;
                    if(!this.vScrollOnRight) {
                        this.widgetArea.x += f7;
                    }
                }
            }
        }
        float f10 = this.disableX ? this.widgetArea.width : Math.max(this.widgetArea.width, f8);
        float f11 = this.disableY ? this.widgetArea.height : Math.max(this.widgetArea.height, f9);
        this.maxX = f10 - this.widgetArea.width;
        this.maxY = f11 - this.widgetArea.height;
        this.scrollX(MathUtils.clamp(this.amountX, 0.0f, this.maxX));
        this.scrollY(MathUtils.clamp(this.amountY, 0.0f, this.maxY));
        if(this.scrollX) {
            if(drawable1 == null) {
                this.hScrollBounds.set(0.0f, 0.0f, 0.0f, 0.0f);
                this.hKnobBounds.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
            else {
                this.hScrollBounds.set((this.scrollbarsOnTop ? f : this.widgetArea.x), (this.hScrollOnBottom ? f3 : f5 - f6), this.widgetArea.width, f6);
                if(this.scrollY && this.scrollbarsOnTop) {
                    this.hScrollBounds.width -= f7;
                    if(!this.vScrollOnRight) {
                        this.hScrollBounds.x += f7;
                    }
                }
                this.hKnobBounds.width = this.variableSizeKnobs ? Math.max(drawable1.getMinWidth(), ((int)(this.hScrollBounds.width * this.widgetArea.width / f10))) : drawable1.getMinWidth();
                if(this.hKnobBounds.width > f10) {
                    this.hKnobBounds.width = 0.0f;
                }
                this.hKnobBounds.height = drawable1.getMinHeight();
                this.hKnobBounds.x = this.hScrollBounds.x + ((float)(((int)((this.hScrollBounds.width - this.hKnobBounds.width) * this.getScrollPercentX()))));
                this.hKnobBounds.y = this.hScrollBounds.y;
            }
        }
        if(this.scrollY) {
            if(drawable2 == null) {
                this.vScrollBounds.set(0.0f, 0.0f, 0.0f, 0.0f);
                this.vKnobBounds.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
            else {
                float f12 = this.vScrollOnRight ? f4 - f1 - f7 : f;
                if(!this.scrollbarsOnTop) {
                    f3 = this.widgetArea.y;
                }
                this.vScrollBounds.set(f12, f3, f7, this.widgetArea.height);
                if(this.scrollX && this.scrollbarsOnTop) {
                    this.vScrollBounds.height -= f6;
                    if(this.hScrollOnBottom) {
                        this.vScrollBounds.y += f6;
                    }
                }
                this.vKnobBounds.width = drawable2.getMinWidth();
                this.vKnobBounds.height = this.variableSizeKnobs ? Math.max(drawable2.getMinHeight(), ((int)(this.vScrollBounds.height * this.widgetArea.height / f11))) : drawable2.getMinHeight();
                if(this.vKnobBounds.height > f11) {
                    this.vKnobBounds.height = 0.0f;
                }
                Rectangle rectangle0 = this.vKnobBounds;
                if(this.vScrollOnRight) {
                    f = f4 - f1 - drawable2.getMinWidth();
                }
                rectangle0.x = f;
                this.vKnobBounds.y = this.vScrollBounds.y + ((float)(((int)((this.vScrollBounds.height - this.vKnobBounds.height) * (1.0f - this.getScrollPercentY())))));
            }
        }
        this.updateWidgetPosition();
        Actor actor1 = this.widget;
        if(actor1 instanceof Layout) {
            actor1.setSize(f10, f11);
            ((Layout)this.widget).validate();
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor0) {
        if(actor0 == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if(actor0 != this.widget) {
            return false;
        }
        this.setActor(null);
        return true;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor0, boolean z) {
        if(actor0 == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if(actor0 != this.widget) {
            return false;
        }
        this.widget = null;
        return super.removeActor(actor0, z);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public Actor removeActorAt(int v, boolean z) {
        Actor actor0 = super.removeActorAt(v, z);
        if(actor0 == this.widget) {
            this.widget = null;
        }
        return actor0;
    }

    public void scrollTo(float f, float f1, float f2, float f3) {
        this.scrollTo(f, f1, f2, f3, false, false);
    }

    public void scrollTo(float f, float f1, float f2, float f3, boolean z, boolean z1) {
        this.validate();
        float f4 = this.amountX;
        if(z) {
            f = f - this.widgetArea.width / 2.0f + f2 / 2.0f;
        }
        else {
            float f5 = f2 + f;
            float f6 = f5 > this.widgetArea.width + f4 ? f5 - this.widgetArea.width : f4;
            if(f >= f6) {
                f = f6;
            }
        }
        this.scrollX(MathUtils.clamp(f, 0.0f, this.maxX));
        float f7 = this.amountY;
        if(z1) {
            f7 = this.maxY - f1 + this.widgetArea.height / 2.0f - f3 / 2.0f;
        }
        else {
            if(f7 > this.maxY - f1 - f3 + this.widgetArea.height) {
                f7 = this.maxY - f1 - f3 + this.widgetArea.height;
            }
            float f8 = this.maxY;
            if(f7 < f8 - f1) {
                f7 = f8 - f1;
            }
        }
        this.scrollY(MathUtils.clamp(f7, 0.0f, this.maxY));
    }

    protected void scrollX(float f) {
        this.amountX = f;
    }

    protected void scrollY(float f) {
        this.amountY = f;
    }

    public void setActor(@Null Actor actor0) {
        Actor actor1 = this.widget;
        if(actor1 == this) {
            throw new IllegalArgumentException("widget cannot be the ScrollPane.");
        }
        if(actor1 != null) {
            super.removeActor(actor1);
        }
        this.widget = actor0;
        Actor actor2 = this.widget;
        if(actor2 != null) {
            super.addActor(actor2);
        }
    }

    public void setCancelTouchFocus(boolean z) {
        this.cancelTouchFocus = z;
    }

    public void setClamp(boolean z) {
        this.clamp = z;
    }

    public void setFadeScrollBars(boolean z) {
        if(this.fadeScrollBars == z) {
            return;
        }
        this.fadeScrollBars = z;
        if(!z) {
            this.fadeAlpha = this.fadeAlphaSeconds;
        }
        this.invalidate();
    }

    public void setFlickScroll(boolean z) {
        if(this.flickScroll == z) {
            return;
        }
        this.flickScroll = z;
        if(z) {
            this.addListener(this.flickScrollListener);
        }
        else {
            this.removeListener(this.flickScrollListener);
        }
        this.invalidate();
    }

    public void setFlickScrollTapSquareSize(float f) {
        this.flickScrollListener.getGestureDetector().setTapSquareSize(f);
    }

    public void setFlingTime(float f) {
        this.flingTime = f;
    }

    public void setForceScroll(boolean z, boolean z1) {
        this.forceScrollX = z;
        this.forceScrollY = z1;
    }

    public void setOverscroll(boolean z, boolean z1) {
        this.overscrollX = z;
        this.overscrollY = z1;
    }

    public void setScrollBarPositions(boolean z, boolean z1) {
        this.hScrollOnBottom = z;
        this.vScrollOnRight = z1;
    }

    public void setScrollBarTouch(boolean z) {
        this.scrollBarTouch = z;
    }

    public void setScrollPercentX(float f) {
        this.scrollX(this.maxX * MathUtils.clamp(f, 0.0f, 1.0f));
    }

    public void setScrollPercentY(float f) {
        this.scrollY(this.maxY * MathUtils.clamp(f, 0.0f, 1.0f));
    }

    public void setScrollX(float f) {
        this.scrollX(MathUtils.clamp(f, 0.0f, this.maxX));
    }

    public void setScrollY(float f) {
        this.scrollY(MathUtils.clamp(f, 0.0f, this.maxY));
    }

    public void setScrollbarsOnTop(boolean z) {
        this.scrollbarsOnTop = z;
        this.invalidate();
    }

    public void setScrollbarsVisible(boolean z) {
        if(z) {
            this.fadeAlpha = this.fadeAlphaSeconds;
            this.fadeDelay = this.fadeDelaySeconds;
            return;
        }
        this.fadeAlpha = 0.0f;
        this.fadeDelay = 0.0f;
    }

    public void setScrollingDisabled(boolean z, boolean z1) {
        this.disableX = z;
        this.disableY = z1;
        this.invalidate();
    }

    public void setSmoothScrolling(boolean z) {
        this.smoothScrolling = z;
    }

    public void setStyle(ScrollPaneStyle scrollPane$ScrollPaneStyle0) {
        if(scrollPane$ScrollPaneStyle0 == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = scrollPane$ScrollPaneStyle0;
        this.invalidateHierarchy();
    }

    public void setVariableSizeKnobs(boolean z) {
        this.variableSizeKnobs = z;
    }

    public void setVelocityX(float f) {
        this.velocityX = f;
    }

    public void setVelocityY(float f) {
        this.velocityY = f;
    }

    @Deprecated
    public void setWidget(@Null Actor actor0) {
        this.setActor(actor0);
    }

    public void setupFadeScrollBars(float f, float f1) {
        this.fadeAlphaSeconds = f;
        this.fadeDelaySeconds = f1;
    }

    public void setupOverscroll(float f, float f1, float f2) {
        this.overscrollDistance = f;
        this.overscrollSpeedMin = f1;
        this.overscrollSpeedMax = f2;
    }

    public void updateVisualScroll() {
        this.visualAmountX = this.amountX;
        this.visualAmountY = this.amountY;
    }

    private void updateWidgetPosition() {
        float f = this.widgetArea.x - ((float)(this.scrollX ? ((int)this.visualAmountX) : 0));
        float f1 = this.widgetArea.y - ((float)(((int)(this.scrollY ? this.maxY - this.visualAmountY : this.maxY))));
        this.widget.setPosition(f, f1);
        if(this.widget instanceof Cullable) {
            this.widgetCullingArea.x = this.widgetArea.x - f;
            this.widgetCullingArea.y = this.widgetArea.y - f1;
            this.widgetCullingArea.width = this.widgetArea.width;
            this.widgetCullingArea.height = this.widgetArea.height;
            ((Cullable)this.widget).setCullingArea(this.widgetCullingArea);
        }
    }

    protected void visualScrollX(float f) {
        this.visualAmountX = f;
    }

    protected void visualScrollY(float f) {
        this.visualAmountY = f;
    }
}

