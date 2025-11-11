package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;

public class Cell implements Pool.Poolable {
   private static final Float zerof = 0.0F;
   private static final Float onef = 1.0F;
   private static final Integer zeroi = 0;
   private static final Integer onei = 1;
   private static final Integer centeri = onei;
   private static final Integer topi = 2;
   private static final Integer bottomi = 4;
   private static final Integer lefti = 8;
   private static final Integer righti = 16;
   private static Files files;
   private static Cell defaults;
   Value minWidth;
   Value minHeight;
   Value prefWidth;
   Value prefHeight;
   Value maxWidth;
   Value maxHeight;
   Value spaceTop;
   Value spaceLeft;
   Value spaceBottom;
   Value spaceRight;
   Value padTop;
   Value padLeft;
   Value padBottom;
   Value padRight;
   Float fillX;
   Float fillY;
   Integer align;
   Integer expandX;
   Integer expandY;
   Integer colspan;
   Boolean uniformX;
   Boolean uniformY;
   @Null
   Actor actor;
   float actorX;
   float actorY;
   float actorWidth;
   float actorHeight;
   private Table table;
   boolean endRow;
   int column;
   int row;
   int cellAboveIndex = -1;
   float computedPadTop;
   float computedPadLeft;
   float computedPadBottom;
   float computedPadRight;

   public Cell() {
      Cell defaults = defaults();
      if (defaults != null) {
         this.set(defaults);
      }
   }

   public void setTable(Table table) {
      this.table = table;
   }

   public Cell setActor(@Null Actor newActor) {
      if (this.actor != newActor) {
         if (this.actor != null && this.actor.getParent() == this.table) {
            this.actor.remove();
         }

         this.actor = newActor;
         if (newActor != null) {
            this.table.addActor(newActor);
         }
      }

      return this;
   }

   public Cell clearActor() {
      this.setActor(null);
      return this;
   }

   @Null
   public Actor getActor() {
      return this.actor;
   }

   public boolean hasActor() {
      return this.actor != null;
   }

   public Cell size(Value size) {
      if (size == null) {
         throw new IllegalArgumentException("size cannot be null.");
      } else {
         this.minWidth = size;
         this.minHeight = size;
         this.prefWidth = size;
         this.prefHeight = size;
         this.maxWidth = size;
         this.maxHeight = size;
         return this;
      }
   }

   public Cell size(Value width, Value height) {
      if (width == null) {
         throw new IllegalArgumentException("width cannot be null.");
      } else if (height == null) {
         throw new IllegalArgumentException("height cannot be null.");
      } else {
         this.minWidth = width;
         this.minHeight = height;
         this.prefWidth = width;
         this.prefHeight = height;
         this.maxWidth = width;
         this.maxHeight = height;
         return this;
      }
   }

   public Cell size(float size) {
      this.size(Value.Fixed.valueOf(size));
      return this;
   }

   public Cell size(float width, float height) {
      this.size(Value.Fixed.valueOf(width), Value.Fixed.valueOf(height));
      return this;
   }

   public Cell width(Value width) {
      if (width == null) {
         throw new IllegalArgumentException("width cannot be null.");
      } else {
         this.minWidth = width;
         this.prefWidth = width;
         this.maxWidth = width;
         return this;
      }
   }

   public Cell width(float width) {
      this.width(Value.Fixed.valueOf(width));
      return this;
   }

   public Cell height(Value height) {
      if (height == null) {
         throw new IllegalArgumentException("height cannot be null.");
      } else {
         this.minHeight = height;
         this.prefHeight = height;
         this.maxHeight = height;
         return this;
      }
   }

   public Cell height(float height) {
      this.height(Value.Fixed.valueOf(height));
      return this;
   }

   public Cell minSize(Value size) {
      if (size == null) {
         throw new IllegalArgumentException("size cannot be null.");
      } else {
         this.minWidth = size;
         this.minHeight = size;
         return this;
      }
   }

   public Cell minSize(Value width, Value height) {
      if (width == null) {
         throw new IllegalArgumentException("width cannot be null.");
      } else if (height == null) {
         throw new IllegalArgumentException("height cannot be null.");
      } else {
         this.minWidth = width;
         this.minHeight = height;
         return this;
      }
   }

   public Cell minWidth(Value minWidth) {
      if (minWidth == null) {
         throw new IllegalArgumentException("minWidth cannot be null.");
      } else {
         this.minWidth = minWidth;
         return this;
      }
   }

   public Cell minHeight(Value minHeight) {
      if (minHeight == null) {
         throw new IllegalArgumentException("minHeight cannot be null.");
      } else {
         this.minHeight = minHeight;
         return this;
      }
   }

   public Cell minSize(float size) {
      this.minSize(Value.Fixed.valueOf(size));
      return this;
   }

   public Cell minSize(float width, float height) {
      this.minSize(Value.Fixed.valueOf(width), Value.Fixed.valueOf(height));
      return this;
   }

   public Cell minWidth(float minWidth) {
      this.minWidth = Value.Fixed.valueOf(minWidth);
      return this;
   }

   public Cell minHeight(float minHeight) {
      this.minHeight = Value.Fixed.valueOf(minHeight);
      return this;
   }

   public Cell prefSize(Value size) {
      if (size == null) {
         throw new IllegalArgumentException("size cannot be null.");
      } else {
         this.prefWidth = size;
         this.prefHeight = size;
         return this;
      }
   }

   public Cell prefSize(Value width, Value height) {
      if (width == null) {
         throw new IllegalArgumentException("width cannot be null.");
      } else if (height == null) {
         throw new IllegalArgumentException("height cannot be null.");
      } else {
         this.prefWidth = width;
         this.prefHeight = height;
         return this;
      }
   }

   public Cell prefWidth(Value prefWidth) {
      if (prefWidth == null) {
         throw new IllegalArgumentException("prefWidth cannot be null.");
      } else {
         this.prefWidth = prefWidth;
         return this;
      }
   }

   public Cell prefHeight(Value prefHeight) {
      if (prefHeight == null) {
         throw new IllegalArgumentException("prefHeight cannot be null.");
      } else {
         this.prefHeight = prefHeight;
         return this;
      }
   }

   public Cell prefSize(float width, float height) {
      this.prefSize(Value.Fixed.valueOf(width), Value.Fixed.valueOf(height));
      return this;
   }

   public Cell prefSize(float size) {
      this.prefSize(Value.Fixed.valueOf(size));
      return this;
   }

   public Cell prefWidth(float prefWidth) {
      this.prefWidth = Value.Fixed.valueOf(prefWidth);
      return this;
   }

   public Cell prefHeight(float prefHeight) {
      this.prefHeight = Value.Fixed.valueOf(prefHeight);
      return this;
   }

   public Cell maxSize(Value size) {
      if (size == null) {
         throw new IllegalArgumentException("size cannot be null.");
      } else {
         this.maxWidth = size;
         this.maxHeight = size;
         return this;
      }
   }

   public Cell maxSize(Value width, Value height) {
      if (width == null) {
         throw new IllegalArgumentException("width cannot be null.");
      } else if (height == null) {
         throw new IllegalArgumentException("height cannot be null.");
      } else {
         this.maxWidth = width;
         this.maxHeight = height;
         return this;
      }
   }

   public Cell maxWidth(Value maxWidth) {
      if (maxWidth == null) {
         throw new IllegalArgumentException("maxWidth cannot be null.");
      } else {
         this.maxWidth = maxWidth;
         return this;
      }
   }

   public Cell maxHeight(Value maxHeight) {
      if (maxHeight == null) {
         throw new IllegalArgumentException("maxHeight cannot be null.");
      } else {
         this.maxHeight = maxHeight;
         return this;
      }
   }

   public Cell maxSize(float size) {
      this.maxSize(Value.Fixed.valueOf(size));
      return this;
   }

   public Cell maxSize(float width, float height) {
      this.maxSize(Value.Fixed.valueOf(width), Value.Fixed.valueOf(height));
      return this;
   }

   public Cell maxWidth(float maxWidth) {
      this.maxWidth = Value.Fixed.valueOf(maxWidth);
      return this;
   }

   public Cell maxHeight(float maxHeight) {
      this.maxHeight = Value.Fixed.valueOf(maxHeight);
      return this;
   }

   public Cell space(Value space) {
      if (space == null) {
         throw new IllegalArgumentException("space cannot be null.");
      } else {
         this.spaceTop = space;
         this.spaceLeft = space;
         this.spaceBottom = space;
         this.spaceRight = space;
         return this;
      }
   }

   public Cell space(Value top, Value left, Value bottom, Value right) {
      if (top == null) {
         throw new IllegalArgumentException("top cannot be null.");
      } else if (left == null) {
         throw new IllegalArgumentException("left cannot be null.");
      } else if (bottom == null) {
         throw new IllegalArgumentException("bottom cannot be null.");
      } else if (right == null) {
         throw new IllegalArgumentException("right cannot be null.");
      } else {
         this.spaceTop = top;
         this.spaceLeft = left;
         this.spaceBottom = bottom;
         this.spaceRight = right;
         return this;
      }
   }

   public Cell spaceTop(Value spaceTop) {
      if (spaceTop == null) {
         throw new IllegalArgumentException("spaceTop cannot be null.");
      } else {
         this.spaceTop = spaceTop;
         return this;
      }
   }

   public Cell spaceLeft(Value spaceLeft) {
      if (spaceLeft == null) {
         throw new IllegalArgumentException("spaceLeft cannot be null.");
      } else {
         this.spaceLeft = spaceLeft;
         return this;
      }
   }

   public Cell spaceBottom(Value spaceBottom) {
      if (spaceBottom == null) {
         throw new IllegalArgumentException("spaceBottom cannot be null.");
      } else {
         this.spaceBottom = spaceBottom;
         return this;
      }
   }

   public Cell spaceRight(Value spaceRight) {
      if (spaceRight == null) {
         throw new IllegalArgumentException("spaceRight cannot be null.");
      } else {
         this.spaceRight = spaceRight;
         return this;
      }
   }

   public Cell space(float space) {
      if (space < 0.0F) {
         throw new IllegalArgumentException("space cannot be < 0: " + space);
      } else {
         this.space(Value.Fixed.valueOf(space));
         return this;
      }
   }

   public Cell space(float top, float left, float bottom, float right) {
      if (top < 0.0F) {
         throw new IllegalArgumentException("top cannot be < 0: " + top);
      } else if (left < 0.0F) {
         throw new IllegalArgumentException("left cannot be < 0: " + left);
      } else if (bottom < 0.0F) {
         throw new IllegalArgumentException("bottom cannot be < 0: " + bottom);
      } else if (right < 0.0F) {
         throw new IllegalArgumentException("right cannot be < 0: " + right);
      } else {
         this.space(Value.Fixed.valueOf(top), Value.Fixed.valueOf(left), Value.Fixed.valueOf(bottom), Value.Fixed.valueOf(right));
         return this;
      }
   }

   public Cell spaceTop(float spaceTop) {
      if (spaceTop < 0.0F) {
         throw new IllegalArgumentException("spaceTop cannot be < 0: " + spaceTop);
      } else {
         this.spaceTop = Value.Fixed.valueOf(spaceTop);
         return this;
      }
   }

   public Cell spaceLeft(float spaceLeft) {
      if (spaceLeft < 0.0F) {
         throw new IllegalArgumentException("spaceLeft cannot be < 0: " + spaceLeft);
      } else {
         this.spaceLeft = Value.Fixed.valueOf(spaceLeft);
         return this;
      }
   }

   public Cell spaceBottom(float spaceBottom) {
      if (spaceBottom < 0.0F) {
         throw new IllegalArgumentException("spaceBottom cannot be < 0: " + spaceBottom);
      } else {
         this.spaceBottom = Value.Fixed.valueOf(spaceBottom);
         return this;
      }
   }

   public Cell spaceRight(float spaceRight) {
      if (spaceRight < 0.0F) {
         throw new IllegalArgumentException("spaceRight cannot be < 0: " + spaceRight);
      } else {
         this.spaceRight = Value.Fixed.valueOf(spaceRight);
         return this;
      }
   }

   public Cell pad(Value pad) {
      if (pad == null) {
         throw new IllegalArgumentException("pad cannot be null.");
      } else {
         this.padTop = pad;
         this.padLeft = pad;
         this.padBottom = pad;
         this.padRight = pad;
         return this;
      }
   }

   public Cell pad(Value top, Value left, Value bottom, Value right) {
      if (top == null) {
         throw new IllegalArgumentException("top cannot be null.");
      } else if (left == null) {
         throw new IllegalArgumentException("left cannot be null.");
      } else if (bottom == null) {
         throw new IllegalArgumentException("bottom cannot be null.");
      } else if (right == null) {
         throw new IllegalArgumentException("right cannot be null.");
      } else {
         this.padTop = top;
         this.padLeft = left;
         this.padBottom = bottom;
         this.padRight = right;
         return this;
      }
   }

   public Cell padTop(Value padTop) {
      if (padTop == null) {
         throw new IllegalArgumentException("padTop cannot be null.");
      } else {
         this.padTop = padTop;
         return this;
      }
   }

   public Cell padLeft(Value padLeft) {
      if (padLeft == null) {
         throw new IllegalArgumentException("padLeft cannot be null.");
      } else {
         this.padLeft = padLeft;
         return this;
      }
   }

   public Cell padBottom(Value padBottom) {
      if (padBottom == null) {
         throw new IllegalArgumentException("padBottom cannot be null.");
      } else {
         this.padBottom = padBottom;
         return this;
      }
   }

   public Cell padRight(Value padRight) {
      if (padRight == null) {
         throw new IllegalArgumentException("padRight cannot be null.");
      } else {
         this.padRight = padRight;
         return this;
      }
   }

   public Cell pad(float pad) {
      this.pad(Value.Fixed.valueOf(pad));
      return this;
   }

   public Cell pad(float top, float left, float bottom, float right) {
      this.pad(Value.Fixed.valueOf(top), Value.Fixed.valueOf(left), Value.Fixed.valueOf(bottom), Value.Fixed.valueOf(right));
      return this;
   }

   public Cell padTop(float padTop) {
      this.padTop = Value.Fixed.valueOf(padTop);
      return this;
   }

   public Cell padLeft(float padLeft) {
      this.padLeft = Value.Fixed.valueOf(padLeft);
      return this;
   }

   public Cell padBottom(float padBottom) {
      this.padBottom = Value.Fixed.valueOf(padBottom);
      return this;
   }

   public Cell padRight(float padRight) {
      this.padRight = Value.Fixed.valueOf(padRight);
      return this;
   }

   public Cell fill() {
      this.fillX = onef;
      this.fillY = onef;
      return this;
   }

   public Cell fillX() {
      this.fillX = onef;
      return this;
   }

   public Cell fillY() {
      this.fillY = onef;
      return this;
   }

   public Cell fill(float x, float y) {
      this.fillX = x;
      this.fillY = y;
      return this;
   }

   public Cell fill(boolean x, boolean y) {
      this.fillX = x ? onef : zerof;
      this.fillY = y ? onef : zerof;
      return this;
   }

   public Cell fill(boolean fill) {
      this.fillX = fill ? onef : zerof;
      this.fillY = fill ? onef : zerof;
      return this;
   }

   public Cell align(int align) {
      this.align = align;
      return this;
   }

   public Cell center() {
      this.align = centeri;
      return this;
   }

   public Cell top() {
      if (this.align == null) {
         this.align = topi;
      } else {
         this.align = (this.align | 2) & -5;
      }

      return this;
   }

   public Cell left() {
      if (this.align == null) {
         this.align = lefti;
      } else {
         this.align = (this.align | 8) & -17;
      }

      return this;
   }

   public Cell bottom() {
      if (this.align == null) {
         this.align = bottomi;
      } else {
         this.align = (this.align | 4) & -3;
      }

      return this;
   }

   public Cell right() {
      if (this.align == null) {
         this.align = righti;
      } else {
         this.align = (this.align | 16) & -9;
      }

      return this;
   }

   public Cell grow() {
      this.expandX = onei;
      this.expandY = onei;
      this.fillX = onef;
      this.fillY = onef;
      return this;
   }

   public Cell growX() {
      this.expandX = onei;
      this.fillX = onef;
      return this;
   }

   public Cell growY() {
      this.expandY = onei;
      this.fillY = onef;
      return this;
   }

   public Cell expand() {
      this.expandX = onei;
      this.expandY = onei;
      return this;
   }

   public Cell expandX() {
      this.expandX = onei;
      return this;
   }

   public Cell expandY() {
      this.expandY = onei;
      return this;
   }

   public Cell expand(int x, int y) {
      this.expandX = x;
      this.expandY = y;
      return this;
   }

   public Cell expand(boolean x, boolean y) {
      this.expandX = x ? onei : zeroi;
      this.expandY = y ? onei : zeroi;
      return this;
   }

   public Cell colspan(int colspan) {
      this.colspan = colspan;
      return this;
   }

   public Cell uniform() {
      this.uniformX = Boolean.TRUE;
      this.uniformY = Boolean.TRUE;
      return this;
   }

   public Cell uniformX() {
      this.uniformX = Boolean.TRUE;
      return this;
   }

   public Cell uniformY() {
      this.uniformY = Boolean.TRUE;
      return this;
   }

   public Cell uniform(boolean uniform) {
      this.uniformX = uniform;
      this.uniformY = uniform;
      return this;
   }

   public Cell uniform(boolean x, boolean y) {
      this.uniformX = x;
      this.uniformY = y;
      return this;
   }

   public void setActorBounds(float x, float y, float width, float height) {
      this.actorX = x;
      this.actorY = y;
      this.actorWidth = width;
      this.actorHeight = height;
   }

   public float getActorX() {
      return this.actorX;
   }

   public void setActorX(float actorX) {
      this.actorX = actorX;
   }

   public float getActorY() {
      return this.actorY;
   }

   public void setActorY(float actorY) {
      this.actorY = actorY;
   }

   public float getActorWidth() {
      return this.actorWidth;
   }

   public void setActorWidth(float actorWidth) {
      this.actorWidth = actorWidth;
   }

   public float getActorHeight() {
      return this.actorHeight;
   }

   public void setActorHeight(float actorHeight) {
      this.actorHeight = actorHeight;
   }

   public int getColumn() {
      return this.column;
   }

   public int getRow() {
      return this.row;
   }

   @Null
   public Value getMinWidthValue() {
      return this.minWidth;
   }

   public float getMinWidth() {
      return this.minWidth.get(this.actor);
   }

   @Null
   public Value getMinHeightValue() {
      return this.minHeight;
   }

   public float getMinHeight() {
      return this.minHeight.get(this.actor);
   }

   @Null
   public Value getPrefWidthValue() {
      return this.prefWidth;
   }

   public float getPrefWidth() {
      return this.prefWidth.get(this.actor);
   }

   @Null
   public Value getPrefHeightValue() {
      return this.prefHeight;
   }

   public float getPrefHeight() {
      return this.prefHeight.get(this.actor);
   }

   @Null
   public Value getMaxWidthValue() {
      return this.maxWidth;
   }

   public float getMaxWidth() {
      return this.maxWidth.get(this.actor);
   }

   @Null
   public Value getMaxHeightValue() {
      return this.maxHeight;
   }

   public float getMaxHeight() {
      return this.maxHeight.get(this.actor);
   }

   @Null
   public Value getSpaceTopValue() {
      return this.spaceTop;
   }

   public float getSpaceTop() {
      return this.spaceTop.get(this.actor);
   }

   @Null
   public Value getSpaceLeftValue() {
      return this.spaceLeft;
   }

   public float getSpaceLeft() {
      return this.spaceLeft.get(this.actor);
   }

   @Null
   public Value getSpaceBottomValue() {
      return this.spaceBottom;
   }

   public float getSpaceBottom() {
      return this.spaceBottom.get(this.actor);
   }

   @Null
   public Value getSpaceRightValue() {
      return this.spaceRight;
   }

   public float getSpaceRight() {
      return this.spaceRight.get(this.actor);
   }

   @Null
   public Value getPadTopValue() {
      return this.padTop;
   }

   public float getPadTop() {
      return this.padTop.get(this.actor);
   }

   @Null
   public Value getPadLeftValue() {
      return this.padLeft;
   }

   public float getPadLeft() {
      return this.padLeft.get(this.actor);
   }

   @Null
   public Value getPadBottomValue() {
      return this.padBottom;
   }

   public float getPadBottom() {
      return this.padBottom.get(this.actor);
   }

   @Null
   public Value getPadRightValue() {
      return this.padRight;
   }

   public float getPadRight() {
      return this.padRight.get(this.actor);
   }

   public float getPadX() {
      return this.padLeft.get(this.actor) + this.padRight.get(this.actor);
   }

   public float getPadY() {
      return this.padTop.get(this.actor) + this.padBottom.get(this.actor);
   }

   public float getFillX() {
      return this.fillX;
   }

   public float getFillY() {
      return this.fillY;
   }

   public int getAlign() {
      return this.align;
   }

   public int getExpandX() {
      return this.expandX;
   }

   public int getExpandY() {
      return this.expandY;
   }

   public int getColspan() {
      return this.colspan;
   }

   public boolean getUniformX() {
      return this.uniformX;
   }

   public boolean getUniformY() {
      return this.uniformY;
   }

   public boolean isEndRow() {
      return this.endRow;
   }

   public float getComputedPadTop() {
      return this.computedPadTop;
   }

   public float getComputedPadLeft() {
      return this.computedPadLeft;
   }

   public float getComputedPadBottom() {
      return this.computedPadBottom;
   }

   public float getComputedPadRight() {
      return this.computedPadRight;
   }

   public void row() {
      this.table.row();
   }

   public Table getTable() {
      return this.table;
   }

   void clear() {
      this.minWidth = null;
      this.minHeight = null;
      this.prefWidth = null;
      this.prefHeight = null;
      this.maxWidth = null;
      this.maxHeight = null;
      this.spaceTop = null;
      this.spaceLeft = null;
      this.spaceBottom = null;
      this.spaceRight = null;
      this.padTop = null;
      this.padLeft = null;
      this.padBottom = null;
      this.padRight = null;
      this.fillX = null;
      this.fillY = null;
      this.align = null;
      this.expandX = null;
      this.expandY = null;
      this.colspan = null;
      this.uniformX = null;
      this.uniformY = null;
   }

   @Override
   public void reset() {
      this.actor = null;
      this.table = null;
      this.endRow = false;
      this.cellAboveIndex = -1;
      this.set(defaults());
   }

   void set(Cell cell) {
      this.minWidth = cell.minWidth;
      this.minHeight = cell.minHeight;
      this.prefWidth = cell.prefWidth;
      this.prefHeight = cell.prefHeight;
      this.maxWidth = cell.maxWidth;
      this.maxHeight = cell.maxHeight;
      this.spaceTop = cell.spaceTop;
      this.spaceLeft = cell.spaceLeft;
      this.spaceBottom = cell.spaceBottom;
      this.spaceRight = cell.spaceRight;
      this.padTop = cell.padTop;
      this.padLeft = cell.padLeft;
      this.padBottom = cell.padBottom;
      this.padRight = cell.padRight;
      this.fillX = cell.fillX;
      this.fillY = cell.fillY;
      this.align = cell.align;
      this.expandX = cell.expandX;
      this.expandY = cell.expandY;
      this.colspan = cell.colspan;
      this.uniformX = cell.uniformX;
      this.uniformY = cell.uniformY;
   }

   void merge(@Null Cell cell) {
      if (cell != null) {
         if (cell.minWidth != null) {
            this.minWidth = cell.minWidth;
         }

         if (cell.minHeight != null) {
            this.minHeight = cell.minHeight;
         }

         if (cell.prefWidth != null) {
            this.prefWidth = cell.prefWidth;
         }

         if (cell.prefHeight != null) {
            this.prefHeight = cell.prefHeight;
         }

         if (cell.maxWidth != null) {
            this.maxWidth = cell.maxWidth;
         }

         if (cell.maxHeight != null) {
            this.maxHeight = cell.maxHeight;
         }

         if (cell.spaceTop != null) {
            this.spaceTop = cell.spaceTop;
         }

         if (cell.spaceLeft != null) {
            this.spaceLeft = cell.spaceLeft;
         }

         if (cell.spaceBottom != null) {
            this.spaceBottom = cell.spaceBottom;
         }

         if (cell.spaceRight != null) {
            this.spaceRight = cell.spaceRight;
         }

         if (cell.padTop != null) {
            this.padTop = cell.padTop;
         }

         if (cell.padLeft != null) {
            this.padLeft = cell.padLeft;
         }

         if (cell.padBottom != null) {
            this.padBottom = cell.padBottom;
         }

         if (cell.padRight != null) {
            this.padRight = cell.padRight;
         }

         if (cell.fillX != null) {
            this.fillX = cell.fillX;
         }

         if (cell.fillY != null) {
            this.fillY = cell.fillY;
         }

         if (cell.align != null) {
            this.align = cell.align;
         }

         if (cell.expandX != null) {
            this.expandX = cell.expandX;
         }

         if (cell.expandY != null) {
            this.expandY = cell.expandY;
         }

         if (cell.colspan != null) {
            this.colspan = cell.colspan;
         }

         if (cell.uniformX != null) {
            this.uniformX = cell.uniformX;
         }

         if (cell.uniformY != null) {
            this.uniformY = cell.uniformY;
         }
      }
   }

   @Override
   public String toString() {
      return this.actor != null ? this.actor.toString() : super.toString();
   }

   public static Cell defaults() {
      if (files == null || files != Gdx.files) {
         files = Gdx.files;
         defaults = new Cell();
         defaults.minWidth = Value.minWidth;
         defaults.minHeight = Value.minHeight;
         defaults.prefWidth = Value.prefWidth;
         defaults.prefHeight = Value.prefHeight;
         defaults.maxWidth = Value.maxWidth;
         defaults.maxHeight = Value.maxHeight;
         defaults.spaceTop = Value.zero;
         defaults.spaceLeft = Value.zero;
         defaults.spaceBottom = Value.zero;
         defaults.spaceRight = Value.zero;
         defaults.padTop = Value.zero;
         defaults.padLeft = Value.zero;
         defaults.padBottom = Value.zero;
         defaults.padRight = Value.zero;
         defaults.fillX = zerof;
         defaults.fillY = zerof;
         defaults.align = centeri;
         defaults.expandX = zeroi;
         defaults.expandY = zeroi;
         defaults.colspan = onei;
         defaults.uniformX = null;
         defaults.uniformY = null;
      }

      return defaults;
   }
}
