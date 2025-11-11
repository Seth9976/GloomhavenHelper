package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.utils.Null;

public abstract class FocusListener implements EventListener {
    public static class FocusEvent extends Event {
        public static enum Type {
            keyboard,
            scroll;

        }

        private boolean focused;
        private Actor relatedActor;
        private Type type;

        @Null
        public Actor getRelatedActor() {
            return this.relatedActor;
        }

        public Type getType() {
            return this.type;
        }

        public boolean isFocused() {
            return this.focused;
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.Event
        public void reset() {
            super.reset();
            this.relatedActor = null;
        }

        public void setFocused(boolean z) {
            this.focused = z;
        }

        public void setRelatedActor(@Null Actor actor0) {
            this.relatedActor = actor0;
        }

        public void setType(Type focusListener$FocusEvent$Type0) {
            this.type = focusListener$FocusEvent$Type0;
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.EventListener
    public boolean handle(Event event0) {
        if(!(event0 instanceof FocusEvent)) {
            return false;
        }
        switch(com.badlogic.gdx.scenes.scene2d.utils.FocusListener.1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$utils$FocusListener$FocusEvent$Type[((FocusEvent)event0).getType().ordinal()]) {
            case 1: {
                this.keyboardFocusChanged(((FocusEvent)event0), event0.getTarget(), ((FocusEvent)event0).isFocused());
                return false;
            }
            case 2: {
                this.scrollFocusChanged(((FocusEvent)event0), event0.getTarget(), ((FocusEvent)event0).isFocused());
                return false;
            }
            default: {
                return false;
            }
        }
    }

    public void keyboardFocusChanged(FocusEvent focusListener$FocusEvent0, Actor actor0, boolean z) {
    }

    public void scrollFocusChanged(FocusEvent focusListener$FocusEvent0, Actor actor0, boolean z) {
    }

    class com.badlogic.gdx.scenes.scene2d.utils.FocusListener.1 {
        static final int[] $SwitchMap$com$badlogic$gdx$scenes$scene2d$utils$FocusListener$FocusEvent$Type;

        static {
            com.badlogic.gdx.scenes.scene2d.utils.FocusListener.1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$utils$FocusListener$FocusEvent$Type = new int[Type.values().length];
            try {
                com.badlogic.gdx.scenes.scene2d.utils.FocusListener.1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$utils$FocusListener$FocusEvent$Type[Type.keyboard.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.badlogic.gdx.scenes.scene2d.utils.FocusListener.1.$SwitchMap$com$badlogic$gdx$scenes$scene2d$utils$FocusListener$FocusEvent$Type[Type.scroll.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

