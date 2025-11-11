package com.google.android.gms.internal.ads;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class zzag {
    static final class zza {
        public static final boolean zzbl;
        private final List zzbm;
        private boolean zzbn;

        static {
            zza.zzbl = zzag.DEBUG;
        }

        zza() {
            this.zzbm = new ArrayList();
            this.zzbn = false;
        }

        @Override
        protected final void finalize() throws Throwable {
            if(!this.zzbn) {
                this.zzc("Request on the loose");
                zzag.e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }

        public final void zza(String s, long v) {
            synchronized(this) {
                if(!this.zzbn) {
                    zzai zzai0 = new zzai(s, v, SystemClock.elapsedRealtime());
                    this.zzbm.add(zzai0);
                    return;
                }
            }
            throw new IllegalStateException("Marker added to finished log");
        }

        public final void zzc(String s) {
            long v1;
            synchronized(this) {
                this.zzbn = true;
                if(this.zzbm.size() == 0) {
                    v1 = 0L;
                }
                else {
                    long v2 = ((zzai)this.zzbm.get(0)).time;
                    v1 = ((zzai)this.zzbm.get(this.zzbm.size() - 1)).time - v2;
                }
                if(v1 <= 0L) {
                    return;
                }
                long v3 = ((zzai)this.zzbm.get(0)).time;
                zzag.d("(%-4d ms) %s", new Object[]{v1, s});
                for(Object object0: this.zzbm) {
                    zzag.d("(+%-4d) [%2d] %s", new Object[]{((long)(((zzai)object0).time - v3)), ((zzai)object0).zzbo, ((zzai)object0).name});
                    v3 = ((zzai)object0).time;
                }
            }
        }
    }

    private static final String CLASS_NAME = null;
    public static boolean DEBUG = false;
    private static String TAG = "Volley";

    static {
        zzag.DEBUG = Log.isLoggable("Volley", 2);
        zzag.CLASS_NAME = "com.google.android.gms.internal.ads.zzag";
    }

    public static void d(String s, Object[] arr_object) {
        Log.d(zzag.TAG, zzag.zza(s, arr_object));
    }

    public static void e(String s, Object[] arr_object) {
        Log.e(zzag.TAG, zzag.zza(s, arr_object));
    }

    public static void v(String s, Object[] arr_object) {
        if(zzag.DEBUG) {
            Log.v(zzag.TAG, zzag.zza(s, arr_object));
        }
    }

    private static String zza(String s, Object[] arr_object) {
        if(arr_object != null) {
            s = String.format(Locale.US, s, arr_object);
        }
        StackTraceElement[] arr_stackTraceElement = new Throwable().fillInStackTrace().getStackTrace();
        for(int v = 2; v < arr_stackTraceElement.length; ++v) {
            if(!arr_stackTraceElement[v].getClassName().equals(zzag.CLASS_NAME)) {
                String s1 = arr_stackTraceElement[v].getClassName();
                String s2 = s1.substring(s1.lastIndexOf(46) + 1);
                String s3 = s2.substring(s2.lastIndexOf(36) + 1) + "." + arr_stackTraceElement[v].getMethodName();
                return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), s3, s);
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), "<unknown>", s);
    }

    public static void zza(Throwable throwable0, String s, Object[] arr_object) {
        Log.e(zzag.TAG, zzag.zza(s, arr_object), throwable0);
    }
}

