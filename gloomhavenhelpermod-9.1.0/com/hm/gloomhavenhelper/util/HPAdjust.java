package com.hm.gloomhavenhelper.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.hm.gloomhavenhelper.App;

public abstract class HPAdjust extends DragAdjust {
   private Actor listenerActor;
   public Container changeContainer;
   public float minX = -2.1474836E9F;

   public HPAdjust(Actor listenerActor, String font) {
      super(new Label("", App.skin, font, Color.WHITE), listenerActor);
      this.listenerActor = listenerActor;
      this.onlyHorizontal = true;
      this.changeContainer = new Container(this.label) {
         @Override
         public void draw(Batch batch, float parentAlpha) {
            if (this.getColor().a == 1.0F) {
               HPAdjust.this.getPosition(App.v2);
               this.setPosition(App.v2.x, App.v2.y);
               this.pack();
            }

            super.draw(batch, parentAlpha);
         }
      };
      this.changeContainer.background(App.drawable("rounded", new Color(0.0F, 0.0F, 0.0F, 0.9F)));
      this.changeContainer.pad(-7.0F, 11.0F, 5.0F, 11.0F);
      this.changeContainer.setTouchable(Touchable.disabled);
      this.changeContainer.setVisible(false);
   }

   protected abstract void getPosition(Vector2 var1);

   @Override
   protected void setValue(int value) {
      this.label.setColor(value + this.extra < this.start ? App.healthRed : App.healthGreen);
      this.reset();
      this.label.getParent().setVisible(true);
      this.label.getParent().toFront();
   }

   protected void apply() {
   }

   @Override
   public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
      if (x <= this.minX) {
         return false;
      } else {
         this.reset();
         return super.touchDown(event, x, y, pointer, button);
      }
   }

   @Override
   public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
      super.touchUp(event, x, y, pointer, button);
      this.dragStop(event, x, y, pointer);
   }

   @Override
   public void dragStart(InputEvent event, float x, float y, int pointer) {
      if (!App.config.hpDrag) {
         this.cancel();
      } else {
         if (!this.label.getParent().isVisible()) {
            this.label.setText("");
         }

         this.reset();
         super.dragStart(event, x, y, pointer);
         App.stage.cancelTouchFocusExcept(this, this.listenerActor);
      }
   }

   private void reset() {
      this.label.getParent().clearActions();
      this.label.getParent().getColor().a = 1.0F;
   }

   @Override
   public void dragStop(InputEvent event, float x, float y, int pointer) {
      super.dragStop(event, x, y, pointer);
      this.hideLabel();
   }

   @Override
   public void adjust(int amount) {
      this.apply();
      this.reset();
      super.adjust(amount);
      this.hideLabel();
   }

   void hideLabel() {
      final Group container = this.label.getParent();
      if (container.isVisible()) {
         container.clearActions();
         container.addAction(
            Actions.sequence(
               Actions.delay(2.0F),
               new Action() {
                  @Override
                  public boolean act(float delta) {
                     container.getColor().a = 0.99F;
                     HPAdjust.this.apply();
                     container.addAction(
                        Actions.sequence(
                           Actions.parallel(
                              Actions.sequence(Actions.delay(0.2F), Actions.fadeOut(0.6F, Interpolation.slowFast)),
                              Actions.moveTo(container.getX(), container.getY() + 50.0F, 1.0F, Interpolation.fastSlow)
                           ),
                           Actions.hide()
                        )
                     );
                     return true;
                  }
               }
            )
         );
      }
   }
}
