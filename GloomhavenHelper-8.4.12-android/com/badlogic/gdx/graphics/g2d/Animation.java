package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.reflect.ArrayReflection;

public class Animation {
    public static enum PlayMode {
        NORMAL,
        REVERSED,
        LOOP,
        LOOP_REVERSED,
        LOOP_PINGPONG,
        LOOP_RANDOM;

    }

    private float animationDuration;
    private float frameDuration;
    Object[] keyFrames;
    private int lastFrameNumber;
    private float lastStateTime;
    private PlayMode playMode;

    public Animation(float f, Array array0) {
        this.playMode = PlayMode.NORMAL;
        this.frameDuration = f;
        Object[] arr_object = (Object[])ArrayReflection.newInstance(array0.items.getClass().getComponentType(), array0.size);
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            arr_object[v1] = array0.get(v1);
        }
        this.setKeyFrames(arr_object);
    }

    public Animation(float f, Array array0, PlayMode animation$PlayMode0) {
        this(f, array0);
        this.setPlayMode(animation$PlayMode0);
    }

    public Animation(float f, Object[] arr_object) {
        this.playMode = PlayMode.NORMAL;
        this.frameDuration = f;
        this.setKeyFrames(arr_object);
    }

    public float getAnimationDuration() {
        return this.animationDuration;
    }

    public float getFrameDuration() {
        return this.frameDuration;
    }

    public Object getKeyFrame(float f) {
        return this.keyFrames[this.getKeyFrameIndex(f)];
    }

    public Object getKeyFrame(float f, boolean z) {
        PlayMode animation$PlayMode0 = this.playMode;
        if(!z || animation$PlayMode0 != PlayMode.NORMAL && this.playMode != PlayMode.REVERSED) {
            if(!z && this.playMode != PlayMode.NORMAL && this.playMode != PlayMode.REVERSED) {
                this.playMode = this.playMode == PlayMode.LOOP_REVERSED ? PlayMode.REVERSED : PlayMode.LOOP;
            }
        }
        else if(this.playMode == PlayMode.NORMAL) {
            this.playMode = PlayMode.LOOP;
        }
        else {
            this.playMode = PlayMode.LOOP_REVERSED;
        }
        Object object0 = this.getKeyFrame(f);
        this.playMode = animation$PlayMode0;
        return object0;
    }

    public int getKeyFrameIndex(float f) {
        if(this.keyFrames.length == 1) {
            return 0;
        }
        int v = (int)(f / this.frameDuration);
        switch(this.playMode) {
            case NORMAL: {
                v = Math.min(this.keyFrames.length - 1, v);
                break;
            }
            case LOOP: {
                v %= this.keyFrames.length;
                break;
            }
            case LOOP_PINGPONG: {
                Object[] arr_object = this.keyFrames;
                v %= arr_object.length * 2 - 2;
                if(v >= arr_object.length) {
                    v = arr_object.length - 2 - (v - arr_object.length);
                }
                break;
            }
            case LOOP_RANDOM: {
                v = ((int)(this.lastStateTime / this.frameDuration)) == v ? this.lastFrameNumber : MathUtils.random(this.keyFrames.length - 1);
                break;
            }
            case REVERSED: {
                v = Math.max(this.keyFrames.length - v - 1, 0);
                break;
            }
            case LOOP_REVERSED: {
                v = this.keyFrames.length - v % this.keyFrames.length - 1;
            }
        }
        this.lastFrameNumber = v;
        this.lastStateTime = f;
        return v;
    }

    public Object[] getKeyFrames() {
        return this.keyFrames;
    }

    public PlayMode getPlayMode() {
        return this.playMode;
    }

    public boolean isAnimationFinished(float f) {
        return this.keyFrames.length - 1 < ((int)(f / this.frameDuration));
    }

    public void setFrameDuration(float f) {
        this.frameDuration = f;
        this.animationDuration = ((float)this.keyFrames.length) * f;
    }

    protected void setKeyFrames(Object[] arr_object) {
        this.keyFrames = arr_object;
        this.animationDuration = ((float)arr_object.length) * this.frameDuration;
    }

    public void setPlayMode(PlayMode animation$PlayMode0) {
        this.playMode = animation$PlayMode0;
    }
}

