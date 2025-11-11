package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Null;

public class Tooltip extends InputListener {
   static Vector2 tmp = new Vector2();
   private final TooltipManager manager;
   final Container container;
   boolean instant;
   boolean always;
   Actor targetActor;

   public Tooltip(@Null Actor contents) {
      this(contents, TooltipManager.getInstance());
   }

   public Tooltip(@Null Actor contents, TooltipManager manager) {
      this.manager = manager;
      this.container = new Container(contents) {
         @Override
         public void act(float delta) {
            super.act(delta);
            if (Tooltip.this.targetActor != null && Tooltip.this.targetActor.getStage() == null) {
               this.remove();
            }
         }
      };
      this.container.setTouchable(Touchable.disabled);
   }

   public TooltipManager getManager() {
      return this.manager;
   }

   public Container getContainer() {
      return this.container;
   }

   public void setActor(@Null Actor contents) {
      this.container.setActor(contents);
   }

   @Null
   public Actor getActor() {
      return this.container.getActor();
   }

   public void setInstant(boolean instant) {
      this.instant = instant;
   }

   public void setAlways(boolean always) {
      this.always = always;
   }

   @Override
   public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
      if (this.instant) {
         this.container.toFront();
         return false;
      } else {
         this.manager.touchDown(this);
         return false;
      }
   }

   @Override
   public boolean mouseMoved(InputEvent event, float x, float y) {
      if (this.container.hasParent()) {
         return false;
      } else {
         this.setContainerPosition(event.getListenerActor(), x, y);
         return true;
      }
   }

   private void setContainerPosition(Actor actor, float x, float y) {
      this.targetActor = actor;
      Stage stage = actor.getStage();
      if (stage != null) {
         this.container.pack();
         float offsetX = this.manager.offsetX;
         float offsetY = this.manager.offsetY;
         float dist = this.manager.edgeDistance;
         Vector2 point = actor.localToStageCoordinates(tmp.set(x + offsetX, y - offsetY - this.container.getHeight()));
         if (point.y < dist) {
            point = actor.localToStageCoordinates(tmp.set(x + offsetX, y + offsetY));
         }

         if (point.x < dist) {
            point.x = dist;
         }

         if (point.x + this.container.getWidth() > stage.getWidth() - dist) {
            point.x = stage.getWidth() - dist - this.container.getWidth();
         }

         if (point.y + this.container.getHeight() > stage.getHeight() - dist) {
            point.y = stage.getHeight() - dist - this.container.getHeight();
         }

         this.container.setPosition(point.x, point.y);
         point = actor.localToStageCoordinates(tmp.set(actor.getWidth() / 2.0F, actor.getHeight() / 2.0F));
         point.sub(this.container.getX(), this.container.getY());
         this.container.setOrigin(point.x, point.y);
      }
   }

   @Override
   public void enter(InputEvent event, float x, float y, int pointer, @Null Actor fromActor) {
      if (pointer == -1) {
         if (!Gdx.input.isTouched()) {
            Actor actor = event.getListenerActor();
            if (fromActor == null || !fromActor.isDescendantOf(actor)) {
               this.setContainerPosition(actor, x, y);
               this.manager.enter(this);
            }
         }
      }
   }

   @Override
   public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
      if (toActor == null || !toActor.isDescendantOf(event.getListenerActor())) {
         this.hide();
      }
   }

   public void hide() {
      this.manager.hide(this);
   }
}
