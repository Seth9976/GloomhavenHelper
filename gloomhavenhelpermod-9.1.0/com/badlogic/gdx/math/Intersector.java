package com.badlogic.gdx.math;

import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import java.util.Arrays;
import java.util.List;

public final class Intersector {
   private static final Vector3 v0 = new Vector3();
   private static final Vector3 v1 = new Vector3();
   private static final Vector3 v2 = new Vector3();
   private static final FloatArray floatArray = new FloatArray();
   private static final FloatArray floatArray2 = new FloatArray();
   private static final Vector2 ip = new Vector2();
   private static final Vector2 ep1 = new Vector2();
   private static final Vector2 ep2 = new Vector2();
   private static final Vector2 s = new Vector2();
   private static final Vector2 e = new Vector2();
   static Vector2 v2a = new Vector2();
   static Vector2 v2b = new Vector2();
   static Vector2 v2c = new Vector2();
   static Vector2 v2d = new Vector2();
   private static final Plane p = new Plane(new Vector3(), 0.0F);
   private static final Vector3 i = new Vector3();
   private static final Vector3 dir = new Vector3();
   private static final Vector3 start = new Vector3();
   static Vector3 best = new Vector3();
   static Vector3 tmp = new Vector3();
   static Vector3 tmp1 = new Vector3();
   static Vector3 tmp2 = new Vector3();
   static Vector3 tmp3 = new Vector3();
   static Vector3 intersection = new Vector3();

   private Intersector() {
   }

   public static boolean isPointInTriangle(Vector3 point, Vector3 t1, Vector3 t2, Vector3 t3) {
      v0.set(t1).sub(point);
      v1.set(t2).sub(point);
      v2.set(t3).sub(point);
      float ab = v0.dot(v1);
      float ac = v0.dot(v2);
      float bc = v1.dot(v2);
      float cc = v2.dot(v2);
      if (bc * ac - cc * ab < 0.0F) {
         return false;
      } else {
         float bb = v1.dot(v1);
         return !(ab * bc - ac * bb < 0.0F);
      }
   }

   public static boolean isPointInTriangle(Vector2 p, Vector2 a, Vector2 b, Vector2 c) {
      float px1 = p.x - a.x;
      float py1 = p.y - a.y;
      boolean side12 = (b.x - a.x) * py1 - (b.y - a.y) * px1 > 0.0F;
      return (c.x - a.x) * py1 - (c.y - a.y) * px1 > 0.0F == side12 ? false : (c.x - b.x) * (p.y - b.y) - (c.y - b.y) * (p.x - b.x) > 0.0F == side12;
   }

   public static boolean isPointInTriangle(float px, float py, float ax, float ay, float bx, float by, float cx, float cy) {
      float px1 = px - ax;
      float py1 = py - ay;
      boolean side12 = (bx - ax) * py1 - (by - ay) * px1 > 0.0F;
      return (cx - ax) * py1 - (cy - ay) * px1 > 0.0F == side12 ? false : (cx - bx) * (py - by) - (cy - by) * (px - bx) > 0.0F == side12;
   }

   public static boolean intersectSegmentPlane(Vector3 start, Vector3 end, Plane plane, Vector3 intersection) {
      Vector3 dir = v0.set(end).sub(start);
      float denom = dir.dot(plane.getNormal());
      if (denom == 0.0F) {
         return false;
      } else {
         float t = -(start.dot(plane.getNormal()) + plane.getD()) / denom;
         if (!(t < 0.0F) && !(t > 1.0F)) {
            intersection.set(start).add(dir.scl(t));
            return true;
         } else {
            return false;
         }
      }
   }

   public static int pointLineSide(Vector2 linePoint1, Vector2 linePoint2, Vector2 point) {
      return (int)Math.signum((linePoint2.x - linePoint1.x) * (point.y - linePoint1.y) - (linePoint2.y - linePoint1.y) * (point.x - linePoint1.x));
   }

   public static int pointLineSide(float linePoint1X, float linePoint1Y, float linePoint2X, float linePoint2Y, float pointX, float pointY) {
      return (int)Math.signum((linePoint2X - linePoint1X) * (pointY - linePoint1Y) - (linePoint2Y - linePoint1Y) * (pointX - linePoint1X));
   }

   public static boolean isPointInPolygon(Array polygon, Vector2 point) {
      Vector2 last = (Vector2)polygon.peek();
      float x = point.x;
      float y = point.y;
      boolean oddNodes = false;

      for (int i = 0; i < polygon.size; i++) {
         Vector2 vertex = (Vector2)polygon.get(i);
         if ((vertex.y < y && last.y >= y || last.y < y && vertex.y >= y) && vertex.x + (y - vertex.y) / (last.y - vertex.y) * (last.x - vertex.x) < x) {
            oddNodes = !oddNodes;
         }

         last = vertex;
      }

      return oddNodes;
   }

   public static boolean isPointInPolygon(float[] polygon, int offset, int count, float x, float y) {
      boolean oddNodes = false;
      float sx = polygon[offset];
      float sy = polygon[offset + 1];
      float y1 = sy;
      int yi = offset + 3;

      for (int n = offset + count; yi < n; yi += 2) {
         float y2 = polygon[yi];
         if (y2 < y && y1 >= y || y1 < y && y2 >= y) {
            float x2 = polygon[yi - 1];
            if (x2 + (y - y2) / (y1 - y2) * (polygon[yi - 3] - x2) < x) {
               oddNodes = !oddNodes;
            }
         }

         y1 = y2;
      }

      if ((sy < y && y1 >= y || y1 < y && sy >= y) && sx + (y - sy) / (y1 - sy) * (polygon[yi - 3] - sx) < x) {
         oddNodes = !oddNodes;
      }

      return oddNodes;
   }

   public static boolean intersectPolygons(Polygon p1, Polygon p2, Polygon overlap) {
      if (p1.getVertices().length != 0 && p2.getVertices().length != 0) {
         Vector2 ip = Intersector.ip;
         Vector2 ep1 = Intersector.ep1;
         Vector2 ep2 = Intersector.ep2;
         Vector2 s = Intersector.s;
         Vector2 e = Intersector.e;
         FloatArray floatArray = Intersector.floatArray;
         FloatArray floatArray2 = Intersector.floatArray2;
         floatArray.clear();
         floatArray2.clear();
         floatArray2.addAll(p1.getTransformedVertices());
         float[] vertices2 = p2.getTransformedVertices();
         int i = 0;

         for (int last = vertices2.length - 2; i <= last; i += 2) {
            ep1.set(vertices2[i], vertices2[i + 1]);
            if (i < last) {
               ep2.set(vertices2[i + 2], vertices2[i + 3]);
            } else {
               ep2.set(vertices2[0], vertices2[1]);
            }

            if (floatArray2.size == 0) {
               return false;
            }

            s.set(floatArray2.get(floatArray2.size - 2), floatArray2.get(floatArray2.size - 1));

            for (int j = 0; j < floatArray2.size; j += 2) {
               e.set(floatArray2.get(j), floatArray2.get(j + 1));
               boolean side = pointLineSide(ep2, ep1, s) > 0;
               if (pointLineSide(ep2, ep1, e) > 0) {
                  if (!side) {
                     intersectLines(s, e, ep1, ep2, ip);
                     if (floatArray.size < 2 || floatArray.get(floatArray.size - 2) != ip.x || floatArray.get(floatArray.size - 1) != ip.y) {
                        floatArray.add(ip.x);
                        floatArray.add(ip.y);
                     }
                  }

                  floatArray.add(e.x);
                  floatArray.add(e.y);
               } else if (side) {
                  intersectLines(s, e, ep1, ep2, ip);
                  floatArray.add(ip.x);
                  floatArray.add(ip.y);
               }

               s.set(e.x, e.y);
            }

            floatArray2.clear();
            floatArray2.addAll(floatArray);
            floatArray.clear();
         }

         if (floatArray2.size != 0) {
            if (overlap != null) {
               if (overlap.getVertices().length == floatArray2.size) {
                  System.arraycopy(floatArray2.items, 0, overlap.getVertices(), 0, floatArray2.size);
               } else {
                  overlap.setVertices(floatArray2.toArray());
               }
            }

            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean intersectPolygons(FloatArray polygon1, FloatArray polygon2) {
      if (isPointInPolygon(polygon1.items, 0, polygon1.size, polygon2.items[0], polygon2.items[1])) {
         return true;
      } else {
         return isPointInPolygon(polygon2.items, 0, polygon2.size, polygon1.items[0], polygon1.items[1]) ? true : intersectPolygonEdges(polygon1, polygon2);
      }
   }

   public static boolean intersectPolygonEdges(FloatArray polygon1, FloatArray polygon2) {
      int last1 = polygon1.size - 2;
      int last2 = polygon2.size - 2;
      float[] p1 = polygon1.items;
      float[] p2 = polygon2.items;
      float x1 = p1[last1];
      float y1 = p1[last1 + 1];

      for (int i = 0; i <= last1; i += 2) {
         float x2 = p1[i];
         float y2 = p1[i + 1];
         float x3 = p2[last2];
         float y3 = p2[last2 + 1];

         for (int j = 0; j <= last2; j += 2) {
            float x4 = p2[j];
            float y4 = p2[j + 1];
            if (intersectSegments(x1, y1, x2, y2, x3, y3, x4, y4, null)) {
               return true;
            }

            x3 = x4;
            y3 = y4;
         }

         x1 = x2;
         y1 = y2;
      }

      return false;
   }

   public static float distanceLinePoint(float startX, float startY, float endX, float endY, float pointX, float pointY) {
      float normalLength = (float)Math.sqrt((endX - startX) * (endX - startX) + (endY - startY) * (endY - startY));
      return Math.abs((pointX - startX) * (endY - startY) - (pointY - startY) * (endX - startX)) / normalLength;
   }

   public static float distanceSegmentPoint(float startX, float startY, float endX, float endY, float pointX, float pointY) {
      return nearestSegmentPoint(startX, startY, endX, endY, pointX, pointY, v2a).dst(pointX, pointY);
   }

   public static float distanceSegmentPoint(Vector2 start, Vector2 end, Vector2 point) {
      return nearestSegmentPoint(start, end, point, v2a).dst(point);
   }

   public static Vector2 nearestSegmentPoint(Vector2 start, Vector2 end, Vector2 point, Vector2 nearest) {
      float length2 = start.dst2(end);
      if (length2 == 0.0F) {
         return nearest.set(start);
      } else {
         float t = ((point.x - start.x) * (end.x - start.x) + (point.y - start.y) * (end.y - start.y)) / length2;
         if (t < 0.0F) {
            return nearest.set(start);
         } else {
            return t > 1.0F ? nearest.set(end) : nearest.set(start.x + t * (end.x - start.x), start.y + t * (end.y - start.y));
         }
      }
   }

   public static Vector2 nearestSegmentPoint(float startX, float startY, float endX, float endY, float pointX, float pointY, Vector2 nearest) {
      float xDiff = endX - startX;
      float yDiff = endY - startY;
      float length2 = xDiff * xDiff + yDiff * yDiff;
      if (length2 == 0.0F) {
         return nearest.set(startX, startY);
      } else {
         float t = ((pointX - startX) * (endX - startX) + (pointY - startY) * (endY - startY)) / length2;
         if (t < 0.0F) {
            return nearest.set(startX, startY);
         } else {
            return t > 1.0F ? nearest.set(endX, endY) : nearest.set(startX + t * (endX - startX), startY + t * (endY - startY));
         }
      }
   }

   public static boolean intersectSegmentCircle(Vector2 start, Vector2 end, Vector2 center, float squareRadius) {
      tmp.set(end.x - start.x, end.y - start.y, 0.0F);
      tmp1.set(center.x - start.x, center.y - start.y, 0.0F);
      float l = tmp.len();
      float u = tmp1.dot(tmp.nor());
      if (u <= 0.0F) {
         tmp2.set(start.x, start.y, 0.0F);
      } else if (u >= l) {
         tmp2.set(end.x, end.y, 0.0F);
      } else {
         tmp3.set(tmp.scl(u));
         tmp2.set(tmp3.x + start.x, tmp3.y + start.y, 0.0F);
      }

      float x = center.x - tmp2.x;
      float y = center.y - tmp2.y;
      return x * x + y * y <= squareRadius;
   }

   public static boolean intersectSegmentCircle(Vector2 start, Vector2 end, Circle circle, Intersector.MinimumTranslationVector mtv) {
      v2a.set(end).sub(start);
      v2b.set(circle.x - start.x, circle.y - start.y);
      float len = v2a.len();
      float u = v2b.dot(v2a.nor());
      if (u <= 0.0F) {
         v2c.set(start);
      } else if (u >= len) {
         v2c.set(end);
      } else {
         v2d.set(v2a.scl(u));
         v2c.set(v2d).add(start);
      }

      v2a.set(v2c.x - circle.x, v2c.y - circle.y);
      if (mtv != null) {
         if (v2a.equals(Vector2.Zero)) {
            v2d.set(end.y - start.y, start.x - end.x);
            mtv.normal.set(v2d).nor();
            mtv.depth = circle.radius;
         } else {
            mtv.normal.set(v2a).nor();
            mtv.depth = circle.radius - v2a.len();
         }
      }

      return v2a.len2() <= circle.radius * circle.radius;
   }

   public static float intersectRayRay(Vector2 start1, Vector2 direction1, Vector2 start2, Vector2 direction2) {
      float difx = start2.x - start1.x;
      float dify = start2.y - start1.y;
      float d1xd2 = direction1.x * direction2.y - direction1.y * direction2.x;
      if (d1xd2 == 0.0F) {
         return Float.POSITIVE_INFINITY;
      } else {
         float d2sx = direction2.x / d1xd2;
         float d2sy = direction2.y / d1xd2;
         return difx * d2sy - dify * d2sx;
      }
   }

   public static boolean intersectRayPlane(Ray ray, Plane plane, Vector3 intersection) {
      float denom = ray.direction.dot(plane.getNormal());
      if (denom != 0.0F) {
         float t = -(ray.origin.dot(plane.getNormal()) + plane.getD()) / denom;
         if (t < 0.0F) {
            return false;
         } else {
            if (intersection != null) {
               intersection.set(ray.origin).add(v0.set(ray.direction).scl(t));
            }

            return true;
         }
      } else if (plane.testPoint(ray.origin) == Plane.PlaneSide.OnPlane) {
         if (intersection != null) {
            intersection.set(ray.origin);
         }

         return true;
      } else {
         return false;
      }
   }

   public static float intersectLinePlane(float x, float y, float z, float x2, float y2, float z2, Plane plane, Vector3 intersection) {
      Vector3 direction = tmp.set(x2, y2, z2).sub(x, y, z);
      Vector3 origin = tmp2.set(x, y, z);
      float denom = direction.dot(plane.getNormal());
      if (denom != 0.0F) {
         float t = -(origin.dot(plane.getNormal()) + plane.getD()) / denom;
         if (intersection != null) {
            intersection.set(origin).add(direction.scl(t));
         }

         return t;
      } else if (plane.testPoint(origin) == Plane.PlaneSide.OnPlane) {
         if (intersection != null) {
            intersection.set(origin);
         }

         return 0.0F;
      } else {
         return -1.0F;
      }
   }

   public static boolean intersectPlanes(Plane a, Plane b, Plane c, Vector3 intersection) {
      tmp1.set(a.normal).crs(b.normal);
      tmp2.set(b.normal).crs(c.normal);
      tmp3.set(c.normal).crs(a.normal);
      float f = -a.normal.dot(tmp2);
      if (Math.abs(f) < 1.0E-6F) {
         return false;
      } else {
         tmp1.scl(c.d);
         tmp2.scl(a.d);
         tmp3.scl(b.d);
         intersection.set(tmp1.x + tmp2.x + tmp3.x, tmp1.y + tmp2.y + tmp3.y, tmp1.z + tmp2.z + tmp3.z);
         intersection.scl(1.0F / f);
         return true;
      }
   }

   public static boolean intersectRayTriangle(Ray ray, Vector3 t1, Vector3 t2, Vector3 t3, Vector3 intersection) {
      Vector3 edge1 = v0.set(t2).sub(t1);
      Vector3 edge2 = v1.set(t3).sub(t1);
      Vector3 pvec = v2.set(ray.direction).crs(edge2);
      float det = edge1.dot(pvec);
      if (MathUtils.isZero(det)) {
         p.set(t1, t2, t3);
         if (p.testPoint(ray.origin) == Plane.PlaneSide.OnPlane && isPointInTriangle(ray.origin, t1, t2, t3)) {
            if (intersection != null) {
               intersection.set(ray.origin);
            }

            return true;
         } else {
            return false;
         }
      } else {
         det = 1.0F / det;
         Vector3 tvec = i.set(ray.origin).sub(t1);
         float u = tvec.dot(pvec) * det;
         if (!(u < 0.0F) && !(u > 1.0F)) {
            Vector3 qvec = tvec.crs(edge1);
            float v = ray.direction.dot(qvec) * det;
            if (!(v < 0.0F) && !(u + v > 1.0F)) {
               float t = edge2.dot(qvec) * det;
               if (t < 0.0F) {
                  return false;
               } else {
                  if (intersection != null) {
                     if (t <= 1.0E-6F) {
                        intersection.set(ray.origin);
                     } else {
                        ray.getEndPoint(intersection, t);
                     }
                  }

                  return true;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   public static boolean intersectRaySphere(Ray ray, Vector3 center, float radius, Vector3 intersection) {
      float len = ray.direction.dot(center.x - ray.origin.x, center.y - ray.origin.y, center.z - ray.origin.z);
      if (len < 0.0F) {
         return false;
      } else {
         float dst2 = center.dst2(ray.origin.x + ray.direction.x * len, ray.origin.y + ray.direction.y * len, ray.origin.z + ray.direction.z * len);
         float r2 = radius * radius;
         if (dst2 > r2) {
            return false;
         } else {
            if (intersection != null) {
               intersection.set(ray.direction).scl(len - (float)Math.sqrt(r2 - dst2)).add(ray.origin);
            }

            return true;
         }
      }
   }

   public static boolean intersectRayBounds(Ray ray, BoundingBox box, Vector3 intersection) {
      if (box.contains(ray.origin)) {
         if (intersection != null) {
            intersection.set(ray.origin);
         }

         return true;
      } else {
         float lowest = 0.0F;
         boolean hit = false;
         if (ray.origin.x <= box.min.x && ray.direction.x > 0.0F) {
            float t = (box.min.x - ray.origin.x) / ray.direction.x;
            if (t >= 0.0F) {
               v2.set(ray.direction).scl(t).add(ray.origin);
               if (v2.y >= box.min.y && v2.y <= box.max.y && v2.z >= box.min.z && v2.z <= box.max.z && (!hit || t < lowest)) {
                  hit = true;
                  lowest = t;
               }
            }
         }

         if (ray.origin.x >= box.max.x && ray.direction.x < 0.0F) {
            float t = (box.max.x - ray.origin.x) / ray.direction.x;
            if (t >= 0.0F) {
               v2.set(ray.direction).scl(t).add(ray.origin);
               if (v2.y >= box.min.y && v2.y <= box.max.y && v2.z >= box.min.z && v2.z <= box.max.z && (!hit || t < lowest)) {
                  hit = true;
                  lowest = t;
               }
            }
         }

         if (ray.origin.y <= box.min.y && ray.direction.y > 0.0F) {
            float t = (box.min.y - ray.origin.y) / ray.direction.y;
            if (t >= 0.0F) {
               v2.set(ray.direction).scl(t).add(ray.origin);
               if (v2.x >= box.min.x && v2.x <= box.max.x && v2.z >= box.min.z && v2.z <= box.max.z && (!hit || t < lowest)) {
                  hit = true;
                  lowest = t;
               }
            }
         }

         if (ray.origin.y >= box.max.y && ray.direction.y < 0.0F) {
            float t = (box.max.y - ray.origin.y) / ray.direction.y;
            if (t >= 0.0F) {
               v2.set(ray.direction).scl(t).add(ray.origin);
               if (v2.x >= box.min.x && v2.x <= box.max.x && v2.z >= box.min.z && v2.z <= box.max.z && (!hit || t < lowest)) {
                  hit = true;
                  lowest = t;
               }
            }
         }

         if (ray.origin.z <= box.min.z && ray.direction.z > 0.0F) {
            float t = (box.min.z - ray.origin.z) / ray.direction.z;
            if (t >= 0.0F) {
               v2.set(ray.direction).scl(t).add(ray.origin);
               if (v2.x >= box.min.x && v2.x <= box.max.x && v2.y >= box.min.y && v2.y <= box.max.y && (!hit || t < lowest)) {
                  hit = true;
                  lowest = t;
               }
            }
         }

         if (ray.origin.z >= box.max.z && ray.direction.z < 0.0F) {
            float t = (box.max.z - ray.origin.z) / ray.direction.z;
            if (t >= 0.0F) {
               v2.set(ray.direction).scl(t).add(ray.origin);
               if (v2.x >= box.min.x && v2.x <= box.max.x && v2.y >= box.min.y && v2.y <= box.max.y && (!hit || t < lowest)) {
                  hit = true;
                  lowest = t;
               }
            }
         }

         if (hit && intersection != null) {
            intersection.set(ray.direction).scl(lowest).add(ray.origin);
            if (intersection.x < box.min.x) {
               intersection.x = box.min.x;
            } else if (intersection.x > box.max.x) {
               intersection.x = box.max.x;
            }

            if (intersection.y < box.min.y) {
               intersection.y = box.min.y;
            } else if (intersection.y > box.max.y) {
               intersection.y = box.max.y;
            }

            if (intersection.z < box.min.z) {
               intersection.z = box.min.z;
            } else if (intersection.z > box.max.z) {
               intersection.z = box.max.z;
            }
         }

         return hit;
      }
   }

   public static boolean intersectRayBoundsFast(Ray ray, BoundingBox box) {
      return intersectRayBoundsFast(ray, box.getCenter(tmp1), box.getDimensions(tmp2));
   }

   public static boolean intersectRayBoundsFast(Ray ray, Vector3 center, Vector3 dimensions) {
      float divX = 1.0F / ray.direction.x;
      float divY = 1.0F / ray.direction.y;
      float divZ = 1.0F / ray.direction.z;
      float minx = (center.x - dimensions.x * 0.5F - ray.origin.x) * divX;
      float maxx = (center.x + dimensions.x * 0.5F - ray.origin.x) * divX;
      if (minx > maxx) {
         float t = minx;
         minx = maxx;
         maxx = t;
      }

      float miny = (center.y - dimensions.y * 0.5F - ray.origin.y) * divY;
      float maxy = (center.y + dimensions.y * 0.5F - ray.origin.y) * divY;
      if (miny > maxy) {
         float t = miny;
         miny = maxy;
         maxy = t;
      }

      float minz = (center.z - dimensions.z * 0.5F - ray.origin.z) * divZ;
      float maxz = (center.z + dimensions.z * 0.5F - ray.origin.z) * divZ;
      if (minz > maxz) {
         float t = minz;
         minz = maxz;
         maxz = t;
      }

      float min = Math.max(Math.max(minx, miny), minz);
      float max = Math.min(Math.min(maxx, maxy), maxz);
      return max >= 0.0F && max >= min;
   }

   public static boolean intersectRayOrientedBoundsFast(Ray ray, BoundingBox bounds, Matrix4 matrix) {
      float tMin = 0.0F;
      float tMax = Float.MAX_VALUE;
      Vector3 oBBposition = matrix.getTranslation(tmp);
      Vector3 delta = oBBposition.sub(ray.origin);
      Vector3 xaxis = tmp1;
      tmp1.set(matrix.val[0], matrix.val[1], matrix.val[2]);
      float e = xaxis.dot(delta);
      float f = ray.direction.dot(xaxis);
      if (Math.abs(f) > 1.0E-6F) {
         float t1 = (e + bounds.min.x) / f;
         float t2 = (e + bounds.max.x) / f;
         if (t1 > t2) {
            float w = t1;
            t1 = t2;
            t2 = w;
         }

         if (t2 < tMax) {
            tMax = t2;
         }

         if (t1 > tMin) {
            tMin = t1;
         }

         if (tMax < tMin) {
            return false;
         }
      } else if (-e + bounds.min.x > 0.0F || -e + bounds.max.x < 0.0F) {
         return false;
      }

      Vector3 yaxis = tmp2;
      tmp2.set(matrix.val[4], matrix.val[5], matrix.val[6]);
      e = yaxis.dot(delta);
      f = ray.direction.dot(yaxis);
      if (Math.abs(f) > 1.0E-6F) {
         float t1x = (e + bounds.min.y) / f;
         float t2x = (e + bounds.max.y) / f;
         if (t1x > t2x) {
            float w = t1x;
            t1x = t2x;
            t2x = w;
         }

         if (t2x < tMax) {
            tMax = t2x;
         }

         if (t1x > tMin) {
            tMin = t1x;
         }

         if (tMin > tMax) {
            return false;
         }
      } else if (-e + bounds.min.y > 0.0F || -e + bounds.max.y < 0.0F) {
         return false;
      }

      Vector3 zaxis = tmp3;
      tmp3.set(matrix.val[8], matrix.val[9], matrix.val[10]);
      e = zaxis.dot(delta);
      f = ray.direction.dot(zaxis);
      if (Math.abs(f) > 1.0E-6F) {
         float t1xx = (e + bounds.min.z) / f;
         float t2xx = (e + bounds.max.z) / f;
         if (t1xx > t2xx) {
            float w = t1xx;
            t1xx = t2xx;
            t2xx = w;
         }

         if (t2xx < tMax) {
            tMax = t2xx;
         }

         if (t1xx > tMin) {
            tMin = t1xx;
         }

         if (tMin > tMax) {
            return false;
         }
      } else if (-e + bounds.min.z > 0.0F || -e + bounds.max.z < 0.0F) {
         return false;
      }

      return true;
   }

   public static boolean intersectRayTriangles(Ray ray, float[] triangles, Vector3 intersection) {
      float min_dist = Float.MAX_VALUE;
      boolean hit = false;
      if (triangles.length % 9 != 0) {
         throw new RuntimeException("triangles array size is not a multiple of 9");
      } else {
         for (int i = 0; i < triangles.length; i += 9) {
            boolean result = intersectRayTriangle(
               ray,
               tmp1.set(triangles[i], triangles[i + 1], triangles[i + 2]),
               tmp2.set(triangles[i + 3], triangles[i + 4], triangles[i + 5]),
               tmp3.set(triangles[i + 6], triangles[i + 7], triangles[i + 8]),
               tmp
            );
            if (result) {
               float dist = ray.origin.dst2(tmp);
               if (dist < min_dist) {
                  min_dist = dist;
                  best.set(tmp);
                  hit = true;
               }
            }
         }

         if (!hit) {
            return false;
         } else {
            if (intersection != null) {
               intersection.set(best);
            }

            return true;
         }
      }
   }

   public static boolean intersectRayTriangles(Ray ray, float[] vertices, short[] indices, int vertexSize, Vector3 intersection) {
      float min_dist = Float.MAX_VALUE;
      boolean hit = false;
      if (indices.length % 3 != 0) {
         throw new RuntimeException("triangle list size is not a multiple of 3");
      } else {
         for (int i = 0; i < indices.length; i += 3) {
            int i1 = indices[i] * vertexSize;
            int i2 = indices[i + 1] * vertexSize;
            int i3 = indices[i + 2] * vertexSize;
            boolean result = intersectRayTriangle(
               ray,
               tmp1.set(vertices[i1], vertices[i1 + 1], vertices[i1 + 2]),
               tmp2.set(vertices[i2], vertices[i2 + 1], vertices[i2 + 2]),
               tmp3.set(vertices[i3], vertices[i3 + 1], vertices[i3 + 2]),
               tmp
            );
            if (result) {
               float dist = ray.origin.dst2(tmp);
               if (dist < min_dist) {
                  min_dist = dist;
                  best.set(tmp);
                  hit = true;
               }
            }
         }

         if (!hit) {
            return false;
         } else {
            if (intersection != null) {
               intersection.set(best);
            }

            return true;
         }
      }
   }

   public static boolean intersectRayTriangles(Ray ray, List triangles, Vector3 intersection) {
      float min_dist = Float.MAX_VALUE;
      boolean hit = false;
      if (triangles.size() % 3 != 0) {
         throw new RuntimeException("triangle list size is not a multiple of 3");
      } else {
         for (int i = 0; i < triangles.size(); i += 3) {
            boolean result = intersectRayTriangle(ray, (Vector3)triangles.get(i), (Vector3)triangles.get(i + 1), (Vector3)triangles.get(i + 2), tmp);
            if (result) {
               float dist = ray.origin.dst2(tmp);
               if (dist < min_dist) {
                  min_dist = dist;
                  best.set(tmp);
                  hit = true;
               }
            }
         }

         if (!hit) {
            return false;
         } else {
            if (intersection != null) {
               intersection.set(best);
            }

            return true;
         }
      }
   }

   public static boolean intersectBoundsPlaneFast(BoundingBox box, Plane plane) {
      return intersectBoundsPlaneFast(box.getCenter(tmp1), box.getDimensions(tmp2).scl(0.5F), plane.normal, plane.d);
   }

   public static boolean intersectBoundsPlaneFast(Vector3 center, Vector3 halfDimensions, Vector3 normal, float distance) {
      float radius = halfDimensions.x * Math.abs(normal.x) + halfDimensions.y * Math.abs(normal.y) + halfDimensions.z * Math.abs(normal.z);
      float s = normal.dot(center) - distance;
      return Math.abs(s) <= radius;
   }

   public static boolean intersectLines(Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4, Vector2 intersection) {
      float x1 = p1.x;
      float y1 = p1.y;
      float x2 = p2.x;
      float y2 = p2.y;
      float x3 = p3.x;
      float y3 = p3.y;
      float x4 = p4.x;
      float y4 = p4.y;
      float d = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
      if (d == 0.0F) {
         return false;
      } else {
         if (intersection != null) {
            float ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / d;
            intersection.set(x1 + (x2 - x1) * ua, y1 + (y2 - y1) * ua);
         }

         return true;
      }
   }

   public static boolean intersectLines(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, Vector2 intersection) {
      float d = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
      if (d == 0.0F) {
         return false;
      } else {
         if (intersection != null) {
            float ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / d;
            intersection.set(x1 + (x2 - x1) * ua, y1 + (y2 - y1) * ua);
         }

         return true;
      }
   }

   public static boolean intersectLinePolygon(Vector2 p1, Vector2 p2, Polygon polygon) {
      float[] vertices = polygon.getTransformedVertices();
      float x1 = p1.x;
      float y1 = p1.y;
      float x2 = p2.x;
      float y2 = p2.y;
      int n = vertices.length;
      float x3 = vertices[n - 2];
      float y3 = vertices[n - 1];

      for (int i = 0; i < n; i += 2) {
         float x4 = vertices[i];
         float y4 = vertices[i + 1];
         float d = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
         if (d != 0.0F) {
            float yd = y1 - y3;
            float xd = x1 - x3;
            float ua = ((x4 - x3) * yd - (y4 - y3) * xd) / d;
            if (ua >= 0.0F && ua <= 1.0F) {
               return true;
            }
         }

         x3 = x4;
         y3 = y4;
      }

      return false;
   }

   public static boolean intersectRectangles(Rectangle rectangle1, Rectangle rectangle2, Rectangle intersection) {
      if (rectangle1.overlaps(rectangle2)) {
         intersection.x = Math.max(rectangle1.x, rectangle2.x);
         intersection.width = Math.min(rectangle1.x + rectangle1.width, rectangle2.x + rectangle2.width) - intersection.x;
         intersection.y = Math.max(rectangle1.y, rectangle2.y);
         intersection.height = Math.min(rectangle1.y + rectangle1.height, rectangle2.y + rectangle2.height) - intersection.y;
         return true;
      } else {
         return false;
      }
   }

   public static boolean intersectSegmentRectangle(float startX, float startY, float endX, float endY, Rectangle rectangle) {
      float rectangleEndX = rectangle.x + rectangle.width;
      float rectangleEndY = rectangle.y + rectangle.height;
      if (intersectSegments(startX, startY, endX, endY, rectangle.x, rectangle.y, rectangle.x, rectangleEndY, null)) {
         return true;
      } else if (intersectSegments(startX, startY, endX, endY, rectangle.x, rectangle.y, rectangleEndX, rectangle.y, null)) {
         return true;
      } else if (intersectSegments(startX, startY, endX, endY, rectangleEndX, rectangle.y, rectangleEndX, rectangleEndY, null)) {
         return true;
      } else {
         return intersectSegments(startX, startY, endX, endY, rectangle.x, rectangleEndY, rectangleEndX, rectangleEndY, null)
            ? true
            : rectangle.contains(startX, startY);
      }
   }

   public static boolean intersectSegmentRectangle(Vector2 start, Vector2 end, Rectangle rectangle) {
      return intersectSegmentRectangle(start.x, start.y, end.x, end.y, rectangle);
   }

   public static boolean intersectSegmentPolygon(Vector2 p1, Vector2 p2, Polygon polygon) {
      float[] vertices = polygon.getTransformedVertices();
      float x1 = p1.x;
      float y1 = p1.y;
      float x2 = p2.x;
      float y2 = p2.y;
      int n = vertices.length;
      float x3 = vertices[n - 2];
      float y3 = vertices[n - 1];

      for (int i = 0; i < n; i += 2) {
         float x4 = vertices[i];
         float y4 = vertices[i + 1];
         float d = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
         if (d != 0.0F) {
            float yd = y1 - y3;
            float xd = x1 - x3;
            float ua = ((x4 - x3) * yd - (y4 - y3) * xd) / d;
            if (ua >= 0.0F && ua <= 1.0F) {
               float ub = ((x2 - x1) * yd - (y2 - y1) * xd) / d;
               if (ub >= 0.0F && ub <= 1.0F) {
                  return true;
               }
            }
         }

         x3 = x4;
         y3 = y4;
      }

      return false;
   }

   public static boolean intersectSegments(Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4, Vector2 intersection) {
      float x1 = p1.x;
      float y1 = p1.y;
      float x2 = p2.x;
      float y2 = p2.y;
      float x3 = p3.x;
      float y3 = p3.y;
      float x4 = p4.x;
      float y4 = p4.y;
      float d = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
      if (d == 0.0F) {
         return false;
      } else {
         float yd = y1 - y3;
         float xd = x1 - x3;
         float ua = ((x4 - x3) * yd - (y4 - y3) * xd) / d;
         if (!(ua < 0.0F) && !(ua > 1.0F)) {
            float ub = ((x2 - x1) * yd - (y2 - y1) * xd) / d;
            if (!(ub < 0.0F) && !(ub > 1.0F)) {
               if (intersection != null) {
                  intersection.set(x1 + (x2 - x1) * ua, y1 + (y2 - y1) * ua);
               }

               return true;
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   public static boolean intersectSegments(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, Vector2 intersection) {
      float d = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
      if (d == 0.0F) {
         return false;
      } else {
         float yd = y1 - y3;
         float xd = x1 - x3;
         float ua = ((x4 - x3) * yd - (y4 - y3) * xd) / d;
         if (!(ua < 0.0F) && !(ua > 1.0F)) {
            float ub = ((x2 - x1) * yd - (y2 - y1) * xd) / d;
            if (!(ub < 0.0F) && !(ub > 1.0F)) {
               if (intersection != null) {
                  intersection.set(x1 + (x2 - x1) * ua, y1 + (y2 - y1) * ua);
               }

               return true;
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   static float det(float a, float b, float c, float d) {
      return a * d - b * c;
   }

   static double detd(double a, double b, double c, double d) {
      return a * d - b * c;
   }

   public static boolean overlaps(Circle c1, Circle c2) {
      return c1.overlaps(c2);
   }

   public static boolean overlaps(Rectangle r1, Rectangle r2) {
      return r1.overlaps(r2);
   }

   public static boolean overlaps(Circle c, Rectangle r) {
      float closestX = c.x;
      float closestY = c.y;
      if (c.x < r.x) {
         closestX = r.x;
      } else if (c.x > r.x + r.width) {
         closestX = r.x + r.width;
      }

      if (c.y < r.y) {
         closestY = r.y;
      } else if (c.y > r.y + r.height) {
         closestY = r.y + r.height;
      }

      closestX -= c.x;
      closestX *= closestX;
      closestY -= c.y;
      closestY *= closestY;
      return closestX + closestY < c.radius * c.radius;
   }

   public static boolean overlapConvexPolygons(Polygon p1, Polygon p2) {
      return overlapConvexPolygons(p1, p2, null);
   }

   public static boolean overlapConvexPolygons(Polygon p1, Polygon p2, Intersector.MinimumTranslationVector mtv) {
      return overlapConvexPolygons(p1.getTransformedVertices(), p2.getTransformedVertices(), mtv);
   }

   public static boolean overlapConvexPolygons(float[] verts1, float[] verts2, Intersector.MinimumTranslationVector mtv) {
      return overlapConvexPolygons(verts1, 0, verts1.length, verts2, 0, verts2.length, mtv);
   }

   public static boolean overlapConvexPolygons(
      float[] verts1, int offset1, int count1, float[] verts2, int offset2, int count2, Intersector.MinimumTranslationVector mtv
   ) {
      if (mtv != null) {
         mtv.depth = Float.MAX_VALUE;
         mtv.normal.setZero();
      }

      boolean overlaps = overlapsOnAxisOfShape(verts2, offset2, count2, verts1, offset1, count1, mtv, true);
      if (overlaps) {
         overlaps = overlapsOnAxisOfShape(verts1, offset1, count1, verts2, offset2, count2, mtv, false);
      }

      if (!overlaps) {
         if (mtv != null) {
            mtv.depth = 0.0F;
            mtv.normal.setZero();
         }

         return false;
      } else {
         return true;
      }
   }

   private static boolean overlapsOnAxisOfShape(
      float[] verts1, int offset1, int count1, float[] verts2, int offset2, int count2, Intersector.MinimumTranslationVector mtv, boolean shapesShifted
   ) {
      int endA = offset1 + count1;
      int endB = offset2 + count2;

      for (int i = offset1; i < endA; i += 2) {
         float x1 = verts1[i];
         float y1 = verts1[i + 1];
         float x2 = verts1[(i + 2) % count1];
         float y2 = verts1[(i + 3) % count1];
         float axisX = y1 - y2;
         float axisY = -(x1 - x2);
         float len = (float)Math.sqrt(axisX * axisX + axisY * axisY);
         axisX /= len;
         axisY /= len;
         float minA = Float.MAX_VALUE;
         float maxA = -Float.MAX_VALUE;

         for (int v = offset1; v < endA; v += 2) {
            float p = verts1[v] * axisX + verts1[v + 1] * axisY;
            minA = Math.min(minA, p);
            maxA = Math.max(maxA, p);
         }

         float minB = Float.MAX_VALUE;
         float maxB = -Float.MAX_VALUE;

         for (int v = offset2; v < endB; v += 2) {
            float p = verts2[v] * axisX + verts2[v + 1] * axisY;
            minB = Math.min(minB, p);
            maxB = Math.max(maxB, p);
         }

         if (maxA < minB || maxB < minA) {
            return false;
         }

         if (mtv != null) {
            float o = Math.min(maxA, maxB) - Math.max(minA, minB);
            boolean aContainsB = minA < minB && maxA > maxB;
            boolean bContainsA = minB < minA && maxB > maxA;
            float mins = 0.0F;
            float maxs = 0.0F;
            if (aContainsB || bContainsA) {
               mins = Math.abs(minA - minB);
               maxs = Math.abs(maxA - maxB);
               o += Math.min(mins, maxs);
            }

            if (mtv.depth > o) {
               mtv.depth = o;
               if (shapesShifted) {
                  boolean condition = minA < minB;
                  axisX = condition ? axisX : -axisX;
                  axisY = condition ? axisY : -axisY;
               } else {
                  boolean condition = minA > minB;
                  axisX = condition ? axisX : -axisX;
                  axisY = condition ? axisY : -axisY;
               }

               if (aContainsB || bContainsA) {
                  boolean var37 = mins > maxs;
                  axisX = var37 ? axisX : -axisX;
                  axisY = var37 ? axisY : -axisY;
               }

               mtv.normal.set(axisX, axisY);
            }
         }
      }

      return true;
   }

   public static void splitTriangle(float[] triangle, Plane plane, Intersector.SplitTriangle split) {
      int stride = triangle.length / 3;
      boolean r1 = plane.testPoint(triangle[0], triangle[1], triangle[2]) == Plane.PlaneSide.Back;
      boolean r2 = plane.testPoint(triangle[0 + stride], triangle[1 + stride], triangle[2 + stride]) == Plane.PlaneSide.Back;
      boolean r3 = plane.testPoint(triangle[0 + stride * 2], triangle[1 + stride * 2], triangle[2 + stride * 2]) == Plane.PlaneSide.Back;
      split.reset();
      if (r1 == r2 && r2 == r3) {
         split.total = 1;
         if (r1) {
            split.numBack = 1;
            System.arraycopy(triangle, 0, split.back, 0, triangle.length);
         } else {
            split.numFront = 1;
            System.arraycopy(triangle, 0, split.front, 0, triangle.length);
         }
      } else {
         split.total = 3;
         split.numFront = (r1 ? 0 : 1) + (r2 ? 0 : 1) + (r3 ? 0 : 1);
         split.numBack = split.total - split.numFront;
         split.setSide(!r1);
         int first = 0;
         if (r1 != r2) {
            splitEdge(triangle, first, stride, stride, plane, split.edgeSplit, 0);
            split.add(triangle, first, stride);
            split.add(split.edgeSplit, 0, stride);
            split.setSide(!split.getSide());
            split.add(split.edgeSplit, 0, stride);
         } else {
            split.add(triangle, first, stride);
         }

         int second = stride + stride;
         if (r2 != r3) {
            splitEdge(triangle, stride, second, stride, plane, split.edgeSplit, 0);
            split.add(triangle, stride, stride);
            split.add(split.edgeSplit, 0, stride);
            split.setSide(!split.getSide());
            split.add(split.edgeSplit, 0, stride);
         } else {
            split.add(triangle, stride, stride);
         }

         first = stride + stride;
         int var10 = 0;
         if (r3 != r1) {
            splitEdge(triangle, first, var10, stride, plane, split.edgeSplit, 0);
            split.add(triangle, first, stride);
            split.add(split.edgeSplit, 0, stride);
            split.setSide(!split.getSide());
            split.add(split.edgeSplit, 0, stride);
         } else {
            split.add(triangle, first, stride);
         }

         if (split.numFront == 2) {
            System.arraycopy(split.front, stride * 2, split.front, stride * 3, stride * 2);
            System.arraycopy(split.front, 0, split.front, stride * 5, stride);
         } else {
            System.arraycopy(split.back, stride * 2, split.back, stride * 3, stride * 2);
            System.arraycopy(split.back, 0, split.back, stride * 5, stride);
         }
      }
   }

   private static void splitEdge(float[] vertices, int s, int e, int stride, Plane plane, float[] split, int offset) {
      float t = intersectLinePlane(vertices[s], vertices[s + 1], vertices[s + 2], vertices[e], vertices[e + 1], vertices[e + 2], plane, intersection);
      split[offset + 0] = intersection.x;
      split[offset + 1] = intersection.y;
      split[offset + 2] = intersection.z;

      for (int i = 3; i < stride; i++) {
         float a = vertices[s + i];
         float b = vertices[e + i];
         split[offset + i] = a + t * (b - a);
      }
   }

   public static class MinimumTranslationVector {
      public Vector2 normal = new Vector2();
      public float depth = 0.0F;
   }

   public static class SplitTriangle {
      public float[] front;
      public float[] back;
      float[] edgeSplit;
      public int numFront;
      public int numBack;
      public int total;
      boolean frontCurrent = false;
      int frontOffset = 0;
      int backOffset = 0;

      public SplitTriangle(int numAttributes) {
         this.front = new float[numAttributes * 3 * 2];
         this.back = new float[numAttributes * 3 * 2];
         this.edgeSplit = new float[numAttributes];
      }

      @Override
      public String toString() {
         return "SplitTriangle [front="
            + Arrays.toString(this.front)
            + ", back="
            + Arrays.toString(this.back)
            + ", numFront="
            + this.numFront
            + ", numBack="
            + this.numBack
            + ", total="
            + this.total
            + "]";
      }

      void setSide(boolean front) {
         this.frontCurrent = front;
      }

      boolean getSide() {
         return this.frontCurrent;
      }

      void add(float[] vertex, int offset, int stride) {
         if (this.frontCurrent) {
            System.arraycopy(vertex, offset, this.front, this.frontOffset, stride);
            this.frontOffset += stride;
         } else {
            System.arraycopy(vertex, offset, this.back, this.backOffset, stride);
            this.backOffset += stride;
         }
      }

      void reset() {
         this.frontCurrent = false;
         this.frontOffset = 0;
         this.backOffset = 0;
         this.numFront = 0;
         this.numBack = 0;
         this.total = 0;
      }
   }
}
