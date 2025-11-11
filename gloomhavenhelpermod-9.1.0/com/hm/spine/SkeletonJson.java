package com.hm.spine;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
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
import com.hm.spine.utils.SpineUtils;
import java.io.InputStream;

public class SkeletonJson extends SkeletonLoader {
   public SkeletonJson(AttachmentLoader attachmentLoader) {
      super(attachmentLoader);
   }

   public SkeletonJson(TextureAtlas atlas) {
      super(atlas);
   }

   @Override
   public SkeletonData readSkeletonData(FileHandle file) {
      if (file == null) {
         throw new IllegalArgumentException("file cannot be null.");
      } else {
         SkeletonData skeletonData = this.readSkeletonData(new JsonReader().parse(file));
         skeletonData.name = file.nameWithoutExtension();
         return skeletonData;
      }
   }

   @Override
   public SkeletonData readSkeletonData(InputStream input) {
      if (input == null) {
         throw new IllegalArgumentException("dataInput cannot be null.");
      } else {
         return this.readSkeletonData(new JsonReader().parse(input));
      }
   }

   public SkeletonData readSkeletonData(JsonValue root) {
      if (root == null) {
         throw new IllegalArgumentException("root cannot be null.");
      } else {
         float scale = this.scale;
         SkeletonData skeletonData = new SkeletonData();
         JsonValue skeletonMap = root.get("skeleton");
         if (skeletonMap != null) {
            skeletonData.hash = skeletonMap.getString("hash", null);
            skeletonData.version = skeletonMap.getString("spine", null);
            skeletonData.x = skeletonMap.getFloat("x", 0.0F);
            skeletonData.y = skeletonMap.getFloat("y", 0.0F);
            skeletonData.width = skeletonMap.getFloat("width", 0.0F);
            skeletonData.height = skeletonMap.getFloat("height", 0.0F);
            skeletonData.fps = skeletonMap.getFloat("fps", 30.0F);
            skeletonData.imagesPath = skeletonMap.getString("images", null);
            skeletonData.audioPath = skeletonMap.getString("audio", null);
         }

         for (JsonValue boneMap = root.getChild("bones"); boneMap != null; boneMap = boneMap.next) {
            BoneData parent = null;
            String parentName = boneMap.getString("parent", null);
            if (parentName != null) {
               parent = skeletonData.findBone(parentName);
               if (parent == null) {
                  throw new SerializationException("Parent bone not found: " + parentName);
               }
            }

            BoneData data = new BoneData(skeletonData.bones.size, boneMap.getString("name"), parent);
            data.length = boneMap.getFloat("length", 0.0F) * scale;
            data.x = boneMap.getFloat("x", 0.0F) * scale;
            data.y = boneMap.getFloat("y", 0.0F) * scale;
            data.rotation = boneMap.getFloat("rotation", 0.0F);
            data.scaleX = boneMap.getFloat("scaleX", 1.0F);
            data.scaleY = boneMap.getFloat("scaleY", 1.0F);
            data.shearX = boneMap.getFloat("shearX", 0.0F);
            data.shearY = boneMap.getFloat("shearY", 0.0F);
            data.transformMode = BoneData.TransformMode.valueOf(boneMap.getString("transform", BoneData.TransformMode.normal.name()));
            data.skinRequired = boneMap.getBoolean("skin", false);
            String color = boneMap.getString("color", null);
            if (color != null) {
               Color.valueOf(color, data.getColor());
            }

            skeletonData.bones.add(data);
         }

         for (JsonValue slotMap = root.getChild("slots"); slotMap != null; slotMap = slotMap.next) {
            String slotName = slotMap.getString("name");
            String boneName = slotMap.getString("bone");
            BoneData boneData = skeletonData.findBone(boneName);
            if (boneData == null) {
               throw new SerializationException("Slot bone not found: " + boneName);
            }

            SlotData data = new SlotData(skeletonData.slots.size, slotName, boneData);
            String color = slotMap.getString("color", null);
            if (color != null) {
               Color.valueOf(color, data.getColor());
            }

            String dark = slotMap.getString("dark", null);
            if (dark != null) {
               data.setDarkColor(Color.valueOf(dark));
            }

            data.attachmentName = slotMap.getString("attachment", null);
            data.blendMode = BlendMode.valueOf(slotMap.getString("blend", BlendMode.normal.name()));
            skeletonData.slots.add(data);
         }

         for (JsonValue constraintMap = root.getChild("ik"); constraintMap != null; constraintMap = constraintMap.next) {
            IkConstraintData datax = new IkConstraintData(constraintMap.getString("name"));
            datax.order = constraintMap.getInt("order", 0);
            datax.skinRequired = constraintMap.getBoolean("skin", false);

            for (JsonValue entry = constraintMap.getChild("bones"); entry != null; entry = entry.next) {
               BoneData bone = skeletonData.findBone(entry.asString());
               if (bone == null) {
                  throw new SerializationException("IK bone not found: " + entry);
               }

               datax.bones.add(bone);
            }

            String targetName = constraintMap.getString("target");
            datax.target = skeletonData.findBone(targetName);
            if (datax.target == null) {
               throw new SerializationException("IK target bone not found: " + targetName);
            }

            datax.mix = constraintMap.getFloat("mix", 1.0F);
            datax.softness = constraintMap.getFloat("softness", 0.0F) * scale;
            datax.bendDirection = constraintMap.getBoolean("bendPositive", true) ? 1 : -1;
            datax.compress = constraintMap.getBoolean("compress", false);
            datax.stretch = constraintMap.getBoolean("stretch", false);
            datax.uniform = constraintMap.getBoolean("uniform", false);
            skeletonData.ikConstraints.add(datax);
         }

         for (JsonValue var17 = root.getChild("transform"); var17 != null; var17 = var17.next) {
            TransformConstraintData datax = new TransformConstraintData(var17.getString("name"));
            datax.order = var17.getInt("order", 0);
            datax.skinRequired = var17.getBoolean("skin", false);

            for (JsonValue entry = var17.getChild("bones"); entry != null; entry = entry.next) {
               BoneData bone = skeletonData.findBone(entry.asString());
               if (bone == null) {
                  throw new SerializationException("Transform constraint bone not found: " + entry);
               }

               datax.bones.add(bone);
            }

            String targetName = var17.getString("target");
            datax.target = skeletonData.findBone(targetName);
            if (datax.target == null) {
               throw new SerializationException("Transform constraint target bone not found: " + targetName);
            }

            datax.local = var17.getBoolean("local", false);
            datax.relative = var17.getBoolean("relative", false);
            datax.offsetRotation = var17.getFloat("rotation", 0.0F);
            datax.offsetX = var17.getFloat("x", 0.0F) * scale;
            datax.offsetY = var17.getFloat("y", 0.0F) * scale;
            datax.offsetScaleX = var17.getFloat("scaleX", 0.0F);
            datax.offsetScaleY = var17.getFloat("scaleY", 0.0F);
            datax.offsetShearY = var17.getFloat("shearY", 0.0F);
            datax.mixRotate = var17.getFloat("mixRotate", 1.0F);
            datax.mixX = var17.getFloat("mixX", 1.0F);
            datax.mixY = var17.getFloat("mixY", datax.mixX);
            datax.mixScaleX = var17.getFloat("mixScaleX", 1.0F);
            datax.mixScaleY = var17.getFloat("mixScaleY", datax.mixScaleX);
            datax.mixShearY = var17.getFloat("mixShearY", 1.0F);
            skeletonData.transformConstraints.add(datax);
         }

         for (JsonValue var18 = root.getChild("path"); var18 != null; var18 = var18.next) {
            PathConstraintData datax = new PathConstraintData(var18.getString("name"));
            datax.order = var18.getInt("order", 0);
            datax.skinRequired = var18.getBoolean("skin", false);

            for (JsonValue entry = var18.getChild("bones"); entry != null; entry = entry.next) {
               BoneData bone = skeletonData.findBone(entry.asString());
               if (bone == null) {
                  throw new SerializationException("Path bone not found: " + entry);
               }

               datax.bones.add(bone);
            }

            String targetName = var18.getString("target");
            datax.target = skeletonData.findSlot(targetName);
            if (datax.target == null) {
               throw new SerializationException("Path target slot not found: " + targetName);
            }

            datax.positionMode = PathConstraintData.PositionMode.valueOf(var18.getString("positionMode", "percent"));
            datax.spacingMode = PathConstraintData.SpacingMode.valueOf(var18.getString("spacingMode", "length"));
            datax.rotateMode = PathConstraintData.RotateMode.valueOf(var18.getString("rotateMode", "tangent"));
            datax.offsetRotation = var18.getFloat("rotation", 0.0F);
            datax.position = var18.getFloat("position", 0.0F);
            if (datax.positionMode == PathConstraintData.PositionMode.fixed) {
               datax.position *= scale;
            }

            datax.spacing = var18.getFloat("spacing", 0.0F);
            if (datax.spacingMode == PathConstraintData.SpacingMode.length || datax.spacingMode == PathConstraintData.SpacingMode.fixed) {
               datax.spacing *= scale;
            }

            datax.mixRotate = var18.getFloat("mixRotate", 1.0F);
            datax.mixX = var18.getFloat("mixX", 1.0F);
            datax.mixY = var18.getFloat("mixY", 1.0F);
            skeletonData.pathConstraints.add(datax);
         }

         for (JsonValue skinMap = root.getChild("skins"); skinMap != null; skinMap = skinMap.next) {
            Skin skin = new Skin(skinMap.getString("name"));

            for (JsonValue entry = skinMap.getChild("bones"); entry != null; entry = entry.next) {
               BoneData bone = skeletonData.findBone(entry.asString());
               if (bone == null) {
                  throw new SerializationException("Skin bone not found: " + entry);
               }

               skin.bones.add(bone);
            }

            skin.bones.shrink();

            for (JsonValue var41 = skinMap.getChild("ik"); var41 != null; var41 = var41.next) {
               IkConstraintData constraint = skeletonData.findIkConstraint(var41.asString());
               if (constraint == null) {
                  throw new SerializationException("Skin IK constraint not found: " + var41);
               }

               skin.constraints.add(constraint);
            }

            for (JsonValue var42 = skinMap.getChild("transform"); var42 != null; var42 = var42.next) {
               TransformConstraintData constraint = skeletonData.findTransformConstraint(var42.asString());
               if (constraint == null) {
                  throw new SerializationException("Skin transform constraint not found: " + var42);
               }

               skin.constraints.add(constraint);
            }

            for (JsonValue var43 = skinMap.getChild("path"); var43 != null; var43 = var43.next) {
               PathConstraintData constraint = skeletonData.findPathConstraint(var43.asString());
               if (constraint == null) {
                  throw new SerializationException("Skin path constraint not found: " + var43);
               }

               skin.constraints.add(constraint);
            }

            skin.constraints.shrink();

            for (JsonValue slotEntry = skinMap.getChild("attachments"); slotEntry != null; slotEntry = slotEntry.next) {
               SlotData slot = skeletonData.findSlot(slotEntry.name);
               if (slot == null) {
                  throw new SerializationException("Slot not found: " + slotEntry.name);
               }

               for (JsonValue jsonValue = slotEntry.child; jsonValue != null; jsonValue = jsonValue.next) {
                  try {
                     Attachment attachment = this.readAttachment(jsonValue, skin, slot.index, jsonValue.name, skeletonData);
                     if (attachment != null) {
                        skin.setAttachment(slot.index, jsonValue.name, attachment);
                     }
                  } catch (Throwable var14) {
                     throw new SerializationException("Error reading attachment: " + jsonValue.name + ", skin: " + skin, var14);
                  }
               }
            }

            skeletonData.skins.add(skin);
            if (skin.name.equals("default")) {
               skeletonData.defaultSkin = skin;
            }
         }

         Object[] items = this.linkedMeshes.items;
         int i = 0;

         for (int n = this.linkedMeshes.size; i < n; i++) {
            SkeletonJson.LinkedMesh linkedMesh = (SkeletonJson.LinkedMesh)items[i];
            Skin skin = linkedMesh.skin == null ? skeletonData.getDefaultSkin() : skeletonData.findSkin(linkedMesh.skin);
            if (skin == null) {
               throw new SerializationException("Skin not found: " + linkedMesh.skin);
            }

            Attachment parentx = skin.getAttachment(linkedMesh.slotIndex, linkedMesh.parent);
            if (parentx == null) {
               throw new SerializationException("Parent mesh not found: " + linkedMesh.parent);
            }

            linkedMesh.mesh.setTimelineAttachment((Attachment)(linkedMesh.inheritTimelines ? parentx : linkedMesh.mesh));
            linkedMesh.mesh.setParentMesh((MeshAttachment)parentx);
            if (linkedMesh.mesh.getRegion() != null) {
               linkedMesh.mesh.updateRegion();
            }
         }

         this.linkedMeshes.clear();

         for (JsonValue eventMap = root.getChild("events"); eventMap != null; eventMap = eventMap.next) {
            EventData datax = new EventData(eventMap.name);
            datax.intValue = eventMap.getInt("int", 0);
            datax.floatValue = eventMap.getFloat("float", 0.0F);
            datax.stringValue = eventMap.getString("string", "");
            datax.audioPath = eventMap.getString("audio", null);
            if (datax.audioPath != null) {
               datax.volume = eventMap.getFloat("volume", 1.0F);
               datax.balance = eventMap.getFloat("balance", 0.0F);
            }

            skeletonData.events.add(datax);
         }

         for (JsonValue animationMap = root.getChild("animations"); animationMap != null; animationMap = animationMap.next) {
            try {
               this.readAnimation(animationMap, animationMap.name, skeletonData);
            } catch (Throwable var13) {
               throw new SerializationException("Error reading animation: " + animationMap.name, var13);
            }
         }

         skeletonData.bones.shrink();
         skeletonData.slots.shrink();
         skeletonData.skins.shrink();
         skeletonData.events.shrink();
         skeletonData.animations.shrink();
         skeletonData.ikConstraints.shrink();
         return skeletonData;
      }
   }

   private Attachment readAttachment(JsonValue map, Skin skin, int slotIndex, String name, SkeletonData skeletonData) {
      float scale = this.scale;
      name = map.getString("name", name);
      switch (AttachmentType.valueOf(map.getString("type", AttachmentType.region.name()))) {
         case region:
            String str2 = map.getString("path", name);
            Sequence sequence1 = this.readSequence(map.get("sequence"));
            RegionAttachment region = this.attachmentLoader.newRegionAttachment(skin, name, str2, sequence1);
            if (region == null) {
               return null;
            }

            region.setPath(str2);
            region.setX(map.getFloat("x", 0.0F) * scale);
            region.setY(map.getFloat("y", 0.0F) * scale);
            region.setScaleX(map.getFloat("scaleX", 1.0F));
            region.setScaleY(map.getFloat("scaleY", 1.0F));
            region.setRotation(map.getFloat("rotation", 0.0F));
            region.setWidth(map.getFloat("width") * scale);
            region.setHeight(map.getFloat("height") * scale);
            region.setSequence(sequence1);
            String str5x = map.getString("color", null);
            if (str5x != null) {
               Color.valueOf(str5x, region.getColor());
            }

            if (region.getRegion() != null) {
               region.updateRegion();
            }

            return region;
         case boundingbox:
            BoundingBoxAttachment box = this.attachmentLoader.newBoundingBoxAttachment(skin, name);
            if (box == null) {
               return null;
            }

            this.readVertices(map, box, map.getInt("vertexCount") << 1);
            String str3 = map.getString("color", null);
            if (str3 != null) {
               Color.valueOf(str3, box.getColor());
            }

            return box;
         case mesh:
         case linkedmesh:
            String str1 = map.getString("path", name);
            Sequence sequence = this.readSequence(map.get("sequence"));
            MeshAttachment mesh = this.attachmentLoader.newMeshAttachment(skin, name, str1, sequence);
            if (mesh == null) {
               return null;
            } else {
               mesh.setPath(str1);
               String str5 = map.getString("color", null);
               if (str5 != null) {
                  Color.valueOf(str5, mesh.getColor());
               }

               mesh.setWidth(map.getFloat("width", 0.0F) * scale);
               mesh.setHeight(map.getFloat("height", 0.0F) * scale);
               mesh.setSequence(sequence);
               String parent = map.getString("parent", null);
               if (parent != null) {
                  this.linkedMeshes.add(new SkeletonJson.LinkedMesh(mesh, map.getString("skin", null), slotIndex, parent, map.getBoolean("timelines", true)));
                  return mesh;
               }

               float[] uvs = map.require("uvs").asFloatArray();
               this.readVertices(map, mesh, uvs.length);
               mesh.setTriangles(map.require("triangles").asShortArray());
               mesh.setRegionUVs(uvs);
               if (mesh.getRegion() != null) {
                  mesh.updateRegion();
               }

               if (map.has("hull")) {
                  mesh.setHullLength(map.require("hull").asInt() << 1);
               }

               if (map.has("edges")) {
                  mesh.setEdges(map.require("edges").asShortArray());
               }

               return mesh;
            }
         case path:
            PathAttachment path = this.attachmentLoader.newPathAttachment(skin, name);
            if (path == null) {
               return null;
            }

            path.setClosed(map.getBoolean("closed", false));
            path.setConstantSpeed(map.getBoolean("constantSpeed", true));
            int vertexCount = map.getInt("vertexCount");
            this.readVertices(map, path, vertexCount << 1);
            float[] lengths = new float[vertexCount / 3];
            int i = 0;

            for (JsonValue curves = map.require("lengths").child; curves != null; curves = curves.next) {
               lengths[i++] = curves.asFloat() * scale;
            }

            path.setLengths(lengths);
            String str6 = map.getString("color", null);
            if (str6 != null) {
               Color.valueOf(str6, path.getColor());
            }

            return path;
         case point:
            PointAttachment point = this.attachmentLoader.newPointAttachment(skin, name);
            if (point == null) {
               return null;
            }

            point.setX(map.getFloat("x", 0.0F) * scale);
            point.setY(map.getFloat("y", 0.0F) * scale);
            point.setRotation(map.getFloat("rotation", 0.0F));
            String color = map.getString("color", null);
            if (color != null) {
               Color.valueOf(color, point.getColor());
            }

            return point;
         case clipping:
            ClippingAttachment clip = this.attachmentLoader.newClippingAttachment(skin, name);
            if (clip == null) {
               return null;
            } else {
               String end = map.getString("end", null);
               if (end != null) {
                  SlotData slot = skeletonData.findSlot(end);
                  if (slot == null) {
                     throw new SerializationException("Clipping end slot not found: " + end);
                  }

                  clip.setEndSlot(slot);
               }

               this.readVertices(map, clip, map.getInt("vertexCount") << 1);
               String str4 = map.getString("color", null);
               if (str4 != null) {
                  Color.valueOf(str4, clip.getColor());
               }

               return clip;
            }
         default:
            return null;
      }
   }

   private Sequence readSequence(@Null JsonValue map) {
      if (map == null) {
         return null;
      } else {
         Sequence sequence = new Sequence(map.getInt("count"));
         sequence.setStart(map.getInt("start", 1));
         sequence.setDigits(map.getInt("digits", 0));
         sequence.setSetupIndex(map.getInt("setup", 0));
         return sequence;
      }
   }

   private void readVertices(JsonValue map, VertexAttachment attachment, int verticesLength) {
      attachment.setWorldVerticesLength(verticesLength);
      float[] vertices = map.require("vertices").asFloatArray();
      if (verticesLength == vertices.length) {
         if (this.scale != 1.0F) {
            int j = 0;

            for (int k = vertices.length; j < k; j++) {
               vertices[j] *= this.scale;
            }
         }

         attachment.setVertices(vertices);
      } else {
         FloatArray weights = new FloatArray(verticesLength * 3 * 3);
         IntArray bones = new IntArray(verticesLength * 3);
         int i = 0;
         int n = vertices.length;

         while (i < n) {
            int boneCount = (int)vertices[i++];
            bones.add(boneCount);

            for (int nn = i + (boneCount << 2); i < nn; i += 4) {
               bones.add((int)vertices[i]);
               weights.add(vertices[i + 1] * this.scale);
               weights.add(vertices[i + 2] * this.scale);
               weights.add(vertices[i + 3]);
            }
         }

         attachment.setBones(bones.toArray());
         attachment.setVertices(weights.toArray());
      }
   }

   private void readAnimation(JsonValue map, String name, SkeletonData skeletonData) {
      float scale = this.scale;
      Array timelines = new Array();

      for (JsonValue slotMap = map.getChild("slots"); slotMap != null; slotMap = slotMap.next) {
         SlotData slot = skeletonData.findSlot(slotMap.name);
         if (slot == null) {
            throw new SerializationException("Slot not found: " + slotMap.name);
         }

         for (JsonValue jsonValue = slotMap.child; jsonValue != null; jsonValue = jsonValue.next) {
            JsonValue keyMap = jsonValue.child;
            if (keyMap != null) {
               int frames = jsonValue.size;
               String timelineName = jsonValue.name;
               if (timelineName.equals("attachment")) {
                  Animation.AttachmentTimeline timeline = new Animation.AttachmentTimeline(frames, slot.index);

                  for (int frame = 0; keyMap != null; frame++) {
                     timeline.setFrame(frame, keyMap.getFloat("time", 0.0F), keyMap.getString("name", null));
                     keyMap = keyMap.next;
                  }

                  timelines.add(timeline);
               } else if (timelineName.equals("rgba")) {
                  Animation.RGBATimeline timeline = new Animation.RGBATimeline(frames, frames << 2, slot.index);
                  float time = keyMap.getFloat("time", 0.0F);
                  String color = keyMap.getString("color");
                  float r = Integer.parseInt(color.substring(0, 2), 16) / 255.0F;
                  float g = Integer.parseInt(color.substring(2, 4), 16) / 255.0F;
                  float b = Integer.parseInt(color.substring(4, 6), 16) / 255.0F;
                  float a = Integer.parseInt(color.substring(6, 8), 16) / 255.0F;
                  int frame = 0;
                  int bezier = 0;

                  while (true) {
                     timeline.setFrame(frame, time, r, g, b, a);
                     JsonValue nextMap = keyMap.next;
                     if (nextMap == null) {
                        timeline.shrink(bezier);
                        timelines.add(timeline);
                        break;
                     }

                     float time2 = nextMap.getFloat("time", 0.0F);
                     color = nextMap.getString("color");
                     float nr = Integer.parseInt(color.substring(0, 2), 16) / 255.0F;
                     float ng = Integer.parseInt(color.substring(2, 4), 16) / 255.0F;
                     float nb = Integer.parseInt(color.substring(4, 6), 16) / 255.0F;
                     float na = Integer.parseInt(color.substring(6, 8), 16) / 255.0F;
                     JsonValue curve = keyMap.get("curve");
                     if (curve != null) {
                        bezier = this.readCurve(curve, timeline, bezier, frame, 0, time, time2, r, nr, 1.0F);
                        bezier = this.readCurve(curve, timeline, bezier, frame, 1, time, time2, g, ng, 1.0F);
                        bezier = this.readCurve(curve, timeline, bezier, frame, 2, time, time2, b, nb, 1.0F);
                        bezier = this.readCurve(curve, timeline, bezier, frame, 3, time, time2, a, na, 1.0F);
                     }

                     time = time2;
                     r = nr;
                     g = ng;
                     b = nb;
                     a = na;
                     keyMap = nextMap;
                     frame++;
                  }
               } else if (timelineName.equals("rgb")) {
                  Animation.RGBTimeline timeline = new Animation.RGBTimeline(frames, frames * 3, slot.index);
                  float time = keyMap.getFloat("time", 0.0F);
                  String color = keyMap.getString("color");
                  float r = Integer.parseInt(color.substring(0, 2), 16) / 255.0F;
                  float g = Integer.parseInt(color.substring(2, 4), 16) / 255.0F;
                  float b = Integer.parseInt(color.substring(4, 6), 16) / 255.0F;
                  int frame = 0;
                  int bezier = 0;

                  while (true) {
                     timeline.setFrame(frame, time, r, g, b);
                     JsonValue nextMapx = keyMap.next;
                     if (nextMapx == null) {
                        timeline.shrink(bezier);
                        timelines.add(timeline);
                        break;
                     }

                     float time2 = nextMapx.getFloat("time", 0.0F);
                     color = nextMapx.getString("color");
                     float nr = Integer.parseInt(color.substring(0, 2), 16) / 255.0F;
                     float ng = Integer.parseInt(color.substring(2, 4), 16) / 255.0F;
                     float nb = Integer.parseInt(color.substring(4, 6), 16) / 255.0F;
                     JsonValue curve = keyMap.get("curve");
                     if (curve != null) {
                        bezier = this.readCurve(curve, timeline, bezier, frame, 0, time, time2, r, nr, 1.0F);
                        bezier = this.readCurve(curve, timeline, bezier, frame, 1, time, time2, g, ng, 1.0F);
                        bezier = this.readCurve(curve, timeline, bezier, frame, 2, time, time2, b, nb, 1.0F);
                     }

                     time = time2;
                     r = nr;
                     g = ng;
                     b = nb;
                     keyMap = nextMapx;
                     frame++;
                  }
               } else if (timelineName.equals("alpha")) {
                  timelines.add(this.readTimeline(keyMap, new Animation.AlphaTimeline(frames, frames, slot.index), 0.0F, 1.0F));
               } else if (timelineName.equals("rgba2")) {
                  Animation.RGBA2Timeline timeline = new Animation.RGBA2Timeline(frames, frames * 7, slot.index);
                  float time = keyMap.getFloat("time", 0.0F);
                  String color = keyMap.getString("light");
                  float r = Integer.parseInt(color.substring(0, 2), 16) / 255.0F;
                  float g = Integer.parseInt(color.substring(2, 4), 16) / 255.0F;
                  float b = Integer.parseInt(color.substring(4, 6), 16) / 255.0F;
                  float a = Integer.parseInt(color.substring(6, 8), 16) / 255.0F;
                  color = keyMap.getString("dark");
                  float r2 = Integer.parseInt(color.substring(0, 2), 16) / 255.0F;
                  float g2 = Integer.parseInt(color.substring(2, 4), 16) / 255.0F;
                  float b2 = Integer.parseInt(color.substring(4, 6), 16) / 255.0F;
                  int frame = 0;
                  int bezier = 0;

                  while (true) {
                     timeline.setFrame(frame, time, r, g, b, a, r2, g2, b2);
                     JsonValue nextMapxx = keyMap.next;
                     if (nextMapxx == null) {
                        timeline.shrink(bezier);
                        timelines.add(timeline);
                        break;
                     }

                     float time2 = nextMapxx.getFloat("time", 0.0F);
                     color = nextMapxx.getString("light");
                     float nr = Integer.parseInt(color.substring(0, 2), 16) / 255.0F;
                     float ng = Integer.parseInt(color.substring(2, 4), 16) / 255.0F;
                     float nb = Integer.parseInt(color.substring(4, 6), 16) / 255.0F;
                     float na = Integer.parseInt(color.substring(6, 8), 16) / 255.0F;
                     color = nextMapxx.getString("dark");
                     float nr2 = Integer.parseInt(color.substring(0, 2), 16) / 255.0F;
                     float ng2 = Integer.parseInt(color.substring(2, 4), 16) / 255.0F;
                     float nb2 = Integer.parseInt(color.substring(4, 6), 16) / 255.0F;
                     JsonValue curve = keyMap.get("curve");
                     if (curve != null) {
                        bezier = this.readCurve(curve, timeline, bezier, frame, 0, time, time2, r, nr, 1.0F);
                        bezier = this.readCurve(curve, timeline, bezier, frame, 1, time, time2, g, ng, 1.0F);
                        bezier = this.readCurve(curve, timeline, bezier, frame, 2, time, time2, b, nb, 1.0F);
                        bezier = this.readCurve(curve, timeline, bezier, frame, 3, time, time2, a, na, 1.0F);
                        bezier = this.readCurve(curve, timeline, bezier, frame, 4, time, time2, r2, nr2, 1.0F);
                        bezier = this.readCurve(curve, timeline, bezier, frame, 5, time, time2, g2, ng2, 1.0F);
                        bezier = this.readCurve(curve, timeline, bezier, frame, 6, time, time2, b2, nb2, 1.0F);
                     }

                     time = time2;
                     r = nr;
                     g = ng;
                     b = nb;
                     a = na;
                     r2 = nr2;
                     g2 = ng2;
                     b2 = nb2;
                     keyMap = nextMapxx;
                     frame++;
                  }
               } else {
                  if (!timelineName.equals("rgb2")) {
                     throw new RuntimeException("Invalid timeline type for a slot: " + timelineName + " (" + slotMap.name + ")");
                  }

                  Animation.RGB2Timeline timeline = new Animation.RGB2Timeline(frames, frames * 6, slot.index);
                  float time = keyMap.getFloat("time", 0.0F);
                  String color = keyMap.getString("light");
                  float r = Integer.parseInt(color.substring(0, 2), 16) / 255.0F;
                  float g = Integer.parseInt(color.substring(2, 4), 16) / 255.0F;
                  float b = Integer.parseInt(color.substring(4, 6), 16) / 255.0F;
                  color = keyMap.getString("dark");
                  float r2 = Integer.parseInt(color.substring(0, 2), 16) / 255.0F;
                  float g2 = Integer.parseInt(color.substring(2, 4), 16) / 255.0F;
                  float b2 = Integer.parseInt(color.substring(4, 6), 16) / 255.0F;
                  int frame = 0;
                  int bezier = 0;

                  while (true) {
                     timeline.setFrame(frame, time, r, g, b, r2, g2, b2);
                     JsonValue nextMapxxx = keyMap.next;
                     if (nextMapxxx == null) {
                        timeline.shrink(bezier);
                        timelines.add(timeline);
                        break;
                     }

                     float time2 = nextMapxxx.getFloat("time", 0.0F);
                     color = nextMapxxx.getString("light");
                     float nr = Integer.parseInt(color.substring(0, 2), 16) / 255.0F;
                     float ng = Integer.parseInt(color.substring(2, 4), 16) / 255.0F;
                     float nb = Integer.parseInt(color.substring(4, 6), 16) / 255.0F;
                     color = nextMapxxx.getString("dark");
                     float nr2 = Integer.parseInt(color.substring(0, 2), 16) / 255.0F;
                     float ng2 = Integer.parseInt(color.substring(2, 4), 16) / 255.0F;
                     float nb2 = Integer.parseInt(color.substring(4, 6), 16) / 255.0F;
                     JsonValue curve = keyMap.get("curve");
                     if (curve != null) {
                        bezier = this.readCurve(curve, timeline, bezier, frame, 0, time, time2, r, nr, 1.0F);
                        bezier = this.readCurve(curve, timeline, bezier, frame, 1, time, time2, g, ng, 1.0F);
                        bezier = this.readCurve(curve, timeline, bezier, frame, 2, time, time2, b, nb, 1.0F);
                        bezier = this.readCurve(curve, timeline, bezier, frame, 3, time, time2, r2, nr2, 1.0F);
                        bezier = this.readCurve(curve, timeline, bezier, frame, 4, time, time2, g2, ng2, 1.0F);
                        bezier = this.readCurve(curve, timeline, bezier, frame, 5, time, time2, b2, nb2, 1.0F);
                     }

                     time = time2;
                     r = nr;
                     g = ng;
                     b = nb;
                     r2 = nr2;
                     g2 = ng2;
                     b2 = nb2;
                     keyMap = nextMapxxx;
                     frame++;
                  }
               }
            }
         }
      }

      for (JsonValue boneMap = map.getChild("bones"); boneMap != null; boneMap = boneMap.next) {
         BoneData bone = skeletonData.findBone(boneMap.name);
         if (bone == null) {
            throw new SerializationException("Bone not found: " + boneMap.name);
         }

         for (JsonValue jsonValuex = boneMap.child; jsonValuex != null; jsonValuex = jsonValuex.next) {
            JsonValue keyMap = jsonValuex.child;
            if (keyMap != null) {
               int frames = jsonValuex.size;
               String timelineName = jsonValuex.name;
               if (timelineName.equals("rotate")) {
                  timelines.add(this.readTimeline(keyMap, new Animation.RotateTimeline(frames, frames, bone.index), 0.0F, 1.0F));
               } else if (timelineName.equals("translate")) {
                  Animation.TranslateTimeline timeline = new Animation.TranslateTimeline(frames, frames << 1, bone.index);
                  timelines.add(this.readTimeline(keyMap, timeline, "x", "y", 0.0F, scale));
               } else if (timelineName.equals("translatex")) {
                  timelines.add(this.readTimeline(keyMap, new Animation.TranslateXTimeline(frames, frames, bone.index), 0.0F, scale));
               } else if (timelineName.equals("translatey")) {
                  timelines.add(this.readTimeline(keyMap, new Animation.TranslateYTimeline(frames, frames, bone.index), 0.0F, scale));
               } else if (timelineName.equals("scale")) {
                  Animation.ScaleTimeline timeline = new Animation.ScaleTimeline(frames, frames << 1, bone.index);
                  timelines.add(this.readTimeline(keyMap, timeline, "x", "y", 1.0F, 1.0F));
               } else if (timelineName.equals("scalex")) {
                  timelines.add(this.readTimeline(keyMap, new Animation.ScaleXTimeline(frames, frames, bone.index), 1.0F, 1.0F));
               } else if (timelineName.equals("scaley")) {
                  timelines.add(this.readTimeline(keyMap, new Animation.ScaleYTimeline(frames, frames, bone.index), 1.0F, 1.0F));
               } else if (timelineName.equals("shear")) {
                  Animation.ShearTimeline timeline = new Animation.ShearTimeline(frames, frames << 1, bone.index);
                  timelines.add(this.readTimeline(keyMap, timeline, "x", "y", 0.0F, 1.0F));
               } else if (timelineName.equals("shearx")) {
                  timelines.add(this.readTimeline(keyMap, new Animation.ShearXTimeline(frames, frames, bone.index), 0.0F, 1.0F));
               } else {
                  if (!timelineName.equals("sheary")) {
                     throw new RuntimeException("Invalid timeline type for a bone: " + timelineName + " (" + boneMap.name + ")");
                  }

                  timelines.add(this.readTimeline(keyMap, new Animation.ShearYTimeline(frames, frames, bone.index), 0.0F, 1.0F));
               }
            }
         }
      }

      for (JsonValue timelineMap = map.getChild("ik"); timelineMap != null; timelineMap = timelineMap.next) {
         JsonValue keyMap = timelineMap.child;
         if (keyMap != null) {
            IkConstraintData constraint = skeletonData.findIkConstraint(timelineMap.name);
            Animation.IkConstraintTimeline timeline = new Animation.IkConstraintTimeline(
               timelineMap.size, timelineMap.size << 1, skeletonData.getIkConstraints().indexOf(constraint, true)
            );
            float time = keyMap.getFloat("time", 0.0F);
            float mix = keyMap.getFloat("mix", 1.0F);
            float softness = keyMap.getFloat("softness", 0.0F) * scale;
            int frame = 0;
            int bezier = 0;

            while (true) {
               timeline.setFrame(
                  frame,
                  time,
                  mix,
                  softness,
                  keyMap.getBoolean("bendPositive", true) ? 1 : -1,
                  keyMap.getBoolean("compress", false),
                  keyMap.getBoolean("stretch", false)
               );
               JsonValue nextMapxxxx = keyMap.next;
               if (nextMapxxxx == null) {
                  timeline.shrink(bezier);
                  timelines.add(timeline);
                  break;
               }

               float time2 = nextMapxxxx.getFloat("time", 0.0F);
               float mix2 = nextMapxxxx.getFloat("mix", 1.0F);
               float softness2 = nextMapxxxx.getFloat("softness", 0.0F) * scale;
               JsonValue curve = keyMap.get("curve");
               if (curve != null) {
                  bezier = this.readCurve(curve, timeline, bezier, frame, 0, time, time2, mix, mix2, 1.0F);
                  bezier = this.readCurve(curve, timeline, bezier, frame, 1, time, time2, softness, softness2, scale);
               }

               time = time2;
               mix = mix2;
               softness = softness2;
               keyMap = nextMapxxxx;
               frame++;
            }
         }
      }

      for (JsonValue var36 = map.getChild("transform"); var36 != null; var36 = var36.next) {
         JsonValue keyMap = var36.child;
         if (keyMap != null) {
            TransformConstraintData constraint = skeletonData.findTransformConstraint(var36.name);
            Animation.TransformConstraintTimeline timeline = new Animation.TransformConstraintTimeline(
               var36.size, var36.size * 6, skeletonData.getTransformConstraints().indexOf(constraint, true)
            );
            float time = keyMap.getFloat("time", 0.0F);
            float mixRotate = keyMap.getFloat("mixRotate", 1.0F);
            float mixX = keyMap.getFloat("mixX", 1.0F);
            float mixY = keyMap.getFloat("mixY", mixX);
            float mixScaleX = keyMap.getFloat("mixScaleX", 1.0F);
            float mixScaleY = keyMap.getFloat("mixScaleY", mixScaleX);
            float mixShearY = keyMap.getFloat("mixShearY", 1.0F);
            int frame = 0;
            int bezier = 0;

            while (true) {
               timeline.setFrame(frame, time, mixRotate, mixX, mixY, mixScaleX, mixScaleY, mixShearY);
               JsonValue nextMapxxxxx = keyMap.next;
               if (nextMapxxxxx == null) {
                  timeline.shrink(bezier);
                  timelines.add(timeline);
                  break;
               }

               float time2 = nextMapxxxxx.getFloat("time", 0.0F);
               float mixRotate2 = nextMapxxxxx.getFloat("mixRotate", 1.0F);
               float mixX2 = nextMapxxxxx.getFloat("mixX", 1.0F);
               float mixY2 = nextMapxxxxx.getFloat("mixY", mixX2);
               float mixScaleX2 = nextMapxxxxx.getFloat("mixScaleX", 1.0F);
               float mixScaleY2 = nextMapxxxxx.getFloat("mixScaleY", mixScaleX2);
               float mixShearY2 = nextMapxxxxx.getFloat("mixShearY", 1.0F);
               JsonValue curve = keyMap.get("curve");
               if (curve != null) {
                  bezier = this.readCurve(curve, timeline, bezier, frame, 0, time, time2, mixRotate, mixRotate2, 1.0F);
                  bezier = this.readCurve(curve, timeline, bezier, frame, 1, time, time2, mixX, mixX2, 1.0F);
                  bezier = this.readCurve(curve, timeline, bezier, frame, 2, time, time2, mixY, mixY2, 1.0F);
                  bezier = this.readCurve(curve, timeline, bezier, frame, 3, time, time2, mixScaleX, mixScaleX2, 1.0F);
                  bezier = this.readCurve(curve, timeline, bezier, frame, 4, time, time2, mixScaleY, mixScaleY2, 1.0F);
                  bezier = this.readCurve(curve, timeline, bezier, frame, 5, time, time2, mixShearY, mixShearY2, 1.0F);
               }

               time = time2;
               mixRotate = mixRotate2;
               mixX = mixX2;
               mixY = mixY2;
               mixScaleY = mixScaleY2;
               mixScaleX = mixScaleX2;
               keyMap = nextMapxxxxx;
               frame++;
            }
         }
      }

      for (JsonValue constraintMap = map.getChild("path"); constraintMap != null; constraintMap = constraintMap.next) {
         PathConstraintData constraint = skeletonData.findPathConstraint(constraintMap.name);
         if (constraint == null) {
            throw new SerializationException("Path constraint not found: " + constraintMap.name);
         }

         int index = skeletonData.pathConstraints.indexOf(constraint, true);

         for (JsonValue jsonValuexx = constraintMap.child; jsonValuexx != null; jsonValuexx = jsonValuexx.next) {
            JsonValue keyMap = jsonValuexx.child;
            if (keyMap != null) {
               int frames = jsonValuexx.size;
               String timelineName = jsonValuexx.name;
               if (timelineName.equals("position")) {
                  Animation.CurveTimeline1 timeline = new Animation.PathConstraintPositionTimeline(frames, frames, index);
                  timelines.add(this.readTimeline(keyMap, timeline, 0.0F, constraint.positionMode == PathConstraintData.PositionMode.fixed ? scale : 1.0F));
               } else if (timelineName.equals("spacing")) {
                  Animation.CurveTimeline1 timeline = new Animation.PathConstraintSpacingTimeline(frames, frames, index);
                  timelines.add(
                     this.readTimeline(
                        keyMap,
                        timeline,
                        0.0F,
                        constraint.spacingMode != PathConstraintData.SpacingMode.length && constraint.spacingMode != PathConstraintData.SpacingMode.fixed
                           ? 1.0F
                           : scale
                     )
                  );
               } else if (timelineName.equals("mix")) {
                  Animation.PathConstraintMixTimeline timeline = new Animation.PathConstraintMixTimeline(frames, frames * 3, index);
                  float time = keyMap.getFloat("time", 0.0F);
                  float mixRotate = keyMap.getFloat("mixRotate", 1.0F);
                  float mixX = keyMap.getFloat("mixX", 1.0F);
                  float mixY = keyMap.getFloat("mixY", mixX);
                  int frame = 0;
                  int bezier = 0;

                  while (true) {
                     timeline.setFrame(frame, time, mixRotate, mixX, mixY);
                     JsonValue nextMapxxxxxx = keyMap.next;
                     if (nextMapxxxxxx == null) {
                        timeline.shrink(bezier);
                        timelines.add(timeline);
                        break;
                     }

                     float time2 = nextMapxxxxxx.getFloat("time", 0.0F);
                     float mixRotate2 = nextMapxxxxxx.getFloat("mixRotate", 1.0F);
                     float mixX2 = nextMapxxxxxx.getFloat("mixX", 1.0F);
                     float mixY2 = nextMapxxxxxx.getFloat("mixY", mixX2);
                     JsonValue curve = keyMap.get("curve");
                     if (curve != null) {
                        bezier = this.readCurve(curve, timeline, bezier, frame, 0, time, time2, mixRotate, mixRotate2, 1.0F);
                        bezier = this.readCurve(curve, timeline, bezier, frame, 1, time, time2, mixX, mixX2, 1.0F);
                        bezier = this.readCurve(curve, timeline, bezier, frame, 2, time, time2, mixY, mixY2, 1.0F);
                     }

                     time = time2;
                     mixRotate = mixRotate2;
                     mixX = mixX2;
                     mixY = mixY2;
                     keyMap = nextMapxxxxxx;
                     frame++;
                  }
               }
            }
         }
      }

      for (JsonValue attachmentsMap = map.getChild("attachments"); attachmentsMap != null; attachmentsMap = attachmentsMap.next) {
         Skin skin = skeletonData.findSkin(attachmentsMap.name);
         if (skin == null) {
            throw new SerializationException("Skin not found: " + attachmentsMap.name);
         }

         for (JsonValue jsonValuexxx = attachmentsMap.child; jsonValuexxx != null; jsonValuexxx = jsonValuexxx.next) {
            SlotData slot = skeletonData.findSlot(jsonValuexxx.name);
            if (slot == null) {
               throw new SerializationException("Slot not found: " + jsonValuexxx.name);
            }

            for (JsonValue attachmentMap = jsonValuexxx.child; attachmentMap != null; attachmentMap = attachmentMap.next) {
               Attachment attachment = skin.getAttachment(slot.index, attachmentMap.name);
               if (attachment == null) {
                  throw new SerializationException("Timeline attachment not found: " + attachmentMap.name);
               }

               for (JsonValue jsonValue1 = attachmentMap.child; jsonValue1 != null; jsonValue1 = jsonValue1.next) {
                  JsonValue keyMap = jsonValue1.child;
                  int frames = jsonValue1.size;
                  String timelineName = jsonValue1.name;
                  if (timelineName.equals("deform")) {
                     VertexAttachment vertexAttachment = (VertexAttachment)attachment;
                     boolean weighted = vertexAttachment.getBones() != null;
                     float[] vertices = vertexAttachment.getVertices();
                     int deformLength = weighted ? vertices.length / 3 << 1 : vertices.length;
                     Animation.DeformTimeline timeline = new Animation.DeformTimeline(frames, frames, slot.index, vertexAttachment);
                     float time = keyMap.getFloat("time", 0.0F);
                     int frame = 0;
                     int bezier = 0;

                     while (true) {
                        JsonValue verticesValue = keyMap.get("vertices");
                        float[] deform;
                        if (verticesValue == null) {
                           deform = weighted ? new float[deformLength] : vertices;
                        } else {
                           deform = new float[deformLength];
                           int start = keyMap.getInt("offset", 0);
                           SpineUtils.arraycopy(verticesValue.asFloatArray(), 0, deform, start, verticesValue.size);
                           if (scale != 1.0F) {
                              int j = start;

                              for (int k = start + verticesValue.size; j < k; j++) {
                                 deform[j] *= scale;
                              }
                           }

                           if (!weighted) {
                              for (int j = 0; j < deformLength; j++) {
                                 deform[j] += vertices[j];
                              }
                           }
                        }

                        timeline.setFrame(frame, time, deform);
                        JsonValue nextMapxxxxxxx = keyMap.next;
                        if (nextMapxxxxxxx == null) {
                           timeline.shrink(bezier);
                           timelines.add(timeline);
                           break;
                        }

                        float time2 = nextMapxxxxxxx.getFloat("time", 0.0F);
                        JsonValue curve = keyMap.get("curve");
                        if (curve != null) {
                           bezier = this.readCurve(curve, timeline, bezier, frame, 0, time, time2, 0.0F, 1.0F, 1.0F);
                        }

                        time = time2;
                        keyMap = nextMapxxxxxxx;
                        frame++;
                     }
                  } else if (timelineName.equals("sequence")) {
                     Animation.SequenceTimeline timeline = new Animation.SequenceTimeline(frames, slot.index, attachment);
                     float lastDelay = 0.0F;

                     for (int frame = 0; keyMap != null; frame++) {
                        float delay = keyMap.getFloat("delay", lastDelay);
                        timeline.setFrame(
                           frame,
                           keyMap.getFloat("time", 0.0F),
                           Sequence.SequenceMode.valueOf(keyMap.getString("mode", "hold")),
                           keyMap.getInt("index", 0),
                           delay
                        );
                        lastDelay = delay;
                        keyMap = keyMap.next;
                     }

                     timelines.add(timeline);
                  }
               }
            }
         }
      }

      JsonValue drawOrdersMap = map.get("drawOrder");
      if (drawOrdersMap != null) {
         Animation.DrawOrderTimeline timeline = new Animation.DrawOrderTimeline(drawOrdersMap.size);
         int slotCount = skeletonData.slots.size;
         int frame = 0;

         for (JsonValue drawOrderMap = drawOrdersMap.child; drawOrderMap != null; frame++) {
            int[] drawOrder = null;
            JsonValue offsets = drawOrderMap.get("offsets");
            if (offsets != null) {
               drawOrder = new int[slotCount];

               for (int j = slotCount - 1; j >= 0; j--) {
                  drawOrder[j] = -1;
               }

               int[] unchanged = new int[slotCount - offsets.size];
               int originalIndex = 0;
               int unchangedIndex = 0;

               for (JsonValue offsetMap = offsets.child; offsetMap != null; offsetMap = offsetMap.next) {
                  SlotData slot = skeletonData.findSlot(offsetMap.getString("slot"));
                  if (slot == null) {
                     throw new SerializationException("Slot not found: " + offsetMap.getString("slot"));
                  }

                  while (originalIndex != slot.index) {
                     unchanged[unchangedIndex++] = originalIndex++;
                  }

                  drawOrder[originalIndex + offsetMap.getInt("offset")] = originalIndex++;
               }

               while (originalIndex < slotCount) {
                  unchanged[unchangedIndex++] = originalIndex++;
               }

               for (int k = slotCount - 1; k >= 0; k--) {
                  if (drawOrder[k] == -1) {
                     drawOrder[k] = unchanged[--unchangedIndex];
                  }
               }
            }

            timeline.setFrame(frame, drawOrderMap.getFloat("time", 0.0F), drawOrder);
            drawOrderMap = drawOrderMap.next;
         }

         timelines.add(timeline);
      }

      JsonValue eventsMap = map.get("events");
      if (eventsMap != null) {
         Animation.EventTimeline timeline = new Animation.EventTimeline(eventsMap.size);
         int frame = 0;

         for (JsonValue eventMap = eventsMap.child; eventMap != null; frame++) {
            EventData eventData = skeletonData.findEvent(eventMap.getString("name"));
            if (eventData == null) {
               throw new SerializationException("Event not found: " + eventMap.getString("name"));
            }

            Event event = new Event(eventMap.getFloat("time", 0.0F), eventData);
            event.intValue = eventMap.getInt("int", eventData.intValue);
            event.floatValue = eventMap.getFloat("float", eventData.floatValue);
            event.stringValue = eventMap.getString("string", eventData.stringValue);
            if (event.getData().audioPath != null) {
               event.volume = eventMap.getFloat("volume", eventData.volume);
               event.balance = eventMap.getFloat("balance", eventData.balance);
            }

            timeline.setFrame(frame, event);
            eventMap = eventMap.next;
         }

         timelines.add(timeline);
      }

      timelines.shrink();
      float duration = 0.0F;
      Object[] items = timelines.items;
      int i = 0;

      for (int n = timelines.size; i < n; i++) {
         duration = Math.max(duration, ((Animation.Timeline)items[i]).getDuration());
      }

      skeletonData.animations.add(new Animation(name, timelines, duration));
   }

   private Animation.Timeline readTimeline(JsonValue keyMap, Animation.CurveTimeline1 timeline, float defaultValue, float scale) {
      float time = keyMap.getFloat("time", 0.0F);
      float value = keyMap.getFloat("value", defaultValue) * scale;
      int frame = 0;
      int bezier = 0;

      while (true) {
         timeline.setFrame(frame, time, value);
         JsonValue nextMap = keyMap.next;
         if (nextMap == null) {
            timeline.shrink(bezier);
            return timeline;
         }

         float time2 = nextMap.getFloat("time", 0.0F);
         float value2 = nextMap.getFloat("value", defaultValue) * scale;
         JsonValue curve = keyMap.get("curve");
         if (curve != null) {
            bezier = this.readCurve(curve, timeline, bezier, frame, 0, time, time2, value, value2, scale);
         }

         time = time2;
         value = value2;
         keyMap = nextMap;
         frame++;
      }
   }

   private Animation.Timeline readTimeline(JsonValue keyMap, Animation.CurveTimeline2 timeline, String name1, String name2, float defaultValue, float scale) {
      float time = keyMap.getFloat("time", 0.0F);
      float value1 = keyMap.getFloat(name1, defaultValue) * scale;
      float value2 = keyMap.getFloat(name2, defaultValue) * scale;
      int frame = 0;
      int bezier = 0;

      while (true) {
         timeline.setFrame(frame, time, value1, value2);
         JsonValue nextMap = keyMap.next;
         if (nextMap == null) {
            timeline.shrink(bezier);
            return timeline;
         }

         float time2 = nextMap.getFloat("time", 0.0F);
         float nvalue1 = nextMap.getFloat(name1, defaultValue) * scale;
         float nvalue2 = nextMap.getFloat(name2, defaultValue) * scale;
         JsonValue curve = keyMap.get("curve");
         if (curve != null) {
            bezier = this.readCurve(curve, timeline, bezier, frame, 0, time, time2, value1, nvalue1, scale);
            bezier = this.readCurve(curve, timeline, bezier, frame, 1, time, time2, value2, nvalue2, scale);
         }

         time = time2;
         value1 = nvalue1;
         value2 = nvalue2;
         keyMap = nextMap;
         frame++;
      }
   }

   int readCurve(
      JsonValue curve, Animation.CurveTimeline timeline, int bezier, int frame, int value, float time1, float time2, float value1, float value2, float scale
   ) {
      if (curve.isString()) {
         if (curve.asString().equals("stepped")) {
            timeline.setStepped(frame);
         }

         return bezier;
      } else {
         curve = curve.get(value << 2);
         float cx1 = curve.asFloat();
         curve = curve.next;
         float cy1 = curve.asFloat() * scale;
         curve = curve.next;
         float cx2 = curve.asFloat();
         curve = curve.next;
         float cy2 = curve.asFloat() * scale;
         setBezier(timeline, frame, value, bezier, time1, value1, cx1, cy1, cx2, cy2, time2, value2);
         return bezier + 1;
      }
   }

   static void setBezier(
      Animation.CurveTimeline timeline,
      int frame,
      int value,
      int bezier,
      float time1,
      float value1,
      float cx1,
      float cy1,
      float cx2,
      float cy2,
      float time2,
      float value2
   ) {
      timeline.setBezier(bezier, frame, value, time1, value1, cx1, cy1, cx2, cy2, time2, value2);
   }

   static class LinkedMesh {
      String parent;
      String skin;
      int slotIndex;
      MeshAttachment mesh;
      boolean inheritTimelines;

      public LinkedMesh(MeshAttachment mesh, String skin, int slotIndex, String parent, boolean inheritTimelines) {
         this.mesh = mesh;
         this.skin = skin;
         this.slotIndex = slotIndex;
         this.parent = parent;
         this.inheritTimelines = inheritTimelines;
      }
   }
}
