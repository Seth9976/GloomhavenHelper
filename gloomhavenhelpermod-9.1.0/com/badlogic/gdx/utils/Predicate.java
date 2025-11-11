package com.badlogic.gdx.utils;

import java.util.Iterator;

public interface Predicate {
   boolean evaluate(Object var1);

   public static class PredicateIterable implements Iterable {
      public Iterable iterable;
      public Predicate predicate;
      public Predicate.PredicateIterator iterator = null;

      public PredicateIterable(Iterable iterable, Predicate predicate) {
         this.set(iterable, predicate);
      }

      public void set(Iterable iterable, Predicate predicate) {
         this.iterable = iterable;
         this.predicate = predicate;
      }

      @Override
      public Iterator iterator() {
         if (Collections.allocateIterators) {
            return new Predicate.PredicateIterator(this.iterable.iterator(), this.predicate);
         } else {
            if (this.iterator == null) {
               this.iterator = new Predicate.PredicateIterator(this.iterable.iterator(), this.predicate);
            } else {
               this.iterator.set(this.iterable.iterator(), this.predicate);
            }

            return this.iterator;
         }
      }
   }

   public static class PredicateIterator implements Iterator {
      public Iterator iterator;
      public Predicate predicate;
      public boolean end = false;
      public boolean peeked = false;
      public Object next = null;

      public PredicateIterator(Iterable iterable, Predicate predicate) {
         this(iterable.iterator(), predicate);
      }

      public PredicateIterator(Iterator iterator, Predicate predicate) {
         this.set(iterator, predicate);
      }

      public void set(Iterable iterable, Predicate predicate) {
         this.set(iterable.iterator(), predicate);
      }

      public void set(Iterator iterator, Predicate predicate) {
         this.iterator = iterator;
         this.predicate = predicate;
         this.end = this.peeked = false;
         this.next = null;
      }

      @Override
      public boolean hasNext() {
         if (this.end) {
            return false;
         } else if (this.next != null) {
            return true;
         } else {
            this.peeked = true;

            while (this.iterator.hasNext()) {
               Object n = (T)this.iterator.next();
               if (this.predicate.evaluate(n)) {
                  this.next = n;
                  return true;
               }
            }

            this.end = true;
            return false;
         }
      }

      @Override
      public Object next() {
         if (this.next == null && !this.hasNext()) {
            return null;
         } else {
            Object result = (T)this.next;
            this.next = null;
            this.peeked = false;
            return result;
         }
      }

      @Override
      public void remove() {
         if (this.peeked) {
            throw new GdxRuntimeException("Cannot remove between a call to hasNext() and next().");
         } else {
            this.iterator.remove();
         }
      }
   }
}
