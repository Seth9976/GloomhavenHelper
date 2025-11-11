package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedSet.OrderedSetIterator;

public class ArraySelection extends Selection {
    private Array array;
    private boolean rangeSelect;
    private Object rangeStart;

    public ArraySelection(Array array0) {
        this.rangeSelect = true;
        this.array = array0;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Selection
    protected void changed() {
        this.rangeStart = null;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Selection
    public void choose(Object object0) {
        if(object0 == null) {
            throw new IllegalArgumentException("item cannot be null.");
        }
        if(this.isDisabled) {
            return;
        }
        if(this.rangeSelect && this.multiple) {
            if(this.selected.size > 0 && UIUtils.shift()) {
                int v = this.rangeStart == null ? -1 : this.array.indexOf(this.rangeStart, false);
                if(v != -1) {
                    Object object1 = this.rangeStart;
                    this.snapshot();
                    int v1 = this.array.indexOf(object0, false);
                    if(v <= v1) {
                        int v2 = v;
                        v = v1;
                        v1 = v2;
                    }
                    if(!UIUtils.ctrl()) {
                        this.selected.clear(8);
                    }
                    while(v1 <= v) {
                        this.selected.add(this.array.get(v1));
                        ++v1;
                    }
                    if(this.fireChangeEvent()) {
                        this.revert();
                    }
                    else {
                        this.changed();
                    }
                    this.rangeStart = object1;
                    this.cleanup();
                    return;
                }
            }
            super.choose(object0);
            this.rangeStart = object0;
            return;
        }
        super.choose(object0);
    }

    public boolean getRangeSelect() {
        return this.rangeSelect;
    }

    public void setRangeSelect(boolean z) {
        this.rangeSelect = z;
    }

    public void validate() {
        Array array0 = this.array;
        if(array0.size == 0) {
            this.clear();
            return;
        }
        boolean z = false;
        OrderedSetIterator orderedSet$OrderedSetIterator0 = this.items().iterator();
        while(orderedSet$OrderedSetIterator0.hasNext()) {
            Object object0 = orderedSet$OrderedSetIterator0.next();
            if(!array0.contains(object0, false)) {
                orderedSet$OrderedSetIterator0.remove();
                z = true;
            }
        }
        if(this.required && this.selected.size == 0) {
            this.set(array0.first());
            return;
        }
        if(z) {
            this.changed();
        }
    }
}

