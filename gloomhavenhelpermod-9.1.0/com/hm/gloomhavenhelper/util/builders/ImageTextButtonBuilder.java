package com.hm.gloomhavenhelper.util.builders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Scaling;
import com.hm.gloomhavenhelper.App;

public class ImageTextButtonBuilder extends ButtonBuilder {
   private BitmapFont font;
   private Color fontColor;
   private Color downFontColor;
   private Color overFontColor;
   private Color checkedFontColor;
   private Color checkedOverFontColor;
   private Color disabledFontColor;
   private Scaling scaling = Scaling.fit;
   private String text;
   private Drawable imageUp;
   private Drawable imageDown;
   private Drawable imageOver;
   private Drawable imageChecked;
   private Drawable imageCheckedOver;
   private Drawable imageDisabled;
   private int align;

   public ImageTextButtonBuilder font(BitmapFont font) {
      this.font = font;
      return this;
   }

   public ImageTextButtonBuilder font(String name) {
      this.font = App.skin.getFont(name);
      return this;
   }

   public ImageTextButtonBuilder fontColor(Color color) {
      this.fontColor = color;
      return this;
   }

   public ImageTextButtonBuilder downFontColor(Color color) {
      this.downFontColor = color;
      return this;
   }

   public ImageTextButtonBuilder overFontColor(Color color) {
      this.overFontColor = color;
      return this;
   }

   public ImageTextButtonBuilder checkedFontColor(Color color) {
      this.checkedFontColor = color;
      return this;
   }

   public ImageTextButtonBuilder checkedOverFontColor(Color color) {
      this.checkedOverFontColor = color;
      return this;
   }

   public ImageTextButtonBuilder disabledFontColor(Color color) {
      this.disabledFontColor = color;
      return this;
   }

   public ImageTextButtonBuilder imageUp(String name) {
      this.imageUp = App.skin.getDrawable(name);
      return this;
   }

   public ImageTextButtonBuilder imageUp(String name, Color color) {
      this.imageUp = App.skin.newDrawable(name, color);
      return this;
   }

   public ImageTextButtonBuilder imageUp(Drawable drawable) {
      this.imageUp = drawable;
      return this;
   }

   public ImageTextButtonBuilder imageDown(String name) {
      this.imageDown = App.skin.getDrawable(name);
      return this;
   }

   public ImageTextButtonBuilder imageDown(String name, Color color) {
      this.imageDown = App.skin.newDrawable(name, color);
      return this;
   }

   public ImageTextButtonBuilder imageDown(Drawable drawable) {
      this.imageDown = drawable;
      return this;
   }

   public ImageTextButtonBuilder imageOver(String name) {
      this.imageOver = App.skin.getDrawable(name);
      return this;
   }

   public ImageTextButtonBuilder imageOver(String name, Color color) {
      this.imageOver = App.skin.newDrawable(name, color);
      return this;
   }

   public ImageTextButtonBuilder imageOver(Drawable drawable) {
      this.imageOver = drawable;
      return this;
   }

   public ImageTextButtonBuilder imageChecked(String name) {
      this.imageChecked = App.skin.getDrawable(name);
      return this;
   }

   public ImageTextButtonBuilder imageChecked(String name, Color color) {
      this.imageChecked = App.skin.newDrawable(name, color);
      return this;
   }

   public ImageTextButtonBuilder imageChecked(Drawable drawable) {
      this.imageChecked = drawable;
      return this;
   }

   public ImageTextButtonBuilder imageCheckedOver(String name) {
      this.imageCheckedOver = App.skin.getDrawable(name);
      return this;
   }

   public ImageTextButtonBuilder imageCheckedOver(String name, Color color) {
      this.imageCheckedOver = App.skin.newDrawable(name, color);
      return this;
   }

   public ImageTextButtonBuilder imageCheckedOver(Drawable drawable) {
      this.imageCheckedOver = drawable;
      return this;
   }

   public ImageTextButtonBuilder imageDisabled(String name) {
      this.imageDisabled = App.skin.getDrawable(name);
      return this;
   }

   public ImageTextButtonBuilder imageDisabled(String name, Color color) {
      this.imageDisabled = App.skin.newDrawable(name, color);
      return this;
   }

   public ImageTextButtonBuilder imageDisabled(Drawable drawable) {
      this.imageDisabled = drawable;
      return this;
   }

   public ImageTextButtonBuilder text(String text) {
      this.text = text;
      return this;
   }

   public ImageTextButtonBuilder align(int align) {
      this.align = align;
      return this;
   }

   public ImageTextButtonBuilder scaling(Scaling scaling) {
      this.scaling = scaling;
      return this;
   }

   public ImageTextButton create() {
      ImageTextButton.ImageTextButtonStyle style = (ImageTextButton.ImageTextButtonStyle)this.set(new ImageTextButton.ImageTextButtonStyle());
      style.font = this.font;
      style.fontColor = this.fontColor;
      style.downFontColor = this.downFontColor;
      style.overFontColor = this.overFontColor;
      style.checkedFontColor = this.checkedFontColor;
      style.checkedOverFontColor = this.checkedOverFontColor;
      style.disabledFontColor = this.disabledFontColor;
      style.imageUp = this.imageUp;
      style.imageDown = this.imageDown;
      style.imageOver = this.imageOver;
      style.imageChecked = this.imageChecked;
      style.imageCheckedOver = this.imageCheckedOver;
      style.imageDisabled = this.imageDisabled;
      ImageTextButton button = (ImageTextButton)this.set(new ImageTextButton(this.text, style));
      button.getImage().setScaling(this.scaling);
      return button;
   }
}
