package com.badlogic.gdx.utils;

import java.util.Iterator;

public interface Predicate {
    public static class PredicateIterable implements Iterable {
        public Iterable iterable;
        public PredicateIterator iterator;
        public Predicate predicate;

        public PredicateIterable(Iterable iterable0, Predicate predicate0) {
            this.iterator = null;
            this.set(iterable0, predicate0);
        }

        @Override
        public Iterator iterator() {
            if(Collections.allocateIterators) {
                return new PredicateIterator(this.iterable.iterator(), this.predicate);
            }
            PredicateIterator predicate$PredicateIterator0 = this.iterator;
            if(predicate$PredicateIterator0 == null) {
                this.iterator = new PredicateIterator(this.iterable.iterator(), this.predicate);
                return this.iterator;
            }
            predicate$PredicateIterator0.set(this.iterable.iterator(), this.predicate);
            return this.iterator;
        }

        public void set(Iterable iterable0, Predicate predicate0) {
            this.iterable = iterable0;
            this.predicate = predicate0;
        }
    }

    public static class PredicateIterator implements Iterator {
        public boolean end;
        public Iterator iterator;
        public Object next;
        public boolean peeked;
        public Predicate predicate;

        public PredicateIterator(Iterable iterable0, Predicate predicate0) {
            this(iterable0.iterator(), predicate0);
        }

        public PredicateIterator(Iterator iterator0, Predicate predicate0) {
            this.end = false;
            this.peeked = false;
            this.next = null;
            this.set(iterator0, predicate0);
        }

        @Override
        public boolean hasNext() {
            if(this.end) {
                return false;
            }
            if(this.next != null) {
                return true;
            }
            this.peeked = true;
            while(this.iterator.hasNext()) {
                Object object0 = this.iterator.next();
                if(this.predicate.evaluate(object0)) {
                    this.next = object0;
                    return true;
                }
                if(false) {
                    break;
                }
            }
            this.end = true;
            return false;
        }

        @Override
        public Object next() {
            if(this.next == null && !this.hasNext()) {
                return null;
            }
            Object object0 = this.next;
            this.next = null;
            this.peeked = false;
            return object0;
        }

        @Override
        public void remove() {
            if(this.peeked) {
                throw new GdxRuntimeException("Cannot remove between a call to hasNext() and next().");
            }
            this.iterator.remove();
        }

        public void set(Iterable iterable0, Predicate predicate0) {
            this.set(iterable0.iterator(), predicate0);
        }

        public void set(Iterator iterator0, Predicate predicate0) {
            this.iterator = iterator0;
            this.predicate = predicate0;
            this.peeked = false;
            this.end = false;
            this.next = null;
        }
    }

    boolean evaluate(Object arg1);
}

