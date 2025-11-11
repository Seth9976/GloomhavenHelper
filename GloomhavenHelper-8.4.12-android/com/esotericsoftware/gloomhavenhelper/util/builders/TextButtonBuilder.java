package com.esotericsoftware.gloomhavenhelper.util.builders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.esotericsoftware.gloomhavenhelper.App;

public class TextButtonBuilder extends ButtonBuilder {
    private int align;
    private Color checkedFontColor;
    private Color checkedOverFontColor;
    private Color disabledFontColor;
    private Color downFontColor;
    private BitmapFont font;
    private Color fontColor;
    private Color overFontColor;
    private String text;

    public TextButtonBuilder align(int v) {
        this.align = v;
        return this;
    }

    public TextButtonBuilder checkedFontColor(Color color0) {
        this.checkedFontColor = color0;
        return this;
    }

    public TextButtonBuilder checkedOverFontColor(Color color0) {
        this.checkedOverFontColor = color0;
        return this;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.builders.ButtonBuilder
    public Button create() {
        return this.create();
    }

    public TextButton create() {
        return this.set(new TextButton("", this.set(new TextButtonStyle())));
    }

    public TextButtonBuilder disabledFontColor(Color color0) {
        this.disabledFontColor = color0;
        return this;
    }

    public TextButtonBuilder downFontColor(Color color0) {
        this.downFontColor = color0;
        return this;
    }

    public TextButtonBuilder font(BitmapFont bitmapFont0) {
        this.font = bitmapFont0;
        return this;
    }

    public TextButtonBuilder font(String s) {
        this.font = App.skin.getFont(s);
        return this;
    }

    public TextButtonBuilder fontColor(Color color0) {
        this.fontColor = color0;
        return this;
    }

    public TextButtonBuilder overFontColor(Color color0) {
        this.overFontColor = color0;
        return this;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.builders.ButtonBuilder
    public Button set(Button button0) {
        return this.set(((TextButton)button0));
    }

    public TextButtonStyle set(TextButtonStyle textButton$TextButtonStyle0) {
        textButton$TextButtonStyle0.font = this.font;
        textButton$TextButtonStyle0.fontColor = this.fontColor;
        textButton$TextButtonStyle0.downFontColor = this.downFontColor;
        textButton$TextButtonStyle0.overFontColor = this.overFontColor;
        textButton$TextButtonStyle0.checkedFontColor = this.checkedFontColor;
        textButton$TextButtonStyle0.checkedOverFontColor = this.checkedOverFontColor;
        textButton$TextButtonStyle0.disabledFontColor = this.disabledFontColor;
        return (TextButtonStyle)super.set(textButton$TextButtonStyle0);
    }

    public TextButton set(TextButton textButton0) {
        super.set(textButton0);
        textButton0.setText(this.text);
        textButton0.getLabel().setAlignment(this.align);
        return textButton0;
    }

    public TextButtonBuilder text(String s) {
        this.text = s;
        return this;
    }
}

