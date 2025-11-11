package com.hm.gloomhavenhelper.util.builders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Scaling;
import com.hm.gloomhavenhelper.App;

public class ImageButtonBuilder extends ButtonBuilder {
   private Drawable imageUp;
   private Drawable imageDown;
   private Drawable imageOver;
   private Scaling scaling = Scaling.fit;
   private Drawable imageChecked;
   private Drawable imageCheckedOver;
   private Drawable imageDisabled;

   public ImageButtonBuilder imageUp(String name) {
      this.imageUp = App.skin.getDrawable(name);
      return this;
   }

   public ImageButtonBuilder imageUp(String name, Color color) {
      this.imageUp = App.skin.newDrawable(name, color);
      return this;
   }

   public ImageButtonBuilder imageUp(Drawable drawable) {
      this.imageUp = drawable;
      return this;
   }

   public ImageButtonBuilder imageDown(String name) {
      this.imageDown = App.skin.getDrawable(name);
      return this;
   }

   public ImageButtonBuilder imageDown(String name, Color color) {
      this.imageDown = App.skin.newDrawable(name, color);
      return this;
   }

   public ImageButtonBuilder imageDown(Drawable drawable) {
      this.imageDown = drawable;
      return this;
   }

   public ImageButtonBuilder imageOver(String name) {
      this.imageOver = App.skin.getDrawable(name);
      return this;
   }

   public ImageButtonBuilder imageOver(String name, Color color) {
      this.imageOver = App.skin.newDrawable(name, color);
      return this;
   }

   public ImageButtonBuilder imageOver(Drawable drawable) {
      this.imageOver = drawable;
      return this;
   }

   public ImageButtonBuilder imageChecked(String name) {
      this.imageChecked = App.skin.getDrawable(name);
      return this;
   }

   public ImageButtonBuilder imageChecked(String name, Color color) {
      this.imageChecked = App.skin.newDrawable(name, color);
      return this;
   }

   public ImageButtonBuilder imageChecked(Drawable drawable) {
      this.imageChecked = drawable;
      return this;
   }

   public ImageButtonBuilder imageCheckedOver(String name) {
      this.imageCheckedOver = App.skin.getDrawable(name);
      return this;
   }

   public ImageButtonBuilder imageCheckedOver(String name, Color color) {
      this.imageCheckedOver = App.skin.newDrawable(name, color);
      return this;
   }

   public ImageButtonBuilder imageCheckedOver(Drawable drawable) {
      this.imageCheckedOver = drawable;
      return this;
   }

   public ImageButtonBuilder imageDisabled(String name) {
      this.imageDisabled = App.skin.getDrawable(name);
      return this;
   }

   public ImageButtonBuilder imageDisabled(String name, Color color) {
      this.imageDisabled = App.skin.newDrawable(name, color);
      return this;
   }

   public ImageButtonBuilder imageDisabled(Drawable drawable) {
      this.imageDisabled = drawable;
      return this;
   }

   public ImageButtonBuilder scaling(Scaling scaling) {
      this.scaling = scaling;
      return this;
   }

   public ImageButton create() {
      ImageButton.ImageButtonStyle style = (ImageButton.ImageButtonStyle)this.set(new ImageButton.ImageButtonStyle());
      style.imageUp = this.imageUp;
      style.imageDown = this.imageDown;
      style.imageOver = this.imageOver;
      style.imageChecked = this.imageChecked;
      style.imageCheckedOver = this.imageCheckedOver;
      style.imageDisabled = this.imageDisabled;
      ImageButton button = (ImageButton)this.set(new ImageButton(style));
      button.getImage().setScaling(this.scaling);
      return button;
   }
}
