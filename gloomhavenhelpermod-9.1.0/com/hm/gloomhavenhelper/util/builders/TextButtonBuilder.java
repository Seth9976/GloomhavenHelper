package com.hm.gloomhavenhelper.util.builders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.hm.gloomhavenhelper.App;

public class TextButtonBuilder extends ButtonBuilder {
   private BitmapFont font;
   private Color fontColor;
   private Color downFontColor;
   private Color overFontColor;
   private Color checkedFontColor;
   private Color checkedOverFontColor;
   private Color disabledFontColor;
   private String text;
   private int align;

   public TextButtonBuilder font(BitmapFont font) {
      this.font = font;
      return this;
   }

   public TextButtonBuilder font(String name) {
      this.font = App.skin.getFont(name);
      return this;
   }

   public TextButtonBuilder fontColor(Color color) {
      this.fontColor = color;
      return this;
   }

   public TextButtonBuilder downFontColor(Color color) {
      this.downFontColor = color;
      return this;
   }

   public TextButtonBuilder overFontColor(Color color) {
      this.overFontColor = color;
      return this;
   }

   public TextButtonBuilder checkedFontColor(Color color) {
      this.checkedFontColor = color;
      return this;
   }

   public TextButtonBuilder checkedOverFontColor(Color color) {
      this.checkedOverFontColor = color;
      return this;
   }

   public TextButtonBuilder disabledFontColor(Color color) {
      this.disabledFontColor = color;
      return this;
   }

   public TextButtonBuilder text(String text) {
      this.text = text;
      return this;
   }

   public TextButtonBuilder align(int align) {
      this.align = align;
      return this;
   }

   public TextButton set(TextButton button) {
      super.set(button);
      button.setText(this.text);
      button.getLabel().setAlignment(this.align);
      return button;
   }

   public TextButton.TextButtonStyle set(TextButton.TextButtonStyle style) {
      style.font = this.font;
      style.fontColor = this.fontColor;
      style.downFontColor = this.downFontColor;
      style.overFontColor = this.overFontColor;
      style.checkedFontColor = this.checkedFontColor;
      style.checkedOverFontColor = this.checkedOverFontColor;
      style.disabledFontColor = this.disabledFontColor;
      return (TextButton.TextButtonStyle)super.set(style);
   }

   public TextButton create() {
      TextButton.TextButtonStyle style = this.set(new TextButton.TextButtonStyle());
      return this.set(new TextButton("", style));
   }
}
