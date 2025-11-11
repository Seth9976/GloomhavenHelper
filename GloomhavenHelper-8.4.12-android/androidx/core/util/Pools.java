package androidx.core.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class Pools {
    public interface Pool {
        @Nullable
        Object acquire();

        boolean release(@NonNull Object arg1);
    }

    public static class SimplePool implements Pool {
        private final Object[] mPool;
        private int mPoolSize;

        public SimplePool(int v) {
            if(v <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.mPool = new Object[v];
        }

        @Override  // androidx.core.util.Pools$Pool
        public Object acquire() {
            int v = this.mPoolSize;
            if(v > 0) {
                Object object0 = this.mPool[v - 1];
                this.mPool[v - 1] = null;
                this.mPoolSize = v - 1;
                return object0;
            }
            return null;
        }

        private boolean isInPool(@NonNull Object object0) {
            for(int v = 0; v < this.mPoolSize; ++v) {
                if(this.mPool[v] == object0) {
                    return true;
                }
            }
            return false;
        }

        @Override  // androidx.core.util.Pools$Pool
        public boolean release(@NonNull Object object0) {
            if(this.isInPool(object0)) {
                throw new IllegalStateException("Already in the pool!");
            }
            int v = this.mPoolSize;
            Object[] arr_object = this.mPool;
            if(v < arr_object.length) {
                arr_object[v] = object0;
                this.mPoolSize = v + 1;
                return true;
            }
            return false;
        }
    }

    public static class SynchronizedPool extends SimplePool {
        private final Object mLock;

        public SynchronizedPool(int v) {
            super(v);
            this.mLock = new Object();
        }

        @Override  // androidx.core.util.Pools$SimplePool
        public Object acquire() {
            synchronized(this.mLock) {
            }
            return super.acquire();
        }

        @Override  // androidx.core.util.Pools$SimplePool
        public boolean release(@NonNull Object object0) {
            synchronized(this.mLock) {
            }
            return super.release(object0);
        }
    }

}

