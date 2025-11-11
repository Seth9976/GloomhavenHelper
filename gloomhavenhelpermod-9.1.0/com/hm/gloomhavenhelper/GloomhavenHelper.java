package com.hm.gloomhavenhelper;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.Timer;
import com.hm.gloomhavenhelper.model.AttackModifier;
import com.hm.gloomhavenhelper.model.CharacterClass;
import com.hm.gloomhavenhelper.model.ElementState;
import com.hm.gloomhavenhelper.model.MonsterData;
import com.hm.gloomhavenhelper.model.Player;
import com.hm.gloomhavenhelper.model.PlayerInit;
import com.hm.gloomhavenhelper.model.Scenario;
import com.hm.gloomhavenhelper.model.SummonColor;
import com.hm.gloomhavenhelper.network.Network;
import com.hm.gloomhavenhelper.util.Animator;
import com.hm.gloomhavenhelper.util.ElementButton;
import com.hm.gloomhavenhelper.util.FormulaEvaluator;
import com.hm.gloomhavenhelper.util.GloomScrollPane;
import com.hm.gloomhavenhelper.util.Row;
import com.hm.gloomhavenhelper.util.TextMenu;
import com.hm.gloomhavenhelper.util.builders.TextButtonBuilder;
import com.hm.minlog.Log;
import com.hm.spine.AnimationState;
import com.hm.spine.utils.SkeletonActor;
import java.util.List;

public class GloomhavenHelper extends ApplicationAdapter {
   Sprite bg;
   Sprite vignette;
   Sprite bgLeft;
   Sprite bgRight;
   Sprite bgMiddle;
   Button mainMenuButton;
   public ElementButton fireButton;
   public ElementButton iceButton;
   public ElementButton airButton;
   public ElementButton earthButton;
   public ElementButton lightButton;
   public ElementButton darkButton;
   TextButton roundButton;
   TextButton drawButton;
   private Label scenarioLevelLabel;
   private Label xpLabel;
   private Label trapsLabel;
   private Label coinsLabel;
   public Label scenarioLabel;
   public Label roundLabel;
   public Label attackLabel;
   public Cell helpCell;
   Stack nextButtonStack;
   Button attackButton;
   Image attackImage1;
   Image attackImage2;
   public Rows rows;
   public ScrollPane rowsScroll;
   Table elementsTable;
   public final Array monsterRows = new Array();
   Table scenarioTable;
   public MainMenu mainMenu;
   public Intro intro;
   public Group toasts;
   private long lastZoomTime;
   public boolean zoomOut;
   public boolean zoomIn;
   int drawMinutes;
   Timer.Task drawTask;
   private int scenarioNumber;
   private boolean solo;
   private Cell networkCell;
   Container networkDisconnected;
   Container networkConnected;
   Container networkServerGray;
   Container networkServerGreen;
   TextButton helpAddPlayers;
   TextButton helpSetScenario;
   TextButton helpAddMonsters;
   Table helpSetScenarioOrAddMonsters;
   Label helpSpawnMonsters;
   Label helpChooseCards1;
   Label helpChooseCards2;
   Label helpChooseCards3;
   Label helpTakeTurns1;
   Label helpTakeTurns2;
   Label helpTakeTurns3;
   Label helpNextRound1;
   Label helpNextRound2;
   public final Array playerRows = new Array();

   @Override
   public void create() {
      App.game.initialize();
      if (App.args != null) {
         int i = 0;

         for (int n = App.args.length; i < n; i++) {
            if (App.args[i].equals("-verbose")) {
               Log.TRACE();
               Gdx.app.setLogLevel(3);
            }
         }
      }

      App.gameThread = App.game.currentThread();
      App.game.loadConfig();
      App.load();
      App.viewport.setMinWorldHeight(App.config.zoomReset ? Math.max(800, Gdx.graphics.getHeight()) : App.config.zoom);
      this.updateViewport();
      this.drawTask = new Timer.Task() {
         @Override
         public void run() {
            GloomhavenHelper.this.drawMinutes++;
         }
      };
      this.createUI();
      this.layoutUI();
      this.events();
      App.stage.setScrollFocus(this.rowsScroll);
      if (App.config.fullscreen) {
         Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
         Gdx.graphics.setVSync(true);
      }

      App.game.loadState();
      this.updateHelpText();
   }

   public void createUI() {
      App.root = new Table();
      this.intro = new Intro();
      this.bg = App.skin.getSprite("bg/bg");
      this.vignette = App.skin.getSprite("vignette/vignette");
      this.bgLeft = App.skin.getSprite("vignette/bg-left");
      this.bgRight = App.skin.getSprite("vignette/bg-right");
      this.bgMiddle = App.skin.getSprite("vignette/bg-middle");
      this.networkDisconnected = new Container(new Image(App.drawable("network-disconnected", new Color(-803332353)))).padLeft(35.0F);
      this.networkConnected = new Container(new Image(App.drawable("network-connected", new Color(209330431)))).padLeft(35.0F);
      this.networkServerGray = new Container(new Image(App.drawable("network-server", App.buttonGray))).padLeft(35.0F);
      this.networkServerGreen = new Container(new Image(App.drawable("network-server", new Color(209330431)))).padLeft(35.0F);
      this.mainMenu = new MainMenu();
      this.mainMenuButton = App.imageButton().imageUp("psd/menu", App.buttonGray).imageOver("psd/menu").imageChecked("psd/menu").create();
      this.scenarioLabel = new Label("", App.skin, "plainLargeOutline", App.buttonGray);
      this.scenarioLabel.setWrap(true);
      this.scenarioLabel.setAlignment(1);
      this.fireButton = new ElementButton("fire") {
         @Override
         protected void clicked(ElementState elementState) {
            App.state.fire = elementState;
            App.state.changed();
         }
      };
      this.iceButton = new ElementButton("ice") {
         @Override
         protected void clicked(ElementState elementState) {
            App.state.ice = elementState;
            App.state.changed();
         }
      };
      this.airButton = new ElementButton("air") {
         @Override
         protected void clicked(ElementState elementState) {
            App.state.air = elementState;
            App.state.changed();
         }
      };
      this.earthButton = new ElementButton("earth") {
         @Override
         protected void clicked(ElementState elementState) {
            App.state.earth = elementState;
            App.state.changed();
         }
      };
      this.lightButton = new ElementButton("light") {
         @Override
         protected void clicked(ElementState elementState) {
            App.state.light = elementState;
            App.state.changed();
         }
      };
      this.darkButton = new ElementButton("dark") {
         @Override
         protected void clicked(ElementState elementState) {
            App.state.dark = elementState;
            App.state.changed();
         }
      };
      this.roundButton = App.textButton(Text.nextRound.replace(" ", "\n")).disabledFontColor(App.gray).create();
      this.roundButton.getLabelCell().pad(-12.0F, 9.0F, 0.0F, 0.0F);
      TextButtonBuilder builder = App.textButton(Text.draw).disabledFontColor(App.gray);
      this.drawButton = builder.set(new TextButton("", builder.set(new TextButton.TextButtonStyle())) {
         @Override
         public void draw(Batch batch, float parentAlpha) {
            if (GloomhavenHelper.this.drawMinutes > 0) {
               App.plainMediumOutline.setColor(App.buttonGray);
               String text = GloomhavenHelper.this.drawMinutes == 1 ? "1 minute" : GloomhavenHelper.this.drawMinutes + " minutes";
               App.plainMediumOutline.draw(batch, text, Math.round(this.getWidth() / 2.0F), this.getHeight() - 11.0F, 0.0F, 1, false);
            }

            super.draw(batch, parentAlpha);
         }
      });
      this.roundLabel = new Label("", App.skin, "plainMediumOutline", App.buttonGray);
      this.roundLabel.setTouchable(Touchable.disabled);
      this.nextButtonStack = new Stack(
         new Container(this.roundLabel)
            .width(0.0F)
            .pad(
               0.0F,
               0.0F,
               5.0F,
               !App.config.language.equals("fr")
                     && !App.config.language.equals("pl")
                     && !App.config.language.equals("jp")
                     && !App.config.language.equals("it")
                     && !App.config.language.equals("ru")
                  ? 8
                  : -4
            )
            .bottom()
            .right()
      );
      this.scenarioLevelLabel = new Label("", App.skin, "default", App.buttonGray);
      this.xpLabel = new Label("", App.skin, "default", App.buttonGray);
      this.trapsLabel = new Label("", App.skin, "default", App.buttonGray);
      this.coinsLabel = new Label("", App.skin, "default", App.buttonGray);
      this.attackButton = App.imageButton().imageUp(App.drawable("attack/back", "attack/border")).create();
      this.attackImage1 = new Image();
      this.attackImage2 = new Image();
      this.attackLabel = new Label("", App.skin, "plainMediumOutline", App.lightGray);
      this.attackLabel.setTouchable(Touchable.disabled);
      this.rows = new Rows();
      this.elementsTable = new Table();
      this.scenarioTable = new Table(App.skin);
      this.scenarioTable.setTouchable(Touchable.enabled);
      this.rowsScroll = new GloomScrollPane(this.rows) {
         @Override
         public void invalidate() {
            super.invalidate();
            GloomhavenHelper.this.rows.invalidate();
         }

         @Override
         protected void visualScrollY(float pixelsY) {
            super.visualScrollY(pixelsY);
            if (App.dragging) {
               App.stage.screenToStageCoordinates(App.v2.set(Gdx.input.getX(), Gdx.input.getY()));
               GloomhavenHelper.this.updateDrag(App.v2.x, App.v2.y);
            }
         }

         @Override
         public void draw(Batch batch, float parentAlpha) {
            this.validate();
            batch.setColor(parentAlpha, parentAlpha, parentAlpha, 0.0F);
            batch.disableBlending();
            float bgWidth = 1024.0F;
            float x = GloomhavenHelper.this.rows.startX;
            float top = Math.round(this.getHeight() + this.getVisualScrollY());

            while (x > 0.0F) {
               x -= bgWidth;
            }

            for (float endX = this.getWidth() + bgWidth; x < endX; x += bgWidth) {
               for (float f = top; f >= -1024.0F; f -= 1024.0F) {
                  batch.draw(GloomhavenHelper.this.bg, x, f, bgWidth, 1024.0F);
               }
            }

            batch.enableBlending();
            batch.setColor(1.0F, 1.0F, 1.0F, parentAlpha);
            float right = this.getWidth() - 145.0F;

            for (float bgy = top; bgy >= -1024.0F; bgy -= 1024.0F) {
               batch.draw(GloomhavenHelper.this.bgLeft, 0.0F, bgy, 124.0F, 1024.0F);
               batch.draw(GloomhavenHelper.this.bgRight, right, bgy, 145.0F, 1024.0F);

               for (int i = 1; i < GloomhavenHelper.this.rows.columns; i++) {
                  batch.draw(
                     GloomhavenHelper.this.bgMiddle,
                     (float)Math.round(
                        GloomhavenHelper.this.rows.startX
                           + (1101.0F + GloomhavenHelper.this.rows.spaceX) * i
                           - GloomhavenHelper.this.rows.spaceX / 2.0F
                           - 40.0F
                     ),
                     bgy,
                     81.0F,
                     1024.0F
                  );
               }
            }

            batch.draw(GloomhavenHelper.this.vignette, this.getX(), this.getY(), this.getWidth(), this.getHeight());
            super.draw(batch, parentAlpha);
         }
      };
      this.rowsScroll.getStyle().vScrollKnob = App.skin.getDrawable("scroll-vert");
      this.rowsScroll.setFadeScrollBars(true);
      this.rowsScroll.setupFadeScrollBars(0.4F, 0.0F);
      this.rowsScroll.setScrollBarTouch(false);
      this.rowsScroll.setFlickScrollTapSquareSize(35.0F);
      this.helpAddPlayers = App.textButton("Add Characters").create();
      this.helpSetScenario = App.textButton("Set Scenario").create();
      this.helpAddMonsters = App.textButton("Add Monsters").create();
      this.helpSetScenarioOrAddMonsters = new Table(App.skin).left();
      this.helpSetScenarioOrAddMonsters.add(this.helpSetScenario);
      this.helpSetScenarioOrAddMonsters.add(new Label("    or    ", App.skin, "plainLargeOutline", App.buttonGray));
      this.helpSetScenarioOrAddMonsters.add(this.helpAddMonsters);
      this.helpSpawnMonsters = new Label("Use the \"+\" buttons\nto spawn monsters.", App.skin, "plainLargeOutline", App.buttonGray);
      this.helpChooseCards1 = new Label("", App.skin, "plainLargeOutline", App.buttonGray);
      this.helpChooseCards2 = new Label("", App.skin, "plainLargeOutline", App.buttonGray);
      this.helpChooseCards3 = new Label("Choose cards.", App.skin, "plainLargeOutline", App.buttonGray);
      this.helpTakeTurns1 = new Label("", App.skin, "plainLargeOutline", App.buttonGray);
      this.helpTakeTurns2 = new Label("", App.skin, "plainLargeOutline", App.buttonGray);
      this.helpTakeTurns3 = new Label("Take turns.", App.skin, "plainLargeOutline", App.buttonGray);
      this.helpNextRound1 = new Label("Click next round.", App.skin, "plainLargeOutline", App.buttonGray);
      this.helpNextRound2 = new Label("Next round.", App.skin, "plainLargeOutline", App.buttonGray);
      this.helpSpawnMonsters.setAlignment(1);
      this.helpChooseCards1.setAlignment(1);
      this.helpChooseCards2.setAlignment(1);
      this.helpChooseCards3.setAlignment(1);
      this.helpTakeTurns1.setAlignment(1);
      this.helpTakeTurns2.setAlignment(1);
      this.helpTakeTurns3.setAlignment(1);
      this.helpNextRound1.setAlignment(1);
      this.helpNextRound2.setAlignment(1);
      this.toasts = new Group();
      App.stage.addActor(this.toasts);
   }

   public void updateHelpText() {
      if (App.state.playerInit == PlayerInit.dragOrder) {
         this.helpChooseCards1.setText("Choose character ability cards,\nthen click Draw.");
         this.helpChooseCards2.setText("Choose character ability cards.");
         this.helpTakeTurns1.setText("Drag portraits for init order.\nTake turns, click each portrait.");
         this.helpTakeTurns2.setText("Drag portraits, take turns.");
      } else if (App.state.playerInit == PlayerInit.numpad) {
         this.helpChooseCards1.setText("Choose character ability cards,\nclick portraits, draw.");
         this.helpChooseCards2.setText("Choose character ability cards,\nset initiatives, draw.");
         this.helpTakeTurns1.setText("Take turns, click each portrait.");
         this.helpTakeTurns2.setText("Take turns, click each portrait.");
      } else {
         this.helpChooseCards1.setText("Choose character ability cards,\ndrag portraits right, draw.");
         this.helpChooseCards2.setText("Choose character ability cards,\nset initiatives, draw.");
         this.helpTakeTurns1.setText("Take turns, click each portrait.");
         this.helpTakeTurns2.setText("Take turns, click each portrait.");
      }
   }

   private void layoutUI() {
      this.elementsTable.defaults().space(5.0F);
      this.elementsTable.add(this.fireButton);
      this.elementsTable.add(this.iceButton);
      this.elementsTable.add(this.airButton);
      this.elementsTable.add(this.earthButton);
      this.elementsTable.add(this.lightButton);
      this.elementsTable.add(this.darkButton);
      this.scenarioTable.add(this.scenarioLabel).colspan(8).padBottom(10.0F).fill().row();
      this.scenarioTable.add(this.scenarioLevelLabel).padRight(10.0F);
      this.scenarioTable.add(App.image("psd/level", App.buttonGray)).padRight(35.0F);
      this.scenarioTable.add(this.trapsLabel).padRight(9.0F);
      this.scenarioTable.add(App.image("psd/traps", App.buttonGray)).padRight(34.0F);
      this.scenarioTable.add(this.xpLabel).padRight(10.0F);
      this.scenarioTable.add(App.image("psd/xp", App.buttonGray)).padRight(34.0F);
      this.scenarioTable.add(this.coinsLabel).padRight(10.0F);
      this.scenarioTable.add(App.image("psd/coins", App.buttonGray));
      Table table = new Table();
      table.padBottom(3.0F);
      table.add(this.scenarioTable);
      this.networkCell = table.add().bottom();
      Table top = new Table();
      top.setBackground(App.skin.getDrawable("psd/bar-top"));
      top.pad(9.0F, 9.0F, 21.0F, 9.0F).defaults().space(15.0F);
      top.add(this.mainMenuButton).size(102.0F);
      this.helpCell = top.add().expandX().padLeft(-9.0F);
      top.add(this.elementsTable);
      Group attack2 = new Group();
      attack2.addActor(this.attackImage2);
      Table attackTable = new Table() {
         float desat;

         @Override
         public void act(float delta) {
            super.act(delta);
            float target = GloomhavenHelper.this.monsterRows.size != 0 && !App.state.canDraw ? 0 : 1;
            this.desat = App.animate(this.desat, target, 0.5F, 1.0F, 2.0F, delta);
         }

         @Override
         public void draw(Batch batch, float parentAlpha) {
            if (this.desat != 0.0F) {
               batch.setShader(App.desatShader);
               App.desatShader.setUniformf("u_desat", 0.9F * this.desat);
            }

            super.draw(batch, parentAlpha);
            if (this.desat != 0.0F) {
               batch.setShader(null);
            }
         }
      };
      attackTable.defaults().space(20.0F);
      attackTable.add(new Stack(this.attackButton, new Container(this.attackLabel).padRight(4.0F).padBottom(-3.0F).bottom().right())).size(158.0F, 107.0F);
      attackTable.add(this.attackImage1).size(158.0F, 107.0F);
      attackTable.add(attack2).width(38.0F);
      Table bottom = new Table();
      bottom.setBackground(App.skin.getDrawable("psd/bar-bottom"));
      bottom.pad(20.0F, 9.0F, 9.0F, 9.0F).defaults().space(20.0F);
      bottom.add(this.nextButtonStack)
         .size(127.0F, 118.0F)
         .pad(
            0.0F,
            !App.config.language.equals("pl") && !App.config.language.equals("cz") && !App.config.language.equals("it") && !App.config.language.equals("es")
               ? (App.config.language.equals("hu") ? 3 : -9)
               : -2,
            -9.0F,
            0.0F
         );
      bottom.add(table).expand().padLeft(-17.0F);
      bottom.add(attackTable);
      this.attackImage2.setRotation(-31.5F);
      this.attackImage2.setBounds(-82.0F, -29.0F, 158.0F, 107.0F);
      attack2.toBack();
      App.root.setFillParent(true);
      App.root.defaults().growX();
      App.root.add(top).padBottom(-12.0F).row();
      App.root.add(this.rowsScroll).grow().row();
      App.root.add(bottom).height(131.0F).padTop(-11.0F);
      top.toFront();
   }

   private void events() {
      Gdx.input.setInputProcessor(new InputMultiplexer(new InputAdapter() {
         @Override
         public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (GloomhavenHelper.this.intro.introPhase > -1 && GloomhavenHelper.this.intro.introPhase < 3) {
               return false;
            } else {
               SkeletonActor actor = App.rippleSkeletonPool.obtain();
               AnimationState.TrackEntry entry = actor.getAnimationState().setAnimation(0, "ripple", false);
               entry.setTrackEnd(entry.getAnimation().getDuration());
               actor.addAction(Actions.sequence(Actions.delay(entry.getTrackEnd()), Actions.removeActor()));
               Vector2 position = App.stage.screenToStageCoordinates(App.v2.set(screenX, screenY));
               actor.setPosition(position.x, position.y);
               App.stage.addActor(actor);
               return false;
            }
         }
      }, App.stage));
      this.attackButton
         .addListener(
            new ClickListener() {
               @Override
               public void clicked(InputEvent event, float x, float y) {
                  if (!App.state.canDraw) {
                     AttackModifier attackModifier3 = App.state.attackModifier2();
                     if (App.state.attackModifiers.size == 0) {
                        App.state.shuffleAttackModifiers(true);
                        GloomhavenHelper.this.attackImage1.setDrawable(null);
                        GloomhavenHelper.this.attackImage2.setDrawable(null);
                     }

                     App.state.attackModifiersDiscard.add(App.state.attackModifiers.pop());
                     AttackModifier attackModifier1 = App.state.attackModifier1();
                     AttackModifier attackModifier2 = App.state.attackModifier2();
                     switch (attackModifier1) {
                        case nullAttack:
                        case doubleAttack:
                           App.state.needsShuffle = true;
                     }

                     App.animateAttackCard(
                        attackModifier1, GloomhavenHelper.this.attackButton, GloomhavenHelper.this.attackImage1, GloomhavenHelper.this.attackImage2
                     );
                     if (!App.config.server && !App.config.client) {
                        App.state.changed();
                     } else {
                        byte[] bytes = new byte[]{
                           97,
                           (byte)(attackModifier1.ordinal() + 1),
                           (byte)(attackModifier2 == null ? 0 : attackModifier2.ordinal() + 1),
                           (byte)(attackModifier3 == null ? 0 : attackModifier3.ordinal() + 1)
                        };
                        App.state.changed(true, bytes);
                     }
                  }
               }
            }
         );
      ClickListener attackDiscardListener = new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            new DeckDialog.AttackModifierDeckDialog().show();
         }
      };
      this.attackImage1.addListener(attackDiscardListener);
      this.attackImage2.addListener(attackDiscardListener);
      this.mainMenuButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            GloomhavenHelper.this.mainMenu.show(GloomhavenHelper.this.mainMenuButton, 0.0F, 0.0F, -31.0F, 0.0F, true);
         }
      });
      this.drawButton
         .addListener(
            new ChangeListener() {
               @Override
               public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                  if ((App.state.playerInit == PlayerInit.dragNumberRequired || App.state.playerInit == PlayerInit.numpad)
                     && !GloomhavenHelper.this.playerInitiativeSet()) {
                     TextMenu menu = new TextMenu();
                     String text = "Player initiative numbers must be set.";
                     menu.table.add(new Container(new Label(text, App.skin, "plainLargeOutline", App.buttonGray)).pad(6.0F, 16.0F, 0.0F, 16.0F)).row();
                     if (App.config.help) {
                        if (App.state.playerInit == PlayerInit.numpad) {
                           text = "Click each player's portrait to set the initiative number.";
                        } else {
                           text = "Drag each player's portrait to the right to set the initiative number.";
                        }

                        menu.table.add(new Container(new Label(text, App.skin, "plainLargeOutline", App.buttonGray)).pad(6.0F, 16.0F, 0.0F, 16.0F)).row();
                     }

                     menu.table.padBottom(9.0F);
                     menu.show(GloomhavenHelper.this.drawButton, 0.0F, 0.0F, 0.0F, 0.0F, true);
                     menu.setBackgroundOffset(9.0F, 9.0F, 0.0F, 0.0F);
                  } else {
                     int i = 0;

                     for (int n = GloomhavenHelper.this.monsterRows.size; i < n; i++) {
                        ((MonsterRow)GloomhavenHelper.this.monsterRows.get(i)).showAbility();
                     }

                     App.state.canDraw = false;
                     GloomhavenHelper.this.sortByInitiative(0.5F);
                     App.state.changed();
                     GloomhavenHelper.this.rowsScroll.setScrollY(0.0F);
                  }
               }
            }
         );
      this.roundButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (App.state.expireConditions && !GloomhavenHelper.this.turnsFinished()) {
               boolean conditions = false;
               boolean wounds = false;
               SnapshotArray children = GloomhavenHelper.this.rows.getChildren();
               if (App.state.expireConditions) {
                  int i = 0;

                  for (int n = children.size; i < n; i++) {
                     if (((Row)children.get(i)).hasExpiringConditions()) {
                        conditions = true;
                     }
                  }
               }

               if (!conditions && !wounds) {
                  GloomhavenHelper.this.nextRound();
                  return;
               }

               final TextMenu menu = new TextMenu();
               String text = "Some turns have not been taken.";
               menu.table.add(new Container(new Label(text, App.skin, "plainLargeOutline", App.buttonGray)).pad(6.0F, 16.0F, 0.0F, 16.0F)).row();
               if (App.config.help) {
                  text = "Click each portrait as turns are completed\nso conditions expire correctly.";
                  menu.table.add(new Container(new Label(text, App.skin, "plainLargeOutline", App.buttonGray)).pad(6.0F, 16.0F, 0.0F, 16.0F)).row();
               }

               ((TextButton)menu.addTextItem(Text.nextRound, new ChangeListener() {
                  @Override
                  public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                     GloomhavenHelper.this.nextRound();
                     menu.hide();
                  }
               }).getActor()).getLabel().setAlignment(1);
               menu.show(GloomhavenHelper.this.roundButton, 0.0F, 0.0F, 0.0F, 0.0F, true);
               menu.setBackgroundOffset(9.0F, 9.0F, 0.0F, 0.0F);
            } else {
               GloomhavenHelper.this.nextRound();
            }
         }
      });
      this.scenarioTable.addListener(new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            TextMenu menu = GloomhavenHelper.this.mainMenu.setScenarioLevel();
            menu.show(GloomhavenHelper.this.scenarioTable, 0.0F, 38.0F, 0.0F, 0.0F, true);
            menu.setBackgroundOffset(-9.0F, -9.0F, 18.0F, 0.0F);
         }
      });
      this.helpAddPlayers.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            GloomhavenHelper.this.mainMenuButton.setProgrammaticChangeEvents(true);
            GloomhavenHelper.this.mainMenuButton.toggle();
            GloomhavenHelper.this.mainMenuButton.setProgrammaticChangeEvents(false);
            GloomhavenHelper.this.mainMenu.addCharacters(true);
         }
      });
      this.helpSetScenario.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            GloomhavenHelper.this.mainMenuButton.setProgrammaticChangeEvents(true);
            GloomhavenHelper.this.mainMenuButton.toggle();
            GloomhavenHelper.this.mainMenuButton.setProgrammaticChangeEvents(false);
            GloomhavenHelper.this.mainMenu.setScenario(true);
         }
      });
      this.helpAddMonsters.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            GloomhavenHelper.this.mainMenuButton.setProgrammaticChangeEvents(true);
            GloomhavenHelper.this.mainMenuButton.toggle();
            GloomhavenHelper.this.mainMenuButton.setProgrammaticChangeEvents(false);
            GloomhavenHelper.this.mainMenu.addMonsters(true);
         }
      });
   }

   void nextRound() {
      if (App.state.round >= 3 || App.config.language.equals("es")) {
         App.game.showAd();
      }

      if (App.state.needsShuffle) {
         App.state.shuffleAttackModifiers(true);
         this.attackImage1.setDrawable(null);
         this.attackImage2.setDrawable(null);
      }

      this.fireButton.endOfRound();
      this.iceButton.endOfRound();
      this.airButton.endOfRound();
      this.earthButton.endOfRound();
      this.lightButton.endOfRound();
      this.darkButton.endOfRound();
      if (App.state.scenarioNumber == 31) {
         this.lightButton.setState(ElementState.inert, true);
         this.darkButton.setState(ElementState.strong, true);
      }

      for (MonsterRow row : this.monsterRows) {
         row.turnEnd(true);
         row.roundEnd();
      }

      for (PlayerRow row : this.playerRows) {
         row.turnEnd(true);
         row.roundEnd();
      }

      App.state.round++;
      App.state.canDraw = true;
      this.sortByInitiative(0.5F);
      App.state.changed();
      this.rowsScroll.setScrollY(0.0F);
   }

   public void updateDrag(float stageX, float stageY) {
      if (App.dragging) {
         this.rows.stageToLocalCoordinates(App.v2.set(stageX, stageY));
         float x = App.v2.x;
         float y = App.v2.y;
         if (App.lastSwapRow != null
            && App.lastSwapRow.dragStartActor != null
            && (
               y < App.lastSwapRow.finalY + App.lastSwapRow.dragStartActor.getY()
                  || y > App.lastSwapRow.finalY + App.lastSwapRow.getHeight() - App.lastSwapRow.getHeight() - App.lastSwapRow.dragStartActor.getTop()
                  || Math.abs(x - App.lastSwapRow.finalX) > 550.0F
            )) {
            App.lastSwapRow = null;
         }

         if (App.lastSwapRow == null) {
            SnapshotArray children = this.rows.getChildren();
            int current = App.dragRow.getZIndex();
            int i = 0;

            for (int n = children.size; i < n; i++) {
               Row row = (Row)children.get(i);
               if (row != App.dragRow && (!App.config.hideMonsters || !(row instanceof MonsterRow))) {
                  float left = row.finalX;
                  float right = row.finalX + row.getWidth() * 0.8F;
                  boolean overHoriz = this.rows.columns == 1 || !(x < left) && !(x >= right);
                  if (overHoriz) {
                     float bottom = row.finalY;
                     float top = row.finalY + row.getHeight();
                     if (i == current - 1) {
                        bottom += row.dragStartActor.getY();
                     }

                     if (i == current + 1) {
                        top -= row.getHeight() - row.dragStartActor.getTop();
                     }

                     boolean swap = y > bottom && y < top;
                     boolean afterOrBefore = false;
                     if (!swap) {
                        if (this.isLast(children, i, row) && y < bottom) {
                           i++;
                           swap = true;
                        } else if (this.isFirst(children, i, row) && y > top) {
                           i = Math.max(0, i - 1);
                           swap = true;
                        }
                     } else {
                        App.lastSwapRow = row;
                     }

                     if (swap) {
                        App.dragRow.setZIndex(i);
                        Animator.storeChildren(this.rows, 0.0F);
                        App.dragRow.getAnimator().finish(false);
                        break;
                     }
                  }
               }
            }
         }

         if (this.rows.columns == 1) {
            App.dragRow.setPosition(App.dragRow.finalX, y - App.dragStart.y);
         } else {
            App.dragRow.setPosition(x - App.dragStart.x, y - App.dragStart.y);
         }
      }
   }

   private boolean isFirst(SnapshotArray children, int i, Row row) {
      if (i == 0) {
         return true;
      } else {
         Row above = (Row)children.get(i - 1);
         if (above == App.dragRow) {
            if (i == 1) {
               return true;
            }

            above = (Row)children.get(i - 2);
         }

         return above.finalX != row.finalX;
      }
   }

   private boolean isLast(SnapshotArray children, int i, Row row) {
      int n = children.size;
      if (i == n - 1) {
         return true;
      } else {
         Row below = (Row)children.get(i + 1);
         if (below == App.dragRow) {
            if (i == n - 2) {
               return true;
            }

            below = (Row)children.get(i + 2);
         }

         return below.finalX != row.finalX;
      }
   }

   public void addMonsterRow(MonsterRow row) {
      this.monsterRows.add(row);
      this.rows.addActor(row);
      App.root.validate();
   }

   public int getLevelFromSpecials(int level, MonsterData data, Array specials, boolean solo) {
      int actualLevel = Math.min(7, level + (solo ? 1 : 0));

      for (String special : specials) {
         if (special.equals("Living Corpse: +2 levels") && data.english.equals("Living Corpse")) {
            actualLevel = Math.min(7, actualLevel + 2);
         }

         if (special.equals("Elite Stone Golem: +1 level, HPxC") && data.english.equals("Stone Golem")) {
            actualLevel = Math.min(7, actualLevel + 1);
         }

         if (special.equals("Harrower Infester: +1 level") && data.english.equals("Harrower Infester")) {
            actualLevel = Math.min(7, actualLevel + 1);
         }

         if (special.equals("City Guard: +1 level") && data.english.equals("City Guard")) {
            actualLevel = Math.min(7, actualLevel + 1);
         }

         if (special.equals("Land Leviathan: +1 level") && data.english.equals("Land Leviathan")) {
            actualLevel = Math.min(7, actualLevel + 1);
         }

         if (special.equals("The Gilded One: +1 level") && data.english.equals("The Gilded One")) {
            actualLevel = Math.min(7, actualLevel + 1);
         }

         if (special.equals("Aesther Ashblade: -1 level") && data.english.equals("Aesther Ashblade")) {
            actualLevel = Math.max(0, actualLevel - 1);
         }

         if (special.equals("City Archer: -1 level") && data.english.equals("City Archer")) {
            actualLevel = Math.max(0, actualLevel - 1);
         }

         if (special.equals("City Guard: -1 level") && data.english.equals("City Guard")) {
            actualLevel = Math.max(0, actualLevel - 1);
         }

         if (special.equals("Inox Archer: -1 level") && data.english.equals("Inox Archer")) {
            actualLevel = Math.max(0, actualLevel - 1);
         }

         if (special.equals("Inox Guard: -1 level") && data.english.equals("Inox Guard")) {
            actualLevel = Math.max(0, actualLevel - 1);
         }
      }

      return actualLevel;
   }

   public void setScenario(int scenarioNumber, int level, boolean solo, boolean addMonsters, String edition) {
      if (level > 7) {
         level = 7;
      }

      for (MonsterRow row : this.monsterRows) {
         row.remove();
      }

      this.monsterRows.clear();
      PlayerRow.localInit.clear(51);
      Array.ArrayIterator arrayIterator = this.playerRows.iterator();

      while (arrayIterator.hasNext()) {
         PlayerRow row = (PlayerRow)arrayIterator.next();
         if (row.player.characterClass.nonPlayer) {
            row.remove();
            arrayIterator.remove();
         } else {
            row.boxes.clear();
            row.monstersGroup.clearChildren();
            row.player.init(0);
            row.turnComplete = false;
            if (row.player.characterClass.asString().equals("BeastTyrant")) {
               MonsterBox box = row.addMonsterBox(1, App.summonData, row.player.level, false, false, SummonColor.beast, false);
               box.monster.hp = box.monster.hpMax = 8 + row.player.level * 2;
               box.monster.summonMove = 3;
               box.monster.summonAttack = 2;
               box.monster.isNew = false;
            }
         }
      }

      int startNumber = 1;

      for (int i = 0; i < App.editions.size(); i++) {
         if (((String)App.editions.get(i)).equals(edition)) {
            startNumber = (Integer)App.scenarioStartingNumbers.get(i);
            break;
         }
      }

      int index = scenarioNumber - startNumber;
      if (scenarioNumber >= startNumber) {
         Scenario scenario = (Scenario)((List)App.allScenarios.get(edition)).get(index);
         if (addMonsters) {
            for (MonsterData data : scenario.monsters) {
               int actualLevel = this.getLevelFromSpecials(level, data, scenario.special, solo);
               this.addMonsterRow(new MonsterRow(data, actualLevel));
            }
         }

         this.addSpecials(scenario.special, level);
      }

      this.scenarioLevelLabel.setText("");
      this.attackImage1.setDrawable(null);
      this.attackImage2.setDrawable(null);

      for (PlayerRow row : this.playerRows) {
         row.player.hp = row.player.hpMax;
         row.player.xp = 0;
         row.player.loot = 0;
         row.player.conditions.clear();
         row.player.expiredConditions.clear();
         row.player.currentTurnConditions.clear();
      }

      App.state.abilityDecks.clear(51);
      App.state.removedAbilities.clear();
      App.state.badOmen = 0;
      App.state.round = 1;
      App.state.scenarioNumber = scenarioNumber;
      App.state.scenarioLevel = level;
      App.state.solo = solo;
      App.state.canDraw = true;
      App.state.needsShuffle = false;
      App.state.attackModifiers.clear();
      App.state.shuffleAttackModifiers(false);
      App.state.fire = ElementState.inert;
      App.state.ice = ElementState.inert;
      App.state.air = ElementState.inert;
      App.state.earth = ElementState.inert;
      App.state.light = ElementState.inert;
      App.state.dark = ElementState.inert;
      App.state.apply(false);
      App.state.changed();
   }

   public void setScenarioLevel(int level, boolean solo, String edition) {
      Scenario scenario = null;
      int startingNumber = 1;

      for (int i = 0; i < App.editions.size(); i++) {
         if (edition.equals(App.editions.get(i))) {
            startingNumber = (Integer)App.scenarioStartingNumbers.get(i);
            break;
         }
      }

      if (App.state.scenarioNumber >= startingNumber && ((List)App.allScenarios.get(edition)).size() > this.scenarioNumber - startingNumber) {
         scenario = (Scenario)((List)App.allScenarios.get(edition)).get(this.scenarioNumber - startingNumber);
      }

      App.state.scenarioLevel = level;
      App.state.solo = solo;

      for (MonsterRow row : this.monsterRows) {
         int actualLevel = Math.min(7, level + (solo ? 1 : 0));
         if (scenario != null) {
            for (MonsterData data : scenario.monsters) {
               if (row.data == data) {
                  actualLevel = this.getLevelFromSpecials(level, data, scenario.special, solo);
               }
            }
         }

         row.setLevel(actualLevel);
      }
   }

   public void addSection(Scenario section) {
      int level = App.state.scenarioLevel;

      for (MonsterData data : section.monsters) {
         int actualLevel = this.getLevelFromSpecials(level, data, section.special, this.solo);
         if (this.getMonsterRow(data) == null) {
            this.addMonsterRow(new MonsterRow(data, actualLevel));
         }
      }

      for (String special : section.special) {
         if (special.equals("Valrath Tracker: %target% C, %pierce% 10, Vermling Shaman: CxH/2, City Guard: CxH/2, Savvas Lavaflow: CxH/2")) {
            this.getMonsterRow("Vermling Shaman");
         }
      }

      this.addSpecials(section.special, level);
   }

   private void addSpecials(Array specials, int level) {
      if (level > 7) {
         level = 7;
      }

      Player last = null;

      for (String special : specials) {
         boolean escort = special.startsWith("Escort: ");
         if (!escort && !special.startsWith("Objective: ")) {
            if (last != null && special.toLowerCase().startsWith("init: ")) {
               int init = App.parseInt(special.substring("init: ".length()));
               last.init(init);
            } else if (last != null) {
               last.hp = last.hpMax = FormulaEvaluator.getHitPoints(this.playerCount(), level, special);
            }
         } else {
            String name = special.substring(escort ? 8 : 11);
            Player player = this.getPlayer(name);
            if (player != null) {
               last = player;
            } else {
               player = new Player();
               player.level = level;
               player.name = name;
               player.characterClass = escort ? (CharacterClass)App.allClasses.get(1) : (CharacterClass)App.allClasses.get(0);
               player.hp = player.hpMax = player.characterClass.hpMax(level);
               player.init(99);
               last = player;
               PlayerRow row = new PlayerRow(player);
               this.playerRows.add(row);
               this.rows.addActor(row);
            }
         }
      }
   }

   public void sortByInitiative(float delay) {
      this.rows.getChildren().sort();
      Animator.storeChildren(this.rows, delay);
      this.roundButton.setDisabled(true);
      this.drawButton.setDisabled(true);
      this.rows.addAction(Actions.sequence(Actions.delay(delay + 0.5F), new Action() {
         @Override
         public boolean act(float delta) {
            GloomhavenHelper.this.roundButton.setDisabled(false);
            GloomhavenHelper.this.drawButton.setDisabled(false);
            return true;
         }
      }));
   }

   boolean turnsFinished() {
      for (MonsterRow row : this.monsterRows) {
         if ((row.isAlive() || !App.state.trackStandees) && !row.turnComplete) {
            return false;
         }
      }

      for (PlayerRow rowx : this.playerRows) {
         if (!rowx.turnComplete && rowx.isAlive()) {
            return false;
         }
      }

      return true;
   }

   boolean playerInitiativeSet() {
      for (PlayerRow row : this.playerRows) {
         if (row.player.init == 0 && row.isAlive()) {
            return false;
         }
      }

      return true;
   }

   public void zoomIn() {
      float height = Math.max(700.0F, App.viewport.getMinWorldHeight() - 5.0F);
      float max = Gdx.graphics.getHeight() * 1113 / Gdx.graphics.getWidth();
      height = Math.max(height, max);
      App.viewport.setMinWorldHeight(height);
      this.updateViewport();
      this.updateTextures();
      Gdx.graphics.requestRendering();
      App.config.zoom = height;
      App.config.zoomReset = false;
      App.game.saveConfig();
   }

   public void zoomOut() {
      float height = App.viewport.getMinWorldHeight() + 5.0F;
      height = Math.max(height, (float)(Gdx.graphics.getHeight() * 1113 / Gdx.graphics.getWidth()));
      App.viewport.setMinWorldHeight(height);
      this.updateViewport();
      this.updateTextures();
      Gdx.graphics.requestRendering();
      App.config.zoom = height;
      App.config.zoomReset = false;
      App.game.saveConfig();
   }

   @Override
   public void render() {
      long time = System.currentTimeMillis();
      if (Gdx.input.isKeyPressed(19) || this.zoomIn) {
         Gdx.graphics.requestRendering();
         if (System.currentTimeMillis() - this.lastZoomTime > 16L) {
            this.lastZoomTime = System.currentTimeMillis();
            this.zoomIn();
         }
      } else if (Gdx.input.isKeyPressed(20) || this.zoomOut) {
         Gdx.graphics.requestRendering();
         if (System.currentTimeMillis() - this.lastZoomTime > 16L) {
            this.lastZoomTime = System.currentTimeMillis();
            this.zoomOut();
         }
      }

      TooltipManager.getInstance().enabled = App.config.help;
      float delta = Math.min(Gdx.graphics.getDeltaTime(), 0.033333335F);
      this.intro.update(delta);
      boolean hasPlayers = this.playerCount() > 0;
      boolean hasMonsterRows = this.monsterRows.size > 0;
      boolean turnsFinished = this.turnsFinished();
      boolean hasMonsters = false;
      if (!App.state.trackStandees) {
         hasMonsters = true;
      } else {
         for (MonsterRow row : this.monsterRows) {
            if (row.boxes.size > 0) {
               hasMonsters = true;
               break;
            }
         }
      }

      boolean canDraw = App.state.canDraw;
      if (this.scenarioLevelLabel.setText(App.state.scenarioLevel) || this.solo != App.state.solo || this.scenarioNumber != App.state.scenarioNumber) {
         this.scenarioNumber = App.state.scenarioNumber;
         this.solo = App.state.solo;
         int level = App.state.scenarioLevel;
         int hazardousTerrainValue = 1 + (int)Math.ceil(level / 3.0F);
         int trapsValue = 2 + level + (App.state.solo && !App.state.edition.equals("JotL") ? 1 : 0);
         this.trapsLabel.setText(trapsValue + " / " + hazardousTerrainValue);
         this.coinsLabel.setText("x" + (level == 7 ? 6 : 2 + level / 2));
         if (App.state.edition.equals("JotL") && App.state.scenarioNumber < 3) {
            this.xpLabel.setText("+0");
         } else {
            this.xpLabel.setText("+" + (4 + level * 2));
         }

         int startingLevel = 1;

         for (int i = 0; i < App.editions.size(); i++) {
            if (App.state.edition.equals(App.editions.get(i))) {
               startingLevel = (Integer)App.scenarioStartingNumbers.get(i);
               break;
            }
         }

         if (App.state.scenarioNumber >= startingLevel && ((List)App.allScenarios.get(App.state.edition)).size() > this.scenarioNumber - startingLevel) {
            this.scenarioLabel.setText(((Scenario)((List)App.allScenarios.get(App.state.edition)).get(App.state.scenarioNumber - startingLevel)).name);
         } else {
            this.scenarioLabel.setText("");
         }
      }

      this.roundLabel.setText(App.state.round);
      this.attackLabel.setText(App.state.attackModifiers.size);
      boolean wasDrawVisible = this.drawButton.hasParent();
      if (!hasMonsterRows) {
         this.drawButton.remove();
         this.roundButton.remove();
      } else if (canDraw) {
         this.nextButtonStack.addActorAt(0, this.drawButton);
         this.roundButton.remove();
      } else {
         this.nextButtonStack.addActorAt(0, this.roundButton);
         this.drawButton.remove();
      }

      boolean drawVisible = this.drawButton.hasParent();
      if (!wasDrawVisible && drawVisible) {
         this.drawTask.cancel();
         Timer.schedule(this.drawTask, 60.0F, 60.0F);
         this.drawMinutes = 0;
      } else if (wasDrawVisible && !drawVisible) {
         this.drawTask.cancel();
      }

      this.attackButton.setVisible(hasMonsterRows);
      this.attackImage1.setVisible(hasMonsterRows);
      this.attackImage2.setVisible(hasMonsterRows);
      this.attackLabel.setVisible(hasMonsterRows);
      this.roundLabel.setVisible(hasMonsterRows);
      this.elementsTable.setVisible(hasMonsterRows);
      this.scenarioTable.setVisible(App.state.scenarioNumber >= 0);
      this.helpCell.width(Value.prefWidth);
      if (!hasMonsterRows && !hasPlayers) {
         this.helpCell.setActor(this.helpAddPlayers).left();
      } else if (!hasMonsterRows) {
         this.helpCell.setActor(this.helpSetScenarioOrAddMonsters).left().width(0.0F);
      } else if (!App.config.help) {
         this.helpCell.setActor(null);
      } else if (canDraw) {
         if (App.state.round == 1 && !hasMonsters) {
            this.helpCell.setActor(this.helpSpawnMonsters).center();
         } else if (App.state.round == 1) {
            this.helpCell.setActor(this.helpChooseCards1).center().width(0.0F);
         } else if (App.state.round == 2) {
            this.helpCell.setActor(this.helpChooseCards2).center();
         } else {
            this.helpCell.setActor(this.helpChooseCards3).center();
         }
      } else if (turnsFinished && App.state.round < 2) {
         this.helpCell.setActor(this.helpNextRound1).center();
      } else if (turnsFinished) {
         this.helpCell.setActor(this.helpNextRound2).center();
      } else if (App.state.round == 1) {
         this.helpCell.setActor(this.helpTakeTurns1).center().width(0.0F);
      } else if (App.state.round == 2) {
         this.helpCell.setActor(this.helpTakeTurns2).center();
      } else {
         this.helpCell.setActor(this.helpTakeTurns3).center();
      }

      if (App.config.client) {
         this.networkCell.setActor(Network.clientRunning() ? (Network.clientIsConnected() ? this.networkConnected : this.networkDisconnected) : null);
      } else if (App.config.server) {
         this.networkCell.setActor(Network.serverRunning() ? (Network.serverHasClient() ? this.networkServerGreen : this.networkServerGray) : null);
      } else {
         this.networkCell.setActor(null);
      }

      App.rippleSkeletonPool.freeComplete();
      this.toasts.toFront();
      Gdx.gl.glClear(16384);
      App.stage.act(delta);
      App.stage.draw();
   }

   public boolean hasCharacterClass(String className) {
      for (PlayerRow row : this.playerRows) {
         if (row.player.characterClass.name.equals(className)) {
            return true;
         }
      }

      return false;
   }

   public boolean hasCharacters() {
      for (PlayerRow row : this.playerRows) {
         if (!row.player.characterClass.nonPlayer) {
            return true;
         }
      }

      return false;
   }

   public MonsterRow getMonsterRow(MonsterData data) {
      for (MonsterRow row : this.monsterRows) {
         if (row.data == data) {
            return row;
         }
      }

      return null;
   }

   public MonsterRow getMonsterRow(String name) {
      for (MonsterRow row : this.monsterRows) {
         if (row.data.name.equals(name)) {
            return row;
         }
      }

      return null;
   }

   public Player getPlayer(String name) {
      for (PlayerRow row : this.playerRows) {
         if (row.player.name.equals(name)) {
            return row.player;
         }
      }

      return null;
   }

   @Override
   public void resize(int width, int height) {
      Gdx.graphics.requestRendering();
      if (width != 0 && height != 0) {
         if (App.config.zoomReset) {
            App.viewport.setMinWorldHeight(Math.max(Gdx.graphics.getHeight(), 800));
         }

         this.updateViewport();
         this.updateTextures();
      }
   }

   public void updateViewport() {
      Game.Insets insets = App.game.getInsets();
      int top = (int)insets.top;
      int left = (int)insets.left;
      int bottom = (int)insets.bottom;
      int right = (int)insets.right;
      App.viewport.update(Gdx.graphics.getWidth() - left - right, Gdx.graphics.getHeight() - top - bottom, true);
      App.viewport.setScreenX(App.viewport.getScreenX() + left);
      App.viewport.setScreenY(App.viewport.getScreenY() + bottom);
      HdpiUtils.glViewport(App.viewport.getScreenX(), App.viewport.getScreenY(), App.viewport.getScreenWidth(), App.viewport.getScreenHeight());
   }

   public void updateTextures() {
      Texture.TextureFilter filter = this.nearest() ? Texture.TextureFilter.Nearest : Texture.TextureFilter.Linear;

      for (Texture texture : App.textures) {
         texture.setFilter(filter, filter);
      }

      if (this.intro.introPhase >= 0 && this.intro.introPhase <= 4) {
         App.atlas.findRegion("esoteric/logo").getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
      }
   }

   public boolean nearest() {
      return App.viewport.getScreenWidth() >= 1113 && App.viewport.getMinWorldHeight() == App.viewport.getScreenHeight();
   }

   public int playerCount() {
      int count = 0;

      for (PlayerRow row : this.playerRows) {
         if (!row.player.characterClass.nonPlayer) {
            count++;
         }
      }

      return count;
   }
}
