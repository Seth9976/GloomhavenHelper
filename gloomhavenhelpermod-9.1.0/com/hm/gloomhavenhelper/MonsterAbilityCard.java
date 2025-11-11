package com.hm.gloomhavenhelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.hm.gloomhavenhelper.model.Condition;
import com.hm.gloomhavenhelper.model.Line;
import com.hm.gloomhavenhelper.model.MonsterAbility;
import com.hm.gloomhavenhelper.model.MonsterAbilityDeck;
import com.hm.gloomhavenhelper.model.MonsterStats;
import com.hm.gloomhavenhelper.model.MonsterType;
import com.hm.gloomhavenhelper.util.Actor3D;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonsterAbilityCard extends Actor {
   public static final Pattern statsPattern = Pattern.compile("!?\\^?%(move|attack|range|shield|retaliate)% ([+-]) ([0-9]+).*");
   private static final GlyphLayout layout = new GlyphLayout();
   final MonsterRow row;
   private final Drawable frontDrawable = App.skin.getDrawable("psd/monsterAbility-front");
   private final Drawable frontDrawableTall = App.skin.getDrawable("psd/monsterAbility-front2");
   private final Drawable backDrawable = App.skin.getDrawable("psd/monsterAbility-back");
   private final Drawable shuffleDrawable = App.skin.getDrawable("abilities/shuffle");
   private final Drawable curseDrawable = App.skin.getDrawable("abilities/curse-medium");
   private final Array attackConditions = new Array();
   public MonsterAbility forceAbility;

   public MonsterAbilityCard(final MonsterRow row, boolean deckDialog) {
      this.row = row;
      if (App.config.showTitle) {
         this.setSize(441.0F, 255.0F);
      } else {
         this.setSize(441.0F, 209.0F);
      }

      if (deckDialog) {
         this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               new DeckDialog.MonsterAbilityDeckDialog(row).show();
            }
         });
      }
   }

   @Override
   public void draw(Batch batch, float a) {
      MonsterAbilityDeck deck = App.state.getAbilityDeck(this.row.data.deckID);
      batch.setColor(1.0F, 1.0F, 1.0F, a);
      if ((deck.shownAbility != null || this.forceAbility != null) && App.state.abilityCards) {
         MonsterAbility ability = this.forceAbility != null ? this.forceAbility : this.row.ability;
         if (ability == null) {
            return;
         }

         if (App.config.showTitle) {
            this.frontDrawableTall.draw(batch, this.x(0.0F), this.y(209.0F), 441.0F, 255.0F);
         } else {
            this.frontDrawable.draw(batch, this.x(0.0F), this.y(209.0F), 441.0F, 209.0F);
         }

         if (App.config.abilityNumbers) {
            App.plainSmallOutline.setColor(App.c(App.lightGray, a));
            App.plainSmallOutline.draw(batch, ability.number, this.x(8.0F), this.y(184.0F));
         }

         if (App.config.showTitle) {
            App.fancyLargeOutline.setColor(1.0F, 1.0F, 1.0F, a);
            App.fancyLargeOutline.draw(batch, ability.title, this.x(20.0F), this.y(-35.0F), this.getWidth(), 1, false);
         }

         App.fancyExtraLargeOutlineNumbers.setColor(1.0F, 1.0F, 1.0F, a);
         App.fancyExtraLargeOutlineNumbers.draw(batch, ability.initiativeString, this.x(33.0F), this.y(18.0F), 0.0F, 1, false);
         boolean hasElite = this.row.hasElite();
         boolean hasNormal = this.row.hasNormal();
         if (this.forceAbility != null && App.state.trackStandees) {
            hasElite = true;
            hasNormal = true;
         }

         float x = this.getX();
         float y = this.getY();
         Line.loadAbilityLines(ability);
         boolean normalOnly = false;
         boolean eliteOnly = false;
         Array.ArrayIterator var11 = ability.lines.iterator();

         while (true) {
            Line line;
            while (true) {
               if (!var11.hasNext()) {
                  if (ability.shuffle) {
                     this.shuffleDrawable.draw(batch, this.x(400.0F), this.y(196.0F), this.shuffleDrawable.getMinWidth(), this.shuffleDrawable.getMinHeight());
                  }

                  return;
               }

               line = (Line)var11.next();
               if (!App.state.calculateStats || !hasElite && !hasNormal) {
                  break;
               }

               if (line.line.equals("NORMAL:")) {
                  normalOnly = true;
                  eliteOnly = false;
                  break;
               }

               if (line.line.equals("ELITE:")) {
                  eliteOnly = true;
                  normalOnly = false;
                  break;
               }

               Matcher matcher = null;

               try {
                  matcher = statsPattern.matcher(line.line);
               } catch (Throwable var17) {
               }

               if (matcher != null && matcher.matches()) {
                  String name = matcher.group(1);
                  if (ability.id == 171 && name.equals("shield")) {
                     break;
                  }

                  int amount;
                  try {
                     amount = App.parseInt(matcher.group(3));
                  } catch (NumberFormatException var18) {
                     continue;
                  }

                  amount *= matcher.group(2).charAt(0) == '-' ? -1 : 1;
                  if (this.drawCalculatedStat(batch, a, x, y, name, amount, line, hasNormal && !eliteOnly, hasElite && !normalOnly)) {
                     continue;
                  }
               }

               if (line.line.endsWith(" ")) {
                  eliteOnly = false;
                  normalOnly = false;
               }
               break;
            }

            line.draw(batch, x, y, this.row.data, a);
         }
      } else {
         if (App.config.showTitle) {
            this.backDrawable.draw(batch, this.x(0.0F), this.y(209.0F), 441.0F, 255.0F);
         } else {
            this.backDrawable.draw(batch, this.x(0.0F), this.y(209.0F), 441.0F, 209.0F);
         }

         if (App.state.abilityCards) {
            App.plainMediumOutline.setColor(App.c(App.lightGray, a));
            App.plainMediumOutline.draw(batch, Integer.toString(deck.abilities.size), this.getX() + 434.0F, this.getY() + 27.0F, 0.0F, 16, false);
         }
      }
   }

   private boolean drawCalculatedStat(Batch batch, float a, float x, float y, String name, int amount, Line line, boolean hasNormal, boolean hasElite) {
      ((Line.LinePart)line.parts.get(0)).draw(batch, line, x, y, this.row.data, a);
      ((Line.LinePart)line.parts.get(1)).draw(batch, line, x, y, this.row.data, a);
      Line.LinePart numberPart = (Line.LinePart)line.parts.get(2);
      float textX = x + line.x + numberPart.x + 3.0F;
      float startX = textX;
      float textY = y + line.y;

      try {
         if (this.row.data.isBoss()) {
            if (hasNormal) {
               int value = this.statValue(name, MonsterType.boss, amount);
               textX = this.drawStat(name, MonsterType.boss, value, batch, line.font, Color.WHITE, textX, textY, "", a);
            }
         } else {
            int normal = hasNormal ? this.statValue(name, MonsterType.normal, amount) : 0;
            int elite = hasElite ? this.statValue(name, MonsterType.elite, amount) : 0;
            if (hasNormal) {
               textX = this.drawStat(name, MonsterType.normal, normal, batch, line.font, Color.WHITE, textX, textY, hasElite ? " /" : "", a);
               if (hasElite) {
                  textX += 5.0F;
               }
            }

            if (hasElite) {
               textX = this.drawStat(name, MonsterType.elite, elite, batch, line.font, App.eliteGold, textX, textY, "", a);
            }
         }

         if (line.parts.size > 3) {
            float width = textX - startX;
            textX = x + numberPart.x + width + 5.0F;
            if (line.parts.get(3) instanceof Line.RegionPart) {
               textX += 11.0F;
            }

            int i = 3;

            for (int n = line.parts.size; i < n; i++) {
               Line.LinePart part = (Line.LinePart)line.parts.get(i);
               float partX = textX - part.x;
               partX = Math.min(partX, x - line.x + 435.0F - part.x - part.width + part.space);
               part.draw(batch, line, partX, y, this.row.data, a);
               textX += part.width;
            }
         }

         return true;
      } catch (NumberFormatException var19) {
         return false;
      }
   }

   public int statValue(String name, MonsterType type, int amount) {
      MonsterStats stats = this.row.data.stats[type.ordinal()][this.row.level];
      if (name.equals("attack")) {
         return amount + stats.attack();
      } else {
         String valueString = null;
         if (name.equals("move")) {
            valueString = stats.move;
         } else if (name.equals("range")) {
            valueString = stats.range;
         } else if (name.equals("shield")) {
            Line.loadAttributeLines(this.row.data, stats, type == MonsterType.elite);

            for (Line attribute : stats.attributeLines) {
               if (attribute.line.startsWith("^%shield% ")) {
                  valueString = attribute.line.substring(10);
                  break;
               }
            }
         } else {
            if (!name.equals("retaliate")) {
               throw new RuntimeException();
            }

            Line.loadAttributeLines(this.row.data, stats, type == MonsterType.elite);

            for (Line attributex : stats.attributeLines) {
               if (attributex.line.startsWith("^%retaliate% ")) {
                  valueString = attributex.line.substring(13);
                  break;
               }
            }
         }

         return amount + (valueString == null ? 0 : App.parseInt(valueString));
      }
   }

   private float drawStat(String name, MonsterType type, int value, Batch batch, BitmapFont font, Color color, float textX, float textY, String extra, float a) {
      MonsterStats stats = this.row.data.stats[type.ordinal()][this.row.level];
      boolean curse = false;
      if (name.equals("attack")) {
         Line.loadAttributeLines(this.row.data, stats, type == MonsterType.elite);

         for (Line attribute : stats.attributeLines) {
            if (attribute.line.equals("^%wound%")) {
               this.attackConditions.add(Condition.wound);
            } else if (attribute.line.equals("^%disarm%")) {
               this.attackConditions.add(Condition.disarm);
            } else if (attribute.line.equals("^%immobilize%")) {
               this.attackConditions.add(Condition.immobilize);
            } else if (attribute.line.equals("^%poison%")) {
               this.attackConditions.add(Condition.poison);
            } else if (attribute.line.equals("^%muddle%")) {
               this.attackConditions.add(Condition.muddle);
            } else if (attribute.line.equals("^%curse%")) {
               curse = true;
            }
         }
      }

      font.setColor(App.c(color, a));
      textX += font.draw(batch, Integer.toString(value), textX, textY + font.getCapHeight()).width;
      if (this.attackConditions.size > 0 || curse) {
         textX += 2.0F;
      }

      for (Condition condition : this.attackConditions) {
         textX += 2.0F;
         condition.drawableMedium.draw(batch, textX, textY - 6.0F, condition.drawableMedium.getMinWidth(), condition.drawableMedium.getMinHeight());
         textX += 31.0F;
      }

      if (curse) {
         textX += 2.0F;
         this.curseDrawable.draw(batch, textX, textY - 6.0F, this.curseDrawable.getMinWidth(), this.curseDrawable.getMinHeight());
         textX += 31.0F;
      }

      if (this.attackConditions.size > 0 || curse) {
         textX -= 4.0F;
      }

      if (extra != null) {
         textX = --textX + (font.draw(batch, extra, textX, textY + font.getCapHeight()).width - 2.0F);
      }

      this.attackConditions.clear();
      return textX;
   }

   private float x(float value) {
      return this.getX() + value;
   }

   private float y(float value) {
      return this.getY() + 209.0F - value;
   }

   public static class Ability3D extends Actor3D {
      private static final float flipDuration = 0.45F;
      private final int deckID;
      private final Actor actor;
      private float flipTime;
      private boolean front;

      public Ability3D(int deckID, Actor actor) {
         super(441, App.config.showTitle ? 255 : 209, 70.0F);
         this.deckID = deckID;
         this.actor = actor;
      }

      public void start(boolean front) {
         this.front = front;
         this.flipTime = 0.45F;
      }

      @Override
      public void act(float delta) {
         this.flipTime -= delta;
         if (this.flipTime < 0.0F) {
            this.remove();
            this.actor.setVisible(true);
         } else {
            Gdx.graphics.requestRendering();
         }
      }

      @Override
      public void draw(Batch batch, float parentAlpha) {
         this.setPosition(this.actor.getX(), this.actor.getY());
         Gdx.gl.glCullFace(1028);
         Gdx.gl.glEnable(2884);
         super.draw(batch, parentAlpha);
         Gdx.gl.glDisable(2884);
      }

      @Override
      protected void updateCamera(PerspectiveCamera camera, float x, float y, float w, float h) {
         float percent = Interpolation.slowFast.apply(1.0F - this.flipTime / 0.45F);
         camera.update(true);
         camera.combined.translate(x + w / 2.0F, y + h / 2.0F, -camera.position.z);
         if (percent < 0.5F) {
            float a = percent / 0.5F;
            float s = 1.0F + 0.3F * a;
            camera.combined.scale(s, s, s);
         } else {
            float a = (percent - 0.5F) / 0.5F;
            float s = 1.3F - 0.3F * a;
            camera.combined.scale(s, s, s);
         }

         boolean shown = App.state.getAbilityDeck(this.deckID).shownAbility != null;
         camera.combined.rotate(App.v3.set(1.0F, 0.0F, 0.0F), 180.0F * percent * (shown ? -1 : 1));
         if (this.front) {
            camera.combined.scale(1.0F, -1.0F, 1.0F);
         }

         camera.combined.translate(-(x + w / 2.0F), -(y + h / 2.0F), camera.position.z);
      }
   }
}
