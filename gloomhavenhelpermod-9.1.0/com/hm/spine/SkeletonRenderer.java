package com.hm.spine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.NumberUtils;
import com.badlogic.gdx.utils.ShortArray;
import com.hm.spine.attachments.Attachment;
import com.hm.spine.attachments.ClippingAttachment;
import com.hm.spine.attachments.MeshAttachment;
import com.hm.spine.attachments.RegionAttachment;
import com.hm.spine.attachments.SkeletonAttachment;
import com.hm.spine.utils.SkeletonClipping;
import com.hm.spine.utils.TwoColorPolygonBatch;

public class SkeletonRenderer {
   private static final short[] quadTriangles = new short[]{0, 1, 2, 2, 3, 0};
   private boolean pmaColors;
   private boolean pmaBlendModes;
   private final FloatArray vertices = new FloatArray(32);
   private final SkeletonClipping clipper = new SkeletonClipping();
   @Null
   private SkeletonRenderer.VertexEffect vertexEffect;
   private final Vector2 temp = new Vector2();
   private final Vector2 temp2 = new Vector2();
   private final Color temp3 = new Color();
   private final Color temp4 = new Color();
   private final Color temp5 = new Color();
   private final Color temp6 = new Color();

   public void draw(Batch batch, Skeleton skeleton) {
      if (batch instanceof TwoColorPolygonBatch) {
         this.draw((TwoColorPolygonBatch)batch, skeleton);
      } else if (batch instanceof PolygonSpriteBatch) {
         this.draw((PolygonSpriteBatch)batch, skeleton);
      } else if (batch == null) {
         throw new IllegalArgumentException("batch cannot be null.");
      } else if (skeleton == null) {
         throw new IllegalArgumentException("skeleton cannot be null.");
      } else {
         SkeletonRenderer.VertexEffect vertexEffect = this.vertexEffect;
         if (vertexEffect != null) {
            vertexEffect.begin(skeleton);
         }

         boolean pmaColors = this.pmaColors;
         boolean pmaBlendModes = this.pmaBlendModes;
         BlendMode blendMode = null;
         float[] vertices = this.vertices.items;
         Color skeletonColor = skeleton.color;
         float r = skeletonColor.r;
         float g = skeletonColor.g;
         float b = skeletonColor.b;
         float a = skeletonColor.a;
         Object[] drawOrder = skeleton.drawOrder.items;
         int i = 0;

         for (int n = skeleton.drawOrder.size; i < n; i++) {
            Slot slot = (Slot)drawOrder[i];
            if (!slot.bone.active) {
               this.clipper.clipEnd(slot);
            } else {
               Attachment attachment = slot.attachment;
               if (attachment instanceof RegionAttachment) {
                  RegionAttachment region = (RegionAttachment)attachment;
                  region.computeWorldVertices(slot, vertices, 0, 5);
                  Color color = region.getColor();
                  Color slotColor = slot.getColor();
                  float alpha = a * slotColor.a * color.a * 255.0F;
                  float multiplier = pmaColors ? alpha : 255.0F;
                  BlendMode slotBlendMode = slot.data.getBlendMode();
                  if (slotBlendMode != blendMode) {
                     if (slotBlendMode == BlendMode.additive && pmaColors) {
                        slotBlendMode = BlendMode.normal;
                        alpha = 0.0F;
                     }

                     blendMode = slotBlendMode;
                     slotBlendMode.apply(batch, pmaBlendModes);
                  }

                  float c = NumberUtils.intToFloatColor(
                     (int)alpha << 24
                        | (int)(b * slotColor.b * color.b * multiplier) << 16
                        | (int)(g * slotColor.g * color.g * multiplier) << 8
                        | (int)(r * slotColor.r * color.r * multiplier)
                  );
                  float[] uvs = region.getUVs();
                  int u = 0;

                  for (int v = 2; u < 8; v += 5) {
                     vertices[v] = c;
                     vertices[v + 1] = uvs[u];
                     vertices[v + 2] = uvs[u + 1];
                     u += 2;
                  }

                  if (vertexEffect != null) {
                     this.applyVertexEffect(vertices, 20, 5, c, 0.0F);
                  }

                  batch.draw(region.getRegion().getTexture(), vertices, 0, 20);
               } else {
                  if (attachment instanceof ClippingAttachment) {
                     this.clipper.clipStart(slot, (ClippingAttachment)attachment);
                     continue;
                  }

                  if (attachment instanceof MeshAttachment) {
                     throw new RuntimeException(
                        batch.getClass().getSimpleName() + " cannot render meshes, PolygonSpriteBatch or TwoColorPolygonBatch is required."
                     );
                  }

                  if (attachment instanceof SkeletonAttachment) {
                     Skeleton attachmentSkeleton = ((SkeletonAttachment)attachment).getSkeleton();
                     if (attachmentSkeleton != null) {
                        this.draw(batch, attachmentSkeleton);
                     }
                  }
               }

               this.clipper.clipEnd(slot);
            }
         }

         this.clipper.clipEnd();
         if (vertexEffect != null) {
            vertexEffect.end();
         }
      }
   }

   public void draw(PolygonSpriteBatch batch, Skeleton skeleton) {
      if (batch == null) {
         throw new IllegalArgumentException("batch cannot be null.");
      } else if (skeleton == null) {
         throw new IllegalArgumentException("skeleton cannot be null.");
      } else {
         Vector2 tempPosition = this.temp;
         Vector2 tempUV = this.temp2;
         Color tempLight1 = this.temp3;
         Color tempDark1 = this.temp4;
         Color tempLight2 = this.temp5;
         Color tempDark2 = this.temp6;
         SkeletonRenderer.VertexEffect vertexEffect = this.vertexEffect;
         if (vertexEffect != null) {
            vertexEffect.begin(skeleton);
         }

         boolean pmaColors = this.pmaColors;
         boolean pmaBlendModes = this.pmaBlendModes;
         BlendMode blendMode = null;
         int verticesLength = 0;
         float[] vertices = null;
         float[] uvs = null;
         short[] triangles = null;
         Color color = null;
         Color skeletonColor = skeleton.color;
         float r = skeletonColor.r;
         float g = skeletonColor.g;
         float b = skeletonColor.b;
         float a = skeletonColor.a;
         Object[] drawOrder = skeleton.drawOrder.items;
         int i = 0;

         for (int n = skeleton.drawOrder.size; i < n; i++) {
            Slot slot = (Slot)drawOrder[i];
            if (!slot.bone.active) {
               this.clipper.clipEnd(slot);
            } else {
               Texture texture = null;
               int vertexSize = this.clipper.isClipping() ? 2 : 5;
               Attachment attachment = slot.attachment;
               if (attachment instanceof RegionAttachment) {
                  RegionAttachment region = (RegionAttachment)attachment;
                  verticesLength = vertexSize << 2;
                  vertices = this.vertices.items;
                  region.computeWorldVertices(slot, vertices, 0, vertexSize);
                  triangles = quadTriangles;
                  texture = region.getRegion().getTexture();
                  uvs = region.getUVs();
                  color = region.getColor();
               } else if (attachment instanceof MeshAttachment) {
                  MeshAttachment mesh = (MeshAttachment)attachment;
                  int count = mesh.getWorldVerticesLength();
                  verticesLength = (count >> 1) * vertexSize;
                  vertices = this.vertices.setSize(verticesLength);
                  mesh.computeWorldVertices(slot, 0, count, vertices, 0, vertexSize);
                  triangles = mesh.getTriangles();
                  texture = mesh.getRegion().getTexture();
                  uvs = mesh.getUVs();
                  color = mesh.getColor();
               } else {
                  if (attachment instanceof ClippingAttachment) {
                     ClippingAttachment clip = (ClippingAttachment)attachment;
                     this.clipper.clipStart(slot, clip);
                     continue;
                  }

                  if (attachment instanceof SkeletonAttachment) {
                     Skeleton attachmentSkeleton = ((SkeletonAttachment)attachment).getSkeleton();
                     if (attachmentSkeleton != null) {
                        this.draw(batch, attachmentSkeleton);
                     }
                  }
               }

               if (texture != null) {
                  Color slotColor = slot.getColor();
                  float alpha = a * slotColor.a * color.a * 255.0F;
                  float multiplier = pmaColors ? alpha : 255.0F;
                  BlendMode slotBlendMode = slot.data.getBlendMode();
                  if (slotBlendMode != blendMode) {
                     if (slotBlendMode == BlendMode.additive && pmaColors) {
                        slotBlendMode = BlendMode.normal;
                        alpha = 0.0F;
                     }

                     blendMode = slotBlendMode;
                     slotBlendMode.apply(batch, pmaBlendModes);
                  }

                  float c = NumberUtils.intToFloatColor(
                     (int)alpha << 24
                        | (int)(b * slotColor.b * color.b * multiplier) << 16
                        | (int)(g * slotColor.g * color.g * multiplier) << 8
                        | (int)(r * slotColor.r * color.r * multiplier)
                  );
                  if (this.clipper.isClipping()) {
                     this.clipper.clipTriangles(vertices, verticesLength, triangles, triangles.length, uvs, c, 0.0F, false);
                     FloatArray clippedVertices = this.clipper.getClippedVertices();
                     ShortArray clippedTriangles = this.clipper.getClippedTriangles();
                     if (vertexEffect != null) {
                        this.applyVertexEffect(clippedVertices.items, clippedVertices.size, 5, c, 0.0F);
                     }

                     batch.draw(texture, clippedVertices.items, 0, clippedVertices.size, clippedTriangles.items, 0, clippedTriangles.size);
                  } else {
                     if (vertexEffect != null) {
                        tempLight1.set(NumberUtils.floatToIntColor(c));
                        tempDark1.set(0);
                        int v = 0;

                        for (int u = 0; v < verticesLength; u += 2) {
                           tempPosition.x = vertices[v];
                           tempPosition.y = vertices[v + 1];
                           tempLight2.set(tempLight1);
                           tempDark2.set(tempDark1);
                           tempUV.x = uvs[u];
                           tempUV.y = uvs[u + 1];
                           vertexEffect.transform(tempPosition, tempUV, tempLight2, tempDark2);
                           vertices[v] = tempPosition.x;
                           vertices[v + 1] = tempPosition.y;
                           vertices[v + 2] = tempLight2.toFloatBits();
                           vertices[v + 3] = tempUV.x;
                           vertices[v + 4] = tempUV.y;
                           v += 5;
                        }
                     } else {
                        int v = 2;

                        for (int u = 0; v < verticesLength; u += 2) {
                           vertices[v] = c;
                           vertices[v + 1] = uvs[u];
                           vertices[v + 2] = uvs[u + 1];
                           v += 5;
                        }
                     }

                     batch.draw(texture, vertices, 0, verticesLength, triangles, 0, triangles.length);
                  }
               }

               this.clipper.clipEnd(slot);
            }
         }

         this.clipper.clipEnd();
         if (vertexEffect != null) {
            vertexEffect.end();
         }
      }
   }

   public void draw(TwoColorPolygonBatch batch, Skeleton skeleton) {
      if (batch == null) {
         throw new IllegalArgumentException("batch cannot be null.");
      } else if (skeleton == null) {
         throw new IllegalArgumentException("skeleton cannot be null.");
      } else {
         Vector2 tempPosition = this.temp;
         Vector2 tempUV = this.temp2;
         Color tempLight1 = this.temp3;
         Color tempDark1 = this.temp4;
         Color tempLight2 = this.temp5;
         Color tempDark2 = this.temp6;
         SkeletonRenderer.VertexEffect vertexEffect = this.vertexEffect;
         if (vertexEffect != null) {
            vertexEffect.begin(skeleton);
         }

         boolean pmaColors = this.pmaColors;
         boolean pmaBlendModes = this.pmaBlendModes;
         batch.setPremultipliedAlpha(pmaColors);
         BlendMode blendMode = null;
         int verticesLength = 0;
         float[] vertices = null;
         float[] uvs = null;
         short[] triangles = null;
         Color color = null;
         Color skeletonColor = skeleton.color;
         float r = skeletonColor.r;
         float g = skeletonColor.g;
         float b = skeletonColor.b;
         float a = skeletonColor.a;
         Object[] drawOrder = skeleton.drawOrder.items;
         int i = 0;

         for (int n = skeleton.drawOrder.size; i < n; i++) {
            Slot slot = (Slot)drawOrder[i];
            if (!slot.bone.active) {
               this.clipper.clipEnd(slot);
            } else {
               Texture texture = null;
               int vertexSize = this.clipper.isClipping() ? 2 : 6;
               Attachment attachment = slot.attachment;
               if (attachment instanceof RegionAttachment) {
                  RegionAttachment region = (RegionAttachment)attachment;
                  verticesLength = vertexSize << 2;
                  vertices = this.vertices.items;
                  region.computeWorldVertices(slot, vertices, 0, vertexSize);
                  triangles = quadTriangles;
                  texture = region.getRegion().getTexture();
                  uvs = region.getUVs();
                  color = region.getColor();
               } else if (attachment instanceof MeshAttachment) {
                  MeshAttachment mesh = (MeshAttachment)attachment;
                  int count = mesh.getWorldVerticesLength();
                  verticesLength = (count >> 1) * vertexSize;
                  vertices = this.vertices.setSize(verticesLength);
                  mesh.computeWorldVertices(slot, 0, count, vertices, 0, vertexSize);
                  triangles = mesh.getTriangles();
                  texture = mesh.getRegion().getTexture();
                  uvs = mesh.getUVs();
                  color = mesh.getColor();
               } else {
                  if (attachment instanceof ClippingAttachment) {
                     ClippingAttachment clip = (ClippingAttachment)attachment;
                     this.clipper.clipStart(slot, clip);
                     continue;
                  }

                  if (attachment instanceof SkeletonAttachment) {
                     Skeleton attachmentSkeleton = ((SkeletonAttachment)attachment).getSkeleton();
                     if (attachmentSkeleton != null) {
                        this.draw(batch, attachmentSkeleton);
                     }
                  }
               }

               if (texture != null) {
                  Color lightColor = slot.getColor();
                  float alpha = a * lightColor.a * color.a * 255.0F;
                  float multiplier = pmaColors ? alpha : 255.0F;
                  BlendMode slotBlendMode = slot.data.getBlendMode();
                  if (slotBlendMode != blendMode) {
                     if (slotBlendMode == BlendMode.additive && pmaColors) {
                        slotBlendMode = BlendMode.normal;
                        alpha = 0.0F;
                     }

                     blendMode = slotBlendMode;
                     slotBlendMode.apply(batch, pmaBlendModes);
                  }

                  float red = r * color.r * multiplier;
                  float green = g * color.g * multiplier;
                  float blue = b * color.b * multiplier;
                  float light = NumberUtils.intToFloatColor(
                     (int)alpha << 24 | (int)(blue * lightColor.b) << 16 | (int)(green * lightColor.g) << 8 | (int)(red * lightColor.r)
                  );
                  Color darkColor = slot.getDarkColor();
                  float dark = darkColor == null
                     ? 0.0F
                     : NumberUtils.intToFloatColor((int)(blue * darkColor.b) << 16 | (int)(green * darkColor.g) << 8 | (int)(red * darkColor.r));
                  if (this.clipper.isClipping()) {
                     this.clipper.clipTriangles(vertices, verticesLength, triangles, triangles.length, uvs, light, dark, true);
                     FloatArray clippedVertices = this.clipper.getClippedVertices();
                     ShortArray clippedTriangles = this.clipper.getClippedTriangles();
                     if (vertexEffect != null) {
                        this.applyVertexEffect(clippedVertices.items, clippedVertices.size, 6, light, dark);
                     }

                     batch.drawTwoColor(texture, clippedVertices.items, 0, clippedVertices.size, clippedTriangles.items, 0, clippedTriangles.size);
                  } else {
                     if (vertexEffect != null) {
                        tempLight1.set(NumberUtils.floatToIntColor(light));
                        tempDark1.set(NumberUtils.floatToIntColor(dark));
                        int v = 0;

                        for (int u = 0; v < verticesLength; u += 2) {
                           tempPosition.x = vertices[v];
                           tempPosition.y = vertices[v + 1];
                           tempLight2.set(tempLight1);
                           tempDark2.set(tempDark1);
                           tempUV.x = uvs[u];
                           tempUV.y = uvs[u + 1];
                           vertexEffect.transform(tempPosition, tempUV, tempLight2, tempDark2);
                           vertices[v] = tempPosition.x;
                           vertices[v + 1] = tempPosition.y;
                           vertices[v + 2] = tempLight2.toFloatBits();
                           vertices[v + 3] = tempDark2.toFloatBits();
                           vertices[v + 4] = tempUV.x;
                           vertices[v + 5] = tempUV.y;
                           v += 6;
                        }
                     } else {
                        int v = 2;

                        for (int u = 0; v < verticesLength; u += 2) {
                           vertices[v] = light;
                           vertices[v + 1] = dark;
                           vertices[v + 2] = uvs[u];
                           vertices[v + 3] = uvs[u + 1];
                           v += 6;
                        }
                     }

                     batch.drawTwoColor(texture, vertices, 0, verticesLength, triangles, 0, triangles.length);
                  }
               }

               this.clipper.clipEnd(slot);
            }
         }

         this.clipper.clipEnd();
         if (vertexEffect != null) {
            vertexEffect.end();
         }
      }
   }

   private void applyVertexEffect(float[] vertices, int verticesLength, int stride, float light, float dark) {
      Vector2 tempPosition = this.temp;
      Vector2 tempUV = this.temp2;
      Color tempLight1 = this.temp3;
      Color tempDark1 = this.temp4;
      Color tempLight2 = this.temp5;
      Color tempDark2 = this.temp6;
      SkeletonRenderer.VertexEffect vertexEffect = this.vertexEffect;
      tempLight1.set(NumberUtils.floatToIntColor(light));
      tempDark1.set(NumberUtils.floatToIntColor(dark));
      if (stride == 5) {
         for (int v = 0; v < verticesLength; v += stride) {
            tempPosition.x = vertices[v];
            tempPosition.y = vertices[v + 1];
            tempUV.x = vertices[v + 3];
            tempUV.y = vertices[v + 4];
            tempLight2.set(tempLight1);
            tempDark2.set(tempDark1);
            vertexEffect.transform(tempPosition, tempUV, tempLight2, tempDark2);
            vertices[v] = tempPosition.x;
            vertices[v + 1] = tempPosition.y;
            vertices[v + 2] = tempLight2.toFloatBits();
            vertices[v + 3] = tempUV.x;
            vertices[v + 4] = tempUV.y;
         }
      } else {
         for (int v = 0; v < verticesLength; v += stride) {
            tempPosition.x = vertices[v];
            tempPosition.y = vertices[v + 1];
            tempUV.x = vertices[v + 4];
            tempUV.y = vertices[v + 5];
            tempLight2.set(tempLight1);
            tempDark2.set(tempDark1);
            vertexEffect.transform(tempPosition, tempUV, tempLight2, tempDark2);
            vertices[v] = tempPosition.x;
            vertices[v + 1] = tempPosition.y;
            vertices[v + 2] = tempLight2.toFloatBits();
            vertices[v + 3] = tempDark2.toFloatBits();
            vertices[v + 4] = tempUV.x;
            vertices[v + 5] = tempUV.y;
         }
      }
   }

   public boolean getPremultipliedAlphaColors() {
      return this.pmaColors;
   }

   public void setPremultipliedAlphaColors(boolean pmaColors) {
      this.pmaColors = pmaColors;
   }

   public boolean getPremultipliedAlphaBlendModes() {
      return this.pmaBlendModes;
   }

   public void setPremultipliedAlphaBlendModes(boolean pmaBlendModes) {
      this.pmaBlendModes = pmaBlendModes;
   }

   public void setPremultipliedAlpha(boolean pmaColorsAndBlendModes) {
      this.pmaColors = pmaColorsAndBlendModes;
      this.pmaBlendModes = pmaColorsAndBlendModes;
   }

   @Null
   public SkeletonRenderer.VertexEffect getVertexEffect() {
      return this.vertexEffect;
   }

   public void setVertexEffect(@Null SkeletonRenderer.VertexEffect vertexEffect) {
      this.vertexEffect = vertexEffect;
   }

   public interface VertexEffect {
      void begin(Skeleton var1);

      void transform(Vector2 var1, Vector2 var2, Color var3, Color var4);

      void end();
   }
}
