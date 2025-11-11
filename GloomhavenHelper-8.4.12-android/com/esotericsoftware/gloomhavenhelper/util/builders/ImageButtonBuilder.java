package com.esotericsoftware.gloomhavenhelper.util.builders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Scaling;
import com.esotericsoftware.gloomhavenhelper.App;

public class ImageButtonBuilder extends ButtonBuilder {
    private Drawable imageChecked;
    private Drawable imageCheckedOver;
    private Drawable imageDisabled;
    private Drawable imageDown;
    private Drawable imageOver;
    private Drawable imageUp;
    private Scaling scaling;

    public ImageButtonBuilder() {
        this.scaling = Scaling.fit;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.builders.ButtonBuilder
    public Button create() {
        return this.create();
    }

    public ImageButton create() {
        ImageButtonStyle imageButton$ImageButtonStyle0 = (ImageButtonStyle)this.set(new ImageButtonStyle());
        imageButton$ImageButtonStyle0.imageUp = this.imageUp;
        imageButton$ImageButtonStyle0.imageDown = this.imageDown;
        imageButton$ImageButtonStyle0.imageOver = this.imageOver;
        imageButton$ImageButtonStyle0.imageChecked = this.imageChecked;
        imageButton$ImageButtonStyle0.imageCheckedOver = this.imageCheckedOver;
        imageButton$ImageButtonStyle0.imageDisabled = this.imageDisabled;
        ImageButton imageButton0 = (ImageButton)this.set(new ImageButton(imageButton$ImageButtonStyle0));
        imageButton0.getImage().setScaling(this.scaling);
        return imageButton0;
    }

    public ImageButtonBuilder imageChecked(Drawable drawable0) {
        this.imageChecked = drawable0;
        return this;
    }

    public ImageButtonBuilder imageChecked(String s) {
        this.imageChecked = App.skin.getDrawable(s);
        return this;
    }

    public ImageButtonBuilder imageChecked(String s, Color color0) {
        this.imageChecked = App.skin.newDrawable(s, color0);
        return this;
    }

    public ImageButtonBuilder imageCheckedOver(Drawable drawable0) {
        this.imageCheckedOver = drawable0;
        return this;
    }

    public ImageButtonBuilder imageCheckedOver(String s) {
        this.imageCheckedOver = App.skin.getDrawable(s);
        return this;
    }

    public ImageButtonBuilder imageCheckedOver(String s, Color color0) {
        this.imageCheckedOver = App.skin.newDrawable(s, color0);
        return this;
    }

    public ImageButtonBuilder imageDisabled(Drawable drawable0) {
        this.imageDisabled = drawable0;
        return this;
    }

    public ImageButtonBuilder imageDisabled(String s) {
        this.imageDisabled = App.skin.getDrawable(s);
        return this;
    }

    public ImageButtonBuilder imageDisabled(String s, Color color0) {
        this.imageDisabled = App.skin.newDrawable(s, color0);
        return this;
    }

    public ImageButtonBuilder imageDown(Drawable drawable0) {
        this.imageDown = drawable0;
        return this;
    }

    public ImageButtonBuilder imageDown(String s) {
        this.imageDown = App.skin.getDrawable(s);
        return this;
    }

    public ImageButtonBuilder imageDown(String s, Color color0) {
        this.imageDown = App.skin.newDrawable(s, color0);
        return this;
    }

    public ImageButtonBuilder imageOver(Drawable drawable0) {
        this.imageOver = drawable0;
        return this;
    }

    public ImageButtonBuilder imageOver(String s) {
        this.imageOver = App.skin.getDrawable(s);
        return this;
    }

    public ImageButtonBuilder imageOver(String s, Color color0) {
        this.imageOver = App.skin.newDrawable(s, color0);
        return this;
    }

    public ImageButtonBuilder imageUp(Drawable drawable0) {
        this.imageUp = drawable0;
        return this;
    }

    public ImageButtonBuilder imageUp(String s) {
        this.imageUp = App.skin.getDrawable(s);
        return this;
    }

    public ImageButtonBuilder imageUp(String s, Color color0) {
        this.imageUp = App.skin.newDrawable(s, color0);
        return this;
    }

    public ImageButtonBuilder scaling(Scaling scaling0) {
        this.scaling = scaling0;
        return this;
    }
}

