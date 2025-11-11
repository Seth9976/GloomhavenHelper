package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap.Entry;
import com.badlogic.gdx.utils.ObjectMap;

public class DragAndDrop {
    public static class Payload {
        @Null
        Actor dragActor;
        @Null
        Actor invalidDragActor;
        @Null
        Object object;
        @Null
        Actor validDragActor;

        @Null
        public Actor getDragActor() {
            return this.dragActor;
        }

        @Null
        public Actor getInvalidDragActor() {
            return this.invalidDragActor;
        }

        @Null
        public Object getObject() {
            return this.object;
        }

        @Null
        public Actor getValidDragActor() {
            return this.validDragActor;
        }

        public void setDragActor(@Null Actor actor0) {
            this.dragActor = actor0;
        }

        public void setInvalidDragActor(@Null Actor actor0) {
            this.invalidDragActor = actor0;
        }

        public void setObject(@Null Object object0) {
            this.object = object0;
        }

        public void setValidDragActor(@Null Actor actor0) {
            this.validDragActor = actor0;
        }
    }

    public static abstract class Source {
        final Actor actor;

        public Source(Actor actor0) {
            if(actor0 == null) {
                throw new IllegalArgumentException("actor cannot be null.");
            }
            this.actor = actor0;
        }

        public void drag(InputEvent inputEvent0, float f, float f1, int v) {
        }

        @Null
        public abstract Payload dragStart(InputEvent arg1, float arg2, float arg3, int arg4);

        public void dragStop(InputEvent inputEvent0, float f, float f1, int v, @Null Payload dragAndDrop$Payload0, @Null Target dragAndDrop$Target0) {
        }

        public Actor getActor() {
            return this.actor;
        }
    }

    public static abstract class Target {
        final Actor actor;

        public Target(Actor actor0) {
            if(actor0 == null) {
                throw new IllegalArgumentException("actor cannot be null.");
            }
            this.actor = actor0;
            Stage stage0 = actor0.getStage();
            if(stage0 != null && actor0 == stage0.getRoot()) {
                throw new IllegalArgumentException("The stage root cannot be a drag and drop target.");
            }
        }

        public abstract boolean drag(Source arg1, Payload arg2, float arg3, float arg4, int arg5);

        public abstract void drop(Source arg1, Payload arg2, float arg3, float arg4, int arg5);

        public Actor getActor() {
            return this.actor;
        }

        public void reset(Source dragAndDrop$Source0, Payload dragAndDrop$Payload0) {
        }
    }

    int activePointer;
    private int button;
    boolean cancelTouchFocus;
    Actor dragActor;
    float dragActorX;
    float dragActorY;
    Source dragSource;
    int dragTime;
    long dragValidTime;
    boolean isValidTarget;
    boolean keepWithinStage;
    Payload payload;
    boolean removeDragActor;
    final ObjectMap sourceListeners;
    private float tapSquareSize;
    Target target;
    final Array targets;
    static final Vector2 tmpVector;
    float touchOffsetX;
    float touchOffsetY;

    static {
        DragAndDrop.tmpVector = new Vector2();
    }

    public DragAndDrop() {
        this.targets = new Array();
        this.sourceListeners = new ObjectMap();
        this.tapSquareSize = 8.0f;
        this.dragActorX = 0.0f;
        this.dragActorY = 0.0f;
        this.dragTime = 0xFA;
        this.activePointer = -1;
        this.cancelTouchFocus = true;
        this.keepWithinStage = true;
    }

    public void addSource(Source dragAndDrop$Source0) {
        com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.1 dragAndDrop$10 = new DragListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragListener
            public void drag(InputEvent inputEvent0, float f, float f1, int v) {
                float f3;
                float f2;
                if(DragAndDrop.this.payload == null) {
                    return;
                }
                if(v != DragAndDrop.this.activePointer) {
                    return;
                }
                Stage stage0 = inputEvent0.getStage();
                Actor actor0 = DragAndDrop.this.dragActor;
                if(actor0 == null) {
                    f2 = 0.0f;
                    f3 = 0.0f;
                }
                else {
                    f2 = actor0.getX();
                    f3 = actor0.getY();
                    actor0.setPosition(2147483648.0f, 2147483648.0f);
                }
                float f4 = inputEvent0.getStageX() + DragAndDrop.this.touchOffsetX;
                float f5 = inputEvent0.getStageY() + DragAndDrop.this.touchOffsetY;
                boolean z = true;
                Actor actor1 = inputEvent0.getStage().hit(f4, f5, true);
                if(actor1 == null) {
                    actor1 = inputEvent0.getStage().hit(f4, f5, false);
                }
                if(actor0 != null) {
                    actor0.setPosition(f2, f3);
                }
                Target dragAndDrop$Target0 = null;
                DragAndDrop dragAndDrop0 = DragAndDrop.this;
                dragAndDrop0.isValidTarget = false;
                Actor actor2 = null;
                if(actor1 != null) {
                    int v1 = dragAndDrop0.targets.size;
                    for(int v2 = 0; v2 < v1; ++v2) {
                        Target dragAndDrop$Target1 = (Target)DragAndDrop.this.targets.get(v2);
                        if(dragAndDrop$Target1.actor.isAscendantOf(actor1)) {
                            Vector2 vector20 = DragAndDrop.tmpVector.set(f4, f5);
                            dragAndDrop$Target1.actor.stageToLocalCoordinates(vector20);
                            dragAndDrop$Target0 = dragAndDrop$Target1;
                            break;
                        }
                    }
                }
                if(dragAndDrop$Target0 != DragAndDrop.this.target) {
                    DragAndDrop.this.target = dragAndDrop$Target0;
                }
                if(dragAndDrop$Target0 != null) {
                    DragAndDrop.this.isValidTarget = dragAndDrop$Target0.drag(dragAndDrop$Source0, DragAndDrop.this.payload, DragAndDrop.tmpVector.x, DragAndDrop.tmpVector.y, v);
                }
                if(DragAndDrop.this.target != null) {
                    actor2 = DragAndDrop.this.isValidTarget ? DragAndDrop.this.payload.validDragActor : DragAndDrop.this.payload.invalidDragActor;
                }
                if(actor2 == null) {
                    actor2 = DragAndDrop.this.payload.dragActor;
                }
                if(actor2 != actor0) {
                    if(actor0 != null && DragAndDrop.this.removeDragActor) {
                        actor0.remove();
                    }
                    DragAndDrop dragAndDrop1 = DragAndDrop.this;
                    dragAndDrop1.dragActor = actor2;
                    if(actor2.getStage() != null) {
                        z = false;
                    }
                    dragAndDrop1.removeDragActor = z;
                    if(DragAndDrop.this.removeDragActor) {
                        stage0.addActor(actor2);
                    }
                }
                if(actor2 == null) {
                    return;
                }
                float f6 = inputEvent0.getStageX() - actor2.getWidth() + DragAndDrop.this.dragActorX;
                float f7 = inputEvent0.getStageY() + DragAndDrop.this.dragActorY;
                if(DragAndDrop.this.keepWithinStage) {
                    if(f6 < 0.0f) {
                        f6 = 0.0f;
                    }
                    if(f7 < 0.0f) {
                        f7 = 0.0f;
                    }
                    if(actor2.getWidth() + f6 > stage0.getWidth()) {
                        f6 = stage0.getWidth() - actor2.getWidth();
                    }
                    if(actor2.getHeight() + f7 > stage0.getHeight()) {
                        f7 = stage0.getHeight() - actor2.getHeight();
                    }
                }
                actor2.setPosition(f6, f7);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragListener
            public void dragStart(InputEvent inputEvent0, float f, float f1, int v) {
                if(DragAndDrop.this.activePointer != -1) {
                    inputEvent0.stop();
                    return;
                }
                DragAndDrop.this.activePointer = v;
                DragAndDrop.this.dragValidTime = System.currentTimeMillis() + ((long)DragAndDrop.this.dragTime);
                DragAndDrop.this.dragSource = dragAndDrop$Source0;
                float f2 = this.getTouchDownX();
                float f3 = this.getTouchDownY();
                DragAndDrop.this.payload = dragAndDrop$Source0.dragStart(inputEvent0, f2, f3, v);
                inputEvent0.stop();
                if(DragAndDrop.this.cancelTouchFocus && DragAndDrop.this.payload != null) {
                    Stage stage0 = dragAndDrop$Source0.getActor().getStage();
                    if(stage0 != null) {
                        stage0.cancelTouchFocusExcept(this, dragAndDrop$Source0.getActor());
                    }
                }
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragListener
            public void dragStop(InputEvent inputEvent0, float f, float f1, int v) {
                if(v != DragAndDrop.this.activePointer) {
                    return;
                }
                DragAndDrop.this.activePointer = -1;
                if(DragAndDrop.this.payload == null) {
                    return;
                }
                if(System.currentTimeMillis() < DragAndDrop.this.dragValidTime) {
                    DragAndDrop.this.isValidTarget = false;
                }
                if(DragAndDrop.this.dragActor != null && DragAndDrop.this.removeDragActor) {
                    DragAndDrop.this.dragActor.remove();
                }
                if(DragAndDrop.this.isValidTarget) {
                    DragAndDrop.this.target.actor.stageToLocalCoordinates(DragAndDrop.tmpVector.set(inputEvent0.getStageX() + DragAndDrop.this.touchOffsetX, inputEvent0.getStageY() + DragAndDrop.this.touchOffsetY));
                    DragAndDrop.this.target.drop(dragAndDrop$Source0, DragAndDrop.this.payload, DragAndDrop.tmpVector.x, DragAndDrop.tmpVector.y, v);
                }
                DragAndDrop.this.dragSource = null;
                DragAndDrop.this.payload = null;
                DragAndDrop.this.target = null;
                DragAndDrop.this.isValidTarget = false;
                DragAndDrop.this.dragActor = null;
            }
        };
        dragAndDrop$10.setTapSquareSize(this.tapSquareSize);
        dragAndDrop$10.setButton(this.button);
        dragAndDrop$Source0.actor.addCaptureListener(dragAndDrop$10);
        this.sourceListeners.put(dragAndDrop$Source0, dragAndDrop$10);
    }

    public void addTarget(Target dragAndDrop$Target0) {
        this.targets.add(dragAndDrop$Target0);
    }

    public void cancelTouchFocusExcept(Source dragAndDrop$Source0) {
        DragListener dragListener0 = (DragListener)this.sourceListeners.get(dragAndDrop$Source0);
        if(dragListener0 == null) {
            return;
        }
        Stage stage0 = dragAndDrop$Source0.getActor().getStage();
        if(stage0 != null) {
            stage0.cancelTouchFocusExcept(dragListener0, dragAndDrop$Source0.getActor());
        }
    }

    public void clear() {
        this.targets.clear();
        for(Object object0: this.sourceListeners.entries()) {
            ((Source)((Entry)object0).key).actor.removeCaptureListener(((EventListener)((Entry)object0).value));
        }
        this.sourceListeners.clear();
    }

    @Null
    public Actor getDragActor() {
        return this.dragActor;
    }

    @Null
    public Payload getDragPayload() {
        return this.payload;
    }

    @Null
    public Source getDragSource() {
        return this.dragSource;
    }

    public int getDragTime() {
        return this.dragTime;
    }

    public boolean isDragValid() {
        return this.payload != null && System.currentTimeMillis() >= this.dragValidTime;
    }

    public boolean isDragging() {
        return this.payload != null;
    }

    public void removeSource(Source dragAndDrop$Source0) {
        DragListener dragListener0 = (DragListener)this.sourceListeners.remove(dragAndDrop$Source0);
        dragAndDrop$Source0.actor.removeCaptureListener(dragListener0);
    }

    public void removeTarget(Target dragAndDrop$Target0) {
        this.targets.removeValue(dragAndDrop$Target0, true);
    }

    public void setButton(int v) {
        this.button = v;
    }

    public void setCancelTouchFocus(boolean z) {
        this.cancelTouchFocus = z;
    }

    public void setDragActorPosition(float f, float f1) {
        this.dragActorX = f;
        this.dragActorY = f1;
    }

    public void setDragTime(int v) {
        this.dragTime = v;
    }

    public void setKeepWithinStage(boolean z) {
        this.keepWithinStage = z;
    }

    public void setTapSquareSize(float f) {
        this.tapSquareSize = f;
    }

    public void setTouchOffset(float f, float f1) {
        this.touchOffsetX = f;
        this.touchOffsetY = f1;
    }
}

