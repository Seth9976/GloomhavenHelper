package com.hm.spine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.hm.spine.attachments.Attachment;
import com.hm.spine.attachments.BoundingBoxAttachment;
import com.hm.spine.attachments.ClippingAttachment;
import com.hm.spine.attachments.MeshAttachment;
import com.hm.spine.attachments.PathAttachment;
import com.hm.spine.attachments.PointAttachment;
import com.hm.spine.attachments.RegionAttachment;

public class SkeletonRendererDebug {
   public static final Color boneLineColor = Color.RED;
   public static final Color boneOriginColor = Color.GREEN;
   public static final Color attachmentLineColor = new Color(0.0F, 0.0F, 1.0F, 0.5F);
   public static final Color triangleLineColor = new Color(1.0F, 0.64F, 0.0F, 0.5F);
   public static final Color aabbColor = new Color(0.0F, 1.0F, 0.0F, 0.5F);
   private final ShapeRenderer shapes;
   private boolean drawBones = true;
   private boolean drawRegionAttachments = true;
   private boolean drawBoundingBoxes = true;
   private boolean drawPoints = true;
   private boolean drawMeshHull = true;
   private final SkeletonBounds bounds = new SkeletonBounds();
   private boolean drawMeshTriangles = true;
   private boolean drawPaths = true;
   private boolean drawClipping = true;
   private final FloatArray vertices = new FloatArray(32);
   private float scale = 1.0F;
   private float boneWidth = 2.0F;
   private boolean premultipliedAlpha;
   private final Vector2 temp1 = new Vector2();
   private final Vector2 temp2 = new Vector2();

   public SkeletonRendererDebug() {
      this.shapes = new ShapeRenderer();
   }

   public SkeletonRendererDebug(ShapeRenderer shapes) {
      if (shapes == null) {
         throw new IllegalArgumentException("shapes cannot be null.");
      } else {
         this.shapes = shapes;
      }
   }

   public void draw(Skeleton skeleton) {
      if (skeleton == null) {
         throw new IllegalArgumentException("skeleton cannot be null.");
      } else {
         Gdx.gl.glEnable(3042);
         int srcFunc = this.premultipliedAlpha ? 1 : 770;
         Gdx.gl.glBlendFunc(srcFunc, 771);
         ShapeRenderer shapes = this.shapes;
         Array bones = skeleton.getBones();
         Array slots = skeleton.getSlots();
         shapes.begin(ShapeRenderer.ShapeType.Filled);
         if (this.drawBones) {
            int i = 0;

            for (int n = bones.size; i < n; i++) {
               Bone bone = (Bone)bones.get(i);
               if (bone.parent != null && bone.active) {
                  float length = bone.data.length;
                  float width = this.boneWidth;
                  if (length == 0.0F) {
                     length = 8.0F;
                     width /= 2.0F;
                     shapes.setColor(boneOriginColor);
                  } else {
                     shapes.setColor(boneLineColor);
                  }

                  float x = length * bone.a + bone.worldX;
                  float y = length * bone.c + bone.worldY;
                  shapes.rectLine(bone.worldX, bone.worldY, x, y, width * this.scale);
               }
            }

            shapes.x(skeleton.getX(), skeleton.getY(), 4.0F * this.scale);
         }

         if (this.drawPoints) {
            shapes.setColor(boneOriginColor);
            int i = 0;

            for (int nx = slots.size; i < nx; i++) {
               Slot slot = (Slot)slots.get(i);
               Attachment attachment = slot.attachment;
               if (attachment instanceof PointAttachment) {
                  PointAttachment point = (PointAttachment)attachment;
                  point.computeWorldPosition(slot.getBone(), this.temp1);
                  this.temp2.set(8.0F, 0.0F).rotate(point.computeWorldRotation(slot.getBone()));
                  shapes.rectLine(this.temp1, this.temp2, this.boneWidth / 2.0F * this.scale);
               }
            }
         }

         shapes.end();
         shapes.begin(ShapeRenderer.ShapeType.Line);
         if (this.drawRegionAttachments) {
            shapes.setColor(attachmentLineColor);
            int i = 0;

            for (int nxx = slots.size; i < nxx; i++) {
               Slot slot = (Slot)slots.get(i);
               Attachment attachment = slot.attachment;
               if (attachment instanceof RegionAttachment) {
                  RegionAttachment region = (RegionAttachment)attachment;
                  float[] vertices = this.vertices.items;
                  region.computeWorldVertices(slot, vertices, 0, 2);
                  shapes.line(vertices[0], vertices[1], vertices[2], vertices[3]);
                  shapes.line(vertices[2], vertices[3], vertices[4], vertices[5]);
                  shapes.line(vertices[4], vertices[5], vertices[6], vertices[7]);
                  shapes.line(vertices[6], vertices[7], vertices[0], vertices[1]);
               }
            }
         }

         if (this.drawMeshHull || this.drawMeshTriangles) {
            int i = 0;

            for (int nxxx = slots.size; i < nxxx; i++) {
               Slot slot = (Slot)slots.get(i);
               Attachment attachment = slot.attachment;
               if (attachment instanceof MeshAttachment) {
                  MeshAttachment mesh = (MeshAttachment)attachment;
                  float[] vertices = this.vertices.setSize(mesh.getWorldVerticesLength());
                  mesh.computeWorldVertices(slot, 0, mesh.getWorldVerticesLength(), vertices, 0, 2);
                  short[] triangles = mesh.getTriangles();
                  int hullLength = mesh.getHullLength();
                  if (this.drawMeshTriangles) {
                     shapes.setColor(triangleLineColor);
                     int ii = 0;

                     for (int nn = triangles.length; ii < nn; ii += 3) {
                        int v1 = triangles[ii] * 2;
                        int v2 = triangles[ii + 1] * 2;
                        int v3 = triangles[ii + 2] * 2;
                        shapes.triangle(vertices[v1], vertices[v1 + 1], vertices[v2], vertices[v2 + 1], vertices[v3], vertices[v3 + 1]);
                     }
                  }

                  if (this.drawMeshHull && hullLength > 0) {
                     shapes.setColor(attachmentLineColor);
                     float lastX = vertices[hullLength - 2];
                     float lastY = vertices[hullLength - 1];
                     int ii = 0;

                     for (int nn = hullLength; ii < nn; ii += 2) {
                        float x = vertices[ii];
                        float y = vertices[ii + 1];
                        shapes.line(x, y, lastX, lastY);
                        lastX = x;
                        lastY = y;
                     }
                  }
               }
            }
         }

         if (this.drawBoundingBoxes) {
            SkeletonBounds bounds = this.bounds;
            bounds.update(skeleton, true);
            shapes.setColor(aabbColor);
            shapes.rect(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
            Array polygons = bounds.getPolygons();
            Array boxes = bounds.getBoundingBoxes();
            int i = 0;

            for (int nxxxx = polygons.size; i < nxxxx; i++) {
               FloatArray polygon = (FloatArray)polygons.get(i);
               shapes.setColor(((BoundingBoxAttachment)boxes.get(i)).getColor());
               shapes.polygon(polygon.items, 0, polygon.size);
            }
         }

         if (this.drawClipping) {
            int i = 0;

            for (int nxxxx = slots.size; i < nxxxx; i++) {
               Slot slot = (Slot)slots.get(i);
               Attachment attachment = slot.attachment;
               if (attachment instanceof ClippingAttachment) {
                  ClippingAttachment clip = (ClippingAttachment)attachment;
                  int nn = clip.getWorldVerticesLength();
                  float[] verticesx = this.vertices.setSize(nn);
                  clip.computeWorldVertices(slot, 0, nn, verticesx, 0, 2);
                  shapes.setColor(clip.getColor());

                  for (int ii = 2; ii < nn; ii += 2) {
                     shapes.line(verticesx[ii - 2], verticesx[ii - 1], verticesx[ii], verticesx[ii + 1]);
                  }

                  shapes.line(verticesx[0], verticesx[1], verticesx[nn - 2], verticesx[nn - 1]);
               }
            }
         }

         if (this.drawPaths) {
            int i = 0;

            for (int nxxxxx = slots.size; i < nxxxxx; i++) {
               Slot slot = (Slot)slots.get(i);
               Attachment attachment = slot.attachment;
               if (attachment instanceof PathAttachment) {
                  PathAttachment path = (PathAttachment)attachment;
                  int nn = path.getWorldVerticesLength();
                  float[] verticesx = this.vertices.setSize(nn);
                  path.computeWorldVertices(slot, 0, nn, verticesx, 0, 2);
                  Color color = path.getColor();
                  float x1 = verticesx[2];
                  float y1 = verticesx[3];
                  float x2 = 0.0F;
                  float y2 = 0.0F;
                  if (path.getClosed()) {
                     shapes.setColor(color);
                     float cx1 = verticesx[0];
                     float cy1 = verticesx[1];
                     float cx2 = verticesx[nn - 2];
                     float cy2 = verticesx[nn - 1];
                     x2 = verticesx[nn - 4];
                     y2 = verticesx[nn - 3];
                     shapes.curve(x1, y1, cx1, cy1, cx2, cy2, x2, y2, 32);
                     shapes.setColor(Color.LIGHT_GRAY);
                     shapes.line(x1, y1, cx1, cy1);
                     shapes.line(x2, y2, cx2, cy2);
                  }

                  nn -= 4;

                  for (int ii = 4; ii < nn; ii += 6) {
                     float cx1 = verticesx[ii];
                     float cy1 = verticesx[ii + 1];
                     float cx2 = verticesx[ii + 2];
                     float cy2 = verticesx[ii + 3];
                     x2 = verticesx[ii + 4];
                     y2 = verticesx[ii + 5];
                     shapes.setColor(color);
                     shapes.curve(x1, y1, cx1, cy1, cx2, cy2, x2, y2, 32);
                     shapes.setColor(Color.LIGHT_GRAY);
                     shapes.line(x1, y1, cx1, cy1);
                     shapes.line(x2, y2, cx2, cy2);
                     x1 = x2;
                     y1 = y2;
                  }
               }
            }
         }

         shapes.end();
         shapes.begin(ShapeRenderer.ShapeType.Filled);
         if (this.drawBones) {
            shapes.setColor(boneOriginColor);
            int i = 0;

            for (int nxxxxxx = bones.size; i < nxxxxxx; i++) {
               Bone bone = (Bone)bones.get(i);
               if (bone.active) {
                  shapes.circle(bone.worldX, bone.worldY, 3.0F * this.scale, 8);
               }
            }
         }

         if (this.drawPoints) {
            shapes.setColor(boneOriginColor);
            int i = 0;

            for (int nxxxxxxx = slots.size; i < nxxxxxxx; i++) {
               Slot slot = (Slot)slots.get(i);
               Attachment attachment = slot.attachment;
               if (attachment instanceof PointAttachment) {
                  PointAttachment point = (PointAttachment)attachment;
                  point.computeWorldPosition(slot.getBone(), this.temp1);
                  shapes.circle(this.temp1.x, this.temp1.y, 3.0F * this.scale, 8);
               }
            }
         }

         shapes.end();
      }
   }

   public ShapeRenderer getShapeRenderer() {
      return this.shapes;
   }

   public void setBones(boolean bones) {
      this.drawBones = bones;
   }

   public void setScale(float scale) {
      this.scale = scale;
   }

   public void setRegionAttachments(boolean regionAttachments) {
      this.drawRegionAttachments = regionAttachments;
   }

   public void setBoundingBoxes(boolean boundingBoxes) {
      this.drawBoundingBoxes = boundingBoxes;
   }

   public void setMeshHull(boolean meshHull) {
      this.drawMeshHull = meshHull;
   }

   public void setMeshTriangles(boolean meshTriangles) {
      this.drawMeshTriangles = meshTriangles;
   }

   public void setPaths(boolean paths) {
      this.drawPaths = paths;
   }

   public void setPoints(boolean points) {
      this.drawPoints = points;
   }

   public void setClipping(boolean clipping) {
      this.drawClipping = clipping;
   }

   public void setPremultipliedAlpha(boolean premultipliedAlpha) {
      this.premultipliedAlpha = premultipliedAlpha;
   }
}
