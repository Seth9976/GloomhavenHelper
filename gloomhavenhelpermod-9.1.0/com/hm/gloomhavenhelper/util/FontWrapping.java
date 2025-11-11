package com.hm.gloomhavenhelper.util;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;

public class FontWrapping {
   public static class Japanese {
      public static int getWrapIndex(Array glyphs, int start) {
         for (int i = start - 1; i >= 1; i--) {
            int startChar = ((BitmapFont.Glyph)glyphs.get(i)).id;
            if (legalAtStart(startChar)) {
               int endChar = ((BitmapFont.Glyph)glyphs.get(i - 1)).id;
               if (legalAtEnd(endChar) && (!doNotSplit(startChar) || !doNotSplit(endChar) || Character.isWhitespace(startChar))) {
                  return i;
               }
            }
         }

         return start;
      }

      private static boolean legalAtStart(int ch) {
         switch (ch) {
            case 33:
            case 34:
            case 41:
            case 44:
            case 46:
            case 58:
            case 59:
            case 63:
            case 93:
            case 187:
            case 8208:
            case 8211:
            case 8217:
            case 8252:
            case 8263:
            case 8264:
            case 8265:
            case 12289:
            case 12290:
            case 12293:
            case 12297:
            case 12299:
            case 12301:
            case 12303:
            case 12305:
            case 12309:
            case 12311:
            case 12313:
            case 12316:
            case 12319:
            case 12347:
            case 12353:
            case 12355:
            case 12357:
            case 12359:
            case 12361:
            case 12387:
            case 12419:
            case 12421:
            case 12423:
            case 12430:
            case 12437:
            case 12438:
            case 12448:
            case 12449:
            case 12451:
            case 12453:
            case 12455:
            case 12457:
            case 12483:
            case 12515:
            case 12517:
            case 12519:
            case 12526:
            case 12533:
            case 12534:
            case 12539:
            case 12540:
            case 12541:
            case 12542:
            case 12784:
            case 12785:
            case 12786:
            case 12787:
            case 12788:
            case 12789:
            case 12790:
            case 12791:
            case 12792:
            case 12793:
            case 12794:
            case 12795:
            case 12796:
            case 12797:
            case 12798:
            case 12799:
            case 65373:
            case 65376:
               return false;
            default:
               return true;
         }
      }

      private static boolean legalAtEnd(int ch) {
         switch (ch) {
            case 34:
            case 40:
            case 91:
            case 171:
            case 8216:
            case 12296:
            case 12298:
            case 12300:
            case 12302:
            case 12304:
            case 12308:
            case 12310:
            case 12312:
            case 12317:
            case 65371:
            case 65375:
               return false;
            default:
               return true;
         }
      }

      private static boolean doNotSplit(int ch) {
         if (ch < 127) {
            return true;
         } else {
            switch (ch) {
               case 8212:
               case 8229:
               case 8230:
               case 12339:
               case 12340:
               case 12341:
               case 65285:
                  return true;
               default:
                  return false;
            }
         }
      }
   }

   public static class Korean {
      public static int getWrapIndex(Array glyphs, int start) {
         for (int i = start - 1; i >= 1; i--) {
            int startChar = ((BitmapFont.Glyph)glyphs.get(i)).id;
            if (legalAtStart(startChar)) {
               int endChar = ((BitmapFont.Glyph)glyphs.get(i - 1)).id;
               if (legalAtEnd(endChar) && (startChar >= 127 || endChar >= 127 || Character.isWhitespace(startChar))) {
                  return i;
               }
            }
         }

         return start;
      }

      private static boolean legalAtStart(int ch) {
         switch (ch) {
            case 33:
            case 34:
            case 37:
            case 41:
            case 44:
            case 46:
            case 58:
            case 59:
            case 63:
            case 93:
            case 125:
            case 162:
            case 176:
            case 8217:
            case 8224:
            case 8225:
            case 8451:
            case 12294:
            case 12296:
            case 12298:
            case 12300:
            case 12302:
            case 12309:
            case 65281:
            case 65285:
            case 65289:
            case 65292:
            case 65294:
            case 65306:
            case 65307:
            case 65311:
            case 65341:
            case 65373:
               return false;
            default:
               return true;
         }
      }

      private static boolean legalAtEnd(int ch) {
         switch (ch) {
            case 32:
            case 34:
            case 35:
            case 36:
            case 40:
            case 91:
            case 92:
            case 123:
            case 163:
            case 165:
            case 8216:
            case 12293:
            case 12295:
            case 12297:
            case 12299:
            case 12301:
            case 12308:
            case 65284:
            case 65288:
            case 65339:
            case 65371:
            case 65376:
            case 65509:
            case 65510:
               return false;
            default:
               return true;
         }
      }
   }
}
