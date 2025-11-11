package com.esotericsoftware.spine;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;

public class SkeletonData {
    final Array animations;
    @Null
    String audioPath;
    final Array bones;
    @Null
    Skin defaultSkin;
    final Array events;
    float fps;
    @Null
    String hash;
    float height;
    final Array ikConstraints;
    @Null
    String imagesPath;
    @Null
    String name;
    final Array pathConstraints;
    final Array physicsConstraints;
    final Array skins;
    final Array slots;
    final Array transformConstraints;
    @Null
    String version;
    float width;
    float x;
    float y;

    public SkeletonData() {
        this.bones = new Array();
        this.slots = new Array();
        this.skins = new Array();
        this.events = new Array();
        this.animations = new Array();
        this.ikConstraints = new Array();
        this.transformConstraints = new Array();
        this.pathConstraints = new Array();
        this.physicsConstraints = new Array();
        this.fps = 30.0f;
    }

    @Null
    public Animation findAnimation(String s) {
        if(s == null) {
            throw new IllegalArgumentException("animationName cannot be null.");
        }
        Object[] arr_object = this.animations.items;
        int v1 = this.animations.size;
        for(int v = 0; v < v1; ++v) {
            Animation animation0 = (Animation)arr_object[v];
            if(animation0.name.equals(s)) {
                return animation0;
            }
        }
        return null;
    }

    @Null
    public BoneData findBone(String s) {
        if(s == null) {
            throw new IllegalArgumentException("boneName cannot be null.");
        }
        Object[] arr_object = this.bones.items;
        int v1 = this.bones.size;
        for(int v = 0; v < v1; ++v) {
            BoneData boneData0 = (BoneData)arr_object[v];
            if(boneData0.name.equals(s)) {
                return boneData0;
            }
        }
        return null;
    }

    @Null
    public EventData findEvent(String s) {
        if(s == null) {
            throw new IllegalArgumentException("eventDataName cannot be null.");
        }
        for(Object object0: this.events) {
            EventData eventData0 = (EventData)object0;
            if(eventData0.name.equals(s)) {
                return eventData0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    @Null
    public IkConstraintData findIkConstraint(String s) {
        if(s == null) {
            throw new IllegalArgumentException("constraintName cannot be null.");
        }
        Object[] arr_object = this.ikConstraints.items;
        int v1 = this.ikConstraints.size;
        for(int v = 0; v < v1; ++v) {
            IkConstraintData ikConstraintData0 = (IkConstraintData)arr_object[v];
            if(ikConstraintData0.name.equals(s)) {
                return ikConstraintData0;
            }
        }
        return null;
    }

    @Null
    public PathConstraintData findPathConstraint(String s) {
        if(s == null) {
            throw new IllegalArgumentException("constraintName cannot be null.");
        }
        Object[] arr_object = this.pathConstraints.items;
        int v1 = this.pathConstraints.size;
        for(int v = 0; v < v1; ++v) {
            PathConstraintData pathConstraintData0 = (PathConstraintData)arr_object[v];
            if(pathConstraintData0.name.equals(s)) {
                return pathConstraintData0;
            }
        }
        return null;
    }

    @Null
    public PhysicsConstraintData findPhysicsConstraint(String s) {
        if(s == null) {
            throw new IllegalArgumentException("constraintName cannot be null.");
        }
        Object[] arr_object = this.physicsConstraints.items;
        int v1 = this.physicsConstraints.size;
        for(int v = 0; v < v1; ++v) {
            PhysicsConstraintData physicsConstraintData0 = (PhysicsConstraintData)arr_object[v];
            if(physicsConstraintData0.name.equals(s)) {
                return physicsConstraintData0;
            }
        }
        return null;
    }

    @Null
    public Skin findSkin(String s) {
        if(s == null) {
            throw new IllegalArgumentException("skinName cannot be null.");
        }
        for(Object object0: this.skins) {
            Skin skin0 = (Skin)object0;
            if(skin0.name.equals(s)) {
                return skin0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    @Null
    public SlotData findSlot(String s) {
        if(s == null) {
            throw new IllegalArgumentException("slotName cannot be null.");
        }
        Object[] arr_object = this.slots.items;
        int v1 = this.slots.size;
        for(int v = 0; v < v1; ++v) {
            SlotData slotData0 = (SlotData)arr_object[v];
            if(slotData0.name.equals(s)) {
                return slotData0;
            }
        }
        return null;
    }

    @Null
    public TransformConstraintData findTransformConstraint(String s) {
        if(s == null) {
            throw new IllegalArgumentException("constraintName cannot be null.");
        }
        Object[] arr_object = this.transformConstraints.items;
        int v1 = this.transformConstraints.size;
        for(int v = 0; v < v1; ++v) {
            TransformConstraintData transformConstraintData0 = (TransformConstraintData)arr_object[v];
            if(transformConstraintData0.name.equals(s)) {
                return transformConstraintData0;
            }
        }
        return null;
    }

    public Array getAnimations() {
        return this.animations;
    }

    @Null
    public String getAudioPath() {
        return this.audioPath;
    }

    public Array getBones() {
        return this.bones;
    }

    @Null
    public Skin getDefaultSkin() {
        return this.defaultSkin;
    }

    public Array getEvents() {
        return this.events;
    }

    public float getFps() {
        return this.fps;
    }

    @Null
    public String getHash() {
        return this.hash;
    }

    public float getHeight() {
        return this.height;
    }

    public Array getIkConstraints() {
        return this.ikConstraints;
    }

    @Null
    public String getImagesPath() {
        return this.imagesPath;
    }

    @Null
    public String getName() {
        return this.name;
    }

    public Array getPathConstraints() {
        return this.pathConstraints;
    }

    public Array getPhysicsConstraints() {
        return this.physicsConstraints;
    }

    public Array getSkins() {
        return this.skins;
    }

    public Array getSlots() {
        return this.slots;
    }

    public Array getTransformConstraints() {
        return this.transformConstraints;
    }

    @Null
    public String getVersion() {
        return this.version;
    }

    public float getWidth() {
        return this.width;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setAudioPath(@Null String s) {
        this.audioPath = s;
    }

    public void setDefaultSkin(@Null Skin skin0) {
        this.defaultSkin = skin0;
    }

    public void setFps(float f) {
        this.fps = f;
    }

    public void setHash(@Null String s) {
        this.hash = s;
    }

    public void setHeight(float f) {
        this.height = f;
    }

    public void setImagesPath(@Null String s) {
        this.imagesPath = s;
    }

    public void setName(@Null String s) {
        this.name = s;
    }

    public void setVersion(@Null String s) {
        this.version = s;
    }

    public void setWidth(float f) {
        this.width = f;
    }

    public void setX(float f) {
        this.x = f;
    }

    public void setY(float f) {
        this.y = f;
    }

    @Override
    public String toString() {
        return this.name == null ? super.toString() : this.name;
    }
}

