package com.hm.gloomhavenhelper;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.hm.gloomhavenhelper.network.Network;
import com.hm.gloomhavenhelper.util.Menu;
import com.hm.gloomhavenhelper.util.TextMenu;
import com.hm.spine.AnimationState;
import com.hm.spine.AnimationStateData;
import com.hm.spine.Skeleton;
import com.hm.spine.utils.SkeletonActor;

public class Intro {
   private Container ghhLogo;
   Container esotericLogo;
   private Image stageCover;
   private float introTime;
   private float introDuration;
   private float logoFadeTime = 2.0F;
   public int introPhase = 0;
   float skeletonScale = Math.round(Math.min(App.stage.getWidth(), App.stage.getHeight()) / 2.0F / 260.0F);
   private boolean welcomeShown;

   public Intro() {
      SkeletonActor esotericSkeleton = new SkeletonActor(
         App.skeletonRenderer, new Skeleton(App.esotericSkeletonData), new AnimationState(new AnimationStateData(App.esotericSkeletonData))
      );
      esotericSkeleton.getSkeleton().getRootBone().setScale(this.skeletonScale);
      if (this.skeletonScale > 1.0F) {
         App.atlas.findRegion("esoteric/logo").getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
      }

      this.esotericLogo = new Container(esotericSkeleton);
      this.esotericLogo.setTouchable(Touchable.disabled);
      this.esotericLogo.setFillParent(true);
      this.ghhLogo = new Container(new Image(App.skin, "separate/logo")).padLeft(13.0F);
      this.ghhLogo.setTouchable(Touchable.disabled);
      this.ghhLogo.getColor().a = 0.0F;
      this.ghhLogo.setFillParent(true);
      this.stageCover = new Image(App.skin.newDrawable("white", Color.BLACK));
      this.stageCover.setFillParent(true);
   }

   public void update(float delta) {
      boolean hasRows = App.gloom.rows.getChildren().size != 0;
      if (!hasRows || this.introPhase != -1) {
         if (Menu.menusShown > 0) {
            this.ghhLogo.remove();
            if (this.esotericLogo != null) {
               this.esotericLogo.remove();
            }
         } else {
            App.stage.addActor(this.ghhLogo);
            this.ghhLogo.toFront();
            if (this.esotericLogo != null) {
               App.stage.addActor(this.esotericLogo);
               this.esotericLogo.toFront();
            }
         }

         if (!hasRows) {
            this.logoFadeTime = 1.4F;
         }

         if (this.introPhase == -1) {
            if (this.esotericLogo != null) {
               this.esotericLogo.getColor().a = 1.0F;
            }

            this.ghhLogo.getColor().a = 1.0F;
         }
      }

      if (this.introPhase != -1) {
         float timeScale;
         if (hasRows) {
            timeScale = this.introPhase <= 3 ? 1.3F : 1.0F;
         } else {
            timeScale = this.introPhase <= 1 ? 0.75F : 1.0F;
         }

         this.introTime -= delta * timeScale;
         float percent = this.introDuration == 0.0F ? 1.0F : 1.0F - Math.max(0.0F, this.introTime / this.introDuration);
         float nextDuration = 0.0F;
         switch (this.introPhase) {
            case 0:
               ((SkeletonActor)this.esotericLogo.getActor()).getAnimationState().setAnimation(0, "logo", false).setTimeScale(timeScale);
               nextDuration = 2.7333333F;
               break;
            case 1:
               nextDuration = 0.0F;
               break;
            case 2:
               ((SkeletonActor)this.esotericLogo.getActor()).getAnimationState().getCurrent(0).setTimeScale(timeScale);
               this.esotericLogo.addAction(Actions.sequence(Actions.delay(0.93333334F / timeScale), new Action() {
                  @Override
                  public boolean act(float delta) {
                     App.atlas.findRegion("esoteric/logo").getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                     return true;
                  }
               }, Actions.delay(-1.2333333F / timeScale), new Action() {
                  @Override
                  public boolean act(float delta) {
                     if (App.gloom.nearest()) {
                        App.atlas.findRegion("esoteric/logo").getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
                     }

                     return true;
                  }
               }));
               Actor image = this.esotericLogo.getActor();
               float startX = image.getX();
               float startY = image.getY();
               this.esotericLogo.bottom().right().pad(0.0F, 0.0F, 195.0F, 130.0F);
               this.esotericLogo.invalidate();
               this.esotericLogo.validate();
               float endX = image.getX();
               float endY = image.getY();
               image.setPosition(startX, startY);
               image.addAction(Actions.sequence(Actions.moveTo(endX, endY, 0.75F / timeScale, Interpolation.fastSlow), new Action() {
                  @Override
                  public boolean act(float delta) {
                     Intro.this.esotericLogo.invalidate();
                     return true;
                  }
               }));
               this.esotericLogo
                  .addAction(
                     new TemporalAction(0.75F / timeScale, Interpolation.fastSlow) {
                        @Override
                        protected void update(float percent) {
                           ((SkeletonActor)Intro.this.esotericLogo.getActor())
                              .getSkeleton()
                              .getRootBone()
                              .setScale(Intro.this.skeletonScale - (Intro.this.skeletonScale - 1.0F) * percent);
                        }
                     }
                  );
               App.stage.getRoot().addActorAt(0, App.root);
               App.stage.getRoot().addActorAt(1, this.stageCover);
               this.stageCover.validate();
               this.stageCover
                  .addAction(
                     Actions.sequence(
                        Actions.alpha(0.2F, 3.0F / timeScale),
                        Actions.delay(2.0F),
                        Actions.alpha(0.0F, 5.0F / timeScale, Interpolation.slowFast),
                        Actions.removeActor()
                     )
                  );
               nextDuration = 1.2F;
               break;
            case 3:
               nextDuration = 1.4F;
               break;
            case 4:
               this.stageCover.setTouchable(Touchable.disabled);
               this.ghhLogo.getColor().a = Interpolation.fastSlow.apply(percent);
               nextDuration = 2.0F;
               break;
            case 5:
               if (!this.welcomeShown) {
                  this.welcomeShown = true;
                  boolean android = Gdx.app.getType() == Application.ApplicationType.Android;
                  if (android && !App.config.welcome && !App.game.isPurchased()) {
                     App.config.welcome = true;
                     App.game.saveConfig();
                     final TextMenu menu = new TextMenu() {
                        @Override
                        protected void updatePosition() {
                           float width = this.getPrefWidth();
                           float height = this.getPrefHeight();
                           if (width > App.stage.getWidth() - 18.0F) {
                              width = App.stage.getWidth() - 18.0F;
                           }

                           if (height > App.stage.getHeight() - 18.0F) {
                              height = App.stage.getHeight() - 18.0F;
                           }

                           this.setPosition(Math.round((App.stage.getWidth() - width) / 2.0F), Math.round((App.stage.getHeight() - height) / 2.0F));
                        }
                     };
                     menu.showArrow = false;
                     menu.autoHide = false;
                     TextButton okButton = App.textButton("OK").create();
                     okButton.pad(10.0F, 40.0F, 10.0F, 18.0F);
                     okButton.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                           menu.hide();
                        }
                     });
                     menu.table
                        .add(
                           new Container(new Label("Welcome to Gloomhaven Helper", App.skin, "plainLargeOutline", Color.WHITE)).pad(16.0F, 16.0F, 0.0F, 16.0F)
                        )
                        .row();
                     menu.table.defaults().fill(false).left();
                     menu.table
                        .add(
                           new Container(new Label("The ads can be removed from the main menu.", App.skin, "plainMediumOutline", App.lightGray))
                              .pad(16.0F, 16.0F, 0.0F, 16.0F)
                        )
                        .row();
                     menu.table
                        .add(
                           new Container(
                                 new Label(
                                    "Sorry for the ads, but thank you for supporting\nGloomhaven Helper's development!",
                                    App.skin,
                                    "plainMediumOutline",
                                    App.lightGray
                                 )
                              )
                              .pad(16.0F, 16.0F, 0.0F, 16.0F)
                        )
                        .row();
                     menu.table.add(okButton).right().row();
                     menu.pack();
                     menu.show(null, 0.0F, 0.0F, 0.0F, 0.0F, false);
                  } else if ((android || Gdx.app.getType() == Application.ApplicationType.WebGL) && App.config.toasts < 3) {
                     App.config.toasts++;
                     App.game.saveConfig();
                  }
               }

               nextDuration = -1.0F;
         }

         if (percent == 1.0F) {
            if (nextDuration == -1.0F) {
               this.introPhase = -1;
               Network.update();
            } else {
               this.introPhase++;
               this.introDuration = this.introTime = nextDuration;
            }
         }

         Gdx.graphics.requestRendering();
      } else if (hasRows && this.logoFadeTime > 0.0F) {
         this.logoFadeTime -= delta;
         if (this.logoFadeTime <= 0.0F) {
            this.ghhLogo.remove();
            if (this.esotericLogo != null) {
               this.esotericLogo.remove();
               this.esotericLogo = null;
               App.atlas.findRegion("esoteric/logo").getTexture().dispose();
               App.esotericSkeletonData = null;
            }
         } else {
            float a = Interpolation.slowFast.apply(Math.min(1.0F, this.logoFadeTime / 1.4F));
            this.ghhLogo.getColor().a = a;
            if (this.esotericLogo != null) {
               this.esotericLogo.getColor().a = a;
            }
         }

         Gdx.graphics.requestRendering();
      }
   }
}
