package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;

public class Label extends Widget {
    public static class LabelStyle {
        @Null
        public Drawable background;
        public BitmapFont font;
        @Null
        public Color fontColor;

        public LabelStyle() {
        }

        public LabelStyle(BitmapFont bitmapFont0, @Null Color color0) {
            this.font = bitmapFont0;
            this.fontColor = color0;
        }

        public LabelStyle(LabelStyle label$LabelStyle0) {
            this.font = label$LabelStyle0.font;
            Color color0 = label$LabelStyle0.fontColor;
            if(color0 != null) {
                this.fontColor = new Color(color0);
            }
            this.background = label$LabelStyle0.background;
        }
    }

    private BitmapFontCache cache;
    @Null
    private String ellipsis;
    private boolean fontScaleChanged;
    private float fontScaleX;
    private float fontScaleY;
    private int intValue;
    private int labelAlign;
    private float lastPrefHeight;
    private final GlyphLayout layout;
    private int lineAlign;
    private final Vector2 prefSize;
    private boolean prefSizeInvalid;
    private static final GlyphLayout prefSizeLayout;
    private LabelStyle style;
    private static final Color tempColor;
    private final StringBuilder text;
    private boolean wrap;

    static {
        Label.tempColor = new Color();
        Label.prefSizeLayout = new GlyphLayout();
    }

    public Label(@Null CharSequence charSequence0, LabelStyle label$LabelStyle0) {
        this.layout = new GlyphLayout();
        this.prefSize = new Vector2();
        this.text = new StringBuilder();
        this.intValue = 0x80000000;
        this.labelAlign = 8;
        this.lineAlign = 8;
        this.prefSizeInvalid = true;
        this.fontScaleX = 1.0f;
        this.fontScaleY = 1.0f;
        this.fontScaleChanged = false;
        if(charSequence0 != null) {
            this.text.append(charSequence0);
        }
        this.setStyle(label$LabelStyle0);
        if(charSequence0 != null && charSequence0.length() > 0) {
            this.setSize(this.getPrefWidth(), this.getPrefHeight());
        }
    }

    public Label(@Null CharSequence charSequence0, Skin skin0) {
        this(charSequence0, ((LabelStyle)skin0.get(LabelStyle.class)));
    }

    public Label(@Null CharSequence charSequence0, Skin skin0, String s) {
        this(charSequence0, ((LabelStyle)skin0.get(s, LabelStyle.class)));
    }

    public Label(@Null CharSequence charSequence0, Skin skin0, String s, Color color0) {
        this(charSequence0, new LabelStyle(skin0.getFont(s), color0));
    }

    public Label(@Null CharSequence charSequence0, Skin skin0, String s, String s1) {
        this(charSequence0, new LabelStyle(skin0.getFont(s), skin0.getColor(s1)));
    }

    private void computePrefSize() {
        this.prefSizeInvalid = false;
        GlyphLayout glyphLayout0 = Label.prefSizeLayout;
        if(!this.wrap || this.ellipsis != null) {
            glyphLayout0.setText(this.cache.getFont(), this.text);
        }
        else {
            float f = this.getWidth();
            float f1 = this.style.background == null ? f : Math.max(f, this.style.background.getMinWidth()) - this.style.background.getLeftWidth() - this.style.background.getRightWidth();
            glyphLayout0.setText(this.cache.getFont(), this.text, Color.WHITE, f1, 8, true);
        }
        this.prefSize.set(glyphLayout0.width, glyphLayout0.height);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public void draw(Batch batch0, float f) {
        this.validate();
        Color color0 = this.getColor();
        Color color1 = Label.tempColor.set(color0);
        color1.a *= f;
        if(this.style.background != null) {
            batch0.setColor(color1.r, color1.g, color1.b, color1.a);
            this.style.background.draw(batch0, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }
        if(this.style.fontColor != null) {
            color1.mul(this.style.fontColor);
        }
        this.cache.tint(color1);
        this.cache.setPosition(this.getX(), this.getY());
        this.cache.draw(batch0);
    }

    protected BitmapFontCache getBitmapFontCache() {
        return this.cache;
    }

    public float getFontScaleX() {
        return this.fontScaleX;
    }

    public float getFontScaleY() {
        return this.fontScaleY;
    }

    public GlyphLayout getGlyphLayout() {
        return this.layout;
    }

    public int getLabelAlign() {
        return this.labelAlign;
    }

    public int getLineAlign() {
        return this.lineAlign;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public float getPrefHeight() {
        if(this.prefSizeInvalid) {
            this.scaleAndComputePrefSize();
        }
        float f = this.prefSize.y - this.style.font.getDescent() * (this.fontScaleChanged ? this.fontScaleY / this.style.font.getScaleY() : 1.0f) * 2.0f;
        Drawable drawable0 = this.style.background;
        return drawable0 == null ? f : Math.max(f + drawable0.getTopHeight() + drawable0.getBottomHeight(), drawable0.getMinHeight());
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public float getPrefWidth() {
        if(this.wrap) {
            return 0.0f;
        }
        if(this.prefSizeInvalid) {
            this.scaleAndComputePrefSize();
        }
        float f = this.prefSize.x;
        Drawable drawable0 = this.style.background;
        return drawable0 == null ? f : Math.max(f + drawable0.getLeftWidth() + drawable0.getRightWidth(), drawable0.getMinWidth());
    }

    public LabelStyle getStyle() {
        return this.style;
    }

    public StringBuilder getText() {
        return this.text;
    }

    public boolean getWrap() {
        return this.wrap;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public void invalidate() {
        super.invalidate();
        this.prefSizeInvalid = true;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public void layout() {
        float f15;
        float f13;
        float f12;
        GlyphLayout glyphLayout1;
        float f11;
        float f10;
        float f9;
        float f8;
        float f7;
        BitmapFont bitmapFont0 = this.cache.getFont();
        float f = bitmapFont0.getScaleX();
        float f1 = bitmapFont0.getScaleY();
        if(this.fontScaleChanged) {
            bitmapFont0.getData().setScale(this.fontScaleX, this.fontScaleY);
        }
        boolean z = this.wrap && this.ellipsis == null;
        if(z) {
            float f2 = this.getPrefHeight();
            if(f2 != this.lastPrefHeight) {
                this.lastPrefHeight = f2;
                this.invalidateHierarchy();
            }
        }
        float f3 = this.getWidth();
        float f4 = this.getHeight();
        Drawable drawable0 = this.style.background;
        if(drawable0 == null) {
            f7 = f3;
            f8 = f4;
            f9 = 0.0f;
            f10 = 0.0f;
        }
        else {
            float f5 = drawable0.getLeftWidth();
            float f6 = drawable0.getBottomHeight();
            f7 = f3 - (drawable0.getLeftWidth() + drawable0.getRightWidth());
            f8 = f4 - (drawable0.getBottomHeight() + drawable0.getTopHeight());
            f9 = f5;
            f10 = f6;
        }
        GlyphLayout glyphLayout0 = this.layout;
        if(z || this.text.indexOf("\n") != -1) {
            glyphLayout1 = glyphLayout0;
            glyphLayout0.setText(bitmapFont0, this.text, 0, this.text.length, Color.WHITE, f7, this.lineAlign, z, this.ellipsis);
            float f14 = glyphLayout1.width;
            f11 = glyphLayout1.height;
            int v = this.labelAlign;
            if((v & 8) != 0) {
                f12 = f14;
                f13 = f9;
            }
            else if((v & 16) != 0) {
                f12 = f14;
                f13 = f9 + (f7 - f14);
            }
            else {
                f12 = f14;
                f13 = f9 + (f7 - f14) / 2.0f;
            }
        }
        else {
            f11 = bitmapFont0.getData().capHeight;
            glyphLayout1 = glyphLayout0;
            f12 = f7;
            f13 = f9;
        }
        int v1 = this.labelAlign;
        if((v1 & 2) == 0) {
            f15 = (v1 & 4) == 0 ? f10 + (f8 - f11) / 2.0f : f10 + (this.cache.getFont().isFlipped() ? f8 - f11 : 0.0f) - this.style.font.getDescent();
        }
        else {
            f15 = f10 + (this.cache.getFont().isFlipped() ? 0.0f : f8 - f11) + this.style.font.getDescent();
        }
        if(!this.cache.getFont().isFlipped()) {
            f15 += f11;
        }
        glyphLayout1.setText(bitmapFont0, this.text, 0, this.text.length, Color.WHITE, f12, this.lineAlign, z, this.ellipsis);
        this.cache.setText(glyphLayout1, f13, f15);
        if(this.fontScaleChanged) {
            bitmapFont0.getData().setScale(f, f1);
        }
    }

    private void scaleAndComputePrefSize() {
        BitmapFont bitmapFont0 = this.cache.getFont();
        float f = bitmapFont0.getScaleX();
        float f1 = bitmapFont0.getScaleY();
        if(this.fontScaleChanged) {
            bitmapFont0.getData().setScale(this.fontScaleX, this.fontScaleY);
        }
        this.computePrefSize();
        if(this.fontScaleChanged) {
            bitmapFont0.getData().setScale(f, f1);
        }
    }

    public void setAlignment(int v) {
        this.setAlignment(v, v);
    }

    public void setAlignment(int v, int v1) {
        this.labelAlign = v;
        if((v1 & 8) != 0) {
            this.lineAlign = 8;
        }
        else if((v1 & 16) == 0) {
            this.lineAlign = 1;
        }
        else {
            this.lineAlign = 16;
        }
        this.invalidate();
    }

    public void setEllipsis(@Null String s) {
        this.ellipsis = s;
    }

    public void setEllipsis(boolean z) {
        if(z) {
            this.ellipsis = "...";
            return;
        }
        this.ellipsis = null;
    }

    public void setFontScale(float f) {
        this.setFontScale(f, f);
    }

    public void setFontScale(float f, float f1) {
        this.fontScaleChanged = true;
        this.fontScaleX = f;
        this.fontScaleY = f1;
        this.invalidateHierarchy();
    }

    public void setFontScaleX(float f) {
        this.setFontScale(f, this.fontScaleY);
    }

    public void setFontScaleY(float f) {
        this.setFontScale(this.fontScaleX, f);
    }

    public void setStyle(LabelStyle label$LabelStyle0) {
        if(label$LabelStyle0 == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        if(label$LabelStyle0.font == null) {
            throw new IllegalArgumentException("Missing LabelStyle font.");
        }
        this.style = label$LabelStyle0;
        this.cache = label$LabelStyle0.font.newFontCache();
        this.invalidateHierarchy();
    }

    public void setText(@Null CharSequence charSequence0) {
        if(charSequence0 == null) {
            if(this.text.length == 0) {
                return;
            }
            this.text.clear();
        }
        else if(charSequence0 instanceof StringBuilder) {
            if(this.text.equals(charSequence0)) {
                return;
            }
            this.text.clear();
            this.text.append(((StringBuilder)charSequence0));
        }
        else {
            if(this.textEquals(charSequence0)) {
                return;
            }
            this.text.clear();
            this.text.append(charSequence0);
        }
        this.intValue = 0x80000000;
        this.invalidateHierarchy();
    }

    public boolean setText(int v) {
        if(this.intValue == v) {
            return false;
        }
        this.text.clear();
        this.text.append(v);
        this.intValue = v;
        this.invalidateHierarchy();
        return true;
    }

    public void setWrap(boolean z) {
        this.wrap = z;
        this.invalidateHierarchy();
    }

    public boolean textEquals(CharSequence charSequence0) {
        int v = this.text.length;
        char[] arr_c = this.text.chars;
        if(v != charSequence0.length()) {
            return false;
        }
        for(int v1 = 0; v1 < v; ++v1) {
            if(arr_c[v1] != charSequence0.charAt(v1)) {
                return false;
            }
        }
        return true;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public String toString() {
        String s = this.getName();
        if(s != null) {
            return s;
        }
        String s1 = this.getClass().getName();
        int v = s1.lastIndexOf(46);
        if(v != -1) {
            s1 = s1.substring(v + 1);
        }
        return (s1.indexOf(36) == -1 ? "" : "Label ") + s1 + ": " + this.text;
    }
}

