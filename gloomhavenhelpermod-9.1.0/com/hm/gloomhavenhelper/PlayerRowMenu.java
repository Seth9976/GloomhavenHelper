package com.hm.gloomhavenhelper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ObjectMap;
import com.hm.gloomhavenhelper.model.Condition;
import com.hm.gloomhavenhelper.model.Player;
import com.hm.gloomhavenhelper.util.DragAdjust;
import com.hm.gloomhavenhelper.util.Menu;
import com.hm.gloomhavenhelper.util.TextMenu;
import com.hm.gloomhavenhelper.util.builders.ImageButtonBuilder;
import java.util.concurrent.atomic.AtomicBoolean;

public class PlayerRowMenu extends Menu {
   private static final int startCondition = 3;
   final PlayerRow row;
   final Player player;
   private ImageButton hpPlusButton;
   private ImageButton hpMinusButton;
   private Image hpImage;
   private ImageButton xpPlusButton;
   private ImageButton xpMinusButton;
   private Image xpImage;
   private ImageButton lootPlusButton;
   private ImageButton lootMinusButton;
   private Image lootImage;
   Label hpLabel;
   Label xpLabel;
   Label lootLabel;
   ImageTextButton levelButton;
   ImageButton killButton;
   Table conditionsTable;
   final ObjectMap conditionButtons = new ObjectMap();

   public PlayerRowMenu(PlayerRow row) {
      this.row = row;
      this.player = row.player;
      this.create();
      this.layoutUI();
      this.events();
   }

   private void create() {
      ImageButtonBuilder sub = App.imageButton().imageUp("psd/sub", App.buttonGray).imageOver("psd/sub").imageDisabled("psd/sub", App.disabledGray);
      ImageButtonBuilder add = App.imageButton().imageUp("psd/add", App.buttonGray).imageOver("psd/add");
      this.hpMinusButton = sub.create();
      this.hpImage = App.image("blood-large");
      this.hpPlusButton = add.create();
      this.xpMinusButton = sub.create();
      this.xpImage = App.image("psd/xp-large", App.xpBlue);
      this.xpPlusButton = add.create();
      this.lootMinusButton = sub.create();
      this.lootImage = App.image("psd/loot-large", App.lootGold);
      this.lootPlusButton = add.create();
      this.levelButton = App.imageTextButton("").imageUp("psd/level", App.buttonGray).imageOver("psd/level").font("plainLargeOutlineFixedNumbers").create();
      this.levelButton.clearChildren();
      this.levelButton.add(this.levelButton.getLabel()).space(8.0F);
      this.levelButton.add(this.levelButton.getImage()).padBottom(4.0F);
      this.killButton = ((ImageButtonBuilder)((ImageButtonBuilder)App.imageButton()
               .imageUp("psd/skull", App.buttonGray)
               .imageOver("psd/skull")
               .imageChecked("psd/skull")
               .checked("selected"))
            .over("selected", App.buttonGray))
         .create();
      this.hpLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE);
      this.hpLabel.setTouchable(Touchable.disabled);
      this.xpLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE);
      this.xpLabel.setTouchable(Touchable.disabled);
      this.lootLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE);
      this.lootLabel.setTouchable(Touchable.disabled);
   }

   private void layoutUI() {
      Table buttons = new Table();
      buttons.columnDefaults(0).size(100.0F);
      buttons.columnDefaults(1).fill();
      buttons.columnDefaults(2).size(100.0F);
      buttons.add(this.hpMinusButton);
      buttons.add(new Stack(this.hpImage, new Container(this.hpLabel).pad(0.0F, 0.0F, -48.0F, -44.0F)));
      buttons.add(this.hpPlusButton).row();
      buttons.add(this.xpMinusButton);
      buttons.add(new Stack(this.xpImage, new Container(this.xpLabel).pad(0.0F, 0.0F, -48.0F, -44.0F)));
      buttons.add(this.xpPlusButton).row();
      buttons.add(this.lootMinusButton);
      buttons.add(new Stack(this.lootImage, new Container(this.lootLabel).pad(0.0F, 0.0F, -48.0F, -44.0F)));
      buttons.add(this.lootPlusButton).row();
      buttons.add(this.killButton).size(100.0F);
      buttons.add(this.levelButton).colspan(2).size(100.0F).left();
      this.conditionsTable = new Table();
      this.conditionsTable.defaults().size(100.0F);
      int rowLength = 4;
      int startCondition = 3;
      int i = 3;

      for (int n = Condition.values.length; i < n; i++) {
         Condition condition = Condition.values[i];
         if (condition != Condition.doom
            && condition != Condition.wound2
            && condition != Condition.poison2
            && condition != Condition.hatchet
            && condition != Condition.poison3
            && condition != Condition.poison4) {
            ImageButton button = ((ImageButtonBuilder)((ImageButtonBuilder)App.imageButton()
                     .imageUp("conditions/" + condition.name() + "-large")
                     .checked("selected"))
                  .over("selected", App.buttonGray))
               .create();
            button.setUserObject(condition);
            this.conditionButtons.put(condition, button);
            this.conditionsTable.add(button);
            if (condition == Condition.wound || condition == Condition.brittle || condition == Condition.rupture) {
               this.conditionsTable.row();
            }
         }
      }

      this.defaults().space(9.0F);
      this.add(buttons);
      this.add(this.conditionsTable);
   }

   private void events() {
      this.hpPlusButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (PlayerRowMenu.this.player.hp < PlayerRowMenu.this.player.hpMax) {
               PlayerRowMenu.this.player.hp++;
               PlayerRowMenu.this.row.hpChanged(1);
               int hp = PlayerRowMenu.this.hpLabel.getText().length == 0 ? 1 : App.parseInt(PlayerRowMenu.this.hpLabel.getText().toString()) + 1;
               PlayerRowMenu.this.hpLabel.setText(hp == 0 ? "" : (hp > 0 ? "+" : "") + hp);
               PlayerRowMenu.this.hpLabel.setColor(hp < 0 ? App.healthRed : App.healthGreen);
            }
         }
      });
      this.hpMinusButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (PlayerRowMenu.this.player.hp > 0) {
               PlayerRowMenu.this.player.hp--;
               PlayerRowMenu.this.row.hpChanged(-1);
               int hp = PlayerRowMenu.this.hpLabel.getText().length == 0 ? -1 : App.parseInt(PlayerRowMenu.this.hpLabel.getText().toString()) - 1;
               PlayerRowMenu.this.hpLabel.setText(hp == 0 ? "" : (hp > 0 ? "+" : "") + hp);
               PlayerRowMenu.this.hpLabel.setColor(hp < 0 ? App.healthRed : App.healthGreen);
            }
         }
      });
      this.xpPlusButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            PlayerRowMenu.this.player.xp++;
            int xp = PlayerRowMenu.this.xpLabel.getText().length == 0 ? 1 : App.parseInt(PlayerRowMenu.this.xpLabel.getText().toString()) + 1;
            PlayerRowMenu.this.xpLabel.setText(xp == 0 ? "" : (xp > 0 ? "+" : "") + xp);
         }
      });
      this.xpMinusButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (PlayerRowMenu.this.player.xp != 0) {
               PlayerRowMenu.this.player.xp--;
               int xp = PlayerRowMenu.this.xpLabel.getText().length == 0 ? -1 : App.parseInt(PlayerRowMenu.this.xpLabel.getText().toString()) - 1;
               PlayerRowMenu.this.xpLabel.setText(xp == 0 ? "" : (xp > 0 ? "+" : "") + xp);
            }
         }
      });
      this.lootPlusButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            PlayerRowMenu.this.player.loot++;
            int loot = PlayerRowMenu.this.lootLabel.getText().length == 0 ? 1 : App.parseInt(PlayerRowMenu.this.lootLabel.getText().toString()) + 1;
            PlayerRowMenu.this.lootLabel.setText(loot == 0 ? "" : (loot > 0 ? "+" : "") + loot);
         }
      });
      this.lootMinusButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (PlayerRowMenu.this.player.loot != 0) {
               PlayerRowMenu.this.player.loot--;
               int loot = PlayerRowMenu.this.lootLabel.getText().length == 0 ? -1 : App.parseInt(PlayerRowMenu.this.lootLabel.getText().toString()) - 1;
               PlayerRowMenu.this.lootLabel.setText(loot == 0 ? "" : (loot > 0 ? "+" : "") + loot);
            }
         }
      });
      this.conditionsTable.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            ImageButton button = (ImageButton)actor;
            Condition condition = (Condition)button.getUserObject();
            if (button.isChecked()) {
               if (!App.state.canDraw && PlayerRowMenu.this.row.isCurrentTurn()) {
                  PlayerRowMenu.this.player.currentTurnConditions.add(condition);
               }

               PlayerRowMenu.this.player.conditions.add(condition);
               PlayerRowMenu.this.player.conditions.sort();
               PlayerRowMenu.this.row.flashIcon(condition.name());
            } else {
               PlayerRowMenu.this.player.conditions.removeValue(condition, true);
               PlayerRowMenu.this.player.currentTurnConditions.removeValue(condition, true);
            }
         }
      });
      this.killButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            PlayerRowMenu.this.player.exhausted = PlayerRowMenu.this.killButton.isChecked();
            App.state.changed();
         }
      });
      this.levelButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            PlayerRowMenu.this.animate = false;
            PlayerRowMenu.this.hide();
            PlayerRowMenu.this.animate = true;
            final AtomicBoolean stateChanged = new AtomicBoolean();
            TextMenu menu = new TextMenu() {
               @Override
               public boolean hide() {
                  super.hide();
                  if (stateChanged.get()) {
                     App.state.changed();
                  }

                  return true;
               }
            };
            Table levelTable = new Table(App.skin);
            levelTable.pad(5.0F, 12.0F, 0.0F, 12.0F).defaults().size(100.0F);
            levelTable.add(App.image("psd/level"));
            ButtonGroup buttonGroup = new ButtonGroup();

            for (int i = 1; i <= 9; i++) {
               TextButton button = App.textButton(String.valueOf(i)).checkedFontColor(Color.WHITE).create();
               buttonGroup.add(button);
               final int level = i;
               button.addListener(new ChangeListener() {
                  @Override
                  public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                     PlayerRowMenu.this.row.setLevel(level);
                  }
               });
               if (i == PlayerRowMenu.this.player.level) {
                  button.setChecked(true);
               }

               levelTable.add(button);
               if (i == 4) {
                  levelTable.row();
               }
            }

            menu.table.add(levelTable).colspan(2).row();
            TextField nameText = App.textField(PlayerRowMenu.this.player.name);
            nameText.setTextFieldListener(new TextField.TextFieldListener() {
               @Override
               public void keyTyped(TextField textField, char c) {
                  String name = textField.getText().trim();
                  if (name.length() == 0) {
                     PlayerRowMenu.this.player.name = PlayerRowMenu.this.player.characterClass.name;
                  } else {
                     PlayerRowMenu.this.player.name = name;
                  }

                  App.state.changed();
               }
            });
            menu.table.add(nameText).growX().padLeft(18.0F).padRight(9.0F);
            ImageButtonBuilder sub = App.imageButton().imageUp("psd/sub", App.buttonGray).imageOver("psd/sub").imageDisabled("psd/sub", App.disabledGray);
            ImageButtonBuilder add = App.imageButton().imageUp("psd/add", App.buttonGray).imageOver("psd/add");
            ImageButton hpMinusButton = sub.create();
            Image hpImage = App.image("blood-large");
            ImageButton hpPlusButton = add.create();
            Label hpMaxLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE) {
               @Override
               public void draw(Batch batch, float parentAlpha) {
                  this.setText(PlayerRowMenu.this.player.hp + "/" + PlayerRowMenu.this.player.hpMax);
                  super.draw(batch, parentAlpha);
               }
            };
            hpMaxLabel.setAlignment(1);
            hpMaxLabel.setTouchable(Touchable.disabled);
            hpPlusButton.addListener(new ChangeListener() {
               @Override
               public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                  if (PlayerRowMenu.this.player.hp >= PlayerRowMenu.this.player.hpMax) {
                     PlayerRowMenu.this.player.hp = PlayerRowMenu.this.player.hpMax + 1;
                  }

                  PlayerRowMenu.this.player.hpMax++;
               }
            });
            hpMinusButton.addListener(new ChangeListener() {
               @Override
               public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                  if (PlayerRowMenu.this.player.hpMax > 1) {
                     if (PlayerRowMenu.this.player.hp <= PlayerRowMenu.this.player.hpMax) {
                        PlayerRowMenu.this.player.hp = PlayerRowMenu.this.player.hpMax - 1;
                     }

                     PlayerRowMenu.this.player.hpMax--;
                     stateChanged.set(true);
                  }
               }
            });
            (new DragAdjust(null, new Actor[]{hpPlusButton, hpImage, hpMinusButton}) {
               @Override
               protected int getValue() {
                  return PlayerRowMenu.this.player.hpMax;
               }

               @Override
               protected void setValue(int value) {
                  if (PlayerRowMenu.this.player.hp >= PlayerRowMenu.this.player.hpMax) {
                     PlayerRowMenu.this.player.hp = value;
                  }

                  PlayerRowMenu.this.player.hpMax = value;
                  stateChanged.set(true);
               }
            }).min = 1;
            Table buttons = new Table();
            buttons.add(hpMinusButton).size(100.0F);
            buttons.add(new Stack(hpImage, new Container(hpMaxLabel).bottom().width(0.0F))).fill();
            buttons.add(hpPlusButton).size(100.0F);
            menu.table.add(buttons);
            menu.animate = false;
            menu.show(PlayerRowMenu.this);
            menu.animate = true;
         }
      });
   }

   @Override
   public boolean show(Actor positionActor, float positionX, float positionY, float positionWidth, float positionHeight, boolean preferRight) {
      this.killButton.setChecked(this.player.exhausted);
      this.levelButton.setText(String.valueOf(this.player.level));
      this.hpLabel.setText("");
      this.xpLabel.setText("");
      this.lootLabel.setText("");

      for (ImageButton button : this.conditionButtons.values()) {
         button.setChecked(false);
      }

      for (Condition condition : this.player.conditions) {
         ((ImageButton)this.conditionButtons.get(condition)).setChecked(true);
      }

      return super.show(positionActor, positionX, positionY, positionWidth, positionHeight, preferRight);
   }

   @Override
   public boolean hide() {
      App.state.changed();
      return super.hide();
   }

   @Override
   public void act(float delta) {
      super.act(delta);
      this.xpMinusButton.setDisabled(this.player.xp <= 0);
      this.lootMinusButton.setDisabled(this.player.loot <= 0);
   }

   @Override
   protected void updatePosition() {
      this.row.nameLabel.localToStageCoordinates(App.v2.set(this.row.nameLabel.getPrefWidth(), Math.round(this.row.nameLabel.getHeight() / 2.0F)));
      float x = App.v2.x + 6.0F;
      float y = App.v2.y;
      this.row.localToStageCoordinates(App.v2.set(0.0F, 0.0F));
      this.setPosition(x, y, 0.0F, 0.0F, App.v2.x + 2.0F, App.v2.y, this.row.getWidth() - 3.0F, this.row.getHeight() + (this.row.boxes.size == 0 ? 0 : 6));
      super.updatePosition();
   }
}
