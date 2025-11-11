package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ArraySelection;
import com.badlogic.gdx.scenes.scene2d.utils.Cullable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class List extends Widget implements Cullable {
   List.ListStyle style;
   final Array items = new Array();
   ArraySelection selection = new ArraySelection(this.items);
   private Rectangle cullingArea;
   private float prefWidth;
   private float prefHeight;
   float itemHeight;
   private int alignment = 8;
   int pressedIndex = -1;
   int overIndex = -1;
   private InputListener keyListener;
   boolean typeToSelect;

   public List(Skin skin) {
      this((List.ListStyle)skin.get(List.ListStyle.class));
   }

   public List(Skin skin, String styleName) {
      this((List.ListStyle)skin.get(styleName, List.ListStyle.class));
   }

   public List(List.ListStyle style) {
      this.selection.setActor(this);
      this.selection.setRequired(true);
      this.setStyle(style);
      this.setSize(this.getPrefWidth(), this.getPrefHeight());
      this.addListener(this.keyListener = new InputListener() {
         long typeTimeout;
         String prefix;

         @Override
         public boolean keyDown(InputEvent event, int keycode) {
            if (List.this.items.isEmpty()) {
               return false;
            } else {
               switch (keycode) {
                  case 3:
                     List.this.setSelectedIndex(0);
                     return true;
                  case 19:
                     int index = List.this.items.indexOf(List.this.getSelected(), false) - 1;
                     if (index < 0) {
                        index = List.this.items.size - 1;
                     }

                     List.this.setSelectedIndex(index);
                     return true;
                  case 20:
                     int index = List.this.items.indexOf(List.this.getSelected(), false) + 1;
                     if (index >= List.this.items.size) {
                        index = 0;
                     }

                     List.this.setSelectedIndex(index);
                     return true;
                  case 29:
                     if (UIUtils.ctrl() && List.this.selection.getMultiple()) {
                        List.this.selection.clear();
                        List.this.selection.addAll(List.this.items);
                        return true;
                     }
                  default:
                     return false;
                  case 111:
                     if (List.this.getStage() != null) {
                        List.this.getStage().setKeyboardFocus(null);
                     }

                     return true;
                  case 123:
                     List.this.setSelectedIndex(List.this.items.size - 1);
                     return true;
               }
            }
         }

         @Override
         public boolean keyTyped(InputEvent event, char character) {
            if (!List.this.typeToSelect) {
               return false;
            } else {
               long time = System.currentTimeMillis();
               if (time > this.typeTimeout) {
                  this.prefix = "";
               }

               this.typeTimeout = time + 300L;
               this.prefix = this.prefix + Character.toLowerCase(character);
               int i = 0;

               for (int n = List.this.items.size; i < n; i++) {
                  if (List.this.toString(List.this.items.get(i)).toLowerCase().startsWith(this.prefix)) {
                     List.this.setSelectedIndex(i);
                     break;
                  }
               }

               return false;
            }
         }
      });
      this.addListener(new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (pointer != 0 || button != 0) {
               return true;
            } else if (List.this.selection.isDisabled()) {
               return true;
            } else {
               if (List.this.getStage() != null) {
                  List.this.getStage().setKeyboardFocus(List.this);
               }

               if (List.this.items.size == 0) {
                  return true;
               } else {
                  int index = List.this.getItemIndexAt(y);
                  if (index == -1) {
                     return true;
                  } else {
                     List.this.selection.choose(List.this.items.get(index));
                     List.this.pressedIndex = index;
                     return true;
                  }
               }
            }
         }

         @Override
         public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            if (pointer == 0 && button == 0) {
               List.this.pressedIndex = -1;
            }
         }

         @Override
         public void touchDragged(InputEvent event, float x, float y, int pointer) {
            List.this.overIndex = List.this.getItemIndexAt(y);
         }

         @Override
         public boolean mouseMoved(InputEvent event, float x, float y) {
            List.this.overIndex = List.this.getItemIndexAt(y);
            return false;
         }

         @Override
         public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            if (pointer == 0) {
               List.this.pressedIndex = -1;
            }

            if (pointer == -1) {
               List.this.overIndex = -1;
            }
         }
      });
   }

   public void setStyle(List.ListStyle style) {
      if (style == null) {
         throw new IllegalArgumentException("style cannot be null.");
      } else {
         this.style = style;
         this.invalidateHierarchy();
      }
   }

   public List.ListStyle getStyle() {
      return this.style;
   }

   @Override
   public void layout() {
      BitmapFont font = this.style.font;
      Drawable selectedDrawable = this.style.selection;
      this.itemHeight = font.getCapHeight() - font.getDescent() * 2.0F;
      this.itemHeight = this.itemHeight + (selectedDrawable.getTopHeight() + selectedDrawable.getBottomHeight());
      this.prefWidth = 0.0F;
      Pool layoutPool = Pools.get(GlyphLayout.class);
      GlyphLayout layout = (GlyphLayout)layoutPool.obtain();

      for (int i = 0; i < this.items.size; i++) {
         layout.setText(font, this.toString(this.items.get(i)));
         this.prefWidth = Math.max(layout.width, this.prefWidth);
      }

      layoutPool.free(layout);
      this.prefWidth = this.prefWidth + (selectedDrawable.getLeftWidth() + selectedDrawable.getRightWidth());
      this.prefHeight = this.items.size * this.itemHeight;
      Drawable background = this.style.background;
      if (background != null) {
         this.prefWidth = Math.max(this.prefWidth + background.getLeftWidth() + background.getRightWidth(), background.getMinWidth());
         this.prefHeight = Math.max(this.prefHeight + background.getTopHeight() + background.getBottomHeight(), background.getMinHeight());
      }
   }

   @Override
   public void draw(Batch batch, float parentAlpha) {
      this.validate();
      this.drawBackground(batch, parentAlpha);
      BitmapFont font = this.style.font;
      Drawable selectedDrawable = this.style.selection;
      Color fontColorSelected = this.style.fontColorSelected;
      Color fontColorUnselected = this.style.fontColorUnselected;
      Color color = this.getColor();
      batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
      float x = this.getX();
      float y = this.getY();
      float width = this.getWidth();
      float height = this.getHeight();
      float itemY = height;
      Drawable background = this.style.background;
      if (background != null) {
         float leftWidth = background.getLeftWidth();
         x += leftWidth;
         itemY = height - background.getTopHeight();
         width -= leftWidth + background.getRightWidth();
      }

      float textOffsetX = selectedDrawable.getLeftWidth();
      float textWidth = width - textOffsetX - selectedDrawable.getRightWidth();
      float textOffsetY = selectedDrawable.getTopHeight() - font.getDescent();
      font.setColor(fontColorUnselected.r, fontColorUnselected.g, fontColorUnselected.b, fontColorUnselected.a * parentAlpha);

      for (int i = 0; i < this.items.size; i++) {
         if (this.cullingArea == null || itemY - this.itemHeight <= this.cullingArea.y + this.cullingArea.height && itemY >= this.cullingArea.y) {
            Object item = (T)this.items.get(i);
            boolean selected = this.selection.contains(item);
            Drawable drawable = null;
            if (this.pressedIndex == i && this.style.down != null) {
               drawable = this.style.down;
            } else if (selected) {
               drawable = selectedDrawable;
               font.setColor(fontColorSelected.r, fontColorSelected.g, fontColorSelected.b, fontColorSelected.a * parentAlpha);
            } else if (this.overIndex == i && this.style.over != null) {
               drawable = this.style.over;
            }

            if (drawable != null) {
               drawable.draw(batch, x, y + itemY - this.itemHeight, width, this.itemHeight);
            }

            this.drawItem(batch, font, i, item, x + textOffsetX, y + itemY - textOffsetY, textWidth);
            if (selected) {
               font.setColor(fontColorUnselected.r, fontColorUnselected.g, fontColorUnselected.b, fontColorUnselected.a * parentAlpha);
            }
         } else if (itemY < this.cullingArea.y) {
            break;
         }

         itemY -= this.itemHeight;
      }
   }

   protected void drawBackground(Batch batch, float parentAlpha) {
      if (this.style.background != null) {
         Color color = this.getColor();
         batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
         this.style.background.draw(batch, this.getX(), this.getY(), this.getWidth(), this.getHeight());
      }
   }

   protected GlyphLayout drawItem(Batch batch, BitmapFont font, int index, Object item, float x, float y, float width) {
      String string = this.toString(item);
      return font.draw(batch, string, x, y, 0, string.length(), width, this.alignment, false, "...");
   }

   public ArraySelection getSelection() {
      return this.selection;
   }

   public void setSelection(ArraySelection selection) {
      this.selection = selection;
   }

   @Null
   public Object getSelected() {
      return this.selection.first();
   }

   public void setSelected(@Null Object item) {
      if (this.items.contains(item, false)) {
         this.selection.set(item);
      } else if (this.selection.getRequired() && this.items.size > 0) {
         this.selection.set(this.items.first());
      } else {
         this.selection.clear();
      }
   }

   public int getSelectedIndex() {
      ObjectSet selected = this.selection.items();
      return selected.size == 0 ? -1 : this.items.indexOf(selected.first(), false);
   }

   public void setSelectedIndex(int index) {
      if (index >= -1 && index < this.items.size) {
         if (index == -1) {
            this.selection.clear();
         } else {
            this.selection.set(this.items.get(index));
         }
      } else {
         throw new IllegalArgumentException("index must be >= -1 and < " + this.items.size + ": " + index);
      }
   }

   public Object getOverItem() {
      return this.overIndex == -1 ? null : this.items.get(this.overIndex);
   }

   public Object getPressedItem() {
      return this.pressedIndex == -1 ? null : this.items.get(this.pressedIndex);
   }

   @Null
   public Object getItemAt(float y) {
      int index = this.getItemIndexAt(y);
      return index == -1 ? null : this.items.get(index);
   }

   public int getItemIndexAt(float y) {
      float height = this.getHeight();
      Drawable background = this.style.background;
      if (background != null) {
         height -= background.getTopHeight() + background.getBottomHeight();
         y -= background.getBottomHeight();
      }

      int index = (int)((height - y) / this.itemHeight);
      return index >= 0 && index < this.items.size ? index : -1;
   }

   public void setItems(Object... newItems) {
      if (newItems == null) {
         throw new IllegalArgumentException("newItems cannot be null.");
      } else {
         float oldPrefWidth = this.getPrefWidth();
         float oldPrefHeight = this.getPrefHeight();
         this.items.clear();
         this.items.addAll(newItems);
         this.overIndex = -1;
         this.pressedIndex = -1;
         this.selection.validate();
         this.invalidate();
         if (oldPrefWidth != this.getPrefWidth() || oldPrefHeight != this.getPrefHeight()) {
            this.invalidateHierarchy();
         }
      }
   }

   public void setItems(Array newItems) {
      if (newItems == null) {
         throw new IllegalArgumentException("newItems cannot be null.");
      } else {
         float oldPrefWidth = this.getPrefWidth();
         float oldPrefHeight = this.getPrefHeight();
         if (newItems != this.items) {
            this.items.clear();
            this.items.addAll(newItems);
         }

         this.overIndex = -1;
         this.pressedIndex = -1;
         this.selection.validate();
         this.invalidate();
         if (oldPrefWidth != this.getPrefWidth() || oldPrefHeight != this.getPrefHeight()) {
            this.invalidateHierarchy();
         }
      }
   }

   public void clearItems() {
      if (this.items.size != 0) {
         this.items.clear();
         this.overIndex = -1;
         this.pressedIndex = -1;
         this.selection.clear();
         this.invalidateHierarchy();
      }
   }

   public Array getItems() {
      return this.items;
   }

   public float getItemHeight() {
      return this.itemHeight;
   }

   @Override
   public float getPrefWidth() {
      this.validate();
      return this.prefWidth;
   }

   @Override
   public float getPrefHeight() {
      this.validate();
      return this.prefHeight;
   }

   public String toString(Object object) {
      return object.toString();
   }

   @Override
   public void setCullingArea(@Null Rectangle cullingArea) {
      this.cullingArea = cullingArea;
   }

   public Rectangle getCullingArea() {
      return this.cullingArea;
   }

   public void setAlignment(int alignment) {
      this.alignment = alignment;
   }

   public void setTypeToSelect(boolean typeToSelect) {
      this.typeToSelect = typeToSelect;
   }

   public InputListener getKeyListener() {
      return this.keyListener;
   }

   public static class ListStyle {
      public BitmapFont font;
      public Color fontColorSelected = new Color(1.0F, 1.0F, 1.0F, 1.0F);
      public Color fontColorUnselected = new Color(1.0F, 1.0F, 1.0F, 1.0F);
      public Drawable selection;
      @Null
      public Drawable down;
      @Null
      public Drawable over;
      @Null
      public Drawable background;

      public ListStyle() {
      }

      public ListStyle(BitmapFont font, Color fontColorSelected, Color fontColorUnselected, Drawable selection) {
         this.font = font;
         this.fontColorSelected.set(fontColorSelected);
         this.fontColorUnselected.set(fontColorUnselected);
         this.selection = selection;
      }

      public ListStyle(List.ListStyle style) {
         this.font = style.font;
         this.fontColorSelected.set(style.fontColorSelected);
         this.fontColorUnselected.set(style.fontColorUnselected);
         this.selection = style.selection;
         this.down = style.down;
         this.over = style.over;
         this.background = style.background;
      }
   }
}
