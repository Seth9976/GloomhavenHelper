package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;

public class TextTooltip extends Tooltip {
   public TextTooltip(@Null String text, Skin skin) {
      this(text, TooltipManager.getInstance(), (TextTooltip.TextTooltipStyle)skin.get(TextTooltip.TextTooltipStyle.class));
   }

   public TextTooltip(@Null String text, Skin skin, String styleName) {
      this(text, TooltipManager.getInstance(), (TextTooltip.TextTooltipStyle)skin.get(styleName, TextTooltip.TextTooltipStyle.class));
   }

   public TextTooltip(@Null String text, TextTooltip.TextTooltipStyle style) {
      this(text, TooltipManager.getInstance(), style);
   }

   public TextTooltip(@Null String text, TooltipManager manager, Skin skin) {
      this(text, manager, (TextTooltip.TextTooltipStyle)skin.get(TextTooltip.TextTooltipStyle.class));
   }

   public TextTooltip(@Null String text, TooltipManager manager, Skin skin, String styleName) {
      this(text, manager, (TextTooltip.TextTooltipStyle)skin.get(styleName, TextTooltip.TextTooltipStyle.class));
   }

   public TextTooltip(@Null String text, final TooltipManager manager, TextTooltip.TextTooltipStyle style) {
      super(null, manager);
      final Label label = new Label(text, style.label);
      label.setWrap(true);
      this.container.setActor(label);
      this.container.width(new Value() {
         @Override
         public float get(@Null Actor context) {
            return Math.min(manager.maxWidth, label.getGlyphLayout().width);
         }
      });
      this.setStyle(style);
   }

   public void setStyle(TextTooltip.TextTooltipStyle style) {
      if (style == null) {
         throw new NullPointerException("style cannot be null");
      } else {
         ((Label)this.container.getActor()).setStyle(style.label);
         this.container.setBackground(style.background);
         this.container.maxWidth(style.wrapWidth);
      }
   }

   public static class TextTooltipStyle {
      public Label.LabelStyle label;
      @Null
      public Drawable background;
      public float wrapWidth;

      public TextTooltipStyle() {
      }

      public TextTooltipStyle(Label.LabelStyle label, @Null Drawable background) {
         this.label = label;
         this.background = background;
      }

      public TextTooltipStyle(TextTooltip.TextTooltipStyle style) {
         this.label = new Label.LabelStyle(style.label);
         this.background = style.background;
         this.wrapWidth = style.wrapWidth;
      }
   }
}
