package com.hm.gloomhavenhelper.util;

public class FormulaEvaluator {
   public static int getHitPoints(Integer C, Integer L, String str) {
      String formula = str.replaceAll("C", C.toString());
      formula = formula.replaceAll("L", L.toString());
      return (int)eval(formula);
   }

   public static double eval(final String str) {
      return (new Object() {
         int pos = -1;
         int ch;

         void nextChar() {
            this.ch = ++this.pos < str.length() ? str.charAt(this.pos) : -1;
         }

         boolean eat(int charToEat) {
            while (this.ch == 32) {
               this.nextChar();
            }

            if (this.ch == charToEat) {
               this.nextChar();
               return true;
            } else {
               return false;
            }
         }

         double parse() {
            this.nextChar();
            double x = this.parseExpression();
            if (this.pos < str.length()) {
               throw new RuntimeException("Unexpected: " + (char)this.ch);
            } else {
               return x;
            }
         }

         double parseExpression() {
            double x = this.parseTerm();

            while (true) {
               while (!this.eat(43)) {
                  if (!this.eat(45)) {
                     return x;
                  }

                  x -= this.parseTerm();
               }

               x += this.parseTerm();
            }
         }

         double parseTerm() {
            double x = this.parseFactor();

            while (true) {
               if (this.eat(42)) {
                  x *= this.parseFactor();
               }

               if (this.eat(120)) {
                  x *= this.parseFactor();
               } else {
                  if (!this.eat(47)) {
                     return x;
                  }

                  x /= this.parseFactor();
               }
            }
         }

         double parseFactor() {
            if (this.eat(43)) {
               return this.parseFactor();
            } else if (this.eat(45)) {
               return -this.parseFactor();
            } else {
               int startPos = this.pos;
               double x;
               if (this.eat(40)) {
                  x = this.parseExpression();
                  if (!this.eat(41)) {
                     throw new RuntimeException("Missing ')'");
                  }
               } else if ((this.ch < 48 || this.ch > 57) && this.ch != 46) {
                  if (this.ch < 97 || this.ch > 122) {
                     throw new RuntimeException("Unexpected: " + (char)this.ch);
                  }

                  while (this.ch >= 97 && this.ch <= 122) {
                     this.nextChar();
                  }

                  String func = str.substring(startPos, this.pos);
                  if (this.eat(40)) {
                     x = this.parseExpression();
                     if (!this.eat(41)) {
                        throw new RuntimeException("Missing ')' after argument to " + func);
                     }
                  } else {
                     x = this.parseFactor();
                  }

                  if (func.equals("sqrt")) {
                     x = Math.sqrt(x);
                  } else if (func.equals("sin")) {
                     x = Math.sin(Math.toRadians(x));
                  } else if (func.equals("cos")) {
                     x = Math.cos(Math.toRadians(x));
                  } else {
                     if (!func.equals("tan")) {
                        throw new RuntimeException("Unknown function: " + func);
                     }

                     x = Math.tan(Math.toRadians(x));
                  }
               } else {
                  while (this.ch >= 48 && this.ch <= 57 || this.ch == 46) {
                     this.nextChar();
                  }

                  x = Double.parseDouble(str.substring(startPos, this.pos));
               }

               if (this.eat(94)) {
                  x = Math.pow(x, this.parseFactor());
               }

               return x;
            }
         }
      }).parse();
   }
}
