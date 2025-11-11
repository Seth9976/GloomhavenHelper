package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.Array;

public class CumulativeDistribution {
    public class CumulativeValue {
        public float frequency;
        public float interval;
        public Object value;

        public CumulativeValue(Object object0, float f, float f1) {
            this.value = object0;
            this.frequency = f;
            this.interval = f1;
        }
    }

    private Array values;

    public CumulativeDistribution() {
        this.values = new Array(false, 10, CumulativeValue.class);
    }

    public void add(Object object0) {
        this.values.add(new CumulativeValue(this, object0, 0.0f, 0.0f));
    }

    public void add(Object object0, float f) {
        this.values.add(new CumulativeValue(this, object0, 0.0f, f));
    }

    public void clear() {
        this.values.clear();
    }

    public void generate() {
        float f = 0.0f;
        for(int v = 0; v < this.values.size; ++v) {
            f += ((CumulativeValue[])this.values.items)[v].interval;
            ((CumulativeValue[])this.values.items)[v].frequency = f;
        }
    }

    public void generateNormalized() {
        float f = 0.0f;
        float f1 = 0.0f;
        for(int v1 = 0; v1 < this.values.size; ++v1) {
            f1 += ((CumulativeValue[])this.values.items)[v1].interval;
        }
        for(int v = 0; v < this.values.size; ++v) {
            f += ((CumulativeValue[])this.values.items)[v].interval / f1;
            ((CumulativeValue[])this.values.items)[v].frequency = f;
        }
    }

    public void generateUniform() {
        float f = 1.0f / ((float)this.values.size);
        int v = 0;
        while(v < this.values.size) {
            ((CumulativeValue[])this.values.items)[v].interval = f;
            CumulativeValue cumulativeDistribution$CumulativeValue0 = ((CumulativeValue[])this.values.items)[v];
            ++v;
            cumulativeDistribution$CumulativeValue0.frequency = ((float)v) * f;
        }
    }

    public float getInterval(int v) {
        return ((CumulativeValue[])this.values.items)[v].interval;
    }

    public Object getValue(int v) {
        return ((CumulativeValue[])this.values.items)[v].value;
    }

    public void setInterval(int v, float f) {
        ((CumulativeValue[])this.values.items)[v].interval = f;
    }

    public void setInterval(Object object0, float f) {
        for(Object object1: this.values) {
            CumulativeValue cumulativeDistribution$CumulativeValue0 = (CumulativeValue)object1;
            if(cumulativeDistribution$CumulativeValue0.value == object0) {
                cumulativeDistribution$CumulativeValue0.interval = f;
                return;
            }
            if(false) {
                break;
            }
        }
    }

    public int size() {
        return this.values.size;
    }

    public Object value() {
        return this.value(MathUtils.random());
    }

    public Object value(float f) {
        int v = this.values.size - 1;
        int v1 = 0;
        while(v1 <= v) {
            int v2 = (v - v1) / 2 + v1;
            CumulativeValue cumulativeDistribution$CumulativeValue0 = ((CumulativeValue[])this.values.items)[v2];
            if(f < cumulativeDistribution$CumulativeValue0.frequency) {
                v = v2 - 1;
            }
            else {
                if(f <= cumulativeDistribution$CumulativeValue0.frequency) {
                    break;
                }
                v1 = v2 + 1;
            }
        }
        return ((CumulativeValue[])this.values.items)[v1].value;
    }
}

