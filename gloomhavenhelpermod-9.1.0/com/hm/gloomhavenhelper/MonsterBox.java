package com.hm.gloomhavenhelper;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Array;
import com.hm.gloomhavenhelper.model.Condition;
import com.hm.gloomhavenhelper.model.Monster;
import com.hm.gloomhavenhelper.model.MonsterType;
import com.hm.gloomhavenhelper.util.Animator;
import com.hm.gloomhavenhelper.util.HPAdjust;
import com.hm.gloomhavenhelper.util.Row;

public class MonsterBox extends Actor implements Comparable, Animator.HasAnimator {
   private static final float conditionSpacing = 26.0F;
   public final Row row;
   public final Monster monster;
   private final float scale;
   private float hpPercent;
   private float conditionsRight;
   private float conditionsRightTarget;
   public final Animator animator = new Animator(this, 10.0F, 500.0F, 150.0F);
   long lastAnimateIcon;
   HPAdjust hpAdjust;
   private Drawable bgDrawable;
   private Drawable summonXDrawable;
   private TextureRegion hpRegion;
   private NinePatch conditionsPatch;
   MonsterBoxMenu menu;

   public MonsterBox(Row row, Monster monster, float scale) {
      this.row = row;
      this.monster = monster;
      this.scale = scale;
      this.hpPercent = Math.min(1.0F, (float)monster.hp / monster.hpMax);
      this.create();
      this.layoutUI();
      this.events();
   }

   private void create() {
      this.bgDrawable = App.skin.getDrawable("psd/monster-" + this.monster.type);
      this.summonXDrawable = App.skin.getDrawable("summon/x");
      this.hpRegion = App.skin.getRegion("psd/monster-hp");
      this.conditionsPatch = App.skin.getPatch("psd/conditions");
      this.menu = new MonsterBoxMenu(this);
      this.hpAdjust = new HPAdjust(this, "plainExtraLargeNumbers") {
         @Override
         protected void getPosition(Vector2 position) {
            MonsterBox.this.localToStageCoordinates(position.set(104.0F, 11.0F));
         }

         @Override
         protected int getMax() {
            return MonsterBox.this.monster.hpMax;
         }

         @Override
         protected int getValue() {
            return MonsterBox.this.monster.hp;
         }

         @Override
         protected void setValue(int value) {
            if (MonsterBox.this.monster.hp != value) {
               MonsterBox.this.hpChanged(value + this.extra - this.start);
            }

            MonsterBox.this.monster.hp = value;
            if (value > 0) {
               App.state.changed();
            }

            super.setValue(value);
         }

         @Override
         protected void apply() {
            MonsterBox.this.checkDead(false);
         }
      };
   }

   private void layoutUI() {
      if (this.monster.data.isBoss()) {
         this.setHeight(95.0F * this.scale + 4.0F);
      } else {
         this.setHeight(90.0F * this.scale + 4.0F);
      }

      this.conditionsRight = this.conditionsRightTarget = 117.0F + 26.0F * ((this.monster.conditions.size - 1) / 2);
      if (this.monster.conditions.size == 0) {
         this.conditionsRight -= 40.0F;
      }

      this.setWidth(this.conditionsRight + 5.0F + 26.0F * this.scale);
   }

   private void events() {
      this.addListener(new ClickListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            event.stop();
            return super.touchDown(event, x, y, pointer, button);
         }

         @Override
         public void clicked(InputEvent event, float x, float y) {
            MonsterBox.this.showMenu();
         }
      });
   }

   public void showMenu() {
      this.localToAscendantCoordinates(App.gloom.rows, App.v2.set(0.0F, 0.0F));
      App.gloom.rowsScroll.scrollTo(0.0F, App.v2.y - 7.0F, 0.0F, this.getHeight() + 14.0F);
      this.menu.show(this, 3.0F, -15.0F, -3.0F, 0.0F, true);
      this.hpAdjust.changeContainer.clearActions();
      this.hpAdjust.changeContainer.setVisible(false);
   }

   @Override
   protected void sizeChanged() {
      Group parent = this.getParent();
      if (parent instanceof Layout) {
         ((Layout)parent).invalidate();
      }
   }

   @Override
   public void act(float delta) {
      super.act(delta);
      float width = this.conditionsRight + 5.0F + 26.0F;
      if (width != this.getWidth()) {
         this.setWidth(width);
         this.row.monstersGroup.invalidateHierarchy();
         Animator.storeChildren(this.row.monstersGroup, 0.0F);
      }

      this.hpPercent = App.animate(this.hpPercent, Math.min(1.0F, (float)this.monster.hp / this.monster.hpMax), 0.2F, 2.0F, 2.0F, delta);
      this.conditionsRightTarget = 117.0F + 26.0F * ((this.monster.conditions.size - 1) / 2);
      if (this.monster.conditions.size == 0) {
         this.conditionsRightTarget -= 40.0F;
      }

      this.conditionsRight = App.animate(this.conditionsRight, this.conditionsRightTarget, 20.0F, 200.0F, 25.0F, delta);
   }

   @Override
   public void draw(Batch batch, float parentAlpha) {
      this.animator.update();
      boolean desat = this.monster.hp <= 0 || this.getParent() == App.stage.getRoot();
      if (desat) {
         batch.setShader(App.desatShader);
         App.desatShader.setUniformf("u_desat", 1.0F);
      }

      float boxX = this.getX();
      float boxY = this.getY();
      float a = this.getColor().a * parentAlpha;
      if (this.monster.type != MonsterType.summon) {
         boxY -= 4.0F;
      } else {
         boxY += 7.0F;
      }

      batch.setColor(1.0F, 1.0F, 1.0F, a);
      int conditionsMinus1 = this.monster.conditions.size - 1;
      float conditionsX = 0.0F;
      if (this.conditionsRight > 77.0F) {
         conditionsX = this.conditionsRight - 26.0F * (conditionsMinus1 / 2);
         float w = 67.0F + 26.0F * (conditionsMinus1 / 2);
         float offset = this.conditionsRightTarget - this.conditionsRight;
         w -= offset;
         batch.setColor(1.0F, 1.0F, 1.0F, a);
         this.conditionsPatch.setColor(App.c(1.0F, 1.0F, 1.0F, a));
         this.conditionsPatch.draw(batch, boxX + conditionsX - 47.0F + offset, boxY + 10.0F, w, 65.0F);
      }

      this.bgDrawable.draw(batch, boxX, boxY, this.bgDrawable.getMinWidth(), this.bgDrawable.getMinHeight());
      if (this.monster.type == MonsterType.summon) {
         this.monster.summonColor.drawable.draw(batch, boxX + 38.0F, boxY + 56.0F, 36.0F, 36.0F);
         if (this.monster.isNew) {
            this.summonXDrawable.draw(batch, boxX + 38.0F, boxY + 56.0F, 36.0F, 36.0F);
         }
      }

      int hp = this.monster.hp;
      if (this.hpPercent > 0.0F) {
         int hpMax = this.monster.hpMax;
         batch.setColor(App.c((hp > 3 || !(this.hpPercent <= 0.4F) || hp == hpMax) && !(this.hpPercent < 0.3F) ? App.healthGreen : App.healthRed, a));
         float deadFade = MathUtils.clamp(this.hpPercent / 1.0F / hpMax, 0.0F, 1.0F);
         this.hpRegion.setRegionWidth((int)Math.max(8.0F * deadFade, (float)Math.round(98.0F * this.hpPercent)));
         batch.draw(this.hpRegion, boxX + 8.0F, boxY + 10.0F);
      }

      if (this.monster.type == MonsterType.boss) {
         App.fancyExtraLargeOutlineNumbers.setColor(App.c(App.bossRed, a));
      } else if (this.monster.type == MonsterType.elite) {
         App.fancyExtraLargeOutlineNumbers.setColor(App.c(App.eliteGold, a));
      } else {
         App.fancyExtraLargeOutlineNumbers.setColor(1.0F, 1.0F, 1.0F, a);
      }

      float x = boxX + 20.0F;
      float f2 = boxY + 68.0F;
      int number = this.monster.number;
      if (number == 1) {
         x += 5.0F;
         f2--;
      } else if (number == 2) {
         x++;
      } else if (number == 7) {
         x += 2.0F;
      } else if (number >= 10) {
         if (number == 11) {
            x--;
         } else {
            x -= 5.0F;
         }

         if (App.config.isRussian()) {
            x -= 4.0F;
         }

         f2--;
      }

      String string = Integer.toString(number);
      if (number >= 10 && App.config.isRussian()) {
         GlyphLayout layout = new GlyphLayout();
         layout.setText(App.fancyExtraLargeOutlineNumbers, string, 0, string.length(), App.fancyExtraLargeOutlineNumbers.getColor(), 0.0F, 8, false, null);
         ((GlyphLayout.GlyphRun)layout.runs.first()).xAdvances.incr(1, -5.0F);
         App.fancyExtraLargeOutlineNumbers.draw(batch, layout, x, f2);
      } else {
         App.fancyExtraLargeOutlineNumbers.draw(batch, Integer.toString(number), x, f2);
      }

      BitmapFont font = App.plainExtraLargeNumbers;
      float f1 = boxX + 58.0F;
      float f3 = boxY + 63.0F;
      if (App.config.isJapanese()) {
         f1 += 4.0F;
         f3 -= 5.0F;
      }

      if (hp == 2 || hp == 3) {
         f1++;
      }

      if (hp >= 100) {
         font = App.plainLargeFixedNumbers;
         f1 += 2.0F;
         f3 -= 15.0F;
         if (hp >= 200) {
            f1++;
         }

         if (App.config.isRussian()) {
            f1 -= 2.0F;
         }

         if (App.config.isJapanese()) {
            f1 -= 7.0F;
         }
      } else if (hp >= 20) {
         f1++;
         if (App.config.isRussian()) {
            f1 += 2.0F;
         }
      } else if (hp >= 10) {
         if (App.config.isRussian()) {
            f1 += 5.0F;
         }
      } else {
         f1 += 12.0F;
         if (hp == 1 || hp == 3) {
            f1--;
         } else if (hp == 8 || hp == 9) {
            f1++;
         }

         if (App.config.isRussian()) {
            f1 += 3.0F;
         }
      }

      font.setColor(1.0F, 1.0F, 1.0F, a);
      font.draw(batch, String.valueOf(hp), f1, f3);
      float y = boxY + 25.0F;

      for (int i = 0; i <= conditionsMinus1; i++) {
         Condition condition = (Condition)this.monster.conditions.get(conditionsMinus1 - i);
         float cy = y;
         if (i != conditionsMinus1 || i % 2 != 0) {
            cy = y + (i % 2 == 0 ? 19 : -19);
         }

         float cx = conditionsX + 26.0F * (i / 2);
         batch.setColor(1.0F, 1.0F, 1.0F, a * Interpolation.slowFast.apply(Math.min(1.0F, (cx - 75.0F) / 42.0F)));
         condition.drawable.draw(batch, boxX + cx, cy, condition.drawable.getMinWidth(), condition.drawable.getMinHeight());
      }

      if (desat) {
         batch.setShader(null);
      }
   }

   @Override
   public int compareTo(Object other) {
      if (!(other instanceof MonsterBox)) {
         return 0;
      } else if (this.row instanceof PlayerRow) {
         return 0;
      } else {
         MonsterBox box = (MonsterBox)other;
         if (App.state.elitesFirst) {
            int i = (this.monster.type == MonsterType.elite ? 0 : 1) - (box.monster.type == MonsterType.elite ? 0 : 1);
            if (i != 0) {
               return i;
            }
         }

         int diff = this.monster.summonColor.ordinal() - box.monster.summonColor.ordinal();
         return diff != 0 ? diff : this.monster.number - box.monster.number;
      }
   }

   @Override
   protected void setParent(Group parent) {
      super.setParent(parent);
      if (parent == null) {
         this.hpAdjust.changeContainer.remove();
      } else {
         App.stage.addActor(this.hpAdjust.changeContainer);
      }
   }

   @Override
   public Animator getAnimator() {
      return this.animator;
   }

   public void checkDead(boolean delay) {
      if (this.monster.hp <= 0) {
         this.removeMonster(delay);
         App.state.changed();
      }
   }

   public void removeMonster(boolean delay) {
      if (this.getParent() != null) {
         if (this.getParent().getParent() != null) {
            this.animator.animating = false;
            Animator.enabled = false;
            final float startHeight = this.row.getPrefHeight();
            this.localToStageCoordinates(App.v2.set(0.0F, 0.0F));
            this.row.boxes.removeValue(this, true);
            App.stage.addActor(this);
            this.setPosition(App.v2.x, App.v2.y);
            final float endHeight = this.row.getPrefHeight();
            Animator.enabled = true;
            if (startHeight != endHeight) {
               this.row.animateHeight = startHeight;
               App.gloom.rows.addAction(Actions.sequence(new TemporalAction(0.5F) {
                  @Override
                  protected void update(float percent) {
                     MonsterBox.this.row.animateHeight = Interpolation.fastSlow.apply(startHeight, endHeight, percent);
                     MonsterBox.this.row.invalidateHierarchy();
                  }

                  @Override
                  protected void end() {
                     MonsterBox.this.row.animateHeight = 0.0F;
                     MonsterBox.this.row.invalidateHierarchy();
                  }
               }));
            }

            Animator.storeChildren(this.row.monstersGroup, 0.0F);
            this.row.monstersGroup.invalidateHierarchy();
            App.root.validate();
            this.setTouchable(Touchable.disabled);
            this.addAction(
               Actions.sequence(
                  Actions.delay(delay ? 0.2F : 0.0F),
                  Actions.parallel(
                     Actions.fadeOut(1.1F, Interpolation.slowFast), Actions.moveTo(this.getX(), this.getY() - 60.0F, 1.5F, Interpolation.fastSlow)
                  ),
                  Actions.removeActor()
               )
            );
         }
      }
   }

   void hpChanged(int amount) {
      long time = System.currentTimeMillis();
      if (time >= this.lastAnimateIcon + 1000L) {
         this.lastAnimateIcon = time;
         Array names = new Array(5);
         if (this.monster.conditions.contains(Condition.poison, true)) {
            names.add("poison");
         }

         if (this.monster.conditions.contains(Condition.brittle, true)) {
            names.add("brittle");
         }

         if (amount < 0) {
            if (this.row instanceof MonsterRow && ((MonsterRow)this.row).abilityCard.statValue("shield", this.monster.type, 0) > 0) {
               names.add("shield");
            }

            if (this.monster.conditions.contains(Condition.ward, true)) {
               names.add("ward");
            }

            if (this.monster.conditions.contains(Condition.regenerate, true)) {
               names.add("regenerate");
            }
         } else if (amount > 0) {
            if (this.monster.conditions.contains(Condition.wound, true)) {
               names.add("wound");
            }

            if (this.monster.conditions.contains(Condition.bane, true)) {
               names.add("bane");
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
            this.row.addAction(Actions.sequence(Actions.delay(i * 0.4F), new Action() {
               @Override
               public boolean act(float delta) {
                  MonsterBox.this.flashIcon(name);
                  return true;
               }
            }));
         } else {
            this.flashIcon(name);
         }
      }
   }

   public void flashIcon(String name) {
      this.row.animateIcon(this, "conditions/" + name + "-large", name.equals("shield") ? 1.3F : 1.6F, 54.0F, 40.0F);
   }

   @Override
   public String toString() {
      return this.monster.data.name + " #" + this.monster.number + ", " + this.monster.summonColor + " " + this.monster.summonColor.ordinal();
   }
}
