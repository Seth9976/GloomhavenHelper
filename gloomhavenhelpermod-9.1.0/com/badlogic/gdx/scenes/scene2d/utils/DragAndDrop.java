package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;

public class DragAndDrop {
   static final Vector2 tmpVector = new Vector2();
   DragAndDrop.Source dragSource;
   DragAndDrop.Payload payload;
   Actor dragActor;
   boolean removeDragActor;
   DragAndDrop.Target target;
   boolean isValidTarget;
   final Array targets = new Array();
   final ObjectMap sourceListeners = new ObjectMap();
   private float tapSquareSize = 8.0F;
   private int button;
   float dragActorX = 0.0F;
   float dragActorY = 0.0F;
   float touchOffsetX;
   float touchOffsetY;
   long dragValidTime;
   int dragTime = 250;
   int activePointer = -1;
   boolean cancelTouchFocus = true;
   boolean keepWithinStage = true;

   public void addSource(final DragAndDrop.Source source) {
      DragListener listener = new DragListener() {
         @Override
         public void dragStart(InputEvent event, float x, float y, int pointer) {
            if (DragAndDrop.this.activePointer != -1) {
               event.stop();
            } else {
               DragAndDrop.this.activePointer = pointer;
               DragAndDrop.this.dragValidTime = System.currentTimeMillis() + DragAndDrop.this.dragTime;
               DragAndDrop.this.dragSource = source;
               DragAndDrop.this.payload = source.dragStart(event, this.getTouchDownX(), this.getTouchDownY(), pointer);
               event.stop();
               if (DragAndDrop.this.cancelTouchFocus && DragAndDrop.this.payload != null) {
                  Stage stage = source.getActor().getStage();
                  if (stage != null) {
                     stage.cancelTouchFocusExcept(this, source.getActor());
                  }
               }
            }
         }

         @Override
         public void drag(InputEvent event, float x, float y, int pointer) {
            if (DragAndDrop.this.payload != null) {
               if (pointer == DragAndDrop.this.activePointer) {
                  source.drag(event, x, y, pointer);
                  Stage stage = event.getStage();
                  Actor oldDragActor = DragAndDrop.this.dragActor;
                  float oldDragActorX = 0.0F;
                  float oldDragActorY = 0.0F;
                  if (oldDragActor != null) {
                     oldDragActorX = oldDragActor.getX();
                     oldDragActorY = oldDragActor.getY();
                     oldDragActor.setPosition(2.1474836E9F, 2.1474836E9F);
                  }

                  float stageX = event.getStageX() + DragAndDrop.this.touchOffsetX;
                  float stageY = event.getStageY() + DragAndDrop.this.touchOffsetY;
                  Actor hit = event.getStage().hit(stageX, stageY, true);
                  if (hit == null) {
                     hit = event.getStage().hit(stageX, stageY, false);
                  }

                  if (oldDragActor != null) {
                     oldDragActor.setPosition(oldDragActorX, oldDragActorY);
                  }

                  DragAndDrop.Target newTarget = null;
                  DragAndDrop.this.isValidTarget = false;
                  if (hit != null) {
                     int i = 0;

                     for (int n = DragAndDrop.this.targets.size; i < n; i++) {
                        DragAndDrop.Target target = (DragAndDrop.Target)DragAndDrop.this.targets.get(i);
                        if (target.actor.isAscendantOf(hit)) {
                           newTarget = target;
                           target.actor.stageToLocalCoordinates(DragAndDrop.tmpVector.set(stageX, stageY));
                           break;
                        }
                     }
                  }

                  if (newTarget != DragAndDrop.this.target) {
                     if (DragAndDrop.this.target != null) {
                        DragAndDrop.this.target.reset(source, DragAndDrop.this.payload);
                     }

                     DragAndDrop.this.target = newTarget;
                  }

                  if (newTarget != null) {
                     DragAndDrop.this.isValidTarget = newTarget.drag(
                        source, DragAndDrop.this.payload, DragAndDrop.tmpVector.x, DragAndDrop.tmpVector.y, pointer
                     );
                  }

                  Actor actor = null;
                  if (DragAndDrop.this.target != null) {
                     actor = DragAndDrop.this.isValidTarget ? DragAndDrop.this.payload.validDragActor : DragAndDrop.this.payload.invalidDragActor;
                  }

                  if (actor == null) {
                     actor = DragAndDrop.this.payload.dragActor;
                  }

                  if (actor != oldDragActor) {
                     if (oldDragActor != null && DragAndDrop.this.removeDragActor) {
                        oldDragActor.remove();
                     }

                     DragAndDrop.this.dragActor = actor;
                     DragAndDrop.this.removeDragActor = actor.getStage() == null;
                     if (DragAndDrop.this.removeDragActor) {
                        stage.addActor(actor);
                     }
                  }

                  if (actor != null) {
                     float actorX = event.getStageX() - actor.getWidth() + DragAndDrop.this.dragActorX;
                     float actorY = event.getStageY() + DragAndDrop.this.dragActorY;
                     if (DragAndDrop.this.keepWithinStage) {
                        if (actorX < 0.0F) {
                           actorX = 0.0F;
                        }

                        if (actorY < 0.0F) {
                           actorY = 0.0F;
                        }

                        if (actorX + actor.getWidth() > stage.getWidth()) {
                           actorX = stage.getWidth() - actor.getWidth();
                        }

                        if (actorY + actor.getHeight() > stage.getHeight()) {
                           actorY = stage.getHeight() - actor.getHeight();
                        }
                     }

                     actor.setPosition(actorX, actorY);
                  }
               }
            }
         }

         @Override
         public void dragStop(InputEvent event, float x, float y, int pointer) {
            if (pointer == DragAndDrop.this.activePointer) {
               DragAndDrop.this.activePointer = -1;
               if (DragAndDrop.this.payload != null) {
                  if (System.currentTimeMillis() < DragAndDrop.this.dragValidTime) {
                     DragAndDrop.this.isValidTarget = false;
                  }

                  if (DragAndDrop.this.dragActor != null && DragAndDrop.this.removeDragActor) {
                     DragAndDrop.this.dragActor.remove();
                  }

                  if (DragAndDrop.this.isValidTarget) {
                     float stageX = event.getStageX() + DragAndDrop.this.touchOffsetX;
                     float stageY = event.getStageY() + DragAndDrop.this.touchOffsetY;
                     DragAndDrop.this.target.actor.stageToLocalCoordinates(DragAndDrop.tmpVector.set(stageX, stageY));
                     DragAndDrop.this.target.drop(source, DragAndDrop.this.payload, DragAndDrop.tmpVector.x, DragAndDrop.tmpVector.y, pointer);
                  }

                  source.dragStop(event, x, y, pointer, DragAndDrop.this.payload, DragAndDrop.this.isValidTarget ? DragAndDrop.this.target : null);
                  if (DragAndDrop.this.target != null) {
                     DragAndDrop.this.target.reset(source, DragAndDrop.this.payload);
                  }

                  DragAndDrop.this.dragSource = null;
                  DragAndDrop.this.payload = null;
                  DragAndDrop.this.target = null;
                  DragAndDrop.this.isValidTarget = false;
                  DragAndDrop.this.dragActor = null;
               }
            }
         }
      };
      listener.setTapSquareSize(this.tapSquareSize);
      listener.setButton(this.button);
      source.actor.addCaptureListener(listener);
      this.sourceListeners.put(source, listener);
   }

   public void removeSource(DragAndDrop.Source source) {
      DragListener dragListener = (DragListener)this.sourceListeners.remove(source);
      source.actor.removeCaptureListener(dragListener);
   }

   public void addTarget(DragAndDrop.Target target) {
      this.targets.add(target);
   }

   public void removeTarget(DragAndDrop.Target target) {
      this.targets.removeValue(target, true);
   }

   public void clear() {
      this.targets.clear();

      for (ObjectMap.Entry entry : this.sourceListeners.entries()) {
         ((DragAndDrop.Source)entry.key).actor.removeCaptureListener((EventListener)entry.value);
      }

      this.sourceListeners.clear();
   }

   public void cancelTouchFocusExcept(DragAndDrop.Source except) {
      DragListener listener = (DragListener)this.sourceListeners.get(except);
      if (listener != null) {
         Stage stage = except.getActor().getStage();
         if (stage != null) {
            stage.cancelTouchFocusExcept(listener, except.getActor());
         }
      }
   }

   public void setTapSquareSize(float halfTapSquareSize) {
      this.tapSquareSize = halfTapSquareSize;
   }

   public void setButton(int button) {
      this.button = button;
   }

   public void setDragActorPosition(float dragActorX, float dragActorY) {
      this.dragActorX = dragActorX;
      this.dragActorY = dragActorY;
   }

   public void setTouchOffset(float touchOffsetX, float touchOffsetY) {
      this.touchOffsetX = touchOffsetX;
      this.touchOffsetY = touchOffsetY;
   }

   public boolean isDragging() {
      return this.payload != null;
   }

   @Null
   public Actor getDragActor() {
      return this.dragActor;
   }

   @Null
   public DragAndDrop.Payload getDragPayload() {
      return this.payload;
   }

   @Null
   public DragAndDrop.Source getDragSource() {
      return this.dragSource;
   }

   public void setDragTime(int dragMillis) {
      this.dragTime = dragMillis;
   }

   public int getDragTime() {
      return this.dragTime;
   }

   public boolean isDragValid() {
      return this.payload != null && System.currentTimeMillis() >= this.dragValidTime;
   }

   public void setCancelTouchFocus(boolean cancelTouchFocus) {
      this.cancelTouchFocus = cancelTouchFocus;
   }

   public void setKeepWithinStage(boolean keepWithinStage) {
      this.keepWithinStage = keepWithinStage;
   }

   public static class Payload {
      @Null
      Actor dragActor;
      @Null
      Actor validDragActor;
      @Null
      Actor invalidDragActor;
      @Null
      Object object;

      public void setDragActor(@Null Actor dragActor) {
         this.dragActor = dragActor;
      }

      @Null
      public Actor getDragActor() {
         return this.dragActor;
      }

      public void setValidDragActor(@Null Actor validDragActor) {
         this.validDragActor = validDragActor;
      }

      @Null
      public Actor getValidDragActor() {
         return this.validDragActor;
      }

      public void setInvalidDragActor(@Null Actor invalidDragActor) {
         this.invalidDragActor = invalidDragActor;
      }

      @Null
      public Actor getInvalidDragActor() {
         return this.invalidDragActor;
      }

      @Null
      public Object getObject() {
         return this.object;
      }

      public void setObject(@Null Object object) {
         this.object = object;
      }
   }

   public abstract static class Source {
      final Actor actor;

      public Source(Actor actor) {
         if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
         } else {
            this.actor = actor;
         }
      }

      @Null
      public abstract DragAndDrop.Payload dragStart(InputEvent var1, float var2, float var3, int var4);

      public void drag(InputEvent event, float x, float y, int pointer) {
      }

      public void dragStop(InputEvent event, float x, float y, int pointer, @Null DragAndDrop.Payload payload, @Null DragAndDrop.Target target) {
      }

      public Actor getActor() {
         return this.actor;
      }
   }

   public abstract static class Target {
      final Actor actor;

      public Target(Actor actor) {
         if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
         } else {
            this.actor = actor;
            Stage stage = actor.getStage();
            if (stage != null && actor == stage.getRoot()) {
               throw new IllegalArgumentException("The stage root cannot be a drag and drop target.");
            }
         }
      }

      public abstract boolean drag(DragAndDrop.Source var1, DragAndDrop.Payload var2, float var3, float var4, int var5);

      public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
      }

      public abstract void drop(DragAndDrop.Source var1, DragAndDrop.Payload var2, float var3, float var4, int var5);

      public Actor getActor() {
         return this.actor;
      }
   }
}
