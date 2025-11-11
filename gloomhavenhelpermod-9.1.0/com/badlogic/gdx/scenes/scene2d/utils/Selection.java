package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.OrderedSet;
import com.badlogic.gdx.utils.Pools;
import java.util.Iterator;

public class Selection implements Disableable, Iterable {
   @Null
   private Actor actor;
   final OrderedSet selected = new OrderedSet();
   private final OrderedSet old = new OrderedSet();
   boolean isDisabled;
   private boolean toggle;
   boolean multiple;
   boolean required;
   private boolean programmaticChangeEvents = true;
   @Null
   Object lastSelected;

   public void setActor(@Null Actor actor) {
      this.actor = actor;
   }

   public void choose(Object item) {
      if (item == null) {
         throw new IllegalArgumentException("item cannot be null.");
      } else if (!this.isDisabled) {
         this.snapshot();

         try {
            if ((this.toggle || UIUtils.ctrl()) && this.selected.contains(item)) {
               if (this.required && this.selected.size == 1) {
                  return;
               }

               this.selected.remove(item);
               this.lastSelected = null;
            } else {
               boolean modified = false;
               if (!this.multiple || !this.toggle && !UIUtils.ctrl()) {
                  if (this.selected.size == 1 && this.selected.contains(item)) {
                     return;
                  }

                  modified = this.selected.size > 0;
                  this.selected.clear(8);
               }

               if (!this.selected.add(item) && !modified) {
                  return;
               }

               this.lastSelected = item;
            }

            if (this.fireChangeEvent()) {
               this.revert();
            } else {
               this.changed();
            }
         } finally {
            this.cleanup();
         }
      }
   }

   @Deprecated
   public boolean hasItems() {
      return this.selected.size > 0;
   }

   public boolean notEmpty() {
      return this.selected.size > 0;
   }

   public boolean isEmpty() {
      return this.selected.size == 0;
   }

   public int size() {
      return this.selected.size;
   }

   public OrderedSet items() {
      return this.selected;
   }

   @Null
   public Object first() {
      return this.selected.size == 0 ? null : this.selected.first();
   }

   void snapshot() {
      this.old.clear(this.selected.size);
      this.old.addAll(this.selected);
   }

   void revert() {
      this.selected.clear(this.old.size);
      this.selected.addAll(this.old);
   }

   void cleanup() {
      this.old.clear(32);
   }

   public void set(Object item) {
      if (item == null) {
         throw new IllegalArgumentException("item cannot be null.");
      } else if (this.selected.size != 1 || this.selected.first() != item) {
         this.snapshot();
         this.selected.clear(8);
         this.selected.add(item);
         if (this.programmaticChangeEvents && this.fireChangeEvent()) {
            this.revert();
         } else {
            this.lastSelected = item;
            this.changed();
         }

         this.cleanup();
      }
   }

   public void setAll(Array items) {
      boolean added = false;
      this.snapshot();
      this.lastSelected = null;
      this.selected.clear(items.size);
      int i = 0;

      for (int n = items.size; i < n; i++) {
         Object item = (T)items.get(i);
         if (item == null) {
            throw new IllegalArgumentException("item cannot be null.");
         }

         if (this.selected.add(item)) {
            added = true;
         }
      }

      if (added) {
         if (this.programmaticChangeEvents && this.fireChangeEvent()) {
            this.revert();
         } else if (items.size > 0) {
            this.lastSelected = items.peek();
            this.changed();
         }
      }

      this.cleanup();
   }

   public void add(Object item) {
      if (item == null) {
         throw new IllegalArgumentException("item cannot be null.");
      } else if (this.selected.add(item)) {
         if (this.programmaticChangeEvents && this.fireChangeEvent()) {
            this.selected.remove(item);
         } else {
            this.lastSelected = item;
            this.changed();
         }
      }
   }

   public void addAll(Array items) {
      boolean added = false;
      this.snapshot();
      int i = 0;

      for (int n = items.size; i < n; i++) {
         Object item = (T)items.get(i);
         if (item == null) {
            throw new IllegalArgumentException("item cannot be null.");
         }

         if (this.selected.add(item)) {
            added = true;
         }
      }

      if (added) {
         if (this.programmaticChangeEvents && this.fireChangeEvent()) {
            this.revert();
         } else {
            this.lastSelected = items.peek();
            this.changed();
         }
      }

      this.cleanup();
   }

   public void remove(Object item) {
      if (item == null) {
         throw new IllegalArgumentException("item cannot be null.");
      } else if (this.selected.remove(item)) {
         if (this.programmaticChangeEvents && this.fireChangeEvent()) {
            this.selected.add(item);
         } else {
            this.lastSelected = null;
            this.changed();
         }
      }
   }

   public void removeAll(Array items) {
      boolean removed = false;
      this.snapshot();
      int i = 0;

      for (int n = items.size; i < n; i++) {
         Object item = (T)items.get(i);
         if (item == null) {
            throw new IllegalArgumentException("item cannot be null.");
         }

         if (this.selected.remove(item)) {
            removed = true;
         }
      }

      if (removed) {
         if (this.programmaticChangeEvents && this.fireChangeEvent()) {
            this.revert();
         } else {
            this.lastSelected = null;
            this.changed();
         }
      }

      this.cleanup();
   }

   public void clear() {
      if (this.selected.size != 0) {
         this.snapshot();
         this.selected.clear(8);
         if (this.programmaticChangeEvents && this.fireChangeEvent()) {
            this.revert();
         } else {
            this.lastSelected = null;
            this.changed();
         }

         this.cleanup();
      }
   }

   protected void changed() {
   }

   public boolean fireChangeEvent() {
      if (this.actor == null) {
         return false;
      } else {
         ChangeListener.ChangeEvent changeEvent = (ChangeListener.ChangeEvent)Pools.obtain(ChangeListener.ChangeEvent.class);

         boolean var2;
         try {
            var2 = this.actor.fire(changeEvent);
         } finally {
            Pools.free(changeEvent);
         }

         return var2;
      }
   }

   public boolean contains(@Null Object item) {
      return item == null ? false : this.selected.contains(item);
   }

   @Null
   public Object getLastSelected() {
      if (this.lastSelected != null) {
         return this.lastSelected;
      } else {
         return this.selected.size > 0 ? this.selected.first() : null;
      }
   }

   @Override
   public Iterator iterator() {
      return this.selected.iterator();
   }

   public Array toArray() {
      return this.selected.iterator().toArray();
   }

   public Array toArray(Array array) {
      return this.selected.iterator().toArray(array);
   }

   @Override
   public void setDisabled(boolean isDisabled) {
      this.isDisabled = isDisabled;
   }

   @Override
   public boolean isDisabled() {
      return this.isDisabled;
   }

   public boolean getToggle() {
      return this.toggle;
   }

   public void setToggle(boolean toggle) {
      this.toggle = toggle;
   }

   public boolean getMultiple() {
      return this.multiple;
   }

   public void setMultiple(boolean multiple) {
      this.multiple = multiple;
   }

   public boolean getRequired() {
      return this.required;
   }

   public void setRequired(boolean required) {
      this.required = required;
   }

   public void setProgrammaticChangeEvents(boolean programmaticChangeEvents) {
      this.programmaticChangeEvents = programmaticChangeEvents;
   }

   @Override
   public String toString() {
      return this.selected.toString();
   }
}
