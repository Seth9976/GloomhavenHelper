package com.hm.spine.attachments;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Null;
import com.hm.spine.Bone;
import com.hm.spine.Slot;
import com.hm.spine.utils.SpineUtils;

public class RegionAttachment extends Attachment implements HasTextureRegion {
   public static final int BLX = 0;
   public static final int BLY = 1;
   public static final int ULX = 2;
   public static final int ULY = 3;
   public static final int URX = 4;
   public static final int URY = 5;
   public static final int BRX = 6;
   public static final int BRY = 7;
   private TextureRegion region;
   private String path;
   private float x;
   private float y;
   private float scaleX = 1.0F;
   private float scaleY = 1.0F;
   private float rotation;
   private final float[] uvs = new float[8];
   private float width;
   private float height;
   private final float[] offset = new float[8];
   private final Color color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
   @Null
   private Sequence sequence;

   public RegionAttachment(String name) {
      super(name);
   }

   protected RegionAttachment(RegionAttachment other) {
      super(other);
      this.region = other.region;
      this.path = other.path;
      this.x = other.x;
      this.y = other.y;
      this.scaleX = other.scaleX;
      this.scaleY = other.scaleY;
      this.rotation = other.rotation;
      this.width = other.width;
      this.height = other.height;
      SpineUtils.arraycopy(other.uvs, 0, this.uvs, 0, 8);
      SpineUtils.arraycopy(other.offset, 0, this.offset, 0, 8);
      this.color.set(other.color);
      this.sequence = other.sequence != null ? new Sequence(other.sequence) : null;
   }

   @Override
   public void updateRegion() {
      float width = this.getWidth();
      float height = this.getHeight();
      float localX2 = width / 2.0F;
      float localY2 = height / 2.0F;
      float localX = -localX2;
      float localY = -localY2;
      boolean rotated = false;
      if (this.region instanceof TextureAtlas.AtlasRegion) {
         TextureAtlas.AtlasRegion region = (TextureAtlas.AtlasRegion)this.region;
         localX += region.offsetX / region.originalWidth * width;
         localY += region.offsetY / region.originalHeight * height;
         if (region.degrees == 90) {
            rotated = true;
            localX2 -= (region.originalWidth - region.offsetX - region.packedHeight) / region.originalWidth * width;
            localY2 -= (region.originalHeight - region.offsetY - region.packedWidth) / region.originalHeight * height;
         } else {
            localX2 -= (region.originalWidth - region.offsetX - region.packedWidth) / region.originalWidth * width;
            localY2 -= (region.originalHeight - region.offsetY - region.packedHeight) / region.originalHeight * height;
         }
      }

      float scaleX = this.getScaleX();
      float scaleY = this.getScaleY();
      localX *= scaleX;
      localY *= scaleY;
      localX2 *= scaleX;
      localY2 *= scaleY;
      float rotation = this.getRotation();
      float cos = (float)Math.cos((float) (Math.PI / 180.0) * rotation);
      float sin = (float)Math.sin((float) (Math.PI / 180.0) * rotation);
      float x = this.getX();
      float y = this.getY();
      float localXCos = localX * cos + x;
      float localXSin = localX * sin;
      float localYCos = localY * cos + y;
      float localYSin = localY * sin;
      float localX2Cos = localX2 * cos + x;
      float localX2Sin = localX2 * sin;
      float localY2Cos = localY2 * cos + y;
      float localY2Sin = localY2 * sin;
      float[] offset = this.offset;
      offset[0] = localXCos - localYSin;
      offset[1] = localYCos + localXSin;
      offset[2] = localXCos - localY2Sin;
      offset[3] = localY2Cos + localXSin;
      offset[4] = localX2Cos - localY2Sin;
      offset[5] = localY2Cos + localX2Sin;
      offset[6] = localX2Cos - localYSin;
      offset[7] = localYCos + localX2Sin;
      float[] uvs = this.uvs;
      if (rotated) {
         uvs[4] = this.region.getU();
         uvs[5] = this.region.getV2();
         uvs[6] = this.region.getU();
         uvs[7] = this.region.getV();
         uvs[0] = this.region.getU2();
         uvs[1] = this.region.getV();
         uvs[2] = this.region.getU2();
         uvs[3] = this.region.getV2();
      } else {
         uvs[2] = this.region.getU();
         uvs[3] = this.region.getV2();
         uvs[4] = this.region.getU();
         uvs[5] = this.region.getV();
         uvs[6] = this.region.getU2();
         uvs[7] = this.region.getV();
         uvs[0] = this.region.getU2();
         uvs[1] = this.region.getV2();
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

   public void computeWorldVertices(Slot slot, float[] worldVertices, int offset, int stride) {
      if (this.sequence != null) {
         this.sequence.apply(slot, this);
      }

      float[] vertexOffset = this.offset;
      Bone bone = slot.getBone();
      float x = bone.getWorldX();
      float y = bone.getWorldY();
      float a = bone.getA();
      float b = bone.getB();
      float c = bone.getC();
      float d = bone.getD();
      float offsetX = vertexOffset[6];
      float offsetY = vertexOffset[7];
      worldVertices[offset] = offsetX * a + offsetY * b + x;
      worldVertices[offset + 1] = offsetX * c + offsetY * d + y;
      offset += stride;
      offsetX = vertexOffset[0];
      offsetY = vertexOffset[1];
      worldVertices[offset] = offsetX * a + offsetY * b + x;
      worldVertices[offset + 1] = offsetX * c + offsetY * d + y;
      offset += stride;
      offsetX = vertexOffset[2];
      offsetY = vertexOffset[3];
      worldVertices[offset] = offsetX * a + offsetY * b + x;
      worldVertices[offset + 1] = offsetX * c + offsetY * d + y;
      offset += stride;
      offsetX = vertexOffset[4];
      offsetY = vertexOffset[5];
      worldVertices[offset] = offsetX * a + offsetY * b + x;
      worldVertices[offset + 1] = offsetX * c + offsetY * d + y;
   }

   public float[] getOffset() {
      return this.offset;
   }

   public float[] getUVs() {
      return this.uvs;
   }

   public float getX() {
      return this.x;
   }

   public void setX(float x) {
      this.x = x;
   }

   public float getY() {
      return this.y;
   }

   public void setY(float y) {
      this.y = y;
   }

   public float getScaleX() {
      return this.scaleX;
   }

   public void setScaleX(float scaleX) {
      this.scaleX = scaleX;
   }

   public float getScaleY() {
      return this.scaleY;
   }

   public void setScaleY(float scaleY) {
      this.scaleY = scaleY;
   }

   public float getRotation() {
      return this.rotation;
   }

   public void setRotation(float rotation) {
      this.rotation = rotation;
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

   @Null
   @Override
   public Sequence getSequence() {
      return this.sequence;
   }

   @Override
   public void setSequence(@Null Sequence sequence) {
      this.sequence = sequence;
   }

   public RegionAttachment copy() {
      return new RegionAttachment(this);
   }
}
