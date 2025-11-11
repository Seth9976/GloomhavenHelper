package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Scaling;

public class CheckBox extends TextButton {
    public static class CheckBoxStyle extends TextButtonStyle {
        public Drawable checkboxOff;
        @Null
        public Drawable checkboxOffDisabled;
        public Drawable checkboxOn;
        @Null
        public Drawable checkboxOnDisabled;
        @Null
        public Drawable checkboxOnOver;
        @Null
        public Drawable checkboxOver;

        public CheckBoxStyle() {
        }

        public CheckBoxStyle(CheckBoxStyle checkBox$CheckBoxStyle0) {
            super(checkBox$CheckBoxStyle0);
            this.checkboxOff = checkBox$CheckBoxStyle0.checkboxOff;
            this.checkboxOn = checkBox$CheckBoxStyle0.checkboxOn;
            this.checkboxOnOver = checkBox$CheckBoxStyle0.checkboxOnOver;
            this.checkboxOver = checkBox$CheckBoxStyle0.checkboxOver;
            this.checkboxOnDisabled = checkBox$CheckBoxStyle0.checkboxOnDisabled;
            this.checkboxOffDisabled = checkBox$CheckBoxStyle0.checkboxOffDisabled;
        }

        public CheckBoxStyle(Drawable drawable0, Drawable drawable1, BitmapFont bitmapFont0, @Null Color color0) {
            this.checkboxOff = drawable0;
            this.checkboxOn = drawable1;
            this.font = bitmapFont0;
            this.fontColor = color0;
        }
    }

    private Image image;
    private Cell imageCell;
    private CheckBoxStyle style;

    public CheckBox(@Null String s, CheckBoxStyle checkBox$CheckBoxStyle0) {
        super(s, checkBox$CheckBoxStyle0);
        this.clearChildren();
        Label label0 = this.getLabel();
        Image image0 = new Image(checkBox$CheckBoxStyle0.checkboxOff, Scaling.none);
        this.image = image0;
        this.imageCell = this.add(image0);
        this.add(label0);
        label0.setAlignment(8);
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
    }

    public CheckBox(@Null String s, Skin skin0) {
        this(s, ((CheckBoxStyle)skin0.get(CheckBoxStyle.class)));
    }

    public CheckBox(@Null String s, Skin skin0, String s1) {
        this(s, ((CheckBoxStyle)skin0.get(s1, CheckBoxStyle.class)));
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextButton
    public void draw(Batch batch0, float f) {
        Drawable drawable0;
        if(!this.isDisabled()) {
            drawable0 = null;
        }
        else if(this.isChecked && this.style.checkboxOnDisabled != null) {
            drawable0 = this.style.checkboxOnDisabled;
        }
        else {
            drawable0 = this.style.checkboxOffDisabled;
        }
        if(drawable0 == null) {
            boolean z = this.isOver() && !this.isDisabled();
            if(!this.isChecked || this.style.checkboxOn == null) {
                drawable0 = !z || this.style.checkboxOver == null ? this.style.checkboxOff : this.style.checkboxOver;
            }
            else if(z && this.style.checkboxOnOver != null) {
                drawable0 = this.style.checkboxOnOver;
            }
            else {
                drawable0 = this.style.checkboxOn;
            }
        }
        this.image.setDrawable(drawable0);
        super.draw(batch0, f);
    }

    public Image getImage() {
        return this.image;
    }

    public Cell getImageCell() {
        return this.imageCell;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextButton
    public ButtonStyle getStyle() {
        return this.getStyle();
    }

    public CheckBoxStyle getStyle() {
        return this.style;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextButton
    public TextButtonStyle getStyle() {
        return this.getStyle();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextButton
    public void setStyle(ButtonStyle button$ButtonStyle0) {
        if(!(button$ButtonStyle0 instanceof CheckBoxStyle)) {
            throw new IllegalArgumentException("style must be a CheckBoxStyle.");
        }
        this.style = (CheckBoxStyle)button$ButtonStyle0;
        super.setStyle(button$ButtonStyle0);
    }
}

