package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;

public class Window extends Table {
    public static class WindowStyle {
        @Null
        public Drawable background;
        @Null
        public Drawable stageBackground;
        public BitmapFont titleFont;
        @Null
        public Color titleFontColor;

        public WindowStyle() {
            this.titleFontColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        }

        public WindowStyle(BitmapFont bitmapFont0, Color color0, @Null Drawable drawable0) {
            this.titleFontColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
            this.titleFont = bitmapFont0;
            this.titleFontColor.set(color0);
            this.background = drawable0;
        }

        public WindowStyle(WindowStyle window$WindowStyle0) {
            this.titleFontColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
            this.background = window$WindowStyle0.background;
            this.titleFont = window$WindowStyle0.titleFont;
            Color color0 = window$WindowStyle0.titleFontColor;
            if(color0 != null) {
                this.titleFontColor = new Color(color0);
            }
            this.background = window$WindowStyle0.background;
        }
    }

    private static final int MOVE = 0x20;
    protected boolean dragging;
    boolean drawTitleTable;
    protected int edge;
    boolean isModal;
    boolean isMovable;
    boolean isResizable;
    boolean keepWithinStage;
    int resizeBorder;
    private WindowStyle style;
    Label titleLabel;
    Table titleTable;
    private static final Vector2 tmpPosition;
    private static final Vector2 tmpSize;

    static {
        Window.tmpPosition = new Vector2();
        Window.tmpSize = new Vector2();
    }

    public Window(String s, Skin skin0) {
        this(s, ((WindowStyle)skin0.get(WindowStyle.class)));
        this.setSkin(skin0);
    }

    public Window(String s, Skin skin0, String s1) {
        this(s, ((WindowStyle)skin0.get(s1, WindowStyle.class)));
        this.setSkin(skin0);
    }

    public Window(String s, WindowStyle window$WindowStyle0) {
        this.isMovable = true;
        this.resizeBorder = 8;
        this.keepWithinStage = true;
        if(s == null) {
            throw new IllegalArgumentException("title cannot be null.");
        }
        this.setTouchable(Touchable.enabled);
        this.setClip(true);
        this.titleLabel = new Label(s, new LabelStyle(window$WindowStyle0.titleFont, window$WindowStyle0.titleFontColor));
        this.titleLabel.setEllipsis(true);
        this.titleTable = new Table() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
            public void draw(Batch batch0, float f) {
                if(Window.this.drawTitleTable) {
                    super.draw(batch0, f);
                }
            }
        };
        this.titleTable.add(this.titleLabel).expandX().fillX().minWidth(0.0f);
        this.addActor(this.titleTable);
        this.setStyle(window$WindowStyle0);
        this.setWidth(150.0f);
        this.setHeight(150.0f);
        this.addCaptureListener(new InputListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                Window.this.toFront();
                return false;
            }
        });
        this.addListener(new InputListener() {
            float lastX;
            float lastY;
            float startX;
            float startY;

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean keyDown(InputEvent inputEvent0, int v) {
                return Window.this.isModal;
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean keyTyped(InputEvent inputEvent0, char c) {
                return Window.this.isModal;
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean keyUp(InputEvent inputEvent0, int v) {
                return Window.this.isModal;
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean mouseMoved(InputEvent inputEvent0, float f, float f1) {
                this.updateEdge(f, f1);
                return Window.this.isModal;
            }

            public boolean scrolled(InputEvent inputEvent0, float f, float f1, int v) {
                return Window.this.isModal;
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                if(v1 == 0) {
                    this.updateEdge(f, f1);
                    Window.this.dragging = Window.this.edge != 0;
                    this.startX = f;
                    this.startY = f1;
                    this.lastX = f - Window.this.getWidth();
                    this.lastY = f1 - Window.this.getHeight();
                }
                return Window.this.edge != 0 || Window.this.isModal;
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent0, float f, float f1, int v) {
                if(!Window.this.dragging) {
                    return;
                }
                float f2 = Window.this.getWidth();
                float f3 = Window.this.getHeight();
                float f4 = Window.this.getX();
                float f5 = Window.this.getY();
                float f6 = Window.this.getMinWidth();
                float f7 = Window.this.getMinHeight();
                Stage stage0 = Window.this.getStage();
                boolean z = Window.this.keepWithinStage && stage0 != null && Window.this.getParent() == stage0.getRoot();
                if((Window.this.edge & 0x20) != 0) {
                    f4 += f - this.startX;
                    f5 += f1 - this.startY;
                }
                if((Window.this.edge & 8) != 0) {
                    float f8 = f2 - (f - this.startX) < f6 ? -(f6 - f2) : f - this.startX;
                    if(z && f4 + f8 < 0.0f) {
                        f8 = -f4;
                    }
                    f2 -= f8;
                    f4 += f8;
                }
                if((Window.this.edge & 4) != 0) {
                    float f9 = f3 - (f1 - this.startY) < f7 ? -(f7 - f3) : f1 - this.startY;
                    if(z && f5 + f9 < 0.0f) {
                        f9 = -f5;
                    }
                    f3 -= f9;
                    f5 += f9;
                }
                if((Window.this.edge & 16) != 0) {
                    float f10 = f2 + (f - this.lastX - f2) < f6 ? f6 - f2 : f - this.lastX - f2;
                    if(z && f4 + f2 + f10 > stage0.getWidth()) {
                        f10 = stage0.getWidth() - f4 - f2;
                    }
                    f2 += f10;
                }
                if((Window.this.edge & 2) != 0) {
                    float f11 = f3 + (f1 - this.lastY - f3) < f7 ? f7 - f3 : f1 - this.lastY - f3;
                    if(z && f5 + f3 + f11 > stage0.getHeight()) {
                        f11 = stage0.getHeight() - f5 - f3;
                    }
                    f3 += f11;
                }
                Window.this.setBounds(((float)Math.round(f4)), ((float)Math.round(f5)), ((float)Math.round(f2)), ((float)Math.round(f3)));
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchUp(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                Window.this.dragging = false;
            }

            private void updateEdge(float f, float f1) {
                float f2 = ((float)Window.this.resizeBorder) / 2.0f;
                float f3 = Window.this.getWidth();
                float f4 = Window.this.getHeight();
                float f5 = Window.this.getPadTop();
                float f6 = Window.this.getPadLeft();
                float f7 = Window.this.getPadBottom();
                float f8 = f3 - Window.this.getPadRight();
                Window.this.edge = 0;
                if(Window.this.isResizable && f >= f6 - f2 && f <= f8 + f2 && f1 >= f7 - f2) {
                    if(f < f6 + f2) {
                        Window.this.edge |= 8;
                    }
                    if(f > f8 - f2) {
                        Window.this.edge |= 16;
                    }
                    if(f1 < f7 + f2) {
                        Window.this.edge |= 4;
                    }
                    if(Window.this.edge != 0) {
                        f2 += 25.0f;
                    }
                    if(f < f6 + f2) {
                        Window.this.edge |= 8;
                    }
                    if(f > f8 - f2) {
                        Window.this.edge |= 16;
                    }
                    if(f1 < f7 + f2) {
                        Window.this.edge |= 4;
                    }
                }
                if(Window.this.isMovable && Window.this.edge == 0 && f1 <= f4 && f1 >= f4 - f5 && f >= f6 && f <= f8) {
                    Window.this.edge = 0x20;
                }
            }
        });
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
    public void draw(Batch batch0, float f) {
        Stage stage0 = this.getStage();
        if(stage0 != null) {
            if(stage0.getKeyboardFocus() == null) {
                stage0.setKeyboardFocus(this);
            }
            this.keepWithinStage();
            if(this.style.stageBackground != null) {
                this.stageToLocalCoordinates(Window.tmpPosition.set(0.0f, 0.0f));
                float f1 = stage0.getWidth();
                float f2 = stage0.getHeight();
                this.stageToLocalCoordinates(Window.tmpSize.set(f1, f2));
                this.drawStageBackground(batch0, f, this.getX() + Window.tmpPosition.x, this.getY() + Window.tmpPosition.y, this.getX() + Window.tmpSize.x, this.getY() + Window.tmpSize.y);
            }
        }
        super.draw(batch0, f);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
    protected void drawBackground(Batch batch0, float f, float f1, float f2) {
        super.drawBackground(batch0, f, f1, f2);
        Color color0 = this.titleTable.getColor();
        color0.a = this.getColor().a;
        float f3 = this.getPadTop();
        float f4 = this.getPadLeft();
        this.titleTable.setSize(this.getWidth() - f4 - this.getPadRight(), f3);
        this.titleTable.setPosition(f4, this.getHeight() - f3);
        this.drawTitleTable = true;
        this.titleTable.draw(batch0, f);
        this.drawTitleTable = false;
    }

    protected void drawStageBackground(Batch batch0, float f, float f1, float f2, float f3, float f4) {
        Color color0 = this.getColor();
        batch0.setColor(color0.r, color0.g, color0.b, color0.a * f);
        this.style.stageBackground.draw(batch0, f1, f2, f3, f4);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
    public float getPrefWidth() {
        return Math.max(super.getPrefWidth(), this.titleTable.getPrefWidth() + this.getPadLeft() + this.getPadRight());
    }

    public WindowStyle getStyle() {
        return this.style;
    }

    public Label getTitleLabel() {
        return this.titleLabel;
    }

    public Table getTitleTable() {
        return this.titleTable;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
    @Null
    public Actor hit(float f, float f1, boolean z) {
        if(!this.isVisible()) {
            return null;
        }
        Actor actor0 = super.hit(f, f1, z);
        if(actor0 == null && this.isModal && (!z || this.getTouchable() == Touchable.enabled)) {
            return this;
        }
        float f2 = this.getHeight();
        if(actor0 != null && actor0 != this && f1 <= f2 && f1 >= f2 - this.getPadTop() && f >= 0.0f && f <= this.getWidth()) {
            Actor actor1;
            for(actor1 = actor0; actor1.getParent() != this; actor1 = actor1.getParent()) {
            }
            if(this.getCell(actor1) != null) {
                return this;
            }
        }
        return actor0;
    }

    public boolean isDragging() {
        return this.dragging;
    }

    public boolean isModal() {
        return this.isModal;
    }

    public boolean isMovable() {
        return this.isMovable;
    }

    public boolean isResizable() {
        return this.isResizable;
    }

    public void keepWithinStage() {
        if(!this.keepWithinStage) {
            return;
        }
        Stage stage0 = this.getStage();
        if(stage0 == null) {
            return;
        }
        Camera camera0 = stage0.getCamera();
        if(camera0 instanceof OrthographicCamera) {
            float f = stage0.getWidth();
            float f1 = stage0.getHeight();
            if(this.getX(16) - camera0.position.x > f / 2.0f / ((OrthographicCamera)camera0).zoom) {
                this.setPosition(camera0.position.x + f / 2.0f / ((OrthographicCamera)camera0).zoom, this.getY(16), 16);
            }
            if(this.getX(8) - camera0.position.x < -f / 2.0f / ((OrthographicCamera)camera0).zoom) {
                this.setPosition(camera0.position.x - f / 2.0f / ((OrthographicCamera)camera0).zoom, this.getY(8), 8);
            }
            if(this.getY(2) - camera0.position.y > f1 / 2.0f / ((OrthographicCamera)camera0).zoom) {
                this.setPosition(this.getX(2), camera0.position.y + f1 / 2.0f / ((OrthographicCamera)camera0).zoom, 2);
            }
            if(this.getY(4) - camera0.position.y < -f1 / 2.0f / ((OrthographicCamera)camera0).zoom) {
                this.setPosition(this.getX(4), camera0.position.y - f1 / 2.0f / ((OrthographicCamera)camera0).zoom, 4);
            }
        }
        else if(this.getParent() == stage0.getRoot()) {
            float f2 = stage0.getWidth();
            float f3 = stage0.getHeight();
            if(this.getX() < 0.0f) {
                this.setX(0.0f);
            }
            if(this.getRight() > f2) {
                this.setX(f2 - this.getWidth());
            }
            if(this.getY() < 0.0f) {
                this.setY(0.0f);
            }
            if(this.getTop() > f3) {
                this.setY(f3 - this.getHeight());
            }
        }
    }

    public void setKeepWithinStage(boolean z) {
        this.keepWithinStage = z;
    }

    public void setModal(boolean z) {
        this.isModal = z;
    }

    public void setMovable(boolean z) {
        this.isMovable = z;
    }

    public void setResizable(boolean z) {
        this.isResizable = z;
    }

    public void setResizeBorder(int v) {
        this.resizeBorder = v;
    }

    public void setStyle(WindowStyle window$WindowStyle0) {
        if(window$WindowStyle0 == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = window$WindowStyle0;
        this.setBackground(window$WindowStyle0.background);
        this.titleLabel.setStyle(new LabelStyle(window$WindowStyle0.titleFont, window$WindowStyle0.titleFontColor));
        this.invalidateHierarchy();
    }
}

