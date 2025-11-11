package com.hm.spine;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DataInput;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.SerializationException;
import com.hm.spine.attachments.Attachment;
import com.hm.spine.attachments.AttachmentLoader;
import com.hm.spine.attachments.AttachmentType;
import com.hm.spine.attachments.BoundingBoxAttachment;
import com.hm.spine.attachments.ClippingAttachment;
import com.hm.spine.attachments.MeshAttachment;
import com.hm.spine.attachments.PathAttachment;
import com.hm.spine.attachments.PointAttachment;
import com.hm.spine.attachments.RegionAttachment;
import com.hm.spine.attachments.Sequence;
import com.hm.spine.attachments.VertexAttachment;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class SkeletonBinary extends SkeletonLoader {
   public static final int BONE_ROTATE = 0;
   public static final int BONE_TRANSLATE = 1;
   public static final int BONE_TRANSLATEX = 2;
   public static final int BONE_TRANSLATEY = 3;
   public static final int BONE_SCALE = 4;
   public static final int BONE_SCALEX = 5;
   public static final int BONE_SCALEY = 6;
   public static final int BONE_SHEAR = 7;
   public static final int BONE_SHEARX = 8;
   public static final int BONE_SHEARY = 9;
   public static final int SLOT_ATTACHMENT = 0;
   public static final int SLOT_RGBA = 1;
   public static final int SLOT_RGB = 2;
   public static final int SLOT_RGBA2 = 3;
   public static final int SLOT_RGB2 = 4;
   public static final int SLOT_ALPHA = 5;
   public static final int ATTACHMENT_DEFORM = 0;
   public static final int ATTACHMENT_SEQUENCE = 1;
   public static final int PATH_POSITION = 0;
   public static final int PATH_SPACING = 1;
   public static final int PATH_MIX = 2;
   public static final int CURVE_LINEAR = 0;
   public static final int CURVE_STEPPED = 1;
   public static final int CURVE_BEZIER = 2;

   public SkeletonBinary(AttachmentLoader attachmentLoader) {
      super(attachmentLoader);
   }

   public SkeletonBinary(TextureAtlas atlas) {
      super(atlas);
   }

   @Override
   public SkeletonData readSkeletonData(FileHandle file) {
      if (file == null) {
         throw new IllegalArgumentException("file cannot be null.");
      } else {
         SkeletonData skeletonData = this.readSkeletonData(file.read());
         skeletonData.name = file.nameWithoutExtension();
         return skeletonData;
      }
   }

   @Override
   public SkeletonData readSkeletonData(InputStream dataInput) {
      if (dataInput == null) {
         throw new IllegalArgumentException("dataInput cannot be null.");
      } else {
         float scale = this.scale;
         SkeletonBinary.SkeletonInput input = new SkeletonBinary.SkeletonInput(dataInput);
         SkeletonData skeletonData = new SkeletonData();

         try {
            long hash = input.readLong();
            skeletonData.hash = hash == 0L ? null : Long.toString(hash);
            skeletonData.version = input.readString();
            if (skeletonData.version.isEmpty()) {
               skeletonData.version = null;
            }

            skeletonData.x = input.readFloat();
            skeletonData.y = input.readFloat();
            skeletonData.width = input.readFloat();
            skeletonData.height = input.readFloat();
            boolean nonessential = input.readBoolean();
            if (nonessential) {
               skeletonData.fps = input.readFloat();
               skeletonData.imagesPath = input.readString();
               if (skeletonData.imagesPath.isEmpty()) {
                  skeletonData.imagesPath = null;
               }

               skeletonData.audioPath = input.readString();
               if (skeletonData.audioPath.isEmpty()) {
                  skeletonData.audioPath = null;
               }
            }

            int n;
            String[] arrayOfString = input.strings = new String[n = input.readInt(true)];

            for (int i = 0; i < n; i++) {
               arrayOfString[i] = input.readString();
            }

            Object[] bones = skeletonData.bones.setSize(n = input.readInt(true));

            for (int j = 0; j < n; j++) {
               String name = input.readString();
               BoneData parent = j == 0 ? null : (BoneData)bones[input.readInt(true)];
               BoneData data = new BoneData(j, name, parent);
               data.rotation = input.readFloat();
               data.x = input.readFloat() * scale;
               data.y = input.readFloat() * scale;
               data.scaleX = input.readFloat();
               data.scaleY = input.readFloat();
               data.shearX = input.readFloat();
               data.shearY = input.readFloat();
               data.length = input.readFloat() * scale;
               data.transformMode = BoneData.TransformMode.values[input.readInt(true)];
               data.skinRequired = input.readBoolean();
               if (nonessential) {
                  Color.rgba8888ToColor(data.color, input.readInt());
               }

               bones[j] = data;
            }

            Object[] slots = skeletonData.slots.setSize(n = input.readInt(true));

            for (int k = 0; k < n; k++) {
               String slotName = input.readString();
               BoneData boneData = (BoneData)bones[input.readInt(true)];
               SlotData data = new SlotData(k, slotName, boneData);
               Color.rgba8888ToColor(data.color, input.readInt());
               int darkColor = input.readInt();
               if (darkColor != -1) {
                  Color.rgb888ToColor(data.darkColor = new Color(), darkColor);
               }

               data.attachmentName = input.readStringRef();
               data.blendMode = BlendMode.values[input.readInt(true)];
               slots[k] = data;
            }

            Object[] arrayOfObject1 = skeletonData.ikConstraints.setSize(n = input.readInt(true));

            for (int var42 = 0; var42 < n; var42++) {
               IkConstraintData data = new IkConstraintData(input.readString());
               data.order = input.readInt(true);
               data.skinRequired = input.readBoolean();
               int nn;
               Object[] constraintBones = data.bones.setSize(nn = input.readInt(true));

               for (int ii = 0; ii < nn; ii++) {
                  constraintBones[ii] = bones[input.readInt(true)];
               }

               data.target = (BoneData)bones[input.readInt(true)];
               data.mix = input.readFloat();
               data.softness = input.readFloat() * scale;
               data.bendDirection = input.readByte();
               data.compress = input.readBoolean();
               data.stretch = input.readBoolean();
               data.uniform = input.readBoolean();
               arrayOfObject1[var42] = data;
            }

            arrayOfObject1 = skeletonData.transformConstraints.setSize(n = input.readInt(true));

            for (int var43 = 0; var43 < n; var43++) {
               TransformConstraintData data = new TransformConstraintData(input.readString());
               data.order = input.readInt(true);
               data.skinRequired = input.readBoolean();
               int nn;
               Object[] constraintBones = data.bones.setSize(nn = input.readInt(true));

               for (int ii = 0; ii < nn; ii++) {
                  constraintBones[ii] = bones[input.readInt(true)];
               }

               data.target = (BoneData)bones[input.readInt(true)];
               data.local = input.readBoolean();
               data.relative = input.readBoolean();
               data.offsetRotation = input.readFloat();
               data.offsetX = input.readFloat() * scale;
               data.offsetY = input.readFloat() * scale;
               data.offsetScaleX = input.readFloat();
               data.offsetScaleY = input.readFloat();
               data.offsetShearY = input.readFloat();
               data.mixRotate = input.readFloat();
               data.mixX = input.readFloat();
               data.mixY = input.readFloat();
               data.mixScaleX = input.readFloat();
               data.mixScaleY = input.readFloat();
               data.mixShearY = input.readFloat();
               arrayOfObject1[var43] = data;
            }

            arrayOfObject1 = skeletonData.pathConstraints.setSize(n = input.readInt(true));

            for (int var44 = 0; var44 < n; var44++) {
               PathConstraintData data = new PathConstraintData(input.readString());
               data.order = input.readInt(true);
               data.skinRequired = input.readBoolean();
               int nn;
               Object[] constraintBones = data.bones.setSize(nn = input.readInt(true));

               for (int ii = 0; ii < nn; ii++) {
                  constraintBones[ii] = bones[input.readInt(true)];
               }

               data.target = (SlotData)slots[input.readInt(true)];
               data.positionMode = PathConstraintData.PositionMode.values[input.readInt(true)];
               data.spacingMode = PathConstraintData.SpacingMode.values[input.readInt(true)];
               data.rotateMode = PathConstraintData.RotateMode.values[input.readInt(true)];
               data.offsetRotation = input.readFloat();
               data.position = input.readFloat();
               if (data.positionMode == PathConstraintData.PositionMode.fixed) {
                  data.position *= scale;
               }

               data.spacing = input.readFloat();
               if (data.spacingMode == PathConstraintData.SpacingMode.length || data.spacingMode == PathConstraintData.SpacingMode.fixed) {
                  data.spacing *= scale;
               }

               data.mixRotate = input.readFloat();
               data.mixX = input.readFloat();
               data.mixY = input.readFloat();
               arrayOfObject1[var44] = data;
            }

            Skin defaultSkin = this.readSkin(input, skeletonData, true, nonessential);
            if (defaultSkin != null) {
               skeletonData.defaultSkin = defaultSkin;
               skeletonData.skins.add(defaultSkin);
            }

            int m = skeletonData.skins.size;

            for (Object[] var49 = skeletonData.skins.setSize(n = m + input.readInt(true)); m < n; m++) {
               var49[m] = this.readSkin(input, skeletonData, false, nonessential);
            }

            n = this.linkedMeshes.size;
            Object[] items = this.linkedMeshes.items;

            for (int i1 = 0; i1 < n; i1++) {
               SkeletonJson.LinkedMesh linkedMesh = (SkeletonJson.LinkedMesh)items[i1];
               Skin skin = linkedMesh.skin == null ? skeletonData.getDefaultSkin() : skeletonData.findSkin(linkedMesh.skin);
               if (skin == null) {
                  throw new SerializationException("Skin not found: " + linkedMesh.skin);
               }

               Attachment parent = skin.getAttachment(linkedMesh.slotIndex, linkedMesh.parent);
               if (parent == null) {
                  throw new SerializationException("Parent mesh not found: " + linkedMesh.parent);
               }

               linkedMesh.mesh.setTimelineAttachment((Attachment)(linkedMesh.inheritTimelines ? parent : linkedMesh.mesh));
               linkedMesh.mesh.setParentMesh((MeshAttachment)parent);
               if (linkedMesh.mesh.getSequence() == null) {
                  linkedMesh.mesh.updateRegion();
               }
            }

            this.linkedMeshes.clear();
            arrayOfObject1 = skeletonData.events.setSize(n = input.readInt(true));

            for (int var68 = 0; var68 < n; var68++) {
               EventData data = new EventData(input.readStringRef());
               data.intValue = input.readInt(false);
               data.floatValue = input.readFloat();
               data.stringValue = input.readString();
               data.audioPath = input.readString();
               if (data.audioPath != null) {
                  data.volume = input.readFloat();
                  data.balance = input.readFloat();
               }

               arrayOfObject1[var68] = data;
            }

            arrayOfObject1 = skeletonData.animations.setSize(n = input.readInt(true));

            for (int var69 = 0; var69 < n; var69++) {
               arrayOfObject1[var69] = this.readAnimation(input, input.readString(), skeletonData);
            }
         } catch (IOException var28) {
            throw new SerializationException("Error reading skeleton file.", var28);
         } finally {
            try {
               input.close();
            } catch (IOException var27) {
            }
         }

         return skeletonData;
      }
   }

   @Null
   private Skin readSkin(SkeletonBinary.SkeletonInput input, SkeletonData skeletonData, boolean defaultSkin, boolean nonessential) throws IOException {
      Skin skin;
      int slotCount;
      if (defaultSkin) {
         slotCount = input.readInt(true);
         if (slotCount == 0) {
            return null;
         }

         skin = new Skin("default");
      } else {
         skin = new Skin(input.readStringRef());
         Object[] bones = skin.bones.setSize(input.readInt(true));
         Object[] items = skeletonData.bones.items;
         int j = 0;

         for (int n = skin.bones.size; j < n; j++) {
            bones[j] = items[input.readInt(true)];
         }

         items = skeletonData.ikConstraints.items;
         j = 0;

         for (int var22 = input.readInt(true); j < var22; j++) {
            skin.constraints.add((ConstraintData)items[input.readInt(true)]);
         }

         items = skeletonData.transformConstraints.items;
         j = 0;

         for (int var23 = input.readInt(true); j < var23; j++) {
            skin.constraints.add((ConstraintData)items[input.readInt(true)]);
         }

         items = skeletonData.pathConstraints.items;
         j = 0;

         for (int var24 = input.readInt(true); j < var24; j++) {
            skin.constraints.add((ConstraintData)items[input.readInt(true)]);
         }

         skin.constraints.shrink();
         slotCount = input.readInt(true);
      }

      for (int i = 0; i < slotCount; i++) {
         int slotIndex = input.readInt(true);
         int ii = 0;

         for (int nn = input.readInt(true); ii < nn; ii++) {
            String name = input.readStringRef();
            Attachment attachment = this.readAttachment(input, skeletonData, skin, slotIndex, name, nonessential);
            if (attachment != null) {
               skin.setAttachment(slotIndex, name, attachment);
            }
         }
      }

      return skin;
   }

   private Attachment readAttachment(
      SkeletonBinary.SkeletonInput input, SkeletonData skeletonData, Skin skin, int slotIndex, String attachmentName, boolean nonessential
   ) throws IOException {
      float scale = this.scale;
      String name = input.readStringRef();
      if (name == null) {
         name = attachmentName;
      }

      switch (AttachmentType.values[input.readByte()]) {
         case region:
            String str1 = input.readStringRef();
            float f1 = input.readFloat();
            float f2 = input.readFloat();
            float f3 = input.readFloat();
            float scaleX = input.readFloat();
            float scaleY = input.readFloat();
            float f4 = input.readFloat();
            float f5 = input.readFloat();
            int i4 = input.readInt();
            Sequence sequence2 = this.readSequence(input);
            if (str1 == null) {
               str1 = name;
            }

            RegionAttachment region = this.attachmentLoader.newRegionAttachment(skin, name, str1, sequence2);
            if (region == null) {
               return null;
            }

            region.setPath(str1);
            region.setX(f2 * scale);
            region.setY(f3 * scale);
            region.setScaleX(scaleX);
            region.setScaleY(scaleY);
            region.setRotation(f1);
            region.setWidth(f4 * scale);
            region.setHeight(f5 * scale);
            Color.rgba8888ToColor(region.getColor(), i4);
            region.setSequence(sequence2);
            if (sequence2 == null) {
               region.updateRegion();
            }

            return region;
         case boundingbox:
            int vertexCount = input.readInt(true);
            SkeletonBinary.Vertices vertices = this.readVertices(input, vertexCount);
            int i1 = nonessential ? input.readInt() : 0;
            BoundingBoxAttachment box = this.attachmentLoader.newBoundingBoxAttachment(skin, name);
            if (box == null) {
               return null;
            }

            box.setWorldVerticesLength(vertexCount << 1);
            box.setVertices(vertices.vertices);
            box.setBones(vertices.bones);
            if (nonessential) {
               Color.rgba8888ToColor(box.getColor(), i1);
            }

            return box;
         case mesh:
            String pathx = input.readStringRef();
            int colorx = input.readInt();
            int m = input.readInt(true);
            float[] uvs = this.readFloatArray(input, m << 1, 1.0F);
            short[] triangles = this.readShortArray(input);
            SkeletonBinary.Vertices vertices3 = this.readVertices(input, m);
            int hullLength = input.readInt(true);
            Sequence sequence1 = this.readSequence(input);
            short[] edges = null;
            float f6 = 0.0F;
            float f7 = 0.0F;
            if (nonessential) {
               edges = this.readShortArray(input);
               f6 = input.readFloat();
               f7 = input.readFloat();
            }

            if (pathx == null) {
               pathx = name;
            }

            MeshAttachment meshAttachment1 = this.attachmentLoader.newMeshAttachment(skin, name, pathx, sequence1);
            if (meshAttachment1 == null) {
               return null;
            }

            meshAttachment1.setPath(pathx);
            Color.rgba8888ToColor(meshAttachment1.getColor(), colorx);
            meshAttachment1.setBones(vertices3.bones);
            meshAttachment1.setVertices(vertices3.vertices);
            meshAttachment1.setWorldVerticesLength(m << 1);
            meshAttachment1.setTriangles(triangles);
            meshAttachment1.setRegionUVs(uvs);
            if (sequence1 == null) {
               meshAttachment1.updateRegion();
            }

            meshAttachment1.setHullLength(hullLength << 1);
            meshAttachment1.setSequence(sequence1);
            if (nonessential) {
               meshAttachment1.setEdges(edges);
               meshAttachment1.setWidth(f6 * scale);
               meshAttachment1.setHeight(f7 * scale);
            }

            return meshAttachment1;
         case linkedmesh:
            String path = input.readStringRef();
            int color = input.readInt();
            String skinName = input.readStringRef();
            String parent = input.readStringRef();
            boolean inheritTimelines = input.readBoolean();
            Sequence sequence = this.readSequence(input);
            float width = 0.0F;
            float height = 0.0F;
            if (nonessential) {
               width = input.readFloat();
               height = input.readFloat();
            }

            if (path == null) {
               path = name;
            }

            MeshAttachment mesh = this.attachmentLoader.newMeshAttachment(skin, name, path, sequence);
            if (mesh == null) {
               return null;
            }

            mesh.setPath(path);
            Color.rgba8888ToColor(mesh.getColor(), color);
            mesh.setSequence(sequence);
            if (nonessential) {
               mesh.setWidth(width * scale);
               mesh.setHeight(height * scale);
            }

            this.linkedMeshes.add(new SkeletonJson.LinkedMesh(mesh, skinName, slotIndex, parent, inheritTimelines));
            return mesh;
         case path:
            boolean closed = input.readBoolean();
            boolean constantSpeed = input.readBoolean();
            int k = input.readInt(true);
            SkeletonBinary.Vertices vertices2 = this.readVertices(input, k);
            float[] lengths = new float[k / 3];
            int i = 0;

            for (int n = lengths.length; i < n; i++) {
               lengths[i] = input.readFloat() * scale;
            }

            int i3 = nonessential ? input.readInt() : 0;
            PathAttachment pathAttachment = this.attachmentLoader.newPathAttachment(skin, name);
            if (pathAttachment == null) {
               return null;
            } else {
               pathAttachment.setClosed(closed);
               pathAttachment.setConstantSpeed(constantSpeed);
               pathAttachment.setWorldVerticesLength(k << 1);
               pathAttachment.setVertices(vertices2.vertices);
               pathAttachment.setBones(vertices2.bones);
               pathAttachment.setLengths(lengths);
               if (nonessential) {
                  Color.rgba8888ToColor(pathAttachment.getColor(), i3);
               }

               return pathAttachment;
            }
         case point:
            float rotation = input.readFloat();
            float x = input.readFloat();
            float y = input.readFloat();
            int i2x = nonessential ? input.readInt() : 0;
            PointAttachment point = this.attachmentLoader.newPointAttachment(skin, name);
            if (point == null) {
               return null;
            }

            point.setX(x * scale);
            point.setY(y * scale);
            point.setRotation(rotation);
            if (nonessential) {
               Color.rgba8888ToColor(point.getColor(), i2x);
            }

            return point;
         case clipping:
            int endSlotIndex = input.readInt(true);
            int j = input.readInt(true);
            SkeletonBinary.Vertices vertices1 = this.readVertices(input, j);
            int i2 = nonessential ? input.readInt() : 0;
            ClippingAttachment clip = this.attachmentLoader.newClippingAttachment(skin, name);
            if (clip == null) {
               return null;
            }

            clip.setEndSlot((SlotData)skeletonData.slots.get(endSlotIndex));
            clip.setWorldVerticesLength(j << 1);
            clip.setVertices(vertices1.vertices);
            clip.setBones(vertices1.bones);
            if (nonessential) {
               Color.rgba8888ToColor(clip.getColor(), i2);
            }

            return clip;
         default:
            return null;
      }
   }

   private Sequence readSequence(SkeletonBinary.SkeletonInput input) throws IOException {
      if (!input.readBoolean()) {
         return null;
      } else {
         Sequence sequence = new Sequence(input.readInt(true));
         sequence.setStart(input.readInt(true));
         sequence.setDigits(input.readInt(true));
         sequence.setSetupIndex(input.readInt(true));
         return sequence;
      }
   }

   private SkeletonBinary.Vertices readVertices(SkeletonBinary.SkeletonInput input, int vertexCount) throws IOException {
      float scale = this.scale;
      int verticesLength = vertexCount << 1;
      SkeletonBinary.Vertices vertices = new SkeletonBinary.Vertices();
      if (!input.readBoolean()) {
         vertices.vertices = this.readFloatArray(input, verticesLength, scale);
         return vertices;
      } else {
         FloatArray weights = new FloatArray(verticesLength * 3 * 3);
         IntArray bonesArray = new IntArray(verticesLength * 3);

         for (int i = 0; i < vertexCount; i++) {
            int boneCount = input.readInt(true);
            bonesArray.add(boneCount);

            for (int ii = 0; ii < boneCount; ii++) {
               bonesArray.add(input.readInt(true));
               weights.add(input.readFloat() * scale);
               weights.add(input.readFloat() * scale);
               weights.add(input.readFloat());
            }
         }

         vertices.vertices = weights.toArray();
         vertices.bones = bonesArray.toArray();
         return vertices;
      }
   }

   private float[] readFloatArray(SkeletonBinary.SkeletonInput input, int n, float scale) throws IOException {
      float[] array = new float[n];
      if (scale == 1.0F) {
         for (int i = 0; i < n; i++) {
            array[i] = input.readFloat();
         }
      } else {
         for (int i = 0; i < n; i++) {
            array[i] = input.readFloat() * scale;
         }
      }

      return array;
   }

   private short[] readShortArray(SkeletonBinary.SkeletonInput input) throws IOException {
      int n = input.readInt(true);
      short[] array = new short[n];

      for (int i = 0; i < n; i++) {
         array[i] = input.readShort();
      }

      return array;
   }

   private Animation readAnimation(SkeletonBinary.SkeletonInput input, String name, SkeletonData skeletonData) throws IOException {
      Array timelines = new Array(input.readInt(true));
      float scale = this.scale;
      int i = 0;

      for (int n = input.readInt(true); i < n; i++) {
         int slotIndex = input.readInt(true);
         int ii = 0;

         label449:
         for (int nn = input.readInt(true); ii < nn; ii++) {
            int timelineType = input.readByte();
            int frameCount = input.readInt(true);
            int frameLast = frameCount - 1;
            switch (timelineType) {
               case 0:
                  Animation.AttachmentTimeline attachmentTimeline = new Animation.AttachmentTimeline(frameCount, slotIndex);

                  for (int frame = 0; frame < frameCount; frame++) {
                     attachmentTimeline.setFrame(frame, input.readFloat(), input.readStringRef());
                  }

                  timelines.add(attachmentTimeline);
                  break;
               case 1:
                  Animation.RGBATimeline rGBATimeline = new Animation.RGBATimeline(frameCount, input.readInt(true), slotIndex);
                  float time = input.readFloat();
                  float r = input.read() / 255.0F;
                  float g = input.read() / 255.0F;
                  float b = input.read() / 255.0F;
                  float f2 = input.read() / 255.0F;
                  int i3 = 0;
                  int i4 = 0;

                  while (true) {
                     rGBATimeline.setFrame(i3, time, r, g, b, f2);
                     if (i3 == frameLast) {
                        timelines.add(rGBATimeline);
                        continue label449;
                     }

                     float time2 = input.readFloat();
                     float f6 = input.read() / 255.0F;
                     float f7 = input.read() / 255.0F;
                     float f8 = input.read() / 255.0F;
                     float a2 = input.read() / 255.0F;
                     switch (input.readByte()) {
                        case 1:
                           rGBATimeline.setStepped(i3);
                           break;
                        case 2:
                           this.setBezier(input, rGBATimeline, i4++, i3, 0, time, time2, r, f6, 1.0F);
                           this.setBezier(input, rGBATimeline, i4++, i3, 1, time, time2, g, f7, 1.0F);
                           this.setBezier(input, rGBATimeline, i4++, i3, 2, time, time2, b, f8, 1.0F);
                           this.setBezier(input, rGBATimeline, i4++, i3, 3, time, time2, f2, a2, 1.0F);
                     }

                     time = time2;
                     r = f6;
                     g = f7;
                     b = f8;
                     f2 = a2;
                     i3++;
                  }
               case 2:
                  Animation.RGBTimeline rGBTimeline = new Animation.RGBTimeline(frameCount, input.readInt(true), slotIndex);
                  float time = input.readFloat();
                  float r = input.read() / 255.0F;
                  float g = input.read() / 255.0F;
                  float b = input.read() / 255.0F;
                  int i1 = 0;
                  int i2 = 0;

                  while (true) {
                     rGBTimeline.setFrame(i1, time, r, g, b);
                     if (i1 == frameLast) {
                        timelines.add(rGBTimeline);
                        continue label449;
                     }

                     float time2 = input.readFloat();
                     float f6 = input.read() / 255.0F;
                     float f7 = input.read() / 255.0F;
                     float f8 = input.read() / 255.0F;
                     switch (input.readByte()) {
                        case 1:
                           rGBTimeline.setStepped(i1);
                           break;
                        case 2:
                           this.setBezier(input, rGBTimeline, i2++, i1, 0, time, time2, r, f6, 1.0F);
                           this.setBezier(input, rGBTimeline, i2++, i1, 1, time, time2, g, f7, 1.0F);
                           this.setBezier(input, rGBTimeline, i2++, i1, 2, time, time2, b, f8, 1.0F);
                     }

                     time = time2;
                     r = f6;
                     g = f7;
                     b = f8;
                     i1++;
                  }
               case 3:
                  Animation.RGBA2Timeline rGBA2Timeline = new Animation.RGBA2Timeline(frameCount, input.readInt(true), slotIndex);
                  float time = input.readFloat();
                  float r = input.read() / 255.0F;
                  float g = input.read() / 255.0F;
                  float b = input.read() / 255.0F;
                  float f1 = input.read() / 255.0F;
                  float f3 = input.read() / 255.0F;
                  float f4 = input.read() / 255.0F;
                  float f5 = input.read() / 255.0F;
                  int i7 = 0;
                  int i8 = 0;

                  while (true) {
                     rGBA2Timeline.setFrame(i7, time, r, g, b, f1, f3, f4, f5);
                     if (i7 == frameLast) {
                        timelines.add(rGBA2Timeline);
                        continue label449;
                     }

                     float time2 = input.readFloat();
                     float nr = input.read() / 255.0F;
                     float ng = input.read() / 255.0F;
                     float nb = input.read() / 255.0F;
                     float na = input.read() / 255.0F;
                     float nr2 = input.read() / 255.0F;
                     float ng2 = input.read() / 255.0F;
                     float nb2 = input.read() / 255.0F;
                     switch (input.readByte()) {
                        case 1:
                           rGBA2Timeline.setStepped(i7);
                           break;
                        case 2:
                           this.setBezier(input, rGBA2Timeline, i8++, i7, 0, time, time2, r, nr, 1.0F);
                           this.setBezier(input, rGBA2Timeline, i8++, i7, 1, time, time2, g, ng, 1.0F);
                           this.setBezier(input, rGBA2Timeline, i8++, i7, 2, time, time2, b, nb, 1.0F);
                           this.setBezier(input, rGBA2Timeline, i8++, i7, 3, time, time2, f1, na, 1.0F);
                           this.setBezier(input, rGBA2Timeline, i8++, i7, 4, time, time2, f3, nr2, 1.0F);
                           this.setBezier(input, rGBA2Timeline, i8++, i7, 5, time, time2, f4, ng2, 1.0F);
                           this.setBezier(input, rGBA2Timeline, i8++, i7, 6, time, time2, f5, nb2, 1.0F);
                     }

                     time = time2;
                     r = nr;
                     g = ng;
                     b = nb;
                     f1 = na;
                     f3 = nr2;
                     f4 = ng2;
                     f5 = nb2;
                     i7++;
                  }
               case 4:
                  Animation.RGB2Timeline rGB2Timeline = new Animation.RGB2Timeline(frameCount, input.readInt(true), slotIndex);
                  float time = input.readFloat();
                  float r = input.read() / 255.0F;
                  float g = input.read() / 255.0F;
                  float b = input.read() / 255.0F;
                  float r2 = input.read() / 255.0F;
                  float g2 = input.read() / 255.0F;
                  float b2 = input.read() / 255.0F;
                  int i5 = 0;
                  int i6 = 0;

                  while (true) {
                     rGB2Timeline.setFrame(i5, time, r, g, b, r2, g2, b2);
                     if (i5 == frameLast) {
                        timelines.add(rGB2Timeline);
                        continue label449;
                     }

                     float time2 = input.readFloat();
                     float nr = input.read() / 255.0F;
                     float ng = input.read() / 255.0F;
                     float nb = input.read() / 255.0F;
                     float nr2 = input.read() / 255.0F;
                     float ng2 = input.read() / 255.0F;
                     float nb2 = input.read() / 255.0F;
                     switch (input.readByte()) {
                        case 1:
                           rGB2Timeline.setStepped(i5);
                           break;
                        case 2:
                           this.setBezier(input, rGB2Timeline, i6++, i5, 0, time, time2, r, nr, 1.0F);
                           this.setBezier(input, rGB2Timeline, i6++, i5, 1, time, time2, g, ng, 1.0F);
                           this.setBezier(input, rGB2Timeline, i6++, i5, 2, time, time2, b, nb, 1.0F);
                           this.setBezier(input, rGB2Timeline, i6++, i5, 3, time, time2, r2, nr2, 1.0F);
                           this.setBezier(input, rGB2Timeline, i6++, i5, 4, time, time2, g2, ng2, 1.0F);
                           this.setBezier(input, rGB2Timeline, i6++, i5, 5, time, time2, b2, nb2, 1.0F);
                     }

                     time = time2;
                     r = nr;
                     g = ng;
                     b = nb;
                     r2 = nr2;
                     g2 = ng2;
                     b2 = nb2;
                     i5++;
                  }
               case 5:
                  Animation.AlphaTimeline timeline = new Animation.AlphaTimeline(frameCount, input.readInt(true), slotIndex);
                  float time = input.readFloat();
                  float a = input.read() / 255.0F;
                  int m = 0;
                  int bezier = 0;

                  while (true) {
                     timeline.setFrame(m, time, a);
                     if (m == frameLast) {
                        timelines.add(timeline);
                        break;
                     }

                     float time2 = input.readFloat();
                     float a2 = input.read() / 255.0F;
                     switch (input.readByte()) {
                        case 1:
                           timeline.setStepped(m);
                           break;
                        case 2:
                           this.setBezier(input, timeline, bezier++, m, 0, time, time2, a, a2, 1.0F);
                     }

                     time = time2;
                     a = a2;
                     m++;
                  }
            }
         }
      }

      i = 0;

      for (int var57 = input.readInt(true); i < var57; i++) {
         int boneIndex = input.readInt(true);
         int ii = 0;

         for (int nn = input.readInt(true); ii < nn; ii++) {
            int type = input.readByte();
            int frameCount = input.readInt(true);
            int bezierCount = input.readInt(true);
            switch (type) {
               case 0:
                  timelines.add(this.readTimeline(input, new Animation.RotateTimeline(frameCount, bezierCount, boneIndex), 1.0F));
                  break;
               case 1:
                  timelines.add(this.readTimeline(input, new Animation.TranslateTimeline(frameCount, bezierCount, boneIndex), scale));
                  break;
               case 2:
                  timelines.add(this.readTimeline(input, new Animation.TranslateXTimeline(frameCount, bezierCount, boneIndex), scale));
                  break;
               case 3:
                  timelines.add(this.readTimeline(input, new Animation.TranslateYTimeline(frameCount, bezierCount, boneIndex), scale));
                  break;
               case 4:
                  timelines.add(this.readTimeline(input, new Animation.ScaleTimeline(frameCount, bezierCount, boneIndex), 1.0F));
                  break;
               case 5:
                  timelines.add(this.readTimeline(input, new Animation.ScaleXTimeline(frameCount, bezierCount, boneIndex), 1.0F));
                  break;
               case 6:
                  timelines.add(this.readTimeline(input, new Animation.ScaleYTimeline(frameCount, bezierCount, boneIndex), 1.0F));
                  break;
               case 7:
                  timelines.add(this.readTimeline(input, new Animation.ShearTimeline(frameCount, bezierCount, boneIndex), 1.0F));
                  break;
               case 8:
                  timelines.add(this.readTimeline(input, new Animation.ShearXTimeline(frameCount, bezierCount, boneIndex), 1.0F));
                  break;
               case 9:
                  timelines.add(this.readTimeline(input, new Animation.ShearYTimeline(frameCount, bezierCount, boneIndex), 1.0F));
            }
         }
      }

      i = 0;

      for (int var58 = input.readInt(true); i < var58; i++) {
         int index = input.readInt(true);
         int frameCount = input.readInt(true);
         int frameLast = frameCount - 1;
         Animation.IkConstraintTimeline timeline = new Animation.IkConstraintTimeline(frameCount, input.readInt(true), index);
         float time = input.readFloat();
         float mix = input.readFloat();
         float softness = input.readFloat() * scale;
         int frame = 0;
         int bezier = 0;

         while (true) {
            timeline.setFrame(frame, time, mix, softness, input.readByte(), input.readBoolean(), input.readBoolean());
            if (frame == frameLast) {
               timelines.add(timeline);
               break;
            }

            float time2 = input.readFloat();
            float mix2 = input.readFloat();
            float softness2 = input.readFloat() * scale;
            switch (input.readByte()) {
               case 1:
                  timeline.setStepped(frame);
                  break;
               case 2:
                  this.setBezier(input, timeline, bezier++, frame, 0, time, time2, mix, mix2, 1.0F);
                  this.setBezier(input, timeline, bezier++, frame, 1, time, time2, softness, softness2, scale);
            }

            time = time2;
            mix = mix2;
            softness = softness2;
            frame++;
         }
      }

      i = 0;

      for (int var59 = input.readInt(true); i < var59; i++) {
         int index = input.readInt(true);
         int frameCount = input.readInt(true);
         int frameLast = frameCount - 1;
         Animation.TransformConstraintTimeline timeline = new Animation.TransformConstraintTimeline(frameCount, input.readInt(true), index);
         float time = input.readFloat();
         float mixRotate = input.readFloat();
         float mixX = input.readFloat();
         float mixY = input.readFloat();
         float mixScaleX = input.readFloat();
         float mixScaleY = input.readFloat();
         float mixShearY = input.readFloat();
         int frame = 0;
         int bezier = 0;

         while (true) {
            timeline.setFrame(frame, time, mixRotate, mixX, mixY, mixScaleX, mixScaleY, mixShearY);
            if (frame == frameLast) {
               timelines.add(timeline);
               break;
            }

            float time2 = input.readFloat();
            float mixRotate2 = input.readFloat();
            float mixX2 = input.readFloat();
            float mixY2 = input.readFloat();
            float mixScaleX2 = input.readFloat();
            float mixScaleY2 = input.readFloat();
            float mixShearY2 = input.readFloat();
            switch (input.readByte()) {
               case 1:
                  timeline.setStepped(frame);
                  break;
               case 2:
                  this.setBezier(input, timeline, bezier++, frame, 0, time, time2, mixRotate, mixRotate2, 1.0F);
                  this.setBezier(input, timeline, bezier++, frame, 1, time, time2, mixX, mixX2, 1.0F);
                  this.setBezier(input, timeline, bezier++, frame, 2, time, time2, mixY, mixY2, 1.0F);
                  this.setBezier(input, timeline, bezier++, frame, 3, time, time2, mixScaleX, mixScaleX2, 1.0F);
                  this.setBezier(input, timeline, bezier++, frame, 4, time, time2, mixScaleY, mixScaleY2, 1.0F);
                  this.setBezier(input, timeline, bezier++, frame, 5, time, time2, mixShearY, mixShearY2, 1.0F);
            }

            time = time2;
            mixRotate = mixRotate2;
            mixX = mixX2;
            mixY = mixY2;
            mixScaleX = mixScaleX2;
            mixScaleY = mixScaleY2;
            mixShearY = mixShearY2;
            frame++;
         }
      }

      i = 0;

      for (int var60 = input.readInt(true); i < var60; i++) {
         int index = input.readInt(true);
         PathConstraintData data = (PathConstraintData)skeletonData.pathConstraints.get(index);
         int ii = 0;

         for (int nn = input.readInt(true); ii < nn; ii++) {
            switch (input.readByte()) {
               case 0:
                  timelines.add(
                     this.readTimeline(
                        input,
                        new Animation.PathConstraintPositionTimeline(input.readInt(true), input.readInt(true), index),
                        data.positionMode == PathConstraintData.PositionMode.fixed ? scale : 1.0F
                     )
                  );
                  break;
               case 1:
                  timelines.add(
                     this.readTimeline(
                        input,
                        new Animation.PathConstraintSpacingTimeline(input.readInt(true), input.readInt(true), index),
                        data.spacingMode != PathConstraintData.SpacingMode.length && data.spacingMode != PathConstraintData.SpacingMode.fixed ? 1.0F : scale
                     )
                  );
                  break;
               case 2:
                  Animation.PathConstraintMixTimeline timeline = new Animation.PathConstraintMixTimeline(input.readInt(true), input.readInt(true), index);
                  float time = input.readFloat();
                  float mixRotate = input.readFloat();
                  float mixX = input.readFloat();
                  float mixY = input.readFloat();
                  int frame = 0;
                  int bezier = 0;
                  int frameLast = timeline.getFrameCount() - 1;

                  while (true) {
                     timeline.setFrame(frame, time, mixRotate, mixX, mixY);
                     if (frame == frameLast) {
                        timelines.add(timeline);
                        break;
                     }

                     float time2 = input.readFloat();
                     float mixRotate2 = input.readFloat();
                     float mixX2 = input.readFloat();
                     float mixY2 = input.readFloat();
                     switch (input.readByte()) {
                        case 1:
                           timeline.setStepped(frame);
                           break;
                        case 2:
                           this.setBezier(input, timeline, bezier++, frame, 0, time, time2, mixRotate, mixRotate2, 1.0F);
                           this.setBezier(input, timeline, bezier++, frame, 1, time, time2, mixX, mixX2, 1.0F);
                           this.setBezier(input, timeline, bezier++, frame, 2, time, time2, mixY, mixY2, 1.0F);
                     }

                     time = time2;
                     mixRotate = mixRotate2;
                     mixX = mixX2;
                     mixY = mixY2;
                     frame++;
                  }
            }
         }
      }

      i = 0;

      for (int var61 = input.readInt(true); i < var61; i++) {
         Skin skin = (Skin)skeletonData.skins.get(input.readInt(true));
         int ii = 0;

         for (int nn = input.readInt(true); ii < nn; ii++) {
            int slotIndex = input.readInt(true);
            int iii = 0;

            label339:
            for (int nnn = input.readInt(true); iii < nnn; iii++) {
               String attachmentName = input.readStringRef();
               Attachment attachment = skin.getAttachment(slotIndex, attachmentName);
               if (attachment == null) {
                  throw new SerializationException("Timeline attachment not found: " + attachmentName);
               }

               int timelineType = input.readByte();
               int frameCount = input.readInt(true);
               int frameLast = frameCount - 1;
               switch (timelineType) {
                  case 0:
                     VertexAttachment vertexAttachment = (VertexAttachment)attachment;
                     boolean weighted = vertexAttachment.getBones() != null;
                     float[] vertices = vertexAttachment.getVertices();
                     int deformLength = weighted ? vertices.length / 3 << 1 : vertices.length;
                     Animation.DeformTimeline deformTimeline = new Animation.DeformTimeline(frameCount, input.readInt(true), slotIndex, vertexAttachment);
                     float time = input.readFloat();
                     int m = 0;
                     int bezier = 0;

                     while (true) {
                        int end = input.readInt(true);
                        float[] deform;
                        if (end == 0) {
                           deform = weighted ? new float[deformLength] : vertices;
                        } else {
                           deform = new float[deformLength];
                           int start = input.readInt(true);
                           end += start;
                           if (scale == 1.0F) {
                              for (int v = start; v < end; v++) {
                                 deform[v] = input.readFloat();
                              }
                           } else {
                              for (int v = start; v < end; v++) {
                                 deform[v] = input.readFloat() * scale;
                              }
                           }

                           if (!weighted) {
                              int v = 0;

                              for (int vn = deform.length; v < vn; v++) {
                                 deform[v] += vertices[v];
                              }
                           }
                        }

                        deformTimeline.setFrame(m, time, deform);
                        if (m == frameLast) {
                           timelines.add(deformTimeline);
                           continue label339;
                        }

                        float time2 = input.readFloat();
                        switch (input.readByte()) {
                           case 1:
                              deformTimeline.setStepped(m);
                              break;
                           case 2:
                              this.setBezier(input, deformTimeline, bezier++, m, 0, time, time2, 0.0F, 1.0F, 1.0F);
                        }

                        time = time2;
                        m++;
                     }
                  case 1:
                     Animation.SequenceTimeline timeline = new Animation.SequenceTimeline(frameCount, slotIndex, attachment);

                     for (int frame = 0; frame < frameCount; frame++) {
                        float f = input.readFloat();
                        int modeAndIndex = input.readInt();
                        timeline.setFrame(frame, f, Sequence.SequenceMode.values[modeAndIndex & 15], modeAndIndex >> 4, input.readFloat());
                     }

                     timelines.add(timeline);
               }
            }
         }
      }

      int drawOrderCount = input.readInt(true);
      if (drawOrderCount > 0) {
         Animation.DrawOrderTimeline timeline = new Animation.DrawOrderTimeline(drawOrderCount);
         int slotCount = skeletonData.slots.size;

         for (int m = 0; m < drawOrderCount; m++) {
            float time = input.readFloat();
            int offsetCount = input.readInt(true);
            int[] drawOrder = new int[slotCount];

            for (int ii = slotCount - 1; ii >= 0; ii--) {
               drawOrder[ii] = -1;
            }

            int[] unchanged = new int[slotCount - offsetCount];
            int originalIndex = 0;
            int unchangedIndex = 0;

            for (int i1 = 0; i1 < offsetCount; i1++) {
               int slotIndex = input.readInt(true);

               while (originalIndex != slotIndex) {
                  unchanged[unchangedIndex++] = originalIndex++;
               }

               drawOrder[originalIndex + input.readInt(true)] = originalIndex++;
            }

            while (originalIndex < slotCount) {
               unchanged[unchangedIndex++] = originalIndex++;
            }

            for (int var141 = slotCount - 1; var141 >= 0; var141--) {
               if (drawOrder[var141] == -1) {
                  drawOrder[var141] = unchanged[--unchangedIndex];
               }
            }

            timeline.setFrame(m, time, drawOrder);
         }

         timelines.add(timeline);
      }

      int eventCount = input.readInt(true);
      if (eventCount > 0) {
         Animation.EventTimeline timeline = new Animation.EventTimeline(eventCount);

         for (int m = 0; m < eventCount; m++) {
            float time = input.readFloat();
            EventData eventData = (EventData)skeletonData.events.get(input.readInt(true));
            Event event = new Event(time, eventData);
            event.intValue = input.readInt(false);
            event.floatValue = input.readFloat();
            event.stringValue = input.readBoolean() ? input.readString() : eventData.stringValue;
            if (event.getData().audioPath != null) {
               event.volume = input.readFloat();
               event.balance = input.readFloat();
            }

            timeline.setFrame(m, event);
         }

         timelines.add(timeline);
      }

      float duration = 0.0F;
      Object[] items = timelines.items;
      int j = 0;

      for (int k = timelines.size; j < k; j++) {
         duration = Math.max(duration, ((Animation.Timeline)items[j]).getDuration());
      }

      return new Animation(name, timelines, duration);
   }

   private Animation.Timeline readTimeline(SkeletonBinary.SkeletonInput input, Animation.CurveTimeline1 timeline, float scale) throws IOException {
      float time = input.readFloat();
      float value = input.readFloat() * scale;
      int frame = 0;
      int bezier = 0;
      int frameLast = timeline.getFrameCount() - 1;

      while (true) {
         timeline.setFrame(frame, time, value);
         if (frame == frameLast) {
            return timeline;
         }

         float time2 = input.readFloat();
         float value2 = input.readFloat() * scale;
         switch (input.readByte()) {
            case 1:
               timeline.setStepped(frame);
               break;
            case 2:
               this.setBezier(input, timeline, bezier++, frame, 0, time, time2, value, value2, scale);
         }

         time = time2;
         value = value2;
         frame++;
      }
   }

   private Animation.Timeline readTimeline(SkeletonBinary.SkeletonInput input, Animation.CurveTimeline2 timeline, float scale) throws IOException {
      float time = input.readFloat();
      float value1 = input.readFloat() * scale;
      float value2 = input.readFloat() * scale;
      int frame = 0;
      int bezier = 0;
      int frameLast = timeline.getFrameCount() - 1;

      while (true) {
         timeline.setFrame(frame, time, value1, value2);
         if (frame == frameLast) {
            return timeline;
         }

         float time2 = input.readFloat();
         float nvalue1 = input.readFloat() * scale;
         float nvalue2 = input.readFloat() * scale;
         switch (input.readByte()) {
            case 1:
               timeline.setStepped(frame);
               break;
            case 2:
               this.setBezier(input, timeline, bezier++, frame, 0, time, time2, value1, nvalue1, scale);
               this.setBezier(input, timeline, bezier++, frame, 1, time, time2, value2, nvalue2, scale);
         }

         time = time2;
         value1 = nvalue1;
         value2 = nvalue2;
         frame++;
      }
   }

   void setBezier(
      SkeletonBinary.SkeletonInput input,
      Animation.CurveTimeline timeline,
      int bezier,
      int frame,
      int value,
      float time1,
      float time2,
      float value1,
      float value2,
      float scale
   ) throws IOException {
      timeline.setBezier(
         bezier, frame, value, time1, value1, input.readFloat(), input.readFloat() * scale, input.readFloat(), input.readFloat() * scale, time2, value2
      );
   }

   static class SkeletonInput extends DataInput {
      private char[] chars = new char[32];
      String[] strings;

      public SkeletonInput(InputStream input) {
         super(input);
      }

      public SkeletonInput(FileHandle file) {
         super(file.read(512));
      }

      @Null
      public String readStringRef() throws IOException {
         int index = this.readInt(true);
         return index == 0 ? null : this.strings[index - 1];
      }

      @Override
      public String readString() throws IOException {
         int byteCount = this.readInt(true);
         switch (byteCount) {
            case 0:
               return null;
            case 1:
               return "";
            default:
               if (this.chars.length < --byteCount) {
                  this.chars = new char[byteCount];
               }

               char[] chars = this.chars;
               int charCount = 0;
               int i = 0;

               while (i < byteCount) {
                  int b = this.read();
                  switch (b >> 4) {
                     case -1:
                        throw new EOFException();
                     case 12:
                     case 13:
                        chars[charCount++] = (char)((b & 31) << 6 | this.read() & 63);
                        i += 2;
                        break;
                     case 14:
                        chars[charCount++] = (char)((b & 15) << 12 | (this.read() & 63) << 6 | this.read() & 63);
                        i += 3;
                        break;
                     default:
                        chars[charCount++] = (char)b;
                        i++;
                  }
               }

               return new String(chars, 0, charCount);
         }
      }
   }

   static class Vertices {
      int[] bones;
      float[] vertices;
   }
}
