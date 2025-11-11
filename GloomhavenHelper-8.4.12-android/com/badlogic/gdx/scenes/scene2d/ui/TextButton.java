package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;

public class TextButton extends Button {
    public static class TextButtonStyle extends ButtonStyle {
        @Null
        public Color checkedDownFontColor;
        @Null
        public Color checkedFocusedFontColor;
        @Null
        public Color checkedFontColor;
        @Null
        public Color checkedOverFontColor;
        @Null
        public Color disabledFontColor;
        @Null
        public Color downFontColor;
        @Null
        public Color focusedFontColor;
        public BitmapFont font;
        @Null
        public Color fontColor;
        @Null
        public Color overFontColor;

        public TextButtonStyle() {
        }

        public TextButtonStyle(TextButtonStyle textButton$TextButtonStyle0) {
            super(textButton$TextButtonStyle0);
            this.font = textButton$TextButtonStyle0.font;
            Color color0 = textButton$TextButtonStyle0.fontColor;
            if(color0 != null) {
                this.fontColor = new Color(color0);
            }
            Color color1 = textButton$TextButtonStyle0.downFontColor;
            if(color1 != null) {
                this.downFontColor = new Color(color1);
            }
            Color color2 = textButton$TextButtonStyle0.overFontColor;
            if(color2 != null) {
                this.overFontColor = new Color(color2);
            }
            Color color3 = textButton$TextButtonStyle0.focusedFontColor;
            if(color3 != null) {
                this.focusedFontColor = new Color(color3);
            }
            Color color4 = textButton$TextButtonStyle0.disabledFontColor;
            if(color4 != null) {
                this.disabledFontColor = new Color(color4);
            }
            Color color5 = textButton$TextButtonStyle0.checkedFontColor;
            if(color5 != null) {
                this.checkedFontColor = new Color(color5);
            }
            Color color6 = textButton$TextButtonStyle0.checkedDownFontColor;
            if(color6 != null) {
                this.checkedDownFontColor = new Color(color6);
            }
            Color color7 = textButton$TextButtonStyle0.checkedOverFontColor;
            if(color7 != null) {
                this.checkedOverFontColor = new Color(color7);
            }
            Color color8 = textButton$TextButtonStyle0.checkedFocusedFontColor;
            if(color8 != null) {
                this.checkedFocusedFontColor = new Color(color8);
            }
        }

        public TextButtonStyle(@Null Drawable drawable0, @Null Drawable drawable1, @Null Drawable drawable2, @Null BitmapFont bitmapFont0) {
            super(drawable0, drawable1, drawable2);
            this.font = bitmapFont0;
        }
    }

    private Label label;
    private TextButtonStyle style;

    public TextButton(@Null String s, Skin skin0) {
        this(s, ((TextButtonStyle)skin0.get(TextButtonStyle.class)));
        this.setSkin(skin0);
    }

    public TextButton(@Null String s, Skin skin0, String s1) {
        this(s, ((TextButtonStyle)skin0.get(s1, TextButtonStyle.class)));
        this.setSkin(skin0);
    }

    public TextButton(@Null String s, TextButtonStyle textButton$TextButtonStyle0) {
        this.setStyle(textButton$TextButtonStyle0);
        this.label = new Label(s, new LabelStyle(textButton$TextButtonStyle0.font, textButton$TextButtonStyle0.fontColor));
        this.label.setAlignment(1);
        this.add(this.label).expand().fill();
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Button
    public void draw(Batch batch0, float f) {
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

    public TextButtonStyle getStyle() {
        return this.style;
    }

    public CharSequence getText() {
        return this.label.getText();
    }

    public void setLabel(Label label0) {
        if(label0 == null) {
            throw new IllegalArgumentException("label cannot be null.");
        }
        this.getLabelCell().setActor(label0);
        this.label = label0;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Button
    public void setStyle(ButtonStyle button$ButtonStyle0) {
        if(button$ButtonStyle0 == null) {
            throw new NullPointerException("style cannot be null");
        }
        if(!(button$ButtonStyle0 instanceof TextButtonStyle)) {
            throw new IllegalArgumentException("style must be a TextButtonStyle.");
        }
        this.style = (TextButtonStyle)button$ButtonStyle0;
        super.setStyle(button$ButtonStyle0);
        Label label0 = this.label;
        if(label0 != null) {
            LabelStyle label$LabelStyle0 = label0.getStyle();
            label$LabelStyle0.font = ((TextButtonStyle)button$ButtonStyle0).font;
            label$LabelStyle0.fontColor = ((TextButtonStyle)button$ButtonStyle0).fontColor;
            this.label.setStyle(label$LabelStyle0);
        }
    }

    public void setText(@Null String s) {
        this.label.setText(s);
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
        return (s1.indexOf(36) == -1 ? "" : "TextButton ") + s1 + ": " + this.label.getText();
    }
}

