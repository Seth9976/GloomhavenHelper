package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;

public class Button extends Table implements Disableable {
    public static class ButtonStyle {
        @Null
        public Drawable checked;
        @Null
        public Drawable checkedDown;
        @Null
        public Drawable checkedFocused;
        public float checkedOffsetX;
        public float checkedOffsetY;
        @Null
        public Drawable checkedOver;
        @Null
        public Drawable disabled;
        @Null
        public Drawable down;
        @Null
        public Drawable focused;
        @Null
        public Drawable over;
        public float pressedOffsetX;
        public float pressedOffsetY;
        public float unpressedOffsetX;
        public float unpressedOffsetY;
        @Null
        public Drawable up;

        public ButtonStyle() {
        }

        public ButtonStyle(ButtonStyle button$ButtonStyle0) {
            this.up = button$ButtonStyle0.up;
            this.down = button$ButtonStyle0.down;
            this.over = button$ButtonStyle0.over;
            this.focused = button$ButtonStyle0.focused;
            this.disabled = button$ButtonStyle0.disabled;
            this.checked = button$ButtonStyle0.checked;
            this.checkedOver = button$ButtonStyle0.checkedOver;
            this.checkedDown = button$ButtonStyle0.checkedDown;
            this.checkedFocused = button$ButtonStyle0.checkedFocused;
            this.pressedOffsetX = button$ButtonStyle0.pressedOffsetX;
            this.pressedOffsetY = button$ButtonStyle0.pressedOffsetY;
            this.unpressedOffsetX = button$ButtonStyle0.unpressedOffsetX;
            this.unpressedOffsetY = button$ButtonStyle0.unpressedOffsetY;
            this.checkedOffsetX = button$ButtonStyle0.checkedOffsetX;
            this.checkedOffsetY = button$ButtonStyle0.checkedOffsetY;
        }

        public ButtonStyle(@Null Drawable drawable0, @Null Drawable drawable1, @Null Drawable drawable2) {
            this.up = drawable0;
            this.down = drawable1;
            this.checked = drawable2;
        }
    }

    ButtonGroup buttonGroup;
    private ClickListener clickListener;
    boolean isChecked;
    boolean isDisabled;
    private boolean programmaticChangeEvents;
    private ButtonStyle style;

    public Button() {
        this.programmaticChangeEvents = true;
        this.initialize();
    }

    public Button(Actor actor0, ButtonStyle button$ButtonStyle0) {
        this.programmaticChangeEvents = true;
        this.initialize();
        this.add(actor0);
        this.setStyle(button$ButtonStyle0);
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
    }

    public Button(Actor actor0, Skin skin0) {
        this(actor0, ((ButtonStyle)skin0.get(ButtonStyle.class)));
    }

    public Button(Actor actor0, Skin skin0, String s) {
        this(actor0, ((ButtonStyle)skin0.get(s, ButtonStyle.class)));
        this.setSkin(skin0);
    }

    public Button(ButtonStyle button$ButtonStyle0) {
        this.programmaticChangeEvents = true;
        this.initialize();
        this.setStyle(button$ButtonStyle0);
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
    }

    public Button(Skin skin0) {
        super(skin0);
        this.programmaticChangeEvents = true;
        this.initialize();
        this.setStyle(((ButtonStyle)skin0.get(ButtonStyle.class)));
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
    }

    public Button(Skin skin0, String s) {
        super(skin0);
        this.programmaticChangeEvents = true;
        this.initialize();
        this.setStyle(((ButtonStyle)skin0.get(s, ButtonStyle.class)));
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
    }

    public Button(@Null Drawable drawable0) {
        this(new ButtonStyle(drawable0, null, null));
    }

    public Button(@Null Drawable drawable0, @Null Drawable drawable1) {
        this(new ButtonStyle(drawable0, drawable1, null));
    }

    public Button(@Null Drawable drawable0, @Null Drawable drawable1, @Null Drawable drawable2) {
        this(new ButtonStyle(drawable0, drawable1, drawable2));
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
    public void draw(Batch batch0, float f) {
        float f2;
        float f1;
        this.validate();
        this.setBackground(this.getBackgroundDrawable());
        if(this.isPressed() && !this.isDisabled()) {
            f1 = this.style.pressedOffsetX;
            f2 = this.style.pressedOffsetY;
        }
        else if(!this.isChecked() || this.isDisabled()) {
            f1 = this.style.unpressedOffsetX;
            f2 = this.style.unpressedOffsetY;
        }
        else {
            f1 = this.style.checkedOffsetX;
            f2 = this.style.checkedOffsetY;
        }
        boolean z = f1 != 0.0f || f2 != 0.0f;
        SnapshotArray snapshotArray0 = this.getChildren();
        if(z) {
            for(int v1 = 0; v1 < snapshotArray0.size; ++v1) {
                ((Actor)snapshotArray0.get(v1)).moveBy(f1, f2);
            }
        }
        super.draw(batch0, f);
        if(z) {
            for(int v = 0; v < snapshotArray0.size; ++v) {
                ((Actor)snapshotArray0.get(v)).moveBy(-f1, -f2);
            }
        }
        Stage stage0 = this.getStage();
        if(stage0 != null && stage0.getActionsRequestRendering() && this.isPressed() != this.clickListener.isPressed()) {
            Gdx.graphics.requestRendering();
        }
    }

    @Null
    protected Drawable getBackgroundDrawable() {
        if(this.isDisabled() && this.style.disabled != null) {
            return this.style.disabled;
        }
        if(this.isPressed()) {
            if(this.isChecked() && this.style.checkedDown != null) {
                return this.style.checkedDown;
            }
            if(this.style.down != null) {
                return this.style.down;
            }
        }
        if(this.isOver()) {
            if(this.isChecked()) {
                if(this.style.checkedOver != null) {
                    return this.style.checkedOver;
                }
            }
            else if(this.style.over != null) {
                return this.style.over;
            }
        }
        boolean z = this.hasKeyboardFocus();
        if(this.isChecked()) {
            if(z && this.style.checkedFocused != null) {
                return this.style.checkedFocused;
            }
            if(this.style.checked != null) {
                return this.style.checked;
            }
            if(this.isOver() && this.style.over != null) {
                return this.style.over;
            }
        }
        return !z || this.style.focused == null ? this.style.up : this.style.focused;
    }

    @Null
    public ButtonGroup getButtonGroup() {
        return this.buttonGroup;
    }

    public ClickListener getClickListener() {
        return this.clickListener;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
    public float getMinHeight() {
        return this.getPrefHeight();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
    public float getMinWidth() {
        return this.getPrefWidth();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
    public float getPrefHeight() {
        float f = super.getPrefHeight();
        if(this.style.up != null) {
            f = Math.max(f, this.style.up.getMinHeight());
        }
        if(this.style.down != null) {
            f = Math.max(f, this.style.down.getMinHeight());
        }
        return this.style.checked == null ? f : Math.max(f, this.style.checked.getMinHeight());
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
    public float getPrefWidth() {
        float f = super.getPrefWidth();
        if(this.style.up != null) {
            f = Math.max(f, this.style.up.getMinWidth());
        }
        if(this.style.down != null) {
            f = Math.max(f, this.style.down.getMinWidth());
        }
        return this.style.checked == null ? f : Math.max(f, this.style.checked.getMinWidth());
    }

    public ButtonStyle getStyle() {
        return this.style;
    }

    private void initialize() {
        this.setTouchable(Touchable.enabled);
        com.badlogic.gdx.scenes.scene2d.ui.Button.1 button$10 = new ClickListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent0, float f, float f1) {
                if(Button.this.isDisabled()) {
                    return;
                }
                Button.this.setChecked(!Button.this.isChecked, true);
            }
        };
        this.clickListener = button$10;
        this.addListener(button$10);
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public boolean isDisabled() {
        return this.isDisabled;
    }

    public boolean isOver() {
        return this.clickListener.isOver();
    }

    public boolean isPressed() {
        return this.clickListener.isVisualPressed();
    }

    public void setChecked(boolean z) {
        this.setChecked(z, this.programmaticChangeEvents);
    }

    void setChecked(boolean z, boolean z1) {
        if(this.isChecked == z) {
            return;
        }
        if(this.buttonGroup != null && !this.buttonGroup.canCheck(this, z)) {
            return;
        }
        this.isChecked = z;
        if(z1) {
            ChangeEvent changeListener$ChangeEvent0 = (ChangeEvent)Pools.obtain(ChangeEvent.class);
            if(this.fire(changeListener$ChangeEvent0)) {
                this.isChecked = !z;
            }
            Pools.free(changeListener$ChangeEvent0);
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public void setDisabled(boolean z) {
        this.isDisabled = z;
    }

    public void setProgrammaticChangeEvents(boolean z) {
        this.programmaticChangeEvents = z;
    }

    public void setStyle(ButtonStyle button$ButtonStyle0) {
        if(button$ButtonStyle0 == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = button$ButtonStyle0;
        this.setBackground(this.getBackgroundDrawable());
    }

    public void toggle() {
        this.setChecked(!this.isChecked);
    }
}

