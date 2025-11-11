package com.esotericsoftware.spine;

import com.badlogic.gdx.utils.ObjectFloatMap;

public class AnimationStateData {
    static class Key {
        Animation a1;
        Animation a2;

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(object0 == null) {
                return false;
            }
            Animation animation0 = this.a1;
            if(animation0 == null) {
                if(((Key)object0).a1 != null) {
                    return false;
                }
            }
            else if(!animation0.equals(((Key)object0).a1)) {
                return false;
            }
            return this.a2 == null ? ((Key)object0).a2 == null : this.a2.equals(((Key)object0).a2);
        }

        @Override
        public int hashCode() {
            return (this.a1.hashCode() + 0x1F) * 0x1F + this.a2.hashCode();
        }

        @Override
        public String toString() {
            return this.a1.name + "->" + this.a2.name;
        }
    }

    final ObjectFloatMap animationToMixTime;
    float defaultMix;
    final SkeletonData skeletonData;
    final Key tempKey;

    public AnimationStateData(SkeletonData skeletonData0) {
        this.animationToMixTime = new ObjectFloatMap(51, 0.8f);
        this.tempKey = new Key();
        if(skeletonData0 == null) {
            throw new IllegalArgumentException("skeletonData cannot be null.");
        }
        this.skeletonData = skeletonData0;
    }

    public float getDefaultMix() {
        return this.defaultMix;
    }

    public float getMix(Animation animation0, Animation animation1) {
        if(animation0 == null) {
            throw new IllegalArgumentException("from cannot be null.");
        }
        if(animation1 == null) {
            throw new IllegalArgumentException("to cannot be null.");
        }
        this.tempKey.a1 = animation0;
        this.tempKey.a2 = animation1;
        return this.animationToMixTime.get(this.tempKey, this.defaultMix);
    }

    public SkeletonData getSkeletonData() {
        return this.skeletonData;
    }

    public void setDefaultMix(float f) {
        this.defaultMix = f;
    }

    public void setMix(Animation animation0, Animation animation1, float f) {
        if(animation0 == null) {
            throw new IllegalArgumentException("from cannot be null.");
        }
        if(animation1 == null) {
            throw new IllegalArgumentException("to cannot be null.");
        }
        Key animationStateData$Key0 = new Key();
        animationStateData$Key0.a1 = animation0;
        animationStateData$Key0.a2 = animation1;
        this.animationToMixTime.put(animationStateData$Key0, f);
    }

    public void setMix(String s, String s1, float f) {
        Animation animation0 = this.skeletonData.findAnimation(s);
        if(animation0 == null) {
            throw new IllegalArgumentException("Animation not found: " + s);
        }
        Animation animation1 = this.skeletonData.findAnimation(s1);
        if(animation1 == null) {
            throw new IllegalArgumentException("Animation not found: " + s1);
        }
        this.setMix(animation0, animation1, f);
    }
}

