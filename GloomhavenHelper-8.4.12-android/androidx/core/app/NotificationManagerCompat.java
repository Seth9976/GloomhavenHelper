package androidx.core.app;

import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler.Callback;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings.Secure;
import android.support.v4.app.INotificationSideChannel.Stub;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

public final class NotificationManagerCompat {
    static class CancelTask implements Task {
        final boolean all;
        final int id;
        final String packageName;
        final String tag;

        CancelTask(String s) {
            this.packageName = s;
            this.id = 0;
            this.tag = null;
            this.all = true;
        }

        CancelTask(String s, int v, String s1) {
            this.packageName = s;
            this.id = v;
            this.tag = s1;
            this.all = false;
        }

        @Override  // androidx.core.app.NotificationManagerCompat$Task
        public void send(INotificationSideChannel iNotificationSideChannel0) throws RemoteException {
            if(this.all) {
                iNotificationSideChannel0.cancelAll(this.packageName);
                return;
            }
            iNotificationSideChannel0.cancel(this.packageName, this.id, this.tag);
        }

        // 去混淆评级： 低(20)
        @Override
        public String toString() {
            return "CancelTask[packageName:" + this.packageName + ", id:" + this.id + ", tag:" + this.tag + ", all:" + this.all + "]";
        }
    }

    static class NotifyTask implements Task {
        final int id;
        final Notification notif;
        final String packageName;
        final String tag;

        NotifyTask(String s, int v, String s1, Notification notification0) {
            this.packageName = s;
            this.id = v;
            this.tag = s1;
            this.notif = notification0;
        }

        @Override  // androidx.core.app.NotificationManagerCompat$Task
        public void send(INotificationSideChannel iNotificationSideChannel0) throws RemoteException {
            iNotificationSideChannel0.notify(this.packageName, this.id, this.tag, this.notif);
        }

        // 去混淆评级： 低(20)
        @Override
        public String toString() {
            return "NotifyTask[packageName:" + this.packageName + ", id:" + this.id + ", tag:" + this.tag + "]";
        }
    }

    static class ServiceConnectedEvent {
        final ComponentName componentName;
        final IBinder iBinder;

        ServiceConnectedEvent(ComponentName componentName0, IBinder iBinder0) {
            this.componentName = componentName0;
            this.iBinder = iBinder0;
        }
    }

    static class SideChannelManager implements ServiceConnection, Handler.Callback {
        static class ListenerRecord {
            boolean bound;
            final ComponentName componentName;
            int retryCount;
            INotificationSideChannel service;
            ArrayDeque taskQueue;

            ListenerRecord(ComponentName componentName0) {
                this.bound = false;
                this.taskQueue = new ArrayDeque();
                this.retryCount = 0;
                this.componentName = componentName0;
            }
        }

        private static final int MSG_QUEUE_TASK = 0;
        private static final int MSG_RETRY_LISTENER_QUEUE = 3;
        private static final int MSG_SERVICE_CONNECTED = 1;
        private static final int MSG_SERVICE_DISCONNECTED = 2;
        private Set mCachedEnabledPackages;
        private final Context mContext;
        private final Handler mHandler;
        private final HandlerThread mHandlerThread;
        private final Map mRecordMap;

        SideChannelManager(Context context0) {
            this.mRecordMap = new HashMap();
            this.mCachedEnabledPackages = new HashSet();
            this.mContext = context0;
            this.mHandlerThread = new HandlerThread("NotificationManagerCompat");
            this.mHandlerThread.start();
            this.mHandler = new Handler(this.mHandlerThread.getLooper(), this);
        }

        private boolean ensureServiceBound(ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord0) {
            if(notificationManagerCompat$SideChannelManager$ListenerRecord0.bound) {
                return true;
            }
            Intent intent0 = new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(notificationManagerCompat$SideChannelManager$ListenerRecord0.componentName);
            notificationManagerCompat$SideChannelManager$ListenerRecord0.bound = this.mContext.bindService(intent0, this, 33);
            if(notificationManagerCompat$SideChannelManager$ListenerRecord0.bound) {
                notificationManagerCompat$SideChannelManager$ListenerRecord0.retryCount = 0;
                return notificationManagerCompat$SideChannelManager$ListenerRecord0.bound;
            }
            Log.w("NotifManCompat", "Unable to bind to listener " + notificationManagerCompat$SideChannelManager$ListenerRecord0.componentName);
            this.mContext.unbindService(this);
            return notificationManagerCompat$SideChannelManager$ListenerRecord0.bound;
        }

        private void ensureServiceUnbound(ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord0) {
            if(notificationManagerCompat$SideChannelManager$ListenerRecord0.bound) {
                this.mContext.unbindService(this);
                notificationManagerCompat$SideChannelManager$ListenerRecord0.bound = false;
            }
            notificationManagerCompat$SideChannelManager$ListenerRecord0.service = null;
        }

        @Override  // android.os.Handler$Callback
        public boolean handleMessage(Message message0) {
            switch(message0.what) {
                case 0: {
                    this.handleQueueTask(((Task)message0.obj));
                    return true;
                }
                case 1: {
                    this.handleServiceConnected(((ServiceConnectedEvent)message0.obj).componentName, ((ServiceConnectedEvent)message0.obj).iBinder);
                    return true;
                }
                case 2: {
                    this.handleServiceDisconnected(((ComponentName)message0.obj));
                    return true;
                }
                case 3: {
                    this.handleRetryListenerQueue(((ComponentName)message0.obj));
                    return true;
                }
                default: {
                    return false;
                }
            }
        }

        private void handleQueueTask(Task notificationManagerCompat$Task0) {
            this.updateListenerMap();
            for(Object object0: this.mRecordMap.values()) {
                ((ListenerRecord)object0).taskQueue.add(notificationManagerCompat$Task0);
                this.processListenerQueue(((ListenerRecord)object0));
            }
        }

        private void handleRetryListenerQueue(ComponentName componentName0) {
            ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord0 = (ListenerRecord)this.mRecordMap.get(componentName0);
            if(notificationManagerCompat$SideChannelManager$ListenerRecord0 != null) {
                this.processListenerQueue(notificationManagerCompat$SideChannelManager$ListenerRecord0);
            }
        }

        private void handleServiceConnected(ComponentName componentName0, IBinder iBinder0) {
            ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord0 = (ListenerRecord)this.mRecordMap.get(componentName0);
            if(notificationManagerCompat$SideChannelManager$ListenerRecord0 != null) {
                notificationManagerCompat$SideChannelManager$ListenerRecord0.service = Stub.asInterface(iBinder0);
                notificationManagerCompat$SideChannelManager$ListenerRecord0.retryCount = 0;
                this.processListenerQueue(notificationManagerCompat$SideChannelManager$ListenerRecord0);
            }
        }

        private void handleServiceDisconnected(ComponentName componentName0) {
            ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord0 = (ListenerRecord)this.mRecordMap.get(componentName0);
            if(notificationManagerCompat$SideChannelManager$ListenerRecord0 != null) {
                this.ensureServiceUnbound(notificationManagerCompat$SideChannelManager$ListenerRecord0);
            }
        }

        @Override  // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
            if(Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Connected to service " + componentName0);
            }
            ServiceConnectedEvent notificationManagerCompat$ServiceConnectedEvent0 = new ServiceConnectedEvent(componentName0, iBinder0);
            this.mHandler.obtainMessage(1, notificationManagerCompat$ServiceConnectedEvent0).sendToTarget();
        }

        @Override  // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName0) {
            if(Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Disconnected from service " + componentName0);
            }
            this.mHandler.obtainMessage(2, componentName0).sendToTarget();
        }

        private void processListenerQueue(ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord0) {
            if(Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Processing component " + notificationManagerCompat$SideChannelManager$ListenerRecord0.componentName + ", " + notificationManagerCompat$SideChannelManager$ListenerRecord0.taskQueue.size() + " queued tasks");
            }
            if(notificationManagerCompat$SideChannelManager$ListenerRecord0.taskQueue.isEmpty()) {
                return;
            }
            if(this.ensureServiceBound(notificationManagerCompat$SideChannelManager$ListenerRecord0) && notificationManagerCompat$SideChannelManager$ListenerRecord0.service != null) {
                Task notificationManagerCompat$Task0;
                while((notificationManagerCompat$Task0 = (Task)notificationManagerCompat$SideChannelManager$ListenerRecord0.taskQueue.peek()) != null) {
                    try {
                        if(Log.isLoggable("NotifManCompat", 3)) {
                            Log.d("NotifManCompat", "Sending task " + notificationManagerCompat$Task0);
                        }
                        notificationManagerCompat$Task0.send(notificationManagerCompat$SideChannelManager$ListenerRecord0.service);
                        notificationManagerCompat$SideChannelManager$ListenerRecord0.taskQueue.remove();
                    }
                    catch(DeadObjectException unused_ex) {
                        if(!Log.isLoggable("NotifManCompat", 3)) {
                            break;
                        }
                        Log.d("NotifManCompat", "Remote service has died: " + notificationManagerCompat$SideChannelManager$ListenerRecord0.componentName);
                        break;
                    }
                    catch(RemoteException remoteException0) {
                        Log.w("NotifManCompat", "RemoteException communicating with " + notificationManagerCompat$SideChannelManager$ListenerRecord0.componentName, remoteException0);
                        break;
                    }
                }
                if(!notificationManagerCompat$SideChannelManager$ListenerRecord0.taskQueue.isEmpty()) {
                    this.scheduleListenerRetry(notificationManagerCompat$SideChannelManager$ListenerRecord0);
                }
                return;
            }
            this.scheduleListenerRetry(notificationManagerCompat$SideChannelManager$ListenerRecord0);
        }

        public void queueTask(Task notificationManagerCompat$Task0) {
            this.mHandler.obtainMessage(0, notificationManagerCompat$Task0).sendToTarget();
        }

        private void scheduleListenerRetry(ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord0) {
            if(this.mHandler.hasMessages(3, notificationManagerCompat$SideChannelManager$ListenerRecord0.componentName)) {
                return;
            }
            ++notificationManagerCompat$SideChannelManager$ListenerRecord0.retryCount;
            if(notificationManagerCompat$SideChannelManager$ListenerRecord0.retryCount > 6) {
                Log.w("NotifManCompat", "Giving up on delivering " + notificationManagerCompat$SideChannelManager$ListenerRecord0.taskQueue.size() + " tasks to " + notificationManagerCompat$SideChannelManager$ListenerRecord0.componentName + " after " + notificationManagerCompat$SideChannelManager$ListenerRecord0.retryCount + " retries");
                notificationManagerCompat$SideChannelManager$ListenerRecord0.taskQueue.clear();
                return;
            }
            int v = (1 << notificationManagerCompat$SideChannelManager$ListenerRecord0.retryCount - 1) * 1000;
            if(Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Scheduling retry for " + v + " ms");
            }
            Message message0 = this.mHandler.obtainMessage(3, notificationManagerCompat$SideChannelManager$ListenerRecord0.componentName);
            this.mHandler.sendMessageDelayed(message0, ((long)v));
        }

        private void updateListenerMap() {
            Set set0 = NotificationManagerCompat.getEnabledListenerPackages(this.mContext);
            if(set0.equals(this.mCachedEnabledPackages)) {
                return;
            }
            this.mCachedEnabledPackages = set0;
            List list0 = this.mContext.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
            HashSet hashSet0 = new HashSet();
            for(Object object0: list0) {
                ResolveInfo resolveInfo0 = (ResolveInfo)object0;
                if(set0.contains(resolveInfo0.serviceInfo.packageName)) {
                    ComponentName componentName0 = new ComponentName(resolveInfo0.serviceInfo.packageName, resolveInfo0.serviceInfo.name);
                    if(resolveInfo0.serviceInfo.permission == null) {
                        hashSet0.add(componentName0);
                    }
                    else {
                        Log.w("NotifManCompat", "Permission present on component " + componentName0 + ", not adding listener record.");
                    }
                }
            }
            for(Object object1: hashSet0) {
                ComponentName componentName1 = (ComponentName)object1;
                if(!this.mRecordMap.containsKey(componentName1)) {
                    if(Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Adding listener record for " + componentName1);
                    }
                    ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord0 = new ListenerRecord(componentName1);
                    this.mRecordMap.put(componentName1, notificationManagerCompat$SideChannelManager$ListenerRecord0);
                }
            }
            Iterator iterator2 = this.mRecordMap.entrySet().iterator();
            while(iterator2.hasNext()) {
                Object object2 = iterator2.next();
                Map.Entry map$Entry0 = (Map.Entry)object2;
                if(!hashSet0.contains(map$Entry0.getKey())) {
                    if(Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Removing listener record for " + map$Entry0.getKey());
                    }
                    this.ensureServiceUnbound(((ListenerRecord)map$Entry0.getValue()));
                    iterator2.remove();
                }
            }
        }
    }

    interface Task {
        void send(INotificationSideChannel arg1) throws RemoteException;
    }

    public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_MAX = 5;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;
    static final int MAX_SIDE_CHANNEL_SDK_VERSION = 19;
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    private static final String SETTING_ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final int SIDE_CHANNEL_RETRY_BASE_INTERVAL_MS = 1000;
    private static final int SIDE_CHANNEL_RETRY_MAX_COUNT = 6;
    private static final String TAG = "NotifManCompat";
    private final Context mContext;
    private final NotificationManager mNotificationManager;
    @GuardedBy("sEnabledNotificationListenersLock")
    private static Set sEnabledNotificationListenerPackages;
    @GuardedBy("sEnabledNotificationListenersLock")
    private static String sEnabledNotificationListeners;
    private static final Object sEnabledNotificationListenersLock;
    private static final Object sLock;
    @GuardedBy("sLock")
    private static SideChannelManager sSideChannelManager;

    static {
        NotificationManagerCompat.sEnabledNotificationListenersLock = new Object();
        NotificationManagerCompat.sEnabledNotificationListenerPackages = new HashSet();
        NotificationManagerCompat.sLock = new Object();
    }

    private NotificationManagerCompat(Context context0) {
        this.mContext = context0;
        this.mNotificationManager = (NotificationManager)this.mContext.getSystemService("notification");
    }

    public boolean areNotificationsEnabled() {
        if(Build.VERSION.SDK_INT >= 24) {
            return this.mNotificationManager.areNotificationsEnabled();
        }
        if(Build.VERSION.SDK_INT >= 19) {
            AppOpsManager appOpsManager0 = (AppOpsManager)this.mContext.getSystemService("appops");
            int v = this.mContext.getApplicationInfo().uid;
            try {
                if(((int)(((Integer)AppOpsManager.class.getMethod("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(appOpsManager0, new Object[]{((int)(((Integer)AppOpsManager.class.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)))), v, "com.esotericsoftware.gloomhavenhelper"})))) != 0) {
                    return false;
                }
            }
            catch(ClassNotFoundException | NoSuchMethodException | NoSuchFieldException | InvocationTargetException | IllegalAccessException | RuntimeException unused_ex) {
            }
            return true;
        }
        return true;
    }

    public void cancel(int v) {
        this.cancel(null, v);
    }

    public void cancel(@Nullable String s, int v) {
        this.mNotificationManager.cancel(s, v);
        if(Build.VERSION.SDK_INT <= 19) {
            this.pushSideChannelQueue(new CancelTask("com.esotericsoftware.gloomhavenhelper", v, s));
        }
    }

    public void cancelAll() {
        this.mNotificationManager.cancelAll();
        if(Build.VERSION.SDK_INT <= 19) {
            this.pushSideChannelQueue(new CancelTask("com.esotericsoftware.gloomhavenhelper"));
        }
    }

    @NonNull
    public static NotificationManagerCompat from(@NonNull Context context0) {
        return new NotificationManagerCompat(context0);
    }

    @NonNull
    public static Set getEnabledListenerPackages(@NonNull Context context0) {
        String s = Settings.Secure.getString(context0.getContentResolver(), "enabled_notification_listeners");
        synchronized(NotificationManagerCompat.sEnabledNotificationListenersLock) {
            if(s != null && !s.equals(NotificationManagerCompat.sEnabledNotificationListeners)) {
                String[] arr_s = s.split(":", -1);
                HashSet hashSet0 = new HashSet(arr_s.length);
                for(int v1 = 0; v1 < arr_s.length; ++v1) {
                    ComponentName componentName0 = ComponentName.unflattenFromString(arr_s[v1]);
                    if(componentName0 != null) {
                        hashSet0.add(componentName0.getPackageName());
                    }
                }
                NotificationManagerCompat.sEnabledNotificationListenerPackages = hashSet0;
                NotificationManagerCompat.sEnabledNotificationListeners = s;
            }
            return NotificationManagerCompat.sEnabledNotificationListenerPackages;
        }
    }

    public int getImportance() {
        return Build.VERSION.SDK_INT < 24 ? -1000 : this.mNotificationManager.getImportance();
    }

    public void notify(int v, @NonNull Notification notification0) {
        this.notify(null, v, notification0);
    }

    public void notify(@Nullable String s, int v, @NonNull Notification notification0) {
        if(NotificationManagerCompat.useSideChannelForNotification(notification0)) {
            this.pushSideChannelQueue(new NotifyTask("com.esotericsoftware.gloomhavenhelper", v, s, notification0));
            this.mNotificationManager.cancel(s, v);
            return;
        }
        this.mNotificationManager.notify(s, v, notification0);
    }

    private void pushSideChannelQueue(Task notificationManagerCompat$Task0) {
        synchronized(NotificationManagerCompat.sLock) {
            if(NotificationManagerCompat.sSideChannelManager == null) {
                NotificationManagerCompat.sSideChannelManager = new SideChannelManager(this.mContext.getApplicationContext());
            }
            NotificationManagerCompat.sSideChannelManager.queueTask(notificationManagerCompat$Task0);
        }
    }

    private static boolean useSideChannelForNotification(Notification notification0) {
        Bundle bundle0 = NotificationCompat.getExtras(notification0);
        return bundle0 != null && bundle0.getBoolean("android.support.useSideChannel");
    }
}

