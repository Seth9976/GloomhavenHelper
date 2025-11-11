package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;

public class TextTooltip extends Tooltip {
    public static class TextTooltipStyle {
        @Null
        public Drawable background;
        public LabelStyle label;
        public float wrapWidth;

        public TextTooltipStyle() {
        }

        public TextTooltipStyle(LabelStyle label$LabelStyle0, @Null Drawable drawable0) {
            this.label = label$LabelStyle0;
            this.background = drawable0;
        }

        public TextTooltipStyle(TextTooltipStyle textTooltip$TextTooltipStyle0) {
            this.label = new LabelStyle(textTooltip$TextTooltipStyle0.label);
            this.background = textTooltip$TextTooltipStyle0.background;
            this.wrapWidth = textTooltip$TextTooltipStyle0.wrapWidth;
        }
    }

    public TextTooltip(@Null String s, Skin skin0) {
        this(s, TooltipManager.getInstance(), ((TextTooltipStyle)skin0.get(TextTooltipStyle.class)));
    }

    public TextTooltip(@Null String s, Skin skin0, String s1) {
        this(s, TooltipManager.getInstance(), ((TextTooltipStyle)skin0.get(s1, TextTooltipStyle.class)));
    }

    public TextTooltip(@Null String s, TextTooltipStyle textTooltip$TextTooltipStyle0) {
        this(s, TooltipManager.getInstance(), textTooltip$TextTooltipStyle0);
    }

    public TextTooltip(@Null String s, TooltipManager tooltipManager0, Skin skin0) {
        this(s, tooltipManager0, ((TextTooltipStyle)skin0.get(TextTooltipStyle.class)));
    }

    public TextTooltip(@Null String s, TooltipManager tooltipManager0, Skin skin0, String s1) {
        this(s, tooltipManager0, ((TextTooltipStyle)skin0.get(s1, TextTooltipStyle.class)));
    }

    public TextTooltip(@Null String s, TooltipManager tooltipManager0, TextTooltipStyle textTooltip$TextTooltipStyle0) {
        super(null, tooltipManager0);
        Label label0 = new Label(s, textTooltip$TextTooltipStyle0.label);
        label0.setWrap(true);
        this.container.setActor(label0);
        this.container.width(new Value() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Value
            public float get(@Null Actor actor0) {
                return Math.min(tooltipManager0.maxWidth, label0.getGlyphLayout().width);
            }
        });
        this.setStyle(textTooltip$TextTooltipStyle0);
    }

    public void setStyle(TextTooltipStyle textTooltip$TextTooltipStyle0) {
        if(textTooltip$TextTooltipStyle0 == null) {
            throw new NullPointerException("style cannot be null");
        }
        ((Label)this.container.getActor()).setStyle(textTooltip$TextTooltipStyle0.label);
        this.container.setBackground(textTooltip$TextTooltipStyle0.background);
        this.container.maxWidth(textTooltip$TextTooltipStyle0.wrapWidth);
    }
}

