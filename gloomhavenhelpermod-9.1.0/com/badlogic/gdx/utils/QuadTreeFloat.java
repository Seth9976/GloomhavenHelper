package com.badlogic.gdx.utils;

import java.util.Arrays;

public class QuadTreeFloat implements Pool.Poolable {
   public static final int VALUE = 0;
   public static final int X = 1;
   public static final int Y = 2;
   public static final int DISTSQR = 3;
   private static final Pool pool = new Pool(128, 4096) {
      @Override
      protected Object newObject() {
         return new QuadTreeFloat();
      }
   };
   public final int maxValues;
   public final int maxDepth;
   public float x;
   public float y;
   public float width;
   public float height;
   public int depth;
   @Null
   public QuadTreeFloat nw;
   @Null
   public QuadTreeFloat ne;
   @Null
   public QuadTreeFloat sw;
   @Null
   public QuadTreeFloat se;
   public float[] values;
   public int count;

   public QuadTreeFloat() {
      this(16, 8);
   }

   public QuadTreeFloat(int maxValues, int maxDepth) {
      this.maxValues = maxValues * 3;
      this.maxDepth = maxDepth;
      this.values = new float[this.maxValues];
   }

   public void setBounds(float x, float y, float width, float height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
   }

   public void add(float value, float valueX, float valueY) {
      int count = this.count;
      if (count == -1) {
         this.addToChild(value, valueX, valueY);
      } else {
         if (this.depth < this.maxDepth) {
            if (count == this.maxValues) {
               this.split(value, valueX, valueY);
               return;
            }
         } else if (count == this.values.length) {
            this.values = Arrays.copyOf(this.values, this.growValues());
         }

         this.values[count] = value;
         this.values[count + 1] = valueX;
         this.values[count + 2] = valueY;
         this.count += 3;
      }
   }

   private void split(float value, float valueX, float valueY) {
      float[] values = this.values;

      for (int i = 0; i < this.maxValues; i += 3) {
         this.addToChild(values[i], values[i + 1], values[i + 2]);
      }

      this.count = -1;
      this.addToChild(value, valueX, valueY);
   }

   private void addToChild(float value, float valueX, float valueY) {
      float halfWidth = this.width / 2.0F;
      float halfHeight = this.height / 2.0F;
      QuadTreeFloat child;
      if (valueX < this.x + halfWidth) {
         if (valueY < this.y + halfHeight) {
            child = this.sw != null ? this.sw : (this.sw = this.obtainChild(this.x, this.y, halfWidth, halfHeight, this.depth + 1));
         } else {
            child = this.nw != null ? this.nw : (this.nw = this.obtainChild(this.x, this.y + halfHeight, halfWidth, halfHeight, this.depth + 1));
         }
      } else if (valueY < this.y + halfHeight) {
         child = this.se != null ? this.se : (this.se = this.obtainChild(this.x + halfWidth, this.y, halfWidth, halfHeight, this.depth + 1));
      } else {
         child = this.ne != null ? this.ne : (this.ne = this.obtainChild(this.x + halfWidth, this.y + halfHeight, halfWidth, halfHeight, this.depth + 1));
      }

      child.add(value, valueX, valueY);
   }

   private QuadTreeFloat obtainChild(float x, float y, float width, float height, int depth) {
      QuadTreeFloat child = (QuadTreeFloat)pool.obtain();
      child.x = x;
      child.y = y;
      child.width = width;
      child.height = height;
      child.depth = depth;
      return child;
   }

   protected int growValues() {
      return this.count + 30;
   }

   public void query(float centerX, float centerY, float radius, FloatArray results) {
      this.query(centerX, centerY, radius * radius, centerX - radius, centerY - radius, radius * 2.0F, results);
   }

   private void query(float centerX, float centerY, float radiusSqr, float rectX, float rectY, float rectSize, FloatArray results) {
      if (this.x < rectX + rectSize && this.x + this.width > rectX && this.y < rectY + rectSize && this.y + this.height > rectY) {
         int count = this.count;
         if (count != -1) {
            float[] values = this.values;

            for (int i = 1; i < count; i += 3) {
               float px = values[i];
               float py = values[i + 1];
               float dx = px - centerX;
               float dy = py - centerY;
               float d = dx * dx + dy * dy;
               if (d <= radiusSqr) {
                  results.add(values[i - 1]);
                  results.add(px);
                  results.add(py);
                  results.add(d);
               }
            }
         } else {
            if (this.nw != null) {
               this.nw.query(centerX, centerY, radiusSqr, rectX, rectY, rectSize, results);
            }

            if (this.sw != null) {
               this.sw.query(centerX, centerY, radiusSqr, rectX, rectY, rectSize, results);
            }

            if (this.ne != null) {
               this.ne.query(centerX, centerY, radiusSqr, rectX, rectY, rectSize, results);
            }

            if (this.se != null) {
               this.se.query(centerX, centerY, radiusSqr, rectX, rectY, rectSize, results);
            }
         }
      }
   }

   public boolean nearest(float x, float y, FloatArray result) {
      result.clear();
      result.add(0.0F);
      result.add(0.0F);
      result.add(0.0F);
      result.add(Float.POSITIVE_INFINITY);
      this.findNearestInternal(x, y, result);
      float nearValue = result.first();
      float nearX = result.get(1);
      float nearY = result.get(2);
      float nearDist = result.get(3);
      boolean found = nearDist != Float.POSITIVE_INFINITY;
      if (!found) {
         nearDist = Math.max(this.width, this.height);
         nearDist *= nearDist;
      }

      result.clear();
      this.query(x, y, (float)Math.sqrt(nearDist), result);
      int i = 3;

      for (int n = result.size; i < n; i += 4) {
         float dist = result.get(i);
         if (dist < nearDist) {
            nearDist = dist;
            nearValue = result.get(i - 3);
            nearX = result.get(i - 2);
            nearY = result.get(i - 1);
         }
      }

      if (!found && result.isEmpty()) {
         return false;
      } else {
         result.clear();
         result.add(nearValue);
         result.add(nearX);
         result.add(nearY);
         result.add(nearDist);
         return true;
      }
   }

   private void findNearestInternal(float x, float y, FloatArray result) {
      if (this.x < x && this.x + this.width > x && this.y < y && this.y + this.height > y) {
         int count = this.count;
         if (count != -1) {
            float nearValue = result.first();
            float nearX = result.get(1);
            float nearY = result.get(2);
            float nearDist = result.get(3);
            float[] values = this.values;

            for (int i = 1; i < count; i += 3) {
               float px = values[i];
               float py = values[i + 1];
               float dx = px - x;
               float dy = py - y;
               float dist = dx * dx + dy * dy;
               if (dist < nearDist) {
                  nearDist = dist;
                  nearValue = values[i - 1];
                  nearX = px;
                  nearY = py;
               }
            }

            result.set(0, nearValue);
            result.set(1, nearX);
            result.set(2, nearY);
            result.set(3, nearDist);
         } else {
            if (this.nw != null) {
               this.nw.findNearestInternal(x, y, result);
            }

            if (this.sw != null) {
               this.sw.findNearestInternal(x, y, result);
            }

            if (this.ne != null) {
               this.ne.findNearestInternal(x, y, result);
            }

            if (this.se != null) {
               this.se.findNearestInternal(x, y, result);
            }
         }
      }
   }

   @Override
   public void reset() {
      if (this.count == -1) {
         if (this.nw != null) {
            pool.free(this.nw);
            this.nw = null;
         }

         if (this.sw != null) {
            pool.free(this.sw);
            this.sw = null;
         }

         if (this.ne != null) {
            pool.free(this.ne);
            this.ne = null;
         }

         if (this.se != null) {
            pool.free(this.se);
            this.se = null;
         }
      }

      this.count = 0;
      if (this.values.length > this.maxValues) {
         this.values = new float[this.maxValues];
      }
   }
}
