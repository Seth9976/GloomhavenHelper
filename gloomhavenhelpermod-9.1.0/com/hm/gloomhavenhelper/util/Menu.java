package com.hm.gloomhavenhelper.util;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.FloatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.hm.gloomhavenhelper.App;

public class Menu extends Table {
   public static int menusShown;
   private final TextureRegion vignette;
   private final TextureRegion arrow;
   private final TextureRegion corner;
   private final TextureRegion white;
   private final NinePatch dimCorners;
   private final TiledDrawable bg;
   private final TiledDrawable top;
   private final TiledDrawable bottom;
   private final TiledDrawable left;
   private final TiledDrawable right;
   private boolean hidden;
   private float arrowX;
   private float arrowY;
   public boolean arrowFlip;
   public boolean showArrow = true;
   private Actor positionActor;
   private float positionX;
   private float positionY;
   private float positionWidth;
   private float positionHeight;
   public boolean preferRight;
   private FloatAction bgAlpha;
   private float bgX;
   private float bgY;
   private float bgWidth;
   private float bgHeight;
   public float bgOffsetX;
   public float bgOffsetY;
   public float bgOffsetWidth;
   public float bgOffsetHeight;
   public boolean animate = true;
   public boolean autoHide = true;
   final InputListener hideListener = new InputListener() {
      @Override
      public boolean handle(Event event) {
         if (!(event instanceof InputEvent)) {
            return false;
         } else if (Menu.this.isAscendantOf(event.getTarget())) {
            return false;
         } else {
            InputEvent inputEvent = (InputEvent)event;
            if (inputEvent.getType() == InputEvent.Type.touchDown) {
               Menu.this.hide();
            }

            event.cancel();
            return true;
         }
      }
   };

   public Menu() {
      super(App.skin);
      this.vignette = App.skin.getRegion("menu/vignette");
      this.arrow = App.skin.getRegion("menu/arrow");
      this.corner = App.skin.getRegion("menu/corner");
      this.white = App.skin.getRegion("white");
      this.dimCorners = App.skin.getPatch("dim-corners");
      this.bg = App.skin.getTiledDrawable("menu/bg");
      this.top = App.skin.getTiledDrawable("menu/top");
      this.bottom = App.skin.getTiledDrawable("menu/bottom");
      this.left = App.skin.getTiledDrawable("menu/left");
      this.right = App.skin.getTiledDrawable("menu/right");
      this.setTransform(true);
      this.pad(12.0F);
   }

   @Override
   public void draw(Batch batch, float parentAlpha) {
      if (this.bgAlpha != null) {
         if (!this.hidden) {
            this.updatePosition();
         }

         batch.setColor(0.0F, 0.0F, 0.0F, 0.5F * this.bgAlpha.getValue() * this.getColor().a);
         batch.draw(this.white, 0.0F, 0.0F, App.stage.getWidth(), this.bgY);
         batch.draw(this.white, 0.0F, this.bgY + this.bgHeight, App.stage.getWidth(), App.stage.getHeight() - (this.bgY + this.bgHeight));
         batch.draw(this.white, 0.0F, this.bgY, this.bgX, this.bgHeight);
         batch.draw(this.white, this.bgX + this.bgWidth, this.bgY, App.stage.getWidth() - (this.bgX + this.bgWidth), this.bgHeight);
         this.dimCorners.draw(batch, this.bgX, this.bgY, this.bgWidth, this.bgHeight);
      }

      super.draw(batch, parentAlpha);
   }

   @Override
   protected void drawBackground(Batch batch, float parentAlpha, float x, float y) {
      x += 9.0F;
      y += 9.0F;
      float w = this.getWidth() - 18.0F;
      float h = this.getHeight() - 18.0F;
      batch.setColor(1.0F, 1.0F, 1.0F, this.getColor().a * parentAlpha);
      this.bg.draw(batch, x, y, w, h);
      batch.draw(this.vignette, x, y, w, h);
   }

   @Override
   protected void drawChildren(Batch batch, float parentAlpha) {
      super.drawChildren(batch, parentAlpha);
      float w = this.getWidth();
      float h = this.getHeight();
      batch.setColor(1.0F, 1.0F, 1.0F, this.getColor().a * parentAlpha);
      if (this.showArrow) {
         if (this.arrowFlip) {
            batch.draw(
               this.arrow.getTexture(),
               this.arrowX,
               this.arrowY,
               this.arrow.getRegionWidth(),
               this.arrow.getRegionHeight(),
               this.arrow.getU2(),
               this.arrow.getV2(),
               this.arrow.getU(),
               this.arrow.getV()
            );
         } else {
            batch.draw(this.arrow, this.arrowX, this.arrowY);
         }
      }

      this.bottom.draw(batch, 0.0F, 0.0F, w, 21.0F);
      this.top.draw(batch, 0.0F, h - 17.0F, w, 17.0F);
      this.left.draw(batch, 0.0F, 0.0F, 21.0F, h);
      this.right.draw(batch, w - 18.0F, 0.0F, 18.0F, h);
      float x = -5.0F;
      float y = -5.0F;
      h -= 27.0F;
      w -= 27.0F;
      batch.draw(this.corner, -5.0F, -5.0F + h);
      float u = this.corner.getU();
      float v = this.corner.getV();
      float u2 = this.corner.getU2();
      float v2 = this.corner.getV2();
      batch.draw(this.corner.getTexture(), -5.0F + w, -5.0F + h, 37.0F, 37.0F, u2, v2, u, v);
      batch.draw(this.corner.getTexture(), -5.0F + w, -5.0F, 37.0F, 37.0F, u2, v, u, v2);
      batch.draw(this.corner.getTexture(), -5.0F, -5.0F, 37.0F, 37.0F, u, v, u2, v2);
   }

   public void setBackgroundOffset(float bgOffsetX, float bgOffsetY, float bgOffsetWidth, float bgOffsetHeight) {
      this.bgOffsetX = bgOffsetX;
      this.bgOffsetY = bgOffsetY;
      this.bgOffsetWidth = bgOffsetWidth;
      this.bgOffsetHeight = bgOffsetHeight;
   }

   public void setPosition(float positionX, float positionY, float positionWidth, float positionHeight, float bgX, float bgY, float bgWidth, float bgHeight) {
      this.positionX = positionX;
      this.positionY = positionY;
      this.positionWidth = positionWidth;
      this.positionHeight = positionHeight;
      this.bgX = bgX;
      this.bgY = bgY;
      this.bgWidth = bgWidth;
      this.bgHeight = bgHeight;
   }

   protected void updatePosition() {
      float width = this.getPrefWidth();
      float height = this.getPrefHeight();
      if (width > App.stage.getWidth() - 18.0F) {
         width = App.stage.getWidth() - 18.0F;
      }

      if (height > App.stage.getHeight() - 18.0F) {
         height = App.stage.getHeight() - 18.0F;
      }

      float x;
      float y;
      float w;
      float h;
      if (this.positionActor != null && this.positionActor.getParent() != null) {
         float px = this.positionActor.getX();
         float py = this.positionActor.getY();
         if (this.positionActor instanceof Animator.HasAnimator) {
            Animator animator = ((Animator.HasAnimator)this.positionActor).getAnimator();
            if (animator.animating) {
               px = animator.target.x;
               py = animator.target.y;
            }
         }

         Vector2 temp = this.positionActor.getParent().localToStageCoordinates(App.v2.set(px + this.bgOffsetX, py + this.bgOffsetY));
         this.bgX = temp.x;
         this.bgY = temp.y;
         this.positionActor
            .getParent()
            .localToStageCoordinates(
               temp.set(px + this.positionActor.getWidth() + this.bgOffsetWidth, py + this.positionActor.getHeight() + this.bgOffsetHeight)
            )
            .sub(this.bgX, this.bgY);
         this.bgWidth = temp.x;
         this.bgHeight = temp.y;
         temp = this.positionActor.getParent().localToStageCoordinates(App.v2.set(px + this.positionX, py + this.positionY));
         x = temp.x;
         y = temp.y;
         temp = this.positionActor
            .getParent()
            .localToStageCoordinates(
               App.v2.set(px + this.positionActor.getWidth() + this.positionWidth, py + this.positionActor.getHeight() + this.positionHeight)
            )
            .sub(x, y);
         w = temp.x;
         h = temp.y;
      } else {
         x = this.positionX;
         y = this.positionY;
         w = this.positionWidth;
         h = this.positionHeight;
      }

      y += Math.round(h / 2.0F);
      float screenEdgeSpace = 10.0F;
      float arrowWidth = 44.0F;
      float arrowBottomToTip = 50.0F;
      float arrowTopToTip = 29.0F;
      float arrowTipToCorner = 67.0F;
      float leftRemaining = x - 44.0F - width;
      float rightRemaining = App.stage.getWidth() - x - w - 44.0F - width;
      float bottomRemaining = y - height / 2.0F;
      float topRemaining = App.stage.getHeight() - (y + height);
      boolean right;
      if (leftRemaining < 0.0F && rightRemaining < 0.0F) {
         right = rightRemaining > leftRemaining;
      } else {
         right = this.preferRight && rightRemaining >= 0.0F || !this.preferRight && leftRemaining < 0.0F;
      }

      if (topRemaining < 0.0F && bottomRemaining < 0.0F) {
         boolean var30 = bottomRemaining > topRemaining;
      } else {
         boolean bottom = bottomRemaining >= 0.0F;
      }

      boolean bottom = true;
      right = this.dir(right);
      this.arrowFlip = !right;
      this.arrowX = right ? x + w : x - 44.0F;
      this.arrowY = y - 50.0F;
      this.arrowX = MathUtils.clamp(this.arrowX, right ? 0.0F : 10.0F + width, App.stage.getWidth() - 44.0F);
      this.arrowY = MathUtils.clamp(this.arrowY, -14.0F, App.stage.getHeight() - 10.0F);
      x = right ? this.arrowX + 44.0F : this.arrowX - width;
      y = this.arrowY + 50.0F + 67.0F - height;
      x = MathUtils.clamp(x, 10.0F, App.stage.getWidth() - width - 10.0F);
      y = MathUtils.clamp(y, 10.0F, App.stage.getHeight() - height - 10.0F);
      this.arrowX = right ? -44.0F : width;
      this.arrowY -= y;
      if (this.arrowY < 10.0F) {
         this.arrowY = 10.0F;
      }

      this.setBounds(x, y, width, height);
      this.setOrigin(right ? this.arrowX : this.arrowX + 44.0F, this.arrowY + 50.0F);
      this.arrowY = MathUtils.clamp(this.arrowY, 5.0F, height - 87.0F);
   }

   protected boolean dir(boolean right) {
      return right;
   }

   public boolean show(Menu menu) {
      boolean shown = this.show(menu.positionActor, menu.positionX, menu.positionY, menu.positionWidth, menu.positionHeight, menu.preferRight);
      this.bgX = menu.bgX;
      this.bgY = menu.bgY;
      this.bgWidth = menu.bgWidth;
      this.bgHeight = menu.bgHeight;
      this.setBackgroundOffset(menu.bgOffsetX, menu.bgOffsetY, menu.bgOffsetWidth, menu.bgOffsetHeight);
      return shown;
   }

   public boolean show(Actor positionActor, float positionX, float positionY, float positionWidth, float positionHeight, boolean preferRight) {
      this.setTouchable(Touchable.enabled);
      this.pack();
      this.positionActor = positionActor;
      this.positionX = positionX;
      this.positionY = positionY;
      this.positionWidth = positionWidth;
      this.positionHeight = positionHeight;
      this.preferRight = preferRight;
      this.updatePosition();
      if (positionActor instanceof Button) {
         ((Button)positionActor).setChecked(true);
      }

      if (!this.hasParent()) {
         this.hidden = true;
      }

      if (!this.hidden) {
         return false;
      } else {
         this.hidden = false;
         this.clearActions();
         App.stage.mouseMoved(Integer.MAX_VALUE, Integer.MAX_VALUE);
         App.stage.act(0.0F);
         App.stage.setKeyboardFocus(this);
         if (this.autoHide) {
            App.stage.addCaptureListener(this.hideListener);
         }

         App.stage.addActor(this);
         this.getColor().a = 1.0F;
         if (this.animate) {
            this.setScale(0.0F, 0.0F);
            this.addAction(Actions.scaleTo(1.0F, 1.0F, 0.2F, Interpolation.fastSlow));
            this.addAction(this.bgAlpha = new FloatAction(0.0F, 1.0F, 0.2F, Interpolation.slowFast));
         } else {
            this.setScale(1.0F, 1.0F);
            (this.bgAlpha = new FloatAction(1.0F, 1.0F, 0.0F)).act(0.0F);
         }

         menusShown++;
         return true;
      }
   }

   public boolean hide() {
      if (this.positionActor instanceof Button) {
         ((Button)this.positionActor).setChecked(false);
      }

      App.stage.removeCaptureListener(this.hideListener);
      App.stage.setScrollFocus(App.gloom.rowsScroll);
      this.setTouchable(Touchable.disabled);
      if (this.hidden) {
         return false;
      } else {
         this.hidden = true;
         if (!this.hasParent()) {
            return false;
         } else {
            menusShown--;
            this.clearActions();
            if (this.animate) {
               float time = 0.35F;
               this.addAction(
                  Actions.sequence(
                     Actions.parallel(
                        Actions.fadeOut(0.35F, Interpolation.fastSlow), Actions.moveTo(this.getX(), this.getY() - 50.0F, 0.35F, Interpolation.fastSlow)
                     ),
                     Actions.removeActor()
                  )
               );
            } else {
               this.remove();
            }

            return true;
         }
      }
   }

   @Override
   protected void setParent(Group parent) {
      if (parent == null) {
         App.stage.removeCaptureListener(this.hideListener);
      }

      super.setParent(parent);
   }
}
