package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.Array;

public class BSpline implements Path {
   private static final float d6 = 0.16666667F;
   public Vector[] controlPoints;
   public Array knots;
   public int degree;
   public boolean continuous;
   public int spanCount;
   private Vector tmp;
   private Vector tmp2;
   private Vector tmp3;

   public static Vector cubic(Vector out, float t, Vector[] points, boolean continuous, Vector tmp) {
      int n = continuous ? points.length : points.length - 3;
      float u = t * n;
      int i = t >= 1.0F ? n - 1 : (int)u;
      u -= i;
      return cubic(out, i, u, points, continuous, tmp);
   }

   public static Vector cubic_derivative(Vector out, float t, Vector[] points, boolean continuous, Vector tmp) {
      int n = continuous ? points.length : points.length - 3;
      float u = t * n;
      int i = t >= 1.0F ? n - 1 : (int)u;
      u -= i;
      return cubic(out, i, u, points, continuous, tmp);
   }

   public static Vector cubic(Vector out, int i, float u, Vector[] points, boolean continuous, Vector tmp) {
      int n = points.length;
      float dt = 1.0F - u;
      float t2 = u * u;
      float t3 = t2 * u;
      out.set(points[i]).scl((3.0F * t3 - 6.0F * t2 + 4.0F) * 0.16666667F);
      if (continuous || i > 0) {
         out.add(tmp.set(points[(n + i - 1) % n]).scl(dt * dt * dt * 0.16666667F));
      }

      if (continuous || i < n - 1) {
         out.add(tmp.set(points[(i + 1) % n]).scl((-3.0F * t3 + 3.0F * t2 + 3.0F * u + 1.0F) * 0.16666667F));
      }

      if (continuous || i < n - 2) {
         out.add(tmp.set(points[(i + 2) % n]).scl(t3 * 0.16666667F));
      }

      return out;
   }

   public static Vector cubic_derivative(Vector out, int i, float u, Vector[] points, boolean continuous, Vector tmp) {
      int n = points.length;
      float dt = 1.0F - u;
      float t2 = u * u;
      float t3 = t2 * u;
      out.set(points[i]).scl(1.5F * t2 - 2.0F * u);
      if (continuous || i > 0) {
         out.add(tmp.set(points[(n + i - 1) % n]).scl(-0.5F * dt * dt));
      }

      if (continuous || i < n - 1) {
         out.add(tmp.set(points[(i + 1) % n]).scl(-1.5F * t2 + u + 0.5F));
      }

      if (continuous || i < n - 2) {
         out.add(tmp.set(points[(i + 2) % n]).scl(0.5F * t2));
      }

      return out;
   }

   public static Vector calculate(Vector out, float t, Vector[] points, int degree, boolean continuous, Vector tmp) {
      int n = continuous ? points.length : points.length - degree;
      float u = t * n;
      int i = t >= 1.0F ? n - 1 : (int)u;
      u -= i;
      return calculate(out, i, u, points, degree, continuous, tmp);
   }

   public static Vector derivative(Vector out, float t, Vector[] points, int degree, boolean continuous, Vector tmp) {
      int n = continuous ? points.length : points.length - degree;
      float u = t * n;
      int i = t >= 1.0F ? n - 1 : (int)u;
      u -= i;
      return derivative(out, i, u, points, degree, continuous, tmp);
   }

   public static Vector calculate(Vector out, int i, float u, Vector[] points, int degree, boolean continuous, Vector tmp) {
      switch (degree) {
         case 3:
            return cubic(out, i, u, points, continuous, tmp);
         default:
            throw new IllegalArgumentException();
      }
   }

   public static Vector derivative(Vector out, int i, float u, Vector[] points, int degree, boolean continuous, Vector tmp) {
      switch (degree) {
         case 3:
            return cubic_derivative(out, i, u, points, continuous, tmp);
         default:
            throw new IllegalArgumentException();
      }
   }

   public BSpline() {
   }

   public BSpline(Vector[] controlPoints, int degree, boolean continuous) {
      this.set(controlPoints, degree, continuous);
   }

   public BSpline set(Vector[] controlPoints, int degree, boolean continuous) {
      if (this.tmp == null) {
         this.tmp = controlPoints[0].cpy();
      }

      if (this.tmp2 == null) {
         this.tmp2 = controlPoints[0].cpy();
      }

      if (this.tmp3 == null) {
         this.tmp3 = controlPoints[0].cpy();
      }

      this.controlPoints = controlPoints;
      this.degree = degree;
      this.continuous = continuous;
      this.spanCount = continuous ? controlPoints.length : controlPoints.length - degree;
      if (this.knots == null) {
         this.knots = new Array(this.spanCount);
      } else {
         this.knots.clear();
         this.knots.ensureCapacity(this.spanCount);
      }

      for (int i = 0; i < this.spanCount; i++) {
         this.knots.add(calculate(controlPoints[0].cpy(), continuous ? i : (int)(i + 0.5F * degree), 0.0F, controlPoints, degree, continuous, this.tmp));
      }

      return this;
   }

   public Vector valueAt(Vector out, float t) {
      int n = this.spanCount;
      float u = t * n;
      int i = t >= 1.0F ? n - 1 : (int)u;
      u -= i;
      return this.valueAt(out, i, u);
   }

   public Vector valueAt(Vector out, int span, float u) {
      return calculate(out, this.continuous ? span : span + (int)(this.degree * 0.5F), u, this.controlPoints, this.degree, this.continuous, this.tmp);
   }

   public Vector derivativeAt(Vector out, float t) {
      int n = this.spanCount;
      float u = t * n;
      int i = t >= 1.0F ? n - 1 : (int)u;
      u -= i;
      return this.derivativeAt(out, i, u);
   }

   public Vector derivativeAt(Vector out, int span, float u) {
      return derivative(out, this.continuous ? span : span + (int)(this.degree * 0.5F), u, this.controlPoints, this.degree, this.continuous, this.tmp);
   }

   public int nearest(Vector in) {
      return this.nearest(in, 0, this.spanCount);
   }

   public int nearest(Vector in, int start, int count) {
      while (start < 0) {
         start += this.spanCount;
      }

      int result = start % this.spanCount;
      float dst = in.dst2((Vector)this.knots.get(result));

      for (int i = 1; i < count; i++) {
         int idx = (start + i) % this.spanCount;
         float d = in.dst2((Vector)this.knots.get(idx));
         if (d < dst) {
            dst = d;
            result = idx;
         }
      }

      return result;
   }

   public float approximate(Vector v) {
      return this.approximate(v, this.nearest(v));
   }

   public float approximate(Vector in, int start, int count) {
      return this.approximate(in, this.nearest(in, start, count));
   }

   public float approximate(Vector in, int near) {
      int n = near;
      Vector nearest = (T)((Vector)this.knots.get(near));
      Vector previous = (T)((Vector)this.knots.get(near > 0 ? near - 1 : this.spanCount - 1));
      Vector next = (T)((Vector)this.knots.get((near + 1) % this.spanCount));
      float dstPrev2 = in.dst2(previous);
      float dstNext2 = in.dst2(next);
      Vector P1;
      Vector P2;
      Vector P3;
      if (dstNext2 < dstPrev2) {
         P1 = nearest;
         P2 = next;
         P3 = in;
      } else {
         P1 = previous;
         P2 = nearest;
         P3 = in;
         n = near > 0 ? near - 1 : this.spanCount - 1;
      }

      float L1Sqr = P1.dst2(P2);
      float L2Sqr = P3.dst2(P2);
      float L3Sqr = P3.dst2(P1);
      float L1 = (float)Math.sqrt(L1Sqr);
      float s = (L2Sqr + L1Sqr - L3Sqr) / (2.0F * L1);
      float u = MathUtils.clamp((L1 - s) / L1, 0.0F, 1.0F);
      return (n + u) / this.spanCount;
   }

   public float locate(Vector v) {
      return this.approximate(v);
   }

   @Override
   public float approxLength(int samples) {
      float tempLength = 0.0F;

      for (int i = 0; i < samples; i++) {
         this.tmp2.set(this.tmp3);
         this.valueAt(this.tmp3, i / (samples - 1.0F));
         if (i > 0) {
            tempLength += this.tmp2.dst(this.tmp3);
         }
      }

      return tempLength;
   }
}
