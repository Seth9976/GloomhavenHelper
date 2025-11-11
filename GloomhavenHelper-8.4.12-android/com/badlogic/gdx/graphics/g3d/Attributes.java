package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.utils.Array;
import java.util.Comparator;
import java.util.Iterator;

public class Attributes implements Comparable, Iterable, Comparator {
    protected final Array attributes;
    protected long mask;
    protected boolean sorted;

    public Attributes() {
        this.attributes = new Array();
        this.sorted = true;
    }

    public int attributesHash() {
        this.sort();
        int v = this.attributes.size;
        long v1 = this.mask + 71L;
        int v2 = 1;
        for(int v3 = 0; v3 < v; ++v3) {
            v2 = v2 * 7 & 0xFFFF;
            v1 += this.mask * ((long)((Attribute)this.attributes.get(v3)).hashCode()) * ((long)v2);
        }
        return (int)(v1 ^ v1 >> 0x20);
    }

    public void clear() {
        this.mask = 0L;
        this.attributes.clear();
    }

    public final int compare(Attribute attribute0, Attribute attribute1) {
        return (int)(attribute0.type - attribute1.type);
    }

    @Override
    public int compare(Object object0, Object object1) {
        return this.compare(((Attribute)object0), ((Attribute)object1));
    }

    public int compareTo(Attributes attributes0) {
        if(attributes0 == this) {
            return 0;
        }
        long v = this.mask;
        long v1 = attributes0.mask;
        if(v != v1) {
            return v < v1 ? -1 : 1;
        }
        this.sort();
        attributes0.sort();
        for(int v2 = 0; v2 < this.attributes.size; ++v2) {
            int v3 = ((Attribute)this.attributes.get(v2)).compareTo(attributes0.attributes.get(v2));
            if(v3 != 0) {
                return v3 >= 0 ? 1 : -1;
            }
        }
        return 0;
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((Attributes)object0));
    }

    private final void disable(long v) {
        this.mask &= ~v;
    }

    private final void enable(long v) {
        this.mask |= v;
    }

    @Override
    public boolean equals(Object object0) {
        if(!(object0 instanceof Attributes)) {
            return false;
        }
        return object0 == this ? true : this.same(((Attributes)object0), true);
    }

    public final Attribute get(long v) {
        if(this.has(v)) {
            for(int v1 = 0; v1 < this.attributes.size; ++v1) {
                if(((Attribute)this.attributes.get(v1)).type == v) {
                    return (Attribute)this.attributes.get(v1);
                }
            }
        }
        return null;
    }

    public final Attribute get(Class class0, long v) {
        return this.get(v);
    }

    public final Array get(Array array0, long v) {
        for(int v1 = 0; v1 < this.attributes.size; ++v1) {
            if((((Attribute)this.attributes.get(v1)).type & v) != 0L) {
                array0.add(this.attributes.get(v1));
            }
        }
        return array0;
    }

    public final long getMask() {
        return this.mask;
    }

    public final boolean has(long v) {
        return v != 0L && (this.mask & v) == v;
    }

    @Override
    public int hashCode() {
        return this.attributesHash();
    }

    protected int indexOf(long v) {
        if(this.has(v)) {
            for(int v1 = 0; v1 < this.attributes.size; ++v1) {
                if(((Attribute)this.attributes.get(v1)).type == v) {
                    return v1;
                }
            }
        }
        return -1;
    }

    @Override
    public final Iterator iterator() {
        return this.attributes.iterator();
    }

    public final void remove(long v) {
        for(int v1 = this.attributes.size - 1; v1 >= 0; --v1) {
            long v2 = ((Attribute)this.attributes.get(v1)).type;
            if((v & v2) == v2) {
                this.attributes.removeIndex(v1);
                this.disable(v2);
                this.sorted = false;
            }
        }
        this.sort();
    }

    public final boolean same(Attributes attributes0) {
        return this.same(attributes0, false);
    }

    public final boolean same(Attributes attributes0, boolean z) {
        if(attributes0 == this) {
            return true;
        }
        if(attributes0 != null && this.mask == attributes0.mask) {
            if(!z) {
                return true;
            }
            this.sort();
            attributes0.sort();
            for(int v = 0; v < this.attributes.size; ++v) {
                if(!((Attribute)this.attributes.get(v)).equals(((Attribute)attributes0.attributes.get(v)))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public final void set(Attribute attribute0) {
        int v = this.indexOf(attribute0.type);
        if(v < 0) {
            this.enable(attribute0.type);
            this.attributes.add(attribute0);
            this.sorted = false;
        }
        else {
            this.attributes.set(v, attribute0);
        }
        this.sort();
    }

    public final void set(Attribute attribute0, Attribute attribute1) {
        this.set(attribute0);
        this.set(attribute1);
    }

    public final void set(Attribute attribute0, Attribute attribute1, Attribute attribute2) {
        this.set(attribute0);
        this.set(attribute1);
        this.set(attribute2);
    }

    public final void set(Attribute attribute0, Attribute attribute1, Attribute attribute2, Attribute attribute3) {
        this.set(attribute0);
        this.set(attribute1);
        this.set(attribute2);
        this.set(attribute3);
    }

    public final void set(Iterable iterable0) {
        for(Object object0: iterable0) {
            this.set(((Attribute)object0));
        }
    }

    public final void set(Attribute[] arr_attribute) {
        for(int v = 0; v < arr_attribute.length; ++v) {
            this.set(arr_attribute[v]);
        }
    }

    public int size() {
        return this.attributes.size;
    }

    public final void sort() {
        if(!this.sorted) {
            this.attributes.sort(this);
            this.sorted = true;
        }
    }
}

