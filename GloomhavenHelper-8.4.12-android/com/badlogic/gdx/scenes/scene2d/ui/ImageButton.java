package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Scaling;

public class ImageButton extends Button {
    public static class ImageButtonStyle extends ButtonStyle {
        @Null
        public Drawable imageChecked;
        @Null
        public Drawable imageCheckedDown;
        @Null
        public Drawable imageCheckedOver;
        @Null
        public Drawable imageDisabled;
        @Null
        public Drawable imageDown;
        @Null
        public Drawable imageOver;
        @Null
        public Drawable imageUp;

        public ImageButtonStyle() {
        }

        public ImageButtonStyle(ButtonStyle button$ButtonStyle0) {
            super(button$ButtonStyle0);
        }

        public ImageButtonStyle(ImageButtonStyle imageButton$ImageButtonStyle0) {
            super(imageButton$ImageButtonStyle0);
            this.imageUp = imageButton$ImageButtonStyle0.imageUp;
            this.imageDown = imageButton$ImageButtonStyle0.imageDown;
            this.imageOver = imageButton$ImageButtonStyle0.imageOver;
            this.imageDisabled = imageButton$ImageButtonStyle0.imageDisabled;
            this.imageChecked = imageButton$ImageButtonStyle0.imageChecked;
            this.imageCheckedDown = imageButton$ImageButtonStyle0.imageCheckedDown;
            this.imageCheckedOver = imageButton$ImageButtonStyle0.imageCheckedOver;
        }

        public ImageButtonStyle(@Null Drawable drawable0, @Null Drawable drawable1, @Null Drawable drawable2, @Null Drawable drawable3, @Null Drawable drawable4, @Null Drawable drawable5) {
            super(drawable0, drawable1, drawable2);
            this.imageUp = drawable3;
            this.imageDown = drawable4;
            this.imageChecked = drawable5;
        }
    }

    private final Image image;
    private ImageButtonStyle style;

    public ImageButton(ImageButtonStyle imageButton$ImageButtonStyle0) {
        super(imageButton$ImageButtonStyle0);
        this.image = new Image();
        this.image.setScaling(Scaling.fit);
        this.add(this.image);
        this.setStyle(imageButton$ImageButtonStyle0);
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
    }

    public ImageButton(Skin skin0) {
        this(((ImageButtonStyle)skin0.get(ImageButtonStyle.class)));
        this.setSkin(skin0);
    }

    public ImageButton(Skin skin0, String s) {
        this(((ImageButtonStyle)skin0.get(s, ImageButtonStyle.class)));
        this.setSkin(skin0);
    }

    public ImageButton(@Null Drawable drawable0) {
        this(new ImageButtonStyle(null, null, null, drawable0, null, null));
    }

    public ImageButton(@Null Drawable drawable0, @Null Drawable drawable1) {
        this(new ImageButtonStyle(null, null, null, drawable0, drawable1, null));
    }

    public ImageButton(@Null Drawable drawable0, @Null Drawable drawable1, @Null Drawable drawable2) {
        this(new ImageButtonStyle(null, null, null, drawable0, drawable1, drawable2));
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Button
    public void draw(Batch batch0, float f) {
        this.updateImage();
        super.draw(batch0, f);
    }

    public Image getImage() {
        return this.image;
    }

    public Cell getImageCell() {
        return this.getCell(this.image);
    }

    @Null
    protected Drawable getImageDrawable() {
        if(this.isDisabled() && this.style.imageDisabled != null) {
            return this.style.imageDisabled;
        }
        if(this.isPressed()) {
            if(this.isChecked() && this.style.imageCheckedDown != null) {
                return this.style.imageCheckedDown;
            }
            if(this.style.imageDown != null) {
                return this.style.imageDown;
            }
        }
        if(this.isOver()) {
            if(this.isChecked()) {
                if(this.style.imageCheckedOver != null) {
                    return this.style.imageCheckedOver;
                }
            }
            else if(this.style.imageOver != null) {
                return this.style.imageOver;
            }
        }
        if(this.isChecked()) {
            if(this.style.imageChecked != null) {
                return this.style.imageChecked;
            }
            return !this.isOver() || this.style.imageOver == null ? this.style.imageUp : this.style.imageOver;
        }
        return this.style.imageUp;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Button
    public ButtonStyle getStyle() {
        return this.getStyle();
    }

    public ImageButtonStyle getStyle() {
        return this.style;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Button
    public void setStyle(ButtonStyle button$ButtonStyle0) {
        if(!(button$ButtonStyle0 instanceof ImageButtonStyle)) {
            throw new IllegalArgumentException("style must be an ImageButtonStyle.");
        }
        this.style = (ImageButtonStyle)button$ButtonStyle0;
        super.setStyle(button$ButtonStyle0);
        if(this.image != null) {
            this.updateImage();
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public String toString() {
        String s = this.getName();
        if(s != null) {
            return s;
        }
        String s1 = this.getClass().getName();
        int v = s1.lastIndexOf(46);
        if(v != -1) {
            s1 = s1.substring(v + 1);
        }
        return (s1.indexOf(36) == -1 ? "" : "ImageButton ") + s1 + ": " + this.image.getDrawable();
    }

    protected void updateImage() {
        Drawable drawable0 = this.getImageDrawable();
        this.image.setDrawable(drawable0);
    }
}

