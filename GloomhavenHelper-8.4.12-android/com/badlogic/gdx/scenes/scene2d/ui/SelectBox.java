package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ArraySelection;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.OrderedSet;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class SelectBox extends Widget implements Disableable {
    static class SelectBoxList extends ScrollPane {
        private InputListener hideListener;
        final List list;
        int maxListCount;
        private Actor previousScrollFocus;
        private final SelectBox selectBox;
        private final Vector2 stagePosition;

        public SelectBoxList(SelectBox selectBox0) {
            super(null, selectBox0.style.scrollStyle);
            this.stagePosition = new Vector2();
            this.selectBox = selectBox0;
            this.setOverscroll(false, false);
            this.setFadeScrollBars(false);
            this.setScrollingDisabled(true, false);
            this.list = new List(selectBox0.style.listStyle) {
                @Override  // com.badlogic.gdx.scenes.scene2d.ui.List
                public String toString(Object object0) {
                    return selectBox0.toString(object0);
                }
            };
            this.list.setTouchable(Touchable.disabled);
            this.list.setTypeToSelect(true);
            this.setActor(this.list);
            com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxList.2 selectBox$SelectBoxList$20 = new ClickListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent0, float f, float f1) {
                    Object object0 = SelectBoxList.this.list.getSelected();
                    if(object0 != null) {
                        selectBox0.selection.items().clear();
                    }
                    selectBox0.selection.choose(object0);
                    SelectBoxList.this.hide();
                }

                @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean mouseMoved(InputEvent inputEvent0, float f, float f1) {
                    int v = SelectBoxList.this.list.getItemIndexAt(f1);
                    if(v != -1) {
                        SelectBoxList.this.list.setSelectedIndex(v);
                    }
                    return true;
                }
            };
            this.list.addListener(selectBox$SelectBoxList$20);
            this.addListener(new InputListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
                public void exit(InputEvent inputEvent0, float f, float f1, int v, @Null Actor actor0) {
                    if(actor0 == null || !SelectBoxList.this.isAscendantOf(actor0)) {
                        SelectBoxList.this.list.selection.set(selectBox0.getSelected());
                    }
                }
            });
            this.hideListener = new InputListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean keyDown(InputEvent inputEvent0, int v) {
                    switch(v) {
                        case 0x6F: {
                            break;
                        }
                        case 66: 
                        case 0xA0: {
                            Object object0 = SelectBoxList.this.list.getSelected();
                            selectBox0.selection.choose(object0);
                            break;
                        }
                        default: {
                            return false;
                        }
                    }
                    SelectBoxList.this.hide();
                    inputEvent0.stop();
                    return true;
                }

                @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                    Actor actor0 = inputEvent0.getTarget();
                    if(SelectBoxList.this.isAscendantOf(actor0)) {
                        return false;
                    }
                    SelectBoxList.this.list.selection.set(selectBox0.getSelected());
                    SelectBoxList.this.hide();
                    return false;
                }
            };
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
        public void act(float f) {
            super.act(f);
            this.toFront();
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
        public void draw(Batch batch0, float f) {
            Vector2 vector20 = SelectBox.temp.set(0.0f, 0.0f);
            this.selectBox.localToStageCoordinates(vector20);
            if(!SelectBox.temp.equals(this.stagePosition)) {
                this.hide();
            }
            super.draw(batch0, f);
        }

        public void hide() {
            if(this.list.isTouchable() && this.hasParent()) {
                this.list.setTouchable(Touchable.disabled);
                Stage stage0 = this.getStage();
                if(stage0 != null) {
                    stage0.removeCaptureListener(this.hideListener);
                    stage0.removeListener(this.list.getKeyListener());
                    if(this.previousScrollFocus != null && this.previousScrollFocus.getStage() == null) {
                        this.previousScrollFocus = null;
                    }
                    Actor actor0 = stage0.getScrollFocus();
                    if(actor0 == null || this.isAscendantOf(actor0)) {
                        stage0.setScrollFocus(this.previousScrollFocus);
                    }
                }
                this.clearActions();
                this.selectBox.onHide(this);
            }
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.Group
        protected void setStage(Stage stage0) {
            Stage stage1 = this.getStage();
            if(stage1 != null) {
                stage1.removeCaptureListener(this.hideListener);
                stage1.removeListener(this.list.getKeyListener());
            }
            super.setStage(stage0);
        }

        public void show(Stage stage0) {
            if(this.list.isTouchable()) {
                return;
            }
            stage0.addActor(this);
            stage0.addCaptureListener(this.hideListener);
            stage0.addListener(this.list.getKeyListener());
            Vector2 vector20 = this.stagePosition.set(0.0f, 0.0f);
            this.selectBox.localToStageCoordinates(vector20);
            float f = this.list.getItemHeight();
            float f1 = ((float)(this.maxListCount > 0 ? Math.min(this.maxListCount, this.selectBox.items.size) : this.selectBox.items.size)) * f;
            Drawable drawable0 = this.getStyle().background;
            if(drawable0 != null) {
                f1 += drawable0.getTopHeight() + drawable0.getBottomHeight();
            }
            Drawable drawable1 = this.list.getStyle().background;
            if(drawable1 != null) {
                f1 += drawable1.getTopHeight() + drawable1.getBottomHeight();
            }
            float f2 = this.stagePosition.y;
            float f3 = stage0.getHeight() - f2 - this.selectBox.getHeight();
            boolean z = true;
            if(f1 > f2) {
                if(f3 > f2) {
                    z = false;
                    f1 = Math.min(f1, f3);
                }
                else {
                    f1 = f2;
                }
            }
            if(z) {
                this.setY(this.stagePosition.y - f1);
            }
            else {
                this.setY(this.stagePosition.y + this.selectBox.getHeight());
            }
            this.setX(this.stagePosition.x);
            this.setHeight(f1);
            this.validate();
            float f4 = Math.max(this.getPrefWidth(), this.selectBox.getWidth());
            if(this.getPrefHeight() > f1 && !this.disableY) {
                f4 += this.getScrollBarWidth();
            }
            this.setWidth(f4);
            this.validate();
            this.scrollTo(0.0f, this.list.getHeight() - ((float)this.selectBox.getSelectedIndex()) * f - f / 2.0f, 0.0f, 0.0f, true, true);
            this.updateVisualScroll();
            this.previousScrollFocus = null;
            Actor actor0 = stage0.getScrollFocus();
            if(actor0 != null && !actor0.isDescendantOf(this)) {
                this.previousScrollFocus = actor0;
            }
            stage0.setScrollFocus(this);
            this.list.selection.set(this.selectBox.getSelected());
            this.list.setTouchable(Touchable.enabled);
            this.clearActions();
            this.selectBox.onShow(this, z);
        }
    }

    public static class SelectBoxStyle {
        @Null
        public Drawable background;
        @Null
        public Drawable backgroundDisabled;
        @Null
        public Drawable backgroundOpen;
        @Null
        public Drawable backgroundOver;
        @Null
        public Color disabledFontColor;
        public BitmapFont font;
        public Color fontColor;
        public ListStyle listStyle;
        @Null
        public Color overFontColor;
        public ScrollPaneStyle scrollStyle;

        public SelectBoxStyle() {
            this.fontColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        }

        public SelectBoxStyle(BitmapFont bitmapFont0, Color color0, @Null Drawable drawable0, ScrollPaneStyle scrollPane$ScrollPaneStyle0, ListStyle list$ListStyle0) {
            this.fontColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
            this.font = bitmapFont0;
            this.fontColor.set(color0);
            this.background = drawable0;
            this.scrollStyle = scrollPane$ScrollPaneStyle0;
            this.listStyle = list$ListStyle0;
        }

        public SelectBoxStyle(SelectBoxStyle selectBox$SelectBoxStyle0) {
            this.fontColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
            this.font = selectBox$SelectBoxStyle0.font;
            this.fontColor.set(selectBox$SelectBoxStyle0.fontColor);
            Color color0 = selectBox$SelectBoxStyle0.overFontColor;
            if(color0 != null) {
                this.overFontColor = new Color(color0);
            }
            Color color1 = selectBox$SelectBoxStyle0.disabledFontColor;
            if(color1 != null) {
                this.disabledFontColor = new Color(color1);
            }
            this.background = selectBox$SelectBoxStyle0.background;
            this.scrollStyle = new ScrollPaneStyle(selectBox$SelectBoxStyle0.scrollStyle);
            this.listStyle = new ListStyle(selectBox$SelectBoxStyle0.listStyle);
            this.backgroundOver = selectBox$SelectBoxStyle0.backgroundOver;
            this.backgroundOpen = selectBox$SelectBoxStyle0.backgroundOpen;
            this.backgroundDisabled = selectBox$SelectBoxStyle0.backgroundDisabled;
        }
    }

    private int alignment;
    private ClickListener clickListener;
    boolean disabled;
    final Array items;
    private float prefHeight;
    private float prefWidth;
    SelectBoxList selectBoxList;
    boolean selectedPrefWidth;
    final ArraySelection selection;
    SelectBoxStyle style;
    static final Vector2 temp;

    static {
        SelectBox.temp = new Vector2();
    }

    public SelectBox(SelectBoxStyle selectBox$SelectBoxStyle0) {
        this.items = new Array();
        this.alignment = 8;
        this.selection = new ArraySelection(this.items) {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.Selection
            public boolean fireChangeEvent() {
                if(SelectBox.this.selectedPrefWidth) {
                    SelectBox.this.invalidateHierarchy();
                }
                return super.fireChangeEvent();
            }
        };
        this.setStyle(selectBox$SelectBoxStyle0);
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
        this.selection.setActor(this);
        this.selection.setRequired(true);
        this.selectBoxList = new SelectBoxList(this);
        com.badlogic.gdx.scenes.scene2d.ui.SelectBox.2 selectBox$20 = new ClickListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                if(v == 0 && v1 != 0) {
                    return false;
                }
                if(SelectBox.this.isDisabled()) {
                    return false;
                }
                if(SelectBox.this.selectBoxList.hasParent()) {
                    SelectBox.this.hideList();
                    return true;
                }
                SelectBox.this.showList();
                return true;
            }
        };
        this.clickListener = selectBox$20;
        this.addListener(selectBox$20);
    }

    public SelectBox(Skin skin0) {
        this(((SelectBoxStyle)skin0.get(SelectBoxStyle.class)));
    }

    public SelectBox(Skin skin0, String s) {
        this(((SelectBoxStyle)skin0.get(s, SelectBoxStyle.class)));
    }

    public void clearItems() {
        if(this.items.size == 0) {
            return;
        }
        this.items.clear();
        this.selection.clear();
        this.invalidateHierarchy();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public void draw(Batch batch0, float f) {
        float f11;
        float f10;
        float f9;
        this.validate();
        Drawable drawable0 = this.getBackgroundDrawable();
        Color color0 = this.getFontColor();
        BitmapFont bitmapFont0 = this.style.font;
        Color color1 = this.getColor();
        float f1 = this.getX();
        float f2 = this.getY();
        float f3 = this.getWidth();
        float f4 = this.getHeight();
        batch0.setColor(color1.r, color1.g, color1.b, color1.a * f);
        if(drawable0 != null) {
            drawable0.draw(batch0, f1, f2, f3, f4);
        }
        Object object0 = this.selection.first();
        if(object0 != null) {
            if(drawable0 == null) {
                f9 = f1;
                f10 = f2 + ((float)(((int)(f4 / 2.0f + bitmapFont0.getData().capHeight / 2.0f))));
                f11 = f3;
            }
            else {
                float f5 = drawable0.getLeftWidth();
                float f6 = drawable0.getRightWidth();
                float f7 = drawable0.getBottomHeight();
                float f8 = drawable0.getTopHeight();
                f9 = f1 + drawable0.getLeftWidth();
                f10 = f2 + ((float)(((int)((f4 - (f7 + f8)) / 2.0f + drawable0.getBottomHeight() + bitmapFont0.getData().capHeight / 2.0f))));
                f11 = f3 - (f5 + f6);
            }
            bitmapFont0.setColor(color0.r, color0.g, color0.b, color0.a * f);
            this.drawItem(batch0, bitmapFont0, object0, f9, f10, f11);
        }
    }

    protected GlyphLayout drawItem(Batch batch0, BitmapFont bitmapFont0, Object object0, float f, float f1, float f2) {
        String s = this.toString(object0);
        return bitmapFont0.draw(batch0, s, f, f1, 0, s.length(), f2, this.alignment, false, "...");
    }

    @Null
    protected Drawable getBackgroundDrawable() {
        if(this.isDisabled() && this.style.backgroundDisabled != null) {
            return this.style.backgroundDisabled;
        }
        if(this.selectBoxList.hasParent() && this.style.backgroundOpen != null) {
            return this.style.backgroundOpen;
        }
        return !this.isOver() || this.style.backgroundOver == null ? this.style.background : this.style.backgroundOver;
    }

    public ClickListener getClickListener() {
        return this.clickListener;
    }

    @Null
    protected Color getFontColor() {
        if(this.isDisabled() && this.style.disabledFontColor != null) {
            return this.style.disabledFontColor;
        }
        return this.style.overFontColor == null || !this.isOver() && !this.selectBoxList.hasParent() ? this.style.fontColor : this.style.overFontColor;
    }

    public Array getItems() {
        return this.items;
    }

    public List getList() {
        return this.selectBoxList.list;
    }

    public int getMaxListCount() {
        return this.selectBoxList.maxListCount;
    }

    public float getMaxSelectedPrefWidth() {
        GlyphLayout glyphLayout0 = (GlyphLayout)Pools.get(GlyphLayout.class).obtain();
        float f = 0.0f;
        for(int v = 0; v < this.items.size; ++v) {
            glyphLayout0.setText(this.style.font, this.toString(this.items.get(v)));
            f = Math.max(glyphLayout0.width, f);
        }
        Drawable drawable0 = this.style.background;
        return drawable0 == null ? f : Math.max(f + drawable0.getLeftWidth() + drawable0.getRightWidth(), drawable0.getMinWidth());
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public float getPrefHeight() {
        this.validate();
        return this.prefHeight;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public float getPrefWidth() {
        this.validate();
        return this.prefWidth;
    }

    public ScrollPane getScrollPane() {
        return this.selectBoxList;
    }

    @Null
    public Object getSelected() {
        return this.selection.first();
    }

    public int getSelectedIndex() {
        OrderedSet orderedSet0 = this.selection.items();
        if(orderedSet0.size == 0) {
            return -1;
        }
        Object object0 = orderedSet0.first();
        return this.items.indexOf(object0, false);
    }

    public ArraySelection getSelection() {
        return this.selection;
    }

    public SelectBoxStyle getStyle() {
        return this.style;
    }

    public void hideList() {
        this.selectBoxList.hide();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public boolean isDisabled() {
        return this.disabled;
    }

    public boolean isOver() {
        return this.clickListener.isOver();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public void layout() {
        Drawable drawable0 = this.style.background;
        BitmapFont bitmapFont0 = this.style.font;
        this.prefHeight = drawable0 == null ? bitmapFont0.getCapHeight() - bitmapFont0.getDescent() * 2.0f : Math.max(drawable0.getTopHeight() + drawable0.getBottomHeight() + bitmapFont0.getCapHeight() - bitmapFont0.getDescent() * 2.0f, drawable0.getMinHeight());
        Pool pool0 = Pools.get(GlyphLayout.class);
        GlyphLayout glyphLayout0 = (GlyphLayout)pool0.obtain();
        float f = 0.0f;
        if(this.selectedPrefWidth) {
            this.prefWidth = 0.0f;
            if(drawable0 != null) {
                this.prefWidth = drawable0.getLeftWidth() + drawable0.getRightWidth();
            }
            Object object0 = this.getSelected();
            if(object0 != null) {
                glyphLayout0.setText(bitmapFont0, this.toString(object0));
                this.prefWidth += glyphLayout0.width;
            }
        }
        else {
            float f1 = 0.0f;
            for(int v = 0; v < this.items.size; ++v) {
                glyphLayout0.setText(bitmapFont0, this.toString(this.items.get(v)));
                f1 = Math.max(glyphLayout0.width, f1);
            }
            this.prefWidth = f1;
            if(drawable0 != null) {
                this.prefWidth = Math.max(this.prefWidth + drawable0.getLeftWidth() + drawable0.getRightWidth(), drawable0.getMinWidth());
            }
            ListStyle list$ListStyle0 = this.style.listStyle;
            ScrollPaneStyle scrollPane$ScrollPaneStyle0 = this.style.scrollStyle;
            float f2 = f1 + list$ListStyle0.selection.getLeftWidth() + list$ListStyle0.selection.getRightWidth();
            Drawable drawable1 = scrollPane$ScrollPaneStyle0.background;
            if(drawable1 != null) {
                f2 = Math.max(f2 + drawable1.getLeftWidth() + drawable1.getRightWidth(), drawable1.getMinWidth());
            }
            if(this.selectBoxList == null || !this.selectBoxList.disableY) {
                float f3 = this.style.scrollStyle.vScroll == null ? 0.0f : this.style.scrollStyle.vScroll.getMinWidth();
                if(this.style.scrollStyle.vScrollKnob != null) {
                    f = this.style.scrollStyle.vScrollKnob.getMinWidth();
                }
                f2 += Math.max(f3, f);
            }
            this.prefWidth = Math.max(this.prefWidth, f2);
        }
        pool0.free(glyphLayout0);
    }

    protected void onHide(Actor actor0) {
        actor0.getColor().a = 1.0f;
        actor0.addAction(Actions.sequence(Actions.fadeOut(0.15f, Interpolation.fade), Actions.removeActor()));
    }

    protected void onShow(Actor actor0, boolean z) {
        actor0.getColor().a = 0.0f;
        actor0.addAction(Actions.fadeIn(0.3f, Interpolation.fade));
    }

    public void setAlignment(int v) {
        this.alignment = v;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public void setDisabled(boolean z) {
        if(z && !this.disabled) {
            this.hideList();
        }
        this.disabled = z;
    }

    public void setItems(Array array0) {
        if(array0 == null) {
            throw new IllegalArgumentException("newItems cannot be null.");
        }
        float f = this.getPrefWidth();
        Array array1 = this.items;
        if(array0 != array1) {
            array1.clear();
            this.items.addAll(array0);
        }
        this.selection.validate();
        this.selectBoxList.list.setItems(this.items);
        this.invalidate();
        if(f != this.getPrefWidth()) {
            this.invalidateHierarchy();
        }
    }

    public void setItems(Object[] arr_object) {
        if(arr_object == null) {
            throw new IllegalArgumentException("newItems cannot be null.");
        }
        float f = this.getPrefWidth();
        this.items.clear();
        this.items.addAll(arr_object);
        this.selection.validate();
        this.selectBoxList.list.setItems(this.items);
        this.invalidate();
        if(f != this.getPrefWidth()) {
            this.invalidateHierarchy();
        }
    }

    public void setMaxListCount(int v) {
        this.selectBoxList.maxListCount = v;
    }

    public void setScrollingDisabled(boolean z) {
        this.selectBoxList.setScrollingDisabled(true, z);
        this.invalidateHierarchy();
    }

    public void setSelected(@Null Object object0) {
        if(this.items.contains(object0, false)) {
            this.selection.set(object0);
            return;
        }
        if(this.items.size > 0) {
            Object object1 = this.items.first();
            this.selection.set(object1);
            return;
        }
        this.selection.clear();
    }

    public void setSelectedIndex(int v) {
        Object object0 = this.items.get(v);
        this.selection.set(object0);
    }

    public void setSelectedPrefWidth(boolean z) {
        this.selectedPrefWidth = z;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    protected void setStage(Stage stage0) {
        if(stage0 == null) {
            this.selectBoxList.hide();
        }
        super.setStage(stage0);
    }

    public void setStyle(SelectBoxStyle selectBox$SelectBoxStyle0) {
        if(selectBox$SelectBoxStyle0 == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = selectBox$SelectBoxStyle0;
        SelectBoxList selectBox$SelectBoxList0 = this.selectBoxList;
        if(selectBox$SelectBoxList0 != null) {
            selectBox$SelectBoxList0.setStyle(selectBox$SelectBoxStyle0.scrollStyle);
            this.selectBoxList.list.setStyle(selectBox$SelectBoxStyle0.listStyle);
        }
        this.invalidateHierarchy();
    }

    public void showList() {
        if(this.items.size == 0) {
            return;
        }
        if(this.getStage() != null) {
            this.selectBoxList.show(this.getStage());
        }
    }

    protected String toString(Object object0) {
        return object0.toString();
    }
}

