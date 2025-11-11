package com.hm.gloomhavenhelper.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GloomScrollPane extends ScrollPane {
   public GloomScrollPane(Actor widget, ScrollPane.ScrollPaneStyle style) {
      super(widget, style);
   }

   public GloomScrollPane(Actor widget, Skin skin, String styleName) {
      super(widget, skin, styleName);
   }

   public GloomScrollPane(Actor widget, Skin skin) {
      super(widget, skin);
   }

   public GloomScrollPane(Actor widget) {
      super(widget);
   }

   @Override
   protected float getMouseWheelY() {
      return this.getScrollHeight() * 0.05F;
   }
}
