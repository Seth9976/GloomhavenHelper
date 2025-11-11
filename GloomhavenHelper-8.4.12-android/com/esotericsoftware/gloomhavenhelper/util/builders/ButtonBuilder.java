package com.esotericsoftware.gloomhavenhelper.util.builders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.esotericsoftware.gloomhavenhelper.App;

public class ButtonBuilder {
    private ChangeListener changeListener;
    private Drawable checked;
    private float checkedOffsetX;
    private float checkedOffsetY;
    private Drawable checkedOver;
    private Drawable disabled;
    private Drawable down;
    private boolean isChecked;
    private boolean isDisabled;
    private Drawable over;
    private float pressedOffsetX;
    private float pressedOffsetY;
    private boolean programmaticChangeEvents;
    private int tapSquareSize;
    private float unpressedOffsetX;
    private float unpressedOffsetY;
    private Drawable up;

    public ButtonBuilder() {
        this.tapSquareSize = 28;
    }

    public ButtonBuilder change(ChangeListener changeListener0) {
        this.changeListener = changeListener0;
        return this;
    }

    public ButtonBuilder checked(Drawable drawable0) {
        this.checked = drawable0;
        return this;
    }

    public ButtonBuilder checked(String s) {
        this.checked = App.skin.getDrawable(s);
        return this;
    }

    public ButtonBuilder checked(String s, Color color0) {
        this.checked = App.skin.newDrawable(s, color0);
        return this;
    }

    public ButtonBuilder checked(boolean z) {
        this.isChecked = z;
        return this;
    }

    public ButtonBuilder checkedOffset(float f, float f1) {
        this.checkedOffsetX = f;
        this.checkedOffsetY = f1;
        return this;
    }

    public ButtonBuilder checkedOver(Drawable drawable0) {
        this.checkedOver = drawable0;
        return this;
    }

    public ButtonBuilder checkedOver(String s) {
        this.checkedOver = App.skin.getDrawable(s);
        return this;
    }

    public ButtonBuilder checkedOver(String s, Color color0) {
        this.checkedOver = App.skin.newDrawable(s, color0);
        return this;
    }

    public Button create() {
        return this.set(new Button(this.set(new ButtonStyle())));
    }

    public ButtonBuilder disabled(Drawable drawable0) {
        this.disabled = drawable0;
        return this;
    }

    public ButtonBuilder disabled(String s) {
        this.disabled = App.skin.getDrawable(s);
        return this;
    }

    public ButtonBuilder disabled(String s, Color color0) {
        this.disabled = App.skin.newDrawable(s, color0);
        return this;
    }

    public ButtonBuilder disabled(boolean z) {
        this.isDisabled = z;
        return this;
    }

    public ButtonBuilder down(Drawable drawable0) {
        this.down = drawable0;
        return this;
    }

    public ButtonBuilder down(String s) {
        this.down = App.skin.getDrawable(s);
        return this;
    }

    public ButtonBuilder down(String s, Color color0) {
        this.down = App.skin.newDrawable(s, color0);
        return this;
    }

    public ButtonBuilder over(Drawable drawable0) {
        this.over = drawable0;
        return this;
    }

    public ButtonBuilder over(String s) {
        this.over = App.skin.getDrawable(s);
        return this;
    }

    public ButtonBuilder over(String s, Color color0) {
        this.over = App.skin.newDrawable(s, color0);
        return this;
    }

    public ButtonBuilder pressedOffset(float f, float f1) {
        this.pressedOffsetX = f;
        this.pressedOffsetY = f1;
        return this;
    }

    public ButtonBuilder programmaticChangeEvents(boolean z) {
        this.programmaticChangeEvents = z;
        return this;
    }

    public ButtonStyle set(ButtonStyle button$ButtonStyle0) {
        button$ButtonStyle0.up = this.up;
        button$ButtonStyle0.down = this.down;
        button$ButtonStyle0.over = this.over;
        button$ButtonStyle0.checked = this.checked;
        button$ButtonStyle0.checkedOver = this.checkedOver;
        button$ButtonStyle0.disabled = this.disabled;
        button$ButtonStyle0.pressedOffsetX = this.pressedOffsetX;
        button$ButtonStyle0.pressedOffsetY = this.pressedOffsetY;
        button$ButtonStyle0.unpressedOffsetX = this.unpressedOffsetX;
        button$ButtonStyle0.unpressedOffsetY = this.unpressedOffsetY;
        button$ButtonStyle0.checkedOffsetX = this.checkedOffsetX;
        button$ButtonStyle0.checkedOffsetY = this.checkedOffsetY;
        return button$ButtonStyle0;
    }

    public Button set(Button button0) {
        button0.getClickListener().setTapSquareSize(((float)this.tapSquareSize));
        button0.setProgrammaticChangeEvents(this.programmaticChangeEvents);
        button0.setDisabled(this.isDisabled);
        button0.setChecked(this.isChecked);
        ChangeListener changeListener0 = this.changeListener;
        if(changeListener0 != null) {
            button0.addListener(changeListener0);
        }
        return button0;
    }

    public ButtonBuilder tapSquareSize(int v) {
        this.tapSquareSize = v;
        return this;
    }

    public ButtonBuilder unpressedOffset(float f, float f1) {
        this.unpressedOffsetX = f;
        this.unpressedOffsetY = f1;
        return this;
    }

    public ButtonBuilder up(Drawable drawable0) {
        this.up = drawable0;
        return this;
    }

    public ButtonBuilder up(String s) {
        this.up = App.skin.getDrawable(s);
        return this;
    }

    public ButtonBuilder up(String s, Color color0) {
        this.up = App.skin.newDrawable(s, color0);
        return this;
    }
}

