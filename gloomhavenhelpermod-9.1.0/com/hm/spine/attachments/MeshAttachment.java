package com.hm.spine.attachments;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Null;
import com.hm.spine.Slot;
import com.hm.spine.utils.SpineUtils;

public class MeshAttachment extends VertexAttachment implements HasTextureRegion {
   private TextureRegion region;
   private String path;
   private float[] regionUVs;
   private float[] uvs;
   private short[] triangles;
   private final Color color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
   private int hullLength;
   @Null
   private MeshAttachment parentMesh;
   @Null
   private Sequence sequence;
   @Null
   private short[] edges;
   private float width;
   private float height;

   public MeshAttachment(String name) {
      super(name);
   }

   protected MeshAttachment(MeshAttachment other) {
      super(other);
      if (this.parentMesh != null) {
         throw new IllegalArgumentException("Use newLinkedMesh to copy a linked mesh.");
      } else {
         this.region = other.region;
         this.path = other.path;
         this.color.set(other.color);
         this.regionUVs = new float[other.regionUVs.length];
         SpineUtils.arraycopy(other.regionUVs, 0, this.regionUVs, 0, this.regionUVs.length);
         this.uvs = new float[other.uvs.length];
         SpineUtils.arraycopy(other.uvs, 0, this.uvs, 0, this.uvs.length);
         this.triangles = new short[other.triangles.length];
         SpineUtils.arraycopy(other.triangles, 0, this.triangles, 0, this.triangles.length);
         this.hullLength = other.hullLength;
         this.sequence = other.sequence != null ? new Sequence(other.sequence) : null;
         if (other.edges != null) {
            this.edges = new short[other.edges.length];
            SpineUtils.arraycopy(other.edges, 0, this.edges, 0, this.edges.length);
         }

         this.width = other.width;
         this.height = other.height;
      }
   }

   @Override
   public void setRegion(TextureRegion region) {
      if (region == null) {
         throw new IllegalArgumentException("region cannot be null.");
      } else {
         this.region = region;
      }
   }

   @Null
   @Override
   public TextureRegion getRegion() {
      return this.region;
   }

   @Override
   public void updateRegion() {
      float[] regionUVs = this.regionUVs;
      if (this.uvs == null || this.uvs.length != regionUVs.length) {
         this.uvs = new float[regionUVs.length];
      }

      float[] uvs = this.uvs;
      int n = uvs.length;
      float width;
      float height;
      float u;
      float v;
      if (this.region instanceof TextureAtlas.AtlasRegion) {
         u = this.region.getU();
         v = this.region.getV();
         TextureAtlas.AtlasRegion region = (TextureAtlas.AtlasRegion)this.region;
         float textureWidth = region.getTexture().getWidth();
         float textureHeight = region.getTexture().getHeight();
         switch (region.degrees) {
            case 90:
               u -= (region.originalHeight - region.offsetY - region.packedWidth) / textureWidth;
               v -= (region.originalWidth - region.offsetX - region.packedHeight) / textureHeight;
               width = region.originalHeight / textureWidth;
               height = region.originalWidth / textureHeight;

               for (int j = 0; j < n; j += 2) {
                  uvs[j] = u + regionUVs[j + 1] * width;
                  uvs[j + 1] = v + (1.0F - regionUVs[j]) * height;
               }

               return;
            case 180:
               u -= (region.originalWidth - region.offsetX - region.packedWidth) / textureWidth;
               v -= region.offsetY / textureHeight;
               width = region.originalWidth / textureWidth;
               height = region.originalHeight / textureHeight;

               for (int j = 0; j < n; j += 2) {
                  uvs[j] = u + (1.0F - regionUVs[j]) * width;
                  uvs[j + 1] = v + (1.0F - regionUVs[j + 1]) * height;
               }

               return;
            case 270:
               u -= region.offsetY / textureWidth;
               v -= region.offsetX / textureHeight;
               width = region.originalHeight / textureWidth;
               height = region.originalWidth / textureHeight;

               for (int j = 0; j < n; j += 2) {
                  uvs[j] = u + (1.0F - regionUVs[j + 1]) * width;
                  uvs[j + 1] = v + regionUVs[j] * height;
               }

               return;
            default:
               u -= region.offsetX / textureWidth;
               v -= (region.originalHeight - region.offsetY - region.packedHeight) / textureHeight;
               width = region.originalWidth / textureWidth;
               height = region.originalHeight / textureHeight;
         }
      } else if (this.region == null) {
         v = 0.0F;
         u = 0.0F;
         height = 1.0F;
         width = 1.0F;
      } else {
         u = this.region.getU();
         v = this.region.getV();
         width = this.region.getU2() - u;
         height = this.region.getV2() - v;
      }

      for (int i = 0; i < n; i += 2) {
         uvs[i] = u + regionUVs[i] * width;
         uvs[i + 1] = v + regionUVs[i + 1] * height;
      }
   }

   @Override
   public void computeWorldVertices(Slot slot, int start, int count, float[] worldVertices, int offset, int stride) {
      if (this.sequence != null) {
         this.sequence.apply(slot, this);
      }

      super.computeWorldVertices(slot, start, count, worldVertices, offset, stride);
   }

   public short[] getTriangles() {
      return this.triangles;
   }

   public void setTriangles(short[] triangles) {
      this.triangles = triangles;
   }

   public float[] getRegionUVs() {
      return this.regionUVs;
   }

   public void setRegionUVs(float[] regionUVs) {
      this.regionUVs = regionUVs;
   }

   public float[] getUVs() {
      return this.uvs;
   }

   public void setUVs(float[] uvs) {
      this.uvs = uvs;
   }

   @Override
   public Color getColor() {
      return this.color;
   }

   @Override
   public String getPath() {
      return this.path;
   }

   @Override
   public void setPath(String path) {
      this.path = path;
   }

   public int getHullLength() {
      return this.hullLength;
   }

   public void setHullLength(int hullLength) {
      this.hullLength = hullLength;
   }

   public void setEdges(short[] edges) {
      this.edges = edges;
   }

   @Null
   public short[] getEdges() {
      return this.edges;
   }

   public float getWidth() {
      return this.width;
   }

   public void setWidth(float width) {
      this.width = width;
   }

   public float getHeight() {
      return this.height;
   }

   public void setHeight(float height) {
      this.height = height;
   }

   @Null
   @Override
   public Sequence getSequence() {
      return this.sequence;
   }

   @Override
   public void setSequence(@Null Sequence sequence) {
      this.sequence = sequence;
   }

   @Null
   public MeshAttachment getParentMesh() {
      return this.parentMesh;
   }

   public void setParentMesh(@Null MeshAttachment parentMesh) {
      this.parentMesh = parentMesh;
      if (parentMesh != null) {
         this.bones = parentMesh.bones;
         this.vertices = parentMesh.vertices;
         this.regionUVs = parentMesh.regionUVs;
         this.triangles = parentMesh.triangles;
         this.hullLength = parentMesh.hullLength;
         this.worldVerticesLength = parentMesh.worldVerticesLength;
         this.edges = parentMesh.edges;
         this.width = parentMesh.width;
         this.height = parentMesh.height;
      }
   }

   public MeshAttachment newLinkedMesh() {
      MeshAttachment mesh = new MeshAttachment(this.name);
      mesh.timelineAttachment = this.timelineAttachment;
      mesh.region = this.region;
      mesh.path = this.path;
      mesh.color.set(this.color);
      mesh.setParentMesh(this.parentMesh != null ? this.parentMesh : this);
      if (mesh.getRegion() != null) {
         mesh.updateRegion();
      }

      return mesh;
   }

   public MeshAttachment copy() {
      return this.parentMesh != null ? this.newLinkedMesh() : new MeshAttachment(this);
   }
}
