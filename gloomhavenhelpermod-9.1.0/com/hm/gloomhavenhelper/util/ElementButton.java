package com.hm.gloomhavenhelper.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hm.gloomhavenhelper.App;
import com.hm.gloomhavenhelper.model.ElementState;
import com.hm.spine.AnimationState;
import com.hm.spine.AnimationStateData;
import com.hm.spine.Skeleton;
import com.hm.spine.utils.SkeletonDrawable;

public class ElementButton extends ImageButton {
   private final String name;
   private final Skeleton skeleton;
   private final AnimationState state;
   public ElementState elementState = ElementState.inert;
   private final TextureAtlas.AtlasRegion ringRegion;
   private final TextureAtlas.AtlasRegion glowRegion;
   private final TextureAtlas.AtlasRegion glowHalfRegion;
   private final TextureAtlas.AtlasRegion elementRegion;
   boolean allowOver = true;
   long lastClickTime;
   float glowAlpha;
   float glowHalfAlpha;

   public ElementButton(String name) {
      super(new ImageButton.ImageButtonStyle());
      this.name = name;
      this.skeleton = new Skeleton(App.elementsSkeletonData);
      this.state = new AnimationState(new AnimationStateData(App.elementsSkeletonData));
      this.state.setAnimation(0, name + "-strong-inert", false).setTrackTime(999.0F);
      ImageButton.ImageButtonStyle style = this.getStyle();
      style.imageUp = new SkeletonDrawable(App.skeletonRenderer, this.skeleton, this.state);
      style.imageUp.setMinWidth(100.0F);
      style.imageUp.setMinHeight(100.0F);
      this.setStyle(style);
      this.ringRegion = App.atlas.findRegion("element-ring");
      this.glowRegion = App.atlas.findRegion("element-glow");
      this.glowHalfRegion = App.atlas.findRegion("element-glow-half");
      this.elementRegion = App.atlas.findRegion("psd/element-" + name);
      this.addListener(new ClickListener(-1) {
         @Override
         public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            super.enter(event, x, y, pointer, fromActor);
            ElementButton.this.allowOver = true;
         }

         @Override
         public void clicked(InputEvent event, float x, float y) {
            long time = System.currentTimeMillis();
            if (time - ElementButton.this.lastClickTime < 400L) {
               ElementButton.this.setState(ElementState.waning, "strong-waning");
            } else if (ElementButton.this.elementState == ElementState.inert) {
               ElementButton.this.infuse();
            } else {
               ElementButton.this.consume();
            }

            ElementButton.this.lastClickTime = time;
            ElementButton.this.allowOver = false;
            ElementButton.this.clicked(ElementButton.this.elementState);
         }
      });
   }

   @Override
   public void act(float delta) {
      super.act(delta);
      this.state.update(delta);
      AnimationState.TrackEntry current = this.state.getCurrent(0);
      if (current != null && (current.getTrackTime() < current.getAnimation().getDuration() || current.getNext() != null)) {
         Gdx.graphics.requestRendering();
      }

      boolean animDone = this.state.getCurrent(0).isComplete() && this.state.getCurrent(0).getNext() == null;
      float target = this.elementState == ElementState.strong && animDone ? 0.7F : 0.0F;
      float modifier = this.glowAlpha < target ? 0.5F : 1.0F;
      this.glowAlpha = App.animate(this.glowAlpha, target, 0.6F * modifier, 1.5F * modifier, 3.0F * modifier, delta);
      target = this.elementState == ElementState.waning && animDone ? 0.7F : 0.0F;
      modifier = this.glowHalfAlpha < target ? 0.5F : 1.0F;
      this.glowHalfAlpha = App.animate(this.glowHalfAlpha, target, 0.6F * modifier, 1.5F * modifier, 3.0F * modifier, delta);
   }

   @Override
   public void draw(Batch batch, float parentAlpha) {
      this.state.apply(this.skeleton);
      if (this.glowAlpha > 0.0F) {
         batch.setColor(1.0F, 1.0F, 1.0F, this.glowAlpha);
         batch.draw(this.glowRegion, this.getX() - 6.0F, this.getY() - 6.0F);
      }

      if (this.glowHalfAlpha > 0.0F) {
         batch.setColor(1.0F, 1.0F, 1.0F, this.glowHalfAlpha);
         batch.draw(this.glowHalfRegion, this.getX() - 6.0F, this.getY() - 6.0F);
      }

      super.draw(batch, parentAlpha);
      batch.setBlendFunction(770, 771);
      if (this.allowOver && this.isOver()) {
         batch.setColor(Color.WHITE);
         if (this.elementState == ElementState.inert) {
            batch.draw(this.elementRegion, this.getX() + this.elementRegion.offsetX, this.getY() + this.elementRegion.offsetY);
         }

         batch.draw(this.ringRegion, this.getX() - 2.0F, this.getY() - 2.0F);
      }
   }

   public void setState(ElementState elementState, boolean animate) {
      if (elementState == null) {
         throw new IllegalArgumentException("elementState cannot be null.");
      } else if (this.elementState != elementState) {
         String anim = null;
         if (animate) {
            if (elementState == ElementState.inert) {
               if (this.elementState == ElementState.inert) {
                  this.setState(ElementState.inert, false);
                  return;
               }

               anim = this.elementState == ElementState.strong ? "strong-inert" : "waning-inert";
            } else if (elementState == ElementState.waning) {
               anim = "strong-waning";
            } else {
               anim = "inert-strong";
            }

            this.setState(elementState, anim);
         } else {
            if (elementState == ElementState.inert) {
               anim = "waning-inert";
            } else if (elementState == ElementState.waning) {
               anim = "strong-waning";
            } else {
               anim = "inert-strong";
            }

            this.state.setAnimation(0, this.name + "-" + anim, false).setTrackTime(999.0F);
            this.elementState = elementState;
         }
      }
   }

   public AnimationState.TrackEntry setState(ElementState elementState, String anim) {
      if (elementState == null) {
         throw new IllegalArgumentException("elementState cannot be null.");
      } else {
         AnimationState.TrackEntry current = this.state.getCurrent(0);
         AnimationState.TrackEntry entry;
         if (current.getNext() == null && current.isComplete()) {
            entry = this.state.addAnimation(0, this.name + "-" + anim, false, current.getTrackTime() + 0.4F);
         } else {
            entry = this.state.setAnimation(0, this.name + "-" + anim, false);
         }

         this.toFront();
         this.elementState = elementState;
         return entry;
      }
   }

   public void endOfRound() {
      if (this.elementState == ElementState.strong) {
         this.setState(ElementState.waning, "strong-waning");
      } else if (this.elementState == ElementState.waning) {
         this.setState(ElementState.inert, "waning-inert");
      }
   }

   public void consume() {
      this.setState(ElementState.inert, "strong-inert");
   }

   public void infuse() {
      this.setState(ElementState.strong, "inert-strong");
   }

   protected void clicked(ElementState elementState) {
   }
}
