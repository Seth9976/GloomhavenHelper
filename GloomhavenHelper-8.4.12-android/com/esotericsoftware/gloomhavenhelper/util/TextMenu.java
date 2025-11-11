package com.esotericsoftware.gloomhavenhelper.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.esotericsoftware.gloomhavenhelper.App;
import com.esotericsoftware.gloomhavenhelper.util.builders.ImageTextButtonBuilder;
import com.esotericsoftware.gloomhavenhelper.util.builders.TextButtonBuilder;

public class TextMenu extends Menu {
    private final ScrollPane scroll;
    public final Table table;

    public TextMenu() {
        this.table = new Table();
        this.table.defaults().growX();
        this.scroll = new GloomScrollPane(this.table);
        this.scroll.getStyle().vScrollKnob = App.skin.getDrawable("scroll-vert");
        this.scroll.setFadeScrollBars(false);
        this.scroll.setupFadeScrollBars(0.4f, 0.0f);
        this.scroll.setScrollBarTouch(false);
        this.scroll.setScrollingDisabled(true, false);
        this.scroll.setFlickScrollTapSquareSize(35.0f);
        this.add(this.scroll).grow().fill();
    }

    public Cell addCheckBoxItem(String s, boolean z, ChangeListener changeListener0) {
        CheckBox checkBox0 = this.checkBoxItem(s, z, changeListener0);
        return this.table.add(checkBox0);
    }

    public Cell addImageTextItem(String s, String s1, ChangeListener changeListener0) {
        ImageTextButton imageTextButton0 = ((ImageTextButtonBuilder)App.imageTextButton(s).imageUp(s1, App.darken).imageOver(s1).imageDisabled(s1, App.disabledDim).change(changeListener0)).align(8).create();
        imageTextButton0.pad(0.0f, 20.0f, 0.0f, 20.0f);
        imageTextButton0.getLabelCell().height(80.0f);
        imageTextButton0.invalidate();
        return this.table.add(imageTextButton0);
    }

    public Cell addSeperator() {
        Container container0 = this.seperator();
        return this.table.add(container0);
    }

    public Cell addTextItem(String s, ChangeListener changeListener0) {
        TextButton textButton0 = this.textItem(s, changeListener0);
        return this.table.add(textButton0);
    }

    public CheckBox checkBoxItem(String s, boolean z, ChangeListener changeListener0) {
        CheckBox checkBox0 = App.checkbox(s);
        checkBox0.setChecked(z);
        checkBox0.pad(0.0f, 20.0f, 0.0f, 20.0f);
        checkBox0.getLabelCell().height(80.0f);
        checkBox0.invalidate();
        if(changeListener0 != null) {
            checkBox0.addListener(changeListener0);
        }
        return checkBox0;
    }

    public CheckBox radioItem(String s, boolean z, ChangeListener changeListener0) {
        CheckBox checkBox0 = App.radio(s);
        checkBox0.setChecked(z);
        checkBox0.pad(0.0f, 20.0f, 0.0f, 20.0f);
        checkBox0.getLabelCell().height(80.0f);
        checkBox0.invalidate();
        if(changeListener0 != null) {
            checkBox0.addListener(changeListener0);
        }
        return checkBox0;
    }

    public Container seperator() {
        return new Container(new Image(App.drawable("white", App.buttonGray))).height(1.0f).pad(9.0f, 20.0f, 9.0f, 20.0f).fillX();
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
    public boolean show(Actor actor0, float f, float f1, float f2, float f3, boolean z) {
        this.scroll.setScrollY(0.0f);
        App.stage.setScrollFocus(this.scroll);
        return super.show(actor0, f, f1, f2, f3, z);
    }

    public TextButton textItem(String s, ChangeListener changeListener0) {
        TextButton textButton0 = ((TextButtonBuilder)App.textButton(s).change(changeListener0)).align(8).create();
        textButton0.pad(0.0f, 20.0f, 0.0f, 20.0f);
        textButton0.getLabelCell().height(80.0f);
        textButton0.invalidate();
        return textButton0;
    }
}

