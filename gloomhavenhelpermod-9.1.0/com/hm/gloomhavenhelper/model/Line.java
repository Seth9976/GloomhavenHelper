package com.hm.gloomhavenhelper.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.hm.gloomhavenhelper.App;
import com.hm.gloomhavenhelper.MonsterAbilityCard;
import java.util.regex.Matcher;

public class Line {
   private static final ObjectMap tokenText = new ObjectMap();
   private static String and;
   public final String line;
   public BitmapFont font;
   public final Array parts = new Array();
   public float x;
   public float y;
   public float width;
   public float height;
   public float spaceTop;

   public Line(String line, String previous, int id, Color color, float wrapWidth, int align, MonsterAbility.LinesLayout layout) {
      if (line == null) {
         throw new IllegalArgumentException("line cannot be null.");
      } else {
         this.line = line;
         if (line.charAt(0) == '!') {
            line = line.substring(1);
         }

         char first = line.charAt(0);
         boolean isSmall = false;
         if (first == '*') {
            isSmall = true;
            this.font = color == Color.BLACK ? App.plainSmall : App.plainSmallOutline;
            line = line.substring(1);
            this.height = 10.0F;
            this.spaceTop = id < 0 ? 10 : 27;
            if (line.startsWith("....")) {
               if (previous.startsWith("*")) {
                  this.spaceTop = 20.0F;
               } else {
                  this.spaceTop = 7.0F;
               }
            }

            if (previous.equals("NORMAL:") || previous.equals("ELITE:") || previous.startsWith("*")) {
               this.spaceTop -= 16.0F;
            }
         } else if (line.charAt(0) == '^') {
            this.font = color == Color.BLACK ? App.plainMedium : App.plainMediumOutline;
            line = line.substring(1);
            this.height = 15.0F;
            this.spaceTop = 12.0F;
            if (id == 62) {
               this.spaceTop--;
            }

            if (id == 171) {
               this.spaceTop -= 2.0F;
            }

            if (id == 179) {
               this.spaceTop--;
            }
         } else {
            this.font = App.plainLargeOutline;
            this.height = 18.0F;
            this.spaceTop = previous.endsWith(and) ? 15 : 24;
            if (id == 62) {
               this.spaceTop -= 7.0F;
            }

            if (id == 171) {
               this.spaceTop -= 6.0F;
            }

            if (id == 172) {
               this.spaceTop -= 7.0F;
            }

            if (id == 174) {
               this.spaceTop -= 7.0F;
            }

            if (id == 179) {
               this.spaceTop -= 3.0F;
            }

            if (id >= 375 && id <= 377) {
               this.spaceTop -= 3.0F;
            }

            if (line.equals("ELITE:")) {
               color = App.eliteGold;
            }

            if (previous.equals("NORMAL:") || previous.equals("ELITE:")) {
               this.spaceTop -= 10.0F;
            }
         }

         if (previous.startsWith("*....") && !isSmall && !this.isMedium()) {
            this.spaceTop -= 9.0F;
         }

         if (layout != null && layout.type.equals("line")) {
            this.spaceTop = this.spaceTop + layout.spaceTopOffset.intValue();
            this.y = this.y - layout.yOffset.intValue();
            this.height = this.height + layout.heightOffset.intValue();
            this.width = this.width - layout.xOffset.intValue();
         }

         if (line.indexOf(37) == -1) {
            this.addTextPart(line, color, wrapWidth, align, layout);
         } else {
            try {
               Matcher matcher = MonsterAbilityCard.statsPattern.matcher(line);
               if (matcher.matches()) {
                  int end = line.indexOf(" + ");
                  if (end == -1) {
                     end = line.indexOf(" - ");
                  }

                  end += 3;

                  int n;
                  for (n = line.length(); end < n; end++) {
                     char c = line.charAt(end);
                     if (c < '0' || c > '9') {
                        break;
                     }
                  }

                  if (end == n) {
                     this.addParts(line, color, id, layout);
                  } else {
                     this.addParts(line.substring(0, end), color, id, layout);
                     this.addParts(line.substring(end), color, id, layout);
                  }
               } else {
                  this.addParts(line, color, id, layout);
               }
            } catch (Throwable var14) {
               throw new RuntimeException("Error parsing line: " + line, var14);
            }
         }
      }
   }

   private void addParts(String line, Color color, int id, MonsterAbility.LinesLayout layout) {
      boolean inToken = false;
      int i = 0;
      int s = 0;

      for (int n = line.length(); i < n; i++) {
         char c = line.charAt(i);
         if (c == '%') {
            if (inToken) {
               this.addIconPart(line.substring(s, i), id, color, layout);
               inToken = false;
               s = i + 1;
            } else {
               if (i - s > 0) {
                  this.addTextPart(line.substring(s, i), color, 0.0F, 0, layout);
               }

               inToken = true;
               s = i + 1;
            }
         }
      }

      if (i - s > 0) {
         this.addTextPart(line.substring(s, i), color, 0.0F, 0, layout);
      }
   }

   private boolean isSmall() {
      return this.font == App.plainSmall || this.font == App.plainSmallOutline;
   }

   private boolean isMedium() {
      return this.font == App.plainMedium || this.font == App.plainMediumOutline;
   }

   private void addTextPart(String value, Color color, float wrapWidth, int align, MonsterAbility.LinesLayout layout) {
      if (value != null) {
         Line.TextPart text = new Line.TextPart(value, this.font, color);
         if (wrapWidth > 0.0F) {
            text.layout.setText(this.font, value, color, wrapWidth, align, true);
            this.height = Math.max(this.height, this.height + (text.layout.runs.size - 1) * -this.font.getData().down);
            text.width = text.layout.width;
            text.layout.setText(this.font, value, color, text.width, align, true);
         } else {
            text.layout.setText(this.font, value, color, 0.0F, 8, false);
            text.x = this.width;
            text.width = text.layout.width;
         }

         if (text.width != 0.0F) {
            this.width = this.width + text.width;
            if (!value.equals(" ")) {
               text.cache.setText(text.layout, 0.0F, 0.0F);
               this.parts.add(text);
               if (value.equals(":")) {
                  if (this.isSmall()) {
                     this.width -= 8.0F;
                  } else {
                     text.x -= 4.0F;
                     this.width -= 6.0F;
                  }
               }

               if (layout != null && layout.type.equals("icon")) {
                  text.y = text.y - layout.spaceTopOffset.intValue();
                  text.x = text.x - layout.xOffset.intValue();
               }
            }
         }
      }
   }

   private TextureRegion findRegion(String name, Color color) {
      String suffix = color == Color.BLACK ? "-black" : "";
      if (this.isSmall()) {
         TextureRegion textureRegion = (TextureRegion)App.skin.optional("abilities/" + name + "-small" + suffix, TextureRegion.class);
         if (textureRegion != null) {
            return textureRegion;
         }
      } else if (this.isMedium()) {
         TextureRegion textureRegion = (TextureRegion)App.skin.optional("abilities/" + name + "-medium" + suffix, TextureRegion.class);
         if (textureRegion != null) {
            return textureRegion;
         }

         textureRegion = (TextureRegion)App.skin.optional("abilities/" + name + "-small" + suffix, TextureRegion.class);
         if (textureRegion != null) {
            return textureRegion;
         }
      }

      TextureRegion region = (TextureRegion)App.skin.optional("abilities/" + name + suffix, TextureRegion.class);
      if (region != null) {
         return region;
      } else if (color == Color.BLACK) {
         return this.findRegion(name, Color.WHITE);
      } else {
         throw new RuntimeException("Region not found: " + name);
      }
   }

   private void addIconPart(String value, int id, Color color, MonsterAbility.LinesLayout layout) {
      boolean move = value.equals("move");
      Line.RegionPart icon = new Line.RegionPart();
      icon.region = this.findRegion(value, color);
      if (move) {
         icon.flying = this.findRegion("flying", color);
      }

      icon.width = icon.region.getRegionWidth();
      String text = (String)tokenText.get(value, "");
      this.addTextPart(text, color, 0.0F, 0, layout);
      if (text.length() > 0 && text.equals(text.toUpperCase())) {
         if (this.isSmall()) {
            icon.x = 4.0F;
            icon.space = 4.0F;
         } else if (this.isMedium()) {
            icon.x = 6.0F;
            icon.space = 7.0F;
         } else {
            icon.x = 6.0F;
            icon.space = 7.0F;
         }
      } else if (this.isSmall()) {
         icon.x = move ? 2 : 4;
         icon.space = move ? 2 : 5;
      } else if (this.isMedium()) {
         icon.x = move ? 5 : 6;
         icon.space = move ? 5 : 8;
      } else {
         icon.x = move ? 9 : 12;
         icon.space = move ? 17 : 18;
      }

      icon.width = icon.width + icon.space;
      icon.x = icon.x + this.width;
      icon.y = Math.round((this.font.getData().capHeight - icon.region.getRegionHeight()) / 2.0F);
      if (id == 179 && value.equals("earth")) {
         icon.y += 8.0F;
         this.height += 4.0F;
      }

      if (id == -46 && !App.config.isRussian()) {
         if (value.equals("boss-aoe-sightless-eye-sp1")) {
            icon.y -= 13.0F;
         }

         if (value.equals("boss-aoe-sightless-eye-sp2")) {
            icon.x -= 80.0F;
            icon.y -= 38.0F;
         }
      }

      if (id == -40 && value.equals("boss-aoe-elder-drake-sp1")) {
         icon.y = icon.y - (App.config.isRussian() ? 23 : 20);
      }

      if (id == 207 && value.equals("aoe-circle")) {
         icon.y -= 33.0F;
      }

      if (id == 202 && value.equals("aoe-triangle-2-side")) {
         icon.y -= 17.0F;
      }

      if (id == 58 && value.equals("fire")) {
         this.height += 15.0F;
      }

      if (id == 228 && value.equals("air")) {
         this.height += 25.0F;
      }

      if (id == 264 && value.equals("aoe-arc-3")) {
         icon.x -= 10.0F;
         icon.y += 15.0F;
      }

      if (layout != null && layout.type.equals("icon")) {
         icon.x = icon.x + layout.xOffset.intValue();
         icon.y = icon.y - layout.yOffset.intValue();
         this.height = this.height + layout.heightOffset.intValue();
      }

      if (value.equals("use")) {
         icon.x = icon.x - icon.width;
         this.width = this.width - icon.width;
         if (this.isSmall()) {
            ((Line.LinePart)this.parts.peek()).x -= 5.0F;
            icon.x -= 5.0F;
            this.width -= 5.0F;
         }
      }

      if (this.font == App.plainLargeOutline
         && (move || value.equals("attack") || value.equals("heal") || value.equals("loot") || value.equals("shield") || value.equals("retaliate"))) {
         icon.y += 2.0F;
      }

      if (this.isMedium() && value.equals("retaliate")) {
         icon.y++;
      }

      this.parts.add(icon);
      if (!value.contains("aoe")) {
         this.width = this.width + icon.width;
      } else {
         icon.y -= 2.0F;
         if (id == 3) {
            icon.y -= 17.0F;
         }

         if (id == 4) {
            icon.y -= 32.0F;
         }

         if (id == 7) {
            icon.y -= 17.0F;
         }

         if (id == 32 || id == 33) {
            icon.x += 8.0F;
            icon.y += 13.0F;
         }

         if (id == 41 || id == 42) {
            icon.x -= 8.0F;
            icon.y -= 10.0F;
         }

         if (id == 55) {
            this.height += 15.0F;
            icon.y += 15.0F;
         }

         if (id == 58) {
            icon.y--;
         }

         if (id == 68 || id == 69) {
            icon.y -= 12.0F;
         }

         if (id == 93) {
            icon.y += 24.0F;
         }

         if (id == 180 && !App.config.isRussian()) {
            icon.y += 37.0F;
         }

         if (id == 228) {
            icon.y = icon.y + (value.equals("aoe-circle-with-side-black") ? 3 : 15);
         }
      }

      if (value.equals("use")) {
         this.addTextPart(":", color, 0.0F, 0, layout);
      }
   }

   public void draw(Batch batch, float cardX, float cardY, MonsterData data, float alpha) {
      for (Line.LinePart part : this.parts) {
         part.draw(batch, this, cardX, cardY, data, alpha);
      }
   }

   public boolean floatRight() {
      return this.line.charAt(0) == '!';
   }

   @Override
   public String toString() {
      return this.line;
   }

   public static void loadAbilityLines(MonsterAbility card) {
      if (card.text.size != 0) {
         try {
            float height = 0.0F;
            String previous = "";
            int index = 0;

            for (String text : card.text) {
               MonsterAbility.LinesLayout layout = null;
               if (card.cardLayout != null) {
                  for (MonsterAbility.LinesLayout layoutt : card.cardLayout.lines) {
                     if (index == layoutt.index) {
                        layout = layoutt;
                     }
                  }

                  index++;
               }

               Line line = new Line(text, previous, card.id, Color.WHITE, 409.0F, 1, layout);
               if (!line.floatRight()) {
                  if (height > 0.0F) {
                     height += line.spaceTop;
                  }

                  height += line.height;
               } else if (card.lines.size >= 2 && ((Line)card.lines.peek()).floatRight()) {
                  height += 24.0F;
               }

               card.lines.add(line);
               previous = text;
            }

            if (card.id == 48) {
               height += 36.0F;
            }

            if (card.id == 62) {
               height += 5.0F;
            }

            if (card.id == 151) {
               height += 5.0F;
            }

            if (card.id == 156 || card.id == 157) {
               height -= 24.0F;
            }

            if (card.id == 224) {
               height -= 7.0F;
            }

            if (card.id == 228) {
               height += 10.0F;
            }

            int x = 218;
            if (card.id == 180 && App.config.isRussian()) {
               x -= 30;
            }

            if (card.id >= 375 && card.id <= 377) {
               x += 20;
            }

            layout(card.lines, x, 108 + Math.round(height / 2.0F), true, card.id);
            card.text.clear();
         } catch (Throwable var9) {
            throw new RuntimeException("Error loading ability lines for card number: " + card.number, var9);
         }
      }
   }

   public static void loadSpecialLines(MonsterData data, MonsterStats stats) {
      if (stats.specialText1.size != 0) {
         try {
            float wrapWidth = 212.0F;
            boolean russian = App.config.isRussian();
            if (russian) {
               wrapWidth = 225.0F;
            }

            if (data.id == -55) {
               wrapWidth = 999.0F;
            }

            if (data.id == -68) {
               wrapWidth = 234.0F;
            }

            float calculatedWrapWidth = wrapWidth;

            for (String text : stats.specialText1) {
               text = text.charAt(0) == '!' ? "!*" + text.substring(1) : "*" + text;
               Line line = new Line(text, "", data.id, Color.WHITE, wrapWidth, 8, null);
               stats.special1.add(line);
               if (!russian) {
                  wrapWidth -= line.height;
               }

               if (wrapWidth < 150.0F) {
                  wrapWidth = 150.0F;
               }

               text = calculateSpecial(text, stats);
               line = new Line(text, "", data.id, Color.WHITE, calculatedWrapWidth, 8, null);
               stats.specialCalculated1.add(line);
               if (!russian) {
                  calculatedWrapWidth -= line.height;
               }

               if (calculatedWrapWidth < 150.0F) {
                  calculatedWrapWidth = 150.0F;
               }
            }

            for (String text : stats.specialText2) {
               text = text.charAt(0) == '!' ? "!*" + text.substring(1) : "*" + text;
               Line linex = new Line(text, "", data.id, Color.WHITE, wrapWidth, 8, null);
               stats.special2.add(linex);
               if (!russian) {
                  wrapWidth -= linex.height;
               }

               if (wrapWidth < 150.0F) {
                  wrapWidth = 150.0F;
               }

               text = calculateSpecial(text, stats);
               linex = new Line(text, "", data.id, Color.WHITE, calculatedWrapWidth, 8, null);
               stats.specialCalculated2.add(linex);
               if (!russian) {
                  calculatedWrapWidth -= linex.height;
               }

               if (calculatedWrapWidth < 150.0F) {
                  calculatedWrapWidth = 150.0F;
               }
            }

            if (stats.notesText != null) {
               if (data.id == -39) {
                  wrapWidth -= 45.0F;
                  calculatedWrapWidth -= 45.0F;
               }

               stats.notes = new Line("*" + stats.notesText, "", data.id, Color.WHITE, wrapWidth, 8, null);
               stats.notesCalculated = new Line("*" + stats.notesText, "", data.id, Color.WHITE, calculatedWrapWidth, 8, null);
            }

            layout(stats.special1, 0.0F, 0.0F, false, data.id);
            layout(stats.special2, 0.0F, 0.0F, false, data.id);
            layout(Array.with(stats.notes), 0.0F, 0.0F, false, data.id);
            layout(stats.specialCalculated1, 0.0F, 0.0F, false, data.id);
            layout(stats.specialCalculated2, 0.0F, 0.0F, false, data.id);
            layout(Array.with(stats.notesCalculated), 0.0F, 0.0F, false, data.id);
            stats.specialText1.clear();
            stats.specialText2.clear();
         } catch (Throwable var8) {
            throw new RuntimeException("Error loading special lines for monster: " + data.english, var8);
         }
      }
   }

   private static String calculateSpecial(String text, MonsterStats stats) {
      StringBuffer buffer = new StringBuffer(text.length());
      boolean inToken = false;
      int i = 0;
      int s = 0;

      for (int n = text.length(); i < n; i++) {
         char c = text.charAt(i);
         if (c == '%') {
            if (!inToken) {
               inToken = true;
               if (i - s > 0) {
                  buffer.append(text.substring(s, i));
               }

               s = i + 1;
            } else {
               inToken = false;
               String name = text.substring(s, i);
               buffer.append('%');
               buffer.append(name);
               buffer.append('%');
               if (n - i >= 4 && (name.equals("move") || name.equals("attack"))) {
                  char dir = text.charAt(i + 2);
                  if (text.charAt(i + 1) == ' ' && (dir == '+' || dir == '-') && text.charAt(i + 3) == ' ') {
                     int end;
                     for (end = i + 4; end < n; end++) {
                        c = text.charAt(end);
                        if (c < '0' || c > '9') {
                           break;
                        }
                     }

                     if (end != i + 4) {
                        String value = calculateSpecialValue(name, text.substring(i + 4, end), dir, stats);
                        if (value != null) {
                           buffer.append(' ');
                           buffer.append(value);
                           i = end - 1;
                           s = end;
                           continue;
                        }
                     }
                  }
               }

               s = i + 1;
            }
         }
      }

      if (i - s > 0) {
         buffer.append(text.substring(s, i));
      }

      return buffer.toString();
   }

   private static String calculateSpecialValue(String name, String abilityString, char dir, MonsterStats stats) {
      int ability;
      try {
         ability = App.parseInt(abilityString);
      } catch (NumberFormatException var10) {
         return null;
      }

      if (dir == '-') {
         ability = -ability;
      }

      String valueString;
      if (name.equals("attack")) {
         valueString = stats.attack;
      } else {
         if (!name.equals("move")) {
            throw new RuntimeException();
         }

         valueString = stats.move;
      }

      try {
         return Integer.toString(App.parseInt(valueString) + ability);
      } catch (NumberFormatException var9) {
         try {
            stats.attack();
            if (ability <= 9 && ability >= -9) {
               return "!!" + dir + Math.abs(ability);
            } else {
               throw new RuntimeException();
            }
         } catch (NumberFormatException var8) {
            return null;
         }
      }
   }

   public static void loadAttributeLines(MonsterData data, MonsterStats stats, boolean elite) {
      if (stats.attributeText.size != 0) {
         try {
            float y = 0.0F;
            int i = 0;

            for (String text : stats.attributeText) {
               if (text.charAt(0) != '*') {
                  text = "^" + text;
               }

               Line line = new Line(text, "", -1, elite ? Color.WHITE : Color.BLACK, 148.0F, elite ? 8 : 16, null);
               if (i > 0 && line.isSmall()) {
                  line.spaceTop -= 11.0F;
                  line.height += 10.0F;
                  if (stats.attributeText.size > 3) {
                     line.height -= 4.0F;
                  }
               }

               if (i != 0) {
                  y -= line.spaceTop;
               }

               if (!elite) {
                  line.x = Math.max(-18.0F, 130.0F - line.width);
               }

               line.y = y;
               stats.attributeLines.add(line);
               y -= line.height + 5.0F;
               i++;
            }

            stats.attributeText.clear();
         } catch (Throwable var8) {
            throw new RuntimeException("Error loading attribute lines for monster: " + data.english, var8);
         }
      }
   }

   public static void layout(Array lines, float x, float y, boolean center, int id) {
      int i = 0;

      for (int n = lines.size; i < n; i++) {
         Line line = (Line)lines.get(i);
         if (i != 0) {
            y -= line.spaceTop;
         }

         y -= line.height;
         line.x = center ? x - Math.round(line.width / 2.0F) : x;
         line.y += y;
         if (i < n - 1 && ((Line)lines.get(i + 1)).floatRight()) {
            Line wrap1 = (Line)lines.get(++i);
            if (i < n - 1 && ((Line)lines.get(i + 1)).floatRight()) {
               Line wrap2 = (Line)lines.get(++i);
               float iconWidth = line.width;
               line.width = line.width + Math.max(wrap1.width, wrap2.width);
               line.height = iconWidth;
               wrap1.height = 0.0F;
               wrap2.height = 0.0F;
               line.x = center ? x - Math.round(line.width / 2.0F) - 8.0F : x;
               y -= 12.0F;
               line.y = y;
               wrap1.x = wrap1.x + (line.x + iconWidth);
               if (wrap1.parts.first() instanceof Line.TextPart) {
                  wrap1.x += 12.0F;
               }

               float spreadY = wrap1.isSmall() ? (id < 0 ? 8 : 12) : 15;
               wrap1.y = y + spreadY;
               wrap2.x = wrap2.x + (line.x + iconWidth);
               if (wrap2.parts.first() instanceof Line.TextPart) {
                  wrap2.x += 12.0F;
               }

               if (wrap2.line.equals("!^Self")) {
                  wrap2.x = wrap2.x + Math.round((wrap1.width - wrap2.width) / 2.0F);
               }

               wrap2.y = y - spreadY;
               y -= 12.0F;
            } else {
               float iconWidthx = line.width;
               line.width = line.width + wrap1.width;
               line.x = center ? x - Math.round(line.width / 2.0F) - 8.0F : x;
               wrap1.x = wrap1.x + (line.x + iconWidthx);
               if (wrap1.parts.first() instanceof Line.TextPart) {
                  wrap1.x += 12.0F;
               }

               wrap1.y = y;
            }
         }
      }
   }

   public static void loadTokens(JsonValue tokens) {
      tokenText.clear(51);
      tokenText.put("attack", tokens.getString("attack"));
      tokenText.put("move", tokens.getString("move"));
      tokenText.put("range", tokens.getString("range"));
      tokenText.put("heal", tokens.getString("heal"));
      tokenText.put("target", tokens.getString("target"));
      tokenText.put("shield", tokens.getString("shield"));
      tokenText.put("loot", tokens.getString("loot"));
      tokenText.put("retaliate", tokens.getString("retaliate"));
      tokenText.put("jump", tokens.getString("jump"));
      tokenText.put("stun", tokens.getString("stun"));
      tokenText.put("wound", tokens.getString("wound"));
      tokenText.put("disarm", tokens.getString("disarm"));
      tokenText.put("immobilize", tokens.getString("immobilize"));
      tokenText.put("poison", tokens.getString("poison"));
      tokenText.put("invisible", tokens.getString("invisible"));
      tokenText.put("strengthen", tokens.getString("strengthen"));
      tokenText.put("muddle", tokens.getString("muddle"));
      tokenText.put("regenerate", tokens.getString("regenerate"));
      tokenText.put("push", tokens.getString("push"));
      tokenText.put("pull", tokens.getString("pull"));
      tokenText.put("pierce", tokens.getString("pierce"));
      tokenText.put("curse", tokens.getString("curse"));
      tokenText.put("bless", tokens.getString("bless"));
      tokenText.put("ward", tokens.getString("ward"));
      tokenText.put("impair", tokens.getString("impair"));
      tokenText.put("bane", tokens.getString("bane"));
      tokenText.put("brittle", tokens.getString("brittle"));
      tokenText.put("chill", tokens.getString("chill"));
      tokenText.put("infect", tokens.getString("infect"));
      tokenText.put("rupture", tokens.getString("rupture"));
      and = tokens.getString("and");
   }

   public abstract static class LinePart {
      public float x;
      public float y;
      public float width;
      public float space;

      public abstract void draw(Batch var1, Line var2, float var3, float var4, MonsterData var5, float var6);
   }

   public static class RegionPart extends Line.LinePart {
      TextureRegion region;
      TextureRegion flying;

      @Override
      public void draw(Batch batch, Line line, float cardX, float cardY, MonsterData data, float alpha) {
         if (data != null && data.flying && this.flying != null) {
            batch.draw(this.flying, cardX + line.x + this.x - 3.0F, cardY + line.y + this.y);
         } else {
            batch.draw(this.region, cardX + line.x + this.x, cardY + line.y + this.y);
         }
      }
   }

   public static class TextPart extends Line.LinePart {
      public final String text;
      private final Color color = new Color();
      public final GlyphLayout layout = new GlyphLayout();
      final BitmapFontCache cache;

      public TextPart(String text, BitmapFont font, Color color) {
         this.text = text;
         this.color.set(color);
         this.cache = new BitmapFontCache(font);
      }

      @Override
      public void draw(Batch batch, Line line, float cardX, float cardY, MonsterData data, float alpha) {
         this.cache.setPosition(cardX + line.x + this.x, cardY + line.y + this.y + this.layout.height);
         this.color.a = alpha;
         this.cache.setColors(this.color.toFloatBits());
         this.cache.draw(batch);
      }
   }
}
