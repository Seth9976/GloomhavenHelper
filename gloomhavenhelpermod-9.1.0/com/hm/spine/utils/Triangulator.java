package com.hm.spine.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BooleanArray;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.ShortArray;

class Triangulator {
   private final Array convexPolygons = new Array(false, 16);
   private final Array convexPolygonsIndices = new Array(false, 16);
   private final ShortArray indicesArray = new ShortArray();
   private final BooleanArray isConcaveArray = new BooleanArray();
   private final ShortArray triangles = new ShortArray();
   private final Pool polygonPool = new Pool() {
      protected FloatArray newObject() {
         return new FloatArray(16);
      }
   };
   private final Pool polygonIndicesPool = new Pool() {
      protected ShortArray newObject() {
         return new ShortArray(16);
      }
   };

   public ShortArray triangulate(FloatArray verticesArray) {
      float[] vertices = verticesArray.items;
      int vertexCount = verticesArray.size >> 1;
      ShortArray indicesArray = this.indicesArray;
      indicesArray.clear();
      short[] indices = indicesArray.setSize(vertexCount);
      short i = 0;

      while (i < vertexCount) {
         indices[i] = i++;
      }

      BooleanArray isConcaveArray = this.isConcaveArray;
      boolean[] isConcave = isConcaveArray.setSize(vertexCount);
      int j = 0;

      for (int n = vertexCount; j < n; j++) {
         isConcave[j] = isConcave(j, vertexCount, vertices, indices);
      }

      ShortArray triangles = this.triangles;
      triangles.clear();
      triangles.ensureCapacity(Math.max(0, vertexCount - 2) << 2);

      while (vertexCount > 3) {
         int previous = vertexCount - 1;
         int k = 0;

         label80: {
            int next;
            for (next = 1; isConcave[k]; next = (next + 1) % vertexCount) {
               if (next == 0) {
                  while (true) {
                     k--;
                     if (!isConcave[k] || k <= 0) {
                        break label80;
                     }
                  }
               }

               previous = k;
               k = next;
            }

            int p1 = indices[previous] << 1;
            int p2 = indices[k] << 1;
            int p3 = indices[next] << 1;
            float p1x = vertices[p1];
            float p1y = vertices[p1 + 1];
            float p2x = vertices[p2];
            float p2y = vertices[p2 + 1];
            float p3x = vertices[p3];
            float p3y = vertices[p3 + 1];

            for (int ii = (next + 1) % vertexCount; ii != previous; ii = (ii + 1) % vertexCount) {
               if (isConcave[ii]) {
                  int v = indices[ii] << 1;
                  float vx = vertices[v];
                  float vy = vertices[v + 1];
                  if (positiveArea(p3x, p3y, p1x, p1y, vx, vy) && positiveArea(p1x, p1y, p2x, p2y, vx, vy) && positiveArea(p2x, p2y, p3x, p3y, vx, vy)) {
                  }
               }
            }
         }

         triangles.add(indices[(vertexCount + k - 1) % vertexCount]);
         triangles.add(indices[k]);
         triangles.add(indices[(k + 1) % vertexCount]);
         indicesArray.removeIndex(k);
         isConcaveArray.removeIndex(k);
         vertexCount--;
         int previousIndex = (vertexCount + k - 1) % vertexCount;
         int nextIndex = k == vertexCount ? 0 : k;
         isConcave[previousIndex] = isConcave(previousIndex, vertexCount, vertices, indices);
         isConcave[nextIndex] = isConcave(nextIndex, vertexCount, vertices, indices);
      }

      if (vertexCount == 3) {
         triangles.add(indices[2]);
         triangles.add(indices[0]);
         triangles.add(indices[1]);
      }

      return triangles;
   }

   public Array decompose(FloatArray verticesArray, ShortArray triangles) {
      float[] vertices = verticesArray.items;
      Array convexPolygons = this.convexPolygons;
      this.polygonPool.freeAll(convexPolygons);
      convexPolygons.clear();
      Array convexPolygonsIndices = this.convexPolygonsIndices;
      this.polygonIndicesPool.freeAll(convexPolygonsIndices);
      convexPolygonsIndices.clear();
      ShortArray polygonIndices = (ShortArray)this.polygonIndicesPool.obtain();
      polygonIndices.clear();
      FloatArray polygon = (FloatArray)this.polygonPool.obtain();
      polygon.clear();
      int fanBaseIndex = -1;
      int lastWinding = 0;
      short[] trianglesItems = triangles.items;
      int i = 0;

      for (int n = triangles.size; i < n; i += 3) {
         int t1 = trianglesItems[i] << 1;
         int t2 = trianglesItems[i + 1] << 1;
         int t3 = trianglesItems[i + 2] << 1;
         float x1 = vertices[t1];
         float y1 = vertices[t1 + 1];
         float x2 = vertices[t2];
         float y2 = vertices[t2 + 1];
         float x3 = vertices[t3];
         float y3 = vertices[t3 + 1];
         boolean merged = false;
         if (fanBaseIndex == t1) {
            int o = polygon.size - 4;
            float[] p = polygon.items;
            int winding1 = winding(p[o], p[o + 1], p[o + 2], p[o + 3], x3, y3);
            int winding2 = winding(x3, y3, p[0], p[1], p[2], p[3]);
            if (winding1 == lastWinding && winding2 == lastWinding) {
               polygon.add(x3);
               polygon.add(y3);
               polygonIndices.add(t3);
               merged = true;
            }
         }

         if (!merged) {
            if (polygon.size > 0) {
               convexPolygons.add(polygon);
               convexPolygonsIndices.add(polygonIndices);
               polygon = (FloatArray)this.polygonPool.obtain();
               polygonIndices = (ShortArray)this.polygonIndicesPool.obtain();
            }

            polygon.clear();
            polygon.add(x1);
            polygon.add(y1);
            polygon.add(x2);
            polygon.add(y2);
            polygon.add(x3);
            polygon.add(y3);
            polygonIndices.clear();
            polygonIndices.add(t1);
            polygonIndices.add(t2);
            polygonIndices.add(t3);
            lastWinding = winding(x1, y1, x2, y2, x3, y3);
            fanBaseIndex = t1;
         }
      }

      if (polygon.size > 0) {
         convexPolygons.add(polygon);
         convexPolygonsIndices.add(polygonIndices);
      }

      Object[] convexPolygonsIndicesItems = convexPolygonsIndices.items;
      Object[] convexPolygonsItems = convexPolygons.items;
      int j = 0;

      for (int k = convexPolygons.size; j < k; j++) {
         polygonIndices = (ShortArray)convexPolygonsIndicesItems[j];
         if (polygonIndices.size != 0) {
            int firstIndex = polygonIndices.first();
            int lastIndex = polygonIndices.get(polygonIndices.size - 1);
            polygon = (FloatArray)convexPolygonsItems[j];
            int o = polygon.size - 4;
            float[] p = polygon.items;
            float prevPrevX = p[o];
            float prevPrevY = p[o + 1];
            float prevX = p[o + 2];
            float prevY = p[o + 3];
            float firstX = p[0];
            float firstY = p[1];
            float secondX = p[2];
            float secondY = p[3];
            int winding = winding(prevPrevX, prevPrevY, prevX, prevY, firstX, firstY);

            for (int ii = 0; ii < k; ii++) {
               if (ii != j) {
                  ShortArray otherIndices = (ShortArray)convexPolygonsIndicesItems[ii];
                  if (otherIndices.size == 3) {
                     int otherFirstIndex = otherIndices.first();
                     int otherSecondIndex = otherIndices.get(1);
                     int otherLastIndex = otherIndices.get(2);
                     FloatArray otherPoly = (FloatArray)convexPolygonsItems[ii];
                     float x3x = otherPoly.get(otherPoly.size - 2);
                     float y3x = otherPoly.get(otherPoly.size - 1);
                     if (otherFirstIndex == firstIndex && otherSecondIndex == lastIndex) {
                        int winding1 = winding(prevPrevX, prevPrevY, prevX, prevY, x3x, y3x);
                        int winding2 = winding(x3x, y3x, firstX, firstY, secondX, secondY);
                        if (winding1 == winding && winding2 == winding) {
                           otherPoly.clear();
                           otherIndices.clear();
                           polygon.add(x3x);
                           polygon.add(y3x);
                           polygonIndices.add(otherLastIndex);
                           prevPrevX = prevX;
                           prevPrevY = prevY;
                           prevX = x3x;
                           prevY = y3x;
                           ii = 0;
                        }
                     }
                  }
               }
            }
         }
      }

      for (int var45 = convexPolygons.size - 1; var45 >= 0; var45--) {
         polygon = (FloatArray)convexPolygonsItems[var45];
         if (polygon.size == 0) {
            convexPolygons.removeIndex(var45);
            this.polygonPool.free(polygon);
            polygonIndices = (ShortArray)convexPolygonsIndices.removeIndex(var45);
            this.polygonIndicesPool.free(polygonIndices);
         }
      }

      return convexPolygons;
   }

   private static boolean isConcave(int index, int vertexCount, float[] vertices, short[] indices) {
      int previous = indices[(vertexCount + index - 1) % vertexCount] << 1;
      int current = indices[index] << 1;
      int next = indices[(index + 1) % vertexCount] << 1;
      return !positiveArea(vertices[previous], vertices[previous + 1], vertices[current], vertices[current + 1], vertices[next], vertices[next + 1]);
   }

   private static boolean positiveArea(float p1x, float p1y, float p2x, float p2y, float p3x, float p3y) {
      return p1x * (p3y - p2y) + p2x * (p1y - p3y) + p3x * (p2y - p1y) >= 0.0F;
   }

   private static int winding(float p1x, float p1y, float p2x, float p2y, float p3x, float p3y) {
      float px = p2x - p1x;
      float py = p2y - p1y;
      return p3x * py - p3y * px + px * p1y - p1x * py >= 0.0F ? 1 : -1;
   }
}
