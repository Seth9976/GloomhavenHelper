package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener.FocusEvent;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;

public class Dialog extends Window {
    Table buttonTable;
    boolean cancelHide;
    Table contentTable;
    FocusListener focusListener;
    protected InputListener ignoreTouchDown;
    Actor previousKeyboardFocus;
    Actor previousScrollFocus;
    @Null
    private Skin skin;
    ObjectMap values;

    public Dialog(String s, Skin skin0) {
        super(s, ((WindowStyle)skin0.get(WindowStyle.class)));
        this.values = new ObjectMap();
        this.ignoreTouchDown = new InputListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                inputEvent0.cancel();
                return false;
            }
        };
        this.setSkin(skin0);
        this.skin = skin0;
        this.initialize();
    }

    public Dialog(String s, Skin skin0, String s1) {
        super(s, ((WindowStyle)skin0.get(s1, WindowStyle.class)));
        this.values = new ObjectMap();
        this.ignoreTouchDown = new InputListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                inputEvent0.cancel();
                return false;
            }
        };
        this.setSkin(skin0);
        this.skin = skin0;
        this.initialize();
    }

    public Dialog(String s, WindowStyle window$WindowStyle0) {
        super(s, window$WindowStyle0);
        this.values = new ObjectMap();
        this.ignoreTouchDown = new InputListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                inputEvent0.cancel();
                return false;
            }
        };
        this.initialize();
    }

    public Dialog button(Button button0) {
        return this.button(button0, null);
    }

    public Dialog button(Button button0, @Null Object object0) {
        this.buttonTable.add(button0);
        this.setObject(button0, object0);
        return this;
    }

    public Dialog button(@Null String s) {
        return this.button(s, null);
    }

    public Dialog button(@Null String s, @Null Object object0) {
        Skin skin0 = this.skin;
        if(skin0 == null) {
            throw new IllegalStateException("This method may only be used if the dialog was constructed with a Skin.");
        }
        return this.button(s, object0, ((TextButtonStyle)skin0.get(TextButtonStyle.class)));
    }

    public Dialog button(@Null String s, @Null Object object0, TextButtonStyle textButton$TextButtonStyle0) {
        return this.button(new TextButton(s, textButton$TextButtonStyle0), object0);
    }

    public void cancel() {
        this.cancelHide = true;
    }

    public Table getButtonTable() {
        return this.buttonTable;
    }

    public Table getContentTable() {
        return this.contentTable;
    }

    public void hide() {
        this.hide(Actions.fadeOut(0.4f, Interpolation.fade));
    }

    public void hide(@Null Action action0) {
        Stage stage0 = this.getStage();
        if(stage0 != null) {
            this.removeListener(this.focusListener);
            if(this.previousKeyboardFocus != null && this.previousKeyboardFocus.getStage() == null) {
                this.previousKeyboardFocus = null;
            }
            Actor actor0 = stage0.getKeyboardFocus();
            if(actor0 == null || actor0.isDescendantOf(this)) {
                stage0.setKeyboardFocus(this.previousKeyboardFocus);
            }
            if(this.previousScrollFocus != null && this.previousScrollFocus.getStage() == null) {
                this.previousScrollFocus = null;
            }
            Actor actor1 = stage0.getScrollFocus();
            if(actor1 == null || actor1.isDescendantOf(this)) {
                stage0.setScrollFocus(this.previousScrollFocus);
            }
        }
        if(action0 != null) {
            this.addCaptureListener(this.ignoreTouchDown);
            this.addAction(Actions.sequence(action0, Actions.removeListener(this.ignoreTouchDown, true), Actions.removeActor()));
            return;
        }
        this.remove();
    }

    private void initialize() {
        this.setModal(true);
        this.defaults().space(6.0f);
        Table table0 = new Table(this.skin);
        this.contentTable = table0;
        this.add(table0).expand().fill();
        this.row();
        Table table1 = new Table(this.skin);
        this.buttonTable = table1;
        this.add(table1).fillX();
        this.contentTable.defaults().space(6.0f);
        this.buttonTable.defaults().space(6.0f);
        this.buttonTable.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(!Dialog.this.values.containsKey(actor0)) {
                    return;
                }
                while(actor0.getParent() != Dialog.this.buttonTable) {
                    actor0 = actor0.getParent();
                }
                Dialog.this.values.get(actor0);
                if(!Dialog.this.cancelHide) {
                    Dialog.this.hide();
                }
                Dialog.this.cancelHide = false;
            }
        });
        this.focusListener = new FocusListener() {
            private void focusChanged(FocusEvent focusListener$FocusEvent0) {
                Stage stage0 = Dialog.this.getStage();
                if(Dialog.this.isModal && stage0 != null && stage0.getRoot().getChildren().size > 0 && stage0.getRoot().getChildren().peek() == Dialog.this) {
                    Actor actor0 = focusListener$FocusEvent0.getRelatedActor();
                    if(actor0 != null && !actor0.isDescendantOf(Dialog.this) && !actor0.equals(Dialog.this.previousKeyboardFocus) && !actor0.equals(Dialog.this.previousScrollFocus)) {
                        focusListener$FocusEvent0.cancel();
                    }
                }
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.FocusListener
            public void keyboardFocusChanged(FocusEvent focusListener$FocusEvent0, Actor actor0, boolean z) {
                if(!z) {
                    this.focusChanged(focusListener$FocusEvent0);
                }
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.FocusListener
            public void scrollFocusChanged(FocusEvent focusListener$FocusEvent0, Actor actor0, boolean z) {
                if(!z) {
                    this.focusChanged(focusListener$FocusEvent0);
                }
            }
        };
    }

    public Dialog key(int v, @Null Object object0) {
        this.addListener(new InputListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean keyDown(InputEvent inputEvent0, int v) {
                if(v == v) {
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            if(!Dialog.this.cancelHide) {
                                Dialog.this.hide();
                            }
                            Dialog.this.cancelHide = false;
                        }
                    });
                }
                return false;
            }
        });
        return this;
    }

    protected void result(@Null Object object0) {
    }

    public void setObject(Actor actor0, @Null Object object0) {
        this.values.put(actor0, object0);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    protected void setStage(Stage stage0) {
        if(stage0 == null) {
            this.addListener(this.focusListener);
        }
        else {
            this.removeListener(this.focusListener);
        }
        super.setStage(stage0);
    }

    public Dialog show(Stage stage0) {
        this.show(stage0, Actions.sequence(Actions.alpha(0.0f), Actions.fadeIn(0.4f, Interpolation.fade)));
        this.setPosition(((float)Math.round((stage0.getWidth() - this.getWidth()) / 2.0f)), ((float)Math.round((stage0.getHeight() - this.getHeight()) / 2.0f)));
        return this;
    }

    public Dialog show(Stage stage0, @Null Action action0) {
        this.clearActions();
        this.removeCaptureListener(this.ignoreTouchDown);
        this.previousKeyboardFocus = null;
        Actor actor0 = stage0.getKeyboardFocus();
        if(actor0 != null && !actor0.isDescendantOf(this)) {
            this.previousKeyboardFocus = actor0;
        }
        this.previousScrollFocus = null;
        Actor actor1 = stage0.getScrollFocus();
        if(actor1 != null && !actor1.isDescendantOf(this)) {
            this.previousScrollFocus = actor1;
        }
        stage0.addActor(this);
        this.pack();
        stage0.cancelTouchFocus();
        stage0.setKeyboardFocus(this);
        stage0.setScrollFocus(this);
        if(action0 != null) {
            this.addAction(action0);
        }
        return this;
    }

    public Dialog text(Label label0) {
        this.contentTable.add(label0);
        return this;
    }

    public Dialog text(@Null String s) {
        Skin skin0 = this.skin;
        if(skin0 == null) {
            throw new IllegalStateException("This method may only be used if the dialog was constructed with a Skin.");
        }
        return this.text(s, ((LabelStyle)skin0.get(LabelStyle.class)));
    }

    public Dialog text(@Null String s, LabelStyle label$LabelStyle0) {
        return this.text(new Label(s, label$LabelStyle0));
    }
}

