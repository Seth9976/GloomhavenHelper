package com.hm.gloomhavenhelper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.ObjectMap;
import com.hm.gloomhavenhelper.model.AttackModifier;
import com.hm.gloomhavenhelper.model.Condition;
import com.hm.gloomhavenhelper.model.Monster;
import com.hm.gloomhavenhelper.model.MonsterType;
import com.hm.gloomhavenhelper.util.DesatDrawable;
import com.hm.gloomhavenhelper.util.DragAdjust;
import com.hm.gloomhavenhelper.util.Menu;
import com.hm.gloomhavenhelper.util.TextMenu;
import com.hm.gloomhavenhelper.util.builders.ImageButtonBuilder;

public class MonsterBoxMenu extends Menu {
   final MonsterBox box;
   final Monster monster;
   private ImageButton hpPlusButton;
   private ImageButton hpMinusButton;
   private Image hpImage;
   private ImageButton blessPlusButton;
   Table left = new Table();
   private ImageButton blessMinusButton;
   private ImageButton cursePlusButton;
   private ImageButton curseMinusButton;
   ImageButton killButton;
   ImageTextButton levelButton;
   Label hpLabel;
   Label blessLabel;
   Label curseLabel;
   Label moveLabel;
   Label attackLabel;
   Label rangeLabel;
   private Image curseImage;
   Table conditionsTable;
   Table buttonsTable;
   ImageButton summonButton;
   boolean lastArrowFlip;

   public MonsterBoxMenu(MonsterBox box) {
      this.box = box;
      this.monster = box.monster;
      this.create();
      this.layoutUI();
      this.events();
      if (this.monster.type == MonsterType.summon) {
         this.setBackgroundOffset(-1.0F, 7.0F, 7.0F, 6.0F);
      } else {
         this.setBackgroundOffset(-1.0F, -4.0F, 7.0F, -6.0F);
      }
   }

   private void create() {
      ImageButtonBuilder sub = App.imageButton().imageUp("psd/sub", App.buttonGray).imageOver("psd/sub").imageDisabled("psd/sub", App.disabledGray);
      ImageButtonBuilder add = App.imageButton().imageUp("psd/add", App.buttonGray).imageOver("psd/add").imageDisabled("psd/add", App.disabledGray);
      this.hpMinusButton = sub.create();
      this.hpImage = App.image("blood-large");
      this.hpPlusButton = add.create();
      this.blessMinusButton = sub.create();
      this.blessPlusButton = add.create();
      this.curseMinusButton = sub.create();
      this.cursePlusButton = add.create();
      this.hpLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE);
      this.hpLabel.setTouchable(Touchable.disabled);
      this.blessLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE);
      this.blessLabel.setTouchable(Touchable.disabled);
      this.curseLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE);
      this.curseLabel.setTouchable(Touchable.disabled);
      if (this.monster.stats.immuneCurse) {
         this.curseMinusButton.setDisabled(true);
         this.cursePlusButton.setDisabled(true);
         this.curseImage = App.image(new DesatDrawable(App.drawable("curse-large")));
         this.curseImage.setColor(App.disabledDim);
         this.curseLabel.setColor(App.lightGray);
      } else {
         this.curseImage = App.image("curse-large");
      }

      this.buttonsTable = new Table();
      this.conditionsTable = new Table();
      this.summonButton = ((ImageButtonBuilder)((ImageButtonBuilder)App.imageButton().imageUp("conditions/summon-large").checked("selected"))
            .over("selected", App.buttonGray))
         .create();
      this.summonButton.setUserObject(Condition.summon);
      this.killButton = App.imageButton().imageUp("psd/skull", App.buttonGray).imageOver("psd/skull", Color.WHITE).create();
      this.levelButton = App.imageTextButton("").imageUp("psd/level", App.buttonGray).imageOver("psd/level").font("plainLargeOutlineFixedNumbers").create();
      this.levelButton.clearChildren();
      this.levelButton.add(this.levelButton.getLabel()).space(8.0F);
      this.levelButton.add(this.levelButton.getImage()).padBottom(4.0F);
   }

   private void layoutUI() {
      this.buttonsTable.columnDefaults(0).size(100.0F);
      this.buttonsTable.columnDefaults(1).fill();
      this.buttonsTable.columnDefaults(2).size(100.0F);
      this.buttonsTable.add(this.hpMinusButton);
      this.buttonsTable.add(new Stack(this.hpImage, new Container(this.hpLabel).pad(0.0F, 0.0F, -48.0F, -44.0F)));
      this.buttonsTable.add(this.hpPlusButton).row();
      if (this.monster.type != MonsterType.summon) {
         this.buttonsTable.add(this.blessMinusButton);
         this.buttonsTable.add(new Stack(App.image("bless-large"), new Container(this.blessLabel).pad(0.0F, 0.0F, -48.0F, -44.0F)));
         this.buttonsTable.add(this.blessPlusButton).row();
         this.buttonsTable.add(this.curseMinusButton);
         this.buttonsTable.add(new Stack(this.curseImage, new Container(this.curseLabel).pad(0.0F, 0.0F, -48.0F, -44.0F)));
         this.buttonsTable.add(this.cursePlusButton).row();
      } else {
         Table stats = new Table();
         stats.defaults().spaceTop(18.0F).spaceRight(9.0F);
         stats.add(App.image("abilities/move"));
         stats.add(this.moveLabel = new Label("", App.skin, "plainLargeOutline", Color.WHITE)).row();
         stats.add(App.image("abilities/attack"));
         stats.add(this.attackLabel = new Label("", App.skin, "plainLargeOutline", Color.WHITE)).row();
         stats.add(App.image("abilities/range"));
         stats.add(this.rangeLabel = new Label("", App.skin, "plainLargeOutline", Color.WHITE)).row();
         this.buttonsTable.add(stats).colspan(3).growY().row();
      }

      this.left.add(this.buttonsTable).colspan(3).growY().top().row();
      this.left.add(this.killButton).size(100.0F);
      this.left.add(this.levelButton).size(100.0F);
      this.conditionsTable.defaults().size(100.0F);
      this.add(this.left).growY();
      this.add(this.conditionsTable);
   }

   private void events() {
      this.hpPlusButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (MonsterBoxMenu.this.monster.hp < MonsterBoxMenu.this.monster.hpMax) {
               MonsterBoxMenu.this.monster.hp++;
               MonsterBoxMenu.this.box.hpChanged(1);
               int hp = MonsterBoxMenu.this.hpLabel.getText().length == 0 ? 1 : App.parseInt(MonsterBoxMenu.this.hpLabel.getText().toString()) + 1;
               MonsterBoxMenu.this.hpLabel.setText(hp == 0 ? "" : (hp > 0 ? "+" : "") + hp);
               MonsterBoxMenu.this.hpLabel.setColor(hp < 0 ? App.healthRed : App.healthGreen);
            }
         }
      });
      this.hpMinusButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (MonsterBoxMenu.this.monster.hp > 0) {
               MonsterBoxMenu.this.monster.hp--;
               MonsterBoxMenu.this.box.hpChanged(-1);
               int hp = MonsterBoxMenu.this.hpLabel.getText().length == 0 ? -1 : App.parseInt(MonsterBoxMenu.this.hpLabel.getText().toString()) - 1;
               MonsterBoxMenu.this.hpLabel.setText(hp == 0 ? "" : (hp > 0 ? "+" : "") + hp);
               MonsterBoxMenu.this.hpLabel.setColor(hp < 0 ? App.healthRed : App.healthGreen);
            }
         }
      });
      this.blessPlusButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (App.state.count(AttackModifier.bless, false) < 10) {
               int bless = App.parseInt(MonsterBoxMenu.this.blessLabel.getText().toString()) + 1;
               MonsterBoxMenu.this.blessLabel.setText(bless);
               App.state.add(AttackModifier.bless);
               App.state.attackModifiers.shuffle();
            }
         }
      });
      this.blessMinusButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (App.state.count(AttackModifier.bless, false) != 0) {
               int bless = Math.max(0, App.parseInt(MonsterBoxMenu.this.blessLabel.getText().toString()) - 1);
               MonsterBoxMenu.this.blessLabel.setText(bless);
               App.state.remove(AttackModifier.bless);
               App.state.attackModifiers.shuffle();
            }
         }
      });
      this.cursePlusButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (App.state.count(AttackModifier.curse, false) < 10) {
               int curse = App.parseInt(MonsterBoxMenu.this.curseLabel.getText().toString()) + 1;
               MonsterBoxMenu.this.curseLabel.setText(curse);
               App.state.addCurse();
            }
         }
      });
      this.curseMinusButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (App.state.count(AttackModifier.curse, false) != 0) {
               int curse = Math.max(0, App.parseInt(MonsterBoxMenu.this.curseLabel.getText().toString()) - 1);
               MonsterBoxMenu.this.curseLabel.setText(curse);
               App.state.remove(AttackModifier.curse);
               App.state.attackModifiers.shuffle();
            }
         }
      });
      this.summonButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (MonsterBoxMenu.this.summonButton.isChecked()) {
               MonsterBoxMenu.this.monster.conditions.add(Condition.summonNew);
               MonsterBoxMenu.this.monster.conditions.sort();
            } else {
               MonsterBoxMenu.this.monster.conditions.removeValue(Condition.summon, true);
               if (MonsterBoxMenu.this.monster.conditions.removeValue(Condition.summonNew, true)) {
                  MonsterBoxMenu.this.monster.conditions.add(Condition.summon);
                  MonsterBoxMenu.this.monster.conditions.sort();
                  MonsterBoxMenu.this.summonButton.setChecked(true);
               }
            }
         }
      });
      this.conditionsTable.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            ImageButton button = (ImageButton)actor;
            if (button != MonsterBoxMenu.this.summonButton) {
               Condition condition = (Condition)button.getUserObject();
               if (button.isChecked()) {
                  if (!App.state.canDraw && MonsterBoxMenu.this.box.row.isCurrentTurn()) {
                     MonsterBoxMenu.this.monster.currentTurnConditions.add(condition);
                  }

                  MonsterBoxMenu.this.monster.conditions.add(condition);
                  MonsterBoxMenu.this.monster.conditions.sort();
                  MonsterBoxMenu.this.box.flashIcon(condition.name());
               } else {
                  MonsterBoxMenu.this.monster.conditions.removeValue(condition, true);
                  MonsterBoxMenu.this.monster.currentTurnConditions.removeValue(condition, true);
               }
            }
         }
      });
      this.killButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            MonsterBoxMenu.this.animate = false;
            MonsterBoxMenu.this.hide();
            MonsterBoxMenu.this.animate = true;
            MonsterBoxMenu.this.box.removeMonster(true);
            App.state.changed();
         }
      });
      this.levelButton
         .addListener(
            new ChangeListener() {
               @Override
               public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                  MonsterBoxMenu.this.animate = false;
                  MonsterBoxMenu.this.hide();
                  MonsterBoxMenu.this.animate = true;
                  TextMenu menu = new TextMenu() {
                     @Override
                     public boolean hide() {
                        boolean hidden = super.hide();
                        if (hidden) {
                           App.state.changed();
                        }

                        return hidden;
                     }
                  };
                  final ImageButton starButton = ((ImageButtonBuilder)((ImageButtonBuilder)App.imageButton()
                           .imageUp("conditions/star-large")
                           .checked("selected"))
                        .over("selected", App.buttonGray))
                     .create();
                  starButton.setChecked(MonsterBoxMenu.this.monster.conditions.contains(Condition.star, true));
                  starButton.addListener(new ChangeListener() {
                     @Override
                     public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                        boolean checked = starButton.isChecked();
                        if (checked) {
                           MonsterBoxMenu.this.monster.conditions.add(Condition.star);
                           MonsterBoxMenu.this.monster.conditions.sort();
                           MonsterBoxMenu.this.box.flashIcon("star");
                           MonsterBoxMenu.this.box.row.animateIcon(MonsterBoxMenu.this.box, "conditions/star-large", 1.6F, 54.0F, 40.0F);
                        } else {
                           MonsterBoxMenu.this.monster.conditions.removeValue(Condition.star, true);
                        }

                        int scenario = App.state.scenarioNumber;
                        String name = MonsterBoxMenu.this.monster.data.english;
                        boolean elite = MonsterBoxMenu.this.monster.type == MonsterType.elite;
                        int hpTimesPlayers = 0;
                        int i = hpTimesPlayers | (scenario == 62 && name.equals("Living Bones") && elite ? 1 : 0);
                        i |= scenario == 87 && name.equals("Ooze") && elite ? 1 : 0;
                        int playerCount = App.gloom.playerCount();
                        if (i != 0 && playerCount > 0) {
                           MonsterBoxMenu.this.monster.hpMax = MonsterBoxMenu.this.monster.stats.hpMax();
                           if (checked) {
                              MonsterBoxMenu.this.monster.hpMax *= playerCount;
                           }

                           MonsterBoxMenu.this.monster.hp = MonsterBoxMenu.this.monster.hpMax;
                        }
                     }
                  });
                  starButton.addListener(
                     App.tooltip(
                        "Special monsters can be marked with a star to denote that they are not affected by character abilities that target only normal or elite monsters."
                     )
                  );
                  if (MonsterBoxMenu.this.monster.type != MonsterType.summon) {
                     Table levelTable = new Table(App.skin);
                     levelTable.pad(5.0F, 12.0F, 0.0F, 12.0F).defaults().size(100.0F);
                     levelTable.add(App.image("psd/level"));
                     ButtonGroup buttonGroup = new ButtonGroup();

                     for (int i = 0; i <= 7; i++) {
                        TextButton button = App.textButton(String.valueOf(i)).checkedFontColor(Color.WHITE).create();
                        buttonGroup.add(button);
                        final int level = i;
                        button.addListener(new ChangeListener() {
                           @Override
                           public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                              MonsterBoxMenu.this.box.row.setLevel(level);
                           }
                        });
                        if (i == MonsterBoxMenu.this.box.row.getLevel()) {
                           button.setChecked(true);
                        }

                        if (App.state.scenarioNumber >= 0 && i == App.state.scenarioLevel) {
                           button.getStyle().up = App.drawable("selected", App.disabledGray);
                        }

                        levelTable.add(button);
                        if (i == 3) {
                           levelTable.row();
                        }
                     }

                     levelTable.add(starButton);
                     menu.table.add(levelTable).row();
                  }

                  ImageButtonBuilder sub = App.imageButton().imageUp("psd/sub", App.buttonGray).imageOver("psd/sub").imageDisabled("psd/sub", App.disabledGray);
                  ImageButtonBuilder add = App.imageButton().imageUp("psd/add", App.buttonGray).imageOver("psd/add");
                  ImageButton hpMinusButton = sub.create();
                  Image hpImage = App.image("blood-large");
                  ImageButton hpPlusButton = add.create();
                  Label hpMaxLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE) {
                     @Override
                     public void draw(Batch batch, float parentAlpha) {
                        this.setText(MonsterBoxMenu.this.monster.hp + "/" + MonsterBoxMenu.this.monster.hpMax);
                        super.draw(batch, parentAlpha);
                     }
                  };
                  hpMaxLabel.setAlignment(1);
                  hpMaxLabel.setTouchable(Touchable.disabled);
                  hpPlusButton.addListener(new ChangeListener() {
                     @Override
                     public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                        if (MonsterBoxMenu.this.monster.hp >= MonsterBoxMenu.this.monster.hpMax) {
                           MonsterBoxMenu.this.monster.hp = MonsterBoxMenu.this.monster.hpMax + 1;
                        }

                        MonsterBoxMenu.this.monster.hpMax++;
                     }
                  });
                  hpMinusButton.addListener(new ChangeListener() {
                     @Override
                     public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                        if (MonsterBoxMenu.this.monster.hpMax > 1) {
                           if (MonsterBoxMenu.this.monster.hp <= MonsterBoxMenu.this.monster.hpMax) {
                              MonsterBoxMenu.this.monster.hp = MonsterBoxMenu.this.monster.hpMax - 1;
                           }

                           MonsterBoxMenu.this.monster.hpMax--;
                        }
                     }
                  });
                  (new DragAdjust(null, hpPlusButton, hpImage, hpMinusButton) {
                     @Override
                     protected int getValue() {
                        return MonsterBoxMenu.this.monster.hpMax;
                     }

                     @Override
                     protected void setValue(int value) {
                        if (MonsterBoxMenu.this.monster.hp >= MonsterBoxMenu.this.monster.hpMax) {
                           MonsterBoxMenu.this.monster.hp = value;
                        }

                        MonsterBoxMenu.this.monster.hpMax = value;
                     }
                  }).min = 1;
                  Table buttons = new Table();
                  buttons.add(hpMinusButton).size(100.0F);
                  buttons.add(new Stack(hpImage, new Container(hpMaxLabel).bottom().width(0.0F))).fill();
                  buttons.add(hpPlusButton).size(100.0F).row();
                  menu.table.add(buttons);
                  if (MonsterBoxMenu.this.monster.type == MonsterType.summon) {
                     ImageButton moveMinusButton = sub.create();
                     Image moveImage = App.image("abilities/move");
                     ImageButton movePlusButton = add.create();
                     Label moveLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE) {
                        @Override
                        public void draw(Batch batch, float parentAlpha) {
                           if (MonsterBoxMenu.this.monster.summonMove == 0) {
                              this.setText("-");
                           } else {
                              this.setText(MonsterBoxMenu.this.monster.summonMove);
                           }

                           super.draw(batch, parentAlpha);
                        }
                     };
                     moveLabel.setAlignment(1);
                     moveLabel.setTouchable(Touchable.disabled);
                     movePlusButton.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                           MonsterBoxMenu.this.monster.summonMove++;
                        }
                     });
                     moveMinusButton.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                           if (MonsterBoxMenu.this.monster.summonMove > 0) {
                              MonsterBoxMenu.this.monster.summonMove--;
                           }
                        }
                     });
                     ImageButton attackMinusButton = sub.create();
                     Image attackImage = App.image("abilities/attack");
                     ImageButton attackPlusButton = add.create();
                     Label attackLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE) {
                        @Override
                        public void draw(Batch batch, float parentAlpha) {
                           if (MonsterBoxMenu.this.monster.summonAttack == 0) {
                              this.setText("-");
                           } else {
                              this.setText(MonsterBoxMenu.this.monster.summonAttack);
                           }

                           super.draw(batch, parentAlpha);
                        }
                     };
                     attackLabel.setAlignment(1);
                     attackLabel.setTouchable(Touchable.disabled);
                     attackPlusButton.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                           MonsterBoxMenu.this.monster.summonAttack++;
                        }
                     });
                     attackMinusButton.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                           if (MonsterBoxMenu.this.monster.summonAttack > 0) {
                              MonsterBoxMenu.this.monster.summonAttack--;
                           }
                        }
                     });
                     ImageButton rangeMinusButton = sub.create();
                     Image rangeImage = App.image("abilities/range");
                     ImageButton rangePlusButton = add.create();
                     Label rangeLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE) {
                        @Override
                        public void draw(Batch batch, float parentAlpha) {
                           if (MonsterBoxMenu.this.monster.summonRange == 0) {
                              this.setText("-");
                           } else {
                              this.setText(MonsterBoxMenu.this.monster.summonRange);
                           }

                           super.draw(batch, parentAlpha);
                        }
                     };
                     rangeLabel.setAlignment(1);
                     rangeLabel.setTouchable(Touchable.disabled);
                     rangePlusButton.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                           MonsterBoxMenu.this.monster.summonRange++;
                        }
                     });
                     rangeMinusButton.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                           if (MonsterBoxMenu.this.monster.summonRange > 0) {
                              MonsterBoxMenu.this.monster.summonRange--;
                           }
                        }
                     });
                     buttons.add(moveMinusButton).size(100.0F);
                     buttons.add(new Stack(moveImage, new Container(moveLabel).bottom().width(0.0F))).fill();
                     buttons.add(movePlusButton).size(100.0F).row();
                     buttons.add(attackMinusButton).size(100.0F);
                     buttons.add(new Stack(attackImage, new Container(attackLabel).bottom().width(0.0F))).fill();
                     buttons.add(attackPlusButton).size(100.0F).row();
                     buttons.add(rangeMinusButton).size(100.0F);
                     buttons.add(new Stack(rangeImage, new Container(rangeLabel).bottom().width(0.0F))).fill();
                     buttons.add(rangePlusButton).size(100.0F).row();
                  }

                  menu.setBackgroundOffset(-1.0F, -2.0F, 7.0F, 3.0F);
                  menu.animate = false;
                  menu.show(MonsterBoxMenu.this);
                  menu.animate = true;
               }
            }
         );
   }

   private ImageButton newConditionButton(Condition condition, ObjectMap buttons) {
      Drawable drawable = App.drawable("conditions/" + condition.name() + "-large");
      ImageButton button = ((ImageButtonBuilder)((ImageButtonBuilder)((ImageButtonBuilder)App.imageButton().imageUp(drawable).checked("selected"))
               .over("selected", App.buttonGray))
            .disabled("white", Color.CLEAR))
         .imageDisabled(new DesatDrawable(drawable))
         .create();
      button.setUserObject(condition);
      buttons.put(condition, button);
      return button;
   }

   @Override
   public boolean show(Actor positionActor, float positionX, float positionY, float positionWidth, float positionHeight, boolean preferRight) {
      this.hpLabel.setText("");
      this.blessLabel.setText(String.valueOf(App.state.count(AttackModifier.bless, false)));
      this.curseLabel.setText(String.valueOf(App.state.count(AttackModifier.curse, false)));
      this.levelButton.setText(String.valueOf(this.box.row.getLevel()));
      this.conditionsTable.clearChildren();
      ObjectMap buttons = new ObjectMap();
      int startCondition = 3;
      int i = startCondition;

      for (int n = Condition.values.length; i < n; i++) {
         Condition condition = Condition.values[i];
         if (condition != Condition.doom
            && condition != Condition.hatchet
            && condition != Condition.rupture
            && condition != Condition.wound2
            && condition != Condition.poison2
            && condition != Condition.poison3
            && condition != Condition.poison4
            && condition != Condition.impair
            && condition != Condition.chill
            && condition != Condition.infect) {
            ImageButton button = this.newConditionButton(condition, buttons);
            this.conditionsTable.add(button);
            if (condition == Condition.wound || condition == Condition.brittle) {
               this.conditionsTable.row();
            }

            if (condition == Condition.brittle) {
               Table mireFootConditionTable = new Table();
               mireFootConditionTable.defaults().size(100.0F);
               Table table = new Table();
               table.defaults().size(100.0F);
               if (this.monster.type != MonsterType.summon) {
                  table.add(this.summonButton);
                  if (App.gloom.hasCharacterClass("Doomstalker")) {
                     table.add(this.newConditionButton(Condition.doom, buttons));
                  }

                  if (App.gloom.hasCharacterClass("Hatchet")) {
                     table.add(this.newConditionButton(Condition.hatchet, buttons));
                  }

                  table.add(this.newConditionButton(Condition.rupture, buttons));
                  if (App.gloom.hasCharacterClass("Mirefoot")) {
                     Button conditionButton1 = this.newConditionButton(Condition.wound2, buttons);
                     Button conditionButton2 = this.newConditionButton(Condition.poison2, buttons);
                     Button conditionButton3 = this.newConditionButton(Condition.poison3, buttons);
                     Button conditionButton4 = this.newConditionButton(Condition.poison4, buttons);
                     mireFootConditionTable.add(conditionButton1);
                     mireFootConditionTable.add(conditionButton2);
                     mireFootConditionTable.add(conditionButton3);
                     mireFootConditionTable.add(conditionButton4);
                     if (this.monster.stats.immunities.contains(Condition.wound)) {
                        conditionButton1.setDisabled(true);
                        conditionButton1.setColor(App.disabledDim);
                     }

                     if (this.monster.stats.immunities.contains(Condition.poison)) {
                        conditionButton2.setDisabled(true);
                        conditionButton2.setColor(App.disabledDim);
                        conditionButton3.setDisabled(true);
                        conditionButton3.setColor(App.disabledDim);
                        conditionButton4.setDisabled(true);
                        conditionButton4.setColor(App.disabledDim);
                     }
                  } else {
                     table.add(this.newConditionButton(Condition.poison2, buttons));
                  }
               }

               boolean emptyRow = true;
               if (table.hasChildren()) {
                  this.conditionsTable.add(table).colspan(4).row();
                  emptyRow = false;
               }

               if (mireFootConditionTable.hasChildren()) {
                  this.conditionsTable.add(mireFootConditionTable).colspan(4).row();
                  emptyRow = false;
               }

               if (emptyRow) {
                  this.conditionsTable.add().height(100.0F).row();
               }
            }

            if (this.monster.stats.immunities.contains(condition)) {
               button.setDisabled(true);
               button.setColor(App.disabledDim);
            }

            if (this.monster.stats.immunities.contains(Condition.wound) && condition == Condition.rupture) {
               button.setDisabled(true);
               button.setColor(App.disabledDim);
            }
         }
      }

      if (this.monster.type == MonsterType.summon) {
         this.moveLabel.setText(this.monster.summonMove == 0 ? "-" : Integer.toString(this.monster.summonMove));
         this.attackLabel.setText(this.monster.summonAttack == 0 ? "-" : Integer.toString(this.monster.summonAttack));
         this.rangeLabel.setText(this.monster.summonRange == 0 ? "-" : Integer.toString(this.monster.summonRange));
      }

      for (ImageButton buttonx : buttons.values()) {
         buttonx.setChecked(false);
      }

      this.summonButton.setChecked(false);

      for (Condition condition : this.monster.conditions) {
         if (condition != Condition.summon && condition != Condition.summonNew && condition != Condition.star) {
            ImageButton buttonx = (ImageButton)buttons.get(condition);
            if (buttonx != null) {
               buttonx.setChecked(true);
            }
         } else {
            this.summonButton.setChecked(true);
         }
      }

      return super.show(positionActor, positionX, positionY, positionWidth, positionHeight, preferRight);
   }

   @Override
   public boolean hide() {
      super.hide();
      this.box.checkDead(true);
      App.state.changed();
      return true;
   }

   @Override
   public void act(float delta) {
      super.act(delta);
      this.hpMinusButton.setDisabled(this.monster.hp <= 0);
      int blesses = App.state.count(AttackModifier.bless, false);
      this.blessMinusButton.setDisabled(blesses <= 0);
      this.blessPlusButton.setDisabled(blesses >= 10);
      int curses = App.state.count(AttackModifier.curse, false);
      this.curseMinusButton.setDisabled(curses <= 0 || this.monster.stats.immuneCurse);
      this.cursePlusButton.setDisabled(curses >= 10 || this.monster.stats.immuneCurse);
   }

   @Override
   protected void updatePosition() {
      super.updatePosition();
      if (this.lastArrowFlip != this.arrowFlip) {
         this.lastArrowFlip = this.arrowFlip;
         this.clearChildren();
         this.left.clearChildren();
         this.left.add(this.buttonsTable).colspan(3).growY().top().row();
         if (this.arrowFlip) {
            if (this.monster.type != MonsterType.summon) {
               this.left.add(this.summonButton).size(100.0F);
            }

            this.left.add(this.levelButton).size(100.0F);
            this.left.add(this.killButton).size(100.0F);
            this.add(this.conditionsTable);
            this.add(this.left).fill().growY();
         } else {
            this.left.add(this.killButton).size(100.0F);
            this.left.add(this.levelButton).size(100.0F);
            if (this.monster.type != MonsterType.summon) {
               this.left.add(this.summonButton).size(100.0F);
            }

            this.add(this.left).fill().growY();
            this.add(this.conditionsTable);
         }

         this.buttonsTable.invalidate();
      }
   }
}
