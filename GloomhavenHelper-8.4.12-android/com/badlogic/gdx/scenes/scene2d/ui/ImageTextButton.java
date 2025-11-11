package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Scaling;

public class ImageTextButton extends Button {
    public static class ImageTextButtonStyle extends TextButtonStyle {
        @Null
        public Drawable imageChecked;
        @Null
        public Drawable imageCheckedDown;
        @Null
        public Drawable imageCheckedOver;
        @Null
        public Drawable imageDisabled;
        @Null
        public Drawable imageDown;
        @Null
        public Drawable imageOver;
        @Null
        public Drawable imageUp;

        public ImageTextButtonStyle() {
        }

        public ImageTextButtonStyle(ImageTextButtonStyle imageTextButton$ImageTextButtonStyle0) {
            super(imageTextButton$ImageTextButtonStyle0);
            this.imageUp = imageTextButton$ImageTextButtonStyle0.imageUp;
            this.imageDown = imageTextButton$ImageTextButtonStyle0.imageDown;
            this.imageOver = imageTextButton$ImageTextButtonStyle0.imageOver;
            this.imageDisabled = imageTextButton$ImageTextButtonStyle0.imageDisabled;
            this.imageChecked = imageTextButton$ImageTextButtonStyle0.imageChecked;
            this.imageCheckedDown = imageTextButton$ImageTextButtonStyle0.imageCheckedDown;
            this.imageCheckedOver = imageTextButton$ImageTextButtonStyle0.imageCheckedOver;
        }

        public ImageTextButtonStyle(TextButtonStyle textButton$TextButtonStyle0) {
            super(textButton$TextButtonStyle0);
        }

        public ImageTextButtonStyle(@Null Drawable drawable0, @Null Drawable drawable1, @Null Drawable drawable2, BitmapFont bitmapFont0) {
            super(drawable0, drawable1, drawable2, bitmapFont0);
        }
    }

    private final Image image;
    private Label label;
    private ImageTextButtonStyle style;

    public ImageTextButton(@Null String s, ImageTextButtonStyle imageTextButton$ImageTextButtonStyle0) {
        super(imageTextButton$ImageTextButtonStyle0);
        this.style = imageTextButton$ImageTextButtonStyle0;
        this.defaults().space(3.0f);
        this.image = new Image();
        this.image.setScaling(Scaling.fit);
        this.label = new Label(s, new LabelStyle(imageTextButton$ImageTextButtonStyle0.font, imageTextButton$ImageTextButtonStyle0.fontColor));
        this.label.setAlignment(1);
        this.add(this.image);
        this.add(this.label);
        this.setStyle(imageTextButton$ImageTextButtonStyle0);
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
    }

    public ImageTextButton(@Null String s, Skin skin0) {
        this(s, ((ImageTextButtonStyle)skin0.get(ImageTextButtonStyle.class)));
        this.setSkin(skin0);
    }

    public ImageTextButton(@Null String s, Skin skin0, String s1) {
        this(s, ((ImageTextButtonStyle)skin0.get(s1, ImageTextButtonStyle.class)));
        this.setSkin(skin0);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Button
    public void draw(Batch batch0, float f) {
        this.updateImage();
        LabelStyle label$LabelStyle0 = this.label.getStyle();
        label$LabelStyle0.fontColor = this.getFontColor();
        super.draw(batch0, f);
    }

    @Null
    protected Color getFontColor() {
        if(this.isDisabled() && this.style.disabledFontColor != null) {
            return this.style.disabledFontColor;
        }
        if(this.isPressed()) {
            if(this.isChecked() && this.style.checkedDownFontColor != null) {
                return this.style.checkedDownFontColor;
            }
            if(this.style.downFontColor != null) {
                return this.style.downFontColor;
            }
        }
        if(this.isOver()) {
            if(this.isChecked()) {
                if(this.style.checkedOverFontColor != null) {
                    return this.style.checkedOverFontColor;
                }
            }
            else if(this.style.overFontColor != null) {
                return this.style.overFontColor;
            }
        }
        boolean z = this.hasKeyboardFocus();
        if(this.isChecked()) {
            if(z && this.style.checkedFocusedFontColor != null) {
                return this.style.checkedFocusedFontColor;
            }
            if(this.style.checkedFontColor != null) {
                return this.style.checkedFontColor;
            }
            if(this.isOver() && this.style.overFontColor != null) {
                return this.style.overFontColor;
            }
        }
        return !z || this.style.focusedFontColor == null ? this.style.fontColor : this.style.focusedFontColor;
    }

    public Image getImage() {
        return this.image;
    }

    public Cell getImageCell() {
        return this.getCell(this.image);
    }

    @Null
    protected Drawable getImageDrawable() {
        if(this.isDisabled() && this.style.imageDisabled != null) {
            return this.style.imageDisabled;
        }
        if(this.isPressed()) {
            if(this.isChecked() && this.style.imageCheckedDown != null) {
                return this.style.imageCheckedDown;
            }
            if(this.style.imageDown != null) {
                return this.style.imageDown;
            }
        }
        if(this.isOver()) {
            if(this.isChecked()) {
                if(this.style.imageCheckedOver != null) {
                    return this.style.imageCheckedOver;
                }
            }
            else if(this.style.imageOver != null) {
                return this.style.imageOver;
            }
        }
        if(this.isChecked()) {
            if(this.style.imageChecked != null) {
                return this.style.imageChecked;
            }
            return !this.isOver() || this.style.imageOver == null ? this.style.imageUp : this.style.imageOver;
        }
        return this.style.imageUp;
    }

    public Label getLabel() {
        return this.label;
    }

    public Cell getLabelCell() {
        return this.getCell(this.label);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Button
    public ButtonStyle getStyle() {
        return this.getStyle();
    }

    public ImageTextButtonStyle getStyle() {
        return this.style;
    }

    public CharSequence getText() {
        return this.label.getText();
    }

    public void setLabel(Label label0) {
        this.getLabelCell().setActor(label0);
        this.label = label0;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Button
    public void setStyle(ButtonStyle button$ButtonStyle0) {
        if(!(button$ButtonStyle0 instanceof ImageTextButtonStyle)) {
            throw new IllegalArgumentException("style must be a ImageTextButtonStyle.");
        }
        this.style = (ImageTextButtonStyle)button$ButtonStyle0;
        super.setStyle(button$ButtonStyle0);
        if(this.image != null) {
            this.updateImage();
        }
        Label label0 = this.label;
        if(label0 != null) {
            LabelStyle label$LabelStyle0 = label0.getStyle();
            label$LabelStyle0.font = ((ImageTextButtonStyle)button$ButtonStyle0).font;
            label$LabelStyle0.fontColor = this.getFontColor();
            this.label.setStyle(label$LabelStyle0);
        }
    }

    public void setText(CharSequence charSequence0) {
        this.label.setText(charSequence0);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
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
        return (s1.indexOf(36) == -1 ? "" : "ImageTextButton ") + s1 + ": " + this.image.getDrawable() + " " + this.label.getText();
    }

    protected void updateImage() {
        Drawable drawable0 = this.getImageDrawable();
        this.image.setDrawable(drawable0);
    }
}

