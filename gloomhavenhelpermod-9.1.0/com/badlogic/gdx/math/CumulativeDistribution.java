package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.Array;

public class CumulativeDistribution {
   private Array values = new Array(false, 10, CumulativeDistribution.CumulativeValue.class);

   public void add(Object value, float intervalSize) {
      this.values.add(new CumulativeDistribution.CumulativeValue(value, 0.0F, intervalSize));
   }

   public void add(Object value) {
      this.values.add(new CumulativeDistribution.CumulativeValue(value, 0.0F, 0.0F));
   }

   public void generate() {
      float sum = 0.0F;

      for (int i = 0; i < this.values.size; i++) {
         sum += ((CumulativeDistribution.CumulativeValue[])this.values.items)[i].interval;
         ((CumulativeDistribution.CumulativeValue[])this.values.items)[i].frequency = sum;
      }
   }

   public void generateNormalized() {
      float sum = 0.0F;

      for (int i = 0; i < this.values.size; i++) {
         sum += ((CumulativeDistribution.CumulativeValue[])this.values.items)[i].interval;
      }

      float intervalSum = 0.0F;

      for (int i = 0; i < this.values.size; i++) {
         intervalSum += ((CumulativeDistribution.CumulativeValue[])this.values.items)[i].interval / sum;
         ((CumulativeDistribution.CumulativeValue[])this.values.items)[i].frequency = intervalSum;
      }
   }

   public void generateUniform() {
      float freq = 1.0F / this.values.size;

      for (int i = 0; i < this.values.size; i++) {
         ((CumulativeDistribution.CumulativeValue[])this.values.items)[i].interval = freq;
         ((CumulativeDistribution.CumulativeValue[])this.values.items)[i].frequency = (i + 1) * freq;
      }
   }

   public Object value(float probability) {
      CumulativeDistribution.CumulativeValue value = null;
      int imax = this.values.size - 1;
      int imin = 0;

      while (imin <= imax) {
         int imid = imin + (imax - imin) / 2;
         value = ((CumulativeDistribution.CumulativeValue[])this.values.items)[imid];
         if (probability < value.frequency) {
            imax = imid - 1;
         } else {
            if (!(probability > value.frequency)) {
               break;
            }

            imin = imid + 1;
         }
      }

      return ((CumulativeDistribution.CumulativeValue[])this.values.items)[imin].value;
   }

   public Object value() {
      return this.value(MathUtils.random());
   }

   public int size() {
      return this.values.size;
   }

   public float getInterval(int index) {
      return ((CumulativeDistribution.CumulativeValue[])this.values.items)[index].interval;
   }

   public Object getValue(int index) {
      return ((CumulativeDistribution.CumulativeValue[])this.values.items)[index].value;
   }

   public void setInterval(Object obj, float intervalSize) {
      for (CumulativeDistribution.CumulativeValue value : this.values) {
         if (value.value == obj) {
            value.interval = intervalSize;
            return;
         }
      }
   }

   public void setInterval(int index, float intervalSize) {
      ((CumulativeDistribution.CumulativeValue[])this.values.items)[index].interval = intervalSize;
   }

   public void clear() {
      this.values.clear();
   }

   public class CumulativeValue {
      public Object value;
      public float frequency;
      public float interval;

      public CumulativeValue(Object value, float frequency, float interval) {
         this.value = value;
         this.frequency = frequency;
         this.interval = interval;
      }
   }
}
