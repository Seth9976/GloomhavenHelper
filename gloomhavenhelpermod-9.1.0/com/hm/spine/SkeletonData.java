package com.hm.spine;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;

public class SkeletonData {
   @Null
   String name;
   final Array bones = new Array();
   final Array slots = new Array();
   final Array skins = new Array();
   final Array events = new Array();
   @Null
   Skin defaultSkin;
   final Array animations = new Array();
   final Array ikConstraints = new Array();
   final Array transformConstraints = new Array();
   final Array pathConstraints = new Array();
   final Array physicsConstraints = new Array();
   float x;
   float y;
   float width;
   float fps = 30.0F;
   float height;
   @Null
   String version;
   @Null
   String hash;
   @Null
   String imagesPath;
   @Null
   String audioPath;

   public Array getBones() {
      return this.bones;
   }

   @Null
   public BoneData findBone(String boneName) {
      if (boneName == null) {
         throw new IllegalArgumentException("boneName cannot be null.");
      } else {
         Object[] bones = this.bones.items;
         int i = 0;

         for (int n = this.bones.size; i < n; i++) {
            BoneData bone = (BoneData)bones[i];
            if (bone.name.equals(boneName)) {
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
   public SlotData findSlot(String slotName) {
      if (slotName == null) {
         throw new IllegalArgumentException("slotName cannot be null.");
      } else {
         Object[] slots = this.slots.items;
         int i = 0;

         for (int n = this.slots.size; i < n; i++) {
            SlotData slot = (SlotData)slots[i];
            if (slot.name.equals(slotName)) {
               return slot;
            }
         }

         return null;
      }
   }

   @Null
   public Skin getDefaultSkin() {
      return this.defaultSkin;
   }

   public void setDefaultSkin(@Null Skin defaultSkin) {
      this.defaultSkin = defaultSkin;
   }

   @Null
   public Skin findSkin(String skinName) {
      if (skinName == null) {
         throw new IllegalArgumentException("skinName cannot be null.");
      } else {
         for (Skin skin : this.skins) {
            if (skin.name.equals(skinName)) {
               return skin;
            }
         }

         return null;
      }
   }

   public Array getSkins() {
      return this.skins;
   }

   @Null
   public EventData findEvent(String eventDataName) {
      if (eventDataName == null) {
         throw new IllegalArgumentException("eventDataName cannot be null.");
      } else {
         for (EventData eventData : this.events) {
            if (eventData.name.equals(eventDataName)) {
               return eventData;
            }
         }

         return null;
      }
   }

   public Array getEvents() {
      return this.events;
   }

   public Array getAnimations() {
      return this.animations;
   }

   @Null
   public Animation findAnimation(String animationName) {
      if (animationName == null) {
         throw new IllegalArgumentException("animationName cannot be null.");
      } else {
         Object[] animations = this.animations.items;
         int i = 0;

         for (int n = this.animations.size; i < n; i++) {
            Animation animation = (Animation)animations[i];
            if (animation.name.equals(animationName)) {
               return animation;
            }
         }

         return null;
      }
   }

   public Array getIkConstraints() {
      return this.ikConstraints;
   }

   @Null
   public IkConstraintData findIkConstraint(String constraintName) {
      if (constraintName == null) {
         throw new IllegalArgumentException("constraintName cannot be null.");
      } else {
         Object[] ikConstraints = this.ikConstraints.items;
         int i = 0;

         for (int n = this.ikConstraints.size; i < n; i++) {
            IkConstraintData constraint = (IkConstraintData)ikConstraints[i];
            if (constraint.name.equals(constraintName)) {
               return constraint;
            }
         }

         return null;
      }
   }

   public Array getTransformConstraints() {
      return this.transformConstraints;
   }

   @Null
   public TransformConstraintData findTransformConstraint(String constraintName) {
      if (constraintName == null) {
         throw new IllegalArgumentException("constraintName cannot be null.");
      } else {
         Object[] transformConstraints = this.transformConstraints.items;
         int i = 0;

         for (int n = this.transformConstraints.size; i < n; i++) {
            TransformConstraintData constraint = (TransformConstraintData)transformConstraints[i];
            if (constraint.name.equals(constraintName)) {
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
   public PathConstraintData findPathConstraint(String constraintName) {
      if (constraintName == null) {
         throw new IllegalArgumentException("constraintName cannot be null.");
      } else {
         Object[] pathConstraints = this.pathConstraints.items;
         int i = 0;

         for (int n = this.pathConstraints.size; i < n; i++) {
            PathConstraintData constraint = (PathConstraintData)pathConstraints[i];
            if (constraint.name.equals(constraintName)) {
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
   public PhysicsConstraintData findPhysicsConstraint(String constraintName) {
      if (constraintName == null) {
         throw new IllegalArgumentException("constraintName cannot be null.");
      } else {
         Object[] physicsConstraints = this.physicsConstraints.items;
         int i = 0;

         for (int n = this.physicsConstraints.size; i < n; i++) {
            PhysicsConstraintData constraint = (PhysicsConstraintData)physicsConstraints[i];
            if (constraint.name.equals(constraintName)) {
               return constraint;
            }
         }

         return null;
      }
   }

   @Null
   public String getName() {
      return this.name;
   }

   public void setName(@Null String name) {
      this.name = name;
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
   public String getVersion() {
      return this.version;
   }

   public void setVersion(@Null String version) {
      this.version = version;
   }

   @Null
   public String getHash() {
      return this.hash;
   }

   public void setHash(@Null String hash) {
      this.hash = hash;
   }

   @Null
   public String getImagesPath() {
      return this.imagesPath;
   }

   public void setImagesPath(@Null String imagesPath) {
      this.imagesPath = imagesPath;
   }

   @Null
   public String getAudioPath() {
      return this.audioPath;
   }

   public void setAudioPath(@Null String audioPath) {
      this.audioPath = audioPath;
   }

   public float getFps() {
      return this.fps;
   }

   public void setFps(float fps) {
      this.fps = fps;
   }

   @Override
   public String toString() {
      return this.name != null ? this.name : super.toString();
   }
}
