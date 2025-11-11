package com.esotericsoftware.gloomhavenhelper.util.builders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.esotericsoftware.gloomhavenhelper.App;

public class CheckBoxBuilder extends TextButtonBuilder {
    private Drawable checkboxOff;
    private Drawable checkboxOffDisabled;
    private Drawable checkboxOn;
    private Drawable checkboxOnDisabled;
    private Drawable checkboxOnOver;
    private Drawable checkboxOver;

    public CheckBoxBuilder checkboxOff(Drawable drawable0) {
        this.checkboxOff = drawable0;
        return this;
    }

    public CheckBoxBuilder checkboxOff(String s) {
        this.checkboxOff = App.skin.getDrawable(s);
        return this;
    }

    public CheckBoxBuilder checkboxOff(String s, Color color0) {
        this.checkboxOff = App.skin.newDrawable(s, color0);
        return this;
    }

    public CheckBoxBuilder checkboxOffDisabled(Drawable drawable0) {
        this.checkboxOffDisabled = drawable0;
        return this;
    }

    public CheckBoxBuilder checkboxOffDisabled(String s) {
        this.checkboxOffDisabled = App.skin.getDrawable(s);
        return this;
    }

    public CheckBoxBuilder checkboxOffDisabled(String s, Color color0) {
        this.checkboxOffDisabled = App.skin.newDrawable(s, color0);
        return this;
    }

    public CheckBoxBuilder checkboxOn(Drawable drawable0) {
        this.checkboxOn = drawable0;
        return this;
    }

    public CheckBoxBuilder checkboxOn(String s) {
        this.checkboxOn = App.skin.getDrawable(s);
        return this;
    }

    public CheckBoxBuilder checkboxOn(String s, Color color0) {
        this.checkboxOn = App.skin.newDrawable(s, color0);
        return this;
    }

    public CheckBoxBuilder checkboxOnDisabled(Drawable drawable0) {
        this.checkboxOnDisabled = drawable0;
        return this;
    }

    public CheckBoxBuilder checkboxOnDisabled(String s) {
        this.checkboxOnDisabled = App.skin.getDrawable(s);
        return this;
    }

    public CheckBoxBuilder checkboxOnDisabled(String s, Color color0) {
        this.checkboxOnDisabled = App.skin.newDrawable(s, color0);
        return this;
    }

    public CheckBoxBuilder checkboxOnOver(Drawable drawable0) {
        this.checkboxOnOver = drawable0;
        return this;
    }

    public CheckBoxBuilder checkboxOnOver(String s) {
        this.checkboxOnOver = App.skin.getDrawable(s);
        return this;
    }

    public CheckBoxBuilder checkboxOnOver(String s, Color color0) {
        this.checkboxOnOver = App.skin.newDrawable(s, color0);
        return this;
    }

    public CheckBoxBuilder checkboxOver(Drawable drawable0) {
        this.checkboxOver = drawable0;
        return this;
    }

    public CheckBoxBuilder checkboxOver(String s) {
        this.checkboxOver = App.skin.getDrawable(s);
        return this;
    }

    public CheckBoxBuilder checkboxOver(String s, Color color0) {
        this.checkboxOver = App.skin.newDrawable(s, color0);
        return this;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.builders.TextButtonBuilder
    public Button create() {
        return this.create();
    }

    public CheckBox create() {
        CheckBoxStyle checkBox$CheckBoxStyle0 = (CheckBoxStyle)this.set(new CheckBoxStyle());
        checkBox$CheckBoxStyle0.checkboxOn = this.checkboxOn;
        checkBox$CheckBoxStyle0.checkboxOff = this.checkboxOff;
        checkBox$CheckBoxStyle0.checkboxOver = this.checkboxOver;
        checkBox$CheckBoxStyle0.checkboxOnOver = this.checkboxOnOver;
        checkBox$CheckBoxStyle0.checkboxOnDisabled = this.checkboxOnDisabled;
        checkBox$CheckBoxStyle0.checkboxOffDisabled = this.checkboxOffDisabled;
        return (CheckBox)this.set(new CheckBox("", checkBox$CheckBoxStyle0));
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.builders.TextButtonBuilder
    public TextButton create() {
        return this.create();
    }
}

