package com.hm.gloomhavenhelper;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.SnapshotArray;
import com.hm.gloomhavenhelper.model.Condition;
import com.hm.gloomhavenhelper.model.Monster;
import com.hm.gloomhavenhelper.model.MonsterAbility;
import com.hm.gloomhavenhelper.model.MonsterAbilityDeck;
import com.hm.gloomhavenhelper.model.MonsterData;
import com.hm.gloomhavenhelper.model.MonsterType;
import com.hm.gloomhavenhelper.model.SummonColor;
import com.hm.gloomhavenhelper.util.Animator;
import com.hm.gloomhavenhelper.util.Row;
import com.hm.gloomhavenhelper.util.builders.ButtonBuilder;
import com.hm.gloomhavenhelper.util.builders.ImageButtonBuilder;
import com.hm.minlog.Log;

public class MonsterRow extends Row {
   String levelString = "";
   Image image;
   private Label nameLabel;
   Stack imageStack;
   Stack abilityCardStack;
   float glowAlpha;
   MonsterAbilityCard abilityCard;
   MonsterAbilityCard.Ability3D ability1;
   MonsterAbilityCard.Ability3D ability2;
   private MonsterStatsCard statsTable;
   Button addNormalButton;
   Button addEliteButton;
   TextButton oozeSplitButton;
   Container oozeSplitContainer;
   boolean oozeSplit;
   public MonsterData data;
   public int level;
   public boolean hasNormal;
   public boolean hasElite;
   public MonsterAbility ability;

   public MonsterRow(MonsterData data, int level) {
      if (data == null) {
         throw new IllegalArgumentException("data cannot be null.");
      } else {
         this.data = data;
         if (level > 7) {
            level = 7;
         }

         this.level = level;
         this.levelString = String.valueOf(level);
         this.create();
         this.layoutUI();
         this.events();
         this.setTransform(true);
      }
   }

   private void create() {
      this.image = new Image() {
         final TextureRegion glowRegion = App.skin.getRegion("monsters/monster-glow");

         @Override
         public void draw(Batch batch, float a) {
            super.draw(batch, a);
            if (MonsterRow.this.glowAlpha != 0.0F) {
               batch.setColor(1.0F, 1.0F, 1.0F, MonsterRow.this.glowAlpha * a);
               batch.draw(this.glowRegion, this.getX() - 16.0F, this.getY() - 13.0F);
            }
         }
      };

      try {
         label35: {
            String name = this.data.gfx.replaceAll(" \\([^)]+\\)", "");
            if (name.startsWith("Common ")) {
               name = name.substring(7);
            } else if (name.startsWith("Basic ")) {
               name = name.substring(6);
            }

            if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
               FileHandle file = new FileHandle("monsters/" + name + ".png");
               if (file.exists()) {
                  try {
                     this.image.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(file))));
                     break label35;
                  } catch (RuntimeException var4) {
                     Log.error("ghh", "Unable to read image: " + file, var4);
                  }
               }
            }

            this.image.setDrawable(App.skin.getDrawable("monsters/" + name));
         }
      } catch (RuntimeException var5) {
      }

      (this.nameLabel = new Label(this.data.display, App.skin, "fancyLargeOutline", Color.WHITE)).setWrap(true);
      this.nameLabel.setAlignment(1);
      this.imageStack = new Stack() {
         @Override
         public void draw(Batch batch, float parentAlpha) {
            MonsterRow.this.beginDesat(batch);
            super.draw(batch, parentAlpha);
         }
      };
      ButtonBuilder addButton = App.imageButton()
         .imageUp("psd/add", App.buttonGray)
         .imageOver("psd/add")
         .imageDisabled("psd/add", App.disabledGray)
         .imageChecked("psd/add", Color.WHITE);
      this.addNormalButton = addButton.create();
      this.addEliteButton = addButton.create();
      this.abilityCard = new MonsterAbilityCard(this, true);
      this.abilityCardStack = new Stack(this.abilityCard);
      (this.oozeSplitButton = App.textButton(Text.split).create())
         .addListener(App.tooltip("Applies wound if needed, then if not stunned, applies 2 damage and spawns new Oozes."));
      this.oozeSplitButton.getLabelCell().padRight(10.0F).padTop(2.0F);
      this.oozeSplitContainer = new Container(this.oozeSplitButton).top().right();
      this.ability1 = new MonsterAbilityCard.Ability3D(this.data.deckID, this.abilityCardStack);
      this.ability2 = new MonsterAbilityCard.Ability3D(this.data.deckID, this.abilityCardStack);
      this.statsTable = new MonsterStatsCard(this);
   }

   private void layoutUI() {
      this.statsTable.defaults().bottom();
      this.statsTable.add(this.addNormalButton).size(102.0F).expand().left();
      if (!this.data.isBoss()) {
         this.statsTable.add(this.addEliteButton).size(102.0F);
      }

      this.addNormalButton.padRight(23.0F).padTop(22.0F);
      this.addEliteButton.padLeft(23.0F).padTop(22.0F);
      this.imageStack.add(new Container(this.image).size(207.0F, 209.0F).top());
      Container nameContainer = new Container(this.nameLabel).bottom().fillX();
      if (App.config.isRussian() || this.data.english.equals("Manifestation of Corruption")) {
         nameContainer.padLeft(-10.0F).padRight(-10.0F);
      }

      this.imageStack.add(nameContainer);
      this.row().padBottom(-4.0F);
      this.add(this.imageStack).fill();
      this.add(this.abilityCardStack).padLeft(6.0F);
      this.add(this.statsTable).padLeft(6.0F).row();
      this.add();
      this.monstersCell = this.add(this.monstersGroup).colspan(2).pad(0.0F, 5.0F, 0.0F, 5.0F).fillX();
      this.abilityCardStack.toFront();
      this.monstersGroup.toBack();
   }

   private void events() {
      this.dragStartActor = this.imageStack;
      this.imageStack.addListener(this.dragListener);
      this.imageStack.addListener(new ClickListener() {
         @Override
         public void touchDragged(InputEvent event, float x, float y, int pointer) {
            super.touchDragged(event, x, y, pointer);
            if (MonsterRow.this.dragListener.isDragging()) {
               this.cancel();
            }
         }

         @Override
         public void clicked(InputEvent event, float x, float y) {
            if (MonsterRow.this.isAlive()) {
               if (!App.state.canDraw) {
                  if (MonsterRow.this.turnComplete) {
                     MonsterRow.this.turnEndRevert();
                  } else {
                     MonsterRow.this.turnEnd(false);
                  }

                  App.state.changed();
               }
            }
         }
      });
      this.addNormalButton.addListener(new InputListener() {
         @Override
         public boolean handle(Event e) {
            e.stop();
            return super.handle(e);
         }
      });
      this.addNormalButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (!App.state.trackStandees) {
               MonsterRow.this.hasNormal = !MonsterRow.this.hasNormal;
               if (!App.state.canDraw && MonsterRow.this.getAbilityDeck().shownAbility == null) {
                  MonsterRow.this.showAbility();
               }

               App.state.changed();
            } else {
               MonsterRow.this.addNormalButton.setChecked(false);
               IntArray free = MonsterRow.this.freeNumbers();
               if (free.size == 1) {
                  MonsterRow.this.addMonsterBox(free.first(), MonsterRow.this.data, MonsterRow.this.level, false, false, SummonColor.blue, true);
                  App.state.changed();
               } else if (App.state.randomStandees) {
                  MonsterRow.this.addMonsterBox(free.random(), MonsterRow.this.data, MonsterRow.this.level, false, false, SummonColor.blue, true);
                  App.state.changed();
               } else {
                  MonsterAddMenu menu = new MonsterAddMenu(MonsterRow.this, free, false);
                  menu.setBackgroundOffset(2.0F, 5.0F, -24.0F, -27.0F);
                  menu.show(MonsterRow.this.addNormalButton, 19.0F, -22.0F, -41.0F, 0.0F, true);
               }
            }
         }
      });
      this.addEliteButton.addListener(new InputListener() {
         @Override
         public boolean handle(Event e) {
            e.stop();
            return super.handle(e);
         }
      });
      this.addEliteButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (!App.state.trackStandees) {
               MonsterRow.this.hasElite = !MonsterRow.this.hasElite;
               if (!App.state.canDraw && MonsterRow.this.getAbilityDeck().shownAbility == null) {
                  MonsterRow.this.showAbility();
               }

               App.state.changed();
            } else {
               MonsterRow.this.addEliteButton.setChecked(false);
               IntArray free = MonsterRow.this.freeNumbers();
               if (free.size == 1) {
                  MonsterRow.this.addMonsterBox(free.first(), MonsterRow.this.data, MonsterRow.this.level, true, false, SummonColor.blue, true);
                  App.state.changed();
               } else if (App.state.randomStandees) {
                  MonsterRow.this.addMonsterBox(free.random(), MonsterRow.this.data, MonsterRow.this.level, true, false, SummonColor.blue, true);
                  App.state.changed();
               } else {
                  MonsterAddMenu menu = new MonsterAddMenu(MonsterRow.this, free, true);
                  menu.setBackgroundOffset(24.0F, 5.0F, -2.0F, -27.0F);
                  menu.show(MonsterRow.this.addEliteButton, 41.0F, -22.0F, -41.0F, 0.0F, false);
               }
            }
         }
      });
      this.statsTable.setTouchable(Touchable.enabled);
      this.statsTable.addListener(new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            if (!App.state.trackStandees) {
               new MonsterBlessCurseMenu(MonsterRow.this).show(MonsterRow.this.image, 0.0F, 0.0F, 0.0F, 0.0F, true);
            }
         }
      });
      this.oozeSplitButton
         .addListener(
            new ChangeListener() {
               @Override
               public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                  MonsterRow.this.oozeSplit = true;
                  App.state.ignoreChanges = true;
                  Array boxes = new Array(MonsterRow.this.boxes);
                  boolean elitesFirstOld = App.state.elitesFirst;
                  App.state.elitesFirst = true;
                  boxes.sort();
                  App.state.elitesFirst = elitesFirstOld;

                  for (MonsterBox box : boxes) {
                     boolean wound = box.monster.conditions.contains(Condition.wound, true);
                     boolean regenerate = box.monster.conditions.contains(Condition.regenerate, true);
                     if (wound && regenerate) {
                        box.flashIcon("wound");
                     } else if (regenerate) {
                        box.flashIcon("regenerate");
                     } else if (wound) {
                        box.flashIcon("wound");
                     }

                     if (box.monster.conditions.contains(Condition.stun, true)) {
                        if (wound) {
                           box.hpAdjust.adjust(-1);
                        }
                     } else {
                        box.lastAnimateIcon = System.currentTimeMillis();
                        box.hpAdjust.adjust(wound ? -3 : -2);
                        box.checkDead(false);
                        if (box.monster.hp > 0) {
                           IntArray free = MonsterRow.this.freeNumbers();
                           if (!free.isEmpty()) {
                              free.shuffle();
                              MonsterBox newBox = MonsterRow.this.addMonsterBox(
                                 free.removeIndex(0), MonsterRow.this.data, MonsterRow.this.level, false, true, SummonColor.blue, false
                              );
                              newBox.monster.hp = Math.min(box.monster.hp, newBox.monster.hpMax);
                           }
                        }
                     }
                  }

                  App.state.ignoreChanges = false;
                  App.state.changed();
               }
            }
         );
   }

   IntArray freeNumbers() {
      IntArray numbers = new IntArray();
      int nn = this.boxes.size;
      int i = 1;

      label24:
      for (int n = this.data.count; i <= n; i++) {
         for (int ii = 0; ii < nn; ii++) {
            if (i == ((MonsterBox)this.boxes.get(ii)).monster.number) {
               continue label24;
            }
         }

         numbers.add(i);
      }

      return numbers;
   }

   private void flipStore1() {
      this.desatDisabled = true;
      if (App.state.abilityCards) {
         this.ability1.update(App.stage.getBatch(), this.abilityCardStack, 1.0F);
         this.ability1.start(false);
      }

      this.addActor(this.ability1);
      this.addActor(this.ability2);
   }

   private void flipStore2() {
      this.updateExtraButtons();
      if (App.state.abilityCards) {
         this.ability2.update(App.stage.getBatch(), this.abilityCardStack, 1.0F);
         this.ability2.start(true);
      }

      this.abilityCardStack.setVisible(false);
      this.desatDisabled = false;
   }

   private void updateExtraButtons() {
      if (this.ability != null && (this.ability.id == 156 || this.ability.id == 157 || !App.state.abilityCards && this.ability.deck.name.equals("Ooze"))) {
         this.oozeSplitButton.setDisabled(this.oozeSplit || this.desat() != 0.0F);
         this.abilityCardStack.addActor(this.oozeSplitContainer);
      } else {
         this.oozeSplit = false;
         if (this.oozeSplitContainer.getParent() == this.abilityCardStack) {
            this.abilityCardStack.removeActor(this.oozeSplitContainer);
         }
      }
   }

   public void showAbility() {
      if (this.isAlive()) {
         this.showAbility(true, this.getAbilityDeck());
      }
   }

   private void showAbility(boolean flipOthers, MonsterAbilityDeck deck) {
      if (this.ability == null) {
         if (deck.shownAbility == null) {
            int i = 0;

            for (int n = App.gloom.monsterRows.size; i < n; i++) {
               MonsterRow row = (MonsterRow)App.gloom.monsterRows.get(i);
               if (row.data.deckID == deck.id) {
                  row.flipStore1();
               }
            }
         }

         if (deck.shownAbility != null && !this.data.isBoss()) {
            this.ability = deck.shownAbility;
         } else {
            Array abilities = deck.abilities;
            if (abilities.size == 0) {
               abilities.clear();
               abilities.addAll(App.findMonsterAbilityDeck(deck.id).abilities);
               App.state.removeAbilities(deck.abilities);
               abilities.shuffle();
               deck.abilitiesDiscard.clear();
            }

            this.ability = (MonsterAbility)abilities.pop();
            deck.abilitiesDiscard.add(this.ability);
            if (!deck.shuffle) {
               deck.shuffle = this.ability.shuffle || abilities.isEmpty();
            }
         }

         if (deck.shownAbility == null) {
            deck.shownAbility = this.ability;
            int i = 0;

            for (int nx = App.gloom.monsterRows.size; i < nx; i++) {
               MonsterRow row = (MonsterRow)App.gloom.monsterRows.get(i);
               if (row.data.deckID == deck.id) {
                  if (flipOthers && row != this) {
                     row.showAbility(false, deck);
                  }

                  row.flipStore2();
               }
            }
         }
      }
   }

   @Override
   public void roundEnd() {
      MonsterAbilityDeck deck = this.getAbilityDeck();
      if (deck.shownAbility != null) {
         this.flipStore1();
         int i = 0;

         for (int n = App.gloom.monsterRows.size; i < n; i++) {
            MonsterRow row = (MonsterRow)App.gloom.monsterRows.get(i);
            if (row != this && row.data.deckID == deck.id) {
               row.flipStore1();
            }
         }

         deck.shownAbility = null;
         this.flipStore2();
         i = 0;

         for (int var7 = App.gloom.monsterRows.size; i < var7; i++) {
            MonsterRow row = (MonsterRow)App.gloom.monsterRows.get(i);
            if (row != this && row.data.deckID == deck.id) {
               row.flipStore2();
            }
         }
      }

      if (deck.shuffle) {
         Array abilities = deck.abilities;
         abilities.clear();
         abilities.addAll(App.findMonsterAbilityDeck(this.data.deckID).abilities);
         App.state.removeAbilities(abilities);
         abilities.shuffle();
         deck.abilitiesDiscard.clear();
         deck.shuffle = false;
      }

      this.ability = null;
      super.roundEnd();
   }

   public MonsterAbilityDeck getAbilityDeck() {
      return App.state.getAbilityDeck(this.data.deckID);
   }

   @Override
   public boolean remove() {
      this.getAbilityDeck().shownAbility = null;
      return super.remove();
   }

   @Override
   public void act(float delta) {
      super.act(delta);
      if (!App.state.ignoreChanges) {
         this.glowAlpha = App.animate(this.glowAlpha, this.isCurrentTurn() ? 1 : 0, 0.2F, 2.0F, 2.0F, delta);
      }
   }

   @Override
   public void draw(Batch batch, float parentAlpha) {
      this.updateExtraButtons();
      HorizontalGroup horizontalGroup = App.state.trackStandees ? this.monstersGroup : null;
      if (this.monstersCell.getActor() != horizontalGroup) {
         this.monstersCell.setActor(horizontalGroup);
         this.abilityCardStack.toFront();
         this.monstersGroup.toBack();
         if (App.state.trackStandees) {
            this.addNormalButton.setChecked(false);
            this.addEliteButton.setChecked(false);
         }
      }

      if (!App.state.trackStandees) {
         this.addNormalButton.setChecked(this.hasNormal);
         this.addEliteButton.setChecked(this.hasElite);
      }

      boolean full = this.boxes.size >= this.data.count && App.state.trackStandees;
      this.addNormalButton.setDisabled(full);
      this.addEliteButton.setDisabled(full);
      super.draw(batch, parentAlpha);
      this.endDesat(batch);
   }

   @Override
   public int getInitiative() {
      if (!this.isAlive()) {
         return 101;
      } else if (App.state.canDraw) {
         return 100;
      } else if (this.ability == null) {
         return 100;
      } else {
         return !App.state.abilityCards ? 100 : this.ability.initiative;
      }
   }

   @Override
   public float desat() {
      return !this.isAlive() ? 1.0F : super.desat();
   }

   @Override
   public boolean hasExpiringConditions() {
      for (MonsterBox box : this.boxes) {
         for (int i = box.monster.conditions.size - 1; i >= 0; i--) {
            switch ((Condition)box.monster.conditions.get(i)) {
               case stun:
               case immobilize:
               case disarm:
               case muddle:
               case strengthen:
               case invisible:
                  return true;
            }
         }
      }

      return false;
   }

   public boolean hasElite() {
      if (!App.state.trackStandees) {
         return this.hasElite;
      } else if (this.data.isBoss()) {
         return false;
      } else {
         for (MonsterBox box : this.boxes) {
            if (box.monster.type == MonsterType.elite) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean hasNormal() {
      if (!App.state.trackStandees) {
         return this.hasNormal;
      } else if (this.data.isBoss()) {
         return this.boxes.size > 0;
      } else {
         for (MonsterBox box : this.boxes) {
            if (box.monster.type == MonsterType.normal) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public boolean isAlive() {
      return App.state.trackStandees ? this.boxes.size > 0 : this.addNormalButton.isChecked() || this.addEliteButton.isChecked();
   }

   @Override
   public float getPrefHeight() {
      return App.state.trackStandees && animatedHeight && Animator.enabled && this.animateHeight != 0.0F ? this.animateHeight : super.getPrefHeight();
   }

   @Override
   public void setLevel(int level) {
      if (level > 7) {
         level = 7;
      }

      this.level = level;
      this.levelString = String.valueOf(level);

      for (MonsterBox box : this.boxes) {
         Monster monster = box.monster;
         int oldMax = monster.hpMax;
         monster.stats = monster.data.stats[monster.type.ordinal()][level];
         monster.hpMax = monster.stats.hpMax();
         if (monster.hp >= oldMax || oldMax == 0) {
            monster.hp = monster.hpMax;
         }
      }

      App.state.changed();
   }

   @Override
   public int getLevel() {
      return this.level;
   }

   @Override
   protected void monsterAdded() {
      boolean allSummons = true;

      for (MonsterBox box : this.boxes) {
         if (!box.monster.conditions.contains(Condition.summonNew, true)) {
            allSummons = false;
            break;
         }
      }

      if (!allSummons && !App.state.canDraw && this.getAbilityDeck().shownAbility == null) {
         this.showAbility();
         if (App.state.abilityCards) {
            int initiative = this.ability.initiative;
            SnapshotArray children = App.gloom.rows.getChildren();
            children.removeValue(this, true);
            int index = 0;
            int i = 0;

            for (int n = children.size; i < n; i++) {
               Row row = (Row)children.get(i);
               if (row.isAlive()) {
                  if (row.getInitiative() > initiative) {
                     index = i;
                     break;
                  }

                  index = i + 1;
               }
            }

            children.insert(index, this);
         }

         Animator.storeChildren(App.gloom.rows, 0.5F);
      }
   }

   @Override
   public String toString() {
      return this.data.name;
   }
}
