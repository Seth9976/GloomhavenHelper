package com.hm.gloomhavenhelper.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragScrollListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.hm.gloomhavenhelper.App;
import com.hm.gloomhavenhelper.MonsterBox;
import com.hm.gloomhavenhelper.model.Condition;
import com.hm.gloomhavenhelper.model.Monster;
import com.hm.gloomhavenhelper.model.MonsterData;
import com.hm.gloomhavenhelper.model.MonsterType;
import com.hm.gloomhavenhelper.model.PlayerInit;
import com.hm.gloomhavenhelper.model.SummonColor;

public abstract class Row extends Table implements Comparable, Animator.HasAnimator {
   public static boolean animatedHeight = true;
   private static final float readyDuration = 0.5F;
   public float finalX;
   public float finalY;
   public Actor dragStartActor;
   public boolean desatDisabled;
   public float readyTime;
   public Animator animator = new Animator(this, 250.0F, 3000.0F, 500.0F);
   public HorizontalGroup monstersGroup;
   public Cell monstersCell;
   public float animateHeight;
   public final Array boxes = new Array();
   public boolean turnComplete;
   public DragListener dragListener = new DragListener() {
      DragScrollListener dragScroll = new DragScrollListener(App.gloom.rowsScroll);

      {
         (this.dragScroll = new DragScrollListener(App.gloom.rowsScroll)).setup(30.0F, 75.0F, 0.05F, 0.9F);
         this.setTapSquareSize(34.0F);
      }

      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
         event.stop();
         return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchDragged(InputEvent event, float x, float y, int pointer) {
         super.touchDragged(
            event,
            (App.state.playerInit == PlayerInit.dragNumber || App.state.playerInit == PlayerInit.dragNumberRequired) && App.dragRow == null
               ? this.getTouchDownX()
               : x,
            y,
            pointer
         );
      }

      @Override
      public void dragStart(InputEvent event, float x, float y, int pointer) {
         if (App.dragRow == null) {
            if (App.state.canDraw) {
               App.stage.cancelTouchFocus();
            } else {
               App.dragging = true;
               App.dragRow = Row.this;
               App.lastSwapRow = null;
               event.getListenerActor().localToAscendantCoordinates(App.dragRow, App.dragStart.set(x, y));
               App.stage.cancelTouchFocusExcept(this, Row.this.dragStartActor);
            }
         }
      }

      @Override
      public void drag(InputEvent event, float x, float y, int pointer) {
         App.gloom.updateDrag(event.getStageX(), event.getStageY());
         this.dragScroll.drag(event, x, y, pointer);
      }

      @Override
      public void dragStop(InputEvent event, float x, float y, int pointer) {
         this.dragScroll.dragStop(event, x, y, pointer);
         App.state.changed();
         App.dragging = false;
         MoveToAction move = Actions.moveTo(Row.this.finalX, Row.this.finalY, 0.25F, Interpolation.fastSlow);
         move.setActor(Row.this);
         App.stage.addAction(Actions.sequence(move, new Action() {
            @Override
            public boolean act(float delta) {
               if (!App.dragging) {
                  App.dragRow = null;
                  Animator.storeChildren(App.gloom.rows, 0.0F);
               }

               return true;
            }
         }));
      }
   };

   public Row() {
      super(App.skin);
      this.monstersGroup = (new HorizontalGroup() {
         @Override
         public void invalidate() {
            if (!Animator.enabled || !Animator.childrenAnimating(this)) {
               super.invalidate();
            }
         }

         @Override
         public void draw(Batch batch, float parentAlpha) {
            Animator.updateChildren(this);
            super.draw(batch, parentAlpha);
         }
      }).space(9.0F).wrap().wrapSpace(4.0F).rowAlign(8).bottom();
      this.top();
   }

   @Override
   public void act(float delta) {
      super.act(delta);
      this.readyTime -= delta;
   }

   public boolean isCurrentTurn() {
      if (this.turnComplete) {
         return false;
      } else if (!this.isAlive()) {
         return false;
      } else {
         SnapshotArray children = App.gloom.rows.getChildren();
         int i = 0;

         for (int n = children.size; i < n; i++) {
            Row row = (Row)children.get(i);
            if (row == this) {
               return true;
            }

            if (!row.turnComplete && row.isAlive()) {
               return false;
            }
         }

         return false;
      }
   }

   public void setTurnComplete(boolean ready, boolean animate) {
      if (this.turnComplete != ready) {
         this.turnComplete = ready;
         if (animate) {
            this.readyTime = 0.5F;
         } else {
            this.readyTime = 0.0F;
         }
      }
   }

   public float desat() {
      if (this.desatDisabled) {
         return 0.0F;
      } else {
         float desat = Math.min(1.0F, 1.0F - this.readyTime / 0.5F);
         if (!this.turnComplete) {
            desat = 1.0F - desat;
         }

         return desat;
      }
   }

   public void beginDesat(Batch batch) {
      if (this.readyTime > 0.0F) {
         Gdx.graphics.requestRendering();
      }

      float desat = this.desat();
      if (desat > 0.0F) {
         batch.setShader(App.desatShader);
         App.desatShader.setUniformf("u_desat", this.turnComplete ? Interpolation.fastSlow.apply(desat) : Interpolation.slowFast.apply(desat));
      }
   }

   public void endDesat(Batch batch) {
      if (this.desat() > 0.0F) {
         batch.setShader(null);
      }
   }

   public void turnStart(boolean endOfRound) {
      Array names = new Array(3);

      for (MonsterBox box : this.boxes) {
         names.clear();
         if (box.monster.conditions.contains(Condition.regenerate, true)) {
            names.add("regenerate");
         }

         if (box.monster.conditions.contains(Condition.wound, true)) {
            names.add("wound");
         }

         if (box.monster.conditions.contains(Condition.bane, true)) {
            names.add("bane");
         }

         box.flashIcons(names);
      }

      if (App.config.autoScroll) {
         App.gloom.rowsScroll.scrollTo(this.getX(), this.getY(), this.getWidth(), this.getHeight(), false, true);
      }
   }

   public void turnStartRevert() {
      if (App.config.autoScroll) {
         App.gloom.rowsScroll.scrollTo(this.getX(), this.getY(), this.getWidth(), this.getHeight(), false, true);
      }
   }

   public void turnEnd(boolean endOfRound) {
      if (!this.turnComplete) {
         SnapshotArray children = App.gloom.rows.getChildren();
         if (!endOfRound) {
            int i = 0;

            for (int n = children.size; i < n; i++) {
               Row row = (Row)children.get(i);
               if (row == this) {
                  break;
               }

               if (!row.turnComplete) {
                  row.turnEnd(endOfRound);
               }
            }

            if (this.isAlive()) {
               this.setTurnComplete(true, true);
            }
         }

         if (this.turnComplete) {
            int next = this.getZIndex() + 1;

            while (next < children.size) {
               Row rowx = (Row)children.get(next++);
               if (rowx.isAlive() && !rowx.turnComplete) {
                  rowx.turnStart(endOfRound);
                  break;
               }
            }
         }

         if (App.state.expireConditions) {
            for (MonsterBox box : this.boxes) {
               int i = box.monster.conditions.size - 1;

               while (i >= 0) {
                  Condition condition = (Condition)box.monster.conditions.get(i);
                  switch (condition) {
                     case stun:
                     case immobilize:
                     case disarm:
                     case muddle:
                     case strengthen:
                     case invisible:
                     case bane:
                        if (!box.monster.currentTurnConditions.contains(condition, true)) {
                           box.monster.conditions.removeIndex(i);
                           box.monster.expiredConditions.add(condition);
                        }
                     default:
                        i--;
                  }
               }
            }
         }
      }
   }

   public void turnEndRevert() {
      if (this.turnComplete) {
         SnapshotArray children = App.gloom.rows.getChildren();
         int i = this.getZIndex() + 1;

         for (int n = children.size; i < n; i++) {
            Row row = (Row)children.get(i);
            if (row.turnComplete) {
               row.turnEndRevert();
            }
         }

         if (this.turnComplete) {
            int next = this.getZIndex() + 1;

            while (next < children.size) {
               Row row = (Row)children.get(next++);
               if (row.isAlive() && !row.turnComplete) {
                  row.turnStartRevert();
                  break;
               }
            }
         }

         if (this.isAlive()) {
            this.setTurnComplete(false, true);
         }

         i = this.getZIndex() + 1;

         for (int var8 = children.size; i < var8; i++) {
            Row row = (Row)children.get(i);
            if (row.turnComplete) {
               row.turnEndRevert();
            }
         }

         Array names = new Array(3);

         for (MonsterBox box : this.boxes) {
            names.clear();
            if (box.monster.conditions.contains(Condition.regenerate, true)) {
               names.add("regenerate");
            }

            if (box.monster.conditions.contains(Condition.wound, true)) {
               names.add("wound");
            }

            if (box.monster.conditions.contains(Condition.bane, true)) {
               names.add("bane");
            }

            box.flashIcons(names);
            box.monster.conditions.addAll(box.monster.expiredConditions);
            box.monster.conditions.sort();
            box.monster.expiredConditions.clear();
         }
      }
   }

   public void roundEnd() {
      this.setTurnComplete(false, true);

      for (MonsterBox box : this.boxes) {
         box.animator.finish(false);
         box.animator.store(0.0F);
         box.monster.isNew = false;
         box.monster.expiredConditions.clear();
         box.monster.currentTurnConditions.clear();
         if (box.monster.conditions.contains(Condition.summonNew, true)) {
            box.monster.conditions.removeValue(Condition.summonNew, true);
            box.monster.conditions.add(Condition.summon);
            box.monster.conditions.sort();
         }
      }

      this.boxes.sort();
      this.monstersGroup.getChildren().sort();
      this.monstersGroup.invalidate();
      Animator.storeChildren(this.monstersGroup, 0.0F);
   }

   @Override
   public Animator getAnimator() {
      return this.animator;
   }

   public void animateIcon(Actor actor, String name, float scale, float x, float y) {
      Vector2 position = actor.localToStageCoordinates(App.v2.set(x, y));
      Container icon = new Container(App.image(name));
      icon.setTouchable(Touchable.disabled);
      icon.setPosition(position.x, position.y);
      icon.setTransform(true);
      icon.setOrigin(icon.getWidth() / 2.0F, icon.getHeight() / 2.0F);
      icon.setScale(0.3F, 0.3F);
      icon.getColor().a = 0.0F;
      icon.addAction(
         Actions.sequence(
            Actions.parallel(Actions.fadeIn(0.2F), Actions.scaleTo(scale, scale, 0.4F, Interpolation.fastSlow)),
            Actions.parallel(Actions.sequence(Actions.delay(0.1F), Actions.fadeOut(0.3F)), Actions.scaleTo(1.0F, 1.0F, 0.4F, Interpolation.slowFast)),
            Actions.removeActor()
         )
      );
      App.stage.addActor(icon);
   }

   public MonsterBox addMonsterBox(int number, MonsterData data, int level, boolean elite, boolean isMonsterSummon, SummonColor color, boolean sort) {
      MonsterType type;
      if (data == App.summonData) {
         type = MonsterType.summon;
      } else if (data.isBoss()) {
         type = MonsterType.boss;
      } else {
         type = elite ? MonsterType.elite : MonsterType.normal;
      }

      MonsterBox box = new MonsterBox(this, new Monster(data, type, level, number), this.monstersGroup.getScaleX());
      box.monster.summonColor = color;
      if (isMonsterSummon) {
         box.monster.conditions.add(Condition.summonNew);
      }

      if (App.state.edition.equals("Gloomhaven")) {
         if (App.state.scenarioNumber == 49 && elite && data.english.equals("Ancient Artillery")) {
            box.monster.hpMax *= 2;
            box.monster.hp = box.monster.hpMax;
            box.monster.conditions.add(Condition.star);
         }

         if (App.state.scenarioNumber == 57 && elite && data.english.equals("Harrower Infester")) {
            box.monster.conditions.add(Condition.star);
         }

         if (App.state.scenarioNumber == 58 && elite && data.english.equals("City Guard")) {
            box.monster.conditions.add(Condition.star);
         }

         if (App.state.scenarioNumber == 67 && elite && data.english.equals("Stone Golem")) {
            box.monster.hpMax = box.monster.hpMax * App.gloom.playerCount();
            box.monster.hp = box.monster.hpMax;
            box.monster.conditions.add(Condition.star);
         }

         if (App.state.scenarioNumber == 110 && elite) {
            if (data.english.equals("Vermling Shaman") || data.english.equals("City Guard") || data.english.equals("Savvas Lavaflow")) {
               box.monster.hpMax = (int)Math.ceil(App.gloom.playerCount() * box.monster.hpMax / 2);
               box.monster.hp = box.monster.hpMax;
               box.monster.conditions.add(Condition.star);
            } else if (data.english.equals("Valrath Tracker")) {
               box.monster.conditions.add(Condition.star);
            }
         }

         if (App.state.scenarioNumber == 111 && elite && data.english.equals("Aesther Ashblade")) {
            box.monster.hpMax = App.gloom.playerCount() * box.monster.hpMax;
            box.monster.hp = box.monster.hpMax;
            box.monster.conditions.add(Condition.star);
         }
      }

      if (App.state.edition.equals("Crimson Scales") && App.state.scenarioNumber == 60 && elite && data.english.equals("Rending Drake")) {
         box.monster.hpMax = (int)(box.monster.hpMax * 1.5);
         box.monster.hp = box.monster.hpMax;
         box.monster.conditions.add(Condition.star);
      }

      box.monster.conditions.sort();
      float time = 0.45F;
      Animator.enabled = false;
      final float startHeight = this.getPrefHeight();
      this.boxes.add(box);
      if (sort) {
         this.boxes.sort();
      }

      this.monstersGroup.addActor(box);
      if (sort) {
         this.monstersGroup.getChildren().sort();
      }

      final float endHeight = this.getPrefHeight();
      Animator.enabled = true;
      if (startHeight != endHeight) {
         this.animateHeight = startHeight;
         App.gloom.rows.addAction(Actions.sequence(new TemporalAction(0.5F) {
            @Override
            protected void update(float percent) {
               Row.this.animateHeight = Interpolation.fastSlow.apply(startHeight, endHeight, percent);
               Row.this.invalidateHierarchy();
            }

            @Override
            protected void end() {
               Row.this.animateHeight = 0.0F;
               Row.this.invalidateHierarchy();
            }
         }));
      }

      Animator.storeChildren(this.monstersGroup, 0.0F);
      this.monsterAdded();
      box.animator.finish(false);
      box.animator.store(0.0F);
      box.getColor().a = 0.0F;
      box.addAction(Actions.fadeIn(time, Interpolation.fastSlow));
      box.moveBy(0.0F, type == MonsterType.summon ? -80 : 80);
      return box;
   }

   protected void monsterAdded() {
   }

   public int compareTo(Row row) {
      return this.getInitiative() - row.getInitiative();
   }

   public abstract int getInitiative();

   public abstract boolean hasExpiringConditions();

   public abstract boolean isAlive();

   public abstract void setLevel(int var1);

   public abstract int getLevel();
}
