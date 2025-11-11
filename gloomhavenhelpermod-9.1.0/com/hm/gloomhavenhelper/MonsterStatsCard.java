package com.hm.gloomhavenhelper;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.hm.gloomhavenhelper.model.Condition;
import com.hm.gloomhavenhelper.model.Line;
import com.hm.gloomhavenhelper.model.MonsterData;
import com.hm.gloomhavenhelper.model.MonsterStats;
import com.hm.gloomhavenhelper.model.MonsterType;
import com.hm.gloomhavenhelper.util.Menu;

public class MonsterStatsCard extends Table {
   private final MonsterRow row;
   private MonsterData data;
   private final Drawable normalOverlay = App.skin.getDrawable("psd/monsterStats-normal-overlay");
   private final Drawable eliteOverlay = App.skin.getDrawable("psd/monsterStats-elite-overlay");
   private final Drawable bossOverlay = App.skin.getDrawable("psd/monsterStats-boss-overlay");
   private final Drawable flyingStatDrawable = App.skin.getDrawable("psd/flying-stat");
   private final Drawable curseDrawable = App.skin.getDrawable("abilities/curse-medium");
   private final Drawable pushDrawable = App.skin.getDrawable("abilities/push-medium");
   private final Drawable pullDrawable = App.skin.getDrawable("abilities/pull-medium");
   private TextureRegion levelRegion = App.skin.getRegion("psd/level-white");
   private float normalDim = 1.0F;
   private float eliteDim = 1.0F;
   final ClickListener clickListener;

   public MonsterStatsCard(MonsterRow row) {
      super(App.skin);
      this.row = row;
      this.data = row.data;
      this.background("psd/monsterStats-" + (this.data.isBoss() ? "boss" : "normal"));
      this.setTouchable(Touchable.enabled);
      this.addListener(this.clickListener = new ClickListener());
   }

   @Override
   public void act(float delta) {
      float eliteTarget = 1.0F;
      float normalTarget = 1.0F;
      boolean hasElite = this.row.hasElite();
      boolean hasNormal = this.row.hasNormal();
      if (this.clickListener.isPressed()) {
         hasElite = true;
         hasNormal = true;
      } else if (!App.state.hideStats) {
         eliteTarget = 0.0F;
         normalTarget = 0.0F;
      }

      if (Menu.menusShown == 0) {
         this.eliteDim = App.animate(this.eliteDim, hasElite ? 0.0F : eliteTarget, 0.25F, 2.0F, 0.25F, delta);
         this.normalDim = App.animate(this.normalDim, hasNormal ? 0.0F : normalTarget, 0.25F, 2.0F, 0.25F, delta);
      }
   }

   @Override
   public void draw(Batch batch, float parentAlpha) {
      boolean disableDesatShader = batch.getShader() == App.desatShader && this.clickListener.isPressed();
      if (disableDesatShader) {
         batch.setShader(null);
      }

      super.draw(batch, parentAlpha);
      drawLevel(batch, this.levelRegion, this.x(204.0F), this.y(202.0F), this.row.level, this.row.levelString, parentAlpha);
      int fontX = !App.config.language.equals("en")
            && !App.config.language.equals("pl")
            && !App.config.language.equals("cz")
            && !App.config.language.equals("de")
            && !App.config.language.equals("fr")
            && !App.config.language.equals("it")
            && !App.config.language.equals("es")
            && !App.config.language.equals("pt")
            && !App.config.language.equals("hu")
         ? -4
         : 0;
      if (this.data.isBoss()) {
         if (this.data.flying) {
            this.flyingStatDrawable.draw(batch, this.x(152.0F), this.y(86.0F), 37.0F, 40.0F);
         }

         if (this.normalDim != 1.0F) {
            float a = (1.0F - this.normalDim) * parentAlpha;
            batch.setColor(1.0F, 1.0F, 1.0F, a);
            MonsterStats[] typeStats = this.data.stats[MonsterType.boss.ordinal()];
            MonsterStats boss = typeStats[this.row.level];
            float x = this.x(9.0F);
            float y = this.y(49.0F);
            int i = 0;

            for (Condition condition : boss.immunities) {
               condition.drawableMedium.draw(batch, x, y, 28.0F, 28.0F);
               if (i % 2 == 0) {
                  x += 14.0F;
               }

               y += i % 2 == 0 ? 14 : -28;
               i++;
            }

            if (boss.immunePush) {
               this.pushDrawable.draw(batch, x, y, 28.0F, 28.0F);
               if (i % 2 == 0) {
                  x += 14.0F;
               }

               y += i % 2 == 0 ? 14 : -28;
               i++;
            }

            if (boss.immunePull) {
               this.pullDrawable.draw(batch, x, y, 28.0F, 28.0F);
               if (i % 2 == 0) {
                  x += 14.0F;
               }

               y += i % 2 == 0 ? 14 : -28;
               i++;
            }

            if (boss.immuneCurse) {
               this.curseDrawable.draw(batch, x, y, 28.0F, 28.0F);
               if (i % 2 == 0) {
                  x += 14.0F;
               }

               y += i % 2 == 0 ? 14 : -28;
               i++;
            }

            int startX = 202;
            int startY = 15;
            int space = 20;
            if (this.data.id == -37) {
               startX -= 8;
               startY -= 4;
               space -= 2;
            } else if (this.data.id == -38) {
               startX -= 5;
               startY -= 5;
               space = -9;
            } else if (this.data.id == -39) {
               startY -= 5;
            } else if (this.data.id == -40) {
               startX -= 5;
               space += 37;
            } else if (this.data.id == -41) {
               startX += 10;
               startY -= 6;
               space -= 4;
            } else if (this.data.id == -42) {
               startX -= 4;
               startY--;
               space += 15;
            } else if (this.data.id == -43) {
               startX -= 6;
               startY -= 4;
            } else if (this.data.id == -46) {
               startX -= 10;
               startY -= 8;
               space += 21;
            } else if (this.data.id == -47) {
               startX -= 9;
               startY -= 8;
               space -= 8;
            } else if (this.data.id == -45) {
               startX -= 7;
               startY -= 9;
               space -= 6;
            } else if (this.data.id == -54) {
               startX -= 18;
               startY -= 8;
               space -= 6;
            } else if (this.data.id == -55) {
               startX -= 16;
               startY -= 8;
               space -= 9;
            } else if (this.data.id == -68) {
               startX -= 16;
               startY -= 8;
               space -= 9;
            } else if (this.data.id == -69) {
               startX -= 14;
               startY -= 8;
               space -= 9;
            }

            int numberX = startX;
            int numberY = 4;
            if (App.config.isRussian()) {
               startX = Math.min(192, startX);
               startY = Math.min(7, startY);
               numberX = startX + 2;
               numberY = 2;
            }

            batch.setColor(1.0F, 1.0F, 1.0F, a);
            App.plainSmallOutline.setColor(1.0F, 1.0F, 1.0F, a);
            float f1 = this.x(startX + 22);
            float f2 = this.y(startY);
            float height = 0.0F;
            Line.loadAttributeLines(this.data, boss, true);
            int j = 0;

            for (int n = boss.attributeLines.size; j < n; j++) {
               Line line = (Line)boss.attributeLines.get(j);
               line.draw(batch, f1, f2 - line.height, this.data, a);
               height += line.height;
            }

            if (boss.attributeLines.notEmpty()) {
               f2 -= height + space;
            }

            height = 0.0F;
            Array special = App.state.calculateStats ? boss.specialCalculated1 : boss.special1;
            if (special.size > 0) {
               App.plainSmallOutline.draw(batch, "1:", this.x(numberX), f2 + numberY, 0.0F, 8, false);
            }

            Line.loadSpecialLines(this.data, boss);
            int k = 0;

            for (int m = special.size; k < m; k++) {
               Line line = (Line)special.get(k);
               if (k != 0) {
                  height += line.spaceTop;
               }

               this.drawSpecialLine(line, batch, f1, f2, boss, a);
               height += line.height;
            }

            f2 -= height + space;
            height = 0.0F;
            Array var54 = App.state.calculateStats ? boss.specialCalculated2 : boss.special2;
            if (var54.size > 0) {
               App.plainSmallOutline.draw(batch, "2:", this.x(numberX), f2 + numberY, 0.0F, 8, false);
            }

            k = 0;

            for (int var58 = var54.size; k < var58; k++) {
               Line line = (Line)var54.get(k);
               if (k != 0) {
                  height += line.spaceTop;
               }

               this.drawSpecialLine(line, batch, f1, f2, boss, a);
               height += line.height;
            }

            f2 -= height + space;
            Line notes = App.state.calculateStats ? boss.notesCalculated : boss.notes;
            if (notes != null) {
               this.drawSpecialLine(notes, batch, f1, f2, boss, a);
            }

            App.plainLargeFixedNumbers.setColor(0.0F, 0.0F, 0.0F, a);
            String hpMax = boss.hpMax;
            if (App.state.calculateStats && hpMax.contains("xC")) {
               hpMax = Integer.toString(boss.hpMax());
            }

            if (App.state.calculateStats && hpMax.contains("x(C")) {
               hpMax = Integer.toString(boss.hpMax());
            }

            App.plainLargeFixedNumbers.draw(batch, hpMax, this.x(145.0F), this.y(17 + fontX), 0.0F, 16, false);
            App.plainLargeFixedNumbers.draw(batch, boss.move, this.x(145.0F), this.y(58 + fontX), 0.0F, 16, false);
            String attack = boss.attack;
            if (App.state.calculateStats && attack.endsWith("C")) {
               attack = Integer.toString(boss.attack());
            }

            App.plainLargeFixedNumbers.draw(batch, attack, this.x(145.0F), this.y(100 + fontX), 0.0F, 16, false);
            App.plainLargeFixedNumbers.draw(batch, boss.range, this.x(145.0F), this.y(140 + fontX), 0.0F, 16, false);
         }
      } else {
         if (this.data.flying) {
            this.flyingStatDrawable.draw(batch, this.x(202.0F), this.y(82.0F), 37.0F, 40.0F);
         }

         if (this.normalDim != 1.0F) {
            float a = (1.0F - this.normalDim) * parentAlpha;
            batch.setColor(1.0F, 1.0F, 1.0F, a);
            MonsterStats normal = this.data.stats[MonsterType.normal.ordinal()][this.row.level];
            App.plainSmall.setColor(0.0F, 0.0F, 0.0F, a);
            float x = this.x(22.0F);
            float y = this.y(12.0F);
            Line.loadAttributeLines(this.data, normal, false);
            int i = 0;

            for (int n = normal.attributeLines.size; i < n; i++) {
               Line line = (Line)normal.attributeLines.get(i);
               line.draw(batch, x, y - line.height, this.data, a);
            }

            App.plainLargeFixedNumbers.setColor(0.0F, 0.0F, 0.0F, a);
            App.plainLargeFixedNumbers.draw(batch, normal.hpMax, this.x(182.0F), this.y(15 + fontX), 0.0F, 1, false);
            App.plainLargeFixedNumbers.draw(batch, normal.move, this.x(182.0F), this.y(54 + fontX), 0.0F, 1, false);
            App.plainLargeFixedNumbers.draw(batch, normal.attack, this.x(182.0F), this.y(96 + fontX), 0.0F, 1, false);
            App.plainLargeFixedNumbers.draw(batch, normal.range, this.x(182.0F), this.y(138 + fontX), 0.0F, 1, false);
         }

         if (this.eliteDim != 1.0F) {
            float a = (1.0F - this.eliteDim) * parentAlpha;
            batch.setColor(1.0F, 1.0F, 1.0F, a);
            MonsterStats elite = this.data.stats[MonsterType.elite.ordinal()][this.row.level];
            App.plainSmall.setColor(1.0F, 1.0F, 1.0F, a);
            float x = this.x(289.0F);
            float y = this.y(12.0F);
            float maxX = this.x(437.0F);
            Line.loadAttributeLines(this.data, elite, true);
            int i = 0;

            for (int n = elite.attributeLines.size; i < n; i++) {
               Line line = (Line)elite.attributeLines.get(i);
               if (x + line.width > maxX) {
                  x = maxX - line.width;
               }
            }

            i = 0;

            for (int var45 = elite.attributeLines.size; i < var45; i++) {
               Line line = (Line)elite.attributeLines.get(i);
               line.draw(batch, x, y - line.height, this.data, a);
            }

            x = this.x(260.0F);
            App.plainLargeOutlineFixedNumbers.setColor(1.0F, 1.0F, 1.0F, a);
            App.plainLargeOutlineFixedNumbers.draw(batch, elite.hpMax, x, this.y(15 + fontX), 0.0F, 1, false);
            App.plainLargeOutlineFixedNumbers.draw(batch, elite.move, x, this.y(54 + fontX), 0.0F, 1, false);
            App.plainLargeOutlineFixedNumbers.draw(batch, elite.attack, x, this.y(96 + fontX), 0.0F, 1, false);
            App.plainLargeOutlineFixedNumbers.draw(batch, elite.range, x, this.y(138 + fontX), 0.0F, 1, false);
         }

         if (this.normalDim != 1.0F || this.eliteDim != 1.0F) {
            if (this.eliteDim > 0.0F) {
               batch.setColor(0.0F, 0.0F, 0.0F, 0.4F * this.eliteDim * parentAlpha);
               this.eliteOverlay.draw(batch, this.x(220.0F), this.y(182.0F), 219.0F, 181.0F);
            }

            if (this.normalDim > 0.0F) {
               batch.setColor(0.0F, 0.0F, 0.0F, 0.4F * this.normalDim * parentAlpha);
               this.normalOverlay.draw(batch, this.x(2.0F), this.y(182.0F), 219.0F, 181.0F);
            }
         }
      }

      if (this.data.isBoss() || this.normalDim == 1.0F && this.eliteDim == 1.0F) {
         batch.setColor(0.0F, 0.0F, 0.0F, 0.4F * this.normalDim * parentAlpha);
         this.bossOverlay.draw(batch, this.x(2.0F), this.y(182.0F), 437.0F, 181.0F);
      }

      if (disableDesatShader) {
         batch.setShader(App.desatShader);
      }
   }

   private void drawSpecialLine(Line line, Batch batch, float x, float y, MonsterStats stats, float a) {
      for (Line.LinePart part : line.parts) {
         if (part instanceof Line.TextPart) {
            Line.TextPart textPart = (Line.TextPart)part;
            String text = textPart.text;
            int index = text.indexOf("!!");
            if (index != -1) {
               int attack = stats.attack() + Integer.parseInt(text.substring(index + 3, index + 4)) * (text.charAt(index + 2) == '-' ? -1 : 1);
               text = text.substring(0, index) + attack + text.substring(index + 4);
               line.font.draw(batch, text, x + line.x + part.x, y + line.y + part.y + textPart.layout.height);
               continue;
            }
         }

         part.draw(batch, line, x, y, this.data, a);
      }
   }

   private float x(float value) {
      return this.getX() + value;
   }

   private float y(float value) {
      return this.getY() + 209.0F - value;
   }

   public static void drawLevel(Batch batch, TextureRegion levelRegion, float x, float y, int level, String levelString, float a) {
      batch.setColor(App.c(App.lightGray, a));
      batch.draw(levelRegion, x, y);
      App.plainSmall.setColor(0.0F, 0.0F, 0.0F, a);
      float levelX = 16.0F;
      if (level == 0 || level == 1 || level == 4 || level == 6) {
         levelX--;
      }

      App.plainSmall.draw(batch, levelString, x + levelX, y + 16.0F, 0.0F, 1, false);
   }
}
