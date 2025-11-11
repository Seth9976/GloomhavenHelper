package com.hm.gloomhavenhelper.util;

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
import com.hm.gloomhavenhelper.App;
import com.hm.gloomhavenhelper.util.builders.ImageTextButtonBuilder;
import com.hm.gloomhavenhelper.util.builders.TextButtonBuilder;

public class TextMenu extends Menu {
   public final Table table = new Table();
   private final ScrollPane scroll;

   public TextMenu() {
      this.table.defaults().growX();
      this.scroll = new GloomScrollPane(this.table);
      this.scroll.getStyle().vScrollKnob = App.skin.getDrawable("scroll-vert");
      this.scroll.setFadeScrollBars(false);
      this.scroll.setupFadeScrollBars(0.4F, 0.0F);
      this.scroll.setScrollBarTouch(false);
      this.scroll.setScrollingDisabled(true, false);
      this.scroll.setFlickScrollTapSquareSize(35.0F);
      this.add(this.scroll).grow().fill();
   }

   @Override
   public boolean show(Actor positionActor, float positionX, float positionY, float positionWidth, float positionHeight, boolean preferRight) {
      this.scroll.setScrollY(0.0F);
      App.stage.setScrollFocus(this.scroll);
      return super.show(positionActor, positionX, positionY, positionWidth, positionHeight, preferRight);
   }

   public static Container seperator() {
      return new Container(new Image(App.drawable("white", App.buttonGray))).height(1.0F).pad(9.0F, 20.0F, 9.0F, 20.0F).fillX();
   }

   public Cell addSeperator() {
      return this.table.add(seperator());
   }

   public static Cell addSeperator(Table table) {
      return table.add(seperator());
   }

   public TextButton textItem(String text, ChangeListener listener) {
      TextButton button = ((TextButtonBuilder)App.textButton(text).change(listener)).align(8).create();
      button.pad(0.0F, 20.0F, 0.0F, 20.0F);
      button.getLabelCell().height(80.0F);
      button.invalidate();
      return button;
   }

   public Cell addTextItem(String text, ChangeListener listener) {
      return this.table.add(this.textItem(text, listener));
   }

   public Cell addImageTextItem(String text, String imageUp, ChangeListener listener) {
      ImageTextButton button = ((ImageTextButtonBuilder)App.imageTextButton(text)
            .imageUp(imageUp, App.darken)
            .imageOver(imageUp)
            .imageDisabled(imageUp, App.disabledDim)
            .change(listener))
         .align(8)
         .create();
      button.pad(0.0F, 20.0F, 0.0F, 20.0F);
      button.getLabelCell().height(80.0F);
      button.invalidate();
      return this.table.add(button);
   }

   public static Cell addImageTextItem(Table table, String text, String imageUp, ChangeListener listener) {
      ImageTextButton button = ((ImageTextButtonBuilder)App.imageTextButton(text)
            .imageUp(imageUp, App.darken)
            .imageOver(imageUp)
            .imageDisabled(imageUp, App.disabledDim)
            .change(listener))
         .align(8)
         .create();
      button.pad(0.0F, 20.0F, 0.0F, 20.0F);
      button.getLabelCell().height(80.0F);
      button.invalidate();
      return table.add(button);
   }

   public CheckBox checkBoxItem(String text, boolean checked, ChangeListener listener) {
      CheckBox checkbox = App.checkbox(text);
      checkbox.setChecked(checked);
      checkbox.pad(0.0F, 20.0F, 0.0F, 20.0F);
      checkbox.getLabelCell().height(80.0F);
      checkbox.invalidate();
      if (listener != null) {
         checkbox.addListener(listener);
      }

      return checkbox;
   }

   public CheckBox radioItem(String text, boolean checked, ChangeListener listener) {
      CheckBox checkbox = App.radio(text);
      checkbox.setChecked(checked);
      checkbox.pad(0.0F, 20.0F, 0.0F, 20.0F);
      checkbox.getLabelCell().height(80.0F);
      checkbox.invalidate();
      if (listener != null) {
         checkbox.addListener(listener);
      }

      return checkbox;
   }

   public Cell addCheckBoxItem(String text, boolean checked, ChangeListener listener) {
      return this.table.add(this.checkBoxItem(text, checked, listener));
   }
}
