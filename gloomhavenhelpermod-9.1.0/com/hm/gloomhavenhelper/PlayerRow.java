package com.hm.gloomhavenhelper;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.SnapshotArray;
import com.hm.gloomhavenhelper.model.Condition;
import com.hm.gloomhavenhelper.model.Monster;
import com.hm.gloomhavenhelper.model.Player;
import com.hm.gloomhavenhelper.model.PlayerInit;
import com.hm.gloomhavenhelper.model.SummonColor;
import com.hm.gloomhavenhelper.network.Network;
import com.hm.gloomhavenhelper.util.Animator;
import com.hm.gloomhavenhelper.util.DragAdjust;
import com.hm.gloomhavenhelper.util.HPAdjust;
import com.hm.gloomhavenhelper.util.Row;
import com.hm.gloomhavenhelper.util.TextMenu;
import com.hm.gloomhavenhelper.util.builders.TextButtonBuilder;
import com.hm.minlog.Log;

public class PlayerRow extends Row {
   public static final ObjectSet localInit = new ObjectSet();
   private float hpPercent;
   Actor dragArea;
   private TextureRegion hpRegion;
   private TextureRegion initRegion;
   Drawable bloodDrawable;
   Drawable iconDrawable;
   Drawable glowDrawable;
   PlayerRowMenu menu;
   HPAdjust hpAdjust;
   HPAdjust xpAdjust;
   long lastAnimateIcon;
   DragAdjust initListener;
   Label nameLabel;
   Label hpLabel;
   Label lootLabel;
   Label xpLabel;
   Table leftInfo;
   Table rightInfo;
   ImageButton addSummonButton;
   float summonAlpha;
   float glowAlpha;
   public final Player player;

   public PlayerRow(Player player) {
      this.player = player;
      this.hpPercent = Math.min(1.0F, (float)player.hp / player.hpMax);
      this.create();
      this.layoutUI();
      this.events();
   }

   private void create() {
      this.dragArea = new Actor();
      this.hpRegion = App.skin.getRegion("player-hp");
      this.initRegion = App.skin.getRegion("init");
      this.glowDrawable = App.skin.getDrawable("player-glow");
      this.bloodDrawable = App.skin.getDrawable("blood");
      this.iconDrawable = App.skin.getDrawable("class-icons/" + this.player.characterClass.asString());
      this.nameLabel = new Label(this.player.characterClass.name, App.skin, "fancyLargeOutline", Color.WHITE);
      this.nameLabel.setEllipsis(true);
      this.leftInfo = new Table();
      this.rightInfo = new Table();
      this.hpLabel = new Label("", App.skin, "plainExtraLargeOutlineNumbers", Color.WHITE);
      this.lootLabel = new Label("", App.skin, "plainLargeOutlineFixedNumbers", App.lootGold);
      this.lootLabel.setAlignment(16);
      this.xpLabel = new Label("", App.skin, "plainLargeOutlineFixedNumbers", App.xpBlue);
      this.xpLabel.setAlignment(16);
      this.addSummonButton = App.imageButton().imageUp("psd/add", App.buttonGray).imageOver("psd/add").imageDisabled("psd/add", App.disabledGray).create();
      this.menu = new PlayerRowMenu(this);
   }

   private void layoutUI() {
      Image icon = new Image(this.iconDrawable);
      icon.setTouchable(Touchable.disabled);
      this.leftInfo.add(icon).pad(-36.0F, -16.0F, -40.0F, -14.0F).minSize(Value.prefWidth);
      this.leftInfo.add(this.nameLabel).growX().pad(-2.0F, 0.0F, -1.0F, 0.0F).minWidth(0.0F).prefWidth(0.0F).row();
      this.leftInfo.add(this.hpLabel).colspan(2).expand().pad(-2.0F, 53.0F, 0.0F, 0.0F).left();
      this.rightInfo.add(this.xpLabel).right().padRight(9.0F).height(0.0F).padTop(2.0F).minWidth(30.0F);
      this.rightInfo.add(new Image(App.skin.newDrawable("psd/xp", App.xpBlue))).row();
      this.rightInfo.add(this.lootLabel).right().padRight(7.0F).height(0.0F).padTop(17.0F).minWidth(30.0F);
      this.rightInfo.add(new Image(App.skin.newDrawable("abilities/loot", App.lootGold))).padTop(13.0F);
      TextureRegion bg = null;

      try {
         label32: {
            if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
               FileHandle file = new FileHandle("class-bgs/" + this.player.characterClass.toString() + ".png");
               if (file.exists()) {
                  try {
                     bg = new TextureRegion(new Texture(file));
                     break label32;
                  } catch (RuntimeException var6) {
                     Log.error("ghh", "Unable to read image: " + file, var6);
                  }
               }
            }

            bg = (TextureRegion)App.skin.optional("class-bgs/" + this.player.characterClass.toString(), TextureRegion.class);
            if (bg == null) {
               bg = App.skin.getRegion("separate/class-bgs/" + this.player.characterClass.toString());
            }
         }
      } catch (RuntimeException var7) {
      }

      Table table = new Table() {
         @Override
         public void draw(Batch batch, float a) {
            PlayerRow.this.beginDesat(batch);
            super.draw(batch, a);
         }
      };
      table.setClip(true);
      table.pad(10.0F, 0.0F, 15.0F, 12.0F);
      if (bg != null) {
         if (bg instanceof TextureAtlas.AtlasRegion) {
            TextureAtlas.AtlasRegion region = (TextureAtlas.AtlasRegion)bg;
            TextureAtlas.AtlasSprite sprite = new TextureAtlas.AtlasSprite(region);
            table.background(new SpriteDrawable(sprite));
         } else {
            Sprite sprite = new Sprite(bg);
            table.background(new SpriteDrawable(sprite));
         }
      }

      table.add(this.dragArea).width(174.0F).fillY().pad(-20.0F, 0.0F, -20.0F, 0.0F);
      table.add(this.leftInfo).growX().top().left().fillY();
      table.add(this.addSummonButton).size(102.0F).padRight(9.0F);
      table.add(this.rightInfo).padRight(158.0F).fillY();
      this.bottom();
      this.monstersCell = this.add(this.monstersGroup).colspan(2).pad(0.0F, 212.0F, 0.0F, 5.0F).fillX().expandY().top();
      this.row();
      this.add(table).pad(-1.0F, -1.0F, 1.0F, -1.0F).fillX().height(Value.prefHeight).padBottom(-5.0F);
   }

   private void events() {
      this.dragStartActor = this.dragArea;
      this.dragArea.addListener(this.dragListener);
      this.dragArea
         .addListener(
            new ClickListener() {
               @Override
               public void touchDragged(InputEvent event, float x, float y, int pointer) {
                  super.touchDragged(event, x, y, pointer);
                  if (PlayerRow.this.dragListener.isDragging()) {
                     this.cancel();
                  }

                  if (PlayerRow.this.initListener.isDragging()) {
                     this.cancel();
                  }
               }

               @Override
               public void clicked(InputEvent event, float x, float y) {
                  if (App.state.canDraw) {
                     if (App.state.playerInit == PlayerInit.numpad) {
                        final TextMenu menu = new TextMenu() {
                           @Override
                           protected void updatePosition() {
                              PlayerRow.this.dragArea
                                 .localToStageCoordinates(
                                    App.v2.set(PlayerRow.this.dragArea.getWidth(), Math.round(PlayerRow.this.dragArea.getHeight() / 2.0F))
                                 );
                              float xx = App.v2.x - 12.0F;
                              float yx = App.v2.y;
                              PlayerRow row = PlayerRow.this;
                              row.localToStageCoordinates(App.v2.set(0.0F, 0.0F));
                              this.setPosition(
                                 xx, yx, 0.0F, 0.0F, App.v2.x + 2.0F, App.v2.y, row.getWidth() - 3.0F, row.getHeight() + (row.boxes.size == 0 ? 0 : 6)
                              );
                              super.updatePosition();
                           }
                        };
                        final Label numberLabel = new Label("__", App.skin, "fancyLargeOutlineFixedNumbers", Color.WHITE);
                        numberLabel.setAlignment(1);
                        menu.table.add(new Container(numberLabel)).row();
                        menu.addListener(new InputListener() {
                           @Override
                           public boolean keyDown(InputEvent event, int keycode) {
                              if (keycode >= 7 && keycode <= 16) {
                                 String digit = Integer.toString(keycode - 7);
                                 numberLabel.setText(numberLabel.getText().charAt(1) + digit);
                                 if (numberLabel.getText().charAt(0) != '_') {
                                    PlayerRow.this.player.init(Integer.parseInt(numberLabel.getText().toString()));
                                    PlayerRow.localInit.add(PlayerRow.this.player.characterClass);
                                    menu.hide();
                                    App.state.changed();
                                 }
                              }

                              return true;
                           }
                        });
                        ChangeListener listener = new ChangeListener() {
                           @Override
                           public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                              String digit = ((TextButton)actor).getText().toString();
                              numberLabel.setText(numberLabel.getText().charAt(1) + digit);
                              if (numberLabel.getText().charAt(0) != '_') {
                                 PlayerRow.this.player.init(Integer.parseInt(numberLabel.getText().toString()));
                                 PlayerRow.localInit.add(PlayerRow.this.player.characterClass);
                                 menu.hide();
                                 App.state.changed();
                              }
                           }
                        };
                        Table numberTable = new Table(App.skin);
                        numberTable.pad(-6.0F, 12.0F, 0.0F, 12.0F).defaults().size(100.0F);
                        numberTable.columnDefaults(0).right();
                        numberTable.columnDefaults(2).left();

                        for (int i = 1; i <= 9; i++) {
                           numberTable.add(((TextButtonBuilder)App.textButton(String.valueOf(i)).change(listener)).create());
                           if (i % 3 == 0) {
                              numberTable.row();
                           }
                        }

                        numberTable.add();
                        numberTable.add(((TextButtonBuilder)App.textButton("0").change(listener)).create());
                        menu.table.add(numberTable).row();
                        menu.show(null, 0.0F, 0.0F, 0.0F, 0.0F, true);
                     }
                  } else {
                     if (PlayerRow.this.turnComplete) {
                        PlayerRow.this.turnEndRevert();
                     } else {
                        PlayerRow.this.turnEnd(false);
                     }

                     App.state.changed();
                  }
               }
            }
         );
      this.setTouchable(Touchable.enabled);
      this.addListener(new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            if (this.inTapSquare(x, y) && !(x < 200.0F) && !(y > 152.0F)) {
               App.gloom.rowsScroll.scrollTo(0.0F, PlayerRow.this.getY() - 7.0F, 0.0F, PlayerRow.this.getHeight() + 14.0F);
               PlayerRow.this.menu.show(null, 0.0F, 0.0F, 0.0F, 0.0F, true);
               PlayerRow.this.hpAdjust.changeContainer.clearActions();
               PlayerRow.this.hpAdjust.changeContainer.setVisible(false);
               PlayerRow.this.xpAdjust.changeContainer.clearActions();
               PlayerRow.this.xpAdjust.changeContainer.setVisible(false);
            }
         }
      });
      this.hpAdjust = new HPAdjust(this, "plainExtraLargeOutlineNumbers") {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (x < 200.0F) {
               return false;
            } else {
               return x >= 780.0F ? false : super.touchDown(event, x, y, pointer, button);
            }
         }

         @Override
         protected void getPosition(Vector2 position) {
            PlayerRow.this.hpLabel.localToStageCoordinates(position.set(PlayerRow.this.hpLabel.getWidth() + 3.0F, -5.0F));
         }

         @Override
         protected int getMax() {
            return PlayerRow.this.player.hpMax;
         }

         @Override
         protected int getValue() {
            return PlayerRow.this.player.hp;
         }

         @Override
         protected void setValue(int value) {
            if (PlayerRow.this.player.hp != value) {
               PlayerRow.this.hpChanged(value + this.extra - this.start);
            }

            PlayerRow.this.player.hp = value;
            if (value > 0) {
               App.state.changed();
            }

            super.setValue(value);
         }

         @Override
         protected void apply() {
            App.state.changed();
         }
      };
      this.xpAdjust = new HPAdjust(this, "plainExtraLargeOutlineNumbers") {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            return x < 780.0F ? false : super.touchDown(event, x, y, pointer, button);
         }

         @Override
         protected void getPosition(Vector2 position) {
            PlayerRow.this.xpLabel.localToStageCoordinates(position.set(PlayerRow.this.xpLabel.getWidth() + 45.0F, -25.0F));
         }

         @Override
         protected int getValue() {
            return PlayerRow.this.player.xp;
         }

         @Override
         protected void setValue(int value) {
            PlayerRow.this.player.xp = value;
            App.state.changed();
            super.setValue(value);
         }
      };
      this.initListener = new DragAdjust(null, this.dragArea) {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            return App.state.playerInit != PlayerInit.dragNumber && App.state.playerInit != PlayerInit.dragNumberRequired
               ? false
               : super.touchDown(event, x, y, pointer, button);
         }

         @Override
         public void touchDragged(InputEvent event, float x, float y, int pointer) {
            super.touchDragged(event, x, this.getTouchDownY(), pointer);
         }

         @Override
         public void dragStart(InputEvent event, float x, float y, int pointer) {
            App.stage.cancelTouchFocusExcept(PlayerRow.this.initListener, PlayerRow.this.dragArea);
            PlayerRow.this.player.init(1);
            PlayerRow.localInit.add(PlayerRow.this.player.characterClass);
            super.dragStart(event, x, y, pointer);
         }

         @Override
         public void dragStop(InputEvent event, float x, float y, int pointer) {
            super.dragStop(event, x, y, pointer);
            if (!App.state.canDraw) {
               PlayerRow.this.setZIndex(99999);
               SnapshotArray children = App.gloom.rows.getChildren();
               int i = 0;

               for (int n = children.size; i < n; i++) {
                  Row row = (Row)children.get(i);
                  if (row != PlayerRow.this) {
                     int init = row.getInitiative();
                     if (init >= 1 && init <= 99 && init >= PlayerRow.this.player.init) {
                        PlayerRow.this.setZIndex(i);
                        break;
                     }
                  }
               }
            }

            App.state.changed();
            if (!App.state.canDraw) {
               Animator.storeChildren(App.gloom.rows, 0.1F);
            }
         }

         @Override
         protected int getValue() {
            return PlayerRow.this.player.init;
         }

         @Override
         protected void setValue(int value) {
            PlayerRow.this.player.init(value);
         }

         @Override
         protected int getMax() {
            return 99;
         }

         @Override
         protected int amount(float x, float y) {
            return Math.round(x / 9.0F);
         }
      };
      this.initListener.min = 1;
      this.addSummonButton.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            event.stop();
            return super.touchDown(event, x, y, pointer, button);
         }
      });
      this.addSummonButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            SummonAddMenu menu = new SummonAddMenu(PlayerRow.this);
            menu.show(PlayerRow.this.addSummonButton, 19.0F, -22.0F, -41.0F, 22.0F, true);
         }
      });
   }

   @Override
   public void act(float delta) {
      super.act(delta);
      this.readyTime -= delta;
      this.hpPercent = App.animate(this.hpPercent, Math.min(1.0F, (float)this.player.hp / this.player.hpMax), 0.2F, 2.0F, 2.0F, delta);
      this.summonAlpha = App.animate(this.summonAlpha, this.boxes.size == 0 ? 0 : 1, 0.2F, 2.0F, 2.0F, delta);
      if (!App.state.ignoreChanges) {
         float target = 0.0F;
         if (App.state.canDraw && this.isAlive()) {
            if ((App.state.playerInit == PlayerInit.numpad || App.state.playerInit == PlayerInit.dragNumberRequired) && this.player.init == 0) {
               target = 1.0F;
            }
         } else if (this.isCurrentTurn()) {
            target = 1.0F;
         }

         this.glowAlpha = App.animate(this.glowAlpha, target, 0.2F, 2.0F, 2.0F, delta);
      }

      this.nameLabel.setText(this.player.name);
   }

   @Override
   public void draw(Batch batch, float a) {
      int init = this.getInitiative();
      boolean showInit = App.state.playerInit != PlayerInit.dragOrder && init > 0 && init < 100;
      if (showInit) {
         if (this.leftInfo.getPadLeft() == 0.0F) {
            this.leftInfo.padLeft(53.0F).invalidate();
            this.hpAdjust.minX = 252.0F;
         }
      } else if (this.leftInfo.getPadLeft() != 0.0F) {
         this.leftInfo.padLeft(0.0F).invalidate();
         this.hpAdjust.minX = 199.0F;
      }

      int hpMax = this.player.hpMax;
      this.hpLabel.setColor(App.c(Color.WHITE, a));
      this.hpLabel.setText(this.player.hp + "/" + hpMax);
      this.lootLabel.setText(this.player.loot);
      this.xpLabel.setText(this.player.xp);
      super.draw(batch, a);
      float x = this.getX();
      float y = this.getY();
      if (showInit) {
         batch.setColor(1.0F, 1.0F, 1.0F, a);
         batch.draw(this.initRegion, x + 188.0F, y + 97.0F);
         App.fancyExtraLargeOutlineNumbers.setColor(1.0F, 1.0F, 1.0F, a);
         boolean canHideInit = App.state.canDraw
            && !this.player.characterClass.nonPlayer
            && (
               App.config.client && Network.clientRunning() && Network.clientIsConnected()
                  || App.config.server && Network.serverRunning() && Network.serverHasClient()
            );
         String initString = canHideInit && !localInit.contains(this.player.characterClass) ? "??" : this.player.initString;
         App.fancyExtraLargeOutlineNumbers.draw(batch, initString, x + 202.0F, y + 89.0F, 0.0F, 1, false);
      }

      x += this.hpLabel.localToAscendantCoordinates(this, App.v2.set(this.hpLabel.getWidth(), 0.0F)).x + 18.0F;
      if (this.player.conditions.size > 0) {
         float space = 42.0F;
         if (this.player.conditions.size == 6) {
            space = 41.0F;
         } else if (this.player.conditions.size == 7) {
            x -= 5.0F;
            space = 36.0F;
         } else if (this.player.conditions.size > 7) {
            space = 31.0F;
            x -= 8.0F;
         }

         batch.setColor(App.c.set(1.0F, 1.0F, 1.0F, a));
         int i = 0;

         for (int last = this.player.conditions.size - 1; i <= last; i++) {
            Condition condition = (Condition)this.player.conditions.get(last - i);
            condition.drawable.draw(batch, x + i * space, y + 29.0F, 34.0F, 34.0F);
         }
      }

      x = this.getX();
      if (this.summonAlpha != 0.0F) {
         batch.setColor(1.0F, 1.0F, 1.0F, this.summonAlpha * a);
         this.iconDrawable.draw(batch, x + 115.0F, y + this.getHeight() - 93.0F, 97.0F, 97.0F);
      }

      if (this.glowAlpha != 0.0F) {
         batch.setColor(1.0F, 1.0F, 1.0F, this.glowAlpha * a);
         this.glowDrawable.draw(batch, x + 24.0F, y + 2.0F, 156.0F, 142.0F);
      }

      this.endDesat(batch);
      float deadFade = MathUtils.clamp(this.hpPercent / 1.0F / hpMax, 0.0F, 1.0F);
      batch.setColor(App.c(926365576, 0.533F * deadFade * a));
      this.hpRegion.setRegionWidth(117);
      batch.draw(this.hpRegion, x + 43.0F, y + 21.0F);
      if (this.hpPercent > 0.0F) {
         int hp = this.player.hp;
         batch.setColor(App.c((hp > 3 || !(this.hpPercent <= 0.4F) || hp == hpMax) && !(this.hpPercent < 0.3F) ? App.healthGreen : App.healthRed, a));
         this.hpRegion.setRegionWidth((int)Math.max(14.0F * deadFade, (float)Math.round(117.0F * this.hpPercent)));
         batch.draw(this.hpRegion, x + 43.0F, y + 21.0F);
      }

      batch.setColor(1.0F, 1.0F, 1.0F, a);
      this.bloodDrawable.draw(batch, x + this.leftInfo.getPadLeft() + 197.0F, y + 32.0F, 18.0F, 25.0F);
   }

   @Override
   public float desat() {
      return !this.isAlive() ? 1.0F : super.desat();
   }

   @Override
   protected void setParent(Group parent) {
      super.setParent(parent);
      if (parent == null) {
         this.hpAdjust.changeContainer.remove();
         this.xpAdjust.changeContainer.remove();
      } else {
         App.stage.addActor(this.hpAdjust.changeContainer);
         App.stage.addActor(this.xpAdjust.changeContainer);
      }
   }

   @Override
   public int getInitiative() {
      return this.isAlive() ? this.player.init : 102;
   }

   @Override
   public void turnStart(boolean endOfRound) {
      super.turnStart(endOfRound);
      Array names = new Array(3);
      if (this.player.conditions.contains(Condition.regenerate, true)) {
         names.add("regenerate");
      }

      if (this.player.conditions.contains(Condition.wound, true)) {
         names.add("wound");
      }

      if (this.player.conditions.contains(Condition.bane, true)) {
         names.add("bane");
      }

      this.flashIcons(names);
   }

   @Override
   public void turnEnd(boolean endOfRound) {
      if (!this.turnComplete) {
         super.turnEnd(endOfRound);
         if (this.isAlive() && App.state.expireConditions) {
            int i = this.player.conditions.size - 1;

            while (i >= 0) {
               Condition condition = (Condition)this.player.conditions.get(i);
               switch (condition) {
                  case stun:
                  case immobilize:
                  case disarm:
                  case muddle:
                  case strengthen:
                  case invisible:
                  case impair:
                  case bane:
                     if (!this.player.currentTurnConditions.contains(condition, true)) {
                        this.player.conditions.removeIndex(i);
                        this.player.expiredConditions.add(condition);
                     }
                  default:
                     i--;
               }
            }
         }
      }
   }

   @Override
   public void turnEndRevert() {
      if (this.turnComplete) {
         super.turnEndRevert();
         Array names = new Array(3);
         if (this.player.conditions.contains(Condition.regenerate, true)) {
            names.add("regenerate");
         }

         if (this.player.conditions.contains(Condition.wound, true)) {
            names.add("wound");
         }

         if (this.player.conditions.contains(Condition.bane, true)) {
            names.add("bane");
         }

         this.flashIcons(names);
         this.player.conditions.addAll(this.player.expiredConditions);
         this.player.conditions.sort();
         this.player.expiredConditions.clear();
      }
   }

   @Override
   public void roundEnd() {
      this.player.expiredConditions.clear();
      this.player.currentTurnConditions.clear();
      if (!this.player.characterClass.nonPlayer) {
         this.player.init(0);
      }

      super.roundEnd();
   }

   @Override
   public boolean hasExpiringConditions() {
      for (int i = this.player.conditions.size - 1; i >= 0; i--) {
         if (this.isAlive()) {
            switch ((Condition)this.player.conditions.get(i)) {
               case stun:
               case immobilize:
               case disarm:
               case muddle:
               case strengthen:
               case invisible:
               case bane:
                  return true;
               case impair:
            }
         }
      }

      return false;
   }

   @Override
   public boolean isAlive() {
      return this.player.hp > 0 && !this.player.exhausted;
   }

   void hpChanged(int amount) {
      long time = System.currentTimeMillis();
      if (time > this.lastAnimateIcon + 1000L) {
         this.lastAnimateIcon = time;
         boolean poison = this.player.conditions.contains(Condition.poison, true);
         boolean infect = this.player.conditions.contains(Condition.infect, true);
         boolean wound = this.player.conditions.contains(Condition.wound, true);
         boolean brittle = this.player.conditions.contains(Condition.brittle, true);
         boolean bane = this.player.conditions.contains(Condition.bane, true);
         boolean ward = this.player.conditions.contains(Condition.ward, true);
         boolean regenerate = this.player.conditions.contains(Condition.regenerate, true);
         Array names = new Array();
         if (poison) {
            names.add("poison");
         }

         if (infect) {
            names.add("infect");
         }

         if (brittle) {
            names.add("brittle");
         }

         if (amount < 0) {
            if (ward) {
               names.add("ward");
            }

            if (regenerate) {
               names.add("regenerate");
            }
         } else if (amount > 0) {
            if (wound) {
               names.add("wound");
            }

            if (bane) {
               names.add("bane");
            }

            if (infect) {
               names.add("infect");
            }
         }

         this.flashIcons(names);
      }
   }

   public void flashIcons(Array names) {
      int i = 0;

      for (int n = names.size; i < n; i++) {
         final String name = (String)names.get(i);
         if (i > 0) {
            this.addAction(Actions.sequence(Actions.delay(i * 0.4F), new Action() {
               @Override
               public boolean act(float delta) {
                  PlayerRow.this.flashIcon(name);
                  return true;
               }
            }));
         } else {
            this.flashIcon(name);
         }
      }
   }

   public void flashIcon(String name) {
      this.animateIcon(this.dragArea, "conditions/" + name + "-large", 2.0F, 105.0F, this.dragArea.getHeight() / 2.0F);
   }

   @Override
   public void setLevel(int level) {
      if (level > 9) {
         level = 9;
      }

      this.player.level = level;
      int oldMax = this.player.hpMax;
      this.player.hpMax = this.player.characterClass.hpMax(level);
      if (this.player.hp >= oldMax) {
         this.player.hp = this.player.hpMax;
      }

      for (MonsterBox box : this.boxes) {
         Monster monster = box.monster;
         oldMax = monster.hpMax;
         if (monster.summonColor == SummonColor.beast) {
            monster.hp = box.monster.hpMax = 8 + this.player.level * 2;
         } else {
            monster.hpMax = monster.stats.hpMax();
            if (monster.hp >= oldMax || oldMax == 0) {
               monster.hp = monster.hpMax;
            }
         }
      }

      App.state.changed();
   }

   @Override
   public int getLevel() {
      return this.player.level;
   }

   @Override
   public float getPrefHeight() {
      return animatedHeight && Animator.enabled && this.animateHeight != 0.0F ? this.animateHeight : super.getPrefHeight();
   }

   @Override
   public String toString() {
      String name = this.player.name;
      if (!name.equals(this.player.characterClass.toString())) {
         name = name + " (" + this.player.characterClass.toString() + ")";
      }

      return name;
   }
}
