package com.esotericsoftware.gloomhavenhelper.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.esotericsoftware.gloomhavenhelper.App;
import com.esotericsoftware.gloomhavenhelper.model.ElementState;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.utils.SkeletonDrawable;

public class ElementButton extends ImageButton {
    boolean allowOver;
    private final AtlasRegion elementRegion;
    public ElementState elementState;
    float glowAlpha;
    float glowHalfAlpha;
    private final AtlasRegion glowHalfRegion;
    private final AtlasRegion glowRegion;
    long lastClickTime;
    private final String name;
    private final AtlasRegion ringRegion;
    private final Skeleton skeleton;
    private final AnimationState state;

    public ElementButton(String s) {
        super(new ImageButtonStyle());
        this.elementState = ElementState.inert;
        this.allowOver = true;
        this.name = s;
        this.skeleton = new Skeleton(App.elementsSkeletonData);
        this.state = new AnimationState(new AnimationStateData(App.elementsSkeletonData));
        this.state.setAnimation(0, s + "-strong-inert", false).setTrackTime(999.0f);
        ImageButtonStyle imageButton$ImageButtonStyle0 = this.getStyle();
        imageButton$ImageButtonStyle0.imageUp = new SkeletonDrawable(App.skeletonRenderer, this.skeleton, this.state);
        imageButton$ImageButtonStyle0.imageUp.setMinWidth(100.0f);
        imageButton$ImageButtonStyle0.imageUp.setMinHeight(100.0f);
        this.setStyle(imageButton$ImageButtonStyle0);
        this.ringRegion = App.atlas.findRegion("element-ring");
        this.glowRegion = App.atlas.findRegion("element-glow");
        this.glowHalfRegion = App.atlas.findRegion("element-glow-half");
        this.elementRegion = App.atlas.findRegion("psd/element-" + s);
        this.addListener(new ClickListener(-1) {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent0, float f, float f1) {
                long v = System.currentTimeMillis();
                if(v - ElementButton.this.lastClickTime < 400L) {
                    ElementButton.this.setState(ElementState.waning, "strong-waning");
                }
                else if(ElementButton.this.elementState == ElementState.inert) {
                    ElementButton.this.infuse();
                }
                else {
                    ElementButton.this.consume();
                }
                ElementButton.this.lastClickTime = v;
                ElementButton.this.allowOver = false;
                ElementButton.this.clicked(ElementButton.this.elementState);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void enter(InputEvent inputEvent0, float f, float f1, int v, Actor actor0) {
                super.enter(inputEvent0, f, f1, v, actor0);
                ElementButton.this.allowOver = true;
            }
        });
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public void act(float f) {
        super.act(f);
        this.state.update(f);
        TrackEntry animationState$TrackEntry0 = this.state.getCurrent(0);
        if(animationState$TrackEntry0 != null && (animationState$TrackEntry0.getTrackTime() < animationState$TrackEntry0.getAnimation().getDuration() || animationState$TrackEntry0.getNext() != null)) {
            Gdx.graphics.requestRendering();
        }
        boolean z = this.state.getCurrent(0).isComplete() && this.state.getCurrent(0).getNext() == null;
        float f1 = this.elementState != ElementState.strong || !z ? 0.0f : 0.7f;
        int v = 0x3F000000;
        int v1 = this.glowAlpha < f1 ? 0x3F000000 : 0x3F800000;
        this.glowAlpha = App.animate(this.glowAlpha, f1, v1 * 1058642330, v1 * 0x3FC00000, v1 * 0x40400000, f);
        float f2 = this.elementState != ElementState.waning || !z ? 0.0f : 0.7f;
        if(this.glowHalfAlpha >= f2) {
            v = 0x3F800000;
        }
        this.glowHalfAlpha = App.animate(this.glowHalfAlpha, f2, v * 1058642330, v * 0x3FC00000, v * 0x40400000, f);
    }

    protected void clicked(ElementState elementState0) {
    }

    public void consume() {
        this.setState(ElementState.inert, "strong-inert");
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.ImageButton
    public void draw(Batch batch0, float f) {
        this.state.apply(this.skeleton);
        float f1 = this.glowAlpha;
        if(f1 > 0.0f) {
            batch0.setColor(1.0f, 1.0f, 1.0f, f1);
            float f2 = this.getX();
            float f3 = this.getY();
            batch0.draw(this.glowRegion, f2 - 6.0f, f3 - 6.0f);
        }
        float f4 = this.glowHalfAlpha;
        if(f4 > 0.0f) {
            batch0.setColor(1.0f, 1.0f, 1.0f, f4);
            float f5 = this.getX();
            float f6 = this.getY();
            batch0.draw(this.glowHalfRegion, f5 - 6.0f, f6 - 6.0f);
        }
        super.draw(batch0, f);
        batch0.setBlendFunction(770, 0x303);
        if(this.allowOver && this.isOver()) {
            batch0.setColor(Color.WHITE);
            if(this.elementState == ElementState.inert) {
                float f7 = this.getX() + this.elementRegion.offsetX;
                float f8 = this.getY();
                batch0.draw(this.elementRegion, f7, f8 + this.elementRegion.offsetY);
            }
            float f9 = this.getX();
            float f10 = this.getY();
            batch0.draw(this.ringRegion, f9 - 2.0f, f10 - 2.0f);
        }
    }

    public void endOfRound() {
        if(this.elementState == ElementState.strong) {
            this.setState(ElementState.waning, "strong-waning");
            return;
        }
        if(this.elementState == ElementState.waning) {
            this.setState(ElementState.inert, "waning-inert");
        }
    }

    public void infuse() {
        this.setState(ElementState.strong, "inert-strong");
    }

    public TrackEntry setState(ElementState elementState0, String s) {
        if(elementState0 == null) {
            throw new IllegalArgumentException("elementState cannot be null.");
        }
        TrackEntry animationState$TrackEntry0 = this.state.getCurrent(0);
        TrackEntry animationState$TrackEntry1 = animationState$TrackEntry0.getNext() != null || !animationState$TrackEntry0.isComplete() ? this.state.setAnimation(0, this.name + "-" + s, false) : this.state.addAnimation(0, this.name + "-" + s, false, animationState$TrackEntry0.getTrackTime() + 0.4f);
        this.toFront();
        this.elementState = elementState0;
        return animationState$TrackEntry1;
    }

    public void setState(ElementState elementState0, boolean z) {
        String s1;
        String s;
        if(elementState0 == null) {
            throw new IllegalArgumentException("elementState cannot be null.");
        }
        if(this.elementState == elementState0) {
            return;
        }
        if(z) {
            if(elementState0 == ElementState.inert) {
                if(this.elementState == ElementState.inert) {
                    this.setState(ElementState.inert, false);
                    return;
                }
                s = this.elementState == ElementState.strong ? "strong-inert" : "waning-inert";
            }
            else {
                s = elementState0 == ElementState.waning ? "strong-waning" : "inert-strong";
            }
            this.setState(elementState0, s);
            return;
        }
        if(elementState0 == ElementState.inert) {
            s1 = "waning-inert";
        }
        else {
            s1 = elementState0 == ElementState.waning ? "strong-waning" : "inert-strong";
        }
        this.state.setAnimation(0, this.name + "-" + s1, false).setTrackTime(999.0f);
        this.elementState = elementState0;
    }
}

