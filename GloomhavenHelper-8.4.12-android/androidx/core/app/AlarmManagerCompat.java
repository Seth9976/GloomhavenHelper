package androidx.core.app;

import android.app.AlarmManager.AlarmClockInfo;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Build.VERSION;
import androidx.annotation.NonNull;

public final class AlarmManagerCompat {
    public static void setAlarmClock(@NonNull AlarmManager alarmManager0, long v, @NonNull PendingIntent pendingIntent0, @NonNull PendingIntent pendingIntent1) {
        if(Build.VERSION.SDK_INT >= 21) {
            alarmManager0.setAlarmClock(new AlarmManager.AlarmClockInfo(v, pendingIntent0), pendingIntent1);
            return;
        }
        AlarmManagerCompat.setExact(alarmManager0, 0, v, pendingIntent1);
    }

    public static void setAndAllowWhileIdle(@NonNull AlarmManager alarmManager0, int v, long v1, @NonNull PendingIntent pendingIntent0) {
        if(Build.VERSION.SDK_INT >= 23) {
            alarmManager0.setAndAllowWhileIdle(v, v1, pendingIntent0);
            return;
        }
        alarmManager0.set(v, v1, pendingIntent0);
    }

    public static void setExact(@NonNull AlarmManager alarmManager0, int v, long v1, @NonNull PendingIntent pendingIntent0) {
        if(Build.VERSION.SDK_INT >= 19) {
            alarmManager0.setExact(v, v1, pendingIntent0);
            return;
        }
        alarmManager0.set(v, v1, pendingIntent0);
    }

    public static void setExactAndAllowWhileIdle(@NonNull AlarmManager alarmManager0, int v, long v1, @NonNull PendingIntent pendingIntent0) {
        if(Build.VERSION.SDK_INT >= 23) {
            alarmManager0.setExactAndAllowWhileIdle(v, v1, pendingIntent0);
            return;
        }
        AlarmManagerCompat.setExact(alarmManager0, v, v1, pendingIntent0);
    }
}

