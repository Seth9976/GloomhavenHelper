package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.input.GestureDetector.GestureAdapter;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Null;

public class ActorGestureListener implements EventListener {
    Actor actor;
    private final GestureDetector detector;
    InputEvent event;
    static final Vector2 tmpCoords;
    static final Vector2 tmpCoords2;
    Actor touchDownTarget;

    static {
        ActorGestureListener.tmpCoords = new Vector2();
        ActorGestureListener.tmpCoords2 = new Vector2();
    }

    public ActorGestureListener() {
        this(20.0f, 0.4f, 1.1f, 2147483648.0f);
    }

    public ActorGestureListener(float f, float f1, float f2, float f3) {
        this.detector = new GestureDetector(f, f1, f2, f3, new GestureAdapter() {
            private final Vector2 initialPointer1;
            private final Vector2 initialPointer2;
            private final Vector2 pointer1;
            private final Vector2 pointer2;

            {
                this.initialPointer1 = new Vector2();
                this.initialPointer2 = new Vector2();
                this.pointer1 = new Vector2();
                this.pointer2 = new Vector2();
            }

            @Override  // com.badlogic.gdx.input.GestureDetector$GestureAdapter
            public boolean fling(float f, float f1, int v) {
                this.stageToLocalAmount(ActorGestureListener.tmpCoords.set(f, f1));
                ActorGestureListener.this.fling(ActorGestureListener.this.event, ActorGestureListener.tmpCoords.x, ActorGestureListener.tmpCoords.y, v);
                return true;
            }

            @Override  // com.badlogic.gdx.input.GestureDetector$GestureAdapter
            public boolean longPress(float f, float f1) {
                ActorGestureListener.this.actor.stageToLocalCoordinates(ActorGestureListener.tmpCoords.set(f, f1));
                return false;
            }

            @Override  // com.badlogic.gdx.input.GestureDetector$GestureAdapter
            public boolean pan(float f, float f1, float f2, float f3) {
                this.stageToLocalAmount(ActorGestureListener.tmpCoords.set(f2, f3));
                float f4 = ActorGestureListener.tmpCoords.x;
                float f5 = ActorGestureListener.tmpCoords.y;
                ActorGestureListener.this.actor.stageToLocalCoordinates(ActorGestureListener.tmpCoords.set(f, f1));
                ActorGestureListener.this.pan(ActorGestureListener.this.event, ActorGestureListener.tmpCoords.x, ActorGestureListener.tmpCoords.y, f4, f5);
                return true;
            }

            @Override  // com.badlogic.gdx.input.GestureDetector$GestureAdapter
            public boolean panStop(float f, float f1, int v, int v1) {
                ActorGestureListener.this.actor.stageToLocalCoordinates(ActorGestureListener.tmpCoords.set(f, f1));
                return true;
            }

            @Override  // com.badlogic.gdx.input.GestureDetector$GestureAdapter
            public boolean pinch(Vector2 vector20, Vector2 vector21, Vector2 vector22, Vector2 vector23) {
                ActorGestureListener.this.actor.stageToLocalCoordinates(this.initialPointer1.set(vector20));
                ActorGestureListener.this.actor.stageToLocalCoordinates(this.initialPointer2.set(vector21));
                ActorGestureListener.this.actor.stageToLocalCoordinates(this.pointer1.set(vector22));
                ActorGestureListener.this.actor.stageToLocalCoordinates(this.pointer2.set(vector23));
                return true;
            }

            private void stageToLocalAmount(Vector2 vector20) {
                ActorGestureListener.this.actor.stageToLocalCoordinates(vector20);
                vector20.sub(ActorGestureListener.this.actor.stageToLocalCoordinates(ActorGestureListener.tmpCoords2.set(0.0f, 0.0f)));
            }

            @Override  // com.badlogic.gdx.input.GestureDetector$GestureAdapter
            public boolean tap(float f, float f1, int v, int v1) {
                ActorGestureListener.this.actor.stageToLocalCoordinates(ActorGestureListener.tmpCoords.set(f, f1));
                return true;
            }

            @Override  // com.badlogic.gdx.input.GestureDetector$GestureAdapter
            public boolean zoom(float f, float f1) {
                return true;
            }
        });
    }

    public void fling(InputEvent inputEvent0, float f, float f1, int v) {
    }

    public GestureDetector getGestureDetector() {
        return this.detector;
    }

    @Null
    public Actor getTouchDownTarget() {
        return this.touchDownTarget;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.EventListener
    public boolean handle(Event event0) {
        if(!(event0 instanceof InputEvent)) {
            return false;
        }
        InputEvent inputEvent0 = (InputEvent)event0;
        switch(com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener.2.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[inputEvent0.getType().ordinal()]) {
            case 1: {
                this.actor = inputEvent0.getListenerActor();
                this.touchDownTarget = inputEvent0.getTarget();
                this.detector.touchDown(inputEvent0.getStageX(), inputEvent0.getStageY(), inputEvent0.getPointer(), inputEvent0.getButton());
                this.actor.stageToLocalCoordinates(ActorGestureListener.tmpCoords.set(inputEvent0.getStageX(), inputEvent0.getStageY()));
                if(inputEvent0.getTouchFocus()) {
                    inputEvent0.getStage().addTouchFocus(this, inputEvent0.getListenerActor(), inputEvent0.getTarget(), inputEvent0.getPointer(), inputEvent0.getButton());
                }
                return true;
            }
            case 2: {
                if(inputEvent0.isTouchFocusCancel()) {
                    this.detector.reset();
                    return false;
                }
                this.event = inputEvent0;
                this.actor = inputEvent0.getListenerActor();
                this.detector.touchUp(inputEvent0.getStageX(), inputEvent0.getStageY(), inputEvent0.getPointer(), inputEvent0.getButton());
                this.actor.stageToLocalCoordinates(ActorGestureListener.tmpCoords.set(inputEvent0.getStageX(), inputEvent0.getStageY()));
                return true;
            }
            case 3: {
                this.event = inputEvent0;
                this.actor = inputEvent0.getListenerActor();
                this.detector.touchDragged(inputEvent0.getStageX(), inputEvent0.getStageY(), inputEvent0.getPointer());
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public boolean longPress(Actor actor0, float f, float f1) [...] // Inlined contents

    public void pan(InputEvent inputEvent0, float f, float f1, float f2, float f3) {
    }

    public void panStop(InputEvent inputEvent0, float f, float f1, int v, int v1) {
    }

    public void pinch(InputEvent inputEvent0, Vector2 vector20, Vector2 vector21, Vector2 vector22, Vector2 vector23) {
    }

    public void tap(InputEvent inputEvent0, float f, float f1, int v, int v1) {
    }

    public void touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
    }

    public void touchUp(InputEvent inputEvent0, float f, float f1, int v, int v1) {
    }

    public void zoom(InputEvent inputEvent0, float f, float f1) {
    }

    class com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener.2 {
        static final int[] $SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type;

        static {
            com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener.2.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type = new int[Type.values().length];
            try {
                com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener.2.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[Type.touchDown.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener.2.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[Type.touchUp.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener.2.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[Type.touchDragged.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

