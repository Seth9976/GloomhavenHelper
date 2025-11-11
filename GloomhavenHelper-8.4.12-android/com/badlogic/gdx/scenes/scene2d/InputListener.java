package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Null;

public class InputListener implements EventListener {
    private static final Vector2 tmpCoords;

    static {
        InputListener.tmpCoords = new Vector2();
    }

    public void enter(InputEvent inputEvent0, float f, float f1, int v, @Null Actor actor0) {
    }

    public void exit(InputEvent inputEvent0, float f, float f1, int v, @Null Actor actor0) {
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.EventListener
    public boolean handle(Event event0) {
        if(!(event0 instanceof InputEvent)) {
            return false;
        }
        InputEvent inputEvent0 = (InputEvent)event0;
        switch(com.badlogic.gdx.scenes.scene2d.InputListener.1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[inputEvent0.getType().ordinal()]) {
            case 1: {
                return this.keyDown(inputEvent0, inputEvent0.getKeyCode());
            }
            case 2: {
                return this.keyUp(inputEvent0, inputEvent0.getKeyCode());
            }
            case 3: {
                return this.keyTyped(inputEvent0, inputEvent0.getCharacter());
            }
            default: {
                inputEvent0.toCoordinates(inputEvent0.getListenerActor(), InputListener.tmpCoords);
                switch(com.badlogic.gdx.scenes.scene2d.InputListener.1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[inputEvent0.getType().ordinal()]) {
                    case 4: {
                        boolean z = this.touchDown(inputEvent0, InputListener.tmpCoords.x, InputListener.tmpCoords.y, inputEvent0.getPointer(), inputEvent0.getButton());
                        if(z && inputEvent0.getTouchFocus()) {
                            inputEvent0.getStage().addTouchFocus(this, inputEvent0.getListenerActor(), inputEvent0.getTarget(), inputEvent0.getPointer(), inputEvent0.getButton());
                        }
                        return z;
                    }
                    case 5: {
                        this.touchUp(inputEvent0, InputListener.tmpCoords.x, InputListener.tmpCoords.y, inputEvent0.getPointer(), inputEvent0.getButton());
                        return true;
                    }
                    case 6: {
                        this.touchDragged(inputEvent0, InputListener.tmpCoords.x, InputListener.tmpCoords.y, inputEvent0.getPointer());
                        return true;
                    }
                    case 7: {
                        return this.mouseMoved(inputEvent0, InputListener.tmpCoords.x, InputListener.tmpCoords.y);
                    }
                    case 8: {
                        return this.scrolled(inputEvent0, InputListener.tmpCoords.x, InputListener.tmpCoords.y, inputEvent0.getScrollAmountX(), inputEvent0.getScrollAmountY());
                    }
                    case 9: {
                        this.enter(inputEvent0, InputListener.tmpCoords.x, InputListener.tmpCoords.y, inputEvent0.getPointer(), inputEvent0.getRelatedActor());
                        return false;
                    }
                    case 10: {
                        this.exit(inputEvent0, InputListener.tmpCoords.x, InputListener.tmpCoords.y, inputEvent0.getPointer(), inputEvent0.getRelatedActor());
                        return false;
                    }
                    default: {
                        return false;
                    }
                }
            }
        }
    }

    public boolean keyDown(InputEvent inputEvent0, int v) {
        return false;
    }

    public boolean keyTyped(InputEvent inputEvent0, char c) {
        return false;
    }

    public boolean keyUp(InputEvent inputEvent0, int v) {
        return false;
    }

    public boolean mouseMoved(InputEvent inputEvent0, float f, float f1) {
        return false;
    }

    public boolean scrolled(InputEvent inputEvent0, float f, float f1, float f2, float f3) {
        return false;
    }

    public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
        return false;
    }

    public void touchDragged(InputEvent inputEvent0, float f, float f1, int v) {
    }

    public void touchUp(InputEvent inputEvent0, float f, float f1, int v, int v1) {
    }

    class com.badlogic.gdx.scenes.scene2d.InputListener.1 {
        static final int[] $SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type;

        static {
            com.badlogic.gdx.scenes.scene2d.InputListener.1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type = new int[Type.values().length];
            try {
                com.badlogic.gdx.scenes.scene2d.InputListener.1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[Type.keyDown.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.badlogic.gdx.scenes.scene2d.InputListener.1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[Type.keyUp.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.badlogic.gdx.scenes.scene2d.InputListener.1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[Type.keyTyped.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.badlogic.gdx.scenes.scene2d.InputListener.1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[Type.touchDown.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.badlogic.gdx.scenes.scene2d.InputListener.1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[Type.touchUp.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.badlogic.gdx.scenes.scene2d.InputListener.1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[Type.touchDragged.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.badlogic.gdx.scenes.scene2d.InputListener.1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[Type.mouseMoved.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.badlogic.gdx.scenes.scene2d.InputListener.1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[Type.scrolled.ordinal()] = 8;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.badlogic.gdx.scenes.scene2d.InputListener.1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[Type.enter.ordinal()] = 9;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.badlogic.gdx.scenes.scene2d.InputListener.1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$InputEvent$Type[Type.exit.ordinal()] = 10;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

