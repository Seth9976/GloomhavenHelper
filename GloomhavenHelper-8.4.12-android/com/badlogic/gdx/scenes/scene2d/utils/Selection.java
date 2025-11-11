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
    boolean isDisabled;
    @Null
    Object lastSelected;
    boolean multiple;
    private final OrderedSet old;
    private boolean programmaticChangeEvents;
    boolean required;
    final OrderedSet selected;
    private boolean toggle;

    public Selection() {
        this.selected = new OrderedSet();
        this.old = new OrderedSet();
        this.programmaticChangeEvents = true;
    }

    public void add(Object object0) {
        if(object0 == null) {
            throw new IllegalArgumentException("item cannot be null.");
        }
        if(!this.selected.add(object0)) {
            return;
        }
        if(this.programmaticChangeEvents && this.fireChangeEvent()) {
            this.selected.remove(object0);
            return;
        }
        this.lastSelected = object0;
        this.changed();
    }

    public void addAll(Array array0) {
        this.snapshot();
        int v = array0.size;
        boolean z = false;
        for(int v1 = 0; v1 < v; ++v1) {
            Object object0 = array0.get(v1);
            if(object0 == null) {
                throw new IllegalArgumentException("item cannot be null.");
            }
            if(this.selected.add(object0)) {
                z = true;
            }
        }
        if(z) {
            if(!this.programmaticChangeEvents || !this.fireChangeEvent()) {
                this.lastSelected = array0.peek();
                this.changed();
            }
            else {
                this.revert();
            }
        }
        this.cleanup();
    }

    protected void changed() {
    }

    public void choose(Object object0) {
        if(object0 == null) {
            throw new IllegalArgumentException("item cannot be null.");
        }
        if(this.isDisabled) {
            return;
        }
        boolean z = false;
        this.snapshot();
        try {
            if(!this.toggle && !UIUtils.ctrl() || !this.selected.contains(object0)) {
                if(!this.multiple || !this.toggle && !UIUtils.ctrl()) {
                    if(this.selected.size == 1 && this.selected.contains(object0)) {
                        return;
                    }
                    if(this.selected.size > 0) {
                        z = true;
                    }
                    this.selected.clear(8);
                }
                if(!this.selected.add(object0) && !z) {
                    return;
                }
                this.lastSelected = object0;
            }
            else {
                if(this.required && this.selected.size == 1) {
                    return;
                }
                this.selected.remove(object0);
                this.lastSelected = null;
            }
            if(this.fireChangeEvent()) {
                this.revert();
            }
            else {
                this.changed();
            }
        }
        finally {
            this.cleanup();
        }
    }

    void cleanup() {
        this.old.clear(0x20);
    }

    public void clear() {
        if(this.selected.size == 0) {
            return;
        }
        this.snapshot();
        this.selected.clear(8);
        if(!this.programmaticChangeEvents || !this.fireChangeEvent()) {
            this.lastSelected = null;
            this.changed();
        }
        else {
            this.revert();
        }
        this.cleanup();
    }

    public boolean contains(@Null Object object0) {
        return object0 == null ? false : this.selected.contains(object0);
    }

    public boolean fireChangeEvent() {
        if(this.actor == null) {
            return false;
        }
        ChangeEvent changeListener$ChangeEvent0 = (ChangeEvent)Pools.obtain(ChangeEvent.class);
        try {
            return this.actor.fire(changeListener$ChangeEvent0);
        }
        finally {
            Pools.free(changeListener$ChangeEvent0);
        }
    }

    @Null
    public Object first() {
        return this.selected.size == 0 ? null : this.selected.first();
    }

    @Null
    public Object getLastSelected() {
        Object object0 = this.lastSelected;
        if(object0 != null) {
            return object0;
        }
        return this.selected.size <= 0 ? null : this.selected.first();
    }

    public boolean getMultiple() {
        return this.multiple;
    }

    public boolean getRequired() {
        return this.required;
    }

    public boolean getToggle() {
        return this.toggle;
    }

    @Deprecated
    public boolean hasItems() {
        return this.selected.size > 0;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public boolean isDisabled() {
        return this.isDisabled;
    }

    public boolean isEmpty() {
        return this.selected.size == 0;
    }

    public OrderedSet items() {
        return this.selected;
    }

    @Override
    public Iterator iterator() {
        return this.selected.iterator();
    }

    public boolean notEmpty() {
        return this.selected.size > 0;
    }

    public void remove(Object object0) {
        if(object0 == null) {
            throw new IllegalArgumentException("item cannot be null.");
        }
        if(!this.selected.remove(object0)) {
            return;
        }
        if(this.programmaticChangeEvents && this.fireChangeEvent()) {
            this.selected.add(object0);
            return;
        }
        this.lastSelected = null;
        this.changed();
    }

    public void removeAll(Array array0) {
        this.snapshot();
        int v = array0.size;
        boolean z = false;
        for(int v1 = 0; v1 < v; ++v1) {
            Object object0 = array0.get(v1);
            if(object0 == null) {
                throw new IllegalArgumentException("item cannot be null.");
            }
            if(this.selected.remove(object0)) {
                z = true;
            }
        }
        if(z) {
            if(!this.programmaticChangeEvents || !this.fireChangeEvent()) {
                this.lastSelected = null;
                this.changed();
            }
            else {
                this.revert();
            }
        }
        this.cleanup();
    }

    void revert() {
        this.selected.clear(this.old.size);
        this.selected.addAll(this.old);
    }

    public void set(Object object0) {
        if(object0 == null) {
            throw new IllegalArgumentException("item cannot be null.");
        }
        if(this.selected.size == 1 && this.selected.first() == object0) {
            return;
        }
        this.snapshot();
        this.selected.clear(8);
        this.selected.add(object0);
        if(!this.programmaticChangeEvents || !this.fireChangeEvent()) {
            this.lastSelected = object0;
            this.changed();
        }
        else {
            this.revert();
        }
        this.cleanup();
    }

    public void setActor(@Null Actor actor0) {
        this.actor = actor0;
    }

    public void setAll(Array array0) {
        this.snapshot();
        this.lastSelected = null;
        this.selected.clear(array0.size);
        int v = array0.size;
        boolean z = false;
        for(int v1 = 0; v1 < v; ++v1) {
            Object object0 = array0.get(v1);
            if(object0 == null) {
                throw new IllegalArgumentException("item cannot be null.");
            }
            if(this.selected.add(object0)) {
                z = true;
            }
        }
        if(z) {
            if(this.programmaticChangeEvents && this.fireChangeEvent()) {
                this.revert();
            }
            else if(array0.size > 0) {
                this.lastSelected = array0.peek();
                this.changed();
            }
        }
        this.cleanup();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public void setDisabled(boolean z) {
        this.isDisabled = z;
    }

    public void setMultiple(boolean z) {
        this.multiple = z;
    }

    public void setProgrammaticChangeEvents(boolean z) {
        this.programmaticChangeEvents = z;
    }

    public void setRequired(boolean z) {
        this.required = z;
    }

    public void setToggle(boolean z) {
        this.toggle = z;
    }

    public int size() {
        return this.selected.size;
    }

    void snapshot() {
        this.old.clear(this.selected.size);
        this.old.addAll(this.selected);
    }

    public Array toArray() {
        return this.selected.iterator().toArray();
    }

    public Array toArray(Array array0) {
        return this.selected.iterator().toArray(array0);
    }

    @Override
    public String toString() {
        return this.selected.toString();
    }
}

