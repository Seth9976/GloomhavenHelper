package com.hm.spine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Null;
import com.hm.spine.attachments.Attachment;
import com.hm.spine.attachments.MeshAttachment;
import com.hm.spine.attachments.PathAttachment;
import com.hm.spine.attachments.RegionAttachment;
import com.hm.spine.utils.SpineUtils;

public class Skeleton {
   final SkeletonData data;
   final Array bones;
   final Array slots;
   Array drawOrder;
   final Array ikConstraints;
   final Array transformConstraints;
   final Array pathConstraints;
   final Array physicsConstraints;
   final Array updateCache = new Array();
   @Null
   Skin skin;
   final Color color;
   float scaleX = 1.0F;
   float scaleY = 1.0F;
   float x;
   float y;

   public Skeleton(SkeletonData data) {
      if (data == null) {
         throw new IllegalArgumentException("data cannot be null.");
      } else {
         this.data = data;
         this.bones = new Array(data.bones.size);
         Object[] bones = this.bones.items;

         for (BoneData boneData : data.bones) {
            Bone bone;
            if (boneData.parent == null) {
               bone = new Bone(boneData, this, null);
            } else {
               Bone parent = (Bone)bones[boneData.parent.index];
               bone = new Bone(boneData, this, parent);
               parent.children.add(bone);
            }

            this.bones.add(bone);
         }

         this.slots = new Array(data.slots.size);
         this.drawOrder = new Array(data.slots.size);

         for (SlotData slotData : data.slots) {
            Bone bone = (Bone)bones[slotData.boneData.index];
            Slot slot = new Slot(slotData, bone);
            this.slots.add(slot);
            this.drawOrder.add(slot);
         }

         this.ikConstraints = new Array(data.ikConstraints.size);

         for (IkConstraintData ikConstraintData : data.ikConstraints) {
            this.ikConstraints.add(new IkConstraint(ikConstraintData, this));
         }

         this.transformConstraints = new Array(data.transformConstraints.size);

         for (TransformConstraintData transformConstraintData : data.transformConstraints) {
            this.transformConstraints.add(new TransformConstraint(transformConstraintData, this));
         }

         this.pathConstraints = new Array(data.pathConstraints.size);

         for (PathConstraintData pathConstraintData : data.pathConstraints) {
            this.pathConstraints.add(new PathConstraint(pathConstraintData, this));
         }

         this.physicsConstraints = new Array(data.physicsConstraints.size);

         for (PhysicsConstraintData physicsConstraintData : data.physicsConstraints) {
            this.physicsConstraints.add(new PhysicsConstraint(physicsConstraintData, this));
         }

         this.color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
         this.updateCache();
      }
   }

   public Skeleton(Skeleton skeleton) {
      if (skeleton == null) {
         throw new IllegalArgumentException("skeleton cannot be null.");
      } else {
         this.data = skeleton.data;
         this.bones = new Array(skeleton.bones.size);

         for (Bone bone : skeleton.bones) {
            Bone newBone;
            if (bone.parent == null) {
               newBone = new Bone(bone, this, null);
            } else {
               Bone parent = (Bone)this.bones.get(bone.parent.data.index);
               newBone = new Bone(bone, this, parent);
               parent.children.add(newBone);
            }

            this.bones.add(newBone);
         }

         this.slots = new Array(skeleton.slots.size);

         for (Slot slot : skeleton.slots) {
            Bone bone = (Bone)this.bones.get(slot.bone.data.index);
            this.slots.add(new Slot(slot, bone));
         }

         this.drawOrder = new Array(this.slots.size);

         for (Slot slot : skeleton.drawOrder) {
            this.drawOrder.add(this.slots.get(slot.data.index));
         }

         this.ikConstraints = new Array(skeleton.ikConstraints.size);

         for (IkConstraint ikConstraint : skeleton.ikConstraints) {
            this.ikConstraints.add(new IkConstraint(ikConstraint, this));
         }

         this.transformConstraints = new Array(skeleton.transformConstraints.size);

         for (TransformConstraint transformConstraint : skeleton.transformConstraints) {
            this.transformConstraints.add(new TransformConstraint(transformConstraint, this));
         }

         this.pathConstraints = new Array(skeleton.pathConstraints.size);

         for (PathConstraint pathConstraint : skeleton.pathConstraints) {
            this.pathConstraints.add(new PathConstraint(pathConstraint, this));
         }

         this.physicsConstraints = new Array(skeleton.physicsConstraints.size);

         for (PhysicsConstraint physicsConstraint : skeleton.physicsConstraints) {
            this.physicsConstraints.add(new PhysicsConstraint(physicsConstraint, this));
         }

         this.skin = skeleton.skin;
         this.color = new Color(skeleton.color);
         this.scaleX = skeleton.scaleX;
         this.scaleY = skeleton.scaleY;
         this.updateCache();
      }
   }

   public void updateCache() {
      Array updateCache = this.updateCache;
      updateCache.clear();
      int boneCount = this.bones.size;
      Object[] bones = this.bones.items;

      for (int i = 0; i < boneCount; i++) {
         Bone bone = (Bone)bones[i];
         bone.sorted = bone.data.skinRequired;
         bone.active = !bone.sorted;
      }

      if (this.skin != null) {
         Object[] skinBones = this.skin.bones.items;
         int k = 0;
         int n = this.skin.bones.size;
         if (k < n) {
            Bone bone = (Bone)bones[((BoneData)skinBones[k]).index];

            while (true) {
               bone.sorted = false;
               bone.active = true;
               bone = bone.parent;
               if (bone == null) {
                  k++;
               }
            }
         }
      }

      int ikCount = this.ikConstraints.size;
      int transformCount = this.transformConstraints.size;
      int pathCount = this.pathConstraints.size;
      int physicsCount = this.physicsConstraints.size;
      Object[] ikConstraints = this.ikConstraints.items;
      Object[] transformConstraints = this.transformConstraints.items;
      Object[] pathConstraints = this.pathConstraints.items;
      Object[] physicsConstraints = this.physicsConstraints.items;
      int constraintCount = ikCount + transformCount + pathCount + physicsCount;

      for (int j = 0; j < constraintCount; j++) {
         for (int ii = 0; ii < ikCount; ii++) {
            IkConstraint constraint = (IkConstraint)ikConstraints[ii];
            if (constraint.data.order == j) {
               this.sortIkConstraint(constraint);
               break;
            }
         }

         for (int var23 = 0; var23 < transformCount; var23++) {
            TransformConstraint constraint = (TransformConstraint)transformConstraints[var23];
            if (constraint.data.order == j) {
               this.sortTransformConstraint(constraint);
               break;
            }
         }

         for (int var24 = 0; var24 < pathCount; var24++) {
            PathConstraint constraint = (PathConstraint)pathConstraints[var24];
            if (constraint.data.order == j) {
               this.sortPathConstraint(constraint);
               break;
            }
         }

         for (int var25 = 0; var25 < physicsCount; var25++) {
            PhysicsConstraint constraint = (PhysicsConstraint)physicsConstraints[var25];
            if (constraint.data.order == j) {
               this.sortPhysicsConstraint(constraint);
               break;
            }
         }
      }

      for (int var22 = 0; var22 < boneCount; var22++) {
         this.sortBone((Bone)bones[var22]);
      }
   }

   private void sortIkConstraint(IkConstraint constraint) {
      constraint.active = constraint.target.active
         && (!constraint.data.skinRequired || this.skin != null && this.skin.constraints.contains(constraint.data, true));
      if (constraint.active) {
         Bone target = constraint.target;
         this.sortBone(target);
         Array constrained = constraint.bones;
         Bone parent = (Bone)constrained.first();
         this.sortBone(parent);
         if (constrained.size == 1) {
            this.updateCache.add(constraint);
            this.sortReset(parent.children);
         } else {
            Bone child = (Bone)constrained.peek();
            this.sortBone(child);
            this.updateCache.add(constraint);
            this.sortReset(parent.children);
            child.sorted = true;
         }
      }
   }

   private void sortTransformConstraint(TransformConstraint constraint) {
      constraint.active = constraint.target.active
         && (!constraint.data.skinRequired || this.skin != null && this.skin.constraints.contains(constraint.data, true));
      if (constraint.active) {
         this.sortBone(constraint.target);
         Object[] constrained = constraint.bones.items;
         int boneCount = constraint.bones.size;
         if (constraint.data.local) {
            for (int j = 0; j < boneCount; j++) {
               Bone child = (Bone)constrained[j];
               this.sortBone(child.parent);
               this.sortBone(child);
            }
         } else {
            for (int j = 0; j < boneCount; j++) {
               this.sortBone((Bone)constrained[j]);
            }
         }

         this.updateCache.add(constraint);

         for (int i = 0; i < boneCount; i++) {
            this.sortReset(((Bone)constrained[i]).children);
         }

         for (int var8 = 0; var8 < boneCount; var8++) {
            ((Bone)constrained[var8]).sorted = true;
         }
      }
   }

   private void sortPathConstraint(PathConstraint constraint) {
      constraint.active = constraint.target.bone.active
         && (!constraint.data.skinRequired || this.skin != null && this.skin.constraints.contains(constraint.data, true));
      if (constraint.active) {
         Slot slot = constraint.target;
         int slotIndex = slot.getData().index;
         Bone slotBone = slot.bone;
         if (this.skin != null) {
            this.sortPathConstraintAttachment(this.skin, slotIndex, slotBone);
         }

         if (this.data.defaultSkin != null && this.data.defaultSkin != this.skin) {
            this.sortPathConstraintAttachment(this.data.defaultSkin, slotIndex, slotBone);
         }

         Attachment attachment = slot.attachment;
         if (attachment instanceof PathAttachment) {
            this.sortPathConstraintAttachment(attachment, slotBone);
         }

         Object[] constrained = constraint.bones.items;
         int boneCount = constraint.bones.size;

         for (int i = 0; i < boneCount; i++) {
            this.sortBone((Bone)constrained[i]);
         }

         this.updateCache.add(constraint);

         for (int var9 = 0; var9 < boneCount; var9++) {
            this.sortReset(((Bone)constrained[var9]).children);
         }

         for (int var10 = 0; var10 < boneCount; var10++) {
            ((Bone)constrained[var10]).sorted = true;
         }
      }
   }

   private void sortPathConstraintAttachment(Skin skin, int slotIndex, Bone slotBone) {
      Object[] entries = skin.attachments.orderedItems().items;
      int i = 0;

      for (int n = skin.attachments.size; i < n; i++) {
         Skin.SkinEntry entry = (Skin.SkinEntry)entries[i];
         if (entry.slotIndex == slotIndex) {
            this.sortPathConstraintAttachment(entry.attachment, slotBone);
         }
      }
   }

   private void sortPathConstraintAttachment(Attachment attachment, Bone slotBone) {
      if (attachment instanceof PathAttachment) {
         int[] pathBones = ((PathAttachment)attachment).getBones();
         if (pathBones == null) {
            this.sortBone(slotBone);
         } else {
            Object[] bones = this.bones.items;
            int i = 0;
            int n = pathBones.length;

            while (i < n) {
               int nn = pathBones[i++];
               nn += i;

               while (i < nn) {
                  this.sortBone((Bone)bones[pathBones[i++]]);
               }
            }
         }
      }
   }

   private void sortPhysicsConstraint(PhysicsConstraint constraint) {
      constraint.active = !constraint.data.skinRequired || this.skin != null && this.skin.constraints.contains(constraint.data, true);
      if (constraint.active) {
         Object[] nodes = constraint.nodes.items;
         int nodeCount = constraint.nodes.size;

         for (int i = 0; i < nodeCount; i++) {
            PhysicsConstraintData.Node node = (PhysicsConstraintData.Node)nodes[i];
            if (node.parentBone != null) {
               this.sortBone(node.parentBone);
            }

            for (Bone bone : node.bones) {
               this.sortBone(bone);
            }
         }

         this.updateCache.add(constraint);

         for (int var10 = 0; var10 < nodeCount; var10++) {
            PhysicsConstraintData.Node node = (PhysicsConstraintData.Node)nodes[var10];
            if (node.parentBone != null) {
               this.sortReset(node.parentBone.children);
            }

            for (Bone bone : node.bones) {
               this.sortReset(bone.children);
            }
         }

         for (int var11 = 0; var11 < nodeCount; var11++) {
            PhysicsConstraintData.Node node = (PhysicsConstraintData.Node)nodes[var11];
            if (node.parentBone != null) {
               node.parentBone.sorted = true;
            }

            for (Bone bone : node.bones) {
               bone.sorted = true;
            }
         }
      }
   }

   private void sortBone(Bone bone) {
      if (!bone.sorted) {
         Bone parent = bone.parent;
         if (parent != null) {
            this.sortBone(parent);
         }

         bone.sorted = true;
         this.updateCache.add(bone);
      }
   }

   private void sortReset(Array bones) {
      Object[] items = bones.items;
      int i = 0;

      for (int n = bones.size; i < n; i++) {
         Bone bone = (Bone)items[i];
         if (bone.active) {
            if (bone.sorted) {
               this.sortReset(bone.children);
            }

            bone.sorted = false;
         }
      }
   }

   public void updateWorldTransform() {
      Object[] bones = this.bones.items;
      int i = 0;

      for (int n = this.bones.size; i < n; i++) {
         Bone bone = (Bone)bones[i];
         bone.ax = bone.x;
         bone.ay = bone.y;
         bone.arotation = bone.rotation;
         bone.ascaleX = bone.scaleX;
         bone.ascaleY = bone.scaleY;
         bone.ashearX = bone.shearX;
         bone.ashearY = bone.shearY;
      }

      Object[] updateCache = this.updateCache.items;
      int j = 0;

      for (int k = this.updateCache.size; j < k; j++) {
         ((Updatable)updateCache[j]).update();
      }
   }

   public void updateWorldTransform(Bone parent) {
      if (parent == null) {
         throw new IllegalArgumentException("parent cannot be null.");
      } else {
         Bone rootBone = this.getRootBone();
         float pa = parent.a;
         float pb = parent.b;
         float pc = parent.c;
         float pd = parent.d;
         rootBone.worldX = pa * this.x + pb * this.y + parent.worldX;
         rootBone.worldY = pc * this.x + pd * this.y + parent.worldY;
         float rotationY = rootBone.rotation + 90.0F + rootBone.shearY;
         float la = SpineUtils.cosDeg(rootBone.rotation + rootBone.shearX) * rootBone.scaleX;
         float lb = SpineUtils.cosDeg(rotationY) * rootBone.scaleY;
         float lc = SpineUtils.sinDeg(rootBone.rotation + rootBone.shearX) * rootBone.scaleX;
         float ld = SpineUtils.sinDeg(rotationY) * rootBone.scaleY;
         rootBone.a = (pa * la + pb * lc) * this.scaleX;
         rootBone.b = (pa * lb + pb * ld) * this.scaleX;
         rootBone.c = (pc * la + pd * lc) * this.scaleY;
         rootBone.d = (pc * lb + pd * ld) * this.scaleY;
         Object[] updateCache = this.updateCache.items;
         int i = 0;

         for (int n = this.updateCache.size; i < n; i++) {
            Updatable updatable = (Updatable)updateCache[i];
            if (updatable != rootBone) {
               updatable.update();
            }
         }
      }
   }

   public void setToSetupPose() {
      this.setBonesToSetupPose();
      this.setSlotsToSetupPose();
   }

   public void setBonesToSetupPose() {
      Object[] bones = this.bones.items;
      int i = 0;

      for (int n = this.bones.size; i < n; i++) {
         ((Bone)bones[i]).setToSetupPose();
      }

      Object[] ikConstraints = this.ikConstraints.items;
      int j = 0;

      for (int m = this.ikConstraints.size; j < m; j++) {
         ((IkConstraint)ikConstraints[j]).setToSetupPose();
      }

      Object[] transformConstraints = this.transformConstraints.items;
      int k = 0;

      for (int i2 = this.transformConstraints.size; k < i2; k++) {
         ((TransformConstraint)transformConstraints[k]).setToSetupPose();
      }

      Object[] pathConstraints = this.pathConstraints.items;
      int i1 = 0;

      for (int i4 = this.pathConstraints.size; i1 < i4; i1++) {
         ((PathConstraint)pathConstraints[i1]).setToSetupPose();
      }

      Object[] physicsConstraints = this.physicsConstraints.items;
      int i3 = 0;

      for (int i5 = this.physicsConstraints.size; i3 < i5; i3++) {
         ((PhysicsConstraint)physicsConstraints[i3]).setToSetupPose();
      }
   }

   public void setSlotsToSetupPose() {
      Object[] slots = this.slots.items;
      int n = this.slots.size;
      SpineUtils.arraycopy(slots, 0, this.drawOrder.items, 0, n);

      for (int i = 0; i < n; i++) {
         ((Slot)slots[i]).setToSetupPose();
      }
   }

   public SkeletonData getData() {
      return this.data;
   }

   public Array getBones() {
      return this.bones;
   }

   public Array getUpdateCache() {
      return this.updateCache;
   }

   public Bone getRootBone() {
      return this.bones.size == 0 ? null : (Bone)this.bones.first();
   }

   @Null
   public Bone findBone(String boneName) {
      if (boneName == null) {
         throw new IllegalArgumentException("boneName cannot be null.");
      } else {
         Object[] bones = this.bones.items;
         int i = 0;

         for (int n = this.bones.size; i < n; i++) {
            Bone bone = (Bone)bones[i];
            if (bone.data.name.equals(boneName)) {
               return bone;
            }
         }

         return null;
      }
   }

   public Array getSlots() {
      return this.slots;
   }

   @Null
   public Slot findSlot(String slotName) {
      if (slotName == null) {
         throw new IllegalArgumentException("slotName cannot be null.");
      } else {
         Object[] slots = this.slots.items;
         int i = 0;

         for (int n = this.slots.size; i < n; i++) {
            Slot slot = (Slot)slots[i];
            if (slot.data.name.equals(slotName)) {
               return slot;
            }
         }

         return null;
      }
   }

   public Array getDrawOrder() {
      return this.drawOrder;
   }

   public void setDrawOrder(Array drawOrder) {
      if (drawOrder == null) {
         throw new IllegalArgumentException("drawOrder cannot be null.");
      } else {
         this.drawOrder = drawOrder;
      }
   }

   @Null
   public Skin getSkin() {
      return this.skin;
   }

   public void setSkin(String skinName) {
      Skin skin = this.data.findSkin(skinName);
      if (skin == null) {
         throw new IllegalArgumentException("Skin not found: " + skinName);
      } else {
         this.setSkin(skin);
      }
   }

   public void setSkin(@Null Skin newSkin) {
      if (newSkin != this.skin) {
         if (newSkin != null) {
            if (this.skin != null) {
               newSkin.attachAll(this, this.skin);
            } else {
               Object[] slots = this.slots.items;
               int i = 0;

               for (int n = this.slots.size; i < n; i++) {
                  Slot slot = (Slot)slots[i];
                  String name = slot.data.attachmentName;
                  if (name != null) {
                     Attachment attachment = newSkin.getAttachment(i, name);
                     if (attachment != null) {
                        slot.setAttachment(attachment);
                     }
                  }
               }
            }
         }

         this.skin = newSkin;
         this.updateCache();
      }
   }

   @Null
   public Attachment getAttachment(String slotName, String attachmentName) {
      SlotData slot = this.data.findSlot(slotName);
      if (slot == null) {
         throw new IllegalArgumentException("Slot not found: " + slotName);
      } else {
         return this.getAttachment(slot.getIndex(), attachmentName);
      }
   }

   @Null
   public Attachment getAttachment(int slotIndex, String attachmentName) {
      if (attachmentName == null) {
         throw new IllegalArgumentException("attachmentName cannot be null.");
      } else {
         if (this.skin != null) {
            Attachment attachment = this.skin.getAttachment(slotIndex, attachmentName);
            if (attachment != null) {
               return attachment;
            }
         }

         return this.data.defaultSkin != null ? this.data.defaultSkin.getAttachment(slotIndex, attachmentName) : null;
      }
   }

   public void setAttachment(String slotName, @Null String attachmentName) {
      if (slotName == null) {
         throw new IllegalArgumentException("slotName cannot be null.");
      } else {
         Slot slot = this.findSlot(slotName);
         if (slot == null) {
            throw new IllegalArgumentException("Slot not found: " + slotName);
         } else {
            Attachment attachment = null;
            if (attachmentName != null) {
               attachment = this.getAttachment(slot.data.index, attachmentName);
               if (attachment == null) {
                  throw new IllegalArgumentException("Attachment not found: " + attachmentName + ", for slot: " + slotName);
               }
            }

            slot.setAttachment(attachment);
         }
      }
   }

   public Array getIkConstraints() {
      return this.ikConstraints;
   }

   @Null
   public IkConstraint findIkConstraint(String constraintName) {
      if (constraintName == null) {
         throw new IllegalArgumentException("constraintName cannot be null.");
      } else {
         Object[] ikConstraints = this.ikConstraints.items;
         int i = 0;

         for (int n = this.ikConstraints.size; i < n; i++) {
            IkConstraint ikConstraint = (IkConstraint)ikConstraints[i];
            if (ikConstraint.data.name.equals(constraintName)) {
               return ikConstraint;
            }
         }

         return null;
      }
   }

   public Array getTransformConstraints() {
      return this.transformConstraints;
   }

   @Null
   public TransformConstraint findTransformConstraint(String constraintName) {
      if (constraintName == null) {
         throw new IllegalArgumentException("constraintName cannot be null.");
      } else {
         Object[] transformConstraints = this.transformConstraints.items;
         int i = 0;

         for (int n = this.transformConstraints.size; i < n; i++) {
            TransformConstraint constraint = (TransformConstraint)transformConstraints[i];
            if (constraint.data.name.equals(constraintName)) {
               return constraint;
            }
         }

         return null;
      }
   }

   public Array getPathConstraints() {
      return this.pathConstraints;
   }

   @Null
   public PathConstraint findPathConstraint(String constraintName) {
      if (constraintName == null) {
         throw new IllegalArgumentException("constraintName cannot be null.");
      } else {
         Object[] pathConstraints = this.pathConstraints.items;
         int i = 0;

         for (int n = this.pathConstraints.size; i < n; i++) {
            PathConstraint constraint = (PathConstraint)pathConstraints[i];
            if (constraint.data.name.equals(constraintName)) {
               return constraint;
            }
         }

         return null;
      }
   }

   public Array getPhysicsConstraints() {
      return this.physicsConstraints;
   }

   @Null
   public PhysicsConstraint findPhysicsConstraint(String constraintName) {
      if (constraintName == null) {
         throw new IllegalArgumentException("constraintName cannot be null.");
      } else {
         Object[] physicsConstraints = this.physicsConstraints.items;
         int i = 0;

         for (int n = this.physicsConstraints.size; i < n; i++) {
            PhysicsConstraint constraint = (PhysicsConstraint)physicsConstraints[i];
            if (constraint.data.name.equals(constraintName)) {
               return constraint;
            }
         }

         return null;
      }
   }

   public void getBounds(Vector2 offset, Vector2 size, FloatArray temp) {
      if (offset == null) {
         throw new IllegalArgumentException("offset cannot be null.");
      } else if (size == null) {
         throw new IllegalArgumentException("size cannot be null.");
      } else if (temp == null) {
         throw new IllegalArgumentException("temp cannot be null.");
      } else {
         Object[] drawOrder = this.drawOrder.items;
         float minX = 2.1474836E9F;
         float minY = 2.1474836E9F;
         float maxX = -2.1474836E9F;
         float maxY = -2.1474836E9F;
         int i = 0;

         for (int n = this.drawOrder.size; i < n; i++) {
            Slot slot = (Slot)drawOrder[i];
            if (slot.bone.active) {
               int verticesLength = 0;
               float[] vertices = null;
               Attachment attachment = slot.attachment;
               if (attachment instanceof RegionAttachment) {
                  RegionAttachment region = (RegionAttachment)attachment;
                  verticesLength = 8;
                  vertices = temp.setSize(8);
                  region.computeWorldVertices(slot, vertices, 0, 2);
               } else if (attachment instanceof MeshAttachment) {
                  MeshAttachment mesh = (MeshAttachment)attachment;
                  verticesLength = mesh.getWorldVerticesLength();
                  vertices = temp.setSize(verticesLength);
                  mesh.computeWorldVertices(slot, 0, verticesLength, vertices, 0, 2);
               }

               if (vertices != null) {
                  for (int ii = 0; ii < verticesLength; ii += 2) {
                     float x = vertices[ii];
                     float y = vertices[ii + 1];
                     minX = Math.min(minX, x);
                     minY = Math.min(minY, y);
                     maxX = Math.max(maxX, x);
                     maxY = Math.max(maxY, y);
                  }
               }
            }
         }

         offset.set(minX, minY);
         size.set(maxX - minX, maxY - minY);
      }
   }

   public Color getColor() {
      return this.color;
   }

   public void setColor(Color color) {
      if (color == null) {
         throw new IllegalArgumentException("color cannot be null.");
      } else {
         this.color.set(color);
      }
   }

   public void setColor(float r, float g, float b, float a) {
      this.color.set(r, g, b, a);
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

   public void setScale(float scaleX, float scaleY) {
      this.scaleX = scaleX;
      this.scaleY = scaleY;
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

   public void setPosition(float x, float y) {
      this.x = x;
      this.y = y;
   }

   @Override
   public String toString() {
      return this.data.name != null ? this.data.name : super.toString();
   }
}
