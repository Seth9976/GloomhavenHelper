package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.esotericsoftware.gloomhavenhelper.network.Network;
import com.esotericsoftware.gloomhavenhelper.util.Menu;
import com.esotericsoftware.gloomhavenhelper.util.TextMenu;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.utils.SkeletonActor;

public class Intro {
    Container esotericLogo;
    private Container ghhLogo;
    private float introDuration;
    public int introPhase;
    private float introTime;
    private float logoFadeTime;
    float skeletonScale;
    private Image stageCover;
    private boolean welcomeShown;

    public Intro() {
        this.logoFadeTime = 2.0f;
        this.introPhase = 0;
        this.skeletonScale = (float)Math.round(Math.min(App.stage.getWidth(), App.stage.getHeight()) / 2.0f / 260.0f);
        SkeletonActor skeletonActor0 = new SkeletonActor(App.skeletonRenderer, new Skeleton(App.esotericSkeletonData), new AnimationState(new AnimationStateData(App.esotericSkeletonData)));
        skeletonActor0.getSkeleton().getRootBone().setScale(this.skeletonScale);
        if(this.skeletonScale > 1.0f) {
            App.atlas.findRegion("esoteric/logo").getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        }
        this.esotericLogo = new Container(skeletonActor0);
        this.esotericLogo.setTouchable(Touchable.disabled);
        this.esotericLogo.setFillParent(true);
        this.ghhLogo = new Container(new Image(App.skin, "separate/logo")).padLeft(13.0f);
        this.ghhLogo.setTouchable(Touchable.disabled);
        this.ghhLogo.getColor().a = 0.0f;
        this.ghhLogo.setFillParent(true);
        this.stageCover = new Image(App.skin.newDrawable("white", Color.BLACK));
        this.stageCover.setFillParent(true);
    }

    public void update(float f) {
        float f2;
        boolean z = App.gloom.rows.getChildren().size != 0;
        float f1 = 1.4f;
        if(!z || this.introPhase != -1) {
            if(Menu.menusShown > 0) {
                this.ghhLogo.remove();
                Container container0 = this.esotericLogo;
                if(container0 != null) {
                    container0.remove();
                }
            }
            else {
                App.stage.addActor(this.ghhLogo);
                this.ghhLogo.toFront();
                if(this.esotericLogo != null) {
                    App.stage.addActor(this.esotericLogo);
                    this.esotericLogo.toFront();
                }
            }
            if(!z) {
                this.logoFadeTime = 1.4f;
            }
            if(this.introPhase == -1) {
                Container container1 = this.esotericLogo;
                if(container1 != null) {
                    container1.getColor().a = 1.0f;
                }
                this.ghhLogo.getColor().a = 1.0f;
            }
        }
        int v = this.introPhase;
        if(v != -1) {
            if(!z) {
                f2 = v > 1 ? 1.0f : 0.75f;
            }
            else if(v <= 3) {
                f2 = 1.3f;
            }
            else {
                f2 = 1.0f;
            }
            this.introTime -= f * f2;
            float f3 = this.introDuration == 0.0f ? 1.0f : 1.0f - Math.max(0.0f, this.introTime / this.introDuration);
            switch(this.introPhase) {
                case 0: {
                    ((SkeletonActor)this.esotericLogo.getActor()).getAnimationState().setAnimation(0, "logo", false).setTimeScale(f2);
                    f1 = 2.733333f;
                    break;
                }
                case 1: {
                    f1 = 0.0f;
                    break;
                }
                case 2: {
                    ((SkeletonActor)this.esotericLogo.getActor()).getAnimationState().getCurrent(0).setTimeScale(f2);
                    this.esotericLogo.addAction(Actions.sequence(Actions.delay(0.933333f / f2), new Action() {
                        @Override  // com.badlogic.gdx.scenes.scene2d.Action
                        public boolean act(float f) {
                            App.atlas.findRegion("esoteric/logo").getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
                            return true;
                        }
                    }, Actions.delay(-1.233333f / f2), new Action() {
                        @Override  // com.badlogic.gdx.scenes.scene2d.Action
                        public boolean act(float f) {
                            if(App.gloom.nearest()) {
                                App.atlas.findRegion("esoteric/logo").getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
                            }
                            return true;
                        }
                    }));
                    Actor actor0 = this.esotericLogo.getActor();
                    this.esotericLogo.bottom().right().pad(0.0f, 0.0f, 195.0f, 130.0f);
                    this.esotericLogo.invalidate();
                    this.esotericLogo.validate();
                    actor0.setPosition(actor0.getX(), actor0.getY());
                    actor0.addAction(Actions.sequence(Actions.moveTo(actor0.getX(), actor0.getY(), 0.75f / f2, Interpolation.fastSlow), new Action() {
                        @Override  // com.badlogic.gdx.scenes.scene2d.Action
                        public boolean act(float f) {
                            Intro.this.esotericLogo.invalidate();
                            return true;
                        }
                    }));
                    this.esotericLogo.addAction(new TemporalAction(0.75f / f2, Interpolation.fastSlow) {
                        @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
                        protected void update(float f) {
                            ((SkeletonActor)Intro.this.esotericLogo.getActor()).getSkeleton().getRootBone().setScale(Intro.this.skeletonScale - (Intro.this.skeletonScale - 1.0f) * f);
                        }
                    });
                    App.stage.getRoot().addActorAt(0, App.root);
                    App.stage.getRoot().addActorAt(1, this.stageCover);
                    this.stageCover.validate();
                    this.stageCover.addAction(Actions.sequence(Actions.alpha(0.2f, 3.0f / f2), Actions.delay(2.0f), Actions.alpha(0.0f, 5.0f / f2, Interpolation.slowFast), Actions.removeActor()));
                    f1 = 1.2f;
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
                    this.stageCover.setTouchable(Touchable.disabled);
                    Color color0 = this.ghhLogo.getColor();
                    color0.a = Interpolation.fastSlow.apply(f3);
                    f1 = 2.0f;
                    break;
                }
                case 5: {
                    if(!this.welcomeShown) {
                        this.welcomeShown = true;
                        boolean z1 = Gdx.app.getType() == ApplicationType.Android;
                        if(z1 && !App.config.welcome && !App.game.isPurchased()) {
                            App.config.welcome = true;
                            App.game.saveConfig();
                            com.esotericsoftware.gloomhavenhelper.Intro.5 intro$50 = new TextMenu() {
                                @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
                                protected void updatePosition() {
                                    float f = this.getPrefWidth();
                                    float f1 = this.getPrefHeight();
                                    if(f > App.stage.getWidth() - 18.0f) {
                                        f = App.stage.getWidth() - 18.0f;
                                    }
                                    if(f1 > App.stage.getHeight() - 18.0f) {
                                        f1 = App.stage.getHeight() - 18.0f;
                                    }
                                    this.setPosition(((float)Math.round((App.stage.getWidth() - f) / 2.0f)), ((float)Math.round((App.stage.getHeight() - f1) / 2.0f)));
                                }
                            };
                            intro$50.showArrow = false;
                            intro$50.autoHide = false;
                            TextButton textButton0 = App.textButton("OK").create();
                            textButton0.pad(10.0f, 40.0f, 10.0f, 18.0f);
                            textButton0.addListener(new ChangeListener() {
                                @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                                public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                                    intro$50.hide();
                                }
                            });
                            Container container2 = new Container(new Label("Welcome to Gloomhaven Helper", App.skin, "plainLargeOutline", Color.WHITE)).pad(16.0f, 16.0f, 0.0f, 16.0f);
                            intro$50.table.add(container2).row();
                            intro$50.table.defaults().fill(false).left();
                            Container container3 = new Container(new Label("The ads can be removed from the main menu.", App.skin, "plainMediumOutline", App.lightGray)).pad(16.0f, 16.0f, 0.0f, 16.0f);
                            intro$50.table.add(container3).row();
                            Container container4 = new Container(new Label("Sorry for the ads, but thank you for supporting\nGloomhaven Helper\'s development!", App.skin, "plainMediumOutline", App.lightGray)).pad(16.0f, 16.0f, 0.0f, 16.0f);
                            intro$50.table.add(container4).row();
                            intro$50.table.add(textButton0).right().row();
                            intro$50.pack();
                            intro$50.show(null, 0.0f, 0.0f, 0.0f, 0.0f, false);
                        }
                        else if((z1 || Gdx.app.getType() == ApplicationType.WebGL) && App.config.toasts < 3) {
                            ++App.config.toasts;
                            App.game.saveConfig();
                        }
                    }
                    f1 = -1.0f;
                    break;
                }
                default: {
                    f1 = 0.0f;
                }
            }
            if(f3 == 1.0f) {
                if(f1 == -1.0f) {
                    this.introPhase = -1;
                    Network.update();
                }
                else {
                    ++this.introPhase;
                    this.introTime = f1;
                    this.introDuration = f1;
                }
            }
            Gdx.graphics.requestRendering();
            return;
        }
        if(z) {
            float f4 = this.logoFadeTime;
            if(f4 > 0.0f) {
                this.logoFadeTime = f4 - f;
                if(this.logoFadeTime <= 0.0f) {
                    this.ghhLogo.remove();
                    Container container5 = this.esotericLogo;
                    if(container5 != null) {
                        container5.remove();
                        this.esotericLogo = null;
                        App.atlas.findRegion("esoteric/logo").getTexture().dispose();
                        App.esotericSkeletonData = null;
                    }
                }
                else {
                    float f5 = Interpolation.slowFast.apply(Math.min(1.0f, this.logoFadeTime / 1.4f));
                    this.ghhLogo.getColor().a = f5;
                    Container container6 = this.esotericLogo;
                    if(container6 != null) {
                        container6.getColor().a = f5;
                    }
                }
                Gdx.graphics.requestRendering();
            }
        }
    }
}

