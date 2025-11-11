package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Pool;

public class AnimationController extends BaseAnimationController {
    public static class AnimationDesc {
        public Animation animation;
        public float duration;
        public AnimationListener listener;
        public int loopCount;
        public float offset;
        public float speed;
        public float time;

        protected float update(float f) {
            int v;
            if(this.loopCount != 0 && this.animation != null) {
                float f1 = this.speed * f;
                float f2 = 0.0f;
                if(MathUtils.isZero(this.duration)) {
                    v = 1;
                }
                else {
                    this.time += f1;
                    if(this.speed < 0.0f) {
                        float f3 = this.duration - this.time;
                        v = (int)Math.abs(f3 / this.duration);
                        this.time = this.duration - Math.abs(f3 % this.duration);
                    }
                    else {
                        v = (int)Math.abs(this.time / this.duration);
                        this.time = Math.abs(this.time % this.duration);
                    }
                }
                for(int v1 = 0; v1 < v; ++v1) {
                    int v2 = this.loopCount;
                    if(v2 > 0) {
                        this.loopCount = v2 - 1;
                    }
                    if(this.loopCount != 0) {
                        AnimationListener animationController$AnimationListener0 = this.listener;
                        if(animationController$AnimationListener0 != null) {
                            animationController$AnimationListener0.onLoop(this);
                        }
                    }
                    if(this.loopCount == 0) {
                        float f4 = this.duration;
                        float f5 = f1 < 0.0f ? f4 - this.time : this.time;
                        if(f1 >= 0.0f) {
                            f2 = this.duration;
                        }
                        this.time = f2;
                        AnimationListener animationController$AnimationListener1 = this.listener;
                        if(animationController$AnimationListener1 != null) {
                            animationController$AnimationListener1.onEnd(this);
                        }
                        return ((float)(v - 1 - v1)) * f4 + f5;
                    }
                }
                return -1.0f;
            }
            return f;
        }
    }

    public interface AnimationListener {
        void onEnd(AnimationDesc arg1);

        void onLoop(AnimationDesc arg1);
    }

    public boolean allowSameAnimation;
    protected final Pool animationPool;
    public AnimationDesc current;
    public boolean inAction;
    private boolean justChangedAnimation;
    public boolean paused;
    public AnimationDesc previous;
    public AnimationDesc queued;
    public float queuedTransitionTime;
    public float transitionCurrentTime;
    public float transitionTargetTime;

    public AnimationController(ModelInstance modelInstance0) {
        super(modelInstance0);
        this.animationPool = new Pool() {
            protected AnimationDesc newObject() {
                return new AnimationDesc();
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return this.newObject();
            }
        };
        this.justChangedAnimation = false;
    }

    protected AnimationDesc action(Animation animation0, float f, float f1, int v, float f2, AnimationListener animationController$AnimationListener0, float f3) {
        return this.action(this.obtain(animation0, f, f1, v, f2, animationController$AnimationListener0), f3);
    }

    protected AnimationDesc action(AnimationDesc animationController$AnimationDesc0, float f) {
        if(animationController$AnimationDesc0.loopCount < 0) {
            throw new GdxRuntimeException("An action cannot be continuous");
        }
        if(this.current == null || this.current.loopCount == 0) {
            this.animate(animationController$AnimationDesc0, f);
        }
        else {
            AnimationDesc animationController$AnimationDesc1 = this.inAction ? null : this.obtain(this.current);
            this.inAction = false;
            this.animate(animationController$AnimationDesc0, f);
            this.inAction = true;
            if(animationController$AnimationDesc1 != null) {
                this.queue(animationController$AnimationDesc1, f);
                return animationController$AnimationDesc0;
            }
        }
        return animationController$AnimationDesc0;
    }

    public AnimationDesc action(String s, float f, float f1, int v, float f2, AnimationListener animationController$AnimationListener0, float f3) {
        return this.action(this.obtain(s, f, f1, v, f2, animationController$AnimationListener0), f3);
    }

    public AnimationDesc action(String s, int v, float f, AnimationListener animationController$AnimationListener0, float f1) {
        return this.action(s, 0.0f, -1.0f, v, f, animationController$AnimationListener0, f1);
    }

    protected AnimationDesc animate(Animation animation0, float f, float f1, int v, float f2, AnimationListener animationController$AnimationListener0, float f3) {
        return this.animate(this.obtain(animation0, f, f1, v, f2, animationController$AnimationListener0), f3);
    }

    protected AnimationDesc animate(AnimationDesc animationController$AnimationDesc0, float f) {
        if(this.current != null && this.current.loopCount != 0) {
            if(this.inAction) {
                this.queue(animationController$AnimationDesc0, f);
                return animationController$AnimationDesc0;
            }
            if(!this.allowSameAnimation && animationController$AnimationDesc0 != null && this.current.animation == animationController$AnimationDesc0.animation) {
                animationController$AnimationDesc0.time = this.current.time;
                this.animationPool.free(this.current);
                this.current = animationController$AnimationDesc0;
                return animationController$AnimationDesc0;
            }
            AnimationDesc animationController$AnimationDesc1 = this.previous;
            if(animationController$AnimationDesc1 != null) {
                this.removeAnimation(animationController$AnimationDesc1.animation);
                this.animationPool.free(this.previous);
            }
            this.previous = this.current;
            this.current = animationController$AnimationDesc0;
            this.transitionCurrentTime = 0.0f;
            this.transitionTargetTime = f;
            return animationController$AnimationDesc0;
        }
        this.current = animationController$AnimationDesc0;
        return animationController$AnimationDesc0;
    }

    public AnimationDesc animate(String s, float f) {
        return this.animate(s, 1, 1.0f, null, f);
    }

    public AnimationDesc animate(String s, float f, float f1, int v, float f2, AnimationListener animationController$AnimationListener0, float f3) {
        return this.animate(this.obtain(s, f, f1, v, f2, animationController$AnimationListener0), f3);
    }

    public AnimationDesc animate(String s, int v, float f, AnimationListener animationController$AnimationListener0, float f1) {
        return this.animate(s, 0.0f, -1.0f, v, f, animationController$AnimationListener0, f1);
    }

    public AnimationDesc animate(String s, int v, AnimationListener animationController$AnimationListener0, float f) {
        return this.animate(s, v, 1.0f, animationController$AnimationListener0, f);
    }

    public AnimationDesc animate(String s, AnimationListener animationController$AnimationListener0, float f) {
        return this.animate(s, 1, 1.0f, animationController$AnimationListener0, f);
    }

    private AnimationDesc obtain(Animation animation0, float f, float f1, int v, float f2, AnimationListener animationController$AnimationListener0) {
        if(animation0 == null) {
            return null;
        }
        AnimationDesc animationController$AnimationDesc0 = (AnimationDesc)this.animationPool.obtain();
        animationController$AnimationDesc0.animation = animation0;
        animationController$AnimationDesc0.listener = animationController$AnimationListener0;
        animationController$AnimationDesc0.loopCount = v;
        animationController$AnimationDesc0.speed = f2;
        animationController$AnimationDesc0.offset = f;
        float f3 = 0.0f;
        if(f1 < 0.0f) {
            f1 = animation0.duration - f;
        }
        animationController$AnimationDesc0.duration = f1;
        if(f2 < 0.0f) {
            f3 = animationController$AnimationDesc0.duration;
        }
        animationController$AnimationDesc0.time = f3;
        return animationController$AnimationDesc0;
    }

    private AnimationDesc obtain(AnimationDesc animationController$AnimationDesc0) {
        return this.obtain(animationController$AnimationDesc0.animation, animationController$AnimationDesc0.offset, animationController$AnimationDesc0.duration, animationController$AnimationDesc0.loopCount, animationController$AnimationDesc0.speed, animationController$AnimationDesc0.listener);
    }

    private AnimationDesc obtain(String s, float f, float f1, int v, float f2, AnimationListener animationController$AnimationListener0) {
        if(s == null) {
            return null;
        }
        Animation animation0 = this.target.getAnimation(s);
        if(animation0 == null) {
            throw new GdxRuntimeException("Unknown animation: " + s);
        }
        return this.obtain(animation0, f, f1, v, f2, animationController$AnimationListener0);
    }

    protected AnimationDesc queue(Animation animation0, float f, float f1, int v, float f2, AnimationListener animationController$AnimationListener0, float f3) {
        return this.queue(this.obtain(animation0, f, f1, v, f2, animationController$AnimationListener0), f3);
    }

    protected AnimationDesc queue(AnimationDesc animationController$AnimationDesc0, float f) {
        if(this.current == null || this.current.loopCount == 0) {
            this.animate(animationController$AnimationDesc0, f);
        }
        else {
            AnimationDesc animationController$AnimationDesc1 = this.queued;
            if(animationController$AnimationDesc1 != null) {
                this.animationPool.free(animationController$AnimationDesc1);
            }
            this.queued = animationController$AnimationDesc0;
            this.queuedTransitionTime = f;
            if(this.current.loopCount < 0) {
                this.current.loopCount = 1;
                return animationController$AnimationDesc0;
            }
        }
        return animationController$AnimationDesc0;
    }

    public AnimationDesc queue(String s, float f, float f1, int v, float f2, AnimationListener animationController$AnimationListener0, float f3) {
        return this.queue(this.obtain(s, f, f1, v, f2, animationController$AnimationListener0), f3);
    }

    public AnimationDesc queue(String s, int v, float f, AnimationListener animationController$AnimationListener0, float f1) {
        return this.queue(s, 0.0f, -1.0f, v, f, animationController$AnimationListener0, f1);
    }

    protected AnimationDesc setAnimation(Animation animation0, float f, float f1, int v, float f2, AnimationListener animationController$AnimationListener0) {
        return this.setAnimation(this.obtain(animation0, f, f1, v, f2, animationController$AnimationListener0));
    }

    protected AnimationDesc setAnimation(AnimationDesc animationController$AnimationDesc0) {
        AnimationDesc animationController$AnimationDesc1 = this.current;
        if(animationController$AnimationDesc1 != null) {
            if(this.allowSameAnimation || animationController$AnimationDesc0 == null || animationController$AnimationDesc1.animation != animationController$AnimationDesc0.animation) {
                this.removeAnimation(this.current.animation);
            }
            else {
                animationController$AnimationDesc0.time = this.current.time;
            }
            this.animationPool.free(this.current);
        }
        this.current = animationController$AnimationDesc0;
        this.justChangedAnimation = true;
        return animationController$AnimationDesc0;
    }

    public AnimationDesc setAnimation(String s) {
        return this.setAnimation(s, 1, 1.0f, null);
    }

    public AnimationDesc setAnimation(String s, float f, float f1, int v, float f2, AnimationListener animationController$AnimationListener0) {
        return this.setAnimation(this.obtain(s, f, f1, v, f2, animationController$AnimationListener0));
    }

    public AnimationDesc setAnimation(String s, int v) {
        return this.setAnimation(s, v, 1.0f, null);
    }

    public AnimationDesc setAnimation(String s, int v, float f, AnimationListener animationController$AnimationListener0) {
        return this.setAnimation(s, 0.0f, -1.0f, v, f, animationController$AnimationListener0);
    }

    public AnimationDesc setAnimation(String s, int v, AnimationListener animationController$AnimationListener0) {
        return this.setAnimation(s, v, 1.0f, animationController$AnimationListener0);
    }

    public AnimationDesc setAnimation(String s, AnimationListener animationController$AnimationListener0) {
        return this.setAnimation(s, 1, 1.0f, animationController$AnimationListener0);
    }

    public void update(float f) {
        if(this.paused) {
            return;
        }
        AnimationDesc animationController$AnimationDesc0 = this.previous;
        if(animationController$AnimationDesc0 != null) {
            float f1 = this.transitionCurrentTime + f;
            this.transitionCurrentTime = f1;
            if(f1 >= this.transitionTargetTime) {
                this.removeAnimation(animationController$AnimationDesc0.animation);
                this.justChangedAnimation = true;
                this.animationPool.free(this.previous);
                this.previous = null;
            }
        }
        if(this.justChangedAnimation) {
            this.target.calculateTransforms();
            this.justChangedAnimation = false;
        }
        if(this.current != null && this.current.loopCount != 0 && this.current.animation != null) {
            float f2 = this.current.update(f);
            if(f2 >= 0.0f) {
                AnimationDesc animationController$AnimationDesc1 = this.queued;
                if(animationController$AnimationDesc1 != null) {
                    this.inAction = false;
                    this.animate(animationController$AnimationDesc1, this.queuedTransitionTime);
                    this.queued = null;
                    if(f2 > 0.0f) {
                        this.update(f2);
                    }
                    return;
                }
            }
            AnimationDesc animationController$AnimationDesc2 = this.previous;
            if(animationController$AnimationDesc2 != null) {
                this.applyAnimations(animationController$AnimationDesc2.animation, this.previous.offset + this.previous.time, this.current.animation, this.current.offset + this.current.time, this.transitionCurrentTime / this.transitionTargetTime);
                return;
            }
            this.applyAnimation(this.current.animation, this.current.offset + this.current.time);
        }
    }
}

