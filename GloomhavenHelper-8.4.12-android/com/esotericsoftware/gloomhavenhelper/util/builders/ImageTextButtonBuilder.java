package com.esotericsoftware.gloomhavenhelper.util.builders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Scaling;
import com.esotericsoftware.gloomhavenhelper.App;

public class ImageTextButtonBuilder extends ButtonBuilder {
    private int align;
    private Color checkedFontColor;
    private Color checkedOverFontColor;
    private Color disabledFontColor;
    private Color downFontColor;
    private BitmapFont font;
    private Color fontColor;
    private Drawable imageChecked;
    private Drawable imageCheckedOver;
    private Drawable imageDisabled;
    private Drawable imageDown;
    private Drawable imageOver;
    private Drawable imageUp;
    private Color overFontColor;
    private Scaling scaling;
    private String text;

    public ImageTextButtonBuilder() {
        this.scaling = Scaling.fit;
    }

    public ImageTextButtonBuilder align(int v) {
        this.align = v;
        return this;
    }

    public ImageTextButtonBuilder checkedFontColor(Color color0) {
        this.checkedFontColor = color0;
        return this;
    }

    public ImageTextButtonBuilder checkedOverFontColor(Color color0) {
        this.checkedOverFontColor = color0;
        return this;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.builders.ButtonBuilder
    public Button create() {
        return this.create();
    }

    public ImageTextButton create() {
        ImageTextButtonStyle imageTextButton$ImageTextButtonStyle0 = (ImageTextButtonStyle)this.set(new ImageTextButtonStyle());
        imageTextButton$ImageTextButtonStyle0.font = this.font;
        imageTextButton$ImageTextButtonStyle0.fontColor = this.fontColor;
        imageTextButton$ImageTextButtonStyle0.downFontColor = this.downFontColor;
        imageTextButton$ImageTextButtonStyle0.overFontColor = this.overFontColor;
        imageTextButton$ImageTextButtonStyle0.checkedFontColor = this.checkedFontColor;
        imageTextButton$ImageTextButtonStyle0.checkedOverFontColor = this.checkedOverFontColor;
        imageTextButton$ImageTextButtonStyle0.disabledFontColor = this.disabledFontColor;
        imageTextButton$ImageTextButtonStyle0.imageUp = this.imageUp;
        imageTextButton$ImageTextButtonStyle0.imageDown = this.imageDown;
        imageTextButton$ImageTextButtonStyle0.imageOver = this.imageOver;
        imageTextButton$ImageTextButtonStyle0.imageChecked = this.imageChecked;
        imageTextButton$ImageTextButtonStyle0.imageCheckedOver = this.imageCheckedOver;
        imageTextButton$ImageTextButtonStyle0.imageDisabled = this.imageDisabled;
        ImageTextButton imageTextButton0 = (ImageTextButton)this.set(new ImageTextButton(this.text, imageTextButton$ImageTextButtonStyle0));
        imageTextButton0.getImage().setScaling(this.scaling);
        return imageTextButton0;
    }

    public ImageTextButtonBuilder disabledFontColor(Color color0) {
        this.disabledFontColor = color0;
        return this;
    }

    public ImageTextButtonBuilder downFontColor(Color color0) {
        this.downFontColor = color0;
        return this;
    }

    public ImageTextButtonBuilder font(BitmapFont bitmapFont0) {
        this.font = bitmapFont0;
        return this;
    }

    public ImageTextButtonBuilder font(String s) {
        this.font = App.skin.getFont(s);
        return this;
    }

    public ImageTextButtonBuilder fontColor(Color color0) {
        this.fontColor = color0;
        return this;
    }

    public ImageTextButtonBuilder imageChecked(Drawable drawable0) {
        this.imageChecked = drawable0;
        return this;
    }

    public ImageTextButtonBuilder imageChecked(String s) {
        this.imageChecked = App.skin.getDrawable(s);
        return this;
    }

    public ImageTextButtonBuilder imageChecked(String s, Color color0) {
        this.imageChecked = App.skin.newDrawable(s, color0);
        return this;
    }

    public ImageTextButtonBuilder imageCheckedOver(Drawable drawable0) {
        this.imageCheckedOver = drawable0;
        return this;
    }

    public ImageTextButtonBuilder imageCheckedOver(String s) {
        this.imageCheckedOver = App.skin.getDrawable(s);
        return this;
    }

    public ImageTextButtonBuilder imageCheckedOver(String s, Color color0) {
        this.imageCheckedOver = App.skin.newDrawable(s, color0);
        return this;
    }

    public ImageTextButtonBuilder imageDisabled(Drawable drawable0) {
        this.imageDisabled = drawable0;
        return this;
    }

    public ImageTextButtonBuilder imageDisabled(String s) {
        this.imageDisabled = App.skin.getDrawable(s);
        return this;
    }

    public ImageTextButtonBuilder imageDisabled(String s, Color color0) {
        this.imageDisabled = App.skin.newDrawable(s, color0);
        return this;
    }

    public ImageTextButtonBuilder imageDown(Drawable drawable0) {
        this.imageDown = drawable0;
        return this;
    }

    public ImageTextButtonBuilder imageDown(String s) {
        this.imageDown = App.skin.getDrawable(s);
        return this;
    }

    public ImageTextButtonBuilder imageDown(String s, Color color0) {
        this.imageDown = App.skin.newDrawable(s, color0);
        return this;
    }

    public ImageTextButtonBuilder imageOver(Drawable drawable0) {
        this.imageOver = drawable0;
        return this;
    }

    public ImageTextButtonBuilder imageOver(String s) {
        this.imageOver = App.skin.getDrawable(s);
        return this;
    }

    public ImageTextButtonBuilder imageOver(String s, Color color0) {
        this.imageOver = App.skin.newDrawable(s, color0);
        return this;
    }

    public ImageTextButtonBuilder imageUp(Drawable drawable0) {
        this.imageUp = drawable0;
        return this;
    }

    public ImageTextButtonBuilder imageUp(String s) {
        this.imageUp = App.skin.getDrawable(s);
        return this;
    }

    public ImageTextButtonBuilder imageUp(String s, Color color0) {
        this.imageUp = App.skin.newDrawable(s, color0);
        return this;
    }

    public ImageTextButtonBuilder overFontColor(Color color0) {
        this.overFontColor = color0;
        return this;
    }

    public ImageTextButtonBuilder scaling(Scaling scaling0) {
        this.scaling = scaling0;
        return this;
    }

    public ImageTextButtonBuilder text(String s) {
        this.text = s;
        return this;
    }
}

