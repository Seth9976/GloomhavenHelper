package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodeAnimation;
import com.badlogic.gdx.graphics.g3d.model.NodeKeyframe;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap.Entry;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pool;

public class BaseAnimationController {
    public static final class Transform implements Poolable {
        public final Quaternion rotation;
        public final Vector3 scale;
        public final Vector3 translation;

        public Transform() {
            this.translation = new Vector3();
            this.rotation = new Quaternion();
            this.scale = new Vector3(1.0f, 1.0f, 1.0f);
        }

        public Transform idt() {
            this.translation.set(0.0f, 0.0f, 0.0f);
            this.rotation.idt();
            this.scale.set(1.0f, 1.0f, 1.0f);
            return this;
        }

        public Transform lerp(Transform baseAnimationController$Transform0, float f) {
            return this.lerp(baseAnimationController$Transform0.translation, baseAnimationController$Transform0.rotation, baseAnimationController$Transform0.scale, f);
        }

        public Transform lerp(Vector3 vector30, Quaternion quaternion0, Vector3 vector31, float f) {
            this.translation.lerp(vector30, f);
            this.rotation.slerp(quaternion0, f);
            this.scale.lerp(vector31, f);
            return this;
        }

        @Override  // com.badlogic.gdx.utils.Pool$Poolable
        public void reset() {
            this.idt();
        }

        public Transform set(Transform baseAnimationController$Transform0) {
            return this.set(baseAnimationController$Transform0.translation, baseAnimationController$Transform0.rotation, baseAnimationController$Transform0.scale);
        }

        public Transform set(Vector3 vector30, Quaternion quaternion0, Vector3 vector31) {
            this.translation.set(vector30);
            this.rotation.set(quaternion0);
            this.scale.set(vector31);
            return this;
        }

        public Matrix4 toMatrix4(Matrix4 matrix40) {
            return matrix40.set(this.translation, this.rotation, this.scale);
        }

        // 去混淆评级： 中等(140)
        @Override
        public String toString() {
            return "(0.0,0.0,0.0) - [0.0|0.0|0.0|1.0] - (0.0,0.0,0.0)";
        }
    }

    private boolean applying;
    public final ModelInstance target;
    private static final Transform tmpT;
    private final Pool transformPool;
    private static final ObjectMap transforms;

    static {
        BaseAnimationController.transforms = new ObjectMap();
        BaseAnimationController.tmpT = new Transform();
    }

    public BaseAnimationController(ModelInstance modelInstance0) {
        this.transformPool = new Pool() {
            protected Transform newObject() {
                return new Transform();
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return this.newObject();
            }
        };
        this.applying = false;
        this.target = modelInstance0;
    }

    protected void apply(Animation animation0, float f, float f1) {
        if(!this.applying) {
            throw new GdxRuntimeException("You must call begin() before adding an animation");
        }
        BaseAnimationController.applyAnimation(BaseAnimationController.transforms, this.transformPool, f1, animation0, f);
    }

    protected static void applyAnimation(ObjectMap objectMap0, Pool pool0, float f, Animation animation0, float f1) {
        if(objectMap0 == null) {
            for(Object object0: animation0.nodeAnimations) {
                BaseAnimationController.applyNodeAnimationDirectly(((NodeAnimation)object0), f1);
            }
            return;
        }
        for(Object object1: objectMap0.keys()) {
            ((Node)object1).isAnimated = false;
        }
        for(Object object2: animation0.nodeAnimations) {
            BaseAnimationController.applyNodeAnimationBlending(((NodeAnimation)object2), objectMap0, pool0, f, f1);
        }
        for(Object object3: objectMap0.entries()) {
            Entry objectMap$Entry0 = (Entry)object3;
            if(!((Node)objectMap$Entry0.key).isAnimated) {
                ((Node)objectMap$Entry0.key).isAnimated = true;
                ((Transform)objectMap$Entry0.value).lerp(((Node)objectMap$Entry0.key).translation, ((Node)objectMap$Entry0.key).rotation, ((Node)objectMap$Entry0.key).scale, f);
            }
        }
    }

    protected void applyAnimation(Animation animation0, float f) {
        if(this.applying) {
            throw new GdxRuntimeException("Call end() first");
        }
        BaseAnimationController.applyAnimation(null, null, 1.0f, animation0, f);
        this.target.calculateTransforms();
    }

    protected void applyAnimations(Animation animation0, float f, Animation animation1, float f1, float f2) {
        if(animation1 != null && f2 != 0.0f) {
            if(animation0 != null && f2 != 1.0f) {
                if(this.applying) {
                    throw new GdxRuntimeException("Call end() first");
                }
                this.begin();
                this.apply(animation0, f, 1.0f);
                this.apply(animation1, f1, f2);
                this.end();
                return;
            }
            this.applyAnimation(animation1, f1);
            return;
        }
        this.applyAnimation(animation0, f);
    }

    private static final void applyNodeAnimationBlending(NodeAnimation nodeAnimation0, ObjectMap objectMap0, Pool pool0, float f, float f1) {
        Node node0 = nodeAnimation0.node;
        node0.isAnimated = true;
        Transform baseAnimationController$Transform0 = BaseAnimationController.getNodeAnimationTransform(nodeAnimation0, f1);
        Transform baseAnimationController$Transform1 = (Transform)objectMap0.get(node0, null);
        if(baseAnimationController$Transform1 != null) {
            if(f > 0.999999f) {
                baseAnimationController$Transform1.set(baseAnimationController$Transform0);
                return;
            }
            baseAnimationController$Transform1.lerp(baseAnimationController$Transform0, f);
            return;
        }
        if(f > 0.999999f) {
            objectMap0.put(node0, ((Transform)pool0.obtain()).set(baseAnimationController$Transform0));
            return;
        }
        objectMap0.put(node0, ((Transform)pool0.obtain()).set(node0.translation, node0.rotation, node0.scale).lerp(baseAnimationController$Transform0, f));
    }

    private static final void applyNodeAnimationDirectly(NodeAnimation nodeAnimation0, float f) {
        Node node0 = nodeAnimation0.node;
        node0.isAnimated = true;
        BaseAnimationController.getNodeAnimationTransform(nodeAnimation0, f).toMatrix4(node0.localTransform);
    }

    protected void begin() {
        if(this.applying) {
            throw new GdxRuntimeException("You must call end() after each call to being()");
        }
        this.applying = true;
    }

    protected void end() {
        if(!this.applying) {
            throw new GdxRuntimeException("You must call begin() first");
        }
        for(Object object0: BaseAnimationController.transforms.entries()) {
            ((Transform)((Entry)object0).value).toMatrix4(((Node)((Entry)object0).key).localTransform);
            this.transformPool.free(((Entry)object0).value);
        }
        BaseAnimationController.transforms.clear();
        this.target.calculateTransforms();
        this.applying = false;
    }

    static final int getFirstKeyframeIndexAtTime(Array array0, float f) {
        int v = array0.size - 1;
        int v1 = 0;
        if(v > 0 && f >= ((NodeKeyframe)array0.get(0)).keytime && f <= ((NodeKeyframe)array0.get(v)).keytime) {
            while(v1 < v) {
                int v2 = (v1 + v) / 2;
                int v3 = v2 + 1;
                if(f > ((NodeKeyframe)array0.get(v3)).keytime) {
                    v1 = v3;
                    continue;
                }
                if(f < ((NodeKeyframe)array0.get(v2)).keytime) {
                    v = v2 - 1;
                    continue;
                }
                return v2;
            }
            return v1;
        }
        return 0;
    }

    private static final Transform getNodeAnimationTransform(NodeAnimation nodeAnimation0, float f) {
        BaseAnimationController.getTranslationAtTime(nodeAnimation0, f, BaseAnimationController.tmpT.translation);
        BaseAnimationController.getRotationAtTime(nodeAnimation0, f, BaseAnimationController.tmpT.rotation);
        BaseAnimationController.getScalingAtTime(nodeAnimation0, f, BaseAnimationController.tmpT.scale);
        return BaseAnimationController.tmpT;
    }

    private static final Quaternion getRotationAtTime(NodeAnimation nodeAnimation0, float f, Quaternion quaternion0) {
        if(nodeAnimation0.rotation == null) {
            return quaternion0.set(nodeAnimation0.node.rotation);
        }
        if(nodeAnimation0.rotation.size == 1) {
            return quaternion0.set(((Quaternion)((NodeKeyframe)nodeAnimation0.rotation.get(0)).value));
        }
        int v = BaseAnimationController.getFirstKeyframeIndexAtTime(nodeAnimation0.rotation, f);
        NodeKeyframe nodeKeyframe0 = (NodeKeyframe)nodeAnimation0.rotation.get(v);
        quaternion0.set(((Quaternion)nodeKeyframe0.value));
        if(v + 1 < nodeAnimation0.rotation.size) {
            NodeKeyframe nodeKeyframe1 = (NodeKeyframe)nodeAnimation0.rotation.get(v + 1);
            quaternion0.slerp(((Quaternion)nodeKeyframe1.value), (f - nodeKeyframe0.keytime) / (nodeKeyframe1.keytime - nodeKeyframe0.keytime));
        }
        return quaternion0;
    }

    private static final Vector3 getScalingAtTime(NodeAnimation nodeAnimation0, float f, Vector3 vector30) {
        if(nodeAnimation0.scaling == null) {
            return vector30.set(nodeAnimation0.node.scale);
        }
        if(nodeAnimation0.scaling.size == 1) {
            return vector30.set(((Vector3)((NodeKeyframe)nodeAnimation0.scaling.get(0)).value));
        }
        int v = BaseAnimationController.getFirstKeyframeIndexAtTime(nodeAnimation0.scaling, f);
        NodeKeyframe nodeKeyframe0 = (NodeKeyframe)nodeAnimation0.scaling.get(v);
        vector30.set(((Vector3)nodeKeyframe0.value));
        if(v + 1 < nodeAnimation0.scaling.size) {
            NodeKeyframe nodeKeyframe1 = (NodeKeyframe)nodeAnimation0.scaling.get(v + 1);
            vector30.lerp(((Vector3)nodeKeyframe1.value), (f - nodeKeyframe0.keytime) / (nodeKeyframe1.keytime - nodeKeyframe0.keytime));
        }
        return vector30;
    }

    private static final Vector3 getTranslationAtTime(NodeAnimation nodeAnimation0, float f, Vector3 vector30) {
        if(nodeAnimation0.translation == null) {
            return vector30.set(nodeAnimation0.node.translation);
        }
        if(nodeAnimation0.translation.size == 1) {
            return vector30.set(((Vector3)((NodeKeyframe)nodeAnimation0.translation.get(0)).value));
        }
        int v = BaseAnimationController.getFirstKeyframeIndexAtTime(nodeAnimation0.translation, f);
        NodeKeyframe nodeKeyframe0 = (NodeKeyframe)nodeAnimation0.translation.get(v);
        vector30.set(((Vector3)nodeKeyframe0.value));
        if(v + 1 < nodeAnimation0.translation.size) {
            NodeKeyframe nodeKeyframe1 = (NodeKeyframe)nodeAnimation0.translation.get(v + 1);
            vector30.lerp(((Vector3)nodeKeyframe1.value), (f - nodeKeyframe0.keytime) / (nodeKeyframe1.keytime - nodeKeyframe0.keytime));
        }
        return vector30;
    }

    protected void removeAnimation(Animation animation0) {
        for(Object object0: animation0.nodeAnimations) {
            ((NodeAnimation)object0).node.isAnimated = false;
        }
    }
}

