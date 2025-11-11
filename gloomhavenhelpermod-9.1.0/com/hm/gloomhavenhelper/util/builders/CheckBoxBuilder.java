package com.hm.gloomhavenhelper.util.builders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.hm.gloomhavenhelper.App;

public class CheckBoxBuilder extends TextButtonBuilder {
   private Drawable checkboxOn;
   private Drawable checkboxOff;
   private Drawable checkboxOver;
   private Drawable checkboxOnOver;
   private Drawable checkboxOnDisabled;
   private Drawable checkboxOffDisabled;

   public CheckBoxBuilder checkboxOn(String name) {
      this.checkboxOn = App.skin.getDrawable(name);
      return this;
   }

   public CheckBoxBuilder checkboxOn(String name, Color color) {
      this.checkboxOn = App.skin.newDrawable(name, color);
      return this;
   }

   public CheckBoxBuilder checkboxOn(Drawable drawable) {
      this.checkboxOn = drawable;
      return this;
   }

   public CheckBoxBuilder checkboxOnOver(String name) {
      this.checkboxOnOver = App.skin.getDrawable(name);
      return this;
   }

   public CheckBoxBuilder checkboxOnOver(String name, Color color) {
      this.checkboxOnOver = App.skin.newDrawable(name, color);
      return this;
   }

   public CheckBoxBuilder checkboxOnOver(Drawable drawable) {
      this.checkboxOnOver = drawable;
      return this;
   }

   public CheckBoxBuilder checkboxOff(String name) {
      this.checkboxOff = App.skin.getDrawable(name);
      return this;
   }

   public CheckBoxBuilder checkboxOff(String name, Color color) {
      this.checkboxOff = App.skin.newDrawable(name, color);
      return this;
   }

   public CheckBoxBuilder checkboxOff(Drawable drawable) {
      this.checkboxOff = drawable;
      return this;
   }

   public CheckBoxBuilder checkboxOver(String name) {
      this.checkboxOver = App.skin.getDrawable(name);
      return this;
   }

   public CheckBoxBuilder checkboxOver(String name, Color color) {
      this.checkboxOver = App.skin.newDrawable(name, color);
      return this;
   }

   public CheckBoxBuilder checkboxOver(Drawable drawable) {
      this.checkboxOver = drawable;
      return this;
   }

   public CheckBoxBuilder checkboxOnDisabled(String name) {
      this.checkboxOnDisabled = App.skin.getDrawable(name);
      return this;
   }

   public CheckBoxBuilder checkboxOnDisabled(String name, Color color) {
      this.checkboxOnDisabled = App.skin.newDrawable(name, color);
      return this;
   }

   public CheckBoxBuilder checkboxOnDisabled(Drawable drawable) {
      this.checkboxOnDisabled = drawable;
      return this;
   }

   public CheckBoxBuilder checkboxOffDisabled(String name) {
      this.checkboxOffDisabled = App.skin.getDrawable(name);
      return this;
   }

   public CheckBoxBuilder checkboxOffDisabled(String name, Color color) {
      this.checkboxOffDisabled = App.skin.newDrawable(name, color);
      return this;
   }

   public CheckBoxBuilder checkboxOffDisabled(Drawable drawable) {
      this.checkboxOffDisabled = drawable;
      return this;
   }

   public CheckBox create() {
      CheckBox.CheckBoxStyle style = (CheckBox.CheckBoxStyle)this.set(new CheckBox.CheckBoxStyle());
      style.checkboxOn = this.checkboxOn;
      style.checkboxOff = this.checkboxOff;
      style.checkboxOver = this.checkboxOver;
      style.checkboxOnOver = this.checkboxOnOver;
      style.checkboxOnDisabled = this.checkboxOnDisabled;
      style.checkboxOffDisabled = this.checkboxOffDisabled;
      return (CheckBox)this.set(new CheckBox("", style));
   }
}
