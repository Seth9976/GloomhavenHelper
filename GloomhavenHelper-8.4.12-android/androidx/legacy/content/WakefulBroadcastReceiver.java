package androidx.legacy.content;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager.WakeLock;
import android.os.PowerManager;
import android.util.Log;
import android.util.SparseArray;

@Deprecated
public abstract class WakefulBroadcastReceiver extends BroadcastReceiver {
    private static final String EXTRA_WAKE_LOCK_ID = "androidx.contentpager.content.wakelockid";
    private static int mNextId;
    private static final SparseArray sActiveWakeLocks;

    static {
        WakefulBroadcastReceiver.sActiveWakeLocks = new SparseArray();
        WakefulBroadcastReceiver.mNextId = 1;
    }

    public static boolean completeWakefulIntent(Intent intent0) {
        int v = intent0.getIntExtra("androidx.contentpager.content.wakelockid", 0);
        if(v == 0) {
            return false;
        }
        synchronized(WakefulBroadcastReceiver.sActiveWakeLocks) {
            PowerManager.WakeLock powerManager$WakeLock0 = (PowerManager.WakeLock)WakefulBroadcastReceiver.sActiveWakeLocks.get(v);
            if(powerManager$WakeLock0 != null) {
                powerManager$WakeLock0.release();
                WakefulBroadcastReceiver.sActiveWakeLocks.remove(v);
                return true;
            }
            Log.w("WakefulBroadcastReceiv.", "No active wake lock id #" + v);
            return true;
        }
    }

    public static ComponentName startWakefulService(Context context0, Intent intent0) {
        synchronized(WakefulBroadcastReceiver.sActiveWakeLocks) {
            int v1 = WakefulBroadcastReceiver.mNextId;
            ++WakefulBroadcastReceiver.mNextId;
            if(WakefulBroadcastReceiver.mNextId <= 0) {
                WakefulBroadcastReceiver.mNextId = 1;
            }
            intent0.putExtra("androidx.contentpager.content.wakelockid", v1);
            ComponentName componentName0 = context0.startService(intent0);
            if(componentName0 == null) {
                return null;
            }
            PowerManager.WakeLock powerManager$WakeLock0 = ((PowerManager)context0.getSystemService("power")).newWakeLock(1, "androidx.core:wake:" + componentName0.flattenToShortString());
            powerManager$WakeLock0.setReferenceCounted(false);
            powerManager$WakeLock0.acquire(60000L);
            WakefulBroadcastReceiver.sActiveWakeLocks.put(v1, powerManager$WakeLock0);
            return componentName0;
        }
    }
}

