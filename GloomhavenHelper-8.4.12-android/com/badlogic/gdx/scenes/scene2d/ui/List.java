package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ArraySelection;
import com.badlogic.gdx.scenes.scene2d.utils.Cullable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.OrderedSet;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class List extends Widget implements Cullable {
    public static class ListStyle {
        @Null
        public Drawable background;
        @Null
        public Drawable down;
        public BitmapFont font;
        public Color fontColorSelected;
        public Color fontColorUnselected;
        @Null
        public Drawable over;
        public Drawable selection;

        public ListStyle() {
            this.fontColorSelected = new Color(1.0f, 1.0f, 1.0f, 1.0f);
            this.fontColorUnselected = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        }

        public ListStyle(BitmapFont bitmapFont0, Color color0, Color color1, Drawable drawable0) {
            this.fontColorSelected = new Color(1.0f, 1.0f, 1.0f, 1.0f);
            this.fontColorUnselected = new Color(1.0f, 1.0f, 1.0f, 1.0f);
            this.font = bitmapFont0;
            this.fontColorSelected.set(color0);
            this.fontColorUnselected.set(color1);
            this.selection = drawable0;
        }

        public ListStyle(ListStyle list$ListStyle0) {
            this.fontColorSelected = new Color(1.0f, 1.0f, 1.0f, 1.0f);
            this.fontColorUnselected = new Color(1.0f, 1.0f, 1.0f, 1.0f);
            this.font = list$ListStyle0.font;
            this.fontColorSelected.set(list$ListStyle0.fontColorSelected);
            this.fontColorUnselected.set(list$ListStyle0.fontColorUnselected);
            this.selection = list$ListStyle0.selection;
            this.down = list$ListStyle0.down;
            this.over = list$ListStyle0.over;
            this.background = list$ListStyle0.background;
        }
    }

    private int alignment;
    private Rectangle cullingArea;
    float itemHeight;
    final Array items;
    private InputListener keyListener;
    int overIndex;
    private float prefHeight;
    private float prefWidth;
    int pressedIndex;
    ArraySelection selection;
    ListStyle style;
    boolean typeToSelect;

    public List(ListStyle list$ListStyle0) {
        this.items = new Array();
        this.selection = new ArraySelection(this.items);
        this.alignment = 8;
        this.pressedIndex = -1;
        this.overIndex = -1;
        this.selection.setActor(this);
        this.selection.setRequired(true);
        this.setStyle(list$ListStyle0);
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
        com.badlogic.gdx.scenes.scene2d.ui.List.1 list$10 = new InputListener() {
            String prefix;
            long typeTimeout;

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean keyDown(InputEvent inputEvent0, int v) {
                if(List.this.items.isEmpty()) {
                    return false;
                }
                switch(v) {
                    case 3: {
                        List.this.setSelectedIndex(0);
                        return true;
                    }
                    case 19: {
                        Object object0 = List.this.getSelected();
                        int v1 = List.this.items.indexOf(object0, false) - 1;
                        if(v1 < 0) {
                            v1 = List.this.items.size - 1;
                        }
                        List.this.setSelectedIndex(v1);
                        return true;
                    }
                    case 20: {
                        Object object1 = List.this.getSelected();
                        int v2 = List.this.items.indexOf(object1, false) + 1;
                        if(v2 >= List.this.items.size) {
                            v2 = 0;
                        }
                        List.this.setSelectedIndex(v2);
                        return true;
                    }
                    case 29: {
                        if(UIUtils.ctrl() && List.this.selection.getMultiple()) {
                            List.this.selection.clear();
                            List.this.selection.addAll(List.this.items);
                            return true;
                        }
                        return false;
                    }
                    case 0x6F: {
                        if(List.this.getStage() != null) {
                            List.this.getStage().setKeyboardFocus(null);
                        }
                        return true;
                    }
                    case 0x7B: {
                        List.this.setSelectedIndex(List.this.items.size - 1);
                        return true;
                    }
                    default: {
                        return false;
                    }
                }
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean keyTyped(InputEvent inputEvent0, char c) {
                if(!List.this.typeToSelect) {
                    return false;
                }
                long v = System.currentTimeMillis();
                if(v > this.typeTimeout) {
                    this.prefix = "";
                }
                this.typeTimeout = v + 300L;
                this.prefix = this.prefix + Character.toLowerCase(c);
                int v1 = List.this.items.size;
                for(int v2 = 0; v2 < v1; ++v2) {
                    Object object0 = List.this.items.get(v2);
                    if(List.this.toString(object0).toLowerCase().startsWith(this.prefix)) {
                        List.this.setSelectedIndex(v2);
                        return false;
                    }
                }
                return false;
            }
        };
        this.keyListener = list$10;
        this.addListener(list$10);
        this.addListener(new InputListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public void exit(InputEvent inputEvent0, float f, float f1, int v, Actor actor0) {
                if(v == 0) {
                    List.this.pressedIndex = -1;
                }
                if(v == -1) {
                    List.this.overIndex = -1;
                }
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean mouseMoved(InputEvent inputEvent0, float f, float f1) {
                List.this.overIndex = List.this.getItemIndexAt(f1);
                return false;
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                if(v != 0 || v1 != 0 || List.this.selection.isDisabled()) {
                    return true;
                }
                if(List.this.getStage() != null) {
                    List.this.getStage().setKeyboardFocus(List.this);
                }
                if(List.this.items.size == 0) {
                    return true;
                }
                int v2 = List.this.getItemIndexAt(f1);
                if(v2 == -1) {
                    return true;
                }
                List.this.selection.choose(List.this.items.get(v2));
                List.this.pressedIndex = v2;
                return true;
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent0, float f, float f1, int v) {
                List.this.overIndex = List.this.getItemIndexAt(f1);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchUp(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                if(v == 0 && v1 == 0) {
                    List.this.pressedIndex = -1;
                }
            }
        });
    }

    public List(Skin skin0) {
        this(((ListStyle)skin0.get(ListStyle.class)));
    }

    public List(Skin skin0, String s) {
        this(((ListStyle)skin0.get(s, ListStyle.class)));
    }

    public void clearItems() {
        if(this.items.size == 0) {
            return;
        }
        this.items.clear();
        this.overIndex = -1;
        this.pressedIndex = -1;
        this.selection.clear();
        this.invalidateHierarchy();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public void draw(Batch batch0, float f) {
        int v1;
        Drawable drawable2;
        float f7;
        float f6;
        this.validate();
        this.drawBackground(batch0, f);
        BitmapFont bitmapFont0 = this.style.font;
        Drawable drawable0 = this.style.selection;
        Color color0 = this.style.fontColorSelected;
        Color color1 = this.style.fontColorUnselected;
        Color color2 = this.getColor();
        batch0.setColor(color2.r, color2.g, color2.b, color2.a * f);
        float f1 = this.getX();
        float f2 = this.getY();
        float f3 = this.getWidth();
        float f4 = this.getHeight();
        Drawable drawable1 = this.style.background;
        if(drawable1 == null) {
            f6 = f1;
            f7 = f3;
        }
        else {
            float f5 = drawable1.getLeftWidth();
            f4 -= drawable1.getTopHeight();
            f6 = f1 + f5;
            f7 = f3 - (f5 + drawable1.getRightWidth());
        }
        float f8 = drawable0.getLeftWidth();
        float f9 = drawable0.getRightWidth();
        float f10 = drawable0.getTopHeight();
        float f11 = bitmapFont0.getDescent();
        bitmapFont0.setColor(color1.r, color1.g, color1.b, color1.a * f);
        float f12 = f4;
        for(int v = 0; v < this.items.size; v = v1 + 1) {
            if(this.cullingArea == null || f12 - this.itemHeight <= this.cullingArea.y + this.cullingArea.height && f12 >= this.cullingArea.y) {
                Object object0 = this.items.get(v);
                boolean z = this.selection.contains(object0);
                if(this.pressedIndex == v && this.style.down != null) {
                    drawable2 = this.style.down;
                }
                else if(z) {
                    bitmapFont0.setColor(color0.r, color0.g, color0.b, color0.a * f);
                    drawable2 = drawable0;
                }
                else {
                    drawable2 = this.overIndex != v || this.style.over == null ? null : this.style.over;
                }
                if(drawable2 != null) {
                    drawable2.draw(batch0, f6, f2 + f12 - this.itemHeight, f7, this.itemHeight);
                }
                v1 = v;
                this.drawItem(batch0, bitmapFont0, v, object0, f6 + f8, f2 + f12 - (f10 - f11), f7 - f8 - f9);
                if(z) {
                    bitmapFont0.setColor(color1.r, color1.g, color1.b, color1.a * f);
                }
            }
            else {
                if(f12 < this.cullingArea.y) {
                    break;
                }
                v1 = v;
            }
            f12 -= this.itemHeight;
        }
    }

    protected void drawBackground(Batch batch0, float f) {
        if(this.style.background != null) {
            Color color0 = this.getColor();
            batch0.setColor(color0.r, color0.g, color0.b, color0.a * f);
            this.style.background.draw(batch0, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }
    }

    protected GlyphLayout drawItem(Batch batch0, BitmapFont bitmapFont0, int v, Object object0, float f, float f1, float f2) {
        String s = this.toString(object0);
        return bitmapFont0.draw(batch0, s, f, f1, 0, s.length(), f2, this.alignment, false, "...");
    }

    public Rectangle getCullingArea() {
        return this.cullingArea;
    }

    @Null
    public Object getItemAt(float f) {
        int v = this.getItemIndexAt(f);
        return v == -1 ? null : this.items.get(v);
    }

    public float getItemHeight() {
        return this.itemHeight;
    }

    public int getItemIndexAt(float f) {
        float f1 = this.getHeight();
        Drawable drawable0 = this.style.background;
        if(drawable0 != null) {
            f1 -= drawable0.getTopHeight() + drawable0.getBottomHeight();
            f -= drawable0.getBottomHeight();
        }
        int v = (int)((f1 - f) / this.itemHeight);
        return v < 0 || v >= this.items.size ? -1 : v;
    }

    public Array getItems() {
        return this.items;
    }

    public InputListener getKeyListener() {
        return this.keyListener;
    }

    public Object getOverItem() {
        return this.overIndex == -1 ? null : this.items.get(this.overIndex);
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

    public Object getPressedItem() {
        return this.pressedIndex == -1 ? null : this.items.get(this.pressedIndex);
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

    public ListStyle getStyle() {
        return this.style;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public void layout() {
        BitmapFont bitmapFont0 = this.style.font;
        Drawable drawable0 = this.style.selection;
        this.itemHeight = bitmapFont0.getCapHeight() - bitmapFont0.getDescent() * 2.0f;
        this.itemHeight += drawable0.getTopHeight() + drawable0.getBottomHeight();
        this.prefWidth = 0.0f;
        Pool pool0 = Pools.get(GlyphLayout.class);
        GlyphLayout glyphLayout0 = (GlyphLayout)pool0.obtain();
        for(int v = 0; v < this.items.size; ++v) {
            glyphLayout0.setText(bitmapFont0, this.toString(this.items.get(v)));
            this.prefWidth = Math.max(glyphLayout0.width, this.prefWidth);
        }
        pool0.free(glyphLayout0);
        this.prefWidth += drawable0.getLeftWidth() + drawable0.getRightWidth();
        this.prefHeight = ((float)this.items.size) * this.itemHeight;
        Drawable drawable1 = this.style.background;
        if(drawable1 != null) {
            this.prefWidth = Math.max(this.prefWidth + drawable1.getLeftWidth() + drawable1.getRightWidth(), drawable1.getMinWidth());
            this.prefHeight = Math.max(this.prefHeight + drawable1.getTopHeight() + drawable1.getBottomHeight(), drawable1.getMinHeight());
        }
    }

    public void setAlignment(int v) {
        this.alignment = v;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Cullable
    public void setCullingArea(@Null Rectangle rectangle0) {
        this.cullingArea = rectangle0;
    }

    public void setItems(Array array0) {
        if(array0 == null) {
            throw new IllegalArgumentException("newItems cannot be null.");
        }
        float f = this.getPrefWidth();
        float f1 = this.getPrefHeight();
        Array array1 = this.items;
        if(array0 != array1) {
            array1.clear();
            this.items.addAll(array0);
        }
        this.overIndex = -1;
        this.pressedIndex = -1;
        this.selection.validate();
        this.invalidate();
        if(f != this.getPrefWidth() || f1 != this.getPrefHeight()) {
            this.invalidateHierarchy();
        }
    }

    public void setItems(Object[] arr_object) {
        if(arr_object == null) {
            throw new IllegalArgumentException("newItems cannot be null.");
        }
        float f = this.getPrefWidth();
        float f1 = this.getPrefHeight();
        this.items.clear();
        this.items.addAll(arr_object);
        this.overIndex = -1;
        this.pressedIndex = -1;
        this.selection.validate();
        this.invalidate();
        if(f != this.getPrefWidth() || f1 != this.getPrefHeight()) {
            this.invalidateHierarchy();
        }
    }

    public void setSelected(@Null Object object0) {
        if(this.items.contains(object0, false)) {
            this.selection.set(object0);
            return;
        }
        if(this.selection.getRequired() && this.items.size > 0) {
            this.selection.set(this.items.first());
            return;
        }
        this.selection.clear();
    }

    public void setSelectedIndex(int v) {
        if(v < -1 || v >= this.items.size) {
            throw new IllegalArgumentException("index must be >= -1 and < " + this.items.size + ": " + v);
        }
        if(v == -1) {
            this.selection.clear();
            return;
        }
        this.selection.set(this.items.get(v));
    }

    public void setSelection(ArraySelection arraySelection0) {
        this.selection = arraySelection0;
    }

    public void setStyle(ListStyle list$ListStyle0) {
        if(list$ListStyle0 == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = list$ListStyle0;
        this.invalidateHierarchy();
    }

    public void setTypeToSelect(boolean z) {
        this.typeToSelect = z;
    }

    public String toString(Object object0) {
        return object0.toString();
    }
}

