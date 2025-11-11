package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.utils.Array;
import java.util.Iterator;

public class ArraySelection extends Selection {
   private Array array;
   private boolean rangeSelect = true;
   private Object rangeStart;

   public ArraySelection(Array array) {
      this.array = array;
   }

   @Override
   public void choose(Object item) {
      if (item == null) {
         throw new IllegalArgumentException("item cannot be null.");
      } else if (!this.isDisabled) {
         if (this.rangeSelect && this.multiple) {
            if (this.selected.size > 0 && UIUtils.shift()) {
               int rangeStartIndex = this.rangeStart == null ? -1 : this.array.indexOf(this.rangeStart, false);
               if (rangeStartIndex != -1) {
                  Object oldRangeStart = (T)this.rangeStart;
                  this.snapshot();
                  int start = rangeStartIndex;
                  int end = this.array.indexOf(item, false);
                  if (rangeStartIndex > end) {
                     int temp = end;
                     end = rangeStartIndex;
                     start = temp;
                  }

                  if (!UIUtils.ctrl()) {
                     this.selected.clear(8);
                  }

                  for (int i = start; i <= end; i++) {
                     this.selected.add(this.array.get(i));
                  }

                  if (this.fireChangeEvent()) {
                     this.revert();
                  } else {
                     this.changed();
                  }

                  this.rangeStart = oldRangeStart;
                  this.cleanup();
                  return;
               }
            }

            super.choose(item);
            this.rangeStart = item;
         } else {
            super.choose(item);
         }
      }
   }

   @Override
   protected void changed() {
      this.rangeStart = null;
   }

   public boolean getRangeSelect() {
      return this.rangeSelect;
   }

   public void setRangeSelect(boolean rangeSelect) {
      this.rangeSelect = rangeSelect;
   }

   public void validate() {
      Array array = this.array;
      if (array.size == 0) {
         this.clear();
      } else {
         boolean changed = false;
         Iterator iter = this.items().iterator();

         while (iter.hasNext()) {
            Object selected = (T)iter.next();
            if (!array.contains(selected, false)) {
               iter.remove();
               changed = true;
            }
         }

         if (this.required && this.selected.size == 0) {
            this.set(array.first());
         } else if (changed) {
            this.changed();
         }
      }
   }
}
