package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.reflect.ArrayReflection;

public class ParallelArray {
    public abstract class Channel {
        public Object data;
        public int id;
        public int strideSize;

        public Channel(int v, Object object0, int v1) {
            this.id = v;
            this.strideSize = v1;
            this.data = object0;
        }

        public abstract void add(int arg1, Object[] arg2);

        protected abstract void setCapacity(int arg1);

        public abstract void swap(int arg1, int arg2);
    }

    public static class ChannelDescriptor {
        public int count;
        public int id;
        public Class type;

        public ChannelDescriptor(int v, Class class0, int v1) {
            this.id = v;
            this.type = class0;
            this.count = v1;
        }
    }

    public interface ChannelInitializer {
        void init(Channel arg1);
    }

    public class FloatChannel extends Channel {
        public float[] data;

        public FloatChannel(int v, int v1, int v2) {
            super(v, new float[v2 * v1], v1);
            this.data = (float[])this.data;
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParallelArray$Channel
        public void add(int v, Object[] arr_object) {
            int v1 = this.strideSize * ParallelArray.this.size;
            int v2 = this.strideSize + v1;
            for(int v3 = 0; v1 < v2; ++v3) {
                this.data[v1] = (float)(((Float)arr_object[v3]));
                ++v1;
            }
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParallelArray$Channel
        public void setCapacity(int v) {
            float[] arr_f = new float[this.strideSize * v];
            System.arraycopy(this.data, 0, arr_f, 0, Math.min(this.data.length, arr_f.length));
            this.data = arr_f;
            this.data = arr_f;
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParallelArray$Channel
        public void swap(int v, int v1) {
            int v2 = this.strideSize * v;
            int v3 = this.strideSize * v1;
            int v4 = this.strideSize + v2;
            while(v2 < v4) {
                float[] arr_f = this.data;
                float f = arr_f[v2];
                arr_f[v2] = arr_f[v3];
                arr_f[v3] = f;
                ++v2;
                ++v3;
            }
        }
    }

    public class IntChannel extends Channel {
        public int[] data;

        public IntChannel(int v, int v1, int v2) {
            super(v, new int[v2 * v1], v1);
            this.data = (int[])this.data;
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParallelArray$Channel
        public void add(int v, Object[] arr_object) {
            int v1 = this.strideSize * ParallelArray.this.size;
            int v2 = this.strideSize + v1;
            for(int v3 = 0; v1 < v2; ++v3) {
                this.data[v1] = (int)(((Integer)arr_object[v3]));
                ++v1;
            }
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParallelArray$Channel
        public void setCapacity(int v) {
            int[] arr_v = new int[this.strideSize * v];
            System.arraycopy(this.data, 0, arr_v, 0, Math.min(this.data.length, arr_v.length));
            this.data = arr_v;
            this.data = arr_v;
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParallelArray$Channel
        public void swap(int v, int v1) {
            int v2 = this.strideSize * v;
            int v3 = this.strideSize * v1;
            int v4 = this.strideSize + v2;
            while(v2 < v4) {
                int[] arr_v = this.data;
                int v5 = arr_v[v2];
                arr_v[v2] = arr_v[v3];
                arr_v[v3] = v5;
                ++v2;
                ++v3;
            }
        }
    }

    public class ObjectChannel extends Channel {
        Class componentType;
        public Object[] data;

        public ObjectChannel(int v, int v1, int v2, Class class0) {
            super(v, ArrayReflection.newInstance(class0, v2 * v1), v1);
            this.componentType = class0;
            this.data = (Object[])this.data;
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParallelArray$Channel
        public void add(int v, Object[] arr_object) {
            int v1 = this.strideSize * ParallelArray.this.size;
            int v2 = this.strideSize + v1;
            for(int v3 = 0; v1 < v2; ++v3) {
                this.data[v1] = arr_object[v3];
                ++v1;
            }
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParallelArray$Channel
        public void setCapacity(int v) {
            Object[] arr_object = (Object[])ArrayReflection.newInstance(this.componentType, this.strideSize * v);
            System.arraycopy(this.data, 0, arr_object, 0, Math.min(this.data.length, arr_object.length));
            this.data = arr_object;
            this.data = arr_object;
        }

        @Override  // com.badlogic.gdx.graphics.g3d.particles.ParallelArray$Channel
        public void swap(int v, int v1) {
            int v2 = this.strideSize * v;
            int v3 = this.strideSize * v1;
            int v4 = this.strideSize + v2;
            while(v2 < v4) {
                Object[] arr_object = this.data;
                Object object0 = arr_object[v2];
                arr_object[v2] = arr_object[v3];
                arr_object[v3] = object0;
                ++v2;
                ++v3;
            }
        }
    }

    Array arrays;
    public int capacity;
    public int size;

    public ParallelArray(int v) {
        this.arrays = new Array(false, 2, Channel.class);
        this.capacity = v;
        this.size = 0;
    }

    public Channel addChannel(ChannelDescriptor parallelArray$ChannelDescriptor0) {
        return this.addChannel(parallelArray$ChannelDescriptor0, null);
    }

    public Channel addChannel(ChannelDescriptor parallelArray$ChannelDescriptor0, ChannelInitializer parallelArray$ChannelInitializer0) {
        Channel parallelArray$Channel0 = this.getChannel(parallelArray$ChannelDescriptor0);
        if(parallelArray$Channel0 == null) {
            parallelArray$Channel0 = this.allocateChannel(parallelArray$ChannelDescriptor0);
            if(parallelArray$ChannelInitializer0 != null) {
                parallelArray$ChannelInitializer0.init(parallelArray$Channel0);
            }
            this.arrays.add(parallelArray$Channel0);
        }
        return parallelArray$Channel0;
    }

    public void addElement(Object[] arr_object) {
        if(this.size == this.capacity) {
            throw new GdxRuntimeException("Capacity reached, cannot add other elements");
        }
        int v = 0;
        for(Object object0: this.arrays) {
            ((Channel)object0).add(v, arr_object);
            v += ((Channel)object0).strideSize;
        }
        ++this.size;
    }

    private Channel allocateChannel(ChannelDescriptor parallelArray$ChannelDescriptor0) {
        if(parallelArray$ChannelDescriptor0.type == Float.TYPE) {
            return new FloatChannel(this, parallelArray$ChannelDescriptor0.id, parallelArray$ChannelDescriptor0.count, this.capacity);
        }
        return parallelArray$ChannelDescriptor0.type == Integer.TYPE ? new IntChannel(this, parallelArray$ChannelDescriptor0.id, parallelArray$ChannelDescriptor0.count, this.capacity) : new ObjectChannel(this, parallelArray$ChannelDescriptor0.id, parallelArray$ChannelDescriptor0.count, this.capacity, parallelArray$ChannelDescriptor0.type);
    }

    public void clear() {
        this.arrays.clear();
        this.size = 0;
    }

    private int findIndex(int v) {
        for(int v1 = 0; v1 < this.arrays.size; ++v1) {
            if(((Channel[])this.arrays.items)[v1].id == v) {
                return v1;
            }
        }
        return -1;
    }

    public Channel getChannel(ChannelDescriptor parallelArray$ChannelDescriptor0) {
        for(Object object0: this.arrays) {
            Channel parallelArray$Channel0 = (Channel)object0;
            if(parallelArray$Channel0.id == parallelArray$ChannelDescriptor0.id) {
                return parallelArray$Channel0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    public void removeArray(int v) {
        this.arrays.removeIndex(this.findIndex(v));
    }

    public void removeElement(int v) {
        int v1 = this.size - 1;
        for(Object object0: this.arrays) {
            ((Channel)object0).swap(v, v1);
        }
        this.size = v1;
    }

    public void setCapacity(int v) {
        if(this.capacity != v) {
            for(Object object0: this.arrays) {
                ((Channel)object0).setCapacity(v);
            }
            this.capacity = v;
        }
    }
}

