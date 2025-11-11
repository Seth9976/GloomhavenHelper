package com.hm.gloomhavenhelper.util.builders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.hm.gloomhavenhelper.App;

public class ButtonBuilder {
   private Drawable up;
   private Drawable down;
   private Drawable over;
   private Drawable checked;
   private Drawable checkedOver;
   private Drawable disabled;
   private float pressedOffsetX;
   private float pressedOffsetY;
   private float unpressedOffsetX;
   private float unpressedOffsetY;
   private float checkedOffsetX;
   private float checkedOffsetY;
   private boolean programmaticChangeEvents;
   private boolean isDisabled;
   private boolean isChecked;
   private ChangeListener changeListener;
   private int tapSquareSize = 28;

   public ButtonBuilder up(String name) {
      this.up = App.skin.getDrawable(name);
      return this;
   }

   public ButtonBuilder up(String name, Color color) {
      this.up = App.skin.newDrawable(name, color);
      return this;
   }

   public ButtonBuilder up(Drawable drawable) {
      this.up = drawable;
      return this;
   }

   public ButtonBuilder down(String name) {
      this.down = App.skin.getDrawable(name);
      return this;
   }

   public ButtonBuilder down(String name, Color color) {
      this.down = App.skin.newDrawable(name, color);
      return this;
   }

   public ButtonBuilder down(Drawable drawable) {
      this.down = drawable;
      return this;
   }

   public ButtonBuilder over(String name) {
      this.over = App.skin.getDrawable(name);
      return this;
   }

   public ButtonBuilder over(String name, Color color) {
      this.over = App.skin.newDrawable(name, color);
      return this;
   }

   public ButtonBuilder over(Drawable drawable) {
      this.over = drawable;
      return this;
   }

   public ButtonBuilder checked(String name) {
      this.checked = App.skin.getDrawable(name);
      return this;
   }

   public ButtonBuilder checked(String name, Color color) {
      this.checked = App.skin.newDrawable(name, color);
      return this;
   }

   public ButtonBuilder checked(Drawable drawable) {
      this.checked = drawable;
      return this;
   }

   public ButtonBuilder checkedOver(String name) {
      this.checkedOver = App.skin.getDrawable(name);
      return this;
   }

   public ButtonBuilder checkedOver(String name, Color color) {
      this.checkedOver = App.skin.newDrawable(name, color);
      return this;
   }

   public ButtonBuilder checkedOver(Drawable drawable) {
      this.checkedOver = drawable;
      return this;
   }

   public ButtonBuilder disabled(String name) {
      this.disabled = App.skin.getDrawable(name);
      return this;
   }

   public ButtonBuilder disabled(String name, Color color) {
      this.disabled = App.skin.newDrawable(name, color);
      return this;
   }

   public ButtonBuilder disabled(Drawable drawable) {
      this.disabled = drawable;
      return this;
   }

   public ButtonBuilder pressedOffset(float x, float y) {
      this.pressedOffsetX = x;
      this.pressedOffsetY = y;
      return this;
   }

   public ButtonBuilder unpressedOffset(float x, float y) {
      this.unpressedOffsetX = x;
      this.unpressedOffsetY = y;
      return this;
   }

   public ButtonBuilder checkedOffset(float x, float y) {
      this.checkedOffsetX = x;
      this.checkedOffsetY = y;
      return this;
   }

   public ButtonBuilder programmaticChangeEvents(boolean programmaticChangeEvents) {
      this.programmaticChangeEvents = programmaticChangeEvents;
      return this;
   }

   public ButtonBuilder disabled(boolean disabled) {
      this.isDisabled = disabled;
      return this;
   }

   public ButtonBuilder checked(boolean checked) {
      this.isChecked = checked;
      return this;
   }

   public ButtonBuilder tapSquareSize(int tapSquareSize) {
      this.tapSquareSize = tapSquareSize;
      return this;
   }

   public ButtonBuilder change(ChangeListener changeListener) {
      this.changeListener = changeListener;
      return this;
   }

   public Button.ButtonStyle set(Button.ButtonStyle style) {
      style.up = this.up;
      style.down = this.down;
      style.over = this.over;
      style.checked = this.checked;
      style.checkedOver = this.checkedOver;
      style.disabled = this.disabled;
      style.pressedOffsetX = this.pressedOffsetX;
      style.pressedOffsetY = this.pressedOffsetY;
      style.unpressedOffsetX = this.unpressedOffsetX;
      style.unpressedOffsetY = this.unpressedOffsetY;
      style.checkedOffsetX = this.checkedOffsetX;
      style.checkedOffsetY = this.checkedOffsetY;
      return style;
   }

   public Button set(Button button) {
      button.getClickListener().setTapSquareSize(this.tapSquareSize);
      button.setProgrammaticChangeEvents(this.programmaticChangeEvents);
      button.setDisabled(this.isDisabled);
      button.setChecked(this.isChecked);
      if (this.changeListener != null) {
         button.addListener(this.changeListener);
      }

      return button;
   }

   public Button create() {
      Button.ButtonStyle style = this.set(new Button.ButtonStyle());
      return this.set(new Button(style));
   }
}
