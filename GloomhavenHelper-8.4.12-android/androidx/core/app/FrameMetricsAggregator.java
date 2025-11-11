package androidx.core.app;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.SparseIntArray;
import android.view.FrameMetrics;
import android.view.Window.OnFrameMetricsAvailableListener;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FrameMetricsAggregator {
    @RequiresApi(24)
    static class FrameMetricsApi24Impl extends FrameMetricsBaseImpl {
        private static final int NANOS_PER_MS = 1000000;
        private static final int NANOS_ROUNDING_VALUE = 500000;
        private ArrayList mActivities;
        Window.OnFrameMetricsAvailableListener mListener;
        SparseIntArray[] mMetrics;
        int mTrackingFlags;
        private static Handler sHandler;
        private static HandlerThread sHandlerThread;

        static {
        }

        FrameMetricsApi24Impl(int v) {
            this.mMetrics = new SparseIntArray[9];
            this.mActivities = new ArrayList();
            this.mListener = new Window.OnFrameMetricsAvailableListener() {
                @Override  // android.view.Window$OnFrameMetricsAvailableListener
                public void onFrameMetricsAvailable(Window window0, FrameMetrics frameMetrics0, int v) {
                    if((FrameMetricsApi24Impl.this.mTrackingFlags & 1) != 0) {
                        SparseIntArray sparseIntArray0 = FrameMetricsApi24Impl.this.mMetrics[0];
                        long v1 = frameMetrics0.getMetric(8);
                        FrameMetricsApi24Impl.this.addDurationItem(sparseIntArray0, v1);
                    }
                    if((FrameMetricsApi24Impl.this.mTrackingFlags & 2) != 0) {
                        SparseIntArray sparseIntArray1 = FrameMetricsApi24Impl.this.mMetrics[1];
                        long v2 = frameMetrics0.getMetric(1);
                        FrameMetricsApi24Impl.this.addDurationItem(sparseIntArray1, v2);
                    }
                    if((FrameMetricsApi24Impl.this.mTrackingFlags & 4) != 0) {
                        SparseIntArray sparseIntArray2 = FrameMetricsApi24Impl.this.mMetrics[2];
                        long v3 = frameMetrics0.getMetric(3);
                        FrameMetricsApi24Impl.this.addDurationItem(sparseIntArray2, v3);
                    }
                    if((FrameMetricsApi24Impl.this.mTrackingFlags & 8) != 0) {
                        SparseIntArray sparseIntArray3 = FrameMetricsApi24Impl.this.mMetrics[3];
                        long v4 = frameMetrics0.getMetric(4);
                        FrameMetricsApi24Impl.this.addDurationItem(sparseIntArray3, v4);
                    }
                    if((FrameMetricsApi24Impl.this.mTrackingFlags & 16) != 0) {
                        SparseIntArray sparseIntArray4 = FrameMetricsApi24Impl.this.mMetrics[4];
                        long v5 = frameMetrics0.getMetric(5);
                        FrameMetricsApi24Impl.this.addDurationItem(sparseIntArray4, v5);
                    }
                    if((FrameMetricsApi24Impl.this.mTrackingFlags & 0x40) != 0) {
                        SparseIntArray sparseIntArray5 = FrameMetricsApi24Impl.this.mMetrics[6];
                        long v6 = frameMetrics0.getMetric(7);
                        FrameMetricsApi24Impl.this.addDurationItem(sparseIntArray5, v6);
                    }
                    if((FrameMetricsApi24Impl.this.mTrackingFlags & 0x20) != 0) {
                        SparseIntArray sparseIntArray6 = FrameMetricsApi24Impl.this.mMetrics[5];
                        long v7 = frameMetrics0.getMetric(6);
                        FrameMetricsApi24Impl.this.addDurationItem(sparseIntArray6, v7);
                    }
                    if((FrameMetricsApi24Impl.this.mTrackingFlags & 0x80) != 0) {
                        SparseIntArray sparseIntArray7 = FrameMetricsApi24Impl.this.mMetrics[7];
                        long v8 = frameMetrics0.getMetric(0);
                        FrameMetricsApi24Impl.this.addDurationItem(sparseIntArray7, v8);
                    }
                    if((FrameMetricsApi24Impl.this.mTrackingFlags & 0x100) != 0) {
                        SparseIntArray sparseIntArray8 = FrameMetricsApi24Impl.this.mMetrics[8];
                        long v9 = frameMetrics0.getMetric(2);
                        FrameMetricsApi24Impl.this.addDurationItem(sparseIntArray8, v9);
                    }
                }
            };
            this.mTrackingFlags = v;
        }

        @Override  // androidx.core.app.FrameMetricsAggregator$FrameMetricsBaseImpl
        public void add(Activity activity0) {
            if(FrameMetricsApi24Impl.sHandlerThread == null) {
                FrameMetricsApi24Impl.sHandlerThread = new HandlerThread("FrameMetricsAggregator");
                FrameMetricsApi24Impl.sHandlerThread.start();
                FrameMetricsApi24Impl.sHandler = new Handler(FrameMetricsApi24Impl.sHandlerThread.getLooper());
            }
            for(int v = 0; v <= 8; ++v) {
                SparseIntArray[] arr_sparseIntArray = this.mMetrics;
                if(arr_sparseIntArray[v] == null && (this.mTrackingFlags & 1 << v) != 0) {
                    arr_sparseIntArray[v] = new SparseIntArray();
                }
            }
            activity0.getWindow().addOnFrameMetricsAvailableListener(this.mListener, FrameMetricsApi24Impl.sHandler);
            this.mActivities.add(new WeakReference(activity0));
        }

        void addDurationItem(SparseIntArray sparseIntArray0, long v) {
            if(sparseIntArray0 != null) {
                int v1 = (int)((v + 500000L) / 1000000L);
                if(v >= 0L) {
                    sparseIntArray0.put(v1, sparseIntArray0.get(v1) + 1);
                }
            }
        }

        @Override  // androidx.core.app.FrameMetricsAggregator$FrameMetricsBaseImpl
        public SparseIntArray[] getMetrics() {
            return this.mMetrics;
        }

        @Override  // androidx.core.app.FrameMetricsAggregator$FrameMetricsBaseImpl
        public SparseIntArray[] remove(Activity activity0) {
            for(Object object0: this.mActivities) {
                WeakReference weakReference0 = (WeakReference)object0;
                if(weakReference0.get() == activity0) {
                    this.mActivities.remove(weakReference0);
                    break;
                }
                if(false) {
                    break;
                }
            }
            activity0.getWindow().removeOnFrameMetricsAvailableListener(this.mListener);
            return this.mMetrics;
        }

        @Override  // androidx.core.app.FrameMetricsAggregator$FrameMetricsBaseImpl
        public SparseIntArray[] reset() {
            SparseIntArray[] arr_sparseIntArray = this.mMetrics;
            this.mMetrics = new SparseIntArray[9];
            return arr_sparseIntArray;
        }

        @Override  // androidx.core.app.FrameMetricsAggregator$FrameMetricsBaseImpl
        public SparseIntArray[] stop() {
            for(int v = this.mActivities.size() - 1; v >= 0; --v) {
                WeakReference weakReference0 = (WeakReference)this.mActivities.get(v);
                Activity activity0 = (Activity)weakReference0.get();
                if(weakReference0.get() != null) {
                    activity0.getWindow().removeOnFrameMetricsAvailableListener(this.mListener);
                    this.mActivities.remove(v);
                }
            }
            return this.mMetrics;
        }
    }

    static class FrameMetricsBaseImpl {
        public void add(Activity activity0) {
        }

        public SparseIntArray[] getMetrics() {
            return null;
        }

        public SparseIntArray[] remove(Activity activity0) {
            return null;
        }

        public SparseIntArray[] reset() {
            return null;
        }

        public SparseIntArray[] stop() {
            return null;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MetricType {
    }

    public static final int ANIMATION_DURATION = 0x100;
    public static final int ANIMATION_INDEX = 8;
    public static final int COMMAND_DURATION = 0x20;
    public static final int COMMAND_INDEX = 5;
    private static final boolean DBG = false;
    public static final int DELAY_DURATION = 0x80;
    public static final int DELAY_INDEX = 7;
    public static final int DRAW_DURATION = 8;
    public static final int DRAW_INDEX = 3;
    public static final int EVERY_DURATION = 0x1FF;
    public static final int INPUT_DURATION = 2;
    public static final int INPUT_INDEX = 1;
    private static final int LAST_INDEX = 8;
    public static final int LAYOUT_MEASURE_DURATION = 4;
    public static final int LAYOUT_MEASURE_INDEX = 2;
    public static final int SWAP_DURATION = 0x40;
    public static final int SWAP_INDEX = 6;
    public static final int SYNC_DURATION = 16;
    public static final int SYNC_INDEX = 4;
    private static final String TAG = "FrameMetrics";
    public static final int TOTAL_DURATION = 1;
    public static final int TOTAL_INDEX;
    private FrameMetricsBaseImpl mInstance;

    public FrameMetricsAggregator() {
        this(1);
    }

    public FrameMetricsAggregator(int v) {
        if(Build.VERSION.SDK_INT >= 24) {
            this.mInstance = new FrameMetricsApi24Impl(v);
            return;
        }
        this.mInstance = new FrameMetricsBaseImpl();
    }

    public void add(@NonNull Activity activity0) {
        this.mInstance.add(activity0);
    }

    @Nullable
    public SparseIntArray[] getMetrics() {
        return this.mInstance.getMetrics();
    }

    @Nullable
    public SparseIntArray[] remove(@NonNull Activity activity0) {
        return this.mInstance.remove(activity0);
    }

    @Nullable
    public SparseIntArray[] reset() {
        return this.mInstance.reset();
    }

    @Nullable
    public SparseIntArray[] stop() {
        return this.mInstance.stop();
    }
}

