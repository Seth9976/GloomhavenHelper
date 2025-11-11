package com.hm.spine.attachments;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Null;
import com.hm.spine.Skin;

public class AtlasAttachmentLoader implements AttachmentLoader {
   private TextureAtlas atlas;

   public AtlasAttachmentLoader(TextureAtlas atlas) {
      if (atlas == null) {
         throw new IllegalArgumentException("atlas cannot be null.");
      } else {
         this.atlas = atlas;
      }
   }

   private void loadSequence(String name, String basePath, Sequence sequence) {
      TextureRegion[] regions = sequence.getRegions();
      int i = 0;

      for (int n = regions.length; i < n; i++) {
         String path = sequence.getPath(basePath, i);
         regions[i] = this.atlas.findRegion(path);
         if (regions[i] == null) {
            throw new RuntimeException("Region not found in atlas: " + path + " (sequence: " + name + ")");
         }
      }
   }

   @Override
   public RegionAttachment newRegionAttachment(Skin skin, String name, String path, @Null Sequence sequence) {
      RegionAttachment attachment = new RegionAttachment(name);
      if (sequence != null) {
         this.loadSequence(name, path, sequence);
      } else {
         TextureAtlas.AtlasRegion region = this.atlas.findRegion(path);
         if (region == null) {
            throw new RuntimeException("Region not found in atlas: " + path + " (region attachment: " + name + ")");
         }

         attachment.setRegion(region);
      }

      return attachment;
   }

   @Override
   public MeshAttachment newMeshAttachment(Skin skin, String name, String path, @Null Sequence sequence) {
      MeshAttachment attachment = new MeshAttachment(name);
      if (sequence != null) {
         this.loadSequence(name, path, sequence);
      } else {
         TextureAtlas.AtlasRegion region = this.atlas.findRegion(path);
         if (region == null) {
            throw new RuntimeException("Region not found in atlas: " + path + " (mesh attachment: " + name + ")");
         }

         attachment.setRegion(region);
      }

      return attachment;
   }

   @Override
   public BoundingBoxAttachment newBoundingBoxAttachment(Skin skin, String name) {
      return new BoundingBoxAttachment(name);
   }

   @Override
   public ClippingAttachment newClippingAttachment(Skin skin, String name) {
      return new ClippingAttachment(name);
   }

   @Override
   public PathAttachment newPathAttachment(Skin skin, String name) {
      return new PathAttachment(name);
   }

   @Override
   public PointAttachment newPointAttachment(Skin skin, String name) {
      return new PointAttachment(name);
   }
}
