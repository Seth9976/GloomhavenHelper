package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class GlyphLayout implements Pool.Poolable {
   private static final Pool glyphRunPool = Pools.get(GlyphLayout.GlyphRun.class);
   private static final Pool colorPool = Pools.get(Color.class);
   private static final Array colorStack = new Array(4);
   private static final float epsilon = 1.0E-4F;
   public final Array runs = new Array(1);
   public float width;
   public float height;

   public GlyphLayout() {
   }

   public GlyphLayout(BitmapFont font, CharSequence str) {
      this.setText(font, str);
   }

   public GlyphLayout(BitmapFont font, CharSequence str, Color color, float targetWidth, int halign, boolean wrap) {
      this.setText(font, str, color, targetWidth, halign, wrap);
   }

   public GlyphLayout(BitmapFont font, CharSequence str, int start, int end, Color color, float targetWidth, int halign, boolean wrap, String truncate) {
      this.setText(font, str, start, end, color, targetWidth, halign, wrap, truncate);
   }

   public void setText(BitmapFont font, CharSequence str) {
      this.setText(font, str, 0, str.length(), font.getColor(), 0.0F, 8, false, null);
   }

   public void setText(BitmapFont font, CharSequence str, Color color, float targetWidth, int halign, boolean wrap) {
      this.setText(font, str, 0, str.length(), color, targetWidth, halign, wrap, null);
   }

   public void setText(BitmapFont font, CharSequence str, int start, int end, Color color, float targetWidth, int halign, boolean wrap, @Null String truncate) {
      Array runs = this.runs;
      glyphRunPool.freeAll(runs);
      runs.clear();
      BitmapFont.BitmapFontData fontData = font.data;
      if (start == end) {
         this.width = 0.0F;
         this.height = fontData.capHeight;
      } else {
         if (truncate != null) {
            wrap = true;
         } else if (targetWidth <= fontData.spaceXadvance * 3.0F) {
            wrap = false;
         }

         Color nextColor = color;
         boolean markupEnabled = fontData.markupEnabled;
         if (markupEnabled) {
            int i = 1;

            for (int n = colorStack.size; i < n; i++) {
               colorPool.free(colorStack.get(i));
            }

            colorStack.clear();
            colorStack.add(color);
         }

         float x = 0.0F;
         float y = 0.0F;
         float down = fontData.down;
         BitmapFont.Glyph lastGlyph = null;
         int runStart = start;

         label231:
         while (true) {
            int runEnd = -1;
            boolean newline = false;
            if (start == end) {
               if (runStart == end) {
                  break;
               }

               runEnd = end;
            } else {
               switch (str.charAt(start++)) {
                  case '\n':
                     runEnd = start - 1;
                     newline = true;
                     break;
                  case '[':
                     if (markupEnabled) {
                        int length = this.parseColorMarkup(str, start, end, colorPool);
                        if (length >= 0) {
                           runEnd = start - 1;
                           start += length + 1;
                           nextColor = (Color)colorStack.peek();
                        } else if (length == -2) {
                           start++;
                           continue;
                        }
                     }
               }
            }

            if (runEnd != -1) {
               if (runEnd != runStart) {
                  GlyphLayout.GlyphRun run = (GlyphLayout.GlyphRun)glyphRunPool.obtain();
                  run.color.set(color);
                  fontData.getGlyphs(run, str, runStart, runEnd, lastGlyph);
                  if (run.glyphs.size == 0) {
                     glyphRunPool.free(run);
                  } else {
                     if (lastGlyph != null) {
                        x -= lastGlyph.fixedWidth
                           ? lastGlyph.xadvance * fontData.scaleX
                           : (lastGlyph.width + lastGlyph.xoffset) * fontData.scaleX - fontData.padRight;
                     }

                     lastGlyph = (BitmapFont.Glyph)run.glyphs.peek();
                     run.x = x;
                     run.y = y;
                     if (newline || runEnd == end) {
                        this.adjustLastGlyph(fontData, run);
                     }

                     runs.add(run);
                     int n = run.xAdvances.size;
                     float[] xAdvances = run.xAdvances.items;
                     if (wrap && n != 0) {
                        x += xAdvances[0] + xAdvances[1];

                        for (int i = 2; i < n; i++) {
                           BitmapFont.Glyph glyph = (BitmapFont.Glyph)run.glyphs.get(i - 1);
                           float glyphWidth = (glyph.width + glyph.xoffset) * fontData.scaleX - fontData.padRight;
                           if (x + glyphWidth - 1.0E-4F <= targetWidth) {
                              x += xAdvances[i];
                           } else {
                              if (truncate != null) {
                                 this.truncate(fontData, run, targetWidth, truncate, i, glyphRunPool);
                                 break label231;
                              }

                              y += down;
                              lastGlyph = null;
                              int wrapIndex = fontData.getWrapIndex(run.glyphs, i);
                              if (wrapIndex == 0 && run.x == 0.0F || wrapIndex >= run.glyphs.size) {
                                 wrapIndex = i - 1;
                              }

                              GlyphLayout.GlyphRun next;
                              if (wrapIndex == 0) {
                                 next = run;
                                 int glyphCount = run.glyphs.size;

                                 while (wrapIndex < glyphCount && fontData.isWhitespace((char)((BitmapFont.Glyph)run.glyphs.get(wrapIndex)).id)) {
                                    wrapIndex++;
                                 }

                                 if (wrapIndex > 0) {
                                    run.glyphs.removeRange(0, wrapIndex - 1);
                                    run.xAdvances.removeRange(1, wrapIndex);
                                 }

                                 xAdvances[0] = -((BitmapFont.Glyph)run.glyphs.first()).xoffset * fontData.scaleX - fontData.padLeft;
                                 if (runs.size > 1) {
                                    GlyphLayout.GlyphRun previous = (GlyphLayout.GlyphRun)runs.get(runs.size - 2);
                                    int lastIndex = previous.glyphs.size - 1;

                                    while (lastIndex > 0 && fontData.isWhitespace((char)((BitmapFont.Glyph)previous.glyphs.get(lastIndex)).id)) {
                                       lastIndex--;
                                    }

                                    previous.glyphs.truncate(lastIndex + 1);
                                    previous.xAdvances.truncate(lastIndex + 2);
                                    this.adjustLastGlyph(fontData, previous);
                                 }
                              } else {
                                 next = this.wrap(fontData, run, wrapIndex, i);
                                 if (next == null) {
                                    x = 0.0F;
                                    break;
                                 }

                                 runs.add(next);
                              }

                              n = next.xAdvances.size;
                              xAdvances = next.xAdvances.items;
                              x = xAdvances[0];
                              if (n > 1) {
                                 x += xAdvances[1];
                              }

                              next.x = 0.0F;
                              next.y = y;
                              i = 1;
                              run = next;
                           }
                        }
                     } else if (markupEnabled) {
                        for (int ix = 0; ix < n; ix++) {
                           x += xAdvances[ix];
                        }
                     }
                  }
               }

               if (newline) {
                  x = 0.0F;
                  if (runEnd == runStart) {
                     y += down * fontData.blankLineScale;
                  } else {
                     y += down;
                  }

                  lastGlyph = null;
               }

               runStart = start;
               color = nextColor;
            }
         }

         this.height = fontData.capHeight + Math.abs(y);
         float width = 0.0F;
         Object[] runsItems = runs.items;
         int runsSize = runs.size;

         for (int ix = 0; ix < runsSize; ix++) {
            GlyphLayout.GlyphRun run = (GlyphLayout.GlyphRun)runsItems[ix];
            float[] xAdvances = run.xAdvances.items;
            float runWidth = xAdvances[0];
            float max = 0.0F;
            Object[] glyphs = run.glyphs.items;
            int ii = 0;

            for (int nn = run.glyphs.size; ii < nn; runWidth += xAdvances[++ii]) {
               BitmapFont.Glyph glyph = (BitmapFont.Glyph)glyphs[ii];
               float glyphWidth = (glyph.width + glyph.xoffset) * fontData.scaleX - fontData.padRight;
               max = Math.max(max, runWidth + glyphWidth);
            }

            run.width = Math.max(runWidth, max);
            width = Math.max(width, run.x + run.width);
         }

         this.width = width;
         if ((halign & 8) == 0) {
            boolean center = (halign & 1) != 0;
            float lineWidth = 0.0F;
            float lineY = -2.1474836E9F;
            int lineStart = 0;

            for (int ix = 0; ix < runsSize; ix++) {
               GlyphLayout.GlyphRun run = (GlyphLayout.GlyphRun)runsItems[ix];
               if (run.y == lineY) {
                  lineWidth = Math.max(lineWidth, run.x + run.width);
               } else {
                  lineY = run.y;
                  float shift = targetWidth - lineWidth;
                  if (center) {
                     shift /= 2.0F;
                  }

                  while (lineStart < ix) {
                     ((GlyphLayout.GlyphRun)runsItems[lineStart++]).x += shift;
                  }

                  lineWidth = run.x + run.width;
               }
            }

            float shift = targetWidth - lineWidth;
            if (center) {
               shift /= 2.0F;
            }

            while (lineStart < runsSize) {
               ((GlyphLayout.GlyphRun)runsItems[lineStart++]).x += shift;
            }
         }
      }
   }

   private void truncate(BitmapFont.BitmapFontData fontData, GlyphLayout.GlyphRun run, float targetWidth, String truncate, int widthIndex, Pool glyphRunPool) {
      GlyphLayout.GlyphRun truncateRun = (GlyphLayout.GlyphRun)glyphRunPool.obtain();
      fontData.getGlyphs(truncateRun, truncate, 0, truncate.length(), null);
      float truncateWidth = 0.0F;
      if (truncateRun.xAdvances.size > 0) {
         this.adjustLastGlyph(fontData, truncateRun);
         float[] xAdvances = truncateRun.xAdvances.items;
         int i = 1;

         for (int n = truncateRun.xAdvances.size; i < n; i++) {
            truncateWidth += xAdvances[i];
         }
      }

      targetWidth -= truncateWidth;
      int count = 0;
      float width = run.x;

      for (float[] xAdvances = run.xAdvances.items; count < run.xAdvances.size; count++) {
         float xAdvance = xAdvances[count];
         width += xAdvance;
         if (width > targetWidth) {
            break;
         }
      }

      if (count > 1) {
         run.glyphs.truncate(count - 1);
         run.xAdvances.truncate(count);
         this.adjustLastGlyph(fontData, run);
         if (truncateRun.xAdvances.size > 0) {
            run.xAdvances.addAll(truncateRun.xAdvances, 1, truncateRun.xAdvances.size - 1);
         }
      } else {
         run.glyphs.clear();
         run.xAdvances.clear();
         run.xAdvances.addAll(truncateRun.xAdvances);
      }

      run.glyphs.addAll(truncateRun.glyphs);
      glyphRunPool.free(truncateRun);
   }

   private GlyphLayout.GlyphRun wrap(BitmapFont.BitmapFontData fontData, GlyphLayout.GlyphRun first, int wrapIndex, int widthIndex) {
      Array glyphs2 = first.glyphs;
      int glyphCount = first.glyphs.size;
      FloatArray xAdvances2 = first.xAdvances;
      int firstEnd = wrapIndex;

      while (firstEnd > 0 && fontData.isWhitespace((char)((BitmapFont.Glyph)glyphs2.get(firstEnd - 1)).id)) {
         firstEnd--;
      }

      int secondStart = wrapIndex;

      while (secondStart < glyphCount && fontData.isWhitespace((char)((BitmapFont.Glyph)glyphs2.get(secondStart)).id)) {
         secondStart++;
      }

      GlyphLayout.GlyphRun second = null;
      if (secondStart < glyphCount) {
         second = (GlyphLayout.GlyphRun)glyphRunPool.obtain();
         second.color.set(first.color);
         Array glyphs1 = second.glyphs;
         glyphs1.addAll(glyphs2, 0, firstEnd);
         glyphs2.removeRange(0, secondStart - 1);
         first.glyphs = glyphs1;
         second.glyphs = glyphs2;
         FloatArray xAdvances1 = second.xAdvances;
         xAdvances1.addAll(xAdvances2, 0, firstEnd + 1);
         xAdvances2.removeRange(1, secondStart);
         xAdvances2.items[0] = -((BitmapFont.Glyph)glyphs2.first()).xoffset * fontData.scaleX - fontData.padLeft;
         first.xAdvances = xAdvances1;
         second.xAdvances = xAdvances2;
      } else {
         glyphs2.truncate(firstEnd);
         xAdvances2.truncate(firstEnd + 1);
      }

      if (firstEnd == 0) {
         glyphRunPool.free(first);
         this.runs.pop();
      } else {
         this.adjustLastGlyph(fontData, first);
      }

      return second;
   }

   private void adjustLastGlyph(BitmapFont.BitmapFontData fontData, GlyphLayout.GlyphRun run) {
      BitmapFont.Glyph last = (BitmapFont.Glyph)run.glyphs.peek();
      if (!last.fixedWidth) {
         float width = (last.width + last.xoffset) * fontData.scaleX - fontData.padRight;
         run.xAdvances.items[run.xAdvances.size - 1] = width;
      }
   }

   private int parseColorMarkup(CharSequence str, int start, int end, Pool colorPool) {
      if (start == end) {
         return -1;
      } else {
         switch (str.charAt(start)) {
            case '#':
               int colorInt = 0;
               int i = start + 1;

               for (; i < end; i++) {
                  char ch = str.charAt(i);
                  if (ch == ']') {
                     if (i >= start + 2 && i <= start + 9) {
                        if (i - start <= 7) {
                           int ii = 0;

                           for (int nn = 9 - (i - start); ii < nn; ii++) {
                              colorInt <<= 4;
                           }

                           colorInt |= 255;
                        }

                        Color color = (Color)colorPool.obtain();
                        colorStack.add(color);
                        Color.rgba8888ToColor(color, colorInt);
                        return i - start;
                     }
                     break;
                  }

                  if (ch >= '0' && ch <= '9') {
                     colorInt = colorInt * 16 + (ch - '0');
                  } else if (ch >= 'a' && ch <= 'f') {
                     colorInt = colorInt * 16 + (ch - 'W');
                  } else {
                     if (ch < 'A' || ch > 'F') {
                        break;
                     }

                     colorInt = colorInt * 16 + (ch - '7');
                  }
               }

               return -1;
            case '[':
               return -2;
            case ']':
               if (colorStack.size > 1) {
                  colorPool.free(colorStack.pop());
               }

               return 0;
            default:
               for (int i = start + 1; i < end; i++) {
                  char chx = str.charAt(i);
                  if (chx == ']') {
                     Color namedColor = Colors.get(str.subSequence(start, i).toString());
                     if (namedColor == null) {
                        return -1;
                     }

                     Color color = (Color)colorPool.obtain();
                     colorStack.add(color);
                     color.set(namedColor);
                     return i - start;
                  }
               }

               return -1;
         }
      }
   }

   @Override
   public void reset() {
      Pools.get(GlyphLayout.GlyphRun.class).freeAll(this.runs);
      this.runs.clear();
      this.width = 0.0F;
      this.height = 0.0F;
   }

   @Override
   public String toString() {
      if (this.runs.size == 0) {
         return "";
      } else {
         StringBuilder buffer = new StringBuilder(128);
         buffer.append(this.width);
         buffer.append('x');
         buffer.append(this.height);
         buffer.append('\n');
         int i = 0;

         for (int n = this.runs.size; i < n; i++) {
            buffer.append(((GlyphLayout.GlyphRun)this.runs.get(i)).toString());
            buffer.append('\n');
         }

         buffer.setLength(buffer.length() - 1);
         return buffer.toString();
      }
   }

   public static class GlyphRun implements Pool.Poolable {
      public Array glyphs = new Array();
      public FloatArray xAdvances = new FloatArray();
      public float x;
      public float y;
      public float width;
      public final Color color = new Color();

      @Override
      public void reset() {
         this.glyphs.clear();
         this.xAdvances.clear();
         this.width = 0.0F;
      }

      @Override
      public String toString() {
         StringBuilder buffer = new StringBuilder(this.glyphs.size + 32);
         Array glyphs = this.glyphs;
         int i = 0;

         for (int n = glyphs.size; i < n; i++) {
            BitmapFont.Glyph g = (BitmapFont.Glyph)glyphs.get(i);
            buffer.append((char)g.id);
         }

         buffer.append(", #");
         buffer.append(this.color);
         buffer.append(", ");
         buffer.append(this.x);
         buffer.append(", ");
         buffer.append(this.y);
         buffer.append(", ");
         buffer.append(this.width);
         return buffer.toString();
      }
   }
}
